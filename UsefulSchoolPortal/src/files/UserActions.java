package files;

import users.*;

public class UserActions {

	public static boolean addSchoolAndAdministrator(String schoolName, 
			String adminUsername, String adminFirstName,
			String adminLastName, String adminPassword) {
		try {
			int schoolID = (int)(Math.random()*100000);
			if (FileManagement.addNewSchoolAdministrator(new Administrator(adminUsername,
					adminFirstName, adminLastName, adminPassword,
					schoolID)) & FileManagement.addSchool(new School(schoolName, schoolID))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addAdministrator(String adminUsername, String adminFirstname,
			String adminLastname, String adminPassword) {
		try {
			
		} catch (Exception e) {
			
		}
		return false;
	}

}
