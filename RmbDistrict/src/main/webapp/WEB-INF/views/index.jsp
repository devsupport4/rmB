<!doctype html>
<html lang="en">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title> RMB District 3060 </title>

<!--<link rel="shortcut icon" href="images/favicon.png" type="image/png">-->
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

<%-- <link href="<%=request.getContextPath() %>/resources/front1/css/main.css" rel="stylesheet">	  --%>
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@400;600;700;900&display=swap" rel="stylesheet">


<link rel='stylesheet prefetch' href='https://owlcarousel2.github.io/OwlCarousel2/assets/owlcarousel/assets/owl.carousel.min.css'>
<link rel='stylesheet prefetch' href='https://owlcarousel2.github.io/OwlCarousel2/assets/owlcarousel/assets/owl.theme.default.min.css'>

	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
	<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
	<script src="<%=request.getContextPath() %>/resources/admin/js/controller/frontslider.js"></script>
</head>

<body ng-app="rcbs" ng-controller="SliderCtrl">

<!--====== PRELOADER PART START ======-->

 <div class="preloader">
<div class="loader rubix-cube">
<div class="layer layer-1"></div>
<div class="layer layer-2"></div>
<div class="layer layer-3 color-1"></div>
<div class="layer layer-4"></div>
<div class="layer layer-5"></div>
<div class="layer layer-6"></div>
<div class="layer layer-7"></div>
<div class="layer layer-8"></div> 
</div>
</div> 

<!--====== PRELOADER PART START ======-->
<%@include file="header.jsp" %>
<%-- 
<!--====== HEADER PART START ======-->

<header id="header-part">

<div class="header-top d-none d-md-block">
<div class="container">
<div class="row align-items-center">

<div class="col-lg-4">


</div>



<div class="col-lg-8">
<div class="header-contact text-lg-right text-right">
<ul>
<li><span><a href="#"> Media </a></span></li>
<li><span><a href="#"> Calendar </a></span></li>
<li><span><a href="#"> Contact </a></span></li>

</ul>

		
			
</div>
</div>



</div> <!-- row -->
</div> <!-- container -->
</div> <!-- header top -->

<div class="navigation navigation-2">
<div class="container">
<div class="row no-gutters">

<div class="col-lg-12 col-md-12 col-sm-12 col-12">

<nav class="navbar navbar-expand-lg">
<div class="logo-rmb">
<a class="navbar-brand" href="#">
<img src="<%=request.getContextPath() %>/resources/front/images/Logo-RMB.png" alt="Logo">
</a>
</div>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>

<div class="collapse navbar-collapse sub-menu-bar" id="navbarSupportedContent">
<ul class="navbar-nav ml-auto">








<li class="nav-item"><a href="#"> About RMB District 3060 </a></li>
<!-- <li class="nav-item"><a href="#"> Board of Directors  </a></li> -->
<li class="nav-item"><a href="#"> Committees & Chairs </a></li>
<li class="nav-item"><a href="#"> Members Directory </a></li>
<li class="nav-item"><a href="#"> Events </a></li>
<li>

<%if(session.getAttribute("memberid") == null)
				{%>
				<ul  class="dropdown">
					<li>
						<a href="<%=request.getContextPath() %>/login">
						<span>	Login </span>
						</a>
					</li>
				</ul>
				<%} 
    
	else
	{%>
	<ul class="nav ">
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding: 5px 10px 4px 10px"> 
			
			 <img alt="'<%=session.getAttribute("membername") %>'" class="image--cover" src="<%=session.getAttribute("memberprofilepicture") %>" onerror="this.onerror=null; this.src='<%=request.getContextPath() %>/resources/admin/images/UserImg.png'" >
				<img alt="'<%=session.getAttribute("membername") %>'" class="image--cover" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" ng-if="!<%=session.getAttribute("memberprofilepicture") %>">
			</a>
			<ul class="dropdown-menu" >
				<li > <a href="<%=request.getContextPath() %>/my_profile"  style="padding: 4px;white-space:nowrape;text-align: center;"> My Profile </a> </li>
				
				<li  style="padding: 0px"><a href="#" ng-click="Frontlogout()" style="padding: 4px"> Logout </a></li>
			</ul>
		</li>
	</ul>
	<ul class="nav navbar-nav navbar-left" style="width:45px;"></ul>
	<%}%>

