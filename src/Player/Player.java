package Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Main.Main;
import card.Card;
import card.CardType;

public class Player {
	
	public int playerName;
	public Scanner input = new Scanner(System.in);
	public int turns;
	public ArrayList<Card> hand;
	
	public Player(int name) {
		hand = new ArrayList<Card>();
		playerName = name;
	}
	boolean wrongCard = false;
	boolean stillPlayingCards = true;
	public void turn() {
		boolean isChoosing = true;
		System.out.println("What would you like to do, player " + playerName + "?");
		while(isChoosing) {
			System.out.println("Type \"usecard\" to use a card, \"showhand\" to see your hand, and \"endturn\" to draw and end your turn.");
			String userInput = input.nextLine(); 
			if(userInput.equalsIgnoreCase("usecard")) {
				while(stillPlayingCards) {
					wrongCard = false;
					System.out.println("What card would you like to use?");
					Card chosenCard = chooseCard();
					playCard(chosenCard);
					if (wrongCard) {
						System.out.println("please choose a valid card. type nocard if you would not like to play a card.");
						if (input.hasNext("nocard")) {
							stillPlayingCards = false;
						}
					}
				}
			}
			if (userInput.equalsIgnoreCase("showhand")) {
				showHand();
			}
			else if (userInput.equalsIgnoreCase("endturn")) {
				endTurn();
				isChoosing = false;
			}
		}
			
	}
	public void endTurn() {
		hand.add(Main.deck.topCard());
		Main.players.get(Main.tempIterations).turns--;
	}
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i).toString());
		}
	}
	public Card chooseCard() {
		Card cardToReturn;
		while (true) {
			try {
				String cardInput = input.nextLine();
				CardType cardType = Card.convertToCardType(cardInput);
				for (int i = 0;i<hand.size()-1;i++) {
					if (hand.get(i).type.equals(cardType)) {
						cardToReturn = hand.get(i);
						return cardToReturn;
					}
				}
			}
			catch(IllegalArgumentException e) {
				System.out.println("please enter a valid card type");
			}
		}
	}	
	public void playCard(Card cardToPlay) {
		switch(cardToPlay.type) {
			case ATTACK:
				Main.players.get(Main.currentPlayer).endTurn();
				Main.players.get(Main.nextPlayer).turns += 2;
				Main.attack = false;
				Main.players.get(Main.currentPlayer).turns --;
				stillPlayingCards = false;
				break;
			case SKIP:
				Main.skip();
				stillPlayingCards = false;
				break;
			case SEE_THE_FUTURE:
				Main.seeTheFuture();
				stillPlayingCards = false;
				break;
			case SHUFFLE:
				Main.shuffle();
				stillPlayingCards = false;
				break;
			case FAVOR:
				Main.favor();
				stillPlayingCards = false;
				break;
			case RAINBOW_RALPHING_CAT:
				nonDescriptOptions();
				stillPlayingCards = false;
				break;
			case CATTERMELON:
				nonDescriptOptions();
				stillPlayingCards = false;
				break;
			case TACOCAT:
				nonDescriptOptions();
				stillPlayingCards = false;
				break;
			case HAIRY_POTATO_CAT:
				nonDescriptOptions();
				stillPlayingCards = false;
				break;
			case BEARD_CAT:
				nonDescriptOptions();
				stillPlayingCards = false;
				break;
			default:
				wrongCard = true;
		}
	}
	public boolean isCardInHand(Card chosenCard) {
		return hand.contains(chosenCard);
	}
	public void nonDescriptOptions() {
		boolean isChoosing = true;
		System.out.println("Welcome" + playerName + "Would you like to do a two or three of a kind steal? Enter '2' for 2 of a kind, '3' for three of a kind");
		while (isChoosing) {
			try {
				int pChoice = Integer.parseInt(input.nextLine());
				if (pChoice==2) {
					Main.twoOfAKindSteal();
					isChoosing = false;
				}
				if (pChoice==3) {
					Main.threeOfAKindSteal();
					isChoosing = false;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter an Arabic numeral.");
			}
		}
	}
}