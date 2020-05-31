package pokerHand;

import java.util.ArrayList;

public class Play {

	private PokerHand p1;
	private PokerHand p2;

	public Play(PokerHand p1, PokerHand p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	// this method computes the players ranks and print the result out
	public void getResult() {

		// compute the result from both players
		computeRanks(p1, p2);

		// compare the rank with two situations:
		// player 1 won with the same rank or higher rank
		if (p1.getValue() > p2.getValue()) {
			if (p1.getRank() == p2.getRank()) {// player1 won with same rank or higher
				System.out.println(p1.getName() + " wins. -" + p1.getRank() + ": " + transRank(p1.getRankValue())
						+ " over " + transRank(p2.getRankValue()));
			} else {
				System.out.println(p1.getName() + " wins. -" + p1.getRank() + ": " + transRank(p1.getRankValue()));
			}

		} else if (p1.getValue() == p2.getValue()) {
			int temp = computeSameRank();

			if (temp == 1) {
				System.out.println(p1.getName() + " wins. -" + p1.getRank() + ": " + transRank(p1.getRankValue())
						+ " over " + transRank(p2.getRankValue()));
			} else if (temp == -1) {
				System.out.println(p2.getName() + " wins. -" + p2.getRank() + ": " + transRank(p2.getRankValue())
						+ " over " + transRank(p1.getRankValue()));
			} else {
				System.out.println("Tie.");
			}

		} else {// player 2 won with same rank or higher

			if (p1.getRank() == p2.getRank()) {// if player 2 the same rank, print the values of both players
				System.out.println(p2.getName() + " wins. -" + p2.getRank() + ": " + transRank(p2.getRankValue())
						+ " over " + transRank(p1.getRankValue()));
			} else {// if not the same rank, print the player 2 name, rank, and value;
				System.out.println(p2.getName() + " wins. -" + p2.getRank() + ": " + transRank(p2.getRankValue()));
			}
		}

	}

	// the only situation is the two pairs, when both hands have the same rank, and
	// same highest value
	// the next highest value has to be the next pair.
	private int computeSameRank() {
		Card[] c1 = p1.getCard();
		Card[] c2 = p2.getCard();
		int val_p1 = p1.getRankValue();
		int val_p2 = p1.getRankValue();
		int result = 0;

		Rank r = p1.getRank();
		switch (r) {
		case TWOPAIRS:

			if (twoPairs(c1, val_p1) > twoPairs(c2, val_p2)) {
				result = 1;
				p1.setRankValue(twoPairs(c1, val_p1));
				p2.setRankValue(twoPairs(c2, val_p2));

			} else if (twoPairs(c1, val_p1) < twoPairs(c2, val_p2)) {
				result = -1;
				p2.setRankValue(twoPairs(c2, val_p2));
				p1.setRankValue(twoPairs(c1, val_p1));
			} else {

				if (lastValue(c1) > lastValue(c2)) {
					p1.setRankValue(lastValue(c1));
					p2.setRankValue(lastValue(c2));
					result = 1;
				} else if (lastValue(c1) < lastValue(c2)) {
					p1.setRankValue(lastValue(c1));
					p2.setRankValue(lastValue(c2));
					result = -1;
				}
			}
			;
			break;
		default:
			result = computeNextHighestValue();
		}

		return result;
	}

	// getLastvalue from two pairs hands
	private int lastValue(Card[] c) {
		// 2 + 2 +1 || 1+2 +2|| 2+1+2
		boolean first = c[0].getValue() == c[1].getValue() && c[2].getValue() == c[3].getValue();
		boolean second = c[1].getValue() == c[2].getValue() && c[3].getValue() == c[4].getValue();
		boolean third = c[0].getValue() == c[1].getValue() && c[3].getValue() == c[4].getValue();
		if (first) {
			return c[4].getValue();
		} else if (second) {
			return c[0].getValue();

		} else if (third) {
			return c[2].getValue();
		}

		return 0;
	}

	private int twoPairs(Card[] c, int val) {
		// 2 + 2 +1 || 1+2 +2|| 2+1+2

		boolean first = c[0].getValue() == c[1].getValue() && c[2].getValue() == c[3].getValue();
		boolean second = c[1].getValue() == c[2].getValue() && c[3].getValue() == c[4].getValue();
		boolean third = c[0].getValue() == c[1].getValue() && c[3].getValue() == c[4].getValue();
		if (first) {
			if (val == c[0].getValue()) {
				return c[2].getValue();
			} else if (val == c[2].getValue()) {
				return c[0].getValue();
			}

		} else if (second) {
			if (val == c[1].getValue()) {
				return c[3].getValue();
			} else if (val == c[3].getValue()) {
				return c[1].getValue();
			}

		} else if (third) {
			if (val == c[0].getValue()) {
				return c[3].getValue();
			} else if (val == c[3].getValue()) {
				return c[0].getValue();
			}
		}

		return 0;
	}

	private int computeNextHighestValue() {
		Card[] c1 = p1.getCard();
		Card[] c2 = p2.getCard();

		int h1 = 0;
		int h2 = 0;

		for (int i = 4; i > 0; i--) {

			if (c1[i].getValue() != p1.getRankValue()) {
				h1 = c1[i].getValue();

				for (int j = i; j > 0; j--) {
					if (c2[j].getValue() != p2.getRankValue()) {
						h2 = c2[j].getValue();

						if (h1 > h2) {
							p1.setRankValue(h1);

							return 1;
						} else if (h1 < h2) {
							p2.setRankValue(h2);
							return -1;
						} else if (h1 == h2) {
							break;
						}
					}
				}

			}
		}
		return 0;

	}

	// get ranks separately
	private void computeRanks(PokerHand p1, PokerHand p2) {
		p1.computeHandRank();
		p2.computeHandRank();
	}

	// get special rank value J, Q, K, A
	private String transRank(int v) {
		if (v == 11) {
			return "Jack";
		} else if (v == 12) {
			return "Queen";
		} else if (v == 13) {
			return "King";
		} else if (v == 14) {
			return "Ace";
		}

		return "" + v;
	}

	// test cases
	// build all possible hands and randomly pick hands to compute in two players.

	public static void main(String args[]) {
		Card[] card1 = { new Card("H", "2"), new Card("D", "3"), new Card("S", "5"), new Card("C", "9"),
				new Card("D", "K") };
		Card[] card2 = { new Card("H", "2"), new Card("h", "3"), new Card("h", "5"), new Card("h", "9"),
				new Card("D", "K") };
		Card[] card3 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "5"), new Card("C", "2"),
				new Card("D", "3") };
		Card[] card4 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "5"), new Card("C", "3"),
				new Card("D", "3") };
		Card[] card5 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "5"), new Card("C", "3"),
				new Card("D", "4") };
		Card[] card6 = { new Card("H", "2"), new Card("D", "4"), new Card("S", "11"), new Card("C", "3"),
				new Card("D", "11") };
		Card[] card7 = { new Card("H", "9"), new Card("S", "2"), new Card("C", "8"), new Card("D", "2"),
				new Card("H", "9") };
		Card[] card8 = { new Card("H", "8"), new Card("S", "3"), new Card("C", "8"), new Card("D", "2"),
				new Card("H", "8") };
		Card[] card9 = { new Card("H", "8"), new Card("S", "9"), new Card("C", "8"), new Card("D", "2"),
				new Card("H", "8") };
		Card[] card10 = { new Card("H", "8"), new Card("S", "9"), new Card("C", "8"), new Card("D", "15"),
				new Card("H", "8") };
		Card[] card11 = { new Card("H", "2"), new Card("D", "10"), new Card("S", "10"), new Card("C", "2"),
				new Card("D", "2") };
		Card[] card12 = { new Card("H", "5"), new Card("D", "10"), new Card("S", "5"), new Card("C", "10"),
				new Card("D", "10") };
		Card[] card13 = { new Card("H", "9"), new Card("D", "9"), new Card("S", "10"), new Card("C", "9"),
				new Card("D", "9") };
		Card[] card14 = { new Card("H", "12"), new Card("D", "12"), new Card("S", "10"), new Card("C", "12"),
				new Card("D", "12") };
		Card[] card15 = { new Card("H", "8"), new Card("D", "10"), new Card("S", "9"), new Card("C", "11"),
				new Card("D", "12") };
		Card[] card16 = { new Card("H", "1"), new Card("D", "5"), new Card("S", "2"), new Card("C", "4"),
				new Card("D", "3") };
		Card[] card17 = { new Card("D", "8"), new Card("D", "2"), new Card("D", "9"), new Card("D", "11"),
				new Card("D", "12") };
		Card[] card18 = { new Card("d", "3"), new Card("d", "10"), new Card("d", "14"), new Card("d", "6"),
				new Card("d", "12") };
		Card[] card19 = { new Card("H", "8"), new Card("h", "10"), new Card("h", "9"), new Card("h", "11"),
				new Card("h", "12") };
		Card[] card20 = { new Card("d", "8"), new Card("d", "10"), new Card("d", "9"), new Card("d", "12"),
				new Card("d", "11") };
		Card[] card21 = { new Card("H", "10"), new Card("D", "10"), new Card("S", "8"), new Card("C", "2"),
				new Card("D", "8") };
		Card[] card22 = { new Card("H", "2"), new Card("D", "2"), new Card("S", "8"), new Card("C", "10"),
				new Card("D", "8") };

		ArrayList<Card[]> cards = new ArrayList<Card[]>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card6);
		cards.add(card7);
		cards.add(card8);
		cards.add(card9);
		cards.add(card10);
		cards.add(card11);
		cards.add(card12);
		cards.add(card13);
		cards.add(card14);
		cards.add(card15);
		cards.add(card16);
		cards.add(card17);
		cards.add(card18);
		cards.add(card19);
		cards.add(card20);
		cards.add(card21);
		cards.add(card22);

		for (int i = 0; i < cards.size(); i++) {
			PokerHand p1 = new PokerHand("Hank");
			PokerHand p2 = new PokerHand("John");
			p1.setCards(cards.get((int) (Math.random() * ((21 - 0) + 1)) + 0));
			p2.setCards(cards.get((int) (Math.random() * ((21 - 0) + 1)) + 0));

			Play play = new Play(p1, p2);
			play.getResult();
		}

		Card[] card23 = { new Card("H", "2"), new Card("D", "2"), new Card("S", "8"), new Card("C", "10"),
				new Card("D", "8") };
		Card[] card24 = { new Card("H", "2"), new Card("D", "2"), new Card("S", "8"), new Card("C", "11"),
				new Card("D", "8") };
		PokerHand p1 = new PokerHand("Hank");
		PokerHand p2 = new PokerHand("John");
		p1.setCards(card23);
		p2.setCards(card24);

		Play play = new Play(p1, p2);
		play.getResult();

	}
}