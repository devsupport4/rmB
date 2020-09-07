<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Reference</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
		
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
	</head>	
	<body ng-app="rcbs" ng-controller="referenceCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="center">
						<h3 style="color:#db3615">Manage Members Reference</h3>    
					</div>
					<div class="col-md-3" align="center">
						<div class="form-group"style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>					
					<div class="col-md-3" align="center">
						<div class="form-group" style="margin-top:15px">
							<select ng-model="pageSize" id="pageSize" class="form-control" style="width: 35%;">
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
						<form role="form" ng-submit="deleteRef()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>
										<th>FROM</th>
										<th>TO</th>										
										<th>DATE</th>
										<th>REFERRAL</th>																	
										<th><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in reference | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize" style="cursor:pointer;">											
											
											<td data-toggle="modal" data-target="#myModal" ng-click='getReferenceDetail(item.memberReferralId ,item.fromFirstName, item.fromLastName)'>{{item.fromFirstName}} {{item.fromLastName}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getReferenceDetail(item.memberReferralId,item.fromFirstName, item.fromLastName)'>{{item.memberFirstName}} {{item.memberLastName}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getReferenceDetail(item.memberReferralId,item.fromFirstName, item.fromLastName)'>{{item.referDate}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getReferenceDetail(item.memberReferralId,item.fromFirstName, item.fromLastName)'>{{item.referralName}}</td>											
											<!-- <td data-toggle="modal" data-target="#myModal" ng-click='getNewsDetail(item.newsId)'>{{item.newsTitle}}</td> -->
											<td><input type="checkbox" ng-model="item.selected" value="{{item.memberReferralId}}"></td>
										</tr>
									</tbody>
								</table>
								<div class="form-group" style="float:right; margin-right:70px">
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= reference.length/pageSize - 1" ng-click="currentPage=currentPage+1">
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
								<h4 class="modal-title">Reference from {{fromMemberName}}</h4>
							</div>
							<div class="modal-body">
								<form>
									<div class="row">
										<div class="col-md-3">														
											<lable>To<font color="red" size="3">*</font></lable>
											<select class="form-control" name="tomemberid" id="tomemberid" ng-model="getReferencebyid.toMemberId">
												<option value="">Select</option>
												<option ng-repeat="item in getmember" value="{{item.memberId}}">{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</option>
											</select>														
										</div>
										<div class="col-md-3">
											<lable>Referral<font color="red" size="3">*</font></lable>
											<input type="text" class="form-control" name="referralname" id="referralname" ng-model="getReferencebyid.referralName">														
										</div>
										<div class="col-md-3">
											<lable>Date<font color="red" size="3">*</font></lable>
											<input class="KendoDate" id="datepicker" ng-model="getReferencebyid.referDate" title="Refer Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>														
										</div>
										<div class="col-md-3">
											<lable>Close by Date<font color="red" size="3">*</font></lable>
											<input class="KendoDate" id="datepicker2" ng-model="getReferencebyid.closeDate" title="Close by Date" placeholder="DD/MM/YYYY" style="width: 100%;"/>														
										</div>
									</div>	
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<lable>Referral Type<font color="red" size="3">*</font></lable><br>
												<label class="radio-inline"><input type="radio" name="optradio" ng-model="getReferencebyid.referralType" value="Inside">Inside</label>
												<label class="radio-inline"><input type="radio" name="optradio" ng-model="getReferencebyid.referralType" value="Outside">Outside</label>																	
											</div>
										</div>
										<div class="col-md-4">
											<lable>Connection<font color="red" size="3">*</font></lable><br>
											<label class="checkbox-inline"><input type="checkbox" id="card" ng-model="getReferencebyid.referralStatus1" ng-checked="getReferencebyid.referralStatus1" value="Given your card">Given your card</label><br>
											<label class="checkbox-inline"><input type="checkbox" id="call" ng-model="getReferencebyid.referralStatus2" ng-checked="getReferencebyid.referralStatus2" value="Told them you would call">Told them you would call</label>
										</div>
										<div class="col-md-4">														
											<lable>Referal Status<font color="red" size="3">*</font></lable>
											<select class="form-control" name="referalstatus" id="referalstatus" ng-model="getReferencebyid.referralStatus">
												<option value="">Status</option>
												<option value="O">Open</option>
												<option value="W">Working</option>
												<option value="C">Close</option>
											</select>														
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<lable>Approx Value</lable>
											<input type="text" class="form-control" name="apprValue" id="apprvalue" ng-model="getReferencebyid.apprValue">
										</div>
										<div class="col-md-4">
											<lable>Email</lable>
											<input type="text" class="form-control" name="referralemail" id="referralemail" ng-model="getReferencebyid.email">
										</div>
										<div class="col-md-4">
											<lable>Contact Number<font color="red" size="3">*</font></lable>
											<input type="text" class="form-control" name="referralcontactno" id="referralcontactno" ng-model="getReferencebyid.contactNumber">
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<lable>Address</lable>
											<textarea rows="4" class="form-control" name="referraladdress" id="referraladdress" ng-model="getReferencebyid.address"></textarea>
										</div>
										<div class="col-md-6">
											<lable>Comments</lable>
											<textarea rows="4" class="form-control" name="comment" id="comment" ng-model="getReferencebyid.comments"></textarea>
										</div>
									</div>
									<div class="row" ng-if="getReferencebyid.referralStatus == 'C'">
										<div class="col-md-6">
											<lable>Reason<font color="red" size="3">*</font></lable>
											<select class="form-control" name="CloseReason" id="CloseReason" ng-model="getReferencebyid.CloseReason" >
												<option value="">--- Select Reason ---</option>
												<option value="Success">Success</option>
												<option value="Rejected">Rejected</option>
												<option value="Not serviced">Not serviced</option>
											</select>
										</div>
										<div class="col-md-6">
											<lable>Comments</lable>
											<textarea class="form-control" name="CloseComment" id="CloseComment" ng-model="getReferencebyid.CloseComment"></textarea>
										</div>
									</div>
									<h4 class="classic-title"></h4>															
									<div class="row">
										<div class="col-md-12 text-center">
											<button class="btn btn-primary" ng-click="updateReferral(getReferencebyid.memberReferralId)" style="background-color:  #005daa;"><i class="fa fa-refresh fa-spin" ng-show="spin == 1"></i> Update </button>
										</div>													
									</div>												
								</form>																							
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
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker,#datepicker2").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true,
					value: new Date()
		         });
		    });
			$(".KendoDate").bind("focus", function(){
	   			$(this).data("kendoDatePicker").open(); 
			});
		</script>
		<%@include file="footer.jsp" %>
		<script>
			jQuery(function($) {
				 function readURL(input) {
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				            	if ($('#target').data('Jcrop')) {
								    $('#target').data('Jcrop').destroy();
								    $('#target').removeAttr('style');
								}
				                $('#target').attr('src', e.target.result);
				                
				                $('#target').Jcrop({
				                	aspectRatio: 2 / 1,	
				                	boxWidth: 650,   
				                    boxHeight: 325,
				                    setSelect: [ 200, 200, 650, 325 ],
				    				onSelect : setCoordinates
				    			});
				            }		            
				            reader.readAsDataURL(input.files[0]);
				        }
				    }
				    
				    $("#imgInp").change(function(){
				        readURL(this);
				    });
				    
					
			});
			function setCoordinates(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm.x.value = Math.round(c.x);
				document.myForm.y.value = Math.round(c.y);
				document.myForm.w.value = Math.round(c.w);
				document.myForm.h.value = Math.round(c.h);
			};
			function checkCoordinates() {
				if (document.myForm.x.value == "" || document.myForm.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};			
		</script>
	</body>
</html>
