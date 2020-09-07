<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title> Photo Albums | Rotary Means Business Fellowship Bangalore </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">		
		<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel = "stylesheet"href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/mobile/css/swiper.min.css">
		<script src="<%=request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src='<%=request.getContextPath() %>/resources/front/mobile/js/owl.carousel.min.js'></script>
		<script src="<%=request.getContextPath() %>/resources/front/mobile/js/OwlCarousel.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/mobile/js/swiper.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/photo_albums.js"></script>
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
	<style type="text/css">
		body {
			font-family: Arial, sans-serif;
			background-size: cover;
			height: 100vh;
		}
		.box {
			width: 40%;
			margin: 0 auto;
			background: rgba(255,255,255,0.2);
			padding: 35px;
			border: 2px solid #fff;
			border-radius: 20px/50px;
			background-clip: padding-box;
			text-align: center;
		}
		.button {
			font-size: 1em;
			padding: 10px;
			color: #fff;
			border: 2px solid #06D85F;
			border-radius: 20px/50px;
			text-decoration: none;
			cursor: pointer;
			transition: all 0.3s ease-out;
		}
		.button:hover {
			background: #06D85F;
		}
		.overlays {
			position: fixed;
			top: 0;
			bottom: 0;
			left: 0;
			right: 0;
			background: rgba(0, 0, 0, 0.9);
			transition: opacity 500ms;
			visibility: hidden;
			opacity: 0;
		}
		.overlays:target {
			visibility: visible;
			opacity: 1;
			z-index: 1;
		}.popups {
			margin: 50px auto;
			padding: 20px;
			background: transparent;
			border-radius: 5px;
			width: 30%;
			position: relative;
			transition: all 5s ease-in-out;
		}
		.popups h2 {
			margin-top: 0;
			color: #333;
			font-family: Tahoma, Arial, sans-serif;
		}
		.popups .closes {
			position: absolute;
			top: 20px;
			right: 30px;
			transition: all 200ms;
			font-size: 30px;
			font-weight: bold;
			text-decoration: none;
			color: white;
		}
		.popups .closes:hover {
			color: #06D85F;
		}
		.popups .contents {
			
			overflow: auto;
		}
		@media screen and (max-width: 700px){
			.box{
				width: 100%;
			}
			.popups{
				width: 100%;
			}
			.imag {
			width: 100%;
		}
		}
		@media screen and (max-height: 350px){
			.box{
				width: auto;
				height: 100%;				
			}
			.popups{
				width: auto;
				height: 100%;
			}
			.imag {
				width:auto;
				height:100%;
			} 
			.contents {
				width: auto;
				height:100%;
			}
			.imgbox {
				width:auto;
				height:100%;
				padding-bottom: 60px;
			}
			.swiper-container {
				width:auto;
				height:100%;
			}
		}
		
		.mrg {margin-top: 50px;}
		h3 {
    		font-size: 17px !important;
   			line-height: 20px !important;
   			margin-top: 4px;
   			margin-bottom:15px;
   		}
	</style>
	<body class="homepage" ng-app="rcbs" ng-controller="albumCtrl">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Photo Albums &nbsp; <span class="pull-right"></span>  </h2>
				</div>
				<div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section id="photo-gallery">								  								
								<%-- <div class="col-xs-12">												
									<div class="pv-30 ph-20 service-block bordered shadow text-center call-action-style1 object-non-visible animated object-visible fadeInDownSmall " data-animation-effect="fadeInDownSmall" data-effect-delay="100">										
										<a class="preview" href=" #popup1" title=" ">
											<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front/mobile/images/pic6.jpg" alt="">
										</a>
										<div id="popup1" class="overlays">
											<div class="popups">.
												<a class="closes" href="#">&times;</a>
												<div class="contents">
													<div class="swiper-container">
														<div class="swiper-wrapper">
															<div class="swiper-slide">
																<div class="imgbox">
																	<img src="<%= request.getContextPath() %>/resources/front/mobile/images/pic6.jpg" class="imag">
																</div>
															</div>
															<c:forEach items="${albumOneImage}" var="albumimages" varStatus="status1">
															<c:if test="${album.albumId == albumimages.albumId && album.albumImageId != albumimages.albumImageId}">
															<div class="swiper-slide">
																<div class="imgbox">
																	<img src="<%= request.getContextPath() %>/resources/front/mobile/images/pic6.jpg" class="imag">
																</div>
															</div>
															</c:if>
															</c:forEach>																
														</div>
													</div>
												</div>
											</div>
										</div>											
										<c:forEach items="${albumimages}" var="albumimages" varStatus="status1">
											<c:if test="${album.albumId == albumimages.albumId && album.albumImageId != albumimages.albumImageId}">
												<a href="<%= request.getContextPath() %>/resources/front/mobile/images/pic6.jpg" ></a>
											</c:if>		
										</c:forEach>												
										<h3>Imagesse</h3>
									</div>													
								</div> --%>
							
								
								
								
								
								
								<c:forEach items="${albumOneImage}" var="albumOneImage" varStatus="status">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">												
										<div class="pv-30 ph-20 service-block bordered shadow text-center call-action-style1 object-non-visible animated object-visible fadeInDownSmall " data-animation-effect="fadeInDownSmall" data-effect-delay="100">										
											<a class="preview" href=" #popup${albumOneImage.albumId}" title="${albumOneImage.imageTitle} ">
												<img class="img-responsive" src="${albumOneImage.image}" alt="">
											</a>
											<div id="popup${albumOneImage.albumId}" class="overlays">
												<div class="popups">.
													<a class="closes" href="#">&times;</a>
													<div class="contents">
														<div class="swiper-container">
															<div class="swiper-wrapper">
																<div class="swiper-slide">
																	<div class="imgbox">
																		<img src="${albumOneImage.image}" class="imag">
																	</div>
																</div>
																<c:forEach items="${albumImages}" var="albumImages" varStatus="status1">
																<c:if test="${albumOneImage.albumId == albumImages.albumId && albumOneImage.albumId != albumImages.albumId}">
																<div class="swiper-slide">
																	<div class="imgbox">
																		<img src="${albumImages.image}" class="imag">
																	</div>
																</div>
																</c:if>
																</c:forEach>																
															</div>
														</div>
													</div>
												</div>
											</div>											
											<%-- <c:forEach items="${albumImages}" var="albumImages" varStatus="status1">
												<c:if test="${albumImages.albumId == albumOneImage.albumId && albumImages.albumId != albumOneImage.albumId}">
													<a href="${albumImages.image}" rel="prettyPhoto[${status.index+1}]" title="${albumImages.imageTitle}"></a>
												</c:if>		
											</c:forEach> --%>												
											<h3> ${albumOneImage.albumTitle}</h3>
										</div>													
									</div>
								</c:forEach>																	
						</section>
					</div>
				</div>
			</main>
		</div>	
		<script>
		    var swiper = new Swiper('.swiper-container', {
		      pagination: {
		        el: '.swiper-pagination',
		      },
		    });
		</script>	
	</body>
</html>