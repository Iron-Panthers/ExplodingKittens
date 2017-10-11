package card;

public class Card {
	public CardType type;
	
	public Card(CardType type) {
		this.type = type;
	}
	/*
	boolean turnOver;
	
	public void playCard(int cardNumber) {
		
		if (type == CardType.SKIP) {
			skip();
		}
		if (type == CardType.ATTACK) {
			attack();
		}
		
	}
	
	public void execute() {
		if (turnOver==true) {
			//method for ending turn in player
		}
	}
	
	public void attack() {
		turnOver = true;
	}

	public void skip() {
		turnOver = true;
	}*/
}

// Card bomb = new Card(CardType.EXPLODING_KITTEN);