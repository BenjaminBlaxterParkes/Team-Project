package commandline;

/**
 * Card class manages the information from the .txt file that contains
 * the deck of cards.  
 * @author apalm
 *
 */

public class Card {

	final protected String description; // card name
	final protected int categoryOne, categoryTwo, categoryThree, categoryFour, categoryFive;
	protected String cardInfo = "";
	
	/**
	 * This is a manual constructor that takes the following parameters
	 * 
	 * @param description
	 * @param categoryOne
	 * @param categoryTwo
	 * @param categoryThree
	 * @param categoryFour
	 * @param categoryFive
	 */
	Card(String description, int categoryOne, int categoryTwo, int categoryThree, int categoryFour, int categoryFive) {
		this.description = description;
		this.categoryOne = categoryOne;
		this.categoryTwo = categoryTwo;
		this.categoryThree = categoryThree;
		this.categoryFour = categoryFour;
		this.categoryFive = categoryFive;
	}
	
	/**
	 * This constructors takes a single String, parses it out
	 * the description and five categories, and creates the Card object.
	 * @param line
	 */
	Card(String line) {
		
		String[] words = line.split(" ");
		
		this.description = words[0];
		this.categoryOne = Integer.parseInt(words[1]);
		this.categoryTwo = Integer.parseInt(words[2]);
		this.categoryThree = Integer.parseInt(words[3]);
		this.categoryFour = Integer.parseInt(words[4]);
		this.categoryFive = Integer.parseInt(words[5]);
	}
	
	/**
	 * Returns the description, or card name.
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Method that returns the integer from the first category.
	 * @return
	 */
	public int getCategoryOne() {
		return categoryOne; 
	}
	
	/**
	 * Method that returns the integer from the second category.
	 * @return
	 */
	public int getCategoryTwo() {
		return categoryTwo;
	}
	
	/**
	 * Method that returns the integer from the third category.
	 * @return
	 */
	public int getCategoryThree() {
		return categoryThree; 
	}
	
	/**
	 * Method that returns the integer from the fourth category.
	 * @return
	 */
	public int getCategoryFour() {
		return categoryFour;
	}
	
	/**
	 * Method that returns the integer from the fifth category.
	 * @return
	 */
	public int getCategoryFive() {
		return categoryFive;
	}
	
	/**
	 * Concatenates the card instance variables.
	 */
	public String toString() {
		return "" + description + " " + categoryOne + " " + categoryTwo + " " + categoryThree + " "
				+ categoryFour + " " + categoryFive;
	}
	
	/**
	 * Sets a String containing the category names to the class variable.
	 * @param info
	 */
	public void setCardInfo(String info) {
		this.cardInfo = info;
	}
	 /**
	  * Returns a String containing the category names of the class variable.
	  * @return
	  */
	public String getCardCategories() {
		return this.cardInfo;
	}
	
	/** 
	 * Returns a concatenation of all a Card's information in a single String.
	 * @return
	 */
	public String getCardInfo() {
		String categories = getDescription() + "\n";
		String card = getCardCategories();

		String catOne = "";
		String catTwo = "";
		String catThree = "";
		String catFour = "";
		String catFive = "";

		try {
			String[] cardInfo = card.split(" ");

			catOne = cardInfo[0];
			catTwo = cardInfo[1];
			catThree = cardInfo[2];
			catFour = cardInfo[3];
			catFive = cardInfo[4];

		}

		catch (NullPointerException n) {
			System.out.println("Null was found in 'getCardInfo' in 'Card' class");
		}

		categories += "1. " + catOne + ": \t" + categoryOne + "\n" + "2. " + catTwo + ": \t" + categoryTwo + "\n"
				+ "3. " + catThree + ": \t" + categoryThree + "\n" + "4. " + catFour + ": \t" + categoryFour + "\n"
				+ "5. " + catFive + ": \t\t" + categoryFive + "\n";

		return categories;
	}
	
	public int getCategoryValue(int choice) {
		int value = 0;
		if (choice == 1) {
			value = this.getCategoryOne();
		}
		if (choice == 2) {
			value = this.getCategoryTwo();
		}
		if (choice == 3) {
			value = this.getCategoryThree();
		}
		if (choice == 4) {
			value = this.getCategoryFour();
		}
		if (choice == 5) {
			value = this.getCategoryFive();
		}
		return value;
	}
	
} // End of class.
