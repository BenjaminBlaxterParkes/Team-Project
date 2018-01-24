package commandline;
import java.util.*;

/**
 * GameMaster class controls the flow of the Top Trumps game.  Its responsibilities 
 * include dealing cards, eliminating Player objects, and transferring
 * cards between Players.  
 * @author apalm
 *
 */

public class GameMaster {
	
	// Class variables
	final private int DECK_SIZE = 40;
	Card card;
	int numOfPlayers = 5; // CL version will always have 5 players
	//Player[] players = new Player[numOfPlayers];
	ArrayList<Player> playersArrList = new ArrayList<Player>();
	ArrayList<Card> cardsInPlay = new ArrayList<Card>();
	String pastActivePlayer;
	int draws, humanWin, AIWin;

	
	/**
	 * Loads Player objects into an array.
	 * @param p
	 * @param position
	 */
	public void loadPlayers(Player p, int position) {
		playersArrList.add(position, p);
		// System.out.println(p.getName());
	}
	
	
	public void createAI(int number) {

		String[] names = { "HAL 9000", "Cortana", "GLaDOS", "Marvin" };
		for (int i = 0; i < number; i++) {
			 playersArrList.add(new Player(names[i]));
		}
	}

	/**
	 * Returns a card from the shuffled deck.   
	 */
	public Card dealCard(CardDeck cd, int position) {
		// System.out.println("dealCard in CardDeck method has been activated");
		Card[] shuffledDeck = cd.getShuffledDeck();
		card = shuffledDeck[0];

		for (int i = 0; i < DECK_SIZE; i++) {
			card = shuffledDeck[position];
		}

		// System.out.println("The card currently being dealt is: " + card);
		return card;
	}
	
	public Player getPlayerByPosition(int position) {
		Player p = playersArrList.get(position);
		return p;
	}
	
	/**
	 * Returns a randomly chosen Player object to go first from ArrayList<Player>.
	 * @return
	 */
	public Player chooseFirstPlayer() {
		Collections.shuffle(playersArrList);
		setPastPlayerName();
		// System.out.println("The shuffled list of players is: " +
		// playersArrList.toString());
		Player p = playersArrList.get(0);
		System.out.println("--- " + p.getName() + " will go first ---\n");
		return p;
	}
	
	/**
	 * Returns all Player's name, number of cards in hand, and list of cards in hand.
	 * @return
	 */
	public String getPlayerArrayInfo() {
		String info = "";
		for (int i = 0; i < playersArrList.size(); i++) {
			info += playersArrList.get(i).toString() + "\n";
		}
		return info;
	}
	
	/**
	 * Sets PastPlayerName class variable.  
	 */
	public void setPastPlayerName() {
		// System.out.println("Most recent active player: " + getActivePlayerName());
		this.pastActivePlayer = getActivePlayerName();
	}
	
	/**
	 * Returns PastPlayerName class variable.  
	 * @return
	 */
	public String getPastPlayerName() {
		// System.out.println("Most recent active player: " + this.pastActivePlayer + "
		// was called");
		return this.pastActivePlayer;
	}
	
	/**
	 * Returns Player based on name parameter.
	 * @param name
	 * @return
	 */
	public Player getPlayerByName(String name) {
		Player p;
		for (int i = 0; i < playersArrList.size(); i++) {
			p = playersArrList.get(i);
			if (p.getName().equals(name)) {
				return p;
			} else {

			}
		}
		return null;
	}
	
	/**
	 * Returns active Player object.  
	 * @return
	 */
	public Player getActivePlayer() {
		Player p;
		p = playersArrList.get(0);
		return p;
	}
	
	/**
	 * Returns active Player ojbect's name.
	 * @return
	 */
	public String getActivePlayerName() {
		Player p = playersArrList.get(0);
		return p.getName();
	}
	
	/**
	 * Returns boolean stating whether a HumanPlayer objects exists in ArrayList<Player>.
	 * @param name
	 * @return
	 */
	public boolean findHumanPlayer(String name) {
		String other = "";
		for (int i = 0; i < playersArrList.size(); i++) {
			other = playersArrList.get(i).getName();
			if (other.equals(name)) {
				return true;
			} else {
				// System.out.println("Human player not found yet.");
			}

		}

		return false;
	}
	
	/**
	 * Returns size of the ArrayList<Player>.
	 * @return
	 */
	public int getArraySize() {
		int size = playersArrList.size();
		return size;
	}
	
