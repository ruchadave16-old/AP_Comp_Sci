package MyPackage;

import MyPackage.*; 
import java.util.*;


public class Zone {
	//Variables
	private String name;
	private int totalAnimalNumber = 0; //number of animals in the zone
	private Animal[] allAnimals = new Animal[0]; //All animals in the zone

	//Constructor
	Zone() {

	}
	Zone(String name) {
		this.name = name;
	}
	
	//Mutator
	public void setName(String name) {
		this.name = name;
	}
	public void setTotalAnimalNumber(int x) {
		this.totalAnimalNumber = x;
	}
	public void setAllAnimals(Animal[] allAnimals) {
		this.allAnimals = allAnimals;
	}

	//Accessor
	public String getName() {
		return this.name;
	}
	public int getTotalAnimalNumber() {
		return this.totalAnimalNumber;
	}
	public Animal[] getAllAnimals() {
		return this.allAnimals;
	}

	public void animalSort(Animal[] animals) {
		//Sorts all the animals in a zone alphabetically

		//Sorting where for each animal in the zone, we look at all the animals before it. If any are greater, then the "greater" elements move up one spot and the "lower" one goes to the end where the first greater one's index was
		for (int i = 0; i < animals.length; i++) {
			Animal a = animals[i];
			String aName = a.getName();
			int j = i - 1;
			while ((j >= 0) && (aName.compareTo(animals[j].getName()) < 0)) {
				animals[j+1] = animals[j];
				j = j - 1;
			}
			animals[j + 1] =  a;
		}
	}

	//Writes zone data to a string
	public String zoneData() {
		String data = "Zone " + this.name + ":";
		for (Animal a : allAnimals) {
			data += a.animalData();
			data += ";";
		}
		return data;
	}

}