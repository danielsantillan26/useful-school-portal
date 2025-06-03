package files;

/**
 * The Constants class contains several values that are used across the program
 * to differentiate users and assignments. This helps with data management.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class Constants {

	/** A value that represents an administrator */
	public static final int ADMINISTRATOR_VALUE = 190;
	/** A value that represents a teacher */
	public static final int TEACHER_VALUE = 191;
	/** A value that represents a student */
	public static final int STUDENT_VALUE = 192;

	/** A value that represents an assignment with weight grading */
	public static final int GRADE_WEIGHTS = 290;
	/** A value that represents an assignment with basic percents grading */
	public static final int GRADE_PERCENTS = 291;
	/** A value that represents an assignment with points grading */
	public static final int GRADE_POINTS = 292;

	/** A delimiter used to determine a grade that has not been entered */
	public static final char DELIMITER_NULL_GRADE = '消';
	
	
	/**
	 * Default constructor
	 */
	public Constants() {
		
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "Constants []";
	}

}
