package com.gts.degree.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gts.degree.exception.DuplicationException;
import com.gts.degree.repository.UserTokenRepository;
import com.gts.degree.util.JwtUtil;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

//	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Autowired
	private JwtUtil jwtUtil;

//	@Autowired
//	private CacheManagerForToken cacheManager;

	
	@Autowired
	private UserTokenRepository userTokenRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Auth_Token");
		System.out.println("Authorization filter HttpServletRequest header : " + header);

		String tokenPayload = null;
		String loginToken = null;
		JSONObject json = null;
		if (header != null && header.startsWith("Bearer ")) {
			loginToken = header.substring(7);

			try {
				tokenPayload = jwtUtil.extractUserDetail(loginToken);
				json = new JSONObject(tokenPayload);
				System.out.println("json : " + json.toString());
				System.out.println("Getting User id from token1: " + json.getLong("user_id"));

			} catch (JSONException e) {
				System.out.println("Exception: "+e);
				throw new DuplicationException("Unauthorized user.. Please give valid credentials");
			}
		}

		if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			String token2=null;
			
			try {
//				cacheToken = this.cacheManager.getUserTokenById(json.getLong("user_id"));
				token2 = userTokenRepo.findByUserId(json.getLong("user_id"));
			} catch (JSONException e) {
				System.out.println("Exception: "+e);
			}

			UserPrincipal userPrincipal = null;
			try {
				userPrincipal = new UserPrincipal(json.getLong("user_id"));
				System.out.println("Getting User id from token2: " + userPrincipal.getGts_user_id());

			} catch (JSONException e) {
				System.out.println("Exception: "+e);
			}

			if (jwtUtil.validateToken(loginToken, token2)) {
				System.out.println("validating token : ");

				UsernamePasswordAuthenticationToken usernamePwdAuthToken = new UsernamePasswordAuthenticationToken(
						userPrincipal, null, userPrincipal.getAuthorities());
				usernamePwdAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePwdAuthToken);
			} else {
				throw new DuplicationException("Unauthorized user.. Please give valid credentials");

			}
		}
		System.out.println("Request and response before filter : ");
		try {
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			throw new DuplicationException("Unauthorized user.. Please give valid credentials");
		}

		System.out.println("Request and response after filter : ");

	}

}
