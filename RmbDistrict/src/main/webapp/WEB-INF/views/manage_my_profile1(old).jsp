<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title> User Profile </title>

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


<!--*******-->
<script src="https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>

<!--*******-->
 <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.mobile.min.css" />
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/kendo.all.min.js"></script>

	<!-- <link rel="icon" href="https://www.tigeradvertising.in/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
		
			<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
			
	<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/manage_my_profile.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>

<!--***datetimepicker****-->
<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/datetimepicker/css/kendo.material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/datetimepicker/css/kendo.material.mobile.min.css" />
 
 
 <script src="<%=request.getContextPath() %>/resources/admin/datetimepicker/js/jquery.min.js"></script> 

<script src="<%=request.getContextPath() %>/resources/admin/datetimepicker/js/kendo.all.min.js"></script>
		
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

<body id="page-top" ng-app="rcbs" ng-controller="manageProfileCtrl" ng-cloak ng-init="getmemberdetail(<%= session.getAttribute("loginid")%>) || getFellowship_id(<%= session.getAttribute("fellowshipId")%>)">
<%@include file="header1.jsp" %>


<form name="myForm">
			<input type="hidden" name="x" id="valuex"/>
			<input type="hidden" name="y" id="valuey"/>
			<input type="hidden" name="w" id="valuew"/>
			<input type="hidden" name="h" id="valueh"/>
		</form>


<div class="container-fluid"> 
<div class="row profile">




<!-- Earnings (Monthly) Card Example -->
<div class="col-lg-12 col-md-12 col-sm-12 col-xl-12">  
<!-- Collapsable Card Example -->
<div class="card  mb-4">


    
<!-- Card Header -->
<div class="card-header py-3">
		
		<div class="row">
		<div class="col-md-12">
				<!-- <h5 class="m-0 font-weight-bold text-primary">
				Edit Profile
				</h5>
				</div> -->
				<div id="smartwizard">

					<ul class="card-header pa-0">
					
						<li>
							<a href="#step-1"> <i class="fa fa-user-circle-o"></i> <br />
								<small style="padding-left: 0px; line-height: 25px;"> Members Info </small>
							</a>
						</li>
						
						<li><a href="#step-2"> <i class="fa fa-map-marker"></i> <br /><small> Residential Address </small></a></li>
						
						<li><a href="#step-3"> <i class="fa fa-users"></i> <br /><small> Family Info </small></a></li>
						
						<li><a href="#step-4"> <i class="fa fa-exchange"></i><br /><small> References </small></a></li>
						
						<!--
						<li><a href="#step-4"> Step 5 <br /><small> Payments </small></a></li>
						-->
					
					</ul>
				
				<div>
				
					
					
		
		</div>
	
</div>


<!-- Card Content -->
<div class="card-body">

