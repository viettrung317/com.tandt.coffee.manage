package com.tandt.coffee.manage.api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tandt.coffee.manage.api.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
			
		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		String username= null;
		
		if (authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username= jwtUtil.extractUsername(token);
		}
		
		if( username !=null && SecurityContextHolder.getContext().getAuthentication() ==null) {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(token)) {
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
	
}
