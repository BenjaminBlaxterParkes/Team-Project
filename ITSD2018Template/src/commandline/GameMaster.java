package commandline;
import java.util.*;

public class GameMaster {
	
	final private int DECK_SIZE = 40;
	CardDeck cardDeck = new CardDeck();
	Card card;
	int numOfPlayers = 5; // CL version will always have 5 players
	Player[] players = new Player[numOfPlayers];
	Card[] shuffleDeck = cardDeck.getShuffledDeck();
	
	
	
	/**
	 * This method randomly chooses which player goes first
	 * @return
	 */
	public Player chooseFirstPlayer() {
		Random rand = new Random();
		int firstPlayer = rand.nextInt(numOfPlayers -1);
		Player p;
		p = players[firstPlayer];
		System.err.println("The first player will be " + p.getName());
		return p;
	}
	
	
	/**
	 * This will load the player objects into an array
	 * @param p
	 * @param position
	 */
	public void loadPlayers(Player p, int position) {
		players[position] = p;
		System.err.println(p.getName());
	}
	
	/**
	 * This method will deal out the cards
	 */
	public Card dealCard(int position) {
		for(int i = 0; i < DECK_SIZE; i++) {
		System.err.println(shuffleDeck[i]);
		}
		Card card;
		System.out.println("dealCard in CardDeck method has been activated");
		card = shuffleDeck[position];
		System.out.println("The card is: " + card);
		return card;
	}
	
	// will find the player with the highest category amount
//	public Player findWinner() {
//		Player p;
//		return p;
//	}
	
}
