package commandline;

/**
 * HumanPlayer class objects hold Cards and play in rounds to gain more cards.
 * @author apalm
 *
 */

public class HumanPlayer extends Player implements Comparable<Player> {

	/**
	 * Constructor
	 * @param name
	 */
	HumanPlayer(String name) {
		super(name);
	}
	
	public int compareTo(Player other) {
		int result = 0;

		return result;
	}
	
}

