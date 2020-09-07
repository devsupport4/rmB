<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Dues & Billing</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/due_and_billing.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<style>
			/* For Firefox */
			input[type='number'] {
			    -moz-appearance:textfield;
			}
			/* Webkit browsers like Safari and Chrome */
			input[type=number]::-webkit-inner-spin-button,
			input[type=number]::-webkit-outer-spin-button {
			    -webkit-appearance: none;
			    margin: 0;
			}
		</style>		
	</head>	
	<body ng-app="rcbs" ng-controller="dueBillingCtlr">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="background-color:#eee;">
					<div class="col-md-12" align="center" style="margin-top: 10px; margin-bottom: 10px; font-size: 18px;">
						<strong>Dues & Billing</strong>					
					</div>									
				</div>
				<form role="form">
					<div class="row">
						<div class="col-md-4" align="center"></div>					
						<div class="col-md-4" align="center">
							<div class="form-group">
								<label>Rotary Year<font color="red" size="3">*</font></label>
								<select class="form-control" id="rotaryyearid" ng-model="rotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear">
									<option value="">Select</option>									
								</select>
							</div>						
						</div>
						<div class="col-md-4" align="center"></div>						
					</div>			
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<strong>Membership Type</strong>
							</div>							
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<strong>Membership Amout</strong>
							</div>							
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<strong>Currency</strong>
							</div>							
						</div>
					</div>
					<div class="row" ng-repeat="item in getmembercategory">
						<div class="col-md-4">
							<div class="form-group">
								{{item.memberCategoryName}}
							</div>							
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<input type="number" class="form-control" id="billingamount" ng-model="item.billingAmount" style="text-align: right;">
							</div>							
						</div>
						<div class="col-md-3">
							<div class="form-group">								
								<select class="form-control" id="currencyid" ng-model="item.currencyId" ng-options="item.currencyId as item.currencyCode for item in currencies" ng-init="item.currencyId = 1">
									<option value="">Select</option>									
								</select>
							</div>						
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
							<button class="btn btn-success" type="submit" ng-click="generateBillingDraft()">Generate Draft</button>
							<button class="btn btn-danger" type="submit" onclick="javascript:window.location.reload();">Reset</button>
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
