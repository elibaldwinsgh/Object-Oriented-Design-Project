package view;

import java.io.*;
import javax.swing.*;

/**
 * This class creates a JFrame, JScrollPane and JTextPanel to display output to the user.
 * This class also has a StringBuilder and a method to add to that StringBuilder. The StringBuilder
 * is then what gets put into the JTextPanel. It also contains a method to set an output file for the results.
 */
public class ViewOutput {
	PrintStream output;
	JFrame frame;
	JTextPane textPane;
	JScrollPane scrollPane;
	StringBuilder sb = new StringBuilder("In all tables Reference data is in the rows and Comparison data is in the columns \n\n");
	int width = 600;
	int height = 600;
	
	public ViewOutput() {}
	
	//help from https://findnerd.com/list/view/To-redirect-System-out-println-output-to-a-file-in-Java-instead-to-console/14681/
	/**
	 * Set a new output file for System.out
	 * @param filepath a String representing a fielpath that the output stream will be set to.
	 */
	public void setStreamFile(String filepath) {
		try {
			this.output = new PrintStream(new File(filepath));
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException for the filepath intended for System.setOut");
			e.printStackTrace();
		}
		
		System.setOut(output);
	}
	
	//help from https://www.geeksforgeeks.org/java/java-jscrollpane/
	/**
	 * Create an output which is a JFrame that has a JScrollPane and JTextPane. The JTextPane is populated with
	 * a StringBuilder object that is passed in as a parameter
	 * @param sb - StringBuilder object containing results information
	 */
	public void showOutput(StringBuilder sb) {
        JFrame frame = new JFrame("Output Report");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        
        textPane = new JTextPane();
        textPane.setSize(width, height);
        textPane.setText(sb.toString());
      
        JScrollPane scrollPane = new JScrollPane(textPane);

        frame.add(scrollPane);
        frame.setVisible(true);
	}
	
	/**
	 * add an object, that can be passed into a StringBUilder to create a String, to the StringBuilder for results,
	 * then also append a newline.
	 * @param addition - Object to add
	 */
	public void addToSb(Object addition) {
		this.sb.append(addition).append(System.lineSeparator());
	}
	
	public StringBuilder getSB() {
		return this.sb;
	}
}
