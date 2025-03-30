package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;

public class GraphicsHelpers {

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
	
	
	public static void modifyButton(JButton button, int width, int height) {
		button.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		button.setForeground(Color.WHITE);
		button.setPreferredSize(new Dimension(width, height));
		button.setFont(GraphicsConstants.FONT_BUTTON);
	}
	
	
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
	
}
