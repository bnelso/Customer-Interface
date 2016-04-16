import java.util.ArrayList;


public class ShoppingCart 
{
	private ArrayList<Item> itemList;
	private double totalFees;
	
	public ShoppingCart()
	{}
	
	
	//adds item to shopping cart
	//increases total fees due by item's price
	public void addItem(Item item)
	{
	  itemList.add(item);
	  //update total fees by adding on the price of the item
	}
	
	//removes item from shopping cart
	//decreases total fees due by removed item's price
	public void removeItem()
	{
	  //goes through shopping cart and removes specific item being searched for
	  //update total fees by subtrating from the price of the item
	}
	
	//necessary?
	public void setFees(double fees)
	{
	  totalFees = fees;
	}
}
