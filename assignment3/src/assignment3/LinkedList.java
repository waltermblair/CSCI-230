package assignment3;

public class LinkedList {
	
	private LinkedListNode head;
	private int length;
	private int findCollisions;

	/**
	 * Normal constructor that also increments the list's length attribute after creating the head node.
	 * @param item usually an Entry object.
	 */
	public LinkedList(Comparable<Object> item) {
		head = new LinkedListNode(item);
		length++;
	}

	/**
	 * LinkedList's insert method is called by HashTable to insert the Comparable<Object>
	 * in the Linked List that HashTable created. Also increments the list's length attribute.
	 * @param item usually an Entry object.
	 */
	public void insert(Comparable<Object> item) {
		LinkedListNode newNode = new LinkedListNode(item);
		length++;
		if(head.getNext() == null)
			head.setNext(newNode);
		else {
			LinkedListNode current = head;
			while(current.getNext() != null)
				current = current.getNext();
			current.setNext(newNode);
		}
	}
	
	/**
	 * LinkedList's find method is called by HashTable to find the query word within the appropriate linked list.
	 * @param word passed by HashTable call
	 * @return the Comprable<Object> LinkedListNode data.
	 */
	public Comparable<Object> find(String word) {
		findCollisions = 0;
		if(((Entry)head.getData()).getWord().equals(word))
			return head.getData();
		else {
			if(head.getNext() == null)
				return null;
			else {
				LinkedListNode current = head;
				while(current.getNext() != null) {
					current = current.getNext();
					findCollisions++;
					if(((Entry)current.getData()).getWord().equals(word))
						return current.getData();
				}
				return null;
			}
		}
	}
	
	/**
	 * LinkedList's toString() method returns a string of all the words it contains.
	 */
	public String toString() {
		String s = "";
		if(head == null)
			s = "Null Index";
		else {
			s = ("# Collisions: " + (this.length-1) + "\n");
			if(head.getNext() == null)
				s += ("Entry word: " + ((Entry)head.getData()).getWord() + "\n");
			else {
				LinkedListNode current = head;
				while(current.getNext() != null) {
					current = current.getNext();
					s += ("Entry word: " + ((Entry)current.getData()).getWord() + "\n");
				}
			}
		}
		return s;
	}

	public LinkedListNode getHead() {
		return head;
	}

	public void setHead(LinkedListNode head) {
		this.head = head;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getFindCollisions() {
		return findCollisions;
	}

	public void setFindCollisions(int findCollisions) {
		this.findCollisions = findCollisions;
	}
	
	
}
