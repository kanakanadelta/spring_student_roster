<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${dorm.name}" /></title>
</head>
<body>
	<h1><c:out value="${dorm.name}"/></h1>
	<a href="/dorms">View all dorms</a>
	<form action="/dorms/${dorm.id}/add" method="POST">
		<select name="student" id="students">
			<c:forEach items="${students}" var="s">
				<option value="${s.id}">${s.firstName} ${s.lastName}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit"/>
	</form>
	
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dormStudents}" var="dS">
				<tr>
					<td>
						<c:out value="${dS.firstName}" />
						<c:out value="${dS.lastName}" />
					</td>
					<td>
						<form action="/dorms/${dorm.id}/remove" method="POST">
							<input type="hidden" name="_method" value="delete"/>
							<input type="hidden" name="student" value="${dS.id}"/>
							<input type="submit" name="student" value="Delete"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>