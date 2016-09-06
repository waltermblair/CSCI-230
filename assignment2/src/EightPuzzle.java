import java.util.Scanner;

/**
 * 
 * This is the main class that contains the 8-Puzzle game.
 * 
 * @author walter
 *
 */
public class EightPuzzle {
	
	private BoardState goal;
	
	/**
	 * EightPuzzle() constructor initiates the puzzle game by collecting user input and solving goal state.
	 */
	public EightPuzzle() {
		
		Scanner kb = new Scanner(System.in);
		int[] goalArray = {8, 1, 3, 0, 7, 4, 2, 5, 6};
		goal = new BoardState(goalArray);
		while(!checkReachable(goal)) {
			System.out.println("Please enter a reachable goal state");
				for(int i = 0; i < 9; i++) {
					goalArray[i] = kb.nextInt();
				}
			goal = new BoardState(goalArray);
		}
		System.out.println("Goal State: " + goal.toString());
		System.out.println("One small step for Walter...");
		solve(goal);
		System.out.println("One giant leap for Walterkind");
		kb.close();
	}
	
	/**
	 * Method checkReachable() returns true if number of tile inversions in state is even, false if odd.
	 * Use a nested loop through array that counts each time i is out of order with j.
	 */
	public boolean checkReachable(BoardState goal) {
		int count = 0;
		int[] array = goal.getArray();
		for(int i = 0; i < array.length; i++) {
				for(int j = i+1; j < array.length; j++) {
					if(array[i] != 0 && array[j] != 0) { // 0's are blanks and don't count
						if(array[i] > array[j])
							count++;
					}
				}
		}
		if(count % 2 == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Solve starts from the default 012345678 state and works toward argument goal state using the A*
	 * Search Algorithm and Manhatten Distance Heuristic. A priority queue is used to order the search
	 * tree by f(n).
	 * @param goal - the target BoardState entered by user.
	 */
	public void solve(BoardState goal) {
		PriorityQueue cQ = new PriorityQueue(); // inner nodes
		PriorityQueue oQ = new PriorityQueue(); // leaf nodes
		int[] arrayStart = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		BoardState start = new BoardState(arrayStart);
		oQ.enqueue(start); //enqueue start state in oQ;
		boolean goalFound = false;
		
		//start solve algorithm
		while(!goalFound) {
			BoardState current = (BoardState)oQ.dequeue(); // start state will be here first time
			if(current.equals(goal)) {
				printPath(current);
				goalFound = true;
			}
			else {
				LinkedList children = makeChildren(current);
				cQ.enqueue(current); // to remember it going forward...don't need priorityEnqueue in cQ
				
				LinkedListNode currentChild = children.getHead();
				while(currentChild != null) {
					// Check that currentChild is found in neither oQ nor cQ
					if(oQ.find(((BoardState)currentChild.getData())) == null && 
					   cQ.find(((BoardState)currentChild.getData())) == null) {
						oQ.priorityEnqueue(((BoardState)currentChild.getData()));
					}
					currentChild = currentChild.getNext();
				}
			}
		}
	}
	
	/**
	 * Prints the path to the solution using recursive method.
	 * The argument is the goal child that was created by makeChildren() that matches the target goal.
	 * This child has info on parent. Traverse the tree using getParent().
	 * @param - takes the solved board state as an argument and works backwards from it.
	 */
	public void printPath(BoardState node) {
		if(node != null) {
			printPath(node.getParent());
			System.out.println(node.toString());
		}
	}
	
	/**
	 * makeChildren(BoardState) makes all of the possible children of the argument BoardState.
	 * This is so long because I'm [unnecessarily?] worried about making deep copies of int[] arrays.
	 * @param state - the BoardState parent
	 * @return - a linkedlist containing 2, 3, or 4 children.
	 */
	public LinkedList makeChildren(BoardState state) {
//		//Should generate all possible moves that can be made from argument BoardState
		LinkedList children = new LinkedList();
		int[] array = state.getArray();
		int emptyTile = -1;
		//Find empty tile position
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 0)
				emptyTile = i;
		}
		BoardState child1 = null;
		BoardState child2 = null;
		BoardState child3 = null;
		BoardState child4 = null;
		//Swap tiles to make 2, 3, or 4 children based on emptyTile
		switch(emptyTile) {
			case 0: { //see google spreadsheet
				//Swap 0 and 1
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[0] = array[1];
				childArray1[1] = array[0];
				child1 = new BoardState(childArray1);
				//Swap 0 and 3
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[0] = array[3];
				childArray2[3] = array[0];
				child2 = new BoardState(childArray2);
				break;
			}
			case 2: {
				//Swap 2 and 1
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[2] = array[1];
				childArray1[1] = array[2];
				child1 = new BoardState(childArray1);
				//Swap 2 and 5
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[2] = array[5];
				childArray2[5] = array[2];
				child2 = new BoardState(childArray2);
				break;
			}
			case 6: {
				//Swap 6 and 3
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[6] = array[3];
				childArray1[3] = array[6];
				child1 = new BoardState(childArray1);
				//Swap 6 and 7
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[6] = array[7];
				childArray2[7] = array[6];
				child2 = new BoardState(childArray2);
				break;
			}
			case 8: {
				//Swap 8 and 5
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[8] = array[5];
				childArray1[5] = array[8];
				child1 = new BoardState(childArray1);
				//Swap 8 and 7
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[8] = array[7];
				childArray2[7] = array[8];
				child2 = new BoardState(childArray2);
				break;
			}
			case 1: {
				//Swap 1 and 0
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[1] = array[0];
				childArray1[0] = array[1];
				child1 = new BoardState(childArray1);
				//Swap 1 and 2
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[1] = array[2];
				childArray2[2] = array[1];
				child2 = new BoardState(childArray2);
				//Swap 1 and 4
				int[] childArray3 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray3[i] = array[i];
				}
				childArray3[1] = array[4];
				childArray3[4] = array[1];
				child3 = new BoardState(childArray3);
				break;
			}
			case 3: {
				//Swap 3 and 0
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[3] = array[0];
				childArray1[0] = array[3];
				child1 = new BoardState(childArray1);
				//Swap 3 and 4
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[3] = array[4];
				childArray2[4] = array[3];
				child2 = new BoardState(childArray2);
				//Swap 3 and 6
				int[] childArray3 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray3[i] = array[i];
				}
				childArray3[3] = array[6];
				childArray3[6] = array[3];
				child3 = new BoardState(childArray3);
				break;
			}
			case 5: {
				//Swap 5 and 2
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[5] = array[2];
				childArray1[2] = array[5];
				child1 = new BoardState(childArray1);
				//Swap 5 and 4
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[5] = array[4];
				childArray2[4] = array[5];
				child2 = new BoardState(childArray2);
				//Swap 5 and 8
				int[] childArray3 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray3[i] = array[i];
				}
				childArray3[5] = array[8];
				childArray3[8] = array[5];
				child3 = new BoardState(childArray3);
				break;
			}
			case 7: {
				//Swap 7 and 4
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[7] = array[4];
				childArray1[4] = array[7];
				child1 = new BoardState(childArray1);
				//Swap 7 and 6
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[7] = array[6];
				childArray2[6] = array[7];
				child2 = new BoardState(childArray2);
				//Swap 7 and 8
				int[] childArray3 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray3[i] = array[i];
				}
				childArray3[7] = array[8];
				childArray3[8] = array[7];
				child3 = new BoardState(childArray3);
				break;
			}
			//make four children swapping [4] with [1], [3], [5], [7]
			case 4: {
				//Swap 4 and 1
				int[] childArray1 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray1[i] = array[i];
				}
				childArray1[4] = array[1];
				childArray1[1] = array[4];
				child1 = new BoardState(childArray1);
				//Swap 4 and 3
				int[] childArray2 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray2[i] = array[i];
				}
				childArray2[4] = array[3];
				childArray2[3] = array[4];
				child2 = new BoardState(childArray2);
				//Swap 4 and 5
				int[] childArray3 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray3[i] = array[i];
				}
				childArray3[4] = array[5];
				childArray3[5] = array[4];
				child3 = new BoardState(childArray3);
				//Swap 4 and 7
				int[] childArray4 = new int[9];
				for(int i = 0; i < array.length; i++) {
					childArray4[i] = array[i];
				}
				childArray4[4] = array[7];
				childArray4[7] = array[4];
				child4 = new BoardState(childArray4);
				break;
			}
		}
		//if child was made, set attributes and add to linked list
		if(child1 != null) {
			child1.setG(state.getG()+1);
			child1.setH(this.Manhatten(child1.getArray(), goal.getArray()));
			child1.setParent(state);
			children.insertBack(child1);
		}
		if(child2 != null) {
			child2.setG(state.getG()+1);
			child2.setH(this.Manhatten(child2.getArray(), goal.getArray()));
			child2.setParent(state);
			children.insertBack(child2);
		}
		if(child3 != null) {
			child3.setG(state.getG()+1);
			child3.setH(this.Manhatten(child3.getArray(), goal.getArray()));
			child3.setParent(state);
			children.insertBack(child3);
		}
		if(child4 != null) {
			child4.setG(state.getG()+1);
			child4.setH(this.Manhatten(child4.getArray(), goal.getArray()));
			child4.setParent(state);
			children.insertBack(child4);
		}

		return children;
	}
	
	/**
	 * Computes Manhatten Distance of board state. If you chart out all the possible
	 * shifts with their puzzle distances, it seems that there is sort of a palindrome 
	 * from a shift of -8 to a shift of 8. Subtracting larger index from smaller index,
	 * regardless of which is initial versus end state, results in shift values from
	 * -8 to -1 that can be assigned specific puzzle distances. Three exceptions are
	 * switching index 2 with 6, index 2 with 3, and index 5 with 6.
	 * 
	 * @returns int which is the total Manhatten Distance of the board state from the
	 * initial state of 012345678.
	 */
	public int Manhatten(int[] boardState, int[] goalState) {
		int sum = 0;
		for(int i = 0; i < boardState.length-1; i++) {
			// find where boardState[i] tile is in the goalState.
			int goalIndex = 0;
			for(int j = 0; j < goalState.length-1; j++) {
				if(boardState[i] == goalState[j])
					goalIndex = j;
			}
			// see how far each boardState tile is from its goalState index j
			int shift = 0;
			if(i < goalIndex) {
				shift = i - goalIndex;
			}
			else if(i > goalIndex) {
				shift = goalIndex - i;
			}
			// now assign shift value to the corresponding distance value.
			// special case 1
			if((i == 2 && goalIndex == 6) || (goalIndex == 2 && i == 6)) 
				sum += 4;
			// special case 2
			else if((i == 2 && goalIndex == 3) || (goalIndex == 2 && i == 3) || 
					(i == 5 && goalIndex == 6) || (goalIndex == 5 && i == 6))
				sum += 3;
			// don't count blank tile
			else if(i == 0) {
				// do nothing for blank tile
			}
			// assign based on normal palindrome
			else {
				switch(shift) {
				case -1: sum += 1;
					break;
				case -2: sum += 2;
					break;
				case -3: sum += 1;
					break;
				case -4: sum += 2;
					break;
				case -5: sum += 3;
					break;
				case -6: sum += 2;
					break;
				case -7: sum += 3;
					break;
				case -8: sum += 4;
					break;
				}
			}
		}
		return sum;
	}
	
	
}
