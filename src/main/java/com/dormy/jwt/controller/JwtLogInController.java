package com.dormy.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dormy.jwt.helper.JwtUtil;
import com.dormy.jwt.models.ForgotRequest;
import com.dormy.jwt.models.JwtRequest;
import com.dormy.jwt.models.JwtResponse;
import com.dormy.jwt.service.CustomUserDetailsService;
import com.dormy.models.UserInformation;
import com.dormy.services.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtLogInController {
	
	@Autowired
	private CustomUserDetailsService customUserService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private UserService userService;

	
	@RequestMapping(value ="/token",method =RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		if(jwtRequest.getUsername()==null || jwtRequest.getPassword()==null || jwtRequest.getPassword().isEmpty()) {
			return ResponseEntity.badRequest().body("Fields can not be blank !!");	
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential !!");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential !!");
		}
		
		//fine Area
		UserDetails userDetails = this.customUserService.loadUserByUsername(jwtRequest.getUsername());
		System.out.println(userDetails);
		String token =this.jwtUtil.generateToken(userDetails);
		
		System.out.println("JWT : "+token );
		
		return ResponseEntity.ok().body(new JwtResponse(token));
	}
	
	
	
	
}
