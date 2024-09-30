package com.gts.degree;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;


@SpringBootApplication
public class GTSDegreesService {
	public static Cache<String, Object> cityCache=LinkedHashMapCacheBuilder.createLinkedHashMapCacheBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS).limit(20).buildCache();
	public static void main(String[] args) {
		SpringApplication.run(GTSDegreesService.class, args);
	}

}
