package aknakereso;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MinesLeftStatusBarTest
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
	public void minesLeftTest()
	{
		assertEquals("minesleft = k",5,testGame.getBar().mines);
		testGame.markCell(0,0);
		testGame.markCell(1,1);
		testGame.markCell(2,2);
		testGame.markCell(0,2);
		assertEquals("minesleft = k-4",1,testGame.getBar().mines);
		testGame.markCell(2,2);
		testGame.markCell(0,2);
		assertEquals("minesleft = k-4+2",3,testGame.getBar().mines);
	}
}