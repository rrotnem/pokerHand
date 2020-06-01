package pokerHand;

enum Rank {
	HIGHCARD, ONEPAIR, TWOPAIRS, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH
}

public class PokerHand {
	private Card[] cards = new Card[5];
	private String name;

	private Rank rank;
	private int value = 0;
	private int rankValue = 0;

	public PokerHand(String name) {
		this.name = name;
		rank = Rank.HIGHCARD;
	}

	public PokerHand(Card[] cards, String name) {
		this.cards = cards;
		this.name = name;
	}

	public void setCards(Card[] c) {
		this.cards = c;
	}

	public int getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Card[] getCard() {
		return this.cards;
	}

	public Rank getRank() {
		return this.rank;
	}

	public int getRankValue() {
		return this.rankValue;
	}

	public void setRankValue(int val) {

		this.rankValue = val;
	}

	// check if all the cards in the same suit
	public Rank computeHandRank() {
		// sort cards
		sortCard();

		// if it is flush, then no pairs. Check integer for flush and straight flush
		if (isFlush()) {

			if (isStraight()) {
				this.rank = Rank.STRAIGHTFLUSH;

				this.value = 900000 + this.cards[4].getValue();
				this.rankValue = this.cards[4].getValue();

			} else {
				this.rank = Rank.FLUSH;
				this.value = 600000 + this.cards[4].getValue();
				this.rankValue = this.cards[4].getValue();
			}

		} else if (isStraight()) {

			this.rank = Rank.STRAIGHT;
			this.value = 500000 + this.cards[4].getValue();
			this.rankValue = this.cards[4].getValue();

		} else if (fourOfKind()) {

			this.rank = Rank.FOUROFAKIND;
			this.value = 800000 + this.cards[2].getValue();
			this.rankValue = this.cards[2].getValue();

		} else if (fullHouse()) {
			this.rank = Rank.FULLHOUSE;
			this.value = 700000 + this.cards[2].getValue();
			this.rankValue = this.cards[2].getValue();

		} else if (threeOfKind()) {
			this.rank = Rank.THREEOFAKIND;

			this.value = 400000 + this.cards[2].getValue();
			this.rankValue = this.cards[2].getValue();

		} else if (twoPairs()) {
			this.rank = Rank.TWOPAIRS;
			int twoPair = findTwoPairs();
			this.value = 300000 + twoPair;
			this.rankValue = twoPair;

		} else if (onePair()) {
			this.rank = Rank.ONEPAIR;
			int onePair = findOnePair();
			this.value = 200000 + onePair;
			this.rankValue = onePair;
		} else {
			this.rank = Rank.HIGHCARD;
			this.value = 100000 + this.cards[4].getValue();
			this.rankValue = this.cards[4].getValue();
		}

		return this.rank;
	}

	// first check if all the cards in the same suit
	private boolean isFlush() {

		Card first = this.cards[0];

		for (int i = 0; i < 5; i++) {
			if (!this.cards[i].getSuit().equalsIgnoreCase(first.getSuit())) {
				return false;
			}
		}

		return true;

	}

	// second, check if it is straight
	// assume the array is sorted
	private boolean isStraight() {
		// 5 cards with consecutive values
		// rank: 2, 3, 4, 5 ,6 ,7 ,8, 9, 10, J, Q, K, A
		return (this.cards[0].getValue() + 1 == this.cards[1].getValue()
				&& this.cards[1].getValue() + 1 == this.cards[2].getValue()
				&& this.cards[2].getValue() + 1 == this.cards[3].getValue()
				&& this.cards[3].getValue() + 1 == this.cards[4].getValue());
	}

	private boolean fourOfKind() {
		// the array is sorted
		// return 4 + 1 || 1 + 4

		boolean first = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[1].getValue() == this.cards[2].getValue()
				&& this.cards[2].getValue() == this.cards[3].getValue();
		boolean second = this.cards[1].getValue() == this.cards[2].getValue()
				&& this.cards[2].getValue() == this.cards[3].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();

		return first || second;

	}

	private boolean fullHouse() {
		// 3 + 2 || 2 + 3

		boolean first = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[1].getValue() == this.cards[2].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();
		boolean second = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[2].getValue() == this.cards[3].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();

		return first || second;

	}

	private boolean threeOfKind() {
		// 1 + 3 +1 || 3 + 1 + 1 || 1 + 1 + 3

		boolean first = this.cards[1].getValue() == this.cards[2].getValue()
				&& this.cards[2].getValue() == this.cards[3].getValue();

		boolean second = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[1].getValue() == this.cards[2].getValue();

		boolean third = this.cards[2].getValue() == this.cards[3].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();

		return first || second || third;
	}

