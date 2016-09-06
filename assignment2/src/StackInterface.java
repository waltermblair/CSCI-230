/**
// Note: You do not need this for your assignment...
//       I included it just because it is awesome! :)
*/

public interface StackInterface {


	public int size();
	public boolean isEmpty();
	public Object top() throws EmptyStackException;
	public Object pop() throws EmptyStackException;
	public void push(Object item);

	
}
