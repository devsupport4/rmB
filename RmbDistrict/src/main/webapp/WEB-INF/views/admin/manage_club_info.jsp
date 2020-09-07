<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<title>Club Info</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
	    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
	    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
	
	    <script src="https://kendo.cdn.telerik.com/2017.3.1026/js/jquery.min.js"></script>
	    <script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
	</head>
	<body ng-app="rcbs" ng-controller="clubInfoCtrl" ng-init="getClubInfoDetail()">
		<%@include file="header.jsp" %><br>		
		<div class="container"> 
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><b>Update Club Info</b></h3>
				</div>
				<div class="panel-body" style="background-color:#f2f2f2;">
					<form role="form" name="event" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label>Club Full Name<font color="red" size="3">*</font></label>									
									<input type="text" class="form-control" id="title" name="title" ng-model="title" autofocus>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Club Short Name</label>
									<input type="text" class="form-control" id="shorttitle" name="shorttitle" ng-model="shorttitle">
								</div>
							</div>							
						</div>
						<div class="row">
							<div class="col-md-6">								
								<div class="form-group">
									<label>Club Logo<font color="red" size="3">*</font></label>
									<input type="file" class="form-control" id="logo" name="logo" ng-model="logo">									
								</div>								
							</div>
							<div class="col-md-6">								
								<img alt="Logo" src="{{clublogo}}" class="img-responsive">								
							</div>																				
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Club No.</label>									
									<input type="text" class="form-control" id="clubno" name="clubno" ng-model="clubno" placeholder="Club No.">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>District No.</label>
									<input type="text" class="form-control" id="districtno" name="districtno" ng-model="districtno" placeholder="District No.">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Zone No.</label>
									<input type="text" class="form-control" id="zoneno" name="zoneno" ng-model="zoneno" placeholder="Zone No.">
								</div>
							</div>							
						</div>						
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Meeting Info & Location</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">								
								<div class="form-group">
									<label>Address Line 1<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="add1" name="add1" ng-model="add1">									
								</div>								
							</div>
							<div class="col-md-6">								
								<div class="form-group">
									<label>Address Line 2<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="add2" name="add2" ng-model="add2">									
								</div>								
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">								
								<div class="form-group">
									<label>Meeting Day<font color="red" size="3">*</font></label>
									<select class="form-control" name="day" id="day" ng-model="day">
										<option value="">Select</option>
										<option value="Monday">Monday</option>
										<option value="Tuesday">Tuesday</option>
										<option value="Wednesday">Wednesday</option>
										<option value="Thursday">Thursday</option>
										<option value="Friday">Friday</option>
										<option value="Saturday">Saturday</option>
										<option value="Sunday">Sunday</option>
									</select>									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Meeting Time<font color="red" size="3">*</font></label>
									<input id="timepicker" title="timepicker" ng-model="time" style="width: 100%;" />									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Map Location Link<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="map" name="map" ng-model="map">									
								</div>								
							</div>														
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Contact Us Info</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">								
								<div class="form-group">
									<label>Contact Person Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="personname" name="personname" ng-model="personname" placeholder="Contact Person Name">									
								</div>								
							</div>
							<div class="col-md-6">								
								<div class="form-group">
									<label>Email<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="contactemail" name="contactemail" ng-model="contactemail">									
								</div>								
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">								
								<div class="form-group">
									<label>Telephone No.</label>
									<input type="text" class="form-control" id="contacttelephoneno" name="contacttelephoneno" ng-model="contacttelephoneno" placeholder="Contact Person Name">									
								</div>								
							</div>
							<div class="col-md-3">								
								<div class="form-group">
									<label>Mobile No.</label>
									<input type="text" class="form-control" id="contactmobileno" name="contactmobileno" ng-model="contactmobileno">									
								</div>								
							</div>
							<div class="col-md-6">								
								<div class="form-group">
									<label>Full Address</label>
									<input type="text" class="form-control" id="contactaddress" name="contactaddress" ng-model="contactaddress">									
								</div>								
							</div>
						</div>							
						<div class="row">							
							<div class="col-md-12" align="center">
								<div class="form-group">
									<button type="submit" name="submit" ng-click="updateclubinfo()" class="btn btn-success" >Update <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button>																		
								</div>
							</div>													
						</div>												
					</form>
				</div>
			</div>
		</div><br>
		<%@include file="footer.jsp" %><br>	
		<script>
            $(document).ready(function () {
                 // create TimePicker from input HTML element
                 $("#timepicker").kendoTimePicker({                	 
                     dateInput: true
                 });                 
            });
        </script>	
	</body>
</html>
