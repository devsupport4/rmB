
<!--====== PRELOADER PART START ======-->

<!--<div class="preloader">
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
</div>-->

<!--====== PRELOADER PART START ======-->

<div class="_1QQpsu _3bWLGx">
<div class="mvW8vq">
<div class="_1U-Jli"></div>
<div class="FqIVIZ"> Please Potate your Device for Best Resolution </div>
</div>
</div>

    
    

<!--====== HEADER PART START ======-->
<header id="header-part">
<div class="header-top d-none d-md-block">
<div class="container">
<div class="row align-items-center">

<div class="col-lg-4"></div>



<div class="col-lg-8">
<div class="header-contact text-lg-right text-right">
<ul>
<li><span><a href="#"> Media </a></span></li>
<li><span><a href="#"> Calendar </a></span></li>
<li><span><a href="#"> Contact </a></span></li>

<div class="dropdown">

<!-- <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">


</a>   -->

<%if(session.getAttribute("memberid") == null)
				{%>
				<ul  class="dropdown">
					<li>
						<a href="<%=request.getContextPath() %>/login" style="  color:#dee2e6" >
							Login
						</a>
					</li>
				</ul>
				<%} 
    
	else
	{%>
	<ul class="nav ">
		<li class="dropdown">
		<span class="mr-2 d-none d-lg-inline text-gray-600 small"> <%=session.getAttribute("membername") %> </span>
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding: 5px 10px 4px 10px"> 
			
			 <img alt="'<%=session.getAttribute("membername") %>'" class="img-profile rounded-circle" src="<%=session.getAttribute("memberprofilepicture") %>" onerror="this.onerror=null; this.src='<%=request.getContextPath() %>/resources/admin/images/UserImg.png'" >
				<%-- <img alt="'<%=session.getAttribute("membername") %>'" class="image--cover" src="<%=request.getContextPath() %>/resources/admin/images/UserImg.png" ng-if="!<%=session.getAttribute("memberprofilepicture") %>"> --%>
			</a>
			
					
		<!-- Dropdown - User Information -->
		<div class="dropdown-content shadow animated--grow-in text-left" aria-labelledby="userDropdown">
		<a class="dropdown-item" href="<%=request.getContextPath() %>/index2" >
		My Dashboard
		</a>
		<a class="dropdown-item" href="<%=request.getContextPath() %>/Frontlogout">
		Logout
		</a>


		</div>

			
			
		</li>
	</ul>
	<ul class="nav navbar-nav navbar-left" style="width:45px;"></ul>
	<%}%>

</li>




</div>


</ul>


</div>
</div>



</div> <!-- row -->
</div> <!-- container -->
</div> <!-- header top -->
<div class="navigation navigation-2">
<div class="container">
<div class="row no-gutters">

<div class="col-lg-12 col-md-12 col-sm-10 col-10">

<nav class="navbar navbar-expand-lg">
<div class="logo-rmb">
<a class="navbar-brand" href="<%=request.getContextPath() %>/">
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

<!--<li class="nav-item">
<a class="active" href="index-2.html">Home</a>
<ul class="sub-menu">
<li><a href="index-2.html">Home 01</a></li>
<li><a class="active" href="index-3.html">Home 02</a></li>
<li><a href="index-4.html">Home 03</a></li>
</ul>
</li>-->

<li class="nav-item">
<a href="about-rmb-district-3060.html"> About RMB District 3060 </a>
</li>
<!--<li class="nav-item"><a href="#"> Board of Directors  </a></li>-->
<li class="nav-item"><a href="committees-&-chairs.html"> Committees & Chairs </a></li>
<li class="nav-item"><a href="#"> Members Directory </a></li>
<li class="nav-item"><a href="#"> Events </a></li>

</ul>
</div>
</nav> <!-- nav -->

</div>

<div class="col-lg-1 col-md-2 col-sm-2 col-2 visible-sm visible-xs">


<div class="right-icon text-right">
<ul>
<li><a href="#" id="search"><i class="fa fa-user-circle-o"></i></a></li>
<!--<li><a href="#"><i class="fa fa-shopping-bag"></i><span>0</span></a></li>-->
</ul>
</div> 
</div>

</div> <!-- row -->
</div> <!-- container -->
</div>
</header>
<!--====== HEADER PART ENDS ======-->






<!--====== Mobile User Menu PART START ======-->
<div id="main-nav" class="search-box">
<div class="serach-form">
<div class="closebtn">
<span></span>
<span></span>
</div>

<ul>
<li><a href="#"> LINK1 </a></li>
<li><a href="#"> LINK2 </a></li>
<li><a href="#"> LINK3 </a></li>
<li><a href="#"> LINK4 </a></li>
<li><a href="#"> LINK5 </a></li>
</ul>

</div> <!-- serach form -->
</div>
<!--======  Mobile User Menu ENDS ======-->


