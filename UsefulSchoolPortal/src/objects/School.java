package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import files.Constants;
import files.DataManagement;
import files.FileWorker;
import files.InvalidFileInputException;

public class School {

	private String name;
	private int schoolID;
	private File userList;
	private File classList;
	private ArrayList<Administrator> admins;
	private ArrayList<Teacher> teachers;
	private ArrayList<Student> students;
	private ArrayList<SchoolClass> classes;
	private int blocks;


	public School(String name, int schoolID, int blocks) {
		this.name = name;
		this.schoolID = schoolID;
		this.blocks = blocks;

		admins = new ArrayList<Administrator>();
		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();
		classes = new ArrayList<SchoolClass>();

		userList = new File("SchoolUsers_" + schoolID + ".csv");
		classList = new File("SchoolClasses_" + schoolID + ".csv");

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
		} catch (Exception e) {

		}
	}


	public void addAdministrator(Administrator a) {
		try {
			admins.add(a);
			writeInUser(a, "Administrator");
		} catch (Exception e) { }
	}


	public void addTeacher(Teacher t) {
		try {
			teachers.add(t);
			writeInUser(t, "Teacher");
		} catch (Exception e) { }
	}


	public void addStudent(Student s) {
		try {
			students.add(s);
			writeInUser(s, "Student");
		} catch (Exception e) { }
	}


	public void addClass(SchoolClass cl) {
		try {
			classes.add(cl);
			writeInClass(cl);
		} catch (Exception e) { }
	}


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

				for (Student s : students) {
					if (sc.hasID(s.getID())) {
						sc.addExistingStudent(s);
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


	public String getName() {
		return name;
	}


	public int getSchoolID() {
		return schoolID;
	}


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


	public ArrayList<SchoolClass> getClasses() {
		return classes;
	}


	public int getBlocks() {
		return blocks;
	}


	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}


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


	@Override
	public String toString() {
		return "School [name=" + name + ", schoolID=" + schoolID + ", userList=" + userList + ", classList=" + classList
				+ ", admins=" + admins + ", teachers=" + teachers + ", students=" + students + ", classes=" + classes
				+ "]";
	}




}
