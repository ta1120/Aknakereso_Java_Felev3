package aknakereso;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UpdateBoardValuesTest {

	Minefield testField;
	
	@Before
	public void setUp() 
	{
		testField = new Minefield(5,16,2,2);
	}
	
	@Test
	public void testCellValues() {
		testField.updateBoardValues();
		assertEquals("0,0 must be bomb",-1,testField.getFieldValue(0, 0));
		assertEquals("1,1 must be 5",5,testField.getFieldValue(1, 1));
		assertEquals("2,2 must be empty",0,testField.getFieldValue(2, 2));
		assertEquals("3,3 must be 5",5,testField.getFieldValue(3, 3));
		assertEquals("3,2 must be 3",3,testField.getFieldValue(3, 2));
		assertEquals("2,1 must be 3",3,testField.getFieldValue(2, 1));
		assertEquals("4,4 must be bomb",-1,testField.getFieldValue(4, 4));
		assertEquals("0,4 must be bomb",-1,testField.getFieldValue(0, 4));
		assertEquals("4,0 must be bomb",-1,testField.getFieldValue(4, 0));
	}

}
