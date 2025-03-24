package objects;

import java.util.ArrayList;

public class SchoolClass {

	private String name;
	private int block;
	private int teacherID;
	private ArrayList<Integer> studentIDs;
	private int gradingMethod;
	private ArrayList<Assignment> assignments;
	private ArrayList<String> weightCategories;

	public SchoolClass(String name, int block, int gradingMethod) {

	}


	public SchoolClass(String name, int block, int teacherID, ArrayList<Integer>
	studentIDs, int gradingMethod, ArrayList<Assignment> assignments, ArrayList<String>
	weightCategories) {
		this.name = name;
		this.block = block;
		this.teacherID = teacherID;
		this.studentIDs = studentIDs;
		this.gradingMethod = gradingMethod;
		this.assignments = assignments;
		this.weightCategories = weightCategories;
	}


	@Override
	public String toString() {
		return "SchoolClass [name=" + name + ", block=" + block + ", teacherID=" + teacherID + ", studentIDs="
				+ studentIDs + ", gradingMethod=" + gradingMethod + ", assignments=" + assignments
				+ ", weightCategories=" + weightCategories + "]";
	}
	
	

}
