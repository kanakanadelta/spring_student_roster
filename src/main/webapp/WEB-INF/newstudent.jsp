<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Student</title>
</head>
<body>
	<h1>New Student</h1>
	<div>
		<form:form action="/students" method="POST" modelAttribute="student">
			<p>
				<form:label path="firstName">First Name</form:label>
				<form:input path="firstName"></form:input>
				<br />
				<form:errors path="firstName"></form:errors>
			</p>
			<p>
				<form:label path="lastName">Last Name</form:label>
				<form:input path="lastName"></form:input>
				<br />
				<form:errors path="lastName"></form:errors>
			</p>
			<p>
				<form:label path="age">Age</form:label>
				<form:input path="age"></form:input>
				<br />
				<form:errors path="age"></form:errors>
			</p>
			<input type="submit" value="Submit"/>
		</form:form>
	</div>
</body>
</html>