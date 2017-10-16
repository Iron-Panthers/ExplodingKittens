package Player;

import java.util.ArrayList;
import java.util.Scanner;

import javax.smartcardio.Card;

import sun.applet.Main;

public class Player {
	
	boolean attack = false;
	
	public Scanner input = new Scanner(System.in);
	public int playerNum = 1;
	public int turns = 1;
	public ArrayList<Card> hand;
	
	public Player() {
		hand = new ArrayList<Card>();
	}
	
	public void turn() {
		turns = +1;
		while (turns > 0) {
			attack = Main.attack();
			if (attack) {
				turns = +2;
			}
			System.out.println("What would you like to do, Player "+playerNum+"? Type usecard to use a card, showhand to see your hand, and endturn to draw and end your turn.");
			if(input.nextLine().equalsIgnoreCase("usecard")) {
				choseCard();
			}
			if(input.nextLine().equalsIgnoreCase("showhand")) {
				showHand();
			}
			if(input.nextLine().equalsIgnoreCase("endturn")) {
				endTurn();
			}
		}
	}
	public void endTurn() {
		hand.add(Main.deck.topCard());
		turns--;
	}
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i));
		}
	}
	public Card choseCard() {
		boolean hasCard = false;
		while(!hasCard) {
			System.out.println("What card would you like to chose?");
			Card chosenCard = Main.card.convertToCardType(input.nextLine());
			if (isCardInHand(chosenCard)) {
				System.out.println("Playing "+chosenCard+" .");
				hand.remove(chosenCard);
				Main.deck.discard(chosenCard);
				hasCard = true;
			} else {
				System.out.println("You do not have "+chosenCard+" in your hand.");
			}
		}
	}
//	public void favor() {
//		System.out.println("What card would you like to give? Type the card name to give or nope to counter the favor.");
//		Card chosenCard = input.nextLine();
//		if (input.nextLine().equalsIgnoreCase("nope") && isCardInHand(chosenCard)) {
//			System.out.println("Favor countered");
//			hand.remove(chosenCard);
//			Main.deck.discard(chosenCard);
//		} else if (hand.contains(chosenCard)) {
//			System.out.println("Giving "+chosenCard+" .");
//			hand.remove(chosenCard);
//			Main.deck.discard(chosenCard);
//		} else {
//			System.out.println("You do not have "+chosenCard+" in your hand.");
//		}
//	}
	public boolean isCardInHand(Card chosenCard) {
		return hand.contains(chosenCard);
	}
}
