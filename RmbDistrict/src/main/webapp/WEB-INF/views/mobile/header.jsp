<!--<script>
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function()
	{
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	ga('create', 'UA-44009873-1', 'rmbv.co.in');
	ga('send', 'pageview');
</script>-->

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-153537496-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-153537496-1');
</script>

<script type="text/javascript" >
	$(document).ready(function()
	{
		$(".account").click(function()
		{
			var X=$(this).attr('id');
			if(X==1)
			{
				$(".submenu").hide();
				$(this).attr('id', '0');
			}
			else
			{
				$(".submenu").show();
				$(this).attr('id', '1');
			}
		});
		//Mouse click on sub menu
		$(".submenu").mouseup(function()
		{
			return false
		});
		//Mouse click on my account link
		$(".account").mouseup(function()
		{
			return false
		});
		//Document Click
		$(document).mouseup(function()
		{
			$(".submenu").hide();
			$(".account").attr('id', '');
		});
	});
</script>
<header class="mdl-layout__header" id="header" ng-app="rcbs" ng-controller="sidebarCtrl">
	<div class="mdl-layout__header-row">
		<span class="mdl-layout-title logo"><a href="<%= request.getContextPath() %>/"> RMBFB </a></span>
		<div class="mdl-layout-spacer">
			
		<%if(session.getAttribute("memberid") != null) {%>
			<button id="demo-menu-lower-right" class="mdl-button mdl-js-button mdl-button--icon">
				<i class="material-icons"> insert_emoticon </i>
			</button>
			<ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="demo-menu-lower-right">
				<a href="<%= request.getContextPath() %>/my_profile"><li class="mdl-menu__item">Manage Profile</li></a>
				<a href="<%= request.getContextPath() %>/change_password"><li class="mdl-menu__item">Change Password</li></a>
				<%-- <a href="<%= request.getContextPath() %>/chapter_summary"><li class="mdl-menu__item">Chapter Summary</li></a>
				<a href="<%= request.getContextPath() %>/member_reference"><li class="mdl-menu__item">References</li></a>
				<a href="<%= request.getContextPath() %>/member_business_transaction"><li class="mdl-menu__item">Business Transactions</li></a>
				<a href="<%=request.getContextPath() %>/member_meeting"><li class="mdl-menu__item"> Meetings</li> </a> --%>
				<a href="<%=request.getContextPath() %>/order_history"><li class="mdl-menu__item"> My Payments</li> </a>
				<a href="<%=request.getContextPath() %>/Frontlogout" ng-click="Frontlogout()"><li class="mdl-menu__item">Logout</li></a>
			</ul>
		<%} else {%>
			<a href="<%= request.getContextPath() %>/login?r=l"  id="demo-menu-lower-right" class="mdl-button mdl-js-button mdl-button--icon">
				<i class="material-icons"> sentiment_very_dissatisfied </i>
			</a>		
		<%}%>		
		</div>
	</div>
</header>