package com.dormy.exception;

import java.util.HashMap;

import lombok.Data;

@Data
public class DormyServiceCustomException extends RuntimeException {

	private String message;
	private boolean success;
	private boolean error;
	private Object data;

	public DormyServiceCustomException(String message) {
		this.message = message;
		this.error = true;
		this.success = false;
		this.data=new HashMap<>();
	}

}
