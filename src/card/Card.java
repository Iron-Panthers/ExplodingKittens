package card;

public class Card {
	public CardType type;
	
	public Card(CardType type) {
		this.type = type;
	}
	
	public static CardType convertToCardType(String str) {
		/**
		 * converts String to CardType
		 * ex: "rAiNbOw_RaLpHiNg CaT" to RAINBOW_RALPHING_CAT
		 */
		str = str.replaceAll("\\s+", "_");
		str = str.replaceAll("-", "_");
		for (int i = 0; i < CardType.values().length; i++) {
			if (CardType.valueOf(str.toUpperCase()) == CardType.values()[i]) {
				return CardType.values()[i];
			}
		}
		return null;
	}
}