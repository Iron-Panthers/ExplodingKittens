package Player;

import java.util.ArrayList;
import java.util.Scanner;


public class Player {
	
	public Scanner input = new Scanner(System.in);
	public ArrayList<String> numPlayers;
	public int playerNum;
	public int turns = 1;
	public ArrayList<Card> hand;
	public boolean hasCard;
	public Player() {
		hand = new ArrayList<Card>();
		this.numPlayers = new ArrayList<String>();
	}
	
	public void turn() {
		for (int i = 0; i < turns; i++) {
			System.out.print("What would you like to do, "+playerNum+"? Type usecard to use a card, showhand to see your hand, and endturn to draw and end your turn.");
			if(input.nextLine().equalsIgnoreCase("usecard")) {
				Main.player.useCard();
			}
			if(input.nextLine().equalsIgnoreCase("showhand")) {
				Main.player.showHand();
			}
			if(input.nextLine().equalsIgnoreCase("endturn")) {
				Main.player.endTurn();
			}
		}
	}
	public void endTurn() {
		hand.add(Main.deck.topCard());
		if (playerNum < numPlayers.size()) {
			playerNum++;
		} else {
			playerNum = 1;
		}
	
	}
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.print(hand.get(i));
		}
	}
	public void useCard() {
		boolean cardInHand = false;
		while (!cardInHand) {
			System.out.print("What card would you like to use?");
			Card playedCard = input.nextLine();
			if (hand.contains(playedCard)) {
				cardInHand = true;
				System.out.print("Playing "+playedCard+" .");
				hand.remove(playedCard);
				Main.deck.discard(playedCard);
			} else {
				System.out.print("You do not have "+playedCard+" in your hand.");
			}
		}
	}
	public void favor() {
		boolean cardInHand = false;
		while (!cardInHand) {
			System.out.print("What card would you like to give? Type the card name to give or nope to counter the favor.");
			Card chosenCard = input.nextLine();
			if (input.nextLine().equalsIgnoreCase("nope") && hand.contains(chosenCard)) {
				System.out.print("Favor countered");
				hand.remove(chosenCard);
				Main.deck.discard(chosenCard);
				cardInHand = true;
			} else if (hand.contains(chosenCard)) {
				cardInHand = true;
				System.out.print("Giving "+chosenCard+" .");
				hand.remove(chosenCard);
				Main.deck.discard(chosenCard);
			} else {
				System.out.print("You do not have "+chosenCard+" in your hand.");
			}
			
		}
	}
	public void explodes() {
		numPlayers.remove(numPlayers.get(playerNum));
	}
}
