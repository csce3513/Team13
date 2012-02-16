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
public class GamePlay extends BasicGameState
{
    public int stateId = 0;

    public Image gameHUD = null;
    public MyGrid grid = null;
    public Sound blockFX = null;

    //Sound blockFX = null;

    private enum STATES
    {
        START_GAME_STATE
    }

    private STATES currentState = null;

    public GamePlay(int stateId)
    {
        this.stateId = stateId;
    }

    @Override
    public int getID()
    {
        return stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        gameHUD = new Image("images/Game Map-v2.jpg");
        
        grid = new MyGrid(10,10,72,72);

        //blockFX  = new Sound("data/pulse.wav");
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        super.enter(gc, sb);

        currentState = STATES.START_GAME_STATE;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
       //Display Map
        gameHUD.draw(40,0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
        Input input = gc.getInput();
        
    }
}
