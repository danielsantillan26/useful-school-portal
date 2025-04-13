package objects;

import java.util.ArrayList;

public class Assignment {
	
	private String name;
	private int points;
	private String weightCategory;
	private ArrayList<Double> grades;
	private int assignmentID;
	private int classID;
	private int schoolID;
	
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
	}
	
	
	public Assignment(String name, String weightCategory, int id, int classID, int schoolID) {
		this.name = name;
		this.weightCategory = weightCategory;
		this.assignmentID = id;
		this.classID = classID;
		this.schoolID = schoolID;
		grades = new ArrayList<Double>();
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
	

	public int getAssignmentID() {
		return assignmentID;
	}


	public int getClassID() {
		return classID;
	}


	public int getSchoolID() {
		return schoolID;
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


	@Override
	public String toString() {
		return "Assignment [name=" + name + ", points=" + points + ", weightCategory=" + weightCategory + ", grades="
				+ grades + "]";
	}
	
	

}
