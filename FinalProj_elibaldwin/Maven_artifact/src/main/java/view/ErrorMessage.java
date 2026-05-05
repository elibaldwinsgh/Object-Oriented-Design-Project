package view;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * A class that contains variables and methods for determining where error messages will be put and how they will be displayed to the user,
 * including changing the PrintStream and creating JFrame, JScrollPane and JTextArea objects.
 */
public class ErrorMessage {
	JTextArea theTextArea;
	JFrame errorFrame;
	StringBuilder errorMessage = new StringBuilder();
	PrintStream errOutput;
	JScrollPane scrollPane;
	int width = 300;
	int height = 500;
	
	/**
	 * creates the JFrame, then the JTextArea which is passed to the JScrollPane constructor, and then the JScrollPane is added to the JFrame.
	 * Makes the JFrame display at a different location than the input dialog and makes the text in the JTextArea red.
	 */
	public ErrorMessage() {
		errorFrame = new JFrame("Error Message");
		errorFrame.setSize(width, height);
		//make sure this JFrame is out of the way of the input dialog
		errorFrame.setLocation(950, 0);
		errorFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		theTextArea = new JTextArea("");
		theTextArea.setForeground(Color.red);
		
		scrollPane = new JScrollPane(theTextArea);
		scrollPane.setSize(width, height);
		
		errorFrame.add(scrollPane);
	}
	
	/**
	 * Read in the error messages from the designated output file into a StringBuilder. Then set the JTextArea to be the the StringBuilder.
	 * @param txtFileString - a String representing the designated output file
	 */
	public void setText(String txtFileString) {
		this.errorMessage = new StringBuilder();
		File txtFile = new File(txtFileString);
		String nextLine;
		try {
			FileReader fileReader = new FileReader(txtFile);
			BufferedReader txtFileReader = new BufferedReader(fileReader);
			while ((nextLine = txtFileReader.readLine()) != null) {
				this.errorMessage.append(nextLine).append("\n");
			}
			txtFileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		theTextArea.setText(errorMessage.toString());
	}
	
	public JFrame getFrame() {
		return this.errorFrame;
	}
	
	/**
	 * Sets the output for System.err to a given filepath, which is where handled error messages will go
	 * @param filepath - a String representing the given filepath
	 */
	public void setErrStreamFile(String filepath) {
		try {
			errOutput = new PrintStream(new File(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.setErr(errOutput);
	}
}
