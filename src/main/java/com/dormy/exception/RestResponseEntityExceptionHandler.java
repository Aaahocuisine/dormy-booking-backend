package com.dormy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(DormyServiceCustomException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(DormyServiceCustomException exception){
		return new ResponseEntity<>(new ErrorResponse().builder()
				.message(exception.getMessage())
				.error(true)
				.success(false)
				.data(exception.getData())
				.build(),HttpStatus.NOT_FOUND);
	}
}
