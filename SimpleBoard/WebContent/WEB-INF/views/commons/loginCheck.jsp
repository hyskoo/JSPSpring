<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	if (window.opener) {
		window.close();
		window.opener.location.reload();
	}
	alert("장시간 이용하지 않았습니다. 다시 로그인해주세요.");
	location.href="${pageContext.request.contextPath}/commons/login";
</script>