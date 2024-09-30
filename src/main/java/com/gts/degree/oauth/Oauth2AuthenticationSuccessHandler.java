package com.gts.degree.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler  implements AuthenticationSuccessHandler{

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

	        if (response.isCommitted()) {
//	            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
	            return;
	        }

	}
	
}
