<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Payments</title>
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/manage_orders.js"></script>
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
			#cover{
				background-size:cover;
				background-position:50% 50%;
			}
 
   			/* label{ margin-right: 15px; } */
		</style>
	</head>	
	<body ng-app="rcbs" ng-controller="registrationListCtrl" ng-init="getAllOrders()">
		<%@include file="header.jsp" %>
		<div class="panel-body">
			<section class="red box1">
				<div class="container">
					<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
						<!-- <div class="col-md-2" style="margin-top:15px">
							<div class="form-group">
								<select id="eventtype" name="eventtype" ng-model="eventtype" class="form-control" ng-init="eventtype='All'">
									<option value="All">All</option>
									<option value="Online">Online</option>
									<option value="Bank">Bank Transfer</option>
									<option value="Free">Free</option>
								</select>											
							</div>
						</div> -->
						<div class="col-lg-2" style="margin-top:15px">
							<input class="KendoDate" id="datepicker" ng-model="fromdate" title="Select From Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
						<div class="col-lg-2" style="margin-top:15px">
							<input class="KendoDate" id="datepicker1" ng-model="todate" title="Select To Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
						<div class="col-lg-1" style="margin-top:15px">
							<button type="submit" class="btn btn-primary" style="margin-top: 0px; background-color: #005daa;" ng-click="getOrdersByDate()"> <i class="fa fa-search" aria-hidden="true"></i> Go </button>
						</div>
						<div class="col-md-2" style="margin-top:15px">
							<div class="form-group">
								<select id="paymentstatusfilter" name="paymentstatusfilter" ng-model="paymentstatusfilter" class="form-control">
									<option value="">All</option>
									<option value="Aborted">Aborted</option>
									<option value="Failure">Failure</option>
									<option value="Free Event">Free Event</option>
									<option value="Success">Paid</option>
									<option value="Pending">Pending</option>
									<!-- <option value="Success">Success</option> -->
									<option value="Unpaid">Unpaid</option>
								</select>											
							</div>
						</div>
						<!-- <div class="col-md-3" align="center" style="margin-top:15px">
							<div class="input-group">
								<input type="text" id="search1" name="search1" ng-model="search1" class="form-control" placeholder="Filter Text"/>
								<a class="input-group-addon" ng-click="searchOrder(search1)" style="cursor: pointer;">
									<span class="glyphicon glyphicon-search"></span>
								</a>
							</div>
						</div> -->
						<div class="col-md-2" align="right" style="margin-top:15px;float: right;">
							<div class="form-group">
								<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changepage()"></select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<form role="form" >
								<div class="table-responsive">
									<table id="mytablenew" class="table table-bordred table-striped">
										<thead>										
											<th>Order No</th>
											<th>Title</th>
											<th>Customer Name</th>
											<!-- <th>Customer Mobile No</th> -->
											<th>Total Amount</th>
											<th>Order Date</th>
											<th>Payment Status</th>
											<th>Update Status</th>
										</thead>
										<tbody>
											<tr ng-repeat="item in getOrders |filter:paymentstatusfilter" style="cursor:pointer;cursor:hand">					
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">{{item.orderNumber}}</td>
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">{{item.eventTitle}}</td>
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">{{item.customerName}}</td>
												<!-- <td data-toggle="modal" data-target="#EditOrderDetails" ng-click="getOrderDetailsById(item.sequence)">{{item.customerMobileno}}</td> -->
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">{{item.totalAmount |number:2}}</td>
												<td data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">{{item.orderDate}}</td>
												<td ng-show="item.paymentStatus=='Success'" data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">Paid</td>
												<td ng-show="item.paymentStatus!='Success'" data-toggle="modal" data-target="#EditEventMemberPayment" ng-click="getOrderDetailsById(item.orderNumber)">{{item.paymentStatus}}</td>
												<td ng-click="getOrderDetailsById(item.orderNumber)"><i ng-show="item.bankReferenceNumber && item.paymentMode=='Offline'" data-toggle="modal" data-target="#EditMemberPayment" class="fa fa-pencil-square-o" aria-hidden="true"></i></td>
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
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>Order Number</label>
											<input type="text" id="ordernumber" name="ordernumber" ng-model="ordernumber" placeholder="Order Number" class="form-control" disabled>
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
								<div class="row">
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
								<div class="row">
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
								<div class="row">								
									<div class="col-md-3">
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
								<div class="row">
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
								<div class="row">
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
									<div class="col-md-3">
										<div class="form-group">
											<label>Bank Name</label>
											<input type="text" class="form-control" id="bankname" name="bankname" ng-model="bankname" disabled>
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
