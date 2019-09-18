<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${course.name}" /></title>
</head>
<body>
	<h1><c:out value="${course.name}" /></h1>
	<a href="/courses">View all courses</a>
	<br />
	<br />
	<h2>Students enrolled to the course:</h2>
	<ul>
		<c:forEach items="${students}" var="s">
			<li>
				<a href="/students/${s.id}">
					<c:out value="${s.firstName}" /> <c:out value="${s.lastName}" />
				</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>