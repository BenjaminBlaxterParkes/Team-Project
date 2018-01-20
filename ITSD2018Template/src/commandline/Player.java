package commandline;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Player implements Comparable<Player> {

	protected String name;
	public ArrayList<Card> hand = new ArrayList<Card>();
	protected int numOfCardsInHand;
	
	Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHand(Card c) {
		hand.add(c);
		numOfCardsInHand++;
		//System.out.println("The card (" + c.getDescription() + ") is being loaded into "+ getName() + "'s hand");
	}
	
	
	public void RemoveTopCard() {
		hand.remove(0);
		numOfCardsInHand--;
	}
	
	public Card getTopCard() {
		Card card = hand.get(0);
		return card;
	}
	
	public int getNumOfCardsInHand() {
		return this.numOfCardsInHand;
	}
	
	public String getTopCardInfo() {
		String desciption = hand.get(0).getDescription() + "\n";
		String allCardInfo = hand.get(0).getCardInfo();
		
		String catOne = "";
		String catTwo = "";
		String catThree = ""; 
		String catFour = "";
		String catFive = "";
		
		try {
		String[] cardInfo = allCardInfo.split(" ");
		
		catOne = cardInfo[0];
		catTwo = cardInfo[1];
		catThree = cardInfo[2];
		catFour = cardInfo[3];
		catFive = cardInfo[4];
		
		}
		
		catch (NullPointerException n) {
			System.out.println("Null was found in 'getTopCardInfo' in 'Players' class");
		}
		
		String categories = "1. " + catOne + ": \t" + hand.get(0).categoryOne + "\n"
							+ "2. " +   catTwo + ": \t" + hand.get(0).categoryTwo + "\n"
							+ "3. " +  catThree + ": \t" + hand.get(0).categoryThree + "\n"
							+ "4. " +   catFour + ": \t" + hand.get(0).categoryFour + "\n"
							+ "5. " +   catFive + ": \t\t" + hand.get(0).categoryFive + "\n";
		
		return desciption + categories;
		
	}
	
	public String toString() {
		return "" + name + ", has num cards: " + getNumOfCardsInHand() +  " & hand: \t" + hand.toString();
	}
	

	public static Comparator<Player> sortByCategoryOne = new Comparator<Player>() {

		public int compare(Player one, Player two) {
				int compareOne = one.getTopCard().getCategoryOne();
				int compareTwo = two.getTopCard().getCategoryOne();

				return compareTwo-(compareOne);
		}
	};
	
	public static Comparator<Player> sortByCategoryTwo = new Comparator<Player>() {

		public int compare(Player one, Player two) {
				int compareOne = one.getTopCard().getCategoryTwo();
				int compareTwo = two.getTopCard().getCategoryTwo();

				return compareTwo-(compareOne);
		}
	};
	
	public static Comparator<Player> sortByCategoryThree = new Comparator<Player>() {

		public int compare(Player one, Player two) {
				int compareOne = one.getTopCard().getCategoryThree();
				int compareTwo = two.getTopCard().getCategoryThree();

				return compareTwo-(compareOne);
		}
	};
	
	public static Comparator<Player> sortByCategoryFour = new Comparator<Player>() {

		public int compare(Player one, Player two) {
				int compareOne = one.getTopCard().getCategoryFour();
				int compareTwo = two.getTopCard().getCategoryFour();

				return compareTwo - compareOne;
		}
	};
	
	public static Comparator<Player> sortByCategoryFive = new Comparator<Player>() {

		public int compare(Player one, Player two) {
				int compareOne = one.getTopCard().getCategoryFive();
				int compareTwo = two.getTopCard().getCategoryFive();

				return compareTwo-(compareOne);
		}
	};
	

}
