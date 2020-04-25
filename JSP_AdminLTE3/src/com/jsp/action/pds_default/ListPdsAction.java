package com.jsp.action.pds_default;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.request.SearchCriteria;
import com.jsp.service.PdsService;

public class ListPdsAction implements Action{
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/list";
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		SearchCriteria cri = new SearchCriteria();
		
		try {
			cri.setPage(Integer.parseInt(page));
			cri.setPerPageNum(Integer.parseInt(perPageNum));
		} catch (NumberFormatException e) {
			System.out.println("[자료실] 페이지 번호 1로 세팅합니다.");
		}
		if (searchType != null && keyword != null) {
			cri.setKeyword(keyword);
			cri.setSearchType(searchType);
		}
		
		try {
			Map<String, Object> dataMap = pdsService.getList(cri);
			request.setAttribute("dataMap", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		
		
		return url;
	}
	
}
