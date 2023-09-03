package com.dormy.dtos;

import lombok.Data;

@Data
public class UserDTO {

	private long userId;
	private String mobileNo;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String role;
	private String geoLocation;
	
}
