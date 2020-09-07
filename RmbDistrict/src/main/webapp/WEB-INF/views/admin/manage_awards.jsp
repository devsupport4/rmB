<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Awards & Recognition</title>
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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">		
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
		<script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
		<script src="https://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
		<script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
		<script>
			webshims.setOptions('forms-ext', {types: 'date'});
			webshims.polyfill('forms forms-ext');
		</script>
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
		<style>
			.gallery
			{
			    display: inline-block;
			    margin-top: 20px;
			}
		</style>
		<script>
			$(document).ready(function(){
			    //FANCYBOX
			    //https://github.com/fancyapps/fancyBox
			    $(".fancybox").fancybox({
			        openEffect: "none",
			        closeEffect: "none"
			    });
			});
		</script>
	</head>	
	<body ng-app="rcbs" ng-controller="awardCtrl">
		<%@include file="header.jsp" %>
		<div class="container text-center">
			<h3 style="color:#db3615">Manage Awards / Recognition</h3>      
		</div>
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-12">
						<table width="100%">
							<tr>
								<td width="5%">
									Shows
								</td>
								<td width="8%">
									<div class="form-group" style="margin-top:15px">
										<select ng-model="pageSize" id="pageSize" class="form-control">
									        <option value="5">5</option>
									        <option value="10">10</option>
									        <option value="15">15</option>
									        <option value="20">20</option>
									     </select>
									</div>
								</td>
								<td width="2%"></td>
								<td width="20%">
									Entries
								</td>
								<td width="5%">
									Search: 
								</td>
								<td width="30%">
									<div class="form-group"style="margin-top:15px">
										<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
									</div>
								</td>
								<td width="15%"></td>
								<td width="15%">
									<div class="form-group" style="margin-top:15px;">
										<a href="add_award" class="btn btn-success btn-default">Add Awards / Recognition &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
									</div> 
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteaward()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>
										<th width="50%">Award Title</th>
										<th width="30%">Award Subtitle</th>
										<th width="15%">Image</th>
										<th width="5%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in awards | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize" style="cursor:pointer;cursor:hand">
											<td data-toggle="modal" data-target="#myModal" ng-click='getaward(item.awardId)'>{{item.awardTitle}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getaward(item.awardId)'>{{item.awardSubtitle}}</td>
											<td data-toggle="modal" data-target="#myModal2" title="Click here to view all images" ng-click='getaward(item.awardId)'><span class="glyphicon glyphicon-picture"></span></td>
											<td><input type="checkbox" ng-model="item.selected" value="{{item.awardId}}"></td>
										</tr>
									</tbody>
								</table>
								<div class="form-group" style="float:right; margin-right:10px">
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= awards.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<div class="container">
				<div class="modal fade" id="myModal2" role="dialog" tabindex="-1">
					<div class="modal-dialog">
					<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Awards Images</h4>
							</div>
							<div class="modal-body">
								<table class="table table-striped">
									<tr class='list-group gallery'>
										<td ng-repeat="item in awardimage" style="float: left;">
											<a class="thumbnail fancybox" rel="ligthbox" href="{{item.image}}" ng-if="item.image != null && item.image != ''">
												<img class="img-responsive" alt="" src="{{item.image}}" style="height:70px; width: 70px;" />
											</a>
										</td>
									</tr>
								</table><br>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="myModal" role="dialog" tabindex="-1">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Update Award / Recognition</h4>
							</div>
							<form role="form" ng-submit="editAward(awardid)">
								<div class="modal-body">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>Rotary Year<font color="red" size="3">*</font></label> 
														<select class="form-control" id="rotaryyearid" name="rotaryyearid" ng-model="rotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear">
															<option value="">Rotary Year</option>													
														</select>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Title<font color="red" size="3">*</font></label>
														<input type="text" class="form-control" id="awardtitle" name="awardtitle" ng-model="awardtitle">		
													</div>
												</div>							
											</div>
											<div class="row">							
												<div class="col-md-6">
													<div class="form-group">
														<label>Sub-Title</label>
														<input type="text" class="form-control" id="awardsubtitle" name="awardsubtitle" ng-model="awardsubtitle">		
													</div>
												</div>	
												<div class="col-md-6">
													<div class="form-group">
														<label>Award Date</label>
														<input type="date" class="form-control" id="awarddate" name="awarddate" ng-model="awarddate">		
													</div>
												</div>						
											</div>
											<div class="row">							
												<div class="col-md-12">
													<div class="form-group">
														<label>Description</label>
														<textarea rows="5" cols="5" class="form-control" id="description" name="description" ng-model="description"></textarea>		
													</div>
												</div>							
											</div>
											<div class="well">
												<div class="row">
													<div class="col-md-3">
														<input type="text" placeholder="Image Title" id="imagetitle" name="imagetitle" ng-model="imagetitle" class="form-control"/>
													</div>
													<div class="col-md-5">
														<textarea class="form-control" id="imagedescription" name="imagedescription" ng-model="imagedescription"></textarea>
													</div>
													<div class="col-md-3">
														<input type="file" multiple  id="image" name="image" ng-model="image" class="form-control"/>
													</div>
													<div class="col-md-1">
														<a class="btn btn-primary" ng-click="addAwardImageRowEdit()">ADD</a>
													</div>
												</div>
												<table class="table table-hover">
													<thead>
														<tr>
															<th>Image Title</th>
															<th>Description</th>
															<th><i class="fa fa-trash"></i></th>
														</tr>
													</thead>
													<tr ng-repeat="item in awardimage">
														<td>{{item.imageTitle}}</td>
														<td>{{item.description}}</td>
														<td><a ng-click="removeRow1(item.imageTitle)" style="cursor: pointer;"><i class="fa fa-trash"></i></a></td>
													</tr>
													<tr ng-repeat="item in awardimagenew" ng-show="item.imageTitle != null">
														<td>{{item.imageTitle}}</td>
														<td>{{item.imageDescription}}</td>
														<td><a ng-click="removeAwardImageRowEdit(item.imageTitle)" style="cursor: pointer;"><i class="fa fa-trash"></i></a></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-success">Update <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
								</div>
							</form>
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
