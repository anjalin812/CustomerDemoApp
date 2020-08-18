package com.dtls.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerDtlsNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CustomerDtlsNotFoundException(String message) {
		super("Customer details not found");
	}
}
