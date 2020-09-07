<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title> Manage Contact Details </title>

<link rel="shortcut icon" href="img/favicon.ico">

<!-- fonts -->
<link href="<%=request.getContextPath() %>/resources/front2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- styles -->
<link href="<%=request.getContextPath() %>/resources/front2/css/sb-admin-2.min.css" rel="stylesheet">
<!-- -->
<link href="<%=request.getContextPath() %>/resources/front2/css/style.css" rel="stylesheet">
<!-- Responsive -->
<link href="<%=request.getContextPath() %>/resources/front2/css/responsive.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/font-awesome-4.7.0/css/font-awesome.min.css">         



<script src="<%=request.getContextPath() %>/resources/front2/js/slimselect.min.js"></script>
<link href="<%=request.getContextPath() %>/resources/front2/css/slimselect.min.css" rel="stylesheet"></link>

<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/css/smart_wizard.min.css">
<link rel='stylesheet' href='<%=request.getContextPath() %>/resources/front2/css/bootstrap.min.css'>
<!--*******-->
<script src="https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>

<!--*******-->
<%-- <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.mobile.min.css" />
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/kendo.all.min.js"></script> --%>

	<link rel="icon" href="https://www.tigeradvertising.in/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
			<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
			
	<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/my_contact_detail.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>

		
		<style>
		
	.dropdownparrow {
  -webkit-appearance: none;
  /*webkit browsers */
  -moz-appearance: none;
  /*Firefox */
  appearance: none;
  /* modern browsers */
  border-radius: 0;
    background-color: #fbfbfb;
    color: black;
}

</style>
</head>

<body id="page-top" ng-app="rcbs" ng-controller="manageContactDetailCtrl" ng-cloak ng-init="getcontactdetail(<%= session.getAttribute("loginid")%>) || getFellowship_id(<%= session.getAttribute("fellowshipId")%>)">
<%@include file="header1.jsp" %>


	<form name="myForm">
									<input type="hidden" name="x" id="valuex"/>
									<input type="hidden" name="y" id="valuey"/>
									<input type="hidden" name="w" id="valuew"/>
									<input type="hidden" name="h" id="valueh"/>																	
								</form>


<div class="container-fluid"> 
<div class="row profile">
<!--  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<div class="tab">
						  <button  id="UserDetails" class="tablinks" onclick="openCity(event, 'London')">User Details</button>
						  <button id="FamilyDetails"  class="tablinks" onclick="openCity(event, 'Paris')">Family Details</button>
					   <button id="PersonalDetails" class="tablinks" onclick="openCity(event, 'Tokyo')">Personal Information</button> 
						   <button id="NomineeDetails" class="tablinks" onclick="openCity(event, 'India')">  Nominee Details </button>
						    <button id="PaymentDetails" class="tablinks" onclick="openCity(event, 'Bharat')">  Payment Details </button>
							<div> <a onclick="myFunction()"> <i class="fa fa-angle-down" id="down" style="color:white;float: right;margin-right:25px;margin-top:15px;  font-size: 20px" aria-hidden="true"></i></a></div>
					</div>
</div> -->
<!-- Earnings (Monthly) Card Example -->
<div class="col-lg-12 col-md-12 col-sm-12 col-xl-12">  
<!-- Collapsable Card Example -->
<div class="card  mb-4">


    
<!-- Card Header -->
<div id="smartwizard">

	<ul class="card-header pa-0">
	
		<li>
			<a href=""> <i class="fa fa-user-circle-o"></i> <br />
			<small style="padding-left: 0px; line-height: 25px;"> Members Info </small>
			</a>
		</li>
		
		<li><a href=""> <i class="fa fa-map-marker"></i> <br /><small> Residential Address </small></a></li>
		
		<li><a href=""> <i class="fa fa-users"></i> <br /><small> Family Info </small></a></li>
		
		<li><a href=""> <i class="fa fa-exchange"></i><br /><small> References </small></a></li>
		
		<li><a href=""> <i class="fa fa-credit-card"></i> <br /><small> Payments </small></a></li>
	
	</ul>

</div>

