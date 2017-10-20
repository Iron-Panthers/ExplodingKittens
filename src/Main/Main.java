package Main;
//Main.attack;
//Main.attack();
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Deck.Deck;
import Player.Player;
import card.Card;

public class Main{
	public static boolean attack;
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static int lastPlayerAlive;
	static ArrayList<Player> players;
	static ArrayList<Card> nonDescripts;

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
		//Adds players to the arrayList players
		for (int i = 1; i < numPlayers; i++) {
			String temp = "player"+i;
			//Make variable temp? Set it to player+num
			//String playerName = "player"+i; //Does not work, cannot make a string used as the player constructer.
			Player player = new Player(temp);
			players.add(player);
			for(i = 0; i < 7; i ++) {
				player.hand.add(deck.topCard());
			}
		}
		//Game loop
		while (players.size() > 1) {
			for (int i = 0; i % players.size() < players.size(); i=(i+1)%players.size()) {
				if (players.size()==1) {
					break;
				}
				int nextPlayer = (i+1) % players.size();
				players.get(i).turns=1;
				while (players.get(i).turns > 0) {
					players.get(i).turn();
					if (attack) {
						players.get(i).endTurn();
						players.get(nextPlayer).turns += 2;
						/**
						 * Main.attack = false;
						 * no need to do Main. if calling from main
						 */ 
						attack = false;
					}
					players.get(i).turns --;
				}
			}
		}
		/**
		if(players.size()==1) {
			System.out.println(players.get(0)+" Won!!!");
		}
		* We don't need to write this if statement.
		* This statement isn't reached until the while loop is done.
		* The while loop exits when playersAlive <= 1.
		* If playersAlive == 1, then players.size() == 1.
		*/
		System.out.println(players.get(0).playerName + " won!");
	}
	public Player askForVictim() {
		System.out.println("Who would you like to target?");
		System.out.println("Would you like to target: ");
		for (int i = 0; i<players.size(); i++) {
			System.out.println(players.get(i).playerName);
		}
		//Have input set to which player they target, 
		return victim;
	}
	public void favor(Player targeter, Player victim) {
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
	public void shuffle() {
		deck.shuffle();
		System.out.println("You have succesfuly randomly shuffled the deck");
	}
	public void skip(Player skipper) {
		endTurnNoDraw(skipper);
	}
	public void attack() {
		attack = true;
		//Ends turn, next player must take two turns
	}
	public void defuse(Player defuser) { //Works for any card, not just exploding kittens
		Card drawnCard;
		drawnCard = getDrawnCard(defuser);
		if (defuser.hand.contains(deck.defuse)) { //Checks if player has defuse, then it defuses the card and puts it back in the deck anywhere the player chooses
			System.out.println("Where would you like to place the card");
			int deckSizeMinusOne = deck.deckList.size()-1;
			System.out.println("You can place the card anywhere in the deck from the top, 0, to "+deckSizeMinusOne);
			while(!input.hasNextInt()) {
				System.out.println("Please enter an integer from 0 to"+deckSizeMinusOne);
			}
			int deckLocation = input.nextInt();
			defuser.hand.remove(drawnCard); //Removes the defused card from hand
			deck.deckList.add(deckLocation, drawnCard); //Puts the defused card back into the deck
			defuser.hand.remove(deck.defuse); //Discards defuse from hand, places it in discard pile.
			deck.discard(deck.defuse);
			System.out.println("You have defused.");
			endTurnNoDraw(defuser);
		}
		else {
			System.out.println("You do not have a defuse in hand.");
		}
	}
	public void explode(Player exploder) {
		System.out.println("You have drawn an exploding kitten. Type 'defuse' to defuse, type 'ok' to die");
		String usrInput = input.nextLine();
		if (usrInput.equalsIgnoreCase("defuse")) {
			defuse(exploder);
		}
		else {
			System.out.println("You die");
			players.remove(exploder);
		}
	}
	public void seeTheFuture(Player cardViewer) {
		ArrayList<Card> tempView = new ArrayList<Card>();
		int tempViewReturn = 3;
		for (int i = 0; i<3; i++) {
			tempView.add(deck.topCard());
			System.out.println(tempView.get(i));
		}
		while(tempView.size()>0) {
			tempViewReturn--;
			deck.deckList.add(0,tempView.get(tempViewReturn));
		}
		System.out.println("See the future is now over");
	}
	public void draw(Player drawer) {
		Card drawnCard = deck.topCard();
		drawer.hand.add(drawnCard);
		if (drawnCard.equals(deck.explodingKitten)) {
			explode(drawer);
		}
		else {
			//Does Nothing
		}
	}
	public void threeOfAKindSteal(Player stealer, Player victim) {
		System.out.println("Player " + victim +" has:");
		for (int i = 0; i<victim.hand.size(); i++) { //prints out the cards in the player's hand
			System.out.println(victim.hand.get(i));
		}
		//not finished
	}
	public Card getDrawnCard(Player drawer) {
		int drawnCardIndex = 0;
		for (int i = 0; i<drawer.hand.size(); i++) { //Gets the last card in the player's hand, which is the card they last drew
			drawnCardIndex++;
		}
		return drawer.hand.get(drawnCardIndex);
	}
	public void endTurnNoDraw(Player skipper) { //Ends turn but places back card player would have drawn normally in the endTurn method. Possibly needs to be added in the 0 location in the deck
		skipper.endTurn();
		deck.deckList.add(0,getDrawnCard(skipper));  
		skipper.hand.remove(getDrawnCard(skipper));
	}
	public void twoOfAKindSteal(Player targeter,Player victim, Card nonDescript) {
		ArrayList<Card> tempHand = new ArrayList<Card>();
		tempHand = targeter.hand;
		//Checks if hand has two of the nonDescripts
		if (nonDescripts.contains(nonDescript)) {
			if (tempHand.contains(nonDescript)) {
				tempHand.remove(nonDescript);
				if (tempHand.contains(nonDescript)) {
					tempHand.remove(nonDescript);
					System.out.println("You have both cards");
					targeter.hand.remove(nonDescript);
					targeter.hand.remove(nonDescript);
					deck.discard(nonDescript);
					deck.discard(nonDescript);
					System.out.println(victim.playerName+", would you like to nope? Say 'yes' to nope, 'no' to not nope");
					String isNoping = input.nextLine();
					boolean choosingNope = true;
					while (choosingNope) {
						if (isNoping.equalsIgnoreCase("yes")) {
							if (victim.hand.contains(deck.nope)) {
								victim.hand.remove(deck.nope);
								deck.discard(deck.nope);
								System.out.println("Two of a kind has been countered.");
								choosingNope = false;
							}
							
						}
						if (isNoping.equalsIgnoreCase("no")) {
							int randCard = randomCard.nextInt(victim.hand.size());
							Card givenCard = victim.hand.get(randCard);
							victim.hand.remove(givenCard);
							targeter.hand.add(givenCard);
							choosingNope = false;
						}
						else {
							System.out.println("Please choose again.");
						}
					}
				}
				else {
					System.out.println("You do not have those two cards");
				}
			}
			else {
				System.out.println("You do not have those two cards.");
			}
		}
		else {
			System.out.println("You must choose two non descripts");
		}
	}
}
