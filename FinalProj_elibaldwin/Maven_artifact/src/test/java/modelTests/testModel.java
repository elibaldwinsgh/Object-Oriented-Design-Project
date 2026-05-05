package modelTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import model.*;

/**
 * Tests that the validMatrix method is correctly outputting the right boolean when both methods it calls are valid,
 * one is valid and the other is not and vice versa, and when both are invalid.
 */
class testModel {

	@Test
	void validMatrixValid() {
		int[][] gtArray= {{1,2,3},
      		  		      {1,2,3},
      		  		      {1,2,3}};
		int[][] lccArray= {{1,2,3},
	   		       		   {1,2,3},
	   		       		   {1,2,3}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		classKey.put(1,"water");
		classKey.put(2, "veg");
		classKey.put(3, "built");
		Model testModel = new Model(gtArray, lccArray, classKey);
		assertTrue(testModel.validMatrix());
	}
	@Test
	void validMatrixWrongRC() {
		int[][] gtArray= {{1,2,3},
      		  		      {1,2,3},
      		  		      {1,2,3}};
		int[][] lccArray= {{1,2,3},
	   		       		   {1,2,3}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		classKey.put(1,"water");
		classKey.put(2, "veg");
		classKey.put(3, "built");
		Model testModel = new Model(gtArray, lccArray, classKey);
		assertFalse(testModel.validMatrix());
	}
	@Test
	void validMatrixWrongValues() {
		int[][] gtArray= {{1,2,3},
      		  		      {1,2,3},
      		  		      {1,2,3}};
		int[][] lccArray= {{1,2,3},
						   {1,2,3},
	   		       		   {1,2,3}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		classKey.put(1,"water");
		classKey.put(0, "veg");
		classKey.put(3, "built");
		Model testModel = new Model(gtArray, lccArray, classKey);
		assertFalse(testModel.validMatrix());
	}
	@Test
	void validMatrixWrongBoth() {
		int[][] gtArray= {{1,2,3},
      		  		      {1,2,3},
      		  		      {1,2,4}};
		int[][] lccArray= {{1,2,3},
	   		       		   {1,2,3}};
		HashMap<Integer, String> classKey = new HashMap<Integer, String>();
		classKey.put(1,"water");
		classKey.put(2, "veg");
		classKey.put(3, "built");
		Model testModel = new Model(gtArray, lccArray, classKey);
		assertFalse(testModel.validMatrix());
	}

}
