package model;

import java.util.*;
/**
 * Contains methods that test whether or not the data from the files selected by the user is compatible for use with the program.
 */
public class Validate {
	private boolean isValid;
	
	/**
	 * Tests whether or not the "reference" and "comparison" data sets have the same size rows and columns. Prints error messages to System.err
	 * @param model - a Model object which contains 2D Integer arrays representing each data set
	 * @return boolean - true if data is valid
	 */
	public boolean validateArrays(Model model) {
		isValid = true;
		int referenceRows = model.getReferenceArray().length;
		int referenceColumns = model.getReferenceArray()[0].length;
		int comparisonRows = model.getComparisonArray().length;
		int comparisonColumns = model.getComparisonArray()[0].length;
		if (referenceRows != comparisonRows) {
			isValid = false;
			System.err.println("Input rasters do not have the same row length");
		}
		if (referenceColumns != comparisonColumns) {
			isValid = false;
			System.err.println("Input rasters do not have the same column length");
		}
		return isValid;
	}

	/**
	 * Tests whether or not every value in the "reference" and "comparison" data is present in the landcover classification key.
	 * Prints error messages, each instance of a value not in the classification key, to System.err
	 * @param model - a Model object that contains the data sets and the classification key, which is a HashMap
	 * @return boolean - true if data is valid
	 */
	public boolean validateValues(Model model) {
		isValid = true;
		Set<Integer> keys = model.getClassKey().keySet();
		//Go through each value in reference data and check if it is in the classification key
		for (int i = 0; i < model.getReferenceArray().length; i++) {
			for (int j = 0; j < model.getReferenceArray()[i].length; j++) {
				if (!keys.contains(model.getReferenceArray()[i][j])) {
					isValid = false;
					System.err.println("Value " + model.getReferenceArray()[i][j] + " in reference data does not exist in the key");
				}
			}
		}
		//Go through each value in comparison data and check if it is in the classification key
		for (int i = 0; i < model.getComparisonArray().length; i++) {
			for (int j = 0; j < model.getComparisonArray()[i].length; j++) {
				if (!keys.contains(model.getComparisonArray()[i][j])) {
					isValid = false;
					System.err.println("Value " + model.getComparisonArray()[i][j] + " in comparison data does not exist in the key");
				}
			}
		}
		return isValid;
	}
}
