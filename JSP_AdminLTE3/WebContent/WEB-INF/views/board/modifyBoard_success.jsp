<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("${board.title}수정에 성공하셨습니다");
	window.opener.location.reload(true);
	location.href="detail.do?bno=${board.bno}&prev=modify&writer=${board.writer}";
</script>
