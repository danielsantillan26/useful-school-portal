package calculations;

import java.util.ArrayList;

public class GradeCalculations {

	public static double calculatePercentGrade(double earnedPercents, int totalAssignments) {
		return (earnedPercents/(totalAssignments * 100))*100;
	}
	
	
	public static double calculatePointsGrade(double earnedPoints, int totalPoints) {
		return (earnedPoints/totalPoints)*100;
	}
	
	
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
	
}
