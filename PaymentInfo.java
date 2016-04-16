
public class PaymentInfo {
	int cardNumber;
	int backNumbers;
	double expiration;
	String name;
	public PaymentInfo(int cardNumber, int backNumbers, double expiration, String name){
		this.cardNumber = cardNumber;
		this.backNumbers = backNumbers;
		this.expiration = expiration;
		this.name = name;
	}
}
