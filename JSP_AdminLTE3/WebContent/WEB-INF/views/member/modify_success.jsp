<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("${member.id}님 정보수정에 성공하셨습니다.");
	location.href="detail?id=${member.id}";
</script>