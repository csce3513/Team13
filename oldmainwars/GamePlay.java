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

    public static int danteX = 548;
    public static int danteY = 568;
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
        gameHUD = new Image("images/map1.png");

        Image spriteSheet = new Image("images/warriorSprite.png");
        spritesUp[0] = spriteSheet.getSubImage(20, 40, 40, 44);
        spritesUp[1] = spriteSheet.getSubImage(70, 40, 40, 44);
        spritesUp[2] = spriteSheet.getSubImage(120, 40, 40, 44);

        spritesRight[0] = spriteSheet.getSubImage(20, 100, 40, 44);
        spritesRight[1] = spriteSheet.getSubImage(70, 100, 40, 44);
        spritesRight[2] = spriteSheet.getSubImage(120, 100, 40, 44);

        spritesDown[0] = spriteSheet.getSubImage(4, 138, 44, 48);
        spritesDown[1] = spriteSheet.getSubImage(48, 138, 44, 48);
        spritesDown[2] = spriteSheet.getSubImage(98, 138, 44, 48);

        spritesLeft[0] = spriteSheet.getSubImage(20, 230, 40, 44);
        spritesLeft[1] = spriteSheet.getSubImage(70, 230, 40, 44);
        spritesLeft[2] = spriteSheet.getSubImage(120, 230, 40, 44);

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
        gameHUD.draw(0,0);

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
