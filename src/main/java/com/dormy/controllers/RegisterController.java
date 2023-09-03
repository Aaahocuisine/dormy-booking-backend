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

import com.dormy.dtos.UserDTO;
import com.dormy.jwt.models.ForgotRequest;
import com.dormy.models.OTPRequest;
import com.dormy.models.UserInformation;
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

//	@GetMapping("/{email}")
//	public ResponseEntity<?> getUserByEmial(@PathVariable String email) {
//		UserInformation user = userService.getUserByEmail(email);
//		return ResponseEntity.ok().body(user);
//	}

	@PostMapping("/forgetReset")
	public ResponseEntity<?> updatePassword(@RequestBody ForgotRequest request) {
		return ResponseEntity.ok().body(userService.updatePassword(request));
	}

	private final Map<String, String> otpMap = new HashMap<>();

	@PostMapping("/generateOTP")
	public ResponseEntity<?> getUserMobile(@RequestBody OTPRequest request) {
		UserInformation user = userService.getUserByMobileNo(request.getPhoneNumber());
		if (user != null) {
			// generate otp
			String otp = userService.sendOtp(request.getPhoneNumber());
			otpMap.put(request.getPhoneNumber(), otp);
			System.out.println(otpMap);

			return ResponseEntity.ok().body("OTP send !!");
		} else {
			return ResponseEntity.badRequest().body("User Not Exist !!");
		}
	}

	@PostMapping("/validateOTP")
	public ResponseEntity<?> validateOTP(@RequestBody OTPRequest request) {
		UserInformation user = userService.getUserByMobileNo(request.getPhoneNumber());
		if (user != null) {
			// verified  otp
			System.out.println(otpMap.get(request.getPhoneNumber()));
			System.out.println(request.getOtp());
			if (otpMap.get(request.getPhoneNumber()).equals(request.getOtp())) {
				System.out.println(otpMap);
				return ResponseEntity.ok().body("OTP Verified !!");
			}else {
				System.out.println(otpMap);
				return ResponseEntity.badRequest().body("OTP is Not Matched !!");
			}

		} else {
			return ResponseEntity.badRequest().body("User Not Exist !!");
		}
	}

	// public ResponseEntity<?> validateOTP()

}
