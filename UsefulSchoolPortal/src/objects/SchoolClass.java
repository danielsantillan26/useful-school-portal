package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import calculations.GradeCalculations;
import files.Constants;
import files.FileWorker;

/**
 * The SchoolClass class is a representation of a class in a school system. It
 * has many essential fields for a course as well as files that hold data for
 * the class.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class SchoolClass {

	/** The class's name */
	private String name;
	/** This class's block */
	private int block;
	/** The class's ID */
	private int classID;
	/** The class's school ID */
	private int schoolID;
	/** The class's list of teachers */
	private ArrayList<Teacher> teachers;
	/** The class's list of students */
	private ArrayList<Student> students;
	/** The class's announcement */
	private String announcement;
	/** The class's grading method */
	private int gradingMethod;
	/** The class's roster file */
	private File classRoster;
	/** The class's assignments file */
	private File classAssignments;
	/** The class's announcements file */
	private File classAnnouncements;
	/** The class's weights file */
	private File classWeights;
	/** The list of assignments */
	private ArrayList<Assignment> assignments;
	/** The list of weight categories */
	private ArrayList<String> weightCategories;
	/** The list of weight percents */
	private ArrayList<Integer> weightPercents;


	/**
	 * This constructor builds a new class, as the ID is randomized. All other
	 * fields are set by the program parameters from the user.
	 * 
	 * @param name				Given name
	 * @param block				Given block
	 * @param gradingMethod		Given grading method
	 * @param schoolID			Given school ID
	 */
	public SchoolClass(String name, int block, int gradingMethod, int schoolID) {
		this(name, block, gradingMethod, schoolID, (int)(Math.random()*10000000));
	}


	/**
	 * This constructor builds an existing class, as the ID along with the other
	 * fields are set by the program parameters from the user. The files are
	 * instantiated and set up in this method.
	 * 
	 * @param name				Given name
	 * @param block				Given block
	 * @param gradingMethod		Given grading method
	 * @param schoolID			Given school ID
	 * @param classID			Given class ID
	 */
	public SchoolClass(String name, int block, int gradingMethod, int schoolID, int classID) {
		this.name = name;
		this.block = block;
		this.gradingMethod = gradingMethod;
		this.schoolID = schoolID;
		this.classID = classID;

		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();
		assignments = new ArrayList<Assignment>();
		weightCategories = new ArrayList<String>();
		weightPercents = new ArrayList<Integer>();

		announcement = "";

		classRoster = new File("SchoolClass_" + schoolID + "_" + classID + "_Roster.csv");
		classAssignments = new File("SchoolClass_" + schoolID + "_" + classID + "_Assignments.csv");
		classAnnouncements = new File("SchoolClass_" + schoolID + "_" + classID + "_Announcements.txt");
		classWeights = new File("SchoolClass_" + schoolID + "_" + classID + "_Weights.csv");

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
			} else {
				addExistingAssignments();
			}

			if (!classAnnouncements.exists()) {
				classAnnouncements.createNewFile();
			} else {
				ArrayList<String> contents = FileWorker.readFile(classAnnouncements);
				for (String s : contents) {
					announcement += s;
				}
			}

			if (!classWeights.exists()) {
				classWeights.createNewFile();
			} else {
				addWeights();
			}

		} catch (Exception e) {

		}
	}


	/**
	 * The addUser method adds a user to the class.
	 * 
	 * @param u			The user to add
	 * @param role		The user's role
	 * @return			Whether the method ran smoothly
	 */
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


	/**
	 * The hasId method returns whether a user with the given ID exists in the
	 * list of users.
	 * 
	 * @param id		The given ID
	 * @return			Whether or not a user with the ID is in the class
	 */
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


	/**
	 * The deleteUser method deletes a user from the files list.
	 * 
	 * @param u		The user to remove
	 * @return		Whether the removal was successful
	 */
	public boolean deleteUser(User u) {
		return FileWorker.removeLine(classRoster, u.getID());
	}


	/**
	 * The addStudent method adds a student to the list of students and the
	 * file. The student's ID is alo added to the list of IDs for the class
	 * assignments.
	 * 
	 * @param s		The student to add
	 * @return		Whether the addition worked
	 */
	public boolean addStudent(Student s) {
		for (Assignment a : assignments) {
			a.addStudentDisplacement(s.getID());
		}
		return addUser(s, "Student");
	}


	/**
	 * The addTeacher method adds a teacher to the list of teachers and the
	 * file.
	 * 
	 * @param t			The teacher to add
	 * @return			Whether the addition worked
	 */
	public boolean addTeacher(Teacher t) {
		return addUser(t, "Teacher");
	}


	/**
	 * The addExistingStudent method adds an existing student from the file.
	 * 
	 * @param s		The student to add
	 */
	public void addExistingStudent(Student s) {
		students.add(s);
	}


	/**
	 * The addExistingTeacher method adds an existing teacher from the file.
	 * 
	 * @param t		The teacher to add
	 */
	public void addExistingTeacher(Teacher t) {
		teachers.add(t);
	}


	/**
	 * The deleteStudent method removes a student from the ArrayList and the
	 * file. The student's ID is also removed from the assignment files.
	 * 
	 * @param s		The student to remove
	 * @return		Whether the removal worked
	 */
	public boolean deleteStudent(Student s) {
		int index = 0;
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getID() == s.getID()) {
				students.remove(i);
				index = i;
			}
		}

		for (Assignment a : assignments) {
			a.removeStudentDisplacement(index);
		}
		return deleteUser(s);
	}


	/**
	 * The deleteTeacher method removes a teacher from the ArrayList and the
	 * file.
	 * 
	 * @param t		The teacher to remove
	 * @return		Whether the removal worked
	 */
	public boolean deleteTeacher(Teacher t) {
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).getID() == t.getID()) {
				teachers.remove(i);
				System.out.println("e");			
			}
		}
		return deleteUser(t);
	}


	/**
	 * The deleteFiles method removes all the files.
	 */
	public void deleteFiles() {
		classRoster.delete();
		classAssignments.delete();
		classAnnouncements.delete();
	}


	/**
	 * The addNewAssignment method adds a new assignment to the assignments
	 * ArrayList and the file.
	 * 
	 * @param a		The assignment to add
	 */
	public void addNewAssignment(Assignment a) {
		assignments.add(a);
		String name = a.getName();
		String assignmentID = String.valueOf(a.getAssignmentID());
		String dataInput = "";
		if (gradingMethod == Constants.GRADE_POINTS) {
			dataInput = name + "," + assignmentID + "," + String.valueOf(a.getPoints());
		} else if (gradingMethod == Constants.GRADE_WEIGHTS) {
			dataInput = name + "," + assignmentID + "," + a.getWeightCategory();
		} else {
			dataInput = name + "," + assignmentID;
		}

		ArrayList<String> contents = FileWorker.readFile(classAssignments);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(classAssignments, false));
			for (int i = 0; i < contents.size(); i++) {
				bw.write(contents.get(i) + "\n");
			}
			bw.write(dataInput);
			bw.close();
		} catch (IOException e) {
		}

	}


	/**
	 * The deleteAssignment method deletes an assignment from the ArrayList and
	 * the file.
	 * 
	 * @param a		The assignment to remove
	 */
	public void deleteAssignment(Assignment a) {
		assignments.remove(a);
	}	


	/**
	 * The modifyexistingAssignment method modifies an existing assignment by
	 * searching for its ID and modifying the line with that ID on the file.
	 * This method is for assignments with weight grading.
	 * 
	 * @param assignmentID			The assignment ID
	 * @param name					The given name
	 * @param weightCategory		The given weight category
	 */
	public void modifyExistingAssignment(int assignmentID, String name, String weightCategory) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				a.setName(name);
				a.setWeightCategory(weightCategory);
			}
		}

		ArrayList<String> contents = FileWorker.readFile(classAssignments);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(classAssignments, false));
			for (int i = 0; i < contents.size(); i++) {
				if (contents.get(i).contains(String.valueOf(assignmentID))) {
					bw.write(name + "," + String.valueOf(assignmentID) + "," + weightCategory);
				} else {
					bw.write(contents.get(i) + "\n");
				}
			}
			bw.close();
		} catch (Exception e) { }

	}


	/**
	 * The modifyexistingAssignment method modifies an existing assignment by
	 * searching for its ID and modifying the line with that ID on the file.
	 * This method is for assignments with points grading.
	 * 
	 * @param assignmentID			The assignment ID
	 * @param name					The given name
	 * @param points				The given points count
	 */
	public void modifyExistingAssignment(int assignmentID, String name, int points) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				a.setName(name);
				a.setPoints(points);
			}
		}

		ArrayList<String> contents = FileWorker.readFile(classAssignments);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(classAssignments, false));
			for (int i = 0; i < contents.size(); i++) {
				if (contents.get(i).contains(String.valueOf(assignmentID))) {
					bw.write(name + "," + String.valueOf(assignmentID) + "," + String.valueOf(points));
				} else {
					bw.write(contents.get(i) + "\n");
				}
			}
			bw.close();
		} catch (Exception e) { }
	}


	/**
	 * The modifyexistingAssignment method modifies an existing assignment by
	 * searching for its ID and modifying the line with that ID on the file.
	 * This method is for assignments with percents grading.
	 * 
	 * @param assignmentID			The assignment ID
	 * @param name					The given name
	 */
	public void modifyExistingAssignment(int assignmentID, String name) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				a.setName(name);
			}
		}

		ArrayList<String> contents = FileWorker.readFile(classAssignments);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(classAssignments, false));
			for (int i = 0; i < contents.size(); i++) {
				if (contents.get(i).contains(String.valueOf(assignmentID))) {
					bw.write(name + "," + String.valueOf(assignmentID));
				} else {
					bw.write(contents.get(i) + "\n");
				}
			}
			bw.close();
		} catch (Exception e) { }
	}


	/**
	 * The deleteAssignment method deletes an assignment by assignment ID.
	 * 
	 * @param assignmentID		The ID of the assignment to remove
	 */
	public void deleteAssignment(int assignmentID) {
		FileWorker.removeLine(classAssignments, assignmentID);
		for (int i = 0; i < assignments.size(); i++) {
			if (assignments.get(i).getAssignmentID() == assignmentID) {
				assignments.remove(i);
			}
		}
	}


	/**
	 * The deleteAllAssignments method removes all the assignments from the
	 * assignments list.
	 */
	public void deleteAllAssignments() {
		if (assignments != null) {
			for (Assignment a : assignments) {
				a.deleteFiles();
			}
			assignments.clear();
		}
	}


	/**
	 * The setWeights method sets the weights for the class. Weights require a
	 * list of categories and a list of percents.
	 * 
	 * @param givenCategories		The list of categories
	 * @param givenPercents			The list of percents
	 * @return						Whether the change went smoothly
	 */
	public boolean setWeights(ArrayList<String> givenCategories, ArrayList<Integer> givenPercents) {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(classWeights, false));
			bWriter.write("Category,Percent\n");
			for (int i = 0; i < givenCategories.size(); i++) {
				bWriter.write(givenCategories.get(i) + "," + String.valueOf(givenPercents.get(i)) + "\n");
			}
			bWriter.close();

			boolean deleteAssignments = false;
			if (givenCategories.size() != weightCategories.size()) {
				deleteAssignments = true;
			} else {
				for (int j = 0; j < givenCategories.size(); j++) {
					if (!givenCategories.get(j).equals(weightCategories.get(j))) {
						deleteAssignments = true;
					}
				}
			}
			if (deleteAssignments) {
				deleteAllAssignments();
			}

			addWeights();
			return true;
		} catch (IOException e) {
			return false;
		}
	}


	/**
	 * The addWeights method adds weights to the class from the file.
	 */
	private void addWeights() {
		ArrayList<String> contents = FileWorker.readFile(classWeights);
		if (contents != null) {
			for (int i = 1; i < contents.size(); i++) {
				weightCategories.add(contents.get(i).substring(0, contents.get(i).indexOf(",")));
				weightPercents.add(Integer.parseInt(contents.get(i).substring(contents.get(i).indexOf(",") + 1)));
			}
		}
	}


	/**
	 * The getIndividualAssignmentWeightCategory method takes an assignment and
	 * returns its individual weight category.
	 * 
	 * @param assignmentID		The assignment ID
	 * @return					The weight category of the assignment
	 */
	public String getIndividualAssignmentWeightCategory(int assignmentID) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				return a.getWeightCategory();
			}
		}
		return null;
	}


	/**
	 * The getIndividualAssignmentPoints method takes an assignmet and returns
	 * its indivdiual points value.
	 * 
	 * @param assignmentID		The assignment ID
	 * @return					The points value of the assignment
	 */
	public int getIndividualAssignmentPoints(int assignmentID) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				return a.getPoints();
			}
		}
		return -1;
	}


	/**
	 * The addExistingAssignments method adds assignments from the
	 * assignments file.
	 */
	private void addExistingAssignments() {
		ArrayList<String> contents = FileWorker.readFile(classAssignments);
		if (contents != null) {
			for (int i = 1; i < contents.size(); i++) {
				String x = contents.get(i);
				String name = x.substring(0, x.indexOf(","));
				x = x.substring(x.indexOf(",") + 1);
				int assignmentID = Integer.parseInt(x.substring(0, x.indexOf(",")));
				x = x.substring(x.indexOf(",") + 1);
				if (gradingMethod == Constants.GRADE_WEIGHTS) {
					String weightCategory = x;
					assignments.add(new Assignment(name, weightCategory, assignmentID, this.classID, this.schoolID));
				} else if (gradingMethod == Constants.GRADE_POINTS) {
					int points = Integer.parseInt(x);
					assignments.add(new Assignment(name, points, assignmentID, this.classID, this.schoolID));
				} else {
					assignments.add(new Assignment(name, assignmentID, this.classID, this.schoolID));
				}
			}
		}
	}


	/**
	 * The getGrades method returns the grades of all students from a particular
	 * assignment.
	 * 
	 * @param assignmentID		The assignment ID
	 * @return					The grades of all students of the assignment
	 */
	public ArrayList<Double> getGrades(int assignmentID) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				return a.getGrades();
			}
		}
		return null;
	}


	/**
	 * The getAllAverages method returns the average grade of every student in
	 * the class.
	 * 
	 * @return		The average grade of every student in the class
	 */
	public ArrayList<Double> getAllAverages() {
		ArrayList<Double> averages = new ArrayList<Double>();

		for (Student s : students) {
			averages.add(getAverage(s.getID()));
		}

		return averages;
	}


	/**
	 * The getIndividualStudentAssignmentGrades method gets an individual's
	 * grade for every assignment in the class.
	 * 	
	 * @param studentID		The student ID
	 * @return				The grade for every assignment available
	 */
	public ArrayList<Double> getIndividualStudentAssignmentGrades(int studentID) {
		ArrayList<Double> individualGrades = new ArrayList<Double>();
		for (Assignment a : assignments) {
			individualGrades.add(a.getIndividualStudentGrade(studentID));
		}
		return individualGrades;
	}


	/**
	 * The getAverage method returns the average grade based on the grades
	 * of all assignments. For weight categories without an assignment, the
	 * program reassigns percents equally to make the grade realistic.
	 * 
	 * @param studentID			The student ID
	 * @return					The overall average
	 */
	public double getAverage(int studentID) {
		if (gradingMethod == Constants.GRADE_POINTS) {
			int totalPoints = 0;
			double earnedPoints = 0;
			for (Assignment a : assignments) {
				if (a.getIndividualStudentGrade(studentID) != -1) {
					earnedPoints += a.getIndividualStudentGrade(studentID);
					totalPoints += a.getPoints();
				}
			}
			return GradeCalculations.calculatePointsGrade(earnedPoints, totalPoints);
		} else if (gradingMethod == Constants.GRADE_PERCENTS) {
			int totalAssignments = 0;
			double earnedPoints = 0;
			for (Assignment a : assignments) {
				if (a.getIndividualStudentGrade(studentID) != -1) {
					earnedPoints += a.getIndividualStudentGrade(studentID);
					totalAssignments++;
				}
			}
			return GradeCalculations.calculatePercentGrade(earnedPoints, totalAssignments);
		} else {
			ArrayList<Double> weightedGrades = new ArrayList<Double>();
			ArrayList<Integer> adjustedPercents = new ArrayList<Integer>();
			int percentToAdd = 0;
			for (int i = 0; i < weightCategories.size(); i++) {
				int earnedPoints = 0;
				int totalAssignments = 0;
				for (Assignment a : assignments) {
					if (a.getWeightCategory().equals(weightCategories.get(i))) {
						if (a.getIndividualStudentGrade(studentID) != -1) {
							earnedPoints += a.getIndividualStudentGrade(studentID);
							totalAssignments++;
						}
					}
				}
				if (totalAssignments == 0) {
					percentToAdd += weightPercents.get(i);
				} else {
					weightedGrades.add(GradeCalculations.calculatePercentGrade(earnedPoints, totalAssignments));
					adjustedPercents.add(weightPercents.get(i));
				}
			}

			for (int i = 0; i < adjustedPercents.size(); i++) {
				adjustedPercents.set(i, adjustedPercents.get(i) + (percentToAdd/adjustedPercents.size()));
			}

			return GradeCalculations.calculateWeightGrade(weightedGrades, adjustedPercents);
		}
	}


	/**
	 * The setGrades method sets the grades for a particular assignment.
	 * 
	 * @param assignmentID		The assignment ID
	 * @param grades			The grades to set to
	 */
	public void setGrades(int assignmentID, ArrayList<Double> grades) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				a.setGrades(grades);
			}
		}
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
	 * Returns the block.
	 * 
	 * @return	The block
	 */
	public int getBlock() {
		return block;
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
	 * Returns the grading method.
	 * 
	 * @return	The grading method
	 */
	public int getGradingMethod() {
		return gradingMethod;
	}


	/**
	 * Returns the announcement.
	 * 
	 * @return	The announcement
	 */
	public String getAnnouncement() {
		return announcement;
	}


	/**
	 * Returns the list of teachers.
	 * 
	 * @return	The list of teachers
	 */
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}


	/**
	 * Returns the list of students.
	 * 
	 * @return	The list of students
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}

	/**
	 * The getStudentIDs method returns the student IDs from the roster file
	 * to ensure proper ordering and grading display for the program.
	 * 
	 * @return	The list of student IDs in order of appearance on the file.
	 */
	public ArrayList<Integer> getStudentIDs() {
		ArrayList<String> contents = FileWorker.readFile(classRoster);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (String s : contents) {
			if (s.contains("Student")) {
				for (int i = 0; i < 4; i++) {
					s = s.substring(s.indexOf(',') + 1);
				}
				ids.add(Integer.parseInt(s));
			}
		}
		return ids;
	}

	/**
	 * Returns the list of assignments.
	 * 
	 * @return	The list of assignments.
	 */
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}


	/**
	 * Returns the list of weight categories.
	 * 
	 * @return	The list of weight categories
	 */
	public ArrayList<String> getWeightCategories() {
		return weightCategories;
	}


	/**
	 * Returns the list of weight percents.
	 * 
	 * @return		The list of weight percents.
	 */
	public ArrayList<Integer> getWeightPercents() {
		return weightPercents;
	}


	/**
	 * Returns the percent of a particular weight by its name.
	 * 
	 * @param weight		The weight
	 * @return				The percent value of the weight
	 */
	public int getPercentByCategory(String weight) {
		for (int i = 0; i < weightCategories.size(); i++) {
			if (weightCategories.get(i).equals(weight)) {
				return weightPercents.get(i);
			}
		}
		return -1;
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
	 * Sets the block.
	 * 
	 * @param block		Given block
	 */
	public void setBlock(int block) {
		this.block = block;
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
	 * Sets the grading method.
	 * 
	 * @param gradingMethod		Given grading method
	 */
	public void setGradingMethod(int gradingMethod) {
		if (this.gradingMethod != gradingMethod) {
			deleteAllAssignments();
		}
		this.gradingMethod = gradingMethod;
	}


	/**
	 * Sets the announcement.
	 * 
	 * @param announcement	Given announcement
	 */
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


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "SchoolClass [name=" + name + ", block=" + block + ", classID=" + classID + ", schoolID=" + schoolID
				+ ", teachers=" + teachers + ", students=" + students + ", announcement=" + announcement
				+ ", gradingMethod=" + gradingMethod + ", classRoster=" + classRoster + ", classAssignments="
				+ classAssignments + ", classAnnouncements=" + classAnnouncements + ", classWeights=" + classWeights
				+ ", assignments=" + assignments + ", weightCategories=" + weightCategories + ", weightPercents="
				+ weightPercents + "]";
	}
}
