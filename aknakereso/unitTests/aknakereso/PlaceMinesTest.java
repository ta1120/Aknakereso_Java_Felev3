package aknakereso;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlaceMinesTest {

	Minefield testField;
	
	@Before
	public void setUp() 
	{
		testField = new Minefield(3,1,0,0); /*Itt letrejon ugyan egy aknamezo, de ezt a kovetkezo metodusok felul fogjak irni*/ 
		testField.createStructure(5, 16);
	}
	
	@Test
	public void testPlacement() {
		testField.placeMines(2,2);
		assertEquals("0,0 must be bomb",-1,testField.getFieldValue(0, 0));
		assertEquals("1,1 must be empty",0,testField.getFieldValue(1, 1));
		assertEquals("2,2 must be empty",0,testField.getFieldValue(2, 2));
		assertEquals("3,3 must be empty",0,testField.getFieldValue(3, 3));
		assertEquals("4,4 must be bomb",-1,testField.getFieldValue(4, 4));
		assertEquals("0,4 must be bomb",-1,testField.getFieldValue(0, 4));
		assertEquals("4,0 must be bomb",-1,testField.getFieldValue(4, 0));
	}

}
