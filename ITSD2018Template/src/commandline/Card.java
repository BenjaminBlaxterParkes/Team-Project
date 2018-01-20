package commandline;

public class Card {

	final protected String description;
	final protected int categoryOne, categoryTwo, categoryThree, categoryFour, categoryFive;
	String cardInfo = "";
	
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
	 * This constructors takes a single String and parses it out
	 * the description and five categories to create the Card object.
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
	 * Returns the description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Method that returns the integer from the first category
	 * @return
	 */
	public int getCategoryOne() {
		return categoryOne; 
	}
	
	/**
	 * Method that returns the integer from the second category
	 * @return
	 */
	public int getCategoryTwo() {
		return categoryTwo;
	}
	
	/**
	 * Method that returns the integer from the third category
	 * @return
	 */
	public int getCategoryThree() {
		return categoryThree; 
	}
	
	/**
	 * Method that returns the integer from the fourth category
	 * @return
	 */
	public int getCategoryFour() {
		return categoryFour;
	}
	
	/**
	 * Method that returns the integer from the fifth category
	 * @return
	 */
	public int getCategoryFive() {
		return categoryFive;
	}
	
	public String toString() {
		return "" + description + " " + categoryOne + " " + categoryTwo + " " + categoryThree + " "
				+ categoryFour + " " + categoryFive;
	}
	
	public void setCardInfo(String info) {
		this.cardInfo = info;
	}
	
	public String getCardCategories() {
		return this.cardInfo;
	}
	
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
		
		categories += 	"1. " + catOne + ": \t" + categoryOne + "\n"
							+ "2. " +   catTwo + ": \t" + categoryTwo + "\n"
							+ "3. " +  catThree + ": \t" + categoryThree + "\n"
							+ "4. " +   catFour + ": \t" + categoryFour + "\n"
							+ "5. " +   catFive + ": \t\t" + categoryFive + "\n";
		
		return categories;
	}
	
}
