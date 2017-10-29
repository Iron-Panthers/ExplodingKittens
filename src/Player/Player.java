package Player;

import java.util.ArrayList;
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
	boolean wrongCard = true;
	boolean noCard = false;
	public void turn() {
		boolean isChoosing = true;
		System.out.println("What would you like to do, player "+playerName+"? Type \"usecard\" to use a card, \"showhand\" to see your hand, and \"endturn\" to draw and end your turn.");
		while(isChoosing) {
			String userInput = input.nextLine(); 
			if(userInput.equalsIgnoreCase("usecard")) {
				while(wrongCard) {
					noCard = false;
					Card chosenCard = chooseCard();
					if (chosenCard == null) {
						if (noCard) {
							break;
						}
						else{
							System.out.println("please enter a valid card");
						}
					}
					if (!(chosenCard == null)) {
						playCard(chosenCard);
						wrongCard = false;
					}
				}
				System.out.println("Please type one of the following options:\nusecard\nshowhand\nendturn");
			}
			if (userInput.equalsIgnoreCase("showhand")) {
				showHand();
				System.out.println("Please type one of the following options:\nusecard\nshowhand\nendturn");
			}
			else if (userInput.equalsIgnoreCase("endturn")) {
				endTurn();
				isChoosing = false;
			}
			else {
				System.out.println("Please type one of the following options:\nusecard\nshowhand\nendturn");
			}
		}
			
	}
	public void endTurn() {
		hand.add(Main.deck.topCard());
		Main.players.get(Main.currentPlayer).turns--;
	}
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i).toString());
		}
	}
	public Card chooseCard() {
		System.out.println("What card would you like to use? Type nocard to not play a card.");
		boolean shilohLovesCalc = true;
		Card cardToReturn;
		String cardInput;
		while (shilohLovesCalc) {
			while (true) {
				try {
					cardInput = input.nextLine();
					break;
				}
				catch(Exception e){
					System.out.println("please enter either a valid card, or nocard.");
				}
			}
			
			try {
				CardType cardType = Card.convertToCardType(cardInput);
				if (cardType == CardType.DEFUSE) {
					return null;
				}
				for (int i = 0;i<hand.size();i++) {
					if (hand.get(i).type.equals(cardType)) {
						cardToReturn = hand.remove(i);
						Main.deck.discard(cardToReturn);
						return cardToReturn;
					}
				}
			} 
			catch (IllegalArgumentException CardNoExist) {
				if (cardInput.equalsIgnoreCase("nocard")) {
					noCard = true;
					return null;
				}
				System.out.println("The card does not exist. Please try again, or enter nocard to not play a card.");
			}
		}
		return null;
	}	
	public void playCard(Card cardToPlay) {
		switch(cardToPlay.type) {
			case ATTACK:
				Main.players.get(Main.nextPlayer).turns += 2;
				//Main.players.get(Main.currentPlayer).hand.remove(Main.deck.attack);
				Main.players.get(Main.currentPlayer).endTurn();
				Main.attack = false;
				break;
			case SKIP:
				Main.skip();
				break;
			case SEE_THE_FUTURE:
				Main.seeTheFuture();
				break;
			case SHUFFLE:
				Main.shuffle();
				break;
			case FAVOR:
				Main.favor();
				break;
			case RAINBOW_RALPHING_CAT:
				nonDescriptOptions();
				break;
			case CATTERMELON:
				nonDescriptOptions();
				break;
			case TACOCAT:
				nonDescriptOptions();
				break;
			case HAIRY_POTATO_CAT:
				nonDescriptOptions();
				break;
			case BEARD_CAT:
				nonDescriptOptions();
				break;
			default:
				break;
		}
	}
	public boolean isCardInHand(Card chosenCard) {
		return hand.contains(chosenCard);
	}
	public void nonDescriptOptions() {
		boolean isChoosing = true;
		System.out.println("Would you like to do a two or three of a kind steal? Enter '2' for 2 of a kind, '3' for three of a kind");
		while (isChoosing) {
			try {
				int pChoice = Integer.parseInt(input.nextLine());
				if (pChoice == 2) {
					Main.twoOfAKindSteal();
					isChoosing = false;
				}
				if (pChoice == 3) {
					Main.threeOfAKindSteal();
					isChoosing = false;
				}
				else {
					System.out.println("Please enter a valid number. 2 or 3.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter an Arabic numeral.");
			}
		}
	}
}