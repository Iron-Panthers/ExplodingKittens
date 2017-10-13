package Main;

import Deck.Deck;

public class Main{
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static Deck deck;
	static int playersAlive = numPlayers;
	public static void main(String[] args) {
		deck = new Deck(explodingKittenNum);
		while (playersAlive > 1) {
			
		}
	}
}
