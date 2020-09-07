<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title> Member's Referrals | Rotary Means Business Fellowship Bangalore </title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css" rel="stylesheet">	
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/resources/front/css/animate.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/resources/front/css/prettyPhoto.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/resources/front/css/main.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/wall.css">
		<link href="<%=request.getContextPath() %>/resources/front/css/responsive.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700" rel="stylesheet">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.prettyPhoto.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.isotope.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/main.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/wow.min.js"></script>		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
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

		<style>
			#foo,#cover {
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
		</style>			
	</head>
	<body ng-app="rcbs" ng-controller="memberReferralCtrl" ng-init="getMemberReferralsById(<%= session.getAttribute("loginid") %>)" ng-cloak>
		<%@include file="header.jsp" %>
		<div class="first-widget parallax" id="blog">
			<div class="parallax-overlay">
				<div class="container pageTitle">
					<div class="row">
						<div class="col-md-6 col-sm-6"></div>
						<div class="col-md-6 col-sm-6 text-right">
							<span class="page-location"><a href="<%=request.getContextPath() %>/">Home</a> /  Member's Referrals </span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<section class="bg-banner">
			<div class="banner-bottom-bg single-bg">
				<div class="banner-bg">
					<div class="container">
						<div class="banner">
							<div class="banner-grids">
								<div class="banner-middle">
									<div class="strip"> </div>
									<div class="col-md-12">
										<section id="News-All">											
											<div class="row">												
												<div class="col-lg-3">
													<input class="KendoDate" id="datepicker" ng-model="fromdate" title="Select From Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
												</div>
												<div class="col-lg-3">
													<input class="KendoDate" id="datepicker1" ng-model="todate" title="Select To Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
												</div>
												<div class="col-lg-3">
													<button type="submit" class="btn btn-primary" style="margin-top: 0px; background-color: #005daa;" ng-click="getReferralsByDate(<%= session.getAttribute("loginid") %>)"> <i class="fa fa-search" aria-hidden="true"></i> Search </button>													
												</div>											
											</div>																																																										
											<div class="clearfix"> </div>																									
										</section>
									</div>
									<div class="clearfix"> </div>
								</div>
							</div>
						</div>
						<div class="banner">
							<div class="banner-grids">
								<div class="banner-middle">
									<div class="strip"> </div>
									<div class="col-md-12">
										<section id="News-All">
											<div class="row">
												<div class="col-lg-12">
													<h4 class="classic-title"><span>Referrals for {{firstname}} {{lastname}}</span></h4> 
												</div>
											</div>
											
											<div class="row">
												<div class="col-lg-12">
													<div class="table-responsive">
														<table class="table table-stripped">
															<thead>
																<tr>
																	<th>Date</th>
																	<th>From</th>
																	<th>Referral</th>
																	<th>Inside/Outside</th>
																	<th>Status</th>
																	<th>Contact No.</th>
																	<th>Email</th>
																	<th>Comments</th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in getMemberReferrals" ng-if="item.referralStatus == 'O'" style="color:green;cursor:pointer;">
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referDate}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralName}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralType}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralStatus1}}<span ng-if="item.referralStatus1 && item.referralStatus2"> & </span>{{item.referralStatus2}}</label></td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.contactNumber}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.email}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.comments}}</td>
																</tr>
																<tr ng-repeat="item in getMemberReferrals" ng-if="item.referralStatus == 'W'" style="color:blue;cursor:pointer;">
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referDate}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralName}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralType}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralStatus1}}<span ng-if="item.referralStatus1 && item.referralStatus2"> & </span>{{item.referralStatus2}}</label></td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.contactNumber}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.email}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.comments}}</td>
																</tr>
																<tr ng-repeat="item in getMemberReferrals" ng-if="item.referralStatus == 'C'" style="color:red;cursor:pointer;">
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referDate}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralName}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralType}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.referralStatus1}}<span ng-if="item.referralStatus1 && item.referralStatus2"> & </span>{{item.referralStatus2}}</label></td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.contactNumber}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.email}}</td>
																	<td data-toggle="modal" data-target="#myModal" ng-click="getReferenceDetail(item.memberReferralId)">{{item.comments}}</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>													
											</div>																																																										
											<div class="clearfix"> </div>																									
										</section>
									</div>
									<div class="clearfix"> </div>
									<i class="fa fa-square" aria-hidden="true" style="color: green"></i> - Open({{allcounts.wonEnquiryCount}}) &nbsp; &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: red"></i> - Closed({{allcounts.lostEnquiryCount}})  &nbsp; &nbsp;<i class="fa fa-square" aria-hidden="true" style="color: blue"></i> - Working({{allcounts.workingEnquiryCount}})
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<div class="container">
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Members Referrals</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label><b>Date:</b></label><br>{{getReferencebyid.referDate}} 
											
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label><b>Close Date:</b></label><br>{{getReferencebyid.closeDate}} 
											
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label><b>To</b></label><br>{{getReferencebyid.memberFirstName}} {{getReferencebyid.memberMiddleName}} {{getReferencebyid.memberLastName}}											
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label><b>Approx Value:</b></label><br>{{getReferencebyid.apprValue}} 
											
										</div>
									</div>
								
									<div class="col-md-4">
										<div class="form-group">
											<label><b>Referral</b></label> <br> {{getReferencebyid.referralName}} 
											
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label><b>From</b></label><br>{{getReferencebyid.fromFirstName}} {{getReferencebyid.fromLastName}}									
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label><b>Referral Type</b></label> <br> {{getReferencebyid.referralType}}											
										</div>
									</div>									
									<div class="col-md-4">
										<div class="form-group">
											<label><b>Contact Number</b></label> <br> {{getReferencebyid.contactNumber}}										
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8">
										<div class="form-group">
											<label><b>Referral Status</b></label> <br> {{getReferencebyid.referralStatus1}}, {{getReferencebyid.referralStatus2}}											
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label><b>E-mail</b></label> <br> {{getReferencebyid.email}}										
										</div>
									</div>
									
								</div>
								<div class="row">										
									<div class="col-md-12">
										<div class="form-group">
											<label><b>Address</b></label><br>  {{getReferencebyid.address}}
										</div>
									</div>
								</div>
								<div class="row">																			
									<div class="col-md-12">
										<div class="form-group">
											<label><b>Comments</b></label><br>  {{getReferencebyid.comments}} 												
										</div>
									</div>
								</div>
								<select class="form-control" name="referalstatusE" id="referalstatusE" ng-model="getReferencebyid.referralStatus" ng-change="ChangeRefStatus(getReferencebyid.memberReferralId, getReferencebyid.referralStatus)">
									<option value="">--- Change Status ---</option>
									<option value="O">Open</option>
									<option value="W">Working</option>
									<option value="C">Close</option>
								</select>		
								<div class="row" ng-if="getReferencebyid.referralStatus == 'C'">
									<div class="col-md-6">
									<lable>Reason<font color="red" size="3">*</font></lable>
										<select class="form-control" name="CloseReason" id="CloseReason" ng-model="getReferencebyid.CloseReason" >
											<option value="">--- Select Reason ---</option>
											<option value="Success">Success</option>
											<option value="Rejected">Rejected</option>
											<option value="Not serviced">Not serviced</option>
										</select>
										<label ng-if="reasonError == 1" style="color:red;">{{reasonMsg}}</label>
									</div>
									<div class="col-md-6">
										<lable>Comments</lable>
										<textarea class="form-control" name="CloseComment" id="CloseComment" ng-model="getReferencebyid.CloseComment"></textarea>
									</div>
								</div>
								<div class="row text-center" ng-if="getReferencebyid.referralStatus == 'C'">
								<br>
									<button ng-disable="spin == 1" class="btn btn-danger" ng-click="CloseRef(getReferencebyid.memberReferralId)"><i class="fa fa-refresh fa-spin" ng-show="spin == 1"></i> Close Reference</button>
								</div>																					
							</div>						
						</div>
					</div>
				</div>
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
		<%@include file="footer.jsp" %>											
	</body>
</html>

