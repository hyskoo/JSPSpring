<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	int price = 100000000;
	DecimalFormat df = new DecimalFormat("#,###");
	out.println(df.format(price));
%>

<span style="color:red; font-weight:900;">
	<fmt:formatNumber value="<%=price %>" pattern="#,###"/>￦
</span>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>