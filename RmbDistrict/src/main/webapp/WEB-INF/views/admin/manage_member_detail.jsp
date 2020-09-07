<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Member Detail</title>
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/admin_manage_my_profile.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
		<style type="text/css" id="picture_basic_dependence_css">
			.picture-element-principal{background:url(images/icon_add_image2.png) no-repeat 50% 50%}.picture-dropped{border:2px #666 dashed!important;}
		</style>
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
				   $("#anniversarydate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#phone").mask("(999) 999-9999");
				   $("#tin").mask("99-9999999");
				   $("#ssn").mask("999-99-9999");
				});
		</script>
			
	</head>	
	<body ng-app="rcbs" ng-controller="manageProfileCtrl" ng-cloak ng-init="getmemberdetail(<%= request.getParameter("memberid")%>)">
		<%@include file="header.jsp" %>	
		<section>
			<div class="container">
				<div class="row">
					<div class="board">
						<div ng-show="membercategoryname != 7" class="board-inner">
							<ul class="nav nav-tabs" id="myTab">
								<div class="liner"></div>
								<li class="active">
									<a href="#membership" data-toggle="tab" title="MEMBERSHIP">
										<span class="round-tabs one">
											<i class="glyphicon glyphicon-home"></i>
										</span>
									</a>
								</li>
								<li class="disabled">
									<a href="#" data-toggle="tab" title="CONTACT DETAILS">
										<span class="round-tabs two">
											<i class="glyphicon glyphicon-gift"></i>
										</span>
									</a>
								</li>
								<li class="disabled">
									<a href="#spouse" data-toggle="tab" title="FAMILY DETAILS">
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
						<div class="tab-content">
							<!-- Membership	 START-->
							<!-- <form role="form"> -->
								<div class="tab-pane fade in active" id="membership">
									<div class="col-md-12">
										<div class="panel panel-default">
											<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
												<div class="row" style="color: #333;">
													<div class="col-md-6">
														<div class="panel-title1">Membership INFO</div>
													</div>	
													<div class="col-md-2">
														<div class="panel-title1">
															<button type="button" class="btn btn-default btn-lg" data-toggle="modal"data-target="#barcodeModal">
																<span class="glyphicon glyphicon-barcode"></span> Barcode
															</button>
														</div>
													</div>
													<div class="col-md-2">
														<div class="panel-title1">
															<button type="button" class="btn btn-default btn-lg" data-toggle="modal"data-target="#qrcodeModal">
																<span class="glyphicon glyphicon-qrcode"></span> QR code
															</button>
														</div>
													</div>
													<div class="col-md-2" align="right">
														<div class="panel-title1">{{rotaryyeartitle}}</div>
													</div>																									
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
												<div class="col-md-3">
														<div class="form-group">
															<label>Fellowship Name<font color="red" size="3">*</font></label>
															<select ng-model="fellowship_id" id="fellowship_id" name="fellowship_name"  class="form-control input-lg2">
																<option value=""selected>Select Fellowship Name</option>
																<option ng-repeat="items in getAllFellowshipNameList" value="{{items.fellowship_id}}">{{items.fellowship_name}}</option>
															</select>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label>Club Name<font color="red" size="3">*</font></label>
															<select ng-model="memberclubname" id="memberclubname" name="memberclubname" ng-options="item.clubId as item.clubName for item in allclubs" class="form-control input-lg2">
																<option value="">Club Name</option>
															</select>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label>Type Of Members<font color="red" size="3">*</font></label>
															<select ng-model="membercategoryname" id="membercategoryname" name="membercategoryname" ng-options="item.memberCategoryId as item.memberCategoryName for item in getmembercategory" ng-change="typeschange()" class="form-control input-lg2">
																<option value="">Type Of Member</option>
															</select>
														</div>
													</div>
													<div class="col-md-3">
														<label>Membership No<font color="red" size="3">*</font></label>
														<input type="text" disabled="disabled" ng-model="membershipId" class="form-control input-lg2" placeholder="Membership No" />
													</div>
												</div>
												<div class="row">													
													<div class="col-md-3">
														<div class="form-group">
															<label>Business Category<font color="red" size="3">*</font></label>
															<select ng-model="businesscategoryid" id="businesscategoryid" name="businesscategoryid" ng-options="item.businessCategoryId as item.businessCategoryName for item in allbusinesscategories" class="form-control input-lg2">
																<option value="">Business Category</option>
															</select>
														</div>
													</div>																												
													<div class="col-md-3">
														<div class="form-group">
															<label>Tenure Plan</label>
															<select id="tenureplan" name="tenureplan" ng-model="tenureplan" class="form-control input-lg2">
																<option value="">Tenure Plan</option>
																<option value="1">1 year</option>
																<option value="2">2 years</option>
																<option value="3">3 years</option>
																<option value="4">4 years</option>
																<option value="5">5 years</option>
																<option value="6">6 years</option>
																<option value="7">7 years</option>
																<option value="8">8 years</option>
																<option value="9">9 years</option>
																<option value="10">10 years</option>																
															</select>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label>Vocation </label>
															<select id="tenureplan" name="vocation" ng-model="vocation" class="form-control input-lg2">
																<option value="0">Vocation</option>
																<option ng-repeat="item in getVocation" value="{{item.VocationId}}">{{item.VocationTitle}}</option>																
															</select>
														</div>
													</div>												
													<div class="col-md-3">
														<div class="form-group">
															<label>Date of Joining</label>
															<input type="text" id="joiningdate" name="joiningdate" ng-model="joiningdate" class="form-control input-lg2" placeholder="DD/MM/YYYY">
														</div>
													</div>
												</div>								
												<div style="padding-bottom: 15px;" class="panel panel-default">
													<div style="padding: 5px 0px 0px 0px;" class="panel-body">
														<div class="panel-heading1"> Personal Info </div>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-2">
																<div class="form-group">
																	<label>Title</label>
																	<select id="membertitle" name="membertitle" ng-model="membertitle" class="form-control input-lg2">
																		<option value="">Title</option>
																		<option value="Dr.">Dr</option>
																		<option value="Er.">Er</option>
																		<option value="Mr.">Mr</option>
																		<option value="Ms.">Ms</option>
																		<option value="Mx.">Mx</option>
																		<option value="Mrs.">Mrs</option>
																		<option value="Miss.">Miss</option>
																	</select>
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>First Name<font color="red" size="3">*</font></label>
																	<input type="text" id="firstname" name="firstname" ng-model="firstname" class="form-control input-lg1" placeholder="First Name" capitalize-first>
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<label>Middle Name</label>
																	<input type="text" id="middlename" name="middlename" ng-model="middlename" class="form-control input-lg1" placeholder="Middle Name" capitalize-first>
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>Last Name<font color="red" size="3">*</font></label>
																	<input type="text" id="lastname" name="lastname" ng-model="lastname" class="form-control input-lg1" placeholder="Last Name" capitalize-first>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-2">
																<label>Gender<font color="red" size="3">*</font></label>
																<select id="gender" name="gender" ng-model="gender" class="form-control input-lg2">
																	<option value="">Gender</option>
																	<option value="M">Male</option>
																	<option value="F">Female</option>
																</select>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>Date Of Birth</label>
																	<input type="text" id="dateofbirth" name="dateofbirth" ng-model="dateofbirth" class="form-control input-lg2" placeholder="DD/MM/YYYY">
																</div>
															</div>
															<div class="col-md-2">
																<label>Blood Group</label>
																<select id="bloodgroup" name="bloodgroup" ng-model="bloodgroup" class="form-control input-lg2">
																	<option value="">Blood Group</option>
																	<option value="A+">A+</option>
																	<option value="A+">A-</option>
																	<option value="B+">B+</option>
																	<option value="B-">B-</option>
																	<option value="B+">O+</option>
																	<option value="B+">O-</option>
																	<option value="B+">AB+</option>
																	<option value="B+">AB-</option>
																</select>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>Anniversary Date</label>
																	<input type="text" id="anniversarydate" name="anniversarydate" ng-model="anniversarydate" class="form-control input-lg2" placeholder="DD/MM/YYYY">
																</div>
															</div>														
														</div>
														<div class="row">
															<div class="col-md-3">
																<div class="form-group" id="typediv">
																	<label>Nationality</label>																	
																	<select ng-model="membertypename" id="membertypename" name="membertypename" ng-options="item.memberTypeId as item.memberTypeName for item in getmembertype" class="form-control input-lg2">
																		<option value="">Nationality</option>
																	</select>
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-group" ng-show="membertypename == 1 || membertypename == 2">
																	<label>Aadhar Number</label>
																	<input type="text" id="aadharnumber" name="aadharnumber" ng-model="$parent.aadharnumber" class="form-control input-lg1" placeholder="Aadhar Number" disallow-spaces capitalize>
																</div>
																<div class="form-group" ng-show="membertypename == 3">
																	<label>Country Of Citizenship<font color="red" size="3">*</font></label>
																	<select id="countrynamecitizenship" name="countrynamecitizenship" ng-model="$parent.countrynamecitizenship" ng-options="item.countryId as item.countryName for item in getcountry" ng-change="onCountryChange()" class="form-control input-lg2">
																		<option value="">Select Country</option>
																	</select>
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>Passport Number</label>
																	<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<label>PAN Number</label>
																	<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control input-lg1" placeholder="PAN No" disallow-spaces capitalize>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-10" align="center">															
																<label style="float: left;">Profile Picture</label>
																<input type='file' id="imgInp" name="imgInp" class="form-control"/>																
																																
																	<img src="#" id="target"/>
																																									
																<form name="myForm">
																	<input type="hidden" name="x" id="valuex"/>
																	<input type="hidden" name="y" id="valuey"/>
																	<input type="hidden" name="w" id="valuew"/>
																	<input type="hidden" name="h" id="valueh"/>																	
																</form>
															</div>
															<div class="col-md-2">
																<img alt="" src="{{profilepicture}}" class="img-responsive" width="130px;" height="130px;">
															</div>
														</div>
													</div>
												</div>
												<div style="border-top: 2px solid green;" class="panel panel-default">
													<div style="padding: 5px 0px 0px 0px;" class="panel-body">
														<div class="panel-heading1"> education details </div>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-2">
																<div class="form-group">
																	<label>Degree Name</label>
																	<input id="degreename" name="degreename" ng-model="degreename" type="text" class="form-control input-lg1" placeholder="Degree">
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<label>Passing Year</label>
																	<input id="passingyear" name="passingyear" ng-model="passingyear" type="text" class="form-control input-lg1" placeholder="Passing Year">
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<label>Grade or %</label>
																	<input id="grade" name="grade" ng-model="grade" type="text" class="form-control input-lg1" placeholder="Grade or %">
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>Institute / University</label>
																	<input id="institutename" name="institutename" ng-model="institutename" type="text" class="form-control input-lg1" placeholder="Institute or College">
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<a id="Add" value="Add" name="Add" ng-click="addEducationRowEdit()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
																</div>
															</div>
														</div>
														<div class="row" ng-repeat="item in getmembereducationdetail">
															<div class="col-md-2">
																<div class="form-group">
																	<label>{{item.degreeName}}</label>
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<label>{{item.passingYear}}</label>
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<label>{{item.grade}}</label>
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<label>{{item.instituteName}}</label>
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<a class="btn btn-danger btn-sm" ng-click="removeEducationRowEdit(item.memberEducationId)" ng-if="item.degreeName != null"/><span class="glyphicon glyphicon-remove"></span></a>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div style="border-top: 2px solid green;" class="panel panel-default">
													<div style="padding: 5px 0px 0px 0px;" class="panel-body">
														<div class="panel-heading1"> login credentials </div>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-6">
																<div class="input-group">
																	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
																	<input id="email" name="email" ng-model="email" type="text" class="form-control input-lg1" placeholder="Email id*" ng-change="checkemailaddress()">
																</div>
															</div>
															<div class="col-md-6">
																<div class="input-group">
																	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
																	<input id="password" name="password" ng-model="password" type="password" class="form-control  input-lg1" value="" placeholder="Password*">                                         
																</div>
															</div>
														</div>
													</div>
													<div class="w3-note w3-example ">
														<p> <span style="color: red; font-weight: 600;"> Note: </span> your user name is your Email Id</p>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">
												<a href="<%= request.getContextPath() %>/manageRmbb/manage_members" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand;">Previous</a>
											</div>
											<div class="col-md-10">
												<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
													<nav aria-label="...">
														<ul class="pager">
															<li class="next" ng-show="membercategoryname != 7"><a ng-click="redirectcontactdetail(<%=request.getParameter("memberid") %>)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand; margin-left: 10px;">Next</a></li>
															<li class="next" ng-show="membercategoryname != 7"><button type="submit" ng-click="editmemberdetail(memberid)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px;">Update & Next <i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
															<li class="next" ng-show="membercategoryname == 7"><button type="submit" ng-click="editmemberdetail(memberid)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px;">Update<i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button></li>
														</ul>
													</nav>
												</div>
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
		<div class="modal fade" id="barcodeModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
						<h3 class="modal-title" id="lineModalLabel"> BARCODE </h3>
					</div>
					<div class="modal-body">
						<img src="{{memberbarcode}}" class="img-responsive">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="qrcodeModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
						<h3 class="modal-title" id="lineModalLabel"> QR CODE </h3>
					</div>
					<div class="modal-body">
						<img src="{{memberqrcode}}" class="img-responsive">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker,#datepicker1").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true,
					value: new Date()
		         });
		         $("#datepicker2").kendoDatePicker({
			    	format: "dd/MM/yyyy",
					dateInput: true					
			     });
		    });
			$(".KendoDate").bind("focus", function(){
	   			$(this).data("kendoDatePicker").open(); 
			});			
		</script>
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
