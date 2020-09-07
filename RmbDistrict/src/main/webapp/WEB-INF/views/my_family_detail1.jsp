<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
 

<title> Manage Family Details </title>

<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front2/img/favicon.ico">

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
<!--*******-->
<script src="<%=request.getContextPath() %>/resources/front2/https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>

<!--*******-->
<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.mobile.min.css" />
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/kendo.all.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.maskedinput.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		
				<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>

		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/my_family_detail.js"></script>


<!--<link rel="stylesheet" href="css/profile.css">-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/css/smart_wizard.min.css">
<link rel='stylesheet' href='<%=request.getContextPath() %>/resources/front2/css/bootstrap.min.css'>


</head>

<body id="page-top" ng-app="rcbs" ng-controller="myFamilyDetailCtrl" ng-init="getspousedetail(<%= session.getAttribute("loginid")%>)" ng-cloak>
<%@include file="header1.jsp" %>

<form name="myForm">
					<input type="hidden" name="x" id="valuex" ng-model="valuex"/>
					<input type="hidden" name="y" id="valuey" ng-model="valuey"/>
					<input type="hidden" name="w" id="valuew" ng-model="valuew"/>
					<input type="hidden" name="h" id="valueh" ng-model="valueh"/>																	
				</form>
<!-- Page Wrapper -->
<div id="wrapper">

<div id="content-wrapper" class="d-flex flex-column">
<!-- Main Content -->
<div id="content">
<!-- End of Topbar -->

<div class="container-fluid"> 
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
<!-- Content Row -->


<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<!-- Card Header -->
<!-- Card Header -->


<!-- Collapsable Card Example -->
<div class="card shadow mb-4">
<!-- Card Header - Accordion -->
<a style="background-color: #4e73df;" href="#collapseCardExample" class="d-block card-header py-3" ng-click="spousememberid('<%= session.getAttribute("membershipnumber") %>', '<%= session.getAttribute("loginid") %>')" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
<h6 class="m-0 font-weight-bold text-light"> ADD SPOUSE DETAIL </h6>
</a>
<!-- Card Content - Collapse show-->



<div class="collapse " id="collapseCardExample" >
<div class="card-body" >
<form class="">

<!------------------------------------ Row Start -->	
		<div class="form-group row">																	
				<div class="col-md-4">
					<div class="form-group">
						<label>Membership Number</label>
						<input type="text" id="spouseid" name="spouseid" ng-model="spouseid" readonly class="form-control input-lg1" placeholder="Membership No">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Relation<font color="red" size="3">*</font></label>
						<select id="relation" name="relation" ng-model="relation" class="form-control input-lg2">
							<option value="">Select Relation</option>
							<option value="Spouse">Spouse</option>
							<option value="Child">Child</option>
						</select>
					</div>
				</div>
				
					</div>


<!------------------------------------ Row Start -->	
		<div class="form-group row">
					<div class="col-lg-2 col-md-3 col-sm-6 col-xs-12 ">
						<div class="form-group">
							<label>Title</label>
							<select id="memberfamilytitle" name="memberfamilytitle" ng-model="memberfamilytitle" class="form-control input-lg2">
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
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 ">
						<div class="form-group">
							<label>First Name<font color="red" size="3">*</font></label>
							<input type="text" id="firstname" name="firstname" ng-model="firstname" class="form-control input-lg1" placeholder="First Name" capitalize-first>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 ">
						<div class="form-group">
							<label>Middle Name</label>
							<input type="text" id="middlename" name="middlename" ng-model="middlename" class="form-control input-lg1" placeholder="Mid. Name" capitalize-first>
						</div>
					</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 ">
						<div class="form-group">
							<label>Last Name<font color="red" size="3">*</font></label>
							<input type="text" id="lastname" name="lastname" ng-model="lastname" class="form-control input-lg1" placeholder="Last Name" capitalize-first>
						</div>
					</div>
				</div>
				
<!------------------------------------ Row Start -->	
		<div class="form-group row">

		
			<div class="col-md-4">
				<label>Gender<font color="red" size="3">*</font></label>
				<select id="gender" name="gender" ng-model="gender" class="form-control input-lg2">
					<option value="">Gender</option>
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</div>

		
		 <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label>Date Of Birth</label>
						
							<input class="KendoDate" id="spouldobedit" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						
					</div>
					
			<!-- 		
			<div class="col-md-8">
				<div class="form-group">
					<label>Date Of Birth</label>
				<div class="row">
					
					
		
						 <div class="col-md-4">
							<select id="birthdate" name="birthdate" ng-model="birthdate" ng-options="item for item in date" class="form-control input-lg2">
								<option value="" style="padding-top: 5px; padding-bottom: 5px;">Date</option>
							</select>
						</div>
					<div class="col-md-5">
							<select id="birthmonth" name="birthmonth" ng-model="birthmonth" class="form-control input-lg2">
								<option value="">Month</option>
								<option value="01">January</option>
								<option value="02">February</option>
								<option value="03">March</option>
								<option value="04">April</option>
								<option value="05">May</option>
								<option value="06">June</option>
								<option value="07">July</option>
								<option value="08">August</option>
								<option value="09">September</option>
								<option value="10">October</option>
								<option value="11">November</option>
								<option value="12">December</option>
							</select>
						</div> 
					
					
					
					 <div class="col-md-3">
							<input type="text" id="birthyear" name="birthyear" ng-model="birthyear" class="form-control input-lg2" placeholder="Year">
						</div> 
					</div>
				</div>
			</div>
			 -->
			
			<div class="col-md-4">
				
					<label>Blood Group</label>
					<select id="bloodgroup" name="bloodgroup" ng-model="bloodgroup" class="form-control input-lg2">
						<option value="">Blood Group</option>
						<option value="A+">A+</option>
						<option value="A-">A-</option>
						<option value="B+">B+</option>
						<option value="B-">B-</option>
						<option value="O+">O+</option>
						<option value="O-">O-</option>
						<option value="AB+">AB+</option>
						<option value="AB-">AB-</option>
					</select>
				
			</div>																		
		</div>																	
