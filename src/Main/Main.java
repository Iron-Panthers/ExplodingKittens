package Main;
//Main.attack;
//Main.attack();
import java.util.ArrayList;
import java.util.Scanner;
import Deck.Deck;
import Player.*;
import card.Card;

public class Main{
	public static boolean attack;
	static int numPlayers = 4;
	static int explodingKittenNum = numPlayers-1;
	static int playersAlive = numPlayers;
	static int lastPlayerAlive;
	static ArrayList<Player> players;

	//Constructors
	static Deck deck;
	static Scanner input;
	
	public static void main(String[] args) {
		attack = false;
		input = new Scanner(System.in);
		deck = new Deck(explodingKittenNum);
		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			//Make variable temp? Set it to player+num
			//String playerName = "player"+i; //Does not work, cannot make a string used as the player constructer.
			Player temp = new Player(); //Trying to make 
			players.add(i);
		}
		while (playersAlive > 1) {
			for (int i = 0; i < players.size(); i++) {
				while (players.get(i).turns > 0) {
					players.get(i).turn();
					if (attack()) {
						turns += 2;
						Main.attack = false;
					}
					if (attack) {  
						i++;
					}
					player.get(i).endTurn();
				}
			}
		}
		if(players.size()==1) {
			System.out.println(players.get(0)+" Won!!!");
		}
	}
	
	public void favor(Player targeter, Player victim) {
		System.out.println("What card would you like to give? Type the card name to give or nope to counter the favor.");
		boolean choosing = true;
		while(choosing) {	
			Card chosenCard = card.input.nextLine();
			if (input.nextLine().equalsIgnoreCase("nope") && victim.hand.contains(chosenCard)) {
				System.out.println("Favor countered");
				victim.hand.remove(chosenCard);
				deck.discard(chosenCard);
				targeter.hand.remove(deck.favor);
				deck.discard(deck.favor);
				choosing = false;
			} else if (victim.hand.contains(chosenCard)) {
				System.out.println("Giving "+chosenCard+" .");
				victim.hand.remove(chosenCard);
				targeter.hand.add(chosenCard);
				targeter.hand.remove(deck.favor);
				deck.discard(deck.favor);
				choosing = false;
			} else {
				System.out.println("You do not have "+chosenCard+" in your hand.");
			}
		}
	}
	public void shuffle() {
		deck.shuffle();
		System.out.println("You have succesfuly randomly shuffled the deck");
	}
	public void skip(Player skipper) {
		endTurnNoDraw(skipper);
	}
	public void attack() {
		attack = true;
		//Ends turn, next player must take two turns
	}
	public void nope(Player noper, Player victim) { //Targets a card that targets the noper
		
	}
	public void defuse(Player defuser) { //Works for any card, not just exploding kittens
		Card drawnCard;
		drawnCard = getDrawnCard(defuser);
		if (defuser.hand.contains(deck.defuse)) { //Checks if player has defuse, then it defuses the card and puts it back in the deck anywhere the player chooses
			System.out.println("Where would you like to place the card");
			int deckSizeMinusOne = deck.deckList.size()-1;
			System.out.println("You can place the card anywhere in the deck from the top, 0, to "+deckSizeMinusOne);
			while(!input.hasNextInt()) {
				System.out.println("Please enter an integer from 0 to"+deckSizeMinusOne);
			}
			int deckLocation = input.nextInt();
			defuser.hand.remove(drawnCard); //Removes the defused card from hand
			deck.deckList.add(deckLocation, drawnCard); //Puts the defused card back into the deck
			defuser.hand.remove(deck.defuse); //Discards defuse from hand, places it in discard pile.
			deck.discard(deck.defuse);
			System.out.println("You have defused.");
			endTurnNoDraw(defuser);
		}
		else {
			System.out.println("You do not have a defuse in hand.");
		}
	}
	public void explode(Player exploder) {
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
			deck.deckList.add(0,tempView.get(tempViewReturn));
		}
		System.out.println("See the future is now over");
	}
	public void draw(Player drawer) {
		Card drawnCard = deck.topCard();
		drawer.hand.add(drawnCard);
		if (drawnCard.equals(deck.explodingKitten)) {
			explode(drawer);
		}
		else {
			//Does Nothing
		}
	}
	public Card getDrawnCard(Player drawer) {
		int drawnCardIndex = 0;
		for (int i = 0; i<drawer.hand.size(); i++) { //Gets the last card in the player's hand, which is the card they last drew
			drawnCardIndex++;
		}
		return drawer.hand.get(drawnCardIndex);
	}
	public void endTurnNoDraw(Player skipper) { //Ends turn but places back card player would have drawn normally in the endTurn method. Possibly needs to be added in the 0 location in the deck
		skipper.endTurn();
		deck.deckList.add(0,getDrawnCard(skipper));  
		skipper.hand.remove(getDrawnCard(skipper));
	}
	public void pair(Player player, Card nonDescript) {
		
	}
}