</li>

</ul>
</div>
</nav> <!-- nav -->

</div>


</div> <!-- row -->
</div> <!-- container -->
</div>

</header>



<!--====== HEADER PART ENDS ======-->

 --%>


<!--====== SEARCH BOX PART START ======-->

<div class="search-box">
<div class="serach-form">
<div class="closebtn">
<span></span>
<span></span>
</div>
<form action="#">
<input type="text" placeholder="Search by keyword">
<button><i class="fa fa-search"></i></button>
</form>
</div> <!-- serach form -->
</div>

<!--====== SEARCH BOX PART ENDS ======-->

<!--====== SLIDER PART START ======-->

<section id="slider-part" class="slider-active" >

<!--  <div class="single-slider bg_cover pt-150" style="background-image: url(resources/front/images/slider/s-1.jpg)" >
</div> 

<div class="single-slider bg_cover pt-150" style="background-image: url(resources/front/images/slider/s-2.jpg)">
</div>
 -->
<c:forEach items="${sliders}" var="slider" varStatus="status">
        <div class="single-slider bg_cover pt-150" style="background-image: url(${slider.image})">
</div>  
      </c:forEach>



	
</section>

<!--====== SLIDER PART ENDS ======-->




<!-- We offer Different Services-->
<section class="diff-offer-wrapper">
<div class="container">
<div class="row diff-offer">
<ul>
<li class="we-offer-cont">
<h2> Rotary Means Business <span> District 3060 </span></h2>
</li>
<li class="we-offer-cont2">
<p> Welcome to Rotary Means Business Fellowship chapter of Vadodara Rotary International District 3060 Rotary Means Business is a Fellowship of Rotary International Today Rotarians are embracing the original foundations of what the Rotary Club was based on as we adhere to the Four Way Test.
<a style="color: #30cefa" class="pull-right" href="#"> readmore <i class="fa fa-angle-double-right"></i> </a>
</p>
</li>
</ul>
</div>

</div>
</section>
<!-- We offer Different Services-->




<!--====== COUNT DOWN PART START ======-->
<section id="count-down-part" class="bg_cover">
<div class="container">
<div class="row align-items-center justify-content-center">

<div class="col-lg-6 col-md-8">
<div class="slider-cont-3 text-center">
<h2> I am Looking for... </h2>
<span>More then 3000+ Businesses for you</span>

<div class="slider-search mt-15 mb-15">
<form action="#">
<div class="row no-gutters">

<div class="col-sm-10">
<input type="text" placeholder="Search keyword">
</div>
<div class="col-sm-2">
<button type="button" class="main-btn"><i class="fa fa-search"></i></button>
</div>
</div> <!-- row -->
</form>
</div>

<p> Rotary Means Business encourages Rotarians to support the success of their fellow Rotarians by doing business with them, and by referring others to them. Fellowship of Rotary International Today Rotarians are embracing... </p>

</div> <!-- slider cont3 -->
</div>


<div class="col-lg-6">


<div  id="owl-2">
<div class="owl-carousel owl-theme">

<div><img class="img-responsive" src="resources/front/images/banner/1.jpg"></div>
<div><img class="img-responsive" src="resources/front/images/banner/2.jpg"></div>
<div><img class="img-responsive" src="resources/front/images/banner/3.jpg"></div>
<div><img class="img-responsive" src="resources/front/images/banner/4.jpg"></div>
<div><img class="img-responsive" src="resources/front/images/banner/5.jpg"></div>
<div><img class="img-responsive" src="resources/front/images/banner/6.jpg"></div>

</div>
</div>
</div>



