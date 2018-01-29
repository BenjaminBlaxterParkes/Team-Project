package commandline;

import java.sql.*;

public class Stats {

	private String databaseName = "m_17_2293327p";
	private String username = "m_17_2293327p";
	private String password = "2293327p";

	private Connection connection = null;

	public Stats(String dName, String username, String password) {
		this.databaseName = dName;
		this.username = username;
		this.password = password;

	}

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
			//System.out.println("Connected to database.\n");
		} else {
			System.err.println("Failed to make connection!");
		}
	}

	public void disconnection() {
		try {
			connection.close();
			//System.out.println("Ended connection to database.\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed – SQL exception");
		}
	}

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

	/*
	 * This method allows all courses to be viewed in another frame
	 */

	public void viewStats() {

		Statement stmt = null;
		String query = "select courses.courseid, courses.name, courses.capacity, instructor.fname, instructor.lname\n "
				+ "FROM gym_schema.courses\n " + "inner join gym_schema.instructor\n "
				+ "on courses.instructorid = instructor.instructorid ";

		String combo = "";
		String header = "# of Games \tDraws \tHuman Wins \tAI Wins \tHighestround \tFName \tSname\n\n";
		String report = "";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String count = getGameCount();
				String draws = rs.getString("draws");
				String humanWin = rs.getString("human_winner");
				String aiWin = rs.getString("ai_winner");
				String rounds = rs.getString("rounds");
				combo += count + "\t" + aiWin + "\t " + humanWin + "\t" + draws + "\t" + rounds + "\n";
			}
			report = header + combo;
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		// this takes line and prints into a viewer gui

	}

	/**
	 * Returns total number of games played
	 * 
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

	// • How many times the computer has won

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

	// • How many times the human has won

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

	// • The average number of draws

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

	// • The largest number of rounds played in a single game

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
	
	public String getGameSummary() {
		
		String summary = "Games played: \t" + getGameCount() + "\n" +
		  "AI wins: \t" + getAIWin() + "\n" +
		  "Human wins: \t" + getHumanWin() + "\n" +
		  "Average draws: \t" + getAveDraws() + "\n" +
		  "Largest round: \t" +  getLargestRound();
		
		return summary;
		
	}
	
	

} // End of class