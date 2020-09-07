
<%
  /* This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction. */
%>
<%@ page import="java.io.*,java.util.*,com.ccavenue.security.*"%>
<html>
	<head>
		<title>Sub-merchant checkout page</title>
		<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	</head>
	<body>
			<%
				String accessCode= "AVTR91HD80CG26RTGC";		//Production Environment. Put in the Access Code in quotes provided by CCAVENUES.
				//String accessCode = "AVFZ03HD17BP48ZFPB"; 		//Test Environment	LOCAL HOST.	Put in the Access Code in quotes provided by CCAVENUES.
				//String accessCode= "AVLE85GF91AX03ELXA";		//ONLINE Test Environment. Put in the Access Code in quotes provided by CCAVENUES.

				String workingKey = "1ACC2D686E8FD30EED3333DC6AF36573";		//Production Environment		    Put in the 32 Bit Working Key provided by CCAVENUES.
				//String workingKey = "E203A93FDD328D78108C2EAEA67301D9"; 		//Test Environment	LOCAL HOST	    Put in the 32 Bit Working Key provided by CCAVENUES.
				//String workingKey = "05B72720DADEE7BB7782026673AC53FA";		//ONLINE Test Environment		    Put in the 32 Bit Working Key provided by CCAVENUES.

				Enumeration enumeration = request.getParameterNames();
				String ccaRequest = "", pname = "", pvalue = "";
				while (enumeration.hasMoreElements()) {
					pname = "" + enumeration.nextElement();
					pvalue = request.getParameter(pname);
					ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
				}
				String encRequest = "";
				try {
					AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
					encRequest = aesUtil.encrypt(ccaRequest);
				} catch (Exception e) {
					encRequest = null;
				}
			%>

		<form id="nonseamless" method="post" name="redirect" action="https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction"/>
		<!-- <form id="nonseamless" method="post" name="redirect" action="https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction" /> -->
			<input type="hidden" id="encRequest" name="encRequest" value="<%=encRequest%>">
			<input type="hidden" name="access_code" id="access_code" value="<%=accessCode%>">
			<input type="hidden" name="redirect_url" id="redirect_url" value="https://rmbbangalore.org/mobile/ccavResponseHandler">
			<input type="hidden" name="cancel_url" id="cancel_url" value="https://rmbbangalore.org/mobile/ccavResponseHandler">
			<script language='javascript'>
				document.redirect.submit();
			</script>
		</form>
	</body>
</html>
