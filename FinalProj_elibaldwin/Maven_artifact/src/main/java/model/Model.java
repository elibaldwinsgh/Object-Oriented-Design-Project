package model;

import java.util.HashMap;

import tech.tablesaw.api.*;

/**
 * This class puts together methods from other classes to validate data and convert it to its output form
 */
public class Model {
	Validate valid = new Validate();
	ErrorMatrix errMat = new ErrorMatrix();
	Mediator mediator;
	PercentCorrect pc;
	//reference file
	private int[][] referenceArray;
	//comparison file
	private int[][] comparisonArray;
	//classification key
	private HashMap<Integer, String> classkey;
	ClassStats[] eachClassStats;
	
	/**
	 * Constructor for the Model class. Creates a Mediator object and determines the size of the array of ClassStats object from the size of the classKey
	 * @param referenceArray - data representing reference values
	 * @param comparisonArray - data to be compared against the reference
	 * @param classKey - a HashMap that relates integer values which should be classification values with Strings that should be classification descriptions
	 */
	public Model(int[][] referenceArray, int[][] comparisonArray, HashMap<Integer, String> classKey) {
		this.referenceArray = referenceArray;
		this.comparisonArray = comparisonArray;
		this.classkey = classKey;
		
		this.mediator = new Mediator(this);
		
		eachClassStats = new ClassStats[classKey.size()];
	}
	
	public int[][] getReferenceArray(){
		return this.referenceArray;
	}
	
	public int[][] getComparisonArray(){
		return this.comparisonArray;
	}
	
	public HashMap<Integer, String> getClassKey() {
		return this.classkey;
	}

	/**
	 * Puts together methods from the Validate class to make sure the data is overall valid, meaning same size rows and columns and all values in input rasters are
	 * in the classification key.
	 * @return boolean - true if valid
	 */
	public boolean validMatrix() {
		if (valid.validateArrays(this) && valid.validateValues(this)) {
			return true;
		}
		return false;
	}
	/**
	 * passes data from mediator into the errorMatrix function to actually construct and return the Error matrix
	 * @return Table - a tablesaw package table that in this case is a Crosstabulation table that is an error matrix
	 */
	public Table createMatrix() {
		return errMat.createTable(mediator.getReferenceStrings(), mediator.getComparisonStrings());
	}
	
	/**
	 * Create a PercentCorrect object, passing in the Mediator object in this Model
	 */
	public void createPC() {
		this.pc = new PercentCorrect(this.mediator);
	}
	
	/**
	 * Create a ClassStats class for each class in the classification key, passing in the value from classKey and the Mediator object in this Model
	 */
	public void calcClassStats() {
		int counter = 0;
		for (String i : this.classkey.values()) {
			eachClassStats[counter] = new ClassStats(i, this.mediator.getReferenceStrings(), this.mediator.getComparisonStrings());
			counter++;
	    }
	}
	
	/**
	 * add the percent correct statistic to a string to append to the output StringBuilder
	 * @return - String
	 */
	public String getOPC() {
		return "Percent Correct: " + this.pc.getOPC();
	}
	
	public ClassStats[] getEachClassStats() {
		return this.eachClassStats;
	}
}
