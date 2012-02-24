package oldmainwars;

import it.randomtower.engine.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameWorld extends World
{
    public GameWorld(int id, GameContainer gc)
    {
        super(id,gc);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        super.init(gc,game);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
    {
        super.update(gc, game, delta);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        super.render(gc,game,g);
    }
}
