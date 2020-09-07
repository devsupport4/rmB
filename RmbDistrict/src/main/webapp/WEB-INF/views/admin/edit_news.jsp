<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Edit News</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="<%=request.getContextPath()%>/resources/admin/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/admin/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/admin/js/angular.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/admin/js/controller/app.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
<link
	href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>"
	rel="stylesheet" type="text/css" />
</head>
<body ng-app="rcbs" ng-controller="newsCtrl">
	<%@include file="header.jsp"%><br>
	<div class="container">
		<div class="login-panel panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<b>Edit News 
				</h3>
				</b>
			</div>
			<div class="panel-body" style="background-color: #f2f2f2;">
				<form role="form" name="news" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>News Title</label> <input type="text"
									class="form-control" id="newsTitle" name="newsTitle"
									ng-model="newsTitle" maxlength="250" required autofocus>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>News Sub-Title</label> <input type="text"
									class="form-control" id="newsSubTitle" name="newsSubTitle"
									ng-model="newsSubTitle" maxlength="250" required>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label>News Date</label> <input type="date" class="form-control"
									id="newsDate" name="newsDate" ng-model="newsDate" required>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>News Photo</label> <input type="file"
									class="form-control" id="myFile" name="myFile"
									file-model="myFile" accept=".jpg,.png" required>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Photo Title</label> <input type="text"
									class="form-control" id="newsImageTitle" name="newsImageTitle"
									ng-model="newsImageTitle" maxlength="100" maxlength="250"
									required>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>News Detail</label>
								<textarea rows="3" class="form-control" id="newsDetail"
									name="newsDetail" ng-model="newsDetail" required></textarea>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4" align="center">
							<div class="form-group">
								<input class="btn btn-primary" type="reset" value="Reset">
							</div>
						</div>
						<div class="col-md-4" align="center">
							<div class="form-group">
								<button type="submit" name="submit" ng-click="editNews()"
									class="btn btn-success">Submit</button>
							</div>
						</div>
						<div class="col-md-4" align="center">
							<div class="form-group">
								<a href="manage_news" class="btn btn-danger">Cancel</a>
							</div>
						</div>
						<div class="col-md-9">
							<img id="loader" style="display: none; width: 5%; height: 5%;"
								alt=""
								src="<c:url value="/resources/admin/images/Loader.gif"></c:url>">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<%@include file="footer.jsp"%><br>
</body>
</html>
