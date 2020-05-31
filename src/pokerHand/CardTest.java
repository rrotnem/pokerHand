package pokerHand;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class CardTest extends TestCase {

	@Test
	void testConstrutor() {
		Card card = new Card("H", "A");

		assertEquals("H", card.getSuit());
		assertTrue(card.getValue() == 14);
	}

	@Test
	void testSuit() {
		Card card = new Card("H", "A");

		assertEquals("H", card.getSuit());

	}

	@Test
	void testValue() {
		Card card = new Card("H", "A");

		assertTrue(card.getValue() == 14);
	}
	
	@Test	
	void testSetSuit() {
		Card card = new Card("H", "A");
		card.setSuit("D");

		assertEquals("D", card.getSuit());
	}
	
	@Test	
	void testSetValue() {
		Card card = new Card("H", "A");
		card.setValue("J");

		assertTrue(card.getValue() == 11);
	}
	
	@Test	
	void testSetValueWithNumber() {
		Card card = new Card("H", "A");
		card.setValue("10");

		assertTrue(card.getValue() == 10);
	}

}
