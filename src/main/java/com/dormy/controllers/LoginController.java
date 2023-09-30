package com.dormy.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ReportAsSingleViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.dormy.responseDTO.SuccessResponse;
import com.dormy.services.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	private UserService userService;

	private final Map<String, String> otpMap = new HashMap<>();
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveUser(@RequestBody UserDTO userDTO) {

		UserInformation user =userService.saveUser(userDTO);
		String otp = userService.sendOtp(userDTO.getMobileNo());
		otpMap.put(userDTO.getMobileNo(), otp);
		SuccessResponse response= SuccessResponse.builder()
				.error(false).success(true)
				.message("User is Successfully Registered !!")
				.data(user)
				.build();
		return ResponseEntity.ok().body(response);
		
	}

	@GetMapping
	public ResponseEntity<?> getAllUser() {
		List<UserInformation> user = userService.getAllUser();
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/{mobileNo}")
	public ResponseEntity<?> getUserByEmial(@PathVariable("mobileNo") String mobile) {
		UserInformation user = userService.getUserByMobileNo(mobile);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<String> updatePassword(@RequestBody UserDTO request) {
		UserInformation user = userService.getUserByMobileNo(request.getMobileNo());
		if (user == null)
			throw new DormyServiceCustomException("User not found");
		if (!request.getOtp().equals("VERIFIED")) {
			throw new DormyServiceCustomException("User not Verified");
		} else {

			return ResponseEntity.ok().body("Password Reset Done");
		}

	}



	@PostMapping("/generateOTP")
	public ResponseEntity<SuccessResponse> getUserMobile(@RequestBody UserDTO request) {
		UserInformation user = userService.getUserByMobileNo(request.getMobileNo());
		if (user == null)
			throw new DormyServiceCustomException("User Not Exist !");
		// generate otp
		String otp = userService.sendOtp(request.getMobileNo());
		otpMap.put(request.getMobileNo(), otp);
		System.out.println(otpMap);

		SuccessResponse respone = SuccessResponse.builder()
				.error(false).success(true).message("OTP Send").data(request.getMobileNo()).build();
		return ResponseEntity.ok().body(respone);

	}

	@PostMapping("/validateOTP")
	public ResponseEntity<SuccessResponse> validateOTP(@RequestBody UserDTO request) {
		if (otpMap.isEmpty())
			throw new DormyServiceCustomException("Please Send OTP");

		UserInformation user = userService.getUserByMobileNo(request.getMobileNo());
		if (user == null)
			throw new DormyServiceCustomException("User Not Exist !");

		// verified otp
		System.out.println(otpMap.get(request.getMobileNo()));
		System.out.println(request.getOtp());
		if (!otpMap.get(request.getMobileNo()).equals(request.getOtp()))
			throw new DormyServiceCustomException("OTP is Not Matched !!");

		UserInformation result =userService.updateOTPStatus(request.getMobileNo());
		System.out.println(otpMap);
		SuccessResponse response = SuccessResponse.builder().error(false).success(true).message("OTP Verified").data(result).build();
		return ResponseEntity.ok().body(response);

	}

}
