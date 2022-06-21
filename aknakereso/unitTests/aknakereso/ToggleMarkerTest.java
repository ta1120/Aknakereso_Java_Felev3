package aknakereso;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ToggleMarkerTest 
{
	
	GameInstance testGame;
	GameFrame testFrame;
	
	@Before
	public void setUp()
	{
		testFrame = new GameFrame();
		testGame = new GameInstance(3,1,testFrame);
		testGame.setFirstClick(false);
	}
	
	@Test
	public void toggleTest()
	{
		assertEquals("baseline",false,testGame.isMarker());
		testGame.toggleMarker();
		assertEquals("first toggle",true,testGame.isMarker());
		testGame.toggleMarker();
		assertEquals("second toggle",false,testGame.isMarker());
		testGame.toggleMarker();
		assertEquals("third toggle",true,testGame.isMarker());
	}
}
