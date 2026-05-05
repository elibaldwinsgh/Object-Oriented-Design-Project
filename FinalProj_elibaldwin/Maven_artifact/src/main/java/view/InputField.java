package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class contains a JButton, which, when pressed, opens a JFileChooser. When a file is chosen its filepath, as a String, is passed to a JLabel.
 */
public class InputField {
	//currently creating a JFileChooser which is directed to a filepath where I have test files stored
	JFileChooser jfc = new JFileChooser("C:/Users/edddy/backup/CS5004/finalProj_stuff/");
	JLabel filepath = new JLabel("");
	JButton launchJFC;
	String filepathString;
	
	/**
	 * Constructor that takes in a name as a String and uses that to name a new JButton it defines, as well as adding the 
	 * action listener to that JButton which calls runInput to get a filepath as a String.
	 * @param name - a String that is used to build the text on the JButton.
	 */
	public InputField(String name) {
		this.launchJFC = new JButton("Select " + name + " File");
		launchJFC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filepathString = runInput();
			}
		});
	}
	
	//Inspiration from Max O'didley on youtube https://www.youtube.com/watch?v=gMVkp8108f0
	/**
	 * This method creates a JFileChooser and returns the filepath that the user chooses from that dialog
	 * @return String - filepath
	 */
	public String runInput() {
		// Open the save dialog
		File selectedFile;
		//in case the user closes the file chooser without selecting anything
		String filepath = "none selected";
		int choice = jfc.showOpenDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			filepath = selectedFile.getAbsolutePath();
		}
		this.filepath.setText(filepath);
		return filepath;
	}
	
	public String getFilepath() {
		return this.filepathString;
	}
	
	/**
	 * A method that adds the JButton and JLabel to a given JPanel
	 * @param thePanel - a JPanel
	 */
	public void addToPanel(JPanel thePanel) {
		thePanel.add(this.launchJFC);
		thePanel.add(this.filepath);
	}
	
	/**
	 * changes the JLabel representing the filepath to be an empty string
	 */
	public void resetJLabel() {
		this.filepath.setText("");
	}
}
