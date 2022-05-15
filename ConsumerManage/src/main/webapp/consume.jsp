<%@page import="model.cons"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumer Details</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/cons.js"></script>

</head>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Consumer Details Form</h1>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

 

<form id="formItem" name="formItem">
 Name
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br>  Address
 <input id="address" name="address" type="text" 
 class="form-control form-control-sm">
 <br> phone Number
 <input id="phone" name="phone" type="text" 
 class="form-control form-control-sm">
 <br> Email
 <input id="email" name="email"  type="text" 
 class="form-control form-control-sm">
 <br>
 <br> Description
 <input id="description" name="description"  type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 <input type="hidden" id="hidpaymentIDSave"name="hidpaymentIDSave" value="">
</form>
 
<br>
<div id="divItemsGrid">
 <%
  
 cons itemObj = new cons(); 
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 



</body>
</html>