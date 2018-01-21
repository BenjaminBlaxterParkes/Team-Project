package commandline;
import java.util.*;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	
	public static void main(String[] args) {
		
		// ----------------------------------------------------
		// Add your game logic here based on the requirements
		// ----------------------------------------------------
		
		// Main variables
		GameMaster gm = new GameMaster();
		CardDeck cd = new CardDeck();
		final int DECK_SIZE = 40;
		int round = 1;
		AIPlayer AI;
		HumanPlayer HP;
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) {
//			writeGameLogsToFile = true; // Command line selection 
//			}

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		Scanner in = new Scanner(System.in);

		// Start game loop
		while (!userWantsToQuit == true) {
		
		System.out.println("Main Menu \n1. Start New Game \n2. See Past Stats");
		String answer = in.next();

		if (answer.equals("1")) {
			
			// start round loop
			int play = 1;
			while (play == 1) {

				// Get human player's name
				System.out.println("\nWhat is your name?");
				String name = in.next();
				
				// Instantiate players
				HumanPlayer human = new HumanPlayer(name);
				AIPlayer AI1 = new AIPlayer("HAL 9000");
				AIPlayer AI2 = new AIPlayer("Cortana");
				AIPlayer AI3 = new AIPlayer("GLaDOS");
				AIPlayer AI4 = new AIPlayer("Marvin");
				
				// Game Master loads players into an ArrayList
				gm.loadPlayers(human, 0);
				gm.loadPlayers(AI1, 1);
				gm.loadPlayers(AI2, 2);
				gm.loadPlayers(AI3, 3);
				gm.loadPlayers(AI4, 4);
				
				// Deck is populated and shuffled
				cd.populateDeck();
				cd.shuffleDeck();
				
				// Game Master deals out the cards
				int i = 0;
				while (i < DECK_SIZE) {
					human.setHand(gm.dealCard(cd, i));
					//System.out.println(human.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI1.setHand(gm.dealCard(cd, i));
					//System.out.println(AI1.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI2.setHand(gm.dealCard(cd, i));
					//System.out.println(AI2.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI3.setHand(gm.dealCard(cd, i));
					//System.out.println(AI3.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI4.setHand(gm.dealCard(cd, i));
					//System.out.println(AI4.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
				}
				
				System.out.println();
				
				// Game Master chooses first player
				gm.chooseFirstPlayer();
				//System.out.println("\n" + gm.getActivePlayer().getTopCardInfo());
				
				// start of round loop
				// continue rounds until one player is left in the Game Master's ArrayList of players
				while (gm.getArraySize() > 1) {
					
					// Game Master checks that all players have at least one card
					gm.playerIsElminated(); 
					
					System.out.println(gm.getPlayerArrayInfo());
					
					// If human player is in the array, ask to proceed to next round
					String startRound = "";
					boolean humanPlayer = gm.findHumanPlayer(name);
						if (humanPlayer == true) {
							System.out.println("Enter any key to continue to round " + round);
							startRound = in.next();
					}
					String activePlayerName = gm.getActivePlayerName();
				
					if (activePlayerName.equals(human.getName())) {
						System.out.println("\nIt's your turn! Here's your card:\n");
						System.out.println(gm.getActivePlayer().getTopCardInfo());
						
						
						int y = 1;
						while (y == 1) {
							System.out.println("Which category would you like to select?");
					
							// fix this later so that it's one thing
							String chooseCategory = in.next();
					
								if (chooseCategory.equals("1")) {
									gm.sortByCategory(1);
									y = 2;
								}
								else if (chooseCategory.equals("2")) {
									gm.sortByCategory(2);
									y = 2;
								}
								else if (chooseCategory.equals("3")) {
									gm.sortByCategory(3);
									y = 2;
								}
								else if (chooseCategory.equals("4")) {
									gm.sortByCategory(4);
									y = 2;
									}
								else if (chooseCategory.equals("5")) {
									gm.sortByCategory(5);
									y = 2;
								}
								else {
									System.out.println("Invalid option.");
								}
						}
					}
					
					else {
						if (activePlayerName.equals(AI1.getName())) {
							int choice = AI1.chooseCategory();
							gm.sortByCategory(choice);
						}
						if (activePlayerName.equals(AI2.getName())) {
							int choice = AI2.chooseCategory();
							gm.sortByCategory(choice);
						}
						if (activePlayerName.equals(AI3.getName())) {
							int choice = AI3.chooseCategory();
							gm.sortByCategory(choice);
						}
						if (activePlayerName.equals(AI4.getName())) {
							int choice = AI4.chooseCategory();
							gm.sortByCategory(choice);
						}
					}
				
					
					gm.communalPile();
					round++;
					
				}
				
				System.out.println("The game is over.");
				System.out.println("The winner of the game was:\t " +gm.getActivePlayerName());
				System.out.println("The number of rounds was:\t " + round);
				System.out.println("They have this many cards:\t " + gm.getActivePlayer().getNumOfCardsInHand());
				System.out.println("Their full hand is:\t\t " + gm.getActivePlayer().toString());
				
				System.out.println("\nStats have been saved");
				// +++++++++++++++++++++++++++++++++++++++++++++++++ add stats object
				round = 1;
				
				
				int again = 1;
				while (again == 1) {
				System.out.println("\nDo you want to play again: Y/N?");
				String playAgain = in.next();
				
				if ((playAgain.equals("Y")) || (playAgain.equals("y"))) {
					play = 1;
					again = 2;
				}
				
				else if ((playAgain.equals("N")) || (playAgain.equals("n"))) {
					play = 2;
					again = 2;
				}
				
				else {
					System.out.println("Invalid answer.\n");
				}
				}
				
				
				//userWantsToQuit = true; // use this when the user wants to exit the game
			}
		}
		
		// if answer is see past game stats
		if (answer.equals("2")) {
			System.out.println("You entered 2.\n");
			// a method from the stats class will load a string which will print in the terminal
		}

		
		else if ((!answer.equals("1")) || (!answer.equals("1")))   {
			System.out.println("Invalid option.\n");
		}
		
		}
	
	
	
	}



}
