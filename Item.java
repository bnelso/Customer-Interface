import java.util.ArrayList;


public class Item implements Comparable{
	
	//Data fields
	String name;
	double price;
	String descript;
	ArrayList<String> keywords;
	ArrayList<String> reviews;
	
	//Constructor
	public Item(String name, double price, String descript, ArrayList<String> keywords) {
		this.name = name;
		this.descript = descript;
		this.keywords = keywords;
	}
	
	//Sets Item name
	public void setName(String n) {
		name = n;
	}
	
	//Returns Item name
	public String getName() {
		return name;
	}
	
	//Sets Item price
	public void setPrice(double p) {
		price = p;
	}
	
	//Returns Item price
	public double getPrice() {
		return price;
	}
	
	//Sets Item description
	public void setDescription(String d) {
		descript = d;
	}
	
	//Returns Item description
	public String getDescription() {
		return descript;
	}
	
	//Adds a keyword to the Item's keyword list
	public void addKeyword(String k) {
		keywords.add(k);
	}
	
	//Returns the Item's keyword list
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	//Adds a Review object to the Item's review list
	public void addReview(String r) {
		reviews.add(r);
	}

	public ArrayList<String> getReviews() {
		return reviews;
	}
	
	//For sorting Items by name
	public int compareTo(Item i) {
		//TODO
		String myName 
		for i
		return ;
	}
}
