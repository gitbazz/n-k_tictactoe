# (n, k) Tic-Tac-Toe

(n, k) Tic-Tac-Toe is played on a board of size n × n and to win the game a player needs to put k symbols on adjacent positions of the same row, column, or diagonal. The program plays against a human opponent.
The human player always starts the game. The human uses ’X’s and the computer uses ’O’s. In each turn the computer examines all possible plays or moves and selects the best one. To do this, each possible move is assigned a score. Each board configuration is also assigned a score. To compute scores, the program uses a recursive algorithm that repeatedly simulates a move
from the computer followed by a move from the human, until an outcome for the game has been
decided. This recursive algorithm implicitly creates a game tree formed by all the moves that the players can make starting at the current configuration. Every time that the score of a board configuration is computed,
the configuration and its score are stored in a dictionary, that is implemented using a hash table. When the algorithm computerPlay explores the game tree trying to determine the computer’s
best move, before it expands a configuration b it looks it up in the dictionary. If b is in the
dictionary, then its score is simply extracted from the dictionary, instead of exploring the part of the
game tree below b.

**Record.java** class that represents an entry in the dictionary, associating a configuration with its integer score.

**Dictionary.java** class that implements a dictionary using a hash table with separate chaining.

**nk_TicTacToe.java** class that implements all the methods needed by algorithm computerPlay.






