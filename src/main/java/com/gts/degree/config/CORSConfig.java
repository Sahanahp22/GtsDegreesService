package com.gts.degree.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class CORSConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**");
    }
    
    @Override
	public void addCorsMappings(CorsRegistry registry) {
		     registry.addMapping("/**").allowedOrigins("*")
			.allowedMethods("PUT","HEAD", "DELETE", "GET","POST","ANY","OPTIONS","PATCH")
			.maxAge(21600);
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//          http.cors().disable()
//              .authorizeRequests()
//              .antMatchers("/**").permitAll()
//              .anyRequest()
//              .fullyAuthenticated()
//              .and()
//              .httpBasic()
//              .and()
//              .csrf().disable();
//    }
}
