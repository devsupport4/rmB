<div class="mdl-layout__drawer">
	<div class="mdl-logo">
		<a href=" <%= request.getContextPath() %>/">
			<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/405X75.png"/>
		</a>
	</div>
	
	<nav class = "mdl-navigation" ng-app="rcbs" ng-controller="sidebarCtrl">
	<%-- 	<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/rmbb_neo_conclave">RMBFB NEO Conclave</a> --%>
		<%-- <a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/service_projects"> Service Projects </a> --%>		
	<%-- 	<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/about_RMBB"> About RMBFB</a> --%>
	<%-- 	<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/news"> News</a> --%>
	<%-- 	<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/board_of_director"> Board of Directors </a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/meeting_info_location"> Meeting Location </a>
	 --%>	<%-- <a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/committee">  Committees members</a> --%>
		<%-- <a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/members_directory"> Members Directory </a> --%>		
		<%-- <a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/club_bulletins"> Club Bulletins </a> --%>
		
		
<%-- 	dd	<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/events"> Events</a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/upcoming_birthdays"> Upcoming birthdays</a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/business_categories"> Business Categories </a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/photo_albums"> Photo Albums </a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/#"> Video Gallery  </a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/active_members"> Active Members </a>
		 --%>
		
		
		<%-- <a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/birthdays_and_anniversaries"> Birthdays & Anniversaries </a>		
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/social_media"> Social Media </a>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/golf_tournament"> Golf Tournament </a> --%>
		<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/my_profile"> My Profile </a>
		
		<%if(session.getAttribute("memberid") != null) {%>			
			<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/Frontlogout" >Logout</a>
		<%} else {%>
			<a class = "mdl-navigation__link" href = "<%= request.getContextPath() %>/member_login"> Signin </a>
		
		<%}%>		
	</nav>
</div>