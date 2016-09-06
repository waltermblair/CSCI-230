/**
 * 
 * General Linked list nodes that compose linked lists.
 * 
 * @author walter
 *
 */
class LinkedListNode {
	private Comparable<Object> data;
	private LinkedListNode next;
	//------------------------------------
	/**
	 * 
	 * public constructor
	 * @param data - could be event or day data
	 */
	public LinkedListNode(Comparable<Object> data) {
		this.data = data;
		this.next = null;
	}
	//------------------------------------
	/**
	 * returns Node's Comparable<Object> data which is used often
	 * to work access event and day info contained in linked lists
	 * @return
	 */
	public Comparable<Object> getData() {
		return data;
	}
	public void setData(Comparable<Object> data) {
		this.data = data;
	}
	public LinkedListNode getNext() {
		return next;
	}
	public void setNext(LinkedListNode next) {
		this.next = next;
	}
	
}