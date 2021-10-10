package MyPackage;

public class Account {
	public String username;
	public String password; 
	public int id;

	//Constructor
	public Account(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	//If the username and password entered equal the account that is trying to be logged into, it returns true (logs in)
	public boolean login(String uname, String pword) {
		if (uname.equals(username) && pword.equals(password)) {
			return true;
		}
		return false;
	}

	//Checks to make sure that the password entered isn't null. Then, if it is different than the current password and is 7 or longer, sets password for the account to the new one
	public boolean changePassword(String currPassword, String newPassword) {
		if (currPassword.equals(password)) {
			if (newPassword != null) {
				if (!newPassword.equals(password) && newPassword.length() > 6) {
					this.password = newPassword;
					return true;
				}
				else if (newPassword.length() <= 6) {
					System.out.println("Password is too short");
				}
			}
		}
		else {
			System.out.println("Wrong password");
			return false;
		}
		return false;
	}
}


