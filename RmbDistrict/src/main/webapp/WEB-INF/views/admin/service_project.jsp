<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Service Project</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<!-- CKEditor Start-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>
		<!-- CKEditor End-->
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
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
	</head>	
	<body ng-app="rcbs" ng-controller="serviceProjectCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-5" align="left">
						<h3>Manage Service Project</h3>					
					</div>
					<div class="col-md-3" align="center">
						<div class="form-group" style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-3" align="right" style="margin-top:15px">
						<div class="form-group">
							<a href="add_service_project" class="btn btn-success btn-default" title="Add Service Project"><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
						</div>
					</div>
					<div class="col-md-1" align="right" style="margin-top:15px">
						<div class="form-group">
							<select ng-model="pageSize" id="pageSize" class="form-control">
								<option value="25">25</option>
								<option value="50">50</option>
								<option value="75">75</option>
								<option value="100">100</option>
							</select>
						</div>
					</div>					
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteServiceProject()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>										
										<th width="45%">Service Project Title</th>
										<th width="45%">Service Project Sub-title</th>																	
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in serviceproject | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">										
											<td data-toggle="modal" data-target="#EditServiceProject" ng-click='getServiceProjectDetail(item.serviceProjectId)'>{{item.serviceProjectTitle}}</td>
											<td data-toggle="modal" data-target="#EditServiceProject" ng-click='getServiceProjectDetail(item.serviceProjectId)'>{{item.serviceProjectSubtitle}}</td>											
											<td><input type="checkbox" ng-model="item.selected" value="{{item.serviceProjectId}}"></td>
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= serviceproject.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="EditServiceProject" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Service Project</h4>
							</div>
							<div class="modal-body">							
								<form role="form" name="event">
									<div class="row">										
										<div class="col-md-4">
											<div class="form-group">
												<label>Service Project Title<font color="red" size="3">*</font></label>									
												<input type="text" class="form-control" id="projecttitle" name="projecttitle" ng-model="projecttitle">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Service Project Subtitle</label>
												<input type="text" class="form-control" id="projectsubtitle" name="projectsubtitle" ng-model="projectsubtitle">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Service Project Subtitle</label>
												<input type="date" class="form-control" id="serviceprojectdate" name="serviceprojectdate" ng-model="serviceprojectdate">
											</div>
										</div>
										<div class="col-md-1">
											<div class="form-group">
												<label>Seq.</label>
												<input type="text" class="form-control" id="serviceprojectsequence" name="serviceprojectsequence" ng-model="serviceprojectsequence">
											</div>
										</div>									
									</div>
									<div class="row">										
										<div class="col-md-12">
											<div class="form-group">
												<label>Description</label>									
												<textarea cols="100"  id="description" name="description" ng-model = "serviceprojectdescription" rows="50"></textarea>
											</div>
										</div>																			
									</div>
									<div class="row">
										<div class="col-md-3">								
											<div class="form-group">
												<label>Sequence<font color="red" size="3">*</font></label>
												<input type="text" class="form-control" id="sequence" name="sequence" ng-model="sequence">									
											</div>								
										</div>
										<div class="col-md-4">								
											<div class="form-group">
												<label>Service Project Image Title<font color="red" size="3">*</font></label>
												<input type="text" class="form-control" id="imagetitle" name="imagetitle" ng-model="imagetitle">									
											</div>								
										</div>
										<div class="col-md-4">								
											<div class="form-group">
												<label>Image<font color="red" size="3">*</font></label>
												<input type="file" class="form-control" id="serviceprojectimage" name="serviceprojectimage" ng-model="serviceprojectimage">									
											</div>								
										</div>
										<div class="col-md-1">								
											<div class="form-group">
												<label>&nbsp;</label>
												<a type="submit" id="Add" value="Add" name="Add" ng-click="addImageRowNew()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
											</div>								
										</div>														
									</div>
									<div class="row">
										<div class="col-md-12">
											<table class="table table-striped" width="100%">
												<tr>
													<th>Sequence</th>
													<th>Image Title</th>
													<th>Image</th>																	
													<th></th>								
												</tr>
												<tr ng-repeat="item in serviceprojectrelatedimages">
													<td>{{item.sequence}}</td>								
													<td>{{item.imageTitle}}</td>
													<td><img ng-src="{{item.image}}" class="img-responsive" alt="image" style="height:40px; width: 40px;" ng-show="item.image != null"></td>
													<td><a class="btn btn-danger btn-sm" ng-click="removeImageRow1(item.imageTitle)" ng-if="item.imageTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
												</tr>
												<tr ng-repeat="item in serviceprojectrelatedimagesnew" ng-show="item.imageTitle!=null">
												<td>{{item.sequence}}</td>								
												<td>{{item.imageTitle}}</td>
												<td></td>								
												<td><a class="btn btn-danger btn-sm" ng-click="removeImageRowNew(item.imageTitle)" ng-show="item.imageTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
											</tr>
											</table>
										</div>							
									</div>														
								</form>							
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="editServiceProject(serviceprojectid)" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
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
		
		<!-- Script For CKEditor Start -->
		<script>
			//Initialize the Editor
			initEditor();		
			//Replace the <textarea id="editor1"> with an CKEditor instance.  
			function initEditor()
			{
				CKEDITOR.replace( 'description',
				{
					pluginsLoaded: function( evt ) 
					{
			 			var doc = CKEDITOR.document, ed = evt.editor;
			 			if ( !ed.getCommand( 'bold' ) )
			  				doc.getById( 'exec-bold' ).hide();
			 			if ( !ed.getCommand( 'link' ) )
			  				doc.getById( 'exec-link' ).hide();
			 			}
				});				
			}			
		</script>
		<!-- Script For CKEditor End -->
		<%@include file="footer.jsp" %>
	</body>
</html>
