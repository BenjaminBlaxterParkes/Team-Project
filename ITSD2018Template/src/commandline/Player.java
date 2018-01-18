package commandline;

import java.util.ArrayList;

public abstract class Player implements Comparable<Player> {

	protected String name;
	public ArrayList<Card> hand = new ArrayList<Card>();
	protected int numOfCardsInHand = 0;
	
	Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHand(Card c) {
		hand.add(c);
		//System.err.println("The card (" + c.getDescription() + ") is being loaded into "+ getName() + "'s hand");
	}
	
	public Card getTopCard() {
		Card card = hand.get(0);
		return card;
	}
	
	public int getNumOfCardsInHand() {
		return this.numOfCardsInHand;
	}
	
	public String getTopCardInfo() {
		String desciption = hand.get(0).getDescription() + "\n";
		String allCardInfo = hand.get(0).getCardInfo();
		
		String catOne = "";
		String catTwo = "";
		String catThree = ""; 
		String catFour = "";
		String catFive = "";
		
		try {
		String[] cardInfo = allCardInfo.split(" ");
		
		catOne = cardInfo[0];
		catTwo = cardInfo[1];
		catThree = cardInfo[2];
		catFour = cardInfo[3];
		catFive = cardInfo[4];
		
		}
		
		catch (NullPointerException n) {
			System.err.println("Null was found in 'getTopCardInfo' in 'Players' class");
		}
		
		String categories = "1. " + catOne + ": \t" + hand.get(0).categoryOne + "\n"
							+ "2. " +   catTwo + ": \t" + hand.get(0).categoryTwo + "\n"
							+ "3. " +  catThree + ": \t" + hand.get(0).categoryThree + "\n"
							+ "4. " +   catFour + ": \t" + hand.get(0).categoryFour + "\n"
							+ "5. " +   catFive + ": \t\t" + hand.get(0).categoryFive + "\n";
		
		return desciption + categories;
		
	}
	
	public String toString() {
		return "" + name + ", has the following hand: " + hand.toString();
	}
	
	
	
	public int compareCatagoryOne(Player other) {
		int result = 0;
	
		if (this.getTopCard().getCategoryOne() > other.getTopCard().getCategoryOne()) { // if other is lesser, return 1
			result = 1; }
		
		else if (this.getTopCard().getCategoryOne() < other.getTopCard().getCategoryOne()) { // if other is greater, return -1
			result = -1; }
		
		else {
			result = 0; } // if equal, return 0

		return result;
	}
	
	
	
	public int compareCatagoryTwo(Player other) {
		int result = 0;
	
		if (this.getTopCard().getCategoryTwo() > other.getTopCard().getCategoryTwo()) { // if other is lesser, return 1
			result = 1; }
		
		else if (this.getTopCard().getCategoryTwo() < other.getTopCard().getCategoryTwo()) { // if other is greater, return -1
			result = -1; }
		
		else {
			result = 0; } // if equal, return 0

		return result;
	}
	
}
