package commandline;
import java.util.*;

public class GameMaster {
	
	final private int DECK_SIZE = 40;
	CardDeck cardDeck = new CardDeck();
	Card card;
	int numOfPlayers = 5; // CL version will always have 5 players
	Player[] players = new Player[numOfPlayers];
	//Card[] shuffleDeck = cardDeck.getShuffledDeck();
	

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
	public Card dealCard(CardDeck cd, int position) {
		//System.out.println("dealCard in CardDeck method has been activated");
		Card[] shuffledDeck = cd.getShuffledDeck();
		Card card = shuffledDeck[0];
		
		for(int i = 0; i < DECK_SIZE; i++) {
			card = shuffledDeck[position];
		}
		
		//System.out.println("The card currently being dealt is: " + card);
		return card;
	}
	
	/**
	 * This method randomly chooses which player goes first
	 * @return
	 */
	public Player chooseFirstPlayer() {
		Random rand = new Random();
		int firstPlayer = rand.nextInt(numOfPlayers -1);
		Player p;
		p = players[firstPlayer];
		System.err.println("The first player will be: " + p.getName());
		return p;
	}
	
	
	// will find the player with the highest category amount
//	public Player findWinner() {
//		Player p;
//		return p;
//	}
	
}
