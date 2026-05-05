package modelTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import model.ClassStats;

class testClassStats {

	String[] referenceStrings = {"water", "water", "water", "forest", "forest", "forest", "urban", "urban", "urban"};
	String[] comparisonStrings = {"water", "forest", "water", "water", "forest", "urban", "water", "forest", "urban"};
	
	ClassStats waterStats = new ClassStats("water", referenceStrings, comparisonStrings);
	//ClassStats forestStats = new ClassStats("forest", referenceStrings, comparisonStrings);
	
	
	@Test
	void testTabulate() {
		assertEquals(2.0, waterStats.getHits(), 0.1);
		assertEquals(1.0, waterStats.getMisses(), 0.1);
		assertEquals(2.0, waterStats.getFAs(), 0.1);
		assertEquals(4.0, waterStats.getCRs(), 0.1);
	}
	
	@Test
	void testCreateContingencyTable() {
		assertEquals("Presence", waterStats.getContingencyTable().get(0, 0));
		assertEquals("Absence", waterStats.getContingencyTable().get(1, 0));
		
		assertEquals((float)2.0, waterStats.getContingencyTable().get(0, 1));
		assertEquals((float)2.0, waterStats.getContingencyTable().get(1, 1));
		
		assertEquals((float)1.0, waterStats.getContingencyTable().get(0, 2));
		assertEquals((float)4.0, waterStats.getContingencyTable().get(1, 2));
	}
	
	@Test
	void testCalculatePrecision() {
		assertEquals(0.50, waterStats.getPrecision(), 0.01);
	}
	
	@Test
	void testCalculateRecall() {
		assertEquals(0.66, waterStats.getRecall(), 0.01);
	}

	@Test 
	void testCalculateF1(){
		double expected = 2 * ((0.5000 * 0.6666) / (0.5000 + 0.6666));
		assertEquals(expected, waterStats.getF1(), 0.001);
	}
}