<!------------------------------------ Row Start -->	
		<div class="form-group row">
			<div class="col-md-4">
				<div class="form-group">
					<label>Aadhar Number</label>
					<input type="text" id="aadharnumber" name="aadharnumber" ng-model="aadharnumber" class="form-control input-lg1" placeholder="Aadhar Number" disallow-spaces capitalize>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label>Passport Numberr</label>
					<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label>Pan Number</label>
					<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control input-lg1" placeholder="PAN Number" disallow-spaces capitalize>
				</div>
			</div>
		</div>
<!------------------------------------ Row Start -->	
		<div class="form-group row">
			<div class="col-md-12" align="center">
				<label style="float: left;">Profile Picture</label>
				<input type='file' id="imgInp" class="form-control"/>																
																				
					<img src="#" id="target"/>
																													
				
			</div>																		
		</div>
	<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
					<h3 class="box-title" style="color:#4A2818;">Education details  </h3>
		     </div>
	
	<!------------------------------------ Row Start -->

			
	
<!------------------------------------ Row Start -->	
		<div class="form-group row">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="form-group">
							<label>Degree Name</label>
							<input id="degreename" name="degreename" ng-model="degreename" type="text" class="form-control input-lg1" placeholder="Degree">
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
						<div class="form-group">
							<label>Passing Year</label>
							<input id="passingyear" name="passingyear" ng-model="passingyear" type="text" class="form-control input-lg1" placeholder="Passing Year">
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
						<div class="form-group">
							<label>Grade or %</label>
							<input id="grade" name="grade" ng-model="grade" type="text" class="form-control input-lg1" placeholder="Grade or %">
						</div>
					</div>
				
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="form-group">
							<label>Institute / University</label>
							<input id="institutename" name="institutename" ng-model="institutename" type="text" class="form-control input-lg1" placeholder="Institute or College">
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<a id="Add" value="Add" name="Add" ng-click="addEducationRow()" class="btn btn-primary btn-lg" style="margin-top:18px;height: 45px"><span class="glyphicon glyphicon-plus-sign" ></span>&nbsp; <span style="color:white">Add</span> </a>
						</div>
					</div>
				</div>
				<div class="row" ng-repeat="item in education">
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
							<a class="btn btn-danger btn-sm" ng-click="removeEducationRow(item.degreeName)" ng-if="item.degreeName != null"/><span class="glyphicon glyphicon-remove"></span></a>
						</div>
					</div>
				</div>
		
	
	<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
					<h3 class="box-title" style="color:#4A2818;"> Login Credentials </h3>
		     </div>
	
	<!------------------------------------ Row Start -->	
		<div class="form-group row">
					<div class="col-md-6">
					<label>Email Id<font color="red" size="3">*</font></label>
						<div class="input-group">
							
							<span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
							<input type="text" id="email" name="email" ng-model="email" class="form-control input-lg1" placeholder="Email Id*" ng-change="checkemailaddress()">
						</div>
					</div>
					<div class="col-md-6">
					<label>Password<font color="red" size="3">*</font></label>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
							<input type="password" id="password" name="password" ng-model="password" class="form-control input-lg1" placeholder="Password*">                                        
						</div>
					</div>
				</div>																			
			
			<div class="w3-note w3-example">
				<p> <span style="color: red; font-weight: 600;"> <br>&emsp;Note: </span> your user name is your Email Id</p>
			</div>
		
		
	<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
				<div class="col-lg-8">	
					<h3 class="box-title" style="color:#4A2818;"> Residential Address </h3>
								&emsp;	&emsp;	&emsp;	&emsp;	&emsp;																								
							&emsp;<input id="residentialaddress" name="residentialaddress" ng-model="residentialaddress" type="checkbox" ng-click="residentialaddresssameasmember(<%= session.getAttribute("memberid")%>)"/>
							<label for="residentialaddress">Same As Member</label>																																																
						</div>
		     </div>
	
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
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
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
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
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Pincode</label>
							<input type="text" id="pincode" name="pincode" ng-model="pincode" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label>Mobile Number</label>
							<input type="text" id="mobilenumber" name="mobilenumber" ng-model="mobilenumber" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
						</div>
					</div>
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Phone Number</label>
							<input type="text" id="phonenumber" name="phonenumber" ng-model="phonenumber" class="form-control input-lg1" placeholder="Phone">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<a id="Add" value="Add" name="Add" ng-click="addFamilyResidentialLandlineRow()" class="btn btn-primary btn-lg" style="margin-top:25px;height:43px"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp; <span style="color:white">Add </span></a>
						</div>
					</div>
				</div>
				<div class="form-group row" ng-repeat="item in familyresidentiallandline">
					<div class="col-md-2">
						<div class="form-group">
							<label>{{item.landlinePhoneNumber}}</label>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<a class="btn btn-danger btn-sm" ng-click="removeFamilyResidentialLandlineRow(item.landlinePhoneNumber)" ng-if="item.landlinePhoneNumber != null"/><span class="glyphicon glyphicon-remove"></span></a>
						</div>
					</div>
				</div>
		
		
	<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
				<div class="col-lg-8">	
					<h3 class="box-title" style="color:#4A2818;"> 	Work Detail </h3>
								&emsp;	&emsp;	&emsp;	&emsp;	&emsp;																								
							&emsp;	<input id="workdetail" name="workdetail" ng-model="workdetail" type="checkbox" ng-click="workdetailsameasmember(<%= session.getAttribute("memberid")%>)"/>
								<label for="workdetail">Same As Member</label>																																																
						</div>
		     </div>
	
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Occupation</label>
							<input type="text" id="occupation" name="occupation" ng-model="occupation" class="form-control input-lg1" placeholder="Occupation" capitalize-first>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Designation</label>
							<input type="text" id="designation" name="designation" ng-model="designation" class="form-control input-lg1" placeholder="Designation" capitalize-first>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Company Name</label>
							<input type="text" id="companyname" name="companyname" ng-model="companyname" class="form-control input-lg1" placeholder="Company Name" capitalize-first>
						</div>
					</div>
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Business Nature</label>
							<input type="text" id="businessnature" name="businessnature" ng-model="businessnature" class="form-control input-lg1" placeholder="Business Nature" capitalize-first>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Fax Number</label>
							<input type="text" id="faxnumber" name="faxnumber" ng-model="faxnumber" class="form-control input-lg1" placeholder="Fax-Office">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Website</label>
							<input type="text" id="website" name="website" ng-model="website" class="form-control input-lg1" placeholder="Website">
						</div>
					</div>
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-12">
						<div class="form-group">
							<label>Email</label>
							<input type="text" id="emailwork" name="emailwork" ng-model="emailwork" class="form-control input-lg1" placeholder="Email">
						</div>
					</div>
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Address-1</label>
							<input type="text" id="address1work" name="address1work" ng-model="address1work" class="form-control input-lg1" placeholder="Address Line 1" capitalize-first>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Address-2</label>
							<input type="text" id="address2work" name="address2work" ng-model="address2work" class="form-control input-lg1" placeholder="Address Line 2" capitalize-first>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Address-3</label>
							<input type="text" id="address3work" name="address3work" ng-model="address3work" class="form-control input-lg1" placeholder="Address Line 3" capitalize-first>
						</div>
					</div>
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Country</label>
							<select id="countrynamework" name="countrynamework" ng-model="countrynamework" ng-options="item.countryId as item.countryName for item in getcountry" ng-init="countrynamework = 1" ng-change="onCountryChange()" class="form-control input-lg2">
								<option value="">---Select Country---</option>
							</select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>State</label>
							<select id="statenamework" name="statenamework" ng-model="statenamework" ng-options="item.stateId as item.stateName for item in getrelatedstate" ng-init="statenamework = 1" class="form-control input-lg2">
								<option value="">---Select State---</option>
							</select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>City Name</label>
							<input type="text" id="citynamework" name="citynamework" ng-model="citynamework" class="form-control input-lg1" ng-init="citynamework = 'Vadodara'" placeholder="City" capitalize-first>
						</div>
					</div>
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Pincode</label>
							<input type="text" id="pincodework" name="pincodework" ng-model="pincodework" class="form-control input-lg1" placeholder="Pincode" disallow-spaces>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label>Mobile Number</label>
							<input type="text" id="mobilenumberwork" name="mobilenumberwork" ng-model="mobilenumberwork" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
						</div>
					</div>
					
				</div>
	<!------------------------------------ Row Start -->			
				<div class="form-group row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Phone Number</label>
							<input type="text" id="phonenumberwork" name="phonenumberwork" ng-model="phonenumberwork" class="form-control input-lg1" placeholder="phone">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<a id="Add" value="Add" name="Add" ng-click="addFamilyWorkLandlineRow()" class="btn btn-primary btn-lg"  style="margin-top:25px;height: 43px"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;<span style="color:white"> Add </span ></a>
						</div>
					</div>
				</div>
					<!------------------------------------ Row Start -->			
				<div class="form-group row" ng-show="showtable ==1" style="margin-left:12px !important;">
		<br><br>
				<table  class="table table-bordered" id="dataTable" style="width:30%;" cellspacing="0">
					<thead>
					<tr>
					<th>Phone Number </th>
					<th> Delete </th>
					</tr>
					</thead>
				
			
					<tr  ng-repeat="item in familyworklandline">
					<td> {{item.landlinePhoneNumber}}</td>
					
					<td>
					<a class="btn btn-danger" style="background-color: white;" ng-click="removeFamilyWorkLandlineRow(item.landlinePhoneNumber)" ng-show="item.landlinePhoneNumber != null"/><i class="fa fa-trash-o txt-danger"></i></a>
					</td>
					</tr>
				
				</table>
			
				</div>
			
	
	
	<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
					<h3 class="box-title" style="color:#4A2818;"> ADDRESS FOR COMMUNICATION <font color="red" size="3">*</font></h3>
	 </div>	

	
