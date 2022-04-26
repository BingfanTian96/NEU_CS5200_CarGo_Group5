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
<title>Search</title>
</head>
<body>

	<header>
	<div class="container">
		<header
			class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
		<a href="/Cargo/search"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
			<img class="mb-4" src="assets/logo.svg" alt="" width="72" height="57">
			<span class="fs-4">CarGo</span>
		</a>

		<ul class="nav nav-pills">
			<li><a href=""
				class="link-primary" style="line-height: 40px; padding-right: 10px;" id="userName"></a></li>
			<li class="nav-item" style="padding-right: 10px;">
				<form action="/Cargo/signin" method="GET">
					<button class="btn btn-outline-primary" type="submit">Sign
						In</button>
				</form>
			</li>
			<li class="nav-item" style="padding-right: 10px;">
				<form action="/Cargo/signup/signup.jsp" method="GET">
					<button class="btn btn-outline-primary" type="submit">Sign
						Up</button>
				</form>
			</li>
			<li class="nav-item" id="new-button" style="padding-right: 10px;">
				<form action="/Cargo/new" method="GET">
					<button class="btn btn-outline-primary" type="submit">New</button>
				</form>
			</li>
		</ul>
		</header>
	</div>
	</header>

	<main> <section class="py-5 text-center container">
	<div class="container-fluid">
		<c:if test="${messages['success'] != null}">
			<div class="alert alert-success" role="alert">
				<c:out value="${messages['success']}" />
			</div>
		</c:if>
		<form class="d-flex" action="/Cargo/search" method="GET">
			<input class="form-control form-control-lg me-2" type="search"
				placeholder="Year" aria-label="year" name="year"
				value=${messages['previousYear']}> <input
				class="form-control form-control-lg me-2" type="search"
				placeholder="Make" aria-label="make" name="make"
				value=${messages['previousMake']}> <input
				class="form-control form-control-lg me-2" type="search"
				placeholder="Model" aria-label="model" name="model"
				value=${messages['previousModel']}> <input
				class="form-control form-control-lg me-2" type="search"
				placeholder="State" aria-label="state" name="state"
				value=${messages['previousState']}>
			<button class="btn btn-outline-primary" type="submit">Search</button>
		</form>

	</div>
	</section>

	<div class="album py-5 bg-light">
		<div class="container">

			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				<c:forEach items="${cars}" var="car">
					<div class="col">
						<div class="card shadow-sm">
							<div class="card-body">
								<h4 class="card-title" style="display: inline;">
									<a href="detail?vin=${car.getVin()}" class="link-primary">
										<c:out value="${car.getMake()}" /> <c:out
											value="${car.getModel()}" />
									</a>
								</h4>
								<form style="display: inline" action="/Cargo/save" method="post" id="form1">
									<input type="hidden" aria-label="vin" name="vin"
										value="${car.getVin()}" /> <input type="hidden"
										aria-label="buyerId" name="buyerId" id="saveInput" />
									<button type="submit" class="btn"
										style="display: inline; position: absolute; right: 20px;">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="#dc3545" class="bi bi-heart" viewBox="0 0 16 16">
										<path
											d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
										</svg>
									</button>
								</form>
								<h5 class="card-title mb-3 text-muted">
									$
									<c:out value="${car.getSellingPrice()}" />
								</h5>
								<h5 class="card-subtitle mb-3 text-muted">
									<c:out value="${car.getOdometer()}" />
									&nbsp Miles
								</h5>

								<p class="card-text">
									Color:&nbsp
									<c:out value="${car.getColor()}" />
									&nbsp&nbsp&nbsp&nbsp Interior:&nbsp
									<c:out value="${car.getInterior()}" />
									&nbsp&nbsp&nbsp&nbsp State:&nbsp
									<c:out value="${car.getState().toUpperCase()}" />
									&nbsp
								</p>
								
							</div>
						</div>
					</div>


				</c:forEach>

			</div>

		</div>
	</div>
	</div>

	</main>

	<footer class="text-muted py-5">
	<div class="container">
		<p class="float-end mb-1">
			<a href="#">Back to top</a>
		</p>
	</div>
	</footer>

	<script>
		var userId = localStorage.getItem('userId');
		var userName = localStorage.getItem('userName');
		if (userId == null || userId.trim().length == 0){
			element = document.getElementById("form1");
			if (element != null) {
				element.remove();
			}
			element2 = document.getElementById("new-button");
			element2.remove();
		}else{
			document.getElementById("userName").innerHTML = userName;
			document.getElementById("userName").href = "/Cargo/profile/account?userId=" + userId;
			document.getElementById("saveInput").value = userId;
		}
	</script>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
