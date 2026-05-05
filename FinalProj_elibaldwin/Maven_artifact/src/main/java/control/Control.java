package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;

import view.*;
import model.*;

/**
 * The Control class brings together methods from other class to control the flow of the program. The Control class extends Intake.
 */
public class Control extends Intake {
		//create variables
		ViewInput newInput;
		ViewOutput newOutput = new ViewOutput();
		Model thisModel;
		int[][] referenceArray;
		int[][] comparisonArray;
		HashMap<Integer, String> classKey = new HashMap<>();
		JButton proceedButton;
		ErrorMessage errMessage = new ErrorMessage();
		//the file location for the error message text
		String errFilePath = "C:/temp/err.txt";
		String outputFilePath = "C:/temp/output.txt";
		
		/**
		 * This function is run when the inputs chosen by the user are invalid. It resets some variables, sets the error message,
		 * shows the error message to the user and then reopens the input dialog.
		 */
		public void badInput() {
			//reset some variables
			validFiles = true;
			newInput.resetLabels();
			//set the error text from the file it was getting written to
			errMessage.setText(errFilePath);
			//show the error message in a JFrame
			errMessage.getFrame().setVisible(true);
			//show the input dialog again
			newInput.showDialog();
		}
		
		/**
		 * The run method is where the program actually runs. It gets user input, validates the input and if valid, runs methods on the input.
		 * A JButton is created which, when pressed, causes the input window to close and the main part of the program to run. 
		 * If the input is found to be invalid, the input dialog will be shown again as well as an error message.
		 * If the inputs are valid, the results are generated and show to the user as well as getting saved out to a file
		 */
		public void run() {
			proceedButton = new JButton("Proceed");
			proceedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					newInput.getFrame().setVisible(false);
					//input reference data
					referenceArray = rasterToArray(newInput.getReferenceFilepath());
					//input for the data being compared against the ground truth
					comparisonArray = rasterToArray(newInput.getComparisonFilepath());
					//input for the classification key
					classKey = key(newInput.getKeyFilepath());
					//use the inputs to generate the error matrix if they are valid
					if(validFiles) {
						thisModel = new Model(referenceArray, comparisonArray, classKey);
						//if the input data has the same rows and columns and classification key values
						if(thisModel.validMatrix()) {
							//calculate the global statistics and add them to the StringBuilder that will be output
							newOutput.addToSb(thisModel.createMatrix());
							thisModel.createPC();
							newOutput.addToSb(thisModel.getOPC());
							//add a blank line to have separation between global and per-class results
							newOutput.addToSb("");
							thisModel.calcClassStats();
							//calculate the per-class statistics and add them to the StringBuilder that will be output
							for (ClassStats i : thisModel.getEachClassStats()) {
								newOutput.addToSb(i.getContingencyTable());
								newOutput.addToSb("Precision: " + i.getPrecision());
								newOutput.addToSb("Recall: " + i.getRecall());
								newOutput.addToSb("F-1 score: " + i.getF1());
								newOutput.addToSb("");
							}
							//output results to display to user in JFrame and save to file
							newOutput.setStreamFile(outputFilePath);
							System.out.println(newOutput.getSB());
							newOutput.showOutput(newOutput.getSB());
						} else {
							badInput();
						}
					} else {
						badInput();
					}
				}
			});
			//set the file where error messages will be recorded
			errMessage.setErrStreamFile(errFilePath);
			//create and show the input dialog
			newInput = new ViewInput(proceedButton);
			
		}
}
