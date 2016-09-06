import java.util.Scanner;

/**
 * 
 * A BoardState is a one dimensional array with the empty square coded as 0.
 * 
 * @author walter
 *
 */
public class BoardState implements Comparable<Object> {

	private int[] array = new int[9];
	private int g;
	private int h;
	private BoardState parent;
		
	public BoardState(int[] array) {
		super();
		for(int i = 0; i < array.length; i++) {
			this.array[i] = array[i];
		}
	}
	/**
	 * Constructor for BoardState that takes user input
	 * Should I check input?
	 * 
	 * @param kb - Scanner object for keyboard input
	 */
	public BoardState(Scanner kb) {
		for(int i = 0; i < 9; i++)
			array[i] = kb.nextInt();
	}
	
	/**
	 * Compares f scores between this BoardState and that BoardState.
	 */
	public int compareTo(Object obj) {
		int thisF = this.getG() + this.getH();
		int thatF = ((BoardState)obj).getG() + ((BoardState)obj).getH();
		if(thisF == thatF)
			return 0;
		else if(thisF < thatF) 
			return -1;
		else
			return 1;
	}
	
	/**
	 * Tests if two board state tile arrangements are equal
	 */
	public boolean equals(Object obj) {
		int[] thisArray = this.getArray();
		int[] thatArray = ((BoardState)obj).getArray();
		for(int i = 0; i < thisArray.length; i++) {
			if(thisArray[i] != thatArray[i])
				return false;
		}
		return true;
	}
	
	/**
	 * Returns contents of array. This should be 3x3.
	 */
	public String toString() {
		String s = "\n";
		for(int a = 0; a < 9; a+=3) {
			for(int i = a; i < a+3; i++) {
				s += array[i];
			}
			s += "\n";
		}
		return s;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public BoardState getParent() {
		return parent;
	}

	public void setParent(BoardState parent) {
		this.parent = parent;
	}
	
}
