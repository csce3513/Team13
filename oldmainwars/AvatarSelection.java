package oldmainwars;

import java.awt.Font;

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
import org.newdawn.slick.UnicodeFont;

public class AvatarSelection extends BasicGameState
{
    public static int numAvatars = 3;
    private static int avatarChosen;

    //images
    public Image background = null;
    public Image back = null;
    public Image next = null;
    public Image[] characters = new Image[numAvatars + 1];

    //x, y coordinates for images
    public static int backX = 12;
    public static int backY = 535;

    public static int nextX = 598;
    public static int nextY = 535;

    //x, y coordinate arrays for avatars
    public int[] characterX = new int[numAvatars + 1];
    public int[] characterY = new int[numAvatars + 1];

    //boolean array to keep track of which avatar is selected.
    //Index[1] represents Avatar1, index[2] represents Avatar2, etc
    //Index[0] keeps track if any avatar has been selected
    boolean[] selected = new boolean[numAvatars + 1];

    //scale floats for if image is to inlarge when moused over
    float scaleStep = 0.0001f;
    float backScale = 1;
    float nextScale = 1;

    public int stateID = -1;

    //constructor
    public AvatarSelection (int stateID)
    {
        this.stateID = stateID;
    }

    //function to get state id
    @Override
    public int getID()
    {
        return stateID;
    }

    //function to initialize images and character arrays
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        selected[0] = false; // no avatar initially selected

        // load images
        background = new Image("images/avatar_selection.jpg");
        back = new Image("images/back.png");
        next = new Image("images/next.png");

        // load avatar1 and set x, y coordinates
        characters[1] = new Image("images/model.png");
        characterX[1] = 280;
        characterY[1] = 325;

        characters[2] = new Image("images/Mage.png");
        characterX[2] = 525;
        characterY[2] = 285;

        characters[3] = new Image("images/archer.png");
        characterX[3] = 70;
        characterY[3] = 310;
    }

    //function to draw images on screen
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
    	//Render String telling player one to choose
    	arg2.drawString("Player 1 Select Avatar", 280, 130);
    	
        // render the background
        background.draw(12, 150);
        
        //render avatar names
    	arg2.drawString("Morothar", 120, 265);
    	arg2.drawString("Dante", 350, 265);
    	arg2.drawString("Mordecai", 525, 265);


        //render back and next buttons
        back.draw(backX, backY, backScale);
        next.draw(nextX, nextY, nextScale);

        for (int i = 1; i <= numAvatars; i++) // for each avatar
        {
            if (selected[i] == true) // if it's selected
            {
                //enlarge the picture by scale of 1.2 and display the pic slightly up to the left to compensate
                characters[i].draw(characterX[i] * .9f, characterY[i] * .9f, 1.2f);
            }
            else
            {
                //show the pic at regular scale
                characters[i].draw(characterX[i], characterY[i], 1);
            }
        }
    }

    //function that updates the screen
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
		//initializing variables
        Input input = gc.getInput();

        //Left/Right mouse clicks
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        // handle background music
        OldMainWars.handleMusic(gc);

        //initializing variables
        boolean insideMainMenu = false;
        boolean insideMapSelection = false;

        if((mouseX >= backX && mouseX <= backX + back.getWidth()) &&
            (mouseY >= backY && mouseY <= backY + back.getHeight())) // mouse over back button
        {
            insideMainMenu = true;
        }
        else if((mouseX >= nextX && mouseX <= nextX + next.getWidth()) &&
            (mouseY >= nextY && mouseY <= nextY + next.getHeight())) // mouse over next button
        {
            insideMapSelection = true;
        }
        for(int i = 1; i <= numAvatars; i++) // for each avatar
        {
            if((mouseX >= characterX[i] && mouseX <= characterX[i] + characters[i].getWidth()) &&
                (mouseY >= characterY[i] && mouseY <= characterY[i] + characters[i].getHeight())) // if moused over
            {
                if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ) // if avatar is clicked on
                {
                    // set all avatars to unselected and then selected the newly clicked avatar
                    for (int j = 1; j <= numAvatars; j++)
                    {
                        selected[j] = false;
                    }
                    selected[i] = true;
                    selected[0] = true; // now we can easily check if an avatar has been selected yet without searching
                }
            }
        }

        if(insideMainMenu)
        {
        	//Makes image bigger
            if(backScale < 1.05f)
                backScale += scaleStep * delta;

            //returns to main menu screen
            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
                sb.enterState(OldMainWars.MainMenu);
            }
        }else
        {
            if(backScale > 1.0f)
                backScale -= scaleStep * delta;
        }

        if((insideMapSelection) && (selected[0] == true)) //if next is clicked on and an avatar is selected
        {
            if(nextScale < 1.05f)
                nextScale += scaleStep * delta;

            //Avatar Selection
            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
                for(int i = 1; i <= numAvatars; i++) //go through each avatar
                {
                    if (selected[i] == true)
                    {
                        AvatarSelection.setAvatar(i);
                    }
                }

                //Advance to the next screen
                sb.enterState(OldMainWars.AvatarSelection2);
            }
        }
        else
        {
            if(nextScale > 1.0f)
                nextScale -= scaleStep * delta;
        }
    }

	//stores number of the avatar chosen
    public static void setAvatar(int n) throws SlickException
    {
        avatarChosen = n;
    }
    
    //returns number of avatar that was chosen
    public static int getAvatar() throws SlickException
    {
        return avatarChosen;
    }
}
