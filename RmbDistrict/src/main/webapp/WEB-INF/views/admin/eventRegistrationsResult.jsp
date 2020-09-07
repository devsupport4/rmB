<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Registrations</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
		<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/eventRegResult.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>	
		<style>
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
			.modal {
  overflow-y:auto;
}
			#cover{
				background-size:cover;
				background-position:50% 50%;
			}
 
   			/* label{ margin-right: 15px; } */
		</style>
	</head>	
	<body ng-app="rcbs" ng-controller="eventRegistrationCtrl" ng-init="getAllRegistrations()">
		<%@include file="header.jsp" %>
		<div class="panel-body">
			<section class="red box1">
				<div class="container">
					<div class="row panel panel-primary" style="width:105%;margin-left:-2.5%;background-color:#eee;">
						<div class="col-md-2" align="right" style="margin-top:15px;">
							<div class="form-group">
								<select class="form-control input-lg2" name="eventDrop" id="eventDrop" ng-model="eventDrop" ng-change="getMembers()">
									<option value=""> All Events</option>
									<option ng-repeat="item in events" value="{{item.eventId}}">{{item.eventTitle}} - {{item.eventDate | date : 'dd/MM/yyyy'}}</option>
								</select>
							</div>
						</div>
						<div class="col-md-2" style="margin-top:15px">
							<select class="form-control" name="membertype" id="membertype" ng-model="membertype" ng-change="filmemberType()">
								<option value="">All Type</option>
								<option value="RMBFB Member">RMBFB Member</option>
								<option value="RMBF">RMBF</option>
								<option value="R I Representative">R I Representative</option>
								<option value="Others">Others</option>
							</select>
						</div>
						<div class="col-md-2" style="margin-top:15px">
							<input class="KendoDate" id="datepicker" ng-model="fromdate" title="Select From Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
						<div class="col-md-2" style="margin-top:15px">
							<input class="KendoDate" id="datepicker1" ng-model="todate" title="Select To Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
						<div class="col-md-1" style="margin-top:15px">
							<button type="submit" class="btn btn-primary" style="margin-top: 0px; background-color: #005daa;" ng-click="getOrdersByDate()"> <i class="fa fa-search" aria-hidden="true"></i> Go </button>
						</div>
						<div class="col-md-1" style="margin-top:15px; width:11.5em;">
							<div class="form-group">
								<select id="paymentstatusfilter" name="paymentstatusfilter" ng-model="paymentstatusfilter" ng-change="paymenStat()" class="form-control">
									<option value="">All</option>
									<option value="Aborted">Aborted</option>
									<option value="Failure">Failure</option>
									<option value="Free Event">Free Event</option>
									<option value="Paid">Paid</option>
									<option value="Pending">Pending</option>
									<!-- <option value="Success">Success</option> -->
									<option value="Unpaid">Unpaid</option>
								</select>											
							</div>
						</div>
						<div class="col-md-1" align="right" style="margin-top:15px;float: right;">
							<div class="form-group">
								<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changepage()"></select>
							</div>
						</div>
						<div align="right" style="margin-top:15px;float: right;">
							<div class="form-group">
								<button class="btn btn-primary" style="margin-top: 0px; background-color: #005daa;" data-toggle="modal" data-target="#RegisterMember"> <i class="fa fa-plus" aria-hidden="true"></i></button>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<form role="form" >
								<div class="table-responsive">
									<table id="mytablenew" class="table table-bordred table-striped">
										<thead>	
											<!-- <th>Title</th> -->
											<th>Customer Name</th>
											<th>Member Type</th>
											<th>Amount</th>
											<th>Registration Date</th>
											<th>Payment Status</th>
											<th><i class=" fa fa-pencil-square fa-lg" aria-hidden="true"></i></th>
											<th>A/I</th>
										</thead>
										<tbody>
											<tr ng-repeat="item in getOrders | filter:{eventId: eventDrop} | filter:{memberType: membertype}:memberlistUpdate" style="cursor:pointer;cursor:hand">
												<!-- <td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">{{item.eventTitle}}</td> -->
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">{{item.firstName}} {{item.lastName}}</td>
												<!-- <td data-toggle="modal" data-target="#EditOrderDetails" ng-click="getOrderDetailsById(item.sequence)">{{item.customerMobileno}}</td> -->
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">{{item.memberType}}</td>
												<td data-toggle="modal" data-target="#EditEventMemberPayment" style="padding-left:60px;" ng-click="getOrderDetailsById(item.eventRegsitrationId)">{{item.amount |number:2}}</td>
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">{{item.registrationDate}}</td>
												<td ng-show="item.paymentStatu=='Success'" data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">Paid</td>
												<td ng-show="item.payReq == 'No' || (item.amount == '0.00' && item.paymentStatu == 'Paid') " data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">Free Event</td>
												<td ng-hide="item.payReq == 'No' || (item.amount == '0.00' && item.paymentStatu == 'Paid' || item.paymentStatu=='Success')" data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.eventRegsitrationId)">{{item.paymentStatu}}</td>
												<td ng-click="getOrderDetailsById(item.eventRegsitrationId)"><i ng-show="item.paymentRefNo && item.paymentStatu=='Pending'" data-toggle="modal" data-target="#EditMemberPayment" class="fa fa-pencil-square-o" aria-hidden="true"></i></td>
												<td ng-show="item.memberActiveInactive=='y'" style="text-align: center;" ng-click="setActiveOrInactiveMember(item.memberId,item.memberActiveInactive)"><i class="fa fa-check-square" aria-hidden="true"></i></td>
															<td ng-show="item.memberActiveInactive=='i'" style="text-align: center;"ng-click=" setActiveOrInactiveMember(item.memberId,item.memberActiveInactive)"><input type="checkbox" ></td>
											</tr>
										</tbody>
									</table>
									<h4 style="text-align:center;" ng-if="getOrders==''">No Data Found....</h4>
									<div class="clearfix"></div>							
								</div>
							</form>
						</div>
					</div>
					<div class="well well-sm">
						<div class="row">
							<div class="col col-lg-8 col-md-8 col-sm-7 col-xs-7 text-left" style="margin: 10px 0;">
								<div class="hint-text">Showing <b>{{startindex+1}}-{{getOrders.length+startindex}}</b> out of <b>{{getOrderscount.length}}</b> entries</div>
							</div>
							<div class="col col-lg-4 col-md-4 col-sm-5 text-center">
								<ul class="pagination hidden-xs pull-right" style="margin: 5px 0;">
									<li class="disabled" ng-show="currentPage <= 0"><a href="#">Previous</a></li>
									<li class="page-item" ng-show="currentPage > 0"><a href="#" ng-click="prev()">Previous</a></li>
									<li class="active"><a href="#">{{currentPage+1}}</a></li>									
									<%-- <li class="disabled" ng-show="getOrders.length+startindex <= getOrders.length"><a href="#" class="page-link">Next</a></li>
									<li class="page-item" ng-show="getOrders.length+startindex == getOrders.length"><a href="#" class="page-link" ng-click="next()">Next</a></li> --%>
									
									<li ng-show="getOrders.length+startindex < getOrderscount.length" class="page-item"><a href="#"  class="page-link" ng-click="next()">Next</a></li>
									<!-- <li  class="disabled" ng-show="getOrders.length+startindex > getOrderscount.length" class="page-item"><a href="#"  class="page-link"">Next</a></li> -->
									<li  class="disabled" ng-show="getOrders.length+startindex == getOrderscount.length" class="page-item"><a href="#"  class="page-link" ng-click="next()">Next</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div class="container">
			<div class="modal fade" id="EditEventMemberPayment" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">View Registered Event Members</h4>
						</div>
						<div class="modal-body">							
							<form role="form">
								<div class="row" ng-hide="hidegrid">
									<div class="col-md-3">
										<div class="form-group">
											<label>Order Number</label>
											<input type="text" id="ordernumber" name="ordernumber" ng-model="ordernumber" placeholder="Order Number" class="form-control ng-pristine ng-valid" disabled="">
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Name</label>
											<input type="text" id="customername" name="customername" ng-model="customername" placeholder="Company Name" class="form-control" disabled>											
										</div>
										<p ng-show="errorCompany == 1" style="color: red; font-size: 14px;">{{msgCompany}}</p>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Email</label>
											<input type="email" id="emailid" name="emailid" ng-model="emailid" placeholder="Email" class="form-control" disabled>
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Mobile Number<font color="red" size="3">*</font></label>
											<input type="text" id="mobileno" name="mobileno" ng-model="mobileno" placeholder="Mobile Number" class="form-control" disabled>
										</div>
									</div>
								</div>
								<div class="row" ng-hide="hidegrid">
								 	<div class="col-md-3">
										<div class="form-group">
											<label>Order Date</label>
											<input type="text" id="orderdate" name="orderdate" ng-model="orderdate" placeholder="Order Date" class="form-control" disabled>											
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Biller Name</label>
											<input type="text" id="billername" name="billername" ng-model="billername" placeholder="Biller Name" class="form-control" disabled>											
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Biller Email</label>
											<input type="email" id="billeremail" name="billeremail" ng-model="billeremail" placeholder="Biller Email" class="form-control" disabled>											
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Biller Address1</label>
											<input type="text" id="billeraddress1" name="billeraddress1" ng-model="billeraddress1" placeholder="Biller Address1" class="form-control" disabled>											
										</div>
									</div>
								</div>
								<div class="row" ng-hide="hidegrid">
									<div class="col-md-3">
										<div class="form-group">
											<label>Biller Address2</label>
											<input type="text" id="billeraddress2" name="billeraddress2" ng-model="billeraddress2" placeholder="Biller Address2" class="form-control" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Biller Country</label>
											<input type="text" id="billercountry" name="billercountry" ng-model="billercountry" placeholder="Biller Country" class="form-control" disabled>											
										</div>
										<p ng-show="errorMobileno == 1" style="color: red; font-size: 14px;">{{msgMobileno}}</p>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Biller State</label>
											<input type="text" id="billerstate" name="billerstate" ng-model="billerstate" placeholder="Biller State" class="form-control" disabled>											
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>City</label>
											<input type="text" id="billercity" name="billercity" ng-model="billercity" placeholder="Biller City" class="form-control" disabled>
									 	</div>
									</div>
								</div>
								<div class="row" ng-hide="hidegrid">								
									<div class="col-md-3" >
										<div class="form-group">
											<label>Biller Pincode</label>
											<input type="text" class="form-control" id="billerpincode" name="billerpincode" ng-model="billerpincode" disabled>
										</div>
									</div>
									<div class="col-md-3">								
										<div class="form-group">
											<label>Biller Mobile No</label>
											<input type="text" class="form-control" id="billermobileno" name="billermobileno" ng-model="billermobileno" disabled>									
										</div>								
									</div>
									<div class="col-md-3">								
										<div class="form-group">
											<label>Sub Total</label>
											<input type="text" class="form-control" id="subtotal" name="subtotal" ng-model="subtotal" disabled>									
										</div>								
									</div>			
									<div class="col-md-3">
										<div class="form-group">
											<label>Total</label>
											<input type="text" class="form-control" id="total" name="total" ng-model="total" disabled>
										</div>
									</div>				
								</div>	
								<div class="row" ng-hide="hidegrid">
									<div class="col-md-3">
										<div class="form-group">
											<label>Order Status</label>
											<input type="text" class="form-control" id="orderstatus" name="orderstatus" ng-model="orderstatus" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Payment Status </label>
											<input type="text" class="form-control" id="paymentstatus" name="paymentstatus" ng-model="paymentstatus" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Tracking ID</label>
											<input type="text" class="form-control" id="trackingid" name="trackingid" ng-model="trackingid" disabled>
										</div>
									</div>	
									<div class="col-md-3">
										<div class="form-group">
											<label>Bank Ref No.</label>
											<input type="text" class="form-control" id="bankrefno" name="bankrefno" ng-model="bankrefno" disabled>
										</div>
									</div>										
								</div>
								<div class="row" ng-hide="hidegrid">
									<div class="col-md-3">
										<div class="form-group">
											<label>Failure message</label>
											<input type="text" class="form-control" id="failuremsg" name="failuremsg" ng-model="failuremsg" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Payment Mode</label>
											<input type="text" class="form-control" id="paymentmode" name="paymentmode" ng-model="paymentmode" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Card Name</label>
											<input type="text" class="form-control" id="cardname" name="cardname" ng-model="cardname" disabled>
										</div>
									</div>	
									<div class="col-md-3" ng-hide="hidegrid">
										<div class="form-group">
											<label>Bank Name</label>
											<input type="text" class="form-control" id="bankname" name="bankname" ng-model="bankname" disabled>
										</div>
									</div>										
								</div>
								<div class="row" ng-show="hidegrid">
									<div class="col-md-3">
										<div class="form-group">
											<label>Name</label>
											<input type="text" id="customername" name="customername" ng-model="customername" placeholder="Company Name" class="form-control" disabled>											
										</div>
										<p ng-show="errorCompany == 1" style="color: red; font-size: 14px;">{{msgCompany}}</p>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Email</label>
											<input type="email" id="emailid" name="emailid" ng-model="emailid" placeholder="Email" class="form-control" disabled>
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Mobile Number<font color="red" size="3">*</font></label>
											<input type="text" id="mobileno" name="mobileno" ng-model="mobileno" placeholder="Mobile Number" class="form-control" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Total</label>
											<input type="text" class="form-control" id="total" name="total" ng-model="total" disabled>
										</div>
									</div>	
								</div>
								<div class="row" ng-show="hidegrid">
								 	<div class="col-md-3">
										<div class="form-group">
											<label>Payment Status </label>
											<input type="text" class="form-control" id="paymentstatus" name="paymentstatus" ng-model="paymentstatus" disabled>
										</div>
									</div>	
									<div class="col-md-3">
										<div class="form-group">
											<label>Bank Ref No.</label>
											<input type="text" class="form-control" id="bankrefno" name="bankrefno" ng-model="bankrefno" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Payment Mode</label>
											<input type="text" class="form-control" id="paymentmode" name="paymentmode" ng-model="paymentmode" disabled>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Card Name</label>
											<input type="text" class="form-control" id="cardname" name="cardname" ng-model="cardname" disabled>
										</div>
									</div>
								</div>														
							</form>							
						</div>
						<div class="modal-footer">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- UserTypeModal -->
		<div class="modal fade" id="EditMemberPayment">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Edit Payment Status</h4>
						<button class="close" type="button" data-dismiss="modal" aria-label="Close">
							&times;
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-6 col-xs-12">
								<div class="form-group input-group">
									<label> Payment Status <span class="text-danger">*</span></label>
									<select id="pymntst" ng-model="pymntst" class="form-control" ng-change="setFlag()">
										<option value="">Status</option>
										<option value="Pending">Pending</option>
										<option value="Success">Paid</option>
										<option value="UnPaid">Unpaid</option>
									</select>
								</div>
								<p class="errormsg" ng-show="errorPayment == 1">{{msgPayment}}</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="col-md-8 text-left">
							<div class="alert alert-success" style="margin-bottom: 0px; padding: 12px;" ng-show="paymentSubmitSuccess==1">
								<strong style="font-size: 16px;">Data updated successfully!</strong>
							</div>
							<div class="alert alert-danger" style="margin-bottom: 0px; padding: 12px;" ng-show="paymentSubmitError==1">
								<strong style="font-size: 16px;">Something wrong, please try again!</strong>
							</div>
						</div>
						<div class="col-md-4 text-right">
							<span class="input-group-btn">
								<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
								<button type="button" class="btn btn-success" name="submit" ng-click="changeStatus(orderid,pymntst)">Update</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="RegisterMember">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Select Event and Member Type</h4>
						<button class="close" type="button" data-dismiss="modal" aria-label="Close">
							&times;
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12" align="right" style="margin-top:15px;">
								<div class="form-group">
									<select class="form-control input-lg2" name="eventDropReg" id="eventDropReg" ng-model="eventDropReg">
										<option value="">Events</option>
										<option ng-repeat="item in events" value="{{item.eventId}}">{{item.eventTitle}} - {{item.eventDate | date : 'dd/MM/yyyy'}}</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" style="margin-top:15px">
								<select class="form-control" name="membertypeReg" id="membertypeReg" ng-model="membertypeReg">
									<option value="">Member Type</option>
									<option value="RMBF">RMBF</option>
									<option value="R I Representative">R I Representative</option>
									<option value="Others">Others</option>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="col-md-8 text-left">
						</div>
						<div class="col-md-4 text-right">
							<span class="input-group-btn">
								<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
								<button type="button" class="btn btn-success" ng-click="RegisterNewUser(eventDropReg,membertypeReg)">Go</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="RegisterMem">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Register {{membertypeREG}} for {{eventName}}</h4>
						<button class="close" type="button" data-dismiss="modal" aria-label="Close">
							&times;
						</button>
					</div>
					<div class="modal-body">
						<div class="row">													
												</div>
												<div class="row">
													<div class="col-md-12">
														<form class="" >
														<div class="form-group row">
															<div class="col-md-3">
																<div class="form-group">
																	<label>Company Name</label>
																	<input type="text" id="rtcmpadd" name="rtcmpadd" ng-model="rtcmpadd" placeholder="Company Name" class="form-control" ng-change="setFlag()">											
																</div>
																<p ng-show="errorCompany == 1" style="color: red; font-size: 14px;">{{msgCompany}}</p>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>First Name<font color="red" size="3">*</font></label>
																	<input type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="First Name" class="form-control" ng-change="setFlag()">
																	<p ng-show="errorFirstName == 1" style="color: red; font-size: 14px;">{{msgFirstName}}</p>
																</div>									
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>Last Name<font color="red" size="3">*</font></label>
																	<input type="text" id="lastnameadd" name="lastnameadd" ng-model="lastnameadd" placeholder="Last Name" class="form-control" ng-change="setFlag()">
																	<p ng-show="errorLastName == 1" style="color: red; font-size: 14px;">{{msgLastName}}</p>
																</div>									
															</div>
															<div class="col-md-3">
																<label>Gender<font color="red" size="3">*</font></label>
																<div class="form-group">
																	<select id="genderadd" name="genderadd" ng-model="genderadd" class="form-control" ng-change="setFlag()">
																		<option value="">Gender</option>
																		<option value="M">Male</option>
																		<option value="F">Female</option>
																		<option value="O">Other</option>
																	</select>											
																</div>
																<p ng-show="errorGender == 1" style="color: red; font-size: 14px;">{{msgGender}}</p>
															</div>    
														</div>
														<div class="form-group row">
															<div class="col-md-3">
																<div class="form-group">
																	<label>Email ID<font color="red" size="3">*</font></label>
																	<input type="email" id="emailadd" name="emailadd" ng-model="emailadd" placeholder="Email" class="form-control" ng-change="setFlag()" onblur="validateemail()">
																	<p ng-show="errorEmail == 1" style="color: red; font-size: 14px;">{{msgEmail}}</p>
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>Mobile No<font color="red" size="3">*</font></label>
																	<input type="text" id="mobilenoadd" name="mobilenoadd" ng-model="mobilenoadd" placeholder="Mobile No" class="form-control" ng-change="setFlag()" maxlength="10">											
																</div>
																<p ng-show="errorMobileno == 1" style="color: red; font-size: 14px;">{{msgMobileno}}</p>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>Company Website</label>
																	<input type="text" id="rtwebsiteadd" name="rtwebsiteadd" ng-model="rtwebsiteadd" placeholder="Company Website" class="form-control">											
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>Country</label>
																	<select id="countryidadd" name="countryidadd" ng-model="countryidadd" ng-init="countryidadd=1" class="form-control">
																		<option value="">Select Country</option>
																		<option ng-repeat="c in getcountry" value="{{c.countryId}}">{{c.countryName}}</option>
														 			</select>	
															 	</div>
															</div>
														</div>
														<div class="form-group row" ng-show="membertypeREG !='RMBFB Member' ">
															<div class="col-md-3" >
																<div class="form-group">
																	<label>Password<font color="red" size="3">*</font></label>
																	<input type="password" id="password" name="password" ng-model="password" placeholder="Password" class="form-control" ng-change="setFlag()">											
																</div>
																<p ng-show="errorPassword == 1" style="color: red; font-size: 14px;">{{msgPassword}}</p>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>Confirm Password<font color="red" size="3">*</font></label>
																	<input type="password" id="cpassword" name="cpassword" ng-model="cpassword" placeholder="Confirm Password" class="form-control" ng-change="setFlag()">											
																</div>
																<p ng-show="errorCPassword == 1" style="color: red; font-size: 14px;">{{msgCPassword}}</p>
															</div>
														</div>
														<div class="form-group row" ng-show="membertypeREG !='Others'">
															 <div class="col-md-4">
																<div class="form-group">
																	<label>Rotary Member ID</label>
																	<input type="text" id="rtmemberadd" name="rtmemberadd" ng-model="rtmemberadd" placeholder="Rotary Member ID" class="form-control">											
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>Rotary Club Name</label>
																	<input type="text" id="rtclubadd" name="rtclubadd" ng-model="rtclubadd" placeholder="Rotary Club Name" class="form-control">											
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>RMB Chapter Name</label>
																	<input type="text" id="rtchapadd" name="rtchapadd" ng-model="rtchapadd" placeholder="RMB Chapter Name" class="form-control">											
																</div>
															</div>
														</div> 
														
														<div class="form-group row">
															<div class="col-md-6">
																<div class="form-group">
																	<label>Short Description of your Company</label>
																	<textarea rows="2" id="shortdescadd" name="shortdescadd" ng-model="shortdescadd" placeholder="Short Description of your Company" class="form-control"></textarea>																																
																</div>
															</div>	
															<div class="col-md-6">
																<div class="form-group">
																	<label>Business Classification</label>			
																	<textarea rows="2" id="businessclsadd" name="businessclsadd" ng-model="businessclsadd" placeholder="Business Classification" class="form-control" ng-change="setFlag()"></textarea>																																
																</div>
																<p ng-show="errorBusiness == 1" style="color: red; font-size: 14px;">{{msgBusiness}}</p>
															</div>	
														</div> 
														<div class="box-footer">
															<div class="col-md-8 text-left">
																<!-- <p ng-show="userSubmitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p> -->
																<p ng-show="userSubmitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>
																<p ng-show="userSubmitError1 == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>	
															</div>
															<div class="text-right">
																
																<button ng-click="registration(eventREG, 0,membertypeREG)" ng-show="userSubmitError != 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
															</div>
														</div>		
													</form>
													<%-- <div class="text-right">
														<button ng-if="userSubmitError == 1"  ng-click="redirectToLoginPage(<%= request.getParameter("eid")%>, '<%= request.getParameter("membertype")%>')" class="btn btn-success" > Login</button>
													</div> --%>
													
													
													</div>
												</div>
					</div>
					<div class="modal-footer">
						<div class="col-md-8 text-left">
						</div>
						<div class="col-md-4 text-right">
							<span class="input-group-btn">
								<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
								<button type="button" class="btn btn-success" name="submit" ng-click="changeStatus(orderid,pymntst)">Register</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp" %>
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
		
			$(document).ready(function(){
	    	$('input[type="radio"]').click(function(){
	        var inputValue = $(this).attr("value");
	        var targetBox = $("." + inputValue);
	        $(".box").not(targetBox).hide();
	        $(targetBox).show();
	    	});
	    		    	
			});
		
			
		</script>
	</body>
</html>
