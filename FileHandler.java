package com.example.demo;


import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;   // Import the FileWriter class

public class FileHandler {
	
	static protected String DataBaseFile = "./DataBaseFile.txt";
	
	public static void switchDataBaseFile(String fileName) {
		DataBaseFile = fileName;
	}
	

	public static boolean writeToFile (int id, String[] content) throws Exception{
		//getting image id, and content of the image. adding the info to inner File
		//if the id was already there returning 0, otherwise 1
		
		
		//checking if id was already there, if so returning false because information not updated
		if (readFromFile(id)!=null || content==null || id<0){
			return false;
		}
		
		//writing id and string content to file
		BufferedWriter file = new BufferedWriter(new FileWriter(DataBaseFile, true));
		file.write(Integer.toString(id)+": ");
		for (int i=0; i<content.length; i++) {
			if (i!=content.length-1) {
				file.write(content[i] +", ");
			}
			else {file.write(content[i] +"\n");}
		}
		
		file.close();
		return true;
	}
	

	
	public static String readFromFile(int ID) throws Exception {
		if (ID<0) return null;
		
		String line;
		String [] vals;
		
		//creating a scanner that reads only integers
		Scanner scanner = new Scanner(new File(DataBaseFile));
		scanner.useDelimiter("[^0-9]+");
		
		//checking the file content to scan for id
		while(scanner.hasNext()) {
			int currID =  scanner.nextInt();
			if (currID == ID) {
				line = scanner.nextLine();
				vals = line.substring(2).split("\\s*,\\s*"); //taking the line without : , and splitting to array of Strings
				scanner.close();
				return String.format(Arrays.toString(vals));
			}
		}
		
		//if id was not found returning null
		scanner.close();
		return null;
		
	}
	
}
