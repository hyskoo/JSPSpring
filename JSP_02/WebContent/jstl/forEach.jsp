<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	for(int dan=2;dan<10;dan++){
		out.println(dan+"입니다.<br/>");
		for(int gop=1;gop<10;gop++){
			out.println(dan + " * " + gop + " = "+(dan*gop)+"<br/>");
		}
		out.println("<br/>");
	}
%>

<c:forEach var="dan" begin="2" end="9" step="1">
	${dan } 입니다.<br/>
	<c:forEach var="gop" begin="1" end="9" step="1">
		${dan } * ${gop } = ${dan*gop }<br/>
	</c:forEach>
	<br/>
</c:forEach>



<%
	List<String> strList = new ArrayList<String>();
	strList.add("1");
	strList.add("2");
	strList.add("3");
	strList.add("a");
	strList.add("b");
	strList.add("c");
	
	for(String str : strList){
		out.println(str+"<br/>");
	}
%>

<c:forEach var="str" items="<%=strList %>">
	${str}<br/>
</c:forEach>



