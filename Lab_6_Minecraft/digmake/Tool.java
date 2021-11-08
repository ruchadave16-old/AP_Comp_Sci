package digmake;
import java.util.*;

public class Tool {
	private String name;

	//Constructor
	public Tool(String n) {
		this.name = n;
	}

	//Accessor for name
	public String getName() {
		return this.name;
	}

	//Methods that get and set the limit for use
	public int getLimit() {
		return 100;
	}

	public void setLimit() {

	}

	//returns a boolean telling if the item can be crafted or not. Default is false
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		return false;
	}
}

// All the different tools that are made 
class Furnace extends Tool {
	public Furnace() {
		super("Furnace");
	}

	//Allows crafting if 8 or more cobblestone item present
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		ArrayList<Item> totalCobblestoneItem = inventoryI.get("Cobblestone Item");
		if (totalCobblestoneItem != null && totalCobblestoneItem.size() >= 8) {
			return true;
		}
		return false;
	}
}

class StonePickaxe extends Tool {
	int limit = 20;

	public StonePickaxe() {
		super("Stone Pickaxe");
	}

	public int getLimit() {
		return this.limit;
	}

	public void setLimit() {
		limit -= 1;
	}

	//Allows crafting if 3 or more cobblestone item and 2 or more stick present
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		ArrayList<Item> totalCobblestoneItem = inventoryI.get("Cobblestone Item");
		ArrayList<Item> stick = inventoryI.get("Stick");

		if (totalCobblestoneItem != null && totalCobblestoneItem.size() >= 3) {
			if (stick != null && stick.size() >= 2) {
				return true;
			}
		}
		return false;
	}
}

class IronPickaxe extends Tool {
	int limit = 60;

	public IronPickaxe() {
		super("Iron Pickaxe");
	}

	public int getLimit() {
		return this.limit;
	}

	public void setLimit() {
		limit -= 1;
	}
	
	//Allows crafting if 3 or more iron ingot and 2 or more stick present
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		ArrayList<Item> ironOre = inventoryI.get("Iron Ore Item");
		ArrayList<Item> stick = inventoryI.get("Stick");

		if (ironOre != null && ironOre.size() >= 3) {
			if (stick != null && stick.size() >= 2) {
				return true;
			}
		}
		return false;
	}
}

class DiamondPickaxe extends Tool {
	public DiamondPickaxe() {
		super("Diamond Pickaxe");
	}

	//Allows crafting if 3 or more diamond gem and 2 or more stick present
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		ArrayList<Item> diamondGem = inventoryI.get("Diamond Gem");
		ArrayList<Item> stick = inventoryI.get("Stick");

		if (diamondGem != null && diamondGem.size() >= 3) {
			if (stick != null && stick.size() >= 2) {
				return true;
			}
		}
		return false;
	}
}