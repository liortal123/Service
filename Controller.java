
  package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


  
  @RestController
  public class Controller {
	  
	  @PostMapping("/add_image")
	     ResponseEntity<String> add_image(@RequestBody String imageUrl, int imageID){
		  return Service.add_image(imageUrl, imageID);
	  }
	  
	  @GetMapping("/imageInfo")
	     String imageInfo(@RequestParam(value = "imageID") int imageID) {
		  return Service.imageInfo(imageID);
	     }

	    
     
  }
