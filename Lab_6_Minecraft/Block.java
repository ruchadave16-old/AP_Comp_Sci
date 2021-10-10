package digmake;

public class Block {
	private String name;
	private String elementName;
	private int totalHits = 0;

	//Constructors: with and without inputs
	public Block() {
		this.name = "Air";
		this.elementName = "AI";
	}

	public Block(String n, String en) {
		this.name = n;
		this.elementName = en;
	}

	//Accessor for the element name of the block
	public String getElementName() {
		return this.elementName;
	}

	//Method to drop an item
	public Item drop() {
		return null;
	}

	//Returns the total times the block has been hit
	public int getTotalHits() {
		return this.totalHits;
	}

	//Adds a hit to the block 
	public void addHit() {
		this.totalHits += 1;
	}
}

//All the different blocks that should be made. All of these have a drop method that returns the item that they drop
class Air extends Block {
	public Air() {
		super("Air", "AI");
	}

	public Item drop() {
		return null;
	}
}

class CoalOre extends Block {
	public CoalOre() {
		super("Coal Ore", "CO");
	}

	public Item drop() {
		return new Coal();
	}
}

class Cobblestone extends Block {
	public Cobblestone() {
		super("Cobblestone", "CB");
	}

	public Item drop() {
		return new CobblestoneItem();
	}
}

class DiamondOre extends Block {
	public DiamondOre() {
		super("Diamond Ore", "DO");
	}

	public Item drop() {
		return new DiamondGem();
	}
}

class Dirt extends Block {
	public Dirt() {
		super("Dirt", "DI");
	}

	public Item drop() {
		return new DirtItem();
	}
}

class GoldOre extends Block {
	public GoldOre() {
		super("Gold Ore", "GO");
	}

	public Item drop() {
		return new GoldOreItem();
	}
}

class IronOre extends Block {
	public IronOre() {
		super("Iron Ore", "IO");
	}

	public Item drop() {
		return new IronOreItem();
	}
}

class Leaves extends Block {
	public Leaves() {
		super("Leaves", "LE");
	}

	public Item drop() {
		return new Stick();
	}
}

class Stone extends Block {
	public Stone() {
		super("Stone", "ST");
	}

	public Item drop() {
		return new CobblestoneItem();
	}
}

class WoodenPlank extends Block {
	public WoodenPlank() {
		super("WoodenPlank", "WP");
	}

	public Item drop() {
		return new WoodenPlankItem();
	}
}
