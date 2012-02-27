package oldmainwars;

import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.GameState;

public class OldMainWars extends StateBasedGame
{
    public static final int MainMenu = 0;
    public static final int AvatarSelection = 1;
    public static final int GamePlay = 2;

    public static final int ScreenWidth = 800;
    public static final int ScreenHeight = 800;
    

    public OldMainWars(String title)
    {
        super(title);

        this.addState(new MainMenu(MainMenu));
        this.addState(new AvatarSelection(AvatarSelection));
        this.addState(new GamePlay(GamePlay));
        this.enterState(MainMenu);
        //this.enterState(GamePlay);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.getState(MainMenu).init(gc, this);
        this.getState(AvatarSelection).init(gc, this);
        this.getState(GamePlay).init(gc, this);
    }

    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer (new OldMainWars("Old Main Wars"));
<<<<<<< HEAD:oldmainwars/OldMainWars.java
        app.setDisplayMode(800, 700, false);
=======
        app.setDisplayMode(ScreenWidth, ScreenHeight,false);
>>>>>>> Dev:oldmainwars/OldMainWars.java
        app.setTargetFrameRate(60);
        app.start();
    }
}
