/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package oldmainwars;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.GameState;
/**
 *
 * @author mmdougla
 */
public class OldMainWars extends StateBasedGame
{

    public static final int MainMenu = 0;
    public static final int AvatarSelection = 1;
    public static final int GamePlay = 2;

    public OldMainWars(String title)
    {
        super(title);

        this.addState(new MainMenu(MainMenu));
        this.addState(new AvatarSelection(AvatarSelection));
        this.addState(new GamePlay(GamePlay));
        this.enterState(MainMenu);
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
        app.setDisplayMode(800, 865, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
