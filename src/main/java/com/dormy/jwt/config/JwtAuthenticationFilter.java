package com.dormy.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dormy.jwt.helper.JwtUtil;
import com.dormy.jwt.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {

			}
			
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userName);
			//security 
			
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UsernamePasswordAuthenticationToken userAuthenticatioToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				userAuthenticatioToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(userAuthenticatioToken);
				
				
			}else {
				System.out.println("Token is not Validated !!!");
			}
		}
	
		filterChain.doFilter(request, response);
		
	}
	

}
