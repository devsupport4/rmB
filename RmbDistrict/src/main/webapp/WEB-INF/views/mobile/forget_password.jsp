<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Forget Password  | Rotary Means Business Fellowship Bangalore  </title>
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
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>			
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>	
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/change_password.js"></script>
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
	<body class="homepage" ng-app="rcbs" ng-controller="passwordCtrl">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18">Forget Password</h2>
				</div>
				<div class = "mdl-grid">
					<div class="login mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone graybox">
						<div class="rcbs-logo">
							<img src="<%= request.getContextPath() %>/resources/front/mobile/images/405X75.png"/>
						</div>
						<div class="mdl-card mdl-shadow--6dp">
							
							<div class="mdl-card__supporting-text">								
								<form ng-submit="forgetPassword()">
									<div class="mdl-textfield mdl-js-textfield">
										<input type="email" id="email" name="email" ng-model="email" class="mdl-textfield__input" placeholder="Registered Email" autofocus>										
										<label class="mdl-textfield__label" for="email">Registered Email</label>
									</div>
									<div class="mdl-textfield mdl-js-textfield">
										<div align="center">
											<button type="submit" class="btn btn-primary btn-lg btn-block" style="width: 90%; margin-top: 40px; ">Submit</button>
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