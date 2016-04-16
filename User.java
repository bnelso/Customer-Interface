import java.util.List;
public class User {
	String name;
	String email;
	String phone;
	String address;
	List<String> previousAddresses;
	PaymentInfo card;
	//Constructor
	public User(String name,String email,String phone,String address){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		previousAddresses.add(address);
	}
	public void changeName(String changedName){
		name = changedName;
	}
	public void changeAddress(String newAddress){
		address = newAddress;
	}
	public void setPayment(PaymentInfo card){
		this.card = card;
	}
}
