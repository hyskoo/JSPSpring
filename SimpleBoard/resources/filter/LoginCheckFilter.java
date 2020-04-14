package filter;

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

import kr.or.naru.util.ViewResolver;
import kr.or.naru.vo.MemberVO;


public class LoginCheckFilter implements Filter {

	private List<String> exURLs = new ArrayList<>();
	
	public void destroy() { }
	
	public void init(FilterConfig fConfig) throws ServletException {
		String param = fConfig.getInitParameter("exclude");
		StringTokenizer sti = new StringTokenizer(param, ",");
		if (sti.hasMoreTokens()) {
			exURLs.add(sti.nextToken());
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String regUrl = httpRequest.getRequestURI();
		
		System.out.println(regUrl);
		
		if (URLCheck(regUrl)) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = httpRequest.getSession();
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser != null) {
			String url = "commons/loginCheck";
			ViewResolver.viewReolve(httpRequest, httpResponse, url);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean URLCheck(String regUrl) {
		for (String str : exURLs) {
			if (regUrl.contains(str)) {
				return true;
			}
		}
		return false;
	}

}
