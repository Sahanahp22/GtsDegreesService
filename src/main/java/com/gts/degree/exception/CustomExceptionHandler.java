package com.gts.degree.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gts.degree.util.ResponseUtil;



@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value=ApplicationException.class)
	public ResponseEntity<String> applicationException(ApplicationException exception){
		HttpStatus status=HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(ResponseUtil.prepareErrorJSON(status,exception),status);
	}
	
	@ExceptionHandler(value=DegreeNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(DegreeNotFoundException exception){
		HttpStatus status=HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(ResponseUtil.prepareErrorJSON(status,exception),status);
	}
	
	@ExceptionHandler(value=DuplicationException.class)
	public ResponseEntity<String> duplicationException(DuplicationException exception){
		HttpStatus status=HttpStatus.FORBIDDEN;
		return new ResponseEntity<>(ResponseUtil.prepareErrorJSON(status,exception),status);
	}
	
	
	
}
