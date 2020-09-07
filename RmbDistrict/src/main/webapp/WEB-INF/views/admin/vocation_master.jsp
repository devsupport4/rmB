<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Vocation Master</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/vocation_master.js"></script>
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
	<body ng-app="rcbs" ng-controller="VocationCtrl" ng-cloak ng-init="getVocation()">
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
												<div class="col-md-1">
													<div class="form-group">
														<label>Shows</label>
													</div>
												</div>
												<div class="col-md-1">
													<div class="form-group" style="margin-top:0px;">
														<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changepage()">
														</select>
													</div>
												</div>
												<div class="col-md-1">
												</div>
												<div class="col-md-5 text-center">
													 <h3>Vocation Master</h3>													
												</div>
												<div class="col-md-3">
												</div>
												<div class="col-md-1" align="right">
													<div class="form-group">
														<a href="#" class="btn btn-success" data-toggle="modal" data-target="#AddVocation" title="Add Vocation"><i class="fa fa-plus" aria-hidden="true"></i></a>														
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
															<th style="color:black; text-align: center;" width="10%">#</th>
															<th style="color:black" width="30%">Title</th>
															<th style="color:black" width="50%">Description</th>
															<th style="color:black; text-align: center;" width="10%"><span class="glyphicon glyphicon-trash"></span></th>
														</tr>
														<tr ng-repeat="item in getVocation" style="cursor:pointer;cursor:hand">
															<td style="color:black; text-align: center;" >{{$index + 1}}</td>
															<td style="color:black;">{{item.VocationTitle}}</td>
															<td style="color:black;">{{item.VocationDesc}}</td>
															<td style="text-align: center;"><input type="checkbox" ng-model="item.selected" value="{{item.VocationId}}"></td>
															
														</tr>
														<tr>
															<td colspan="3"></td>															
															<td style="text-align: center;">
																<button type="button" class="btn btn-danger btn-sm" ng-click="deleteVocation()">
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
		<div class="container">
				<div class="modal fade" id="AddVocation" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Vocation</h4>
							</div>
							<div class="modal-body">
								<form>
									<div class="row">
										<div class="col-md-12">
											<lable>Title<font color="red" size="3">*</font></lable>
											<input type="text" class="form-control" name="title" id="title" ng-model="title">
											<label style="color:red;" ng-if="titleError == 1">Title is mandatory</label>														
										</div>
									</div>	
									<div class="row" style="padding-top:5px;">
										<div class="col-md-12">
											<lable>Description</lable>
											<textarea rows="4" class="form-control" name="description" id="description" ng-model="description"></textarea>
										</div>
									</div>
									<h4 class="classic-title"></h4>															
									<div class="row">
										<div class="col-md-12 text-center">
											<button class="btn btn-success" ng-click="addVocation()"><i class="fa fa-refresh fa-spin" ng-show="spin == 1"></i> Add </button>
										</div>													
									</div>												
								</form>																							
							</div>						
						</div>
					</div>
				</div>
			</div>
		<%@include file="footer.jsp" %>
	</body>
</html>
