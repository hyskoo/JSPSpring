<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%
	session.invalidate();
%>
<script>
	alert("로그아웃 되었습니다.");
	location.href="loginForm.jsp";
</script>