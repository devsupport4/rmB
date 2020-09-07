<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Member Reference | Rotary Means Business Fellowship Bangalore  </title>				
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
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/member_reference.js"></script>
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
	<style>
		.mdl-card {		    
		    height: auto;
		}
		.mt-0 {
			margin-top:0px;
		}
	</style>
	<body class="homepage" ng-app="rcbs" ng-controller="memberReferralCtrl" ng-init="getMembersReferencesById('<%= session.getAttribute("loginid") %>','<%= session.getAttribute("fromdate") %>','<%= session.getAttribute("todate") %>')" ng-cloak>
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/activities" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Member's References</h2>
					<a href="<%=request.getContextPath() %>/referrals" class="addicn"><i class="fa fa-plus-circle pull-right icnsize icnright" aria-hidden="true"></i></a>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="profile">
							<div class="row row-margin">												
								<div class="col-xs-5" style="padding:5px;">
									<input class="KendoDate" id="datepicker" ng-model="fromdate" title="Select From Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
								</div>
								<div class="col-xs-5" style="padding:5px;">
									<input class="KendoDate" id="datepicker1" ng-model="todate" title="Select To Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
								</div>
								<div class="col-xs-2" style="padding:5px;">
									<button type="submit" class="btn btn-primary"  style="margin-top: 0px; background-color: #005daa;" ng-click="getReferencesByDate(<%= session.getAttribute("loginid") %>)"> <i class="fa fa-search"></i></button>													
								</div>																																	
							</div>
							<div class="page-content">							
								<div class="mdl-card  demo-card-wide">
									<div class="panel-group d-accordion">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div class="row row-margin">
													<div class="col-xs-4 pl-0">
														<h4 class="panel-title linelnt" style="font-size:14px"><i class="fa fa-calendar mt-0" aria-hidden="true"></i></h4>
														<h4 class="panel-title linelnt" style="font-size:14px">I/O</h4>
													</div>
													<div class="col-xs-8 pl-0">
														<h4 class="panel-title linelnt" style="font-size:13px">To</h4>
														<h4 class="panel-title linelnt" style="font-size:13px">Referral</h4>
													</div>
												</div>
												
											</div>											
										</div>
										<div ng-repeat="item in getMemberReferences">
											<div class="panel panel-default" >
												<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#whoweare{{item.memberReferralId}}">
												
													<div class="row row-margin">
														<div class="col-xs-4 pl-0">
															<h4 class="panel-title linelnt" style="font-size:14px">{{item.referDate}}</h4>
															<h4 class="panel-title linelnt" style="font-size:13px">{{item.referralType}}</h4>
														</div>
														<div class="col-xs-7 pl-0">
															<h4 class="panel-title linelnt" style="font-size:13px">{{item.memberFirstName}} {{item.memberLastName}}</h4>
															<h4 class="panel-title linelnt" style="font-size:13px">{{item.referralName}}</h4>
														</div>
														<div class="col-xs-1 pl-0">														
															<h4 class="panel-title linelnt" style="font-size:17px"><i class="fa fa-chevron-down" aria-hidden="true"></i></i></h4>
														</div>
													</div>
												</div>
												<div id="whoweare{{item.memberReferralId}}" class="panel-collapse collapse">
													<ul class="list-group">													
														<li class="list-group-item"><i class="fa fa-envelope-o"></i>{{item.email}}</li>
														<li class="list-group-item"><i class="fa fa-mobile"></i>+91 {{item.contactNumber}}</li>
														<li class="list-group-item"><i class="fa fa-check-circle-o" aria-hidden="true"></i>{{item.referralStatus1}}&nbsp;&nbsp; &nbsp;{{item.referralStatus2}}</li>
														<li class="list-group-item"><i class="fa fa-map-marker" aria-hidden="true"></i>	{{item.address}}</li>
														<li class="list-group-item"><i class="fa fa-commenting-o" aria-hidden="true"></i>{{item.comments}} </li>
													</ul>
												</div>
											</div>											
										</div>
										
										<%-- <div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#family-info">
												<h4 class="panel-title"> FAMILY INFORMATION <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="family-info" class="panel-collapse collapse family-info">
												<ul class="list-group">
													<li class="list-group-item" ng-repeat="item in getspousedata">
														<a href="#" ng-click="redirecttomemberfamilyinfo(item.membersFamilyId, <%= session.getAttribute("memberid")%>)">
															<img class="img-responsive avatar" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png">															
															<img src="{{getspousedata.memberFamilyProfilePicture}}" class="img-responsive avatar" alt="" ng-if="getspousedata.memberFamilyProfilePicture != ''">
															<img class="img-responsive avatar " src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt=""  ng-if="getspousedata.memberFamilyProfilePicture == ''">
															<h4 class = "panel-title">{{item.memberFamilyTitleName}} {{item.memberFamilyFirstName}} {{item.memberFamilyMiddleName}} {{item.memberFamilyLastName}} </h4>
															<span> {{item.memberFamilyTypeOfRelation}} </span>
														</a>
													</li>													
												</ul>
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