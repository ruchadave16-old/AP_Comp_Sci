import java.util.*;
import java.io.*;
import java.lang.*;

public class TypingTest {

	public List<String> getWords(ArrayList<String> allWords) {
		//This method takes an arraylist with all the words from the txt file and chooses 10 of them by randomly shuffling the list and then returning an arraylist of the first 10 words
		List<String> randomWords = new ArrayList<String>();
		Collections.shuffle(allWords);
		randomWords = allWords.subList(0, 10);
		return randomWords;
	}

	public int getIdealTime(List<String> randomWords) {
		//This method takes in the 10 random words and find the ideal typing time they should take by finding the total number of characters and adding 10 seconds
		int totalCharacters = 0;
		for (String word : randomWords) {
			totalCharacters += word.length();
		}
		int idealTimeSeconds = totalCharacters + 10;
		return idealTimeSeconds;
	}

	public ArrayList<Integer> secToMin(int seconds) {
		//This method converts the time from seconds to minutes and seconds
		ArrayList<Integer> minAndSec = new ArrayList<Integer>();
		if (seconds < 60) {
			minAndSec.add(0);
			minAndSec.add(seconds);
		}
		else {
			minAndSec.add(seconds/60);
			minAndSec.add(seconds%60);
		}
		return minAndSec;
	}

	public long testingTime(List<String> randomWords) {
		//Creates stopwatch and starts timer
		long start = System.nanoTime();

		//Goes through the list of words, prints them, and checks the scanner input
		Scanner sc = new Scanner(System.in);
		for (String word : randomWords) {
			String answer = "";
			while (!answer.equals(word)) {
				System.out.println(word);
				answer = sc.nextLine();
			}
		}
				
		//Stops the timer and converts nanoseconds to seconds
		long stop = System.nanoTime();
		long finalTime = (stop-start)/1000000000;
		return finalTime;
	}
	
	public static void main(String[] args) throws Exception {
		//Uses methods above to run a typing test. 
		TypingTest obj = new TypingTest();
		Scanner sc = new Scanner(System.in);

		//Menu to decide what to do
		String action = "";
		while (!action.equals("end")) {
			System.out.println("Enter 'y' if you want to take the typing test and 'end' if you want to exit");
			action = sc.nextLine();

			//Starts test if the person inputs y
			if (action.equals("y")) {
				System.out.println("Welcome to the typing test. You will see 10 words on the screen. Please type the word exactly as shown after it appears on the screen. If the spelling is wrong, you will be asked to retry. After successfully completing all 10 words, you will be told the target time and what your time was. Good luck!");
				Thread.sleep(1000);

				//Creates ArrayList with all words from txt file
				File file = new File("words.txt");
				Scanner s = new Scanner(file);
				ArrayList<String> allWords = new ArrayList<String>();

				while (s.hasNextLine()) {
					String w = s.nextLine();
					allWords.add(w);
				}

				//Uses getWords to create list of 10 random words
				List<String> randomWords = obj.getWords(allWords);

				//Uses getIdealTime to find the maximum time the test should take, and then converts the time from seconds to minutes and seconds
				int idealTimeSeconds = obj.getIdealTime(randomWords);
				ArrayList<Integer> idealTimeMinutes = obj.secToMin(idealTimeSeconds);

				System.out.println("Ready...");
				Thread.sleep(2000);
				System.out.println("Set...");
				Thread.sleep(2000);
				System.out.println("GO!");

				//Uses testingTime to run the typing test
				int myTime = (int)obj.testingTime(randomWords);
				ArrayList<Integer> actualTimeMinutes= obj.secToMin((int)myTime);

				System.out.println("The ideal time for this test was " + idealTimeMinutes.get(0) + " minutes and " + idealTimeMinutes.get(1) + " seconds.");
				System.out.println("You took " + actualTimeMinutes.get(0) + " minutes and " + actualTimeMinutes.get(1) + " seconds.");
			
				//Returns message for winning or losing
				if (idealTimeSeconds < myTime) {
					System.out.println("Sorry! You weren't able to pass the typing test.");
				}
				else {
					System.out.println("Congrats! You passed the test.");
				}
			}
			
		}

	}
}