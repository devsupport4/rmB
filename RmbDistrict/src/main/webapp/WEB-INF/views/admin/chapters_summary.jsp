<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Members Committee</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	    
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>	  		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>		
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>		
	</head>	
	<body ng-app="rcbs" ng-controller="chapterSummaryCtrl" ng-init="getChapterSummary()" ng-cloak>
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-3" align="left">
						<h3>Chapter's Summary</h3>					
					</div>
					<div class="col-md-3">
						<div class="form-group" style="margin-top:15px">
							<input class="KendoDate" id="datepicker" ng-model="fromdate" title="Select From Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group" style="margin-top:15px">
							<input class="KendoDate" id="datepicker1" ng-model="todate" title="Select To Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group" style="margin-top:15px">
							<button type="submit" class="btn btn-primary" ng-click="getChaptersSummary()"> <i class="fa fa-search" aria-hidden="true"></i> Search </button>
						</div>
					</div>										
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form">
							<div class="table-responsive">
								<table class="table table-stripped">
									<thead>
										<tr>
											<th>Member Name</th>
											<th class="text-right" title="Reference Given Inside">RGI</th>
											<th class="text-right" title="Reference Given Outside">RGO</th>
											<th class="text-right" title="Reference Received Inside">RRI</th>
											<th class="text-right" title="Reference Received Outside">RRO</th>
											<th class="text-right" title="Business Transactions">BT</th>
											<th class="text-right" title="Meetings">M</th>																	
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="item in getChapterSummaryByDate">																	
											<td>{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>
											<td class="text-right" title="Reference Given Inside">{{item.referenceGivenInside}}</td>
											<td class="text-right" title="Reference Given Outside">{{item.referenceGivenOutside}}</td>
											<td class="text-right" title="Reference Received Inside">{{item.referenceReceivedInside}}</td>
											<td class="text-right" title="Reference Received Outside">{{item.referenceReceivedOutside}}</td>
											<td class="text-right" title="Business Transactions" data-toggle="modal" data-target="#myModal" ng-click='getMemberBusinessdetails(item.memberId)'>{{item.memberBusinessTransactionCount | number:2}}</td>																	
											<td class="text-right" title="Meetings">{{item.memberMeetingCount}}</td>																	
										</tr>
										<tr>																	
											<td class="text-right"><b>Total</b></td>
											<td class="text-right" title="Total Reference Given Inside">{{totalReferenceGivenInside}}</td>
											<td class="text-right" title="Total Reference Given Outside">{{totalReferenceGivenOutside}}</td>
											<td class="text-right" title="Total Reference Received Inside">{{totalReferenceReceivedInside}}</td>
											<td class="text-right" title="Total Reference Received Outside">{{totalReferenceReceivedOutside}}</td>
											<td class="text-right" title="Total Business Transactions">{{totalMemberBusinessTransactionCount | number:2}}</td>																	
											<td class="text-right" title="Total Meetings">{{totalMemberMeetingCount}}</td>																	
										</tr>
									</tbody>
								</table>
							</div>
						</form>
					</div>
				</div>
				<%-- <div class="row">
					<div class="col-md-12 text-center" >
						<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="currentPage=currentPage-1">
			    			<i class="glyphicon glyphicon-step-backward"></i>
			    		</button>
			    		{{currentPage+1}}/{{numberOfPages()}}
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= memberscommittee.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div> --%>
			</div>
			<div class="container">
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Business Transaction</h4>
									</div>
									<div class="modal-body">
										<table class="table table-stripped">
											<thead>
												<tr>
													<th>From</th>
													<th title="To">To</th>
													<th class="text-right" title="Slip Date">Slip Date</th>
													<th class="text-right" title="Amount">Amount</th>																													
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in getMemberBusiness">																	
													<td>{{item.fromFirstName}} {{item.fromLastName}}</td>
													<td title="Reference Given Inside">{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>																										
													<td class="text-right" title="Reference Given Outside">{{item.slipDate}}</td>
													<td class="text-right" title="Reference Given Outside">{{item.amount | number:2}}</td>																													
												</tr>
												
											</tbody>
										</table>																													
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
