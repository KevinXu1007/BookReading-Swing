package edu.tamuc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInputText {

	public static boolean checkInputIsNull(String input) {
    	if(input.equals("")) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    public static  boolean checkIsContainNumber(String input) {
        
    	for(int i = 0; i < input.length(); i++) {
          if(!Character.isDigit(input.charAt(i))){
            return false;
          }
    	}
    	return true;
    }
    
    
    public static  boolean checkIsContainCharactor(String input) {
    	for(int i = 0; i < input.length(); i++) {
    		if(input.charAt(i) < '0' || input.charAt(i) > '9') {
    			return true;
    		}    		
    	}
    	return false;
    }
    
	public static boolean checkEmailFormatIsLegal(String email) {
		Pattern emailPattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = emailPattern.matcher(email);
		if (matcher.find()) {
			return true;			
		}
		return false;
	}
    
    public static  boolean checkDateFormatIsLegal(String s) {
    	if(s.length() != 10) {
    		return false;
    	}
    	else if(s.charAt(4) != '-' || s.charAt(7) != '-') {
    		return false;
    	}
    	
    	else if(Integer.parseInt(s.substring(0,4)) < 0) {
    		return false;
    	}
    	
    	else if(Integer.parseInt(s.substring(5,7)) <= 0 || Integer.parseInt(s.substring(5,7)) > 12) {
    		return false;
    	}
    	
    	else if(Integer.parseInt(s.substring(8,10)) <= 0 || Integer.parseInt(s.substring(8,10)) > 31) {
    		return false;
    	}
    	return true;
    }
}
