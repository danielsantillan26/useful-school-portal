package files;

import java.util.ArrayList;

import objects.*;

public class DataManagement {

	private static User loggedInUser;
	private static School currentSchool;

	private static ArrayList<User> users;
	private static ArrayList<School> schools;
	private static ArrayList<SchoolClass> classes;

	public DataManagement() {

	}


	public static void setup() {
		users = new ArrayList<User>();
		schools = new ArrayList<School>();
		classes = new ArrayList<SchoolClass>();

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


	private static boolean addNewSchoolAdministrator(Administrator a) {
		try {
			users.add(a);
			setLoggedInUser(a);

			currentSchool.addAdministrator(a);

			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean addNewAdministrator(String username, String
			firstName, String lastName, String password) {
		try {
			Administrator a = new Administrator(username, firstName, lastName, password, currentSchool.getSchoolID());
			users.add(a);
			currentSchool.addAdministrator(a);
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
			School s = new School(schoolName, schoolID);
			currentSchool = s;
			if (addSchool(s) &&
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
			Teacher t = new Teacher(username, firstName, lastName, password, currentSchool.getSchoolID());
			users.add(t);
			currentSchool.addTeacher(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean addNewStudent(String username, String
			firstName, String lastName, String password) {
		try {
			Student s = new Student(username, firstName, lastName, password, currentSchool.getSchoolID());
			users.add(s);
			currentSchool.addStudent(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean addNewClass(String className, int block) {
		try {
			if (block > 0 && block <= getBlocks()) {
				SchoolClass cl = new SchoolClass(className, block, Constants.GRADE_POINTS, currentSchool.getSchoolID());
				classes.add(cl);
				currentSchool.addClass(cl);
				return true;
			}
			return false;
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
	
	
	public static boolean addClassToList(SchoolClass sc) {
		try {
			classes.add(sc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean deleteUser(int id) {
		try {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getId() == id) {
					currentSchool.deleteUser(users.get(i));
					return true;
				}
			} 
			return false;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean deleteClass(int id) {
		try {
			for (int i = 0; i < classes.size(); i++) {
				if (classes.get(i).getClassID() == id) {
					currentSchool.deleteClass(classes.get(i));
					return true;
				}
			}
			return false;
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
		for (School s : schools) {
			if (s.getSchoolID() == u.getSchoolID()) {
				currentSchool = s;
			}
		}
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


	public static User getLoggedInUser() {
		if (loggedInUser == null) {
			return null;
		}
		return loggedInUser;
	}


	public static void logOutUser() {
		loggedInUser = null;
		currentSchool = null;
	}


	public static ArrayList<User> getCurrentSchoolUsers() {
		return currentSchool.getUsers();
	}


	public static ArrayList<SchoolClass> getCurrentSchoolClasses() {
		return currentSchool.getClasses();
	}


	public static void setBlocks(int blocks) {
		currentSchool.setBlocks(blocks);
	}


	public static int getBlocks() {
		if (currentSchool != null) {
			return currentSchool.getBlocks();
		}
		return -1;
	}
}
