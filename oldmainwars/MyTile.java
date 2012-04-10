/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmainwars;

import org.newdawn.slick.*;

public class MyTile
{
	//initialize variables
    private boolean isOccupied;
    private int OccupiedBy;
    private int xLoc;
    private int yLoc;
    private int xGridLoc;
    private int yGridLoc;
    
    //defualt constructor
    MyTile()
    {
        isOccupied = false;
        xLoc = 0;
        yLoc = 0;
        xGridLoc = 0;
        yGridLoc = 0;
    }
    
    //Constructor
    MyTile(int x, int y, int newXGridLoc, int newYGridLoc)
    {
        isOccupied = false;
        xLoc = x;
        yLoc = y;
        xGridLoc = newXGridLoc;
        yGridLoc = newYGridLoc;
    }
    
    //returns x location
    public int GetX()
    {
        return xLoc;
    }
    
    //return y location
    public int GetY()
    {
        return yLoc;
    }
    
    //returns true/false if tile is occupied
    public boolean GetOccupied()
    {
    	return isOccupied;
    }
    
    
    //returns what is in the tile
    public int GetOccupiedBy()
    {
    	return OccupiedBy;
    }
    
    //set function to setOccupied to true
    public void SetOccupiedTrue()
    {
    	isOccupied = true;
    }
    
  //set function to setOccupied to false
    public void SetOccupiedFalse()
    {
    	isOccupied = false;
    }
    
    //updates what is inside the tile
    public void SetOccupiedBy(int newInt)
    {
    	OccupiedBy = newInt;
    }
    
    //gets x coordinate for the grid
    public int getXGridLoc()
    {
    	return xGridLoc;
    }
    
    //gets y coordinate for the grid
    public int getYGridLoc()
    {
    	return yGridLoc;
    }
}