<!-- <div class="card-header py-3">
		
		<div class="row">
		<div class="col-md-3">
				<h5 class="m-0 font-weight-bold text-primary">
				RESIDENTIAL ADDRESS 
				</h5>
				</div>
				<div class="col-md-3">
				</div>
				
					<div class="col-md-3">
					
						<input type="text" value="{{memberfirstname}} {{membermiddlename}} {{memberlastname}}" disabled="disabled" class="form-control input-lg2" placeholder="Old Membership No"/>
					</div>
					<div class="col-md-3">
						<input type="text" disabled="disabled" ng-model="membershipnumber" class="form-control input-lg2" placeholder="Membership No" data-toggle="tooltip" title="New Membership No." />
					</div>
		
		</div>
	
</div> -->
    <br>



<!-- Card Content -->
<div class="card-body">

<form class="">


<div class="form-group row">

	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
		<label> Address-1<font color="red" size="3">*</font> </label>	
				<input type="text" id="address1" name="address1" ng-model="address1" class="form-control " placeholder="Address Line-1" capitalize-first>		
	</div>
	
	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
		<label> Address-2<font color="red" size="3">*</font> </label>
				<input type="text" id="address2" name="address2" ng-model="address2" class="form-control " placeholder="Address Line-2" capitalize-first>		
	 </div>
	
	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
		<label> Address-3 <font color="red" size="3">*</font> </label>
		<input type="text" id="address3" name="address3" ng-model="address3" class="form-control " placeholder="Address Line-3" capitalize-first>
	</div>
		

</div>

 
<!------------------------------------------------- Row End---------------------------------------- -->

<div class="form-group row">

		<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
		<label>Country<font color="red" size="3">*</font></label>
				<select  id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countryname = 1" ng-change="onCountryChange()" class="form-control input-lg2">
					<option value="">---Select Country---</option>
				</select>		
		</div>

		<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
			<label>State<font color="red" size="3">*</font></label>
				<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statename = 1" class="form-control input-lg2">
				<option value="">---Select State---</option>
				</select>		
		</div>
	
		<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
		<label>  City Name </label>
		<input  type="text" id="cityname" name="cityname" ng-model="cityname" ng-init="cityname = 'Vadodara'" class="form-control input-lg1" placeholder="City" capitalize-first>
		</div>
	    
	


</div>
<!------------------------------------------------- Row End---------------------------------------- -->

<div class="form-group row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Pincode</label>
						<input type="text" id="pincode" name="pincode" ng-model="pincode" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Mobile Number<font color="red" size="3">*</font></label>
						<input type="text" id="mobilenumber" name="mobilenumber" ng-model="mobilenumber" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
					</div>
				</div>
			</div>
<!------------------------------------------------- Row End---------------------------------------- -->

