<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<title>Add Service Project</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<!-- CKEditor Start-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>
		<!-- CKEditor End-->
		<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
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
			 
			  -webkit-appearance: none;
			  margin: 0;
			}
		</style>
	</head>
	<body ng-app="rcbs" ng-controller="serviceProjectCtrl">
		<%@include file="header.jsp" %><br>		
		<div class="container"> 
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><b>Add Service Project</b></h3>
				</div>
				<div class="panel-body" style="background-color:#f2f2f2;">
					<form role="form" name="ServiceProject" enctype="multipart/form-data">
						<div class="row">										
							<div class="col-md-4">
								<div class="form-group">
									<label>Service Project Title<font color="red" size="3">*</font></label>									
									<input type="text" class="form-control" id="title" name="title" ng-model="title">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Service Project Subtitle</label>
									<input type="text" class="form-control" id="subtitle" name="subtitle" ng-model="subtitle">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Service Project Date</label>
									<input type="date" class="form-control" id="projectdate" name="projectdate" ng-model="projectdate">
								</div>
							</div>
							<div class="col-md-1">
								<div class="form-group">
									<label>Seq.</label>
									<input type="text" class="form-control" id="projectsequence" name="projectsequence" ng-model="projectsequence" ng-init="nextsequenceno()">
								</div>
							</div>									
						</div>
						<div class="row">										
							<div class="col-md-12">
								<div class="form-group">
									<label>Description</label>									
									<textarea cols="100"  id="description" name="description" rows="50"></textarea>
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
									<a type="submit" id="Add" value="Add" name="Add" ng-click="addImageRow()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
								</div>								
							</div>														
						</div>
						<div class="row">
							<div class="col-md-12">
								<table class="table table-striped" width="100%">
									<tr>
										<th>Sequence</th>
										<th>Image Title</th>																	
										<th></th>								
									</tr>
									<tr ng-repeat="item in serviceprojectimagelist">
										<td>{{item.sequence}}</td>								
										<td>{{item.imageTitle}}</td>
										<td><a class="btn btn-danger btn-sm" ng-click="removeImageRow(item.imageTitle)" ng-if="item.imageTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
									</tr>
								</table>
							</div>							
						</div>												
					</form>
				</div>
				<div class="panel-footer" align="right">
					<button type="button" ng-click="addServiceProject()" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
					<a href="service_project" class="btn btn-danger">Cancle</a>
				</div>
			</div>
		</div><br>
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
		<%@include file="footer.jsp" %><br>		
	</body>
</html>
