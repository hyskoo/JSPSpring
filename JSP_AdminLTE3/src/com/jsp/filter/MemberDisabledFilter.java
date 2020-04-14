package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.utils.ViewResolver;

public class MemberDisabledFilter implements Filter {

	private List<String> exURLs = new ArrayList<>();
	
	public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//제외할 url 확인
		String reqUrl=httpRequest.getRequestURI();

		System.out.println("MemberDisableFilter_reqUrl : " + reqUrl);
		
		if(!excludeCheck(reqUrl)) {
			chain.doFilter(request, response); 
			return;
		}
		
		HttpSession session = httpRequest.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if (loginUser != null && loginUser.getEnabled() == 0) {
			String url = "member/disable";
			ViewResolver.view(httpRequest, httpResponse, url);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean excludeCheck(String url) {
		for(String exURL:exURLs) {
			if(url.contains(exURL)) {
				return true;
			}
		}		
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String excludeURLNames=fConfig.getInitParameter("checkURL");
		StringTokenizer st= new StringTokenizer(excludeURLNames,",");
		while(st.hasMoreTokens()) {
			exURLs.add(st.nextToken());
		}	
	}

}
