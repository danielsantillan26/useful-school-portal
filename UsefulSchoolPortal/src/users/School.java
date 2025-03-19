package users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

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
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean addTeacher(Teacher t) {
		try {
			teachers.add(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getSchoolID() {
		return schoolID;
	}

}
