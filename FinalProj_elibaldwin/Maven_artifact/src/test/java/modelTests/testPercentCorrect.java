package modelTests;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.PercentCorrect;

class testPercentCorrect {
	PercentCorrect pc = new PercentCorrect();
	
	@Test
	void testAllCorrect() {
		String[] gc = {"water", "built", "veg"};
		String[] lcc = {"water", "built", "veg"};
		pc.calcOPC(gc, lcc);
		assertEquals(pc.getOPC(), 100.0, 0.1);
	}
	
	@Test
	void testSomeCorrect() {
		String[] gc = {"water", "built", "veg"};
		String[] lcc = {"water", "built", "water"};
		pc.calcOPC(gc, lcc);
		assertEquals(pc.getOPC(), 66.6, 0.1);
	}
	
	@Test
	void testNoneCorrect() {
		String[] gc = {"water", "built", "veg"};
		String[] lcc = {"veg", "water", "built"};
		pc.calcOPC(gc, lcc);
		assertEquals(pc.getOPC(), 0.0, 0.1);
	}

}
