package users;

public class Administrator extends User {

	private int schoolID;
	
	public Administrator(String username, String firstName, String lastName,
			String password, int schoolID) {
		this(username, firstName, lastName, password, (int)(Math.random()*1000000), schoolID);
	}
	
	public Administrator(String username, String firstName, String lastName, 
			String password, int id, int schoolID) {
		super(username, firstName, lastName, password, id);
		this.schoolID = schoolID;	
	}
}
