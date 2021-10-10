package MyPackage;

import MyPackage.*; 
import java.util.*;


public class Zoo {
	//Variables
	private String name; //name of the Zoo

	//Create the 9 Zones of the zoo
	private Zone yellow = new Zone("yellow");
	private Zone green = new Zone("green");
	private Zone blue = new Zone("blue");
	private Zone red = new Zone("red");
	private Zone navy = new Zone("navy");
	private Zone gray = new Zone("gray");
	private Zone brown = new Zone("brown");
	private Zone teal = new Zone("teal");
	private Zone maroon = new Zone("maroon");

	private Zone[] allZones = {this.yellow, this.green, this.blue, this.red, this.navy, this.gray, this.brown, this.teal, this.maroon};

	//Mutator
	public void setName(String name) {
		this.name = name;
	}

	//Accessor
	public String getName() {
		return this.name;
	}
	public Zone getYellow() {
		return this.yellow;
	}
	public Zone getGreen() {
		return this.green;
	}
	public Zone getBlue() {
		return this.blue;
	}
	public Zone getRed() {
		return this.red;
	}
	public Zone getNavy() {
		return this.navy;
	}
	public Zone getGray() {
		return this.gray;
	}
	public Zone getBrown() {
		return this.brown;
	}
	public Zone getTeal() {
		return this.teal;
	}
	public Zone getMaroon() {
		return this.maroon;
	}
	public Zone[] getAllZones() {
		return this.allZones;
	}

	public void animalToZone(Animal animal) {
		Scanner sc = new Scanner(System.in);

		//Checks each zone in allZones to see if the total number > 20. All eligible zones are added to an arraylist. Then it prints out the currently open zones. 

		ArrayList<String> openZones = new ArrayList<String>();
		for (Zone z: allZones) {
			int totalAnimals = z.getTotalAnimalNumber();
			if (totalAnimals < 19) {
				openZones.add(z.getName());
			}
		}

		Zone zoneForAnimal = new Zone();

		String correct = "wrong";
		while (correct.equals("wrong")) {

			System.out.println("Here is a list of the zones available in the zoo: " + openZones + ". If there are no names here, then the zoo is currently full. Please enter which zone you wish to place the animal into.");
			String zoneChoice = sc.nextLine();

			//Gets the zone for the animal inputted

			if (zoneChoice.equals("yellow")) {
				zoneForAnimal = this.yellow;
				correct = "right";
			}
			if (zoneChoice.equals("green")) {
				zoneForAnimal = this.green;
				correct = "right";
			}
			if (zoneChoice.equals("blue")) {
				zoneForAnimal = this.blue;
				correct = "right";
			}
			if (zoneChoice.equals("red")) {
				zoneForAnimal = this.red;
				correct = "right";
			}
			if (zoneChoice.equals("navy")) {
				zoneForAnimal = this.navy;
				correct = "right";
			}
			if (zoneChoice.equals("gray")) {
				zoneForAnimal = this.gray;
				correct = "right";
			}
			if (zoneChoice.equals("brown")) {
				zoneForAnimal = this.brown;
				correct = "right";
			}
			if (zoneChoice.equals("teal")) {
				zoneForAnimal = this.teal;
				correct = "right";
			}
			if (zoneChoice.equals("maroon")) {
				zoneForAnimal = this.maroon;
				correct = "right";
			}
		}

		//increases the number of animals in zone by 1
		zoneForAnimal.setTotalAnimalNumber(zoneForAnimal.getTotalAnimalNumber() + 1);

		//sets the zone for the animal instance to what was chosen
		animal.setZone(zoneForAnimal.getName());

		//add name of the animal to list of species in the zone
		ArrayList<String> namesNow = zoneForAnimal.getAnimalSpecies();
		namesNow.add(animal.getSpecies());
		zoneForAnimal.setAnimals(namesNow);

		//Add new animal to the list for the Zone
		ArrayList<Animal> allAnimalsNow = zoneForAnimal.getAllAnimals();
		allAnimalsNow.add(animal);
		zoneForAnimal.setAllAnimals(allAnimalsNow);
	}

