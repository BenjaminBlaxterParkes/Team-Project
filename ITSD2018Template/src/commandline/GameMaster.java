package commandline;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * GameMaster class controls the flow of the Top Trumps game. Its
 * responsibilities include dealing cards, eliminating Player objects, and
 * transferring cards between Players.
 * @author apalm
 *
 */

public class GameMaster {

	/*
	 * Class variables
	 */
	final private int DECK_SIZE = 40; // Deck will always have 40 cards
	Card card; // instantiate a card object
	ArrayList<Player> playersArrList = new ArrayList<Player>(); // instantiate array of players
	ArrayList<Card> cardsInPlay = new ArrayList<Card>(); // instantiate cardsInPlay array
	String pastActivePlayer;
	int draws, humanWin, AIWin;

	// Instantiate Logger for logging
	final static Logger LOGGER = Logger.getLogger(TopTrumpsCLIApplication.class.getName());
	FileHandler fh = null;

	/**
	 * Method to create logger file handler and
	 * do logger setup
	 * @param log
	 */
	public void startLogger(boolean log) {
		if (log == true) {
			LOGGER.setLevel(Level.INFO); // Set logger level to info
			try {
				fh = new FileHandler("toptrumps.log"); // Set log name and save in src folder, don't append files
				System.out.println("logger is working");
				LOGGER.addHandler(fh); // add handler to logger
				fh.setFormatter(new SimpleFormatter()); // set format to simpleFormatter
				LOGGER.setUseParentHandlers(false);
			} catch (Exception e) {
				System.out.println("FileHander can't handle .log file"); // print out error message if exception is caught
			}
		}
	}


	/**
	 * Method to close the logger when it's done logging
	 * @param log
	 */
	public void closeLogger(boolean log) {
		if (log == true) {
			try {
				fh.close(); // close fileHandler
				System.out.println("filehandler closed okay");
			} catch (NullPointerException n) {
				System.out.println("filehandler couldn't close"); // catch exceptions
			}
		}
	}




	/**
	 * Loads Player objects into an array.
	 * @param p
	 * @param position
	 */
	public void loadPlayers(Player p, int position) {
		playersArrList.add(position, p);
	}


	/**
	 * Method to create AI players based on user input
	 * @param number
	 */
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
		Card[] shuffledDeck = cd.getShuffledDeck(); // load shuffled deck into a new array

