package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyTileTest {

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
	public void testMyTile() 
	{
		MyTile TilePos = new MyTile();
		assertEquals(TilePos.GetX(), 0);
		assertEquals(TilePos.GetY(), 0);
	}

	@Test
	public void testMyTileIntInt() 
	{
		MyTile Pos = new MyTile(5, 10,0,0);
		assertEquals(Pos.GetX(), 5);
		assertEquals(Pos.GetY(), 10);
	}
}
