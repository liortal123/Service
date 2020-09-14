package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Service {
	
     public static ResponseEntity<String> add_image(String imageUrl, int imageID){
    	 
    	 String url;
    	 
    	 if (imageID<0) 
	   		  return new ResponseEntity<>("ID is not legal",HttpStatus.BAD_REQUEST);
    	 
    	 try {
    		 //decoding Url
			url = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e1) {
			return new ResponseEntity<>("Problem with decoding Url",HttpStatus.BAD_REQUEST);
		}
   
	  
	   	  //processing the image and returning a String array with String elements
	   	  String [] st = ImageProc.getImageInfo(url);
	   	  if (st==null) 
	   		  return new ResponseEntity<>("Url syntax not valid",HttpStatus.BAD_REQUEST);
		
	   	  
	   	  
	   	  //writing the String elements to Data Base File
	   	  try {
	   		  //if true image was added to data base successfully
	   		  if (FileHandler.writeToFile(imageID,st)) {
	   			  return new ResponseEntity<>("Image was added to data base successfully",HttpStatus.OK);
	   		  }
	   		  
	   		  //id was already in system
		  		return new ResponseEntity<>("ID was already in system",HttpStatus.BAD_REQUEST);
	   	  }
	   	  catch(Exception e){
	   		  //problem with opening data base
		  		return new ResponseEntity<>("Problem with data base file",HttpStatus.BAD_REQUEST);
	   	  }
	   	 
	}
	     
	     
	//this function returns the image String array information based on image Id, 
     //or null if the image is not in the data base file. 
	     public static String imageInfo(int imageID) {	  
	    	 if (imageID<0) {
	    		 return "ID not valid (negative)";
	    	 }
	    	 try {
	    		 String answer = FileHandler.readFromFile(imageID);
	    		 if (answer==null) {
	    			 return "ID is not in data base";
	    		 }
	    		 return answer;
		   	 }
	    	 catch (Exception e) {
	    		 return "Problem with file";
	    	 }
	     }
    

   
}
