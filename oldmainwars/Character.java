package oldmainwars;

/*
    Dante = 0;
    Warrior = 1;
    Morohtar = 2;
    Archer = 3;
    Mordecai = 4;
    Mage = 5;
 */

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Character
{
    public Character(int type)
    {
        Image down[] = new Image[3];

        switch (type)
        {
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

                Image sheet = GamePlay.danteSheet;

                //clip the three down facing sprites
                down[0] = sheet.getSubImage(4, 138, 44, 48);
                down[1] = sheet.getSubImage(48, 138, 44, 48);
                down[2] = sheet.getSubImage(98, 138, 44, 48);

                animation = new Animation(down, 200); //second number is "speed" of animation
            }
            break;
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

                Image sheet = GamePlay.warriorSheet;

                down[0] = sheet.getSubImage(4, 138, 39, 49);
                down[1] = sheet.getSubImage(54, 138, 32, 49);
                down[2] = sheet.getSubImage(97, 138, 39, 49);

                animation = new Animation(down, 200);
            }
            break;
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

                Image sheet = GamePlay.morohtarSheet;
                
                down[0] = sheet.getSubImage(10, 144, 29, 43);
                down[1] = sheet.getSubImage(54, 142, 32, 45);
                down[2] = sheet.getSubImage(103, 144, 29, 43);

                animation = new Animation(down, 350);
            }
            break;
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

                Image sheet = GamePlay.archerSheet;

                down[0] = sheet.getSubImage(12, 140, 27, 47);
                down[1] = sheet.getSubImage(56, 139, 30, 48);
                down[2] = sheet.getSubImage(103, 141, 29, 46);

                animation = new Animation(down, 350);
            }
            break;
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

                Image sheet = GamePlay.mordecaiSheet;

                down[0] = sheet.getSubImage(7, 142, 31, 45);
                down[1] = sheet.getSubImage(50, 140, 36, 45);
                down[2] = sheet.getSubImage(98, 142, 33, 45);

                animation = new Animation(down, 500);
            }
            break;
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

                Image sheet = GamePlay.mageSheet;

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

    //Has no animation, used for unit testing
    public Character(int type, boolean junk)
    {
        switch (type)
        {
            case OldMainWars.Dante:
            {
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
            }
            break;
            case OldMainWars.Warrior:
            {
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
            }
            break;
            case OldMainWars.Morohtar:
            {
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
            }
            break;
            case OldMainWars.Archer:
            {
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
            }
            break;
            case OldMainWars.Mordecai:
            {
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
            }
            break;
            case OldMainWars.Mage:
            default:
            {
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
            }
            break;
        }

        xCoordinate = 0;
        yCoordinate = 0;
        selected = false;
    }

    public void Damage(int DamageTaken)
    {
        HP -= DamageTaken;
    }

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

    public boolean EnoughMP(int cost)
    {
        if (MP >= cost)
            return true;
        else
            return false;
    }

    public int CastSpell()
    {
        double MagicDamage = 0;
        int cost = 10;

        Random Attack = new Random();
        if (EnoughMP(cost))
        {
            MagicDamage = (Attack.nextInt(20) + 1) * MagicMultiplier;
            MP -= cost;
        }

        return (int)MagicDamage;
    }

    public int  AttackDamage()
    {
        Random Attack = new Random();
        double DamageGiven = (Attack.nextInt(20) + 1) * PhysicalMultiplier;
        return (int)DamageGiven;
    }

    public void draw()
    {
        animation.draw(xCoordinate, yCoordinate);
    }

    public void drawHUD(Graphics g)
    {
        animation.draw(120, 695);
        g.drawString(getName(), 205, 685);
        g.drawString("HP: " + getHP() + "/" + getMaxHP(), 205, 705);
        g.drawString("MP: " + getMP() + "/" + getMaxMP(), 205, 725);
    }

    public String getName()
    {
    	return name;
    }
    
    public void setName(String charName)
    {
    	name = charName;
    }
    
    public int getHP()
    {
        return HP;
    }

    public int getMaxHP()
    {
        return MaxHP;
    }

    public int getMP()
    {
        return MP;
    }

    public int getMaxMP()
    {
        return MaxMP;
    }

    public double getMM()
    {
        return MagicMultiplier;
    }

    public double getPM()
    {
        return PhysicalMultiplier;
    }

    public int getMovement()
    {
        return MovementRange;
    }

    public int getAttackRange()
    {
        return AttackRange;
    }

    public int getMagicRange()
    {
        return MagicRange;
    }

    public int getHealRange()
    {
        return HealRange;
    }

    public int getXCoordinate()
    {
        return xCoordinate;
    }

    public int getYCoordinate()
    {
        return yCoordinate;
    }

    public void setCoordinates(int x, int y)
    {
        xCoordinate = x;
        yCoordinate = y;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelectedTrue()
    {
        selected = true;
    }

    public void setSelectedFalse()
    {
        selected = false;
    }

    public boolean isAlive()
    {
        if (HP > 0)
            return true;
        else
            return false;
    }

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
