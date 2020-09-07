<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title> My Dashboard </title>
<!-- fonts-->
<link href="<%=request.getContextPath() %>/resources/front2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/font-awesome-4.7.0/css/font-awesome.min.css"> 

<!-- styles-->
<link href="<%=request.getContextPath() %>/resources/front2/css/sb-admin-2.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/front2/css/style.css" rel="stylesheet">



<script src="<%=request.getContextPath() %>/resources/front2/js/slimselect.min.js"></script>
<link href="<%=request.getContextPath() %>/resources/front2/css/slimselect.min.css" rel="stylesheet"></link>




<!--***datetimepicker****-->
<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front2/datetimepicker/css/kendo.material.mobile.min.css" />
<!--<script src="https://kendo.cdn.telerik.com/2018.1.221/js/jquery.min.js"></script>-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front2/datetimepicker/js/kendo.all.min.js"></script>

		<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/member_meeting.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/one_to_one_model.js"></script>
	


</head>

<body id="page-top" ng-app="rcbs" ng-controller="memberMeetingCtrl" ng-init="getMemberMeetingsDetailById(<%= session.getAttribute("loginid") %>)" ng-cloak>

<%@include file="header1.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

<div class="row">
<div class="col-lg-8"></div>
<div class="col-lg-4">
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4 pull-right">
<!--<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>-->
<a href="#" data-toggle="modal" data-target="#AddMeetingsModal" class="d-none d-sm-inline-block btn btn-md btn-success shadow-sm"> Add New R2R Meeting  
</a>
</div>
</div>
</div>


<div class="row">
<div class="col-lg-12 col-md-12 col-sm-6 col-xs-12">

<!-- DataTales Example -->
<div class="card shadow mb-4">

<!-- Card Header -->
<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">


<h5 class="m-0 font-weight-bold text-darkblue"> My meeting summary <!-- beetween {{fromdate}} and {{todate}} --></h5>


<form style="    padding-top: 20px;" class="form-inline" action="#">
<div class="row">

<div class="col-lg-6">
<label for="email">From:</label>
<input id="datetimepicker" ng-model="fromdate" title="datetimepicker" style="width: 80%;" />
</div>

<div class="col-lg-6">
<label for="pwd">To:</label>
<input id="todatetimepicker" ng-model="todate" title="todatetimepicker" ng-change="getMeetingsByDate(<%= session.getAttribute("loginid") %>)" style="width: 80%;" />
</div>



</div>
</form>
</div>


<div class="card-body">
<div class="table-responsive">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
<thead>
<tr>
<th> Date </th>
<th> Met with	</th>
<th> Location </th>
<th> Topics </th>
</tr>
</thead>

<!--<tfoot>

<tr>
<th>Name</th>
<th>Position</th>
<th>Office</th>
<th>Age</th>
<th>Start date</th>
<th>Salary</th>
</tr>
</tfoot>-->

	<tbody>
			<tr ng-repeat="item in getMemberMeetings">
				<td>{{item.meetingDate}}</td>
				<td>{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</td>
				<td>{{item.inviteeFirstName}} {{item.inviteeMiddleName}} {{item.inviteeLastName}}</td>
				<td>{{item.location}}</td>
				<td>{{item.conversationTopic}}</td>
			</tr>
	</tbody>
</table>
</div>
</div>
</div>

</div>
</div>



</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Footer -->
<footer class="sticky-footer bg-white">
<div class="container my-auto">
<div class="copyright text-center my-auto">
<span>Content Copyright &copy; RMBF Tamilnadu 2020 </span> &nbsp; |  &nbsp;
<span> Powered by: <a href="https://www.myclubman.com/" style="color: #858796;" target="_blank"> MyClubman.com </a></span>
</div>
</div>
</footer>
<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->



<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
<i class="fas fa-angle-up"></i>
</a>






<!--  Add New R2R Meetings Modal-->
<div class="modal fade" id="AddMeetingsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<%@include file="member_meeting.jsp" %>
</div>








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

<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
<script src="<%=request.getContextPath() %>/resources/front2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<!--<script src="vendor/jquery-easing/jquery.easing.min.js"></script>-->


<!---->
<script src="<%=request.getContextPath() %>/resources/front2/js/main.js"></script>

<!-- Custom scripts for all pages-->
<!--<script src="js/sb-admin-2.min.js"></script>-->


<!-- Page level custom scripts this js for Date and time id's function -->
<script src="<%=request.getContextPath() %>/resources/front2/js/kendo-function.js"></script>   



<!--***********************************-->
<!--<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>-->

<script>
$('.select2').select2();
</script>




<script>
/***************** Dropdown Search & Select Strat ***********************/
$(document).ready(function() {
var x = new SlimSelect({
select: '#demo'
});
});



$(document).ready(function() {
var x = new SlimSelect({
select: '#metmemberid'
});
});

/***************** Dropdown Search & Select End ***********************/

</script>


<script>

</script>

</body>


</html>