<form class="">
<div class="form-group row">


		<div class="col-lg-3 col-md-3">
			<div class="profile-sidebar">
				<div class="profile-img">
				<img class="img-responsive" src="<%=request.getContextPath() %>/resources/front2/img/profile-pic.jpg" alt="" />
				<div class="file btn btn-lg btn-primary">
				Change Photo
				<input type="file" name="file" id="imgInp" />
				</div>
				</div>
			</div>
		
		</div>
		
	
	<div class="col-lg-9 col-md-9 ">
		<div class="form-group row">
			<div class="col-lg-3"></div>
				<div class="col-lg-3"> </div>
					
					<div class="col-lg-3">
						<!-- <label style="visibility: hidden">     Barcode</label> -->	
							<button type="button" class="btn btn-default btn-lg" style="height: 45px;" data-toggle="modal"data-target="#barcodeModal">
								<i class="fa fa-barcode" aria-hidden="true"></i>  Barcode
							</button>		
					</div>
					
					<div class="col-md-3">
						<!-- <label style="visibility: hidden">     QR code</label>	 -->
								<button type="button" class="btn btn-default btn-lg" style="height: 45px;" data-toggle="modal"data-target="#qrcodeModal">
									<i class="fa fa-qrcode" aria-hidden="true"></i>  QR code 
								</button>				
						</div>	
					</div>
					
					<div class="form-group row">	
						<div class="col-md-6">
					<div class="form-group ">
					<label> Fellowship Name  </label>
						<select class="form-control dropdownparrow" ng-model="fellowship_id" id="fellowship_id" name="fellowship_id" style=" background-color: #5b5fe424; color: black;" disabled >
							<option value="" selected> Fellowship Name</option>
							<option style=" background-color: #5b5fe424; color: black;" ng-repeat="item in getAllFellowshipNameList"  value="{{item.fellowship_id}}">{{item.fellowship_name}}</option> 
						</select> 
						</div>
					</div>
					<div class="col-md-6">
					<div class="form-group ">
					<label> Club Name </label>
						<select class="form-control dropdownparrow"  ng-model="memberclubname" style=" background-color: #5b5fe424; color: black;" disabled id="memberclubname" name="memberclubname" ng-options="item.clubId as item.clubName for item in allclubs"  >
							<option value="">Club Name</option>
						</select> 
						</div>
					</div>
					
						
						
		</div>
		
		
					<div class="form-group row">	
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<div class="form-group ">
						<label> Type Of Member </label>
								<select ng-model="membercategoryname" style=" background-color: #5b5fe424; color: black;" disabled id="membercategoryname" name="membercategoryname" ng-options="item.memberCategoryId as item.memberCategoryName for item in getmembercategory" class="form-control input-lg2 dropdownparrow">
									<option value="">Type Of Member</option>
								</select>
							</div>
					</div>
			<div class="col-md-6">
			<div class="form-group ">
					<label>  Membership No  </label>	
						
					<input class="form-control" style=" background-color: #5b5fe424;    border-radius: inherit; color: black;" readonly ng-model="membershipId"  type="text">			
</div>
			</div>			
						
		</div>
		
		
	</div>
		
</div>


<!------------------------------------------------- Row End---------------------------------------- -->


<div class="form-group row">
		<!-- <div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
		<label> Type Of Members </label>
			<div class="form-group input-group mb-0">
				<select ng-model="membercategoryname" style=" background-color: #ffffff; color: black;" disabled id="membercategoryname" name="membercategoryname" ng-options="item.memberCategoryId as item.memberCategoryName for item in getmembercategory" class="form-control input-lg2 dropdownparrow">
					<option value="">Type Of Member</option>
				</select>
			</div>
		</div>
 -->
		
	    
	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 ">
			<label>Business Category<font color="red" size="3">*</font></label>
			<div class="form-group input-group mb-0">
				<select class="form-control" ng-model="businesscategoryid" id="businesscategoryid" name="businesscategoryid" ng-options="item.businessCategoryId as item.businessCategoryName for item in allbusinesscategories">
				<option value="">Business Category</option>
				</select>
			</div>
	</div>


</div>
				
<!------------------------------------ Row Start -->					
		<div class="box-header with-border card shadow mb-4 col-lg-12" style="padding-top:15px;background-color: #f8f9fc;">
			<h3 class="box-title" style="color:#4A2818;"> Personal Information<font color="red" size="3">*</font> </h3>
    	</div>
    	
<!------------------------------------ Row Start -->  

<div class="form-group row">

<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
	<label>Title</label>
		<select class="form-control" id="membertitle" name="membertitle" ng-model="membertitle">
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

<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<label>First Name<font color="red" size="3">*</font></label>
<input class="form-control" id="firstname" name="firstname" ng-model="firstname"  type="text" placeholder="First Name">
</div>


<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<label>Middle Name</label>
<input type="text" id="middlename" name="middlename" ng-model="middlename" class="form-control input-lg1" placeholder="Middle Name">
</div>

<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<label>Last Name<font color="red" size="3">*</font></label>
<input type="text" id="lastname" name="lastname" ng-model="lastname" class="form-control input-lg1" placeholder="Last Name">
</div>


</div>
    


