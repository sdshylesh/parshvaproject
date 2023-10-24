package com.interview.parshvaproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ParshvaProjectEntity {
	@Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int parshvaProjectId;
	
	String name;
	String startTime;
	String endTime;
	String noOfHoursWorked;
	String ratePerHour;
   String supplierName;
   String purchaseOrder;
   String selectedDescription;
      
}
