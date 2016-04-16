import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The Iterator for Binary Search Tree (BST) that is built using Java's Stack
 * class. This iterator steps through the items BST using an INORDER traversal.
 *
 * @author CS367
 */
public class BinTreeIterator<K,T> implements Iterator<K> 
{

    /** Stack to track where the iterator is in the BST*/
    Stack<BinTreenode<K,T>> stack;

    /**
     * Constructs the iterator so that it is initially at the smallest
     * value in the set. Hint: Go to farthest left node and push each node
     * onto the stack while stepping down the BST to get there.
     *
     * @param n the root node of the BST
     */
    public BinTreeIterator(BinTreenode<K,T> n)
    {
        //TODO
    	//create stack
    	stack = new Stack<BinTreenode<K,T>>();
    	
    	//fill stack with root, and left-children nodes
    	stack.push(n);
    	travelLeft(n);
    }
   
    /**
     * Returns true iff the iterator has more items.
     *
     * @return true iff the iterator has more items
     */
    public boolean hasNext() 
    {
        //TODO
        return !stack.isEmpty();
    }

    /**
     * Returns the next item in the iteration.
     *
     * @return the next item in the iteration
     * @throws NoSuchElementException if the iterator has no more items
     */
    public K next() 
    {
        //TODO
    	//nothing more to return: stack is empty
    	if(!hasNext())
    		throw new NoSuchElementException();
    	
    	//node containing next key value
    	BinTreenode<K,T> nextNode = stack.pop();
    	
    	//examine right child for potential unvisited nodes 
    	if(nextNode.getRight()!=null)
    	{
    		//fill stack with unvisited nodes
    		stack.push(nextNode.getRight());
    		travelLeft(stack.peek());
    	}
    	return nextNode.getKey();
    }
    
    /**
     * Iterative helper method for next()
     * Pushes nodes onto stack while traversing left from starting node
     * until unable to go further.
     * 
     * @param start the starting node from which left-most traversal begins
     */
    private void travelLeft(BinTreenode<K,T> start)
    {
    	BinTreenode<K,T> current = start.getLeft();
    	
    	//push left-child nodes on stack until node does not have left child
    	while(current!=null)
    	{
    		stack.push(current);
    		current = current.getLeft();
    	}
    }    

    /**
     * Not Supported
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    public static void main(String[] args)
    {
    	BinTreenode<Integer, String> root = new BinTreenode<Integer, String>(10, null);
    	BinTreenode<Integer, String> n1 = new BinTreenode<Integer, String>(3, null);
    	BinTreenode<Integer, String> n2 = new BinTreenode<Integer, String>(15, null);
    	BinTreenode<Integer, String> n3 = new BinTreenode<Integer, String>(2, null);
    	BinTreenode<Integer, String> n4 = new BinTreenode<Integer, String>(5, null);
    	BinTreenode<Integer, String> n5 = new BinTreenode<Integer, String>(20, null);
    	BinTreenode<Integer, String> n6 = new BinTreenode<Integer, String>(4, null);
    	BinTreenode<Integer, String> n7 = new BinTreenode<Integer, String>(7, null);
    	BinTreenode<Integer, String> n8 = new BinTreenode<Integer, String>(8, null);
    	
    	root.setLeft(n1);
    	root.setRight(n2);
    	n1.setLeft(n3);
    	n1.setRight(n4);
    	n2.setRight(n5);
    	n4.setLeft(n6);
    	n4.setRight(n7);
    	n7.setRight(n8);
    	
    	BinTreeIterator<Integer, String> iterator = new BinTreeIterator<Integer, String>(root);
    	System.out.println(iterator.hasNext());
    	while(iterator.hasNext())
    		System.out.println(iterator.next());
    }
}