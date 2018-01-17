package commandline;

public class Card {

	final protected String description;
	final protected int categoryOne, categoryTwo, categoryThree, categoryFour, categoryFive;
	
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
	public int getcategoryThree() {
		return categoryThree; 
	}
	
	/**
	 * Method that returns the integer from the fourth category
	 * @return
	 */
	public int getcategoryFour() {
		return categoryFour;
	}
	
	/**
	 * Method that returns the integer from the fifth category
	 * @return
	 */
	public int getcategoryFive() {
		return categoryFive;
	}
	
}
