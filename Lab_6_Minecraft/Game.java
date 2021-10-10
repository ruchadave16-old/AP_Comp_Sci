package digmake;
import java.util.*;

public class Game {
	private Block[][] allBlocks = new Block[5][5];	
	private HashMap<String, ArrayList<Item>> inventoryItem = new HashMap<String, ArrayList<Item>>();
	private ArrayList<Tool> inventoryTool = new ArrayList<Tool>();
	private Random r = new Random();

	//Constructor
	public Game() {
		buildArray();
	}

	//Adds the blocks to the block Array based on probabilities by randoomly creating an int and checking the range
	public void buildArray() {
		System.out.println();
		for (int i = 0; i < 5; i++) { //rows
			for (int j = 0; j < 5; j++) { //columns
				int spawnOn = r.nextInt(100); //Random int from 0 - 99
				if (spawnOn < 10) {
					allBlocks[i][j] = new Air();
				}
				else if (spawnOn < 20) {
					allBlocks[i][j] = new CoalOre();
				}
				else if (spawnOn < 30) {
					allBlocks[i][j] = new Stone();
				}
				else if (spawnOn < 40) {
					allBlocks[i][j] = new Cobblestone();
				}
				else if (spawnOn < 60) {
					allBlocks[i][j] = new Dirt();
				}
				else if (spawnOn < 70) {
					allBlocks[i][j] = new Leaves();
				}
				else if (spawnOn < 80) {
					allBlocks[i][j] = new WoodenPlank();
				}
				else if (spawnOn < 90) {
					allBlocks[i][j] = new IronOre();
				}
				else if (spawnOn < 95) {
					allBlocks[i][j] = new GoldOre();
				}
				else if (spawnOn < 100) {
					allBlocks[i][j] = new DiamondOre();
				}
			}
		}
	}

	//Goes through and prints out the block, also formats the array with the row and column names
	public void printArray() {
		boolean printLetter = true;
		char letter = 'A';
		System.out.println();
		System.out.print("  1  2  3  4  5");

		for (int i = 0; i < 5; i++) {
			System.out.println("");
			printLetter = true;

			for (int j = 0; j < 5; j++) {
				if (printLetter) {
					System.out.print(letter);
					letter++;
					printLetter = false;
				}

				System.out.print(" " + allBlocks[i][j].getElementName());
			}
		}
		System.out.println();
		System.out.println();
	}

	//Checks if gold bar has been made and the game has been won
	public boolean checkWin() {
		for (ArrayList<Item> entry: inventoryItem.values()) {
			for (Item i: entry) {
				if (i.getName().equals("Gold Block")) {
					return true;
				}
			}
		}
		return false;
	}

	//Mines the block by replacing it with an Air block
	//For the item that is dropped, checks if it results in something by using dropOrNot method. 
	//If something is dropped, adds it to the inventory
	public void mine(int y, int x) {
		inventoryTool = checkToolUsage(inventoryTool);

		Block b = allBlocks[y][x];
		Item droppedItem = b.drop();

		if (droppedItem != null) {
			//Adds a hit to the count 
			b.addHit();
			boolean addDrop = droppedItem.dropOrNot(inventoryTool, b);

			//Adds the object to inventory and turns block to air if addDrop is true
			if (addDrop == true) {
				ArrayList<Item> currList;
				if (inventoryItem.containsKey(droppedItem.getName())) {
					currList = inventoryItem.get(droppedItem.getName());
				}

				else {
					currList = new ArrayList<Item>();
				}

				currList.add(droppedItem);
				inventoryItem.put(droppedItem.getName(), currList);
				allBlocks[y][x] = new Air();
			}

			//Because Cobblestone and Stone require 2 hits without a pickaxe, it turns the block to air when the drop is false if it isn't either of them. 
			//This is because for cobblestone and and stone, once 2 hits happens, it automatically returns true for addDrop
			else {
				String elName = b.getElementName();
				if (!elName.equals("CB") && !elName.equals("ST")) {
					allBlocks[y][x] = new Air();
				}
			}
			printArray();
		}
	}

	//Prints out the item names in the inventory
	public void printInventory() {
		System.out.println();
		System.out.println("Inventory:");
		for (HashMap.Entry<String, ArrayList<Item>> e: inventoryItem.entrySet()) {
			System.out.println(e.getKey() + "    " + e.getValue().size());
		}
		for (Tool t: inventoryTool) {
			System.out.println(t.getName());
		}
		System.out.println();
	}

