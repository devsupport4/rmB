<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<title>Add Photo Album</title>
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
			
			  -webkit-appearance: none;
			  margin: 0;
			}
		</style>
	</head>
	<body ng-app="rcbs" ng-controller="albumCtrl">
		<%@include file="header.jsp" %><br>		
		<div class="container"> 
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><b>Add Photo Album</b></h3>
				</div>
				<div class="panel-body" style="background-color:#f2f2f2;">
					<form role="form">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Service Project</label>
									<select class="form-control" id="project" name="project" ng-model="project">
										<option value="">Service Project</option>
										<option ng-repeat="item in serviceproject" value="{{item.serviceProjectId}}">{{item.serviceProjectTitle}}</option>
									</select>		
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Album Title<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="albumtitle" name="albumtitle" ng-model="albumtitle">		
								</div>
							</div>							
						</div>
						<div class="row">							
							<div class="col-md-6">
								<div class="form-group">
									<label>Album Sub-Title</label>
									<input type="text" class="form-control" id="albumsubtitle" name="albumsubtitle" ng-model="albumsubtitle">		
								</div>
							</div>	
							<div class="col-md-6">
								<div class="form-group">
									<label>Album Date</label>
									<input type="date" class="form-control" id="albumdate" name="albumdate" ng-model="albumdate">		
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
									<a class="btn btn-primary" ng-click="addAlbumImageRow()">ADD</a>
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
								<tr ng-repeat="item in albumimage" ng-show="item.imageTitle != null">
									<td>{{item.imageTitle}}</td>
									<td>{{item.imageDescription}}</td>
									<td><a ng-click="removeAlbumImageRow(item.imageTitle)" style="cursor: pointer;"><i class="fa fa-trash"></i></a></td>
								</tr>
							</table>
						</div>						
					</form>
				</div>
				<div class="panel-footer" align="right">
					<button type="button" ng-click="addalbum()" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
					<a href="service_project" class="btn btn-danger">Cancle</a>
				</div>
			</div>
		</div><br>		
		<%@include file="footer.jsp" %><br>		
	</body>
</html>
