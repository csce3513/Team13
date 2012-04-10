package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MorohtarTest {

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
	public void testMorohtar() 
	{
		Morohtar Morohtar = new Morohtar();
		
		assertEquals(Morohtar.getMorohtarHP(),105);
		assertEquals(Morohtar.getMorohtarMP(),70);
		assertEquals(Morohtar.getMorohtarMM(), 0.60, 0);
		assertEquals(Morohtar.getMorohtarPM(), 0.80, 0);
                assertEquals(Morohtar.getMovement(), 2);
	}

	@Test
	public void testDamage() 
	{
		Morohtar Morohtar1 = new Morohtar();
		Morohtar1.Damage(10);
		assertEquals(Morohtar1.getMorohtarHP(), 95);
		
		Morohtar1.Damage(20);
		assertEquals(Morohtar1.getMorohtarHP(), 75);
		
		Morohtar1.Damage(55);
		assertEquals(Morohtar1.getMorohtarHP(), 20);
	}

	@Test
	public void testHealed() 
	{
		Morohtar Morohtar2 = new Morohtar();
		Morohtar2.Healed(20);
		assertEquals(Morohtar2.getMorohtarHP(), 105);
		
		Morohtar2.Damage(20);
		Morohtar2.Healed(20);
		assertEquals(Morohtar2.getMorohtarHP(), 105);
		
		Morohtar2.Damage(50);
		Morohtar2.Healed(20);
		assertEquals(Morohtar2.getMorohtarHP(), 75);
	}

	@Test
	public void testCastSpell() 
	{
		Morohtar Morohtar3 = new Morohtar();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Morohtar3.CastSpell() <= 12) && (Morohtar3.CastSpell() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}

        @Test
	public void testMPCost()
	{
		Morohtar m = new Morohtar();
		int damage = m.CastSpell();

		assertEquals(m.getMorohtarMP(), 60);

                damage = m.CastSpell();
                damage = m.CastSpell();
                damage = m.CastSpell();
                damage = m.CastSpell();
                damage = m.CastSpell();
                damage = m.CastSpell();

                assertEquals(m.getMorohtarMP(), 0);

                damage = m.CastSpell();
                assertEquals(m.getMorohtarMP(), 0);


	}

	@Test
	public void testAttackDamage() 
	{
		Morohtar Morohtar4 = new Morohtar();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Morohtar4.AttackDamage() <= 16) && (Morohtar4.AttackDamage() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}

}
