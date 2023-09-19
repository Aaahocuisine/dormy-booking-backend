package com.dormy.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dormy.exception.DormyServiceCustomException;
import com.dormy.jwt.models.ForgotRequest;
import com.dormy.models.UserInformation;
import com.dormy.requestDTO.OTPRequest;
import com.dormy.requestDTO.UserDTO;
import com.dormy.services.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {

		try {
			userService.saveUser(userDTO);
			return ResponseEntity.ok("User registered successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllUser() {
		List<UserInformation> user = userService.getAllUser();
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/{mobileNo}")
	public ResponseEntity<?> getUserByEmial(@PathVariable String mobile) {
		UserInformation user = userService.getUserByMobileNo(mobile);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<String> updatePassword(@RequestBody UserDTO request) {
		UserInformation user = userService.getUserByMobileNo(request.getMobileNo());
		if (user == null)
			throw new DormyServiceCustomException("User not found", "DETAILS_NOT_AVAILABLE");
		if (!request.getOtp().equals("VERIFIED")) {
			throw new DormyServiceCustomException("User not Verified", "USER_NOT_VERIFIED");
		} else {
			
			return ResponseEntity.ok().body("Password Reset Done");
		}

	}

	private final Map<String, String> otpMap = new HashMap<>();

	@PostMapping("/generateOTP")
	public ResponseEntity<?> getUserMobile(@RequestBody UserDTO request) {
		UserInformation user = userService.getUserByMobileNo(request.getMobileNo());
		if (user != null)
			throw new DormyServiceCustomException("User Not Exist !", "USER_NOT_FOUND");
			// generate otp
			String otp = userService.sendOtp(request.getMobileNo());
			otpMap.put(request.getMobileNo(), otp);
			System.out.println(otpMap);

			return ResponseEntity.ok().body("OTP send !!");
		
	}

	@PostMapping("/validateOTP")
	public ResponseEntity<?> validateOTP(@RequestBody UserDTO request) {
		if (otpMap.isEmpty())
			throw new DormyServiceCustomException("Please Send OTP", "OTP_NOT_FOUND");

		UserInformation user = userService.getUserByMobileNo(request.getMobileNo());
		if (user == null)
			throw new DormyServiceCustomException("User Not Exist !", "USER_NOT_FOUND");

		// verified otp
		System.out.println(otpMap.get(request.getMobileNo()));
		System.out.println(request.getOtp());
		if (!otpMap.get(request.getMobileNo()).equals(request.getOtp()))
			throw new DormyServiceCustomException("OTP is Not Matched !!", "OTP_NOT_MATCH");

		userService.updateOTPStatus(request.getMobileNo());
		System.out.println(otpMap);
		return ResponseEntity.ok().body("OTP Verified !!");

	}

}
