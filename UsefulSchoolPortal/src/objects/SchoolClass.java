package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import files.FileWorker;

public class SchoolClass {

	private String name;
	private int block;
	private int classID;
	private int schoolID;
	private ArrayList<Teacher> teachers;
	private ArrayList<Student> students;
	private int gradingMethod;
	private File classRoster;
	private File classAssignments;
	private ArrayList<Assignment> assignments;
	/**
	private ArrayList<String> weightCategories;
	 */

	public SchoolClass(String name, int block, int gradingMethod, int schoolID) {
		this(name, block, gradingMethod, schoolID, (int)(Math.random()*10000000));
	}
	
	
	public SchoolClass(String name, int block, int gradingMethod, int schoolID, int classID) {
		this.name = name;
		this.block = block;
		this.gradingMethod = gradingMethod;
		this.schoolID = schoolID;
		this.classID = classID;
		
		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();

		classRoster = new File("SchoolClass_" + schoolID + "_" + classID + "_Roster.csv");
		classAssignments = new File("SchoolClass_" + schoolID + "_" + classID + "_Assignments.csv");
		
		try {
			if (!classRoster.exists()) {
				classRoster.createNewFile();
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(classRoster, false));
				bWriter.write("Username,First Name,Last Name,Role,User ID");
				bWriter.close();
			}
			
			if (!classAssignments.exists()) {
				classAssignments.createNewFile();
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(classAssignments, false));
				bWriter.write("Assignment Name,Assignment ID,Points or Weight");
				bWriter.close();
			}
		} catch (Exception e) {
			
		}
	}


	public boolean addUser(User u, String role) {
		ArrayList<String> contents = FileWorker.readFile(classRoster);
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(classRoster, false));
			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}
			
			bWriter.write(u.getUsername() + "," + u.getFirstName() + "," +
			u.getLastName() + "," + role + "," + u.getId());
			bWriter.close();
			
			if (role.equals("Teacher")) {
				teachers.add((Teacher) u);
			} else {
				students.add((Student) u);
			}
			
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	
	public boolean addStudent(Student s) {
		return addUser(s, "Student");
	}
	
	
	public boolean addTeacher(Teacher t) {
		return addUser(t, "Teacher");
	}
	
	public void deleteFiles() {
		classRoster.delete();
		classAssignments.delete();
	}


	
	
	
	public String getName() {
		return name;
	}
	
	
	public int getBlock() {
		return block;
	}


	public int getClassID() {
		return classID;
	}


	public int getSchoolID() {
		return schoolID;
	}
	
	
	public int getGradingMethod() {
		return gradingMethod;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setBlock(int block) {
		this.block = block;
	}


	public void setClassID(int classID) {
		this.classID = classID;
	}


	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}
	
	
	public void setGradingMethod(int gradingMethod) {
		this.gradingMethod = gradingMethod;
	}

}
