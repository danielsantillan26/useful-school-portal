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
		file = new File(FILENAME);

		try {
			file.createNewFile();;
		} catch (IOException e) {
			e.printStackTrace();
		}

		setupGroups();
	}


	private static void setupGroups() {

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
	
	
	public static void logOutUser() {
		loggedInUserID = -1;
		currentSchoolID = -1;
	}





}