<div class="form-group row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Phone Number</label>
							<input type="text" id="phonenumber" name="phonenumber" ng-model="phonenumber" class="form-control input-lg1" placeholder="Phone">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<a id="Add" value="Add" name="Add" style="margin-top:27px;height: 43px" ng-click="addResidentialLandlineRow1()" class="btn btn-primary btn-lg" style="margin-top:26px;"><span  style="color:white">&nbsp;Add </span></a>
						</div>
					</div>
				</div>
				<div class="form-group row" ng-repeat="item in getmemberresidentiallandline">
					<div class="col-md-2">
						<div class="form-group">
							<label>{{item.landlinePhoneNumber}}</label>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<a class="btn btn-danger btn-sm" style="background-color: white;" ng-click="removeResidentialLandlineRow1(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><i class="fa fa-trash-o txt-danger"></i></a>
						</div>
					</div>
				</div>
		<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
					<h3 class="box-title" style="color:#4A2818;"> WORK DETAILS  </h3>
		     </div>
				
					<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label>Company Name<font color="red" size="3">*</font></label>
						<input type="text"id="companyname" name="companyname" ng-model="companyname" class="form-control " placeholder="Company Name" capitalize-first>
						</div>
				
							<div class="col-md-6" align="center">															
								<label style="float: left;">Company Logo</label>
								<input type='file' id="imgInp" class="form-control" accept=".jpg,.jpeg,.png"/>																
																								
									<img src="#" id="target"/>
																																	
							
							</div>
							<div class="col-md-2">
								<img alt="" src="{{logoimage}}" class="img-responsive" width="130px;" height="130px;">
							</div>
						</div>
				<div class="form-group row">
				
				
				
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
				<label>Occupation<font color="red" size="3">*</font></label>
				<input class="form-control" id="occupation" name="occupation" ng-model="occupation"  type="text" placeholder="Occupation" capitalize-first>
				</div>
				
				
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
				<label>Designation<font color="red" size="3">*</font></label>
				<input type="text" id="designation" name="designation" ng-model="designation" class="form-control " placeholder="Designation" capitalize-first>
				</div>
				
				
				
				</div>
		    

			
			<div class="form-group row">
					
					
					 <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label>Business Nature<font color="red" size="3">*</font></label>
						<input type="text" id="businessnature" name="businessnature" ng-model="businessnature" class="form-control " placeholder="Business Nature" capitalize-first>
					</div>
					
			
					  <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
								<label>Fax Number</label>
								<input type="text" id="faxnumber" name="faxnumber" ng-model="faxnumber" class="form-control " placeholder="Fax-Office">
							
							</div>
							
							 <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
									
										<label>Website</label>
									<input type="text" id="website" name="website" ng-model="website" class="form-control " placeholder="Website">
											
								</div>			
			
			</div>
					
		<div class="form-group row">
			<div class="col-md-6">
				<div class="form-group">
					<label>About your Business</label>
					<textarea rows="4" class="form-control input-lg1" id="aboutbusiness" name="aboutbusiness" ng-model="aboutbusiness" placeholder="About your business..."></textarea>
				</div>
			</div>
		
			<div class="col-md-6">
				<div class="form-group">
					<label>Business Keywords (Comma separated)</label>
					<textarea rows="4" class="form-control input-lg1" id="businesskeywords" name="businesskeywords" ng-model="businesskeywords" placeholder="Ex.- IT Firm, Website, Mobile Application, ..."></textarea>
				</div>
			</div>
		</div>	

		<div class="form-group row">
			<div class="col-md-12">
				<div class="form-group">
					<label>Email</label>
					<input type="text" id="email" name="email" ng-model="email" class="form-control input-lg1" placeholder="Email id">
				</div>
			</div>
		</div>
	
	<div class="form-group row">
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


	<div class="form-group row">
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

<!------------------------------------ Row Start -->	
				<div class="form-group row">
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
				
				
				<div class=" form-group row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Phone Number</label>
									<input type="text" id="phonenumberwork" name="phonenumberwork" ng-model="phonenumberwork" class="form-control input-lg1" placeholder="Phone">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<a id="Add" value="Add" name="Add" ng-click="addWorkLandlineRow1()" class="btn btn-primary btn-lg"  style="margin-top:24px;height: 43px;color:white"><span class="glyphicon glyphicon-plus-sign"></span style="color:white">&nbsp;Add</a>
								</div>
							</div>
						</div>
						<div class="form-group  row" ng-repeat="item in getmemberworklandline">
							<div class="col-md-2">
								<div class="form-group">
									<label>{{item.landlinePhoneNumber}}</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<a class="btn btn-danger btn-sm" style="background-color: white" ng-click="removeWorkLandlineRow1(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><i class="fa fa-trash-o txt-danger"></i></a>
								</div>
							</div>
						</div>
						
<!------------------------------------ Row Start -->					
		<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
			<h3 class="box-title" style="color:#4A2818;"> Address for communication<font color="red" size="3">*</font> </h3>
    	</div>
    	
<!------------------------------------ Row Start -->	
			<div class="form-group row">
				
				
				<div class="col-md-2">
					<label class="radio-inline">
						<input type="radio" ng-value="'work'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()"> &nbsp;Work
					</label>
				</div>
				<div class="col-md-2">
					<label class="radio-inline">
						<input type="radio" ng-value="'residential'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()"> &nbsp;Residential
					</label>
				</div>
				
				<div class="col-md-2">
					<label class="radio-inline">
						<input type="radio" ng-value="'both'" id="communication" name="communication" ng-model="communication" ng-click="checkcommunication()"> &nbsp;Both
					</label>
				</div>
				
			</div>

	<!------------------------------------ Row Start -->					
		<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
			<h3 class="box-title" style="color:#4A2818;"> Gains Profile </h3>
    	</div>
<!------------------------------------ Row Start -->	
			<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Goals</label>
							<textarea rows="4" class="form-control input-lg1" id="businessgoals" name="businessgoals" ng-model="businessgoals" placeholder="Business Goals..."></textarea>
						</div>
					</div>													

					<div class="col-md-4">
						<div class="form-group">
							<label>Accomplishments</label>
							<textarea rows="4" class="form-control input-lg1" id="accomplishments" name="accomplishments" ng-model="accomplishments" placeholder="Accomplishments..."></textarea>
						</div>
					</div>													

					<div class="col-md-4">
						<div class="form-group">
							<label>Interests</label>
							<textarea rows="4" class="form-control input-lg1" id="interests" name="interests" ng-model="interests" placeholder="Interests..."></textarea>
						</div>
					</div>													
				</div>
