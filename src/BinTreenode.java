public class BinTreenode<K,T>
{
	private K key;
	private T data;
	private BinTreenode<K,T> left;
	private BinTreenode<K,T> right;
	private int height;
	private int balanceFactor;
	
	public BinTreenode(K key, T data)
	{
		this.key = key;
		this.data = data;
		left = null;
		right = null;
		height = 1;
		balanceFactor = 0;
	}
	
	public K getKey()
	{
		return key;
	}
	
	public BinTreenode<K,T> getLeft()
	{
		return left;
	}
	
	public BinTreenode<K,T> getRight()
	{
		return right;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getBalanceFactor()
	{
		return balanceFactor;
	}
	
	public T getData()
	{
		return data;
	}
	
	public void setKey(K key)
	{
		this.key = key;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	public void setLeft(BinTreenode<K,T> left)
	{
		this.left = left;
	}
	
	public void setRight(BinTreenode<K,T> right)
	{
		this.right = right;
	}
	
	public void setHeight(int h)
	{
		height = h;
	}
	
	public void setBalanceFactor(int bf)
	{
		balanceFactor = bf;
	}
}