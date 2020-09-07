<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Rotary Year</title>
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
	<body ng-app="rcbs" ng-controller="rotaryYearCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-3" align="left">
						<h3>Manage Rotary Year</h3>					
					</div>
					<div class="col-md-3" align="center">
						<div class="form-group" style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-3" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="#" data-toggle="modal" data-target="#AddRotaryYear" class="btn btn-success btn-default">Add Rotary Year &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
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
						<form role="form" ng-submit="deleteRotaryYear()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>										
										<th width="25%">Rotary Year Title</th>
										<th width="25%">Rotary Year Start Date</th>
										<th width="25%">Rotary Year End Date</th>	
										<th width="15%">Default Year</th>									
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in rotaryyear | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">					
											<td data-toggle="modal" data-target="#EditRotaryModal" ng-click='getRotaryYearDetail(item.rotaryYearId)'>{{item.rotaryYearTitle}}</td>					
											<td data-toggle="modal" data-target="#EditRotaryModal" ng-click='getRotaryYearDetail(item.rotaryYearId)'>{{item.yearStartDate | date: 'dd/MM/yyyy'}}</td>
											<td data-toggle="modal" data-target="#EditRotaryModal" ng-click='getRotaryYearDetail(item.rotaryYearId)'>{{item.yearEndDate | date: 'dd/MM/yyyy'}}</td>
											<td><input type="checkbox" ng-show="item.defaultYear == 'y'" checked>
											<input type="checkbox" ng-show="item.defaultYear == 'n'" ng-click="changeDefaultYear(item.rotaryYearId)"></td>
											<td><input type="checkbox" ng-model="item.selected" value="{{item.rotaryYearId}}"></td>
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= rotaryyear.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="EditRotaryModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Rotary Year</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">	
										<div class="col-md-4">
											<div class="form-group">
												<label>Year Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="yeartitle" name="yeartitle" ng-model="yeartitle" autofocus>
											</div>
										</div>									
										<div class="col-md-4">
											<div class="form-group">
												<label>Start Year<font color="red" size="3">*</font></label>									
												<input type="date" class="form-control" id="startdate" name="startdate" ng-model="startdate">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>End Date<font color="red" size="3">*</font></label>
												<input type="date" class="form-control" id="enddate" name="enddate" ng-model="enddate">
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editRotaryYear(rotaryyearid)" class="btn btn-success">Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>						
						</div>
					</div>
				</div>
				<div class="modal fade" id="AddRotaryYear" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Rotary Year</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Year Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="yeartitle" name="yeartitle" ng-model="addyeartitle" autofocus>
											</div>
										</div>										
										<div class="col-md-4">
											<div class="form-group">
												<label>Start Year<font color="red" size="3">*</font></label>									
												<input type="date" class="form-control" id="startdate" name="startdate" ng-model="addstartdate">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>End Date<font color="red" size="3">*</font></label>
												<input type="date" class="form-control" id="enddate" name="enddate" ng-model="addenddate">
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="addRotaryYear()" class="btn btn-success">Submit</button>
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
