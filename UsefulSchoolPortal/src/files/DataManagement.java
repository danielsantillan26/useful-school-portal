package files;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import objects.*;

/**
 * The DataManagement class is the transition point between static data entered
 * in the program and object data created from files. Object data is obtained
 * from the objects in this class and are transferred to the graphics classes,
 * which can only take information from static methods.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class DataManagement {

	/** The logged in user */
	private static User loggedInUser;
	/** The school of the logged in user */
	private static School currentSchool;

	/** The list of users in the entire system (across all schools) */
	private static ArrayList<User> users;
	/** The list of schools in the entire system */
	private static ArrayList<School> schools;
	/** The list of classes in the entire system */
	private static ArrayList<SchoolClass> classes;


	/**
	 * An empty constructor
	 */
	public DataManagement() {

	}


	/**
	 * The setup method instantiates the lists and reads the school file
	 * found in FileWorker to add every school in data to the schools ArrayList.
	 */
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


	/**
	 * The addUser method adds a user to the overall user ArrayList.
	 * 
	 * @param u		The user to be added
	 * @return		Whether or not the user was added
	 */
	public static boolean addUser(User u) {
		try {
			users.add(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The addNewSchoolAdministrator method adds a new administrator after
	 * the creation of a school system. The administrator becomes the logged
	 * in user as well. 
	 * 
	 * @param a		The administrator to be added and logged in to
	 * @return		Whether or not everything was successful
	 */
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


	/**
	 * The addNewAdministrator method adds a new administrator to a school
	 * system without logging in the user to the system. This must be done after
	 * a school system has been created in the program.
	 * 
	 * @param username		The given username
	 * @param firstName		The given first name
	 * @param lastName		The given last name
	 * @param password		The given password
	 * @return				Whether or not everything was successful
	 */
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


	/**
	 * The addSchoolAndAdministrator method adds a new school to the system and
	 * adds the new administrator as well using the addNewSchoolAdministrator
	 * method. This is used when a new school is registered to the program.
	 * 
	 * @param schoolName			The given school name
	 * @param adminUsername			The given admin username
	 * @param adminFirstName		The given admin first name
	 * @param adminLastName			The given admin last name
	 * @param adminPassword			The given admin password
	 * @return						Whether or not everything worked
	 */
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


	/**
	 * The addNewTeacher method adds a new teacher to the overall user
	 * ArrayList and then adds the teacher to the particular school system they
	 * are part of.
	 * 
	 * @param username			The given teacher username
	 * @param firstName			The given teacher first name
	 * @param lastName			The given teacher last name
	 * @param password			The given teacher password
	 * @return					Whether or not everything worked
	 */
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


	/**
	 * The addNewStudent method adds a new student to the overall user
	 * ArrayList and then adds the student to the particular school system they
	 * are part of.
	 * 
	 * @param username			The given student username
	 * @param firstName			The given student first name
	 * @param lastName			The given student last name
	 * @param password			The given student password
	 * @return					Whether or not everything worked
	 */
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


	/**
	 * The addNewClass method adds a new class to the overall class ArrayList
	 * and then adds it to the particular school system if it is within the
	 * given number of blocks the school system offers.
	 * 
	 * @param className			The given class name
	 * @param block				The given number of blocks
	 * @return					Whether or not the class was added
	 */
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


	/**
	 * The addSchool method adds a school to the overall school ArrayList and
	 * then writes it to the school file that is accessed later.
	 * 
	 * @param s			The given school
	 * @return			Whether or not everything worked
	 */
	public static boolean addSchool(School s) {
		try {
			schools.add(s);
			FileWorker.writeSchool(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The addAdministratorToList method adds an already-existing administrator
	 * from data to the list of users.
	 * 
	 * @param a			The given adminstrator
	 * @return			Whether or not the administrator was added
	 */
	public static boolean addAdministratorToList(Administrator a) {
		try {
			users.add(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The addTeacherToList method adds an already-existing teacher from data to
	 * the list of users.
	 * 
	 * @param t			The given teacher
	 * @return			Whether or not the teacher was added
	 */
	public static boolean addTeacherToList(Teacher t) {
		try {
			users.add(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The addStudentToList method adds an already-existing student from data to
	 * the list of users.
	 * 
	 * @param s			The given student
	 * @return			Whether or not the student was added
	 */
	public static boolean addStudentToList(Student s) {
		try {
			users.add(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The addClassToList method adds an already-existing class from data to the
	 * list of classes.
	 * 
	 * @param sc		The class to be added
	 * @return			Whether or not the class was added
	 */
	public static boolean addClassToList(SchoolClass sc) {
		try {
			classes.add(sc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The deleteUser method searches for a user by given ID and deletes that
	 * user if found from the data.
	 * 
	 * @param id		The user ID to search for
	 * @return			Whether or not a user was deleted
	 */
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


	/**
	 * The deleteClass method searches for a class by given ID and deletes that
	 * class if found from the data.
	 * 
	 * @param id		The class ID to search for
	 * @return			Whether or not a class was deleted
	 */
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


	/**
	 * The getGradingMethod method searches for a class by given ID and returns
	 * that class's grading method, which is an integer representation defined
	 * in the Constants class. -1 is returned if there is no class with the ID.
	 * 
	 * @param classID		The classID to search for
	 * @return				The grading method used by the class.
	 */
	public static int getGradingMethod(int classID) {
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getClassID() == classID) {
				return classes.get(i).getGradingMethod();
			}
		}
		return -1;
	}


	/**
	 * The setGradingMethod method searches for a class by given ID and gives
	 * it a new grading method, which is passed in to the method. All
	 * existing assignments are deleted, and the class's information is modified
	 * on data.
	 * 
	 * @param classID			The classID to search for
	 * @param gradingMethod		The grading method to set it to
	 * @return					Whether or not the grading method was set
	 */
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


	/**
	 * The login method takes in a username and password. If the username
	 * and password correlate with a user, the user is logged in.
	 * 
	 * @param username		The given username
	 * @param password		The given password
	 * @return				Whether or not a user was logged in
	 */
	public static boolean login(String username, String password) {
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				setLoggedInUser(u);
				return true;
			}
		}
		return false;
	}


	/**
	 * The setLoggedInUser method sets the logged in user and sets the current
	 * school to that user's school.
	 * 
	 * @param u			The user to log in to
	 */
	public static void setLoggedInUser(User u) {
		loggedInUser = u;
		for (School s : schools) {
			if (s.getSchoolID() == u.getSchoolID()) {
				currentSchool = s;
			}
		}
	}


	/**
	 * The getLoggedInUserRole method gets the role of the logged in user. This
	 * is either administrator, teacher, or student. 
	 * 
	 * @return			The user's role in an integer representation defined in
	 * 					Constants
	 */
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


	/**
	 * The getLoggedInUser returns the user who is currently logged in. If no
	 * user is logged in, null is returned.
	 * 
	 * @return			The logged in user
	 */
	public static User getLoggedInUser() {
		if (loggedInUser == null) {
			return null;
		}
		return loggedInUser;
	}


	/**
	 * The logOutUser method logs out the user by setting the loggedInUser
	 * field to null. The current school is also set to null. 
	 */
	public static void logOutUser() {
		loggedInUser = null;
		currentSchool = null;
	}


	/**
	 * The getCurrentSchoolUsers method returns the list of users from the
	 * current school.
	 * 
	 * @return		The current school's list of users
	 */
	public static ArrayList<User> getCurrentSchoolUsers() {
		return currentSchool.getUsers();
	}


	/**
	 * The getCurrentSchoolClasses method returns the list of classes from the
	 * current school.
	 * 
	 * @return		The current school's list of classes
	 */
	public static ArrayList<SchoolClass> getCurrentSchoolClasses() {
		return currentSchool.getClasses();
	}


	/**
	 * The getCurrentSchoolTeachers method returns the list of teachers from the
	 * current school by getting the list of users and determining which users
	 * are teachers from the current school.
	 * 
	 * @return		The current school's list of teachers
	 */
	public static ArrayList<Teacher> getCurrentSchoolTeachers() {
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		for (User u : users) {
			if (u.isTeacher() && u.getSchoolID() == currentSchool.getSchoolID()) {
				teacherList.add((Teacher)u);
			}
		}
		return teacherList;
	}


	/**
	 * The getCurrentSchoolStudents method returns the list of students from the
	 * current school by getting the list of users and determining which users
	 * are students from the current school.
	 * 
	 * @return		The current school's list of students
	 */
	public static ArrayList<Student> getCurrentSchoolStudents() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		for (User u : users) {
			if (u.isStudent() && u.getSchoolID() == currentSchool.getSchoolID()) {
				studentList.add((Student)u);
			}
		}
		return studentList;
	}


	/**
	 * The refactorUser method edits a user. If a user is editing their name or
	 * password, the user is searched by ID and then is changed based on a
	 * method from the School class, which will accordingly rewrite data. If
	 * nothing is given for fields, the field remains the same.
	 * 
	 * @param username			The given username
	 * @param firstName			The given first name
	 * @param lastName			The given last name
	 * @param password			The given password
	 * @param id				The user ID
	 * @return					Whether the user was successfuly edited
	 */
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


	/**
	 * The refactorYourself method refactors the user logged in. It returns the
	 * result of refactorUser with the user ID being the logged in user's ID.
	 * 
	 * @param username			The given username
	 * @param firstName			The given first name
	 * @param lastName			The given last name
	 * @param password			The given password
	 * @return					Whether the user was successfully edited
	 */
	public static boolean refactorYourself(String username, String firstName, String lastName, String password) {
		return refactorUser(username, firstName, lastName, password, loggedInUser.getID());
	}


	/**
	 * The changePassword method uses the refactorUser method to change the
	 * password of a user. This is used for users who can only change their
	 * passwords.
	 * 
	 * @param password			The given password
	 * @return					Whether the password was successfully changed
	 */
	public static boolean changePassword(String password) {
		return refactorUser(loggedInUser.getUsername(), loggedInUser.getFirstName(), 
				loggedInUser.getLastName(), password, loggedInUser.getID());
	}


	/**
	 * The setBlocks method sets the number of blocks a school has. If there is
	 * a class that is not in the range of blocks, that class is deleted.
	 * 
	 * @param blocks			The number of blocks a school implements
	 */
	public static void setBlocks(int blocks) {
		currentSchool.setBlocks(blocks);
		for (SchoolClass cl : classes) {
			if (cl.getSchoolID() == currentSchool.getSchoolID() && cl.getBlock() > blocks) {
				currentSchool.deleteClass(cl);
			}
		}
	}


	/**
	 * The getBlocks method returns the number of blocks the current school has.
	 * 
	 * @return		The current school's number of blocks
	 */
	public static int getBlocks() {
		if (currentSchool != null) {
			return currentSchool.getBlocks();
		}
		return -1;
	}


	/**
	 * The getClassTeachers method takes in a class ID and uses it to find a
	 * class. The method returns the teachers in that class.
	 * 
	 * @param classID		The class ID to search for
	 * @return				The list of teachers in the class
	 */
	public static ArrayList<Teacher> getClassTeachers(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				return cl.getTeachers();
			}
		}
		return null;
	}


	/**
	 * The getNonClassTeachers method takes in a class ID and uses it to find a
	 * class. The method returns every teacher except the teachers in that
	 * class.
	 * 
	 * @param classID		The class ID to search for
	 * @return				The list of teachers not in the class
	 */
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


	/**
	 * The getClassStudents method takes in a class ID and uses it to find a 
	 * class. The method returns the students in that class.
	 * 
	 * @param classID		The class ID to search for
	 * @return				The list of students in the class
	 */
	public static ArrayList<Student> getClassStudents(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				return cl.getStudents();
			}
		}
		return null;
	}


	/**
	 * The getNonClassStudents method takes in a class ID and uses it to find a
	 * class. The method returns every student except the students in that
	 * class.
	 * 
	 * @param classID		The class ID to search for
	 * @return				The list of students not in the class
	 */
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


	/**
	 * The getClassUsers method takes in a class ID and uses it to find a class.
	 * The method returns every user in the class. Users include both teachers
	 * and students.
	 * 
	 * @param classID		The class ID to search for
	 * @return				The list of users in the class
	 */
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


	/**
	 * The getClassAssignments method takes in a class ID and uses it to find a
	 * class. The method returns a list of assignments in that class.
	 * 
	 * @param classID		The class ID to search for
	 * @return				The list of assignments in the class
	 */
	public static ArrayList<Assignment> getClassAssignments(int classID) {
		for (SchoolClass cl : classes) {
			if (cl.getClassID() == classID && cl.getSchoolID() == currentSchool.getSchoolID()) {
				return cl.getAssignments();
			}
		}
		return null;
	}


	/**
	 * The manageStudentInClass method either adds or removes a student from a
	 * particular class. If an administrator tries to add a student to a
	 * particular class in a block where the student already has a class, the
	 * code will prevent the administrator from adding the student.
	 * 
	 * @param classID			The class ID of the class to add to/delete from
	 * @param studentID			The student ID of the student to be managed
	 * @param alreadyAdded		Whether the student is already in the class
	 */
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


	/**
	 * The manageTeacherInClass method either adds or removes a teacher from a
	 * particular class. If an administrator tries to add a teacher to a
	 * particular class in a block where the teacher already has a class, the
	 * code will prevent the administrator from adding the teacher.
	 * 
	 * @param classID			The class ID of the class to add to/delete from
	 * @param teacherID			The teacher ID of the student to be managed
	 * @param alreadyAdded		Whether the student is already in the class	
	 */
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


	/**
	 * The getCurrentUserClasses method searches for the list of classes the
	 * logged in user is in and returns that list.
	 * 
	 * @return		The list of classes the current user is a part of
	 */
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


	/**
	 * The setAnnouncement method sets the announcement of a particular class.
	 * 
	 * @param s				The given announcement
	 * @param classID		The class ID of the class with the announcement
	 */
	public static void setAnnouncement(String s, int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.setAnnouncement(s);
			}
		}
	}


	/**
	 * The getAnnouncement method gets the announcement of a particular class.
	 * 
	 * @param classID		The class ID of the class with the announcement
	 * @return				The class's announcement
	 */
	public static String getAnnouncement(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getAnnouncement();
			}
		}
		return "";
	}


	/**
	 * The getWeightCategoriesMethod searches for a particular class by ID and
	 * returns its list of weight categories. This only applies to assignments 
	 * in classes that use the weight grading system. If nothing is found, null
	 * is returned.
	 * 
	 * @param classID		The class ID of the class with the desired weight
	 * 						categories
	 * @return				The class's list of weight categories
	 */
	public static ArrayList<String> getWeightCategories(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getWeightCategories();
			}
		}
		return null;
	}


	/**
	 * The getIndividualAssignmentWeightCategory method searches for a
	 * particular assignment by its class ID and assignment ID and returns the
	 * weight category it is in. This only applies to assignments in classes
	 * that use the weight grading system. If nothing is found, null is
	 * returned.
	 * 
	 * @param classID			The class ID of the assignment
	 * @param assignmentID		The assignment's ID
	 * @return					The weight category
	 */
	public static String getIndividualAssignmentWeightCategory(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getIndividualAssignmentWeightCategory(assignmentID);
			}
		}
		return null;
	}


	/**
	 * The getIndividualAssignmentPoints method searches for a particular
	 * assignment by its class ID and assignment ID and returns the number of
	 * points the assignment is worth. This only applies to assignments in
	 * classes that use the points grading system. If nothing is found, a
	 * negative value is returned.
	 * 
	 * @param classID			The class ID of the assignment
	 * @param assignmentID		The assignment's ID
	 * @return					The number of points the assignment is worth
	 */
	public static int getIndividualAssignmentPoints(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getIndividualAssignmentPoints(assignmentID);
			}
		}
		return -1;
	}


	/**
	 * The getWeightPercents method searches for a particular class by its
	 * class ID and returns its weight percents. This only applies to classes
	 * that use the weights grading system. If nothing is found, null is
	 * returned.
	 * 
	 * @param classID		The class ID of the assignment
	 * @return				The list of weight percents in the class
	 */
	public static ArrayList<Integer> getWeightPercents(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getWeightPercents();
			}
		}
		return null;
	}


	/**
	 * The getWeightPercentByCategory method searches for a particular class by
	 * its class ID and returns the weight percent of a particular weight, given
	 * in a parameter. This only applies to classes that use the weights grading
	 * system. If nothing is found, null is returned.
	 * 
	 * @param classID		The class ID 
	 * @param weight		The weight
	 * @return				The percent of the weight
	 */
	public static int getWeightPercentByCategory(int classID, String weight) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getPercentByCategory(weight);
			}
		}
		return -1;
	}


	/**
	 * The setWeights method searches for a particular class by its class ID and
	 * sets the class's weights to the given categories and percents.
	 * 
	 * @param categories		The list of weight categories
	 * @param percents			The list of weight percents
	 * @param classID			The class ID of the class with new weights
	 */
	public static void setWeights(ArrayList<String> categories, ArrayList<Integer> percents, int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.setWeights(categories, percents);
			}
		}
	}


	/**
	 * The addAssignment method adds an assignment to a class based on the class
	 * ID. The assignment receives a name and a particular number of points.
	 * 
	 * @param classID		The class ID
	 * @param name			The name of the assignment
	 * @param points		The number of points the assignment is worth
	 */
	public static void addAssignment(int classID, String name, int points) {
		Assignment a = new Assignment(name, points, classID, currentSchool.getSchoolID());
		addAssignment(a, classID);
	}


	/**
	 * The addAssignment method adds an assignment to a class based on the class
	 * ID. The assignment recieves a name and a weight category.
	 * 
	 * @param classID			The class ID
	 * @param name				The name of the assignment
	 * @param weightCategory	The weight category the assignment belongs to
	 */
	public static void addAssignment(int classID, String name, String weightCategory) {
		Assignment a = new Assignment(name, weightCategory, classID, currentSchool.getSchoolID());
		addAssignment(a, classID);
	}


	/**
	 * The addAssignment method adds an assignment to a class based on the class
	 * ID. The assignment receives a name.
	 * 
	 * @param classID		The class ID
	 * @param name			The name of the assignment
	 */
	public static void addAssignment(int classID, String name) {
		Assignment a = new Assignment(name, classID, currentSchool.getSchoolID());
		addAssignment(a, classID);
	}


	/**
	 * The addAssignment method adds an assignment to a class directly with an
	 * object.
	 * 
	 * @param a				The assignment to be added
	 * @param classID		The class ID
	 */
	private static void addAssignment(Assignment a, int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.addNewAssignment(a);
			}
		}
	}


	/**
	 * The modifyAssignment method modifies an already existing assignment in
	 * a class that uses the points grading system. These modifications can
	 * include a name change or a change in the number of points the assignment
	 * is worth.
	 * 
	 * @param classID			The class ID
	 * @param assignmentID		The assignment ID
	 * @param name				The new name
	 * @param points			The new points value
	 */
	public static void modifyAssignment(int classID, int assignmentID, String name, int points) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.modifyExistingAssignment(assignmentID, name, points);
			}
		}
	}


	/**
	 * The modifyAssignment method modifies an already existing assignment in a
	 * class that uses the weights grading system. These modifications can
	 * include a name change or a change in the weight category.
	 * 
	 * @param classID				The class ID
	 * @param assignmentID			The assignment ID
	 * @param name					The new name
	 * @param weightCategory		The new weight category
	 */
	public static void modifyAssignment(int classID, int assignmentID, String name, String weightCategory) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.modifyExistingAssignment(assignmentID, name, weightCategory);
			}
		}
	}


	/**
	 * The modifyAssignment method modifies an already existing assignment in a
	 * class that uses a basic percents grading system. This modification can 
	 * include a name change.
	 * 
	 * @param classID			The class ID
	 * @param assignmentID		The assignment ID
	 * @param name				The new name
	 */
	public static void modifyAssignment(int classID, int assignmentID, String name) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.modifyExistingAssignment(assignmentID, name);
			}
		}
	}


	/**
	 * The deleteAssignment method deletes an assignment in a class by ID.
	 * 
	 * @param classID			The class ID
	 * @param assignmentID		The assignment ID
	 */
	public static void deleteAssignment(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.deleteAssignment(assignmentID);
			}
		}
	}


	/**
	 * The getGrades method returns the grades of each student in a class's
	 * assignment. If the class or assignment cannot be found, null is returned.
	 * 
	 * @param classID			The class ID
	 * @param assignmentID		The assignment ID
	 * @return					The list of grades for every student
	 */
	public static ArrayList<Double> getGrades(int classID, int assignmentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getGrades(assignmentID);
			}
		}
		return null;
	}


	/**
	 * The getIndividualStudentAssignmentGrades method return the grades of each
	 * assignment for one student in a class. If the student or class cannot
	 * be found, null is returned.
	 * 
	 * @param classID			The class ID
	 * @param studentID			The student ID
	 * @return					The list of assignment grades
	 */
	public static ArrayList<Double> getIndividualStudentAssignmentGrades(int classID, int studentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getIndividualStudentAssignmentGrades(studentID);
			}
		}
		return null;
	}


	/**
	 * The getIndividualStudentAverage method returns the class average for an
	 * individual student in a class. If the student or class cannot be found,
	 * null is returned.
	 * 
	 * @param classID			The class ID
	 * @param studentID			The student ID
	 * @return					The average
	 */
	public static double getIndividualStudentAverage(int classID, int studentID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getAverage(studentID);
			}
		}
		return -1;
	}


	/**
	 * The getAllAverages method returns every student's class average in a
	 * class. If the class cannot be found, null is returned.
	 * 
	 * @param classID			The class ID
	 * @return					The list of averages
	 */
	public static ArrayList<Double> getAllAverages(int classID) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				return c.getAllAverages();
			}
		}
		return null;
	}


	/**
	 * The setGrades method sets the grades for every student in a particular
	 * assignment (after the teacher confirms the gradebook, which can be
	 * edited at any time).
	 * 
	 * @param classID			The class ID
	 * @param assignmentID		The assignment ID
	 * @param grades			The list of grades to add
	 */
	public static void setGrades(int classID, int assignmentID, ArrayList<Double> grades) {
		for (SchoolClass c : classes) {
			if (c.getClassID() == classID) {
				c.setGrades(assignmentID, grades);
			}
		}
	}


	/**
	 * The addInfraction method adds an infraction to the school's infraction
	 * database. An infraction includes a name, student ID, and reason. The
	 * staff ID of the infraction (see Infraction class) is set by the logged
	 * in user's ID.
	 * 
	 * @param name			The given infraction name
	 * @param studentID		The given student ID
	 * @param reason		The given reason
	 */
	public static void addInfraction(String name, int studentID, String reason) {
		Infraction inf = new Infraction(name, studentID, currentSchool.getSchoolID(), loggedInUser.getID(), reason);
		currentSchool.addNewInfraction(inf);
	}


	/**
	 * The modifyInfraction method modifies an infraction. Modifications can
	 * include name, who the student being infracted is, and reason.
	 * 
	 * @param name				The given infraction name
	 * @param infractionID		The infraction ID
	 * @param studentID			The given student ID
	 * @param reason			the given reason
	 */
	public static void modifyInfraction(String name, int infractionID, int studentID, String reason) {
		currentSchool.modifyInfraction(name, infractionID, studentID, loggedInUser.getID(), reason);
	}


	/**
	 * The deleteInfraction method deletes an infraction.
	 * 
	 * @param infractionID		The infraction ID
	 */
	public static void deleteInfraction(int infractionID) {
		currentSchool.deleteInfraction(infractionID);
	}


	/**
	 * The getInfractionsByUser method returns the list of infractions that
	 * involve a particular user. The user can either be the person
	 * infracting or the person infracted.
	 * 
	 * @param userID		The user ID
	 * @return				The list of infractions the user is involved in
	 */
	public static ArrayList<Infraction> getInfractionsByUser(int userID) {
		return currentSchool.getInfractionsByUser(userID);
	}


	/**
	 * The getCurrentSchoolInfractions method returns the infractions in
	 * a school system.
	 * 
	 * @return		The full list of infractions
	 */
	public static ArrayList<Infraction> getCurrentSchoolInfractions() {
		return currentSchool.getInfractions();
	}


	/**
	 * The getNameByID method returns a user's name based on their user ID.
	 * 
	 * @param id	The user ID
	 * @return		The user's name
	 */
	public static String getNameByID(int id) {
		String name = "";
		for (User u : users) {
			if (u.getID() == id) {
				name = u.getFirstName() + " " + u.getLastName();
			}
		}
		return name;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "DataManagement []";
	}

}
