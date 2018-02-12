package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mysql.fabric.xmlrpc.base.Array;

import commandline.Card;
import commandline.CardDeck;
import commandline.Player;
import commandline.Stats;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	ObjectMapper mapper = new ObjectMapper();
	
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
	}
	
	
	// Instance variables
	private int numPlayers = 1;
	private String name = "player";
	private CardDeck cd = new CardDeck();
	private Stats stats = new Stats("m_17_2293327p", "m_17_2293327p", "2293327p");
	private final int DECK_SIZE = 40;	
	private static commandline.GameMaster gm; 
	private static commandline.Player human;
	
	//Variables for updating the database
	private int humanWinner = 0;
	private int AIWinner = 0;
	private int gameID;
	private int draws;
	private int AIRounds;
	private int humanRounds;
	private int rounds;
	private String gameWinner;

	
	
	@GET
	@Path("/setAI")
	/**
	 * Method to set the number of AI players and initialise the game
	 * Loads all human and AI players
	 * Initiates the shuffling and dealing of the cards
	 * 
	 * @throws IOException 
	 */
	public void setAI(@QueryParam("ai") int ai) throws IOException{
		numPlayers += ai;
		gm = new commandline.GameMaster();
		human = new commandline.Player(name);
		gm.loadPlayers(human, 0);
		gm.createAI(ai);
		shuffleAndDeal();
	}
	
	

	/**
	 * Method to populate, shuffle, and deal the cards
	 * Called when setting the number of AI players
	 *  
	 * @throws IOException
	 */
	@GET
	@Path("/shuffleAndDeal")
	public void shuffleAndDeal() throws IOException{
		
		// Deck is populated and shuffled
		cd.populateDeck();
		cd.shuffleDeck();
	
		// Game Master deals out the cards
		int j = 0;
		int i = 0;
		while (j < DECK_SIZE) {
			gm.getPlayerByPosition(i).setHand(gm.dealCard(cd, j));
			j++; // increment Card
			i++; // increment Player
			if (i == (gm.getArraySize())) {
				i = 0; // start dealing again to first player
			}
		}
	}



	/**
	 * Method to initiate the game master to choose the first player
	 * 
	 * @return String
	 * @throws IOException
	 */
	@GET
	@Path("/chooseFirstPlayer")
	public String chooseFirstPlayer() throws IOException{
		
			// Game Master chooses first player
			gm.chooseFirstPlayer();
			String fP = new String(gm.getActivePlayer().getName());
			return fP;
	}
	
	
	/**
	 * Method to display the human players first card when the game is initialised
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/peek")
	public String peek() throws IOException{
		
		// Contains the details of the humans top card
		String humanCard;
		try {
			// Try get card info
			humanCard = human.getTopCardInfo(); 
		}
		catch(Exception e) {
			// Displayed if human player is eliminated
			humanCard = "\n You're boned!!!\n \n \n \n \n "; 
		}
		return humanCard;
	}
	
	
	
	
	
	/** 
	 * Method to display the current active player
	 * the human players card after a round before continue is clicked
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/changeState")
	public String changeState() throws IOException{
		
		String s = "";
		// Get the active player name
		s += gm.getActivePlayer().getName() + "\n";
		try {
			// Try get card info
			s += human.getTopCardInfo();
		}
		catch (Exception e) {
			// Displayed if human player is eliminated
			s += "\n They're boned!!!\n \n \n \n \n \n \n \n \n \n";
		}
		gm.playerIsElminated();// check if any players have been eliminated
		return s;
	}
	
	
	
	
	
	/**
	 * Method to display the AI players cards after a round is played
	 *
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getAICards")
	public String getAICards() throws IOException{ 
		
		//Contains the info of all the AI cards
		String cardInfo = "";
		Player p = null;
		for (int i = 0; i<numPlayers; i++) {
			try {
				// Try get each player in the players array list
				p = gm.getPlayerByPosition(i);
			}
			catch (Exception e) {
				//Display if the player has been eliminated
				cardInfo += "They're boned!!!\n \n \n \n \n \n \n \n \n"
						+ "They're boned!!!\n \n \n \n \n \n \n \n \n"
						+ "They're boned!!!\n \n \n \n \n \n \n \n \n"
						+ "They're boned!!!\n \n \n \n \n \n \n \n \n";
			}
			if (p != human) {
				//Get the AI players info
				cardInfo += p.getName() + "\n";
				cardInfo += p.getTopCardInfo();
			}
		}
		return cardInfo;
	}
	
	
	
	/**
	 * Method that initialises the process of playing a round
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/playRound")
	public String playRound() throws IOException{
		
		// Check if the active player is AI
	if (gm.getActivePlayerName() != "Player") {
		int choice = gm.getActivePlayer().AIChooseCategory();//Let AI players choose a category
		gm.sortByCategory(choice);//Compare cards to see who won
		gm.communalPile();//Update the communal pile display
		gm.playerIsElminated();//Check to see if any players have been eliminated 
		if(gm.getArraySize()==1);
		}
	// Display who won the round
	String whowon = gm.getPlayersArrList().get(0).getName() + "won the round.";
	return whowon;
	}
	
	
	
	/**
	 * Method to check if the game is over
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/isItOver")
	public String isItOver() throws IOException{
		Player p; 
		String s = "";
		if(gm.getArraySize()==1) {
			p = gm.getPlayerByPosition(0);
			s += p.getName();
			}
		return s;
	}
	
	
	/**
	 * Method to end the game master and the game
	 * 
	 * @throws NullPointerException
	 */
	@GET
	@Path("/killSwitch")
	public void killSwitch() throws NullPointerException{
				gm = null;
				}
	
	/**
	 * Method to check who won the game
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getWhoWon")
	public String getWhoWon() throws IOException{
		return gm.getActivePlayerName();
	}
	
	
	
	/**
	 * Method to allow the human player to select a category
	 * when it is their turn
	 * 
	 * @param choice
	 * @throws IOException
	 */
	@GET
	@Path("/chooseCat")
	public void chooseCat(@QueryParam("choice") int choice) throws IOException{
		human.setCategoryChoice(choice);//Send category choice to be compared to other cards
		gm.sortByCategory(choice);//Compare cards to see who won
		gm.communalPile();//Update the communal pile display
		gm.playerIsElminated();//Check to see if any players have been eliminated 
		if(gm.getArraySize()==1);
	}
	
	
	
	
	
	@GET
	@Path("/getCardsInPlay")
	/** 
	 * Method gets the number of cards in the communal pile
	* 
	* @param choice
	* @throws IOException
	*/
	public String getCardsInPlay() throws IOException{
		String s = "";
		try {
		int i = gm.getNumCardsInPlay().size();
		s = "There are " + i + " cards in the communal pile";
		}
		catch (Exception e) {};
		return s;
	}
	
	
	
	@GET
	@Path("/getAllHands")
	/** 
	 * Method get the number of cards in the players hands
	* 
	* @param choice
	* @throws IOException
	*/
	public String getAllHands() throws IOException{
		String s = "";
		
		for(int i=0;i<gm.getArraySize();i++) {
			Player p = gm.getPlayerByPosition(i);
			s += p.getName() + " " + p.getNumOfCardsInHand() + " - ";
		}
		return s;
	}
	
	
	
	
	@GET
	@Path("/getDraw")
	/** Method checks for a draw
	 * 
	 * @param choice
	 * @throws IOException
	 */
	public String getDraw() throws IOException{
		String s;
		try {
		s = Boolean.toString(gm.checkforDraw());
		}
		catch (Exception e){
		s = "";	
		}
		return s;	
	}
	
	@GET
	@Path("/database")
	/**
	 * Connects to the database
	 * Stores statistics to be saved in database
	 * 
	 */
	public void database()  throws IOException{
		
		// Initialise the winner variables
		humanWinner = 0;  
		AIWinner = 0;
		
		// String to test if winning player is human or AI
		String winningPlayer = null;
		
		try {
			winningPlayer = gm.getActivePlayerName();
			if (winningPlayer.equals(name)) {
				humanWinner = 1;
				}
				else {
				AIWinner = 1;
				}
		}
		catch (Exception e){}
		
		//Open database connection
		stats.connection();
		
		//store the stats to send to the database
		try {
		gameID = Integer.parseInt(stats.getGameCount()) + 1;
		draws = gm.getDraws();
		AIRounds = gm.getAIWin();
		humanRounds = gm.getHumanWin();
		gameWinner = gm.getActivePlayerName();
		rounds = AIRounds + humanRounds;}
		catch (Exception e) {}
	}
	
	
	/**
	 * Send the game statistics to the database to be stored
	 * closes the database connection
	 * 
	 * @throws IOException
	 */
	@GET
	@Path("/recordStats")
	public void recordStats()  throws IOException{
		stats.recordStats(gameID, draws, humanWinner, AIWinner, rounds, humanRounds, AIRounds, gameWinner);
		stats.disconnection();
	
	}
	
	/**
	 * Loads previous statistics from the database
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getPreviousStats")
	public String getPreviousStats()  throws IOException{
		stats.connection();
		String pStats = stats.getGameSummary();
		stats.disconnection();
		return pStats;
		}
	
	
} //END OF CLASS
