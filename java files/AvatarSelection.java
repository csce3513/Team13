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
import org.newdawn.slick.tests.xml.Entity;
import org.newdawn.slick.SpriteSheet;
import it.randomtower.engine.ResourceManager;

/**
 *
 * @author Administrator
 */
public class AvatarSelection extends BasicGameState
{
    public Image background = null;
    public Image back = null;
    public Image next = null;
    public Image character = null;

    public int stateID = -1;

    public AvatarSelection (int stateID)
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
        background = new Image("images/avatar_selection.jpg");

        Image backImage = new Image("images/back.png");
        back = backImage.getSubImage(0, 0, 200, 35);

        Image characterImage = new Image("images/model.png");
        character = characterImage.getSubImage(0, 0, 200, 200);

        Image nextImage = new Image("images/next.png");
        next = nextImage.getSubImage(0, 0, 200, 35);

    }

    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        // render the background
        background.draw(12, 150);

        back.draw(12, 535);

        next.draw(590, 535);

        character.draw(280,325);
    }

    public static int backX = 12;
    public static int backY = 535;

    public static int nextX = 590;
    public static int nextY = 535;
    
    public static int characterX = 280;
    public static int characterY = 325;

    float startGameScale = 1;
    float exitScale = 1;

    float scaleStep = 0.0001f;

    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        boolean insideMainMenu = false;
        boolean insideGamePlay = false;

        if( ( mouseX >= backX && mouseX <= backX + back.getWidth()) &&
            ( mouseY >= backY && mouseY <= backY + back.getHeight()) )
        {
            insideMainMenu = true;
        }
        else if( ( mouseX >= nextX && mouseX <= nextX+ next.getWidth()) &&
            ( mouseY >= nextY && mouseY <= nextY + next.getHeight()) )
        {
            insideGamePlay = true;
        }

        if(insideMainMenu)
        {
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
                //fx.play();
                sb.enterState(OldMainWars.MainMenu);
            }
            else
            {
            if(startGameScale > 1.0f)
                startGameScale -= scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
                gc.exit();
            }

        }
        if(insideGamePlay)
        {
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ){
                //fx.play();
                sb.enterState(OldMainWars.GamePlay);
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
