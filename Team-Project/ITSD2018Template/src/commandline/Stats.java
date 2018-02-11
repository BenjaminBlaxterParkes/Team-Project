package commandline;

import java.sql.*;

/**
 * This class contains and controls the database queries
 * @author Kyrie
 */

public class Stats {

	/*
	 * Class constants
	 */
	private String databaseName = "m_17_2293327p";
	private String username = "m_17_2293327p";
	private String password = "2293327p";
	private Connection connection = null;

	/**
	 * Stats constructor
	 * @param dName
	 * @param username
	 * @param password
	 */
	public Stats(String dName, String username, String password) {
		this.databaseName = dName;
		this.username = username;
		this.password = password;
	}

	/**
	 * Method to form connection with DB
	 */
	public void connection() {

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + databaseName,
					username, password);
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
		} else {
			System.err.println("Failed to make connection!");
		}
	}

	/**
	 * Method to disconnect from DB
	 */
	public void disconnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed – SQL exception");
		}
	}

	/**
	 * Method to insert query into DB
	 * @param gameID
	 * @param draws
	 * @param humanWinner
	 * @param AIWinner
	 * @param rounds
	 * @param humanRounds
	 * @param AIRounds
	 * @param gameWinner
	 */
	public void recordStats(int gameID, int draws, int humanWinner, int AIWinner, int rounds, int humanRounds,
			int AIRounds, String gameWinner) {

		Statement stmt = null;
		String query = "insert into stats.gamestats \n" + "values \n" + "(" + gameID + "," + draws + "," + humanWinner
				+ "," + AIWinner + "," + rounds + "," + humanRounds + "," + AIRounds + ", '" + gameWinner + "'); \n";

		try {
			stmt = connection.createStatement();
			int rows = stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
	}
	

	/**
	 * Returns total number of games played
	 * @return
	 */
	public String getGameCount() {

		Statement stmt = null;
		String query = "SELECT COUNT (gameID) \n " + "FROM stats.gamestats ;";

		String count = "";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				count = rs.getString("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		return count;
	}


	/**
	 * Method to return number of AI wins
	 * @return
	 */
	public String getAIWin() {

		Statement stmt = null;
		String query = "SELECT SUM(ai_winner) \n " + "FROM stats.gamestats;";

		String ave = "";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ave = rs.getString("SUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query: " + query);
		}
		return ave;
	}


	/**
	 * Method to return number of human wins
	 * @return
	 */
	public String getHumanWin() {

		Statement stmt = null;
		String query = "SELECT SUM (human_winner) \n " + "FROM stats.gamestats;";

		String ave = "";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ave = rs.getString("SUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query: " + query);
		}

		return ave;
	}


	/**
	 * Method to return average number of draws
	 * @return
	 */
	public String getAveDraws() {

		Statement stmt = null;
		String query = "SELECT AVG(draws) \n " + "FROM stats.gamestats;";

		String ave = "";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ave = rs.getString("AVG");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query: " + query);
		}

		double number = Double.parseDouble(ave);

		String answer = String.format("%.0f", number);

		return answer;
	}


	/**
	 * Returns the largest number of rounds played in a single game
	 * @return
	 */
	public String getLargestRound() {

		Statement stmt = null;
		String query = "SELECT MAX(rounds) \n " + "FROM stats.gamestats;";

		String ave = "";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ave = rs.getString("MAX");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query: " + query);
		}

		return ave;
	}

	
	/**
	 * Method to return string of all stats info
	 * @return
	 */
	public String getGameSummary() {

		String summary = "Games played: \t" + getGameCount() + "\n" + "AI wins: \t" + getAIWin() + "\n"
				+ "Human wins: \t" + getHumanWin() + "\n" + "Average draws: \t" + getAveDraws() + "\n"
				+ "Largest round: \t" + getLargestRound();

		return summary;
	}

} // End of class