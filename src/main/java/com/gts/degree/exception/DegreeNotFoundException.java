package com.gts.degree.exception;

public class DegreeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	 
    public DegreeNotFoundException(String message) {
        super(message);
    }

}
