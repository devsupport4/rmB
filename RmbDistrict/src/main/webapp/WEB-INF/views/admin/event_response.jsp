<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Events</title>
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
	</head>	
	<body ng-app="rcbs" ng-controller="eventResponseCtrl">
		<%@include file="header.jsp" %>
		<div class="container text-center">
			<h3 style="color:#db3615">Event Response</h3>      
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
									
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteevent()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>										
										<th width="50%">EVENT TITLE</th>										
										<th width="15%">COMING</th>
										<th width="15%">NOT COMING</th>
										<th width="15%">PENDING</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in eventresponsecounts"  style="cursor:pointer; cursor:hand">											
											<td data-toggle="modal" data-target="#myModal" ng-click='getEventResponceDetail(item.eventId)'>{{item.eventTitle}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getEventResponceDetail(item.eventId)'>{{item.comming}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getEventResponceDetail(item.eventId)'>{{item.notcomming}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getEventResponceDetail(item.eventId)'></td>											
										</tr>
									</tbody>
								</table>								
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= eventresponsecounts.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">{{eventtitle}} Event Response Details</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-md-12" align="center">
										<h4><b>Members Who Are Coming</b></h4>
									</div>
								</div>							
								<div class="table-responsive">
									<table id="mytable" class="table table-bordred table-striped">
										<thead>										
											<th width="25%">MEMBER NAME</th>										
											<th width="10%">SELF</th>
											<th width="10%">SPOUSE</th>
											<th width="10%">ANNET</th>
											<th width="10%">NO. OF ANNET</th>
											<th width="35%">COMMENT</th>
										</thead>
										<tbody>
											<tr ng-repeat="item in comingmembers" ng-if="item.firstname != null">											
												<td>{{item.firstname}} {{item.middlename}} {{item.lastname}}</td>
												<td>{{item.joinself}}</td>
												<td>{{item.joinspouse}}</td>
												<td>{{item.joinannet}}</td>
												<td>{{item.noofannetsjoin}}</td>
												<td>{{item.comment}}</td>											
											</tr>
										</tbody>
									</table>								
									<div class="clearfix"></div>							
								</div>
								<div class="row">
									<div class="col-md-12" align="center">
										<h4><b>Members Who Are Not Coming</b></h4>
									</div>
								</div>
								<div class="table-responsive">
									<table id="mytable" class="table table-bordred table-striped">
										<thead>										
											<th width="25%">MEMBER NAME</th>									
											<th width="75%">REASON FOR NOT COMMING</th>
										</thead>
										<tbody>
											<tr ng-repeat="item in notcomingmembers" ng-if="item.firstname != null">											
												<td>{{item.firstname}} {{item.middlename}} {{item.lastname}}</td>
												<td>{{item.notcomingreason}}</td>																						
											</tr>
										</tbody>
									</table>								
									<div class="clearfix"></div>							
								</div>					
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
