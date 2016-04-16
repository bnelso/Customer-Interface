import java.util.Iterator;
import java.util.Stack;


public class BinTree<K extends Comparable<K>,T>
{
	private BinTreenode<K,T> root;
	//private int height;
	private int nodeCount;
	
	public BinTree()
	{
		root = null;
		//height = 0;
		nodeCount = 0;
	}
	
	public int getCount()
	{
		return nodeCount;
	}
	
	
	/*
	 * inserts a node of the given key and data
	 * duplicates are not allowed
	 */
	public void insert(K key, T data)
	{
		root = insert(root, key, data);
		nodeCount++;
	}
	
	//helper method for insert: recursive
	private BinTreenode<K,T> insert(BinTreenode<K,T> node, K key, T data)
	{
		//base case: reached a leaf
		if(node == null)
			return new BinTreenode<K,T>(key, data);
		int comparison = node.getKey().compareTo(key);
		//what you are looking for is greater than what you are looking at
		if(comparison < 0)
		{
			node.setRight(insert(node.getRight(), key, data));
			if(node.getLeft()!=null)
			{
				node.setHeight(1+max(node.getRight().getHeight(), node.getLeft().getHeight()));
				node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
			}
			else
			{
				node.setHeight(1+node.getRight().getHeight());
				node.setBalanceFactor(-1*node.getRight().getHeight());
			}
			return node;
		}
		//what you are looking for is less than what you are looking at
		else if(comparison > 0)
		{
			node.setLeft(insert(node.getLeft(), key, data));
			if(node.getRight()!=null)
			{
				node.setHeight(1+max(node.getRight().getHeight(), node.getLeft().getHeight()));
				node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
			}
			else
			{
				node.setHeight(1+node.getLeft().getHeight());
				node.setBalanceFactor(node.getLeft().getHeight());
			}
			return node;
		}
		else
			throw new DuplicateException();
	}
	
	private int max(int num1, int num2)
	{
		if(num1 >= num2)
			return num1;
		return num2;
	}
	
	public void delete(K key)
	{
		if(root!=null)
		{
			root = delete(root, key, root.getData());
			nodeCount--;
		}
		
	}
	
	private BinTreenode<K,T> delete(BinTreenode<K,T> node, K key, T data)
	{
		if(node == null)
			return null;
		int comparison = node.getKey().compareTo(key);
		if(comparison == 0)
		{
			//no children
			if(node.getLeft() == null && node.getRight() == null)
				return null;
			//one child
			if(node.getLeft() == null && node.getRight() != null)
				return node.getRight();
			if(node.getLeft() != null && node.getRight() == null)
				return node.getLeft();
			//two children
			
			//return the leftmost child of the right subtree
			BinTreenode<K,T> smallest = smallest(node.getRight());
			node.setKey(smallest.getKey());
			node.setData(smallest.getData());
			node.setRight(delete(node.getRight(), smallest.getKey(), smallest.getData()));
			return node;
		}
		//what you are looking for is greater than what you are looking at
		else if(comparison < 0)
		{
			node.setRight(delete(node.getRight(), key, data));
			return node;
		}
		//what you are looking for is less than what you are looking at
		else
		{
			node.setLeft(delete(node.getLeft(), key, data));
			return node;
		}
	}
	
	//leftmost node of right subtree
	private BinTreenode<K,T> smallest(BinTreenode<K,T> node)
	{
		if(node.getLeft() == null)
			return node;
		else
			return smallest(node.getLeft());
	}
	
	//determines whether or not a node with such a key is in the tree
	public boolean lookup(K key)
	{
		return lookup(root, key);
	}
	
	//helper method for lookup: recursive
	private boolean lookup(BinTreenode<K,T> node, K key)
	{
		if(node == null)
			return false;
		int comparison = node.getKey().compareTo(key);
		if(comparison == 0)
			return true;
		//what you are looking for is greater that what you are looking at
		if(comparison < 0)
			return lookup(node.getRight(), key);
		//what you are looking for is less than what you are looking at
		else
			return lookup(node.getLeft(), key);
	}
	
	/*
	 * returns the parent of the node with a specific key
	 * returns null if such a node does not exist
	 */
	public BinTreenode<K,T> search(K key)
	{
		return search(null, root, key);
	}
	
	//helper method for search: recursive
	private BinTreenode<K,T> search(BinTreenode<K,T> parent, BinTreenode<K,T> child, K key)
	{
		if(child == null)
			return null;
		int comparison = child.getKey().compareTo(key);
		if(comparison == 0)
			return parent;
		if(comparison < 0)
			return search(child, child.getRight(), key);
		else
			return search(child, child.getLeft(), key);
	}
	
	//only for binary search trees
	public void inOrder()
	{
		inPrint(root);
	}
	
	//helper method for inOrder
	private void inPrint(BinTreenode<K,T> node)
	{
		if(node == null)
			return;
		inPrint(node.getLeft());
		System.out.println("Key: "+node.getKey()+"\tData: "+node.getData());
		inPrint(node.getRight());
	}
	
	//cs program 4 tester method
	public Stack<BinTreenode<K,T>> stackInOrder()
	{
		Stack<BinTreenode<K,T>> treeStack = new Stack<BinTreenode<K,T>>();
		stackOrder(root, treeStack);
		return treeStack;
	}
	
