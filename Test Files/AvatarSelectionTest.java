package oldmainwars;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AvatarSelectionTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAvatarSelection() 
	{
		AvatarSelection IDTest = new AvatarSelection(1);
		assertEquals(IDTest.getID(),1);
	}
	
	@Test
    public void testAvatarSelectionbackX()
    {
		AvatarSelection selectXpos = new AvatarSelection(1);
        assertEquals(selectXpos.backX,12);
    }

    @Test
    public void testAvatarSelectionbackY()
    {
    	AvatarSelection selectYpos = new AvatarSelection(1);
        assertEquals(selectYpos.backY,535);
    }

    @Test
    public void testAvatarSelectionnextX()
    {
    	AvatarSelection nextXpos = new AvatarSelection(1);
        assertEquals(nextXpos.nextX,598);
    }

    @Test
    public void testAvatarSelectionnextY()
    {
    	AvatarSelection nextYpos = new AvatarSelection(1);
        assertEquals(nextYpos.nextY,535);
    }
    
    /*@Test
    public void testAvatarSelectionCharacterX()
    {
    	AvatarSelection AvatarPosX = new AvatarSelection(1);
        assertEquals(AvatarPosX.characterX,280);
    }

    @Test
    public void testAvatarSelectionCharacterY()
    {
    	AvatarSelection AvatarPosY = new AvatarSelection(1);
        assertEquals(AvatarPosY.characterY,325);
    }*/
}