<!------------------------------------ Row Start -->	
			<div class="form-group row">
				
				
				<div class="col-md-2">
					<label class="radio-inline">
							<input type="radio" id="communication" name="communication" ng-model="communication" ng-value="'work'" ng-click="checkcommunication()"> &nbsp;Work
					</label>
				</div>
				<div class="col-md-2">
					<label class="radio-inline">
						<input type="radio" id="communication" ng-model="communication" ng-value="'residential'" ng-click="checkcommunication()"> &nbsp;Residential
					</label>
				</div>
				
				
				
			</div>







<hr>

<div class="form-group row">

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<a href="#" class="btn btn-md btn-success btn-block" ng-click="addspousedetail()">
	<!-- <input type="submit" ng-click="addspousedetail()" class="btn btn-success" value="Save"> -->
Submit
</a>
</div>

</div>



</form>   


</div>
</div>
</div>
    
</div>    
</div>
</div>




<div class="container-fluid"> 
<!-- Content Row -->
<div class="row">

<!-- Earnings (Monthly) Card Example -->
<div class="col-lg-12 col-md-12  col-sm-12 col-xl-12">  
<!-- Collapsable Card Example -->
<div class="card shadow mb-4">


<!-- Card Header -->
<!-- <div class="card-header py-3">
		
		<div class="row">
		<div class="col-md-3">
				<h5 class="m-0 font-weight-bold text-primary">
				All Family Information
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

