package commandline;
import java.awt.desktop.SystemEventListener;
import java.util.*;

public class GameMaster {
	
	final private int DECK_SIZE = 40;
	CardDeck cardDeck = new CardDeck();
	Card card;
	int numOfPlayers = 5; // CL version will always have 5 players
	Player[] players = new Player[numOfPlayers];
	ArrayList<Player> playersArrList; 
	ArrayList<Card> cardsInPlay = new ArrayList<Card>();
	//Card[] shuffleDeck = cardDeck.getShuffledDeck();
	

	/**
	 * This will load the player objects into an array
	 * @param p
	 * @param position
	 */
	public void loadPlayers(Player p, int position) {
		players[position] = p;
		//System.out.println(p.getName());
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
		//System.out.println("The shuffled list of players is: " + playersArrList.toString());
		Player p = playersArrList.get(0);
		System.out.println("The first player will be: " + p.getName());
		return p;
	}
	
	public String getPlayerArrayInfo() {
		String info = "";
		for (int i = 0; i < playersArrList.size(); i ++) {
			info += playersArrList.get(i).toString() + "\n";
		}
		return info;
	}
	
	public Player getActivePlayer() {
		Player p;
		p = playersArrList.get(0);
		return p; 
	}
	
	public String getActivePlayerName() {
		Player p = playersArrList.get(0);
		return p.getName();
	}
	
	public int getArraySize() {
		int size = playersArrList.size();
		return size;
	}
	

	
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
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("can't access pos 0 of empty array");
		}
		
		

//		for(int i = 0; i < playersArrList.size(); i++) {
//			System.out.println(playersArrList.get(i));
//		}
		
	}
	 
	public void communalPile() {
		Card c;
		for(int i = 0; i < playersArrList.size(); i++) {
			c = playersArrList.get(i).getTopCard();
//			System.out.println("the card " + c + " from player " + playersArrList.get(i).getName() 
//								+ "is now in the comm pile.  The array now holds " + cardsInPlay.toString());
			cardsInPlay.add(i, c);
			//System.out.println(cardsInPlay.toString());
			playersArrList.get(i).RemoveTopCard();
		}
		
		
		System.out.println("The comm pile holds the following cards: " + cardsInPlay.toString());
		rewardWinner();
	}
	
	
	public void rewardWinner() {
		System.out.println("The winner was " + playersArrList.get(0).getName());
		System.out.println("Their card was: " + cardsInPlay.get(0).getCardInfo());
		Card c;
		for(int i = 0; i < cardsInPlay.size(); i++) {
			c = cardsInPlay.get(i);
			playersArrList.get(0).setHand(c);
		}
		cardsInPlay.clear();
		//System.out.println(playersArrList.get(0).toString());
		//System.out.println(cardsInPlay.toString());
	}
	
	
	public void playerIsElminated() {
		for(int i = 0; i < playersArrList.size(); i ++) {
			if(playersArrList.get(i).getNumOfCardsInHand() == 0) {
				//System.out.println(playersArrList.get(i).getName() + " was eliminated");
				playersArrList.remove(i);
				
			}
			else {
				//System.out.println(playersArrList.get(i).getName() + " still has cards, is safe");
			}
		}
//		for(int i =0; i < playersArrList.size(); i++) {
//			System.out.println(playersArrList.get(i).getName());
//		}
	}
	// will find the player with the highest category amount
//	public Player findWinner() {
//		Player p;
//		return p;
//	}
	
	
	
	
	
}
