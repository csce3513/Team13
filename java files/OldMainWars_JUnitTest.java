/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import oldmainwars.MainMenu;
import oldmainwars.AvatarSelection;
import oldmainwars.GamePlay;
import oldmainwars.OldMainWars;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author mmdougla
 */
public class OldMainWars_JUnitTest extends TestCase {

    private MainMenu test = new MainMenu(0);
    private AvatarSelection test1 = new AvatarSelection(1);
    private GamePlay test2 = new GamePlay(2);

    public OldMainWars_JUnitTest(String name)
    {
        super(name);
    }

    /*****Main Menu Testing****/
    @Test
    public void testMainMenuStateId()
    {

        assertEquals(test.stateID,0);
    }

    @Test
    public void testMainMenuStartX()
    {

        assertEquals(test.startX,100);
    }


    @Test
    public void testMainMenuStartY()
    {

        assertEquals(test.startY,590);
    }

    @Test
    public void testMainMenuOptionX()
    {

        assertEquals(test.optionX,520);
    }

    @Test
    public void testMainMenuOptionY()
    {

        assertEquals(test.optionY,590);
    }

    /*****Avatar Selection Testing****/
    @Test
    public void testAvatarSelectoinStateId()
    {

        assertEquals(test1.stateID,1);
    }

    @Test
    public void testAvatarSelectionbackX()
    {

        assertEquals(test1.backX,12);
    }

    @Test
    public void testAvatarSelectionbackY()
    {

        assertEquals(test1.backY,535);
    }

    @Test
    public void testAvatarSelectionnextX()
    {

        assertEquals(test1.nextX,590);
    }

    @Test
    public void testAvatarSelectionnextY()
    {

        assertEquals(test1.nextY,535);
    }

    /*****Game Play Testing****/
    @Test
    public void testGamePlayStateId()
    {

        assertEquals(test2.stateId,2);
    }
}