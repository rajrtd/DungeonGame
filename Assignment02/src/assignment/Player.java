package assignment;

import java.util.Scanner;
/**
 * This is what represents the user and how they can move around.
 * It has methods to handle user input and prompting user to buy from the shop.
 * @author rajrathod 17969599
 **/
public class Player
{
	private int xCoordinates;
	private int yCoordinates;
	private Shop playersShop;
	private int coinPouch;
	private int healthPoints;
	private Loot equippedItem;
	/**
	 	 * This constructor sets the default 'spawn' point of a newly created player to be (0,0)
	 	 * and makes it so that they start off with no coins.
	 	 * It also instantiates a Shop object allowing the player to access the shop.
	 	 * @author 17969599
	 	 **/
	public Player() 
	{
		super();
		Shop objShop = new Shop();
		this.xCoordinates = 0;
		this.yCoordinates = 0;
		this.playersShop = objShop;
		this.coinPouch = 0;
		this.healthPoints = 10;
		this.equippedItem = null;
	}

	public int getXCoordinates() 
	{
		return xCoordinates;
	}

	public int getYCoordinates() 
	{
		return yCoordinates;
	}
	
	public int getCoinPouch() 
	{
		return coinPouch;
	}
	
	public void setCoinPouch(int coinPouch) 
	{
		this.coinPouch = coinPouch;
	}
	
	public Loot getEquippedItem() 
	{
		return equippedItem;
	}
	
	public void setEquippedItem(Loot equippedItem) 
	{
		this.equippedItem = equippedItem;
	}
	
	public int getHealthPoints() 
	{
		return healthPoints;
	}
	
	public void setHealthPoints(int hp) 
	{
		this.healthPoints = hp;
	}
	
	private final static void edgeOfWorldText()
	{
		System.out.println("+-----------------------------------------+");
		System.out.println("* You have reached the edge of the world! *");
		System.out.println("+-----------------------------------------+");
	}
	/**
	 	 * This method prompts the user to buy items from the shop.
	 	 * It verifies the user input and makes sure the item they want to buy is actually in the shop.
	 	 * Once the item is bought, it is used by the player.
	 	 * @param scan this is used to get user input.
	 	 * @return this returns the item that is bought by the player,
	 	 * so it can be 'equipped' by the player.
	 	 **/
	public Loot promptToBuy(Scanner scan)
	{
		String item = "";
		// Checking if  the item that  the user wanted is in the shop, 
		// if not then say that item does not exist, try again...
		while((!item.equals("L")) && (!item.equals("l")))
		{
			//prints shop allowing user to see what they can buy from the shop
			System.out.println(this.playersShop);
			System.out.println("\tYou have: " + this.getCoinPouch() + " coins to spend.");
			System.out.println("\tYou can leave the shop by pressing 'L'\n");
			// asking for user input
			System.out.print("Enter the name of the item you want to buy:");
			item = scan.nextLine();
			// checking if the item exists in the shop
			if(this.playersShop.getShop().containsKey(item.toLowerCase()))
			{
				int itemValue = this.playersShop.getShop().get(item.toLowerCase()).getValue();
				int playerFunds = this.getCoinPouch();
				// checking if the player has enough coins in their coinpouch to purchase the item.
				if(playerFunds >= itemValue)
				{
					playerFunds -= itemValue;
					String itemName = this.playersShop.getShop().get(item.toLowerCase()).getName();
					
					System.out.println("\nCongratulation! You've bought: " +itemName);
					this.setCoinPouch(playerFunds);
					System.out.println("Your coin pouch now contains: " +playerFunds + " coins.");
					
					this.setHealthPoints(this.getHealthPoints() + this.playersShop.getShop().get(item.toLowerCase()).getHealthStats());
					System.out.println("You now have " + this.getHealthPoints() + " health points.\n");
					item = "L"; // exit the shop after you've bought an item
				}
				else
				{
					System.out.println("Sorry, you don't have enough coins.");
				}
			}// if the item does not exist in the shop and it is also not the condition for breaking the loop 
			// in which the user is requesting to leave the shop, then the item must not exist...
			else if(!(item.toLowerCase().equals("L")) && !(item.toLowerCase().equals("l")))
			{
				System.out.println("This item does not exist in the shop!");
			}
		}
		Loot boughtItem;
		boughtItem = this.playersShop.getShop().remove(item.toLowerCase());
		return boughtItem;
	}
	/**
	 	* This controls where the player can move within the game and prompts them with
	 	* the shop.
	 	* @param location this is the user input of where the user wants to move.
	 	* @return returns user input to check whether they want to quit the game.
	 	* @author 17969599
	 	**/
	public char getPlayerChoices(char location)
	{
		Coordinates playerCoords = new Coordinates();
		playerCoords.setX(this.xCoordinates);
		playerCoords.setX(this.yCoordinates);
		
		switch(location) 
		{
		case 'N':
		case 'n':
			if(this.yCoordinates == 10)
			{
				edgeOfWorldText();
			}
			else
			{					
				this.yCoordinates++;
			}
			break;
		case 'E':
		case 'e':
			if (this.xCoordinates == 10)
			{
				edgeOfWorldText();
			}
			else
			{						
				this.xCoordinates++;
			}
			break;
		case 'S':
		case 's':
			if(this.yCoordinates == -10)
			{
				edgeOfWorldText();
			}
			else
			{					
				this.yCoordinates--;
			}
			break;
		case 'W':
		case 'w':
			if (this.xCoordinates == -10)
			{
				edgeOfWorldText();
			}
			else
			{						
				this.xCoordinates--;
			}
			break;
		case 'P':
		case 'p':
			Scanner itemName = new Scanner(System.in);
			this.promptToBuy(itemName);
			break;
		case 'Q':
		case 'q':
			System.out.println("You've quit Faygo's Dream Dungeon!");
			break;
		default:
			System.out.println("Invalid Command! Try Again!\n");
		}
		return location;			
	}
}