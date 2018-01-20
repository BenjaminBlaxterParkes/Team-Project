package commandline;
import java.util.Collections;
import java.util.Comparator;

public class AIPlayer extends Player implements Comparable<Player> {

	AIPlayer(String name) {
		super(name);
	}
	
	public int chooseCategory() {
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
		for(i = 0; i < categories.length; i++) {
	        if (categories[i] > max) {
	            max = categories[i];
	            index = i;
	        }
	        
		}
		System.out.println("the max number for " + getName() + " is " + max + " ,which is category: " + (index + 1)+ "");
		
		
		return index + 1;
	}

	public int compareTo(Player other) {
		int result = 0;

		

		return result;
	}
	
}
