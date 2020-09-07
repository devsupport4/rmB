<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>  Events | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="icon" href="<%=request.getContextPath()%>/resources/admin/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/events.js"></script>
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
	<body class="homepage" ng-app="rcbs" ng-controller="frontEventCtrl" ng-cloak>
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Events &nbsp; </h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="club-news">
							<ul class="nav nav-tabs">
							  <li class="active"><a data-toggle="tab" href="#Upcoming">Upcoming Events</a></li>
							  <li><a data-toggle="tab" href="#Past">Past Events</a></li>
							</ul>
							<div class="tab-content">
											  <div id="Upcoming" class="tab-pane fade in active">
											   	<div style="height: auto;" class="page-content club-news mdl-card mdl-shadow--2dp demo-card-wide  mdl-js-ripple-effect"ng-repeat="item in events | orderBy : 'eventDate'" ng-if="item.eventDate >= todaydate">
													<span class="mdl-ripple"></span>
													<div class="mdl-card__supporting-text">
														<h4> <a href="event_detail?id={{item.eventId}}" style="color:blue;"> {{item.eventTitle}} </a></h4>
														<span> Event Dt.: {{item.eventDate | date : 'dd/MM/yyyy'}}</span>
														<p class="comments">@{{item.eventVenue}}</p>
														
														<p><strong><a href="event_detail?id={{item.eventId}}" style="color:black" ng-bind-html="item.eventDescription | to_trusted"> </a></strong></p>
													</div>
												</div>
											  </div>
											  <div id="Past" class="tab-pane fade">
												    <div style="height: auto;" class="page-content club-news mdl-card mdl-shadow--2dp demo-card-wide  mdl-js-ripple-effect"ng-repeat="item in events | orderBy : 'eventDate'" ng-if="item.eventDate < todaydate">
														<span class="mdl-ripple"></span>
														<div class="mdl-card__supporting-text">
															<h4> <a href="event_detail?id={{item.eventId}}" style="color:blue;"> {{item.eventTitle}} </a></h4>
															<span> Event Dt.: {{item.eventDate | date : 'dd/MM/yyyy'}}</span>
															<p class="comments">@{{item.eventVenue}}</p>
															
															<p><strong><a href="event_detail?id={{item.eventId}}" style="color:black" ng-bind-html="item.eventDescription | to_trusted"> </a></strong></p>
														</div>
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