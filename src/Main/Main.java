package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import Deck.Deck;
import Player.Player;
import card.Card;
import card.CardType;

public class Main{
	public static boolean attack;
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static int lastPlayerAlive;
	public static ArrayList<Player> players;
	static ArrayList<Card> nonDescripts;
	public static int currentPlayer;
	public static int nextPlayer;

	//Constructors
	public static Deck deck;
	public static Scanner input;
	public static Random randomCard;
	
	public static void main(String[] args) {
		attack = false;
		input = new Scanner(System.in);
		deck = new Deck(explodingKittenNum);
		randomCard = new Random();
		players = new ArrayList<Player>();
		nonDescripts = new ArrayList<Card>();
		nonDescripts.add(deck.catterMelon);
		nonDescripts.add(deck.beardCat);
		nonDescripts.add(deck.hairyPotatoCat);
		nonDescripts.add(deck.rainbowRalphingCat);
		nonDescripts.add(deck.tacoCat);
		System.out.print("Welcome to Exploding Kittens. ");
		System.out.println("How many people are playing?");
		boolean isWrongCard = true;
		while (isWrongCard) {
			try {
				numPlayers = input.nextInt();
				if (numPlayers < 2 || numPlayers > 4) {
					System.out.println("please enter an integer from 2-4");
					continue;
				} 
				isWrongCard = false;
			}
			catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("please enter an integer");
				continue;
			}
		}
		//Adds players to the arrayList players
		for (int i = 1; i <= numPlayers; i++) {
			Player player = new Player(i);
			players.add(player);
			for(int x = 0; x < 4; x ++) {
				player.hand.add(deck.topCard());
			}
			player.hand.add(deck.defuse);
		}
		//After players draw their cards, it puts exploding kittens into the deck
		deck.fill(deck.explodingKitten,explodingKittenNum);
		deck.shuffle();
		//Game loop
		while (players.size() > 1) {
			//for each player, turn
			for (int i = 0; i % players.size() < players.size(); i=(i + 1) % players.size()) {
				//Checks if someone has won
				if (players.size() == 1) {
					break;
				}
				nextPlayer = (i+1) % players.size();
				currentPlayer = i;
				players.get(i).turns=1;
				while (players.get(i).turns > 0) {
					players.get(i).turn();
				}
				if (getDrawnCard().type.equals(CardType.EXPLODING_KITTEN)) {
					explode();
				}
			}
		}
		System.out.println(players.get(0).playerName + " won!"); //executes after while loop exits
	}
	public static Player askForVictim() {
		System.out.println("Who would you like to target?");
		System.out.println("Would you like to target: ");
		//Cycles through array and prints
		for (int i = 0; i<players.size(); i++) {
			System.out.println(players.get(i).playerName);
		}
		boolean isChoosing = true;
		int playerLocation = 10; //Sets location of player input player in the array to where their chosen player is.
		System.out.println("Please enter the integer for the player name.");
		while (isChoosing) {
			try {
				int player = Integer.parseInt(input.nextLine());
				//For loop that checks if any of the players in the array have the name of int
				for (int i = 0; i<players.size(); i++) {
					if (players.get(i).playerName==player) {
						playerLocation = i; //Gets the player location
						isChoosing = false; 
					} //end if
				} //end for
				if (playerLocation==10) {
					System.out.println("That is not a valid player. Please try again.");
				} //end if
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter an Arabic numeral.");
			}
		}
		Player victim = players.get(playerLocation);
		return victim;
	}
	public static void favor() {
		Player targeter = players.get(currentPlayer);
		Player victim = askForVictim();
		victim.showHand();
		System.out.println("What card would you like to give? Type the card name to give or nope to counter the favor.");
		boolean choosing = true;
		while(choosing) {	
			Card chosenCard = new Card(Card.convertToCardType(input.nextLine()));
			if (input.nextLine().equalsIgnoreCase("nope") && victim.hand.contains(chosenCard)) { //Removes card, discards card if nope
				System.out.println("Favor countered");
				victim.hand.remove(chosenCard);   
				deck.discard(chosenCard);
				targeter.hand.remove(deck.favor);
				deck.discard(deck.favor);
				choosing = false;
			} else if (victim.hand.contains(chosenCard)) { //Removes cards, discards card if given
				System.out.println("Giving "+chosenCard+" .");
				victim.hand.remove(chosenCard);
				targeter.hand.add(chosenCard);
				targeter.hand.remove(deck.favor);
				deck.discard(deck.favor);
				choosing = false;
			} else {
				System.out.println("You do not have "+chosenCard+" in your hand.");
			}
		}
	}
	public static void shuffle() {
		deck.shuffle();
		//players.get(currentPlayer).hand.remove(deck.shuffle);
		System.out.println("You have succesfully randomly shuffled the deck");
	}
	public static void skip() {
		//players.get(currentPlayer).hand.remove(deck.skip);
		endTurnNoDraw();
	}
	public static void attack() { //Not used
		attack = true;
		//Ends turn, next player must take two turns
	}
	public static void defuse() { //Works for any card, not just exploding kittens
		Player defuser = players.get(currentPlayer);
		Card drawnCard;
		drawnCard = getDrawnCard();
		if (defuser.hand.contains(deck.defuse)) { //Checks if player has defuse, then it defuses the card and puts it back in the deck anywhere the player chooses
			System.out.println("Where would you like to place the card");
			int deckSizeMinusOne = deck.deckList.size()-1;
			System.out.println("You can place the card anywhere in the deck from the top, 0, to "+deckSizeMinusOne);
			boolean shilohLovesCalc = true;
			int deckLocation = 0; //intializes deckLocation
			while (shilohLovesCalc) {
				try {
					deckLocation = Integer.parseInt(input.nextLine());
					if (deckLocation >= 0 && deckLocation < deck.deckList.size()) {
						break;
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter an integer from 0 to "+ deckSizeMinusOne + ".");
				}
			}
			defuser.hand.remove(drawnCard); //Removes the defused card from hand
			deck.deckList.add(deckLocation, drawnCard); //Puts the defused card back into the deck
			//defuser.hand.remove(deck.defuse); //Discards defuse from hand, places it in discard pile.
			//deck.discard(deck.defuse);
			System.out.println("You have defused.");
			endTurnNoDraw();
		}
		else {
			System.out.println("You do not have a defuse in hand.");
		}
	}
	public static void explode() {
		Player exploder = players.get(currentPlayer);
		System.out.println("You have drawn an exploding kitten. Type 'defuse' to defuse, type 'ok' to die");
		String usrInput = input.nextLine();
		if (usrInput.equalsIgnoreCase("defuse")) {
			defuse();
		}
		else {
			System.out.println("You die");
			players.remove(exploder);
		}
	}
	public static void seeTheFuture() {
		Player cardViewer = players.get(currentPlayer);
		ArrayList<Card> tempView = new ArrayList<Card>();
		int tempViewReturn = 3;
		//Discards card
		deck.discard(deck.seeTheFuture);
		cardViewer.hand.remove(deck.seeTheFuture);
		//Shows cards
		for (int i = 0; i<3; i++) {
			tempView.add(deck.topCard());
			System.out.println(tempView.get(i));
		}
		//Puts cards back
		while(tempView.size()>0) {
			tempViewReturn--;
			deck.deckList.add(0,tempView.get(tempViewReturn));
		}
		System.out.println("See the future is now over");
	}
	public static void draw() {
		Player drawer = players.get(currentPlayer);
		Card drawnCard = deck.topCard();
		drawer.hand.add(drawnCard);
		if (drawnCard.equals(deck.explodingKitten)) {
			explode();
		}
		else {
			//Does Nothing
		}
	}
	public static void threeOfAKindSteal() {
		
		/**
		 * 
		 * Hey guys. This code does something other than that is in the rules. It is how I played at home.
		 * 
		 * It shows the victim's deck to the stealer and asks the stealer to pick one card of their choise.
		 * 
		 * If you keep it I will be happy but if you change/remove it, I will understand.
		 * 
		 */
		
		Player stealer = players.get(currentPlayer);
		Player victim = askForVictim();
		System.out.println("Player " + victim +" has:");
		for (int i = 0; i<victim.hand.size(); i++) { //prints out the cards in the player's hand
			System.out.println(i+1 + ": " + victim.hand.get(i));
		}
		System.out.println("Which card would you like? (Say a number listed)");
		String ui = input.nextLine();
		try {
			int num = Integer.parseInt(ui);
			stealer.hand.add(victim.hand.get(num));
			System.out.println("You stole card " + victim.hand.get(num) + " from player " + victim);
			victim.hand.remove(num);

		}
		catch(NumberFormatException e) {
			System.out.println("Invalid imput");
			threeOfAKindSteal();
		}
	}
	public static Card getDrawnCard() {
		Player drawer = players.get(currentPlayer);
		int drawnCardIndex = 0;
		for (int i = 0; i<drawer.hand.size(); i++) { //Gets the last card in the player's hand, which is the card they last drew
			drawnCardIndex++;
		}
		return drawer.hand.get(drawnCardIndex);
	}
	public static void endTurnNoDraw() { //Ends turn but places back card player would have drawn normally in the endTurn method. Possibly needs to be added in the 0 location in the deck
		Player skipper = players.get(currentPlayer);
		skipper.turns--;
	}
	public static void twoOfAKindSteal() {
		Player targeter = players.get(currentPlayer);
		Player victim = askForVictim();
		//Asks player for nonDescript
		boolean isChoosing = true;
		while (isChoosing) {
			System.out.println("What Non-Descript would you like to play?");
			String playerInput = input.nextLine();
			CardType chosenType = Card.convertToCardType(playerInput);
			if (chosenType.equals(CardType.ATTACK)||chosenType.equals(CardType.DEFUSE)||chosenType.equals(CardType.FAVOR)||chosenType.equals(CardType.NOPE)||chosenType.equals(CardType.SEE_THE_FUTURE)||chosenType.equals(CardType.SHUFFLE)||chosenType.equals(CardType.SKIP)) {
				System.out.println("Please enter a non-descript.");
			}
			else {
				
				//Checks if hand has two of the nonDescripts
				for (int i = 0; i<players.get(currentPlayer).hand.size();i++) {
					//If contains nonDescript:
					if (players.get(currentPlayer).hand.get(i).type.equals(chosenType)){
						deck.discard(players.get(currentPlayer).hand.get(i));
						players.get(currentPlayer).hand.remove(i);
						//Ask for nope
						System.out.println("Player "+victim.playerName+", would you like to nope? Say 'yes' to nope, 'no' to not nope");
						boolean choosingNope = true;
						while (choosingNope) {
							String isNoping = input.nextLine();
							if (isNoping.equalsIgnoreCase("yes")) {
								if (victim.hand.contains(deck.nope)) {
									victim.hand.remove(deck.nope);
									deck.discard(deck.nope);
									System.out.println("Two of a kind has been countered.");
									isChoosing = false;
									choosingNope = false;
								}					
							}
							//Resolves
							if (isNoping.equalsIgnoreCase("no")) {
								int randCard = randomCard.nextInt(victim.hand.size());
								Card givenCard = victim.hand.get(randCard);
								victim.hand.remove(givenCard);
								targeter.hand.add(givenCard);
								choosingNope = false;
								isChoosing = false;
							}
							else {
								System.out.println("Please choose again. Yes or No.");
							}
						}
					}
				}
				System.out.println("You do not have those non-descripts");
			}
		}
	}
}
