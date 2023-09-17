package com.dormy.requestDTO;

public class OTPRequest {

	String phoneNumber;
	String otp;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public OTPRequest(String phoneNumber, String otp) {
		super();
		this.phoneNumber = phoneNumber;
		this.otp = otp;
	}

	public OTPRequest() {
	}

}
