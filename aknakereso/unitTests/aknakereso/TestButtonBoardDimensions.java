package aknakereso;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;

public class TestButtonBoardDimensions {

	GameInstance testGame;
	GameFrame testFrame;
	
	@Before
	public void setUp()
	{
		testFrame = new GameFrame();
		testGame = new GameInstance(10,0,testFrame);
	}
	
	@Test
	public void testDimensions()
	{
		List<List<JButton>> board = testGame.getBoard();
		assertEquals("board length = n",10,board.get(0).size());
		assertEquals("board depth = n",10,board.size());
	}

}
