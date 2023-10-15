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
table, td, th {
  border: 1px solid black;
}

table {
  border-collapse: collapse;
  width: 50%;
}
 td {
 	text-align: center;
 }
 th {
  background-color: #04AA6D;
  color: white;
}
a {
  color: hotpink;
}
/* unvisited link */
a:link {
  color: red;
}

/* visited link */
a:visited {
  color: green;
}

/* mouse over link */
a:hover {
  color: hotpink;
}

/* selected link */
a:active {
  color: blue;
}
</style>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Dashboard</title>
</head>
<body>

	<h1>Welcome, <c:out value="${loggedUser.userName}"/>!</h1>
	<p><a href="/logout">logout</a></p>
	<table class="homeTable">
		<thead>
			<tr>
				<th>Team-Name</th>
				<th>Skill-Level 1-5</th>
				<th>Game-Day</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="thisTeam" items="${allTeams}">
					<tr>
						<td><a href="/teams/${thisTeam.id}">${thisTeam.teamName}</a></td>
						<td><c:out value="${thisTeam.skillLevel}"></c:out>
						<td><c:out value="${thisTeam.gameDay}"></c:out>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<button><a href="/teams/new">Create New Team</a></button>
</body>
</html>