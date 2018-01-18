package commandline;
import java.util.*;

public class GameMaster {
	
	final private int DECK_SIZE = 40;
	CardDeck cardDeck = new CardDeck();
	Card card;
	int numOfPlayers = 5; // CL version will always have 5 players
	Player[] players = new Player[numOfPlayers];
	ArrayList<Player> playersArrList; 
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
		playersArrList = new ArrayList<Player>(Arrays.asList(players));
		Collections.shuffle(playersArrList);
		System.err.println("The shuffled list of players is: " + playersArrList.toString());
		Player p = playersArrList.get(0);
		System.err.println("The first player will be: " + p.getName());
		return p;
	}
	
	public Player getActivePlayer() {
		Player p;
		p = playersArrList.get(0);
		return p; 
	}
	
	public int getArraySize() {
		int size = playersArrList.size();
		return size;
	}
	
	public void categoryChosen(int choice) {
		//choice = category (say, 1, which is combat)
		// int max = 0;
		//for(int i = 0; i < playersArrList; i++){
		//	if (playersArrList.getTopHand[i] > max) {
        	//max = playersArrList[i];}
	
    }
		//return category;
	
	public void sortByFirstCategory() {
		Collections.sort(playersArrList, Collections.reverseOrder());
		
		for(int i = 0; i < playersArrList.size(); i++) {
			System.out.println(playersArrList.get(i));
		}
		
	}
	 
	
	// will find the player with the highest category amount
//	public Player findWinner() {
//		Player p;
//		return p;
//	}
	
}
