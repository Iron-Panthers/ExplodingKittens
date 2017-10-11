package cardPackage;
import java.util.ArrayList;

public class Card {
	
	public CardTypes type;
	public ArrayList<CardTypes> cards;
	
	public Card(CardTypes type) {
		this.type = type;
		cards = new ArrayList<CardTypes>();
		cards.add(CardTypes.DEFUSE);
		cards.add(CardTypes.EXPLODING_KITTEN);
		cards.add(CardTypes.FAVOR);
		cards.add(CardTypes.SEE_THE_FUTURE);
		cards.add(CardTypes.ATTACK);
		cards.add(CardTypes.NOPE);
		cards.add(CardTypes.SKIP);
		cards.add(CardTypes.SHUFFLE);
		cards.add(CardTypes.RAINBOW_RALPHING_CAT);
		cards.add(CardTypes.HAIRY_POTATO_CAT);
		cards.add(CardTypes.BEARD_CAT);
		cards.add(CardTypes.TACO_CAT);
		cards.add(CardTypes.CATTERMELON);
		
	}
	
	public CardOutput output(CardTypes type) {
		if (type == CardTypes.SEE_THE_FUTURE) {
			return new CardOutput();
		}
	}
	
	
}
