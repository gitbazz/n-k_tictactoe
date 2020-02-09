/**
 * Implements all the methods needed by algorithm computerPlay in order for a
 * human to play a game of TicTacToe against the computer
 * 
 * @author Bazillah Zargar
 */

public class nk_TicTacToe {

	private int board_size; // size of the board
	private int inline; // number of symbols needed in-line to win the game
	private int max_levels; // max level of the game tree that will be explored by the program
	private char[][] gameBoard; // stores the gameboard

	/**
	 * Constructor for the class. Initializes the game board so that every entry
	 * stores a space.
	 * 
	 * @param board_size is the size of the board
	 * @param inline     is the number of symbols needed in-line to win the game
	 * @param max_levels is the max level fo the game tree that will be explored by
	 *                   the program
	 */
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		gameBoard = new char[board_size][board_size];

		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				gameBoard[row][col] = ' ';
			}
		}
	}

	/**
	 * Creates an empty dictionary of the size selected (7993)
	 * 
	 * @return an empty dictionary of the size selected (7993)
	 */
	public Dictionary createDictionary() {
		Dictionary dictionaryTable = new Dictionary(7993);
		return dictionaryTable;
	}

	/**
	 * Represents the content of gameBoard as a string. Then checks whether the
	 * string representing the gameBoard is in the configurations dictionary.
	 * 
	 * @param configurations is the content of gameBoard represented as a string
	 * @return score associated with the configuration representing the gameBoard or
	 *         -1 if the configuration is not in the dictionary.
	 */
	public int repeatedConfig(Dictionary configurations) {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				sb.append(gameBoard[row][col]);
			}
		}
		String string = sb.toString();
		return configurations.get(string);
	}

	/**
	 * Represents the content of the gameBoard as a string. Then inserts the given
	 * string and score in the configurations dictionary.
	 * 
	 * @param configurations is the content of gameBoard represented as a string
	 * @param score          is the score associated with the content of the
	 *                       gameBoard
	 */
	public void insertConfig(Dictionary configurations, int score) {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				sb.append(gameBoard[row][col]);
			}
		}
		String string = sb.toString();
		Record newEntry = new Record(string, score);
		configurations.insert(newEntry);
		return;
	}

	/**
	 * Stores the given symbol in the gameBoard at the given row and column location
	 * 
	 * @param row    is the row in the gameBoard where the symbol is to be stored
	 * @param col    is the column in the gameBoard where the symbol is to be stored
	 * @param symbol is the symbol is to be stored in the gameBoard
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}

	/**
	 * Returns true is the given location in the gameBoard is empty, and false
	 * otherwise
	 * 
	 * @param row is the row to be checked in the gameBoard
	 * @param col is the column to be checked in the gameBoard
	 * @return true is the given location in the gameBoard is empty, and false
	 *         otherwise
	 */
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines whether the given player has won or lost the game.
	 * 
	 * @param symbol is the symbol X of O the player who's win status is being
	 *               checked
	 * @return true if there are k adjacent occurrences of the symbol in the same
	 *         row, column or diagonal of the gameBoard, and false otherwise
	 */
	public boolean wins(char symbol) {

		// Check for horizontal win
		for (int row = 0; row < board_size; row++) {
			int countHorizontal = 0;
			for (int col = 0; col < board_size; col++) {
				if (gameBoard[row][col] == symbol) {
					countHorizontal++;
				}
				if (countHorizontal == inline) {
					return true;
				} else if (countHorizontal > inline) {
					return true;
				} else if (gameBoard[row][col] != symbol) {
					countHorizontal = 0;
				}
			}

		}

		// Check for vertical win
		for (int col = 0; col < board_size; col++) {
			int countVertical = 0;
			for (int row = 0; row < board_size; row++) {
				if (gameBoard[row][col] == symbol) {
					countVertical++;
				}
				if (countVertical == inline) {
					return true;
				} else if (countVertical > inline) {
					return true;
				} else if (gameBoard[row][col] != symbol) {
					countVertical = 0;
				}
			}
		}

		// Check for diagonal win
		int countToTarget = inline - 1;
		int countDiagonal;
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				if ((col + countToTarget < board_size) && (row + countToTarget < board_size)) {
					countDiagonal = 0;
					for (int i = 0; i < inline; i++) {
						if (gameBoard[row + i][col + i] == symbol) {
							countDiagonal++;
						}
					}
					if (countDiagonal == inline) {
						return true;
					}
				}
				if ((col + countToTarget < board_size) && (row - countToTarget >= 0)) {
					countDiagonal = 0;
					for (int i = 0; i < inline; i++) {
						if (gameBoard[row - i][col + i] == symbol) {
							countDiagonal++;
						}
					}
					if (countDiagonal == inline) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Determines whether or not the game is a draw
	 * 
	 * @return true if gameBoard has no empty positions left and no player has won
	 *         the game, returns false otherwise.
	 */
	public boolean isDraw() {
		boolean empty = false;
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				if (gameBoard[row][col] == ' ') {
					empty = true;
				}
			}
		}
		if (!(empty) && !(wins('X')) && !(wins('O'))) {
			return true;
		}
		return false;
	}

	/**
	 * Assigns a score value to the configuration based on whether computer won,
	 * human won, game is a draw or the game is still undecided
	 * 
	 * @return 3 if computer has won, 0 is human has won, 2 if game is a draw, 1 if
	 *         game is still undecided
	 */
	public int evalBoard() {
		if (wins('O')) {
			return 3;
		} else if (wins('X')) {
			return 0;
		} else if (isDraw()) {
			return 2;
		} else {
			return 1;
		}
	}
}
