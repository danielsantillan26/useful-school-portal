package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import users.School;


public class FileWorker {


	public static File schoolList;

	public static void makeFile() {
		schoolList = new File("SchoolList.csv");

		try {
			if (!schoolList.exists()) {
				schoolList.createNewFile();
				FileWriter writer = new FileWriter(schoolList, false);
				BufferedWriter bWriter = new BufferedWriter(writer);
				bWriter.write("School Name,School ID");
				bWriter.close();
			}

		} catch (Exception e) {

		}
	}



	public static boolean writeSchool(School s){
		try {
			ArrayList<String> contents = readFile(schoolList);

			FileWriter writer = new FileWriter(schoolList, false);
			BufferedWriter bWriter = new BufferedWriter(writer);

			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}

			bWriter.write(s.getName() + "," + s.getSchoolID());
			bWriter.close();
			
			return true;

		} catch (Exception e) {
			return false;
		}
	}


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

		}

		return contents;
	}
	
	
	public static ArrayList<String> readSchoolFile() {
			return readFile(schoolList);
	}
	
	
	public static boolean removeLine(File f, int id) {
		ArrayList<String> contents = readFile(f);
	
		int forgetIndex = -1;
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).contains(Integer.toString(id))) {
				forgetIndex = i;
			}
		}	
		
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

}