</div> <!-- row -->
</div> <!-- container -->
</section>
<!--====== COUNT DOWN PART ENDS ======-->



<!--====== About RMB District 3060  START ======-->
<section id="aboutrmb-part">
<div class="container">
<div class="row">

<div style="padding-right: 3%;" class="col-lg-6">
<div class="section-title mt-50">
<h5> About </h5>
<h2> RMB District 3060 </h2>
</div> <!-- section title -->
<div class="aboutrmb-cont">
<p> Rotary Means Business Fellowship chapter of Vadodara Rotary International District 3060

Rotary Means Business is a Fellowship of Rotary International

Today Rotarians are embracing the original foundations of what the Rotary Club was based on as we adhere to the Four Way Test.

This fellowship operates in accordance with Rotary International policy, but is not an agency of, or controlled by, Rotary International. <a style="color: #30cefa" class="pull-right" href="#"> readmore <i class="fa fa-angle-double-right"></i> </a> </p>

</div> <!-- teachers cont -->
</div>

<div class="col-lg-6">
<div class="teachers1 mt-20">
<div class="row">

<div class="col-sm-6">
<div class="singel-teachers1 mt-30 text-center">
<div class="image">
<img src="resources/front/images/rtn-prashant-jani.jpg" alt="Rtn. Prashant Jani">
</div>
<div class="cont">
<a href="#"><h6>  Rtn. Prashant Jani  </h6></a>
<span> District Governor </span>
</div>
</div> <!-- singel -->
</div>


<div class="col-sm-6">
<div class="singel-teachers1 mt-30 text-center">
<div class="image">
<img src="<%=request.getContextPath() %>/resources/front/images/about/ParagDoshi.png" style="height: 329px;" alt="Rtn. Parag Doshi">
</div>
<div class="cont">
<a href="teachers-singel.html"><h6> Rtn. Parag Doshi </h6></a>
<span> RMB Chair   </span>
</div>
</div> <!-- singel teachers -->
</div>



</div> <!-- row -->
</div> <!-- teachers -->
</div>

</div> <!-- row -->

<div class="row">
<div class="col-lg-12">
<div class="apply">
<div class="row no-gutters">
<div class="col-lg-6">
<div class="apply-cont apply-color-1">
<h3> Vision </h3>
<p> 
Rotary Means Business Fellowship chapter of Vadodara Rotary International District 3060 Rotary Means Business is a Fellowship of Rotary International Today Rotarians are embracing the original foundations of what the Rotary Club was based on as we.  </p>
</div> <!-- apply cont -->
</div>
<div class="col-lg-6">
<div class="apply-cont apply-color-2">
<h3> Mission </h3>
<p> Rotary Means Business Fellowship chapter of Vadodara Rotary International District 3060 Rotary Means Business is a Fellowship of Rotary International Today Rotarians are embracing the original foundations of what the Rotary Club was based on as we.</p>
</div> <!-- apply cont -->
</div> 
</div>
</div> <!-- row -->
</div>
</div>

</div> <!-- container -->
</section>
<!--====== About RMB District 3060  ENDS ======-->






<!--====== COUNTER PART START ======-->
<div id="counter-part" class="bg_cover" data-overlay="9" style="background-image: url(resources/front/images/bg-2.jpg)">
<div class="container">
<div class="row">

<div class="col-lg-3 col-sm-6">
<div class="singel-counter text-center mt-40">
<span><span class="counter"> 05 </span>+</span>
<p> Fellowships </p>
</div> <!-- singel counter -->
</div>

<div class="col-lg-2 col-sm-6">
<div class="singel-counter text-center mt-40">
<span><span class="counter"> 500 </span>+</span>
<p> Members </p>
</div> <!-- singel counter -->
</div>

<div class="col-lg-2 col-sm-6">
<div class="singel-counter text-center mt-40">
<span><span class="counter">1500</span>+</span>
<p> R2R Meetings </p>
</div> <!-- singel counter -->
</div>

