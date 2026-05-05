package control;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * The Intake class contains the methods for turning files into data in the form necessary for this program.
 * This class contains a boolean variable used by its methods to determine if the input files are valid
 */
public class Intake {
	Boolean validFiles = true;

	//help from Google AI answer to searching
	/**
	 * Turns input raster data into a 2D array of integers with the same number of rows and columns.
	 * Also catches errors and prints them to the System.err location.
	 * @param filepath - a String of the filepath
	 * @return int[][] of the integer values in the file
	 */
	public int[][] rasterToArray(String filepath){
		int[][] twodArray = new int[0][0];
		File file = new File(filepath);
		
		try {
			BufferedImage theImage = ImageIO.read(file);
			//make sure there is a real file
			if (theImage == null) {
	            System.err.println("Failed to load image: " + file.getAbsolutePath());
	        }else {
	        	//where the raster is converted to 2D array
	        	Raster raster = theImage.getData();
	        	//set dimensions
	            int width = raster.getWidth();
	            int height = raster.getHeight();
	            twodArray = new int[height][width];
	            //transfer values
	            for (int y = 0; y < height; y++) {
	                for (int x = 0; x < width; x++) {
	                	twodArray[y][x] = raster.getSample(x, y, 0);
	                }
	            }
	        }
	        return twodArray;
		}catch (IOException e) {
			validFiles = false;
            System.err.println("Error reading image: " + e.getMessage());
        }return twodArray;
		
	}

	//help from: https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/
	/**
	 * Reads in a csv file using CSVReader and converts it into a HashMap. Landcover Classification keys are frequently stored in (more or less) this way
	 * often also with a color associated with them. Also catches errors and prints them to the System.err location.
	 * @param filepath - String containing the filepath
	 * @return HashMap<Integer, String> that represents a classification key
	 */
	public HashMap<Integer, String> key(String filepath) {
		HashMap<Integer, String> theKey = new HashMap<>();
		try {
			File csvFile = new File(filepath);
			String[] nextLine;
	        // Create an object of filereader class with CSV file as a parameter.
	        FileReader filereader = new FileReader(csvFile);
	        // create csvReader object passing file reader as a parameter
	        CSVReader csvReader = new CSVReaderBuilder(filereader).build();
	        //read data line by line and put it into the hashmap
	        while ((nextLine = csvReader.readNext()) != null) {
	            theKey.put(Integer.parseInt(nextLine[0]), nextLine[1]);
	        }
	        csvReader.close();
	        return theKey;
		}catch(Exception e) {
			validFiles = false;
			System.err.println("Error reading file: " + filepath);
		}return theKey;
	}
}