<div class="row">

<div class="col-sm-12 col-md-12">
<div class="table-responsive">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

<thead>
<tr>
<th> Membership No </th>
<th> Name  </th>
<th> Relation  </th>
<th>Email</th>
<th>Mobile No.</th>
<th class="text-center">
<i class="fas fa-barcode"></i>
</th>
<th class="text-center">
	<i class="fas fa-qrcode"></i>
</th>
<th class="text-center"> Actions</td>

</tr>
</thead>
<tbody>
	<tr ng-repeat="item in getspousedata" style="cursor:pointer;cursor:hand">
		<td data-toggle="modal" data-target="#edit" ng-click="getspouse(item.membersFamilyId, '<%=session.getAttribute("membershipnumber") %>', '<%= request.getParameter("memberid") %>')">{{item.membershipNumber}}</td>
		<td data-toggle="modal" data-target="#edit" ng-click="getspouse(item.membersFamilyId, '<%=session.getAttribute("membershipnumber") %>', '<%= request.getParameter("memberid") %>')">{{item.memberFamilyFirstName}} {{item.memberFamilyMiddleName}} {{item.memberFamilyLastName}}</td>
		<td data-toggle="modal" data-target="#edit" ng-click="getspouse(item.membersFamilyId, '<%= session.getAttribute("membershipnumber") %>', '<%= request.getParameter("memberid") %>')">{{item.memberFamilyTypeOfRelation}}</td>
		<td data-toggle="modal" data-target="#edit" ng-click="getspouse(item.membersFamilyId, '<%= session.getAttribute("membershipnumber") %>', '<%= request.getParameter("memberid") %>')">{{item.memberFamilyEmail}}</td>
		<td data-toggle="modal" data-target="#edit" ng-click="getspouse(item.membersFamilyId, '<%= session.getAttribute("membershipnumber") %>', '<%= request.getParameter("memberid") %>')">{{item.memberFamilyMobileNumber}}</td>
		<td class="text-center">
			<button type="button" class="btn btn-default btn-lg" data-toggle="modal"data-target="#barcodeModal" ng-click="getfamilybarcode(item.membersFamilyId)">
				<span class="glyphicon glyphicon-barcode"> <i class="fas fa-barcode"></i></span>
			</button>
		</td>
		<td class="text-center">
			<button type="button" class="btn btn-default btn-lg" data-toggle="modal"data-target="#qrcodeModal" ng-click="getfamilyqrcode(item.membersFamilyId)">
				<span class="glyphicon glyphicon-qrcode"> <i class="fas fa-qrcode"></i></span>
			</button>
		</td>
		
		<td>
		
			<ul class="pagination text-center" style="display: flex;  margin: 5px 24px 5px;">
			
				<li data-placement="top" data-toggle="tooltip" title="Delete" style="background-color: white;"><a href="#"  data-title="Delete" style="    font-size: 25px;"ng-click="deletefamilymember(item.membersFamilyId)"><i class="fa fa-trash-o txt-danger"></i></a></li>
			</ul>
	
		</td>
	</tr>
</tbody>


</table>
</div>
<!-- 
<div class="row">
			<div class="col-md-2">																	
				<a href="" ng-click="redirectcontactdetail(memberid)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand;">Previous</a>																													
			</div>
			<div class="col-md-9">
				<div style="padding: 1px 10px 0px 0px; ">
					
			<a ng-click="redirectreference(membercategoryid,2)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand; margin-left: 10px;">Next</a>
						
					
				</div>
			</div>
		</div> -->
<br>
<div class="row">	<br>	<!------------------------------------ Row Start -->							
				<div class="col-md-6 text-left">
					<div class="col-md-3 text-right">
					<button type="submit" ng-click="redirectcontactdetail(memberid)" class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Previous</button>			
				</div>
					</div>										
				<div class="col-md-4 text-right">
							
				</div>
				<div class="col-md-2 text-right">
					<button type="submit" ng-click="redirectreference(membercategoryid,2)"  class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Next</button>			
				</div>
				
				</div>		
		
		
</div>
</div>
<br>


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



