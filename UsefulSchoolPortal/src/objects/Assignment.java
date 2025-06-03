package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import files.Constants;
import files.DataManagement;
import files.FileWorker;

/**
 * The Assignment class is a representation of an assignment. It has important
 * values such as grades (stored in a file), IDs, and more to ensure that it
 * performs the functions of an assignment in the program.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class Assignment {

	/** Assignment name */
	private String name;
	/** Assignment points value */
	private int points;
	/** Assignment weight category */
	private String weightCategory;
	/** List of assignment grades */
	private ArrayList<Double> grades;
	/** Assignment ID */
	private int assignmentID;
	/** Class ID */
	private int classID;
	/** School ID */
	private int schoolID;
	/** Grades List File */
	private File gradesList;

	/**
	 * This constructor builds a new assignment, as the ID is randomized. All
	 * other fields are set by the program parameters from the user. This is for
	 * an assignment that uses the points scoring method.
	 * 
	 * @param name			Given name
	 * @param points		Given points value
	 * @param classID		Given class ID
	 * @param schoolID		Given school ID
	 */
	public Assignment(String name, int points, int classID, int schoolID) {
		this(name, points, (int)(Math.random()*1000000000), classID, schoolID);
	}


	/**
	 * This constructor builds a new assignment, as the ID is randomized. All
	 * other fields are set by the program parameters from the user. This is for
	 * an assignment that uses weights.
	 * 
	 * @param name				Given name
	 * @param weightCategory	Given weight category
	 * @param classID			Given class ID
	 * @param schoolID			Given school ID
	 */
	public Assignment(String name, String weightCategory, int classID, int schoolID) {
		this(name, weightCategory, (int)(Math.random()*1000000000), classID, schoolID);
	}


	/**
	 * This constructor builds a new assignment, as the ID is randomized. All
	 * other fields are set by the program paremeters from the user. This is for
	 * an assignment that uses a basic percents system.
	 * 
	 * @param name				Given name
	 * @param classID			Given class ID
	 * @param schoolID			Given school ID
	 */
	public Assignment(String name, int classID, int schoolID) {
		this((int)(Math.random()*1000000000), name, classID, schoolID);
	}


	/**
	 * This constructor builds an existing assignment, as the ID along with the
	 * other fields are set by the program parameters from the user. This is for
	 * an assignment that uses a points system.
	 * 
	 * @param name			Given name
	 * @param points		Given points
	 * @param id			Given ID
	 * @param classID		Given class ID
	 * @param schoolID		Given school ID
	 */
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


	/**
	 * This constructor builds an existing assignment, as the ID along with the
	 * other fields are set by the program parameters from the user. This is for
	 * an assignment that uses weights.
	 * 
	 * @param name					Given name
	 * @param weightCategory		Given weight category
	 * @param id					Given ID
	 * @param classID				Given class ID
	 * @param schoolID				Given school ID
	 */				
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


	/**
	 * This constructor builds an existing assignment, as the ID along with the
	 * other fields are set by the program parameters from the user. This is for
	 * an assignment that uses a basic percents system.
	 * 
	 * @param id			Given ID
	 * @param name			Given name
	 * @param classID		Given class ID
	 * @param schoolID		Given school ID
	 */
	public Assignment(int id, String name, int classID, int schoolID) {
		this.name = name;
		this.assignmentID = id;
		this.classID = classID;
		this.schoolID = schoolID;
		grades = new ArrayList<Double>();
		gradesList = new File("Assignment_" + schoolID + "_" + classID + "_" + assignmentID + "_Grades.csv");
		createFile();
	}


	/**
	 * The createFile method creates the file with the grades list. If the file
	 * was already created, the data is read in and is added to the grades
	 * ArrayList.
	 */
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


	/**
	 * Returns the list of grades.
	 * 
	 * @return The list of grades
	 */
	public ArrayList<Double> getGrades() {
		return grades;
	}


	/**
	 * The setGrades method sets the grades of the students in the assignment.
	 * It is also written in the file.
	 * 
	 * @param grades		The list of grades
	 * @return				Whether or not everything was added properly
	 */
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


	/**
	 * The addStudentDisplacement method adds a new student's student ID to
	 * the grades list file.
	 * 
	 * @param studentID			The student ID of the new student
	 */
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


	/**
	 * The removeStudentDisplacement method removes a deleted student's info
	 * from the grades list file.
	 * 
	 * @param index		The index of the student on the file
	 */
	public void removeStudentDisplacement(int index) {
		FileWorker.removeLineByIndex(gradesList, index + 1);
		grades.remove(index);
	}


	/**
	 * The getIndividualStudentGrade method gets a student's individual
	 * grade from an assignment.
	 * 
	 * @param studentID		The student ID
	 * @return				The grade of the student
	 */
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


	/**
	 * The deleteFiles() method deletes the grades list file.
	 */
	public void deleteFiles() {
		gradesList.delete();
	}


	/**
	 * Returns the name.
	 * 
	 * @return	The name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Returns the assignment ID.
	 * 
	 * @return The assignment ID
	 */
	public int getAssignmentID() {
		return assignmentID;
	}


	/**
	 * Returns the class ID.
	 * 
	 * @return	The class ID
	 */
	public int getClassID() {
		return classID;
	}


	/**
	 * Returns the school ID.
	 * 
	 * @return	The school ID
	 */
	public int getSchoolID() {
		return schoolID;
	}


	/**
	 * Returns the assignment points value.
	 * 
	 * @return	The assignment points value
	 */
	public int getPoints() {
		return points;
	}


	/**
	 * Returns the weight category.
	 * 
	 * @return	The weight category
	 */
	public String getWeightCategory() {
		return weightCategory;
	}


	/**
	 * Sets the name.
	 * 
	 * @param name	Given name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Sets the assignment ID.
	 * 
	 * @param assignmentID	Given assignment ID
	 */
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}


	/**
	 * Sets the class ID.
	 * 
	 * @param classID	Given class ID
	 */
	public void setClassID(int classID) {
		this.classID = classID;
	}


	/**
	 * Sets the school ID.
	 * 
	 * @param schoolID	Given school ID
	 */
	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}


	/**
	 * Sets the points.
	 * 
	 * @param points	Given points
	 */
	public void setPoints(int points) {
		this.points = points;
	}


	/**
	 * Sets the weight category.
	 * 
	 * @param weightCategory	Given weight category.
	 */
	public void setWeightCategory(String weightCategory) {
		this.weightCategory = weightCategory;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "Assignment [name=" + name + ", points=" + points + ", weightCategory=" + weightCategory + ", grades="
				+ grades + "]";
	}



}