	//helper method for cs program 4 tester method
	private void stackOrder(BinTreenode<K,T> node, Stack<BinTreenode<K,T>> stack)
	{
		if(node != null)
		{
			stackOrder(node.getRight(), stack);
			stack.push(node);
			stackOrder(node.getLeft(), stack);
		}
	}
		
	//post order traversal
	public void postOrder()
	{
		postPrint(root);
	}
	
	//helper method for postOrder
	private void postPrint(BinTreenode<K,T> node)
	{
		if(node == null)
			return;
		postPrint(node.getLeft());
		postPrint(node.getRight());
		System.out.println("Key: "+node.getKey()+"\tData: "+node.getData());
	}
	
	//pre order traversal
	public void preOrder()
	{
		prePrint(root);
	}
		
	//helper method for preOrder
	private void prePrint(BinTreenode<K,T> node)
	{
		if(node == null)
			return;
		System.out.println("Key: "+node.getKey()+"\tData: "+node.getData());
		prePrint(node.getLeft());
		prePrint(node.getRight());
	}
		
	//level order traversal
	public void levelOrder()
	{
		Queue<BinTreenode<K,T>> queue = new Queue<BinTreenode<K,T>>();
		if(root == null)
		{
			System.out.print("tree is empty");
			return;
		}
		queue.enqueue(root);
		while(!queue.isEmpty())
		{
			BinTreenode<K,T> node = queue.dequeue();
			System.out.println("Key: "+node.getKey()+" Data: "+node.getData());
			if(node.getLeft()!=null)
				queue.enqueue(node.getLeft());
			if(node.getRight()!=null)
				queue.enqueue(node.getRight());
		}
	}
	
	public int determineHeight()
	{
		Queue<BinTreenode<K,T>> queue = new Queue<BinTreenode<K,T>>();
		if(root == null)
			return 0;
		queue.enqueue(root);
		int height = 0;
		int nodeCount = 0;
		while(!queue.isEmpty())
		{
			BinTreenode<K,T> node = queue.dequeue();
			nodeCount++;
			if(nodeCount == Math.pow(2,height))
			{
				height++;
				nodeCount = 0;
			}
			if(node!=null)
			{
				queue.enqueue(node.getLeft());
				queue.enqueue(node.getRight());
			}
		}
		return height;
	}
	
	public void rebalance()
	{
		 K[] keys = (K[]) new Comparable[nodeCount];
	     Stack<BinTreenode<K,T>> stack  = stackInOrder();
	     int counter = 0;
	     while(!stack.isEmpty())
	     {
	    	 keys[counter] = stack.pop().getKey();
	    	 counter++;
	     }
	     root = helpRebalance(0, counter-1, keys);
	}
	
	private BinTreenode<K,T> helpRebalance(int start, int end, K[] array)
    {
    	int middle = (start+end)/2;
    	BinTreenode<K,T> node = new BinTreenode<K,T>(array[middle], null);
    	if(start < end)
    	{
    		if(start < middle)
    		{
    			node.setLeft(helpRebalance(start, middle-1, array));
    			if(node.getRight()!=null)
    			{
    				node.setHeight(1+max(node.getRight().getHeight(), node.getLeft().getHeight()));
    				node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
    			}
    			else
    			{
    				node.setHeight(1+node.getLeft().getHeight());
    				node.setBalanceFactor(node.getLeft().getHeight());
    			}
    		}
    		if(middle < end)
    		{
    			node.setRight(helpRebalance(middle+1, end, array));
    			if(node.getLeft()!=null)
    			{
    				node.setHeight(1+max(node.getRight().getHeight(), node.getLeft().getHeight()));
    				node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
    			}
    			else
    			{
    				node.setHeight(1+node.getRight().getHeight());
    				node.setBalanceFactor(node.getRight().getHeight());
    			}
    		}
    	}
    	return node;
    }   
	
	public static void main(String[] args)
	{
		BinTree<Integer,String> tree = new BinTree<Integer,String>();
		tree.insert(1,"bob");
		tree.insert(4,"bernie");
		tree.insert(0,"sam");
		tree.insert(3,"alex");
		tree.insert(-1,"dan");
		tree.insert(20,"dan");
		tree.insert(5,"dan");
		tree.insert(2,"dan");
		tree.insert(24,"dan");
		
		/*
		Stack<BinTreenode<Integer, String>> stack = tree.stackInOrder();
		int[] keys = new int[tree.getCount()];
		int counter = 0;
		while(!stack.isEmpty())
		{
			keys[counter] = stack.pop().getKey();
			counter++;
		}
		
		System.out.println("Stack in-order");
		while(!stack.isEmpty())
		{
			BinTreenode<Integer, String> node = stack.pop();
			System.out.println("Key: "+node.getKey()+" Data: "+node.getData());
		}
		*/
		System.out.println("Normal in-order");
		tree.levelOrder();
		System.out.println("Root height: "+tree.root.getHeight()+" Root balance factor: "+tree.root.getBalanceFactor());
		System.out.println(tree.root.getBalanceFactor());
		
		tree.rebalance();
		tree.levelOrder();
	}
}