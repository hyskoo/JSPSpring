package com.jsp.action.pds;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class UploadFilePds {

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB 
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	
	public PdsVO saveFileAndFormData(HttpServletRequest request) throws Exception {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		PdsVO pdsVO = new PdsVO();
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new Exception(); // return null; 대신 예외처리
		}
		// 하드디스크에 저장을 위한 셋팅 --> sqlSessionFactory의 sqlSession의 역할같은것
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		// commons fileupolad의 객체 호출
		ServletFileUpload fileUpload = new ServletFileUpload(factory); 
		fileUpload.setFileSizeMax(MAX_FILE_SIZE);
		fileUpload.setSizeMax(MAX_REQUEST_SIZE); // 업로드 request크기 적용
		
		
		
		// 실제 경로 설정, 폴더의 날짜 설정
//		Date date = new Date();
//		String yy = new SimpleDateFormat("yyyy").format(date);
//		String mm = new SimpleDateFormat("MM").format(date);
//		String dd = new SimpleDateFormat("dd").format(date);
//		String today = yy + File.separator + mm + File.separator + dd;
//		
		// 파일업로드 경로 설정
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
//		uploadPath = uploadPath + File.separator + today;

		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패");
		};
		// 여기까지 설정에 대한 내용입니다.
		
		//request로부터 받는 파일을 추출해서 저장
		List<FileItem> formItems = fileUpload.parseRequest(request);
		
		// 파일 갯수 확인
		if (formItems != null && formItems.size() > 0) {
			for (FileItem item : formItems) {
				if (!item.isFormField()) {
					// summernote에서 만든 input type="file"의 임시태그중 name가 files인것이 있으므로 제외시켜준다.
					if (!item.getFieldName().equals("uploadFile")) { continue; }
					
					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
					String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
					File storeFile = new File(filePath);
					
					// local HDD에 저장.
					try {
						item.write(storeFile);
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
					
					AttachVO attach = new AttachVO();
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFileType(fileType);

					attachList.add(attach);
					
				} else {
					int pno = -1;
					String title = null;
					String content = null;
					String writer = null;
					
					switch (item.getFieldName()) {
					case "pno":
						pno = Integer.parseInt(item.getString("utf-8"));
						pdsVO.setPno(pno);
						break;
					case "title":
						title = item.getString("utf-8");
						pdsVO.setTitle(title);
						break;
					case "content":
						content = item.getString("utf-8");
						pdsVO.setContent(content);
						break;
					case "writer":
						writer = item.getString("utf-8");
						pdsVO.setWriter(writer);
						break;
					}
				}
			}
			pdsVO.setAttachList(attachList);
		}
		return pdsVO;
	}
	
}
