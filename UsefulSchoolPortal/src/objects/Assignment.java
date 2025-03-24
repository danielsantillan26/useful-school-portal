package objects;

import java.util.ArrayList;

public class Assignment {
	
	private String name;
	private int points;
	private String weightCategory;
	private ArrayList<Double> grades;
	
	public Assignment(String name, int points) {
		this.name = name;
		this.points = points;
		grades = new ArrayList<Double>();
	}
	
	
	public Assignment(String name, String weightCategory) {
		this.name = name;
		this.weightCategory = weightCategory;
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


	@Override
	public String toString() {
		return "Assignment [name=" + name + ", points=" + points + ", weightCategory=" + weightCategory + ", grades="
				+ grades + "]";
	}
	
	

}
