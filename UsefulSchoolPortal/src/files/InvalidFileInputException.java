package files;

/**
 * The InvalidUserInputException is an exception that indicates if a user on
 * file does not have all of the necessary information provided.
 * 
 * @author Daniel Santillan
 * 
 */
public class InvalidFileInputException extends Exception {

	/** Version */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * This constructor establishes an InvalidUserInputException using a message
	 * with the name of the exception.
	 */
	public InvalidFileInputException() {
		this("Invalid File Input Exception");
	}
	
	
	/**
	 * This constructor establishes an InvalidUserInputException using a message
	 * provided by input.
	 * 
	 * @param message		A message that can be used when an error is caught
	 */
	public InvalidFileInputException(String message) {
		super(message);
	}
	
}
