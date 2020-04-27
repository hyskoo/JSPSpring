package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

public class UploadPictureAction implements Action {

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; // 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; // 2MB

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = null;
		try {
			fileName = saveFile(request, response);
		} catch (Exception e) {
			e.printStackTrace(); // 개발중 에러확인
			
			// 사용자 : throw, 관리자 : 로그
			MakeLogForException.makeLog(request, e);
			throw new IOException("파일 업로드 실패"); // 사용자 화면 용 (ajax)
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(fileName);
		return null;
	}
	
	private String saveFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {

		// request 파일 첨부 여부 확인.
		if (!ServletFileUpload.isMultipartContent(request)) {
			// return null; // throw exception message 처리
			throw new Exception();
		}

		// 업로드를 위한 upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용 ( 임시 디렉토리에 저장할 파일의 용량 크기)
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정. ( 자바에 존재하는 임시저장소를 사용)
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 업로드 request 크기 적용.
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 실제 저장경로를 설정.
		// .properties파일에서 읽어오는녀석 properties파일을 static으로 올려놓고 가져오기에 key값이 동일한 것이 있으면 안된다.
		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패했습니다.");
		}
		;

		// request로 부터 받는 파일을 추출해서 저장. (Integer.parseInt처럼 파싱해서 꺼내는것)
		List<FileItem> formItems = upload.parseRequest(request);

		String fileName = null;
		// 파일 갯수 확인
		if (formItems != null && formItems.size() > 0) {
			for (FileItem item : formItems) { // form items를 반복하여 꺼내는 구문
				if (!item.isFormField()) { // isFormField는 일반파라미터인지 확인해주는것 (즉, 여기서는 파일일 경우 해당됨) - 업로드된 파일 저장

					// uuid + 구분자 + 파일명
					fileName = MakeFileName.toUUIDFileName(".jpg", "");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);

					// local HDD에 저장
					item.write(storeFile);

				} else { // oldFile 삭제 (regist에서 보내주는 name=oldPicture)
					switch (item.getFieldName()) {
					case "oldPicture":
						String oldFileNmae = item.getString("utf-8");
						File oldFile = new File(uploadPath + File.separator + oldFileNmae);
						if (oldFile.exists()) {
							oldFile.delete();
						}
						break;
					}
				}
			}
		}

		return fileName;
	}
}
