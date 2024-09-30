package com.gts.degree.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gts.degree.entity.UserTokenEntity;


@Repository
public interface UserTokenRepository extends PagingAndSortingRepository<UserTokenEntity, Long>{
	
	@Query(value = "select MAX(user_token_pk) from GTS_USER_TOKENS", nativeQuery = true)
    Long getMaxUserTokenPK();

	@Query(value = "select user_token from GTS_USER_TOKENS where user_id=:user_id", nativeQuery = true)
	String findByUserId(@Param("user_id") long user_id);

//	@Query(value = "INSERT INTO GTS_USER_TOKENS VALUES user_id=:user_id", nativeQuery = true)
//	void saveToken(@Param("user_id") long user_id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from GTS_USER_TOKENS where user_id=:user_id", nativeQuery = true)
	void deleteByUserId(@Param("user_id") long user_id);

	@Transactional
	@Modifying
	@Query(value="update GTS_USER_TOKENS set user_token =:user_token, where user_id =:user_id" , nativeQuery = true)
	void updateToken(@Param("user_id") long user_id, @Param("user_token") String user_token);


}
