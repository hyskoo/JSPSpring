package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.PdsVO;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class UploadFilePdsAction {

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 20; // 20MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 45; // 45MB 
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	
	public PdsVO saveFileAndFormData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		PdsVO pdsVO = new PdsVO();
		MemberVO loginUser = (MemberVO)request.getSession().getAttribute("loginUser");
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new Exception(); // return null; 대신 예외처리
		}
		// 업로드를 위한 셋팅
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		// commons fileupolad의 객체 호출
		ServletFileUpload fileUpload = new ServletFileUpload(factory); 
		fileUpload.setFileSizeMax(MAX_FILE_SIZE);
		fileUpload.setSizeMax(MAX_REQUEST_SIZE); // 업로드 request크기 적용
		
		
		
		// 실제 경로 설정, 폴더의 날짜 설정
		Date date = new Date();
		String yy = new SimpleDateFormat("yyyy").format(date);
		String mm = new SimpleDateFormat("MM").format(date);
		String dd = new SimpleDateFormat("dd").format(date);
		String today = yy + File.separator + mm + File.separator + dd;
		
		// 파일업로드 경로 설정
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		uploadPath = uploadPath + File.separator + today;

		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패");
		};
		
		//request로부터 받는 파일을 추출해서 저장
		List<FileItem> formItems = fileUpload.parseRequest(request);
		
		// 파일 갯수 확인
		if (formItems != null && formItems.size() > 0) {
			for (FileItem item : formItems) {
				AttachVO attach = new AttachVO();
				if (!item.isFormField() && item.getFieldName().equals("uploadFile")) {
					String fileName = item.getName();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					String oriFileName = fileName.replace(fileType, "");
					String filePath = uploadPath + File.separator + MakeFileName.toUUIDFileNmae(fileType, "");
					
					File storeFile = new File(filePath);
					
					item.write(storeFile);
					
					attach.setUploadPath(uploadPath);
					attach.setFileName(oriFileName);
					attach.setFileType(fileType);
					attach.setAttacher(loginUser.getId());
					
					attachList.add(attach);
					
//					System.out.println("[UploadFileAction] fileName : " + fileName);
//					System.out.println("[UploadFileAction] fileType : " + fileType);
//					System.out.println("[UploadFileAction] oriFileName : " + oriFileName);
//					System.out.println("[UploadFileAction] filePath : " + filePath);
					
				} else {
					switch (item.getFieldName()) {
					case "pno":
						String pno = item.getString("utf-8");
						pdsVO.setPno(Integer.parseInt(pno));
						break;
					case "writer":
						String writer = item.getString("utf-8");
						pdsVO.setWriter(writer);
						break;
					case "title":
						String title = item.getString("utf-8");
						pdsVO.setTitle(title);
						break;
					case "content":
						String content = item.getString("utf-8");
						pdsVO.setContent(content);
						break;
					}
				}
			}
			pdsVO.setAttachList(attachList);
		}
		return pdsVO;
	}
	
}
