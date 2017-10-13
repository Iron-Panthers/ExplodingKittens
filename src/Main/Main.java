package Main;

import Deck.Deck;
import CardPackage.*;
import Player.*;

public class Main{
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static Deck deck;
	static int playersAlive = numPlayers;
	static int lastPlayerAlive;
	public static void main(String[] args) {
		deck = new Deck(explodingKittenNum);4
		for (int i = 0; i<numPlayers; i++) {
			String playerName = "player"+i;
			static Player playerName = new Player();
		}
		while (playersAlive > 1) {
			
		}
		if(1==1/* checks player alive in array of players */) {
			lastPlayerAlive = 0 /* the only player alive */;
		}
	}
	
	public void favor() {
		System.out.println("What card would you like to give? Type the card name to give or nope to counter the favor.");
		Card chosenCard = input.nextLine();
		if (input.nextLine().equalsIgnoreCase("nope") && isCardInHand(chosenCard)) {
			System.out.println("Favor countered");
			hand.remove(chosenCard);
			Main.deck.discard(chosenCard);
		} else if (hand.contains(chosenCard)) {
			System.out.println("Giving "+chosenCard+" .");
			hand.remove(chosenCard);
			Main.deck.discard(chosenCard);
		} else {
			System.out.println("You do not have "+chosenCard+" in your hand.");
		}
	}
	public void shuffle() {
		deck.shuffle();
	}
	public void skip() {
		//Does Stuff
		//Ends turn, no draw
	}
	public void attack() {
		//Ends turn, next player must take two turns
	}
	public void favor() {
		
	}
	
}
