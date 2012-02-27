<<<<<<< HEAD:oldmainwars/MyGrid.java
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmainwars;

/**
 *
 * @author ryan
 */
public class MyGrid {
    
    private MyTile[][] grid;
    
    MyGrid()
    {
        grid = new MyTile[0][0];
    }
    
    MyGrid(int rows, int columns, int height, int width)
    {
        
        grid = new MyTile[rows][columns];
        
        for(int y=0; y < rows; y++)
        {
            for(int x =0; x < columns; x++)
            {
                grid[y][x] = new MyTile(x*width,y*height);
            }
        }
    }
    
    public MyTile GetTile(int row, int column)
    {
        return grid[row][column];
    }
    
}
=======
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmainwars;

import org.newdawn.slick.*;

public class MyGrid
{
    private MyTile[][] grid;
    
    MyGrid()
    {
        grid = new MyTile[0][0];
    }
    
    MyGrid(int rows, int columns, int height, int width)
    {
        
        grid = new MyTile[rows][columns];
        
        for(int y=0; y < rows; y++)
        {
            for(int x =0; x < columns; x++)
            {
                grid[y][x] = new MyTile(x*width,y*height);
            }
        }
    }
    
    public MyTile GetTile(int row, int column)
    {
        return grid[row][column];
    }
    
}
>>>>>>> Dev:oldmainwars/MyGrid.java
