<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Customer Management V10.1</h1>
<form id="formCustomer" name="formCustomer">
First Name:
<input id="first_name" name="first_name" type="text"
class="form-control form-control-sm">
<br> 	Last Name:
<input id="last_name" name="last_name" type="text"
class="form-control form-control-sm">
<br> Address:
<input id="address" name="address" type="text"
class="form-control form-control-sm">
<br> Account Number:
<input id="account_number" name="account_number" type="text"
class="form-control form-control-sm">
<br>
<br> Phone Number:
<input id="phone_number" name="phone_number" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hididSave"
name="hididSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">
<%

Customer customerObj = new Customer();
out.print(customerObj.readCustomers());
%>
</div>
</div> </div> </div>
</body>
</html>