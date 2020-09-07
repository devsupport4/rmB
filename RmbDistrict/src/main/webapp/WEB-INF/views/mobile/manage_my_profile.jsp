<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Manage My Profile | Rotary Means Business Fellowship Bangalore  </title>				
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/font-awesome-4.7.0/css/font-awesome.min.css"> 
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/profile.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/manage_my_mobile_profile.js"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.maskedinput.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-153537496-1"></script>
        <script>
             window.dataLayer = window.dataLayer || [];
             function gtag(){dataLayer.push(arguments);}
             gtag('js', new Date());
             gtag('config', 'UA-153537496-1');
        </script>

		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">
	
		<style>
			.mdl-card {
			    width: 100%;
			    height: 100%; 
			}
			.profile .panel-default>.panel-heading+.panel-collapse>.panel-body {
				background-color: #f5f5f5;
			}
			.k-toolbar .k-split-button, span.k-colorpicker, span.k-combobox, span.k-datepicker, span.k-datetimepicker, span.k-dropdown, span.k-numerictextbox, span.k-timepicker {
			    background-image: none;
			    border: 1px solid gainsboro;
			}
			.profile .demo-card-wide.mdl-card {
			    width: 100%;
			    max-width: 512px;
			    margin: 0px auto;
			}
			.deletefmypro a {
				font-size: 14px;
			    margin: 1px 0px 0px 0px;
			    font-weight: 600;
			    line-height: 24px;
			    text-transform: none;
			    color: #003a85 !important;
			}
		</style>
		<script type="text/javascript">
			jQuery(function($){
				   $("#joiningdate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#dateofbirth").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#anniversarydate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#phone").mask("(999) 999-9999");
				   $("#tin").mask("99-9999999");
				   $("#ssn").mask("999-99-9999");
				});
		</script>
	</head>
	<body class="homepage" ng-app="rcbs" ng-controller="manageProfileCtrl" ng-cloak ng-init="getmemberdetail(<%= session.getAttribute("loginid")%>)">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/my_profile" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Manage My Profile </h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="profile">
							<div class="page-content">								
								<%-- <div class="container icons">
									<div class="big-icon">
					
										<img src="{{getmemberdetail.memberProfilePicture}}" class="img-responsive " alt="" ng-if="getmemberdetail.memberProfilePicture != ''"/>
										<img class="img-responsive " src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt="" ng-if="getmemberdetail.memberProfilePicture == ''">
									</div>
								</div> --%>
								
								<div class="mdl-card  demo-card-wide">
									<div class="panel-group d-accordion">
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#aboutus">
												<h4 class="panel-title"> Personal Info <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="aboutus" class="panel-collapse collapse in">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-2">
															<div class="form-group">
																<label>Title</label>
																<select id="membertitle" name="membertitle" ng-model="membertitle" class="form-control input-lg2">
																	<option value="">Title</option>
																	<option value="Dr.">Dr</option>
																	<option value="Er.">Er</option>
																	<option value="Mr.">Mr</option>
																	<option value="Ms.">Ms</option>
																	<option value="Mx.">Mx</option>
																	<option value="Mrs.">Mrs</option>
																	<option value="Miss.">Miss</option>
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>First Name<font color="red" size="3">*</font></label>
																<input type="text" id="firstname" name="firstname" ng-model="firstname" class="form-control input-lg1" placeholder="First Name">
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<label>Middle Name</label>
																<input type="text" id="middlename" name="middlename" ng-model="middlename" class="form-control input-lg1" placeholder="Middle Name">
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>Last Name<font color="red" size="3">*</font></label>
																<input type="text" id="lastname" name="lastname" ng-model="lastname" class="form-control input-lg1" placeholder="Last Name">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-2">
															<div class="form-group">
																<label>Gender<font color="red" size="3">*</font></label>
																<select id="gender" name="gender" ng-model="gender" class="form-control input-lg2">
																	<option value="">Gender</option>
																	<option value="M">Male</option>
																	<option value="F">Female</option>
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>Date Of Birth</label>
																<input class="KendoDate" id="datepicker1" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<label>Blood Group</label>
																<select id="bloodgroup" name="bloodgroup" ng-model="bloodgroup" class="form-control input-lg2">
																	<option value="">Blood Group</option>
																	<option value="A+">A+</option>
																	<option value="A+">A-</option>
																	<option value="B+">B+</option>
																	<option value="B-">B-</option>
																	<option value="B+">O+</option>
																	<option value="B+">O-</option>
																	<option value="B+">AB+</option>
																	<option value="B+">AB-</option>
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>Anniversary Date</label>
																<input class="KendoDate" id="datepicker2" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
															</div>
														</div>														
													</div>
													<div class="row">
														<div class="col-md-3">
															<div class="form-group" id="typediv">
																<label>Nationality</label>																	
																<select ng-model="membertypename" id="membertypename" name="membertypename" ng-options="item.memberTypeId as item.memberTypeName for item in getmembertype" class="form-control input-lg2" ng-change="onCountryChange()">
																	<option value="">Nationality</option>
																</select>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group" ng-show="membertypename == 1 || membertypename == 2">
																<label>Aadhar Number</label>
																<input type="text" id="aadharnumber" name="aadharnumber" ng-model="$parent.aadharnumber" class="form-control input-lg1" placeholder="Aadhar Number" disallow-spaces capitalize>
															</div>
															<div class="form-group" ng-show="membertypename == 3">
																<label>Country Of Citizenship<font color="red" size="3">*</font></label>
																<select id="countrynamecitizenship" name="countrynamecitizenship" ng-model="$parent.countrynamecitizenship" ng-options="item.countryId as item.countryName for item in getcountry" ng-change="onCountryChange()" class="form-control input-lg2">
																	<option value="">Select Country</option>
																</select>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<label>Passport Number</label>
																<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<label>PAN Number</label>
																<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control input-lg1" placeholder="PAN No" disallow-spaces capitalize>
															</div>
														</div>
													</div>
													
													<br>
													<div class="row">
														<div class="col-xs-12 text-center">
															<div style="padding: 5px 0px 0px 0px;" class="panel-body">
																<div class="panel-heading1"> Login Credentials </div>
															</div>
														</div>
													</div>													
													<div class="row">
														<div class="col-md-12">
															<div class="input-group">
																<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
																<input id="email" name="email" ng-model="email" type="text" class="form-control input-lg1" placeholder="Email id*" ng-change="checkemailaddress()">
															</div>
														</div>																			
													</div>
													
													<div class="w3-note w3-example ">
														<p> <span style="color: red; font-weight: 600;"> Note: </span> your user name is your Email Id</p>
													</div>
													<div class="row">													
														<div class="col-md-10">
															<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
																<nav aria-label="...">
																	<ul class="pager">																	
																		<li class="next"><button type="submit" ng-click="editmemberdetail(memberid)" style="float: right; padding: 10px 20px; background-color: #5cb85c; border: 1px solid #4cae4c; color: white; border-radius: 15px;">Update <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
																	</ul>
																</nav>
															</div>
														</div>
													</div>
												</div>											
											</div>
										</div>
										
										
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#Picture">
												<h4 class="panel-title"> Profile Picture <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="Picture" class="panel-collapse collapse">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-10" align="center">															
															<label style="float: left;">Profile Picture</label>
															<input type='file' id="imgInp2" class="form-control" style="padding: 3px 12px;"/>																													
															<img id="target2"/>																																													
															<form name="myForm2">
																<input type="hidden" name="x" id="valuex"/>
																<input type="hidden" name="y" id="valuey"/>
																<input type="hidden" name="w" id="valuew"/>
																<input type="hidden" name="h" id="valueh"/>																	
															</form>
														</div>
														<div class="col-md-2">
															<img alt="" src="{{profilepicture}}" class="img-responsive" width="130px;" height="130px;">
														</div>
													</div>
													<div class="row">													
														<div class="col-md-10">
															<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
																<nav aria-label="...">
																	<ul class="pager">																	
																		<li class="next"><button type="submit" ng-click="editmemberprofile(memberid)" style="float: right; padding: 10px 20px; background-color: #5cb85c; border: 1px solid #4cae4c; color: white; border-radius: 15px;">Update <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
																	</ul>
																</nav>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										
										
										
										
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#whoweare">
												<h4 class="panel-title"> Education Details <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="whoweare" class="panel-collapse collapse">
												<div class="panel-body">
													<div class="row">
														<div class="col-xs-12">
															<div class="form-group">
																<label>Degree Name</label>
																<input id="degreename" name="degreename" ng-model="degreename" type="text" class="form-control input-lg1" placeholder="Degree">
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Passing Year</label>
																<input id="passingyear" name="passingyear" ng-model="passingyear" type="text" class="form-control input-lg1" placeholder="Passing Year">
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Grade or %</label>
																<input id="grade" name="grade" ng-model="grade" type="text" class="form-control input-lg1" placeholder="Grade or %">
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Institute / University</label>
																<input id="institutename" name="institutename" ng-model="institutename" type="text" class="form-control input-lg1" placeholder="Institute or College">
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group text-center">
																<a id="Add" value="Add" name="Add" ng-click="addEducationRowEdit()" class="btn btn-primary btn-lg" style="margin-top:10px; width:80%;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
															</div>
														</div>
													</div>
													<div class="row" ng-repeat="item in getmembereducationdetail">
														<div class="col-xs-8">
															<div class="form-group">
																<label>Degree Name</label>  {{item.degreeName}}
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<a class="btn btn-danger btn-sm" ng-click="removeEducationRowEdit(item.degreeName)" ng-if="item.degreeName != null"/><span class="glyphicon glyphicon-remove"></span></a>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Passing Year</label>  {{item.passingYear}}
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Grade</label>  {{item.grade}}
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Institute Name </label>  {{item.instituteName}}<br>
															</div>
														</div>														
													</div>
													<div class="row">													
														<div class="col-md-10">
															<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
																<nav aria-label="...">
																	<ul class="pager">																	
																		<li class="next"><button type="submit" ng-click="editmembereducationdetail(memberid)" style="float: right; padding: 10px 20px; background-color: #5cb85c; border: 1px solid #4cae4c; color: white; border-radius: 15px;">Update <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
																	</ul>
																</nav>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#contactus">
												<h4 class="panel-title"> RESIDENTIAL ADDRESS <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="contactus" class="panel-collapse collapse">
												<div class="panel-body">
													<div class="row">
														<div class="col-xs-12">
															<div class="form-group">
																<label>Address-1<font color="red" size="3">*</font></label>
																<input type="text" id="address1" name="address1" ng-model="address1" class="form-control input-lg1" placeholder="Address Line-1" >
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Address-2</label>
																<input type="text" id="address2" name="address2" ng-model="address2" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Address-3</label>
																<input type="text" id="address3" name="address3" ng-model="address3" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-12">
															<div class="form-group">
																<label>Country<font color="red" size="3">*</font></label>
																<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countryname = 1" ng-change="onCountryChange()" class="form-control input-lg2">
																	<option value="">---Select Country---</option>
																</select>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>State<font color="red" size="3">*</font></label>
																<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statename = 1" class="form-control input-lg2">
																	<option value="">---Select State---</option>
																</select>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>City Name<font color="red" size="3">*</font></label>
																<input type="text" id="cityname" name="cityname" ng-model="cityname" ng-init="cityname = 'Vadodara'" class="form-control input-lg1" placeholder="City" capitalize-first>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-12">
															<div class="form-group">
																<label>Pincode</label>
																<input type="text" id="pincode" name="pincode" ng-model="pincode" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<label>Mobile Number<font color="red" size="3">*</font></label>
																<input type="text" id="mobilenumber" name="mobilenumber" ng-model="mobilenumber" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-8">
															<div class="form-group">
																<label>Phone Number</label>
																<input type="text" id="phonenumber" name="phonenumber" ng-model="phonenumber" class="form-control input-lg1" placeholder="Phone">
															</div>
														</div>
														<div class="col-xs-4 ">
															<div class="form-group">
																<a id="Add" value="Add" name="Add" ng-click="addResidentialLandlineRow1()" class="btn btn-primary btn-lg" style="margin-top:26px; float:right;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
															</div>
														</div>
													</div>
													<div class="row" ng-repeat="item in getmemberresidentiallandline">
														<div class="col-xs-9">
															<div class="form-group">
																<label>{{item.landlinePhoneNumber}}</label>
															</div>
														</div>
														<div class="col-xs-3">
															<div class="form-group">
																<a class="btn btn-danger btn-sm" ng-click="removeResidentialLandlineRow1(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
															</div>
														</div>
													</div>
													<div class="row">													
														<div class="col-md-10">
															<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
																<nav aria-label="...">
																	<ul class="pager">																	
																		<li class="next"><button type="submit" ng-click="editcontactdetail(memberid)"style="float: right; padding: 10px 20px; background-color: #5cb85c; border: 1px solid #4cae4c; color: white; border-radius: 15px;">Update <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
																	</ul>
																</nav>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#work-info">
												<h4 class="panel-title"> WORK DETAILS <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="work-info" class="panel-collapse collapse work-info">
												<div class="panel-body">
													
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label>Occupation<font color="red" size="3">*</font></label>
																<input type="text" id="occupation" name="occupation" ng-model="occupation" class="form-control input-lg1" placeholder="Occupation" capitalize-first>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label>Designation<font color="red" size="3">*</font></label>
																<input type="text" id="designation" name="designation" ng-model="designation" class="form-control input-lg1" placeholder="Designation" capitalize-first>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label>Company Name<font color="red" size="3">*</font></label>
																<input type="text" id="companyname" name="companyname" ng-model="companyname" class="form-control input-lg1" placeholder="Company Name" capitalize-first>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label>Business Nature<font color="red" size="3">*</font></label>
																<input type="text" id="businessnature" name="businessnature" ng-model="businessnature" class="form-control input-lg1" placeholder="Business Nature" capitalize-first>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label>Fax Number</label>
																<input type="text" id="faxnumber" name="faxnumber" ng-model="faxnumber" class="form-control input-lg1" placeholder="Fax-Office">
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label>Website</label>
																<input type="text" id="website" name="website" ng-model="website" class="form-control input-lg1" placeholder="Website">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<label>About your Business</label>
																<textarea rows="4" class="form-control input-lg1" id="aboutbusiness" name="aboutbusiness" ng-model="aboutbusiness" placeholder="About your business..."></textarea>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<label>Business Keywords (Comma separated)</label>
																<textarea rows="4" class="form-control input-lg1" id="businesskeywords" name="businesskeywords" ng-model="businesskeywords" placeholder="Ex.- IT Firm, Website, Mobile Application, ..."></textarea>
															</div>
														</div>
													</div>												
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<label>Email</label>
																<input type="text" id="email" name="email" ng-model="email" class="form-control input-lg1" placeholder="Email id">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group">
																<label>Address-1<font color="red" size="3">*</font></label>
																<input type="text" id="address1work" name="address1work" ng-model="address1work" class="form-control input-lg1" placeholder="Address Line-1" capitalize-first>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>Address-2<font color="red" size="3">*</font></label>
																<input type="text" id="address2work" name="address2work" ng-model="address2work" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>Address-3</label>
																<input type="text" id="address3work" name="address3work" ng-model="address3work" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group">
																<label>Country<font color="red" size="3">*</font></label>
																<select id="countrynamework" name="countrynamework" ng-model="countrynamework" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countrynamework = 1" ng-change="onCountryChange()" class="form-control input-lg2">
																	<option value="">---Select Country---</option>
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>State<font color="red" size="3">*</font></label>
																<select id="statenamework" name="statenamework" ng-model="statenamework" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statenamework = 1" class="form-control input-lg2">
																	<option value="">---Select State---</option>
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<label>City Name<font color="red" size="3">*</font></label>
																<input type="text" id="citynamework" name="citynamework" ng-model="citynamework" ng-init="citynamework = 'Vadodara'" class="form-control input-lg1" placeholder="City" capitalize-first>
															</div>
														</div>
													</div>
													<div class="row">
														<div class=" col-md-6">
															<div class="form-group">
																<label>Pincode<font color="red" size="3">*</font></label>
																<input type="text" id="pincodework" name="pincodework" ng-model="pincodework" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label>Mobile Number<font color="red" size="3">*</font></label>
																<input type="text" id="mobilenumberwork" name="mobilenumberwork" ng-model="mobilenumberwork" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-8">
															<div class="form-group">
																<label>Phone Number</label>
																<input type="text" id="phonenumberwork" name="phonenumberwork" ng-model="phonenumberwork" class="form-control input-lg1" placeholder="Phone">
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<a id="Add" value="Add" name="Add" ng-click="addWorkLandlineRow1()" class="btn btn-primary btn-lg" style="margin-top:26px; float:right;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
															</div>
														</div>
													</div>
													<div class="row" ng-repeat="item in getmemberworklandline">
														<div class="col-xs-9">
															<div class="form-group">
																<label>{{item.landlinePhoneNumber}}</label>
															</div>
														</div>
														<div class="col-xs-3">
															<div class="form-group">
																<a class="btn btn-danger btn-sm" ng-click="removeWorkLandlineRow1(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
															</div>
														</div>
													</div>
													<div style="border-top: 2px solid green;" class="panel panel-default">
														<div style="padding: 5px 0px 0px 10px;" class="panel-body">
															<div class="panel-heading1"> Address for communication<font color="red" size="3">*</font> </div>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<label class="radio-inline">
																		<input type="radio" ng-value="'work'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()">Work
																	</label>
																	<label class="radio-inline">
																		<input type="radio" ng-value="'residential'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()">Residential
																	</label>
																	<label class="radio-inline">
																		<input type="radio" ng-value="'both'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()">Both
																	</label>
																</div>
															</div>
														</div>
													</div>
													
													<div class="row">													
														<div class="col-md-10">
															<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
																<nav aria-label="...">
																	<ul class="pager">	<br>																
																		<li class="next"><button type="submit" ng-click="editworkdetail(memberid)" style="float: right; padding: 10px 20px; background-color: #5cb85c; border: 1px solid #4cae4c; color: white; border-radius: 15px;">Update <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
																	</ul>
																</nav>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>										
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#reference-info">
												<h4 class="panel-title"> REFERENCE <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="reference-info" class="panel-collapse collapse reference-info">
												<ul class="list-group">
													<li class="list-group-item" ng-repeat="item in getspousedata">
														<a href="#" ng-click="redirecttomemberfamilyinfo(item.membersFamilyId, <%= session.getAttribute("memberid")%>)">
															<img class="img-responsive avatar" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png">															
															<%-- <img src="{{getspousedata.memberFamilyProfilePicture}}" class="img-responsive avatar" alt="" ng-if="getspousedata.memberFamilyProfilePicture != ''">
															<img class="img-responsive avatar " src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt=""  ng-if="getspousedata.memberFamilyProfilePicture == ''"> --%>
															<h4 class = "panel-title">{{item.memberFamilyTitleName}} {{item.memberFamilyFirstName}} {{item.memberFamilyMiddleName}} {{item.memberFamilyLastName}} </h4>
															<span> {{item.memberFamilyTypeOfRelation}} </span>
														</a>
													</li>													
												</ul>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#family-info">
												<h4 class="panel-title"> FAMILY INFORMATION <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="family-info" class="panel-collapse collapse family-info">
												<ul class="list-group" >
													<li class="list-group-item" ng-repeat="item in getspousedata">
														<a href="#">
															<%-- <img class="img-responsive avatar" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png"> --%>															
															<img src="{{item.memberFamilyProfilePicture}}" class="img-responsive avatar" alt="" ng-if="item.memberFamilyProfilePicture != ''">
															<img class="img-responsive avatar " src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt=""  ng-if="item.memberFamilyProfilePicture == ''">
															
															<h4 class="panel-title deletefmypro">{{item.memberFamilyTitleName}} {{item.memberFamilyFirstName}} {{item.memberFamilyMiddleName}} {{item.memberFamilyLastName}}  <a style="float:right; font-size:17px;" ng-click="deletefamilymember(item.membersFamilyId)"><i class="fa fa-trash" aria-hidden="true"></i>Delete </a></h4>
															<span> {{item.memberFamilyTypeOfRelation}} </span><br>
															<p style="padding-top:5px;"> <a style="float:left;font-size:17px;" data-toggle="modal" data-target="#edit" ng-click="getspouse(item.membersFamilyId, '<%= session.getAttribute("membershipnumber") %>', '<%= request.getParameter("memberid") %>')"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i>EDIT</a>   <a style="float:right; font-size:17px;" data-toggle="modal" data-target="#squarespaceModal1" ng-click="spousememberid('<%= session.getAttribute("membershipnumber") %>', '<%= session.getAttribute("loginid") %>')"><i class="fa fa-plus-circle" aria-hidden="true"></i>ADD </a>  </p>
														</a>
													</li>													
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</main>
			<div class="modal fade" id="squarespaceModal1" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">																
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">x</span><span class="sr-only">close</span></button>
							<h4 class="modal-title" id="lineModalLabel">ADD SPOUSE DETAIL</h4>
						</div>
						<div class="modal-body" style="font-size: 15px;">
							<div class="row">																		
								<div class="col-xs-12">
									<div class="form-group">
										<label>Membership Number</label>
										<input type="text" id="spouseid" name="spouseid" ng-model="spouseid" readonly class="form-control input-lg1" placeholder="Membership No">
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<label>Relation<font color="red" size="3">*</font></label>
										<select id="relation" name="relation" ng-model="relation" class="form-control input-lg2">
											<option value="">Select Relation</option>
											<option value="Spouse">Spouse</option>
											<option value="Child">Child</option>
										</select>
									</div>
								</div>
								
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label>Title</label>
										<select id="memberfamilytitle" name="memberfamilytitle" ng-model="memberfamilytitle" class="form-control input-lg2">
											<option value="">Title</option>
											<option value="Dr.">Dr</option>
											<option value="Er.">Er</option>
											<option value="Mr.">Mr</option>
											<option value="Ms.">Ms</option>
											<option value="Mx.">Mx</option>
											<option value="Mrs.">Mrs</option>
											<option value="Miss.">Miss</option>
										</select>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<label>First Name<font color="red" size="3">*</font></label>
										<input type="text" id="familyfirstname" name="familyfirstname" ng-model="familyfirstname" class="form-control input-lg1" placeholder="First Name" capitalize-first>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<label>Middle Name</label>
										<input type="text" id="familymiddlename" name="familymiddlename" ng-model="familymiddlename" class="form-control input-lg1" placeholder="Mid. Name" capitalize-first>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label>Last Name<font color="red" size="3">*</font></label>
										<input type="text" id="familylastname" name="familylastname" ng-model="familylastname" class="form-control input-lg1" placeholder="Last Name" capitalize-first>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<label>Gender<font color="red" size="3">*</font></label>
										<select id="familygender" name="familygender" ng-model="familygender" class="form-control input-lg2">
											<option value="">Gender</option>
											<option value="Male">Male</option>
											<option value="Female">Female</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">																
									<label>Date Of Birth</label>
									<div class="row">
										<div class="col-xs-4 pr-5">
											<div class="form-group">
												<select id="birthdate" name="birthdate" ng-model="birthdate" ng-options="item for item in date" class="form-control input-lg2">
													<option value="" style="padding-top: 5px; padding-bottom: 5px;">Date</option>
												</select>
											</div>
										</div>
										<div class="col-xs-4 pl-5 pr-5">
											<div class="form-group">
												<select id="birthmonth" name="birthmonth" ng-model="birthmonth" class="form-control input-lg2">
													<option value="">Month</option>
													<option value="01">January</option>
													<option value="02">February</option>
													<option value="03">March</option>
													<option value="04">April</option>
													<option value="05">May</option>
													<option value="06">June</option>
													<option value="07">July</option>
													<option value="08">August</option>
													<option value="09">September</option>
													<option value="10">October</option>
													<option value="11">November</option>
													<option value="12">December</option>
												</select>
											</div>
										</div>
										<div class="col-xs-4 pl-5">
											<div class="form-group">
												<input type="text" id="birthyear" name="birthyear" ng-model="birthyear" class="form-control input-lg2" placeholder="Year">
											</div>
										</div>
									</div>
									
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label>Blood Group</label>
										<select id="bloodgroup" name="bloodgroup" ng-model="bloodgroup" class="form-control input-lg2">
											<option value="">Blood Group</option>
											<option value="A+">A+</option>
											<option value="A-">A-</option>
											<option value="B+">B+</option>
											<option value="B-">B-</option>
											<option value="O+">O+</option>
											<option value="O-">O-</option>
											<option value="AB+">AB+</option>
											<option value="AB-">AB-</option>
										</select>
									</div>
								</div>																		
							</div>																	
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<input type="text" id="aadharnumber" name="aadharnumber" ng-model="aadharnumber" class="form-control input-lg1" placeholder="Aadhar Number" disallow-spaces capitalize>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control input-lg1" placeholder="PAN Number" disallow-spaces capitalize>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12" align="center">
									<label style="float: left;">Profile Picture</label>
									<input type='file' id="imgInp" class="form-control" style="padding-top:3px;"/>																
																									
										<img src="#" id="target"/>
																																		
									<form name="myForm">
										<input type="hidden" name="x" id="valuex" ng-model="valuex"/>
										<input type="hidden" name="y" id="valuey" ng-model="valuey"/>
										<input type="hidden" name="w" id="valuew" ng-model="valuew"/>
										<input type="hidden" name="h" id="valueh" ng-model="valueh"/>																	
									</form>
								</div>																		
							</div>
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<div style="padding: 10px 0px 0px 0px;" class="panel-body text-center">
									<div class="panel-heading1" style="font-size:18px;"> Education Details </div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												<label>Degree Name</label>
												<input id="degreename" name="degreename" ng-model="degreename" type="text" class="form-control input-lg1" placeholder="Degree">
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>Passing Year</label>
												<input id="passingyear" name="passingyear" ng-model="passingyear" type="text" class="form-control input-lg1" placeholder="Passing Year">
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>Grade or %</label>
												<input id="grade" name="grade" ng-model="grade" type="text" class="form-control input-lg1" placeholder="Grade or %">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												<label>Institute / University</label>
												<input id="institutename" name="institutename" ng-model="institutename" type="text" class="form-control input-lg1" placeholder="Institute or College">
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<a id="Add" value="Add" name="Add" ng-click="addEducationRow()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
											</div>
										</div>
									</div>
									<div class="row" ng-repeat="item in education">
										<div class="col-xs-12">
											<div class="form-group">
												<label>{{item.degreeName}}</label>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>{{item.passingYear}}</label>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>{{item.grade}}</label>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>{{item.instituteName}}</label>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<a class="btn btn-danger btn-sm" ng-click="removeEducationRow(item.degreeName)" ng-if="item.degreeName != null"/><span class="glyphicon glyphicon-remove"></span></a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<div style="padding: 10px 0px 0px 0px;" class="panel-body text-center">
									<div class="panel-heading1" style="font-size:18px;"> Login Credentials<font color="red" size="3">*</font> </div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
													<input type="text" id="email" name="email" ng-model="email" class="form-control input-lg1" placeholder="Email Id*" ng-change="checkemailaddress()">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
													<input type="password" id="password" name="password" ng-model="password" class="form-control input-lg1" placeholder="Password*">                                        
												</div>
											</div>
										</div>
									</div>																			
								</div>
								<div class="w3-note w3-example">
									<p> <span style="color: red; font-weight: 600;"> Note: </span> your user name is your Email Id</p>
								</div>
							</div>
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<div style="padding: 5px 0px 0px 0px;" class="panel-body">
									<div class="panel-heading1">
										<div class="row">
											<div class="col-xs-6" style="font-size:16px;">Residential Address</div>
											<div class="col-xs-6">																											
												<input id="residentialaddress" name="residentialaddress" ng-model="residentialaddress" type="checkbox" ng-click="residentialaddresssameasmember(<%= session.getAttribute("memberid")%>)"/>
												<label for="residentialaddress">Same As Member</label>																																																
											</div>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												<label>Address-1<font color="red" size="3">*</font></label>
												<input type="text" id="familyaddress1" name="familyaddress1" ng-model="familyaddress1" class="form-control input-lg1" placeholder="Address Line-1" capitalize-first>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>Address-2</label>
												<input type="text" id="familyaddress2" name="familyaddress2" ng-model="familyaddress2" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>Address-3</label>
												<input type="text" id="familyaddress3" name="familyaddress3" ng-model="familyaddress3" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												<label>Country<font color="red" size="3">*</font></label>
												<select id="familycountryname" name="familycountryname" ng-model="familycountryname" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countryname = 1" ng-change="onCountryChange()" class="form-control input-lg2">
													<option value="">---Select Country---</option>
												</select>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>State<font color="red" size="3">*</font></label>
												<select id="familystatename" name="familystatename" ng-model="familystatename" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statename = 1" class="form-control input-lg2">
													<option value="">---Select State---</option>
												</select>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>City Name<font color="red" size="3">*</font></label>
												<input type="text" id="familycityname" name="familycityname" ng-model="familycityname" ng-init="cityname = 'Vadodara'" class="form-control input-lg1" placeholder="City" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												<label>Pincode</label>
												<input type="text" id="familypincode" name="familypincode" ng-model="familypincode" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="form-group">
												<label>Mobile Number</label>
												<input type="text" id="familymobilenumber" name="familymobilenumber" ng-model="familymobilenumber" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-8">
											<div class="form-group">
												<label>Phone Number</label>
												<input type="text" id="phonenumber" name="phonenumber" ng-model="phonenumber" class="form-control input-lg1" placeholder="Phone">
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<a id="Add" value="Add" name="Add" ng-click="addFamilyResidentialLandlineRow()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
											</div>
										</div>
									</div>
									<div class="row" ng-repeat="item in familyresidentiallandline">
										<div class="col-md-2">
											<div class="form-group">
												<label>{{item.landlinePhoneNumber}}</label>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<a class="btn btn-danger btn-sm" ng-click="removeFamilyResidentialLandlineRow(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<div style="padding: 10px 0px 0px 5px;" class="panel-body">
									<div class="panel-heading1">
										<div class="row">
											<div class="col-xs-6" style="font-size:16px;">Work Detail</div>
											<div class="col-xs-6">																											
												<input id="workdetail" name="workdetail" ng-model="workdetail" type="checkbox" ng-click="workdetailsameasmember(<%= session.getAttribute("memberid")%>)"/>
												<label for="workdetail">Same As Member</label>																											
											</div>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Occupation</label>
												<input type="text" id="familyoccupation" name="familyoccupation" ng-model="familyoccupation" class="form-control input-lg1" placeholder="Occupation" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Designation</label>
												<input type="text" id="familydesignation" name="familydesignation" ng-model="familydesignation" class="form-control input-lg1" placeholder="Designation" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Company Name</label>
												<input type="text" id="familycompanyname" name="familycompanyname" ng-model="familycompanyname" class="form-control input-lg1" placeholder="Company Name" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Business Nature</label>
												<input type="text" id="familybusinessnature" name="familybusinessnature" ng-model="familybusinessnature" class="form-control input-lg1" placeholder="Business Nature" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Fax Number</label>
												<input type="text" id="familyfaxnumber" name="familyfaxnumber" ng-model="familyfaxnumber" class="form-control input-lg1" placeholder="Fax-Office">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Website</label>
												<input type="text" id="familywebsite" name="familywebsite" ng-model="familywebsite" class="form-control input-lg1" placeholder="Website">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Email</label>
												<input type="text" id="familyemailwork" name="familyemailwork" ng-model="familyemailwork" class="form-control input-lg1" placeholder="Email">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-1</label>
												<input type="text" id="familyaddress1work" name="familyaddress1work" ng-model="familyaddress1work" class="form-control input-lg1" placeholder="Address Line 1" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-2</label>
												<input type="text" id="familyaddress2work" name="familyaddress2work" ng-model="familyaddress2work" class="form-control input-lg1" placeholder="Address Line 2" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-3</label>
												<input type="text" id="familyaddress3work" name="familyaddress3work" ng-model="familyaddress3work" class="form-control input-lg1" placeholder="Address Line 3" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Country</label>
												<select id="familycountrynamework" name="familycountrynamework" ng-model="familycountrynamework" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countrynamework = 1" ng-change="onCountryChange()" class="form-control input-lg2">
													<option value="">---Select Country---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>State</label>
												<select id="familystatenamework" name="familystatenamework" ng-model="familystatenamework" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statenamework = 1" class="form-control input-lg2">
													<option value="">---Select State---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>City Name</label>
												<input type="text" id="familycitynamework" name="familycitynamework" ng-model="familycitynamework" class="form-control input-lg1" ng-init="citynamework = 'Vadodara'" placeholder="City" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Pincode</label>
												<input type="text" id="familypincodework" name="familypincodework" ng-model="familypincodework" class="form-control input-lg1" placeholder="Pincode" disallow-spaces>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Mobile Number</label>
												<input type="text" id="familymobilenumberwork" name="familymobilenumberwork" ng-model="familymobilenumberwork" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
											</div>
										</div>
										
									</div>
									<div class="row">
										<div class="col-xs-8">
											<div class="form-group">
												<label>Phone Number</label>
												<input type="text" id="phonenumberwork" name="phonenumberwork" ng-model="phonenumberwork" class="form-control input-lg1" placeholder="phone">
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<a id="Add" value="Add" name="Add" ng-click="addFamilyWorkLandlineRow()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
											</div>
										</div>
									</div>
									<div class="row" ng-repeat="item in familyworklandline">
										<div class="col-md-2">
											<div class="form-group">
												<label>{{item.landlinePhoneNumber}}</label>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<a class="btn btn-danger btn-sm" ng-click="removeFamilyWorkLandlineRow(item.landlinePhoneNumber)" ng-show="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<div style="padding: 5px 0px 0px 0px;" class="panel-body text-center">
									<div class="panel-heading1" style="font-size:18px;"> ADDRESS FOR COMMUNICATION<font color="red" size="3">*</font></div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<label class="radio-inline" >
												<input type="radio" id="familycommunication" name="familycommunication" ng-model="familycommunication" ng-value="'work'" ng-click="checkcommunication()">Work
											</label>
											<label class="radio-inline">
												<input type="radio" id="familycommunication" ng-model="familycommunication" ng-value="'residential'" ng-click="checkcommunication()">Residential
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="btn-group btn-group-justified" role="group" aria-label="group button">
								<div class="btn-group" role="group">
									<input type="submit" ng-click="addspousedetail()" class="btn btn-success" value="Save">
								</div>
								<div class="btn-group" role="group">
									<button type="button" class="btn btn-default" data-dismiss="modal"  role="button">Close</button>
								</div>
							</div>
						</div>																
					</div>
				</div>
			</div>
			<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">					
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
						<h4 class="modal-title custom_align" id="Heading">Edit Spouse Detail </h4>
					</div>
					<div class="modal-body">
						<div class="row">
							
							<div class="col-md-4">
								<div class="form-group">
									<label>Membership Number</label>
									<input type="text" id="spouseidedit" name="spouseidedit" ng-model="spouseidedit" readonly class="form-control input-lg1" placeholder="Membership No">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Relation<font color="red" size="3">*</font></label>
									<select id="relationedit" name="relationedit" ng-model="relationedit" class="form-control input-lg2">
										<option value="">Select Relation</option>
										<option value="Spouse">Spouse</option>
										<option value="Child">Child</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Title</label>
									<select id="memberfamilytitleedit" name="memberfamilytitleedit" ng-model="memberfamilytitleedit" class="form-control input-lg2">
										<option value="">Title</option>
										<option value="Dr.">Dr</option>
										<option value="Er.">Er</option>
										<option value="Mr.">Mr</option>
										<option value="Ms.">Ms</option>
										<option value="Mx.">Mx</option>
										<option value="Mrs.">Mrs</option>
										<option value="Miss.">Miss</option>
									</select>
								</div>
							</div>
							<div class="col-md-5">
								<div class="form-group">
									<label>First Name<font color="red" size="3">*</font></label>
									<input type="text" id="firstnameedit" name="firstnameedit" ng-model="firstnameedit" class="form-control input-lg1" placeholder="First Name" capitalize-first>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Middle Name</label>
									<input type="text" id="middlenameedit" name="middlenameedit" ng-model="middlenameedit" class="form-control input-lg1" placeholder="Middle Name" capitalize-first>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<div class="form-group">
									<label>Last Name<font color="red" size="3">*</font></label>
									<input type="text" id="lastnameedit" name="lastnameedit" ng-model="lastnameedit" class="form-control input-lg1" placeholder="Last Name" capitalize-first>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Gender<font color="red" size="3">*</font></label>
									<select id="genderedit" name="genderedit" ng-model="genderedit" class="form-control input-lg2">
										<option value="">Select Gender</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">								
								<label>Date Of Birth</label>
								<div class="row">
									<div class="col-xs-4 pr-5">
										<div class="form-group">
											<select id="birthdateedit" name="birthdateedit" ng-model="birthdateedit" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
									</div>
									<div class="col-xs-4 pl-5 pr-5">
										<div class="form-group">
											<select id="birthmonthedit" name="birthmonthedit" ng-model="birthmonthedit" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
									</div>
									<div class="col-xs-4 pl-5">
										<div class="form-group">
											<input type="text" id="birthyearedit" name="birthyearedit" ng-model="birthyearedit" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>									
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Blood Group</label>
									<select id="bloodgroupedit" name="bloodgroupedit" ng-model="bloodgroupedit" class="form-control input-lg2">
										<option value="">--- Select Blood Group ---</option>
										<option value="A+">A+</option>
										<option value="A-">A-</option>
										<option value="B+">B+</option>
										<option value="B-">B-</option>
										<option value="O+">O+</option>
										<option value="O-">O-</option>
										<option value="AB+">AB+</option>
										<option value="AB-">AB-</option>
									</select>
								</div>
							</div>									
						</div>									
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<input type="text" id="aadharnumberedit" name="aadharnumberedit" ng-model="aadharnumberedit" class="form-control input-lg1" placeholder="Aadhar Number" disallow-spaces capitalize>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input type="text" id="passportnumberedit" name="passportnumberedit" ng-model="passportnumberedit" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input type="text" id="pannumberedit" name="pannumberedit" ng-model="pannumberedit" class="form-control input-lg1" placeholder="PAN Number" disallow-spaces capitalize>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10" align="center">
								<label style="float: left;">Profile Picture</label>
								<input type='file' id="imgInp1" class="form-control" style="padding-top:3px;"/>																
								<img src="#" id="target1"/>
								<form name="myForm1">
									<input type="hidden" name="x" id="valuex"/>
									<input type="hidden" name="y" id="valuey"/>
									<input type="hidden" name="w" id="valuew"/>
									<input type="hidden" name="h" id="valueh"/>																	
								</form>
							</div>
							<div class="col-md-2">
								<img alt="" src="{{profileedit}}" class="img-responsive" width="130px;" height="130px;">
							</div>
						</div>
						<div style="padding-bottom: 15px;" class="panel panel-default">
							<div style="padding: 5px 0px 0px 0px;" class="panel-body text-center">
								<div class="panel-heading1" style="font-size:18px"> Education Details </div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Degree Name</label>
											<input id="degreenameedit" name="degreenameedit" ng-model="degreenameedit" type="text" class="form-control input-lg1" placeholder="Degree">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Passing Year</label>
											<input id="passingyearedit" name="passingyearedit" ng-model="passingyearedit" type="text" class="form-control input-lg1" placeholder="Passing Year">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Grade or %</label>
											<input id="gradeedit" name="gradeedit" ng-model="gradeedit" type="text" class="form-control input-lg1" placeholder="Grade or %">
										</div>
									</div>
									
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Institute / University</label>
											<input id="institutenameedit" name="institutenameedit" ng-model="institutenameedit" type="text" class="form-control input-lg1" placeholder="Institute or College">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<a id="Add" value="Add" name="Add" ng-click="addEducationRow1()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
										</div>
									</div>
								</div>
								<div class="row" ng-repeat="item in getfamilyeducationdetail">
									<div class="col-md-2">
										<div class="form-group">
											<label>{{item.degreeName}}</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>{{item.passingYear}}</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>{{item.grade}}</label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>{{item.instituteName}}</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<a class="btn btn-danger btn-sm" ng-click="removeEducationRow1(item.degreeName)" ng-if="item.degreeName != null"/><span class="glyphicon glyphicon-remove"></span></a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="padding-bottom: 15px;" class="panel panel-default">
							<div style="padding: 5px 0px 0px 0px;" class="panel-body text-center">
								<div class="panel-heading1" style="font-size:18px"> Login Credentials<font color="red" size="3">*</font> </div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												<input type="text" id="emailedit" name="emailedit" ng-model="emailedit" class="form-control input-lg1" placeholder="Email Id*" ng-change="checkemailaddress1()">
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
												<input type="password" id="passwordedit" name="passwordedit" ng-model="passwordedit" class="form-control input-lg1" placeholder="Password*">                                        
											</div>
										</div>
									</div>
								</div>											
							</div>
							<div class="w3-note w3-example">
								<p> <span style="color: red; font-weight: 600;"> Note: </span> your user name is your Email Id</p>
							</div>
						</div>
						<div style="padding-bottom: 15px;" class="panel panel-default">
							<div style="padding: 10px 0px 0px 0px;" class="panel-body text-center">
								<div class="panel-heading1">
									<div class="row">
										<div class="col-xs-6" style="font-size:17px">Residential Address</div>
										<div class="col-xs-6">
											<input id="residentialaddressedit" name="residentialaddressedit" ng-model="residentialaddress" type="checkbox" ng-click="residentialaddresssameasmember(<%= session.getAttribute("memberid")%>)"/>
											<label for="residentialaddress">Same As Member</label>											
										</div>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Address-1<font color="red" size="3">*</font></label>
											<input type="text" id="address1edit" name="address1edit" ng-model="address1edit" class="form-control input-lg1" placeholder="Address Line-1" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Address-2</label>
											<input type="text" id="address2edit" name="address2edit" ng-model="address2edit" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Address-3</label>
											<input type="text" id="address3edit" name="address3edit" ng-model="address3edit" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Country<font color="red" size="3">*</font></label>
											<select id="countrynameedit" name="countrynameedit" ng-model="countrynameedit" ng-options="item.countryId as item.countryName for item in getcountry" ng-change="onCountryChange()" class="form-control input-lg2">
												<option value="">---Select Country---</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>State<font color="red" size="3">*</font></label>
											<select id="statenameedit" name="statenameedit" ng-model="statenameedit" ng-options="item.stateId as item.stateName for item in getrelatedstate" class="form-control input-lg2">
												<option value="">---Select State---</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>City Name<font color="red" size="3">*</font></label>
											<input type="text" id="citynameedit" name="citynameedit" ng-model="citynameedit" class="form-control input-lg1" placeholder="City" capitalize-first>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Pincode</label>
											<input type="text" id="pincodeedit" name="pincodeedit" ng-model="pincodeedit" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Mobile Number</label>
											<input type="text" id="mobilenumberedit" name="mobilenumberedit" ng-model="mobilenumberedit" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-8 pr-5">
										<div class="form-group">
											<label>Phone Number</label>
											<input type="text" id="phonenumberedit" name="phonenumberedit" ng-model="phonenumberedit" class="form-control input-lg1" placeholder="Phone">
										</div>
									</div>
									<div class="col-xs-4 pl-5">
										<div class="form-group">
											<a id="Add" value="Add" name="Add" ng-click="addFamilyResidentialLandlineRow1()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
										</div>
									</div>
								</div>
								<div class="row" ng-repeat="item in getfamilyresidentiallandline">
									<div class="col-md-2">
										<div class="form-group">
											<label>{{item.landlinePhoneNumber}}</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<a class="btn btn-danger btn-sm" ng-click="removeFamilyResidentialLandlineRow1(item.landlinePhoneNumber)"/><span class="glyphicon glyphicon-remove"></span></a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="padding-bottom: 15px;" class="panel panel-default">
							<div style="padding: 10px 0px 0px 0px;" class="panel-body text-center">
								<div class="panel-heading1">
									<div class="row">
										<div class="col-xs-6" style="font-size:17px;">Work Detail	</div>
										<div class="col-xs-6">												
											<input id="workdetailedit" name="workdetailedit" ng-model="workdetail" type="checkbox" ng-click="workdetailsameasmember(<%= session.getAttribute("memberid")%>)"/>
											<label for="workdetailedit">Same As Member</label>												
										</div>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Occupation</label>
											<input type="text" id="occupationedit" name="occupationedit" ng-model="occupationedit" class="form-control input-lg1" placeholder="Occupation" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Designation</label>
											<input type="text" id="designationedit" name="designationedit" ng-model="designationedit" class="form-control input-lg1" placeholder="Designation" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Company Name</label>
											<input type="text" id="companynameedit" name="companynameedit" ng-model="companynameedit" class="form-control input-lg1" placeholder="Company Name" capitalize-first>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Business Nature</label>
											<input type="text" id="businessnatureedit" name="businessnatureedit" ng-model="businessnatureedit" class="form-control input-lg1" placeholder="Business Nature" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Fax Number</label>
											<input type="text" id="faxnumberedit" name="faxnumberedit" ng-model="faxnumberedit" class="form-control input-lg1" placeholder="Fax-Office">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Website</label>
											<input type="text" id="websiteedit" name="websiteedit" ng-model="websiteedit" class="form-control input-lg1" placeholder="Website">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label>Email</label>
											<input type="text" id="emailworkedit" name="emailworkedit" ng-model="emailworkedit" class="form-control input-lg1" placeholder="Email">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Address-1</label>
											<input type="text" id="address1workedit" name="address1workedit" ng-model="address1workedit" class="form-control input-lg1" placeholder="Address Line 1" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Address-2</label>
											<input type="text" id="address2workedit" name="address2workedit" ng-model="address2workedit" class="form-control input-lg1" placeholder="Address Line 2" capitalize-first>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Address-3</label>
											<input type="text" id="address3workedit" name="address3workedit" ng-model="address3workedit" class="form-control input-lg1" placeholder="Address Line 3" capitalize-first>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Country</label>
											<select id="countrynameworkedit" name="countrynameworkedit" ng-model="countrynameworkedit" ng-options="item.countryId as item.countryName for item in getcountry" ng-change="onCountryChange()" class="form-control input-lg2">
												<option value="">---Select Country---</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>State</label>
											<select id="statenameworkedit" name="statenameworkedit" ng-model="statenameworkedit" ng-options="item.stateId as item.stateName for item in getrelatedstate" class="form-control input-lg2">
												<option value="">---Select State---</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>City Name</label>
											<input type="text" id="citynameworkedit" name="citynameworkedit" ng-model="citynameworkedit" class="form-control input-lg1" placeholder="City" capitalize-first>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Pincode</label>
											<input type="text" id="pincodeworkedit" name="pincodeworkedit" ng-model="pincodeworkedit" class="form-control input-lg1" placeholder="Pincode" disallow-spaces>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Mobile Number</label>
											<input type="text" id="mobilenumberworkedit" name="mobilenumberworkedit" ng-model="mobilenumberworkedit" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-8 pr-5">
										<div class="form-group">
											<label>Phone Number</label>
											<input type="text" id="phonenumberworkedit" name="phonenumberworkedit" ng-model="phonenumberworkedit" class="form-control input-lg1" placeholder="phone">
										</div>
									</div>
									<div class="col-xs-4 pl-5">
										<div class="form-group">
											<a id="Add" value="Add" name="Add" ng-click="addFamilyWorkLandlineRow1()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
										</div>
									</div>
								</div>
								<div class="row" ng-repeat="item in getfamilyworklandline">
									<div class="col-md-2">
										<div class="form-group">
											<label>{{item.landlinePhoneNumber}}</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<a class="btn btn-danger btn-sm" ng-click="removeFamilyWorkLandlineRow1(item.landlinePhoneNumber)"/><span class="glyphicon glyphicon-remove"></span></a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="padding-bottom: 15px;" class="panel panel-default">
							<div style="padding: 5px 0px 0px 0px;" class="panel-body text-center">
								<div class="panel-heading1" style="font-size: 18px;"> ADDRESS FOR COMMUNICATION<font color="red" size="3">*</font></div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<label class="radio-inline">
											<input type="radio" id="communicationedit" name="communicationedit" ng-model="communicationedit" ng-value="'work'" ng-click="checkcommunicationedit()">Work
										</label>
										<label class="radio-inline">
											<input type="radio" id="communicationedit" name="communicationedit" ng-model="communicationedit" ng-value="'residential'" ng-click="checkcommunicationedit()">Residential
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="btn-group btn-group-justified" role="group" aria-label="group button">
							<div class="btn-group" role="group">
								<input type="submit" ng-click="editspousedetail(membersfamilyid,2)" class="btn btn-success" value="Edit">
							</div>
							<div class="btn-group" role="group">
								<button type="button" class="btn btn-primary" data-dismiss="modal"  role="button">Close</button>
							</div>
						</div>
					</div>						
					</div>
				</div>
			</div>
		</div>		
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker,#datepicker1").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true,
					value: new Date()
		         });
		         $("#datepicker2").kendoDatePicker({
			    	format: "dd/MM/yyyy",
					dateInput: true					
			     });
		    });
			$(".KendoDate").bind("focus", function(){
	   			$(this).data("kendoDatePicker").open(); 
			});			
		</script>	
		<script>
			
			jQuery(function($) {
				 function readURL(input) {
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				            	if ($('#target').data('Jcrop')) {
								    $('#target').data('Jcrop').destroy();
								    $('#target').removeAttr('style');
								}
				                $('#target').attr('src', e.target.result);
				                
				                $('#target').Jcrop({
				                	aspectRatio: 1 / 1,
				                	boxWidth: 650,   
				                    boxHeight: 400,
				                    setSelect:   [ 100, 100, 350, 350 ],							                    
				    				onSelect : setCoordinates
				    			});
				            }		            
				            reader.readAsDataURL(input.files[0]);
				        }
				    }				    
				    $("#imgInp").change(function(){
				        readURL(this);
				    });			
			});
			jQuery(function($) {
				 function readURL(input) {
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				            	if ($('#target1').data('Jcrop')) {
								    $('#target1').data('Jcrop').destroy();
								    $('#target1').removeAttr('style');
								}
				                $('#target1').attr('src', e.target.result);
				                
				                $('#target1').Jcrop({
				                	onSelect : setCoordinates1,
				                	aspectRatio: 1 / 1,
				                	boxWidth: 650,   
				                    boxHeight: 400,
				                    setSelect:   [ 50, 50, 350, 350 ]
				    			});
				            }		            
				            reader.readAsDataURL(input.files[0]);
				        }
				    }				    
				    $("#imgInp1").change(function(){
				        readURL(this);
				    });			
			});
			jQuery(function($) {
				 function readURL(input) {
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				            	if ($('#target2').data('Jcrop')) {
								    $('#target2').data('Jcrop').destroy();
								    $('#target2').removeAttr('style');
								}
				                $('#target2').attr('src', e.target.result);
				                
				                $('#target2').Jcrop({
				                	aspectRatio: 1 / 1,	
				                	boxWidth: 650,   
				                    boxHeight: 400,
				                    setSelect: [ 100, 100, 350, 350 ],
				    				onSelect : setCoordinates2
				    			});
				            }		            
				            reader.readAsDataURL(input.files[0]);
				        }
				    }	    
				    $("#imgInp2").change(function(){
				        readURL(this);
				    });			    
					
			});
			
				
			function setCoordinates(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm.x.value = Math.round(c.x);
				document.myForm.y.value = Math.round(c.y);
				document.myForm.w.value = Math.round(c.w);
				document.myForm.h.value = Math.round(c.h);
			};
			function setCoordinates1(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm1.x.value = Math.round(c.x);
				document.myForm1.y.value = Math.round(c.y);
				document.myForm1.w.value = Math.round(c.w);
				document.myForm1.h.value = Math.round(c.h);
			};
			function setCoordinates2(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm2.x.value = Math.round(c.x);
				document.myForm2.y.value = Math.round(c.y);
				document.myForm2.w.value = Math.round(c.w);
				document.myForm2.h.value = Math.round(c.h);
			};			
			function checkCoordinates() {
				if (document.myForm.x.value == "" || document.myForm.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
			function checkCoordinates1() {
				if (document.myForm1.x.value == "" || document.myForm1.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
			function checkCoordinates2() {
				if (document.myForm2.x.value == "" || document.myForm2.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
					
		</script>
		
	</body>
</html>