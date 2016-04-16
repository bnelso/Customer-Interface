import java.util.List;
public class User {
	String name;
	String email;
	String phone;
	String address;
	List<String> previousAddresses;
	PaymentInfo card;
	public User(String name,String email,String phone,String address){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		previousAddresses.add(address);
	}
	public void setPayment(PaymentInfo card){
		this.card = card;
	}
}
