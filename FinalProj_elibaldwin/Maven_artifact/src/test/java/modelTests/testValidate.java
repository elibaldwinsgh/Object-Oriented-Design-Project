package modelTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import model.*;

/**
 * tests the Validate class in situations with valid and invalid data, including invalid rows, columns and values
 */
class testValidate {
	Validate valid = new Validate();
	int[][] gtArray= {{1,2,3},
            		  {1,2,3},
            		  {1,2,3}};
	int[][] lccArray= {{1,2,3},
  		   		       {1,2,3},
  		   		       {1,2,3}};

	@Test
	void validateArraysValid() {
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		
		assertTrue(valid.validateArrays(new Model(gtArray, lccArray, classKey)));
	}
	@Test
	void validateArraysRowsWrong() {
		int[][] gtArray= {{1,2,3},
                		  {1,2,3},
                		  {1,2,3}};
		int[][] lccArray= {{1,2,3},
      		   			   {1,2,3}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();

		assertFalse(valid.validateArrays(new Model(gtArray, lccArray, classKey)));
	}
	@Test
	void validateArraysColumnsWrong() {
		int[][] gtArray= {{1,2},
                	      {1,2},
                	      {1,2}};
		int[][] lccArray= {{1,2,3},
      		   	           {1,2,3},
      		   	           {1,2,3}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();

		assertFalse(valid.validateArrays(new Model(gtArray, lccArray, classKey)));
	}
	@Test
	void validateArraysBothWrong() {
		int[][] gtArray= {{1,2,3},
                		  {1,2,3}};
		int[][] lccArray= {{1,2,3,4},
      		   		       {1,2,3,4},
      		   		       {1,2,3,4}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();

		assertFalse(valid.validateArrays(new Model(gtArray, lccArray, classKey)));
	}
	@Test
	void validateValuesTrue() {
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		classKey.put(1,"water");
		classKey.put(2, "veg");
		classKey.put(3, "built");
		assertTrue(valid.validateValues(new Model(gtArray, lccArray, classKey)));
	}
	@Test
	void validateValuesFalse() {
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		classKey.put(1,"water");
		classKey.put(2, "veg");
		assertFalse(valid.validateValues(new Model(gtArray, lccArray, classKey)));
	}

}
