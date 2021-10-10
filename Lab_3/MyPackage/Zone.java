package MyPackage;

import MyPackage.*; 
import java.util.*;


public class Zone {
	//Variables
	private String name;
	private int totalAnimalNumber = 0; //number of animals in the zone
	private ArrayList<String> animalSpecies = new ArrayList<String>(); //arraylist of all the names of animals currently in this zone

	private ArrayList<Animal> allAnimals = new ArrayList<Animal>(); //All animals in the zone 

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
	public void setAnimals(ArrayList<String> animalSpecies) {
		this.animalSpecies = animalSpecies;
	}
	public void setAllAnimals(ArrayList<Animal> allAnimals) {
		this.allAnimals = allAnimals;
	}

	//Accessor
	public String getName() {
		return this.name;
	}
	public int getTotalAnimalNumber() {
		return this.totalAnimalNumber;
	}
	public ArrayList<String> getAnimalSpecies() {
		return this.animalSpecies;
	
	}
	public ArrayList<Animal> getAllAnimals() {
		return this.allAnimals;
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