<!DOCTYPE html>
<html lang="en">
	<head>
		<title>RMBB Mailer</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  		
		<%-- <script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script> --%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />		 		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.maskedinput.js" type="text/javascript"></script>		
		<!-------------- Bootstrap File Input Example Upload IMAGE  Scrept Start---->
		<link href="<%=request.getContextPath() %>/resources/upload-img/style.css" media="all" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/upload-img/jquery-ui.css" media="all" rel="stylesheet" type="text/css" />		
		<link href="<%=request.getContextPath() %>/resources/upload-img/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/upload-img/js/fileinput.js" type="text/javascript"></script>		
		<script src="<%=request.getContextPath() %>/resources/upload-img/js/fileinput_locale_fr.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/upload-img/js/fileinput_locale_es.js" type="text/javascript"></script>
		<!-------------- Bootstrap File Input Example Upload IMAGE  Scrept Start---->
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/mailer.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
		<style type="text/css" id="picture_basic_dependence_css">
			.picture-element-principal{background:url(images/icon_add_image2.png) no-repeat 50% 50%}.picture-dropped{border:2px #666 dashed!important;}
		</style>
		<style>	
			.table-wrapper{
				position: relative;
				height: 400px;
				overflow: auto;
			} 
			
			.custom-scroll {
				display: block;
			}		
			label {
			    display: inline-block;
			    max-width: 100%;
			    margin-bottom: 5px;
			    font-weight: 400;
			}			
		</style>		
		<script type="text/javascript">
			jQuery(function($){
				   $("#joiningdate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#dateofbirth").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#anniversarydate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#phone").mask("(999) 999-9999");
				   $("#tin").mask("99-9999999");
				   $("#ssn").mask("999-99-9999");
				});
		</script>
			
	</head>	
	<body ng-app="rcbs" ng-controller="rmbbMailer" ng-cloak ng-init="getRecevierDetail(<%= request.getParameter("memberid")%>)">
		<%@include file="header.jsp" %>	
		<section>
			<div class="container">
				<div class="row">
					<div class="board">
						<div class="tab-content">
								<div class="tab-pane fade in active" id="membership">
									<div class="col-md-12">
										<div class="panel panel-default">
											<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
												<div class="row" style="color: #333;">
													<div class="col-md-12 text-center">
														<div class="panel-title1">RMBB Mailer</div>
													</div>																									
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12">
														<label>Write Mail</label>
														<div class="form-group">
															<textarea cols="80" rows="10" data-sample-short id="mailContent" name="mailContent" ng-model="mailContent" placeholder="Content..." class="form-control"></textarea>
															<font style="color:red;" ng-if="mailContError == 1">{{mailContMsg}}</font>
														</div>
													</div>
												</div>
												<div style="border-top: 2px solid green;" class="panel panel-default">
													<div style="padding: 5px 0px 0px 0px;" class="panel-body">
														<div class="panel-heading1"> Member List </div>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-10">
																<label>Select Members</label>
																<select class="form-control input-lg2" name="MemberLi" id="MemberLi" ng-model="MemberLi">
																	<option value="All">Select All ({{getmember.length}})</option>
																	<option ng-repeat="item in getmember | orderBy:'memberFirstName'" value="{{item.memberId}}">{{item.memberFirstName}} {{item.memberLastName}} Email - {{item.memberEmail}} Mobile - {{item.memberMobileNumber}}</option>
																</select>
																<font style="color:red;" ng-if="memLiError == 1">{{memLirMsg}}</font>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<a id="Add" value="Add" name="Add" ng-click="addMemberMailerList()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div style="border-top: 2px solid green;" class="panel panel-default">
													<div style="padding: 5px 0px 0px 0px;" class="panel-body">
														<div class="panel-heading1"> Event List </div>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-6">
																<label>Select Event</label>
																<select class="form-control input-lg2" name="eventDrop" id="eventDrop" ng-model="eventDrop" ng-change="getMembers()">
																	<option value="">Select</option>
																	<option ng-repeat="item in events | orderBy:'eventTitle'" value="{{item.eventId}}">{{item.eventTitle}} {{item.eventDate | date : 'dd/MM/yyyy'}} @{{item.eventVenue}}</option>
																</select>
															</div>
															<div class="col-md-6">
																<label>Select member type</label>
																	<select class="form-control input-lg2" name="membertype" id="membertype" ng-model="membertype">
																		<option value="">Member Type</option>
																		<option value="RMBFB Member">RMBFB Member</option>
																		<option value="RMBF">RMBF</option>
																		<option value="R I Representative">R I Representative</option>
																		<option value="Others">Others</option>
																	</select>
															</div>
														</div><br>
														<div class="row">
															<div class="col-md-10">
																<label>Select Registered Users</label>
																<select class="form-control input-lg2" name="regUsers" id="regUsers" ng-model="regUsers">
																	<option value="All" selected>Select All</option>
																	<option ng-repeat="item in regMem = (RegisteredMembers | filter:{memberType: membertype}:memberlistUpdate)" value="{{item.memberId}}">{{item.firstName}} {{item.lastName}} Email - {{item.emailId}} Mobile - {{item.mobileNo}}</option>
																</select>
																<font style="color:red;" ng-if="regUserError == 1">{{regUserMsg}}</font>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<a id="Add" value="Add" name="Add" ng-click="addEventMailerList()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="table-wrapper custom-scroll">
													<div class="row text-center" ng-repeat="item in malierList">
														<div class="col-md-3">
															<div class="form-group">
																<label>{{item.memberFirstName}} {{item.memberLastName}}</label>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<label>{{item.memberEmail}}</label>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<label>{{item.memberMobileNumber}}</label>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<a class="btn btn-danger btn-sm" ng-click="removeEventMailerList(item)" ng-if="item.memberMobileNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
															</div>
														</div>
													</div>
												</div>
												<input type="checkbox" id="emailSE" ng-model="emailSE" value="emailchecked"/>Email &nbsp;&nbsp;&nbsp;
												<input type="checkbox" id="smsSE" ng-model="smsSE" value="smschecked"/>Sms
											</div>
										</div>
										<div class="row">
										<div class="col-md-12 text-center">
											
											<button class="btn btn-success btn-lg" ng-click="sendMail()" style="width:100%;" ng-disable="spin == 1"><i class="fa fa-refresh fa-spin" ng-show="spin == 1"></i><i class="fa fa-paper-plane" ng-show="spin != 1" ></i>&nbsp;&nbsp; Send </button>
										</div>													
									</div>										
									<!-- </form> -->
								</div>
							</div>
							<!-- Membership	 END-->	
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<%@include file="footer.jsp" %>
		<script src="https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>
		<script>
			$('#myModal').on('hidden', function(){
				document.location.reload();
			})					
		
		CKEDITOR.replace('mailContent', {
			height: 150
		});
		
		</script>
	</body>
</html>
