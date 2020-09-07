<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Service Project Beneficiary</title>
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.maskedinput.js" type="text/javascript"></script>
		<script type="text/javascript">
			jQuery(function($){
				   $("#startdate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#enddate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#startdateedit").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#enddateedit").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				});
		</script>
	</head>	
	<body ng-app="rcbs" ng-controller="serviceProjectBeneficiaryCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="left">
						<h3>Manage Service Project Beneficiary</h3>					
					</div>
					<div class="col-md-3" align="right">
						<div class="form-group" style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-3" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="#" data-toggle="modal" data-target="#AddServiceProjectBeneficiary" class="btn btn-success btn-default">Add Service Project Beneficiary &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
						</div>
					</div>
					<div class="col-md-2" align="right" style="margin-top:15px">
						<div class="form-group">
							<select ng-model="pageSize" id="pageSize" class="form-control" style="width: 50%;">
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
										<th width="100%">Service Project Title</th>
										<!-- <th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th> -->																	
									</thead>
									<tbody>
										<tr ng-repeat="item in serviceproject | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">										
											<td data-toggle="modal" data-target="#ServiceProjectBeneficiary" ng-click='getServiceProjectBeneficiary(item.serviceProjectId)'>{{item.serviceProjectTitle}}</td>
											<!-- <td><input type="checkbox" ng-model="item.selected" value="{{item.serviceProjectId}}"></td> -->
										</tr>
									</tbody>
								</table>
								<!-- <div class="form-group" style="float:right; margin-right:65px">
									<input type="submit" id="Delete" name="submit" class="btn btn-danger" value="Delete">
								</div> -->
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= serviceproject.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="AddServiceProjectBeneficiary" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Service Project Beneficiary</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="ServiceProject" enctype="multipart/form-data">
									<div class="row">										
										<div class="col-md-4">
											<div class="form-group">
												<label>Service Project<font color="red" size="3">*</font></label>
												<select id="serviceprojectid" name="serviceprojectid" ng-model="serviceprojectid" ng-options="item.serviceProjectId as item.serviceProjectTitle for item in serviceproject" class="form-control">
													<option value="">Service Project</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Beneficiary Type</label>
												<select id="beneficiarytypeid" name="beneficiarytypeid" ng-model="beneficiarytypeid" ng-options="item.beneficiaryTypeId as item.beneficiaryTypeTitle for item in beneficiarytype" class="form-control">
													<option value="">Beneficiary Type</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Beneficiary<font color="red" size="3">*</font></label>
												<select id="beneficiaryid" name="beneficiaryid" ng-model="beneficiaryid" ng-options="item.memberId as item.memberFirstName+' '+item.memberMiddleName+' '+item.memberLastName for item in getmember" class="form-control">
													<option value="">Beneficiary</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Donor</label>
												<select id="donorid" name="donorid" ng-model="donorid" ng-options="item.memberId as item.memberFirstName+' '+item.memberMiddleName+' '+item.memberLastName for item in getmember" class="form-control">
													<option value="">Beneficiary</option>
												</select>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label>Start Date</label>
												<input type="text" id="startdate" name="startdate" ng-model="startdate" class="form-control" placeholder="DD/MM/YYYY">
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label>End Date</label>
												<input type="text" id="enddate" name="enddate" ng-model="enddate" class="form-control" placeholder="DD/MM/YYYY">
											</div>
										</div>
									</div>												
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="addServiceProjectBeneficiary()" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="spin == 0"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
				<div class="modal fade" id="EditServiceProjectBeneficiary" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Service Project Benificiary</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">										
										<div class="col-md-4">
											<div class="form-group">
												<label>Service Project<font color="red" size="3">*</font></label>
												<select id="serviceprojectidedit" name="serviceprojectidedit" ng-model="serviceprojectidedit" ng-options="item.serviceProjectId as item.serviceProjectTitle for item in serviceproject" class="form-control">
													<option value="">Service Project</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Beneficiary Type</label>
												<select id="beneficiarytypeidedit" name="beneficiarytypeidedit" ng-model="beneficiarytypeidedit" ng-options="item.beneficiaryTypeId as item.beneficiaryTypeTitle for item in beneficiarytype" class="form-control">
													<option value="">Beneficiary Type</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Beneficiary<font color="red" size="3">*</font></label>
												<select id="beneficiaryidedit" name="beneficiaryidedit" ng-model="beneficiaryidedit" ng-options="item.memberId as item.memberFirstName+' '+item.memberMiddleName+' '+item.memberLastName for item in getmember" class="form-control">
													<option value="">Beneficiary</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Donor</label>
												<select id="donoridedit" name="donoridedit" ng-model="donoridedit" ng-options="item.memberId as item.memberFirstName+' '+item.memberMiddleName+' '+item.memberLastName for item in getmember" class="form-control">
													<option value="">Beneficiary</option>
												</select>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label>Start Date</label>
												<input type="text" id="startdateedit" name="startdateedit" ng-model="startdateedit" class="form-control" placeholder="DD/MM/YYYY">
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label>End Date</label>
												<input type="text" id="enddateedit" name="enddateedit" ng-model="enddateedit" class="form-control" placeholder="DD/MM/YYYY">
											</div>
										</div>
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editServiceProjectBeneficiary(serviceprojectbeneficiaryid)" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="spin == 0"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
			</div>						
		</div><br>
		<div class="container">			
				<div class="modal fade" id="ServiceProjectBeneficiary" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Service Project Beneficiary - {{serviceprojectname}}</h4>
							</div>
							<div class="modal-body">							
								<div class="row">
									<div class="col-md-12">
										<form role="form" ng-submit="deleteServiceProjectBeneficiary()">
											<div class="table-responsive">
												<table id="mytable" class="table table-bordred table-striped">
													<thead>
														<th width="21%">Benificiary Name</th>
														<th width="22%">Benificiary Type</th>
														<th width="22%">Donor Name</th>
														<th width="14%">Start Date</th>
														<th width="14%">End Date</th>
														<th width="7%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>																	
													</thead>
													<tbody>
														<tr ng-repeat="item in serviceprojectbeneficiary"  style="cursor:pointer;cursor:hand">
															<td data-toggle="modal" data-target="#EditServiceProjectBeneficiary" ng-click='getServiceProjectBeneficiary1(item.serviceProjectBeneficiaryId)'>{{item.benificiaryFirstName}} {{item.benificiaryMiddleName}} {{item.benificiaryLastName}}</td>
															<td data-toggle="modal" data-target="#EditServiceProjectBeneficiary" ng-click='getServiceProjectBeneficiary1(item.serviceProjectBeneficiaryId)'>{{item.beneficiaryTypeTitle}}</td>
															<td data-toggle="modal" data-target="#EditServiceProjectBeneficiary" ng-click='getServiceProjectBeneficiary1(item.serviceProjectBeneficiaryId)'>{{item.donorFirstName}} {{item.donorMiddleName}} {{item.donorLastName}}</td>
															<td data-toggle="modal" data-target="#EditServiceProjectBeneficiary" ng-click='getServiceProjectBeneficiary1(item.serviceProjectBeneficiaryId)'>{{item.startDate | date:'dd-MM-yyyy'}}</td>
															<td data-toggle="modal" data-target="#EditServiceProjectBeneficiary" ng-click='getServiceProjectBeneficiary1(item.serviceProjectBeneficiaryId)'>{{item.endDate | date:'dd-MM-yyyy'}}</td>
															<td><input type="checkbox" ng-model="item.selected" value="{{item.serviceProjectBeneficiaryId}}"></td>
														</tr>
													</tbody>
												</table>
												<div class="form-group" style="float:right; margin-right:25px">
													<!-- <input type="submit" id="Delete" name="submit" class="btn btn-danger" value="Delete"> -->
													<button type="submit" class="btn btn-danger btn-sm">
														<span class="glyphicon glyphicon-trash"></span> 
													</button>
												</div>
												<div class="clearfix"></div>							
											</div>
										</form>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
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
