package files;

import users.*;

public class UserActions {

	public static boolean addSchoolAndAdministrator(String schoolName, 
			String adminUsername, String adminFirstName,
			String adminLastName, String adminPassword) {
		try {
			int schoolID = (int)(Math.random()*100000);
			if (FileManagement.addSchool(new School(schoolName, schoolID)) &&
					FileManagement.addNewSchoolAdministrator(new Administrator(adminUsername,
					adminFirstName, adminLastName, adminPassword,
					schoolID))) {
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
			if (FileManagement.addNewAdministrator(adminUsername, adminFirstname, adminLastname, adminPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addTeacher(String teacherUsername, String teacherFirstname,
			String teacherLastname, String teacherPassword) {
		try {
			if (FileManagement.addNewTeacher(teacherUsername, teacherFirstname, teacherLastname, teacherPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addStudent(String studentUsername, String studentFirstname,
			String studentLastname, String studentPassword) {
		try {
			if (FileManagement.addNewStudent(studentUsername, studentFirstname, studentLastname, studentPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
