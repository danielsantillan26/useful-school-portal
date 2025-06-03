package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import objects.School;

/**
 * The FileWorker class is the class that maintains the list of schools, the
 * first file read by the program. The FileWorker class also contains methods
 * used to modify data in files.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class FileWorker {

	/** The school list file */
	private static File schoolList;
	
	
	/**
	 * Default constructor
	 */
	public FileWorker() {
		
	}

	
	/**
	 * The makeFile method creates the schoolList file. If the file does not
	 * exist (i.e., first time user is running the program), the file is set up. 
	 */
	public static void makeFile() {
		schoolList = new File("SchoolList.csv");

		try {
			if (!schoolList.exists()) {
				schoolList.createNewFile();
				FileWriter writer = new FileWriter(schoolList, false);
				BufferedWriter bWriter = new BufferedWriter(writer);
				bWriter.write("School Name,School ID,Blocks");
				bWriter.close();
			}

		} catch (Exception e) {

		}
	}


	/**
	 * The writeSchool method writes in a school to the schoolList file. The
	 * name and the school's ID is written in to the file.
	 * 
	 * @param s		The school to write in
	 * @return		Whether everything worked
	 */
	public static boolean writeSchool(School s){
		try {
			ArrayList<String> contents = readFile(schoolList);

			FileWriter writer = new FileWriter(schoolList, false);
			BufferedWriter bWriter = new BufferedWriter(writer);

			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}

			bWriter.write(s.getName() + "," + s.getSchoolID() + ",6");
			bWriter.close();

			return true;

		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * The readFile method reads a file and returns the contents of the file as
	 * an ArrayList of type String.
	 * 
	 * @param f		The file to be read
	 * @return		The contents of the file
	 */
	public static ArrayList<String> readFile(File f) {
		ArrayList<String> contents = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			while ((line = reader.readLine()) != null) {
				contents.add(line);
			}
			reader.close();
		} catch (Exception e) {
			return contents;
		}

		return contents;
	}


	/**
	 * The readSchoolFile method reads the schoolList file in the class, using
	 * the readFile method from this class.
	 * 
	 * @return		The contents of the schoolList file
	 */
	public static ArrayList<String> readSchoolFile() {
		return readFile(schoolList);
	}


	/**
	 * The removeLine method removes a line from a file by searching for a
	 * particular ID from the file and removing the line with that ID. This is
	 * helpful for removing users.
	 * 
	 * @param f		The file to search
	 * @param id	The ID with the line to delete
	 * @return		Whether the removal was successful
	 */
	public static boolean removeLine(File f, int id) {
		ArrayList<String> contents = readFile(f);

		int forgetIndex = -1;
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).contains(Integer.toString(id))) {
				forgetIndex = i;
			}
		}	

		return removeLineByIndex(f, forgetIndex);
	}


	/**
	 * The removeLineByIndex method removes a line from a file through the
	 * index. The contents at that index are removed from the file.
	 * 
	 * @param f					The file
	 * @param forgetIndex		The index to delete
	 * @return					Whether the method ran smoothly
	 */
	public static boolean removeLineByIndex(File f, int forgetIndex) {
		ArrayList<String> contents = readFile(f);

		try {
			FileWriter writer = new FileWriter(f, false);
			BufferedWriter bWriter = new BufferedWriter(writer);

			for (int i = 0; i < contents.size(); i++) {
				if (i != forgetIndex) {
					bWriter.write(contents.get(i) + "\n");
				}
			}

			bWriter.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "FileWorker []";
	}
}
