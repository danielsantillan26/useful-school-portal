package objects;

/**
 * The Student Class is a representation of a school student. It stems from the
 * User class with some modifications to ensure it is a student in the system.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class Student extends User {

	/**
	 * This constructor builds a new student, as the ID is randomized. All
	 * other fields are set by the program parameters from the user.
	 * 
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param schoolID		Given school ID
	 */
	public Student(String username, String firstName, String lastName,
			String password, int schoolID) {
		this(username, firstName, lastName, password, (int)(Math.random()*1000000), schoolID);
	}


	/**
	 * This constructor builds an existing student, as the ID along with 
	 * the other fields are set by the program parameters from the user.
	 * 	
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param id			Given ID
	 * @param schoolID		Given school ID
	 */
	public Student(String username, String firstName, String lastName, 
			String password, int id, int schoolID) {
		super(username, firstName, lastName, password, id, schoolID);
	}


	/**
	 * Determines whether a user is a student.
	 * 
	 * @return	Whether the user is a student.
	 */
	@Override
	public boolean isStudent() {
		return true;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "Student []";
	}



}
