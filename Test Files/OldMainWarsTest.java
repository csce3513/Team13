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

    static GamePlay gp = new GamePlay(3);

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
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        gp.enter(gc, sbg);

        Input input = gc.getInput();
        input.getMouseX();

        //test that first unit was initialized to display in right space
        assertEquals(gp.unit[0].getXCoordinate(), 20);
        assertEquals(gp.unit[0].getYCoordinate(), 266);

        // test attack function in game
        gp.attackPressed = true;
        gp.pos1 = gp.grid.GetTile(4, 0);
        gp.pos2 = gp.grid.GetTile(3, 0);
        gp.Action(gp.pos1, input, gp.ATTACK);
        assertFalse(gp.unit[1].getHP() == 100);

        // test magic function in game
        gp.attackPressed = true;
        gp.pos1 = gp.grid.GetTile(4, 0);
        gp.pos2 = gp.grid.GetTile(5, 0);
        gp.Action(gp.pos1, input, gp.MAGIC);
        assertFalse(gp.unit[2].getHP() == 75);
        
        // test heal function in game
        gp.attackPressed = true;
        gp.pos1 = gp.grid.GetTile(4, 0);
        gp.pos2 = gp.grid.GetTile(4, 0);
        gp.Action(gp.pos1, input, gp.HEAL);
        assertFalse(gp.unit[0].getHP() == 105);

        // test turn switching
        assertTrue(gp.turnTimer > 0);
        gp.resetTimer();
        assertTrue(gp.turnTimer == 0);

        // test all of player 1's units died
        gp.unit[0].setHealth(0);
        gp.unit[1].setHealth(0);
        gp.unit[2].setHealth(0);
        assertEquals(gp.victor, 2);

        // test random number generator
        assertTrue(gp.randomNumber() < 3);

        // test if action was taken and timer reset to draw animation
        gp.lastAction = gp.HEAL;
        assertTrue(gp.timer == 0);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
<<<<<<< HEAD
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }
        hp = gp.unit[0].getHP();
        System.out.println("hp: " + hp);

        //container.exit();
=======
        //gp.update(gc, sbg, delta);
        //gc.exit();
>>>>>>> Testing File
    }

    public static void main(String[] argv)
    {
        try {
            AppGameContainer app = new AppGameContainer(new OldMainWarsTest());
            app.setDisplayMode(800, 800, false);

            app.start();

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

}