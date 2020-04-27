<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("자료 등록 성공");
	window.opener.location.href="list.do?page=1&perPageNum=10";
	window.close();
</script>