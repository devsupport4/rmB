<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title> My Profile | Rotary Means Business Vadodara </title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<%=request.getContextPath() %>/resources/front1/css/bootstrap.min.css" rel="stylesheet">	
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/resources/front1/css/animate.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/resources/front1/css/prettyPhoto.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/resources/front1/css/main.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front1/css/wall.css">
		<link href="<%=request.getContextPath() %>/resources/front1/css/responsive.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700" rel="stylesheet">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front1/images/favicon.png">
		
		<script src="<%=request.getContextPath() %>/resources/front1/js/jquery.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front1/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front1/js/jquery.prettyPhoto.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front1/js/jquery.isotope.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front1/js/main.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front1/js/wow.min.js"></script>		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/member_profile.js"></script>
		<%-- <script src="<%=request.getContextPath() %>/resources/admin/js/controller/my_profile.js"></script> --%>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-153537496-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());
            gtag('config', 'UA-153537496-1');

            
        </script>
	 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/style.css"> 
		<style>
		.navbar-nav {
    float: right !important;
    margin: 0;
}
.navbar{
	margin-bottom: 0px  !important;
}
h1,h2,h3,h4,h5{
font-size: 16px !important;
font-weight: 600 !important;
    font-family: 'PT Sans', sans-serif;
    color: #4e4e4e !important;
	}
			#foo,#cover{
				height: 180px;
				overflow: hidden;
				background: #fff;
				display: inline-block;
				vertical-align: top;
				margin-bottom: 8px;
			}
			img{
				max-width: 100%;
				height: auto;
			}
			#cover{
				background-size:cover;
				background-position:50% 50%;
			}
			li {
				display: -webkit-box;
			}			
			.profile-heading h3 {
				margin-top: 2px;
				margin-bottom: 5px;
			}
			#about .about-img img {
			    border: 8px solid #ffffff;
			    position: absolute;
			    left: 0px;
			    bottom: -80px;
			    padding: 6px;
			    border: 1px solid #e8e8e8;
			    box-shadow: 0 0 25px rgba(0, 0, 0, .04) inset;
			    -o-box-shadow: 0 0 25px rgba(0, 0, 0, .04) inset;
			    -moz-box-shadow: 0 0 25px rgba(0, 0, 0, .04) inset;
			    -webkit-box-shadow: 0 0 25px rgba(0, 0, 0, .04) inset;
			    background-color: #fff;
			}
			.img-responsive {
			    display: block;
			    height: auto;
			    max-width: 85%;
			}
		</style>			
	</head>
	<body ng-app="rcbs" ng-controller="memberProfileCtrl" ng-cloak ng-init="getMemberDetailById(<%= session.getAttribute("loginid") %>)">
		<%@include file="header.jsp" %>
		
		<br><br><br><br><br><br><br>
		<%-- <div class="first-widget parallax" id="blog">
			<div class="parallax-overlay">
				<div class="container pageTitle">
					<div class="row">
						<div class="col-md-6 col-sm-6"></div>
						<div class="col-md-6 col-sm-6 text-right">
							<span class="page-location"><a href="<%=request.getContextPath() %>/">Home</a> /  My Profile  </span>
						</div>
					</div>
				</div>
			</div>
		</div> --%>
		<section id="members-profile" class="container">
			<div class="row" style="background-color: white">
				<div class="page-header" style="border-bottom: 0px"></div>
				<div class="profile-container shadow col-md-10 col-md-offset-1">
					<div class="row" id="about">
						<div class="col-lg-3 col-md-3 col-sm-3 hidden-xs about-img">
							<img src="{{getmemberdetail.memberProfilePicture}}" class="img-responsive img-circle" alt="" ng-if="getmemberdetail.memberProfilePicture != ''">
							<img class="img-responsive img-circle" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" alt=""  ng-if="getmemberdetail.memberProfilePicture == ''">
						</div>						
						<div class="col-lg-6 col-md-6 col-sm-6 hidden-xs profile-info">
							<h4 class="user-name"> Rtn. {{getmemberdetail.memberFirstName}} {{getmemberdetail.memberMiddleName}} {{getmemberdetail.memberLastName}}</h4>
							<h5 class="user-company"> {{getmemberdetail.memberDesignation}}, {{getmemberdetail.memberCompanyName}} </h5>
							<h5 class="user-mail"> Mobile: {{getmemberdetail.memberMobileNumber}}, Email: <i>{{getmemberdetail.memberEmail}}</i></h5>
						</div>						
						<div class="col-lg-3 col-md-3 col-sm-3 hidden-xs profile-info" align="right">										
							<a class="user-company" href="<%=request.getContextPath() %>/manage_my_profile"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i> EDIT PROFILE </a>										
						</div>						
					</div>
					
					<div class="space60"></div>
					<div class="row">
					<br><br>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th><i class="fa fa-birthday-cake" aria-hidden="true"></i> Birth Date:	</th>
										<th><i class="fa fa-heart" aria-hidden="true"></i> Marriage Anniversary:	 </th>
										<th><i class="fa fa-tint" aria-hidden="true"></i> Blood group : </th>
										<th> <i class="fa fa-book" aria-hidden="true"></i> Qualification :</th>
									</tr>
								</thead>
								<tbody>
									<tr style="border-bottom: 1px solid #eeeeee;" class="profile-heading">
										<td><h3>{{getmemberdetail.memberDateOfBirth | date: 'dd-MMM'}}</h3></td>
										<td><h3>{{getmemberdetail.memberAnniversaryDate | date: 'dd-MMM'}}</h3></td>
										<td><h3>{{getmemberdetail.memberBloodGroup}}</h3></td>
										<td><h3>{{getmemberdetail.memberOccupation}}</h3></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="space30"></div>
						
						<br/>
						<div class="row">
							<h4 class="classic-title"><span> <i class="fa fa-users" aria-hidden="true"></i> BUSINESS INFORMATION</span></h4>
							<div class="table-responsive">
								<div class="row">
									<div class="col-md-9">
										<table class="table">
											<thead>
												<tr>											
													<th>Designation</th>
													<th>Company Name</th>
													<th>Business Nature</th>											
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>{{getmemberdetail.memberDesignation}}</td>
													<td>{{getmemberdetail.memberCompanyName}}</td>
													<td>{{getmemberdetail.memberBusinessNature}}</td>																						
												</tr>																						
											</tbody>
										</table>
										<label>About your Business</label>
										<p> {{getmemberdetail.memberCompanyDescription}}</p><br>
										<label>Business Keywords (Comma separated)</label>
										<p>{{getmemberdetail.memberCompanyKeywords}} </p>
									</div>
									<div class="col-md-3">
										<img src="{{getmemberdetail.companyLogo}}" class="img-responsive" alt="">
									</div>
								</div>								
							</div>
						</div>
						<div style="margin-right: -30px; margin-left: -30px;" class="row user-address">
							<div class="col-md-6">
								<div class="call-action-style1 call-action-boxed">
									<h4 class="classic-title"><span><i class="fa fa-briefcase" aria-hidden="true"></i>  Work Detail	 </span></h4>									
									<ul class="icons-list">
										<li><i class="fa fa-building-o" aria-hidden="true"></i>  </i><strong>Address:</strong> {{getmemberdetail.memberCompanyAddress1}}, {{getmemberdetail.memberCompanyAddress2}}, {{getmemberdetail.memberCompanyAddress3}} {{getmemberdetail.memberCompanyCityName}}-{{getmemberdetail.memberCompanyPincode}}</li>
										<li><i class="fa fa-envelope-o"></i> <strong>Email:</strong> <a href="{{getmemberdetail.memberCompanyEmail}}" target="_blank"> {{getmemberdetail.memberCompanyEmail}} </a> </li>
									   <li><i class="fa fa-mobile"></i> <strong>Mobile:</strong> {{getmemberdetail.memberCompanyMobileNumber}}</li>
										<li><i class="fa fa-globe">  </i><strong>Website:</strong> <a ng-href="{{getmemberdetail.memberWebsiteName}}" target="_blank"> {{getmemberdetail.memberWebsiteName}} </a></li>
									</ul>
								</div>
							</div>
							
						</div>
							<br>
					</div>
				</div>
				<div class="space40"></div>
			</section>
		<br><br><br>
		<%@include file="footer.jsp" %>											
	</body>
</html>

