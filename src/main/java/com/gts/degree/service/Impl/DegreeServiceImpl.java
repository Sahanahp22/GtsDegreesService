package com.gts.degree.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.degree.entity.DegreeEntity;
import com.gts.degree.repository.DegreeRepository;
import com.gts.degree.service.DegreeService;
import com.gts.degree.util.JsonResponseModel;

@Service
public class DegreeServiceImpl implements DegreeService {
	@Autowired
	DegreeRepository degreeRepo;
	
	@Override
	public int getDegreeCount() {
		
		Integer count = degreeRepo.countDegrees();
		int numberOfDegrees=0;
		if(count != null) {
			numberOfDegrees=count;
		}
		return numberOfDegrees;
	}
	
	@Override
	public JsonResponseModel createDegree(List<DegreeEntity> degreeEntities) {
		
		JsonResponseModel jsonResponseModel= new JsonResponseModel();

		try {

			degreeRepo.saveAll(degreeEntities);
      	    jsonResponseModel.setSuccess("True");
      	    jsonResponseModel.setMessage("GTS degrees added successfully");
      	    jsonResponseModel.setStatus_code("201");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
      	    jsonResponseModel.setMessage("GTS degrees failed to add");
      	    jsonResponseModel.setStatus_code("403");
      	    
      	    System.out.println("Exception: "+e);
      	    System.out.println("Message : " + e.getMessage());
		}
		return jsonResponseModel;	

	}

	@Override
	public List<DegreeEntity> getAllDegrees() {
		List<DegreeEntity> gtsDegrees = new ArrayList<>();
		degreeRepo.findAll().forEach(gtsDegrees::add);
		return gtsDegrees;
	}
	
	@Override
	public List<DegreeEntity> findActiveDegrees() {
		return degreeRepo.findActiveDegrees();
	}

	@Override
	public List<DegreeEntity> findInactiveDegrees() {
		return degreeRepo.findInActiveDegrees();
	}

	@Override
	public DegreeEntity getDegreeById(long degree_id) {
		return degreeRepo.findById(degree_id);
	}
	
	@Override
	public DegreeEntity getDegreeByName(String degreeName) {
		return degreeRepo.findDegreeByName(degreeName);
	}

	@Override
	public List<DegreeEntity> getDegreesByName(String degree_name) {
		return degreeRepo.findListOfDegreesByName(degree_name);
	}

	@Override
	public JsonResponseModel updateDegree(DegreeEntity degreeEntity) {

		JsonResponseModel jsonResponseModel= new JsonResponseModel();

		try {

			degreeRepo.save(degreeEntity);
      	    jsonResponseModel.setSuccess("True");
      	    jsonResponseModel.setMessage("GTS Degree is updated successfully");
      	    jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
      	    jsonResponseModel.setMessage("Degree failed to update");
      	    jsonResponseModel.setStatus_code("403");
      	    
      	    System.out.println("Exception: "+e);
    	    System.out.println("Message : " + e.getMessage());
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel activateDegreeStatus(long gts_degree_id) {
		JsonResponseModel jsonResponseModel = new JsonResponseModel();
    	
		try {
         	degreeRepo.activateDegree(gts_degree_id);
      	    jsonResponseModel.setSuccess("True");
      	    jsonResponseModel.setMessage("Degree is activated successfully");
      	    jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
      	    jsonResponseModel.setMessage("Not able to activate");
      	    jsonResponseModel.setStatus_code("403");
      	    
      	    System.out.println("Exception: "+e);
    	    System.out.println("Message : " + e.getMessage());
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel inactivateDegreeStatus(long gts_degree_id) {
		JsonResponseModel jsonResponseModel = new JsonResponseModel();
    	
		try {
			degreeRepo.inactivateDegree(gts_degree_id);
      	    jsonResponseModel.setSuccess("True");
      	    jsonResponseModel.setMessage("Degree is inactivated successfully");
      	    jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
      	    jsonResponseModel.setMessage("Not able to inactivate");
      	    jsonResponseModel.setStatus_code("403");
      	    
      	    System.out.println("Exception: "+e);
    	    System.out.println("Message : " + e.getMessage());
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel deleteDegreeById(long degree_id) {

		JsonResponseModel jsonResponseModel= new JsonResponseModel();

		try {
			degreeRepo.deleteById(degree_id);
      	    jsonResponseModel.setSuccess("True");
      	    jsonResponseModel.setMessage("Degree deleted successfully");
      	    jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
      	    jsonResponseModel.setMessage("Degree failed to delete");
      	    jsonResponseModel.setStatus_code("403");
      	    
      	    System.out.println("Exception: "+e);
      	    System.out.println("Message : " + e.getMessage());
		}
		return jsonResponseModel;	 			

	}

}
