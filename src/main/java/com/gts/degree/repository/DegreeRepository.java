package com.gts.degree.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gts.degree.entity.DegreeEntity;

@Repository
public interface DegreeRepository extends JpaRepository<DegreeEntity,Long>{
	
	@Query(value = "select MAX(gts_degree_id) from GTS_DEGREES", nativeQuery = true)
    Integer countDegrees();

	@Query(value=" select * from GTS_DEGREES where gts_degree_id =:degree_id" , nativeQuery = true)
	DegreeEntity findById(@Param ("degree_id") long degree_id);

	@Query(value=" select * from GTS_DEGREES where gts_degree_name like %:degree_name%" , nativeQuery = true)
	List<DegreeEntity> findListOfDegreesByName(@Param ("degree_name") String degree_name);
	
	@Query(value=" select * from GTS_DEGREES where gts_degree_name =:degree_name" , nativeQuery = true)
	DegreeEntity findDegreeByName(@Param ("degree_name") String degree_name);
	
	@Query(value = "SELECT * FROM GTS_DEGREES WHERE gts_degree_status = true", nativeQuery = true)
	List<DegreeEntity> findActiveDegrees();
	
	@Query(value = "SELECT * FROM GTS_DEGREES WHERE gts_degree_status = false", nativeQuery = true)
	List<DegreeEntity> findInActiveDegrees();

	@Transactional
	@Modifying
	@Query(value="update GTS_DEGREES set gts_degree_status = true where gts_degree_id =:degree_id" , nativeQuery = true)
	int activateDegree(@Param ("degree_id") long degree_id);
	
	@Transactional
	@Modifying
	@Query(value="update GTS_DEGREES set gts_degree_status=false where gts_degree_id =:degree_id" , nativeQuery = true)
	int inactivateDegree(@Param ("degree_id") long degree_id);
	
}

	
	
	


