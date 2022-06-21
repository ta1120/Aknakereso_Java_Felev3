package aknakereso;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MarkerTest {

	GameInstance testGame;
	GameFrame testFrame;
	
	@Before
	public void setUp()
	{
		testFrame = new GameFrame();
		testGame = new GameInstance(5,0,testFrame);
		testGame.setFirstClick(false);
	}
	
	@Test
	public void testMarking()
	{
		testGame.markCell(1,1);
		assertEquals("cell marked",9,testGame.getDisplayFieldValue(1, 1));
		assertEquals("marked cells not null",1,testGame.getMarkedCells());
		testGame.markCell(1,1);
		assertEquals("cell unmarked",10,testGame.getDisplayFieldValue(1, 1));
		assertEquals("marked cells null",0,testGame.getMarkedCells());
	}

}
