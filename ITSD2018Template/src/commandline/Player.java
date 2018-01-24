package commandline;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Player class objects hold Cards and play in rounds to gain more cards.
 * @author apalm
 *
 */

public class Player implements Comparable<Player> {

	// Class variables
	protected String name;
	public ArrayList<Card> hand = new ArrayList<Card>();
	protected int numOfCardsInHand;
	protected int categoryChoice;
	
	/**
	 * Constructor
	 * @param name
	 */
	Player(String name) {
		this.name = name;
	}
	
	/**
	 * Returns name of Player.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets a Card to the Player's ArrayList<Card>.
	 * @param c
	 */
	public void setHand(Card c) {
		hand.add(c);
		numOfCardsInHand++;
		//System.out.println("The card (" + c.getDescription() + ") is being loaded into "+ getName() + "'s hand");
	}
	
	/**
	 * Removes first Card from the Player's ArrayList<Card>.
	 */
	public void RemoveTopCard() {
		hand.remove(0);
		numOfCardsInHand--;
	}
	
	/**
	 * Returns the first Card from the Player's ArrayList<Card>.
	 * @return
	 */
	public Card getTopCard() {
		Card card = hand.get(0);

		if (card == null) {
			return null;
		}

		return card;
	}
	
	public void setCategoryChoice(int choice) {
		this.categoryChoice = choice;
	}
	
	public int getCategoryChoice() {
		return this.categoryChoice;
	}
	
	/**
	 * Returns number of Cards in the Player's ArrayList<Card>.
	 * @return
	 */
	public int getNumOfCardsInHand() {
		return this.numOfCardsInHand;
	}
	
	public int AIChooseCategory() {
	int[] categories = new int[5];
	Card c = getTopCard();
	categories[0] = c.getCategoryOne();
	categories[1] = c.getCategoryTwo();
	categories[2] = c.getCategoryThree();
	categories[3] = c.getCategoryFour();
	categories[4] = c.getCategoryFive();

	int i = 0;
	int max = 0;
	int index = 0;
	for (i = 0; i < categories.length; i++) {
		if (categories[i] > max) {
			max = categories[i];
			index = i;
		}
	}
	// System.out.println("The max number in " + getName() +
	// "'s hand is " + max + ", which is category: " + (index + 1) + "");

	this.setCategoryChoice(index+1);
	return index + 1;
}
	
	/** 
	 * Returns a String containing information from Player's first Card in ArrayList<Card>.
	 * @return
	 */
	public String getTopCardInfo() {
		String desciption = "=================================\n" 
						  +"| " + hand.get(0).getDescription() +"   \t\t\t| \n";
		String allCardInfo = hand.get(0).getCardCategories();

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
			System.out.println("Null was found in 'getTopCardInfo' in 'Players' class");
		}

		String categories = "| 1. " + catOne + ": \t\t" + hand.get(0).categoryOne + "\t|\n" + "| 2. " + catTwo + ": \t\t"
				+ hand.get(0).categoryTwo + "\t|\n" + "| 3. " + catThree + ": \t\t" + hand.get(0).categoryThree + "\t|\n" + "| 4. "
				+ catFour + ": \t\t" + hand.get(0).categoryFour + "\t|\n" + "| 5. " + catFive + ": \t\t"
				+ hand.get(0).categoryFive + "\t|\n"
				+ "=================================\n";
					

		return desciption + categories;

	}
	
	/**
	 * Returns Player's name, number of cards in hand, and list of cards in hand.
	 */
	public String toString() {
		return "-" + name + " has " + getNumOfCardsInHand() + " cards.";
	}
	
	/**
	 * Allows Player objects to be sorted by their first Card's first category.  
	 */
	public static Comparator<Player> sortByCategoryOne = new Comparator<Player>() {

		public int compare(Player one, Player two) {
			int compareOne = one.getTopCard().getCategoryOne();
			int compareTwo = two.getTopCard().getCategoryOne();

			return compareTwo - (compareOne);
		}
	};

	/**
	 * Allows Player objects to be sorted by their first Card's second category.  
	 */
	public static Comparator<Player> sortByCategoryTwo = new Comparator<Player>() {

		public int compare(Player one, Player two) {
			int compareOne = one.getTopCard().getCategoryTwo();
			int compareTwo = two.getTopCard().getCategoryTwo();

			return compareTwo - (compareOne);
		}
	};

	/**
	 * Allows Player objects to be sorted by their first Card's third category.  
	 */
	public static Comparator<Player> sortByCategoryThree = new Comparator<Player>() {

		public int compare(Player one, Player two) {
			int compareOne = one.getTopCard().getCategoryThree();
			int compareTwo = two.getTopCard().getCategoryThree();

			return compareTwo - (compareOne);
		}
	};

	/**
	 * Allows Player objects to be sorted by their first Card's fourth category.  
	 */
	public static Comparator<Player> sortByCategoryFour = new Comparator<Player>() {

		public int compare(Player one, Player two) {
			int compareOne = one.getTopCard().getCategoryFour();
			int compareTwo = two.getTopCard().getCategoryFour();

			return compareTwo - (compareOne);
		}
	};

	/**
	 * Allows Player objects to be sorted by their first Card's fifth category.  
	 */
	public static Comparator<Player> sortByCategoryFive = new Comparator<Player>() {

		public int compare(Player one, Player two) {
			int compareOne = one.getTopCard().getCategoryFive();
			int compareTwo = two.getTopCard().getCategoryFive();

			return compareTwo - (compareOne);
		}
	};
	

	public int compareTo(Player other) {
		int result = 0;

		return result;
	}
	
} // End of class
