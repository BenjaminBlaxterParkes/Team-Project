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

import commandline.CardDeck;
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

	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------

//	@GET
//	@Path("/helloJSONList")
//	/**
//	 * Here is an example of a simple REST get request that returns a String.
//	 * We also illustrate here how we can convert Java objects to JSON strings.
//	 * @return - List of words as JSON
//	 * @throws IOException
//	 */
//	public String helloJSONList() throws IOException {
//
//		List<String> listOfWords = new ArrayList<String>();
//		listOfWords.add("Hello");
//		listOfWords.add("World");
//
//		// We can turn arbatory Java objects directly into JSON strings using
//		// Jackson seralization, assuming that the Java objects are not too complex.
//		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
//		return listAsJSONString;
//	}
//
//	@GET
//	@Path("/helloWord")
//	/**
//	 * Here is an example of how to read parameters provided in an HTML Get request.
//	 * @param Word - A word
//	 * @return - A String
//	 * @throws IOException
//	 */
//	public String helloWord(@QueryParam("Word") String Word) throws IOException {
//		return "Hello "+Word;
//	}

	//*~*Variables!
	int play = 0;
	String answer = "0";
	String name = "player";
	String opponents = "";
	CardDeck cd = new CardDeck();
	Stats stats = new Stats("m_17_2293327p", "m_17_2293327p", "2293327p");
	final int DECK_SIZE = 40;
	int round = 0;
	boolean writeGameLogsToFile = false; // Should we write game logs to file?
	// if (args[0].equalsIgnoreCase("true")) {
	// writeGameLogsToFile = true; // Command line selection
	// }

	//I think by making 'gm' here, a gamemaster class, there is a better chance of
	//utilising further down.
	commandline.GameMaster gm = new commandline.GameMaster();

	//Using this logic, it makes more sense to make the player here...
	commandline.Player human = new commandline.Player(name);



	// Starting a new game, then, can be
	//as imple as loading players into an ArrayList
	@GET
	@Path("/newGame")
	/**My thinking here is that when we call a new game, it can be to add
	 * players to a list... so, this is the REST that will be called when we
	 * click the new game button
	 * At present, when we click, 'new game' we start a load players into the game master class.
	 */
	public void newGame() throws IOException{
	gm.loadPlayers(human, 0);
	System.out.println("players loaded");
	}

	@GET
	@Path("/setAI")
	/**I want the player to be able to set the number of opponents.
	 * we will make buttons that set the number of AI opponents
	 *
	 */
	public void setAI(@QueryParam("ai") int ai) throws IOException{
		gm.createAI(ai);
		System.out.println("number of players set = " + ai);
	}

	@GET
	@Path("/setName")
	/**This should allow us to set the player's name*/
	public void setName(@QueryParam("nm") String nm) throws IOException{
		name = nm;
	}

//	I don't know if this is needed...
//@GET
//@Path("/getName")
//public String getName() throws IOException {
//							return "Player Name";
//							}

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

@GET
@Path("/nextRound")
public void nextRound() throws IOException{
								round++;

//								System.out.println("Round " + round + "\n====================\n" + "It's "
//										+ gm.getPastPlayerName() + "'s turn!\n\n" + "Players:");
//								System.out.println(gm.getPlayerArrayInfo());
							//We will need this information
								//to be be spat out in HTML version...
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
	System.out.println("Peek at your top card:");
	String s;
	//we will print this in HTML
	try {
	human.getTopCardInfo();
	s = human.getTopCardInfo();}
	catch(Exception e) {
		s = "You're boned!!!\n \n \n \n \n ";
	}
	return s;
}

@GET
@Path("/playRound")
public String playRound() throws IOException{
if (gm.getActivePlayerName() != "") {
	int choice = gm.getActivePlayer().AIChooseCategory();
	gm.sortByCategory(choice);
	gm.communalPile();
	gm.playerIsElminated();
	}
String whowon = gm.getPlayersArrList().get(0).getName() + "won the round.";
return whowon;
}

@GET
@Path("/whoseTurn")
public String whoseTurn() throws IOException{
	return gm.getActivePlayerName();
}

//I THINK THIS IS NOT NEEDED AS A SEPARATE REST METHOD
//IT WILL BE IMPLEMENTED IF FIRST PLAYER IS A COMPUTER
//I WILL LEAVE IT HERE JUST IN CASE
@GET
@Path("/enemyTurn")
public void enemyTurn() throws IOException{
gm.getActivePlayer().AIChooseCategory();
gm.sortByCategory(1);
								}



@GET
@Path("/chooseCat")
public void chooseCat(@QueryParam("choice") int choice) throws IOException{
	human.setCategoryChoice(choice);
	gm.sortByCategory(choice);
	System.out.println("you chose: " + choice);
	gm.communalPile();
	gm.playerIsElminated();
}


//@GET
//@Path("/checkforDraw")
///** checks for a draw**
// *
// * @param choice
// * @throws IOException
// */


//SO - AT THIS POINT IT'S READY FOR THE NEXT ROUND


					}
