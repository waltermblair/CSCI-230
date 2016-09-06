package assignment3;
import java.io.*;
import java.util.Scanner;

/**
 * 
 * This program creates a hash table of size 24,593, reads and inputs from LEXICON.txt,
 * prints some info on the hash table, and asks the user for search queries. 
 * 
 * @author walter
 *
 */
class Main {

	public static void main(String[] args) {

		HashTable hashTable = new HashTable(24593);

		// Read file and build hash table
		File file = new File("LEXICON.txt");
		try {
			Scanner input = new Scanner(file);
			hashTable.populate(input);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		// Print finished hashTable info
		System.out.println("Number of lexigraphic entries: \n" + hashTable.numberOfEntries());
		System.out.println("Total number of collisions in the table: \n" + hashTable.numberOfCollisions());
		System.out.println("Average number of collisions per entry: \n" + hashTable.averageNumberOfCollisions());
		System.out.println("The array index with the most number of collisions: \n" + hashTable.maxCollision());
		
		// Loop that takes user input for lookup
		while(true) {
			Scanner kb = new Scanner(System.in);
			System.out.println("Enter a word you'd like to find: \n");
			String query = kb.next();
			System.out.println(hashTable.find(query));
		}
	}
}