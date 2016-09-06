/**
 * 
 * Queue extends LinkedList and implements QueueInterface.
 * 	Why does it have its own size, couldn't it use LinkedList's size attribute?
 * Methods are:
 *   size()
 *   isEmpty()
 *   front()
 *   dequeue() - removeFront
 *   enqueue() - insertBack
 * 
 * @author Prof
 *
 */
public class Queue extends LinkedList implements QueueInterface
{
	protected int size;
	
	/**
	 * constructor calls LinkedList's constructor
	 */
	public Queue()
	{
		super();
	}
	
	/**
	 * getter for int size
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Returns true if empty, false if not empty.
	 * Used in front(), dequeue(), enqueue() functions.
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	/**
	 * Returns front of queue's node.getData() object or exception if empty.
	 * Uses isEmpty() function.
	 */
	public Object front() throws EmptyQueueException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("Queue is empty!");
		}
		return head.getData();
	}
	
	/**
	 * Uses super's removeFront() method to dequeue and decrements size (LinkedList's or Queue's size?)
	 * Uses isEmpty() function
	 */
	public Object dequeue() throws EmptyQueueException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("Queue is empty!");
		}
		size--;
		return removeFront();
	}
	
	/**
	 * Uses super's insertBack() method to enqueue and increments size (LinkedList's or Queue's size?)
	 */
	public void enqueue(Object item)
	{
		insertBack(item);
		size++;
	}
	
	
	
	
}
