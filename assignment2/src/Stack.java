/**
// Note: You do not need this for your assignment...
//       I included it just because it is awesome! :)
*/

public class Stack extends LinkedList implements StackInterface
{
	protected int size;
	
	public Stack()
	{
		super();
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public Object top() throws EmptyStackException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("Stack is empty!");
		}
		return head.getData();
	}
	
	public Object pop() throws EmptyStackException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("Stack is empty!");
		}
		size--;
		return removeFront();
	}
	
	public void push(Object item)
	{
		insertFront(item);
		size++;
	}
	
	
	
	
}
