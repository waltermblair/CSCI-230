/**
 * 
 * LinkedList is inherited by Calendar, and CalendarDay keeps
 * a list of events. Contains general insert, find, and remove
 * methods for working with the list.
 * 
 * @author walter
 *
 */
class LinkedList {
	public LinkedListNode head;
	//------------------------------------
	/**
	 * public constructor of empty list
	 */
	public LinkedList() {
		this.head = null;
	}
	//------------------------------------
	/**
	 * 
	 * @return - returns true if empty, false if not empty
	 */
	public boolean isEmpty() {
		if(head == null)
			return true;
		else
			return false;
	}
	//------------------------------------
	/**
	 * 
	 * removes a specified node
	 * 
	 * @param item - could be a CalendarEvent or CalendarDay
	 * @return - returns the Event or Day that it removed
	 */
	public Comparable<Object> remove(Comparable<Object> item) {
		if(head == null)
			return null;
		else {
			LinkedListNode current = head;
			if(current.getNext() == null) {
				head = null;
				return current.getData();
			}
			else {
				while(current.getNext() != null) {
					if(current.getNext() == item) {
						LinkedListNode temp = current.getNext(); 
						current.setNext(temp.getNext());
						return temp.getData();
					}
					current = current.getNext(); 
				}
				return null;
			}
		}
	}
	//------------------------------------
	/**
	 * 
	 * finds the specified CalendarDay or CalendarEvent
	 * 
	 * @param item - could be a CalendarDay or CalendarEvent
	 * @return - returns the found Day or Event
	 */
	public Comparable<Object> find(Comparable<Object> item) {
		if(head == null) 
			return null;
		else {
			LinkedListNode current = head;
			if(current.getNext() == null) {
				if(current == item)
					return current.getData();
			}
			else {
				while(current.getNext() != null) {
					if(current == item)
						return current.getData();
					current = current.getNext(); 
				}
			}
			return null;
		}
	}
	//------------------------------------
	/**
	 * 
	 * Inserts the CalendarDay or CalendarEvent into the appropriate
	 * place in the associated linked list.
	 * 
	 * @param item - Could be a Day or Event
	 * @return - returns the inserted item
	 */
	// nested traverse/compareTo() to find where -1 switches to 1.
	public Comparable<Object> insert(Comparable<Object> item) {	
		if(head == null) { // if list is empty
			head = new LinkedListNode(item); 
			return head.getData();
		}
		else {
			if(item.compareTo(head.getData()) < 0) { // if list has a node but item is smaller than node, insert item before node
				LinkedListNode temp = new LinkedListNode(item);
				temp.setNext(head);
				head = temp;
				return temp.getData();
			}
			else { // if list has a node but item is bigger, start traverse
				LinkedListNode current = head;
				LinkedListNode temp = new LinkedListNode(item);
				while(current != null) {
					if(current.getNext() == null) { // if we're at end of list
							current.setNext(temp);
							return temp.getData();
					}
					else if(item.compareTo(current.getNext().getData()) < 0) {	// if there is a subsequent node, test it
						temp.setNext(current.getNext());
						current.setNext(temp);
						return temp.getData();
					}
					else
						current = current.getNext(); 
				}
			}
			return (new LinkedListNode(item)).getData();
		}
	}
	//------------------------------------
	/**
	 * General toString() method
	 */
	public String toString() { 
		String s = "";
		LinkedListNode p = head;
		while(p != null) {
			s += p.getData();
			p = p.getNext(); 
		}
		return s;
	}
}