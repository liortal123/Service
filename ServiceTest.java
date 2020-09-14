package com.example.demo;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServiceTest {
	 public static void main(String[] args) throws Exception {
		 boolean answer;
		 String url = "imageUrl=http://check";
		  String testFileName = "./testDataBase.txt";
		 
		  //using a different file for testing and emptying it
		  FileHandler.switchDataBaseFile(testFileName);
		  PrintWriter writer = new PrintWriter(testFileName);
		  writer.print("");
		  writer.close();
		 
		  
		 //test 0-9 adding id i making sure it was added
		  // trying to add againg and making sure it was not added
		  for(int i=0;i<10;i++) {
			  answer  = testadd_image(url,i);
			  check(answer,i+0.0);
			  answer = testimageInfo(i)!=null;
			  check(answer,i+0.1);
			  answer = testadd_image(url,i) == false;
			  check(answer,i+0.2);
		  }
		  
		 //test 10 making sure that when the url is not valid the id was not added
		 answer = testadd_image("imageUrl=check", 10)==false;
		 check(answer,10.0);
		 answer = testimageInfo(10)==null;
		 check(answer,10.1);
		 
		 //test 11- making sure that when the url is not valid the id was not added
		 answer = testadd_image("",11)==false;
		 check(answer,11.0);
		 answer = testimageInfo(11)==null;
		 check(answer,11.1);
		 
		 //test 12- making sure when the id is negative the id was not added
		 answer = testadd_image(url,-1)==false;
		 check(answer,12.0);
		 answer = testimageInfo(-1)==null;
		 check(answer,12.1);

		 System.out.println("success");
	 }
	 
	 //testing add image
	 static boolean testadd_image(String imageUrl, int imageID) throws UnsupportedEncodingException {	 
		 String Url = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString());
		 ResponseEntity<String> response = Service.add_image(Url, imageID);
		 return response.getStatusCode().equals(HttpStatus.OK);
	 }
	 
	 //testing imageInfo
	 static String testimageInfo(int imageID) {
//		System.out.println(Arrays.toString(Service.imageInfo(imageID))); 
		 String response = Service.imageInfo(imageID);
		 if (response.equals("ID is not in data base"))
			 return null;
		 if (response.equals("ID not valid (negative)"))
			 return null;
		 return response;
		 
	 }
	 
	 //a function that throws exception when the test have not passed
	  private static void check(boolean answer, double i) throws Exception {
		  if (!answer) {
			  throw new Exception("problem with test number"+i);
			  
		  }
	  }

}
