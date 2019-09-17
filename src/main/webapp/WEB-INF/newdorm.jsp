<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Dormitories</h1>
	<form:form action="/dorms" method="post" modelAttribute="dorm">
		<form:label path="name">Name:</form:label>
		<form:input path="name" />
		<br />
		<form:errors path="name"></form:errors>
	</form:form>
	<input type="submit" value="Submit"/>
</body>
</html>