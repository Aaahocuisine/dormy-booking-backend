package com.dormy.requestDTO;

import com.dormy.constant.OtpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private long userId;
	private String mobileNo;
	private String firstName;
	private String lastName;
	private String password;
	private String rePassword;
	
	private String otp;
	
}
