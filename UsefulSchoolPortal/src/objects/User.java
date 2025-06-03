package objects;

/**
 * The User class is a representation of a user in the system. It includes
 * essential fields for a user and also has methods determining its role. This
 * class is a superclass for the other classes representing users.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class User {

	/** User's username */
	private String username;
	/** User's first name */
	private String firstName;
	/** User's last name */
	private String lastName;
	/** User's password */
	private String password;
	/** User's id */
	private int id;
	/** User's school ID */
	private int schoolID;


	/**
	 * This constructor builds a new user, as the ID is randomized. All other
	 * fields are set by the program parameters from the user.
	 * 
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param schoolID		Given school ID
	 */
	public User(String username, String firstName, String lastName, String password, int schoolID) {
		this(username, firstName, lastName, password, (int)Math.random()*1000000, schoolID);
	}


	/**
	 * This constructor builds an existing user, as the ID along with the other
	 * fields are set by the program parameters from the user.
	 * 	
	 * @param username		Given username
	 * @param firstName		Given first name
	 * @param lastName		Given last name
	 * @param password		Given password
	 * @param id			Given ID
	 * @param schoolID		Given school ID
	 */
	public User(String username, String firstName, String lastName, String password, int id, int schoolID) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.id = id;
		this.schoolID = schoolID;
	}


	/**
	 * Determines whether a user is an administrator.
	 * 
	 * @return	Whether the user is an administrator
	 */
	public boolean isAdministrator() {
		return false;
	}


	/**
	 * Determines whether a user is a teacher.
	 * 
	 * @return	Whether the user is a teacher.
	 */
	public boolean isTeacher() {
		return false;
	}


	/**
	 * Determines whether a user is a student.
	 * 
	 * @return	Whether the user is a student.
	 */
	public boolean isStudent() {
		return false;
	}


	/**
	 * Returns the username.
	 * 
	 * @return	User's username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Returns the first name.
	 * 
	 * @return	User's first name
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * Returns the last name.
	 * 
	 * @return	User's last name
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Returns the password.
	 * 
	 * @return	User's password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Returns the ID.
	 * 
	 * @return User's id
	 */
	public int getID() {
		return id;
	}


	/**
	 * Returns the school ID.
	 * 
	 * @return User's school ID
	 */
	public int getSchoolID() {
		return schoolID;
	}


	/**
	 * Sets the username.
	 * 
	 * @param username	Given username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * Sets the first name.
	 * 
	 * @param firstName	Given first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * Sets the last name.
	 * 
	 * @param lastName Given last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * Sets the password.
	 * 
	 * @param password	Given password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", id=" + id + ", schoolID=" + schoolID + "]";
	}

}
