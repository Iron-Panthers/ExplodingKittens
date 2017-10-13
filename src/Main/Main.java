package Main;

import Deck.Deck;
import CardPackage.*;
import Player.*;

public class Main{
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static Deck deck;
	static int playersAlive = numPlayers;
	public static void main(String[] args) {
		deck = new Deck(explodingKittenNum);
		for (int i = 0; i<numPlayers; i++) {
			String playerName = "player"+i;
			static Player playerName = new Player();
		}
		while (playersAlive > 1) {
			
		}
	}
	public void shuffle() {
		deck.shuffle();
	}
	public void skip() {
		//Does Stuff
		//Ends turn, no draw
	}
	public void attack() {
		//Ends turn, next player must take two turns
	}
	public void favor() {
		
	}
	
}
