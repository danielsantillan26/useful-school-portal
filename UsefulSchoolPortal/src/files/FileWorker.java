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
			ArrayList<String> contents = readFile();
			System.out.println(contents.size());

			FileWriter writer = new FileWriter(schoolList, false);
			BufferedWriter bWriter = new BufferedWriter(writer);

			for (int i = 0; i < contents.size(); i++) {
				bWriter.write(contents.get(i) + "\n");
			}

			bWriter.write(s.getName() + "," + s.getSchoolID());
			bWriter.close();
			
			makeSchoolFiles(s);
			return true;

		} catch (Exception e) {
			return false;
		}
	}


	private static ArrayList<String> readFile() {
		ArrayList<String> contents = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(schoolList));
			String line;
			while ((line = reader.readLine()) != null) {
				contents.add(line);
			}
			reader.close();
		} catch (Exception e) {

		}

		return contents;
	}


	private static void makeSchoolFiles(School s) {
		String schoolUsersFilename = "SchoolUsers_" + s.getSchoolID() + ".csv";
		String schoolClassesFilename = "SchoolClasses_" + s.getSchoolID() + ".csv";

		File schoolUsers = new File(schoolUsersFilename);
		File schoolClasses = new File(schoolClassesFilename);

		try {
			schoolUsers.createNewFile();
			schoolClasses.createNewFile();
		} catch (Exception e) {

		}
	}

}
