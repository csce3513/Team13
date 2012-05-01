/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package oldmainwars;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Administrator
 */
public class OldMainWarsTest extends BasicGame
{

    private GameContainer gc;
    private StateBasedGame sbg;

    int hp;

    GamePlay gp = new GamePlay(3);

    public OldMainWarsTest()
    {
        super("OldMainWars Test");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Before
    public void setUp() throws SlickException {
    }

    @After
    public void tearDown() {
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        this.gc = gc;
        gp.init(gc, sbg);
        gp.enter(gc, sbg);
        System.out.println("HP: " + gp.unit[0].getHP());
        //gp.unit[0] = new Character(0);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        gp.unit[0].draw();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }
        hp = gp.unit[0].getHP();
        System.out.println("hp: " + hp);

        //container.exit();
    }

    public static void main(String[] argv)
    {
        try {
            AppGameContainer app = new AppGameContainer(new OldMainWarsTest());
            app.setDisplayMode(800, 600, false);

            app.start();

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

}