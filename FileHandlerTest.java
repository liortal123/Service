package com.example.demo;

import java.io.PrintWriter;
import java.util.Arrays;

public class FileHandlerTest {

	  public static void main(String[] args) throws Exception {	
		  //this test use a different test data base file 
		  boolean answer;
		  String testFileName = "./testDataBase.txt";
		  
		  //using a different file for testing and emptying it
		  FileHandler.switchDataBaseFile(testFileName);
		  PrintWriter writer = new PrintWriter(testFileName);
		  writer.print("");
		  writer.close();
		  
  
		  //test number 0: checking that null array was not inserted to file
		  String[] vals = null;
		  answer = FileHandler.writeToFile(0, vals)==false;
		  check(answer,0.0);
		  answer =  FileHandler.readFromFile(0)==null;
		  check(answer,0.1);
		  
		  //adding a different array in id 0, making sure it was entered and not the null array
		  String[] vals1 = {"0"};
		  answer = FileHandler.writeToFile(0, vals1);
		  check(answer,0.2);
		  answer = FileHandler.readFromFile(0).equals(String.format(Arrays.toString(vals1)));
		  check(answer,0.3);
 
		  //test 1-11 adding id i and checking it was inserted correctly
		  String [] vals2 = {"a"};
		  for (int i=1;i<11;i++) {
			  vals2[0] = Integer.toString(i);
			  answer = FileHandler.writeToFile(i, vals2);
			  check(answer,i+0.0);
			  answer = FileHandler.readFromFile(i).equals(String.format(Arrays.toString(vals2)));
			  check(answer,i+0.1);	
		  }
		  
		  //teesting 1-11 trying to add something instead of 1-11, making sure it was not inserted
		  for (int i=1;i<11;i++) {
			  vals2[0] = "error";
			  answer = FileHandler.writeToFile(i, vals2)==false;
			  check(answer,i+0.2);
			  vals2[0] = Integer.toString(i);
			  answer = FileHandler.readFromFile(i).equals(String.format(Arrays.toString(vals2)));
			  check(answer,i+0.3);
		  }
		  
		  //test 12 trying to add a negative id and making sure it wasn't added
		  answer = FileHandler.writeToFile(-1, vals)==false;
		  check(answer,12.0);	
		  
		  System.out.println("success");
	  }
	  
	  //checking answer is true, else throwing an error 
	  private static void check(boolean answer, double i) throws Exception {
		  if (!answer) {
			  throw new Exception("problem with test number"+i);
			  
		  }
	  }
}
