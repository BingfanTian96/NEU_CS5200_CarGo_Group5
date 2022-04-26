<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="shortcut icon" href="assets/logo.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in</title>
<link href="signin/signin.css" rel="stylesheet">
</head>
<body class="text-center">
	<main class="form-signin">
	<div class="container-fluid">
		<form action="/Cargo/signin" method="POST">
			<a href="/Cargo/search"> <img class="mb-4" src="assets/logo.svg"
				alt="" width="72" height="57" href="/Cargo/search">
			</a>
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

			<div class="form-floating">
				<input type="hidden" id="userId" value="${user.getUserId()}">
				<input type="hidden" id="firstName" value="${user.getFirstName()}">
				<input type="hidden" id="lastName" value="${user.getLastName()}">
				<input type="email" class="form-control" name="email"
					placeholder="name@example.com" value="${email}"> <label
					for="floatingInput">Email address</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" name="password"
					placeholder="Password"> <label for="floatingPassword">Password</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
				In</button>
		</form>

		<span id="successMessage"><b>${messages.success}</b></span>
	</div>
	</main>

	<script>
		var userId = document.getElementById("userId").value;
		var userName = document.getElementById("firstName").value + " "
				+ document.getElementById("lastName").value;
		localStorage.setItem('userId', userId);
		localStorage.setItem('userName', userName);
	</script>
</body>
</html>
