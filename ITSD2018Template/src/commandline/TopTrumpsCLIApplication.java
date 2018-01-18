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

		System.out.println("Main Menu \n 1. Start New Game \n 2. See Past Stats");

		int answer = in.nextInt();

		if (answer == 1) {

			// start game loop
			while (!userWantsToQuit) {

				System.out.println("What is your name?");

				String name = in.next();
				
				System.out.println();
				
				HumanPlayer human = new HumanPlayer(name);
				AIPlayer AI1 = new AIPlayer("HAL 9000");
				AIPlayer AI2 = new AIPlayer("Cortana");
				AIPlayer AI3 = new AIPlayer("GLaDOS");
				AIPlayer AI4 = new AIPlayer("Marvin");
				
				gm.loadPlayers(human, 0);
				gm.loadPlayers(AI1, 1);
				gm.loadPlayers(AI2, 2);
				gm.loadPlayers(AI3, 3);
				gm.loadPlayers(AI4, 4);
				
				System.err.println();
				
				cd.populateDeck();
				cd.shuffleDeck();
				
				System.err.println();
				
				int i = 0;
				while (i < DECK_SIZE) {
					human.setHand(gm.dealCard(cd, i));
					System.err.println("" +human.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI1.setHand(gm.dealCard(cd, i));
					System.err.println("" +AI1.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI2.setHand(gm.dealCard(cd, i));
					System.err.println("" +AI2.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI3.setHand(gm.dealCard(cd, i));
					System.err.println("" +AI3.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
					AI4.setHand(gm.dealCard(cd, i));
					System.err.println("" +AI4.getName() + " now has: "+ gm.dealCard(cd, i).getDescription());
					i++;
				}
				
				System.err.println();
				
				System.err.println(human.toString());
				System.err.println(AI1.toString());
				System.err.println(AI2.toString());
				System.err.println(AI3.toString());
				System.err.println(AI4.toString());
				
				System.err.println();
				
				gm.chooseFirstPlayer();
				
				// start of round loop
				while (gm.getArraySize() > 2) {
				
					System.out.println("Enter any key to continue to round " + round);
				
					String startRound = in.next();
				
					if (gm.getActivePlayer() == human) {
						System.out.println("It's your turn! Here's your card:\n");
						System.out.println(gm.getActivePlayer().getTopCardInfo());
						System.out.println("Which category would you like to select?");
					
						String chooseCategory = in.next();
					
							if (chooseCategory.equals("1")) {
								gm.sortByFirstCategory();
							}
							if (chooseCategory.equals("2")) {
								
							}
							if (chooseCategory.equals("3")) {
								
							}
							if (chooseCategory.equals("4")) {
									
								}
							if (chooseCategory.equals("5")) {
								
							}
							
					}
					else {
					
					}
				
				
				}
				
				System.out.println("The game is over.");
				
				
				// creating players

				// Loop until the user wants to exit the game

				// ----------------------------------------------------
				// Add your game logic here based on the requirements
				// ----------------------------------------------------

				
				//System.out.println("Do you want to play again?");
				userWantsToQuit = true; // use this when the user wants to exit the game
			}
		}
		
		// if answer is see past game stats
		if(answer == 2) {
			// a method from the stats class will load a string which will print in the terminal
		}


	}

	private static void CardDeck() {
		// TODO Auto-generated method stub
		
	}

}
