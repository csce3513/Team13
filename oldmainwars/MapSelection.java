package oldmainwars;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tests.xml.Entity;
import org.newdawn.slick.SpriteSheet;
import it.randomtower.engine.ResourceManager;

public class MapSelection extends BasicGameState
{
    public static int numMaps = 3;
    private static int mapChosen;

    //images
    public Image back = null;
    public Image next = null;
    public Image[] maps = new Image[numMaps];
    public Image displayMap = null;

    //x, y coordinates for images
    public static int backX = 12;
    public static int backY = 720;

    public static int nextX = 598;
    public static int nextY = 720;

    //x, y coordinate arrays for maps
    public int[] mapX = new int[numMaps];
    public int[] mapY = new int[numMaps];

    //boolean array to keep track of which map is selected.
    boolean[] selected = new boolean[numMaps];

    //scale floats for if image is to inlarge when moused over
    float scaleStep = 0.0001f;
    float backScale = 1;
    float nextScale = 1;

    public int stateID = -1;
    private java.awt.Font robotypeRegular;

    //constructor
    public MapSelection (int stateID)
    {
        this.stateID = stateID;
    }

    //get state id
    @Override
    public int getID()
    {
        return stateID;
    }

    //initialize map images
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        selected[0] = true; // first map initially selected

        // load images
        back = new Image("images/back.png");
        next = new Image("images/next.png");

        // load map1 and set x, y coordinates
        maps[0] = new Image("images/map1_2.png");
        maps[0] = maps[0].getScaledCopy(.15f);
        mapX[0] = 12;
        mapY[0] = 615;

        maps[1] = new Image("images/map2_2.png");
        maps[1] = maps[1].getScaledCopy(.15f);
        mapX[1] = 660;
        mapY[1] = 615;
        
        maps[2] = new Image("images/map3.png");
        maps[2] = maps[2].getScaledCopy(.15f);
        mapX[2] = 333;
        mapY[2] = 615;

    }

    //function to draw images on screen
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        back.draw(backX, backY, backScale);
        next.draw(nextX, nextY, nextScale);

        for (int i = 0; i < numMaps; i++) // for each map
        {
            maps[i].draw(mapX[i], mapY[i], 1);

            if (selected[i]) //display the selected map in the center of the screen
            {
                displayMap = maps[i].getScaledCopy(5.5f);
                displayMap.drawCentered(OldMainWars.ScreenWidth / 2, OldMainWars.ScreenHeight / 2 - 75);
            }
        }
    }

    //function to update screen
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	//initialize input variable
        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        // handle background music
        OldMainWars.handleMusic(gc);

        boolean insideAvatarSelection = false;
        boolean insideGamePlay = false;

        if((mouseX >= backX && mouseX <= backX + back.getWidth()) &&
            (mouseY >= backY && mouseY <= backY + back.getHeight())) // mouse over back button
        {
            insideAvatarSelection = true;
        }
        else if((mouseX >= nextX && mouseX <= nextX + next.getWidth()) &&
            (mouseY >= nextY && mouseY <= nextY + next.getHeight())) // mouse over next button
        {
            insideGamePlay = true;
        }
        for(int i = 0; i < numMaps; i++) // for each map
        {
            if((mouseX >= mapX[i] && mouseX <= mapX[i] + maps[i].getWidth()) &&
                (mouseY >= mapY[i] && mouseY <= mapY[i] + maps[i].getHeight())) // if moused over
            {
                if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ) // if map is clicked on
                {
                    // set all maps to unselected and then selected the newly clicked map
                    for (int j = 0; j < numMaps; j++)
                    {
                        selected[j] = false;
                    }
                    selected[i] = true;
                }
            }
        }

        //Returns user to previous screen
        if(insideAvatarSelection)
        {
            if(backScale < 1.05f)
                backScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
                sb.enterState(OldMainWars.AvatarSelection2);
            }
        }else
        {
            if(backScale > 1.0f)
                backScale -= scaleStep * delta;

            //if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
            //    gc.exit();
        }

        if((insideGamePlay)) //if next is clicked on
        {
            if(nextScale < 1.05f)
                nextScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
                for(int i = 0; i < numMaps; i++) //go through each map
                {
                    if (selected[i] == true)
                    {
                        MapSelection.setMap(i + 1);
                    }
                }
                sb.enterState(OldMainWars.GamePlay);
            }
        }
        else
        {
            if(nextScale > 1.0f)
                nextScale -= scaleStep * delta;

            //if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
            //    gc.exit();
        }
    }

    //function that sets map chosen
    public static void setMap(int n) throws SlickException
    {
        mapChosen = n;
    }
    
    //returns map chosen
    public static int getMap() throws SlickException
    {
        return mapChosen;
    }
}