<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<title>Add Event</title>
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
	</head>
	<body ng-app="rcbs" ng-controller="eventCtrl">
		<%@include file="header.jsp" %><br>		
		<div class="container"> 
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><b>Add Event</h3></b>
				</div>
				<div class="panel-body" style="background-color:#f2f2f2;">
					<form role="form" name="event" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Registration</label>									
									<select class="form-control" name="registration" id="registration" ng-model="registration">
										<option value="">Select Registration</option>
										<option value="No">No</option>
										<option value="Yes">Yes</option>
									</select>
								</div>
							</div>
							<div class="col-md-3" ng-show="registration=='Yes'">
								<div class="form-group">
									<label>Paid</label>									
									<select class="form-control" name="paid" id="paid" ng-model="paid">
										<option value="">Paid</option>
										<option value="No">No</option>
										<option value="Yes">Yes</option>
									</select>
								</div>
							</div>
						</div>	
						<div class="row" ng-show="registration=='Yes'">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Registration Charges</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row" ng-show="registration=='Yes'">
							<div class="col-md-3">
								<div class="form-group">
									<label>Member Type </label>
									<select class="form-control" name="registrationfor" id="registrationfor" ng-model="registrationfor">
										<option value="">Member Type</option>
										<option value="RMBFB Member">RMBFB Member</option>
										<option value="RMBF">RMBF</option>
										<option value="R I Representative">R I Representative</option>
										<option value="Others">Others</option>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Currency Type </label>
									<select class="form-control" name="currencytype" id="currencytype" ng-model="currencytype">
										<option value="">Currency Type</option>
										<option ng-repeat="c in getcurrency" value="{{c.currencyId}}">{{c.currencyName}}</option>
									</select>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Amount</label>
									<input type="number" class="form-control" id="amount" name="amount" ng-model="amount">
								</div>
							</div>
							<div class="col-md-1">								
								<div class="form-group">
									<label>&nbsp;</label>
									<a type="submit" id="Add" value="Add" name="Add" ng-click="addRegistrationRow()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
								</div>								
							</div>
							<div class="col-md-3">								
								<div class="form-group">
									<label>&nbsp;</label>
									<label>&nbsp; Note: By default all members are set to free</label>									
								</div>								
							</div>
						</div>
						<div class="row" ng-show="registration=='Yes'">
							<div class="col-md-12">
								<table class="table table-striped" width="100%">
									<tr>
										<th>Member Type</th>							
										<th>Currency</th>
										<th>Amount</th>
										<th></th>								
									</tr>
									<tr ng-repeat="item in registrationlist">
										<td ng-show="item.amount != 0.000">{{item.registrationFor}}</td>
										<td ng-show="item.amount != 0.000">{{item.currencyTitle}}</td>								
										<td ng-show="item.amount != 0.000">{{item.amount}}</td>
										<td ng-show="item.amount != 0.000"><a class="btn btn-danger btn-sm" ng-click="removeRegistrationRow(item.registrationFor)" ng-if="item.registrationFor != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
									</tr>
								</table>
							</div>							
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Event Details</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Event Type</label>									
									<select class="form-control" name="eventtype" id="eventtype" ng-model="eventtype">
										<option value="">Select</option>
										<option ng-repeat="item in eventtypes" value="{{item.eventTypeId}}" >{{item.eventTypeTitle}}</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Event Title<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="eventtitle" name="eventtitle" ng-model="eventtitle">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Event Subtitle</label>
									<input type="text" class="form-control" id="eventsubtitle" name="eventsubtitle" ng-model="eventsubtitle">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">								
								<div class="form-group">
									<label>Event Date<font color="red" size="3">*</font></label>
									<input type="date" class="form-control" id="eventdate" name="eventdate" ng-model="eventdate">									
								</div>								
							</div>
							<div class="col-md-2">								
								<div class="form-group">
									<label>Event Time<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="eventtime" name="eventtime" ng-model="eventtime">									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Event Venue</label>
									<input type="text" class="form-control" id="eventvenue" name="eventvenue" ng-model="eventvenue">									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Event Map Location</label>
									<input type="text" class="form-control" id="eventmaplocation" name="eventmaplocation" ng-model="eventmaplocation">									
								</div>								
							</div>														
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<textarea cols="80" rows="10" data-sample-short id="eventdescription" name="eventdescription" ng-model="eventdescription" placeholder="Description..." class="form-control"></textarea>
								</div>
							</div>						
							<!-- <div class="col-md-12">								
								<div class="form-group">
									<label>Event Description</label>
									<textarea rows="5" class="form-control" id="eventdescription" name="eventdescription" ng-model="eventdescription"></textarea>									
								</div>								
							</div> -->														
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Event Angenda</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-1">								
								<div class="form-group">
									<label>Sequence</label>
									<input type="text" class="form-control" id="eventagendasequence" name="eventagendasequence" ng-model="eventagendasequence">									
								</div>								
							</div>
							<div class="col-md-5">								
								<div class="form-group">
									<label>Event Agenda</label>
									<input type="text" class="form-control" id="eventagenda" name="eventagenda" ng-model="eventagenda">									
								</div>								
							</div>
							<div class="col-md-5">								
								<div class="form-group">
									<label>Agenda Conclusion</label>
									<input type="text" class="form-control" id="agendaconclusion" name="agendaconclusion" ng-model="agendaconclusion">									
								</div>								
							</div>
							<div class="col-md-1">								
								<div class="form-group">
									<label>&nbsp;</label>
									<a type="submit" id="Add" value="Add" name="Add" ng-click="addAgendaRow()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
								</div>								
							</div>														
						</div>
						<div class="row">
							<div class="col-md-12">
								<table class="table table-striped" width="100%">
									<tr>
										<th>Sequence</th>
										<th>Agenda</th>							
										<th>Conclusion</th>
										<th></th>								
									</tr>
									<tr ng-repeat="item in eventrelatedagenda1">
										<td>{{item.eventAgendaSequence}}</td>
										<td>{{item.eventAgendaTitle}}</td>								
										<td>{{item.eventAgendaConclusion}}</td>
										<td><a class="btn btn-danger btn-sm" ng-click="removeAgendaRow2(item.eventAgendaTitle)" ng-if="item.eventAgendaTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
									</tr>
								</table>
							</div>							
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Event Images</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">								
								<div class="form-group">
									<label>Event Image Title</label>
									<input type="text" class="form-control" id="imagetitle" name="imagetitle" ng-model="imagetitle">									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Event Image Description</label>
									<input type="text" class="form-control" id="imagedescription" name="imagedescription" ng-model="imagedescription">									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Event Image</label>
									<input type="file" class="form-control" id="eventimage" name="eventimage" ng-model="eventimage">									
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
										<th>Image Title</th>
										<th>Image Description</th>																	
										<th></th>								
									</tr>
									<tr ng-repeat="item in eventimagelist">
										<td>{{item.eventImageTitle}}</td>								
										<td>{{item.eventImageDescription}}</td>
										<td><a class="btn btn-danger btn-sm" ng-click="removeImageRow(item.eventImageTitle)" ng-if="item.eventImageTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
									</tr>
								</table>
							</div>							
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Event URLs</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">								
								<div class="form-group">
									<label>Sequence</label>
									<input type="text" class="form-control" id="eventurlsequence" name="eventurlsequence" ng-model="eventurlsequence">									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Url Title</label>
									<input type="text" class="form-control" id="eventurltitle" name="eventurltitle" ng-model="eventurltitle">									
								</div>								
							</div>
							<div class="col-md-5">								
								<div class="form-group">
									<label>Url</label>
									<input type="text" class="form-control" id="eventurl" name="eventurl" ng-model="eventurl">									
								</div>								
							</div>
							<div class="col-md-1">								
								<div class="form-group">
									<label>&nbsp;</label>
									<a type="submit" id="Add" value="Add" name="Add" ng-click="addUrlRow()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
								</div>								
							</div>														
						</div>
						<div class="row">
							<div class="col-md-12">
								<table class="table table-striped" width="100%">
									<tr>
										<th>Sequence</th>
										<th>Url Title</th>
										<th>Url</th>																	
										<th></th>								
									</tr>
									<tr ng-repeat="item in eventurllist">
										<td>{{item.eventUrlSequence}}</td>								
										<td>{{item.eventUrlTitle}}</td>
										<td>{{item.eventUrl}}</td>
										<td><a class="btn btn-danger btn-sm" ng-click="removeUrlRow(item.eventUrlTitle)" ng-if="item.eventUrlTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
									</tr>
								</table>
							</div>							
						</div>					
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<input type="checkbox" id="notifyviaemail" name="notifyviaemail" ng-model="notifyviaemail">&nbsp;Notify Members via e-mails																		
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">									
									<input type="checkbox" id="notifyviasms" name="notifyviasms" ng-model="notifyviasms">&nbsp;Notify Members via SMS									
								</div>
							</div>
							<div class="col-md-4" align="right">
								<div class="form-group">
									<button type="submit" name="submit" ng-click="addevent()" class="btn btn-success" >Submit <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button>
									<a href="manage_events" class="btn btn-danger">Cancel</a>									
								</div>
							</div>													
						</div>												
					</form>
				</div>
			</div>
		</div><br>
		<%@include file="footer.jsp" %><br>
		<script src="https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>
		<script>
		CKEDITOR.replace('eventdescription', {
			height: 150
		});
		</script>
	</body>
</html>
