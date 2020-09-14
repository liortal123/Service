package com.example.demo;



import java.util.Random;


public class ImageProc {
	private static String[] randomStrings = {"dog", "cat", "goat", "duck", "girl", "flower",
			"boy", "book","table", "shelf", "telephone", "television", 
			"shirt", "pants", "picture", "basket", "door", "window", 
			"hair", "tablet", "pencil", "bottle", "pen", "toy", "chair", 
			"clock", "bed", "pillow", "dress", "cable", "snake", "desk",
			"bird", "apple", "orange", "banana", "tomato", "cucumber", "coat"};
	

	
	static String[] getImageInfo(String imageUrl){
		//this function get imageUrl (valid) and returns String array with random information
		
		if (!checkURL(imageUrl)){
			return null;
		}
		
		//choosing the number of values a number between 3 to 8
		Random r = new Random(); 
		int numberOfStrings = r.nextInt(6)+3;
		
		//creating vals array with 3 to 8 elements
		String[] vals = new String[numberOfStrings];
		
		//making sure there wouldn't be duplicates
		int[] randomNumarr = new int[randomStrings.length];
		for(int i=0;i<randomStrings.length;i++) {
			randomNumarr[i] = i;
		}
		 
		//filling vals with random Strings from the randomStrings arr
		 for (int i=0;i<numberOfStrings;i++) {
			 int rand = r.nextInt(randomStrings.length-i);
			vals[i] = randomStrings[randomNumarr[rand]];
			randomNumarr[rand] = randomNumarr[randomStrings.length-i-1];
		 }
		
		
		return vals;
		
	}
	
    //checking the Url is valid syntax 
    private static boolean checkURL(String imageUrl) {
    	if (imageUrl.startsWith("imageUrl=http://")|| imageUrl.startsWith("imageUrl=https://")|| 
    			imageUrl.startsWith("imageUrl=ftp://")) {
    		return true;}
    
    	return false;
    }
}
