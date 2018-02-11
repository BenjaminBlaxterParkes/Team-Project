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
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
	}
	
	
	//*~*Variables!
	int numPlayers = 1;
	String name = "player";
	CardDeck cd = new CardDeck();
	Stats stats = new Stats("m_17_2293327p", "m_17_2293327p", "2293327p");
	final int DECK_SIZE = 40;
	boolean writeGameLogsToFile = false; // Should we write game logs to file?
	// if (args[0].equalsIgnoreCase("true")) {
	// writeGameLogsToFile = true; // Command line selection
	// }
	
	//Variables for database
	int humanWinner = 0;
	int AIWinner = 0;
	int gameID;
	int draws;
	int AIRounds;
	int humanRounds;
	int rounds;
	String gameWinner;
	
	//I think by making 'gm' here, a gamemaster class, there is a better chance of 
	//utilising further down. 
	static commandline.GameMaster gm; 
	
	//Using this logic, it makes more sense to make the player here...
	//commandline.Player human = new commandline.Player(name);
	static commandline.Player human;
	
	
	
	@GET
	@Path("/setAI")
	/**I want the player to be able to set the number of opponents.
	 * we will make buttons that set the number of AI opponents
	 * 
	 */
	public void setAI(@QueryParam("ai") int ai) throws IOException{
		numPlayers += ai;
		gm = new commandline.GameMaster();
		human = new commandline.Player(name);
		gm.loadPlayers(human, 0);
		System.out.println("human player loaded");
		gm.createAI(ai);
		System.out.println("number of AI set = " + ai);
	}
	
	

	/**This is GOT by the Javascript 'shuf' Function which is 
	 * called by the newGame Javacript function. 
	 * @throws IOException
	 */
	@GET
	@Path("/shuffleAndDeal")
	public void shuffleAndDeal() throws IOException{
								System.out.println("The deck was constructed shuffled and dealt");
								// Deck is populated and shuffled
								cd.populateDeck();
								cd.shuffleDeck();
	
								// Game Master deals out the cards
								int j = 0;
								int i = 0;
								while (j < DECK_SIZE) {
									gm.getPlayerByPosition(i).setHand(gm.dealCard(cd, j));
									System.out.println(gm.getPlayerByPosition(i).getName() + " got"
									 + " card: " + gm.dealCard(cd, j));
									j++; // increment Card
									i++; // increment Player
									if (i == (gm.getArraySize())) {
										i = 0; // start dealing again to first player
									}
								}
	}



	/**This, I think, will need to be called at the same time
	 * as next round...
	 * @return
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
	
	
	
	
	
	
	
	@GET
	@Path("/peek")
	public String peek() throws IOException{
		String remove = "|";
		String s;
		//we will print this in HTML
		try {
		//human.getTopCardInfo();<--THIS MAY NOT BE NEEDED REMOVE IF WORKING.
		s = human.getTopCardInfo();
		}
		catch(Exception e) {
			s = "\n You're boned!!!\n \n \n \n \n ";
		}
		s.replaceAll(remove, "");
		return s;
	}
	
	
	
	
	
	/** INTERIM STATE
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/changeState")
	public String changeState() throws IOException{
		String s = "";
		s += gm.getActivePlayer().getName() + "\n";
		try {
			human.getTopCardInfo();
			s += human.getTopCardInfo();
		}
		catch (Exception e) {
			s += "\n They're boned!!!\n \n \n \n \n \n \n \n \n \n";
		}
		gm.playerIsElminated();
		System.out.println(s);
		return s;
	}
	
	
	
	
	
	/**This allows us to 
	 *See the enemy cards 
	 *- it does not allow us to 
	 *see how many cards in hand.  
	 *
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getAICards")
	public String getAICards() throws IOException{ 
		String cardInfo = "";
		Player p = null;
		for (int i = 0; i<numPlayers; i++) {
			try {
				p = gm.getPlayerByPosition(i);
			}
			catch (Exception e) {
				cardInfo += "They're boned!!!\n \n \n \n \n \n \n \n \n"
						+ "They're boned!!!\n \n \n \n \n \n \n \n \n"
						+ "They're boned!!!\n \n \n \n \n \n \n \n \n"
						+ "They're boned!!!\n \n \n \n \n \n \n \n \n";
			}
			if (p != human) {	
				cardInfo += p.getName() + "\n";
				cardInfo += p.getTopCardInfo();
				System.err.println(cardInfo);

			}
		}
		System.out.println(cardInfo);
		return cardInfo;
	}
	
	
	
	
	
	
	@GET
	@Path("/playRound")
	public String playRound() throws IOException{
	if (gm.getActivePlayerName() != "Player") {
		int choice = gm.getActivePlayer().AIChooseCategory();
		gm.sortByCategory(choice);
		gm.communalPile();
		gm.playerIsElminated();
		if(gm.getArraySize()==1) System.out.println("The game has ended");
		}
	String whowon = gm.getPlayersArrList().get(0).getName() + "won the round.";
	return whowon;
	}
	
	
	
	
	
	
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
	
	
	
	@GET
	@Path("/killSwitch")
	public void killSwitch() throws NullPointerException{
				gm = null;
				}
	
	
	@GET
	@Path("/getWhoWon")
	public String getWhoWon() throws IOException{
		return gm.getActivePlayerName();
	}
	
	
	
	
	
	@GET
	@Path("/chooseCat")
	public void chooseCat(@QueryParam("choice") int choice) throws IOException{
		human.setCategoryChoice(choice);
		gm.sortByCategory(choice);
		System.out.println("you chose: " + choice);
		gm.communalPile();
		gm.playerIsElminated();
		if(gm.getArraySize()==1) System.out.println("The game has ended");
	}
	
	
	
	
	
	@GET
	@Path("/getCardsInPlay")
	/** gets cards in the communal pile**
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
	/** gets all the cards in player's hands**
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
		System.out.println(s);
		return s;
	}
	
	
	
	
	@GET
	@Path("/getDraw")
	/** checks for a draw**
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
	 * Store statistics to be saved in database
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
		
//		System.err.println(gameID + "\n" + draws + "\n" + humanWinner + "\n" + AIWinner + 
//				"\n" + rounds + "\n" + humanRounds + "\n" + AIRounds + "\n" + gameWinner);
	}
	
	
	/**
	 * Send the game stats to the database to be stored
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
	 * Loads previous stats from the database
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
