<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Add Member</title>
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
		<link href="<%=request.getContextPath() %>/resources/upload-img/style.css" media="all" rel="stylesheet" type="text/css" />		 		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.maskedinput.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
		<style>			
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
				   $("#phone").mask("(999) 999-9999");
				   $("#tin").mask("99-9999999");
				   $("#ssn").mask("999-99-9999");
				});
		</script>
			
	</head>	
	<body ng-app="rcbs" ng-controller="memberCtrl" ng-init="getcontactdetail1('<%= session.getAttribute("membershipnumber") %>')" ng-cloak>
		<%@include file="header.jsp" %>	
		<section>
			<div class="container">
				<div class="row">
					<div class="board">
						<div class="board-inner">
							<ul class="nav nav-tabs " id="myTab">
								<div class="liner"></div>
								<li class="disabled">
									<a href="" data-toggle="tab" title="MEMBERSHIP">
										<span class="round-tabs one">
											<i class="glyphicon glyphicon-home"></i>
										</span> 
									</a>
								</li>
								<li class="active">
									<a href="#" data-toggle="tab" title="CONTACT DETAILS">
										<span class="round-tabs two">
											<i class="glyphicon glyphicon-gift"></i>
										</span>
									</a>
								</li>
								<li class="disabled">
									<a href="#" data-toggle="tab" title="FAMILY DETAILS">
										<span class="round-tabs three">
											<i class="glyphicon glyphicon-comment"></i>
										</span>
									</a>
								</li>								
								<li class="disabled">
									<a href="#references" data-toggle="tab" title="REFERENCES">
										<span class="round-tabs five">
											<i class="glyphicon glyphicon-ok"></i>
										</span>
									</a>
								</li>
								<li class="disabled">
									<a href="#payments" data-toggle="tab" title="PAYMENTS">
										<span class="round-tabs four">
											<i class="glyphicon glyphicon-comment"></i>
										</span>
									</a>
								</li>
							</ul>
						</div>
						<!-- <form> -->
							<div class="tab-content">
								<div class="tab-pane fade in active" id="personal">
									<div class="col-md-12">	
										<div class="panel panel-default">
											<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
												<div class="row" style="color: #333;">
													<div class="col-md-4">
														<div class="panel-title1"> RESIDENTIAL ADDRESS  </div>
													</div>
													<div class="col-md-4">
														<input type="text" value="{{memberfirstname}} {{membermiddlename}} {{memberlastname}}" disabled="disabled" class="form-control input-lg2" placeholder="Old Membership No"/>
													</div>
													<div class="col-md-2">
														<input type="text" disabled="disabled" ng-model="membershipnumber" class="form-control input-lg2" placeholder="Membership No" data-toggle="tooltip" title="New Membership No." />
													</div>													
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label>Address-1<font color="red" size="3">*</font></label>
															<input type="text" id="address1" name="address1" ng-model="address1" class="form-control input-lg1" placeholder="Address Line-1" capitalize-first>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>Address-2</label>
															<input type="text" id="address2" name="address2" ng-model="address2" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>Address-3</label>
															<input type="text" id="address3" name="address3" ng-model="address3" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label>Country<font color="red" size="3">*</font></label>
															<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countryname = 1" ng-change="onCountryChange()" class="form-control input-lg2">
																<option value="">---Select Country---</option>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>State<font color="red" size="3">*</font></label>
															<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statename = 1" class="form-control input-lg2">
																<option value="">---Select State---</option>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>City Name<font color="red" size="3">*</font></label>
															<input type="text" id="cityname" name="cityname" ng-model="cityname" ng-init="cityname = 'Vadodara'" class="form-control input-lg1" placeholder="City" capitalize-first>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label>Pincode</label>
															<input type="text" id="pincode" name="pincode" ng-model="pincode" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>Mobile Number<font color="red" size="3">*</font></label>
															<input type="text" id="mobilenumber" name="mobilenumber" ng-model="mobilenumber" class="form-control input-lg1" placeholder="Mobile" disabled="disabled" disallow-spaces>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label>Phone Number</label>
															<input type="text" id="phonenumber" name="phonenumber" ng-model="phonenumber" class="form-control input-lg1" placeholder="Phone">
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>Email</label>
															<input type="text" id="memberemail" name="memberemail" ng-model="memberemail" class="form-control input-lg1" placeholder="email" disabled="disabled" disallow-spaces>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<a id="Add" value="Add" name="Add" ng-click="addResidentialLandlineRow()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
														</div>
													</div>
													
												</div>
												<div class="row" ng-repeat="item in residentiallandline">
													<div class="col-md-2">
														<div class="form-group">
															<label>{{item.landlinePhoneNumber}}</label>
														</div>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<a class="btn btn-danger btn-sm" ng-click="removeResidentialLandlineRow(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
														</div>
													</div>
												</div>
											</div>
										</div>									
										<div class="panel panel-default">
											<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
												<div class="panel-title1" style="color: #333;"> WORK DETAILS </div>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12" align="center">
														<label style="float: left;">Company Logo</label>
														<input type='file' id="imgInp" class="form-control"/>																
														<img src="#" id="target"/>																									
														<form name="myForm">
															<input type="hidden" name="x" id="valuex" ng-model="valuex"/>
															<input type="hidden" name="y" id="valuey" ng-model="valuey"/>
															<input type="hidden" name="w" id="valuew" ng-model="valuew"/>
															<input type="hidden" name="h" id="valueh" ng-model="valueh"/>																	
														</form>																
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label>Occupation<font color="red" size="3">*</font></label>
															<input type="text" id="occupation" name="occupation" ng-model="occupation" class="form-control input-lg1" placeholder="Occupation" capitalize-first>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>Designation<font color="red" size="3">*</font></label>
															<input type="text" id="designation" name="designation" ng-model="designation" class="form-control input-lg1" placeholder="Designation" capitalize-first>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label>Company Name<font color="red" size="3">*</font></label>
															<input type="text" id="companyname" name="companyname" ng-model="companyname" class="form-control input-lg1" placeholder="Company Name" capitalize-first>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>Business Nature<font color="red" size="3">*</font></label>
															<input type="text" id="businessnature" name="businessnature" ng-model="businessnature" class="form-control input-lg1" placeholder="Business Nature" capitalize-first>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label>Fax Number</label>
															<input type="text" id="faxnumber" name="faxnumber" ng-model="faxnumber" class="form-control input-lg1" placeholder="Fax-Office">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>Website</label>
															<input type="text" id="website" name="website" ng-model="website" class="form-control input-lg1" placeholder="Website">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>About your Business</label>
															<textarea rows="4" class="form-control input-lg1" id="aboutbusiness" name="aboutbusiness" ng-model="aboutbusiness" placeholder="About your business..."></textarea>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Business Keywords (Comma separated)</label>
															<textarea rows="4" class="form-control input-lg1" id="businesskeywords" name="businesskeywords" ng-model="businesskeywords" placeholder="Ex.- IT Firm, Website, Mobile Application, ..."></textarea>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Email</label>
															<input type="text" id="email" name="email" ng-model="email" class="form-control input-lg1" placeholder="Email id">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label>Address-1<font color="red" size="3">*</font></label>
															<input type="text" id="address1work" name="address1work" ng-model="address1work" class="form-control input-lg1" placeholder="Address Line-1" capitalize-first>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>Address-2<font color="red" size="3">*</font></label>
															<input type="text" id="address2work" name="address2work" ng-model="address2work" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>Address-3</label>
															<input type="text" id="address3work" name="address3work" ng-model="address3work" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label>Country<font color="red" size="3">*</font></label>
															<select id="countrynamework" name="countrynamework" ng-model="countrynamework" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countrynamework = 1" ng-change="onCountryChange()" class="form-control input-lg2">
																<option value="">---Select Country---</option>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>State<font color="red" size="3">*</font></label>
															<select id="statenamework" name="statenamework" ng-model="statenamework" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statenamework = 1" class="form-control input-lg2">
																<option value="">---Select State---</option>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>City Name<font color="red" size="3">*</font></label>
															<input type="text" id="citynamework" name="citynamework" ng-model="citynamework" ng-init="citynamework = 'Vadodara'" class="form-control input-lg1" placeholder="City" capitalize-first>
														</div>
													</div>
												</div>
												<div class="row">
													<div class=" col-md-6">
														<div class="form-group">
															<label>Pincode<font color="red" size="3">*</font></label>
															<input type="text" id="pincodework" name="pincodework" ng-model="pincodework" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>Mobile Number<font color="red" size="3">*</font></label>
															<input type="text" id="mobilenumberwork" name="mobilenumberwork" ng-model="mobilenumberwork" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label>Phone Number</label>
															<input type="text" id="phonenumberwork" name="phonenumberwork" ng-model="phonenumberwork" class="form-control input-lg1" placeholder="Phone">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<a id="Add" value="Add" name="Add" ng-click="addWorkLandlineRow()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="table-responsive">
												<table class="table table-bordered">
													<tbody >
														<tr style="color:black">
															<th style="color:black" width="5%">#</th>
															<th style="color:black" width="12%">Company Name #</th>
															<th style="color:black" width="25%">Designation</th>
															<th style="color:black" width="27%">Address</th>
															<th style="color:black" width="10%">Mobile #</th>
															<th style="color:black" width="16%">Email</th>
															<th style="color:black; text-align: center;" width="2%">Del.</th>
														</tr>
														<tr ng-repeat="item in getmemberworklandline" style="cursor:pointer;cursor:hand">
															<td style="color:black;" >{{$index + 1}}</td>
															<td style="color:black;" >{{item.memberCompanyName}}</td>
															<td style="color:black;" >{{item.memberDesignation}}</td>
															<td style="color:black;" >{{item.memberComapnyAddress1}}, {{item.memberComapnyAddress2}}, {{item.memberComapnyAddress3}}, {{item.memberCompanyCity}}</td>
															<td style="color:black;" >{{item.memberComapnyMobileNumber}}</td>
															<td style="color:black;" >{{item.memberComapnyEmail}}</td>
															<td><a class="btn btn-danger btn-sm" ng-click="removeWorkLandlineRow1(item.memberCompanyName)" ng-if="item.memberCompanyName != null"/><span class="glyphicon glyphicon-remove"></span></a></td>
														</tr>
														<tr>
															<td colspan="7"></td>															
															<td>
																<button type="button" class="btn btn-danger btn-sm" ng-click="deletemember()">
																	<span class="glyphicon glyphicon-trash"></span> 
																</button>
															</td>
														
														</tr>
													</tbody>
												</table>
													<!-- <div class="col-md-2">
														<div class="form-group">
															<label>{{item.landlinePhoneNumber}}</label>
														</div>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<a class="btn btn-danger btn-sm" ng-click="removeWorkLandlineRow(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
														</div>
													</div> -->
												</div>
												<div style="border-top: 2px solid green;" class="panel panel-default">
													<div style="padding: 5px 0px 0px 0px;" class="panel-body">
														<div class="panel-heading1"> address for communication<font color="red" size="3">*</font> </div>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-12">
																<label class="radio-inline">
																	<input type="radio" ng-value="'work'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()">Work
																</label>
																<label class="radio-inline">
																	<input type="radio" ng-value="'residential'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()">Residential
																</label>
																<label class="radio-inline">
																	<input type="radio" ng-value="'both'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()">Both
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
												<div class="row" style="color: #333;">
													<div class="col-md-4">
														<div class="panel-title1"> GAINS Profile  </div>
													</div>																										
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Goals</label>
															<textarea rows="4" class="form-control input-lg1" id="businessgoals" name="businessgoals" ng-model="businessgoals" placeholder="Business Goals..."></textarea>
														</div>
													</div>													
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Accomplishments</label>
															<textarea rows="4" class="form-control input-lg1" id="accomplishments" name="accomplishments" ng-model="accomplishments" placeholder="Accomplishments..."></textarea>
														</div>
													</div>													
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Interests</label>
															<textarea rows="4" class="form-control input-lg1" id="interests" name="interests" ng-model="interests" placeholder="Interests..."></textarea>
														</div>
													</div>													
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Networks</label>
															<textarea rows="4" class="form-control input-lg1" id="networks" name="networks" ng-model="networks" placeholder="Networks..."></textarea>
														</div>
													</div>													
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Skills</label>
															<textarea rows="4" class="form-control input-lg1" id="skills" name="skills" ng-model="skills" placeholder="Skills..."></textarea>
														</div>
													</div>													
												</div>			
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
												<div class="row" style="color: #333;">
													<div class="col-md-4">
														<div class="panel-title1"> TOPS Profile  </div>
													</div>																										
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Ideal Referral</label>
															<textarea rows="4" class="form-control input-lg1" id="idealreferral" name="idealreferral" ng-model="idealreferral" placeholder="Ideal Referral..."></textarea>
														</div>
													</div>													
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Top Product</label>
															<textarea rows="4" class="form-control input-lg1" id="topproduct" name="topproduct" ng-model="topproduct" placeholder="Top Product..."></textarea>
														</div>
													</div>													
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Top Problem Solved</label>
															<textarea rows="4" class="form-control input-lg1" id="topproblemsolved" name="topproblemsolved" ng-model="topproblemsolved" placeholder="Top Problem Solved..."></textarea>
														</div>
													</div>													
												</div>															
											</div>
										</div>		
										<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
											<nav aria-label="...">
												<ul class="pager">
													
													<li class="next"><input type="submit" ng-click="addcontactdetail(<%= request.getParameter("membercategoryid")%>, <%= request.getParameter("tenureplan")%>, '<%= request.getParameter("familyplan")%>')" value="Save & Next" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px;"></li>
												</ul>
											</nav>
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						<!-- </form> -->
					</div>
				</div>
			</div>
		</section>
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
				                	aspectRatio: 1 / 1,	
				                	boxWidth: 650,   
				                    boxHeight: 400,
				                    setSelect: [ 100, 100, 350, 350 ],
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