	public Animal createAnimal() {
		//creates an animal by taking in input from the user. This assumes that the user follows guidelines given
		Scanner sc = new Scanner(System.in);
		Animal a = new Animal();

		System.out.println("Please enter the species of the animal you want to create");
		a.setSpecies(sc.nextLine());

		System.out.println("Next, enter the category it falls into: mammal, fish, bird, amphibian, or reptile");
		a.setCategory(sc.nextLine());

		System.out.println("Enter the food the animal eats: carnivore, herbivore, or omnivore");
		a.setFood(sc.nextLine());

		System.out.println("Finally, give the animal a name!");
		a.setName(sc.nextLine());

		return a;
	}

	public String zooData() {
		//Writes zoo data to a string
		String data = "";
		for (Zone z : allZones) {
			data += "[";
			data += z.zoneData();
			data += "]";
		}
		System.out.println(data);
		return data;
	}

	public Animal readAnimalData(String animalData, String zone) {
		//Creates a new animal by reading in the string and zone it should be placed in, and sets the variables for the animal to what is inputted in the string
		Animal a = new Animal();

		String name = animalData.substring(7, animalData.indexOf("("));
		a.setName(name);
		String remainingData = animalData.substring(animalData.indexOf("(")+1);

		String species = remainingData.substring(0, remainingData.indexOf(","));
		a.setSpecies(species);
		remainingData = remainingData.substring(remainingData.indexOf(",")+1);

		String category = remainingData.substring(1, remainingData.indexOf(","));
		a.setCategory(category);
		remainingData = remainingData.substring(remainingData.indexOf(",")+1);

		String food = remainingData.substring(1, remainingData.indexOf(")"));
		a.setFood(food);

		a.setZone(zone);
		return a;
	}

	public void readZoneData(String zoneData) {
		//Finds the correct zone based on the string inputted for the zoo
		int indexOfColon = zoneData.indexOf(":");
		String zoneName = zoneData.substring(6, indexOfColon);
		String remainingData = zoneData.substring(indexOfColon+1);
		Zone zoneForData = new Zone();

		if (zoneName.equals("yellow")) {
			zoneForData = this.yellow;
		}
		if (zoneName.equals("green")) {		
			zoneForData = this.green;
		}
		if (zoneName.equals("blue")) {
			zoneForData = this.blue;
		}
		if (zoneName.equals("red")) {
			zoneForData = this.red;
		}
		if (zoneName.equals("navy")) {
			zoneForData = this.navy;
		}
		if (zoneName.equals("gray")) {
			zoneForData = this.gray;
		}
		if (zoneName.equals("brown")) {
			zoneForData = this.brown;
		}
		if (zoneName.equals("teal")) {
			zoneForData = this.teal;
		}
		if (zoneName.equals("maroon")) {
			zoneForData = this.maroon;
		}

		int indexOfSemicolon = remainingData.indexOf(";");

		//If there are any animals already in the zone, then this sets the zone variables to what has been inputted already
		if (indexOfSemicolon != -1) {

			String[] allAnimalData = remainingData.split(";");
			ArrayList<Animal> allAnimals = new ArrayList<Animal>();
			ArrayList<String> animalSpecies = new ArrayList<String>();
			int totalAnimals = 0;

			//For each animal, it reads the data from the string and adds the appropriate information to the variables above, which are then set for the zone

			for (String a: allAnimalData) {
				Animal animal = readAnimalData(a, zoneName);
				allAnimals.add(animal);
				animalSpecies.add(animal.getSpecies());
				totalAnimals += 1;
			}

			zoneForData.setTotalAnimalNumber(totalAnimals);
			zoneForData.setAnimals(animalSpecies);
			zoneForData.setAllAnimals(allAnimals);
		}
	}
}