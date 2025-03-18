package files;

import users.*;

public class UserActions {

	public static boolean addSchoolAndAdministrator(String schoolName, 
			String adminUsername, String adminFirstname,
			String adminLastname, String adminPassword) {
		try {
			int schoolID = (int)(Math.random()*100000);
			if (FileManagement.addUser(new Administrator(adminUsername,
					adminFirstname, adminLastname, adminPassword,
					schoolID)) & FileManagement.addSchool(new School(schoolName, schoolID))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
