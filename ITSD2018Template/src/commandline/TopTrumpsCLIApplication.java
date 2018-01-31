package commandline;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */

	// Instantiae Logger for logging
	final static Logger LOGGER = Logger.getLogger(TopTrumpsCLIApplication.class.getName());

	public static void main(String[] args) {
		/*
		 * Logger and FileHandler variables
		 */
		FileHandler fh = null;
		try {
			LogManager.getLogManager().reset(); // Reset LogManager
			fh = new FileHandler("src/toptrumps.log", false); // Set log name and save in src folder, don't append files
			LOGGER.addHandler(fh); // add handler to logger
			fh.setFormatter(new SimpleFormatter()); // set format to simpleFormatter
			LOGGER.setUseParentHandlers(false); // No parent handler
			LOGGER.setLevel(Level.INFO); // Set logger level to info
		} catch (Exception e) {
			System.out.println("FileHander can't handle .log file"); // print out error message if exception is caught
		}

		
		
		/*
		 * Main Variables
		 */
		CardDeck cd = new CardDeck(); // instantiate a carddeck object
		Stats stats = new Stats("m_17_2293327p", "m_17_2293327p", "2293327p"); // Login for DB
		final int DECK_SIZE = 40; // There will always be 40 cards in a deck
		int round = 0; // start rounds at 0
		Scanner in = new Scanner(System.in); // Instantiate scanner to be used for user input
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		boolean writeGameLogsToFile = false; 
//		if (args[0].equalsIgnoreCase("true")) {
//			writeGameLogsToFile = true; // Command line selection
//		}

		

		/*
		 * Start round loop
		 */
		 
		// Continue is name for game loop 
		CONTINUE: 
			
			while (!userWantsToQuit == true) {	//play until user wants to quit
			boolean yesPlayAgain = false; // User doesn't want to play again, back to Main Menu 

			// Print out main menu
			System.out.println(
					"*****************\n"
				   + "** Main Menu  ** \n"
				  + "*****************\n"
				  + "1. Start New Game \n"
				  + "2. See Past Stats");
			
			String answer = in.next(); // Get main menu input from user
			
		
			if (answer.equals("1")) { // If user chooses "Start New Game" 

				// Start game loop
				while (yesPlayAgain == false) {
					
					GameMaster gm = new GameMaster(); // Instantiate Game Master

					// Get human player's name
					String name = "";
					int checkName = 1;
					while (checkName == 1) {
						System.out.println(
								" -------------------\n" + 
								"| What's your name? | \n " 
							   + "-------------------");
						name = in.next();

						boolean check = gm.checkHumanName(name); // see if name is taken by AI
						if (check == true) { // if human name is unique, check is true
							checkName = 2;
						} else { // if human name isn't unique, make them pick a new one
							System.out.println(" --------------------------------------------------------------------\n"
										     + "| That name is taken, please enter a different name and press enter. |\n"
										     + " --------------------------------------------------------------------\n");
						}

					}

					
					Player human = new Player(name); // Instantiate players
					gm.loadPlayers(human, 0); 	// Game Master loads players into an ArrayList

					int b = 1; // int for AI # input
					String opponents = "";
					while (b == 1) {
						System.out.println(" -----------------------------------------------------------------\n"
										 + "| How many AI's would you like to play with you, between 1 and 4? |\n"
										 + " -----------------------------------------------------------------");
						opponents = in.next(); // collect number of AI opp from user

						if (opponents.equals("1")) {
							gm.createAI(1); // if user chooses 1 AI, make 1 AI player 
							b = 2;
						} else if (opponents.equals("2")) {
							gm.createAI(2); // if user chooses 2 AIs, make 2 AI players
							b = 2;
						} else if (opponents.equals("3")) {
							gm.createAI(3); // if user chooses 3 AIs, make 3 AI players
							b = 2;
						} else if (opponents.equals("4")) {
							gm.createAI(4); // if user chooses 4 AIs, make 4 AI players
							b = 2;
						} else { // if user chooses wrong input, display error messages
							System.out.print("-----------------------------------------------------------------------\n"
									+ "| Invalid option.  Please enter a number between 1 and 4 and hit enter |\n"
									+ "-----------------------------------------------------------------------\n");
						}
					}

					
					cd.populateDeck(); // Deck is populated
					
					// Log original deck
					LOGGER.info("\nThe contents of the complete deck once it has been read in and constructed: \n"
							+ Arrays.toString(cd.getOriginalDeck())
							+ "\n ----------------------------------------------");

					
					cd.shuffleDeck(); // Deck is shuffled
					// Log shuffled deck
					LOGGER.info("\nThe contents of the complete deck after it has been shuffled: \n"
							+ Arrays.toString(cd.getShuffledDeck())
							+ "\n ----------------------------------------------");

					// Game Master deals out the cards
					int j = 0;
					int i = 0;
					while (j < DECK_SIZE) {
						// get player in array, set their hand, deal the cards
						gm.getPlayerByPosition(i).setHand(gm.dealCard(cd, j));
						j++; // increment Card
						i++; // increment Player
						if (i == (gm.getArraySize())) {
							i = 0; // start dealing again to first player
						}

					}

					// Log players hands once cards are dealt
					LOGGER.info(
							"\nThe contents of the user’s deck and the computer’s deck(s) once they have been allocated: \n"
									+ gm.playersAndHand() + "\n ----------------------------------------------");

					System.out.println();
					
					gm.chooseFirstPlayer(); // Game Master chooses first player

					// continue rounds until one player is left in the Game Master's ArrayList of players
					while (gm.getActivePlayer().getNumOfCardsInHand() < 40) {

						round++;

						System.out.println("ROUND " + round + "\n====================\n" + "It's "
								+ gm.getPastPlayerName() + "'s turn!\n\n" + "List of active players:");

						System.out.println(gm.getPlayerArrayInfo());

						// If human player is in the array, ask to proceed to next round
						String startRound = "";
						boolean humanPlayer = gm.findHumanPlayer(name);
						if (humanPlayer == true) { // if its a human player

							System.out.println("Here's your card for this round:");
							System.out.println(human.getTopCardInfo()); // show top card info to user

							int r = 1;
							while (r == 1) {
								System.out.println(
										" ---------------------------------\n" + 
										"| Now that you've seen your card: |\n"
									  + " ---------------------------------\n"
									  + "  C. Continue to category choice\n" + 
									    "  E. Exit to Main Menu");
								
								startRound = in.next(); // collect user input for C or E
								
								if (startRound.equals("c") || startRound.equals("C")) {
									yesPlayAgain = false;
									r = 2;
									
								} else if (startRound.equals("e") || startRound.equals("E")) {
									r = 2;
									continue CONTINUE;

								} else {
									System.out.println(" -----------------------------------------\n"
													 + "| Invalid option, please enter 'C' or 'E' |\n"
													 + " -----------------------------------------\n");
								}
							}
						}
						
						String activePlayerName = gm.getPastPlayerName(); // load past player name

						if (activePlayerName.equals(human.getName())) { // if the PastPlayer is human

							int y = 1;
							while (y == 1) {
								System.out.println(" ------------------------------------------\n"
											   	 + "| Which category would you like to select? |\n"
											   	+  " ------------------------------------------\n");

								String chooseCategory = in.next(); // collect input from user for cat choice

								if (chooseCategory.equals("1")) {
									gm.sortByCategory(1); // sort players based on cat one
									human.setCategoryChoice(1); // remember cat choice was 1
									y = 2;
								} else if (chooseCategory.equals("2")) {
									gm.sortByCategory(2); // sort players based on cat two
									human.setCategoryChoice(2); // remember cat choice was 2
									y = 2;
								} else if (chooseCategory.equals("3")) {
									gm.sortByCategory(3); // sort players based on cat three
									human.setCategoryChoice(3); // remember cat choice was 3
									y = 2;
								} else if (chooseCategory.equals("4")) {
									gm.sortByCategory(4); // sort players based on cat four
									human.setCategoryChoice(4); // remember cat choice was 4
									y = 2;
								} else if (chooseCategory.equals("5")) {
									gm.sortByCategory(5); // sort players based on cat five
									human.setCategoryChoice(5); // remember cat choice was 5
									y = 2;
								} else {
									System.out.println( // if choice is invalid
											" ----------------------------------------------------------------\n"
											+ "| Invalid option, please enter a # between 1 and 5 and hit enter |\n"
											+ " ----------------------------------------------------------------\n");
								}
							}
						}

						else { // if PastPlayer is not human player
							int choice = gm.getActivePlayer().AIChooseCategory(); // AI choose category
							gm.sortByCategory(choice); // sort players based on AI choice

						}

						// Log what cards are in play
						LOGGER.info(
								"\nThe contents of the current cards in play (the cards from the top of the user’s deck and the computer’s "
										+ "deck(s)): \n" + gm.getCardsInPlay()
										+ "\n ----------------------------------------------");

						// puts all current cards in play into a communal pile
						gm.communalPile();

						// Game Master checks that all players have at least one card.
						// If a Player is out of cards, they're removed from the game.  
						gm.playerIsElminated();

						// Log players hands after each round
						LOGGER.info("\nThe contents of each deck after a round: \n" + gm.playersAndHand()
								+ "\n ----------------------------------------------");

					}

					// Game is over. Print results.
					System.out.println("The game is over!");
					System.out.println("The winner of the game was:\t " + gm.getActivePlayerName());
					System.out.println("The number of rounds was:\t " + round);

					// Log the winning player
					LOGGER.info("\nThe winner of the game: \n" + gm.getActivePlayerName()
							+ "\n ----------------------------------------------");


					/*
					 *  This code only works on Aaron Palmer's lab computer since 
					 *  it's connected to his SQL database.
					 */

					// int humanWinner = 0;
					// int AIWinner = 0;
					// if (gm.getActivePlayerName().equals(name)) {
					// humanWinner = 1;
					// }
					// else {
					// AIWinner = 1;
					// }
					//
					// stats.connection();
					// int gameID = Integer.parseInt(stats.getGameCount()) + 1;
					// int draws = gm.getDraws();
					// int AIRounds = gm.getAIWin();
					// int humanRounds = gm.getHumanWin();
					// String gameWinner = gm.getActivePlayerName();
					//
					// stats.recordStats(gameID, draws, humanWinner, AIWinner, round, humanRounds,
					// AIRounds, gameWinner);
					//
					// stats.disconnection();

					System.out.println("\nStats have been saved to the database.");
					round = 1; // reset round counter

					try {
						fh.close(); // close fileHandler
					} catch (NullPointerException n) {
						System.out.println("filehandler couldn't close"); // catch exceptions
					}

					// Ask if human player wants to play again.  
					int again = 1;
					while (again == 1) {
						System.out.println("\n ---------------------------------\n"
											+ "| Do you want to play again: Y/N? |\n" 
											+ " ---------------------------------\n");
						String playAgain = in.next(); // get user input

						if ((playAgain.equals("Y")) || (playAgain.equals("y"))) {
							yesPlayAgain = false; // end game.  Return to main menu
							again = 2; // exit loop
						}

						else if ((playAgain.equals("N")) || (playAgain.equals("n"))) {
							yesPlayAgain = true; // start game loop over
							again = 2; // exit loop
						}

						else {
							System.out.println(
									" ----------------------------------\n" 
									+ "| Invalid answer, please try again |\n"
									+ " ----------------------------------\n");
						}
					}
				}
			}

			// On main menu, if answer is see past game stats
			if (answer.equals("2")) {
				System.out.println(" ------------------------------\n" 
								+ "| Here are the previous stats: |\n"
								+ " ------------------------------\n");

				/*
				 *  This code only works on Aaron Palmer's lab computer since 
				 *  it's connected to his SQL database.
				 */

				// stats.connection();
				// System.out.println(stats.getGameSummary() + "\n");
				// stats.disconnection();

			}

			else if ((!answer.equals("1")) || (!answer.equals("1"))) { // If input for main menu is invalid
				System.out.println(" -------------------------------------------------\n"
								+ "| Invalid option! Press 1 or 2, then press enter. |\n"
								+ " -------------------------------------------------\n");
			}

		}

	}

}
