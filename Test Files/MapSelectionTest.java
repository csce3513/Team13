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

/**
 *
 * @author Administrator
 */
public class MapSelectionTest
{

    public MapSelectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMapSelection()
    {
        MapSelection IDTest = new MapSelection(2);
        assertEquals(IDTest.getID(), 2);
    }

    @Test
    public void testBackButton()
    {
        MapSelection m = new MapSelection(2);
        assertEquals(m.backX, 12);
        assertEquals(m.backY, 720);
    }

    @Test
    public void testNextButton()
    {
        MapSelection m = new MapSelection(2);
        assertEquals(m.nextX, 598);
        assertEquals(m.nextY, 720);
    }

    @Test
    public void testGetSetMap() throws Exception
    {
    	MapSelection m = new MapSelection(2);
        try
        {
            m.setMap(5);
        } catch (Exception se){}

        assertEquals(m.getMap(), 5);
    }
}