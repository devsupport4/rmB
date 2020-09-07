<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title> Login | RMB District 3060 </title>

<link rel="apple-touch-icon" sizes="180x180" href="images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="images/favicon/favicon-16x16.png">
<link rel="manifest" href="images/favicon/site.webmanifest">
<meta name="msapplication-TileColor" content="#da532c">
<meta name="theme-color" content="#ffffff">


<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/slick.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/animate.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/nice-select.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/jquery.nice-number.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/magnific-popup.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/default.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/responsive.css">

<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@400;600;700;900&display=swap" rel="stylesheet">

<link rel='stylesheet prefetch' href='css/owl.carousel.min.css'>
<link rel='stylesheet prefetch' href='css/owl.theme.default.min.css'>

	<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>		
</head>

<body ng-app="rcbs" ng-controller="sidebarCtrl">



<!--/header START-->
	<%@include file="header.jsp" %>
<!--/header END-->







<!--====== PAGE BANNER PART START ======-->

<section id="page-banner" class="bg_cover" data-overlay="8" style="background-image: url(images/page-banner-1.jpg)">
<div class="container">
<div class="row">
<div class="col-lg-12">
<div class="page-banner-cont">
<nav aria-label="breadcrumb">
<ol class="breadcrumb">
<li class="breadcrumb-item"><a href="#">Home</a></li>
<li class="breadcrumb-item active" aria-current="page"> Login </li>
</ol>
</nav>
</div>  <!-- page banner cont -->
</div>
</div> <!-- row -->
</div> <!-- container -->
</section>

<!--====== PAGE BANNER PART ENDS ======-->



<!--====== LOGIN PART START ======-->

<section id="contact-page" class="pt-90 pb-120">
<div class="container">
<div class="row">

<div class="col-lg-3"></div>

<div class="col-lg-6">
<div class="contact-from">
<div class="section-title">
<h5> Login </h5>
<!--<h2> Rotary Means Business District 3060 </h2>-->
</div> <!-- section title -->

<div class="main-form ">
<form id="contact-form" action="#" method="post" data-toggle="validator" ng-submit="checkFrontLogin(<%= request.getParameter("membertype")%>)" novalidate>
<div class="row">

<div class="col-md-12">
<div class="singel-form form-group">
<input name="name" type="text"  name="username" id="username" ng-model="username"  placeholder="Enter Email Address..." required="required">
<div class="help-block with-errors"></div>
</div> <!-- singel form -->
</div>

<div class="col-md-12">
<div class="singel-form form-group">
<input name="pws" type="password"  name="password" id="password" ng-model="password" placeholder="Password"
 required="required">
<div class="help-block with-errors"></div>
</div> <!-- singel form -->
</div>




<div class="col-md-6">
<div style="    padding-top: 15px;" class="form-group">
<div class="custom-control custom-checkbox small">
<input type="checkbox" class="custom-control-input" ng-model="loggedin" id="customCheck">
<label class="custom-control-label" for="customCheck"> Remember Me </label>
</div>
</div>
</div>


    
<p class="form-message"></p>

<div class="col-md-12">
<div class="singel-form">
<button type="submit" class="main-btn btn-block"> Login </button>
</div> <!-- singel form -->
</div> 

</div> <!-- row -->



<div style="padding-top: 20px;" class="text-center">
<a style="color: #007bff" class="small"href="<%=request.getContextPath() %>/forget_password">Forgot Password?</a>
</div>

    
    
</form>
</div> <!-- main form -->
</div> <!--  contact from -->
</div>

<div class="col-lg-3"></div>

</div> <!-- row -->
</div> <!-- container -->

</section>

<!--====== LOGIN PART ENDS ======-->





<!--/#footer START-->
<%@include file="footer.jsp" %>		
<!--/#footer END-->






<!--====== jquery js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/vendor/modernizr-3.6.0.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front/js/vendor/jquery-1.12.4.min.js"></script>

<!--====== Bootstrap js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/bootstrap.min.js"></script>

<!--====== Slick js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/slick.min.js"></script>

<!--====== Magnific Popup js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/jquery.magnific-popup.min.js"></script>

<!--====== Counter Up js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/waypoints.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/front/js/jquery.counterup.min.js"></script>

<!--====== Nice Select js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/jquery.nice-select.min.js"></script>

<!--====== Nice Number js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/jquery.nice-number.min.js"></script>

<!--====== Count Down js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/jquery.countdown.min.js"></script>

<!--====== Validator js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/validator.min.js"></script>

<!--====== Ajax Contact js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/ajax-contact.js"></script>

<!--====== Main js ======-->
<script src="<%=request.getContextPath() %>/resources/front/js/main.js"></script>

<!--====== Map js ======-->
<!--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDC3Ip9iVC0nIxC6V14CKLQ1HZNF_65qEQ"></script>
<script src="js/map-script.js"></script>-->



<script src='https://owlcarousel2.github.io/OwlCarousel2/assets/owlcarousel/owl.carousel.js'></script>




<script  src="<%=request.getContextPath() %>/resources/front/js/index.js"></script>



</body>
</html>
