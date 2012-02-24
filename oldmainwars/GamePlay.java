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

public class GamePlay extends BasicGameState
{
    public int stateId = 0;

    public Image gameHUD = null;
    public MyGrid grid = null;
    public Sound blockFX = null;

    public Image spritesUp[] = new Image[3];
    public Animation danteUp = new Animation();
    public Image spritesRight[] = new Image[3];
    public Animation danteRight = new Animation();
    public Image spritesDown[] = new Image[3];
    public Animation danteDown = new Animation();
    public Image spritesLeft[] = new Image[3];
    public Animation danteLeft = new Animation();

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
        spritesUp[0] = spriteSheet.getSubImage(1, 21, 62, 75);
        spritesUp[1] = spriteSheet.getSubImage(75, 21, 63, 75);
        spritesUp[2] = spriteSheet.getSubImage(144, 21, 63, 75);

        spritesRight[0] = spriteSheet.getSubImage(3, 126, 60, 67);
        spritesRight[1] = spriteSheet.getSubImage(81, 123, 63, 69);
        spritesRight[2] = spriteSheet.getSubImage(153, 126, 63, 65);

        spritesDown[0] = spriteSheet.getSubImage(6, 212, 63, 76);
        spritesDown[1] = spriteSheet.getSubImage(81, 212, 63, 76);
        spritesDown[2] = spriteSheet.getSubImage(153, 212, 62, 72);

        spritesLeft[0] = spriteSheet.getSubImage(0, 312, 63, 72);
        spritesLeft[1] = spriteSheet.getSubImage(73, 312, 63, 72);
        spritesLeft[2] = spriteSheet.getSubImage(147, 312, 63, 72);

        danteUp = new Animation(spritesUp, 3);
        danteUp.setSpeed(.01f);

        danteRight = new Animation(spritesRight, 3);
        danteRight.setSpeed(.01f);

        danteDown = new Animation(spritesDown, 3);
        danteDown.setSpeed(.01f);

        danteLeft = new Animation(spritesLeft, 3);
        danteLeft.setSpeed(.01f);
        
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

        danteDown.draw(danteX, danteY);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
        {
            while (danteY < mouseY)
            {
                danteY++;
            }
            while (danteY > mouseY)
            {
                danteY--;
            }
            while (danteX < mouseX)
            {
                danteX++;
            }
            while (danteX > mouseX)
            {
                danteX--;
            }
        }
    }
}
