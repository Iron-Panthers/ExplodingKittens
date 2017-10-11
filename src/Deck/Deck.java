package Deck;

import java.util.ArrayList;
import java.util.Collections;
import card.*;

public class Deck {
	public ArrayList<Card> deckList;
	//Player player; Do this in Main class
	
	//Non-descripts
	int rainbowRalphingCatNum = 4;
	int hairyPotatoCatNum = 4;
	int beardCatNum = 4;
	int tacoCatNum = 4;
	int catterMelonNum = 4;
	
	//Action Cards
	int explodingKittenNum;
	int seeTheFutureNum = 4;
	int shuffleNum = 4;
	int defuseNum = 6;
	int attackNum = 4;
	int skipNum = 4;
	int favorNum = 4;
	int nopeNum = 5;
	
	public Deck (int explodingKittenNumber) {
		//player = new Player(); Do this in main class
		explodingKittenNum=explodingKittenNumber;
		deckList = new ArrayList<Card>();
		fill(cardType.EXPLODINGKITTEN,explodingKittenNum);
		fill(cardType.SEETHEFUTURE,seeTheFutureNum);
		fill(cardType.SHUFFLE,shuffleNum);
		fill(cardType.DEFUSE,defuseNum);
		fill(cardType.ATTACK,attackNum);
		fill(cardType.SKIP,skipNum);
		fill(cardType.FAVOR,favorNum);
		fill(cardType.NOPE,nopeNum);
		shuffle();
	}
	public void shuffle() {
		Collections.shuffle(deckList);
	}
	public Card topCard() {
		Card topCard = deckList.get(0);
		deckList.remove(topCard);
		return topCard;
	}
	public enum cardType{
		SKIP,
		ATTACK,
		DEFUSE,
		EXPLODINGKITTEN,
		SEETHEFUTURE,
		SHUFFLE,
		FAVOR,
		NOPE
	}
	public void fill(cardType type, int count) {
		for (int i=0; i<count; i++) {
			deckList.add(type);
		}
	}
}
