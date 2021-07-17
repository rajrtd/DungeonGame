package assignment;
/**
 * This represents the rarity each item can have.
 * They values each rarity has corresponds to the item price.
 * @author rajrathod 17969599
 **/
public enum Rarity 
{
	COMMON(1), UNCOMMON(3), RARE(5), VERYRARE(10); 
	private int price;
	
	private Rarity(int price)
	{
		this.price = price;
	}

	public int getPrice() 
	{
		return price;
	}	
}
