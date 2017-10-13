package card;

public class Card {
	
	public CardType type;
	
	public Card(CardType type) {
		this.type = type;
		
	}
	
	public CardType convert(String card) {
		for (int i = 0; i < CardType.values().length; i++) {
			if (CardType.valueOf(card.toUpperCase()) == CardType.values()[i]) {
				return CardType.values()[i];
			}
		}
		
	}
	
}
