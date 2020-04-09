<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%-- // ${}의 경우 주석은 톰캣에서 읽는다. 이것은 request.getparameter("")같은형식이라 --%>
<script>
	alert("회원등록이 실패했습니다.");
	history.go(-1);
</script>