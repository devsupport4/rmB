<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Draft List</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/draft_list.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />		
	</head>	
	<body ng-app="rcbs" ng-controller="draftListCtlr">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="background-color:#eee;">
					<div class="col-md-12" align="center" style="margin-top: 10px; margin-bottom: 10px; font-size: 18px;">
						<strong>Draft List</strong>					
					</div>									
				</div>
				<form role="form">										
					<div class="row">
						<div class="col-md-12">
							<table class="table table-striped" width="100%">
								<tr>
									<th>Member</th>
									<th>Rotary Year</th>
									<th>Member Type</th>							
									<th class="text-right">Amt. (INR)</th>																			
								</tr>								 
								<tr ng-repeat="item in getmembershipchargesbymember">
									<td>{{item.memberFirstName}} {{item.memberLastName}}</td>
									<td>{{item.rotaryYearTitle}}</td>
									<td>	
										<select ng-model="item.memberCategoryId" ng-options="item.memberCategoryId as item.memberCategoryName for item in getmembercategory" ng-change="getPaymentAmountByMemberId(item.rotaryYearId, item.memberCategoryId, item.memberId)">
											<option value="">select</option>
										</select>
									</td>
									<td class="text-right">{{item.billingAmount | number:2}}</td>																										
								</tr>																
							</table>
						</div>							
					</div>					
					<div class="row">
						<div class="col-md-4"></div>						
						<div class="col-md-4">
							<div class="alert alert-success" style="padding: 12px;" ng-hide="successMsg">
								<strong style="font-size: 16px;">Data added successfully!</strong>
							</div>
							<div class="alert alert-danger" style="padding: 12px;" ng-hide="errorMsg">
								<strong style="font-size: 16px;">Something wrong, please try again!</strong>
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>				
					<div class="row">
						<div class="col-md-12 text-center">
							<button class="btn btn-success" ng-click="addMembershipPaymentTransaction()">Save</button>
						</div>
					</div>
				</form>				
			</div>				
		</div>		
		<script>
			$('#myModal').on('hidden', function()
					{
						document.location.reload();
					})
		</script>
		<%@include file="footer.jsp" %>
	</body>
</html>
