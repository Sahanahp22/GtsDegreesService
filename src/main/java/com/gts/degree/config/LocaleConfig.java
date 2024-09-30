package com.gts.degree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LocaleConfig implements WebMvcConfigurer {
	
	 
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        
        //redisStandaloneConfiguration.setHostName("127.0.0.1");
        //redisStandaloneConfiguration.setPort(6379);
        //redisStandaloneConfiguration.setPassword("Gts@1234");

        redisStandaloneConfiguration.setHostName("redis-13616.c1.ap-southeast-1-1.ec2.cloud.redislabs.com");
        redisStandaloneConfiguration.setPort(13616);
        redisStandaloneConfiguration.setPassword("IV6xLDqx20I1IsYwatjM327mgUGYx46F");
        
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        System.out.println("Redis connection is successful");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    } 
	 
}
