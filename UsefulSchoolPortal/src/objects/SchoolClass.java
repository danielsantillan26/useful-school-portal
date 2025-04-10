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
	private String announcement;
	private int gradingMethod;
	private File classRoster;
	private File classAssignments;
	private File classAnnouncements;
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
		
		announcement = "";

		classRoster = new File("SchoolClass_" + schoolID + "_" + classID + "_Roster.csv");
		classAssignments = new File("SchoolClass_" + schoolID + "_" + classID + "_Assignments.csv");
		classAnnouncements = new File("SchoolClass_" + schoolID + "_" + classID + "_Announcements.csv");
		
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
			
			if (!classAnnouncements.exists()) {
				classAnnouncements.createNewFile();
			} else {
				ArrayList<String> contents = FileWorker.readFile(classAnnouncements);
				for (String s : contents) {
					announcement += s;
				}
			}
		} catch (Exception e) {
			
		}
	}


	private boolean addUser(User u, String role) {
		ArrayList<String> contents = FileWorker.readFile(classRoster);
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(classRoster, false));
			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}
			
			bWriter.write(u.getUsername() + "," + u.getFirstName() + "," +
			u.getLastName() + "," + role + "," + u.getID());
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
	
	
	public boolean hasID(int id) {
		ArrayList<String> contents = FileWorker.readFile(classRoster);
		boolean hasID = false;
		for (String str : contents) {
			if (str.contains(Integer.toString(id))) {
				hasID = true;
			}
		}
		return hasID;
	}
	
	
	public boolean deleteUser(User u) {
		return FileWorker.removeLine(classRoster, u.getID());
	}
	
	
	public boolean addStudent(Student s) {
		return addUser(s, "Student");
	}
	
	
	public boolean addTeacher(Teacher t) {
		return addUser(t, "Teacher");
	}
	
	
	public void addExistingStudent(Student s) {
		students.add(s);
	}
	
	
	public void addExistingTeacher(Teacher t) {
		teachers.add(t);
	}
	
	 
	public boolean deleteStudent(Student s) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getID() == s.getID()) {
				students.remove(i);
			}
		}
		return deleteUser(s);
	}
	 
	public boolean deleteTeacher(Teacher t) {
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).getID() == t.getID()) {
				teachers.remove(i);
				System.out.println("e");			}
		}
		return deleteUser(t);
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
	
	
	public String getAnnouncement() {
		return announcement;
	}
	
	
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}
	
	
	public ArrayList<Student> getStudents() {
		return students;
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
	
	
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(classAnnouncements, false));
			bWriter.write(announcement);
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
