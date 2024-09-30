package com.gts.degree.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableOAuth2Client
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	   @Autowired
	   private AuthorizationFilter authorizationFilter;
	   
	   @Autowired
	   @Qualifier("oauth2authSuccessHandler")
	   private AuthenticationSuccessHandler oauth2authSuccessHandler;
	   
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {

		   http.cors().and()
	                 .csrf().disable().authorizeRequests()
//	                 .antMatchers("/api/v1/city").permitAll()
	                 .anyRequest().authenticated()
	                 .and()
	                 .sessionManagement()
	                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

	              }
	   	          
       @Override
       @Bean
       public AuthenticationManager authenticationManagerBean() throws Exception {
    	   return super.authenticationManagerBean();
       }
      
}
