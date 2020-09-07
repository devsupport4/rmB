<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
 

<title> Manage Payments </title>

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


<!--<link rel="stylesheet" href="css/profile.css">-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/css/smart_wizard.min.css">
<link rel='stylesheet' href='<%=request.getContextPath() %>/resources/front2/css/bootstrap.min.css'>

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
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/my_payment.js"></script>


</head>

<body id="page-top" ng-app="rcbs" ng-controller="myPaymentCtrl" ng-init="getpayment(<%= session.getAttribute("loginid")%>)" ng-cloak>
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

</div><!-- 
<div class="card-header py-3">
		
		<div class="row">
		<div class="col-md-3">
			 <h5 class="m-0 font-weight-bold text-primary">
			All Payments Information
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
<br>
<!-- Card Content -->
<div class="card-body">

<div class="row">

<div class="col-sm-12 col-md-12">
<div class="table-responsive">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

<thead>
<tr>
		<th>No.</th>
		<th class="text-right">Trx. Amt.</th>
		<th class="text-right">Trx. Chg.</th>
		<th class="text-right">Total Amount</th>
		<th>Payment Type</th>
		<th>Cheque Date</th>
		<th>Trx. Date</th>
		<th>Comments</th>
		<th>Actions</th>
</tr>
</thead>
	<tbody>
			<tr ng-repeat = "item in getpayments" style="cursor: pointer; cursor: hand;">
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{$index + 1}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)" class="text-right">&#8377; {{item.transactionAmount | currency : "" : 2}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)" class="text-right">&#8377; {{item.transactionCharges | currency : "" : 2}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)" class="text-right">&#8377; {{item.amountCharges | currency : "" : 2}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.paymentType}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.chequeDate | date : 'dd/MM/yyyy'}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.transactionDate | date : 'dd/MM/yyyy'}}</td>
				<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.comments}}</td>
				<td>
					<ul class="pagination" style="display: flex; margin: 0px 0;">
						<li data-placement="top" data-toggle="tooltip" title="Delete"><a ng-click="deletepayment(item.paymentId)" href="#" style="background-color: #c9302c;  font-size: 25px;;color: #fff;" data-title="Delete"><i class="fa fa-trash-o txt-danger"></i></a></li>
					</ul>
				</td>
			</tr>
		</tbody>
<%-- <tbody>
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
			
				<li data-placement="top" data-toggle="tooltip" title="Delete"><a href="#"  data-title="Delete" style="    font-size: 25px;"ng-click="deletefamilymember(item.membersFamilyId)"><i class="fa fa-trash-o txt-danger"></i></a></li>
			</ul>
	
		</td>
	</tr>
</tbody>
 --%>

</table>
</div>
<br><br>
<%-- <div class="row">
			<div class="col-md-2">																	
				<a href="" ng-click="redirectreference(membercategoryid,2)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand;">Previous</a>																													
			</div>
			<div class="col-md-9">
				<div style="padding: 1px 10px 0px 0px; ">
				
						<a href="<%=request.getContextPath() %>/my_profile">Next</a>
						<!-- 	<a ng-click="redirectreference(membercategoryid,2)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand; margin-left: 10px;">Next</a> -->
						
					
				</div>
			</div>
		</div>
		 --%>
		
<div class="row">		<!------------------------------------ Row Start -->							
				<div class="col-md-6 text-left">
						<button type="submit" ng-click="redirectreference(membercategoryid,2)"   class="btn btn-success tablinks"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Previous
					<i class="fa fa-plus" aria-hidden="true" ng-if="nospin == 1"></i><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
					</button></div>										
				<div class="col-md-4 text-right">
							
				</div>
				<div class="col-md-2 text-right">
					<button type="submit" ng-click='return_myprofile()'  class="btn btn-md btn-success btn-block"><i ng-show="spin == 1" class="fa fa-spin fa-spinner" aria-hidden="true"></i> Finish</button>			
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
selected: 4, 
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
