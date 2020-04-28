package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.service.PdsService;
import com.jsp.utils.CreatePageMaker;

public class RemovePdsAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/remove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		
		List<AttachVO> attachList = null;
		try {
			attachList = pdsService.getPds(pno).getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					File filePathForDelete = new File(attach.getUploadPath() + File.separator + attach.getFileName());
					if (filePathForDelete.exists()) {
						filePathForDelete.delete();
					}
				}
			}
			pdsService.remove(pno);
			
			PageMaker pageMaker = CreatePageMaker.make(request);
			
			request.setAttribute("pageMaker", pageMaker);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return url;
	}

}
