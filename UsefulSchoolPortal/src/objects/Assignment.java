package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import files.DataManagement;
import files.FileWorker;

public class Assignment {

	private String name;
	private int points;
	private String weightCategory;
	private ArrayList<Student> students;
	private ArrayList<Double> grades;
	private int assignmentID;
	private int classID;
	private int schoolID;
	private File gradesList;

	public Assignment(String name, int points, int classID, int schoolID) {
		this(name, points, (int)(Math.random()*1000000000), classID, schoolID);
	}


	public Assignment(String name, String weightCategory, int classID, int schoolID) {
		this(name, weightCategory, (int)(Math.random()*1000000000), classID, schoolID);
	}


	public Assignment(String name, int points, int id, int classID, int schoolID) {
		this.name = name;
		this.points = points;
		this.assignmentID = id;
		this.classID = classID;
		this.schoolID = schoolID;
		grades = new ArrayList<Double>();
		students = new ArrayList<Student>();
		gradesList = new File("Assignment_" + schoolID + "_" + classID + "_" + assignmentID + "_Grades.csv");
		createFile();
	}


	public Assignment(String name, String weightCategory, int id, int classID, int schoolID) {
		this.name = name;
		this.weightCategory = weightCategory;
		this.assignmentID = id;
		this.classID = classID;
		this.schoolID = schoolID;
		grades = new ArrayList<Double>();
		students = new ArrayList<Student>();
		gradesList = new File("Assignment_" + schoolID + "_" + classID + "_" + assignmentID + "_Grades.csv");
		createFile();
	}
	
	
	
	private void createFile() {
		if (gradesList.exists()) {
			ArrayList<String> contents = FileWorker.readFile(gradesList);
			if (contents.size() > 1) {
				
			}
		} else {
			try {
				gradesList.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(gradesList, false));
				bw.write("Total Points/Weight,Grade,Grade,Grade,Grade,Grade,Grade,\n");
				
				students = DataManagement.getClassStudents(classID);
				for (int i = 0; i < students.size(); i++) {
					bw.write("NA,");
				}
				
				bw.close();
			} catch (IOException e) {

			}
		}
	}
	
	
	



	public boolean setGrade(int index, double grade) {
		try {
			grades.set(index, grade);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean addStudentDisplacement(int index) {
		try {
			grades.add(index, null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean removeStudentDisplacement(int index) {
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void deleteFiles() {
		gradesList.delete();
	}
	
	
	public String getName() {
		return name;
	}


	public int getAssignmentID() {
		return assignmentID;
	}


	public int getClassID() {
		return classID;
	}


	public int getSchoolID() {
		return schoolID;
	}
	
	
	public int getPoints() {
		return points;
	}
	
	
	public String getWeightCategory() {
		return weightCategory;
	}


	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}


	public void setClassID(int classID) {
		this.classID = classID;
	}


	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}
	
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	
	public void setWeightCategory(String weightCategory) {
		this.weightCategory = weightCategory;
	}


	@Override
	public String toString() {
		return "Assignment [name=" + name + ", points=" + points + ", weightCategory=" + weightCategory + ", grades="
				+ grades + "]";
	}



}
