package commandline;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * CardDeck class organizes Card objects and shuffles them so the GameMaster
 * can deal them out to the Player objects.  
 * @author apalm
 *
 */

// Class to contain all variables contained in the deck of cards
public class CardDeck {
	
	// Constant variables
	final private int DECK_SIZE = 40;
	final private int NUM_OF_CATEGORIES = 5;
	final String FUTURAMA_DECK = "FuturamaDeck.txt";
	
	// Instance variables
	private String deckOfCards;
	private String [] categories = new String[NUM_OF_CATEGORIES];
	private Card[] originalDeck = new Card[DECK_SIZE];
	public Card[] shuffledDeck = new Card[DECK_SIZE];
	private ArrayList<Card> tempDeck;
	private String listOfCategories;

	
	// CardDeck object
	// Initiates reading in deck file.txt
	public CardDeck() {

		// Read in deck .txt file
		StringBuilder deckFile = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(FUTURAMA_DECK), StandardCharsets.UTF_8)) {
			stream.forEach(s -> deckFile.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String deckAsString = deckFile.toString(); // Save deck file as string
		String[] temp = deckAsString.split("\n", 2);

		String descriptionAsString = temp[0];
		// System.out.println(descriptionAsString);

		deckOfCards = temp[1];
		// System.out.println(deckOfCards);

		String[] temp2 = descriptionAsString.split(" ", 2);
		this.listOfCategories = temp2[1];
		// System.out.println(listOfCategories + " is list of categories");
		categories = listOfCategories.split(" ");
		// System.out.println(deckOfCards);
	}
	
	/**
	 * Sorts the deck file into individual Cards and populate originalDeck[].
	 */
	public void populateDeck() {
		// System.out.println(listOfCategories + " in populateDeck");
		Card newCard; // new card objects to fill originalDeck[]
		// String [] temp = deckOfCards.split("\n", 2);
		// String doc = temp[1];
		String[] deckSplit = deckOfCards.split("\n"); // split to individual cards

		// Loop through deck, index i = 1 to skip first line of deck file
		for (int i = 0; i < deckSplit.length; i++) {
			String singleCard = deckSplit[i]; // Store each line of deck file
			newCard = new Card(singleCard); // Create new Card with each new line
			newCard.setCardInfo(listOfCategories);

			originalDeck[i] = newCard; // Insert Card elements into array, i-1 to fill index 0
		}
	}
	
	/**
	 * Shuffles originalDeck.
	 */
	public void shuffleDeck() {
		Card tempCard; // Card object for passing from originalDeck to shuffledDeck

		tempDeck = new ArrayList<Card>(Arrays.asList(originalDeck)); // Convert originalDeck into ArrayList
		// System.out.println(tempDeck);

		Collections.shuffle(tempDeck); // Shuffle cards
		// System.out.println(tempDeck.toString());

		// Loop through shuffled cards
		for (int i = 0; i < DECK_SIZE; i++) {
			tempCard = tempDeck.get(i); // Stored each card
			shuffledDeck[i] = tempCard; // Populate the shuffled deck
			// System.out.println(shuffledDeck[i].getDescription());
		}
	}
	
	/**
	 * Returns original deck from the Card[].
	 * @return
	 */
	public Card[] getOriginalDeck() {
		return originalDeck;
	}
	
	/**
	 * Returns shuffled dead from the Card[].
	 * @return
	 */
	public Card[] getShuffledDeck() {
		return shuffledDeck;
	}
	
	/**
	 * Returns the categories from the .txt file.
	 * @return
	 */
	public String[] getCategories() {
		return categories; 
	}
	
}
