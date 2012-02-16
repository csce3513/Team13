/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package oldmainwars;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author mmdougla
 */
public class MainMenu extends BasicGameState
{
    public Image background = null;
    public Image title = null;
    public Image startGameOption = null;
    public Image Options = null;

    public int stateID = -1;

    //Sound fx = null;

    public MainMenu(int stateID)
    {
        this.stateID = stateID;
    }

    @Override
    public int getID()
    {
        return stateID;
    }

    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        //Load Title
        Image menuOptions = new Image("images/gamename.png");
        title = menuOptions.getSubImage(0, 0, 500, 100);

        background = new Image("images/old_main.jpg");

        // Load the menu images
        Image menuOptions1 = new Image("images/Start.png");
        startGameOption = menuOptions1.getSubImage(0, 0, 200, 35);

        Image menuOptions2 = new Image("images/options.png");
        Options = menuOptions2.getSubImage(0, 0, 200, 35);


        //--------------------------------------------------

        //fx = new Sound("data/Desktop/Pictures/pulse.wav");

        //--------------------------------------------------
    }



    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        //Render Title
        title.draw(180, 50);

        // render the background
        background.draw(100, 150);

        // Draw menu options
        startGameOption.draw(100,590);
        Options.draw(520, 590);
    }

    public static int startX = 100;
    public static int startY = 590;

    public static int optionX = 520;
    public static int optionY = 590;

    float startGameScale = 1;
    float exitScale = 1;

    float scaleStep = 0.0001f;

    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        boolean insideStartGame = false;
        boolean insideOptions = false;

        if( ( mouseX >= startX && mouseX <= startX + startGameOption.getWidth()) &&
            ( mouseY >= startY && mouseY <= startY + startGameOption.getHeight()) )
        {
            insideStartGame = true;
        }
        else if( ( mouseX >= optionX && mouseX <= optionX+ Options.getWidth()) &&
            ( mouseY >= optionY && mouseY <= optionY + Options.getHeight()) )
        {
            insideOptions = true;
        }

        if(insideStartGame)
        {
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
                //fx.play();
                sb.enterState(OldMainWars.AvatarSelection);
            }
            else
            {
            if(startGameScale > 1.0f)
                startGameScale -= scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
                gc.exit();
            }

        }
    }
}
