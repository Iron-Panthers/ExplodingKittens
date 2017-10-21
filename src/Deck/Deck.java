package Deck;

import java.util.ArrayList;
import java.util.Collections;

import card.Card;
import card.CardType;

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
	public Card rainbowRalphingCat;
	public Card hairyPotatoCat;
	public Card beardCat;
	public Card tacoCat;
	public Card catterMelon;
	
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
		rainbowRalphingCat = new Card(CardType.RAINBOW_RALPHING_CAT);
		hairyPotatoCat = new Card(CardType.HAIRY_POTATO_CAT);
		beardCat = new Card(CardType.BEARD_CAT);
		tacoCat = new Card(CardType.TACOCAT);
		catterMelon = new Card(CardType.CATTERMELON);
		
		//Action Card Constructs
		explodingKitten = new Card(CardType.EXPLODING_KITTEN);
		seeTheFuture = new Card(CardType.SEE_THE_FUTURE);
		shuffle = new Card(CardType.SHUFFLE);
		defuse = new Card(CardType.DEFUSE);
		attack = new Card(CardType.ATTACK);
		skip = new Card(CardType.SKIP);
		favor = new Card(CardType.FAVOR);
		nope = new Card(CardType.NOPE);
		
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
		//Does not fill with Exploding kitten to avoid confusion in game start
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
