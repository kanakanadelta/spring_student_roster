<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Information</title>
</head>
<body>
	<h1><c:out value="${student.firstName}" /> <c:out value="${student.lastName}" /></h1>
	<a href="/students">Go back to view all students</a>
	<br />
	<br />
	<form:form action="/students/${student.id}/add" method="POST" modelAttribute="enrollment">
		<form:select name="course" path="course">
			<c:forEach items="${courses}" var="c">
				<form:option value="${c[0]}">
					<c:out value="${c[2]}" />
				</form:option>
			</c:forEach>
		</form:select>
		<input type="submit" value="Submit">
	</form:form>
	
	<h2> 
		Courses 
		<c:out value="${student.firstName}"/> 
		<c:out value="${student.lastName}"/> 
		is enrolled in:
	</h2>
	<table>
		<thead>
			<tr>
				<th>Course Name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${enrolledCourses}" var="eC">
				<tr>
					<td>
						<a href="/courses/${eC[0]}">
							<c:out value="${eC[2]}" />
						</a>
					</td>
					<td>
						<form action="/students/${student.id}/drop" method="POST">
							<input type="hidden" name="_method" value="delete"/>
							<input type="hidden" name="course" value="${eC[0]}"/>
							<input type="submit" value="Delete"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>