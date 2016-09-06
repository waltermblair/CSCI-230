/**
 * 
 * I don't understand purpose of this interface...
 * 
 * @author Prof
 *
 */
public interface QueueInterface 
{
	public int size();
	public boolean isEmpty();
	public Object front() throws EmptyQueueException;
	public Object dequeue() throws EmptyQueueException;
	public void enqueue(Object item);
}
