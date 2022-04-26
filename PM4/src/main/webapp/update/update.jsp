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
<title>Update</title>
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
			<form action="/Cargo/update" method="POST">
				<div class="mb-3">
					<label for="InputVin" class="form-label">Vin</label> 
					<input type="text" class="form-control" name="vin" value="${car.getVin()}" readonly>
				</div>
				<div class="mb-3">
					<label for="InputYear" class="form-label">Year</label> 
					<input type="text" class="form-control" name="year" value="${car.getYear()}">
				</div>
				<div class="mb-3">
					<label for="InputMake" class="form-label">Make</label> 
					<input type="text" class="form-control" name="make" value="${car.getMake()}">
				</div>
				<div class="mb-3">
					<label for="InputModel" class="form-label">Model</label> 
					<input type="text" class="form-control" name="model" value="${car.getModel()}">
				</div>
				<div class="mb-3">
					<label for="InputTrim" class="form-label">Trim</label> 
					<input type="text" class="form-control" name="trim" value="${car.getTrim()}">
				</div>
				<div class="mb-3">
					<label for="InputBody" class="form-label">Body</label> 
					<input type="text" class="form-control" name="body" value="${car.getBody()}">
				</div>
				<div class="mb-3">
					<label for="InputTransmission" class="form-label">Transmission</label> 
					<input type="text" class="form-control" name="transmission" value="${car.getTransmission()}">
				</div>
				<div class="mb-3">
					<label for="InputState" class="form-label">State</label> 
					<input type="text" class="form-control" name="state" value="${car.getState()}">
				</div>
				<div class="mb-3">
					<label for="InputOdometer" class="form-label">Odometer</label> 
					<input type="text" class="form-control" name="odometer" value="${car.getOdometer()}">
				</div>
				<div class="mb-3">
					<label for="InputCarCondition" class="form-label">Car Condition</label> 
					<input type="text" class="form-control" name="carCondition" value="${car.getCarCondition()}">
				</div>
				<div class="mb-3">
					<label for="InputColor" class="form-label">Color</label> 
					<input type="text" class="form-control" name="color" value="${car.getColor()}">
				</div>
				<div class="mb-3">
					<label for="InputInterior" class="form-label">Interior</label> 
					<input type="text" class="form-control" name="interior" value="${car.getInterior()}">
				</div>
				<div class="mb-3">
					<label for="InputMmr" class="form-label">Mmr</label> 
					<input type="text" class="form-control" name="mmr" value="${car.getMmr()}">
				</div>
				<div class="mb-3">
					<label for="InputSellingPrice" class="form-label">Selling Price</label> 
					<input type="text" class="form-control" name="sellingPrice" value="${car.getSellingPrice()}">
				</div>
				<div class="mb-3" hidden>
					<label for="InputUserId" class="form-label">User Id</label> 
					<input type="text" class="form-control" name="userId" value="${car.getSeller().getUserId()}">
				</div>
				
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>



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


	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
