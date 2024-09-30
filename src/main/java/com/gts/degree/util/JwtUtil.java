package com.gts.degree.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import com.gts.degree.exception.DuplicationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	private String SECRET_KEY = "GoraiTechSol";
	
	public String extractUserDetail(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), 
                SignatureAlgorithm.HS256.getJcaName());
		return Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, String cahcheTOken) {
		
		final String userValue = extractUserDetail(token);
		final String userValue2 = extractUserDetail(cahcheTOken);
		if (userValue.equals(userValue2) && !isTokenExpired(token)) {
			return (userValue.equals(userValue2) && !isTokenExpired(token));
		}else{
			throw new DuplicationException("Unauthorized user.. Please give valid credentials");
		}
	
	}
}
