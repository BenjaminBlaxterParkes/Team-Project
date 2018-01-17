package commandline;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

// Class to contain all variables contained in the deck of cards
public class CardDeck {
	
	// Constant variables
	final private int DECK_SIZE = 40;
	
	// Instance variables
	private String deckOfCards;	
	private Card[] originalDeck = new Card[DECK_SIZE];
	private Card[] shuffledDeck = new Card[DECK_SIZE];
	private ArrayList<Card> tempDeck;
	Card card;
	
	// CardDeck object
	// Initiates reading in deck file.txt
	public CardDeck() {
		
		// Read in deck .txt file
   		StringBuilder deckFile = new StringBuilder();
   			try (Stream<String> stream = Files.lines(Paths.get("FuturamaDeck.txt"), StandardCharsets.UTF_8))
   			{
   			stream.forEach(s -> deckFile.append(s).append("\n"));
   			}
   			catch (IOException e)
   			{
   			e.printStackTrace();
   			}
   			deckOfCards = deckFile.toString(); // Save deck file as string
//			System.out.println(deckOfCards);

	}
	
	
	// Method to sort the deck file into individual cards and populate originalDeck[]
	public void populateDeck() {
		
		Card newCard; // new card objects to fill originalDeck[]
		String[] deckSplit = deckOfCards.split("\n"); // split to individual cards
		
		// Loop through deck, index i = 1 to skip first line of deck file
		for (int i = 1; i < deckSplit.length; i++) {
				String singleCard = deckSplit[i]; // Store each line of deck file
				newCard = new Card(singleCard); // Create new Card with each new line
				originalDeck[i-1] = newCard; // Insert Card elements into array, i-1 to fill index 0
		}
	}
	
	
	// Method to shuffle originalDeck
	public void shuffleDeck() {
		
		Card tempCard; // Card object for passing from originalDeck to shuffledDeck
		
		tempDeck = new ArrayList<Card>(Arrays.asList(originalDeck)); // Convert originalDeck into ArrayList
		System.out.println(tempDeck);
		
		Collections.shuffle(tempDeck); // Shuffle cards
		System.out.println(tempDeck.toString());

		// Loop through shuffled cards
		for (int i = 0; i < DECK_SIZE; i++) {
			tempCard = tempDeck.get(i); // Stored each card
			shuffledDeck[i] = tempCard; // Populate the shuffled deck
			System.out.println(shuffledDeck[i].getDescription());
		}
	}
	
	
	// Accessor methods
	public Card[] getOriginalDeck() {
		return originalDeck;
	}
	public Card[] getShuffledDeck() {
		return shuffledDeck;
	}
	
	// Main class for testing
	public static void main(String[] args) {
		
		CardDeck cd = new CardDeck();
		cd.populateDeck();
		cd.shuffleDeck();
	}
}
