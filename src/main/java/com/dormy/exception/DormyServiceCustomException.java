package com.dormy.exception;

import lombok.Data;

@Data
public class DormyServiceCustomException extends RuntimeException {

	private String errorCode;

	public DormyServiceCustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;

	}

}
