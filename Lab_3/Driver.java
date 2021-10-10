/import MyPackage.*;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.*;

public class Driver {

	public static void main(String args[]) throws IOException, ClassNotFoundException {

		Scanner sc = new Scanner(System.in);

		//Menu to decide action
		String action = "";
		while (!action.equals("exit")) {

			System.out.println("Hello! Welcome to the zoo. Please enter one of the following options to continue.");
			System.out.println("To create a new zoo, enter 'new'. To open a saved version of the old zoo, enter 'saved'. To exit this game, enter 'exit'.");
			action = sc.nextLine();

			Zoo zoo = new Zoo();

			//Creates new zoo if 'new'
			if (action.equals("new")) {
				//Set name of zoo
				String name = " ";
				while (name.indexOf(" ") != -1) {
					System.out.println("Enter the name of the zoo. Please don't include any spaces");
					name = sc.nextLine();
				}
				zoo.setName(name);
			}

			//Open a saved instance of a zoo
			if (action.equals("saved")) {

				String fileName = " ";
				while (fileName.indexOf(".txt") == -1) {
					System.out.println("Enter the name of the file with the .txt that you wish to open");

					//Takes an input in the format of file.txt and reads the information into a string. 
					fileName = sc.nextLine();
				}
				Path filePath = Path.of(fileName);

				String allData = Files.readString(filePath);

				//Creates list where each string represents an entry of zoneData.
				String[] allZones = allData.split("]");
				for (String s: allZones) {
					zoo.readZoneData(s);
				}

			}

			if (!action.equals("exit")) {
				String choice = "";
				while (!choice.equals("leave")) {

					System.out.println("To add an animal to the zoo, enter 'add'. To view the current data of your zoo, enter 'data'. To save the current version of your zoo, enter 'save'. To leave this menu to either create a new zoo, open another zoo, or exit the game, enter 'leave'.");
					choice = sc.nextLine();

					//Adds animal to zoo if 'add'
					if (choice.equals("add")) {
						Animal a = zoo.createAnimal();
						zoo.animalToZone(a);
					}

					//Shows current data about zoo if 'data'
					if (choice.equals("data")) {		
						System.out.println("Here is all of your data for this zoo:");
						zoo.zooData();

					}

					//Saves current version of zoo if 'save'
					if (choice.equals("save")) {
						String data = zoo.zooData();
						System.out.println(data);

						//Write data to file
						File file = new File(zoo.getName() + ".txt");
						FileWriter fw = new FileWriter(file);
						fw.write(data);
						fw.flush();
						fw.close();

						System.out.println("Your file was saved as: " + file.getName());

					}
				}
			}

		}

	}

}