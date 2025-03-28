package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import files.Constants;
import files.DataManagement;
import files.FileWorker;

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


	public School(String name, int schoolID) {
		this.name = name;
		this.schoolID = schoolID;

		admins = new ArrayList<Administrator>();
		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();
		classes = new ArrayList<SchoolClass>();

		userList = new File("SchoolUsers_" + schoolID + ".csv");
		classList = new File("SchoolClasses_" + schoolID + ".csv");
		
		blocks = 6;

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


	public boolean addAdministrator(Administrator a) {
		try {
			admins.add(a);
			writeInUser(a, "Administrator");
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean addTeacher(Teacher t) {
		try {
			teachers.add(t);
			writeInUser(t, "Teacher");
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean addStudent(Student s) {
		try {
			students.add(s);
			writeInUser(s, "Student");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean addClass(SchoolClass cl) {
		try {
			classes.add(cl);
			writeInClass(cl);
			return true;
		} catch (Exception e) {
			return false;
		}
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
			+ "," + role + "," + u.getId() + "," + u.getPassword());
			bWriter.close();
		} catch (Exception e) {

		}
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
		} catch (Exception e) {
			
		}
	}


	private void addExistingUsers() {
		ArrayList<String> contents = FileWorker.readFile(userList);
		for (int i = 1; i < contents.size(); i++) {
			String x = contents.get(i);
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
		}
	}
	
	
	private void addExistingClasses() {
		ArrayList<String> contents = FileWorker.readFile(classList);
		for (int i = 1; i < contents.size(); i++) {
			String x = contents.get(i);
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
			
		}
	}


	public void deleteUser(User u) {
		if (u.isAdministrator()) {
			for (int i = 0; i < admins.size(); i++) {
				if (admins.get(i).getId() == u.getId()) {
					admins.remove(i);
				}
			}
		} else if (u.isTeacher()) {
			for (int i = 0; i < teachers.size(); i++) {
				if (teachers.get(i).getId() == u.getId()) {
					teachers.remove(i);
				}
			}
		} else if (u.isStudent()) {
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getId() == u.getId()) {
					students.remove(i);
				}
			}
		}

		try {
			FileWorker.removeLine(userList, u.getId());
		} catch (Exception e) {

		}

	}
	
	
	public void deleteClass(SchoolClass sc) {
		try {
			for (int i = 0; i < classes.size(); i++) {
				if (classes.get(i).getClassID() == sc.getClassID()) {
					classes.get(i).deleteFiles();
					classes.remove(i);
				}
			}
			FileWorker.removeLine(classList, sc.getClassID());
		} catch (Exception e) {
			
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


	@Override
	public String toString() {
		return "School [name=" + name + ", schoolID=" + schoolID + ", userList=" + userList + ", classList=" + classList
				+ ", admins=" + admins + ", teachers=" + teachers + ", students=" + students + ", classes=" + classes
				+ "]";
	}
	
	
	

}
