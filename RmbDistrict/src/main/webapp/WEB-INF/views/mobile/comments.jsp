<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Business Categories | Rotary Means Business Fellowship Bangalore  </title>
		<link href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css" rel="stylesheet">
		<link rel="icon" href="<%=request.getContextPath()%>/resources/admin/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<%-- <link href="<%=request.getContextPath() %>/resources/front/css/responsive.css" rel="stylesheet"> --%>
		<link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700" rel="stylesheet">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">		
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/font-awesome-4.7.0/css/font-awesome.min.css"> 
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/mobile/images/favicon.ico">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/profile.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/css/wall.css">		
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/main.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/wow.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.prettyPhoto.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/jquery.isotope.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/64754/autosize.min.js'></script>
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
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18">Comments</h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="members-directory">
							<div class="page-content">															
								<div class="panel panel-default year-box">
									<div class="panel-body">
										<div class="col-sm-12 col-xs-12 wow fadeInDown animated" align="right">
											<div class="input-group" style="width:100%;">
												<div id="post-row" class="row">
													<div class="col-xs-12 col-sm-12 box-bord" id="new_status">
														<ul class="navbar-nav col-xs-12" id="post_header" role="navigation">
															<li style="list-style-type:none;" class="colr"><a href="#" class="post-color"><span class="glyphicon glyphicon-pencil"></span> Post Your Message to Wall </a></li>
														</ul>
														<form>
															<div class="col-xs-12 set-size" id="post_content" >
																<%-- <img alt="profile picture" class="col-sm-3 col-xs-3 hidden-sm hidden-xs" src="<%=request.getContextPath() %>/resources/front/images/man1.jpg"> --%>
																<div class="textarea_wrap">
																	<textarea class="form-control post-textarea col-xs-9 textareabox" rows="3" style="resize:none" placeholder="Post a comment, share a link, or upload a photo..." id="memberpost" ng-model="memberpost"></textarea>
																</div>
															</div>
															<div class="col-xs-12" id="post_footer">
																<div class="col-xs-12 text-right">
																	<%if(session.getAttribute("memberid") == null)
																	{%>																		
																		<button class="btn btn-primary btnn" ng-click="showAlert()">Post</button>																	
																	<%}
																	else
																	{%>																		
																		<button class="btn btn-primary btnn" ng-click="createNewPost()">Post</button>																		
																	<%}%>
																</div>
															</div>
														</form>
													</div>
												</div><br>
												<div id="main feed" class="row" ng-repeat="item in getLastThreeMemberPost">
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
												</div><br>
											</div><BR>
										</div>
									</div>
								</div>
																										
							</div>
						</section>					
					</div>
				</div>
			</main>
		</div>		
	</body>
</html>