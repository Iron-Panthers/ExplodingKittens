package Player;

import java.util.ArrayList;
import java.util.Scanner;

import Main.Main;
import card.Card;

public class Player {
	
	public int playerName;
	public Scanner input = new Scanner(System.in);
	public int turns;
	public ArrayList<Card> hand;
	
	public Player(int name) {
		hand = new ArrayList<Card>();
		playerName = name;
	}
	
	public void turn() {
		while (turns > 0) {
			System.out.println("What would you like to do, "+playerName+"? Type usecard to use a card, showhand to see your hand, and endturn to draw and end your turn.");
			if(input.nextLine().equalsIgnoreCase("usecard")) {
				try {
					if (chooseCard().type == Card.convertToCardType("skip")){
						Main.skip();
					}
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
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
	}
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i));
		}
	}
	public Card chooseCard() {
		System.out.println("What card would you like to chose?");
		try {
			Card chosenCard = new Card(Card.convertToCardType(input.nextLine()));
			return chosenCard;
		} catch (IllegalArgumentException CardNoExist) {
			System.out.println("The card does not exist. Please try again.");
			chooseCard();
		} 
		return null;
//		if (isCardInHand(chosenCard)) {
//			return chosenCard;
//		}			
//		else {
//			return null;
//		}		
	}	
//	public void playCard(Card cardToPlay) {
//		switch(cardToPlay.type) {
//			case attack:
//				Main.attack();
//				break;
//			case "skip":
//				Main.skip();
//				break;
//			case "seeTheFuture":
//				Main.seeTheFuture();
//				break;
//			case "shuffle":
//				Main.shuffle();
//				break;
//			case "favor":
//				Main.favor();
//				break;
//		}
//	}
	public boolean isCardInHand(Card chosenCard) {
		return hand.contains(chosenCard);
	}
}