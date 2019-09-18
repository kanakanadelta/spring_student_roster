<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Courses</title>
</head>
<body>
	<h1>All Courses</h1>
	<ul>
		<li>
			<a href="/students">Go Back</a>
		</li>
		<li>
			<a href="/courses/new">Add New Course</a>
		</li>
	</ul>
	
	<h2>Courses:</h2>
	<table>
		<thead>
			<tr>
				<th>Course</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courses}" var="c">
				<tr>
					<td>
						<a href="/courses/${c.id}">
							<c:out value="${c.name}" />
						</a>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>