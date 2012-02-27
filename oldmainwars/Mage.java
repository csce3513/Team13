package oldmainwars;

import java.util.Random;

public class Mage 
{
	public Mage()
	{
		MageHP = 75;
		MageMP = 100;
		PhysicalMultiplier = 0.50;
		MagicMultiplier = 0.85;
	}
	
	public void Damage(int DamageTaken)
	{
		MageHP -= DamageTaken;
	}
	
	public void Healed(int Heal)
	{
		double TotalHP = MageHP + Heal;
		
		if(TotalHP >= 75)
		{
			MageHP = 75;
		}
				
		else
		{
			MageHP = (int)TotalHP;
		}
	}
	
	public int CastSpell()
	{
		double MagicDamage = 0;
		
		Random Attack = new Random();
		MagicDamage = (Attack.nextInt(20) + 1) * MagicMultiplier;
		MageMP -= 10;
		
		return (int)MagicDamage;
	}
	
	public int  AttackDamage()
	{
		Random Attack = new Random();
		DamageGiven = (Attack.nextInt(20) + 1) * PhysicalMultiplier;
		return (int)DamageGiven;
	}
	
	public int getMageHP()
	{
		return MageHP;
	}
	
	public int getMageMP()
	{
		return MageMP;
	}
	
	public double getMageMM()
	{
		return MagicMultiplier;
	}
	
	public double getMagePM()
	{
		return PhysicalMultiplier;
	}

	private int MageHP;
	private int MageMP;
	private double MagicMultiplier;
	private double PhysicalMultiplier;
	private double DamageGiven;
}