<div class="modal fade" id="barcodeModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
							<h3 class="modal-title" id="lineModalLabel"> BARCODE </h3>
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
				
					</div>
					<div class="modal-body">
						<img src="{{memberfamilybarcode}}" class="img-responsive">
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
						<h3 class="modal-title" id="lineModalLabel"> QR CODE </h3> 
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
					
					</div>
					<div class="modal-body">
						<img src="{{memberfamilyqrcode}}" class="img-responsive">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>



		<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">					
					<div class="modal-header">
					<h4 class="modal-title custom_align" id="Heading">Edit Spouse Detail </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span aria-hidden="true">×</span></button>
						
					</div>
					<div class="modal-body">
						<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label>Membership Number</label>
									<input type="text" id="spouseidedit" name="spouseidedit" ng-model="spouseidedit" readonly class="form-control input-lg1" placeholder="Membership No">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Relation<font color="red" size="3">*</font></label>
									<select id="relationedit" name="relationedit" ng-model="relationedit" class="form-control input-lg2">
										<option value="">Select Relation</option>
										<option value="Spouse">Spouse</option>
										<option value="Child">Child</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<div class="form-group">
									<label>Title</label>
									<select id="memberfamilytitleedit" name="memberfamilytitleedit" ng-model="memberfamilytitleedit" class="form-control input-lg2">
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
									<input type="text" id="firstnameedit" name="firstnameedit" ng-model="firstnameedit" class="form-control input-lg1" placeholder="First Name" capitalize-first>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Middle Name</label>
									<input type="text" id="middlenameedit" name="middlenameedit" ng-model="middlenameedit" class="form-control input-lg1" placeholder="Middle Name" capitalize-first>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Last Name<font color="red" size="3">*</font></label>
									<input type="text" id="lastnameedit" name="lastnameedit" ng-model="lastnameedit" class="form-control input-lg1" placeholder="Last Name" capitalize-first>
								</div>
							</div>
						</div>
						<div class="row">
							
							<div class="col-md-4">
								<div class="form-group">
									<label>Gender<font color="red" size="3">*</font></label>
									<select id="genderedit" name="genderedit" ng-model="genderedit" class="form-control input-lg2">
										<option value="">Select Gender</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
									</select>
								</div>
							</div>
							 <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label>Date Of Birth</label>
						
							<input class="KendoDate" id="spouldob" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						
					</div>
							<div class="col-md-4">
									<div class="form-group">
										<label>Blood Group</label>
										<select id="bloodgroupedit" name="bloodgroupedit" ng-model="bloodgroupedit" class="form-control input-lg2">
											<option value="">--- Select Blood Group ---</option>
											<option value="A+">A+</option>
											<option value="A-">A-</option>
											<option value="B+">B+</option>
											<option value="B-">B-</option>
											<option value="O+">O+</option>
											<option value="O-">O-</option>
											<option value="AB+">AB+</option>
											<option value="AB-">AB-</option>
										</select>
									</div>
								</div>		
						</div>
						
						<!-- <div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label>Date Of Birth</label>
										<div class="row">
											<div class="col-md-3">
												<select id="birthdateedit" name="birthdateedit" ng-model="birthdateedit" class="form-control input-lg2">
													<option value="">Date</option>
													<option value="01">1</option>
													<option value="02">2</option>
													<option value="03">3</option>
													<option value="04">4</option>
													<option value="05">5</option>
													<option value="06">6</option>
													<option value="07">7</option>
													<option value="08">8</option>
													<option value="09">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
													<option value="19">19</option>
													<option value="20">20</option>
													<option value="21">21</option>
													<option value="22">22</option>
													<option value="23">23</option>
													<option value="24">24</option>
													<option value="25">25</option>
													<option value="26">26</option>
													<option value="27">27</option>
													<option value="28">28</option>
													<option value="29">29</option>
													<option value="30">30</option>
													<option value="31">31</option>
												</select>
											</div>
											<div class="col-md-6">
												<select id="birthmonthedit" name="birthmonthedit" ng-model="birthmonthedit" class="form-control input-lg2">
													<option value="">Month</option>
													<option value="01">January</option>
													<option value="02">February</option>
													<option value="03">March</option>
													<option value="04">April</option>
													<option value="05">May</option>
													<option value="06">June</option>
													<option value="07">July</option>
													<option value="08">August</option>
													<option value="09">September</option>
													<option value="10">October</option>
													<option value="11">November</option>
													<option value="12">December</option>
												</select>
											</div>
											<div class="col-md-3">
												<input type="text" id="birthyearedit" name="birthyearedit" ng-model="birthyearedit" class="form-control input-lg2" placeholder="Year">
											</div>
										</div>
									</div>
								</div>
															
							</div>	
							 -->								
							<div class="row">
								<div class="col-md-4">
									<label>Aadhar Number</label>
									<div class="form-group">
										<input type="text" id="aadharnumberedit" name="aadharnumberedit" ng-model="aadharnumberedit" class="form-control input-lg1" placeholder="Aadhar Number" disallow-spaces capitalize>
									</div>
								</div>
								<div class="col-md-4">
									<label>Passport Number</label>
									<div class="form-group">
										<input type="text" id="passportnumberedit" name="passportnumberedit" ng-model="passportnumberedit" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
									</div>
								</div>
								<div class="col-md-4">
									<label>PAN Number</label>
									<div class="form-group">
										<input type="text" id="pannumberedit" name="pannumberedit" ng-model="pannumberedit" class="form-control input-lg1" placeholder="PAN Number" disallow-spaces capitalize>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-10" align="center">
									<label style="float: left;">Profile Picture</label>
									<input type='file' id="imgInp1" class="form-control"/>																
									<img src="#" id="target1"/>
									<form name="myForm1">
										<input type="hidden" name="x1" id="valuex1"/>
										<input type="hidden" name="y1" id="valuey1"/>
										<input type="hidden" name="w1" id="valuew1"/>
										<input type="hidden" name="h1" id="valueh1"/>																	
									</form>
								</div>
								<div class="col-md-2">
									<img alt="" src="{{profileedit}}" class="img-responsive" width="130px;" height="130px;">
								</div>
							</div>
							
	<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
					<h3 class="box-title" style="color:#4A2818;">Education details  </h3>
		     </div>
	
	<!------------------------------------ Row Start -->

									
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<!-- <div style="padding: 5px 0px 0px 0px;" class="panel-body">
									<div class="panel-heading1"> education details </div>
								</div> -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Degree Name</label>
												<input id="degreenameedit" name="degreenameedit" ng-model="degreenameedit" type="text" class="form-control input-lg1" placeholder="Degree">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Passing Year</label>
												<input id="passingyearedit" name="passingyearedit" ng-model="passingyearedit" type="text" class="form-control input-lg1" placeholder="Passing Year">
											</div>
										</div>
										
										
									</div>
									<div class="row">
									<div class="col-md-3">
											<div class="form-group">
												<label>Grade or %</label>
												<input id="gradeedit" name="gradeedit" ng-model="gradeedit" type="text" class="form-control input-lg1" placeholder="Grade or %">
											</div>
										</div>
										<div class="col-md-7">
											<div class="form-group">
												<label>Institute / University</label>
												<input id="institutenameedit" name="institutenameedit" ng-model="institutenameedit" type="text" class="form-control input-lg1" placeholder="Institute or College">
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<a id="Add" value="Add" name="Add" ng-click="addEducationRow1()" class="btn btn-primary btn-lg"   style="margin-top:24px;height: 41px"><span  style="color:white" >&nbsp;Add </span></a>
											</div>
										</div>
									</div>
									<div class="row" ng-repeat="item in getfamilyeducationdetail">
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
												<a class="btn btn-danger btn-sm" style="background-color: white;" ng-click="removeEducationRow1(item.degreeName)" ng-if="item.degreeName != null"/><i class="fa fa-trash-o txt-danger"></i></a>
											</div>
										</div>
									</div>
								</div>
							</div>
		<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
					<h3 class="box-title" style="color:#4A2818;"> Login Credentials </h3>
		     </div>
								
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<!-- <div style="padding: 5px 0px 0px 0px;" class="panel-body">
									<div class="panel-heading1"> login credentials<font color="red" size="3">*</font> </div>
								</div> -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
												<input type="text" id="emailedit" name="emailedit" ng-model="emailedit" class="form-control input-lg1" placeholder="Email Id*" ng-change="checkemailaddress1()">
											</div>
										</div>
										<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
												<input type="password" id="passwordedit" name="passwordedit" ng-model="passwordedit" class="form-control input-lg1" placeholder="Password*">                                        
											</div>
										</div>
									</div>											
								</div>
								<div class="w3-note w3-example">
									<p> <span style="color: red; font-weight: 600;"> Note: </span> your user name is your Email Id</p>
								</div>
							</div>
							
	<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
				<div class="col-lg-8">	
					<h3 class="box-title" style="color:#4A2818;"> Residential Address </h3>
								&emsp;	&emsp;	&emsp;	&emsp;	&emsp;																								
							&emsp;<input id="residentialaddressedit" name="residentialaddressedit" ng-model="residentialaddress" type="checkbox" ng-click="residentialaddresssameasmember(<%= session.getAttribute("memberid")%>)"/>
												<label for="residentialaddress">Same As Member</label>																																																
						</div>
		     </div>
	
	<!------------------------------------ Row Start -->								
							<div style="padding-bottom: 15px;" class="panel panel-default">
							<%-- 	<div style="padding: 5px 0px 0px 0px;" class="panel-body">
									<div class="panel-heading1">
										<div class="row">
											<div class="col-md-8">
												residential address
											</div>
											<div class="col-md-4">
												<input id="residentialaddressedit" name="residentialaddressedit" ng-model="residentialaddress" type="checkbox" ng-click="residentialaddresssameasmember(<%= session.getAttribute("memberid")%>)"/>
												<label for="residentialaddress">Same As Member</label>											
											</div>
										</div>
									</div>
								</div> --%>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-1<font color="red" size="3">*</font></label>
												<input type="text" id="address1edit" name="address1edit" ng-model="address1edit" class="form-control input-lg1" placeholder="Address Line-1" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-2</label>
												<input type="text" id="address2edit" name="address2edit" ng-model="address2edit" class="form-control input-lg1" placeholder="Address Line-2" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-3</label>
												<input type="text" id="address3edit" name="address3edit" ng-model="address3edit" class="form-control input-lg1" placeholder="Address Line-3" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Country<font color="red" size="3">*</font></label>
												<select id="countrynameedit" name="countrynameedit" ng-model="countrynameedit" ng-options="item.countryId as item.countryName for item in getcountry" ng-change="onCountryChange()" class="form-control input-lg2">
													<option value="">---Select Country---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>State<font color="red" size="3">*</font></label>
												<select id="statenameedit" name="statenameedit" ng-model="statenameedit" ng-options="item.stateId as item.stateName for item in getrelatedstate" class="form-control input-lg2">
													<option value="">---Select State---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>City Name<font color="red" size="3">*</font></label>
												<input type="text" id="citynameedit" name="citynameedit" ng-model="citynameedit" class="form-control input-lg1" placeholder="City" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Pincode</label>
												<input type="text" id="pincodeedit" name="pincodeedit" ng-model="pincodeedit" class="form-control input-lg1" placeholder="Pin Code" disallow-spaces>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Mobile Number</label>
												<input type="text" id="mobilenumberedit" name="mobilenumberedit" ng-model="mobilenumberedit" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Phone Number</label>
												<input type="text" id="phonenumberedit" name="phonenumberedit" ng-model="phonenumberedit" class="form-control input-lg1" placeholder="Phone">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<a id="Add" value="Add" name="Add" ng-click="addFamilyResidentialLandlineRow1()" class="btn btn-primary btn-lg"  style="margin-top:24px;height: 41px"><span  style="color:white">&nbsp;Add</span></a>
											</div>
										</div>
									</div>
									<div class="row" ng-repeat="item in getfamilyresidentiallandline">
										<div class="col-md-2">
											<div class="form-group">
												<label>{{item.landlinePhoneNumber}}</label>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<a class="btn btn-danger btn-sm"  style="background-color: white;" ng-click="removeFamilyResidentialLandlineRow1(item.landlinePhoneNumber)"/><i class="fa fa-trash-o txt-danger"></i></a>
											</div>
										</div>
									</div>
								</div>
							</div>
							
								<!------------------------------------ Row Start -->	
			<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
				<div class="col-lg-8">	
					<h3 class="box-title" style="color:#4A2818;"> 	Work Detail </h3>
								&emsp;	&emsp;	&emsp;	&emsp;	&emsp;																								
							&emsp;	<input id="workdetailedit" name="workdetailedit" ng-model="workdetail" type="checkbox" ng-click="workdetailsameasmember(<%= session.getAttribute("memberid")%>)"/>
												<label for="workdetailedit">Same As Member</label>																																																
						</div>
		     </div>
	
	<!------------------------------------ Row Start -->	
							<div style="padding-bottom: 15px;" class="panel panel-default">
								<%-- <div style="padding: 5px 0px 0px 0px;" class="panel-body">
									<div class="panel-heading1">
										<div class="row">
											<div class="col-md-8">
												work detail
											</div>
											<div class="col-md-4">												
												<input id="workdetailedit" name="workdetailedit" ng-model="workdetail" type="checkbox" ng-click="workdetailsameasmember(<%= session.getAttribute("memberid")%>)"/>
												<label for="workdetailedit">Same As Member</label>												
											</div>
										</div>
									</div>
								</div> --%>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Occupation</label>
												<input type="text" id="occupationedit" name="occupationedit" ng-model="occupationedit" class="form-control input-lg1" placeholder="Occupation" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Designation</label>
												<input type="text" id="designationedit" name="designationedit" ng-model="designationedit" class="form-control input-lg1" placeholder="Designation" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Company Name</label>
												<input type="text" id="companynameedit" name="companynameedit" ng-model="companynameedit" class="form-control input-lg1" placeholder="Company Name" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Business Nature</label>
												<input type="text" id="businessnatureedit" name="businessnatureedit" ng-model="businessnatureedit" class="form-control input-lg1" placeholder="Business Nature" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Fax Number</label>
												<input type="text" id="faxnumberedit" name="faxnumberedit" ng-model="faxnumberedit" class="form-control input-lg1" placeholder="Fax-Office">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Website</label>
												<input type="text" id="websiteedit" name="websiteedit" ng-model="websiteedit" class="form-control input-lg1" placeholder="Website">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Email</label>
												<input type="text" id="emailworkedit" name="emailworkedit" ng-model="emailworkedit" class="form-control input-lg1" placeholder="Email">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-1</label>
												<input type="text" id="address1workedit" name="address1workedit" ng-model="address1workedit" class="form-control input-lg1" placeholder="Address Line 1" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-2</label>
												<input type="text" id="address2workedit" name="address2workedit" ng-model="address2workedit" class="form-control input-lg1" placeholder="Address Line 2" capitalize-first>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Address-3</label>
												<input type="text" id="address3workedit" name="address3workedit" ng-model="address3workedit" class="form-control input-lg1" placeholder="Address Line 3" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Country</label>
												<select id="countrynameworkedit" name="countrynameworkedit" ng-model="countrynameworkedit" ng-options="item.countryId as item.countryName for item in getcountry" ng-change="onCountryChange()" class="form-control input-lg2">
													<option value="">---Select Country---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>State</label>
												<select id="statenameworkedit" name="statenameworkedit" ng-model="statenameworkedit" ng-options="item.stateId as item.stateName for item in getrelatedstate" class="form-control input-lg2">
													<option value="">---Select State---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>City Name</label>
												<input type="text" id="citynameworkedit" name="citynameworkedit" ng-model="citynameworkedit" class="form-control input-lg1" placeholder="City" capitalize-first>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Pincode</label>
												<input type="text" id="pincodeworkedit" name="pincodeworkedit" ng-model="pincodeworkedit" class="form-control input-lg1" placeholder="Pincode" disallow-spaces>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Mobile Number</label>
												<input type="text" id="mobilenumberworkedit" name="mobilenumberworkedit" ng-model="mobilenumberworkedit" class="form-control input-lg1" placeholder="Mobile" disallow-spaces>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Phone Number</label>
												<input type="text" id="phonenumberworkedit" name="phonenumberworkedit" ng-model="phonenumberworkedit" class="form-control input-lg1" placeholder="phone">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<a id="Add" value="Add" name="Add"   ng-click="addFamilyWorkLandlineRow1()" class="btn btn-primary btn-lg" style="margin-top:24px;color:white;height: 41px">&nbsp;Add</a>
											</div>
										</div>
									</div>
									<div class="row" ng-repeat="item in getfamilyworklandline">
										<div class="col-md-2">
											<div class="form-group">
												<label>{{item.landlinePhoneNumber}}</label>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<a class="btn btn-danger btn-sm" style="background-color: white;" ng-click="removeFamilyWorkLandlineRow1(item.landlinePhoneNumber)"/><i class="fa fa-trash-o txt-danger"></i></a>
											</div>
										</div>
									</div>
								</div>
							</div>
