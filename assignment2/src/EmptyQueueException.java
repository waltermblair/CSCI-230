/**
 * 
 * Custom exception for empty queue handling.
 * 
 * @author Prof
 *
 */
public class EmptyQueueException extends RuntimeException
{
	public EmptyQueueException(String err)
	{
		super(err);
	}
}
