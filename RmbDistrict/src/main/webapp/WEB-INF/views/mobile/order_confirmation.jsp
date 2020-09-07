<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>  Payment Confirmation  | Rotary Means Business Fellowship Bangalore  </title>
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/order_confirmation.js"></script>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-153537496-1"></script>
        <script>
           window.dataLayer = window.dataLayer || [];
           function gtag(){dataLayer.push(arguments);}
           gtag('js', new Date());
           gtag('config', 'UA-153537496-1');
        </script>

		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">		
			<style>
	 body {
		font-family: 'Varela Round', sans-serif;
	}
	.modal-confirm {		
		color: #434e65;
		width: 525px;
	}
	.modal-confirm .modal-content {
		padding: 20px;
		font-size: 16px;
		border-radius: 5px;
		border: none;
		float:center;
	}
	.modal-confirm .modal-header {
		background: #e85e6c;
		border-bottom: none;   
        position: relative;
		text-align: center;
		margin: -20px -20px 0;
		border-radius: 5px 5px 0 0;
		padding: 35px;
	}
	.modal-confirm h4 {
		text-align: center;
		font-size: 36px;
		margin: 10px 0;
	}
	.modal-confirm .form-control, .modal-confirm .btn {
		min-height: 40px;
		border-radius: 3px; 
	}
	.modal-confirm .close {
        position: absolute;
		top: 15px;
		right: 15px;
		color: #fff;
		text-shadow: none;
		opacity: 0.5;
	}
	.modal-confirm .close:hover {
		opacity: 0.8;
	}
	.modal-confirm .icon-box {
		color: #fff;		
		width: 95px;
		height: 95px;
		display: inline-block;
		border-radius: 50%;
		z-index: 9;
		border: 5px solid #fff;
		padding: 15px;
		text-align: center;
	}
	.modal-confirm .icon-box i {
		font-size: 58px;
		margin: -2px 0 0 -2px;
	}
	.modal-confirm.modal-dialog {
		margin-top: 80px;
	}
	.pymnt
	{
	color:#fb9000;
	}
    .modal-confirm .btn {
        color: #fff;
        border-radius: 4px;
		background: #eeb711;
		text-decoration: none;
		transition: all 0.4s;
        line-height: normal;
		border-radius: 30px;
		margin-top: 10px;
		margin-bottom:20px;
		padding: 6px 20px;
		min-width: 150px;
        border: none;
    }
	.modal-confirm .btn:hover, .modal-confirm .btn:focus {
		background: #eda645;
		outline: none;
	}
	.trigger-btn {
		display: inline-block;
		margin: 100px auto;
	}	
		form {
	    	display: block;
	    	margin-top: 0em;
	    	box-sizing: border-box;
	    	align-text:center;
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
	
	</style>
	</head>
	

	<body ng-app="rcbs" ng-controller="orderConfirmationCtrl" ng-cloak ng-init="getOrderDetail('<%=session.getAttribute("ordernumber")%>')">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-dark">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18"> Payment Confirmation </h2>
				</div>
				<div class = "mdl-grid">
					<div class="login mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone graybox">
						<div class="mdl-card mdl-shadow--6dp">
							<div class="mdl-card__content">
								<form>
									<div class="modal-content" style="text-align: -webkit-center;" ng-if="'<%=session.getAttribute("orderStatus")%>' != 'Success'">
										<div class="modal-header">
											<h5 class="pymnt"><i class="fa fa-credit-card" aria-hidden="true"></i>&nbsp;&nbsp;Payment Failure</h5>
										</div>
										<div class="modal-body text-center">
											<p style="font-size: 24px;"><i class="fa fa-frown-o" aria-hidden="true"></i></p>
											<h6>Ooops!</h6>
											<p>However your payment was not successful but you can try again after sometime.</p>
											<br>
											<button style="margin-bottom: 20px;" ng-click="backToHome()" class="btn btn-success">Back to home</button><br>
										</div>
									</div>
									<div class="modal-content" style="text-align: -webkit-center;" ng-if="'<%=session.getAttribute("orderStatus")%>' == 'Success'">
										<div class="modal-header">
											<h5 class="pymnt"><i class="fa fa-credit-card" aria-hidden="true"></i>&nbsp;&nbsp;Payment Success</h5>
										</div>
										<div class="modal-body text-center">
											<p style="font-size: 24px;"><i class="fa fa-smile-o" aria-hidden="true"></i></p>
											<h6>Your payment has been received successfully.</h6>
											<p>Your payment of Rs. {{orderdetail.totalAmount | number:2}} is successful.<br>Please note your transaction id : <%=session.getAttribute("trackingid")%> for future reference,<br>Thank you for your payment.We are happy and delighted to serve you.</p>
											<br>
											<button style="margin-bottom: 20px;"  ng-click="backToHome()" class="btn btn-success">Back to home</button><br>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
			</main>
		</div>
			
	</body>
</html>