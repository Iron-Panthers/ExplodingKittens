package Player;

import java.util.ArrayList;
import java.util.Scanner;

//make each plaer's hand separate. Construct 4 separate hands. Distinguish between the player who is currently playing.
public class Player {
	
	public Scanner input = new Scanner(System.in);
	public ArrayList<String> players;
	public int playerNum = 1;
	public int turns = 1;
	public ArrayList<Card> hand;
	public boolean hasCard;
	public Player() {
		hand = new ArrayList<Card>();
		players = new ArrayList<String>();
	}
	
	public void turn() {
		turns = 1;
		for (int i = 0; i < turns; i++) {
			System.out.println("What would you like to do, Player "+playerNum+"? Type usecard to use a card, showhand to see your hand, and endturn to draw and end your turn.");
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
		if (playerNum < players.size()) {
			playerNum++;
		} else {
			playerNum = 1;
		}
	
	}
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i));
		}
	}
	public void useCard() {
		boolean cardInHand = false;
		while (!cardInHand) {
			System.out.println("What card would you like to use?");
			Card playedCard = input.nextLine();
			if (hand.contains(playedCard)) {
				System.out.println("Playing "+playedCard+" .");
				hand.remove(playedCard);
				Main.deck.discard(playedCard);
				cardInHand = true;
			} else {
				System.out.println("You do not have "+playedCard+" in your hand.");
			}
		}
	}
	public void favor() {
		boolean cardInHand = false;
		while (!cardInHand) {
			System.out.println("What card would you like to give? Type the card name to give or nope to counter the favor.");
			Card chosenCard = input.nextLine();
			if (input.nextLine().equalsIgnoreCase("nope") && hand.contains(chosenCard)) {
				System.out.println("Favor countered");
				hand.remove(chosenCard);
				Main.deck.discard(chosenCard);
				cardInHand = true;
			} else if (hand.contains(chosenCard)) {
				System.out.println("Giving "+chosenCard+" .");
				hand.remove(chosenCard);
				Main.deck.discard(chosenCard);
				cardInHand = true;
			} else {
				System.out.println("You do not have "+chosenCard+" in your hand.");
			}
			
		}
	}
	public void explodes() {
		players.remove(players.get(playerNum));
	}
}
