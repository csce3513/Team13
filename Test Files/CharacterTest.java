/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
    Dante = 0;
    Warrior = 1;
    Morohtar = 2;
    Archer = 3;
    Mordecai = 4;
    Mage = 5;
 */

package oldmainwars;

import org.newdawn.slick.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tests.*;

/**
 *
 * @author Administrator
 */
public class CharacterTest
{



    public CharacterTest() {
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
    public void testConstructor()
    {
        Character dante = new Character(OldMainWars.Dante,1);
        Character warrior = new Character(1, 1);
        Character morohtar = new Character(OldMainWars.Morohtar,1);
        Character archer = new Character(3, 1);
        Character mordecai = new Character(OldMainWars.Mordecai,1);
        Character mage = new Character(OldMainWars.Mage,1);

        assertEquals(dante.getName(), "Dante");
        assertEquals(dante.getHP(), 120);
        assertEquals(dante.getMP(), 0);
        assertEquals(dante.getPM(), 0.90, 0);
        assertEquals(dante.getMM(), 0, 0);
        assertEquals(dante.getMovement(), 3);
        assertEquals(dante.getAttackRange(), 1);

        assertEquals(warrior.getName(), "Warrior");
        assertEquals(warrior.getHP(), 100);
        assertEquals(warrior.getMP(), 0);
        assertEquals(warrior.getPM(), 0.75, 0);
        assertEquals(warrior.getMM(), 0, 0);
        assertEquals(warrior.getMovement(), 3);
        assertEquals(warrior.getAttackRange(), 1);

        assertEquals(morohtar.getName(), "Morohtar");
        assertEquals(morohtar.getHP(), 105);
        assertEquals(morohtar.getMP(), 70);
        assertEquals(morohtar.getPM(), 0.80, 0);
        assertEquals(morohtar.getMM(), 0.60, 0);
        assertEquals(morohtar.getMovement(), 2);
        assertEquals(morohtar.getAttackRange(), 3);

        assertEquals(archer.getName(), "Archer");
        assertEquals(archer.getHP(), 85);
        assertEquals(archer.getMP(), 60);
        assertEquals(archer.getPM(), 0.60, 0);
        assertEquals(archer.getMM(), 0.50, 0);
        assertEquals(archer.getMovement(), 2);
        assertEquals(archer.getAttackRange(), 3);

        assertEquals(mordecai.getName(), "Mordecai");
        assertEquals(mordecai.getHP(), 95);
        assertEquals(mordecai.getMP(), 120);
        assertEquals(mordecai.getPM(), 0.50, 0);
        assertEquals(mordecai.getMM(), 0.95, 0);
        assertEquals(mordecai.getMovement(), 2);
        assertEquals(mordecai.getAttackRange(), 2);

        assertEquals(mage.getName(), "Mage");
        assertEquals(mage.getHP(), 75);
        assertEquals(mage.getMP(), 100);
        assertEquals(mage.getPM(), 0.50, 0);
        assertEquals(mage.getMM(), 0.85, 0);
        assertEquals(mage.getMovement(), 2);
        assertEquals(mordecai.getAttackRange(), 2);
    }

    @Test
    public void testDamage()
    {
        Character warrior = new Character(1, 1);
        warrior.Damage(10);
        assertEquals(warrior.getHP(), 90);

        warrior.Damage(20);
        assertEquals(warrior.getHP(), 70);
        
        warrior.Damage(50);
        assertEquals(warrior.getHP(), 20);
    }

    @Test
    public void testHealed()
    {
        Character mage = new Character(5, 1);
        mage.Healed(20);
        assertEquals(mage.getHP(), 75);

        mage.Damage(20);
        mage.Healed(20);
        assertEquals(mage.getHP(), 75);

        mage.Damage(50);
        mage.Healed(20);
        assertEquals(mage.getHP(), 45);
    }

    @Test
    public void testCastSpell()
    {
        Character archer = new Character(OldMainWars.Archer, 1);
        int numTrue = 0;

        for(int x = 0; x <= 100; x++)
        {
            //damage should be between 0 and 10
            if((archer.CastSpell() <= 10) && (archer.CastSpell() >= 0))
                numTrue++;
        }

        assertEquals(numTrue, 101);
    }

    @Test
    public void testEnoughMP()
    {
        Character mordecai = new Character(4, 1);
        int count = 0;

        //mordecai has 120 MP so should only be able to cast spell 12 times before out of MP
        while (mordecai.EnoughMP(10))
        {
            mordecai.CastSpell();
            count++;
        }

        assertEquals(count, 12);
    }

    @Test
    public void testAttackDamage()
    {
        Character dante = new Character(0, 1);
        int numTrue = 0;

        int damage;
        int average = 0;

        for(int x = 0; x <= 100; x++)
        {
            damage = dante.AttackDamage();
            //dante's damage should be between 0 and 18
            if((damage <= 18) && (damage >= 0))
            {
                average += damage;
                numTrue++;
            }
        }

        average /= 101;
        //System.out.println(average + " damage average");

        assertEquals(numTrue, 101);
    }
}