package users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import files.FileManagement;
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


	public School(String name, int schoolID) {
		this.name = name;
		this.schoolID = schoolID;

		admins = new ArrayList<Administrator>();
		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();
		classes = new ArrayList<SchoolClass>();

		userList = new File("SchoolUsers_" + schoolID + ".csv");
		classList = new File("SchoolClasses_" + schoolID + ".txt");

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
			} else {
				
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
				FileManagement.addAdministratorToList(a);
			} else if (role.equals("Teacher")) {
				Teacher t = new Teacher(username, firstName, lastName,
						password, id, this.schoolID);
				teachers.add(t);
				FileManagement.addTeacherToList(t);
			} else {
				Student s = new Student(username, firstName, lastName,
						password, id, this.schoolID);
				students.add(s);
				FileManagement.addStudentToList(s);
			}
		}
	}


	public String getName() {
		return name;
	}


	public int getSchoolID() {
		return schoolID;
	}
	
	
	public ArrayList<String> getUserNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		for (Administrator a : admins) {
			names.add(a.getFirstName() + " " + a.getLastName() + ", Administrator");
		}
		
		for (Teacher t : teachers) {
			names.add(t.getFirstName() + " " + t.getLastName() + ", Teacher");
		}
		
		for (Student s : students) {
			names.add(s.getFirstName() + " " + s.getLastName() + ", Student");
		}
		
		return names;
	}

}
