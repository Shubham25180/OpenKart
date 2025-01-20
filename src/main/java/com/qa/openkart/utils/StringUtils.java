package com.qa.openkart.utils;

public class StringUtils {
	
	public static String getRandomEmailId() {
		String emailId = "testautomation" + System.currentTimeMillis()+"@gmail.com";
		return emailId;
	}

}
