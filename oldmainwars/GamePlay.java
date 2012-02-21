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

public class GamePlay extends BasicGameState
{
    public int stateId = 0;

    public Image gameHUD = null;
    public Image dante = null;
    public MyGrid grid = null;
    public Sound blockFX = null;

    public static int danteX = 48;
    public static int danteY = 68;
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
        Image spriteSheet = new Image("images/modelSprite.png");
        dante = spriteSheet.getSubImage(6, 212, 63, 76);
        
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

        dante.draw(danteX, danteY);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
        {
            danteX = mouseX;
            danteY = mouseY;
        }        
    }
}
