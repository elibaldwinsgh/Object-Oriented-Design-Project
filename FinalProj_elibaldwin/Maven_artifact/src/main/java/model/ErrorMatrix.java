package model;


import tech.tablesaw.api.*;

/**
 * contains one method to create the error matrix, which is a tablesaw table that is a Crosstabulation of reference and comparison values
 */
public class ErrorMatrix {
	//https://jtablesaw.github.io/tablesaw/gettingstarted.html
	/**
	 * Takes two String arrays of classification descriptions, turns them into columns in a tablesaw table, then runs
	 * Crosstabulation on those columns to create a new tablesaw table that is the error matrix.
	 * @param reference - A String array of landcover descriptions representing reference data
	 * @param comparison - A String array of landcover descriptions representing data to be compared against the reference
	 * @return Table - a tablesaw Table that is a crosstabulation, the error matrix
	 */
	public Table createTable(String[] reference, String[] comparison) {
		//create columns
		StringColumn Reference = StringColumn.create("Reference", reference);
		StringColumn Comparison = StringColumn.create("Comparison", comparison);
		//create table with the two columns just created
		Table twocol = Table.create();
		twocol.addColumns(Reference, Comparison);
		//create crosstabulation table
		Table output = twocol.xTabCounts("Reference", "Comparison");
		return output;
	}
}
