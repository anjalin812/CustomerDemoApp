package com.dtls.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomerExceptionHandler {
	@ExceptionHandler(CustomerDtlsNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(CustomerDtlsNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>("Customer Details not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		return new ResponseEntity<>("Customer details not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
