package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import files.Constants;
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
	private File classWeights;
	private ArrayList<Assignment> assignments;
	private ArrayList<String> weightCategories;
	private ArrayList<Integer> weightPercents;

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
				System.out.println("e");			
			}
		}
		return deleteUser(t);
	}

	public void deleteFiles() {
		classRoster.delete();
		classAssignments.delete();
		classAnnouncements.delete();
	}


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
			e.printStackTrace();
		}

	}


	public void deleteAssignment(Assignment a) {
		assignments.remove(a);
	}	
	
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
	
	
	public void deleteAssignment(int assignmentID) {
		int index = -1;
		for (int i = 0; i < assignments.size(); i++) {
			
		}
	}


	public void deleteAllAssignments() {
		if (assignments != null) {
			for (Assignment a : assignments) {
				a.deleteFiles();
			}
			assignments.clear();
		}
	}


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


	private void addWeights() {
		ArrayList<String> contents = FileWorker.readFile(classWeights);
		if (contents != null) {
			for (int i = 1; i < contents.size(); i++) {
				weightCategories.add(contents.get(i).substring(0, contents.get(i).indexOf(",")));
				weightPercents.add(Integer.parseInt(contents.get(i).substring(contents.get(i).indexOf(",") + 1)));
			}
		}
	}
	
	
	public String getIndividualAssignmentWeightCategory(int assignmentID) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				return a.getWeightCategory();
			}
		}
		return null;
	}
	
	
	public int getIndividualAssignmentPoints(int assignmentID) {
		for (Assignment a : assignments) {
			if (a.getAssignmentID() == assignmentID) {
				return a.getPoints();
			}
		}
		return -1;
	}


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
				}
			}
		}
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


	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}


	public ArrayList<String> getWeightCategories() {
		return weightCategories;
	}

	public ArrayList<Integer> getWeightPercents() {
		return weightPercents;
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
		if (this.gradingMethod != gradingMethod) {
			deleteAllAssignments();
		}
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
