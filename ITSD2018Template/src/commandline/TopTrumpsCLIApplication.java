package commandline;

import java.util.*;

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

	public static void main(String[] args) {

		// ----------------------------------------------------
		// Add your game logic here based on the requirements
		// ----------------------------------------------------

		// Main variables
		CardDeck cd = new CardDeck();
		Stats stats = new Stats("m_17_2293327p", "m_17_2293327p", "2293327p");
		final int DECK_SIZE = 40;
		int round = 0;
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		// if (args[0].equalsIgnoreCase("true")) {
		// writeGameLogsToFile = true; // Command line selection
		// }

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		Scanner in = new Scanner(System.in);

		// start round loop
		int play = 1;
		while (!userWantsToQuit == true) {
		

			System.out.println("*****************\n**  Main Menu  ** \n*****************\n1. Start New Game \n2. See Past Stats");
			String answer = in.next();

			if (answer.equals("1")) {

				// Start game loop
				while (!userWantsToQuit == true) {

					// Instantiate Game Master
					GameMaster gm = new GameMaster();
					
					// Get human player's name
					String name = "";
					int checkName = 1;
					while (checkName == 1) {
						System.out.println(" -------------------\n"
								         + "| What's your name? | \n "
								          + "-------------------");
						name = in.next();

						boolean check = gm.checkHumanName(name);
						if (check == true) {
							checkName = 2;
						} else {
							System.out.println(" --------------------------------------------------------------------\n"
									         + "| That name is taken, please enter a different name and press enter. |\n"
									         + " --------------------------------------------------------------------\n");
						}

					}

					// Instantiate players
					Player human = new Player(name);

					// Game Master loads players into an ArrayList
					gm.loadPlayers(human, 0);

					int b = 1;
					String opponents = "";
					while (b == 1) {
						System.out.println(" -----------------------------------------------------------------\n"
								         + "| How many AI's would you like to play with you, between 1 and 4? |\n"
								         + " -----------------------------------------------------------------");
						opponents = in.next();

						if (opponents.equals("1")) {
							gm.createAI(1);
							b = 2;
						} else if (opponents.equals("2")) {
							gm.createAI(2);
							b = 2;
						} else if (opponents.equals("3")) {
							gm.createAI(3);
							b = 2;
						} else if (opponents.equals("4")) {
							gm.createAI(4);
							b = 2;
						} else {
							System.out.print("-----------------------------------------------------------------------\n"
									       + "| Invalid option.  Please enter a number between 1 and 4 and hit enter |\n"
										   + "-----------------------------------------------------------------------\n");
						}

					}

					// Deck is populated and shuffled
					cd.populateDeck();
					cd.shuffleDeck();

					// Game Master deals out the cards
					int j = 0;
					int i = 0;
					while (j < DECK_SIZE) {
						gm.getPlayerByPosition(i).setHand(gm.dealCard(cd, j));
						// System.out.println(gm.getPlayerByPosition(i).getName() + " got"
						// + " card: " + gm.dealCard(cd, j));
						j++; // increment Card
						i++; // increment Player
						if (i == (gm.getArraySize())) {
							i = 0; // start dealing again to first player
						}
					}

					System.out.println();

					// Game Master chooses first player
					gm.chooseFirstPlayer();
					// System.out.println("\n" + gm.getActivePlayer().getTopCardInfo());

					// start of round loop
					// continue rounds until one player is left in the Game Master's ArrayList of
					// players
					while (gm.getArraySize() > 1) {

						round++;
						// System.out.println(gm.getPlayerArrayInfo());

						System.out.println("ROUND " + round + "\n====================\n" + "It's "
								+ gm.getPastPlayerName() + "'s turn!\n\n" + "List of active players:");

						System.out.println(gm.getPlayerArrayInfo());

						// If human player is in the array, ask to proceed to next round
						String startRound = "";
						boolean humanPlayer = gm.findHumanPlayer(name);
						if (humanPlayer == true) {

							System.out.println("Here's your card for this round:");
							System.out.println(human.getTopCardInfo());
							
							int r = 1;
							while(r == 1) {
								System.out.println(" ---------------------------------\n"
										          +"| Now that you've seen your card: |\n"
										          +" ---------------------------------\n"
										+ "  C. Continue to category choice\n"
										+ "  E. Exit to Main Menu");
								startRound = in.next();
								if(startRound.equals("c") || startRound.equals("C")) {
									userWantsToQuit = false;
									r = 2;
								}
								else if(startRound.equals("e") || startRound.equals("E")) {
									userWantsToQuit = true;
									r = 2;
								}
								else {
									System.out.println(" -----------------------------------------\n"
											        +  "| Invalid option, please enter 'C' or 'E' |\n"
													 + " -----------------------------------------\n");
								}
							}
							
							
						}
						String activePlayerName = gm.getPastPlayerName();

						if (activePlayerName.equals(human.getName())) {
//							System.out.println("\nIt's your turn! Here's your top card, again:\n");
//							System.out.println(gm.getActivePlayer().getTopCardInfo());

							int y = 1;
							while (y == 1) {
								System.out.println(" ------------------------------------------\n"
									        	+  "| Which category would you like to select? |\n"
												+  " ------------------------------------------\n");

								// fix this later so that it's one thing
								String chooseCategory = in.next();

								if (chooseCategory.equals("1")) {
									gm.sortByCategory(1);
									human.setCategoryChoice(1);
									y = 2;
								} else if (chooseCategory.equals("2")) {
									gm.sortByCategory(2);
									human.setCategoryChoice(2);
									y = 2;
								} else if (chooseCategory.equals("3")) {
									gm.sortByCategory(3);
									human.setCategoryChoice(3);
									y = 2;
								} else if (chooseCategory.equals("4")) {
									gm.sortByCategory(4);
									human.setCategoryChoice(4);
									y = 2;
								} else if (chooseCategory.equals("5")) {
									gm.sortByCategory(5);
									human.setCategoryChoice(5);
									y = 2;
								} else {
									System.out.println(" ----------------------------------------------------------------\n"
											         + "| Invalid option, please enter a # between 1 and 5 and hit enter |\n"
													 + " ----------------------------------------------------------------\n");
								}
							}
						}

						else {
							int choice = gm.getActivePlayer().AIChooseCategory();
							gm.sortByCategory(choice);

						}

						gm.communalPile();

						// Game Master checks that all players have at least one card
						gm.playerIsElminated();

					}

					// Game is over.  Print results.
					System.out.println("The game is over!");
					System.out.println("The winner of the game was:\t " + gm.getActivePlayerName());
					System.out.println("The number of rounds was:\t " + round);
//					System.out.println("They have this many cards:\t " + gm.getActivePlayer().getNumOfCardsInHand());
					// System.out.println("Their full hand is:\t\t " +
					// gm.getActivePlayer().toString());

					

					
//					int humanWinner = 0;
//					int AIWinner = 0;
//					if (gm.getActivePlayerName().equals(name)) {
//						humanWinner = 1;
//					}
//					else {
//						AIWinner = 1;
//					}
//					
//					stats.connection();
//					int gameID = Integer.parseInt(stats.getGameCount()) + 1;
//					int draws = gm.getDraws();
//					int AIRounds = gm.getAIWin();
//					int humanRounds = gm.getHumanWin();
//					
//					stats.recordStats(gameID, draws, humanWinner, AIWinner, round, humanRounds, AIRounds);
//
//					stats.disconnection();
					
					System.out.println("\nStats have been saved to the database.");
					round = 1; // reset round counter

					
					
					
					
					int again = 1;
					while (again == 1) {
						System.out.println("\n ---------------------------------\n"
										 + "| Do you want to play again: Y/N? |\n"
										 + " ---------------------------------\n");
						String playAgain = in.next();

						if ((playAgain.equals("Y")) || (playAgain.equals("y"))) {
							userWantsToQuit = false;
							again = 2;
						}

						else if ((playAgain.equals("N")) || (playAgain.equals("n"))) {
							userWantsToQuit = true;
							again = 2;
						}

						else {
							System.out.println(" ----------------------------------\n"
											 + "| Invalid answer, please try again |\n"
											 + " ----------------------------------\n");
						}
					}

				
				}
			}

			// if answer is see past game stats
			if (answer.equals("2")) {
				System.out.println(" ------------------------------\n"
								 + "| Here are the previous stats: |\n"
								 + " ------------------------------\n");
				
				stats.connection();
				System.out.println(stats.getGameSummary() + "\n");
				stats.disconnection();
				
			}

			else if ((!answer.equals("1")) || (!answer.equals("1"))) {
				System.out.println(" -------------------------------------------------\n"
								+  "| Invalid option! Press 1 or 2, then press enter. |\n"
								+  " -------------------------------------------------\n");
			}

		}

	}

}