<div class="col-lg-2 col-sm-6">
<div class="singel-counter text-center mt-40">
<span><span class="counter">2700</span>+</span>
<p> References </p>
</div> <!-- singel counter -->
</div>

<div class="col-lg-3 col-sm-6">
<div class="singel-counter text-center mt-40">
<span><span class="counter">10000000</span>+</span>
<p> Amount of business </p>
</div> <!-- singel counter -->
</div>

</div> <!-- row -->
</div> <!-- container -->
</div>
<!--====== COUNTER PART ENDS ======-->





<!-- Events Section -->
<!-- Events Section -->
<section class="events-section style-two">
<div class="container">

<div class="row align-items-end">
<div class="col-lg-6 col-md-8 col-sm-7">
<div class="section-title">
<!--<h5>Publications</h5>-->
<h2> Events </h2>
</div> <!-- section title -->
</div>
<div class="col-lg-6 col-md-4 col-sm-5">
<div class="products-btn text-right">
<a href="#" class="main-btn"> View Events </a>
</div> <!-- products btn -->
</div>
</div> <!-- row -->


<div class="row">

<div class="event-block-one col-lg-4 col-md-6">
<div class="inner-box">
<div class="image"><img src="resources/front/images/event/event-1.jpg" alt=""></div>
<div class="lower-content">
<div class="date">
<h1>25</h1>
<div class="text"><span>August</span> <br> 09.00am  - 03.00pm</div>

<h2> <i class="fa fa-map-marker"></i> </h2>

</div>

<h4><a href="#"> RMBV NEO - New Emerging Opportunites </a></h4>

<!--<div class="location">
<i class="fa fa-map-marker" aria-hidden="true"></i> @Wave Club, Bhayli Road, Vadodara 
</div>-->

<a href="#" class="reg-btn"> Register Now </a>

</div>



</div>
</div>

<div class="event-block-one col-lg-4 col-md-6">
<div class="inner-box">
<div class="image"><img src="resources/front/images/event/event-2.jpg" alt=""></div>
<div class="lower-content">
<div class="date">
<h1>16</h1>
<div class="text"><span>October</span> <br>10.00am  - 12.00pm</div>
<h2> <i class="fa fa-map-marker"></i> </h2>
</div>
<h4><a href="#"> Meeting of Rotary Means Business Fellowship of Rotary District 3060</a></h4>

<a href="#" class="reg-btn"> Register Now </a>

</div>

</div>
</div>

<div class="event-block-one col-lg-4 col-md-6">
<div class="inner-box">
<div class="image"><img src="resources/front/images/event/event-3.jpg" alt=""></div>
<div class="lower-content">
<div class="date">
<h1>05</h1>
<div class="text"><span>November</span> <br>10.00am  - 05.00pm</div>
<h2> <i class="fa fa-map-marker"></i> </h2>
</div>
<h4><a href="#"> Meeting of Rotary Means Business Fellowship of Rotary District 3060</a></h4>

<a href="#" class="md-btn"> Read More </a>

</div>
</div>


</div>

</div>
</section>
<!-- Events Section -->




<!-- Top Performers Section -->
<!-- 
<section id="homeIntro"  class="team-section parallax first-widget">
<div class="container">
<div class="row m-0 justify-content-md-between align-items-end">
<div class="sec-title light">
<h1> Top Performers of June 2020 in Rotary District 3060</h1>
<div class="text">
of June 2020 in Rotary District 3060
</div>
</div>
Link Btn

</div>

<div class="cause-wrapper">
<div class="row">

Cause Block Four
<div class="cause-block-four col-lg-4">
<div class="inner-box">
<div class="image">                                
<img src="resources/front/images/performers-1.jpg" alt="">
</div>
<div class="lower-content">
<div class="wrapper-box">
<h4><a href="#"> Rtn Nishant Ramani </a></h4>
<div class="text"> Rotary Club of Baroda </div>

