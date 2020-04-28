<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("필터에서 잡힘");
	if (window.opener) {
		alert("세션이 만료되었습니다. \n 다시로그인하시기 바랍니다.");
		window.close();
		window.opener.location.href="<%=request.getContextPath()%>/commons/loginForm.do";
	} else {
		location.href="${pageContext.request.getContextPath()}/commons/loginForm.do";
	}
</script>