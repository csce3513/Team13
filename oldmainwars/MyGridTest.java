package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyGridTest 
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
	public void testMyGrid() 
	{
		MyGrid Grid = new MyGrid();
		assertNotNull(Grid);
	}

	@Test
	public void testMyGridIntIntIntInt() 
	{
		MyGrid Grid1 = new MyGrid(5,5, 5, 10);
		assertEquals(Grid1.GetTile(2, 3).GetX(), 30);
		assertEquals(Grid1.GetTile(2, 3).GetY(), 10);
	}
}
