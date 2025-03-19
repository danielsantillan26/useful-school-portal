package users;

public class User {

	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private int id;
	private int schoolID;
	
	
	public User(String username, String firstName, String lastName, String password, int schoolID) {
		this(username, firstName, lastName, password, (int)Math.random()*1000000, schoolID);
	}
	
	
	public User(String username, String firstName, String lastName, String password, int id, int schoolID) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.id = id;
		this.schoolID = schoolID;
	}
	
	
	public boolean isAdministrator() {
		return false;
	}
	
	public boolean isTeacher() {
		return false;
	}
	
	public boolean isStudent() {
		return false;
	}


	public String getUsername() {
		return username;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getPassword() {
		return password;
	}


	public int getId() {
		return id;
	}
	
	
	public int getSchoolID() {
		return schoolID;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
