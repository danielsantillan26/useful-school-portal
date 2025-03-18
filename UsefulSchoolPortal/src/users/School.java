package users;

import java.util.ArrayList;

public class School {

	private String name;
	private int schoolID;
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
	}
	
	
	public boolean addAdminstrator(Administrator a) {
		try {
			admins.add(a);
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
