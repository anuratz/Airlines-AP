package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;
	public static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public NotFoundException(String message) {
		super(message, httpStatus);
	}

}