	//Removes all tools that aren't 
	public ArrayList<Tool> checkToolUsage(ArrayList<Tool> tInventory) {
		ArrayList<Tool> newI = tInventory;
		for (int i = tInventory.size() - 1; i > -1; i--) {
			if (tInventory.get(i).getLimit() == 0) {
				newI.remove(i);
			}
		}
		return newI;
	}

	//Removes the materials from the inventory that were used in crafting the item
	public void removeCraftItem(Item itemCrafted) {

		System.out.println();
		//If stick was made, removes a wooden plank 
		if (itemCrafted.getName().equals("Stick")) {
			ArrayList<Item> woodenPlankList = inventoryItem.get("Wooden Plank Item");
			woodenPlankList.remove(0);
			inventoryItem.put("Wooden Plank Item", woodenPlankList);

			System.out.println("You successfully crafted a stick");
		}

		//if gold ingot was made, removes coal and gold ore item
		else if (itemCrafted.getName().equals("Gold Ingot")) {
			ArrayList<Item> coalList = inventoryItem.get("Coal");
			coalList.remove(0);
			inventoryItem.put("Coal", coalList);

			ArrayList<Item> goldOreList = inventoryItem.get("Gold Ore Item");
			goldOreList.remove(0);
			inventoryItem.put("Gold Ore Item", goldOreList);

			System.out.println("You successfully crafted a gold ingot");
		}

		//if iron ingot was made, removes coal and iron ore item
		else if (itemCrafted.getName().equals("Iron Ingot")) {
			ArrayList<Item> coalList = inventoryItem.get("Coal");
			coalList.remove(0);
			inventoryItem.put("Coal", coalList);

			ArrayList<Item> ironOreList = inventoryItem.get("Iron Ore Item");
			ironOreList.remove(0);
			inventoryItem.put("Iron Ore Item", ironOreList);

			System.out.println("You successfully crafted an iron ingot");
		}

		//if gold bar was made, removes 9 gold ingots
		else if (itemCrafted.getName().equals("Gold Block")) {
			ArrayList<Item> goldIngotList = inventoryItem.get("Gold Ingot");
			for (int i = 0; i < 9; i++) {
				goldIngotList.remove(0);
			}
			inventoryItem.put("Gold Ingot", goldIngotList);

			System.out.println("You successfully crafted a gold block");
		}
	}

	//Removes materials from invetory that were used in crafting the tool
	public void removeCraftTool(Tool toolCrafted) {
		System.out.println();
		//If furnace was made, removes 8 cobblestone item
		if (toolCrafted.getName().equals("Furnace")) {
			ArrayList<Item> cobblestoneList = inventoryItem.get("Cobblestone Item");
			for (int i = 0; i < 8; i++) {
				cobblestoneList.remove(0);
			}
			inventoryItem.put("Cobblestone Item", cobblestoneList);

			System.out.println("You successfully crafted a furnace. This tool can be used to create gold ingots, iron ingots, and gold blocks");
		}

		//if stone pickaxe was made, removes 3 cobblestone and 2 sticks
		else if (toolCrafted.getName().equals("Stone Pickaxe")) {
			ArrayList<Item> cobblestoneList = inventoryItem.get("Cobblestone Item");
			for (int i = 0; i < 3; i++) {
				cobblestoneList.remove(0);
			}
			inventoryItem.put("Cobblestone Item", cobblestoneList);

			ArrayList<Item> stickList = inventoryItem.get("Stick");
			for (int i = 0; i < 2; i++) {
				stickList.remove(0);
			}
			inventoryItem.put("Stick", stickList);

			System.out.println("You successfully crafted an stone pickaxe. This tool has 20 uses, after which it will break");
		}

		//if iron pickaxe was made, removes 3 iron ingot and 2 sticks
		else if (toolCrafted.getName().equals("Iron Pickaxe")) {
			ArrayList<Item> ironOreList = inventoryItem.get("Iron Ore Item");
			for (int i = 0; i < 3; i++) {
				ironOreList.remove(0);
			}
			inventoryItem.put("Iron Ore Item", ironOreList);

			ArrayList<Item> stickList = inventoryItem.get("Stick");
			for (int i = 0; i < 2; i++) {
				stickList.remove(0);
			}
			inventoryItem.put("Stick", stickList);

			System.out.println("You successfully crafted an iron pickaxe. This tool has 60 uses, after which it will break");
		}

		//if diamond pickaxe was made, removes 3 diamond gems and 2 sticks
		else if (toolCrafted.getName().equals("Diamond Pickaxe")) {
			ArrayList<Item> diamondGemList = inventoryItem.get("Diamond Gem");
			for (int i = 0; i < 3; i++) {
				diamondGemList.remove(0);
			}
			inventoryItem.put("Diamond Gem", diamondGemList);

			ArrayList<Item> stickList = inventoryItem.get("Stick");
			for (int i = 0; i < 2; i++) {
				stickList.remove(0);
			}
			inventoryItem.put("Stick", stickList);

			System.out.println("You successfully crafted a diamond pickaxe. This tool has unlimited uses");
		}
	}

