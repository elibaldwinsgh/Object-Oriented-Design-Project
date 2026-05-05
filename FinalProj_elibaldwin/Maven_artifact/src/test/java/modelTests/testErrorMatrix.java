package modelTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import model.ErrorMatrix;
import tech.tablesaw.api.Table;

/**
 * Tests the methods in the ErrorMatrix class. Test creating a column, aka a string array of landcover descriptions. 
 * Tests the createMatrix method by creating an error matrix and then assertEquals for each value in that table going by column
 */
class testErrorMatrix {
	//setup variables
	ErrorMatrix errMat = new ErrorMatrix();
	public static HashMap<Integer, String> classKey = new HashMap<Integer, String>();
	static String[] gt = {"water", "veg", "built","water", "veg", "built","water", "veg", "built"};
	static String[] lcc = {"water", "veg", "built", "built", "water", "veg", "veg", "built", "water"};

	public static Table theTable;
	
	/**
	 * Setup many of the objects needed for testing 
	 */
	@BeforeAll
	static void setup() {
		ErrorMatrix errMat = new ErrorMatrix();
		classKey.put(1, "water");
		classKey.put(2, "veg");
		classKey.put(3, "built");
		//System.out.println(classKey);
		theTable = errMat.createTable(gt, lcc);
	}

	@Test
	void testCreateTableColZero() {

		assertEquals("built", theTable.get(0,0));
		assertEquals("veg", theTable.get(1,0));
		assertEquals("water", theTable.get(2,0));
		assertEquals("Total", theTable.get(3,0));
	}
	@Test
	void testCreateTableColOne() {
		
		assertEquals(1, theTable.get(0,1));
		assertEquals(1, theTable.get(1,1));
		assertEquals(1, theTable.get(2,1));
		assertEquals(3, theTable.get(3,1));
	}
	@Test
	void testCreateTableColTwo() {
		
		assertEquals(1, theTable.get(0,2));
		assertEquals(1, theTable.get(1,2));
		assertEquals(1, theTable.get(2,2));
		assertEquals(3, theTable.get(3,2));
	}
	@Test
	void testCreateTableColThree() {
		
		assertEquals(1, theTable.get(0,3));
		assertEquals(1, theTable.get(1,3));
		assertEquals(1, theTable.get(2,3));
		assertEquals(3, theTable.get(3,3));
	}
	@Test
	void testCreateTableColFour() {
		
		assertEquals(3, theTable.get(0,4));
		assertEquals(3, theTable.get(1,4));
		assertEquals(3, theTable.get(2,4));
		assertEquals(9, theTable.get(3,4));
	}

}
