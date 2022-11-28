package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {
	private static final long serialVersionUID = 1L;
	public static final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public ConflictException(String message) {
		super(message, httpStatus);
	}

}
