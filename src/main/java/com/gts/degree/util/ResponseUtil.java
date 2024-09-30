package com.gts.degree.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
	
	private ResponseUtil() {
	}
		
		public static String prepareErrorJSON(HttpStatus status,Exception ex) {
	    	JSONObject errorJSON=new JSONObject();
	    	try {
				errorJSON.put("success","False");
				errorJSON.put("message",ex.getMessage());
		    	errorJSON.put("status_code",status.value());
			} catch (JSONException e) {
				
				System.out.println("Exception: "+e);
			}
	    	
	    	return errorJSON.toString();

	}
		
		public static Object prepareErrorJSON(HttpStatus status, String localizedMessage) {
			
			JSONObject errorJSON = new JSONObject();
			try {
				errorJSON.put("success", "False");
				errorJSON.put("message", "Invalid input");
				errorJSON.put("status_code", status.value());
			} catch (JSONException e) {

				System.out.println("Exception: "+e);
			}

			return errorJSON.toString();
			
			
		}

}
