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
<title></title>
</head>
<style>
	p {
  border-style: solid;
  border-width: 15px;
  border: 1px solid black;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 15px;
  margin-left: 40px;
  background-color: lightblue;
  border: 1px solid black;
  background-color: lightblue;
  padding-top: 50px;
  padding-right: 30px;
  padding-bottom: 50px;
  padding-left: 50px;}
  
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
<body>
	<h1><c:out value="${thisTeam.teamName}"/></h1>
	<p><a href="/home">Dashboard</a></p>
	<p>Team-Name:<c:out value="${thisTeam.teamName}"/></p>
	<p>Added By: <c:out value="${thisTeam.creator.userName}"/></p>
	<p>Skill-Level:<c:out value="${thisTeam.skillLevel}"/></p>
	<p>Game-Day:<c:out value="${thisTeam.gameDay}"/></p>
	
	<c:if test="${loggedUser.id == thisTeam.creator.id }">
		<a href="/teams/${thisTeam.id }/edit">Edit</a>
		<form action="/teams/${ thisTeam.id }" method="POST">
			<input type="hidden" name="_method" value="Delete"/>
			<input type="submit" value="Delete"/>
		</form>
	</c:if>
</body>
</html>