<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Members Committee</title>
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
	</head>	
	<body ng-app="rcbs" ng-controller="committeeCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="left">
						<h3>Manage Members Committee</h3>					
					</div>
					<div class="col-md-4" align="center">
						<div class="form-group" style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-2" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="#" data-toggle="modal" data-target="#AddModal" class="btn btn-success btn-default" title="Add Board Of Directors"><span class="glyphicon glyphicon-plus"></span></a>
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
						<form role="form" ng-submit="deleteMembersCommittee()">
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
									<!-- | startFrom:currentPage*pageSize | limitTo:pageSize -->
										<tr ng-repeat="item in memberscommittee | filter:search "  style="cursor:pointer;cursor:hand">					
											<td data-toggle="modal" data-target="#EditModal" ng-click='getMembersCommitteeDetail(item.membersCommitteeId)'>{{item.sequence}}</td>					
											<td data-toggle="modal" data-target="#EditModal" ng-click='getMembersCommitteeDetail(item.membersCommitteeId)'>{{item.rotaryYearTitle}} </td>
											<td data-toggle="modal" data-target="#EditModal" ng-click='getMembersCommitteeDetail(item.membersCommitteeId)'>{{item.designation}}</td>
											<td data-toggle="modal" data-target="#EditModal" ng-click='getMembersCommitteeDetail(item.membersCommitteeId)'>{{item.memberFirstName}} {{item.memberLastName}}</td>
											<td><input type="checkbox" ng-model="item.selected" value="{{item.membersCommitteeId}}"></td>
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= memberscommittee.length/pageSize - 1" ng-click="currentPage=currentPage+1">
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
								<h4 class="modal-title">Edit Members Committee</h4>
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
												<label>Fellowship Name<font color="red" size="3">*</font></label>									
													<select ng-model="fellowship_id" id="fellowship_id" name="fellowship_name"  class="form-control input-lg2">
														<option value=""selected>Select Fellowship Name</option>
														<option ng-repeat="items in getAllFellowshipNameList" value="{{items.fellowship_id}}">{{items.fellowship_name}}</option>
													</select>

											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Member<font color="red" size="3">*</font></label>
												<select class="form-control" id="memberid" name="memberid" ng-model="memberid" ng-options="item.memberId as item.memberFirstName for item in getmember">
													<option value="">Select Member</option>													
												</select>
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editMembersCommittee(memberscommitteeid)" class="btn btn-success">Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
				
				
			<!-- // ---------------------------------------------------------------- add Member ------------------------->
				<div class="modal fade" id="AddModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Members Committee</h4>
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
										<div class="col-md-5">
											<div class="form-group">
												<label>Fellowship Name<font color="red" size="3">*</font></label>									
													<select ng-model="fellowship_id" id="fellowship_id" name="fellowship_name"  ng-change="getmember(fellowship_id)" class="form-control input-lg2">
														<option value=""selected>Select Fellowship Name</option>
														<option ng-repeat="items in getAllFellowshipNameList"  value="{{items.fellowship_id}}">{{items.fellowship_name}}</option>
													</select>

											</div>
										</div>
										
										<div class="col-md-5">
											<div class="form-group">
												<label>Member Name<font color="red" size="3">*</font></label>									
													<select ng-model="addmemberid" id="fellowship_name" name="fellowship_name" class="form-control input-lg2">
														<option value=""selected>Member Name</option>
														<option ng-repeat="items in getAllMemberNamedata | filter: { fellowship_id: fellowship_id}" value="{{items.member_id}}">{{items.firstName}}{{items.lastName}}</option>
													</select>

											</div>
										</div>
					
									<!--	<div class="col-md-3">
											<div class="form-group">
												<label>Fellowship Name<font color="red" size="3">*</font></label>									
													<select ng-model="fellowship_id" id="fellowship_id" name="fellowship_name"  class="form-control input-lg2">
														<option value=""selected>Select Fellowship Name</option>
														<option ng-repeat="items in getAllFellowshipNameList" value="{{items.fellowship_id}}">{{items.fellowship_name}}</option>
													</select>

											</div>
										</div>
										 <div class="col-md-3">
											<div class="form-group">
												<label>Member<font color="red" size="3">*</font></label>
												<select class="form-control" id="memberid" name="memberid" ng-model="addmemberid" ng-options="item.memberId as item.memberFirstName for item in getmember">
													<option value="">Select Member</option>													
												</select>
											</div>
										</div>	 -->	
										
														
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="addMembersCommittee()" class="btn btn-success">Submit</button>
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
