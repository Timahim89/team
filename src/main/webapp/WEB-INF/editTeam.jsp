<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<style>

input[type=text], select, textarea {
  width: 25%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 10px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: left;
}

input[type=submit]:hover {
  background-color: #45a049;
}
</style>
<meta charset="UTF-8">
<title>Edit Team</title>
</head>
<body>
	<h1>Edit Team: <c:out value="${originalName}"></c:out></h1>
	<p><a href="/home">Dashboard</a></p>
	<form:form action="/teams/${thisTeam.id}/edit" method="POST" modelAttribute="thisTeam">
		<input type="hidden" name="_method" value="PUT"/>
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
				
		<form:input type="hidden" path="creator" value="${thisTeam.creator.id}"/>
		
		<form action="/teams/${ thisTeam.id }" method="POST">
			<input type="submit" value="Submit"/>
		</form>

		<form action="/teams/${ thisTeam.id }" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<input type="submit" value="Delete"/>
		</form>
	</form:form>
</body>
</html>