<div class="category1">
<h6 style="font-weight: normal; font-size: 26px;"> R2R MEETINGS: </h6>
<h6> 018 </h6>
</div>

</div>
</div>
</div>
</div>



Cause Block Four
<div class="cause-block-four col-lg-4">
<div class="inner-box">
<div class="image">                                
<img src="resources/front/images/performers-3.jpg" alt="">
</div>
<div class="lower-content">
<div class="wrapper-box">
<h4><a href="#"> Rtn Dr. Rakesh Patel </a></h4>
<div class="text"> Rotary Club of Baroda </div>

<div class="category1">
<h6 style="font-weight: normal; font-size: 26px;">REFERENCES: </h6>
<h6> 027 </h6>
</div>

</div>
</div>
</div>
</div>

Cause Block Four
<div class="cause-block-four col-lg-4">
<div class="inner-box">
<div class="image">                                
<img src="resources/front/images/performers-2.jpg" alt="">
</div>
<div class="lower-content">
<div class="wrapper-box">
<h4><a href="#"> Rtn Chetan Dedhia </a></h4>
<div class="text"> Rotary Club of Baroda </div>

<div class="category1">
<h6 style="font-weight: normal; font-size: 26px;"> BUSINESS: </h6>
<h6> ₹1500000 </h6>
</div>

</div>
</div>
</div>
</div>

</div>                
</div>


</div>
</section>


 -->


<!--====== CATEGORY PART START ======-->
<section id="category-2-part">
<div class="container">

<div class="row align-items-end">
<div class="col-lg-8 col-md-8 col-sm-7">
<div class="section-title">
<!--<h5>Publications</h5>-->
<h2> RMB Fellowships of RMB District 3060 </h2>
</div> <!-- section title -->
</div>
<div class="col-lg-4 col-md-4 col-sm-5">
<div class="products-btn text-right">
<a href="#" class="main-btn"> View All </a>
</div> <!-- products btn -->
</div>
</div>


<div class="row" >
<div class="col-lg-12">

<div class="category-2-items pt-10" >
<div class="row" >

<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" ng-repeat="item in getAllFellowshipNameList">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="<%=request.getContextPath() %>/resources/front/images/Fellowships-1.jpg" alt="">
</div>

<div class="items-cont" >
<a href="#">
<h5> {{item.fellowship_name}} </h5>
<!--<span>24 courses</span>-->
</a>
</div>
</div> <!-- singel items -->
</div>

<!-- 
<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Kheda Anand </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>

<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Ankleshwar Bharuch </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>


<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Surat </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>


<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Rajkot </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>



<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Vapi </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>


<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Daman </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>


<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<div class="singel-items text-center mt-30">
<div class="items-image">
<img src="resources/front/images/Fellowships-1.jpg" alt="">
</div>
<div class="items-cont">
<a href="#">
<h5> Nadiad </h5>
<span>24 courses</span>
</a>
</div>
</div> singel items
</div>
 -->
</div> <!-- row -->
</div> <!-- category -->
</div>
</div> <!-- row -->
</div> <!-- container -->
</section>
<!--====== CATEGORY PART ENDS ======-->



<!-- Section: events -->
<!-- <section id="event" class="bg-silver-light">
<div class="container">


<div class="row align-items-end">
<div class="col-lg-9 col-md-8 col-sm-7">
<div class="section-title">
<h5>Publications</h5>
<h2>Calendar of RMB District 3060 for July 2020 </h2>
</div> section title
</div>
<div class="col-lg-3 col-md-4 col-sm-5">
<div style="margin-bottom: 25px;" class="products-btn text-right">
<a href="#" class="main-btn"> View All </a>
</div> products btn
</div>
</div>

<div class="section-content">
<div class="row">

<div class="col-md-6">

<div class="bday media sm-maxwidth400 border-bottom  pt-10 pb-10">
<div class="row">

<div class="col-xs-2 col-md-3">
<div class="bday-date text-center bg-theme-colored">
<ul>
<a href="#">
<i class="fa fa-birthday-cake"></i>
</a>
</ul>
</div>
</div>

