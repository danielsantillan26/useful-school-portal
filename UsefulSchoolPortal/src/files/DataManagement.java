package files;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
		ArrayList<Integer> blocks = new ArrayList<Integer>();

		for (int i = 1; i < contents.size(); i++) {
			schoolNames.add(contents.get(i).substring(0, contents.get(i).indexOf(',')));
			String modifiedString = contents.get(i).substring(contents.get(i).indexOf(',') + 1);
			schoolIDs.add(Integer.parseInt(modifiedString.substring(0, modifiedString.indexOf(','))));
			blocks.add(Integer.parseInt(modifiedString.substring(modifiedString.indexOf(',') + 1)));
		}

		for (int j = 0; j < schoolNames.size(); j++) {
			schools.add(new School(schoolNames.get(j), schoolIDs.get(j), blocks.get(j)));
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
			School s = new School(schoolName, schoolID, 6);
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
				if (users.get(i).getID() == id) {
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
	
	
	public static int getGradingMethod(int classID) {
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getClassID() == classID) {
				return classes.get(i).getGradingMethod();
			}
		}
		return -1;
	}
	
	
	public static boolean setGradingMethod(int classID, int gradingMethod) {
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getClassID() == classID) {
				classes.get(i).setGradingMethod(gradingMethod);
				classes.get(i).deleteAllAssignments();
				currentSchool.modifyClassInFile(classes.get(i), gradingMethod);
				return true;
			}
		}
		return false;
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


	public static ArrayList<Teacher> getCurrentSchoolTeachers() {
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		for (User u : users) {
			if (u.isTeacher() && u.getSchoolID() == currentSchool.getSchoolID()) {
				teacherList.add((Teacher)u);
			}
		}
		return teacherList;
	}


	public static ArrayList<Student> getCurrentSchoolStudents() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		for (User u : users) {
			if (u.isStudent() && u.getSchoolID() == currentSchool.getSchoolID()) {
				studentList.add((Student)u);
			}
		}
		return studentList;
	}


	public static boolean refactorUser(String username, String firstName, String lastName, String password, int id) {
		try {
			for (User u : users) {
				if (u.getID() == id && u.getSchoolID() == currentSchool.getSchoolID()) {
					if (username.strip() != "") {
						u.setUsername(username);
					}

					if (firstName.strip() != "") {
						u.setFirstName(firstName);
					}

					if (lastName.strip() != "") {
						u.setLastName(lastName);
					}

					if (password.strip() != "") {
						u.setPassword(password);
					}

					currentSchool.refactorUser(username, firstName, lastName, password, id);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean refactorYourself(String username, String firstName, String lastName, String password) {
		return refactorUser(username, firstName, lastName, password, loggedInUser.getID());
	}


	public static boolean changePassword(String password) {
		return refactorUser(loggedInUser.getUsername(), loggedInUser.getFirstName(), 
				loggedInUser.getLastName(), password, loggedInUser.getID());
	}


	public static void setBlocks(int blocks) {
		currentSchool.setBlocks(blocks);
		for (SchoolClass cl : classes) {
			if (cl.getSchoolID() == currentSchool.getSchoolID() && cl.getBlock() > blocks) {
				currentSchool.deleteClass(cl);
			}
		}
	}


	public static int getBlocks() {
		if (currentSchool != null) {
			return currentSchool.getBlocks();
		}
		return -1;
	}


	public static ArrayList<Teacher> getClassTeachers(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				return cl.getTeachers();
			}
		}
		return null;
	}


	public static ArrayList<Teacher> getNonClassTeachers(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				ArrayList<Teacher> classTeachers = cl.getTeachers();
				ArrayList<Teacher> allTeachers = getCurrentSchoolTeachers();
				ArrayList<Teacher> nonClassTeachers = new ArrayList<Teacher>();

				for (Teacher t : allTeachers) {
					boolean isInClass = false;
					for (Teacher u : classTeachers) {
						if (t.getID() == u.getID()) {
							isInClass = true;
						}
					}

					if (!isInClass) {
						nonClassTeachers.add(t);
					}
				}
				return nonClassTeachers;
			}
		}
		return null;
	}


	public static ArrayList<Student> getClassStudents(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				return cl.getStudents();
			}
		}
		return null;
	}


	public static ArrayList<Student> getNonClassStudents(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				ArrayList<Student> classStudents = cl.getStudents();
				ArrayList<Student> allStudents = getCurrentSchoolStudents();
				ArrayList<Student> nonClassStudents = new ArrayList<Student>();

				for (Student s : allStudents) {
					boolean isInClass = false;
					for (Student t : classStudents) {
						if (s.getID() == t.getID()) {
							isInClass = true;
						}
					}

					if (!isInClass) {
						nonClassStudents.add(s);
					}
				}
				return nonClassStudents;
			}
		}
		return null;
	}


	public static ArrayList<User> getClassUsers(int classID) {
		ArrayList<User> classUsers = new ArrayList<User>();
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				ArrayList<Student> classStudents = getClassStudents(classID);
				ArrayList<Teacher> classTeachers = getClassTeachers(classID);

				for (Teacher t : classTeachers) {
					classUsers.add((User)t);
				}

				for (Student s : classStudents) {
					classUsers.add((User)s);
				}
			}
		}
		return classUsers;
	}


	public static ArrayList<Assignment> getClassAssignments(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				return cl.getAssignments();
			}
		}
		return null;
	}


	public static void manageStudentInClass(int classID, int studentID, boolean alreadyAdded) {
		SchoolClass c = null;
		Student s = null;

		for (SchoolClass sc : classes) {
			if (sc.getClassID() == classID) {
				c = sc;
			}
		}

		for (User st : users) {
			if (st.getID() == studentID) {
				s = (Student)st;
			}
		}

		if (!alreadyAdded) {
			boolean alreadyBusy = false;
			for (SchoolClass sc : classes) {
				if (sc.getSchoolID() == currentSchool.getSchoolID()) {
					if (sc.hasID(studentID) && sc.getBlock() == c.getBlock()) {
						alreadyBusy = true;
						c = sc;
					}
				}
			}
			if (alreadyBusy) {
				JOptionPane.showMessageDialog(null, "The student already has"
						+ " a class in this block. Remove the student from " +
						c.getName() + " before adding the student to this class.",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				c.addStudent(s);
			}
		} else {
			c.deleteStudent(s);
		}
	}


	public static void manageTeacherInClass(int classID, int teacherID, boolean alreadyAdded) {
		SchoolClass c = null;
		Teacher t = null;

		for (SchoolClass sc : classes) {
			if (sc.getClassID() == classID) {
				c = sc;
			}
		}

		for (User te : users) {
			if (te.getID() == teacherID) {
				t = (Teacher)te;
			}
		}

		if (!alreadyAdded) {
			boolean alreadyBusy = false;
			for (SchoolClass sc : classes) {
				if (sc.getSchoolID() == currentSchool.getSchoolID()) {
					if (sc.hasID(teacherID) && sc.getBlock() == c.getBlock()) {
						alreadyBusy = true;
						c = sc;
					}
				}
			}
			if (alreadyBusy) {
				JOptionPane.showMessageDialog(null, "The teacher already has"
						+ " a class in this block. Remove the teacher from " +
						c.getName() + " before adding the teacher to this class.",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				c.addTeacher(t);
			}
		} else {
			c.deleteTeacher(t);
		}
	}


	public static ArrayList<SchoolClass> getCurrentUserClasses() {
		ArrayList<SchoolClass> list = getCurrentSchoolClasses();
		ArrayList<SchoolClass> personalizedList = new ArrayList<SchoolClass>();

		for (SchoolClass sc : list) {
			if (sc.hasID(loggedInUser.getID())) {
				personalizedList.add(sc);
			}
		}

		return personalizedList;
	}


	public static void setAnnouncement(String s, int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.setAnnouncement(s);
			}
		}
	}


	public static String getAnnouncement(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getAnnouncement();
			}
		}
		return "";
	}
	
	
	public static ArrayList<String> getWeightCategories(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getWeightCategories();
			}
		}
		return null;
	}
	
	
	public static String getIndividualAssignmentWeightCategory(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getIndividualAssignmentWeightCategory(assignmentID);
			}
		}
		return null;
	}
	
	
	public static int getIndividualAssignmentPoints(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getIndividualAssignmentPoints(assignmentID);
			}
		}
		return -1;
	}
	
	
	public static ArrayList<Integer> getWeightPercents(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getWeightPercents();
			}
		}
		return null;
	}
	
	
	public static int getWeightPercentByCategory(int classID, String weight) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getPercentByCategory(weight);
			}
		}
		return -1;
	}
	
	
	public static void setWeights(ArrayList<String> categories, ArrayList<Integer> percents, int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.setWeights(categories, percents);
			}
		}
	}

	
	public static void addAssignment(int classID, String name, int points) {
		Assignment a = new Assignment(name, points, classID, currentSchool.getSchoolID());
		addAssignment(a, classID);
	}
	
	
	public static void addAssignment(int classID, String name, String weightCategory) {
		Assignment a = new Assignment(name, weightCategory, classID, currentSchool.getSchoolID());
		addAssignment(a, classID);
	}
	
	
	public static void addAssignment(int classID, String name) {
		Assignment a = new Assignment(name, classID, currentSchool.getSchoolID());
		addAssignment(a, classID);
	}
	
	
	private static void addAssignment(Assignment a, int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.addNewAssignment(a);
			}
		}
	}
	
	
	public static void modifyAssignment(int classID, int assignmentID, String name, int points) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.modifyExistingAssignment(assignmentID, name, points);
			}
		}
	}
	
	
	public static void modifyAssignment(int classID, int assignmentID, String name, String weightCategory) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.modifyExistingAssignment(assignmentID, name, weightCategory);
			}
		}
	}
	
	
	public static void modifyAssignment(int classID, int assignmentID, String name) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.modifyExistingAssignment(assignmentID, name);
			}
		}
	}
	
	
	public static void deleteAssignment(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.deleteAssignment(assignmentID);
			}
		}
	}
	
	
	public static ArrayList<Double> getGrades(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getGrades(assignmentID);
			}
		}
		return null;
	}
	
	
	public static void setGrades(int classID, int assignmentID, ArrayList<Double> grades) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.setGrades(assignmentID, grades);
			}
		}
	}
	
}
