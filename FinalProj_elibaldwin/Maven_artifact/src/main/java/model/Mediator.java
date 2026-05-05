package model;

import java.util.HashMap;

/**
 * holds data in the form of String arrays that will be passed to multiple other classes in the model package to calculate results from
 */
public class Mediator {
	
	private String[] referenceStrings;
	private String[] comparisonStrings;
	
	public Mediator(Model model) {
		referenceStrings = this.createColumn(model.getReferenceArray(), model.getClassKey());
		comparisonStrings = this.createColumn(model.getComparisonArray(), model.getClassKey());
	}
	
	/**
	 * Takes a 2D integer array that was necessary for testing the validity of the data and turns it into a list of String values
	 * that are landcover class descriptions using the classification key
	 * @param inputArray - classification values
	 * @param classKey - keys are integers of classification values, values are Strings that are classification descriptions
	 * @return String Array - Classification descriptions, to be used in the createTable function
	 */
	public String[] createColumn(int[][] inputArray, HashMap<Integer, String> classKey) {
		int length = inputArray.length * inputArray[0].length;
		String[] aColumn = new String[length];
		int counter = 0;
		for (int i = 0; i < inputArray.length; i++) {
			for (int j = 0; j < inputArray[i].length; j++) {
				aColumn[counter] = classKey.get(inputArray[i][j]);
				counter ++;
			}
		}
		return aColumn;
	}
	
	public String[] getReferenceStrings() {
		return this.referenceStrings;
	}
	
	public String[] getComparisonStrings() {
		return this.comparisonStrings;
	}
	
	
}
