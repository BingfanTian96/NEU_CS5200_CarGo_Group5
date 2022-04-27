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
<link href="../profile/index.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>

	<header>
	<div class="container">

		<main>
		<div class="container-fluid">
			<div class="row">
				<nav id="sidebarMenu"
					class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">

				<div class="position-sticky pt-3">
					<ul class="nav flex-column">
						<a href="/Cargo/search" style="padding-left: 20px;"
							class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
							<img class="mb-4" src="../assets/logo.svg" alt="" width="72"
							height="57"> <span class="fs-4">CarGo</span>
						</a>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/Cargo/profile/account?userId=3"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-home"> <path
									d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path> <polyline
									points="9 22 9 12 15 12 15 22"></polyline></svg> Profile
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/Cargo/profile/posts?userId=3"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-file"> <path
									d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
								<polyline points="13 2 13 9 20 9"></polyline></svg> Posts
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/Cargo/profile/saved?userId=3"><svg
									xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="feather feather-file"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									viewBox="0 0 16 16"> <path
									d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
								</svg> Saved </a></li>
						<li class="nav-item"><a class="nav-link active"
							href="/Cargo/profile/messages?userId=3"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-file"> <path
									d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
								<polyline points="13 2 13 9 20 9"></polyline></svg> Messages
						</a></li>
					</ul>



				</div>
				</nav>
			</div>
		</div>
		<div class="container container-right" >
			<form action="" method="POST" id="form1">
				<h3>Leave a message to ${toId}</h3>
	
				<div class="mb-3">
					<textarea class="form-control" name="content" rows="5"></textarea>
				</div>
				<input hidden name="toId" value="${toId}" />
				<input hidden name="fromId" id="fromId" />
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
		
		</main>

		
	<script>
		var userId = localStorage.getItem('userId');
		document.getElementById("fromId").value = userId;
		if (userId == null || userId.trim().length == 0) {
			element = document.getElementById("form1");
			element.remove();
		}
	</script>
		<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
