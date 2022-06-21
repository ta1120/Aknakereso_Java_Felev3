package aknakereso;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UncoverTest 
{
	GameInstance testGame;
	GameFrame testFrame;
	
	@Before
	public void setUp()
	{
		testFrame = new GameFrame();
		testGame = new GameInstance(3,5,testFrame);
		testGame.createMinefield(2,2);
	}
	
	@Test
	public void uncoverTest()
	{
		testGame.uncover(2,2);
		assertEquals("cell empty",0,testGame.getDisplayFieldValue(2,2));
		assertEquals("cell 2,1",2,testGame.getDisplayFieldValue(2,1));
		assertEquals("cell 1,2",2,testGame.getDisplayFieldValue(1,2));
		assertEquals("cell 1,1",5,testGame.getDisplayFieldValue(1,1));
		assertEquals("cell unknown",10,testGame.getDisplayFieldValue(0,0));
		
	}
}
