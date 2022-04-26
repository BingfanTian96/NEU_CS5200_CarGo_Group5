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
<title>Detail</title>
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

		</header>
	</div>
	</header>

	<main>

	<div class="album py-5 bg-light">
		<div class="container">
			<table class="table">

				<tbody>
					<tr>
						<th scope="row">Vin</th>
						<td colspan="3">${car.getVin()}</td>
					</tr>
					<tr>
						<th scope="row">Year</th>
						<td>${car.getYear()}</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<th scope="row">Make</th>
						<td>${car.getMake()}</td>
						<th>Model
						</td>
						<td>${car.getModel()}</td>
					</tr>
					<tr>
						<th scope="row">Color</th>
						<td>${car.getColor()}</td>
						<th>Interior
						</td>
						<td>${car.getInterior()}</td>
					</tr>
					<tr>
						<th scope="row">Trim</th>
						<td>${car.getTrim()}</td>
						<th>Body
						</td>
						<td>${car.getBody()}</td>
					</tr>
					<tr>
						<th scope="row">Transmission</th>
						<td colspan="3">${car.getTransmission()}</td>
					</tr>
					<tr>
						<th scope="row" colspan="4" style="border: none; height: 50px;"></th>
					</tr>
					<tr>
						<th scope="row">Mmr</th>
						<td>${car.getMmr()}</td>
						<th>Selling Price
						</td>
						<td>${car.getSellingPrice()}</td>
					</tr>
					<tr>
						<th scope="row">Odometer</th>
						<td>${car.getOdometer()}</td>
						<th>Car Condition
						</td>
						<td>${car.getCarCondition()}</td>
					</tr>
					<tr>
						<th scope="row" colspan="4" style="border: none; height: 50px;"></th>
					</tr>
					<tr>
						<th scope="row">Seller</th>
						<td>${car.getSeller().getFirstName()}
							${car.getSeller().getLastName()}</td>
						<th>State
						</td>
						<td>${car.getState().toUpperCase()}</td>
					</tr>
				</tbody>
			</table>
			<div style="padding-top: 50px">
				<c:if test="${messages['success'] != null}">
					<div class="alert alert-success" role="alert">
						<c:out value="${messages['success']}" />
					</div>
				</c:if>
				<form action="" method="POST" id="form1">
					<h3>Leave a message to ${car.getSeller().getFirstName()}</h3>

					<div class="mb-3">
						<textarea class="form-control" name="content" rows="5"></textarea>
					</div>
					<input hidden name="toId" value="${car.getSeller().getUserId()}" />
					<input hidden name="fromId" id="fromId" />
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
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
		document.getElementById("fromId").value = userId;
		if (userId == null || userId.trim().length == 0) {
			element = document.getElementById("form1");
			element.remove();
		}
	</script>

	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
