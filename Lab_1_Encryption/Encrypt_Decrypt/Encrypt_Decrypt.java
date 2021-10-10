import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Encrypt_Decrypt {

	public int get_key() { 
		//This method creates a random double, multiplies it by 100, and returns the int part  as the key for encryption
		double x = Math.random();
		int key = (int)(x * 12);
		if (key == 0 || key == 1) {
			x = Math.random();
			key = (int)(x * 10);
		}
		return key;
	}

	public ArrayList<Integer> string_to_acsii(String entry) {
		//This method uses the scanner to get a string and converts it to an acsii arrayList

		//Casts string characters to int by creating a character array and looping through it, converting characters to integers. Result is an integer arraylist with acsii values
		char arr[] = entry.toCharArray();
		ArrayList<Integer> acsii_values = new ArrayList<Integer>(); 
		for (char c : arr) {
			int acsii = (int) c;
			acsii_values.add(acsii);
		}
		return acsii_values;
	}

	public String encrypt_acsii(ArrayList<Integer> acsii, int key) {
		//This method takes an ArrayList of acsii values and encrypts it based on the key provided
		//For each acsii value, the equation is applied to encrypt it and the new values are added to the ArrayList. 
		//Because  only values 32-126 that can be printed, this first subtracts 32 from each value, essentially erasing the first 31 values from the table, ensuring that later when we use mod to loop them back around, the encrypted string can be printed
		//Equation is key^2 - key + 14
		//Answer mod 94 is taken to make sure any values outside of the 94 printable ones are looped around to the first few values
		//32 is added again to get the real acsii values
		String encrypted_string = "";
		for (int n : acsii) {
			int encrypted_value = (((n - 32) + (key * key) - key) % 94) + 32;
			char c = (char) encrypted_value;
			encrypted_string += c;
		} 
		return encrypted_string;
	}

	public String decrypt_acsii(ArrayList<Integer> acsii, int key) {
		//This method takes in an ArrayList of acsii values and decrypts it based on the key and the formula from the encrypt_acsii value
		String decrypted_string = "";
		for (int n : acsii) {
			int x = (n - 32) - (key * key) + key + 32;
			if (x < 32) {
				x = x + 94;
			}
			char c = (char) x;
			decrypted_string += c;
		}
		return decrypted_string;
	}

	public String encrypt() {
		//This method uses the scanner to get a string and encrypts it

		//Asks user for the message that is to be encrypted and uses get_key() to create the encryption key
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the message you want to encrypt");
		String orig_message = sc.nextLine();
		int key = get_key();
		System.out.println("Got key");

		//Uses string_to_acsii to convert string to acsii, and encrypt_acsii to encrypt the acsii
		ArrayList<Integer> acsii_values = string_to_acsii(orig_message);
		String encrypted = encrypt_acsii(acsii_values, key);
		ArrayList<Integer> encr_acsii_values = string_to_acsii(encrypted);

		System.out.println("Your original message was '" + orig_message + "', your encrypted message is '" + encrypted +  "', and your key is '" + key + "'.");
		System.out.println("Original acsii: " + acsii_values + "; Encrypted acsii: " + encr_acsii_values);
		return encrypted;
	}

	public String decrypt() {
		//This method uses the scanner to get an encrypted string and a key and decrypts it

		//Asks user for encrypted message and key
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the message you want to decrypt");
		String encrypted_message = sc.nextLine();
		System.out.println("Enter the key used to encrypt this");
		int key = Integer.parseInt(sc.nextLine());

		//If the key is out of range, asks you to continue trying again
		while (key < -1 || key > 13) {
			System.out.println("Wrong key. Please try again");
			key = Integer.parseInt(sc.nextLine());
		}

		//Convert encrypted message to acsii
		ArrayList<Integer> acsii_encrypted = string_to_acsii(encrypted_message);
		String decrypted_string = decrypt_acsii(acsii_encrypted, key);
		ArrayList<Integer> acsii_decrypted = string_to_acsii(decrypted_string);

		System.out.println("Your encrypted message was '" + encrypted_message +  "' and your message is '" + decrypted_string + "'.");
		System.out.println("Decrypted acsii: " + acsii_decrypted);
		return decrypted_string;
	}

	public static void main(String[] args) {
		Encrypt_Decrypt obj = new Encrypt_Decrypt();
		Scanner sc = new Scanner(System.in);

		//Asks user if they want to encrypt, decrypt, or exit the program
		String action = "";
		while (!action.equals("end")) {
			System.out.println("Enter 'e' if you would like to encrypt a message and 'd' if you would like to decrypt a message. Enter 'end' if you want to stop the program");
			action = sc.nextLine();
			if (action.equals("e")) {
				obj.encrypt();
			}
			if (action.equals("d")) {
				obj.decrypt();
			}
			
		}
		
	}

}

