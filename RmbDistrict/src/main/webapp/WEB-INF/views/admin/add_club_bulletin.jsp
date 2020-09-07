<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<title>Add Club Bulletin</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script	src="<%=request.getContextPath()%>/resources/admin/js/jquery.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/bootstrap.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/angular.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/controller/app.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>"	rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>"	rel="stylesheet" type="text/css" />
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
	<body ng-app="rcbs" ng-controller="bulletinCtrl" ng-init="getCurrentDefaultYear()">
		<%@include file="header.jsp"%><br>
		<div class="container">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"> Add Club Bulletin </h3>
				</div>
				<div class="panel-body" style="background-color: #f2f2f2;">
					<form role="form" name="news" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Rotary Year<font color="red" size="3">*</font></label> 
									<select class="form-control" id="rotaryyearid" name="rotaryyearid" ng-model="rotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear">
										<option value="">Rotary Year</option>													
									</select>
								</div>
							</div>										
							<div class="col-md-4">
								<div class="form-group">
									<label>Issue Number<font color="red" size="3">*</font></label>									
									<input type="text" class="form-control" id="issueno" name="issueno" ng-model="issueno" autofocus>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Bulletin Title<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="bulletintitle" name="bulletintitle" ng-model="bulletintitle">
								</div>
							</div>									
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Issue Date<font color="red" size="3">*</font></label>																					
									<input type="date" class="form-control" id="issuedate" name="issuedate" ng-model="issuedate">
								</div>
							</div>										
							<div class="col-md-4">
								<div class="form-group">
									<label>File<font color="red" size="3">*</font></label>									
									<input type="file" class="form-control" id="file" name="file" ng-model="file">
								</div>
							</div>																			
						</div>						
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="form-group">
									<button type="submit" name="submit" ng-click="addBulletin()" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
									<a href="manage_club_bulletins" class="btn btn-danger">Cancel</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>		
		<%@include file="footer.jsp"%><br>
	</body>
</html>
