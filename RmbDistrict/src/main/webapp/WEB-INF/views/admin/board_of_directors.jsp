<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Board Of Directors</title>
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
			
			  -webkit-appearance: none;
			  margin: 0;
			}
		</style>
	</head>	
	<body ng-app="rcbs" ng-controller="boardOfDirectorsCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="left">
						<h3>Manage Board of Directors</h3>					
					</div>
					<div class="col-md-4" align="center">
						<div class="form-group" style="margin-top:15px">
							<input ng-model="search1" id="search1" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-2" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="#" data-toggle="modal" data-target="#AddModal" class="btn btn-success btn-default" title="Add Board Of Directors"><span class="glyphicon glyphicon-plus"></span></a>
						</div>
					</div>
					<div class="col-md-2" align="right" style="margin-top:15px">
						<div class="form-group">
							<select ng-model="pageSize1" id="pageSize1" class="form-control" style="width: 50%;">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="50">50</option>
							</select>
						</div>
					</div>					
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteBoardOfDirectors()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>
										<th width="15%">Sequence</th>										
										<th width="25%">Rotary Year</th>
										<th width="25%">Designation</th>
										<th width="25%">Member Name</th>																			
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in boardofdirectors | filter:search1 | startFrom:currentPage1*pageSize1 | limitTo:pageSize1"  style="cursor:pointer;cursor:hand">					
											<td data-toggle="modal" data-target="#EditModal" ng-click='getBoardOfDirectorsDetail(item.boardOfDirectorsId)'>{{item.sequence}}</td>					
											<td data-toggle="modal" data-target="#EditModal" ng-click='getBoardOfDirectorsDetail(item.boardOfDirectorsId)'>{{item.rotaryYearTitle}}</td>
											<td data-toggle="modal" data-target="#EditModal" ng-click='getBoardOfDirectorsDetail(item.boardOfDirectorsId)'>{{item.designation}}</td>
											<td data-toggle="modal" data-target="#EditModal" ng-click='getBoardOfDirectorsDetail(item.boardOfDirectorsId)'>{{item.firstName}} {{item.lastName}}</td>
											<td><input type="checkbox" ng-model="item.selected" value="{{item.boardOfDirectorsId}}"></td>
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
						<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage1 <= 0" ng-click="currentPage1=currentPage1-1">
			    			<i class="glyphicon glyphicon-step-backward"></i>
			    		</button>
			    		{{currentPage1+1}}/{{numberOfPages1()}}
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage1 >= boardofdirectors.length/pageSize1 - 1" ng-click="currentPage1=currentPage1+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="EditModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Board Of Directors</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">	
										<div class="col-md-3">
											<div class="form-group">
												<label>Rotary Year<font color="red" size="3">*</font></label> 
												<select class="form-control" id="rotaryyearid" name="rotaryyearid" ng-model="rotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear">
													<option value="">Rotary Year</option>													
												</select>
											</div>
										</div>										
										<div class="col-md-3">
											<div class="form-group">
												<label>Sequence<small>(Numbers only)</small><font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="sequence" name="sequence" ng-model="sequence" allow-decimal-numbers>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Designation<font color="red" size="3">*</font></label>
												<input type="text" class="form-control" id="designation" name="designation" ng-model="designation">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Member<font color="red" size="3">*</font></label>
												<select class="form-control" id="editid" name="editid" ng-model="editid" ng-options="item.id as item.firstName+' '+item.lastName  for item in memberAndMemberFamilyList">
													<option value="">Select Member</option>													
												</select>
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editBoardOfDirectors(rotaryyearid)" class="btn btn-success">Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
				<div class="modal fade" id="AddModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Board Of Directors</h4>
							</div>
							<div class="modal-body" ng-init="getCurrentDefaultYear()">							
								<form role="form" name="event">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label>Rotary Year<font color="red" size="3">*</font></label> 
												<select class="form-control" id="rotaryyearid" name="rotaryyearid" ng-model="addrotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear">
													<option value="">Rotary Year</option>													
												</select>
											</div>
										</div>										
										<div class="col-md-3">
											<div class="form-group">
												<label>Sequence<small>(Numbers only)</small><font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="sequence" name="sequence" ng-model="addsequence" allow-decimal-numbers>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Designation<font color="red" size="3">*</font></label>
												<input type="text" class="form-control" id="designation" name="designation" ng-model="adddesignation">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Member<font color="red" size="3">*</font></label>
												<select class="form-control" id="addid" name="addid" ng-model="addid" ng-options="item.id as item.firstName+' '+item.lastName  for item in memberAndMemberFamilyList">
													<option value="">Select Member</option>													
												</select>
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="addBoardOfDirectors()" class="btn btn-success">Submit</button>
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