<!------------------------------------ Row Start -->	
				<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
							<h3 class="box-title" style="color:#4A2818;"> ADDRESS FOR COMMUNICATION <font color="red" size="3">*</font></h3>
			 </div>	


							
							
							<div class="form-group row">
				
				
				<div class="col-md-2">
					<label class="radio-inline">
							<input type="radio" id="communicationedit" name="communicationedit" ng-model="communicationedit" ng-value="'work'" ng-click="checkcommunicationedit()">&nbsp;Work
					</label>
				</div>
				<div class="col-md-2">
					<label class="radio-inline">
					<input type="radio" id="communicationedit" name="communicationedit" ng-model="communicationedit" ng-value="'residential'" ng-click="checkcommunicationedit()"> &nbsp;Residential
						
					</label>
				</div>
				
				
				
			</div>
							
						</div>
						<div class="modal-footer">
							<div class="btn-group btn-group-justified" role="group" aria-label="group button">
								<div class="btn-group" role="group">
									<input type="submit" ng-click="editspousedetail(membersfamilyid,2)" class="btn btn-success" value="Edit">
								</div>
								<div class="btn-group" role="group">
									<button type="button" class="btn btn-primary" data-dismiss="modal"  role="button">Close</button>
								</div>
							</div>
						</div>						
					</div>
				</div>
			</div>
			
			
