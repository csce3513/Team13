package oldmainwars;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Character
{
	//constructor
    public Character(int type, int team)
    {
        Image sheet = null;
        Image down[] = new Image[3];

        switch (type)
        {
        	//initialize Warrior Super hero stats
            case OldMainWars.Dante:
            {
            	name = "Dante";
                MaxHP = 120;
                HP = MaxHP;
                MaxMP = 0;
                MP = MaxMP;
                PhysicalMultiplier = 0.90;
                MagicMultiplier = 0.00;
                MovementRange = 3;
                AttackRange = 1;
                MagicRange = -1;
                HealRange = -1;

                if (team == 1)
                    sheet = GamePlay.danteSheet1;
                else //if (team == 2)
                    sheet = GamePlay.danteSheet2;

                //clip the three down facing sprites
                down[0] = sheet.getSubImage(4, 138, 44, 48);
                down[1] = sheet.getSubImage(48, 138, 44, 48);
                down[2] = sheet.getSubImage(98, 138, 44, 48);

                animation = new Animation(down, 200); //second number is "speed" of animation
            }
            break;
            
            //initialize Warrior hero stats
            case OldMainWars.Warrior:
            {
            	name = "Warrior";
                MaxHP = 100;
                HP = MaxHP;
                MaxMP = 0;
                MP = MaxMP;
                PhysicalMultiplier = 0.75;
                MagicMultiplier = 0.00;
                MovementRange = 3;
                AttackRange = 1;
                MagicRange = -1;
                HealRange = -1;

                if (team == 1)
                    sheet = GamePlay.warriorSheet1;
                else //if (team == 2)
                    sheet = GamePlay.warriorSheet2;

                down[0] = sheet.getSubImage(4, 138, 39, 49);
                down[1] = sheet.getSubImage(54, 138, 32, 49);
                down[2] = sheet.getSubImage(97, 138, 39, 49);

                animation = new Animation(down, 200);
            }
            break;
            
            //initialize Archer Super hero stats
            case OldMainWars.Morohtar:
            {
            	name = "Morohtar";
                MaxHP = 105;
                HP = MaxHP;
                MaxMP = 70;
                MP = MaxMP;
                PhysicalMultiplier = 0.80;
                MagicMultiplier = 0.60;
                MovementRange = 2;
                AttackRange = 3;
                MagicRange = 2;
                HealRange = 1;

                if (team == 1)
                    sheet = GamePlay.morohtarSheet1;
                else //if (team == 2)
                    sheet = GamePlay.morohtarSheet2;
                
                down[0] = sheet.getSubImage(10, 144, 29, 43);
                down[1] = sheet.getSubImage(54, 142, 32, 45);
                down[2] = sheet.getSubImage(103, 144, 29, 43);

                animation = new Animation(down, 350);
            }
            break;
            
            //initialize Archer hero stats
            case OldMainWars.Archer:
            {
            	name = "Archer";
                MaxHP = 85;
                HP = MaxHP;
                MaxMP = 60;
                MP = MaxMP;
                PhysicalMultiplier = 0.60;
                MagicMultiplier = 0.50;
                MovementRange = 2;
                AttackRange = 3;
                MagicRange = 2;
                HealRange = 1;

                if (team == 1)
                    sheet = GamePlay.archerSheet1;
                else //if (team == 2)
                    sheet = GamePlay.archerSheet2;

                down[0] = sheet.getSubImage(12, 140, 27, 47);
                down[1] = sheet.getSubImage(56, 139, 30, 48);
                down[2] = sheet.getSubImage(103, 141, 29, 46);

                animation = new Animation(down, 350);
            }
            break;
            
            //initialize Mage Super hero stats
            case OldMainWars.Mordecai:
            {
            	name = "Mordecai";
                MaxHP = 95;
                HP = MaxHP;
                MaxMP = 120;
                MP = MaxMP;
                PhysicalMultiplier = 0.50;
                MagicMultiplier = 0.95;
                MovementRange = 2;
                AttackRange = 2;
                MagicRange = 3;
                HealRange = 3;

                if (team == 1)
                    sheet = GamePlay.mordecaiSheet1;
                else //if (team == 2)
                    sheet = GamePlay.mordecaiSheet2;

                down[0] = sheet.getSubImage(7, 142, 31, 45);
                down[1] = sheet.getSubImage(50, 140, 36, 45);
                down[2] = sheet.getSubImage(98, 142, 33, 45);

                animation = new Animation(down, 500);
            }
            break;
            
            //initialize Mage hero stats
            case OldMainWars.Mage:
            default:
            {
            	name = "Mage";
                MaxHP = 75;
                HP = MaxHP;
                MaxMP = 100;
                MP = MaxMP;
                PhysicalMultiplier = 0.50;
                MagicMultiplier = 0.85;
                MovementRange = 2;
                AttackRange = 2;
                MagicRange = 3;
                HealRange = 3;

                if (team == 1)
                    sheet = GamePlay.mageSheet1;
                else //if (team == 2)
                    sheet = GamePlay.mageSheet2;

                down[0] = sheet.getSubImage(8, 136, 33, 53);
                down[1] = sheet.getSubImage(49, 134, 38, 53);
                down[2] = sheet.getSubImage(98, 136, 36, 53);

                animation = new Animation(down, 500);
            }
            break;
        }

        xCoordinate = 0;
        yCoordinate = 0;
        selected = false;
    }

    //function that decreases health
    public void Damage(int DamageTaken)
    {
        HP -= DamageTaken;
    }

    //function that increases health
    public void Healed(int Heal)
    {
        double TotalHP = HP + Heal;

        if(TotalHP >= MaxHP)
        {
            HP = MaxHP;
        }
        else
        {
            HP = (int)TotalHP;
        }
    }

    //function to check if they have enough magic point
    public boolean EnoughMP(int cost)
    {
        if (MP >= cost)
            return true;
        else
            return false;
    }

    //function that deals magic damage
    public int CastSpell()
    {
        double MagicDamage = 0;
        int cost = 10;

        Random Attack = new Random();
        if (EnoughMP(cost))
        {
            MagicDamage = (Attack.nextInt(10) + 20) * MagicMultiplier;
            MP -= cost;
        }

        return (int)MagicDamage;
    }

    //function that deals melee damage
    public int  AttackDamage()
    {
        Random Attack = new Random();
        double DamageGiven = (Attack.nextInt(10) + 20) * PhysicalMultiplier;
        return (int)DamageGiven;
    }

    //function to draw animation
    public void draw()
    {
        animation.draw(xCoordinate, yCoordinate);
    }

    //function to draw interface
    public void drawHUD(Graphics g)
    {
        animation.draw(120, 695);
        g.drawString(getName(), 205, 685);
        g.drawString("HP: " + getHP() + "/" + getMaxHP(), 205, 705);
        g.drawString("MP: " + getMP() + "/" + getMaxMP(), 205, 725);
    }

    //function that gets name
    public String getName()
    {
    	return name;
    }
    
    //function that sets name
    public void setName(String charName)
    {
    	name = charName;
    }
    
    //function returns health points
    public int getHP()
    {
        return HP;
    }

    //returns max health points
    public int getMaxHP()
    {
        return MaxHP;
    }

    //returns magic points
    public int getMP()
    {
        return MP;
    }

    //returns max magic points
    public int getMaxMP()
    {
        return MaxMP;
    }

    //returns magic multiplier
    public double getMM()
    {
        return MagicMultiplier;
    }

    //returns physical multipier
    public double getPM()
    {
        return PhysicalMultiplier;
    }

    //returns where character can move
    public int getMovement()
    {
        return MovementRange;
    }

    //returns attack range
    public int getAttackRange()
    {
        return AttackRange;
    }

    //returns magic range
    public int getMagicRange()
    {
        return MagicRange;
    }

    //returns heal range
    public int getHealRange()
    {
        return HealRange;
    }

    //returns x coordinate
    public int getXCoordinate()
    {
        return xCoordinate;
    }

    //returns y coordinate
    public int getYCoordinate()
    {
        return yCoordinate;
    }

    //function to set coordinates
    public void setCoordinates(int x, int y)
    {
        xCoordinate = x;
        yCoordinate = y;
    }

    //checks if character is selected
    public boolean isSelected()
    {
        return selected;
    }

    //function that sets selected character to true
    public void setSelectedTrue()
    {
        selected = true;
    }

  //function that sets unselected character to false
    public void setSelectedFalse()
    {
        selected = false;
    }

    //function to check if character is still alive
    public boolean isAlive()
    {
        if (HP > 0)
            return true;
        else
            return false;
    }

    //initialize variables
    private int HP;
    private int MP;
    private final int MaxHP;
    private final int MaxMP;
    private double PhysicalMultiplier;
    private double MagicMultiplier;
    private final int MovementRange;
    private final int AttackRange;
    private final int MagicRange;
    private final int HealRange;
    private String name;

    private Animation animation = null;
    private int xCoordinate;
    private int yCoordinate;
    private boolean selected; //whether unit is currently selected or not
}
