package digmake;
import java.util.*;

public class Item {
	private String name;

	//Constructor
	public Item(String name) {
		this.name = name;
	}

	//Returns name of the item
	public String getName() {
		return this.name;
	}

	//returns a boolean telling whether the item should be dropped or not. Default is true
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		return true;
	}

	//returns a boolean telling if the item can be crafted or not. Default is false
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		return false;
	}
}

//All the different items that are made
class Coal extends Item {
	public Coal() {
		super("Coal");
	}

	//Only drops coal if inventory includes a pickaxe
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		for (Tool i: inventory) {
			if (i.getName().equals("Diamond Pickaxe")) {
				return true;
			}
			else if (i.getName().equals("Iron Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}	
			}		
			else if (i.getName().equals("Stone Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}
			}
		}
		return false;
	}
}

class CobblestoneItem extends Item {	
	public CobblestoneItem() {
		super("Cobblestone Item");
	}

	//Only drops coal if inventory includes a pickaxe or if the block has been hit twice
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		for (Tool i: inventory) {
			if (i.getName().equals("Diamond Pickaxe")) {
				return true;
			}
			else if (i.getName().equals("Iron Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}	
			}		
			else if (i.getName().equals("Stone Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}
			}
		}
		if (b.getTotalHits() == 2) {
			return true;
		}
		return false;
	}
}

class DiamondGem extends Item {
	public DiamondGem() {
		super("Diamond Gem");
	}

	//Only drops diamond gem if inventory includes a pickaxe
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		for (Tool i: inventory) {
			if (i.getName().equals("Diamond Pickaxe")) {
				return true;
			}
			else if (i.getName().equals("Iron Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}	
			}		
			else if (i.getName().equals("Stone Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}
			}
		}
		return false;
	}
}

class DirtItem extends Item {
	public DirtItem() {
		super("Dirt Item");
	}
}

class GoldOreItem extends Item {
	public GoldOreItem() {
		super("Gold Ore Item");
	}

	//Only drops gold ore if inventory includes a pickaxe
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		for (Tool i: inventory) {
			if (i.getName().equals("Diamond Pickaxe")) {
				return true;
			}
			else if (i.getName().equals("Iron Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}	
			}		
			else if (i.getName().equals("Stone Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}
			}
		}
		return false;
	}
}

class IronOreItem extends Item {
	public IronOreItem() {
		super("Iron Ore Item");
	}

	//Only drops iron ore if inventory includes a pickaxe
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		for (Tool i: inventory) {
			if (i.getName().equals("Diamond Pickaxe")) {
				return true;
			}
			else if (i.getName().equals("Iron Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}	
			}		
			else if (i.getName().equals("Stone Pickaxe")) {
				if (i.getLimit() > 0) {
					i.setLimit();
					return true;
				}
			}
		}
		return false;
	}
}

class Stick extends Item {
	public Stick() {
		super("Stick");
	}

	//Creates random number from 0-99 and if it is from 0-4, drops the stick 
	public boolean dropOrNot(ArrayList<Tool> inventory, Block b) {
		Random r = new Random();
		int dropRate = r.nextInt(100);

		if (dropRate < 5) {
			return true;
		}
		return false;
	}

	//Checks if the inventory of items has a wooden plank. If so, it returns true. 
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {
		//Checks if there is anything in the arraylist
		ArrayList<Item> woodenPlankItem = inventoryI.get("Wooden Plank Item");
		if (woodenPlankItem != null) {
			return true;
		}
		return false;
	}
}

class WoodenPlankItem extends Item {
	public WoodenPlankItem() {
		super("Wooden Plank Item");
	}
}

//Items that can be crafted: 
class GoldIngot extends Item {
	public GoldIngot() {
		super("Gold Ingot");
	}

	//Checks if inventory of tools includes a furnace and if the inventory of items includes coal and gold ore
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {

		//Checks presence of furnace in inventory of tools 
		boolean furnaceOrNot = false; 
		for (Tool t: inventoryT) {
			if (t.getName().equals("Furnace")) {
				furnaceOrNot = true;
			}
		}

		//Checks presence of gold ore item and coal in inventory of items
		ArrayList<Item> goldOreItem = inventoryI.get("Gold Ore Item");
		ArrayList<Item> coal = inventoryI.get("Coal");

		if (furnaceOrNot == true) {
			if (goldOreItem.size() > 0 && coal.size() > 0) {
				return true;
			}
		}
		return false;
	}
}

class IronIngot extends Item {
	public IronIngot() {
		super("Iron Ingot");
	}

	//Checks if inventory of tools includes a furnace and if the inventory of items includes coal and iron ore
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {

		//Checks presence of furnace in inventory of tools 
		boolean furnaceOrNot = false; 
		for (Tool t: inventoryT) {
			if (t.getName().equals("Furnace")) {
				furnaceOrNot = true;
			}
		}

		//Checks presence of iron ore item and coal in inventory of items
		ArrayList<Item> ironOreItem = inventoryI.get("Iron Ore Item");
		ArrayList<Item> coal = inventoryI.get("Coal");

		if (furnaceOrNot == true) {
			if (ironOreItem.size() > 0 && coal.size() > 0) {
				return true;
			}
		}
		return false;
	}
}

class GoldBlock extends Item {
	public GoldBlock() {
		super("Gold Block");
	}

	//Checks if inventory has furnace and 9 gold ingots
	public boolean craftOrNot(ArrayList<Tool> inventoryT, HashMap<String, ArrayList<Item>> inventoryI) {

		//Checks presence of furnace in inventory of tools 
		boolean furnaceOrNot = false; 
		for (Tool t: inventoryT) {
			if (t.getName().equals("Furnace")) {
				furnaceOrNot = true;
			}
		}

		//Checks presence of gold ingots
		ArrayList<Item> goldIngot = inventoryI.get("Gold Ingot");
		if (furnaceOrNot == true && goldIngot.size() > 0) {
			if (goldIngot.size() >= 9) {
				return true;
			}
		}
		return false;
	}
}
