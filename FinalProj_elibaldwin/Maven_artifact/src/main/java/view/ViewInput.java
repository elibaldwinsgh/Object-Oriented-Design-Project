package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * This class contains the code for the UI used to select files to run the program on.
 * This includes creating a JFrame, a JPanel and 3 InputField objects.
 * The InputField objects are added to the JPanel which is added to the JFrame.
 */
public class ViewInput {
	JPanel thePanel;
	JFrame theFrame;
	InputField reference;
	InputField comparison;
	InputField key;
	int width = 900;
	int height = 500;
	
	/**
	 * This constructor contains the code for creating a JFrame, a JPanel and 3 InputField objects.
	 * There is one InputField object for each input data file with a button and a JLabel that will hold the filepath once selected
	 * The InputField objects are added to the JPanel which is added to the JFrame.
	 * This constructor also creates a JTextArea with a description of what the input files should be
	 * This constructor also takes a JButton as a parameter which is also added to the JFrame.
	 * Finally, the JFrame is shown.
	 */
	public ViewInput(JButton theButton) {
		theFrame = new JFrame("Input File Selector Dialog");
		theFrame.setSize(width, height);
		theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		thePanel = new JPanel();
		thePanel.setSize(width, height);
		thePanel.setLayout(new GridLayout(5,2));
		
		JTextArea description = new JTextArea("The inputs are two raster files with landcover data and a \n"
				+ "classification key. The most common use case is to enter ground truth \n"
				+ "data for reference and data classified from remotely sensed imagery for \n"
				+ "the comparison. The raster data must be the same size in terms of rows and \n"
				+ "columns, all the values in the raster data must be in the classification key");
		thePanel.add(description);
		JLabel header = new JLabel("     Underneath will be filepaths once they are selected");
		thePanel.add(header);
		
		reference = new InputField("Reference Raster");
		reference.addToPanel(thePanel);
		comparison = new InputField("Comparison Raster");
		comparison.addToPanel(thePanel);
		key = new InputField("Classification Key CSV");
		key.addToPanel(thePanel);
		
		thePanel.add(theButton);
		
		theFrame.add(thePanel);
		theFrame.setVisible(true);
		
	}
	
	public void showDialog() {
		theFrame.setVisible(true);
	}
	
	/**
	 * reset the JLabels associated with each InputField object, aka the filepaths next to the buttons on the input dialog
	 */
	public void resetLabels() {
		reference.resetJLabel();
		comparison.resetJLabel();
		key.resetJLabel();
	}
	
	public String getReferenceFilepath() {
		return reference.getFilepath();
	}
	
	public String getComparisonFilepath() {
		return comparison.getFilepath();
	}
	
	public String getKeyFilepath() {
		return key.getFilepath();
	}
	
	public JFrame getFrame() {
		return this.theFrame;
	}
	
	public JPanel getPanel() {
		return this.thePanel;
	}
	
}
