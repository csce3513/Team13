package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DanteTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testDante() 
	{
		Dante dante = new Dante();
		assertEquals(dante.getDanteHP(), 120);
		assertEquals(dante.getDantePM(), 0.90, 0);
                assertEquals(dante.getMovement(), 3);
	}

	@Test
	public void testDamage() 
	{
		Dante dante2 = new Dante();
		dante2.Damage(10);
		assertEquals(dante2.getDanteHP(), 110);
		
		dante2.Damage(20);
		assertEquals(dante2.getDanteHP(), 90);
		
		dante2.Damage(50);
		assertEquals(dante2.getDanteHP(), 40);
	}

	@Test
	public void testHealed() 
	{
		Dante dante3 = new Dante();
		dante3.Healed(20);
		assertEquals(dante3.getDanteHP(), 120);
		
		dante3.Damage(20);
		dante3.Healed(20);
		assertEquals(dante3.getDanteHP(), 120);
		
		dante3.Damage(50);
		dante3.Healed(20);
		assertEquals(dante3.getDanteHP(), 90);
	}

	@Test
	public void testAttackDamage() 
	{
		Dante dante4 = new Dante();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((dante4.AttackDamage() <= 18) && (dante4.AttackDamage() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}
}
