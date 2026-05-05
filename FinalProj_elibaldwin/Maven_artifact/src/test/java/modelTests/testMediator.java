package modelTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import model.Mediator;
import model.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class testMediator {
	static Mediator mediator;
	static Model model;
	static int[][] gtArray = {{1,2,3},
			   			      {1,2,3},
			   			      {1,2,3}};
	static int[][] lccArray = {{1,2,3},
							   {3,1,2},
							   {2,3,1}};
	
	public static HashMap<Integer, String> classKey = new HashMap<Integer, String>();
	
	@BeforeAll
	static void setup() {
		classKey.put(1, "water");
		classKey.put(2, "veg");
		classKey.put(3, "built");
		model = new Model(gtArray, lccArray, classKey);
		mediator = new Mediator(model);
	}


	@Test
	void testCreateColumnLCC() {
		//System.out.println(classKey);
		String[] correctArray = {"water", "veg", "built", "built", "water", "veg", "veg", "built", "water"};
		//System.out.println(gtArray[0][0]);
		assertArrayEquals(correctArray, mediator.createColumn(lccArray, classKey));
	}
	
	@Test
	void testCreateColumnGT() {
		//System.out.println(classKey);
		String[] correctArray = {"water", "veg", "built", "water", "veg", "built", "water", "veg", "built"};
		//System.out.println(gtArray[0][0]);
		assertArrayEquals(correctArray, mediator.createColumn(gtArray, classKey));
	}
}
