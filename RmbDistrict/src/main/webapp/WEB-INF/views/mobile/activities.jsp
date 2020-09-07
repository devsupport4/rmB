<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Member Activities |  Rotary Means Business Fellowship Bangalore  </title>				
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
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/activities.js"></script>
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
	</head>
	<body class="homepage" ng-app="rcbs" ng-controller="memberActivitiesCtrl" ng-init="getMemberActivitiesDetailById(<%= session.getAttribute("loginid") %>)" ng-cloak>
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Member Activities </h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="profile">
							<div class="page-content">														
								<div class="panel-heading bcgdclr">												
									<img class="img-responsive avatar avtimg avtimgs" src="{{getmemberdetail.memberProfilePicture}}" alt="" ng-if="getmemberdetail.memberProfilePicture != ''"/>
									<img class="img-responsive avatar avtimg avtimgs"  src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt="" ng-if="getmemberdetail.memberProfilePicture == ''"/>
									<h4 class = "panel-title"> {{firstname}} {{lastname}} </h4>												
									<span>Active </span>									
								</div>								
								<div class="row row-margin">												
									<div class="col-xs-5" style="padding:10px;">
										<input class="KendoDate" id="datepicker" ng-model="fromdate" title="Select From Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
									</div>
									<div class="col-xs-5" style="padding:10px;">
										<input class="KendoDate" id="datepicker1" ng-model="todate" title="Select To Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
									</div>
									<div class="col-xs-2" style="padding:10px;">
										<button type="submit" class="btn btn-primary"  style="margin-top: 0px; background-color: #005daa;" ng-click="getMemberActivitiesByDate(<%= session.getAttribute("loginid") %>)"> <i class="fa fa-search"></i></button>													
									</div>																																	
								</div>
								<div class="mdl-card  demo-card-wide wd-100">
									<div class="panel-group d-accordion">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													 
													<a href="<%= request.getContextPath() %>/member_reference">References
													<span class="amount">{{noofreference}}</span></a>
													<a href="<%=request.getContextPath() %>/referrals">
														<i class="fa fa-plus-circle icnsize pull-right" aria-hidden="true"></i>
													</a>
												</h4>
											</div>
											
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" >
												<h4 class="panel-title">
													
													<a href="<%= request.getContextPath() %>/member_business_transaction">Business
													<span class="amount">{{businessamount | number : 2}}</span></a>
													<a href="<%=request.getContextPath() %>/thankyou_slip">
														<i class="fa fa-plus-circle icnsize pull-right" aria-hidden="true"></i>
													</a> 
												</h4>
											</div>
										
										</div>
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													
													<a href="<%= request.getContextPath() %>/member_meeting">Meetings
													<span class="amount">{{noofmeeting}}</span> </a>
													<a href="<%=request.getContextPath() %>/one_to_one">
														<i class="fa fa-plus-circle icnsize pull-right" aria-hidden="true"></i>
													</a>
												</h4>
											</div>
										
										</div>										
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</main>
		</div>
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker, #datepicker1").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true					
		         });		         
		    });
			$(".KendoDate").bind("focus", function(){
	   			$(this).data("kendoDatePicker").open(); 
			});
		</script>
	</body>
</html>