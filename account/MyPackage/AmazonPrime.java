package MyPackage;
import java.util.*;

public class AmazonPrime extends Account {

	public ArrayList<String> shoppingList = new ArrayList<String>();
	public ArrayList<Double> priceList = new ArrayList<Double>();
	public int expirationTime = 12; //Assuming this initially is a 1 yr membership
	
	//Constructor 
	public AmazonPrime(String username, String password, int id) {
		super(username, password, id);
	}

	//Adds however many of a certain item that you want to the shopping cart; Adds the price to the price ArrayList
	public void addToCart(String object, double cost, int quantity) {
		for (int i = 0; i < quantity; i++) {
			shoppingList.add(object);
			priceList.add(cost);
		}
		System.out.println("Your list is now: " + shoppingList);
	}

	//extends your prime account by the amount of months you ask and gives you the cost (monthly fee = 13)
	public void extendAccount(int months) {
		expirationTime += months;
		int cost = months * 13;
		System.out.println("You have " + expirationTime + " months left. This cost you " + cost + " dollars");
	}

	//buys everything in your cart and clears the cart
	public void buy() {
		double total = 0;
		for (double x: priceList) {
			total += x;
		}
		System.out.println("Your list of items is: " + shoppingList);
		System.out.println("The total cost for these items is " + total + " dollars.");
		shoppingList = new ArrayList<String>();
		priceList = new ArrayList<Double>();
	}
}
