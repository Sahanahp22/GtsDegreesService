package com.gts.degree.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gts.degree.entity.DegreeEntity;
import com.gts.degree.exception.ApplicationException;
import com.gts.degree.exception.DegreeNotFoundException;
import com.gts.degree.exception.DuplicationException;
import com.gts.degree.service.DegreeService;
import com.gts.degree.util.JsonResponseModel;

@RestController
@RequestMapping("/api/v1/degree")
@CrossOrigin(origins="*")
public class DegreeController {

	@Autowired 
	DegreeService degreeService;
	
	private synchronized int generateDegreeID() {
	  	  return degreeService.getDegreeCount()+1;
	
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public synchronized ResponseEntity<JsonResponseModel> createDegree(@RequestBody List<DegreeEntity> degreeEntities) {
		
		JsonResponseModel degrees=null;
		int nextDegreeID=generateDegreeID();
		List<DegreeEntity> degreeEntitiesWithId = new ArrayList<>();
		for(DegreeEntity degreeEntity: degreeEntities ) {
			
			String degreeName=degreeEntity.getGts_degree_name();
			if(degreeName == null || degreeName.isEmpty()) {
			    throw new ApplicationException("Degree name should not be empty");   
			}

			DegreeEntity degreeDetail = degreeService.getDegreeByName(degreeName);
			if(degreeDetail != null) {
			    throw new DuplicationException("Degree exist already");   
			}
			
			degreeEntity.setGts_degree_id(nextDegreeID);
			degreeEntitiesWithId.add(degreeEntity);
			nextDegreeID++;
		}
		
		degrees=degreeService.createDegree(degreeEntities); 
		return new ResponseEntity<>(degrees, HttpStatus.CREATED);
		
	}
	
	@GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<DegreeEntity>> getAllDegrees(){
   	     	 
   	 List<DegreeEntity> allDegrees = degreeService.getAllDegrees();
   	  	 
   	 if(allDegrees == null || allDegrees.isEmpty()) {
   		 throw new DegreeNotFoundException("Record not found");
	 }
   	 
   	 return new ResponseEntity<>(allDegrees,HttpStatus.OK);
    
	}
    	
	@GetMapping(path="/active")
	public ResponseEntity<List<DegreeEntity>> findActiveDegrees(){
		List<DegreeEntity> listOfActiveDegrees=degreeService.findActiveDegrees();
		if(listOfActiveDegrees == null || listOfActiveDegrees.isEmpty())
		{
			throw new DegreeNotFoundException("Active degrees not found ");
		}
		return new ResponseEntity<>(listOfActiveDegrees,HttpStatus.OK);
	}

	@GetMapping(path="/inactive")
	public ResponseEntity<List<DegreeEntity>> findInActiveDegrees(){
		List<DegreeEntity> listOfInactiveDegrees=degreeService.findInactiveDegrees();
		if(listOfInactiveDegrees == null || listOfInactiveDegrees.isEmpty())
		{
			throw new DegreeNotFoundException("Inactive degrees not found");
		}
		return new ResponseEntity<>(listOfInactiveDegrees,HttpStatus.OK);
	}	
	
	@GetMapping(path="/degree_id/{degree_id}" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DegreeEntity> getDegreebyId(@PathVariable long degree_id) {
		 
		if(degree_id <= 0) {
			throw new ApplicationException("Degree id is invalid");
		}
		
		DegreeEntity degreeEntity = degreeService.getDegreeById(degree_id);

		if(degreeEntity == null) {
			   throw new DegreeNotFoundException("Degree id not found");
		}
        
		return new ResponseEntity<>(degreeEntity, HttpStatus.OK);
	 }

	@GetMapping(path="/degree_name/{degree_name}" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<DegreeEntity> getDegreesByName(@PathVariable String degree_name) {
   	  
		if(degree_name == null || degree_name.trim().isEmpty()) {
			
			throw new ApplicationException("Degree name is invalid");
		}
		
		List<DegreeEntity>  degreeEntity  = degreeService.getDegreesByName(degree_name);

		if(degreeEntity.isEmpty()) {
    	   throw new DegreeNotFoundException("Degree name not found");
		}
   
		return degreeEntity;
	 }
    
    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public JsonResponseModel updateDegree (@RequestBody DegreeEntity degreeEntity) {
		  
    	String degreeName = degreeEntity.getGts_degree_name();
    	long degreeID = degreeEntity.getGts_degree_id();
    	
    	if(degreeName == null || degreeName.trim().isEmpty()) {
		    throw new ApplicationException("Degree name should not be empty");   
    	}
    	
    	if(degreeID <= 0) {
		    throw new ApplicationException("Degree id is invalid");   
    	}

    	DegreeEntity degreeDetail = degreeService.getDegreeById(degreeID);
    			 		 
		if(degreeDetail == null) {
			 throw new DegreeNotFoundException("Degree id not found");
	    }
		
		DegreeEntity degreeDetailName = degreeService.getDegreeByName(degreeName);
		if(degreeDetailName != null) {
		    throw new DuplicationException("Degree exist already");   
		}
	    
		return degreeService.updateDegree(degreeEntity);
	 }
    
    @PutMapping(path="/activate", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JsonResponseModel> activateDegreeStatus(@RequestBody DegreeEntity degreeEntity) {
		 
		long degreeID = degreeEntity.getGts_degree_id();	
	    if(degreeID <= 0) {
		    	 
	    	throw new ApplicationException("Degree id is invalid");
		}
	    	
	    DegreeEntity statusDetail  = degreeService.getDegreeById(degreeID); 	    	
	    if(statusDetail == null) {
		    	
	    	throw new DegreeNotFoundException("Degree id not found");
	       
		}
	    			     
		JsonResponseModel  updateStatus =  degreeService.activateDegreeStatus(degreeID);
		return new ResponseEntity<>(updateStatus, HttpStatus.OK);
	   
    }
   
    @PutMapping(path="/inactivate", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JsonResponseModel> inactivateDegreeStatus(@RequestBody DegreeEntity degreeEntity) {
		
    	long degreeID = degreeEntity.getGts_degree_id();	
	    if(degreeID <= 0) {
		    	 
	    	throw new ApplicationException("Degree id is invalid");
		}
	    	
	    DegreeEntity statusDetail  = degreeService.getDegreeById(degreeID); 
	    if(statusDetail == null) {	
	    	throw new DegreeNotFoundException("Degree id not found");
		}
	    	
		JsonResponseModel  updateStatus =  degreeService.inactivateDegreeStatus(degreeID);
		return new ResponseEntity<>(updateStatus, HttpStatus.OK);
	 
    }
        
    @DeleteMapping(path="/degree_id/{degree_id}" ,  produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseModel> deleteDegreeById(@PathVariable int degree_id) {
    	    
    	if(degree_id <= 0) {
		   	 
	    	throw new ApplicationException("Degree id is invalid");
		}
    	    
    	DegreeEntity degreeEntity = degreeService.getDegreeById(degree_id);
    	if(degreeEntity == null) {
    		throw new DegreeNotFoundException("Degree id not found");
 		}
 			
 		return new ResponseEntity<>(degreeService.deleteDegreeById(degree_id), HttpStatus.OK);
 			
     }
          
}

