package assignment;
/**
 * This class represents loot that is within the game.
 * It allows for other loot to be made.
 * @author rajrathod 17969599
 **/
public abstract class Loot
{
	private String name;
	private int healthStats; 
	private int value; 
	private Rarity rarity;

	public Loot()
	{
		this.name = "UNKNOWN";
		this.healthStats = 0;
		this.rarity = Rarity.COMMON;
	}
	// Constructor for health potions
	public Loot(String name, int hp, int value, Rarity rarity)
	{
		this.name = name;
		this.healthStats = hp;
		this.value = value;
		this.rarity = rarity;
	}

	public Rarity getRarity() 
	{
		return rarity;
	}
	
	public String getName() 
	{
		return name;
	}

	public int getHealthStats() 
	{
		return healthStats;
	}

	public int getValue() 
	{
		return value;
	}

	public String toString() 
	{
		return "Name: " +name + ", Health Points: " +healthStats + ", Value: " + value + ", Rarity: " +rarity;
	}
}
