package kr.pe.krh.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	
	public void view(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		
		if(url==null) {
			return;
		}
		
		if (url.contains("redirect:")) {
			url = request.getContextPath() + url.replace("redirect:", "");
			response.sendRedirect(url);
		}
		else {
			String prefix = "/WEB-INF/views/";
			String subfix = ".jsp"; 
			url = prefix + url + subfix;
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
}




