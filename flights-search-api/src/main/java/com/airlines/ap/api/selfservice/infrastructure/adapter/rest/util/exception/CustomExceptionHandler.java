package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public ResponseEntity<?> baseExceptionhandler(BaseException be) {
		return new ResponseEntity<>(be.getMessage(), be.getHttpStatus());
	}

	@ExceptionHandler(DataIntegrityViolationException  .class)
	@ResponseBody
	public ResponseEntity<?> integrityExceptionhandler(DataIntegrityViolationException be) {
		return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ResponseEntity<?> notFoundResourcesExceptionhandler(NotFoundException be) {
		return  ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not supported");
	}
	
}
