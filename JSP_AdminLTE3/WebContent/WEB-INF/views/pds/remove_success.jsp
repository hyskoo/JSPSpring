<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	alert("자료실 글삭제에 성공하셨습니다");
	window.close();
 	window.opener.searchList_go(${pageMaker.cri.page}, "<%=request.getContextPath() %>/pds/list.do");
// 	window.opener.location.href="list.do${pageMaker.makeQuery()}";
</script>

