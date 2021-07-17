package assignment;

import java.util.Map;
import java.util.TreeMap;
/**
 * This class allows for a shop to be made.
 * It essentially acts as a list of Loots. 
 * The Loots are stored in a TreeMap. 
 * @author rajrathod 17969599 
 **/
public class Shop
{
	private TreeMap<String, Loot> shop;
	
	public Shop() 
	{
		this.shop = shopList();
	}

	public TreeMap<String, Loot> getShop() 
	{
		return shop;
	}

	public void setShop(TreeMap<String, Loot> shop) 
	{
		this.shop = shop;
	}
	/**
	 	 * This method stores a list of health potions 
	 	 * which can be bought by the player.
	 	 * @return it returns a TreeMap of health potions.
	 	 * @author 17969599
	 	 **/
	public TreeMap<String, Loot> shopList()
	{
		TreeMap<String, Loot> shop = new TreeMap<String, Loot>();
		
		shop.put("minihealthpotion", new HealthPotion("MiniHealthPotion", 1, Rarity.COMMON.getPrice(), Rarity.COMMON));
		shop.put("healthpotion", new HealthPotion("HealthPotion", 3, Rarity.UNCOMMON.getPrice(), Rarity.UNCOMMON));
		shop.put("healthflask", new HealthPotion("HealthFlask", 5, Rarity.RARE.getPrice(), Rarity.RARE));
		shop.put("megahealthflask", new HealthPotion("MegaHealthFlask", 10, Rarity.VERYRARE.getPrice(), Rarity.VERYRARE));
		
		return shop;
	}
	/**
	 	 *Prints out the contents of a shop.
	 	 *@author 17969599
	 	 **/
	public String toString() 
	{
		String shopOutput = "SHOP:\n        ITEM NAME:       ITEM DESCRIPTION:\n\t";
		
		for(Map.Entry<String, Loot> entry : shop.entrySet())
		{
			shopOutput += entry.getValue() +"\t";
		}
		return shopOutput;		
	}
}
