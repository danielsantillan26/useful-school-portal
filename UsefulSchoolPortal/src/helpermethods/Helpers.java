package helpermethods;

public class Helpers {

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
	
}
