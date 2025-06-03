package run;

import files.DataManagement;
import files.FileWorker;
import graphics.Frame;

/**
 * The Run class allows the Useful School Portal project to run. The class
 * creates the frame that lets users access and implement the Useful School
 * Portal.
 * 
 * @version 1.0
 * @author Daniel Santillan
 */
public class Run {
	
	/**
	 * Default constructor
	 */
	public Run() {
		
	}

	/**
	 * The main method instantiates a frame that holds all the contents of the
	 * Useful School Portal. It sets the frame as visible (so users can see it)
	 * and prints the toString method for this class.
	 * 
	 * @param args main method arguments
	 */
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setVisible(true);

		FileWorker.makeFile();
		DataManagement.setup();

		System.out.println(new Run());
	}


	/**
	 * This is the toString method for this class. It prints a message stating
	 * that the class is working.
	 */
	public String toString() {
		return "Run\nClass is working!";
	}

}
