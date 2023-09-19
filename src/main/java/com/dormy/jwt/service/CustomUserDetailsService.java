package com.dormy.jwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dormy.jwt.models.CustomUserDetails;
import com.dormy.models.UserInformation;
import com.dormy.repos.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInformation user =this.userRepo.findByMobileNo(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found !!");
		}else {
			return new CustomUserDetails(user);
		}
		
		
//		if(username.equals("Durgesh")) {
//			return new User("Durgesh","Durgesh123",new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("User Not found");
//		}
	}

}
