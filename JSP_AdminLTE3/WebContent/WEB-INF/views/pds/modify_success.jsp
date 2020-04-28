<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("게시글 수정 성공");
	location.href="detail.do${pageMaker.makeQuery()}&pno=${pds.pno}&from=modify";
</script>