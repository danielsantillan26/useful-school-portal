package objects;

/**
 * The Teacher class is a representation of a school teacher. It
 * stems from the User class with some modifications to ensure it is an
 * teacher in the system.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class Teacher extends User {

	/**
	 * This constructor builds a new teacher, as the ID is randomized. All
	 * other fields are set by the program parameters from the user.
	 * 
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param schoolID		Given school ID
	 */
	public Teacher(String username, String firstName, String lastName,
			String password, int schoolID) {
		this(username, firstName, lastName, password, (int)(Math.random()*1000000), schoolID);
	}


	/**
	 * This constructor builds an existing teacher, as the ID along with 
	 * the other fields are set by the program parameters from the user.
	 * 	
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param id			Given ID
	 * @param schoolID		Given school ID
	 */
	public Teacher(String username, String firstName, String lastName, 
			String password, int id, int schoolID) {
		super(username, firstName, lastName, password, id, schoolID);
	}


	/**
	 * Determines whether a user is a teacher.
	 * 
	 * @return	Whether the user is a teacher.
	 */
	@Override
	public boolean isTeacher() {
		return true;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "Teacher []";
	}



}
