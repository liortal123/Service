package com.example.demo;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageProcTest {
	
	  public static void main(String[] args) throws Exception {
		  //testing ImageProc with different Urls
		  //because the function creats a random array of String I haven't tested the array itself
		  
		  check(testGetImageInfo("imageUrl=http://check"),0); 
		  check(testGetImageInfo("imageUrl=check")==false,1);
		  check(testGetImageInfo("imageUrl=https://check"),2); 
		  check(testGetImageInfo("imageUrl=ftp://://check"),3); 
		  check(testGetImageInfo("imageUrl=ftp://://"),4); 
		  check(testGetImageInfo("")==false,1);
		  System.out.println("success");
	  }
	  
	  private static void check(boolean answer, int i) throws Exception {
		  if (!answer) {
			  throw new Exception("problem with test number"+i);
			  
		  }
	  }
	  
	  
	  static boolean testGetImageInfo(String imageUrl) {
		  
		  String[] vals = ImageProc.getImageInfo(imageUrl);
		  if (vals== null) {
			  return false;
		  }
		  
		  return true;
/*		  for (int i=0; i<vals.length; i++) {
			  if (i!=vals.length-1) {
				  System.out.print(vals[i] +",");
			  }
			  else System.out.print(vals[i]);
		  }*/
	  }


}
