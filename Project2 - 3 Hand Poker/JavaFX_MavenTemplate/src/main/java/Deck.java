import java.util.ArrayList;

public class Deck extends ArrayList<Card> {

	Deck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		char[] suit = {'C', 'H', 'D', 'S'};
	        for (int i = 0; i < 4; i++) {
	           for (int j = 2; j < 15; j++) {
		       deck.add(new Card(suit[i], j));
		   }
	 	}
	}
}
