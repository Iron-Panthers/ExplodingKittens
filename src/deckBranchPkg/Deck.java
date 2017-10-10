package deckBranchPkg;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<String> deckList;
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
		deckList = new ArrayList<String>();
		//Action Cards
		for (int i = 0; i<explodingKittenNum; i++) {
			deckList.add("explodingKitten"+i);
		}
		for (int i = 0; i<seeTheFutureNum; i++) {
			deckList.add("seeTheFuture"+i);
		}
		for (int i = 0; i<shuffleNum; i++) {
			deckList.add("shuffle"+i);
		}
		for (int i = 0; i<defuseNum; i++) {
			deckList.add("defuse"+i);
		}
		for (int i = 0; i<attackNum; i++) {
			deckList.add("attack"+i);
		}
		for (int i = 0; i<skipNum; i++) {
			deckList.add("skip"+i);
		}
		for (int i = 0; i<favorNum; i++) {
			deckList.add("favor"+i);
		}
		for (int i = 0; i<nopeNum; i++) {
			deckList.add("nope"+i);
		}
		
		//Non-descripts
		for (int i = 0; i<rainbowRalphingCatNum; i++) {
			deckList.add("rainbowRalphingCat"+i);
		}
		for (int i = 0; i<hairyPotatoCatNum; i++) {
			deckList.add("hairyPotatoCat"+i);
		}
		for (int i = 0; i<beardCatNum; i++) {
			deckList.add("beardCat"+i);
		}
		
		for (int i = 0; i<tacoCatNum; i++) {
			deckList.add("tacoCat"+i);
		}
		
		for (int i = 0; i<catterMelonNum; i++) {
			deckList.add("catterMelon"+i);
		}
		shuffle();
	}
	public void shuffle() {
		Collections.shuffle(deckList);
	}
	public void draw() {
		String topCard = deckList.get(0);
		deckList.remove(topCard);
		Main.player.hand.add(topCard); //For when Player player is constructed in Main
	}
}
