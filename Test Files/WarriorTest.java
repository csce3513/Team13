package oldmainwars;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WarriorTest {

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
	public void testWarrior() 
	{
		Warrior War1 = new Warrior();
		assertEquals(War1.getWarriorHP(), 100);
		assertEquals(War1.getWarriorPM(), 0.75, 0);
                assertEquals(War1.getMovement(), 3);
	}

	@Test
	public void testDamage() 
	{
		Warrior War2 = new Warrior();
		War2.Damage(10);
		assertEquals(War2.getWarriorHP(), 90);
		
		War2.Damage(20);
		assertEquals(War2.getWarriorHP(), 70);
		
		War2.Damage(50);
		assertEquals(War2.getWarriorHP(), 20);
	}

	@Test
	public void testHealed() 
	{
		Warrior War3 = new Warrior();
		War3.Healed(20);
		assertEquals(War3.getWarriorHP(), 100);
		
		War3.Damage(20);
		War3.Healed(20);
		assertEquals(War3.getWarriorHP(), 100);
		
		War3.Damage(50);
		War3.Healed(20);
		assertEquals(War3.getWarriorHP(), 70);
	}

	@Test
	public void testAttackDamage() 
	{
		Warrior War4 = new Warrior();
		int numTrue = 0;
		
		for(int x = 0; x <= 100; x++)
		{
			if((War4.AttackDamage() <= 15) && (War4.AttackDamage() >= 0))
				numTrue++;
		}
		
		assertEquals(numTrue, 101);
	}
}
