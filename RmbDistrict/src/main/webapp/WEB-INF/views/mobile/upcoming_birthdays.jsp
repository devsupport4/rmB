<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Upcoming Birthdays | Rotary Means Business Fellowship Bangalore  </title>
		<link href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css" rel="stylesheet">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link href="<%=request.getContextPath() %>/resources/front/css/responsive.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700" rel="stylesheet">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/profile.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">		
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/main.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/wow.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.prettyPhoto.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.isotope.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/home.js"></script>
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
	<body class="homepage" ng-app="rcbs" ng-controller="homeCtrl" ng-init="getCurrentDefaultYear()">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18">Birthdays</h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="birthdays-anniversaries">
							<div class="page-content club-detail">
								
								<div class="mdl-card mdl-shadow--2dp demo-card-wide">
									<div class='mdl-tabs mdl-js-tabs mdl-js-ripple-effect'>
									
										<div class='mdl-tabs__tab-bar'>
											<a href='#attributes-panel' class='mdl-tabs__tab is-active'><i class="fa fa-birthday-cake"></i>BIRTHDAYS</a>											
										</div>
										<div class='mdl-tabs__panel is-active' id='attributes-panel'>
											<div class="row">
												<div class="col-md-12" ng-init="getBirthdaysAndAnniversariesByDate()">
													<ul class="list-group birthdays">
														<li class="list-group-item" ng-repeat="item in getfirstfourbirthdaysbydate" ng-show = "$index < 4">
															<img class="img-responsive avatar" src="{{item.memberProfilePicture}}" ng-if="item.memberProfilePicture != ''">
															<img class="img-responsive avatar" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" ng-if="item.memberProfilePicture == ''">
															<h4 class = "panel-title">  {{item.memberFirstName}} {{item.memberLastName}}</h4>
															<span> {{item.memberDateOfBirth | date : "EEEE"}}, {{item.memberDateOfBirth | date : "dd"}}, {{item.memberDateOfBirth | date : "MMM"}} </span>
														</li>
													</ul>
												</div>
											</div>
										</div>
										<%-- <div class='mdl-tabs__panel' id='skills-panel'>
											<div class="row">
												<div class="col-md-12">
													<ul class="list-group birthdays" >
														<li class="list-group-item " ng-repeat="item in getallanniversaries">
															<img class="img-responsive avatar" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png">
															<h4 class = "panel-title">{{item.memberFirstName}} {{item.memberLastName}} & {{item.memberFamilyFirstName}} {{item.memberFamilyLastName}} </h4>
															<span> {{item.memberAnniversaryDate | date : "EEEE"}}, {{item.memberAnniversaryDate | date : "dd"}}, {{item.memberAnniversaryDate | date : "MMM"}} </span>
														</li>
													</ul>
												</div>
											</div>
										</div> --%>
									</div>
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