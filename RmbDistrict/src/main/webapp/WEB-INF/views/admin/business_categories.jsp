<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Business Categories</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/business_category.js"></script>
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
	<body ng-app="rcbs" ng-controller="businessCategoryCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="left">
						<h3>Manage Business Categories</h3>					
					</div>
					<div class="col-md-4" align="center" style="margin-top:15px">
						<div class="input-group">
							<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Filter Text" ng-keyup="$event.keyCode == 13 ? searchRecord() : null"/>
							<a class="input-group-addon" ng-click="searchRecord()" style="cursor: pointer;">
								<span class="glyphicon glyphicon-search"></span>
							</a>
						</div>
					</div>
					<div class="col-md-2" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="#" data-toggle="modal" data-target="#AddBusinessCategory" class="btn btn-success btn-default">Add &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
						</div>
					</div>
					<div class="col-md-2" align="right" style="margin-top:15px">
						<div class="form-group">
							<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changepage()"></select>
						</div>
					</div>					
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteBusinessCategory()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>										
										<th width="25%">Category Title</th>
										<th width="65%">Description</th>																			
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in businesscategories" style="cursor:pointer;cursor:hand">					
											<td data-toggle="modal" data-target="#EditBusinessCategory" ng-click='getBusinessCategoryDetail(item.businessCategoryId)'>{{item.businessCategoryName}}</td>					
											<td data-toggle="modal" data-target="#EditBusinessCategory" ng-click='getBusinessCategoryDetail(item.businessCategoryId)'>{{item.businessCategoryDescription}}</td>											
											<td><input type="checkbox" ng-model="item.selected" value="{{item.businessCategoryId}}"></td>
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
				<div class="well well-sm">
					<div class="row">
						<div class="col col-lg-8 col-md-8 col-sm-7 col-xs-7 text-left" style="margin: 10px 0;">
							<div class="hint-text">Showing <b>{{startindex+1}}-{{businesscategories.length+startindex}}</b> out of <b>{{allbusinesscategories.length}}</b> entries</div>
						</div>
						<div class="col col-lg-4 col-md-4 col-sm-5 text-center">
							<ul class="pagination hidden-xs pull-right" style="margin: 5px 0;">
								<li class="disabled" ng-show="currentPage <= 0"><a href="#">Previous</a></li>
								<li class="page-item" ng-show="currentPage > 0"><a href="#" ng-click="prev()">Previous</a></li>
								<li class="active"><a href="#">{{currentPage+1}}</a></li>									
								<li class="disabled" ng-show="businesscategories.length+startindex == allbusinesscategories.length"><a href="#" class="page-link">Next</a></li>
								<li class="page-item" ng-show="businesscategories.length+startindex != allbusinesscategories.length"><a href="#" class="page-link" ng-click="next()">Next</a></li>
							</ul>
							<ul class="pagination visible-xs pull-right" style="margin: 10px 0;">
								<li class="disabled" ng-show="currentPage <= 0"><a href="#"><span class="glyphicon glyphicon-arrow-left"></span></a></li>
								<li ng-show="currentPage > 0"><a href="#"><span class="glyphicon glyphicon-arrow-left"></span></a></li>
								<li class="disabled" ng-show="businesscategories.length+startindex == allbusinesscategories.length"><a href="#"><span class="glyphicon glyphicon-arrow-right"></span></a></li>
								<li ng-show="businesscategories.length+startindex != allbusinesscategories.length"><a href="#"><span class="glyphicon glyphicon-arrow-right"></span></a></li>
							</ul>
						</div>
					</div>
				</div>				
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="EditBusinessCategory" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Rotary Year</h4>
							</div>
							<div class="modal-body">							
								<form role="form">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Business Category Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="businesscategorytitle" name="businesscategorytitle" ng-model="editbusinesscategorytitle" placeholder="Business Category Title" autofocus>
											</div>
										</div>
									</div>
									<div class="row">								
										<div class="col-md-12">
											<div class="form-group">
												<label>Description</label>
												<textarea rows="3" class="form-control" name="businesscategorydescription" id="businesscategorydescription" ng-model="editbusinesscategorydescription" placeholder="Description"></textarea>
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 text-left" align="right">
									<div class="alert alert-success" style="margin-bottom: 0px; padding: 12px;" ng-hide="successMsg">
										<strong style="font-size: 16px;">Data updated successfully!</strong>
									</div>
									<div class="alert alert-danger" style="margin-bottom: 0px; padding: 12px;" ng-hide="errorMsg">
										<strong style="font-size: 16px;">Something wrong, please try again!</strong>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<button type="button" ng-click="editBusinessCategory(businesscategoryid)" class="btn btn-success">Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>								
							</div>													
						</div>
					</div>
				</div>
				<div class="modal fade" id="AddBusinessCategory" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Business Category</h4>
							</div>
							<div class="modal-body">							
								<form role="form">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Business Category Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="businesscategorytitle" name="businesscategorytitle" ng-model="businesscategorytitle" placeholder="Business Category Title" autofocus>
											</div>
										</div>
									</div>
									<div class="row">								
										<div class="col-md-12">
											<div class="form-group">
												<label>Description</label>
												<textarea rows="3" class="form-control" name="businesscategorydescription" id="businesscategorydescription" ng-model="businesscategorydescription" placeholder="Description"></textarea>
											</div>
										</div>									
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 text-left" align="right">
									<div class="alert alert-success" style="margin-bottom: 0px; padding: 12px;" ng-hide="successMsg">
										<strong style="font-size: 16px;">Data added successfully!</strong>
									</div>
									<div class="alert alert-danger" style="margin-bottom: 0px; padding: 12px;" ng-hide="errorMsg">
										<strong style="font-size: 16px;">Something wrong, please try again!</strong>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<button type="button" ng-click="addBusinessCategory()" class="btn btn-success">Save</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>								
							</div>						
						</div>
					</div>
				</div>
			</div>			
		</div>		
		<%@include file="footer.jsp" %>
	</body>
</html>
