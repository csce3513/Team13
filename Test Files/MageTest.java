package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MageTest {

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
	public void testMage() 
	{
		Mage Mage1 = new Mage();
		
		assertEquals(Mage1.getMageHP(),75);
		assertEquals(Mage1.getMageMP(),100);
		assertEquals(Mage1.getMageMM(), 0.85, 0);
		assertEquals(Mage1.getMagePM(), 0.50, 0);
	}

	@Test
	public void testDamage() 
	{
		Mage Mage2 = new Mage();
		Mage2.Damage(10);
		assertEquals(Mage2.getMageHP(), 65);
		
		Mage2.Damage(20);
		assertEquals(Mage2.getMageHP(), 45);
	}

	@Test
	public void testHealed() 
	{
		Mage Mage3 = new Mage();
		Mage3.Healed(20);
		assertEquals(Mage3.getMageHP(), 75);
		
		Mage3.Damage(20);
		Mage3.Healed(20);
		assertEquals(Mage3.getMageHP(), 75);
		
		Mage3.Damage(50);
		Mage3.Healed(20);
		assertEquals(Mage3.getMageHP(), 45);
	}

	@Test
	public void testCastSpell() 
	{
		Mage Mage5 = new Mage();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Mage5.CastSpell() <= 17) && (Mage5.CastSpell() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}
	
	@Test
	public void testMPCost()
	{
		Mage Mage6 = new Mage();
		int damage = 0;
		
		damage = Mage6.CastSpell();
		
		assertEquals(Mage6.getMageMP(), 90);
	}

	@Test
	public void testAttackDamage() 
	{
		Mage Mage4 = new Mage();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Mage4.AttackDamage() <= 10) && (Mage4.AttackDamage() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}

}
