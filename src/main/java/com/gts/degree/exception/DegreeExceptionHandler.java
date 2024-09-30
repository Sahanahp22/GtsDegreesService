package com.gts.degree.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gts.degree.util.ResponseUtil;

@ControllerAdvice
public class DegreeExceptionHandler extends ResponseEntityExceptionHandler 
{
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	return new ResponseEntity<>(ResponseUtil.prepareErrorJSON(status, ex.getLocalizedMessage()),status);
    	
    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
    		HttpStatus status, WebRequest request) {
   
    	return new ResponseEntity<>(ResponseUtil.prepareErrorJSON(status, ex),status);
    }
    
}