		for (int i = 0; i < DECK_SIZE; i++) {
			card = shuffledDeck[position];
		}
		return card;
	}


	/**
	 * Returns the player at a given position in the array
	 * @param position
	 * @return
	 */
	public Player getPlayerByPosition(int position) {
		Player p = playersArrList.get(position);
		return p;
	}


	/**
	 * Returns a randomly chosen Player object to go first from ArrayList<Player>.
	 * @return
	 */
	public Player chooseFirstPlayer() {
		Collections.shuffle(playersArrList); // shuffle the array of players
		setPastPlayerName();
		Player p = playersArrList.get(0); // p is the first player
		System.out.println("--- " + p.getName() + " will go first ---\n");
		return p;
	}


	/**
	 * Returns all Player's name, number of cards in hand, and list of cards in
	 * hand.
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
		this.pastActivePlayer = getActivePlayerName();
	}


	/**
	 * Returns PastPlayerName class variable.
	 * @return
	 */
	public String getPastPlayerName() {
		return this.pastActivePlayer;
	}


	/**
	 * Returns Player based on given name.
	 * @param name
	 * @return
	 */
	public Player getPlayerByName(String name) {
		Player p;
		for (int i = 0; i < playersArrList.size(); i++) {
			p = playersArrList.get(i);
			// if given String name is equal to the players name in the array...
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
	 * Returns boolean stating whether a HumanPlayer objects exists in
	 * ArrayList<Player>.
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

	//method getPlayersArrList()

	/**
	 * Returns the ArrayList<Player>.
	 * @return
	 */
	public ArrayList<Player> getPlayersArrList() {
		return playersArrList;
	}
// method added by Cnr 
	public ArrayList<Card> getNumCardsInPlay(){
		return cardsInPlay;
	}
	/**
	 * Sorts ArrayList<Player> based on the integer choice.
	 * @param choice
	 */
	public void sortByCategory(int choice) {
		String option = "";
		String words = "\nThe category selected and corresponding values when a user or computer selects a category: \n";
		String breaking = "\n ----------------------------------------------";

		try {
			if (choice == 1) { // if the int choice is equal to 1
				Collections.sort(playersArrList, Player.sortByCategoryOne); // sort the players array based on cat 1
				option = ("\n_________________________________________\n" + "** For " + getPastPlayerName()
						+ "'s turn, they chose the category: Combat for "
						+ getPlayerByName(getPastPlayerName()).getTopCard().getCategoryOne() + " points.");
				System.out.println(option);

				// Log what's in communal pile
				TopTrumpsCLIApplication.LOGGER.info(words + option + breaking);
			}


			if (choice == 2) { // if the int choice is equal to 2
				Collections.sort(playersArrList, Player.sortByCategoryTwo); // sort the players array based on cat 2
				option = ("\n_________________________________________\n" + "** For " + getPastPlayerName()
						+ "'s turn, they chose the category: Lewdness for "
						+ getPlayerByName(getPastPlayerName()).getTopCard().getCategoryTwo() + " points.\"");
				System.out.println(option);
				TopTrumpsCLIApplication.LOGGER.info(words + option + breaking);
			}


			if (choice == 3) { // if the int choice is equal to 3
				Collections.sort(playersArrList, Player.sortByCategoryThree); // sort the players array based on cat 3
				option = ("\n_________________________________________\n" + "** For " + getPastPlayerName()
						+ "'s turn, they chose the category: Agility for "
						+ getPlayerByName(getPastPlayerName()).getTopCard().getCategoryThree() + " points.");
				System.out.println(option);
				TopTrumpsCLIApplication.LOGGER.info(words + option + breaking);
			}


			if (choice == 4) { // if the int choice is equal to 4
				Collections.sort(playersArrList, Player.sortByCategoryFour); // sort the players array based on cat 4
				option = ("\n_________________________________________\n" + "** For " + getPastPlayerName()
						+ "'s turn, they chose the category: Lunacy for "
						+ getPlayerByName(getPastPlayerName()).getTopCard().getCategoryFour() + " points.");
				System.out.println(option);
				TopTrumpsCLIApplication.LOGGER.info(words + option + breaking);
			}


			if (choice == 5) { // if the int choice is equal to 5
				Collections.sort(playersArrList, Player.sortByCategoryFive); // sort the players array based on cat 5
				option = ("\n_________________________________________\n" + "** For " + getPastPlayerName()
						+ "'s turn, they chose the category: IQ for "
						+ getPlayerByName(getPastPlayerName()).getTopCard().getCategoryFive() + " points.");
				System.out.println(option);
				TopTrumpsCLIApplication.LOGGER.info(words + option + breaking);
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
			cardsInPlay.add(i, c);
			playersArrList.get(i).RemoveTopCard();
		}
		if (checkforDraw() == false) {
			rewardWinner(); // if no draw, add communal pile to winners hand
		}
	}


	/**
	 * Return the communal pile
	 * @return
	 */
	public String getCommPile() {
		return cardsInPlay.toString();
	}

	/**
	 * Returns the array cardsInPlay as a string
	 * @return
	 */
	public String getCardsInPlay() {
		String cards = "";
		for (int i = 0; i < playersArrList.size(); i++) {
			cards += playersArrList.get(i).getTopCardInfo();
		}
		return cards;
	}


	/**
	 * Checks if draw, or tie, happened during the round.
	 * @return
	 */
	public boolean checkforDraw() {
		card = cardsInPlay.get(0); // load first card of comm. pile
		Card other = cardsInPlay.get(1); // load second card of the comm. pile

		Player p = getPlayerByName(getPastPlayerName()); // load person who initiated tie

		int category = p.getCategoryChoice(); // find out the category choice

		// if the 2 cards are equal, a draw has occured, based on category
		if (card.getCategoryValue(category) == other.getCategoryValue(category)) {
			draws++;
			System.out.println("\n*** TIE DETECTED ***");
			System.out.println("The cards " + card.getDescription() + " and " + other.getDescription() + " tied.");
			System.out.println("Next round will be the tie breaker!\n");
			return true;
		}
		return false;
	}


	/**
	 * Returns the number of draws
	 * @return
	 */
	public int getDraws() {
		return this.draws;
	}


	/**
	 * Returns number of AI wins
	 * @return
	 */
	public int getAIWin() {
		return this.AIWin;
	}


	/**
	 * Returns number of human player wins
	 * @return
	 */
	public int getHumanWin() {
		return this.humanWin;
	}


	/**
	 * Checks to make sure human name isn't an AI name
	 * @param name
	 * @return
	 */
	public boolean checkHumanName(String name) {
		if ((name.equals("HAL 9000")) || (name.equals("Cortana")) || (name.equals("GLaDOS"))
				|| (name.equals("Marvin"))) {
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

		// if the winner is an AI, increment AI wins
		if ((getActivePlayerName().equals("HAL 9000")) || (getActivePlayerName().equals("Cortana"))
				|| (getActivePlayerName().equals("GLaDOS")) || (getActivePlayerName().equals("Marvin"))) {
			AIWin++;
		} else { // else, increment human wins
			humanWin++;
		}

		// Log what's in communal pile
		TopTrumpsCLIApplication.LOGGER
				.info("\nThe contents of the communal pile when cards are added or removed from it: \n" + getCommPile()
						+ "\n ----------------------------------------------");

		System.out.println("Their winning card was: \n" + cardsInPlay.get(0).getCardInfo()
				+ "\n**\n**\n\nThe next round has started!\n");

		// for loop to give comm. pile to winner
		Card c;
		for (int i = 0; i < cardsInPlay.size(); i++) {
			c = cardsInPlay.get(i); // load card from comm. pile
			playersArrList.get(0).setHand(c); // set to winners hand
		}
		cardsInPlay.clear(); // clear comm. pile
	}


	/**
	 * Removes a Player from the ArrayList<Player> when eliminated.
	 */
	public void playerIsElminated() {
		for (int i = 0; i < playersArrList.size(); i++) {
			if (playersArrList.get(i).getNumOfCardsInHand() == 0) {
				playersArrList.remove(i); // remove player if their hand is empty
				i--; // decrement if person is removed, so loop is correct
			} else {
			}
		}
	}


	/**
	 * Returns a string of the player and their hand
	 * @return
	 */
	public String playersAndHand() {
		String players = "";
		for (int i = 0; i < playersArrList.size(); i++) {
			players += playersArrList.get(i).getName() + "'s hand is: " + playersArrList.get(i).getHand() + "\n";
		}
		return players;
	}

} // End of class
