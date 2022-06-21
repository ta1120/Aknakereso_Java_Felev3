package aknakereso;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MinesMarkedTest 
{
	GameInstance testGame;
	GameFrame testFrame;
	
	@Before
	public void setUp()
	{
		testFrame = new GameFrame();
		testGame = new GameInstance(3,5,testFrame);
		testGame.createMinefield(2,2);
		testGame.setFirstClick(false);
	}
	
	@Test
	public void minesMarkedTest()
	{
		testGame.markCell(0,0);
		testGame.markCell(1,1);
		testGame.markCell(2,2);
		testGame.markCell(0,2);
		assertEquals("2 mines marked",2,testGame.getMinesMarked());
	}
}