package com.tandt.coffee.manage.api.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${app.jwt.secret}")
	private String secret;

	@Value("${app.jwt.expiration}")
	private Long expiretion;
	
	public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	}

	public String generateToken(String usename) {
		return Jwts.builder()
				.setSubject(usename)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiretion))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
		    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
		    .build()
		    .parseClaimsJws(token);

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}