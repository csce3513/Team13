package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArcherTest {

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
	public void testArcher() 
	{
		Archer Arch = new Archer();
		
		assertEquals(Arch.getArcherHP(),85);
		assertEquals(Arch.getArcherMP(),60);
		assertEquals(Arch.getArcherMM(), 0.50, 0);
		assertEquals(Arch.getArcherPM(), 0.60, 0);
	}

	@Test
	public void testDamage() 
	{
		Archer Arch1 = new Archer();
		Arch1.Damage(10);
		assertEquals(Arch1.getArcherHP(), 75);
		
		Arch1.Damage(20);
		assertEquals(Arch1.getArcherHP(), 55);
		
		Arch1.Damage(55);
		assertEquals(Arch1.getArcherHP(), 0);
	}

	@Test
	public void testHealed() 
	{
		Archer Arch2 = new Archer();
		Arch2.Healed(20);
		assertEquals(Arch2.getArcherHP(), 85);
		
		Arch2.Damage(20);
		Arch2.Healed(20);
		assertEquals(Arch2.getArcherHP(), 85);
		
		Arch2.Damage(50);
		Arch2.Healed(20);
		assertEquals(Arch2.getArcherHP(), 55);
	}

	@Test
	public void testCastSpell() 
	{
		Archer Arch3 = new Archer();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Arch3.CastSpell() <= 10) && (Arch3.CastSpell() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}

	@Test
	public void testAttackDamage() 
	{
		Archer Arch4 = new Archer();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Arch4.AttackDamage() <= 12) && (Arch4.AttackDamage() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}
}
