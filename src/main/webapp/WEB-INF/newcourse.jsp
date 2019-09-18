<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course View</title>
</head>
<body>
	<h1>Add New Course</h1>
	<br />
	<form:form action="/courses" method="POST" modelAttribute="course">
		<form:label path="name">Name:</form:label>
		<form:input path="name" />
		<br />
		<form:errors path="name" />
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>