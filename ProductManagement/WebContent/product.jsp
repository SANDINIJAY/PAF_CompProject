<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management - GadgetBadget</title>

<link href="myStyle.css" rel="stylesheet" />
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/product.js"></script>

</head>

<body>
	<div class="container">

		<p class="font-weight-bold">
		<center>
			<h1>
				<b>Product Management - GadgetBadget</b>
			</h1>
		</center>
		</p>
		<br>
		<br>

		<fieldset class="mx-auto" style="width: 900px;">

			<legend>
				<b>Add Product Details</b>
			</legend>
			<form id="PRODUCT" name="PRODUCT" class="border border-light p-5">

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">Product
						ID</label> <input type="hidden" id="ID" name="ID"> <input
						type="text" id="ProID" class="form-control" name="ProID">
				</div>

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">Product
						Name</label> <input type="text" id="ProName" class="form-control"
						name="ProName">
				</div>

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">Product
						Grade</label> <input type="text" id="ProGrade" class="form-control"
						name="ProGrade">
				</div>

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">Product
						Price</label> <input type="text" id="ProPrice" class="form-control"
						name="ProPrice">
				</div>


				<br> <input id="btnSave" name="btnSave" type="button"
					value="Save" class="btn btn-success btn-sm btn-block">

			</form>

			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>

			<br/>
		
		</fieldset>

		<br>
	<div class="container" id="ProductGrid">
				<fieldset>
					<legend>
						<b>View Product Details</b>
					</legend>
					<form method="post" action="product.jsp"
						class="table table-striped">
						<%
						Product viewProduct = new Product();
						out.print(viewProduct.readProduct());
						%>
					</form>
					<br>
				</fieldset>
			</div>

	</div>
</body>
</html>