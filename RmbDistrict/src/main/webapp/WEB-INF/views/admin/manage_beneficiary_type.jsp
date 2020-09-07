<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Beneficiary Type</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<style>
			input[type="date"]:hover::-webkit-calendar-picker-indicator {
			  color: red;
			}
			input[type="date"]:hover:after {
			  content: " Date Picker ";
			  color: #555;
			  padding-right: 5px;
			}
			input[type="date"]::-webkit-inner-spin-button {
			  /* display: none; <- Crashes Chrome on hover */
			  -webkit-appearance: none;
			  margin: 0;
			}
		</style>
	</head>	
	<body ng-app="rcbs" ng-controller="beneficiaryTypeCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-3" align="left">
						<h3>Manage Beneficiary Type</h3>					
					</div>
					<div class="col-md-3" align="center">
						<div class="form-group" style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-3" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="#" data-toggle="modal" data-target="#Add" class="btn btn-success btn-default">Add Beneficiary Type &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
						</div>
					</div>
					<div class="col-md-3" align="right" style="margin-top:15px">
						<div class="form-group">
							<select ng-model="pageSize" id="pageSize" class="form-control" style="width: 30%;">
								<option value="5">5</option>
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
							</select>
						</div>
					</div>					
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteBeneficiaryType()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>										
										<th width="90%">Beneficiary Type</th>																			
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in beneficiarytype | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">					
											<td data-toggle="modal" data-target="#Edit" ng-click='getBeneficiaryTypeDetail(item.beneficiaryTypeId)'>{{item.beneficiaryTypeTitle}}</td>					
											<td><input type="checkbox" ng-model="item.selected" value="{{item.beneficiaryTypeId}}"></td>
										</tr>
									</tbody>
								</table>
								<div class="form-group" style="float:right; margin-right:65px">
									<input type="submit" id="Delete" name="submit" class="btn btn-danger" value="Delete">
								</div>
								<div class="clearfix"></div>							
							</div>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-center" >
						<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="currentPage=currentPage-1">
			    			<i class="glyphicon glyphicon-step-backward"></i>
			    		</button>
			    		{{currentPage+1}}/{{numberOfPages()}}
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= beneficiarytype.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="Edit" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Beneficiary Type</h4>
							</div>
							<div class="modal-body">							
								<form role="form">
									<div class="row">	
										<div class="col-md-12">
											<div class="form-group">
												<label>Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="title" name="title" ng-model="beneficiarytitle" autofocus placeholder="Beneficiary Type Title">
											</div>
										</div>																				
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editBeneficiaryType(beneficiarytypeid)" class="btn btn-success">Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
				<div class="modal fade" id="Add" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Beneficiary Type</h4>
							</div>
							<div class="modal-body">							
								<form role="form">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="title" name="title" ng-model="title" autofocus>
											</div>
										</div>										
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="addBeneficiaryType()" class="btn btn-success">Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
			</div>			
		</div><br>
		
		<script>
			$('#myModal').on('hidden', function()
					{
						document.location.reload();
					})
		</script>
		<%@include file="footer.jsp" %>
	</body>
</html>
