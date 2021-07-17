package assignment;

import java.util.Random;
/**
 * This class is used to track the co-ordinates of players, 
 * boulders and coins. It is also used to spawn the exit coordinates of the dungeon.
 * @author rajrathod 17969599
 **/
public class Coordinates 
{
	private int x;
	private int y;
	
	public Coordinates() 
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinates(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public void setX(int x) 
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y) 
	{
		this.y = y;
	}
	/**
	 	 * This method first stores all the possible co-ordinates
	 	 * for the x-axis in the game which is between -10 and 10.
	 	 * This is done so a random co-ordinate can be picked at random.
	 	 * It is implemented  this way because negative numbers cannot be generated.
	 	 * @return returns a random int between -10 and 10.
	 	 * @author 17969599
	 	 **/
	public int spawnExitX()
	{
		Random rand = new Random();
		int possibleCoordsForExit[] = new int[21], index = 0, randIndex = 0;
		
		for(int i = -10; i < possibleCoordsForExit.length; i++)
		{
			if(index != 21)
			{				
				possibleCoordsForExit[index] = i;
				index++;
			}
		}
		randIndex = rand.nextInt(21);
		return possibleCoordsForExit[randIndex];
	}
	/**
	 	 * This method first stores all the possible co-ordinates
	 	 * for the y-axis in the game which is between -10 and 10.
	 	 * This is done so a random co-ordinate can be picked at random.
	 	 * It is implemented  this way because negative numbers cannot be generated.
	 	 * @return returns a random int between -10 and 10.
	 	 * @author 17969599
	 	 **/
	public int spawnExitY()
	{
		Random rand = new Random();
		int possibleCoordsForExit[] = new int[21], index = 0, randIndex = 0;
		
		for(int i = -10; i < possibleCoordsForExit.length; i++)
		{
			if(index != 21)
			{				
				possibleCoordsForExit[index] = i;
				index++;
			}
		}
		randIndex = rand.nextInt(21);
		return possibleCoordsForExit[randIndex];
	}
}
