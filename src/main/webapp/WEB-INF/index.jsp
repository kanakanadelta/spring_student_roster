<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	* {
		font-family: Comic Sans MS;
	}
</style>
<meta charset="UTF-8">
<title>Students</title>
</head>
<body>
	<h1>All Students</h1>
	<ul>
		<li><a href="/students/create">Add New Student</a></li>
		<li><a href="/contacts/create">Add Contact Info</a></li>
	</ul>
	<div id="students_table">
		<table>
			<thead>
				<tr>
					<td>Name</td>
					<td>Age</td>
					<td>Address</td>
					<td>City</td>
					<td>State</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="student">
					<tr>
						<td>${student.firstName} ${student.lastName}</td>
						<td>${student.studentInfo.student.studentInfo.student.age}</td>
						<td>${student.studentInfo.address}</td>
						<td>${student.studentInfo.city}</td>
						<td>${student.studentInfo.state}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>