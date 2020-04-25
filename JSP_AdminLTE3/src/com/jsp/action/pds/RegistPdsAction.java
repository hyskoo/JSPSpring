package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.request.RegistPdsRequest;
import com.jsp.service.PdsService;

public class RegistPdsAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/regist_success"; 
		
		try {
			PdsVO pds = new UploadFilePdsAction().saveFileAndFormData(request, response);
			pdsService.regist(pds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

}