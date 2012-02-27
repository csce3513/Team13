package oldmainwars;

import java.util.*;

public class Warrior 
{
	public Warrior()
	{
		WarriorHP = 100;
		PhysicalMultiplier = 0.75;
	}
	
	public void Damage(int DamageTaken)
	{
		WarriorHP -= DamageTaken;
	}
	
	public void Healed(int Heal)
	{
		double TotalHP = WarriorHP + Heal;
		
		if(TotalHP >= 100)
		{
			WarriorHP = 100;
		}
				
		else
		{
			WarriorHP = (int)TotalHP;
		}
	}
	
	public int  AttackDamage()
	{
		Random Attack = new Random();
		DamageGiven = (Attack.nextInt(20) + 1) * PhysicalMultiplier;
		return (int)DamageGiven;
	}
	
	public int getWarriorHP()
	{
		return WarriorHP;
	}
	
	public double getWarriorPM()
	{
		return PhysicalMultiplier;
	}

	private int WarriorHP;
	private double PhysicalMultiplier;
	private double DamageGiven;
}
