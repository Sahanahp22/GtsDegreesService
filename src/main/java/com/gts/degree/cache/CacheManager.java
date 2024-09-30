package com.gts.degree.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CacheManager{

    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "USER_TOKEN";

    @SuppressWarnings("unchecked")
    public boolean saveUserToken(long user_id, String token) {
    	System.out.println("Saving token in Redis...");
        try {
            redisTemplate.opsForHash().put(KEY, user_id, token);
            return true;
        } catch (Exception e) {
            System.out.println("Exception: "+e);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public String getUserTokenById(Long id) {
        String token= (String) redisTemplate.opsForHash().get(KEY,id);
        return token;
    }

    @SuppressWarnings("unchecked")
    public boolean deleteUserToken(Long id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;
        } catch (Exception e) {
            System.out.println("Exception: "+e);
            return false;
        }
    }

 }
