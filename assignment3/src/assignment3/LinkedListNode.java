package assignment3;

/**
 * Basic node for LinkedList. Comparable<Object> data is usually an Entry object.
 * @author walter
 *
 */
public class LinkedListNode {
	
	private Comparable<Object> data;
	private LinkedListNode next;

	public LinkedListNode(Comparable<Object> item) {
		data = item;
	}

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
