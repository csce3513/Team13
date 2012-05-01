/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmainwars;

import org.newdawn.slick.*;

public class MyGrid
{
	//initialize variables
    private MyTile[][] grid;
    private int height;
    private int width;
    
    //default constructor
    MyGrid()
    {
        grid = new MyTile[0][0];
    }
    
    //constructor
    MyGrid(int rows, int columns, int newHeight, int newWidth)
    {
        height = newHeight;
        width = newWidth;
        
        grid = new MyTile[rows][columns];
        
        for(int y=0; y < rows; y++)
        {
            for(int x =0; x < columns; x++)
            {
                grid[y][x] = new MyTile(x*width,y*height,x,y);
            }
        }
    }
    
    //returns tile location
    public MyTile GetTile(int row, int column)
    {
        return grid[row][column];
    }
    
    //returns tile location based off the coordinates
    public MyTile FindTile(int x, int y)
    {
    	int xLoc;
    	int yLoc;
    	
    	xLoc = x / width;
    	yLoc = y / height;
    	
    	return GetTile(yLoc,xLoc);
    }
    
    //returns x coordinate
    public int GetXLoc(int x)
    {
    	return x/width;
    }
    
    //return y coordinates
    public int GetYLoc(int y)
    {
    	return y/height;
    }
}
