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
			if (input.nextLine().equalsIgnoreCase("usecard")) {
				
			}
		}
	}
	public void endTurn(Card topCard) {
		hand.add(topCard);
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
			String playedCard = input.nextLine();
			if (hand.contains(playedCard)) {
				cardInHand = true;
				System.out.print("Playing "+playedCard+" .");
				hand.remove(playedCard);
			} else {
				System.out.print("You do not have "+playedCard+" in your hand.");
			}
		}
	}
	public void favor() {
		
	}
	public void playerNope() {
		
	}
	public void loseCard() {
		hand.remove(card);
	}
	public void explodes() {
		numPlayers.remove(numPlayers.get(playerNum));
	}
}
