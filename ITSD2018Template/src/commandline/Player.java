package commandline;

import java.util.ArrayList;

public abstract class Player {

	protected String name;
	public ArrayList<Card> hand = new ArrayList<Card>();
	
	Player(String name) {
		this.name = name;
	}
	
	public void setHand(Card c) {
		hand.add(c);
		System.err.println("The card dealt was: " + c);
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "The player, " + name + " has the following hand: " + hand.toString();
	}
}
