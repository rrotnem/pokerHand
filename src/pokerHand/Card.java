package pokerHand;
public class Card{
	private String suit;
	private int value;
	//assume the card is in the form rank + suit
	public Card(String suit, String value) {
		
		this.suit = suit.toUpperCase();
		//take care special situations
		if(value.equalsIgnoreCase("A")) {
			this.value = 14;
		}else if(value.equalsIgnoreCase("K")) {
			this.value = 13;
		}else if(value.equalsIgnoreCase("Q")) {
			this.value = 12;
		}else if(value.equalsIgnoreCase("J")) {
			this.value = 11;
		}else {
			this.value = Integer.parseInt(value);
		}		
	}
	
	public int getValue() {
		return this.value;
	}
	public String getSuit() {
		return this.suit;
	}
	public void setValue(String or) {
		if(or.equalsIgnoreCase("A")) {
			this.value = 14;
		}else if(or.equalsIgnoreCase("K")) {
			this.value = 13;
		}else if(or.equalsIgnoreCase("Q")) {
			this.value = 12;
		}else if(or.equalsIgnoreCase("J")) {
			this.value = 11;
		}else {
			this.value = Integer.parseInt(or);
		}	
	}
	
	public void setSuit(String s) {
		this.suit = s.toUpperCase();
	}
}