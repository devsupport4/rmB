<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
 

<title> Manage Reference </title>

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

<!--<link rel="stylesheet" href="css/profile.css">-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/css/smart_wizard.min.css">
<link rel='stylesheet' href='<%=request.getContextPath() %>/resources/front2/css/bootstrap.min.css'>

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
	<script src="<%=request.getContextPath() %>/resources/admin/js/controller/my_reference.js"></script>


</head>

<body id="page-top" ng-app="rcbs" ng-controller="myReferenceCtrl" ng-init="getreference(<%= session.getAttribute("loginid")%>)" ng-cloak>	
<%@include file="header1.jsp" %>

<!-- Page Wrapper -->
<div id="wrapper">

<div id="content-wrapper" class="d-flex flex-column">
<!-- Main Content -->
<div id="content">
<!-- End of Topbar -->

<div class="container-fluid"> 

<!-- Content Row -->
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">


    
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
<!-- 
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
<!-- 
<div class="card-header py-3">
<h6 class="m-0 font-weight-bold text-primary">
All References Information
</h6>
</div>
     -->
<!-- Card Header --><!-- 
<div class="card-header py-3">
		
		<div class="row">
		<div class="col-md-3">
				<h5 class="m-0 font-weight-bold text-primary">
				All References Information
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
	
</div>
    
 -->
 <br><br>

<!-- Card Content -->
<div class="card-body">

<div class="row">

<div class="col-sm-12 col-md-12">
<div class="table-responsive">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

<thead>
<tr>
<th> No. </th>
	<th>FirstName</th>
	<th>LastName</th>
	<th>Mobile</th>
	<th>Email</th>
	<th>Comapny</th>
	<th>Occupation</th>
	<th>Actions</th>
</tr>
</thead>
<tbody>
	<tr ng-repeat = "item in getreferences" style="cursor: pointer; cursor: hand;">
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{$index + 1}}</td>
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{item.referenceFirstName}}</td>
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{item.referenceLastName}}</td>
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{item.referenceMobileNumber}}</td>
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{item.referenceEmail}}</td>
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{item.referenceCompanyName}}</td>
		<td data-toggle="modal" data-target="#editRef1" ng-click="getreferencedetail(item.referenceId)">{{item.referenceOccupation}}</td>
		<td>
			<ul class="pagination" style="display: flex; margin: 0px 0;">																	
				<li data-placement="top" data-toggle="tooltip" title="Delete"><a ng-click="deletereference(item.referenceId)" href="#" style="background-color: #c9302c; color: #fff;" data-title="Delete"><i class="fa fa-trash-o txt-danger"></i></a></li>
			</ul>
		</td>
	</tr>
</tbody>

</table>
</div>
<!-- <br><br>
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
					<button type="submit"  ng-click="redirectfamilydetail(memberid, 2)" class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Previous</button>			
				</div>
					</div>										
				<div class="col-md-4 text-right">
							
				</div>
				<div class="col-md-2 text-right">
					<button type="submit"ng-click="redirectpayments(2)" class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Next</button>			
				</div>
				
				</div>		
				
</div>
</div>

<br><br>
</div>

</div>

</div>

</div>





</div>
</div>

<!-- End of Main Content -->

<!--/#footer START-->
<footer class="sticky-footer bg-white" style="position: absolute;bottom: 0px">

<div class="container my-auto ">

<div class="copyright text-center my-auto">

<span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Content Copyright &copy; 2020 Bhargav Bhatt. All rights reserved.  </span> &nbsp; |  &nbsp;
<span> Powered by: <a href="https://www.astartechnologies.net" style="color: #858796;" target="_blank">Astar Technologies</a></span>

</div>

</div>

</footer>
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

<script src="<%=request.getContextPath() %>/resources/front2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath() %>/resources/front2/js/sb-admin-2.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/front2/js/jquery.smartWizard.min.js"></script>






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
selected: 3, 
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