	//Crafts an item that is requested to be made
	public void craft(String itemToCraft) {
		Item itemToBeMade = null; 
		Tool toolToBeMade = null;

		//Creates an new object that is going to be made
		if (itemToCraft.equals("Stick")) {
			itemToBeMade = new Stick();
		}
		else if (itemToCraft.equals("Gold Ingot")) {
			itemToBeMade = new GoldIngot();
		}
		else if (itemToCraft.equals("Iron Ingot")) {
			itemToBeMade = new IronIngot();
		}
		else if (itemToCraft.equals("Gold Block")) {
			itemToBeMade = new GoldBlock();
		}
		else if (itemToCraft.equals("Furnace")) {
			toolToBeMade = new Furnace();
		}
		else if (itemToCraft.equals("Stone Pickaxe")) {
			toolToBeMade = new StonePickaxe();
		}
		else if (itemToCraft.equals("Iron Pickaxe")) {
			toolToBeMade = new IronPickaxe();
		}
		else if (itemToCraft.equals("Diamond Pickaxe")) {
			toolToBeMade = new DiamondPickaxe();
		}
		else {
			System.out.println("This object cannot be crafted. Try to craft another object or make sure the first letter is capitalized.");
		}

		//If an item is being made, then it checks if craft or not is true
		if (itemToBeMade != null) {
			boolean makeItem = itemToBeMade.craftOrNot(inventoryTool, inventoryItem);

			//if the object can be crafted, then it adds the object to the hashmap 
			if (makeItem == true) {
				ArrayList<Item> currList;
				if (inventoryItem.containsKey(itemToBeMade.getName())) {
					currList = inventoryItem.get(itemToBeMade.getName());
				}

				else {
					currList = new ArrayList<Item>();
				}

				currList.add(itemToBeMade);
				inventoryItem.put(itemToBeMade.getName(), currList);
				removeCraftItem(itemToBeMade);
			}

			//if the item can't be crafted, then it prints out what the requirements for crafting are
			else {
				System.out.println();
				if (itemToBeMade.getName().equals("Stick")) {
					System.out.println("Crafting a stick requires one wooden plank");
				}
				if (itemToBeMade.getName().equals("Gold Ingot")) {
					System.out.println("Crafting a gold ingot requires one coal and one gold ore, and a furnace");
				}
				if (itemToBeMade.getName().equals("Iron Ingot")) {
					System.out.println("Crafting an iron ingot requires one coal and one iron ore, and a furnace");
				}
				if (itemToBeMade.getName().equals("Gold Block")) {
					System.out.println("Crafting a gold block requires 9 gold ingots, and a furnace");
				}
			}
		}

		//If a tool is being made, then checks if craft or not is true
		else if (toolToBeMade != null) {
			boolean makeTool = toolToBeMade.craftOrNot(inventoryTool, inventoryItem);

			//if object can be crafted, then adds to arraylist of tools
			if (makeTool == true) {
				inventoryTool.add(toolToBeMade);
				removeCraftTool(toolToBeMade);
			}

			//if tool can't be crafted, then prints out the requirements for crafting 
			else {
				if (toolToBeMade.getName().equals("Furnace")) {
					System.out.println("Crafting a furnace requires 8 cobblestone");
				}
				if (toolToBeMade.getName().equals("Stone Pickaxe")) {
					System.out.println("Crafting a stone pickaxe requires 3 cobblestone and 2 sticks");
				}
				if (toolToBeMade.getName().equals("Iron Pickaxe")) {
					System.out.println("Crafting an iron pickaxe requires 3 iron ore and 2 sticks");
				}
				if (toolToBeMade.getName().equals("Diamond Pickaxe")) {
					System.out.println("Crafting a diamond pickaxe requires 3 diamond gems and 2 sticks");
				}
			}
		}
		printInventory();
	}
}