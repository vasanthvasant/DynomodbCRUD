package com.spring.project.dynomodb.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
@Component
public class ValidateInput {
	
	public boolean validMail(String mail) {
		String str = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}
	
	public boolean validateMobile(String mobile) {
		return mobile.matches("\\d{10}");
	}
	public boolean validateSname(String sname) {
		return sname.matches(".*\\d.*");
	}


}
