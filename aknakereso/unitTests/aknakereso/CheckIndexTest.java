package aknakereso;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckIndexTest {

	Minefield testField1;
	Minefield testField2;
	
	@Before
	public void setUp()
	{
		testField1 = new Minefield(3,0,0,0); /*A konstruktorban letrehozott mezot a kovetkezo fuggveny felulirja*/
		testField2 = new Minefield(3,0,0,0); /*A konstruktorban letrehozott mezot a kovetkezo fuggveny felulirja*/
		testField1.createStructure(10, 1);
		testField2.createStructure(5, 1);
	}
	
	@Test
	public void testIndexRestraints() {
		assertEquals("i=9 when n=10",true,testField1.checkIndex(9));
		assertEquals("i=9 when n=5",false,testField2.checkIndex(9));
		assertEquals("i=3 when n=10",true,testField1.checkIndex(3));
		assertEquals("i=3 when n=5",true,testField2.checkIndex(3));
		assertEquals("i=10 when n=10",false,testField1.checkIndex(10));
		assertEquals("i=-1 when n=10",false,testField1.checkIndex(-1));
		assertEquals("i=-1 when n=5",false,testField1.checkIndex(-1));
	}

}