<div class="col-xs-9 col-md-9">
<div class="bday-content">
<h4 class="media-heading font-weight-600">
<a href="#"> 
Bday of Rtn Pritesh Shah
</a>
</h4>
<p style="line-height: 24px; font-size: 18px;     font-weight: 600;" class="mb-0">
<i style="    margin-right: 5px;" class="fa fa-calendar"></i> 04 July 2020
</p>
</div>
</div>

</div>
</div>

<div class="bday media sm-maxwidth400 border-bottom  pt-10 pb-10">
<div class="row">

<div class="col-xs-2 col-md-3">
<div class="bday-date text-center bg-theme-colored">
<ul>
<a href="#">
<i style="color: #DA1C5C;" class="fa fa-heart"></i>
</a>
</ul>
</div>
</div>

<div class="col-xs-9 col-md-9">
<div class="bday-content">
<h4 class="media-heading font-weight-600">
<a href="#"> 
Rtn Dattesh & Ann Nitu Shah
</a>
</h4>
<p style="line-height: 24px; font-size: 18px;     font-weight: 600;" class="mb-0">
<i style="    margin-right: 5px;" class="fa fa-calendar"></i> 08 July 2020
</p>
</div>
</div>

</div>
</div>



</div>

<div class="col-md-6">



<div class="event media sm-maxwidth400 border-bottom  pt-10 pb-10">
<div class="row">

<div class="col-xs-2 col-md-3">
<div class="event-date bg-theme-colored2 text-center">
<ul>
<a href="#">
<i style="color: #00246c;" class="fa fa-briefcase"></i>
<li class="font-28 text-white font-weight-700">28</li>
<li class="font-18 text-white text-center text-uppercase">Feb</li>
</a>
</ul>
</div>
</div>

<div class="col-xs-9 col-md-9">
<div class="event-content">
<h4 class="media-heading font-weight-600">
<a href="#"> 
RMBV Meeting </a></h4>
<p style="    line-height: 24px;" class="mb-0">
psum dolor amet consectetur elitQuas evenie tnemo dicta Ullam namdolor sit amet adipisic evenietneo evenie </p>
</div>
</div>

</div>
</div>

    


<div class="event media sm-maxwidth400 border-bottom   pt-10 pb-10">
<div class="row">

<div class="col-xs-2 col-md-3">
<div class="event-date bg-theme-colored2 text-center">
<ul>
<a href="#">
<i style="color: #00246C" class="fa fa-calendar"></i>
<li class="font-28 text-white font-weight-700">28</li>
<li class="font-18 text-white text-center text-uppercase">Feb</li>
</a>
</ul>
</div>
</div>

<div class="col-xs-9 col-md-9">
<div class="event-content">
<h4 class="media-heading font-weight-600">
<a href="#"> 
RMBV NEO - New Emerging Opportunites </a></h4>
<p style="    line-height: 24px;" class="mb-0">psum dolor amet consectetur elitQuas evenie tnemo dicta Ullam namdolor sit amet adipisic evenietneo evenie </p>
</div>
</div>

</div>
</div>

</div>
</div>

</div>
</div>
</section>


 -->
<!--====== PUBLICATION PART START ======-->
<section id="publication-part" class="gray-bg">
<div class="container">
<div class="row align-items-end">
<div class="col-lg-6 col-md-8 col-sm-7">
<div class="section-title ">
<h2>  Media Gallery   </h2>
</div> <!-- section title -->
</div>
<div class="col-lg-6 col-md-4 col-sm-5">
<div class="products-btn text-right">
<a href="#" class="main-btn"> View All </a>
</div> <!-- products btn -->
</div>
</div> <!-- row -->
<div class="row justify-content-center">

