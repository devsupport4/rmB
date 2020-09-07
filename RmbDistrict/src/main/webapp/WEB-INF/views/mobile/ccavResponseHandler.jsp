<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*,com.ccavenue.security.*"%>
<% System.out.println("===========================>files imported"); %>
<html>
	<head>
	<script>
	console.log("inside ccavenue head");</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Response Handler</title>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<%if(session.getAttribute("sitepreference").toString().equalsIgnoreCase("MOBILE")){ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>
		<%}else{ %>
			<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<%} %>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/ccavresponse.js"></script>
	</head>
	<body ng-app="rcbs" ng-controller="ccAvResCtrl" style="display: none;">
	<script>
	console.log("inside ccavenue body");</script>
	<% System.out.println("===========================> inside body"); %>
	<!-- <body ng-app="rcbs" ng-controller="ccAvResCtrl"> -->
	<%
	  //String workingKey = "E203A93FDD328D78108C2EAEA67301D9"; //Test Environment	LOCAL HOST	32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
	  //String workingKey = "05B72720DADEE7BB7782026673AC53FA"; //ONLINE Test Environment	32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
	  String workingKey = "1ACC2D686E8FD30EED3333DC6AF36573";			//Production Environment		32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		System.out.println("working key================>"+workingKey);
	  String encResp = request.getParameter("encResp");
	  System.out.println("encResp ================>"+encResp);
	  AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
	  String decResp = aesUtil.decrypt(encResp);
	  System.out.println("decResp ================>"+decResp);
	  StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
	  System.out.println("tokenizer ================>"+tokenizer);
	  Hashtable hs = new Hashtable();
	  String pair = null, pname = null, pvalue = null;
	  while (tokenizer.hasMoreTokens()) {
	    pair = (String) tokenizer.nextToken();
	    if (pair != null) {
	      StringTokenizer strTok = new StringTokenizer(pair, "=");
	      pname = "";
	      pvalue = "";
	      if (strTok.hasMoreTokens()) {
	        pname = (String) strTok.nextToken();
	        if (strTok.hasMoreTokens())
	          pvalue = (String) strTok.nextToken();
	        hs.put(pname, pvalue);
	      }
	    }
	  }	  
	  String order_id = (String) hs.get("order_id");	 
	  System.out.println("order_id================>"+order_id);
	  String bank_ref_number = (String) hs.get("bank_ref_no");	  
	  String order_status = (String) hs.get("order_status");
	  String failure_message = (String) hs.get("failure_message");
	  String payment_mode = (String) hs.get("payment_mode");	  
	  String card_name = (String) hs.get("card_name");	  
	  String tracking_id = (String) hs.get("tracking_id");
	  String status_code = (String) hs.get("status_code");
	  String status_message = (String) hs.get("status_message");
	  String currency = (String) hs.get("currency");
	%>
	<center>
		<font size="4" color="blue"><b>Response Page</b></font>
		<table border="1">
			<%
			  Enumeration enumeration = hs.keys();
			  while (enumeration.hasMoreElements()) {
			    pname = "" + enumeration.nextElement();
			    pvalue = "" + hs.get(pname);
			%>
			<tr>
				<td><%=pname%></td>
				<td><%=pvalue%></td>
			</tr>
			<%
			  }
			%>
		</table>
	</center>

	<form>
		<input type="hidden" ng-model="orderid" ng-init="orderid = '<%=order_id%>'">
		<input type="hidden" ng-model="trackingid" ng-init="trackingid = <%=tracking_id%>">
		<input type="hidden" ng-model="bankrefnumber" ng-init="bankrefnumber = '<%=bank_ref_number%>'">
		<input type="hidden" ng-model="orderstatus" ng-init=" orderstatus = '<%=order_status%>'">
		<input type="hidden" ng-model="failuremessage" ng-init="failuremessage = '<%=failure_message%>'">
		<input type="hidden" ng-model="paymentmode" ng-init="paymentmode = '<%=payment_mode%>'">
		<input type="hidden" ng-model="cardname" ng-init="cardname = '<%=card_name%>'">
		<input type="hidden" ng-model="statuscode" ng-init="statuscode = '<%=status_code%>'">
		<input type="hidden" ng-model="statusmessage" ng-init="statusmessage = '<%=status_message%>'">
		<input type="hidden" ng-model="currency" ng-init="currency = '<%=currency%>'">
		<input type="submit" value="Submit" ng-init="ccavenueresponse()">		
	</form>
</body>
</html>