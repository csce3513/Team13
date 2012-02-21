/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmainwars;

/**
 *
 * @author ryan
 */
public class MyTile {
    
    private boolean isOccupied;
    private int xLoc;
    private int yLoc;
    
    MyTile()
    {
        isOccupied = false;
        xLoc = 0;
        yLoc = 0;
    }
    
    MyTile(int x, int y)
    {
        isOccupied = false;
        xLoc = x;
        yLoc = y;
    }
    
    public int GetX()
    {
        return xLoc;
    }
    
    public int GetY()
    {
        return yLoc;
    }
    
}
