package MyPackage;

public class Bank extends Account {

	public double balance = 0;
	public double minToKeep = 0;

	//Constructor
	public Bank(String username, String password, int id) {
		super(username, password, id);
	}

	//Adds amount to your balance
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("You deposited " + amount + " and your balance is now " + balance);
			return true;
		}
		else {
			System.out.println("The amount you are depositing is negative. Please enter a positive amount or use the withdraw function");
		}
		return false;
	}

	//Takes away amount from your balance if it doesn't put you under negatives
	public boolean withdraw(double amount) {
		if (balance - amount < minToKeep) {
			System.out.println("This transaction could not go through because you don't have enough money in your account");
			return false;
		}
		else {
			balance -= amount;
			System.out.println("You withdrew " + amount + " and now have a balance of " + balance);
		}
		return true;
	}

	//Makes fixed deposit to add to the limit that you can't withdraw
	public boolean makeFixedDeposit(double amount) {
		if (amount < balance) {
			minToKeep += amount;
			System.out.println("You now will be required to keep " + minToKeep + " in your account");
			return true;
		}
		else {
			System.out.println("You don't have this amount in your balance");
		}
		return false;
	}

}