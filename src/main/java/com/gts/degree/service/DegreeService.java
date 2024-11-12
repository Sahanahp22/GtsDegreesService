package com.gts.degree.service;


import java.util.List;

import com.gts.degree.entity.DegreeEntity;
import com.gts.degree.util.JsonResponseModel;

public interface DegreeService 
{
	int getDegreeCount();

	JsonResponseModel createDegree(List<DegreeEntity> degreeEntities);
	
	List<DegreeEntity> getAllDegrees();
	List<DegreeEntity> findActiveDegrees();
	List<DegreeEntity> findInactiveDegrees();
	DegreeEntity getDegreeById(long degree_id);
	List<DegreeEntity> getDegreesByName(String degree_name);
	
	JsonResponseModel updateDegree(DegreeEntity degreeEntity);
	JsonResponseModel activateDegreeStatus(long gts_degree_id);
	JsonResponseModel inactivateDegreeStatus(long gts_degree_id);

	JsonResponseModel deleteDegreeById(long degree_id);

	DegreeEntity getDegreeByName(String degreeName);	
}