<!------------------------------------ Row Start -->	
			<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Networks</label>
							<textarea rows="4" class="form-control input-lg1" id="networks" name="networks" ng-model="networks" placeholder="Networks..."></textarea>
						</div>
					</div>													

					<div class="col-md-4">
						<div class="form-group">
							<label>Skills</label>
							<textarea rows="4" class="form-control input-lg1" id="skills" name="skills" ng-model="skills" placeholder="Skills..."></textarea>
						</div>
					</div>													
				</div>	
				
	<!------------------------------------ Row Start -->					
		<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
			<h3 class="box-title" style="color:#4A2818;">Tops Profile </h3>
    	</div>
    		<div class="form-group row">
						<div class="col-md-4">
							<div class="form-group">
								<label>Ideal Referral</label>
								<textarea rows="4" class="form-control input-lg1" id="idealreferral" name="idealreferral" ng-model="idealreferral" placeholder="Ideal Referral..."></textarea>
							</div>
						</div>													
					
						<div class="col-md-4">
							<div class="form-group">
								<label>Top Product</label>
								<textarea rows="4" class="form-control input-lg1" id="topproduct" name="topproduct" ng-model="topproduct" placeholder="Top Product..."></textarea>
							</div>
						</div>													
				
						<div class="col-md-4">
							<div class="form-group">
								<label>Top Problem Solved</label>
								<textarea rows="4" class="form-control input-lg1" id="topproblemsolved" name="topproblemsolved" ng-model="topproblemsolved" placeholder="Top Problem Solved..."></textarea>
							</div>
						</div>	
						<br><br>													
					</div>					  	
<!------------------------------------ Row Start -->	

<br><br>

</form>   
<div class="form-group row">	
						<br><br>
				<div class="col-md-3 text-left">
				
					<button type="submit"  ng-click='redirectmemberdetail(<%=request.getParameter("memberid") %>,2)'  class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Previous
					<i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
					</button>	</div>	
					<div class="col-md-4 text-left"> </div>									
				<div class="col-md-3 text-right">
					<button type="submit" ng-click="editcontactdetail(memberid, membercategoryid, tenureplan, 2)"  class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Update & Next
					<i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
					</button>			
				</div>
				<div class="col-md-2 text-right">
					<button type="submit" ng-click="redirectfamilydetail(memberid, membercategoryid, tenureplan, 2)" class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Next</button>			
				</div>
				
				</div>	

</div>
</div>

</div>

</div>
</div>
<!-- End of Main Content -->

<!--/#footer START-->
<%@include file="footer1.jsp" %>
<!--/#footer END-->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
<i class="fas fa-angle-up"></i>
</a>








<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
<a class="btn btn-primary" href="login.html">Logout</a>
</div>
</div>
</div>
</div>




<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath() %>/resources/front2/js/sb-admin-2.min.js"></script>

<!--inputmask-->
<script src="<%=request.getContextPath() %>/resources/front2/js/bootstrap-inputmask.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/front2/js/jquery.smartWizard.min.js"></script>

<script>
  
$(document).ready(function(){
$('.pass_show').append('<span class="ptxt">Show</span>');  
});
  

$(document).on('click','.pass_show .ptxt', function(){ 

$(this).text($(this).text() == "Show" ? "Hide" : "Show"); 

$(this).prev().attr('type', function(index, attr){return attr == 'password' ? 'text' : 'password'; }); 

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


		
<script type="text/javascript">
$(document).ready(function(){
// Smart Wizard
$('#smartwizard').smartWizard({ 
selected: 1, 
theme: 'default',
transitionEffect:'fade',
toolbarSettings: {toolbarPosition: '',
toolbarExtraButtons: [
// {label: 'Finish', css: 'btn-info', onClick: function(){ alert('Finish Clicked'); }},
// {label: 'Cancel', css: 'btn-danger', onClick: function(){ $('#smartwizard').smartWizard("reset"); }}
]
}
});

});   
</script>  	
    
</body>

</html>
