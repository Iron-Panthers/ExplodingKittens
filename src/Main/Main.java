package Main;

import java.util.ArrayList;
import java.util.Scanner;

import javax.smartcardio.Card;

import CardPackage.*;
import Deck.Deck;
import Player.*;

public class Main{
	public static boolean attack = false;
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static Deck deck;
	static int playersAlive = numPlayers;
	static int lastPlayerAlive;
	static ArrayList<Player> players;
	static Scanner input;
	public static void main(String[] args) {
		input = new Scanner(System.in);
		deck = new Deck(explodingKittenNum);
		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			String playerName = "player"+i;
			static Player playerName = new Player();
			players.add(playerName);
		}
		while (playersAlive > 1) {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).turn();
				if (attack) {
					i++;
					attack = false;
				}
			}
		}
		if(players.length==1) {
			System.out.println(Player[0]+" Won!!!");
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
		System.out.println("You have succesfuly randomly shuffled the deck");
	}
	public void skip(Player skipper) {
		skipper.endTurn();
	}
	public boolean attack(Player attacker) {
		attack = true;
		return attack;
		//Ends turn, next player must take two turns
	}
	public void favor(Player targeter, Player victim) {
		//Targets a player, who chooses a card and gives it back to the targeter
	}
	public void nope(Player noper, Player victim) {
		//Targets a card that targets the noper
	}
	public void defuse(Player defuser) {
		Card drawnCard;
		int drawnCardIndex = 0;
		for (int i = 0; i<defuser.hand.size(); i++) {
			drawnCardIndex++;
		}
		drawnCard = defuser.hand.get(drawnCardIndex);
		if (defuser.hand.contains(Card defuse)) {
			System.out.println("Where would you like to place the card");
			int deckSizeMinusOne = deck.deckList.size()-1;
			System.out.println("You can place the card anywhere in the deck from the top, 0, to "+deckSizeMinusOne);
			while(!input.hasNextInt()) {
				System.out.println("Please enter an integer from 0 to"+deckSizeMinusOne);
			}
			int deckLocation = input.nextInt();
			deck.deckList.add(deckLocation, Card drawnCard);
			exploder.hand.remove(Card defuse);
			deck.discard(Card defuse);
		}
		defuser.endTurn();
		defuser.remove();
		deck.discard(cardTypes.defuse);
		System.out.println("You have been saved.");
	}
	public void explode(Player exploder) { //NOT FINISHED
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
			deck.deckList.add(tempView.get(tempViewReturn));
		}
		System.out.println("See the future is now over");
	}
	public void draw(Player drawer) {
		Card drawnCard = deck.topCard();
		drawer.hand.add(drawnCard);
		if (drawnCard = explodingKitten) {
			explode(drawer);
		}
		else {
			//Does Nothing
		}
	}
}
