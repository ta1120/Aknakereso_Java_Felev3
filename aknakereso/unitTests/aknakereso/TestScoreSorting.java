package aknakereso;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestScoreSorting {

	GameInstance testGame1;
	GameInstance testGame2;
	GameInstance testGame3;
	GameInstance testGame4;
	GameFrame testFrame;
	ScoresData sData;
	
	@Before
	public void setUp()
	{
		testFrame = new GameFrame();
		testGame1 = new GameInstance(5,0,testFrame);
		testGame2 = new GameInstance(3,0,testFrame);
		testGame3 = new GameInstance(10,1,testFrame);
		testGame4 = new GameInstance(10,5,testFrame);
		ArrayList<ScoreInstance> s = new ArrayList<ScoreInstance>();
		s.add(new ScoreInstance(testGame1));
		s.add(new ScoreInstance(testGame2));
		s.add(new ScoreInstance(testGame3));
		s.add(new ScoreInstance(testGame4));
		sData = new ScoresData(s);
		
	}
	
	@Test
	public void testSortment()
	{
		assertEquals("first before sort",5,sData.getResults().get(0).getN());
		assertEquals("second before sort",3,sData.getResults().get(1).getN());
		assertEquals("third before sort",10,sData.getResults().get(2).getN());
		assertEquals("third before sort/2",1,sData.getResults().get(2).getK());
		assertEquals("fourth before sort",10,sData.getResults().get(3).getN());
		assertEquals("fourth before sort/2",5,sData.getResults().get(3).getK());
		sData.sortScores();
		assertEquals("first after sort",10,sData.getResults().get(0).getN());
		assertEquals("first after sort/2",5,sData.getResults().get(0).getK());
		assertEquals("second after sort",10,sData.getResults().get(1).getN());
		assertEquals("second after sort/2",1,sData.getResults().get(1).getK());
		assertEquals("third after sort",5,sData.getResults().get(2).getN());
		assertEquals("fourth after sort",3,sData.getResults().get(3).getN());
	}

}
