/**
 * 
 * PriorityQueue extends Queue for the purpose of queuing BoardStates sorted by f(n).
 * oQ will be the open queue, and cQ will be the closed queue. 
 * 
 * @author walter
 *
 */
public class PriorityQueue extends Queue {

	/**
	 * Standard constructor calls super().
	 */
	public PriorityQueue()
	{
		super();
	}
	
	/**
//	 * priorityEnqueue inserts BoardStates(Comparable<Object>) to the oQ PriorityQueue sorted by f(n).
	 * @param item of type Comparable<Object>, usually a BoardState
	 */
	public void priorityEnqueue(Comparable<Object> item) {
		// If empty
		if(this.isEmpty()) {
			enqueue(item);
		}
		else {
			LinkedListNode node = new LinkedListNode(item);
			LinkedListNode current = head;
			int currentF = ((BoardState)current.getData()).getH() + ((BoardState)current.getData()).getG();
			int itemF = ((BoardState)item).getH() + ((BoardState)item).getG();
			// If item has lower F than head
			if(itemF <= currentF) {
				this.insertFront(item);
				size++;
			}
			else {
				// If only one node in list and item has higher F
				if(current.getNext() == null) {
					insertBack(item);
					size++;
				}
				// If at least two nodes in list and need to check
				else {
					while(current.getNext() != null) {
						int nextF = ((BoardState)current.getNext().getData()).getH() + 
								((BoardState)current.getNext().getData()).getG();
						if(itemF <= nextF) {
							node.setNext(current.getNext());
							current.setNext(node);
							size++;
							return;
						}
						else {
							current = current.getNext();
						}
					}
					current.setNext(node);
					size++;
				}
			}
		}
	} 

	/**
	 * find does searches for a BoardState (Comparable<Object>) in the PriorityQueue and returns it.
	 * 
	 * @param item of type Comparable<Object>, usually a BoardState
	 * @returns Comparable<Object>, usually a BoardState
	 */
	public Comparable<Object> find(Comparable<Object> item) {
		// Empty list
		if(this.getHead() == null)
			return null;
		// Not empty list
		else {
			LinkedListNode current = head;
			while(current != null) {
				if(((BoardState)current.getData()).equals(((BoardState)item))) {
					return ((BoardState)current.getData());
				}
				else {
					current = current.getNext();
				}
			}
			return null;
		} 
	}
}
