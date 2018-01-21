package commandline;
import java.awt.desktop.SystemEventListener;
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
	Player[] players = new Player[numOfPlayers];
	ArrayList<Player> playersArrList;
	ArrayList<Card> cardsInPlay = new ArrayList<Card>();
	
	/**
	 * Loads Player objects into an array.
	 * @param p
	 * @param position
	 */
	public void loadPlayers(Player p, int position) {
		players[position] = p;
		// System.out.println(p.getName());
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
	
	/**
	 * Returns a randomly chosen Player object to go first from ArrayList<Player>.
	 * @return
	 */
	public Player chooseFirstPlayer() {
		playersArrList = new ArrayList<Player>(Arrays.asList(players));
		Collections.shuffle(playersArrList);
		// System.out.println("The shuffled list of players is: " +
		// playersArrList.toString());
		Player p = playersArrList.get(0);
		System.out.println("The first player will be: " + p.getName() + "\n");
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
				System.out.println("The chosen category was Combat");
			}

			if (choice == 2) {
				Collections.sort(playersArrList, Player.sortByCategoryTwo);
				System.out.println("The chosen category was Lewdness");
			}

			if (choice == 3) {
				Collections.sort(playersArrList, Player.sortByCategoryThree);
				System.out.println("The chosen category was Agility");
			}

			if (choice == 4) {
				Collections.sort(playersArrList, Player.sortByCategoryFour);
				System.out.println("The chosen category was Lunacy");
			}

			if (choice == 5) {
				Collections.sort(playersArrList, Player.sortByCategoryFive);
				System.out.println("The chosen category was IQ");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Cannot access position 0 of an empty Array");
		}

	}
	
	/**
	 * Removes cards from Players and places them in a ArrayList<Card> 
	 * and then gives them to the winner of the round.  
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

		System.out.println("The comm pile holds the following cards: " + cardsInPlay.toString());
		rewardWinner();
	}
	
	/**
	 * Gives Cards from ArrayList<Card> to the winner of the round.    
	 */
	public void rewardWinner() {
		System.out.println("\nThe winner was " + playersArrList.get(0).getName());
		System.out.println("Their winning card was: " + cardsInPlay.get(0).getCardInfo());
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
