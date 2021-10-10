import java.util.Scanner;
public class Countdown {
	//Input of time Assumes that the time entered is in mm:ss format. If just a number is entered, assumes this is the number of seconds
	//Assumes each attempt takes 1 minute to complete
	public int getMinute(String time) {
		int colon = time.indexOf(":");
		if (colon != -1) {
			String minute = time.substring(0, colon);
			return Integer.parseInt(minute);
		} else {
			return 0;
		}
	}
	public int getSecond(String time) {
		int colon = time.indexOf(":");
		if (colon != -1) {
			String second = time.substring(colon+1);
			return Integer.parseInt(second);
		} else {
			return Integer.parseInt(time);
		}
	}	
	public String startCountdown(String time) {
		System.out.println("Starting countdown. Enter password to stop takeoff.");
		System.out.println(time + " until time's up.");

		int minute = getMinute(time);
		int second = getSecond(time);
		int curr_minute = minute;
		Scanner sc = new Scanner(System.in);

		while (curr_minute > 0) {
			System.out.println("Enter password");
			String password = sc.nextLine();
			if (password.equals("password")) {
				return "You saved the world!";
			} else {
				curr_minute = curr_minute - 1;
				System.out.println(curr_minute + ":" + second + " until time's up.");
			}
		} 
		if (second != 0) {
			System.out.println("Enter password");
			String password = sc.nextLine();
			if (password.equals("password")) {
				return "You saved the world!";
			}
		} 
		return "Sorry! You couldn't save the world!";
	}
	public static void main(String[] args) {
		Countdown obj = new Countdown();
		String time_entered = "4:30";
		System.out.println(obj.startCountdown(time_entered));
	}
}