	private boolean twoPairs() {
		// 2 + 2 +1 || 1+2 +2|| 2+1+2
		boolean first = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[2].getValue() == this.cards[3].getValue();
		boolean second = this.cards[1].getValue() == this.cards[2].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();
		boolean third = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();

		return first || second || third;
	}

	private boolean onePair() {

		// 2+1+1+1 || 1+2+1+1|| 1+1+2+1|| 1+1+1+2

		return (this.cards[0].getValue() == this.cards[1].getValue()
				|| this.cards[1].getValue() == this.cards[2].getValue()
				|| this.cards[2].getValue() == this.cards[3].getValue()
				|| this.cards[3].getValue() == this.cards[4].getValue());
	}

	private int findTwoPairs() {

		boolean first = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[2].getValue() == this.cards[3].getValue();
		boolean second = this.cards[1].getValue() == this.cards[2].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();
		boolean third = this.cards[0].getValue() == this.cards[1].getValue()
				&& this.cards[3].getValue() == this.cards[4].getValue();
		if (first) {
			if (this.cards[0].getValue() > this.cards[2].getValue()) {
				return this.cards[0].getValue();
			} else {
				return this.cards[2].getValue();
			}
		} else if (second) {
			if (this.cards[1].getValue() > this.cards[3].getValue()) {
				return this.cards[1].getValue();
			} else {
				return this.cards[3].getValue();
			}
		} else if (third) {
			if (this.cards[0].getValue() > this.cards[3].getValue()) {
				return this.cards[0].getValue();
			} else {
				return this.cards[3].getValue();
			}
		}

		return -1;
	}

	private int findOnePair() {
		if (this.cards[0].getValue() == this.cards[1].getValue()) {
			return this.cards[0].getValue();
		} else if (this.cards[1].getValue() == this.cards[2].getValue()) {
			return this.cards[1].getValue();
		} else if (this.cards[2].getValue() == this.cards[3].getValue()) {
			return this.cards[2].getValue();
		} else if (this.cards[3].getValue() == this.cards[4].getValue()) {
			return this.cards[3].getValue();
		}

		return -1;
	}

	private void sortCard() {
		for (int i = 0; i < 5; i++) {
			Card sm = this.cards[i];

			for (int j = i; j < 5; j++) {

				if (sm.getValue() > this.cards[j].getValue()) {
					Card temp = this.cards[j];
					this.cards[j] = sm;
					sm = temp;
					this.cards[i] = sm;

				}
			}
		}

		for (int i = 0; i < 5; i++) {
			System.out.print(this.cards[i].getValue() + this.cards[i].getSuit() + ",");
		}
		System.out.println("");
	}

	// test

