package oldmainwars;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayTest 
{
	GamePlay gp = new GamePlay(3);
	@Test
	public void testresetTimer() 
	{
		gp.resetTimer();
		assertEquals(0,gp.timer);
	}

}
