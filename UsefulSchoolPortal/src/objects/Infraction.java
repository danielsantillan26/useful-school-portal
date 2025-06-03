package objects;

/**
 * The Infraction class is a representation of an object in the school system.
 * It includes essential fields for an infraction, such as type of infraction
 * and reason.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class Infraction {

	/** Infraction's name */
	private String name;
	/** Infraction's ID */
	private int id;
	/** Infraction's student ID */
	private int studentID;
	/** Infraction's school ID */
	private int schoolID;
	/** Infraction's staff ID */
	private int staffID;
	/** Infraction's reason */
	private String reason;


	/**
	 * This constructor builds a new infraction, as the ID is randomized. All
	 * other fields are set by the program parameters from the user.
	 * 
	 * @param name			Given name
	 * @param studentID		Given student ID
	 * @param schoolID		Given school ID
	 * @param staffID		Given staff ID
	 * @param reason		Given reason
	 */
	public Infraction(String name, int studentID, int schoolID, int staffID, String reason) {
		this(name, (int)(Math.random()*10000), studentID, schoolID, staffID, reason);
	}


	/**
	 * This constructor builds an existing infraction, as the ID along with the
	 * other fields are set by the program parameters from the user. This is for
	 * an assignment that uses a points system.
	 * 
	 * @param name			Given name
	 * @param id			Given ID
	 * @param studentID		Given student ID
	 * @param schoolID		Given school ID
	 * @param staffID		Given staff ID
	 * @param reason		Given reason
	 */
	public Infraction(String name, int id, int studentID, int schoolID, int staffID, String reason) {
		this.name = name;
		this.id = id;
		this.schoolID = schoolID;
		this.studentID = studentID;
		this.staffID = staffID;
		this.reason = reason;
	}


	/**
	 * Returns the name.
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Returns the ID.
	 * 
	 * @return	The infraction ID
	 */
	public int getId() {
		return id;
	}


	/**
	 * Returns the student ID.
	 * 
	 * @return	The student ID
	 */
	public int getStudentID() {
		return studentID;
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
	 * Returns the staff ID.
	 * 
	 * @return	The staff ID
	 */
	public int getStaffID() {
		return staffID;
	}


	/**
	 * Returns the reason.
	 * 
	 * @return	The reason
	 */
	public String getReason() {
		return reason;
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
	 * Sets the ID.
	 * 
	 * @param id	Given ID
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Sets the student ID.
	 * 
	 * @param studentID		Given student ID
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}


	/**
	 * Sets the school ID
	 * 
	 * @param schoolID		Given school ID
	 */
	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}


	/**
	 * Sets the staff ID.
	 * 
	 * @param staffID	Given staff ID
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}


	/**
	 * Sets the reason.
	 * 
	 * @param reason	Given reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "Infraction [name=" + name + ", id=" + id + ", studentID=" + studentID + ", schoolID=" + schoolID
				+ ", staffID=" + staffID + ", reason=" + reason + "]";
	}
}
