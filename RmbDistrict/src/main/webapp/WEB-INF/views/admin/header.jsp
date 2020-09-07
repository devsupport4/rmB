<!-- Menu Start -->
	<script>
	    $(function()
	    		{
	        		$(".dropdown").hover(            
	                function()
	                {
	                    $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
	                    $(this).toggleClass('open');
	                    $('b', this).toggleClass("caret caret-up");                
	                },
	                function()
	                {
	                    $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
	                    $(this).toggleClass('open');
	                    $('b', this).toggleClass("caret caret-up");                
	                });
	        });
		        
	</script>
<!-- Menu End -->
		
<!-- <div> -->
	<div class="jumbotron">
		<div class="container text-left">
			<div class="row" style="margin-left: 0px;">
				<div class="col-xs-6 col-sm-4 col-lg-4">
					<a href="<%= request.getContextPath()%>/manageRmbDistrict/admin_home" style="text-decoration: none; color: black;"><h3>RMB-District 3060  </h3></a>
				</div>
				<%-- <div class="col-xs-6 col-sm-4 col-lg-4" ng-show="<%=session.getAttribute("roleId") %> == '1'">
				
					<select ng-model="fellowship_id" id="fellowship_id" name="fellowship_name"  class="form-control input-lg2">
						<option value=""selected>Select Fellowship Name</option>
						<option ng-repeat="items in getAllFellowshipNameList" value="{{items.fellowship_id}}">{{items.fellowship_name}}</option>
					</select>
				</div> --%>
			</div>
			
		</div>
	</div>
	<div class="header-inner text-center">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<nav class="navbar navbar-default" role="navigation">
					    <div class="navbar-header">
					    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					    		<span class="sr-only">Toggle navigation</span>
					    		<span class="icon-bar"></span>
					    		<span class="icon-bar"></span>
					    		<span class="icon-bar"></span>
					    	</button>
		    			</div>
		    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    				<ul class="nav navbar-nav">		    					
		    					<li class="dropdown">
		    						<a href="#">EVENTS <b class="caret"></b></a>
		    						<ul class="dropdown-menu">
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_events" onclick="javascript:window.location.reload();">Manage Events</a></li>
		    							<%-- <li><a href="<%= request.getContextPath()%>/manageRmbDistrict/event_response" onclick="javascript:window.location.reload();">Event Response</a></li> --%>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/eventRegistrationsResult" onclick="javascript:window.location.reload();">Event Registration list</a></li>
		    						</ul>		    						
		    					</li>		    	
		    					<!-- Menu for News -->
		    					<li class="dropdown">
		    						<a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_news" onclick="javascript:window.location.reload();">NEWS</a>   								    						
		    					</li>
		    					<li class="dropdown">
		    						<a href="#">MANAGE<b class="caret"></b></a>
		    						<ul class="dropdown-menu">
		    					
		    	                 <li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_clubs" onclick="javascript:window.location.reload();">CLUB LIST</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_fellowship" onclick="javascript:window.location.reload();">FELLOWSHIP</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/rotary_year" onclick="javascript:window.location.reload();">ROTARY YEAR</a></li>		
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_members" onclick="javascript:window.location.reload();">MEMBERS</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_slider" onclick="javascript:window.location.reload();">MANAGE SLIDER</a></li>					
		    									    							
		    							
		    								<%if(session.getAttribute("roleId").equals("1")) { %>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_admin" onclick="javascript:window.location.reload();">MANAGE ADMIN</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/members_committee" onclick="javascript:window.location.reload();">MEMBERS COMMITTEE</a></li>
		    							<%} %>
		    							<%-- <li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_awards" onclick="javascript:window.location.reload();">AWARDS & RECOGNITION</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_beneficiary_type" onclick="javascript:window.location.reload();">BENEFICIARY TYPE</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/board_of_directors" onclick="javascript:window.location.reload();">BOARD OF DIRECTORS</a></li>
		    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_business_transaction" onclick="javascript:window.location.reload();">BUSINESS TRANSACTION</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_members_meeting" onclick="javascript:window.location.reload();">MEMBERS MEETING</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_reference" onclick="javascript:window.location.reload();">REFERENCE</a></li>
		    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/business_categories" onclick="javascript:window.location.reload();">BUSINESS CATEGORIES</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_club_bulletins" onclick="javascript:window.location.reload();">CLUB BULLETINS</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_club_info" onclick="javascript:window.location.reload();">RMBFB INFO</a></li>
		    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/dues_and_billing" onclick="javascript:window.location.reload();">DUES & BILLING</a></li>		    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_members" onclick="javascript:window.location.reload();">MEMBERS</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/members_committee" onclick="javascript:window.location.reload();">MEMBERS COMMITTEE</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/payment_history" onclick="javascript:window.location.reload();">PAYMENT HISTORY</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/pending_payments" onclick="javascript:window.location.reload();">PENDING PAYMENTS</a></li>
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/photo_album" onclick="javascript:window.location.reload();">PHOTO ALBUM</a></li> --%>
		    							    							
		    							<%-- <li><a href="<%= request.getContextPath()%>/manageRmbf/service_project" onclick="javascript:window.location.reload();">SERVICE PROJECTS</a></li>
		    							<%-- <li><a href="<%= request.getContextPath()%>/manageRmbf/manage_service_project_beneficiary" onclick="javascript:window.location.reload();">SERVICE PROJECTS BENEFICIARY</a></li>		    									    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_social_media_links" onclick="javascript:window.location.reload();">SOCIAL MEDIA LINKS</a></li> --%>
		    						
		    						 </ul>		    						
		    					</li>
		    					<li class="dropdown">
		    						<a href="#" onclick="javascript:window.location.reload();">REPORTS <b class="caret"></b></a>
		    						<ul class="dropdown-menu">
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/chapters_summary" onclick="javascript:window.location.reload();">Chapter's Summary</a></li>
		    						</ul>		    						
		    					</li>
		    					<li class="dropdown">
		    						<a href="<%= request.getContextPath()%>/manageRmbDistrict/manage_orders" onclick="javascript:window.location.reload();">PAYMENTS</a>
		    					</li>
		    					<li class="dropdown">
		    						<a href="<%= request.getContextPath()%>/manageRmbDistrict/rmbb_mailer" onclick="javascript:window.location.reload();">MAILER</a>   								    						
		    					</li>
		    					<li class="dropdown">
		    						<a href="#">MASTER<b class="caret"></b></a>
		    						<ul class="dropdown-menu">
		    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/vocation_master" onclick="javascript:window.location.reload();">VOCATION MASTER</a></li>
		    						<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/readExcel" onclick="javascript:window.location.reload();">Import Excel</a></li>
		    						</ul>		    						
		    					</li>
		    				</ul>
		    				<ul class="nav navbar-nav navbar-right">
		    					<li class="dropdown">
		    						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("adminshowname") %><b class="caret"></b></a>
		    						<ul class="dropdown-menu">		    							
		    							<li><a href="<%= request.getContextPath()%>/manageRmbDistrict/adminlogout">Logout</a></li>
		    						</ul>
		    					</li>
		    				</ul>
		    			</div>
		    		</nav>
				</div>
			</div>
		</div>
	</div>
<!-- </div> -->