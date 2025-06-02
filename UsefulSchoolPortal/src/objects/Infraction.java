package objects;

public class Infraction {
	
	private String name;
	private int id;
	private int studentID;
	private int schoolID;
	private int staffID;
	private String reason;
	
	public Infraction(String name, int studentID, int schoolID, int staffID, String reason) {
		this(name, (int)(Math.random()*10000), studentID, schoolID, staffID, reason);
	}
	
	
	public Infraction(String name, int id, int studentID, int schoolID, int staffID, String reason) {
		this.name = name;
		this.id = id;
		this.schoolID = schoolID;
		this.studentID = studentID;
		this.staffID = staffID;
		this.reason = reason;
	}


	public String getName() {
		return name;
	}


	public int getId() {
		return id;
	}


	public int getStudentID() {
		return studentID;
	}


	public int getSchoolID() {
		return schoolID;
	}


	public int getStaffID() {
		return staffID;
	}


	public String getReason() {
		return reason;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}


	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}


	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	

}
