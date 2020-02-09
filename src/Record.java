/**
 * Represents an entry in the dictionary, associating a gameBoard configuration
 * with its integer score.
 * 
 * @author Bazillah Zargar
 */

public class Record {

	private String config; // string configuration of the gameBoard
	private int score; // score associated with the string configuration

	/**
	 * Find the next cell in the path from the WPC cell to the destination cell C
	 * 
	 * @param config is the string representation of the gameBoard from the top left
	 *               position and moving from left to right and from top to bottom.
	 *               Ex "O XXXXOO "
	 * @param score  is the score associated with the configuration
	 * @return a new record with the specified configuration and score
	 */
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}

	/**
	 * This method returns the configuration stored in the given record.
	 * 
	 * @return configuration stored in record.
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * This method returns the score stored in the given record.
	 * 
	 * @return score stored in record.
	 */
	public int getScore() {
		return score;
	}

}
