<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Register and Login</title>
</head>
<body>
	<nav class="d-flex justify-content-between p-5">
		<h1>Welcome!</h1>
		<div><a href="/">Go Back</a></div>
	</nav>
	<div class="d-flex p-1 justify-content-around">
		<!-- REGISTER FORM -->
		<form:form modelAttribute="registerUser" method="post" action="/register">
			<h2>Register</h2>
			<p class="d-flex justify-content-between">
				<form:label class="m-1 .flex-grow-1" path="userName">User Name:</form:label>
				<form:errors class="p-1" path="userName"/>
				<form:input class="p-1 w-45" path="userName"/>
			</p>
			<p class="d-flex justify-content-between">
				<form:label class="m-1 .flex-grow-1" path="email">Email:</form:label>
				<form:errors class="p-1" path="email"/>
				<form:input class="p-1 w-45" type="email" path="email"/>
			</p>
			<p class="d-flex justify-content-between">
				<form:label class="m-1 .flex-grow-1" path="password">Password:</form:label>
				<form:errors class="p-1" path="password"/>
				<form:password class="p-1 w-45" path="password"/>
			</p>
			<p class="d-flex justify-content-between">
				<form:label class="m-1 .flex-grow-1" path="confirmPassword">Confirm PW:</form:label>
				<form:errors class="p-1" path="confirmPassword"/>
				<form:password class="p-1 w-45" path="confirmPassword"/>
			</p>
			<input type="submit" value="Resgiter" />			
		</form:form>

			
		<!-- LOGIN FORM  -->
		<form:form modelAttribute="newLogin" method="post" action="/login">
		<h2>Log in</h2>
			<!-- BOOTSTRAP NOTE: can use gap-3 with flex parent to add a gap around each col/row  -->
			<p>
				<form:errors class="p-1" path="email"/>
			
			</p>
			<p>
			
				<form:errors class="p-1" path="password"/>
			</p>
			<p class="d-flex justify-content-between gap-3">
				<form:label class=".flex-grow-1" path="email">Email:</form:label>
				
				<form:input class="p-1 w-45" type="email" path="email"/> <!-- type="email" forces check of @ sign -->
			</p>
			<p class="d-flex justify-content-between gap-3">
				<form:label class=".flex-grow-1" path="password">Password:</form:label>

				<form:password class="p-1 w-45" path="password"/>
			</p>
			<input type="submit" value="Login" />
		</form:form>

	</div>

</body>
</html>