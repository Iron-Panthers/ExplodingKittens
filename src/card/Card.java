package card;

public class Card {
	public CardType type;
	
	public Card(CardType type) {
		this.type = type;
	}
	
	public static CardType convertToString(String str) {
		// ex: NOPe
		str = str.replaceAll("\\s+", "_");
		str = str.replaceAll("-", "_");
		for (int i = 0; i < CardType.values().length; i++) {
			if (CardType.valueOf(str.toUpperCase()) == CardType.values()[i]) {
				return CardType.values()[i];
			}
		}
		return null;
	}

	/**
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
	}**/
}

// Card bomb = new Card(CardType.EXPLODING_KITTEN);