<div class="form-group row">
		
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 designation">
		   <label>Gender<font color="red" size="3">*</font></label>
				<div class="form-group input-group mb-0">
					<select class="form-control" id="gender" name="gender" ng-model="gender">
					<option value="">Gender</option>
					<option value="M">Male</option>
					<option value="F">Female</option>
					</select>
				
				</div>
		</div>
		
	 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
	 
			<label>Date Of Birth</label>
						<div class="form-group input-group mb-0">
			
		<input class="KendoDate" id="datepicker1" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
		</div>
		</div>
		

		 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
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
				<div class="col-lg-3">
						<div class="form-group">
							<label>Anniversary Date</label>
							<input   class="KendoDate" id="datepicker2" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
						</div>
					</div>		

</div>

		<div class="form-group row">
				
			 <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
			 <div class="form-group">
					<label>Nationality</label>	
					<select ng-model="membertypename" id="membertypename" name="membertypename" ng-options="item.memberTypeId as item.memberTypeName for item in getmembertype" class="form-control input-lg2">
							<option value="">Nationality</option>
					</select>
					</div>
				</div>	
				
				<div class="col-md-4">
					<div class="form-group">
						<label>Passport Number</label>
						<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control input-lg1" placeholder="Passport Number" disallow-spaces capitalize>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<div class="form-group">
						<label>PAN Number</label>
						<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control input-lg1" placeholder="PAN No" disallow-spaces capitalize>
					</div>
				</div>
			</div>    

	<div class="form-group row">

					<div class="col-md-3">
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
					<div class="col-md-3">
						<div class="form-group">
							<label>Institute / University</label>
							<input id="institutename" name="institutename" ng-model="institutename" type="text" class="form-control input-lg1" placeholder="Institute or College">
						</div>
					</div>
				<!-- 	<div class="col-md-2">
						<div class="form-group">
							<a id="Add" value="Add" name="Add" ng-click="addEducationRowEdit()" class="btn btn-primary btn-lg" style="margin-top:26px;height:40px;   color: #fff;"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add</a>
						</div>
					</div> -->
			<div class="col-md-2" style="margin-top:32px;">		
	<span class="input-group-btn"  >
	
	<a id="Add" value="Add" name="Add" ng-click="addEducationRowEdit()" class="btn btn-primary btn-lg"  style="margin-top:24px;height: 41px"><span  style="color:white"></span>&nbsp;Add</a>
	</span>
	</div>			</div>


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
									<a class="btn btn-danger btn-sm" ng-click="removeEducationRowEdit(item.degreeName)" ng-if="item.degreeName != null"/><span class="glyphicon glyphicon-remove"></span></a>
								</div>
							</div>
						</div>
<hr>

<!-- <div class="form-group row">

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<a href="#" class="btn btn-md btn-success btn-block">
Update Profile
</a>
</div>

</div> -->

<div class="row">		<!------------------------------------ Row Start -->							
				<div class="col-md-6 text-left">
					<strong ng-show="albumSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
					<strong ng-show="albumSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
				</div>										
				<div class="col-md-4 text-right">
					<button type="submit" ng-click="editmemberdetail(memberid, 2)"   class="btn btn-success tablinks"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Update & Next
					<i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
					</button>			
				</div>
				<div class="col-md-2 text-right">
					<button type="submit" ng-click="redirectcontactdetail(memberid, 2)"  class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Next</button>			
				</div>
				
				</div>	

</form>   
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
						<h3 class="modal-title" id="lineModalLabel"> QR CODE </h3>
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
					
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


<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath() %>/resources/front2/js/sb-admin-2.min.js"></script>

<!--inputmask-->
<script src="<%=request.getContextPath() %>/resources/front2/js/bootstrap-inputmask.js"></script>




<!-- <script>
$(document).ready(function() {
var x = new SlimSelect({
select: '#demo'
});
});
</script>

 -->











<script>

			$(document).ready(function () {	
				var d = new Date();		 		         
				$("#datepicker1").kendoDatePicker({
					format: "dd/MM/yyyy",
					dateInput: true,
					
					value: new Date(new Date().setDate(new Date().getDate() - 30))
		         });
			 });
			$(document).ready(function () {	
				var d = new Date();		 		         
				$("#datepicker").kendoDatePicker({
					format: "dd/MM/yyyy",
					dateInput: true,
					
					value: new Date(new Date().setDate(new Date().getDate() - 30))
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
