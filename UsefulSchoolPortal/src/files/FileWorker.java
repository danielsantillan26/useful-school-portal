package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import users.School;


public class FileWorker {
	
	private static BufferedWriter writer;
	

	public static boolean makeFile() {
		try {
			writer = new BufferedWriter(new FileWriter("SchoolList.txt"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	public static boolean writeSchool(School s){
		try {
			writer.write(s.getName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static void closeWriter() {
		try {
			writer.close();
		} catch (IOException e) {

		}
	}
	
}
