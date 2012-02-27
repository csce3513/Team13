package oldmainwars;

import java.util.Random;

public class Archer 
{
	public Archer()
	{
		ArcherHP = 85;
		ArcherMP = 60;
		PhysicalMultiplier = 0.60;
		MagicMultiplier = 0.50;
	}
	
	public void Damage(int DamageTaken)
	{
		ArcherHP -= DamageTaken;
	}
	
	public void Healed(int Heal)
	{
		double TotalHP = ArcherHP + Heal;
		
		if(TotalHP >= 85)
		{
			ArcherHP = 85;
		}
				
		else
		{
			ArcherHP = (int)TotalHP;
		}
	}
	
	public int CastSpell()
	{
		double MagicDamage = 0;
		
		Random Attack = new Random();
		MagicDamage = (Attack.nextInt(20) + 1) * MagicMultiplier;
		ArcherMP -= 10;
		
		return (int)MagicDamage;
	}
	
	public int  AttackDamage()
	{
		Random Attack = new Random();
		DamageGiven = (Attack.nextInt(20) + 1) * PhysicalMultiplier;
		return (int)DamageGiven;
	}
	
	public int getArcherHP()
	{
		return ArcherHP;
	}
	
	public int getArcherMP()
	{
		return ArcherMP;
	}
	
	public double getArcherMM()
	{
		return MagicMultiplier;
	}
	
	public double getArcherPM()
	{
		return PhysicalMultiplier;
	}

	private int ArcherHP;
	private int ArcherMP;
	private double MagicMultiplier;
	private double PhysicalMultiplier;
	private double DamageGiven;
}
