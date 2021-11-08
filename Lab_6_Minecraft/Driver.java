import java.util.*;
import digmake.*;

import java.io.*;
import java.lang.*;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("Hello! Welcome to DigMake (totally not Minecraft). ");

		//Menu to decide action
		String action = "";
		while (!action.equals("exit")) {
			System.out.println();
			System.out.println("Please enter 'play' to begin the game. To exit this game, enter 'exit'");
			action = sc.nextLine();

			//Starts game when "play" is entered
			if (action.equals("play")) {
				Game g = new Game();

				System.out.println();
				System.out.println("To play this game, you will enter a set of coordinates (example b3) to represent a certain block of land that you wish to mine. Whatever items you gain will be added to the inventory, from which you can craft different tools and items");
				System.out.println();
				System.out.println("Certain blocks such as iron ore or coal can only be mined if a pickaxe has been crafted. Cobblestone and stone need to be struck twice if no pickaxe is in the inventory. To win the game, create one gold block.");
				System.out.println();

				//Checks if gold bar has been made or not
				while (g.checkWin() == false) {

					//Menu to choose to either craft or continue mining
					System.out.println("To mine, enter 'm' and to craft something, enter 'craft ' followed by the name of the object you wish to craft. The possible objects are: Furnace, Stone Pickaxe, Iron Pickaxe, Diamond Pickaxe, Stick, Gold Ingot, Iron Ingot, Gold Block");
					System.out.println("To leave this grid and dig deeper, enter 'dig'");
					action = sc.nextLine();

					//Starts mining process 
					if (action.equals("m")) {
						g.printArray();

						//Finds the row that wants to be mined, and makes sure no other values have been entered
						String rowTrue = "f";
						String columnTrue = "f";
						int row = 0;
						int column = 0;

						while (rowTrue.equals("f") || columnTrue.equals("f")) {
							System.out.println("Enter the letter of the row to be mined followed by the number of the column to be mined");
							String entered = sc.nextLine();

							rowTrue = "f";
							columnTrue = "f";

							//Makes sure entry includes 2 characters
							if (entered.length() == 2) {
								String rowEntered = entered.substring(0, 1);

								//Checks that row entered exists
								if (rowEntered.equals("A")) {
									row = 0;
									rowTrue = "t";
								}
								else if (rowEntered.equals("B")) {
									row = 1;
									rowTrue = "t";
								}
								else if (rowEntered.equals("C")) {
									row = 2;
									rowTrue = "t";
								}
								else if (rowEntered.equals("D")) {
									row = 3;
									rowTrue = "t";
								}
								else if (rowEntered.equals("E")) {
									row = 4;
									rowTrue = "t";
								}
								else {
									System.out.println("This is not a row in the block.");
								}

								String columnEntered = entered.substring(1);

								//Checks that column entered exits
								if (columnEntered.equals("1")) {
									column = 0;
									columnTrue = "t";
								}
								else if (columnEntered.equals("2")) {
									column = 1;
									columnTrue = "t";
								}
								else if (columnEntered.equals("3")) {
									column = 2;
									columnTrue = "t";
								}
								else if (columnEntered.equals("4")) {
									column = 3;
									columnTrue = "t";
								}
								else if (columnEntered.equals("5")) {
									column = 4;
									columnTrue = "t";
								}
								else {
									System.out.println("This is not a column in the block.");
								}
							}		
						}	

						//Mines and prints inventory
						g.mine(row, column);
						g.printInventory();
					}

					//Crafts the object being asked
					else if (action.length() > 6) {
						if (action.substring(0, 6).equals("craft ")) {
							g.craft(action.substring(6));
						}
					}

					//Digs further if asked to and generates a new grid. 
					else if (action.equals("dig")) {
						g.buildArray();
						System.out.println("New Grid");
						System.out.println();
						g.printArray();
					}
				}
				if (g.checkWin() == true) {
					System.out.println("YOU WIN!!");

				}

			}


		}


	}

}