<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Members</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
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
	<body ng-app="rcbs" ng-controller="memberCtrl" ng-cloak ng-init="<%=session.getAttribute("fellowshipId") %>">
		<%@include file="header.jsp" %>		
		<section>
			<div class="container">
				<div class="row">
					<div class="board">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="membership">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
											<div class="row" style="color: #333;">
												<div class="col-md-1" style="font-size: 20px;">
													
														<span><label>Shows</label> </span>
												
												</div>
												<div class="col-md-1">
													
														<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changepage()">
														</select>
													
												</div>
												<div class="col-md-2 " ng-show="<%=session.getAttribute("roleId") %> == '2'">
												
													
												</div>
												<div class="col-md-3 " style="font-size: 25px;">
													<span class="glyphicon glyphicon-user"> Manage Members </span>													
												</div>
												<div class="col-md-1" ng-show="<%=session.getAttribute("roleId") %> == '2'">
												
													
												</div>
												 <div class="col-md-3 " ng-show="<%=session.getAttribute("roleId") %> == '1'">
												
													<select ng-model="fellowship_id" id="fellowship_id" name="fellowship_name" ng-change="getmember(fellowship_id)" class="form-control input-lg2">
														<option value=""selected>Select Fellowship Name</option>
														<option ng-repeat="items in getAllFellowshipNameList" value="{{items.fellowship_id}}">{{items.fellowship_name}}</option>
													</select>
												</div>
												
												
												<div class="col-md-3">
													<div class="input-group">
														<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Filter Text" ng-keyup="$event.keyCode == 13 ? searchmember() : null"/>
														<a class="input-group-addon" ng-click="searchmember()" style="cursor: pointer;">
															<i class="fa fa-search"></i>
														</a>
													</div>
												</div>
												
												<div class="col-md-1" align="right">
													<div class="form-group">
														<a href="add_member" class="btn btn-success" data-toggle="tooltip" title="Add Member"><i class="fa fa-plus" aria-hidden="true"></i></a>														
													</div>
												</div>
											</div>
										</div>
										<div class="panel-body">
											<div class="panel panel-success" ng-show="a == 0">
												<div class="panel-heading text-center">
													Loading Data Please Wait...
													<i class="fa fa-refresh fa-spin" style="font-size:24px"></i>
												</div>
											</div>											
											<div class="table-responsive">
												<table class="table table-bordered">
													<tbody >
														<tr style="color:black">
															<th style="color:black" width="5%">#</th>
															<th style="color:black" width="25%">Fellowship Name / Club Name</th>
															<th style="color:black" width="15%">MEMBERSHIP #</th>
															<th style="color:black" width="40%">NAME</th>
														<!-- 	 <th style="color:black" width="27%"></th>  -->
															<th style="color:black" width="10%">MOBILE / EMAIL ID</th>
															<!-- <th style="color:black" width="16%">EMAIL ID</th>	 -->														
															<th style="color:black; text-align: center;" title="Active Or Inactive" width="3%">A/I</th>
															<th style="color:black; text-align: center;" width="2%"><span class="glyphicon glyphicon-trash"></span></th>
														</tr>
														<tr ng-repeat="item in getmember1  | filter: { fellowship_id: fellowship_id}" style="cursor:pointer;cursor:hand">
															<td style="color:black;" ng-click='redirectmemberdetail(item.memberId)'>{{$index + 1}}</td>
															<td style="color:black;white-space: nowrap;" ng-click='redirectmemberdetail(item.memberId)'>{{item.fellowship_name}} <br> {{item.clubName}} </td>
															<td style="color:black;" ng-click='redirectmemberdetail(item.memberId)'>{{item.membershipNumber}}</td>
															<td style="color:black;" ng-click='redirectmemberdetail(item.memberId)'>{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>
															 <!-- <td style="color:black;" ng-click='redirectmemberdetail(item.memberId)'></td>  -->
															<td style="color:black;" ng-click='redirectmemberdetail(item.memberId)'>{{item.memberMobileNumber}} <br> {{item.memberEmail}}</td>
															<!-- <td style="color:black;" ng-click='redirectmemberdetail(item.memberId)'>{{item.memberEmail}}</td> -->
															<td ng-show="item.status=='y'" style="text-align: center;" ng-click="setActiveOrInactiveMember(item.memberId,item.status)"><i class="fa fa-check-square" aria-hidden="true"></i></td>
															<td ng-show="item.status=='i'" style="text-align: center;"ng-click=" setActiveOrInactiveMember(item.memberId,item.status)"><input type="checkbox" ></td>
															<td style="text-align: center;"><input type="checkbox" ng-model="item.selected" value="{{item.memberId}}"></td>
															
														</tr>
														<tr>
															<td colspan="7"></td>															
															<td>
																<button type="button" class="btn btn-danger btn-sm" ng-click="deletemember()">
																	<span class="glyphicon glyphicon-trash"></span> 
																</button>
															</td>
														
														</tr>
													</tbody>
												</table>
											</div>											
											<div class="clearfix"></div>
											<div class="row">
												<div class="col-md-12 text-center" >													
											   		<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="prev()">
											   			<i class="glyphicon glyphicon-step-backward"></i>
											   		</button>											   		
											   		{{currentPage+1}}											   		
											   		<button type="submit" class="btn btn-primary" ng-disabled="getmember1.length == 0" ng-click="next()">
											   			<i class="glyphicon glyphicon-step-forward"></i>
											   		</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<%@include file="footer.jsp" %>
	</body>
</html>
