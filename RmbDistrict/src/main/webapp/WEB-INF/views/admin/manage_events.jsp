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
	<body ng-app="rcbs" ng-controller="eventCtrl" ng-init="sendNotificationToMembers()">
		<%@include file="header.jsp" %>
		<div class="container text-center">
			<h3 style="color:#db3615">Manage Event</h3>      
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
										<a href="add_event" class="btn btn-success btn-default">Add Event &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
									</div> 
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
										<th width="90%">EVENT TITLE</th>										
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in events | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">											
											<td data-toggle="modal" data-target="#myModal" ng-click='geteventdetails(item.eventId)'>{{item.eventTitle}}</td>
											<td><input type="checkbox" ng-model="item.selected" value="{{item.eventId}}"></td>
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= events.length/pageSize - 1" ng-click="currentPage=currentPage+1">
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
								<h4 class="modal-title">Update Event</h4>
							</div>
							<div class="modal-body">							
							<form role="form" name="event">
							
								<!-- <div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>Registration</label>									
											<select class="form-control" name="registrationedit" id="registrationedit" ng-model="registrationedit">
												<option value="">Select Registration</option>
												<option value="No">No</option>
												<option value="Yes">Yes</option>
											</select>
										</div>
									</div>
									<div class="col-md-2" ng-show="registrationedit=='Yes'">
										<div class="form-group">
											<label>Paid</label>									
											<select class="form-control" name="paidedit" id="paidedit" ng-model="paidedit">
												<option value="">Paid</option>
												<option value="No">No</option>
												<option value="Yes">Yes</option>
											</select>
										</div>
									</div>
									<div class="col-md-3" ng-show="paidedit=='Yes'">
										<div class="form-group">
											<label>RMBFB member (<i class="fa fa-inr" aria-hidden="true"></i>)</label>
											<input type="number" class="form-control" id="rmbfbmemberedit" name="rmbfbmemberedit" ng-model="rmbfbmemberedit">
										</div>
									</div>
									<div class="col-md-2" ng-show="paidedit=='Yes'">
										<div class="form-group">
											<label>Rotarian (<i class="fa fa-inr" aria-hidden="true"></i>)</label>
											<input type="number" class="form-control" id="rotarianedit" name="rotarianedit" ng-model="rotarianedit">
										</div>
									</div>
									<div class="col-md-2" ng-show="paidedit=='Yes'">
										<div class="form-group">
											<label>Others (<i class="fa fa-inr" aria-hidden="true"></i>)</label>
											<input type="number" class="form-control" id="othersedit" name="othersedit" ng-model="othersedit">
										</div>
									</div>
								</div> -->
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>Registration</label>									
											<select class="form-control" name="registrationedit" id="registrationedit" ng-model="registrationedit">
												<option value="">Select Registration</option>
												<option value="No">No</option>
												<option value="Yes">Yes</option>
											</select>
										</div>
									</div>
									<div class="col-md-3" ng-show="registrationedit=='Yes'">
										<div class="form-group">
											<label>Paid</label>									
											<select class="form-control" name="paidedit" id="paidedit" ng-model="paidedit">
												<option value="">Paid</option>
												<option value="No">No</option>
												<option value="Yes">Yes</option>
											</select>
										</div>
									</div>
									<!-- <div class="col-md-3" ng-show="registrationedit=='Yes'">
										<div class="form-group">
											<label class="checkbox-inline"><input type="checkbox" id="closeEventRegistration" ng-model="closeEventRegistration" ng-checked="closeEventRegistration" ng-click="closeRegistration(eventid)" value="close">Close event Registration</label>
										</div>
									</div> -->
								</div>	
								<div class="row" ng-show="registrationedit=='Yes'">
									<div class="col-md-12" align="center">
										<div class="panel" style="background-color: #C0C0C0;">
										<div class="panel-heading" style="padding: 0px 15px;">
											<h4>Registration Charges</h4>
										</div>
										</div>
									</div>
								</div>
								<div class="row" ng-show="registrationedit=='Yes'">
									<div class="col-md-3">
										<div class="form-group">
											<label>Member Type </label>
											<select class="form-control" name="registrationfor" id="registrationfor" ng-model="registrationfor">
												<option value="">Type</option>
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
											<a type="submit" id="Add" value="Add" name="Add" ng-click="addRegistrationRowEdit()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
										</div>								
									</div>
								</div>
								<div class="row" ng-show="registrationedit=='Yes'">
									<div class="col-md-12">
										<table class="table table-striped" width="100%">
											<tr>
												<th>Member Type</th>							
												<th>Currency</th>
												<th>Amount</th>
												<th></th>								
											</tr>
											<tr ng-repeat="item in registrationlist1">
												<td ng-show="item.amount != 0.000">{{item.registrationFor}}</td>
												<td ng-show="item.amount != 0.000">{{item.currencyName}}</td>								
												<td ng-show="item.amount != 0.000">{{item.amount}}</td>
												<td ng-show="item.amount != 0.000"><a class="btn btn-danger btn-sm" ng-click="deleteRegistrationRow(item.eventChargeId)" ng-if="item.eventChargeId != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
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
											<input type="text" class="form-control" id="eventtitle" name="eventtitle" ng-model="eventtitle" autofocus>
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
									<div class="col-md-3">								
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
									<div class="col-md-3">								
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
									<!-- <div class="col-md-12">								
										<div class="form-group">
											<label>Event Description</label>
											<textarea rows="5" class="form-control" id="eventdescription" name="eventdescription" ng-model="eventdescription"></textarea>									
										</div>								
									</div> -->
									<div class="col-md-12">
										<div class="form-group">
											<textarea cols="80" rows="10" data-sample-short id="eventdescription" name="eventdescription" ng-model="eventdescription" placeholder="Description..." class="form-control"></textarea>
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
											<a type="submit" id="Add" value="Add" name="Add" ng-click="addAgendaRow1()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
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
											<tr ng-repeat="item in eventrelatedagenda">
												<td>{{item.eventAgendaSequence}}</td>
												<td>{{item.eventAgendaTitle}}</td>								
												<td>{{item.eventAgendaConclusion}}</td>
												<td><a class="btn btn-danger btn-sm" ng-click="removeAgendaRow1(item.eventAgendaTitle)" ng-if="item.eventAgendaTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
											</tr>
										</table>
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
											<a type="submit" id="Add" value="Add" name="Add" ng-click="addImageRowNew()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
										</div>								
									</div>														
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-striped" width="100%">
											<tr>
												<th>Image Title</th>
												<th>Image Description</th>																	
												<th>Image</th>
												<th></th>								
											</tr>
											<tr ng-repeat="item in eventrelatedimages">
												<td>{{item.eventImageTitle}}</td>								
												<td>{{item.eventImageDescription}}</td>
												<td><img ng-src="{{item.image}}" class="img-responsive" alt="image" style="height:40px; width: 40px;" ng-show="item.image != null"></td>
												<td><a class="btn btn-danger btn-sm" ng-click="removeImageRow1(item.eventImageTitle)" ng-if="item.eventImageTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
											</tr>
											<tr ng-repeat="item in eventrelatedimagesnew" ng-show="item.eventImageTitle!=null">
												<td>{{item.eventImageTitle}}</td>								
												<td>{{item.eventImageDescription}}</td>
												<td></td>								
												<td><a class="btn btn-danger btn-sm" ng-click="removeImageRowNew(item.eventImageTitle)" ng-show="item.eventImageTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
											</tr>
										</table>
									</div>							
								</div>
								<div class="row">
									<div class="col-md-3">								
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
									<div class="col-md-4">								
										<div class="form-group">
											<label>Url</label>
											<input type="text" class="form-control" id="eventurl" name="eventurl" ng-model="eventurl">									
										</div>								
									</div>
									<div class="col-md-1">								
										<div class="form-group">
											<label>&nbsp;</label>
											<a type="submit" id="Add" value="Add" name="Add" ng-click="addUrlRow1()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>									
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
											<tr ng-repeat="item in eventrelatedurl">
												<td>{{item.eventUrlSequence}}</td>								
												<td>{{item.eventUrlTitle}}</td>
												<td>{{item.eventUrl}}</td>
												<td><a class="btn btn-danger btn-sm" ng-click="removeUrlRow1(item.eventUrlTitle)" ng-if="item.eventUrlTitle != null"/><span class="glyphicon glyphicon-remove"></span></a></td>								
											</tr>											
										</table>
									</div>							
								</div>					
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<button type="submit" name="submit" ng-click="editevent(eventid)" class="btn btn-success" >Submit</button>
											<a href="manage_events" class="btn btn-danger" data-dismiss="modal">Cancel</a>									
										</div>
									</div>
									<div class="col-md-9">
										<img id="loader" style="display:none; width: 5%; height: 5%;" alt="" src="<c:url value="/resources/admin/images/Loader.gif"></c:url>">
									</div>						
								</div>												
							</form>							
							</div>						
						</div>
					</div>
				</div>
			</div>			
		</div><br>
		<script src="https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>
		<script>
			$('#myModal').on('hidden', function(){
				document.location.reload();
			})					
		
		CKEDITOR.replace('eventdescription', {
			height: 150
		});
		
		</script>
		<%@include file="footer.jsp" %>
	</body>
</html>
