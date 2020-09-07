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
		<title>  Event Detail  | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
			<link href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css" rel="stylesheet">	
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700" rel="stylesheet">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/event_detail.js"></script>
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
	<body class="homepage" ng-app="rcbs" ng-controller="eventDetailCtrl" ng-init="getEventDetailById(<%= request.getParameter("id")%>, <%= session.getAttribute("memberid")%>)">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/events" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Events Details   </h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="club-detail">
							<div class="page-content club-detail">
								<div class="mdl-card mdl-shadow--2dp demo-card-wide">
									<%-- <img class="img-responsive" src="<%= request.getContextPath() %>/resources/front/mobile/images/events-img.jpg"/> --%>
									<div class="mdl-card__supporting-text">
										<h5 style="margin: -5px 0 16px;"> <a href="#" style="color:#005DAA;" > {{geteventdetailbyid.eventTitle}} </a> </h5>
										<span> Post by:<a href="#" style="color:black;" > Admin</a></span>
										<h4 style="color:black;">{{geteventdetailbyid.eventSubtitle}}</h4>										
										<p class="text" ng-bind-html="geteventdetailbyid.eventDescription | to_trusted"></p>
										<p class="text"> <i class="fa fa-calendar"></i> {{geteventdetailbyid.eventDate | date: 'dd/MM/yyyy'}},  <i class="fa fa-clock-o"></i> {{geteventdetailbyid.eventTime}} </p>
										
									
									</div>
								<div class="col-md-12">
									<h4>Registration Charges</h4>
									<div class="col-md-3 text-center"  style="border: 1px solid darkgray;" ng-repeat="r in geteventchargesbyid">
										<label style="padding-top: 6px;">{{r.registrationFor}}</label>
										<p style="text-align: -webkit-center;">{{r.currencyCode}} {{r.amount | number:2}}</p>
									</div>
								</div>
								
								<div class="col-md-12"><br>
									<div class="event-map-location">
										<h4 style="color:#005DAA;">Event Map Location</h4><br/>
			
										<a href="{{geteventdetailbyid.eventMapLocation}}" target="_blank"><p class="font-16"> <i class="fa fa-map-marker" aria-hidden="true"></i> {{geteventdetailbyid.eventVenue}} </p></a>
										<!-- <div class="map">
											<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d236213.5020561644!2d73.03299855546264!3d22.32204247880572!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x395fc8ab91a3ddab%3A0xac39d3bfe1473fb8!2sVadodara%2C+Gujarat!5e0!3m2!1sen!2sin!4v1526627129860"  frameborder="0" style="border:0; width: 100%; height: 260px;" allowfullscreen></iframe>
											<iframe src="{{geteventdetailbyid.eventMapLocation}}" frameborder="0" style="border:0; width: 100%; height: 260px;" allowfullscreen></iframe>
										</div> -->
									</div>
								</div>
								<div class="clearfix"> </div>
								
								<div class="col-md-12" ng-show="geteventdetailbyid.registration=='Yes'" ng-if="<%= request.getParameter("id")%> != 7"><br>
									<div class="col-md-4 text-center">
										<!-- <label>How do you want to register?</label> -->
									
									</div>
									<div class="col-md-4 text-center" ng-show="!<%= session.getAttribute("memberid")%>">
										<a data-toggle="modal" data-target="#myModal1" class="btn btn-lg btn-success btn-block btn-signin" type="submit">Register</a>
									</div>
									<div class="col-md-4 text-center" ng-show="<%= session.getAttribute("memberid")%>">
										<button ng-show="paymentStatus != 'Success' && registrationStatus == 'Success' && reqpaid == 'Yes'" ng-click="redirectToPayNowPage(<%= session.getAttribute("memberid")%>,geteventdetailbyid.eventId)" class="btn btn-lg btn-success btn-block btn-signin">Pay Now</button>
										<button ng-show="registrationStatus != 'Success' " ng-click="redirectToRegisterpage(<%= session.getAttribute("memberid")%>,geteventdetailbyid.eventId)" class="btn btn-lg btn-success btn-block btn-signin">Register </button>
										<label ng-show="registrationStatus=='Success' && paymentStatus == 'Success' ">You are already registered for this event</label>
										<label ng-show="registrationStatus=='Success' && reqpaid != 'Yes' ">You are already registered for this event and no payment is required</label>
									</div><br>
									 <div class="col-md-4 text-right" ng-show="<%= session.getAttribute("memberid")%>">
										<button ng-show="registrationStatus=='Success' && (paymentStatus == 'Success' || reqpaid != 'Yes')" ng-click="redirectToRegisteredMEmbersDirectory(geteventdetailbyid.eventId)" class="btn btn-lg btn-success btn-block btn-signin">Event Partcipants Directory</button>
									</div>
									 <div class="col-md-4 text-right" ng-show="!<%= session.getAttribute("memberid")%>">
										<button ng-show="registrationStatus!='Success' && (paymentStatus != 'Success' || reqpaid == 'Yes')" data-toggle="modal" data-target="#ConfirmModal" class="btn btn-lg btn-success btn-block btn-signin">Event Partcipants Directory</button>
									</div>
									 <br><br>
								</div>
								<div class="col-md-12" ng-show="geteventdetailbyid.registration=='Yes'" ng-if="<%= request.getParameter("id")%> == 7"><br>
									<div class="col-md-4 text-center">
										<!-- <label>How do you want to register?</label> -->
									
									</div>
									<div class="col-md-4 text-center" ng-show="!<%= session.getAttribute("memberid")%>">
										<a href="#"class="btn btn-lg btn-danger btn-block">Registration Closed</a>
										
									</div>
									<div class="col-md-4 text-center" ng-show="<%= session.getAttribute("memberid")%>">
										<button ng-show="paymentStatus != 'Success' && registrationStatus == 'Success' && reqpaid == 'Yes'" class="btn btn-lg btn-danger btn-block">Registration Closed</button>
										<button ng-show="registrationStatus != 'Success' " class="btn btn-lg btn-danger btn-block">Registration Closed</button>
										<label ng-show="registrationStatus=='Success' && paymentStatus == 'Success' ">You are already registered for this event</label>
										<label ng-show="registrationStatus=='Success' && reqpaid != 'Yes' ">You are already registered for this event and no payment is required</label>
									</div>
									<br>
									<div class="col-md-4 text-right" ng-show="<%= session.getAttribute("memberid")%>">
										<button ng-show="registrationStatus=='Success' && (paymentStatus == 'Success' || reqpaid != 'Yes')" ng-click="redirectToRegisteredMEmbersDirectory(geteventdetailbyid.eventId)" class="btn btn-lg btn-success btn-block btn-signin">Event Partcipants Directory</button>
									</div>
									 <div class="col-md-4 text-right" ng-show="!<%= session.getAttribute("memberid")%>">
										<button ng-show="registrationStatus!='Success' && (paymentStatus != 'Success' || reqpaid == 'Yes')" data-toggle="modal" data-target="#ConfirmModal" class="btn btn-lg btn-success btn-block btn-signin">Event Partcipants Directory</button>
									</div>
									 <br><br>
								</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</main>
		</div>
		<div class="modal fade" id="myModal1" role="dialog">
    			<div class="modal-dialog modal-xs">
      				<div class="modal-content">
      					<!-- <div class="modal-header">
				         
				        </div> -->
        				<div class="modal-body">
        					 <button type="button" class="close" data-dismiss="modal">&times;</button>
							<div class="account-wall">
								<div style="text-align:center;">
									<img style="border-radius: 50%;" class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120" alt="">
								</div>
								<form class="form-signin" novalidate>
									<label>How do you want to register?</label>
										<div class="form-group">
											<label>Member Type</label>
											<select class="form-control" name="membertype" id="membertype" ng-model="membertype">
												<option value="">Member Type</option>
												<option value="RMBFB Member">RMBFB Member</option>
												<option value="RMBF">RMBF</option>
												<option value="R I Representative">R I Representative</option>
												<option value="Others">Others</option>
											</select>
											<p ng-show="errorType == 1" style="color: red; font-size: 14px;">{{msgType}}</p>
										</div>
									<button ng-click="redirectToNextPage(membertype,<%= request.getParameter("id")%>)" class="btn btn-primary btn-block" type="submit">Submit</button>											
								</form>
							</div>
						</div>
      				</div>
    			</div>
  			</div>
  			<!-- Modal -->
<div id="ConfirmModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Events Participants Directory</h4>
      </div>
      <div class="modal-body">
        <p>Event Participants Directory is available to registered participants of the event only. Please log-in if you are a registered participant.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a class="btn btn-default" href="<%= request.getContextPath() %>/login?r=l">login</a>
      </div>
    </div>

  </div>
</div>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>