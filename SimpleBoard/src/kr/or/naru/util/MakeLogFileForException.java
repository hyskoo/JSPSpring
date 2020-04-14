package kr.or.naru.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class MakeLogFileForException {
	public static void makeLogFile(HttpServletRequest request, Exception e) throws IOException{
		String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String uri = request.getRequestURI();
		String errorMessage = e.getMessage();
		
		String log = errorMessage + "," + uri + "," + today;
		
		String savePath = GetUploadPath.getUploadPath("error.log");
		
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		// 공백기준으로 짜르기
		String fileName = today.split(" ")[0] + "-log.csv";
		
		String logFilePath = savePath + fileName;
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(logFilePath, true));
			
			// 로그를 기록
			out.write(log);
			out.newLine();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
