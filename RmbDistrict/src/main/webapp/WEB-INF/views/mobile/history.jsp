<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>  Club Info   | Rotary Means Business Fellowship Bangalore </title>		
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/front_sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/front_home.js"></script>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-153537496-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());
            gtag('config', 'UA-153537496-1');
        </script>

		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">
	</head>
	<body class="homepage" ng-app="rcbs" ng-controller="sidebarCtrl">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18">History</h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="service-project-detail">
							<div class="page-content">
								<div class="mdl-card mdl-shadow--2dp demo-card-wide">
									<div class="panel-group d-accordion">
										<div class="panel panel-default">
											<div class="panel-heading" data-toggle="collapse" data-parent=".d-accordion" href="#">
												<h4 class="panel-title">   History of RMBFB</h4>
											</div>
											<div class="panel-collapse collapse in">
												<div class="panel-body">
													<p> <span class="weight-600"> 1. </span> The idea of forming the 4th Rotary Club in Baroda came up in July, 1993. Bulk of the spade work was done from August to December 1993. The Provisional Club started in January 1994. The Charter was granted on June 30, 1994 by RI President (1993-94) Rtn. Robert Barth of Switzerland, and handed over to Charter President Rtn. Shamit Patel on October 20, 1994 by District Governor (1993-94) Rtn. Vishnu Patel of Vapi, Gujarat. <br>Chief Guest at the Charter Presentation & 1st Installation Ceremony of the Club was RI Director-Elect (1995-97) Rtn. Kalyan Banerjee, who later became RI President (2011-12).</p>
													<p> <span class="weight-600"> 2. </span> The Sponsor Club was Rotary Club of Jawaharnagar and the Governorâs Special Representative (GSR) for the formation of the new Rotary Club was PP Rtn. Jatin Bhatt, who later became RI District 3060 Governor (2002-03).<br>Initially they had proposed the name of the new Rotary Club as âRotary Club of Fertilizer Nagarâ, but PDG Rtn. Ashok C. Patel (1979-80) of Rotary Club of Baroda suggested the name âRotary Club of Barod Sayajinagariâ which looked very appropriate and unique, and was therefore readily accepted. </p>
													<p> <span class="weight-600"> 3. </span> There were already 3 Rotary Clubs in Baroda in 1993. But they had average Membership age of about 45 years and above. The need was felt to have a new Rotary Club with a younger age group. And hence, all the 25 Charter Members of our Rotary Club were in the age group of 25 â 35 years at the time of inception of our Club in 1994. The 25 Charter Members were selected with great care, and over a period of about 3 to 4 months. </p>
													<p> <span class="weight-600"> 4. </span> The Principal office-bearers of the Club for the first 2 Â½ years <br>January 1994 â June 1994	(Provisional Club)<br>July 1994 â June 1995	(First Year) <br>July 1995 â June 1996	(Second year)</p>
													<div class="table-bordered">
														<table class="table mb-0">
															<thead>
																<tr>
																	<th> President </th>
																	<th> Vice President		</th>
																	<th> Hon. Secretary	 </th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td> Rtn. Shamit A. Patel	 </td>
																	<td> Rtn. Snehal J. Parikh	 </td>
																	<td> Rtn. Kalpesh M. Shah </td>
																</tr>
															</tbody>
														</table>
													</div>
													<p> <span class="weight-600"> 5. </span> The Club Logo âKALA GHODAâ and the name for our Club Bulletin âSAYAJI SWARâ were suggested by Vice President Rtn. Snehal Parikh and his advertising agency TV ADS. </p>
													<p> <span class="weight-600"> 6. </span> Among the many Service Projects done in the First Year, 2 noteworthy (outstanding) Projects were:</p>
													<p> <span class="weight-600"> A. </span> Joint Participation with 3 Sister Rotary Clubs of Baroda in first-ever Pulse Polio Immunization (PPI) in Vadodara, which proved to be highly successful. Later on, the same model was used all over the country for administering Oral Polio Vaccine (Drops) to infants and young children. <br> In the first 4 rounds, majority of our Club Members and Anns have spent almost 8 hours (full day)- 9.00 AM â 5.00 PM â at the Polio Immunization Booths to create awareness and ensure maximum possible immunization. </p>
													<p> <span class="weight-600"> B. </span> Unique âPRINCE & PRINCESSâ of Sayaji Nagari Contest, where children from about 30 Schools of Vadodara participated. They went through 2 preliminary rounds before the Grand Finals at Mahatma Gandhinagar Gruh, which was witnessed by an audience of about 1000 persons. </p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</main>
		</div>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>