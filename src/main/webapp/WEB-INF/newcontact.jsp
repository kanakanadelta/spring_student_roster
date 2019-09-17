<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Contact</title>
</head>
<body>
	<h1>New Contact</h1>
	<div>
		<form:form action="/contacts" method="POST" modelAttribute="studentInfo">
			<p>
				<form:label path="student">Student</form:label>
				<form:select path="student">
					<c:forEach items="${noInfoStudents}" var="s">
						<form:option value="${s.id}">
							<c:out value="${s.firstName}"/> <c:out value="${s.lastName}"/>
						</form:option>
					</c:forEach>
				</form:select>
			</p>
			<p>
				<form:label path="address">Address</form:label>
				<form:input path="address"></form:input>
				<br />
				<form:errors path="address"></form:errors>
			</p>
			<p>
				<form:label path="city">City</form:label>
				<form:input path="city"></form:input>
				<br />
				<form:errors path="city"></form:errors>
			</p>
			<p>
				<form:label path="state">State</form:label>
				<form:input path="state"></form:input>
				<br />
				<form:errors path="state"></form:errors>
			</p>
			<input type="submit" value="Submit"/>
		</form:form>
	</div>
</body>
</html>