package assignment;
/**
 * This class represents healing potions.
 * This is what a player will use to gain health points,
 * so they can survive longer in the dungeon to find the exit.
 * @author rajrathod 17969599
 **/
public class HealthPotion extends Loot 
{
	public HealthPotion(String name, int hp, int value, Rarity rarity)
	{
		super(name, hp, value, rarity);
	}
	
	public String toString() 
	{
		return super.getName() + ":\t Heals " + super.getHealthStats() + " health points, " + "price: " + super.getValue() + " coins" + "\n";
	}
}
