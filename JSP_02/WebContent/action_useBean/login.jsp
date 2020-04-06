<%@page import="com.jsp.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%--
	<%
		loginRequest loginReq = new loginRequest();
		loginReq.setId(request.getParameter("id"));
		loginReq.setPwd(request.getParameter("pwd"));		
	%> 
--%>

<jsp:useBean id="loginReq" class="com.jsp.request.LoginRequest"></jsp:useBean>
<%-- <jsp:setProperty property="*" name="loginReq"/>  해당 내용은 아래의 것을 자동으로해준다. (get, set을 꺼내와서) --%>
<jsp:setProperty name="loginReq" property="id" value='<%=request.getParameter("id") %>'></jsp:setProperty>
<jsp:setProperty name="loginReq" property="pwd" value='<%=request.getParameter("pwd") %>'></jsp:setProperty>


<%
	MemberVO member = loginReq.toMemberVO();
	member.setName("홍길동");
	member.setPhone("010-4578-4788");
	member.setAddress("대전노잼도시");
	
	
	session.setAttribute("loginUser", member);
	session.setMaxInactiveInterval(10);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	아이디 : <%=loginReq.getId() %><br/>
	패스워드 : <%=loginReq.getPwd() %><br/>
	
	<button type="button" onclick="location.href='main.jsp';">메인가기</button>
</body>
</html>