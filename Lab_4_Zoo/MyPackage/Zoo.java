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

	public void zoneSort(Zone[] allZones) {
		//Sorts all the zones alphabetically

		//Loops through each zone and finds the lowest name alphabetically. It switches the position of that name and the first index that is open 
		for (int i = 0; i < allZones.length-1; i++) {
			int minIndex = i;
			for (int j = i+1; j < allZones.length; j++) {
				String name = allZones[j].getName();
				if (name.compareTo(allZones[minIndex].getName()) < 0) {
					minIndex = j;
				}
			}
			Zone temp = allZones[i];
			allZones[i] = allZones[minIndex];
			allZones[minIndex] = temp;
		}
	}
 
	public Zone zoneSearch(Zone[] allZones, String zoneName) {
		int l = 0;
		int r = allZones.length - 1;

		//Ensures that the zoneArray is sorted alphabetically
		zoneSort(allZones);

		//Goes through the binary search by looking at the middle and comparing
		Zone zone = new Zone();
		while (l <= r) {
			int mid = (l + r) / 2;
			zone = allZones[mid];

			//If the middle value == zone name entered, returns the zone
			if (zoneName.compareTo(zone.getName()) == 0) {
				return zone;
			}

			//If the zone name entered is less than the zone in the middle, it searches the lower half
			if (zoneName.compareTo(zone.getName()) < 0) {
				r = mid - 1;
			}

			//If the zone name entered is greater than the zone in the middle, it searches the upper half
			if (zoneName.compareTo(zone.getName()) > 0) {
				l = mid + 1;
			}
		}
		return zone;
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

		Zone zoneForAnimal = null;

		//Runs zoneSearch until the zone selected isn't null
		while (zoneForAnimal == null) {
			System.out.println("Here is a list of the zones available in the zoo: " + openZones + ". If there are no names here, then the zoo is currently full. Please enter which zone you wish to place the animal into.");
			String zoneChoice = sc.nextLine();
			zoneForAnimal = zoneSearch(allZones, zoneChoice);
		}

		//increases the number of animals in zone by 1
		zoneForAnimal.setTotalAnimalNumber(zoneForAnimal.getTotalAnimalNumber() + 1);

		//sets the zone for the animal instance to what was chosen
		animal.setZone(zoneForAnimal.getName());

		//Add new animal to the list for the Zone
		Animal[] allAnimalsBefore = zoneForAnimal.getAllAnimals();
		Animal[] allAnimalsNow = new Animal[allAnimalsBefore.length + 1];
		for (int x = 0; x < allAnimalsBefore.length; x++) {
			allAnimalsNow[x] = allAnimalsBefore[x];
		}
		allAnimalsNow[allAnimalsBefore.length] = animal;

		zoneForAnimal.setAllAnimals(allAnimalsNow);

		Animal[] allList = zoneForAnimal.getAllAnimals();
		zoneForAnimal.animalSort(allList);
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
		String nameEntered = sc.nextLine();
		a.setName(nameEntered.substring(0,1).toUpperCase() + nameEntered.substring(1)); //Capitalizes first letter of name so that the alphabetical sort works properly

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
		return data;
	}

	public void displayData() {
		for (Zone z: allZones) {
			System.out.println("Zone " + z.getName() + ":");
			for (Animal a: z.getAllAnimals()) {
				System.out.println(a.animalData());
			}
			System.out.println("Total animals in this zone: " + z.getTotalAnimalNumber() + '\n');
		}
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
		Zone zoneForData = zoneSearch(allZones, zoneName);
		int indexOfSemicolon = remainingData.indexOf(";");

		//If there are any animals already in the zone, then this sets the zone variables to what has been inputted already
		if (indexOfSemicolon != -1) {

			String[] allAnimalData = remainingData.split(";");
			ArrayList<Animal> allAnimals = new ArrayList<Animal>();
			int totalAnimals = 0;

			//For each animal, it reads the data from the string and adds the appropriate information to the variables above, which are then set for the zone

			for (String a: allAnimalData) {
				Animal animal = readAnimalData(a, zoneName);
				allAnimals.add(animal);
				totalAnimals += 1;
			}

			Animal[] allAnimalArray = new Animal[allAnimals.size()];
			int x = 0;
			for (Animal a: allAnimals) {
				allAnimalArray[x] = a;
				x += 1;
			}
			zoneForData.setTotalAnimalNumber(totalAnimals);
			zoneForData.setAllAnimals(allAnimalArray);
			zoneForData.animalSort(allAnimalArray);
		}
	}
}