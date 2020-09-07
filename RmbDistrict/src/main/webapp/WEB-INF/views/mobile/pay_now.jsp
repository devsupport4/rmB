<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>  Pay Now  | Rotary Means Business Fellowship Bangalore  </title>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
		<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/mobile/images/favicon.ico">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script  src="<%= request.getContextPath() %>/resources/front/mobile/js/index.js"></script>		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>		
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>			
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/sidebar.js"></script>	
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/pay_now.js"></script>
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
		form {
	    	display: block;
	    	margin-top: 0em;
	    	box-sizing: border-box;
		}
		* {
		    -webkit-box-sizing: border-box;
		    -moz-box-sizing: border-box;
		    box-sizing: border-box;
		}
		.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
		    display: block;
		    max-width: 100%;
		    height: auto;
		}	
		img {
	    	border: 0;
		}
		a {
			text-decoration: none;
		}
		.box{
       /*  color: #fff; */
        padding: 20px;
        display: none;
        margin-top: 20px;
    }
	
	</style>
	<body ng-app="rcbs" ng-controller="payNowCtrl" ng-cloak ng-init="getEventDetailsForPay(<%= request.getParameter("eventid")%>,<%= session.getAttribute("memberid")%>)">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Pay Now </h2>
				</div>
				<div class = "mdl-grid">
					<div class="login mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone graybox">
						<div class="rcbs-logo">
							<img src="<%= request.getContextPath() %>/resources/front/mobile/images/405X75.png"/>
						</div>
						<div class="mdl-card mdl-shadow--6dp">
							<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
								<h2 class="mdl-card__title-text"> Pay Now </h2>
							</div>
							<div class="mdl-card__supporting-text" ng-show="!amount">
								<h4 style="color:green;font-size: 17px;"> You are registered for this event Successfully. Thank You</h4>
							</div>
							
							<div class="mdl-card__supporting-text" ng-show="amount">
									<div class="red col-md-12" ng-show="paymentstatus !='Pending'">
											<h4 style="color:green;font-size: 17px;">Please make payment to complete registration request.</h4>
									</div>
									<div class="col-md-12">
											<h4 style="font-size: 17px;">{{geteventdetailbyid.eventTitle}}</h4>
											<p class="comments">{{geteventdetailbyid.eventDate | date : 'dd/MM/yyyy'}} </p>
											<p class="comments">@{{geteventdetailbyid.eventVenue}}</p>
											<p>Amount : <b> {{geteventchargesbyid.currencyCode}} {{geteventchargesbyid.amount | number:2}}</b> </p>																	
											<br><br>
									</div>
									<div class="red col-md-12" ng-show="paymentstatus =='Pending'">
											<h4 style="color:green;font-size: 17px;"> We acknowledge your payment request. We get back to you soon. Thank you </h4>
									</div>
									<div ng-show="paymentstatus!='Pending'">
											<div>
												<!-- <div class="col-md-1"></div> -->
												<div class="col-md-9">
		        										<label ng-click="setValueRadio('show')"><input type="radio" name="colorRadio" value="red" checked="checked"> Bank Transfer</label>
		        										<label ng-click="setValueRadio('hide')"><input type="radio" name="colorRadio" value="green"> Online Pay</label><br>
		        								</div><br>
	    									</div><br>
	    								<div class="red col-md-12" ng-if="hidebankdetails!='hide'">
												<h6> Bank transfer detail for payment </h6>
												<!-- <p> Please make payment through NEFT using following bank information.</p> -->
												<p>A/c Holder Name: Rotary Means Business Fellowship Bangalore Alia RMBFB</p>
												<p>Account Number : 11040100313850</p>
												<p>IFSC Code      : FDRL0001104</p>
												<p>Bank Name and Address  :  Federal Bank Limited. Bangalore St. Mark's Road Baranch. No. 9 Halcyon Complex, St. Mark's road, Bangalore - 560001</p>
												<br><br>
											<div class="mdl-textfield mdl-js-textfield">
													<label>Payment Reference Number</label><br><br>
													<input type="text" id="paymentrefno" ng-model="paymentrefno" name="paymentrefno" class="mdl-textfield__input">
													<p ng-show="errorPaymentNo == 1" style="color: red; font-size: 14px;">{{msgPaymentNo}}</p>
											</div>
											
											<div class="col-md-3" style="padding-top: 4px;">
													<label></label><br><br>
													<button ng-click="savePaymentDetails(<%= session.getAttribute("memberid")%>,<%= request.getParameter("eventid")%>,paymentrefno,geteventchargesbyid.amount)" class="btn btn-md btn-success btn-block btn-signin" type="submit">Submit</button>
											</div>
										</div>
										<div class="col-md-4"></div>
										<div class="green box col-md-4 text-center" ng-show="<%= session.getAttribute("memberid")%>">
												<button class="btn btn-lg btn-success btn-block btn-signin" type="submit"><a style="color:black" href="<%=request.getContextPath() %>/billing_address?eid=<%= request.getParameter("eventid")%>">{{geteventchargesbyid.currencyCode}} {{geteventchargesbyid.amount | number:2}} Pay Now</a></button>
										</div><br>
											<div class="clearfix"> </div>
												<br>
										</div>
										<div class="row">
											<div class="col-md-4"></div>
												<div class="col-md-4 text-center"  ng-show="paymentstatus =='Pending'"><br>
													<button class="btn btn-md btn-success btn-block btn-signin" type="submit"><a style="color:black" href="<%=request.getContextPath() %>/"> Home</a> </button>
												</div><br>
												<div class="clearfix"> </div>
														<br>
										</div>
								</div>
						</div>
					</div>
				</div>
			</main>
		</div>
			<script>
			$(document).ready(function(){
	    	$('input[type="radio"]').click(function(){
	        var inputValue = $(this).attr("value");
	        var targetBox = $("." + inputValue);
	        $(".box").not(targetBox).hide();
	        $(targetBox).show();
	    	});
	    		    	
});
</script>				
	</body>
</html>