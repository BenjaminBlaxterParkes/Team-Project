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
		while (play == 1) {
		

			System.out.println("Main Menu \n1. Start New Game \n2. See Past Stats");
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
						System.out.println("\nWhat is your name?");
						name = in.next();

						boolean check = gm.checkHumanName(name);
						if (check == true) {
							checkName = 2;
						} else {
							System.out.println("That name is already taken.");
						}

					}

					// Instantiate players
					Player human = new Player(name);

					// Game Master loads players into an ArrayList
					gm.loadPlayers(human, 0);

					int b = 1;
					String opponents = "";
					while (b == 1) {
						System.out.println("\nHow many AI opponents would you like to play against: 1-4?");
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
							System.out.print("Invalid option.\n");
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

						System.out.println("Round " + round + "\n====================\n" + "It's "
								+ gm.getPastPlayerName() + "'s turn!\n\n" + "Players:");

						System.out.println(gm.getPlayerArrayInfo());

						// If human player is in the array, ask to proceed to next round
						String startRound = "";
						boolean humanPlayer = gm.findHumanPlayer(name);
						if (humanPlayer == true) {

							System.out.println("Peek at your top card:");
							System.out.println(human.getTopCardInfo());

							System.out.println("To continue, enter any key.\n");
							startRound = in.next();
						}
						String activePlayerName = gm.getPastPlayerName();

						if (activePlayerName.equals(human.getName())) {
							System.out.println("\nIt's your turn! Here's your top card:\n");
							System.out.println(gm.getActivePlayer().getTopCardInfo());

							int y = 1;
							while (y == 1) {
								System.out.println("Which category would you like to select?");

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
									System.out.println("Invalid option.");
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
					System.out.println("The game is over.");
					System.out.println("The winner of the game was:\t " + gm.getActivePlayerName());
					System.out.println("The number of rounds was:\t " + round);
					System.out.println("They have this many cards:\t " + gm.getActivePlayer().getNumOfCardsInHand());
					// System.out.println("Their full hand is:\t\t " +
					// gm.getActivePlayer().toString());

					

					// Database stuff
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
						System.out.println("\nDo you want to play again: Y/N?");
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
							System.out.println("Invalid answer.\n");
						}
					}

				
				}
			}

			// if answer is see past game stats
			if (answer.equals("2")) {
				System.out.println("Here are the previous stats: \n");
				
//				stats.connection();
//				System.out.println(stats.getGameSummary() + "\n");
//				stats.disconnection();
				
			}

			else if ((!answer.equals("1")) || (!answer.equals("1"))) {
				System.out.println("Invalid option.\n");
			}

		}

	}

}
