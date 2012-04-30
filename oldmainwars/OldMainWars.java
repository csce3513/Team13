package oldmainwars;

import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.GameState;

public class OldMainWars extends StateBasedGame
{
	//initializing variables
    public static final int MainMenu = 0;
    public static final int AvatarSelection = 1;
    public static final int MapSelection = 2;
    public static final int GamePlay = 3;
    public static final int AvatarSelection2 = 4;
    public static final int VictoryScreen = 5;
    public static final int InstructionScreen = 6;

    public static final int ScreenWidth = 800;
    public static final int ScreenHeight = 800;

    public static final int Dante = 0;
    public static final int Warrior = 1;
    public static final int Morohtar = 2;
    public static final int Archer = 3;
    public static final int Mordecai = 4;
    public static final int Mage = 5;

    public static Sound fx = null;

    //constructor
    public OldMainWars(String title)
    {
        super(title);

        this.addState(new MainMenu(MainMenu));
        this.addState(new InstructionScreen(InstructionScreen));
        this.addState(new AvatarSelection(AvatarSelection));
        this.addState(new AvatarSelection2(AvatarSelection2));
        this.addState(new MapSelection(MapSelection));
        this.addState(new GamePlay(GamePlay));
        this.addState(new VictoryScreen(VictoryScreen));
        this.enterState(MainMenu);
    }

    //initializes all the states
    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.getState(MainMenu).init(gc, this);
        this.getState(AvatarSelection).init(gc, this);
        this.getState(AvatarSelection2).init(gc, this);
        this.getState(MapSelection).init(gc, this);
        this.getState(GamePlay).init(gc, this);
        this.getState(VictoryScreen).init(gc, this);
        this.getState(InstructionScreen).init(gc, this);
        fx.loop();
    }

    //Starts Game
    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer (new OldMainWars("Old Main Wars"));
        app.setDisplayMode(ScreenWidth, ScreenHeight, false);
        app.setTargetFrameRate(60);

        fx = new Sound("sounds/soundtrack.wav");

        app.start();
    }
    
    //Function to starts/stops music playing
    public static void handleMusic(GameContainer gc)
    {
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_S))
        {
            if (fx.playing())
                fx.stop();
            else
                fx.loop();
        }
    }
}