	public static void main(String args[]) {

		Card[] card1 = { new Card("H", "2"), new Card("D", "3"), new Card("S", "5"), new Card("C", "9"),
				new Card("D", "K") };
		Card[] card110 = { new Card("H", "2"), new Card("h", "3"), new Card("h", "5"), new Card("h", "9"),
				new Card("D", "K") };
		Card[] card20 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "5"), new Card("C", "2"),
				new Card("D", "3") };
		Card[] card21 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "5"), new Card("C", "3"),
				new Card("D", "3") };
		Card[] card22 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "5"), new Card("C", "3"),
				new Card("D", "4") };
		Card[] card23 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "11"), new Card("C", "3"),
				new Card("D", "11") };
		Card[] card3 = { new Card("H", "9"), new Card("S", "2"), new Card("C", "8"), new Card("D", "2"),
				new Card("H", "9") };
		Card[] card4 = { new Card("H", "8"), new Card("S", "3"), new Card("C", "8"), new Card("D", "2"),
				new Card("H", "8") };
		Card[] card41 = { new Card("H", "8"), new Card("S", "9"), new Card("C", "8"), new Card("D", "2"),
				new Card("H", "8") };
		Card[] card42 = { new Card("H", "8"), new Card("S", "9"), new Card("C", "8"), new Card("D", "15"),
				new Card("H", "8") };
		Card[] card5 = { new Card("H", "2"), new Card("D", "10"), new Card("S", "10"), new Card("C", "2"),
				new Card("D", "2") };
		Card[] card51 = { new Card("H", "5"), new Card("D", "10"), new Card("S", "5"), new Card("C", "10"),
				new Card("D", "10") };
		Card[] card6 = { new Card("H", "9"), new Card("D", "9"), new Card("S", "10"), new Card("C", "9"),
				new Card("D", "9") };
		Card[] card61 = { new Card("H", "12"), new Card("D", "12"), new Card("S", "10"), new Card("C", "12"),
				new Card("D", "12") };
		Card[] card7 = { new Card("H", "8"), new Card("D", "10"), new Card("S", "9"), new Card("C", "11"),
				new Card("D", "12") };
		Card[] card71 = { new Card("H", "1"), new Card("D", "5"), new Card("S", "2"), new Card("C", "4"),
				new Card("D", "3") };
		Card[] card8 = { new Card("D", "8"), new Card("D", "2"), new Card("D", "9"), new Card("D", "11"),
				new Card("D", "12") };
		Card[] card81 = { new Card("d", "3"), new Card("d", "10"), new Card("d", "14"), new Card("d", "6"),
				new Card("d", "12") };
		Card[] card9 = { new Card("H", "8"), new Card("h", "10"), new Card("h", "9"), new Card("h", "11"),
				new Card("h", "12") };
		Card[] card91 = { new Card("d", "8"), new Card("d", "10"), new Card("d", "9"), new Card("d", "12"),
				new Card("d", "11") };
		Card[] card10 = { new Card("H", "10"), new Card("D", "10"), new Card("S", "8"), new Card("C", "2"),
				new Card("D", "8") };
		Card[] card11 = { new Card("H", "2"), new Card("D", "2"), new Card("S", "8"), new Card("C", "10"),
				new Card("D", "8") };

		PokerHand hand1 = new PokerHand(card1, "Player 1");
		PokerHand hand110 = new PokerHand(card110, "Player 1");
		PokerHand hand20 = new PokerHand(card20, "Player 1");
		PokerHand hand21 = new PokerHand(card21, "Player 1");
		PokerHand hand22 = new PokerHand(card22, "Player 1");
		PokerHand hand23 = new PokerHand(card23, "Player 1");
		PokerHand hand3 = new PokerHand(card3, "Player 1");
		PokerHand hand4 = new PokerHand(card4, "Player 1");
		PokerHand hand41 = new PokerHand(card41, "Player 1");
		PokerHand hand42 = new PokerHand(card42, "Player 1");
		PokerHand hand5 = new PokerHand(card5, "Player 1");
		PokerHand hand51 = new PokerHand(card51, "Player 1");
		PokerHand hand6 = new PokerHand(card6, "Player 1");
		PokerHand hand61 = new PokerHand(card61, "Player 1");
		PokerHand hand7 = new PokerHand(card7, "Player 1");
		PokerHand hand71 = new PokerHand(card71, "Player 1");
		PokerHand hand8 = new PokerHand(card8, "Player 1");
		PokerHand hand81 = new PokerHand(card81, "Player 1");
		PokerHand hand9 = new PokerHand(card9, "Player 1");
		PokerHand hand91 = new PokerHand(card91, "Player 1");
		PokerHand hand10 = new PokerHand(card10, "Player 1");
		PokerHand hand11 = new PokerHand(card11, "Player 1");

		System.out.println(" " + hand1.computeHandRank() + "  " + hand1.getValue());
		System.out.println(" " + hand110.computeHandRank() + "  " + hand110.getValue());
		System.out.println(" " + hand20.computeHandRank() + "  " + hand20.getValue());
		System.out.println(" " + hand21.computeHandRank() + "  " + hand21.getValue());
		System.out.println(" " + hand22.computeHandRank() + "  " + hand22.getValue());
		System.out.println(" " + hand23.computeHandRank() + "  " + hand23.getValue());
		System.out.println(" " + hand10.computeHandRank() + "  " + hand10.getValue());
		System.out.println(" " + hand11.computeHandRank() + "  " + hand11.getValue());
		System.out.println(" " + hand3.computeHandRank() + "  " + hand3.getValue());
		System.out.println(" " + hand4.computeHandRank() + "  " + hand4.getValue());
		System.out.println(" " + hand41.computeHandRank() + "  " + hand41.getValue());
		System.out.println(" " + hand42.computeHandRank() + "  " + hand42.getValue());
		System.out.println(" " + hand5.computeHandRank() + "  " + hand5.getValue());
		System.out.println(" " + hand51.computeHandRank() + "  " + hand51.getValue());
		System.out.println(" " + hand6.computeHandRank() + "  " + hand6.getValue());
		System.out.println(" " + hand61.computeHandRank() + "  " + hand61.getValue());
		System.out.println(" " + hand7.computeHandRank() + "  " + hand7.getValue());
		System.out.println(" " + hand71.computeHandRank() + "  " + hand71.getValue());
		System.out.println(" " + hand8.computeHandRank() + "  " + hand8.getValue());
		System.out.println(" " + hand81.computeHandRank() + "  " + hand81.getValue());
		System.out.println(" " + hand9.computeHandRank() + "  " + hand9.getValue());
		System.out.println(" " + hand91.computeHandRank() + "  " + hand91.getValue());

	}

}
