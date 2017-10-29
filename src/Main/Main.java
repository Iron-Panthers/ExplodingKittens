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
	//public static boolean attack;
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static int lastPlayerAlive;
	public static ArrayList<Player> players;
	static ArrayList<Card> nonDescripts;
	public static int currentPlayer;
	public static int nextPlayer;
	public static boolean hasSkipped;

	//Constructors
	public static Deck deck;
	public static Scanner input;
	public static Random randomCard;
	
	public static void main(String[] args) {
		//attack = false;
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
			player.turns = 0;
			for(int x = 0; x < 4; x ++) {
				player.hand.add(deck.topCard());
			}
			player.hand.add(deck.defuse);
			//Removes a defuse from the deck for each player
			deck.deckList.remove(deck.defuse);
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
				players.get(i).turns+=1;
				while (players.get(i).turns > 0) {
					hasSkipped=false;
					players.get(i).turn();
					if (!hasSkipped) {
						if (getDrawnCard().type.equals(CardType.EXPLODING_KITTEN)) {
							explode();
						}
						else {
							System.out.println("You have drawn "+getDrawnCard().type);
							System.out.println("Would you like to defuse? Enter yes to defuse, or no to not defuse.");
							boolean isChoosing = true;
							while (isChoosing) {
								String defuseChoice = input.nextLine();
								if (defuseChoice.equalsIgnoreCase("yes")) {
									defuse(false);
									isChoosing = false;
								}
								if (defuseChoice.equalsIgnoreCase("no")) {
									System.out.println("Not defusing. Moving to next player.");
									isChoosing = false;
								}
								else {
									System.out.println("That is not a valid choice.");
								}
							}
						}
					}
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
			if (!(players.get(i).playerName==(players.get(currentPlayer).playerName))) {
				System.out.println(players.get(i).playerName);
			}
		}
		boolean isChoosing = true;
		int playerLocation = 10; //Sets location of player input player in the array to where their chosen player is.
		System.out.println("Please enter the integer for the player name.");
		while (isChoosing) {
			try {
				int player = Integer.parseInt(input.nextLine());
				//For loop that checks if any of the players in the array have the name of int
				//Only chooses players other than targeter
				for (int i = 0; i<players.size(); i++) {
					if (players.get(i).playerName==player) {
						playerLocation = i; //Gets the player location
						isChoosing = false; 
					} //end if
				}
				 //end for
				if (playerLocation==10) {
					System.out.println("That is not a valid player. Please try again.");
				} 
				//Checks if player is trying to target themselves
				if (!(players.get(playerLocation).playerName==players.get(currentPlayer).playerName)){
					return players.get(playerLocation);
				}
				else {
					System.out.println("You cannot target yourself");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter an Arabic numeral.");
			}
		}
		return null;
	}
	public static void favor() {
		Player targeter = players.get(currentPlayer);
		Player victim = askForVictim();
		victim.showHand();
		System.out.println("What card would you like to give? Type the card name to give or nope to counter the favor.");
		boolean choosing = true;
		boolean victimHasCard = false;
		int victimCardLocation=100;
		while(choosing) {	
			CardType chosenType = Card.convertToCardType(input.nextLine());
			for (int i = 0; i<victim.hand.size();i++) {
				if (victim.hand.get(i).type.equals(chosenType)) {
					victimCardLocation=i;
					victimHasCard=true;
				}
			}
			if(!(victimCardLocation==100)){
				if (chosenType.equals(CardType.NOPE) && victimHasCard) { //Removes card, discards card if nope
					System.out.println("Favor countered");
					Card discardedCard = victim.hand.get(victimCardLocation);
					victim.hand.remove(victimCardLocation);   
					deck.discard(discardedCard);
					choosing = false;
				} else if (victimHasCard) { //Removes cards, discards card if given
					System.out.println("Giving "+victim.hand.get(victimCardLocation).type+" .");
					Card givenCard = victim.hand.get(victimCardLocation);
					victim.hand.remove(victimCardLocation); 
					targeter.hand.add(givenCard);
					choosing = false;
				} else {
					System.out.println("You do not have that card in your hand.");
				}
			}
			else {
				System.out.println("You do not have that card in your hand");
			}
		}
	}
	public static void shuffle() {
		deck.shuffle();
		//players.get(currentPlayer).hand.remove(deck.shuffle);
		System.out.println("You have succesfully randomly shuffled the deck");
	}
	public static void attack() { 
		if (!askForNope()) {
			players.get(nextPlayer).turns += 1;
			//System.out.println(players.get(nextPlayer).turns);
			hasSkipped = true;
			players.get(currentPlayer).endTurnNoDraw();
			//attack = false;
		}
	}
	public static boolean askForNope() { //Only for skip and attack, which always target the next player
		boolean isChoosing = true;
		System.out.println("Player "+players.get(nextPlayer).playerName+", would you like to nope? 'yes' to nope or 'no' to not nope.");
		while (isChoosing) {
			String isNoping = input.nextLine(); 
			if (isNoping.equalsIgnoreCase("yes")) {
				//Check if they have a nope
				for (int i = 0; i<players.get(nextPlayer).hand.size()-1;i++) {
					if (players.get(nextPlayer).hand.get(i).type.equals(CardType.NOPE)) {
						System.out.println("Card has been countered");
						//Discards nope
						deck.discard(players.get(nextPlayer).hand.remove(i));
						return true;
					}
				}
				break;
			}
			if (isNoping.equalsIgnoreCase("no")) {
				break;
			}
			else {
				System.out.println("Please type 'yes' or 'no'");
			}
		}
		return false;
	}
	public static void skip() {
		if (!askForNope()) {
			//Skips
			hasSkipped=true;
			players.get(currentPlayer).endTurnNoDraw();
		}
	}
	public static void defuse(boolean isExploding) { //Works for any card, not just exploding kittens
		Player defuser = players.get(currentPlayer);
		Card drawnCard=getDrawnCard();
		boolean hasDefuse = false;
		//Checks for defuse
		for (int i = 0;i<defuser.hand.size()-1;i++) {
			if (defuser.hand.get(i).type.equals(CardType.DEFUSE)) {
				hasDefuse = true;
				Card usedDefuse = defuser.hand.remove(i);
				deck.discard(usedDefuse);
				continue;
			}
		}
		if (hasDefuse) { //Checks if player has defuse, then it defuses the card and puts it back in the deck anywhere the player chooses
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
					else {
						System.out.println("Please enter an integer from 0 to "+ deckSizeMinusOne + ".");
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter an integer from 0 to "+ deckSizeMinusOne + ".");
				}
			}
			//Puts the defused card back into the deck
			deck.deckList.add(deckLocation, drawnCard); 
			defuser.hand.remove(drawnCard);
			System.out.println("You have defused.");
			defuser.endTurnNoDraw();
		}
		else if(isExploding){
			System.out.println("You do not have a defuse in hand.");
			System.out.println("You die");
			//discards hand
			for(int i = 0; i<defuser.hand.size()-1;i++) {
				deck.discard(defuser.hand.get(i));
				defuser.hand.remove(i);
			}
			players.remove(defuser);
		}
		else {
			System.out.println("You do not have a defuse in hand.");
		}
	}
	public static void explode() {
		System.out.println("You have drawn an exploding kitten.");
		defuse(true);
	}
	public static void seeTheFuture() {
		ArrayList<Card> tempView = new ArrayList<Card>();
		//Amount of cards to show
		int tempViewReturn = 3;
		//Shows cards
		for (int i = 0; i<tempViewReturn; i++) {
			tempView.add(0,deck.topCard());
		}
		System.out.println("Top\tSecond from top\tThird from top");
		System.out.println(tempView);
		//Puts cards back
		while(tempView.size()>0) {
			tempViewReturn--;
			if (tempViewReturn>=0) {
				deck.deckList.add(0,tempView.get(tempViewReturn));
			}
			else {
				break;
			}
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
	public static boolean threeOfAKindSteal(CardType type) {
		CardType chosenNonDescript = type;
		boolean isChoosing = true;
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
		//Temporarily makes hand to check if player has two, and if it does, remove those two from hand
		ArrayList<Card> temp = stealer.hand;
		int amtOfNonDescripts = 0;
		//checks if player has two remaining nondescripts of that type
		for (int i = 0;i<temp.size()-1;i++) {
			if (temp.get(i).type.equals(chosenNonDescript)) {
				temp.remove(i);
				amtOfNonDescripts++;
			}
		}
		if (amtOfNonDescripts>=2) {
			for (int i = 0;i<2;i++) {
				if (stealer.hand.get(i).type.equals(chosenNonDescript)) {
					deck.discard(stealer.hand.remove(i));
					//Discards two of the nonDescripts
				}
			}
			//Now player targets someone, chooses 
			Player victim = askForVictim();
			System.out.println("Player " + victim.playerName +" has:");
			for (int i = 0; i<victim.hand.size(); i++) { //prints out the cards in the player's hand
				System.out.println(i + ": " + victim.hand.get(i));
			}
			while (isChoosing) {
				System.out.println("Which card would you like? (Say a number listed)");
				String ui = input.nextLine();
				try {
					int num = Integer.parseInt(ui);
					stealer.hand.add(victim.hand.get(num));
					System.out.println("You stole card " + victim.hand.get(num).type + " from player " + victim);
					deck.discard(victim.hand.remove(num));
					return true;
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid imput");
				}
			}
			
		}
		else {
			System.out.println("You do not have two remaining nonDescripts.");
		}
		return false;
	}
	public static Card getDrawnCard() {
		Player drawer = players.get(currentPlayer);
		return drawer.hand.get(drawer.hand.size()-1);
	}
	public static boolean twoOfAKindSteal(CardType type) {
		CardType chosenNonDescript = type;
		Player targeter = players.get(currentPlayer);
		Player victim = askForVictim();
		//Asks player for nonDescript
		boolean isChoosing = true;
		while (isChoosing) {
			//Checks if hand has two of the nonDescripts
			for (int i = 0; i<players.get(currentPlayer).hand.size();i++) {
				//If contains nonDescript:
				if (players.get(currentPlayer).hand.get(i).type.equals(chosenNonDescript)){
					deck.discard(players.get(currentPlayer).hand.remove(i));
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
								return true;								
							}					
						}
						//Resolves
						if (isNoping.equalsIgnoreCase("no")) {
							int randCard = randomCard.nextInt(victim.hand.size());
							Card givenCard = victim.hand.get(randCard);
							victim.hand.remove(givenCard);
							targeter.hand.add(givenCard);
							isChoosing = false;
							return true;
						}
						else {
							System.out.println("Please choose again. Yes or No.");
						}
					}
				}
			}
		}
		return false;
	}
}
