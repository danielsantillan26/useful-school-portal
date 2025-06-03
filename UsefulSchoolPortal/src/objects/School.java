package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import files.DataManagement;
import files.FileWorker;
import files.InvalidFileInputException;

/**
 * The School class is a representation of a school in the system. It includes
 * many essential fields for a school and also has methods that help the school
 * perform its job as a school.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class School {

	/** School's name */
	private String name;
	/** School's ID */
	private int schoolID;
	/** School's user list file */
	private File userList;
	/** School's class list file */
	private File classList;
	/** School's infraction list file */
	private File infractionList;
	/** School's list of administrators */
	private ArrayList<Administrator> admins;
	/** School's list of teachers */
	private ArrayList<Teacher> teachers;
	/** School's list of students */
	private ArrayList<Student> students;
	/** School's list of classes */
	private ArrayList<SchoolClass> classes;
	/** School's list of infractions */
	private ArrayList<Infraction> infractions;
	/** School's number of blocks */
	private int blocks;


	/**
	 * This constructor builds a new school by ID, name, and block count. The
	 * files are either created (for new schools) or read in (for existing
	 * schools), establishing the data locations.
	 * 
	 * @param name			Given name
	 * @param schoolID		Given school ID
	 * @param blocks		Given block count
	 */
	public School(String name, int schoolID, int blocks) {
		this.name = name;
		this.schoolID = schoolID;
		this.blocks = blocks;

		admins = new ArrayList<Administrator>();
		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();
		classes = new ArrayList<SchoolClass>();
		infractions = new ArrayList<Infraction>();

		userList = new File("SchoolUsers_" + schoolID + ".csv");
		classList = new File("SchoolClasses_" + schoolID + ".csv");
		infractionList = new File("SchoolInfractions_" + schoolID + ".csv");

		try {
			if (!userList.exists()) {
				userList.createNewFile();
				FileWriter writer = new FileWriter(userList, false);
				BufferedWriter bWriter = new BufferedWriter(writer);
				bWriter.write("Username,First Name,Last Name, Role,User ID,Password");
				bWriter.close();
			} else {
				addExistingUsers();
			}

			if (!classList.exists()) {
				classList.createNewFile();
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(classList, false));
				bWriter.write("Class Name,Class ID,Block,Grading Method");
				bWriter.close();
			} else {
				addExistingClasses();
			}

			if (!infractionList.exists()) {
				infractionList.createNewFile();
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(infractionList, false));
				bWriter.write("Infraction Name,Infraction ID,Student Infracted ID,Staff ID, Reason");
				bWriter.close();
			} else {
				addExistingInfractions();
			}
		} catch (Exception e) {

		}
	}


	/**
	 * The addAdministrator method adds an administrator to the school.
	 * 
	 * @param a	The administrator to add
	 */
	public void addAdministrator(Administrator a) {
		try {
			admins.add(a);
			writeInUser(a, "Administrator");
		} catch (Exception e) { }
	}


	/**
	 * The addTeacher method adds a teacher to the school.
	 * 
	 * @param t		The teacher to add
	 */
	public void addTeacher(Teacher t) {
		try {
			teachers.add(t);
			writeInUser(t, "Teacher");
		} catch (Exception e) { }
	}


	/**
	 * The addStudent method adds a student to the school.
	 * 
	 * @param s		The student to add
	 */
	public void addStudent(Student s) {
		try {
			students.add(s);
			writeInUser(s, "Student");
		} catch (Exception e) { }
	}


	/**
	 * The addClass method adds a class to the school.
	 * 
	 * @param cl	The class to add.
	 */
	public void addClass(SchoolClass cl) {
		try {
			classes.add(cl);
			writeInClass(cl);
		} catch (Exception e) { }
	}


	/**
	 * The writeInUser method adds a user and their role to the users list file.
	 * 
	 * @param u			The user to write in
	 * @param role		The role of the user
	 */
	private void writeInUser(User u, String role) {
		try {
			ArrayList<String> contents = FileWorker.readFile(userList);
			FileWriter writer = new FileWriter(userList, false);
			BufferedWriter bWriter = new BufferedWriter(writer);

			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}

			bWriter.write(u.getUsername() + "," + u.getFirstName() + "," + u.getLastName()
			+ "," + role + "," + u.getID() + "," + u.getPassword());
			bWriter.close();
		} catch (Exception e) { }
	}


	/**
	 * The writeInClass method adds a class to the class list file.
	 * 
	 * @param cl		The class to write in
	 */
	private void writeInClass(SchoolClass cl) {
		try {
			ArrayList<String> contents = FileWorker.readFile(classList);
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(classList, false));

			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}

			bWriter.write(cl.getName() + "," + cl.getClassID() + "," + cl.getBlock()
			+ "," + cl.getGradingMethod());
			bWriter.close();
		} catch (Exception e) { }
	}


	/**
	 * The addExistingUsers method adds existing users from data to the
	 * ArrayLists and the DataManagement list for use by static classes.
	 */
	private void addExistingUsers() {
		ArrayList<String> contents = FileWorker.readFile(userList);
		for (int i = 1; i < contents.size(); i++) {

			String x = contents.get(i);
			try {
				if (x.split(",").length != 6) {
					throw new InvalidFileInputException();
				}

				String username = x.substring(0, x.indexOf(","));
				x = x.substring(x.indexOf(",") + 1);
				String firstName = x.substring(0, x.indexOf(","));
				x = x.substring(x.indexOf(",") + 1);
				String lastName = x.substring(0, x.indexOf(","));
				x = x.substring(x.indexOf(",") + 1);
				String role = x.substring(0, x.indexOf(","));
				x = x.substring(x.indexOf(",") + 1);
				int id = Integer.parseInt(x.substring(0, x.indexOf(",")));
				x = x.substring(x.indexOf(",") + 1);
				String password = x;

				if (role.equals("Administrator")) {
					Administrator a = new Administrator(username, firstName, lastName,
							password, id, this.schoolID);
					admins.add(a);
					DataManagement.addAdministratorToList(a);
				} else if (role.equals("Teacher")) {
					Teacher t = new Teacher(username, firstName, lastName,
							password, id, this.schoolID);
					teachers.add(t);
					DataManagement.addTeacherToList(t);
				} else {
					Student s = new Student(username, firstName, lastName,
							password, id, this.schoolID);
					students.add(s);
					DataManagement.addStudentToList(s);
				}
			} catch (InvalidFileInputException e) {
				JOptionPane.showMessageDialog(null, "Some errors with data! Check the data log.",
						"System Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Corrupt Line");
				System.out.println("File: " + userList.getName());
				System.out.println("Line: " + (i + 1));
				System.out.println("Reason: " + e.getMessage());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Some errors with data! Check the data log.",
						"System Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Corrupt Line");
				System.out.println("File: " + userList.getName());
				System.out.println("Line: " + (i + 1));
				System.out.println("Reason: " + e.getMessage());
			}
		}
	}


	/**
	 * The addExistingClasses method adds existing classes from data to the
	 * ArrayLists and the DataManagement list for use by static classes.
	 */
	private void addExistingClasses() {
		ArrayList<String> contents = FileWorker.readFile(classList);
		for (int i = 1; i < contents.size(); i++) {
			String x = contents.get(i);

			try {

				if (x.split(",").length != 4) {
					throw new InvalidFileInputException();
				}

				String className = x.substring(0, x.indexOf(","));
				x = x.substring(x.indexOf(",") + 1);
				int classID = Integer.parseInt(x.substring(0, x.indexOf(",")));
				x = x.substring(x.indexOf(",") + 1);
				int block = Integer.parseInt(x.substring(0, x.indexOf(",")));
				x = x.substring(x.indexOf(",") + 1);
				int gradingMethod = Integer.parseInt(x);

				SchoolClass sc = new SchoolClass(className, block, gradingMethod, schoolID, classID);
				classes.add(sc);
				DataManagement.addClassToList(sc);

				for (Teacher t : teachers) {
					if (sc.hasID(t.getID())) {
						sc.addExistingTeacher(t);
					}
				}

				for (int id : sc.getStudentIDs()) {
					for (Student s : students) {
						if (id == s.getID()) {
							sc.addExistingStudent(s);
						}
					}
				}

			} catch (InvalidFileInputException e) {
				JOptionPane.showMessageDialog(null, "Some errors with data! Check the data log.",
						"System Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Corrupt Line");
				System.out.println("File: " + classList.getName());
				System.out.println("Line: " + (i + 1));
				System.out.println("Reason: " + e.getMessage());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Some errors with data! Check the data log.",
						"System Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Corrupt Line");
				System.out.println("File: " + classList.getName());
				System.out.println("Line: " + (i + 1));
				System.out.println("Reason: " + e.getMessage());
			}

		}
	}


	/**
	 * The deleteUser method deletes a user from their role ArrayList and
	 * from the user list file.
	 * 
	 * @param u		The user to remove
	 */
	public void deleteUser(User u) {
		if (u.isAdministrator()) {
			for (int i = 0; i < admins.size(); i++) {
				if (admins.get(i).getID() == u.getID()) {
					admins.remove(i);
				}
			}
		} else if (u.isTeacher()) {
			for (int i = 0; i < teachers.size(); i++) {
				if (teachers.get(i).getID() == u.getID()) {
					teachers.remove(i);
				}
			}
		} else if (u.isStudent()) {
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getID() == u.getID()) {
					students.remove(i);
				}
			}
		}

		try {
			FileWorker.removeLine(userList, u.getID());
		} catch (Exception e) { }
	}


	/**
	 * The deleteClass method deletes a class from their role ArrayList and
	 * from the class list file.
	 * 
	 * @param cl	The class to remove
	 */
	public void deleteClass(SchoolClass cl) {
		try {
			for (int i = 0; i < classes.size(); i++) {
				if (classes.get(i).getClassID() == cl.getClassID()) {
					classes.get(i).deleteFiles();
					classes.remove(i);
				}
			}
			FileWorker.removeLine(classList, cl.getClassID());
		} catch (Exception e) { }
	}


	/**
	 * The addNewInfraction method adds a new infraction to the infractions
	 * ArrayList and the infractions file.
	 * 
	 * @param inf		The infraction to add
	 */
	public void addNewInfraction(Infraction inf) {
		infractions.add(inf);
		ArrayList<String> contents = FileWorker.readFile(infractionList);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(infractionList, false));
			for (int i = 0; i < contents.size(); i++) {
				bw.write(contents.get(i) + "\n");
			}
			bw.write(inf.getName() + "," + String.valueOf(inf.getId()) + "," + 
					String.valueOf(inf.getStudentID()) + "," + String.valueOf(inf.getStaffID())
					+ "," + inf.getReason() + "\n");
			bw.close();
		} catch (Exception e) {

		}
	}


	/**
	 * The modifyInfraction method modifies an existing infraction (the one with
	 * the same ID) and adds the modifications to the infractions file without
	 * adding a new infraction.
	 * 
	 * @param name				Given name
	 * @param infractionID		Given infraction ID
	 * @param studentID			Given student ID
	 * @param staffID			Given staff ID
	 * @param reason			Given reason
	 */
	public void modifyInfraction(String name, int infractionID, int studentID, int staffID, String reason) {
		Infraction inf = new Infraction(name, infractionID, studentID, schoolID, staffID, reason);
		for (int i = 0; i < infractions.size(); i++) {
			if (infractions.get(i).getId() == infractionID) {
				infractions.set(i, inf);
			}
		}

		ArrayList<String> contents = FileWorker.readFile(infractionList);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(infractionList, false));
			for (int i = 0; i < contents.size(); i++) {
				if (contents.get(i).contains(String.valueOf(infractionID))) {
					bw.write(inf.getName() + "," + String.valueOf(inf.getId()) + "," + 
							String.valueOf(inf.getStudentID()) + "," + String.valueOf(inf.getStaffID())
							+ "," + inf.getReason() + "\n");
				} else {
					bw.write(contents.get(i) + "\n");
				}
			}
			bw.close();
		} catch (Exception e) {

		}


	}


	/**
	 * The deleteInfraction method removes an infraction from the ArrayList of
	 * infractions and the infractions file.
	 * 
	 * @param infractionID		The infractionID of the infraction to be purged
	 */
	public void deleteInfraction(int infractionID) {
		for (int i = 0; i < infractions.size(); i++) {
			if (infractions.get(i).getId() == infractionID) {
				infractions.remove(i);
			}
		}
	}


	/**
	 * The getInfractionsByUser method returns the list of infractions by
	 * user. This includes the users involved in the infraction.
	 * 
	 * @param userID		The user ID
	 * @return				The infractions with the user involved.
	 */
	public ArrayList<Infraction> getInfractionsByUser(int userID) {
		ArrayList<String> contents = FileWorker.readFile(infractionList);
		ArrayList<Infraction> returnInfractions = new ArrayList<Infraction>();
		for (int i = 1; i < contents.size(); i++) {
			if (contents.get(i).contains(String.valueOf(userID))) {
				returnInfractions.add(infractions.get(i - 1));
			}
		}
		return returnInfractions;
	}


	/**
	 * The addExistingInfractions method adds existing infractions from data to
	 * the infractions list.
	 */
	private void addExistingInfractions() {
		ArrayList<String> contents = FileWorker.readFile(infractionList);
		if (contents != null) {
			for (int i = 1; i < contents.size(); i++) {
				String info = contents.get(i);
				String infractionName = info.substring(0, info.indexOf(','));
				info = info.substring(info.indexOf(',') + 1);
				int infractionID = Integer.parseInt(info.substring(0, info.indexOf(',')));
				info = info.substring(info.indexOf(',') + 1);
				int studentInfractedID = Integer.parseInt(info.substring(0, info.indexOf(',')));
				info = info.substring(info.indexOf(',') + 1);
				int staffInfractingID = Integer.parseInt(info.substring(0, info.indexOf(',')));
				info = info.substring(info.indexOf(',') + 1);
				String infractionReason = info;
				infractions.add(new Infraction(infractionName, infractionID, 
						studentInfractedID, schoolID, staffInfractingID, infractionReason));
			}
		}
	}


	/**
	 * The modifyClassInFile method takes a class from data and edits its entry
	 * in the school list file and the ArrayList. The grading method is changed.
	 * 
	 * @param cl				The class to modify
	 * @param gradingMethod		The new grading method
	 */
	public void modifyClassInFile(SchoolClass cl, int gradingMethod) {
		try {
			for (int i = 0; i < classes.size(); i++) {
				if (classes.get(i).getClassID() == cl.getClassID()) {
					ArrayList<String> contents = FileWorker.readFile(classList);

					int refactorIndex = -1;
					for (int j = 0; j < contents.size(); j++) {
						if (contents.get(j).contains(Integer.toString(classes.get(i).getClassID()))) {
							refactorIndex = j;;
						}
					}

					String replace = contents.get(refactorIndex);
					String replacePreID = "";
					for (int k = 0; k < 3; k++) {
						replacePreID += replace.substring(0, replace.indexOf(',') + 1);
						replace = replace.substring(replace.indexOf(',') + 1);
					}

					BufferedWriter bWriter = new BufferedWriter(new FileWriter(classList, false));

					for (int l = 0; l < contents.size(); l++) {
						if (l != refactorIndex) {
							bWriter.write(contents.get(l) + "\n");
						} else {
							bWriter.write(replacePreID + gradingMethod + "\n");
						}
					}

					bWriter.close();
				}
			}
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}
	}


	/**
	 * Returns the name.
	 * 
	 * @return	The name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Returns the school ID.
	 * 
	 * @return	The school ID
	 */
	public int getSchoolID() {
		return schoolID;
	}


	/**
	 * Returns the list of users.
	 * 
	 * @return	The list of users
	 */
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();

		for (Administrator a : admins) {
			users.add(a);
		}

		for (Teacher t : teachers) {
			users.add(t);
		}

		for (Student s : students) {
			users.add(s);
		}

		return users;
	}


	/**
	 * Returns the list of classes.
	 * 
	 * @return	The list of classes
	 */
	public ArrayList<SchoolClass> getClasses() {
		return classes;
	}


	/**
	 * Returns the list of infractions.
	 * 
	 * @return	The list of infractions
	 */
	public ArrayList<Infraction> getInfractions() {
		return infractions;
	}


	/**
	 * Returns the number of blocks.
	 * 
	 * @return	The number of blocks
	 */
	public int getBlocks() {
		return blocks;
	}


	/**
	 * Sets the number of blocks.
	 * 
	 * @param blocks	Given number of blocks
	 */
	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}


	/**
	 * The refactorUser method edits a user in the ArrayList.
	 * 
	 * @param username		Given usernmae
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param id			Given ID
	 */
	public void refactorUser(String username, String firstName, String lastName, String password, int id) {
		for (Administrator a : admins) {
			if (a.getID() == id) {
				editUser(a, username, firstName, lastName, password);
				return;
			}
		}

		for (Teacher t : teachers) {
			if (t.getID() == id) {
				editUser(t, username, firstName, lastName, password);
				return;
			}
		}

		for (Student s : students) {
			if (s.getID() == id) {
				editUser(s, username, firstName, lastName, password);
				return;
			}
		}
	}


	/**
	 * The editUser method edits a user in the actual data file.
	 * 
	 * @param u				The user to edit
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 */
	private void editUser(User u, String username, String firstName, String lastName, String password) {
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

		ArrayList<String> contents = FileWorker.readFile(userList);

		int refactorIndex = -1;
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).contains(Integer.toString(u.getID()))) {
				refactorIndex = i;
			}
		}	

		String role = "";
		if (u.isAdministrator()) {
			role = "Administrator";
		} else if (u.isTeacher()) {
			role = "Teacher";
		} else if (u.isStudent()) {
			role = "Student";
		}

		try {
			FileWriter writer = new FileWriter(userList, false);
			BufferedWriter bWriter = new BufferedWriter(writer);

			for (int i = 0; i < contents.size(); i++) {
				if (i != refactorIndex) {
					bWriter.write(contents.get(i) + "\n");
				} else {
					bWriter.write(u.getUsername() + "," + u.getFirstName() + "," + u.getLastName() +
							"," + role + "," + u.getID() + "," + u.getPassword() + "\n");
				}
			}

			bWriter.close();
		} catch (Exception e) { }
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "School [name=" + name + ", schoolID=" + schoolID + ", userList=" + userList + ", classList=" + classList
				+ ", admins=" + admins + ", teachers=" + teachers + ", students=" + students + ", classes=" + classes
				+ "]";
	}




}
