package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7718828512143293558L;
	private HttpStatus httpStatus;

	public BaseException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus =httpStatus;
	}

	public BaseException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
