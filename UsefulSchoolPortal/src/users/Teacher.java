package users;

public class Teacher extends User {

	public Teacher(String username, String firstName, String lastName,
			String password, int schoolID) {
		this(username, firstName, lastName, password, (int)(Math.random()*1000000), schoolID);
	}
	
	public Teacher(String username, String firstName, String lastName, 
			String password, int id, int schoolID) {
		super(username, firstName, lastName, password, id, schoolID);
	}
	
	
	@Override
	public boolean isTeacher() {
		return true;
	}
	
}
