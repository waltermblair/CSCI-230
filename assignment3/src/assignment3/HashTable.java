package assignment3;
import java.util.Scanner;

/**
 * 
 * HashTable is an adjacency list hash table.
 * Contains the hashFunction that converts the word key into the array index as well as insert and find functions.
 * Also contains some functions to return summary statistics.
 * 
 * @author walter
 *
 */
public class HashTable {
	private LinkedList[] array;
	private int indicesUsed = 0;

	public HashTable(int x) {
		array = new LinkedList[x];
	}
	
	/**
	 * Reads structured file and creates an Entry object from each line 
	 * and inserts object into hash table using Insert method
	 * @param Scanner object created in Main
	 */
	public void populate(Scanner file) {
		while(file.hasNextLine()) {
			String nextLine = file.nextLine();
			String delims = "[ ]+";
			String[] tokens = nextLine.split(delims);
			// Store first String as the word
			String word = tokens[0];
			// Store remaining Strings as the parts of speech array
			String[] POS = new String[15];
			for(int i = 0; i < tokens.length-1; i++) {
				POS[i] = tokens[(i+1)];
			}
			
			// Create and insert new lexigraphic Entry object
			Entry entry = new Entry(word, POS);
			
			// Insert Entry into hashTable at index
			insert(entry);
		}
	}
	
	/**
	 * Computes [Product of (each character's ASCII value * its position in the word)] % size of HashTable.
	 * Performs mod operation after each letter for better or worse.
	 * @param word is the key
	 * @return int index in the HashTable
	 */
	public int hashFunction(String word) {
		int product = 1;
		for(int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			product *= letter * (i+1);
			// Doing mod operation every single time...
			product %= array.length;
		}
		return product;
	}
	
	/**
	 * Calls hashFunction and inserts Entry object into the array, creating or modifying
	 * the linked list at that index as needed. Calls LinkedList's insert method.
	 * @param entry - Entry object created from lexicon in Main
	 */
	public void insert(Entry entry) {
		// Call hashFunction
		int index = hashFunction(entry.getWord());
		
		// If index is empty, create new Linked List. If index is not empty, modify Linked List
		if(array[index] == null) {
			array[index] = new LinkedList(entry);
			indicesUsed++;
		}
		else {
			array[index].insert(entry);
		}
	}
	
	/**
	 * HashTable's find method calls hashFunction to find the right index and then 
	 * calls LinkedList's find method to search the linked list.
	 * @param word entered by user
	 * @return Comparable<Object>, usually an Entry object
	 */
	public String find(String word) {
		String s = "";
		int index = hashFunction(word);
		if(array[index] == null)
			return null;
		else {
			String[] pos = ((Entry)array[index].find(word)).getPOS();
			for(int i = 0; i < pos.length; i++) {
				if(pos[i] != null)
					s += (pos[i] + " ");
			}
			s = ("Part(s) of speech: " + s + "\n");
			s += ("Number of collisions to find: " + array[index].getFindCollisions() + "\n");
			return s;
		}
	}
	
	/**
	 * @return returns int number of lexigraphic entries using each LinkedList's length attribute
	 */
	public int numberOfEntries() {
		int n = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				n += array[i].getLength();
			}
		}
		return n;
	}
	
	/**
	 * @return returns int number of collisions (numberOfEntries() - this.indicesUsed)
	 */
	public int numberOfCollisions() {
		return numberOfEntries() - indicesUsed;
	}
	
	/**
	 * @return returns double average number of collisions per lexical entry (numberOfEntries() / numberOfCollisions())
	 */
	public double averageNumberOfCollisions() {
		return numberOfCollisions() / (double)numberOfEntries();
	}
	
	/**
	 * @return returns String containing the number of collisions and word entries for the index with most number of collisions
	 */
	public String maxCollision() {
		// Struggling with two separate loop thing here
		int i = 0;
		int index = i;
		LinkedList current = array[i];
		while(current == null) {
			i++;
			if(array[i] != null) {
				current = array[i];
				index = i;
			}
		}
		for(int j = i+1; j < array.length; j++) {
			if(array[j] != null && array[j].getLength() > current.getLength()) {
				current = array[j];
				index = j;
			}
		}
		return ("Index " + index + "\n" + current.toString());
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(LinkedList[] array) {
		this.array = array;
	}
	
	

}
