/**
 * 
 * LinkedList with protected head and size attributes. 
 * Size is used by descendants queue and stack
 * 
 * Methods are: 
 * 	insertFront(Object item)
 * 	insertBack(Object item)
 * 	removeFront()
 * 	removeBack()
 * 
 * @author Prof
 *
 */
public class LinkedList
{
	protected int size;
	protected LinkedListNode head;
	
	//Creates a new instance of LinkedList
	public LinkedList()
	{
		head=null;
	}
	
	//Returns the head position of the linked list
	public LinkedListNode getHead()
	{
		return head;
	}
	
	//Sets the head of the linked list
	public void setHead(LinkedListNode head)
	{
		head = head.getNext();
	}
	
	/**
	 * Adds item to the front of the linked list.
	 * Used in Stack's push method
	 * 
	 * @param item of type Object
	 */
	public void insertFront(Object item)
	{
		LinkedListNode node = new LinkedListNode(item);
		
		if(head == null)
		{
			head = node;
		}
		else
		{
			node.setNext(head);//makes node point to the first node
			head = node; //makes variable head point to the new node and set it;
		}
	}
	
	/**
	 * 
	 * Adds item to linked list from the back end.
	 * Used in Queue's enqueue method.
	 * 
	 * @param item
	 */
	public void insertBack(Object item)
	{
		LinkedListNode node = new LinkedListNode(item);
		LinkedListNode current = head;
		
		//If list is empty node will be inserted and be set as head.
		if(current == null)
		{
			head = node;
		}
		else
		{
			while(current.getNext() != null) // why doesn't this throw nullpointerexception if only one node?
			   {
			    current = current.getNext();
			   }
			     current.setNext(node); 
	    }
	}
	
	/**
	 * Removes item at the front of the linked list.
	 * Used in Queue's dequeue function.
	 * 
	 * @return
	 */
	public Object removeFront()
	{
		LinkedListNode node;
		
		Object item = head.getData();
		node = head;
		head = head.getNext();
		node.setNext(null);
		return item;
	}
	
	/**
	 * Removes item at the back of the linked list.
	 * Used in Stack's pop method.
	 * 
	 * @return
	 */
	public Object removeBack()
	{
		LinkedListNode current; 
		
		Object item = head.getData();
		current = head;
		while(current.getNext() != null) // no base case?
		{
			current = current.getNext();
		}
		current.setNext(null);
		return item;
	}
	
	
}
