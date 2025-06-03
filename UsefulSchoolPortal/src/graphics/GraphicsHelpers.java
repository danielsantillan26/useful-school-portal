package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;

/**
 * The GraphicsHelpers class contains methods useful for the graphics classes as
 * they process through data before sending it to DataManagement or complete
 * repetitive tasks.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class GraphicsHelpers {

	/**
	 * Default constructor
	 */
	public GraphicsHelpers() {
		
	}
	
	
	/**
	 * The isPasswordValid method takes in a password and sees if it meets
	 * several requirements. This includes the inclusion of various types of
	 * characters and an absence of spaces.
	 * 
	 * @param password		The password to check
	 * @return				Whether the password is valid
	 */
	public static boolean isPasswordValid(String password) {
		if (!password.strip().equals(password)) {
			return false;
		} else if (password.length() < 10) {
			return false;
		} else if (password.length() > 50) {
			return false;
		} else {
			boolean hasDigit = false;
			boolean hasLowercase = false;
			boolean hasUppercase = false;
			boolean hasSpace = false;

			for (int i = 0; i < password.length(); i++) {
				if (Character.isUpperCase(password.charAt(i))) {
					hasUppercase = true;
				}

				if (Character.isLowerCase(password.charAt(i))) {
					hasLowercase = true;
				}

				if (Character.isDigit(password.charAt(i))) {
					hasDigit = true;
				}

				if (Character.isSpaceChar(password.charAt(i))) {
					hasSpace = true;
				}
			}

			if (!hasDigit || !hasUppercase || !hasLowercase || hasSpace) {
				return false;
			}
		}

		return true;
	}


	/**
	 * The modifyButton method modifies a JButton to conform with the graphics
	 * of the program. The width and height can be updated in this method.
	 * 
	 * @param button		The button to modify
	 * @param width			The width of the button
	 * @param height		The hight of the button
	 */
	public static void modifyButton(JButton button, int width, int height) {
		button.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		button.setForeground(Color.WHITE);
		button.setPreferredSize(new Dimension(width, height));
		button.setFont(GraphicsConstants.FONT_BUTTON);
	}


	/**
	 * The hasComma method checks if there is a comma in the text. Commas cannot
	 * be in data due to the use of .csv files. 
	 * 
	 * @param inputs	The input of Strings to check
	 * @return			Whether there is a comma in any of the text
	 */
	public static boolean hasComma(ArrayList<String> inputs) {
		boolean hasComma = false;

		for (String s : inputs) {
			for (int i = 0; i < s.length(); i++) {
				if (s.substring(i, i+1).equals(",")) {
					hasComma = true;
				}
			}
		}

		return hasComma;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "GraphicsHelpers []";
	}

}
