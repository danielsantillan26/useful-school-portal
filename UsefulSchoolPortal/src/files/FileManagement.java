package files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import users.*;

public class FileManagement {

	private static final String FILENAME = "data.txt";
	private static File file;

	private static final char DELIMITER_SCHOOL = '校';
	private static final char DELIMITER_ADMIN = '头';
	private static final char DELIMITER_TEACHER = '生';
	private static final char DELIMITER_STUDENT = '师';
	private static final char DELIMITER_CLASS = '课';
	private static final char DELIMITER_END = '端';
	private static final char DELIMITER_CLASS_CLASS = '大';
	private static final char DELIMITER_CLASS_TEACHER = '张';
	private static final char DELIMITER_CLASS_STUDENT = '伟';
	private static final char DELIMITER_CLASS_END = '习';

	private static User loggedInUser;
	private static int currentSchoolID;
	private static int loggedInUserID;

	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<School> schools = new ArrayList<School>();
	private static ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();

	public FileManagement() {

	}


	public static void setup() {
		ArrayList<String> contents = FileWorker.readSchoolFile();
		
		ArrayList<String> schoolNames = new ArrayList<String>();
		ArrayList<Integer> schoolIDs = new ArrayList<Integer>();
		
		for (int i = 1; i < contents.size(); i++) {
			schoolNames.add(contents.get(i).substring(0, contents.get(i).indexOf(',')));
			schoolIDs.add(Integer.parseInt(contents.get(i).substring(contents.get(i).indexOf(',') + 1)));
		}
		
		for (int j = 0; j < schoolNames.size(); j++) {
			schools.add(new School(schoolNames.get(j), schoolIDs.get(j)));
		}
		
	}
	

	public static boolean addUser(User u) {
		try {
			users.add(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addNewSchoolAdministrator(Administrator a) {
		try {
			users.add(a);
			setLoggedInUser(a);
			
			for (School s: schools) {
				if (s.getSchoolID() == currentSchoolID) {
					s.addAdministrator(a);
				}
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addNewAdministrator(String username, String
			firstName, String lastName, String password) {
		try {
			Administrator a = new Administrator(username, firstName, lastName, password, currentSchoolID);
			users.add(a);
			
			for (School s: schools) {
				if (s.getSchoolID() == currentSchoolID) {
					s.addAdministrator(a);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addSchoolAndAdministrator(String schoolName, 
			String adminUsername, String adminFirstName,
			String adminLastName, String adminPassword) {
		try {
			int schoolID = (int)(Math.random()*100000);
			if (addSchool(new School(schoolName, schoolID)) &&
					addNewSchoolAdministrator(new Administrator(adminUsername,
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
	
	
	public static boolean addNewTeacher(String username, String
			firstName, String lastName, String password) {
		try {
			Teacher t = new Teacher(username, firstName, lastName, password, currentSchoolID);
			users.add(t);
			
			for (School s: schools) {
				if (s.getSchoolID() == currentSchoolID) {
					s.addTeacher(t);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addNewStudent(String username, String
			firstName, String lastName, String password) {
		try {
			Student s = new Student(username, firstName, lastName, password, currentSchoolID);
			users.add(s);
			
			for (School sc: schools) {
				if (sc.getSchoolID() == currentSchoolID) {
					sc.addStudent(s);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addSchool(School s) {
		try {
			schools.add(s);
			FileWorker.writeSchool(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addAdministratorToList(Administrator a) {
		try {
			users.add(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addTeacherToList(Teacher t) {
		try {
			users.add(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean addStudentToList(Student s) {
		try {
			users.add(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean login(String username, String password) {
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				setLoggedInUser(u);
				return true;
			}
		}
		return false;
	}
	
	
	public static void setLoggedInUser(User u) {
		loggedInUser = u;
		loggedInUserID = u.getId();
		currentSchoolID = u.getSchoolID();
	}
	
	
	public static int getLoggedInUserRole() {
		if (loggedInUser.isAdministrator()) {
			return Constants.ADMINISTRATOR_VALUE;
		} else if (loggedInUser.isTeacher()) {
			return Constants.TEACHER_VALUE;
		} else if (loggedInUser.isStudent()) {
			return Constants.STUDENT_VALUE;
		}
		return -1;
	}
	
	
	public static String getLoggedInUserName() {
		if (loggedInUser == null) {
			return ("User");
		}
		return loggedInUser.getFirstName() + " " + loggedInUser.getLastName();
	}
	
	
	public static void logOutUser() {
		loggedInUserID = -1;
		currentSchoolID = -1;
	}
	
	
	public static ArrayList<String> getCurrentSchoolUserNames() {
		for (School s : schools) {
			if (s.getSchoolID() == currentSchoolID) {
				return s.getUserNames();
			}
		}
		return null;
	}





}