	/**
	 * Sorts ArrayList<Player> based on integer parameter.  
	 * @param choice
	 */
	public void sortByCategory(int choice) {
		try {
			if (choice == 1) {
				Collections.sort(playersArrList, Player.sortByCategoryOne);
				System.out.println("\n_________________________________________\n"
						+ "** For " + getPastPlayerName() + "'s turn, they chose the category: Combat **");
			}

			if (choice == 2) {
				Collections.sort(playersArrList, Player.sortByCategoryTwo);
				System.out.println("\n_________________________________________\n"
						+ "** For " + getPastPlayerName() + "'s turn, they chose the category: Lewdness **");
			}

			if (choice == 3) {
				Collections.sort(playersArrList, Player.sortByCategoryThree);
				System.out.println("\n_________________________________________\n"
						+ "** For " + getPastPlayerName() + "'s turn, they chose the category: Agility **");
			}

			if (choice == 4) {
				Collections.sort(playersArrList, Player.sortByCategoryFour);
				System.out.println("\n_________________________________________\n"
						+ "** For " + getPastPlayerName() + "'s turn, they chose the category: Lunacy **");
			}

			if (choice == 5) {
				Collections.sort(playersArrList, Player.sortByCategoryFive);
				System.out.println("\n_________________________________________\n"
						+ "** For " + getPastPlayerName() + "'s turn, they chose the category: IQ **");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Cannot access position 0 of an empty Array");
		}

	}
	
	/**
	 * Removes cards from Players and places them in a ArrayList<Card> and then
	 * gives them to the winner of the round.
	 */
	public void communalPile() {
		Card c;
		for (int i = 0; i < playersArrList.size(); i++) {
			c = playersArrList.get(i).getTopCard();
			// System.out.println("the card " + c + " from player " +
			// playersArrList.get(i).getName()
			// + "is now in the comm pile. The array now holds " + cardsInPlay.toString());
			cardsInPlay.add(i, c);
			// System.out.println(cardsInPlay.toString());
			playersArrList.get(i).RemoveTopCard();
		}

		// System.out.println("The comm pile holds the following cards: " +
		// cardsInPlay.toString());

		if (checkforDraw() == false) {
			rewardWinner();
		}

	}
	
	/**
	 * Checks to see if draw, or tie, happened during the round.
	 * @return
	 */
	public boolean checkforDraw() {
		card = cardsInPlay.get(0);
		Card other = cardsInPlay.get(1);

		Player p = getPlayerByName(getPastPlayerName());

		int category = p.getCategoryChoice();
		// System.out.println("\n"+getPastPlayerName() + "'s draw category was:"
		// + (category));

		if (card.getCategoryValue(category) == other.getCategoryValue(category)) {
			draws++;
			System.out.println("\n*** TIE DETECTED ***");
			System.out.println("The cards " + card.getDescription() + " and " + other.getDescription() + " tied.");
			System.out.println("Next round will be the tie breaker!\n");
			// System.out.println("because " + card.getCategoryValue(category) + " equals "
			// + other.getCategoryValue(category) + "\n");
			return true;
		}
		return false;
	}
	
	public int getDraws() {
		return this.draws;
	}
	
	public int getAIWin() {
		return this.AIWin;
	}
	
	public int getHumanWin() {
		return this.humanWin;
	}
	
	public boolean checkHumanName(String name) {
		if ((name.equals("HAL 9000")) ||
				(name.equals("Cortana")) ||
				(name.equals("GLaDOS")) ||
				(name.equals("Marvin"))) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gives Cards from ArrayList<Card> to the winner of the round.    
	 */
	public void rewardWinner() {
		setPastPlayerName();
		System.out.println("\n" + playersArrList.get(0).getName() + " won the round!");
		
		if ((getActivePlayerName().equals("HAL 9000")) ||
				(getActivePlayerName().equals("Cortana")) ||
				(getActivePlayerName().equals("GLaDOS")) ||
				(getActivePlayerName().equals("Marvin"))) {
			 AIWin++;
		}
		else {
			humanWin++;
		}
		
		System.out.println("Their winning card was: \n" + cardsInPlay.get(0).getCardInfo() + "\n**\n**\n\nThe next round has started!\n");
		Card c;
		for (int i = 0; i < cardsInPlay.size(); i++) {
			c = cardsInPlay.get(i);
			playersArrList.get(0).setHand(c);
		}
		cardsInPlay.clear();
		// System.out.println(playersArrList.get(0).toString());
		// System.out.println(cardsInPlay.toString());
	}
	
	/**
	 * Removes a Player from the ArrayList<Player>. 
	 */
	public void playerIsElminated() {
		for (int i = 0; i < playersArrList.size(); i++) {
			if (playersArrList.get(i).getNumOfCardsInHand() == 0) {
				// System.out.println(playersArrList.get(i).getName() + " was eliminated");
				playersArrList.remove(i);
				i--;

			} else {
				// System.out.println(playersArrList.get(i).getName() + " still has cards, is
				// safe");
			}
		}
		// for(int i =0; i < playersArrList.size(); i++) {
		// System.out.println(playersArrList.get(i).getName());
		// }
	}

	
} // End of class
