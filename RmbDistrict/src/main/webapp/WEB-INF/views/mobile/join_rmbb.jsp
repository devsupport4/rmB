<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> JOIN RMBFB | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">	
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">	
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/mobile/images/favicon.ico">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/join_rmbv.js"></script>
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
	<body class="homepage"  ng-app="rcbs" ng-controller="joinRMBVCtrl">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> JOIN RMBFB </h2>
				</div>
				<div class = "mdl-grid">
					<div class="login mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone graybox">
						<div class="rcbs-logo">
							<img src="<%= request.getContextPath() %>/resources/front/mobile/images/405X75.png"/>
						</div>
						<div class="mdl-card mdl-shadow--6dp">
							<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
								<h2 class="mdl-card__title-text"> JOIN RMBFB </h2>
							</div>
							<div class="mdl-card__supporting-text">
								<form ng-submit="joinRmbf()">
									<div class="mdl-textfield mdl-js-textfield">
										<input class="mdl-textfield__input" id="firstname" ng-model="firstname" placeholder="First Name" type="text" ng-change="setFlag()"/>
										<label class="mdl-textfield__label" for="firstname">First Name</label>
										<p ng-show="errorFirstName == 1" style="color: red;">{{msgFirstName}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input class="mdl-textfield__input" id="lastname" ng-model="lastname" placeholder="Last Name" type="text"  ng-change="setFlag()"/>
										<label class="mdl-textfield__label" for="lastname">Last Name</label>
										<p ng-show="errorLastName == 1" style="color: red;">{{msgLastName}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<input class="mdl-textfield__input" id="mobilenumber" ng-model="mobilenumber" data-mask="9999999999" placeholder="Mobile" type="number"  ng-change="setFlag()"/>
										<label class="mdl-textfield__label" for="mobilenumber">Mobile Number</label>
										<p ng-show="errorMobile == 1" style="color: red;">{{msgMobile}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<label class="mdl-textfield__label" for="email">Email</label>
										<input class="mdl-textfield__input" id="email" ng-model="email"  placeholder=" Email" type="email"  ng-change="setFlag()"/>
										<p ng-show="errorEmail == 1" style="color: red;">{{msgEmail}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<select class="mdl-textfield__input" id="rotarian" ng-model="rotarian" ng-init="rotarian='yes'" ng-change="setFlag()">
										<option value="yes"> Yes </option>
										<option value="no"> No </option>
										</select>
										<label class="mdl-textfield__label" for="rotarian">Rotarian</label>
										<p ng-show="errorRotarian == 1" style="color: red;">{{msgRotarian}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<textarea class="mdl-textfield__input" id="message" ng-model="message" placeholder="Message" rows="3"></textarea>
										<label class="mdl-textfield__label" for="message">Message</label>
										<p ng-show="errorMessage == 1" style="color: red;">{{msgMessage}}</p>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<div align="center">
											<p ng-show="submitSuccess == 1" style="color: green;">{{msgSuccess}}</p>
											<p ng-show="submitError == 1" style="color: red;">{{msgError}}</p>
											<button type="submit" ng-disabled="spin == 1" class="btn btn-primary btn-lg btn-block" style="width: 90%; margin-top: 40px;"> <i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
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