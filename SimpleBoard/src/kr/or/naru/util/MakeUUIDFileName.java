package kr.or.naru.util;

import java.util.UUID;

public class MakeUUIDFileName {

	// delimiter는 구분문자라고 한다.
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + fileName + delimiter;
	}
}
