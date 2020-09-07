<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Social Media Links</title>
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
	<body ng-app="rcbs" ng-controller="socialMediaCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="left">
						<h3>Manage Social Media Links</h3>					
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
						<form role="form" ng-submit="deleteSocialMediaLink()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>
										<th width="30%">Media Platform</th>										
										<th width="60%">Link</th>																													
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in getsocialmedialinks | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">					
											<td data-toggle="modal" data-target="#EditModal" ng-click='getSocialMediaLinkDetail(item.socialMediaLinkId)'>{{item.socialMediaPlatformTitle}}</td>					
											<td data-toggle="modal" data-target="#EditModal" ng-click='getSocialMediaLinkDetail(item.socialMediaLinkId)'>{{item.socialMediaUrl}}</td>											
											<td><input type="checkbox" ng-model="item.selected" value="{{item.socialMediaLinkId}}"></td>
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= getsocialmedialinks.length/pageSize - 1" ng-click="currentPage=currentPage+1">
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
								<h4 class="modal-title">Edit Social Media Link</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">	
										<div class="col-md-3">
											<div class="form-group">
												<label>Social Media Platform<font color="red" size="3">*</font></label> 
												<select class="form-control" id="mediaplatformtitle" name="mediaplatformtitle" ng-model="editmediaplatformtitle">
													<option value="">Select</option>
													<option value="facebook">Facebook</option>
													<option value="youtube">Youtube</option>
													<option value="twitter">Twitter</option>
													<option value="linkedin">Linked-In</option>
													<option value="flickr">Flickr</option>																									
												</select>
											</div>
										</div>										
										<div class="col-md-9">
											<div class="form-group">
												<label>Link<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="medialink" name="medialink" ng-model="editmedialink">
											</div>
										</div>																			
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editSocialMediaLink(socialmedialinkid)" class="btn btn-success">Submit</button>
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
								<h4 class="modal-title">Add Social Media Link</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label>Social Media Platform<font color="red" size="3">*</font></label> 
												<select class="form-control" id="mediaplatformtitle" name="mediaplatformtitle" ng-model="mediaplatformtitle">
													<option value="">Select</option>
													<option value="facebook">Facebook</option>
													<option value="youtube">Youtube</option>
													<option value="twitter">Twitter</option>
													<option value="linkedin">Linked-In</option>
													<option value="flickr">Flickr</option>																																							
												</select>
											</div>
										</div>										
										<div class="col-md-9">
											<div class="form-group">
												<label>Link<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="medialink" name="medialink" ng-model="medialink">
											</div>
										</div>																			
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="addSocialMediaLink()" class="btn btn-success">Submit</button>
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
