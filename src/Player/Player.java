package Player;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  Is card in hand method
 */
public class Player {
	
	public Scanner input = new Scanner(System.in);
	public int playerNum = 1;
	public int turns = 1;
	public ArrayList<Card> hand;
	public boolean hasCard;
	
	public Player() {
		hand = new ArrayList<Card>();
	}
	
	public void turn() {
		while (turns > 0) {
			for (int i = 0; i < turns; i++) {
				System.out.println("What would you like to do, Player "+playerNum+"? Type usecard to use a card, showhand to see your hand, and endturn to draw and end your turn.");
				if(input.nextLine().equalsIgnoreCase("usecard")) {
					useCard();
				}
				if(input.nextLine().equalsIgnoreCase("showhand")) {
					showHand();
				}
				if(input.nextLine().equalsIgnoreCase("endturn")) {
					endTurn();
				}
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
	public void useCard() {
		System.out.println("What card would you like to use?");
		Card chosenCard = input.nextLine();
		if (hand.contains(chosenCard)) {
			System.out.println("Playing "+chosenCard+" .");
			hand.remove(chosenCard);
			Main.deck.discard(chosenCard);
		} else {
			System.out.println("You do not have "+playedCard+" in your hand.");
		}	
	}
	public Card choseCard() {
		System.out.println("What card would you like to chose?");
		Card chosenCard = convertToCardType(input.nextLine());
		if 
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
