<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%
// 	session.invalidate(); // 세션을 초기화해줘야지 application에 대한 것이보인다.
	
	String str = "5교시...졸리다.... 식곤증심해....";
	pageContext.setAttribute("msg", str);
	request.setAttribute("msg", "이거 아주 방법이없네");
	session.setAttribute("msg", "졸려죽겠당...");
	application.setAttribute("msg", "애는 보기도 힘들겠네 ㄹㅇ..");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<ul>
		<li>스크립트릿 : <% out.println(pageContext.getAttribute("msg")); %></li>
		<li>표현식 : <%= pageContext.getAttribute("msg") %></li>
		<li>EL문 : ${sessionScope.msg}</li>
		<li>pageContext : ${pageScope.msg}</li>
		<li>request : ${requestScope.msg}</li>
		<li>session : ${sessionScope.msg}</li>
		<li>application : ${applicationScope.msg}</li>
	</ul>
</body>
</html>