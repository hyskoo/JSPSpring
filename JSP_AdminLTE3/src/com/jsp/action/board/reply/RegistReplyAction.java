package com.jsp.action.board.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.request.PageMaker;
import com.jsp.request.RegistReplyRequest;
import com.jsp.request.SearchCriteria;
import com.jsp.service.ReplyService;

public class RegistReplyAction implements Action {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		RegistReplyRequest replyReq = mapper.readValue(request.getReader(), RegistReplyRequest.class);
		
		// 댓글 등록시 해당 등록댓글을 보여주기위해서 리스트를 realEndPage의 위치로 보여줘야한다.
		response.setContentType("text/plain; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		try {
			replyService.registReply(replyReq.toReplyVO());
			
			SearchCriteria cri = new SearchCriteria();
			
			Map<String, Object> dataMap = replyService.getReplyList(replyReq.getBno(), cri);
			
			PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
			int realEndPage = pageMaker.getRealEndPage();
			out.print("SUCCESS,"+realEndPage); // 구분을 위해서 ,를 사용했다.
			
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("FAIL,1");
			url = "error/500_error";
		} finally {
			if (out != null) { out.close(); }
		}
		
		return url;
	}

}
