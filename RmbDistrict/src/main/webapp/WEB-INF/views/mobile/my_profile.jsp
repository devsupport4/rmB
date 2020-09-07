<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> My Profile | Rotary Means Business Fellowship Bangalore  </title>				
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front1/images/favicon.png">
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/font-awesome-4.7.0/css/font-awesome.min.css"> 
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/css/profile.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/css/OwlCarousel.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/css/OwlCarousel.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front1/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/member_profile.js"></script>
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
		    width: 100%;
		    height: 100%; 
		}
	</style>
	<body class="homepage" ng-app="rcbs" ng-controller="memberProfileCtrl" ng-cloak ng-init="getMemberDetailById(<%= session.getAttribute("loginid") %>)">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> My Profile <a class="user-company" href="<%=request.getContextPath() %>/manage_my_profile" style="color:#005daa;position: absolute; right:10px; "> <i class="fa fa-pencil-square-o" aria-hidden="true"></i> EDIT </a> </h2>
					
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="profile">
							<div class="page-content">
								<div class="jumbo"></div>
								<div class="container icons">
									<div class="big-icon">
										
										<img src="{{getmemberdetail.memberProfilePicture}}" class="img-responsive " alt="" ng-if="getmemberdetail.memberProfilePicture != ''" />
										<img class="img-responsive " src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt="" ng-if="getmemberdetail.memberProfilePicture == ''">
									</div>
								</div>								
								<div class="details">
									<h3> Rtn. {{firstname}} {{firstname}} </h3>
									<p class="mb-10"> {{businesscategoryname}}</p>
									<p class="mb-0"><i class="fa fa-mobile"></i> +91 {{getmemberdetail.memberMobileNumber}} </p>
									<p><i class="fa fa-envelope-o"></i> {{getmemberdetail.memberEmail}} </p>
								</div>
								<div class="mdl-card  demo-card-wide">
									<div class="panel-group d-accordion">
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#aboutus">
												<h4 class="panel-title"> Basic Info <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="aboutus" class="panel-collapse collapse in">
												<ul class="list-group">
													<li class="list-group-item"><i class="fa fa-birthday-cake"></i><span class="weight-600">Birth Date:</span> {{getmemberdetail.memberDateOfBirth | date: 'dd-MMM'}}</li>
													<li class="list-group-item"><i class="fa fa-heart"></i><span class="weight-600"> Marriage Anniversary: </span> {{getmemberdetail.memberAnniversaryDate | date: 'dd-MMM'}}</li>
													<li class="list-group-item"><i class="fa fa-tint"></i><span class="weight-600">  Blood group: </span> {{getmemberdetail.memberBloodGroup}}</li>
													<li class="list-group-item"><i class="fa fa-book" aria-hidden="true"></i><span class="weight-600"> Qualification: </span> {{getmemberdetail.memberOccupation}}</li>
												</ul>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#whoweare">
												<h4 class="panel-title"> WORK DETAIL <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="whoweare" class="panel-collapse collapse">
												<ul class="list-group">
													<li class="list-group-item"><span class="weight-600"> {{getmemberdetail.memberCompanyName}} </span></li>
													<li class="list-group-item"><i class="fa fa-building-o"></i>{{getmemberdetail.memberCompanyAddress1}}, {{getmemberdetail.memberCompanyAddress2}}, {{getmemberdetail.memberCompanyAddress3}} {{getmemberdetail.memberCompanyCityName}}-{{getmemberdetail.memberCompanyPincode}}</li>
													<li class="list-group-item"><i class="fa fa-envelope-o"></i><a href="{{getmemberdetail.memberCompanyEmail}}"> {{getmemberdetail.memberCompanyEmail}} </a></li>
													<li class="list-group-item"><i class="fa fa-mobile"></i>+91 {{getmemberdetail.memberCompanyMobileNumber}}</li>
													<li class="list-group-item"><i class="fa fa-globe">  </i><a href="http://{{getmemberdetail.memberWebsiteName}}"> {{getmemberdetail.memberWebsiteName}} </a></li>
												</ul>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#contactus">
												<h4 class="panel-title"> RESIDENCE <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="contactus" class="panel-collapse collapse">
												<ul class="list-group">
													<li class="list-group-item"><i class="fa fa-building-o"></i>{{getmemberdetail.memberAddress1}}, {{getmemberdetail.memberAddress2}}, {{getmemberdetail.memberAddress3}} {{getmemberdetail.memberCityName}}-{{getmemberdetail.memberPincode}}</li>
													<li class="list-group-item"><i class="fa fa-envelope-o"></i><a href="{{getmemberdetail.memberEmail}}"> {{getmemberdetail.memberEmail}}</a></li>
													<li class="list-group-item"><i class="fa fa-mobile"></i>+91 {{getmemberdetail.memberMobileNumber}}</li>
												</ul>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#family-info">
												<h4 class="panel-title"> FAMILY INFORMATION <i class="fa fa-chevron-up pull-right"></i></h4>
											</div>
											<div id="family-info" class="panel-collapse collapse family-info">
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
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</main>
		</div>
		<script  src="<%= request.getContextPath() %>/resources/front1/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>