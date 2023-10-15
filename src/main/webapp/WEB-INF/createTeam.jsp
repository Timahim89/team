<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Page</title>
</head>
<body>
	<h1>New Team</h1>
	<p><a href="/home">Dashboard</a></p>
	<form:form action="/teams/new" method="POST" modelAttribute="newTeam">
		<p>
			<form:label path="teamName">Team-Name:</form:label>
			<form:errors path="teamName"></form:errors>
			<form:input path="teamName"></form:input>
		</p>
		<p>
			<form:label path="skillLevel">skill-Level:</form:label>
			<form:errors path="skillLevel"></form:errors>
			<form:input type="number" path="skillLevel"></form:input>
		</p>
		<p>
			<form:label path="gameDay">Game-Day:</form:label>
			<form:errors path="gameDay"></form:errors>
			<form:input path="gameDay"></form:input>
		</p>
		
		<form:input type="hidden" path="creator" value="${loggedUser.id}"/>
		<input type="submit" value="create team"/> 
	</form:form>
	
</body>
</html>