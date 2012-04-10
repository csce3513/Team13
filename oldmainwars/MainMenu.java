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

public class MainMenu extends BasicGameState
{
    public Image background = null;
    public Image title = null;
    public Image startGameOption = null;

    public static int startX = 300;
    public static int startY = 595;

    float scaleStep = 0.0001f;
    float startGameScale = 1;
    //float optionsScale = 1;

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
        System.out.println("MainMenu init");

        //Load background
        background = new Image("images/old_main.jpg");

        title = new Image("images/gamename.png");
        startGameOption = new Image("images/Start.png");

        //--------------------------------------------------

        //fx = new Sound("data/Desktop/Pictures/pulse.wav");

        //--------------------------------------------------
    }

    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        // render the background
        background.draw((OldMainWars.ScreenWidth - background.getWidth()) / 2, 150);

        title.draw((OldMainWars.ScreenWidth - title.getWidth()) / 2, 50);
        startGameOption.draw(startX, startY, startGameScale);
    }

    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {

        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        boolean insideStartGame = false;

        if( ( mouseX >= startX && mouseX <= startX + startGameOption.getWidth()) &&
            ( mouseY >= startY && mouseY <= startY + startGameOption.getHeight()) )
        {
            insideStartGame = true;
        }

        if(insideStartGame)
        {
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
                sb.enterState(OldMainWars.AvatarSelection);
            }
        }
        else
        {
            if(startGameScale > 1.0f)
                startGameScale -= scaleStep * delta;

            //if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
            //    gc.exit();
        }
    }
}