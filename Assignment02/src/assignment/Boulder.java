package assignment;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class acts as a list of methods for a boulder.
 * It sets all the possible locations a boulder can spawn.
 * It also implements the Drop interface, using the dropBoulder
 * method to deal a random amount of damage between 1-3 to a player.
 * @author rajrathod 17969599
 **/
public class Boulder implements Drop
{
	private final int MAX_ARRAY_SIZE = 21;
	private final int MAX_BOULDER_COORDS = 40;

	public Boulder()
	{
		// left empty as this class is just a method to access the methods within it.
	}

	public int getMAX_ARRAY_SIZE()
	{
		return MAX_ARRAY_SIZE;
	}

	public int getMAX_BOULDER_COORDS()
	{
		return MAX_BOULDER_COORDS;
	}
	// implemented from Drop
	public int dropBoulder()
	{
		Random rand = new Random();
		int monsterAttack = rand.nextInt(3) + 1;
		return monsterAttack;
	}
	/**
	 	 * This method first generates all the possible co-ordinates
	 	 * a player can travel on each axis, which is between -10 and 10; storing it in an array.
	 	 * Then it randomly stores these in an ArrayList for boulder Coordinates.
	 	 * @return It returns the ArrayList filled with these Coordinates.
	 	 **/
	public final ArrayList<Coordinates> boulderSpawnLocations()
	{
		ArrayList<Coordinates> boulderCoords = new ArrayList<Coordinates>();
		int allMapCoords[] = new int[MAX_ARRAY_SIZE], index = 0;
		// fills the possible coordinates of the game from -10 to 10
		for(int i = -10; i < allMapCoords.length; i++)
		{
			if(index != MAX_ARRAY_SIZE)
			{
				allMapCoords[index] = i;
				index++;
			}
		}

		for(int l = 0; l < MAX_BOULDER_COORDS; l++)
		{
			Random randB = new Random();
			int randomIndexX;
			int randomIndexY;

			do
			{
				randomIndexX = randB.nextInt(21);
				randomIndexY = randB.nextInt(21);

			}while(boulderCoords.contains(new Coordinates(allMapCoords[randomIndexX], allMapCoords[randomIndexY])));

			boulderCoords.add(l, new Coordinates(allMapCoords[randomIndexX], allMapCoords[randomIndexY]));
		}
		return boulderCoords;
	}
}
