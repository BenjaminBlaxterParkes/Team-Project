package commandline;

public class AIPlayer extends Player implements Comparable<Player> {

	AIPlayer(String name) {
		super(name);
	}
	
	public void chooseCategory() {
		
	}
	
	
	public int compareTo(Player other) {
		int result = 0;

		if (this.getTopCard().getCategoryOne() > other.getTopCard().getCategoryOne()) { // if other is lesser, return 1
			result = 1; }
		
		else if (this.getTopCard().getCategoryOne() < other.getTopCard().getCategoryOne()) { // if other is greater, return -1
			result = -1; }
		
		else {
			result = 0; } // if equal, return 0

		return result;
	}
	
}
