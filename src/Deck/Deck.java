package Deck;

import java.util.ArrayList;
import java.util.Collections;

import javax.smartcardio.Card;

import card.*;

public class Deck {
	public ArrayList<Card> deckList;
	public ArrayList<Card> discardPile;
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
	
	//Non-Descript Instance Variables
	Card rainbowRalphingCat;
	Card hairyPotatoCat;
	Card beardCat;
	Card tacoCat;
	Card catterMelon;
	
	//Action Card Instance Variables
	public Card explodingKitten;
	public Card seeTheFuture;
	public Card shuffle;
	public Card defuse;
	public Card attack;
	public Card skip;
	public Card favor;
	public Card nope;
	
	public Deck (int explodingKittenNumber) {
		//player = new Player(); Do this in main class
		//Non-descript Constructs
		rainbowRalphingCat = new Card(CardTypes.RAINBOWRALPHINGCAT);
		hairyPotatoCat = new Card(CardTypes.HAIRYPOTATOCAT);
		beardCat = new Card(CardTypes.BEARDCAT);
		tacoCat = new Card(CardTypes.TACOCAT);
		catterMelon = new Card(CardTypes.CATTERMELON);
		
		//Action Card Constructs
		explodingKitten = new Card(CardTypes.EXPLODINGKITTEN);
		seeTheFuture = new Card(CardTypes.SEETHEFUTURE);
		shuffle = new Card(CardTypes.SHUFFLE);
		defuse = new Card(CardTypes.DEFUSE);
		attack = new Card(CardTypes.ATTACK);
		skip = new Card(CardTypes.SKIP);
		favor = new Card(CardTypes.FAVOR);
		nope = new Card(CardTypes.NOPE);
		
		explodingKittenNum=explodingKittenNumber;
		deckList = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		
		//Non-Descript Card Fills
		fill(rainbowRalphingCat,rainbowRalphingCatNum);
		fill(hairyPotatoCat,hairyPotatoCatNum);
		fill(beardCat,beardCatNum);
		fill(tacoCat,tacoCatNum);
		fill(catterMelon,catterMelonNum);
		
		//Action Card Fills
		fill(explodingKitten,explodingKittenNum);
		fill(seeTheFuture,seeTheFutureNum);
		fill(shuffle,shuffleNum);
		fill(defuse,defuseNum);
		fill(attack,attackNum);
		fill(skip,skipNum);
		fill(favor,favorNum);
		fill(nope,nopeNum);
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
	public void fill(Card type, int count) {
		for (int i=0; i<count; i++) {
			deckList.add(type);
		}
	}
	public void discard(Card type) {
		discardPile.add(type);
	}
	public boolean checkDiscard(Card card) {
		return discardPile.contains(card);
	}
	public void showDiscard() {
		for (int i = 0; i<discardPile.size(); i++) {
			System.out.println(discardPile.get(i));
		}
	}
}
