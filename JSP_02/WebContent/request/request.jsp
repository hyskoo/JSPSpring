<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<style>
	td{
		padding: 5px;
	}
</style>
<body>
	<table border="1">
		<tr>
			<td>메소드</td>
			<td>값</td>	
		</tr>
		<tr>
			<td>getContextPatd()</td>
			<td><%= request.getContextPath() %></td>	
		</tr>
		<tr>
			<td>getMetdod</td>
			<td><%= request.getMethod() %></td>	
		</tr>
		<tr>
			<td>getSession</td>
			<td><%= request.getSession() %></td>	
		</tr>
		<tr>
			<td>getProtocol</td>
			<td><%= request.getProtocol() %></td>	
		</tr>
		<tr>
			<td>getRequestURL</td>
			<td><%= request.getRequestURL() %></td>	
		</tr>
		<tr>
			<td>getRequestURI</td>
			<td><%= request.getRequestURI() %></td>	
		</tr>
		<tr>
			<td>getQueryString</td>
			<td><%= request.getQueryString() %></td>	
		</tr>
		<tr>
			<td>getRemoteHost</td>
			<td><%= request.getRemoteHost() %></td>
		</tr>
	</table>
</body>
</html>