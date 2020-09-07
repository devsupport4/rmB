<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Home | Rotary Means Business Fellowship Bangalore    </title>
		
		<link rel="apple-touch-icon" href="<%= request.getContextPath() %>/resources/front1/images/icon/152X152.png">
		<link rel="apple-touch-icon" sizes="152x152" href="<%= request.getContextPath() %>/resources/front1/images/icon/152X152.png">
		<link rel="apple-touch-icon" sizes="180x180" href="<%= request.getContextPath() %>/resources/front1/images/icon/180X180.png">
		<link rel="apple-touch-icon" sizes="167x167" href="<%= request.getContextPath() %>/resources/front1/images/icon/167X167.png">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front1/images/favicon.png">
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front1/mobile/css/OwlCarousel.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front1/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/home.js"></script>		
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
	<style> 
		.modal-backdrop {
		    position: fixed;
		    top: 0;
		    right: 0;
		    bottom: 0;
		    left: 0;
		    z-index: auto !important;
		    background-color: #000;
		}
		.committee .bordercolor {
  	 	 	border-color: white;
		}
		.box-bord{
			box-shadow: 1px 0 10px #AAA;
		}
	</style>
	<body class="homepage" ng-app="rcbs" ng-controller="homeCtrl" ng-init="getCurrentDefaultYear()">
		<!-- Always shows a header, even in smaller screens. -->		
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content members-directory">
				<div class="page-content">
					<%-- <section class="hero--main">
						<div class="owl-carousel owl-theme carousel--cards">
						<a href="<%=request.getContextPath() %>/rmbv_neo_conclave">
							<div class="item content mdl-js-ripple-effect">
								
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/images/sliders/RMBV-NEO-Slider-for-MobileApp.jpg" alt="" />
									<span class="mdl-ripple"></span>
								
							</div>
							</a>
						</div>
					</section> --%>
					<div class="panel panel-default year-box">
						<div class="panel-body">
							<div class="col-sm-12 col-xs-12 wow fadeInDown animated" align="right">
								<div class="input-group" style="width:100%;">
									<div id="post-row" class="row">
										<div class="col-xs-12 col-sm-12 box-bord" id="new_status">
											<ul class="navbar-nav col-xs-12" id="post_header" role="navigation">
												<li style="list-style-type:none;" class="colr"><a href="#" class="post-color"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;RMBFB Wall </a></li>
											</ul>
											<form>
												<div class="col-xs-12 set-size" id="post_content" >
													<%-- <img alt="profile picture" class="col-sm-3 col-xs-3 hidden-sm hidden-xs" src="<%=request.getContextPath() %>/resources/front1/images/man1.jpg"> --%>
													<div class="textarea_wrap">
														<textarea class="form-control post-textarea col-xs-9 textareabox" rows="3" style="resize:none" placeholder="Post a comment, share a link, or upload a photo..." id="memberpost" ng-model="memberpost"></textarea>
													</div>
												</div>
												<div class="col-xs-12" id="post_footer">													
													<div class="col-xs-6 text-left col-padding" style="">																															
														<a class="btn btn-primary btnn btnview" href="<%=request.getContextPath() %>/wall_posts">View Comments</a>												
													</div>
													<div class="col-xs-6 text-right pr-0">
														<%if(session.getAttribute("memberid") == null)
														{%>																		
															<button class="btn btn-primary btnn btnpost" ng-click="showAlert()">Post</button>																	
														<%}
														else
														{%>																															
															<button class="btn btn-primary btnn btnpost" ng-click="createNewPost()">Post</button>																		
														<%}%>
													</div>
												</div>
											</form>
										</div>										
									</div>
									<%-- <div id="main feed" class="row" ng-repeat="item in getLastThreeMemberPost">
										<div class="col-sm-12 col-xs-12 box-bord mb-10" >
											<div id="stream-well" class="">
												<div class="panel panel-default wall-post-bg" >
													<div class="panel-body img-panel">																			
														<blockquote class="block">
															<p class="wordwrap">{{item.memberPost}}</p>
															<footer>{{item.memberFirstName}} {{item.memberLastName}}, {{item.createdDate}} </footer>
														</blockquote>																			
														<div class="post-btn-group">												
															<button class="btn-comment btn-md" ng-click="openComments(item.memberPostId)">
																<i class="fa fa-comment-o" aria-hidden="true"></i>
																Comments <span class="badge">{{item.commentCount}}</span>
															</button>																				
														</div>
														<div class="panel-footer post-reply comments" id="{{item.memberPostId}}" style="display: none;">
															<div class="comment" ng-repeat="item in postallcomments">
																<blockquote class="block">
																	<p class="wordwrap">{{item.postComment}}</p>
																	<footer>{{item.memberFirstName}} {{item.memberLastName}}, {{item.createdDate}} </footer>
																</blockquote>
															</div>
															<%if(session.getAttribute("memberid") != null)
															{%>								
															<div class="form-group input-group mb-5">																				
																<textarea class="form-control reply-textarea" rows="1" style="resize:none" placeholder="Post a comment..." ng-attr-id="{{'comment'+$index}}" ng-model="comment"></textarea>
																<span class="input-group-btn">
																	<button class="btn btn-default" type="button" ng-click="addComment(item.memberPostId, $index)">
																		<i class="fa fa-comment" aria-hidden="true"></i>
																	</button>
																</span>																			
															</div>
															<%}%>																	
														</div>
													</div>
												</div>
											</div>
										</div>
									</div><br> --%>
								</div>
							</div>
						</div>
					</div>
					<section class="list--grid">
						<ul class="list--grid__list">
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/service_projects">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/6.png"/>
									<h3 class="cat-title"> Service <br> projects </h3>
									
								</a>
							</li> --%>
						<%-- 	<li class="content mdl-js-ripple-effect">
								<%if(session.getAttribute("memberid") == null)
								{%>
								<a href="<%= request.getContextPath() %>/login?r=a">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/11.png"/>
									<h3 class="cat-title">Member Activities </h3>
								</a>
								<%} else { %>
								<a href="<%= request.getContextPath() %>/activities">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/11.png"/>
									<h3 class="cat-title">Member Activities </h3>
								</a>
								<%} %>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<%if(session.getAttribute("memberid") == null)
								{%>
								<a href="<%= request.getContextPath() %>/login?r=c">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/clube-info.png"/>
									<h3 class="cat-title"> Chapter Summary </h3>
								</a>
								<%} else { %>
								<a href="<%= request.getContextPath() %>/chapter_summary">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/clube-info.png"/>
									<h3 class="cat-title"> Chapter Summary </h3>
								</a>
								<%} %>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/board_of_director">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/3.png"/>
									<h3 class="cat-title">  Board of directors </h3>
									
								</a>
							</li>
							
							 --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/active_members">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/4.png"/>
									<h3 class="cat-title">Recently Active Members </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/members_directory">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/5.png"/>
									<h3 class="cat-title"> Members <br> directory </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/club_bulletins">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/7.png"/>
									<h3 class="cat-title"> CLUB BULLETINS </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/events">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/2.png"/>
									<h3 class="cat-title"> EVENTS </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/history">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/clube-info.png"/>
									<h3 class="cat-title"> History </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/photo_albums">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/8.png"/>
									<h3 class="cat-title"> Photo Albums </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/#">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/9.png"/>
									<h3 class="cat-title">  Video Gallery  </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/award">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/12.png"/>
									<h3 class="cat-title">  Awards </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/birthdays_and_anniversaries">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/13.png"/>
									<h3 class="cat-title">  Birthday & Anniversary </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/social_media">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/15.png"/>
									<h3 class="cat-title"> Social Media </h3>
									
								</a>
							</li> --%>
							<%-- <li class="content mdl-js-ripple-effect">
								<a href="<%= request.getContextPath() %>/golf_tournament">
									<span class="mdl-ripple"></span>
									<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front1/mobile/images/icons/16.png"/>
									<h3 class="cat-title">  RCBS Golf </h3>
									
								</a>
							</li> --%>
						</ul>
					</section>
				</div>
			</main>
		</div>
		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.6/owl.carousel.min.js'></script>
		<script src="<%= request.getContextPath() %>/resources/front1/mobile/js/OwlCarousel.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>

