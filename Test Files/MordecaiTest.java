package oldmainwars;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MordecaiTest {

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
	public void testMordecai() 
	{
		Mordecai Mordecai1 = new Mordecai();
		
		assertEquals(Mordecai1.getMordecaiHP(),95);
		assertEquals(Mordecai1.getMordecaiMP(),120);
		assertEquals(Mordecai1.getMordecaiMM(), 0.95, 0);
		assertEquals(Mordecai1.getMordecaiPM(), 0.50, 0);
                assertEquals(Mordecai1.getMovement(), 2);
	}

	@Test
	public void testDamage() 
	{
		Mordecai Mordecai2 = new Mordecai();
		Mordecai2.Damage(10);
		assertEquals(Mordecai2.getMordecaiHP(), 85);
		
		Mordecai2.Damage(20);
		assertEquals(Mordecai2.getMordecaiHP(), 65);
	}

	@Test
	public void testHealed() 
	{
		Mordecai Mordecai3 = new Mordecai();
		Mordecai3.Healed(20);
		assertEquals(Mordecai3.getMordecaiHP(), 95);
		
		Mordecai3.Damage(20);
		Mordecai3.Healed(20);
		assertEquals(Mordecai3.getMordecaiHP(), 95);
		
		Mordecai3.Damage(50);
		Mordecai3.Healed(20);
		assertEquals(Mordecai3.getMordecaiHP(), 65);
	}

	@Test
	public void testCastSpell() 
	{
		Mordecai Mordecai5 = new Mordecai();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Mordecai5.CastSpell() <= 19) && (Mordecai5.CastSpell() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}

        @Test
        public void testEnoughMP()
        {
            Mordecai m = new Mordecai();
            assertEquals(m.EnoughMP(120), true);
            assertEquals(m.EnoughMP(121), false);
        }

	@Test
	public void testAttackDamage() 
	{
		Mordecai Mordecai4 = new Mordecai();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((Mordecai4.AttackDamage() <= 10) && (Mordecai4.AttackDamage() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}
}
