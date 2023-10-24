package com.interview.parshvaproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.parshvaproject.entity.ParshvaProjectEntity;
import com.interview.parshvaproject.service.ParshvaService;

@RequestMapping("/parshva")
@RestController
@CrossOrigin
public class ParshvaController {
	@Autowired
	private ParshvaService parshvaService;

	    @GetMapping("/parshvaProject/primaryDetail")
	public ResponseEntity<List<Map<String,?>>> getPonumberSupplier() {

		List<Map<String,?>> basicDetails = parshvaService.getDetails();
		return ResponseEntity.status(200).body(basicDetails);
	}
	

	      @PostMapping("/parshvaProject/addingDetails")
	public ResponseEntity<String> addDetails(@RequestBody ParshvaProjectEntity parshvaProjectEntity) {
	    	  ParshvaProjectEntity parshvaProjectEntityupdatedId=new ParshvaProjectEntity();
//                         parshvaProjectEntity.setParshvaProjectId(parshvaProjectEntityupdatedId.getParshvaProjectId());
	    	  System.out.println(parshvaProjectEntityupdatedId.getParshvaProjectId());
//                         System.out.println(parshvaProjectEntity.getName());
		String detailsadded = parshvaService.addDetailsService(parshvaProjectEntity);
		return ResponseEntity.status(200).body(detailsadded);
	}
	
	      @GetMapping("/parshvaProject/allDetail")
	  	public ResponseEntity<List<ParshvaProjectEntity>> getAllDetail() {

	    	List<ParshvaProjectEntity> allDetails = parshvaService.getAllDetail();
	  		return ResponseEntity.status(200).body(allDetails);
	  	}      
	      

}
