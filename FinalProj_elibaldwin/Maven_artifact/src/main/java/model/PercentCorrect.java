package model;

/**
 * class for calculating overall percent correct from the data held in the Mediator object in the Model object
 */
public class PercentCorrect {
	private float OPC;
	//constructor intended for actual use
	public PercentCorrect(Mediator mediator) {
		this.calcOPC(mediator.getReferenceStrings(), mediator.getComparisonStrings());
	}
	//for convenient unit testing
	public PercentCorrect() {}
	
	/**
	 * Count each index in the String arrays from the Mediator that represent pixels in the input rasters as correct or wrong,
	 * then calculate overall percent correct.
	 * @param referenceStrings - String array representing the reference data
	 * @param comparisonStrings - String array representing the comparison data
	 */
	public void calcOPC(String[] referenceStrings, String[] comparisonStrings) {
		float correctCount = 0;
		float wrongCount = 0;
		for (int i = 0; i < referenceStrings.length; i++) {
			if (referenceStrings[i].equals(comparisonStrings[i])){
				correctCount++;
			}else {
				wrongCount++;
			}
		}
		this.OPC = ((correctCount / (correctCount + wrongCount)) * 100);
	}
	
	public float getOPC() {
		return this.OPC;
	}
}
