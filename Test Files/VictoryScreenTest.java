package oldmainwars;

import static org.junit.Assert.*;

import org.junit.Test;

public class VictoryScreenTest{
	
	GamePlay gp = new GamePlay(3);
	MapSelection mapSelect = new MapSelection(3);
    VictoryScreen vic = new VictoryScreen(3);


	@Test
	public void testVictor() {
		gp.setVictor(VictoryScreen.team1);
		assertEquals(1,gp.getVictor());
		
		gp.setVictor(VictoryScreen.team2);
		assertEquals(2,gp.getVictor());
	}
	


}
