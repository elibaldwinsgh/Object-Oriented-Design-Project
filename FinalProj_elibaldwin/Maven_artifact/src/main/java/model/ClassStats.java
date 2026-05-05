package model;

import tech.tablesaw.api.FloatColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

/**
 * This class contains methods to calculate statistics for a single landcover class, including a contingency table of presence and absence, precision, recall and f1-score
 */
public class ClassStats {
	Table contingencyTable;
	Mediator mediator;
	float hits;
	float correctRejections;
	float misses;
	float falseAlarms;
	
	float recall;
	float precision;
	float f1score;
	
	/**
	 * This constructor calls all of the calculation functions in this class from the given class name and Mediator object.
	 * This includes counting hits, misses, false alarms and correct rejections and creating a contingency table from those results, as well
	 * as calculating the precision, recall and f1-score.
	 * @param className - a String representing the class name of the class to calculate stats for
	 * @param mediator - a Mediator object containing data for all classes
	 */
	public ClassStats(String className, String[] referenceStrings, String[] comparisonStrings) {
		this.tabulate(className, referenceStrings, comparisonStrings);
		this.createContingencyTable(className);
		this.calcPrecision();
		this.calcRecall();
		this.calcF1();
	}
	
	
	/**
	 * This method counts hits, misses, false alarms and correct rejections from two String arrays which represent landcover data and presumably
	 * come from the Mediator class in this program.
	 * @param className
	 * @param referenceStrings
	 * @param comparisonStrings
	 */
	public void tabulate(String className, String[] referenceStrings, String[] comparisonStrings) {
		hits = 0;
		misses = 0;
		falseAlarms = 0;
		correctRejections = 0;
		
		for (int i = 0; i < referenceStrings.length; i++) {
			if (referenceStrings[i].equals(className) && comparisonStrings[i].equals(className)) {
				hits++;
			}
			else if(!referenceStrings[i].equals(className) && !comparisonStrings[i].equals(className)) {
				correctRejections++;
			}
			else if(!referenceStrings[i].equals(className) && comparisonStrings[i].equals(className)) {
				falseAlarms++;
			}
			else if(referenceStrings[i].equals(className) && !comparisonStrings[i].equals(className)) {
				misses++;
			}
			else {
				//just in case
				System.err.println("a value was not successfully tabulated");
			}
		}
	}
	
	/**
	 * This class creates the contingency table of reference and comparison presence and absence from the
	 * tabulated hits, misses, false alarms and correct rejections
	 * @param className - the name of the landcover class for this instantiation of ClassStats
	 */
	public void createContingencyTable(String className) {
		float[] presence = {hits, falseAlarms};
		float[] absence = {misses, correctRejections};
		String[] rowNames = {"Presence", "Absence"};
		StringColumn rowNamesCol = StringColumn.create("reference/comparison", rowNames);
		FloatColumn hitsfaA = FloatColumn.create("Presence", presence);
		FloatColumn missCoR = FloatColumn.create("Absence", absence);
		contingencyTable = Table.create(className + " Contingency Table");
		contingencyTable.addColumns(rowNamesCol, hitsfaA, missCoR);
	}
	
	/**
	 * Calculate precision for this class, precision is the proportion of presence-in-both in comparison presence
	 */
	public void calcPrecision() {
		this.precision = this.hits / (this.hits + this.falseAlarms);
	}
	
	/**
	 * Calculate recall for this class, recall is the proportion of presence-in-both in reference presence
	 */
	public void calcRecall() {
		this.recall = this.hits / (this.hits + this.misses);
	}
	
	/**
	 * calculate the f1-score for this class, the f1-score is calculated from precision and recall.
	 * It is one unified statistic for how well the comparison matches the reference.
	 */
	public void calcF1() {
		this.f1score = 2 * ((this.precision * this.recall) / (this.precision + this.recall));
	}
	
	public Table getContingencyTable() {
		return this.contingencyTable;
	}
	
	public float getPrecision() {
		return this.precision;
	}
	
	public float getRecall() {
		return this.recall;
	}
	
	public float getF1() {
		return this.f1score;
	}
	
	public float getHits() {
		return this.hits;
	}
	public float getMisses() {
		return this.misses;
	}
	public float getFAs() {
		return this.falseAlarms;
	}
	public float getCRs() {
		return this.correctRejections;
	}
}
