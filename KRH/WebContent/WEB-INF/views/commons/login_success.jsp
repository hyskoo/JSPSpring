<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("${loginUser.name}님 환영합니다.");
	location.href="<%=request.getContextPath()%>/board/list.do";
</script>