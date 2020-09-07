<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Event Registration  | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/mobile/images/favicon.ico">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.3.0/material.min.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>		
		<script  src="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.3.0/material.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>			
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/registration.js"></script>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-153537496-1"></script>
        <script>
           window.dataLayer = window.dataLayer || [];
           function gtag(){dataLayer.push(arguments);}
           gtag('js', new Date());
           gtag('config', 'UA-153537496-1');
        </script>

		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">		
	</head>
	<style>	
		form {
	    	display: block;
	    	margin-top: 0em;
	    	box-sizing: border-box;
		}
		* {
		    -webkit-box-sizing: border-box;
		    -moz-box-sizing: border-box;
		    box-sizing: border-box;
		}
		.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
		    display: block;
		    max-width: 100%;
		    height: auto;
		}	
		img {
	    	border: 0;
		}
		a {
			text-decoration: none;
		}
	
	</style>
	<body class="homepage"  ng-app="rcbs" ng-controller="registrationCtrl" ng-init="getLogedinMemberDetails(<%= session.getAttribute("memberid")%>,<%= request.getParameter("eid")%>)">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18">Event Registration </h2>
				</div>
				<div class = "mdl-grid">
					<div class="login mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone graybox">
						<div class="rcbs-logo">
							<img src="<%= request.getContextPath() %>/resources/front/mobile/images/405X75.png"/>
						</div>
						<div class="mdl-card mdl-shadow--6dp">
							<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
								<h2 class="mdl-card__title-text">Event Registration </h2>
							</div>
							<div class="mdl-card__supporting-text">
								<form ng-submit="registration(<%= request.getParameter("eid")%>, <%= session.getAttribute("memberid")%>,'<%= request.getParameter("membertype")%>')" >
									<div class="mdl-textfield mdl-js-textfield">
										<input type="text" id="rtcmpadd" name="rtcmpadd" ng-model="rtcmpadd" class="mdl-textfield__input" placeholder="Company Name" ng-change="setFlag()" autofocus>
										<label class="mdl-textfield__label" for="rtcmpadd">Company Name</label>
										<p ng-show="errorCompany == 1" style="color: red; font-size: 14px;">{{msgCompany}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="First Name" class="mdl-textfield__input"  ng-change="setFlag()" style="margin-top: 0px;">
										<label class="mdl-textfield__label" for="firstnameadd">First Name<font color="red" size="3">*</font></label>
										<p ng-show="errorFirstName == 1" style="color: red; font-size: 14px;">{{msgFirstName}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input type="text" id="lastnameadd" name="lastnameadd" ng-model="lastnameadd" placeholder="Last Name" class="mdl-textfield__input"  ng-change="setFlag()" style="margin-top: 0px;">
										<label class="mdl-textfield__label" for="lastnameadd">Last Name</label>
										<p ng-show="errorLastName == 1" style="color: red; font-size: 14px;">{{msgLastName}}</p>
									</div>
									<div  class="mdl-textfield mdl-js-textfield">
										<select style="width: 100%;height: 35px;" id="genderadd" name="genderadd" ng-model="genderadd" class="mdl-selectfield__select"  ng-change="setFlag()">
											<option value="">Gender</option>
											<option value="M">Male</option>
											<option value="F">Female</option>
											<option value="O">Other</option>
										</select>
										<p ng-show="errorGender == 1" style="color: red; font-size: 14px;">{{msgGender}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input  type="email" id="emailadd" name="emailadd" ng-model="emailadd" placeholder="Email" class="mdl-textfield__input" style="margin-top: 0px;"  ng-change="setFlag()">
										<label class="mdl-textfield__label" for="emailadd">Email</label>
										<p ng-show="errorEmail == 1" style="color: red; font-size: 14px;">{{msgEmail}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input type="text" id="mobilenoadd" name="mobilenoadd" ng-model="mobilenoadd" placeholder="Mobile No" class="mdl-textfield__input" style="margin-top: 0px;"  ng-change="setFlag()">
										<label class="mdl-textfield__label" for="mobilenoadd">Mobile No</label>
										<p ng-show="errorMobileno == 1" style="color: red; font-size: 14px;">{{msgMobileno}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input type="text" id="rtwebsiteadd" name="rtwebsiteadd" ng-model="rtwebsiteadd" placeholder="Company Website" class="mdl-textfield__input" style="margin-top: 0px;"  ng-change="setFlag()">
										<label class="mdl-textfield__label" for="rtwebsiteadd">Company Website</label>
										<p ng-show="errorMobileno == 1" style="color: red; font-size: 14px;">{{msgMobileno}}</p>
									</div>
									<div  class="mdl-textfield mdl-js-textfield">
										<select style="width: 100%;height: 35px;" id="countryidadd" name="countryidadd" ng-model="countryidadd" ng-init="countryidadd=1" class="mdl-selectfield__select" ng-change="setFlag()">
											<option value="">Select Country</option>
											<option ng-repeat="c in getcountry" value="{{c.countryId}}">{{c.countryName}}</option>
										</select>
										
									</div>
									<div class="mdl-textfield mdl-js-textfield" ng-show="'<%= request.getParameter("membertype")%>' !='RMBFB Member' && !<%= session.getAttribute("memberid")%>">
										<input type="password" id="password" name="password" ng-model="password" placeholder="Password" class="mdl-textfield__input" style="margin-top: 0px;" ng-change="setFlag()">
										<label class="mdl-textfield__label" for="password">Password</label>
										<p ng-show="errorPassword == 1" style="color: red; font-size: 14px;">{{msgPassword}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield" ng-show="'<%= request.getParameter("membertype")%>' !='RMBFB Member' && !<%= session.getAttribute("memberid")%>">
										<input type="password" id="cpassword" name="cpassword" ng-model="cpassword" placeholder="Confirm Password" class="mdl-textfield__input" style="margin-top: 0px;" ng-change="setFlag()">
										<label class="mdl-textfield__label" for="cpassword">Confirm Password</label>
										<p ng-show="errorCPassword == 1" style="color: red; font-size: 14px;">{{msgCPassword}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield" ng-show="'<%= request.getParameter("membertype")%>' !='Others'">
										<input type="text" id="rtmemberadd" name="rtmemberadd" ng-model="rtmemberadd" placeholder="Rotary Member ID" class="mdl-textfield__input" style="margin-top: 0px;" ng-change="setFlag()">
										<label class="mdl-textfield__label" for="rtmemberadd">Rotary Member ID</label>
									</div>
									<div class="mdl-textfield mdl-js-textfield" ng-show="'<%= request.getParameter("membertype")%>' !='Others'">
										<input type="text" id="rtclubadd" name="rtclubadd" ng-model="rtclubadd" placeholder="Rotary Club Name"  class="mdl-textfield__input" style="margin-top: 0px;" ng-change="setFlag()">
										<label class="mdl-textfield__label" for="rtclubadd">Rotary Club Name</label>
									</div>
									<div class="mdl-textfield mdl-js-textfield" ng-show="'<%= request.getParameter("membertype")%>' !='Others'">
										<input type="text" id="rtchapadd" name="rtchapadd" ng-model="rtchapadd" placeholder="RMB Chapter Name" class="mdl-textfield__input" style="margin-top: 0px;" ng-change="setFlag()">
										<label class="mdl-textfield__label" for="rtchapadd">RMB Chapter Name</label>
									</div>
									
									<div class="mdl-textfield mdl-js-textfield">
										<textarea class="mdl-textfield__input" type="text" rows= "3" id="shortdescadd" name="shortdescadd" ng-model="shortdescadd" placeholder="Short Description of your Company" ></textarea>
										<label class="mdl-textfield__label" for="shortdescadd">Short Description of your Company</label>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<textarea class="mdl-textfield__input" type="text" rows= "3" id="businessclsadd" name="businessclsadd" ng-model="businessclsadd" placeholder="Business Classification" ></textarea>
										<label class="mdl-textfield__label" for="businessclsadd">Business Classification</label>
									</div>
									
									<p ng-show="userSubmitError == 1" style="color: red; font-size: 15px;"><b>{{msgError}}</b></p>
									<p ng-show="userSubmitError1 == 1" style="color: red; font-size: 15px;"><b>{{msgError}}</b></p>
									
									<div class="mdl-textfield mdl-js-textfield">
										<div align="center">
											<!-- <button type="submit" class="btn btn-primary btn-lg btn-block" style="width: 90%; margin-top: 40px; ">Submit</button> -->
											<button ng-show="userSubmitError == 1"  ng-click="redirectToLoginPage(<%= request.getParameter("eid")%>, '<%= request.getParameter("membertype")%>')" class="btn btn-success" > Login</button>
											<button ng-show="userSubmitError != 1" type="submit" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
										</div>										
									</div>
								</form>
							</div>							
						</div>
					</div>
				</div>
			</main>
		</div>		
	</body>
</html>