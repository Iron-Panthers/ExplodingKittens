package Main;

import java.util.ArrayList;
import java.util.Scanner;

import CardPackage.*;
import Deck.Deck;
import Player.*;

public class Main{
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static Deck deck;
	static int playersAlive = numPlayers;
	static ArrayList<Player> players;
	static Scanner input;
	public static void main(String[] args) {
		input = new Scanner(System.in);
		deck = new Deck(explodingKittenNum);
		players = new ArrayList<Player>();
		for (int i = 0; i<numPlayers; i++) {
			String playerName = "player"+i;
			static Player playerName = new Player();
			players.add(playerName);
		}
		while (playersAlive > 1) {
			
		}
	}
	public void shuffle() {
		deck.shuffle();
	}
	public void skip(Player skipper) {
		//Does Stuff
		//Ends turn, no draw
	}
	public void attack(Player attacker) {
		//Ends turn, next player must take two turns
	}
	public void favor(Player targeter, Player victim) {
		//Targets a player, who chooses a card and gives it back to the targeter
	}
	public void nope(Player noper, Player victim) {
		//Targets a card that targets the noper
	}
	public void defuse(Player defuser) {
		
	}
	public void explode(Player exploder) { //NOT FINISHED
		System.out.println("You have drawn an exploding kitten. Type 'defuse' to defuse, type 'ok' to die");
		String usrInput = input.nextLine();
		if (usrInput.equalsIgnoreCase("defuse")) {
			if (exploder.hand.contains(Card defuse)) {
				System.out.println("Where would you like to place the exploding kitten?");
				//System.out.println("You can place the exploding kitten anywhere in the deck from the top, 0, to "+deck.deckList.size()-1);
			}
		}
	}
	public void draw(Player drawer) {
		drawer.hand.add(deck.topCard());
	}
}
