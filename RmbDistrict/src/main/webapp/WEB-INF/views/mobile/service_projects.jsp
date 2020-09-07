<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Service Projects  | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/front_sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/service_project.js"></script>
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
	<body class="homepage" ng-app="rcbs" ng-controller="serviceProjectCtrl">		
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Service Projects </h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="service-projects">					
							<div class="mdl-grid">
								<div class="mdl-cell mdl-cell--6-col mdl-cell--8-col-tablet" ng-repeat="item in serviceprojectwithimage">
									<a href="#" ng-click="redirecttoserviceprojectdetail(item.serviceProjectId)">
										<div class="mdl-card mdl-shadow--2dp mdl-card-theme content mdl-js-ripple-effect">
											<span class="mdl-ripple"></span>
											<div class="mdl-card__title">
												<img src="{{item.image}}" class="img-responsive" title="{{item.imageTitle}}">																								
											</div>
											<div class="heading">
												<div class="card-block px-3">													
													<p class="card-text text" style="margin: 0px 0 5px; text-align:center;"><a href="#" style="color:blue;"  ng-click="redirecttoserviceprojectdetail(item.serviceProjectId)">{{item.serviceProjectTitle}}</a></p>	
													<p class="card-text" style="margin: 0px 0 5px; text-align:center;">{{item.serviceProjectSubtitle}}</p>
																									
													<p style=" text-align:right; padding-right: 20px;"><a href="#" style="font-size: 50px; color:green;" class="next round" ng-click="redirecttoserviceprojectdetail(item.serviceProjectId)">&#8250;</a></p>
												</div>
											</div>											
										</div>
									</a>
								</div>								
							</div>
						</section>
					</div>
				</div>
			</main>
		</div>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>