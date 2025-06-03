package calculations;

import java.util.ArrayList;

/**
 * The GradeCalculations class contains methods that help the program
 * determine the average grade for students based on the grading system a
 * particular course uses.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class GradeCalculations {
	
	/**
	 * Default constructor
	 */
	public GradeCalculations() {
		
	}

	
	/**
	 * The calculatePercentGrade method takes in the sum of the percent values
	 * earned in every assignment of a class with a basic percents grading
	 * method and divides it by the total number of assignments to get the
	 * average grade.
	 * 
	 * @param earnedPercents		the sum of all percent values earned from each assignment
	 * @param totalAssignments		the number of assignments	
	 * @return						the average grade
	 */
	public static double calculatePercentGrade(double earnedPercents, int totalAssignments) {
		return Math.round((((earnedPercents/(totalAssignments * 100))*100)) * 100 / 100);
	}


	/**
	 * The calculatePointsGrade method takes in the sum of the points earned
	 * from every assignment in a class with a points grading method and divides
	 * it by the total number of assignments to get the average grade.
	 * 
	 * @param earnedPoints			the sum of all points earned from each assignment
	 * @param totalPoints			the number of assignments
	 * @return						the average grade
	 */
	public static double calculatePointsGrade(double earnedPoints, int totalPoints) {
		return Math.round((((earnedPoints/totalPoints)*100) * 100) / 100);
	}


	/**
	 * The calculateWeightGrade method takes in a list of grades of assignments
	 * from every assignment category and uses the weight percents to determine
	 * the average grade. This is used for classes with a weights grading method. 
	 * 
	 * @param grades				list of the total grades for each category
	 * @param weightPercents		list of the percent weights for each category
	 * @return						the average grade
	 */
	public static double calculateWeightGrade(ArrayList<Double> grades, ArrayList<Integer> weightPercents) {
		if (grades.size() != weightPercents.size()) {
			return -1;
		} else {
			double total = 0.0;
			for (int i = 0; i < grades.size(); i++) {
				total += (grades.get(i) * ((double)weightPercents.get(i)/100));
			}
			return Math.round(total * 100.0) / 100.0;
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "GradeCalculations []";
	}

}