<!-- Bootstrap core JavaScript-->

<script src="<%=request.getContextPath() %>/resources/front2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<%-- <script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery-easing/jquery.easing.min.js"></script>
 --%>
<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath() %>/resources/front2/js/sb-admin-2.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/front2/js/jquery.smartWizard.min.js"></script>


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
				                    setSelect:   [ 100, 100, 350, 350 ],							                    
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
		<script>
			jQuery(function($) {
				 function readURL(input) {
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				            	if ($('#target1').data('Jcrop')) {
								    $('#target1').data('Jcrop').destroy();
								    $('#target1').removeAttr('style');
								}
				                $('#target1').attr('src', e.target.result);
				                
				                $('#target1').Jcrop({
				                	aspectRatio: 1 / 1,
				                	boxWidth: 650,   
				                    boxHeight: 400,
				                    setSelect:   [ 100, 100, 350, 350 ],							                    
				    				onSelect : setCoordinates1
				    			});
				            }		            
				            reader.readAsDataURL(input.files[0]);
				        }
				    }				    
				    $("#imgInp1").change(function(){
				        readURL(this);
				    });			
			});
			function setCoordinates1(c) {
				//alert("x1 " + c.x + " y1 " + c.y);
				//alert("w1 " + c.w + " h1 " + c.h);
				document.myForm1.x1.value = Math.round(c.x);
				document.myForm1.y1.value = Math.round(c.y);
				document.myForm1.w1.value = Math.round(c.w);
				document.myForm1.h1.value = Math.round(c.h);
			};
			function checkCoordinates1() {
				if (document.myForm1.x1.value == "" || document.myForm1.y1.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>




<script>
CKEDITOR.replace('editor1', {
height: 150
});
</script>


<script>
$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#fromdatetimepicker").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});
</script>


<script>
$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#todatetimepicker").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});
</script>
    
    
    
    <script>
// Add the following code if you want the name of the file appear on select
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>

<script type="text/javascript">
$(document).ready(function(){
// Smart Wizard
$('#smartwizard').smartWizard({ 
selected: 2, 
theme: 'default',
transitionEffect:'fade',
toolbarSettings: {toolbarPosition: '',
toolbarExtraButtons: [
// {label: 'Finish', css: 'btn-info', onClick: function(){ alert('Finish Clicked'); }},
// {label: 'Cancel', css: 'btn-danger', onClick: function(){ $('#smartwizard').smartWizard("reset"); }}
]
}
});


// External Button Events
$("#reset-btn").on("click", function() {
// Reset wizard
$('#smartwizard').smartWizard("reset");
return true;
});

$("#prev-btn").on("click", function() {
// Navigate previous
$('#smartwizard').smartWizard("prev");
return true;
});


$("#next-btn").on("click", function() {
// Navigate next
$('#smartwizard').smartWizard("next");
return true;
});


$("#theme_selector").on("change", function() {
// Change theme
$('#smartwizard').smartWizard("theme", $(this).val());
return true;
});

});   
</script> 

<script>
$(document).ready(function() {
var x = new SlimSelect({
select: '#demo'
});
});
</script>
</body>

</html>
