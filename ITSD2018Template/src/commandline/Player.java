package commandline;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Player class objects hold Cards and play in rounds to gain more cards.
 * @author apalm
 *
 */

public abstract class Player implements Comparable<Player> {

	// Class variables
	protected String name;
	public ArrayList<Card> hand = new ArrayList<Card>();
	protected int numOfCardsInHand;
	
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
	
	/**
	 * Returns number of Cards in the Player's ArrayList<Card>.
	 * @return
	 */
	public int getNumOfCardsInHand() {
		return this.numOfCardsInHand;
	}
	
	/** 
	 * Returns a String containing information from Player's first Card in ArrayList<Card>.
	 * @return
	 */
	public String getTopCardInfo() {
		String desciption = hand.get(0).getDescription() + "\n";
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

		String categories = "1. " + catOne + ": \t" + hand.get(0).categoryOne + "\n" + "2. " + catTwo + ": \t"
				+ hand.get(0).categoryTwo + "\n" + "3. " + catThree + ": \t" + hand.get(0).categoryThree + "\n" + "4. "
				+ catFour + ": \t" + hand.get(0).categoryFour + "\n" + "5. " + catFive + ": \t\t"
				+ hand.get(0).categoryFive + "\n";

		return desciption + categories;

	}
	
	/**
	 * Returns Player's name, number of cards in hand, and list of cards in hand.
	 */
	public String toString() {
		return "" + name + " has " + getNumOfCardsInHand() + " cards:\t" + hand.toString();
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
	

} // End of class
