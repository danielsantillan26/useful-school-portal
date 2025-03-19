package users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

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
			}

			if (!classList.exists()) {
				classList.createNewFile();
			}
		} catch (Exception e) {

		}
	}


	public boolean addAdminstrator(Administrator a) {
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
			FileWriter writer = new FileWriter(userList, false);
			BufferedWriter bWriter = new BufferedWriter(writer);
			ArrayList<String> contents = FileWorker.readFile(userList);

			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}

			bWriter.write(u.getUsername() + "," + u.getFirstName() + "," + u.getLastName()
			+ "," + role + "," + u.getId() + "," + u.getPassword());
			writer.close();
		} catch (Exception e) {

		}
	}


	public String getName() {
		return name;
	}


	public int getSchoolID() {
		return schoolID;
	}

}
