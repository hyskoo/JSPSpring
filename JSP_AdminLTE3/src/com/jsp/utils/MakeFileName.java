package com.jsp.utils;

import java.util.UUID;

public class MakeFileName {
	
	
	// delimiter는 구분문자라는 뜻
	public static String toUUIDFileNmae(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + delimiter + fileName;
	}
	
}
