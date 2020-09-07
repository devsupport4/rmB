<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Members Directory |  Rotary Means Business Fellowship Bangalore  </title>
		<link href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css" rel="stylesheet">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/members_directory.js"></script>
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
		.modal-backdrop {
		    position: fixed;
		    top: 0;
		    right: 0;
		    bottom: 0;
		    left: 0;
		    z-index: auto !important;
		    background-color: #000;
		}
		.texter{
			display: block;
  			width: 200px;
 		 	overflow: hidden;
 		 	white-space: nowrap;
  			text-overflow: ellipsis;
		}
	</style>
	<body class="homepage" ng-app="rcbs" ng-controller="membersDirectoryCtrl" ng-init="getAllMemberDirectory()" ng-cloak>
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18">Members Directory</h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="members-directory">
							<div class="page-content">															
								<div class="panel panel-default year-box">
									<div class="panel-body">
										<div class="col-sm-12 col-xs-12 wow fadeInDown animated" align="right">
											<div class="input-group">
												<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search Member"/>
												<a class="input-group-addon" style="cursor: pointer;">
													<i class="fa fa-search"></i>
												</a>
											</div><BR>
										</div>
									</div>
								</div>
								<div class="mdl-card  demo-card-wide committee directory">
									<div class="panel-group d-accordion">
										<div class="panel panel-default" ng-repeat="item in getMemberdirectory | filter:search" >
										<%-- <%if(session.getAttribute("memberid") != null) {%>
											<a href="#" ng-click="redirectToMemberProfile(item.memberId)">
										<%}%> --%>
											<div class="panel-heading">
												
												<img class="img-responsive avatar" src="{{item.memberProfilePicture}}" onerror="this.onerror=null; this.src='<%=request.getContextPath() %>/resources/admin/images/UserImg.png'" alt="" ng-if="item.memberProfilePicture != ''"/>
												<img class="img-responsive avatar" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt="" ng-if="item.memberProfilePicture == ''"/>
												<h4 class = "panel-title texter"> {{item.memberFirstName}} {{item.memberLastName}} </h4>
												
												
												
												<div class="form-group">
													<%if(session.getAttribute("memberid") != null && session.getAttribute("memberstatus").equals("y")) {%>
														<i class="fa fa-chevron-right pull-right" ng-click="redirectToMemberProfile(item.memberId)"></i>
													<%}	else {%>	
													<i class="fa fa fa-envelope pull-right" ng-click="getMemberDetailById(item.memberId)" data-toggle="modal" data-target="#myModal"></i>
													<%}%>
												</div>									
												
											</div>
											<%-- <%if(session.getAttribute("memberid") != null) {%>
												</a>
											<%}%> --%>
											
										</div>									
									</div>
								</div>								
															
							</div>
						</section>					
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog" style="margin-top: 60px;">
								<div class="modal-content">
									<div class="modal-header">						
										<h4 class="modal-title">Send message to {{firstname}} {{lastname}} </h4>
									</div>
									<form ng-submit="sendMessage(memberid)">
									<div class="modal-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>First Name<font color="red" size="3">*</font></label>
													<input type="text" class="form-control" id="userfirstname" ng-model="userfirstname" placeholder="First Name" ng-change="setFlag()">
													<p ng-show="errorFirstName == 1" style="color: red; font-size: 14px;">{{msgFirstName}}</p>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Last Name<font color="red" size="3">*</font></label>
													<input type="text" class="form-control" id="userlastname" ng-model="userlastname" placeholder="Last Name" ng-change="setFlag()">
													<p ng-show="errorLastName == 1" style="color: red; font-size: 14px;">{{msgLastName}}</p>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Email<font color="red" size="3">*</font></label>
													<input type="email" class="form-control" id="useremail" ng-model="useremail" placeholder="Email" ng-change="setFlag()">
													<p ng-show="errorEmail == 1" style="color: red; font-size: 14px;">{{msgEmail}}</p>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Mobile No.<font color="red" size="3">*</font></label>
													<input type="number" class="form-control" id="usermobileno" ng-model="usermobileno" placeholder="Mobile number" ng-change="setFlag()">
													<p ng-show="errorMobileNo == 1" style="color: red; font-size: 14px;">{{msgMobileNo}}</p>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Message<font color="red" size="3">*</font></label>
													<textarea row="3" class="form-control" id="usermessage" ng-model="usermessage" placeholder="Your message" ng-change="setFlag()"></textarea>
													<p ng-show="errorMessage == 1" style="color: red; font-size: 14px;">{{msgMessage}}</p>
												</div>
											</div>							
										</div>
									</div>
									<div class="modal-footer">
										<div class="row">
											<div class="col-md-6 text-left">
												<p ng-show="submitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
												<p ng-show="submitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>
											</div>
											<div class="col-md-6">								
												<button type="submit" class="btn btn-warning" ng-disabled="spin == 1"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											</div>
										</div>						
									</div>
									</form>
								</div>
							</div>
						</div>					
						
					</div>
				</div>
			</main>
		</div>		
	</body>
</html>