package kr.pe.krh.filter;

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

import kr.pe.krh.dispatcher.ViewResolver;
import kr.pe.krh.vo.MemberVO;



public class MemberDisabledFilter implements Filter {

	private List<String> checkURLs=new ArrayList<String>();
	private ViewResolver viewResolver;
	
	public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		String uri = httpReq.getRequestURI();
		
		MemberVO loginUser = (MemberVO) httpReq.getSession().getAttribute("loginUser");
		
		if(loginUser!=null && loginUser.getEnabled()!=1) {
			for (String url : checkURLs) {	
				if (uri.contains(url)) {
					url="commons/checkDisabled";
					viewResolver.view(httpReq, httpResp, url);
					return;
				}
			}
		}
		chain.doFilter(request, response);			
	}  

	
	public void init(FilterConfig fConfig) throws ServletException {
		String paramValue = fConfig.getInitParameter("checkURL");
		StringTokenizer st = new StringTokenizer(paramValue,",");
		while(st.hasMoreTokens()) {
			String urlKey = st.nextToken();
			checkURLs.add(urlKey);
		}
		
		String viewResolverType = fConfig.getInitParameter("viewResolver");
		try {
			Class<?> cls = Class.forName(viewResolverType);
			this.viewResolver = (ViewResolver)cls.newInstance();
			System.out.println("[MemberDisabledFilter]" + viewResolverType + "이 준비완료 되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[MemberDisabledFilter]" + viewResolverType + "이 준비되지 않았습니다");
		}
	}

}





