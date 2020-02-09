
/**
 * Implements a dictionary of records using a hash table with separate chaining. Collisions are resolved via separate chaining.
 * 
 * @author Bazillah Zargar
 */

import java.util.LinkedList;

public class Dictionary implements DictionaryADT {

	private LinkedList<Record>[] table; // hash table with linked lists for separate chaining
	private int numElements; // number of keys in the dictionary

	/**
	 * Creates a dictionary of the specified size.
	 * 
	 * @param size of the dictionary to be created
	 */
	public Dictionary(int size) {
		table = (LinkedList<Record>[]) new LinkedList[size]; // create an array of linked lists of the given size
		numElements = 0; // set the number of keys in the hash table to 0
	}

	/**
	 * Inserts the given record pair into the dictionary. Throws a
	 * DictionaryException if the record pair is already in the dictionary
	 * 
	 * @param pair is the record pair to be inserted into the dictionary.
	 * @return 1 if the insertion of pair produces a collision, 0 otherwise
	 */
	public int insert(Record pair) throws DictionaryException {
		int hashLocation = hashFunction(pair.getConfig());

		// Throws a DictionaryException or returns 1 if there is a collision
		if (table[hashLocation] != null) {
			for (int i = 0; i < table[hashLocation].size(); i++) {
				Record entry = table[hashLocation].get(i);
				if (entry.getConfig().compareTo(pair.getConfig()) == 0) {
					throw new DictionaryException();
				}
			}
			table[hashLocation].add(pair);
			numElements++;
			return 1;
		}
		// Creates a new linked list at the hash location if there is no collision
		else {
			LinkedList<Record> newLList = new LinkedList<Record>();
			newLList.addFirst(pair);
			table[hashLocation] = newLList;
			numElements++;
			return 0;
		}
	}

	/**
	 * Removes the entry with the given configuration from the dictionary. Throws a
	 * DictionaryException if the configuration is not in the dictionary
	 * 
	 * @param config is the key of the record pair to be removed from the dictionary
	 */
	public void remove(String config) throws DictionaryException {
		int hashLocation = hashFunction(config);

		// Throws DictionaryException if the configuration is not in the dictionary
		if (table[hashLocation] == null) {
			throw new DictionaryException();
		}
		// Removes entry from dictionary
		for (int i = 0; i < table[hashLocation].size(); i++) {
			Record entry = table[hashLocation].get(i);
			if (entry.getConfig().compareTo(config) == 0) {
				table[hashLocation].remove(i);
				numElements--;
				return;
			}
		}
		throw new DictionaryException();
	}

	/**
	 * Finds and returns the score stored in the dictionary for the given
	 * configuration. Throws a DictionaryException if the record pair is not in the
	 * dictionary
	 * 
	 * @param config is the configuration key used to search the dictionary
	 * @return the score stored in the dictionary for the given configuration or -1
	 *         if the configuration is not in the dictionary.
	 */
	public int get(String config) {
		int hashLocation = hashFunction(config);

		// When configuration is not in dictionary
		if (table[hashLocation] == null) {
			return -1;
		}

		// Returns score when configuration is in dictionary
		for (int i = 0; i < table[hashLocation].size(); i++) {
			Record entry = table[hashLocation].get(i);
			if (entry.getConfig().compareTo(config) == 0) {
				return entry.getScore();
			}
		}
		return -1;
	}

	/**
	 * Returns the number of records stored in the dictionary.
	 * 
	 * @return the number of record objects stored in the dictionary
	 */
	public int numElements() {
		return numElements;
	}

	/**
	 * Calculates and returns a location in the dictionary (hash table) for the
	 * given configuration
	 * 
	 * @param config is the configuration is used to determine a location in the
	 *               dictionary (hash table)
	 * @return the hash location of the given configuration
	 */
	private int hashFunction(String config) {
		int hash = 0;
		for (int i = 0; i < config.length(); i++) {
			hash = ((hash * 37) + config.charAt(i)) % table.length;
		}
		return hash;
	}

}
