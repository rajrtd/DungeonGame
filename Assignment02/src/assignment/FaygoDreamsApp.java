package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * This is the driver class of the app.
 * It spawns coin locations, prints the starting game text,
 * and has a method that takes as input the variables that are needed
 * to run the game.
 * @author rajrathod17969599
 **/
public class FaygoDreamsApp 
{	
	/**
	 	 * This method first generates all the possible co-ordinates
	 	 * a player can travel on each axis, which is between -10 and 10; storing it in an array.
	 	 * Then it randomly stores these in an ArrayList for coin Coordinates.
	 	 * @return It returns the ArrayList filled with these Coordinates.
	 	 * @author 17969599
	 	 **/
	public final static ArrayList<Coordinates> coinSpawnLocations()
	{
		final int MAX_ARRAY_SIZE = 21;
		ArrayList<Coordinates> coinCoords = new ArrayList<Coordinates>();
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
		
		for(int l = 0; l < 40; l++)
		{
			Random randCS = new Random();
			int randomIndexX;
			int randomIndexY;
			
			do 
			{
				randomIndexX = randCS.nextInt(21);
				randomIndexY = randCS.nextInt(21);
				
			}while(coinCoords.contains(new Coordinates(allMapCoords[randomIndexX], allMapCoords[randomIndexY])));
			
			coinCoords.add(l, new Coordinates(allMapCoords[randomIndexX], allMapCoords[randomIndexY]));
		}
		return coinCoords;
	}
	/**
	 	 * These are all the variables needed to run the game.
	 	 * @param coinCoords This is the Coordinates of where coins will spawn.
	 	 * @param boulder This is used to access the dropBoulder and boulderSpawnLocations methods.
	 	 * @param boulderLocations This is the Coordinates of where boulders will spawn.
	 	 * @param objPlayer This is the player that begins the game and can move in any direciton N/E/S/W.
	 	 * @param quit This is used to check whether the player wants to quit the game or not.
	 	 * @param dungeonExit This is used to spawn a random exit where the player can leave the game.
	 	 * @param scan This is used to received user input.
	 	 * @author 17969599
	 	 **/
	public static char runGame(ArrayList<Coordinates> coinCoords, Boulder boulder, ArrayList<Coordinates> boulderLocations, Player objPlayer, char quit, int exitX, int exitY, Scanner scan)
	{				
		System.out.println("Where would you like to move N/E/S/W? You're currently at (" +objPlayer.getXCoordinates()+ "," +objPlayer.getYCoordinates() + ")");
		quit = objPlayer.getPlayerChoices(scan.next().charAt(0));
		int playerX = objPlayer.getXCoordinates();
		int playerY = objPlayer.getYCoordinates();
		
		for(Coordinates cs : coinCoords)
		{
			if(playerX == cs.getX() && playerY == cs.getY())
			{
				// removes coin once found so player doesn't get same coin when they leave the shop
				cs.setX(-9999);
				cs.setY(-9999);
				System.out.println("You found a coin!");
				objPlayer.setCoinPouch(objPlayer.getCoinPouch() + 1);
				break;
			}
		}

		for(int j = 0; j < boulder.getMAX_BOULDER_COORDS(); j ++)
		{
			if(playerX == exitX && playerY == exitY)
			{
				System.out.println("You found the dungeon exit!");

				try 
				{
					FileOutputStream out = new FileOutputStream("17969599-Found-Exit.txt");
					String exitFoundAt = "You found the exit at: " + "(" + String.valueOf(objPlayer.getXCoordinates()) + "," + String.valueOf(objPlayer.getYCoordinates()) + ")";
					byte[] exitFoundAtBytes = exitFoundAt.getBytes();
					out.write(exitFoundAtBytes);
					out.close();
				}
				catch(FileNotFoundException e)
				{
					System.out.println("Error: Cannot open file for writing");
				}
				catch(IOException e)
				{
					System.out.println("Error: Cannot write to file");
				}

				quit = 'q';
				break;
			}

			if(playerX == boulderLocations.get(j).getX() && playerY == boulderLocations.get(j).getY())
			{
				// removes boulder after it lands on player so player doesn't get hit by the same boulder when they leave the shop.
				boulderLocations.get(j).setX(-9999);
				boulderLocations.get(j).setY(-9999);
				System.out.println("\nA boulder has hurt you");
				objPlayer.setHealthPoints(objPlayer.getHealthPoints() - boulder.dropBoulder());

				if(objPlayer.getHealthPoints() == 0 || objPlayer.getHealthPoints() < 0)
				{
					System.out.println("You're Dead!");
					quit = 'q';

					try 
					{
						FileOutputStream out = new FileOutputStream("17969599-Player's-Stats-On-Death.txt");
						String deathLoc = "You died at: " + "(" + String.valueOf(objPlayer.getXCoordinates()) + "," + String.valueOf(objPlayer.getYCoordinates()) + ")\n";
						String coinsGathered = "You gathered " + String.valueOf(objPlayer.getCoinPouch()) + " coins\n";
						String exitLoc = "The Exit was at: (" + String.valueOf(exitX) + "," + String.valueOf(exitY) + ")\n";
						String playerStats = deathLoc + coinsGathered + exitLoc;
						byte[] playerStatsBytes = playerStats.getBytes();
						out.write(playerStatsBytes);
						out.close();
					}
					catch(FileNotFoundException e)
					{
						System.out.println("Error: Cannot open file for writing");
					}
					catch(IOException e)
					{
						System.out.println("Error: Cannot write to file");
					}
				}
				else
				{						
					System.out.println("Your current health points: " +objPlayer.getHealthPoints());
				}	
			}
		}
		return quit;
	}
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		Boulder objBoulder = new Boulder();
		ArrayList<Coordinates> boulderLocations = objBoulder.boulderSpawnLocations();
		ArrayList<Coordinates> coinCoords = coinSpawnLocations();
		Player objPlayer = new Player();
		Coordinates dungeonExit = new Coordinates();
		int exitX = dungeonExit.spawnExitX();
		int exitY = dungeonExit.spawnExitY();
		char quit = '\0';
		char userInput = '\0';
		
		try
		{
			File myFile = new File("17969599-Start-Text.txt");
			Scanner scanner = new Scanner(myFile);
			
			while(scanner.hasNextLine())
			{
				String text = scanner.nextLine();
				System.out.println(text);
			}
			
			FileWriter mf = new FileWriter(myFile);
//			mf.append("heeey");
			mf.close();
			
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: Cannot open file for reading");
		}
		catch(IOException e)
		{
			System.out.println("Error: Cannot read to file");
		}
		
		while(userInput != 'q' && userInput != 'Q')
		{			
			userInput = runGame(coinCoords, objBoulder, boulderLocations, objPlayer, quit, exitX, exitY, scan);
		}
		
		System.out.println("Goodbye!");
		
		scan.close();
	}
}