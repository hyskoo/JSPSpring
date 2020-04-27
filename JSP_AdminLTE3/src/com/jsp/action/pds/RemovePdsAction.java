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
import com.jsp.service.PdsService;

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
		
		try {
			PdsVO pdsVO = pdsService.getPds(pno);
			List<AttachVO> attachList = pdsVO.getAttachList();
			for (AttachVO attach : attachList) {
				File filePathForDelete = new File(attach.getUploadPath() + File.separator + attach.getFileName());
				if (filePathForDelete.exists()) {
					filePathForDelete.delete();
				}
			}
			pdsService.remove(pno);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		return url;
	}

}
