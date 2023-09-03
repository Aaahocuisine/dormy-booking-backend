package com.dormy.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dormy.dtos.UserDTO;
import com.dormy.jwt.models.ForgotRequest;
import com.dormy.models.UserInformation;
import com.dormy.repos.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class UserService {
	@Value("${twilio.phoneNumber}")
	private String twilioPhoneNumber;
	@Value("${twilio.accountSid}")
	private String ACCOUNT_SID;
	@Value("${twilio.authToken}")
	private String AUTH_TOKEN;

	@Autowired
	private UserRepository userRepository;

	public UserInformation saveUser(UserDTO userDTO) {
		try {
			UserInformation user = new UserInformation();
			user.setEmail(userDTO.getEmail());
			user.setFirstName(userDTO.getFirstName());
			user.setGeoLocation(userDTO.getGeoLocation());
			user.setLastName(userDTO.getLastName());
			user.setMobileNo(userDTO.getMobileNo());
			user.setPassword(userDTO.getPassword());
			user.setRole(userDTO.getRole());

			// UserInformation checkResult = userRepository.save(user);
			return userRepository.save(user);

		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Email already registered");
		}
	}

	public List<UserInformation> getAllUser() {
		return userRepository.findAll();
	}

	public UserInformation getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public UserInformation getUserByMobileNo(String mobileNo) {
		return userRepository.findByMobileNo(mobileNo);
	}

	public UserInformation updatePassword(ForgotRequest request) {
		UserInformation user = userRepository.findByEmail(request.getUsername());
		user.setPassword(request.getPassword());

		return userRepository.save(user);
	}
	
	 public String sendOtp(String recipientPhoneNumber) {
	    	Random random = new Random();
	        StringBuilder otpCode = new StringBuilder();

	        for (int i = 0; i < 4; i++) {
	            int digit = random.nextInt(10); // Generate a random digit (0-9)
	            otpCode.append(digit);
	        }
	        otpCode.toString();
	    	
//	        Message.creator(new PhoneNumber(recipientPhoneNumber), new PhoneNumber(twilioPhoneNumber),
//	                "Your OTP code is: " + otpCode)
//	                .create(twilioRestClient);
	        
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		      Message.creator(
		        new com.twilio.type.PhoneNumber(recipientPhoneNumber),
		        new com.twilio.type.PhoneNumber(twilioPhoneNumber),
		        otpCode.toString())
		      .create();
		      
		      return otpCode.toString();
	    }
}
