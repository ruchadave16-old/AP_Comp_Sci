package MyPackage;


public class Animal {

	//Variables
	private String species; //species of the animal
	private String category; //mammal, fish, bird, amphibian, reptile
	private String food; //herbivore, omnivore, carnivore
	private String name; //name of animal

	private String zone; //zone animal is in


	//Mutator
	public void setSpecies(String species) {
		this.species = species;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}

	//Accessor
	public String getSpecies() {
		return this.species;
	}
	public String getCategory() {
		return this.category;
	}
	public String getFood() {
		return this.food;
	}
	public String getName() {
		return this.name;
	}
	public String getZone() {
		return this.zone;
	}

	//Write all data of animal to a string:
	public String animalData() {
		String data = "Animal " + this.name + "(" + this.species + ", " + this.category + ", " + this.food + ")";
		return data;
	}
}