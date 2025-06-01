package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import files.Constants;
import files.DataManagement;
import files.FileWorker;

public class Assignment {

	private String name;
	private int points;
	private String weightCategory;
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
	
	
	public Assignment(String name, int classID, int schoolID) {
		this((int)Math.random()*1000000000, name, classID, schoolID);
	}


	public Assignment(String name, int points, int id, int classID, int schoolID) {
		this.name = name;
		this.points = points;
		this.assignmentID = id;
		this.classID = classID;
		this.schoolID = schoolID;
		grades = new ArrayList<Double>();
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
		gradesList = new File("Assignment_" + schoolID + "_" + classID + "_" + assignmentID + "_Grades.csv");
		createFile();
	}
	
	
	public Assignment(int id, String name, int classID, int schoolID) {
		this.name = name;
		this.assignmentID = id;
		this.classID = classID;
		this.schoolID = schoolID;
		grades = new ArrayList<Double>();
		gradesList = new File("Assignment_" + schoolID + "_" + classID + "_" + assignmentID + "_Grades.csv");
		createFile();
	}
	
	
	
	private void createFile() {
		if (gradesList.exists()) {
			ArrayList<String> contents = FileWorker.readFile(gradesList);
			if (contents.size() > 1) {
				for (int i = 1; i < contents.size(); i++) {
					if (contents.get(i).substring(contents.get(i).indexOf(",") + 1).equals(Character.toString(Constants.DELIMITER_NULL_GRADE)) || 
							contents.get(i).substring(contents.get(i).indexOf(",") + 1).equals("")) {
						grades.add(null);
					} else {
						grades.add(Double.parseDouble(contents.get(i).substring(contents.get(i).indexOf(",") + 1)));
					}
				}
			}
		} else {
			try {
				gradesList.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(gradesList, false));
				bw.write("Student ID,Grade,\n");
				
				ArrayList<Student> students = DataManagement.getClassStudents(classID);
				for (int i = 0; i < students.size(); i++) {
					bw.write(String.valueOf(students.get(i).getID()) + ",\n");
				}
				
				bw.close();
			} catch (IOException e) {

			}
		}
	}
	
	
	public ArrayList<Double> getGrades() {
		return grades;
	}



	public boolean setGrades(ArrayList<Double> grades) {
		try {
			this.grades = grades;
			ArrayList<String> contents = FileWorker.readFile(gradesList);
			BufferedWriter bw = new BufferedWriter(new FileWriter(gradesList, false));
			
			for (int i = 0; i < contents.size(); i++) {
				if (i == 0) {
					bw.write(contents.get(i).substring(0, contents.get(i).indexOf(",")) + ",\n");
				} else {
					if (grades.get(i - 1) == null) {
						bw.write(contents.get(i).substring(0, contents.get(i).indexOf(",")) + "," + Constants.DELIMITER_NULL_GRADE + "\n");
					} else {
						bw.write(contents.get(i).substring(0, contents.get(i).indexOf(",")) + "," + String.valueOf(grades.get(i - 1)) + "\n");
					}
				}
			}
			
			bw.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public void addStudentDisplacement(int studentID) {
		try {
			ArrayList<String> contents = FileWorker.readFile(gradesList);
			BufferedWriter bw = new BufferedWriter(new FileWriter(gradesList, false));
			
			for (int i = 0; i < contents.size(); i++) {
				bw.write(contents.get(i) + "\n");
			}
			
			bw.write(String.valueOf(studentID) + ",");
			bw.close();
		} catch (Exception e) {
			
		}
	}


	public void removeStudentDisplacement(int index) {
		FileWorker.removeLineByIndex(gradesList, index + 1);
		grades.remove(index);
	}
	
	
	public double getIndividualStudentGrade(int studentID) {
		ArrayList<String> contents = FileWorker.readFile(gradesList);
		String grade = "";
		for (String s : contents) {
			if (s.contains(String.valueOf(studentID))) {
				grade = s.substring(s.indexOf(",") + 1);
			}
		}
		
		if (grade.equals(Character.toString(Constants.DELIMITER_NULL_GRADE)) || grade.equals("")) {
			return -1;
		} else {
			return Double.parseDouble(grade);
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
	
	
	public void setName(String name) {
		this.name = name;
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
