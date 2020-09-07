<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>  News   | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
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
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/front_news.js"></script>
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
	<body class="homepage" ng-app="rcbs" ng-controller="frontNewsCtrl" ng-init="getCurrentDefaultYear()">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> News RMBF &nbsp; <span class="pull-right"></span>  </h2>
				</div>
				<div class="page-content">
				
					<div class="panel panel-default year-box">
						<div class="panel-body" style="padding:15px 15px 0px 15px;">
							<div class="form-group" style="margin-bottom:0px;">
								<select class="form-control input-lg" id="rotaryyearid" name="rotaryyearid" ng-model="rotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear" ng-change="getNewsDetailByRotaryYear()">
									<option value="" class="">Select Year</option>
								</select>
							</div>
						</div>
					</div>					
					<div class="mdl-layout mdl-js-layout">
						<section class="club-news">
							<div class="page-content club-news mdl-card mdl-shadow--2dp demo-card-wide  mdl-js-ripple-effect " ng-repeat="item in getcurrentyearnews">
								<span class="mdl-ripple"></span>
								<div class="mdl-card__title" style="padding:3px;" >
									<a href="news_detail?id={{item.newsId}}&projectid={{item.serviceProjectId}}">
										<img src="{{item.image}}" style="width: 100%;" />
									</a>
								</div>
								<div class="mdl-card__supporting-text">
									<h4><a href="news_detail?id={{item.newsId}}&projectid={{item.serviceProjectId}}" style="color: #005DAA;">{{item.newsTitle}}</a> </h4>
									<span>{{item.newsDate | date : 'dd/MM/yyyy'}}</span>									
									<p>{{item.newsDesc | cut:true:35:' ...'}}&nbsp;&nbsp;&nbsp;&nbsp;<a href="news_detail?id={{item.newsId}}&projectid={{item.serviceProjectId}}"/></p>
									<p style=" text-align:right; padding-right: 20px;"><a href="news_detail?id={{item.newsId}}&projectid={{item.serviceProjectId}}" style="font-size: 50px; color:green;" class="next round" >&#8250;</a></p>
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