<div class="col-lg-4 col-md-6 col-sm-8">
<div class="singel-publication mt-30">
<div class="image">
<img src="resources/front/images/Gallery/2.jpg">
</div>
<div class="cont">
<div class="name">
<a href="#"><h6> RMBV NEO </h6></a>
<span> @Wave Club, Bhayli Road, Vadodara </span>
</div>
</div>
</div> <!-- singel publication -->
</div>


<div class="col-lg-4 col-md-6 col-sm-8">
<div class="singel-publication mt-30">
<div class="image">
<img src="resources/front/images/Gallery/3.jpg">
</div>
<div class="cont">
<div class="name">
<a href="#"><h6> Meeting of RMBF of Rotary District 3060 </h6></a>
<span> @Online </span>
</div>
</div>
</div> <!-- singel publication -->
</div>


<div class="col-lg-4 col-md-6 col-sm-8">
<div class="singel-publication mt-30">
<div class="image">
<img src="resources/front/images/Gallery/1.jpg">
</div>
<div class="cont">
<div class="name">
<a href="#"><h6> RMBV NEO </h6></a>
<span> @Wave Club, Bhayli Road, Vadodara </span>
</div>
</div>
</div> <!-- singel publication -->
</div>

</div> <!-- row -->
</div> <!-- container -->
</section>

<!--====== PUBLICATION PART ENDS ======-->


<!--====== FOOTER PART START ======-->

<footer id="footer-part">
<div class="footer-top">
<div class="container">
<div class="row">
<div class="col-lg-4 col-md-6">
<div class="footer-about">
<div class="footer-title pb-pb-15">
<h6> Follow us on </h6>
</div>


<ul class="pt-20">
<li><a href="#"><i class="fa fa-facebook-f"></i></a></li>
<li><a href="#"><i class="fa fa-twitter"></i></a></li>
<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
<li><a href="#"><i class="fa fa-instagram"></i></a></li>
</ul>

</div> <!-- footer about -->
</div>

<div class="col-lg-5 col-md-5 col-sm-6">
<div class="footer-link">
<div class="footer-title pb-20">
<h6> Quick Links </h6>
</div>

<ul>
<li><a href="#"><i class="fa fa-angle-right"></i>  About RMB District 3060 </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Board of Directors </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Committees & Chairs </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Members Directory </a></li>

</ul>

<ul>
<li><a href="#"><i class="fa fa-angle-right"></i> Events </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Media </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Calendar </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Contact </a></li>

</ul>
</div> <!-- footer link -->
</div>















<div class="col-lg-2 col-md-6 col-sm-6">
<div class="footer-link support">
<div class="footer-title pb-20">
<h6> Customer Service </h6>
</div>
<ul>
<li><a href="#"><i class="fa fa-angle-right"></i>FAQS</a></li>
<li><a href="#"><i class="fa fa-angle-right"></i> Terms & Conditions </a></li>
<li><a href="#"><i class="fa fa-angle-right"></i>Privacy Policy</a></li>
<!--<li><a href="#"><i class="fa fa-angle-right"></i>Support</a></li>-->
<li><a href="#"><i class="fa fa-angle-right"></i> Refund & Cancellation</a></li>
</ul>
</div> <!-- support -->
</div>



</div> <!-- row -->
</div> <!-- container -->
</div> <!-- footer top -->

<div class="footer-copyright pt-10 pb-25">
<div class="container">
<div class="row">
<div class="col-md-8">
<div class="copyright text-md-left text-center pt-15">
<p> Content © 2020 RMB District 3060 | Powered by: Astar Technologies </p>
</div>
</div>
<div class="col-md-4">
<div class="copyright text-md-right text-center pt-15">

</div>
</div>
</div> <!-- row -->
</div> <!-- container -->
</div> <!-- footer copyright -->
</footer>

<!--====== FOOTER PART ENDS ======-->

<!--====== BACK TO TP PART START ======-->

<a href="#" class="back-to-top"><i class="fa fa-angle-up"></i></a>

<!--====== BACK TO TP PART ENDS ======-->








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
<script src="resources/front/js/jquery.countdown.min.js"></script>

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
