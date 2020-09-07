<html>
<head>
<script>
	window.onload = function() {
		var d = new Date().getTime();
		document.getElementById("tid").value = d;
	};
</script>
</head>
<!-- <body> -->
<body style="display:none;">
	<form method="post" name="customerData" action="ccavRequestHandler">
		<table width="40%" height="100" border='1' align="center">
			<caption>
				<font size="4" color="blue"><b>Integration Kit</b></font>
			</caption>
		</table>
		<table width="40%" height="100" border='1' align="center">
			<tr>
				<td>Parameter Name:</td>
				<td>Parameter Value:</td>
			</tr>
			<tr>
				<td colspan="2">Compulsory information</td>
			</tr>
			<tr>
				<td>TID	:</td><td><input type="text" name="tid" id="tid" readonly /></td>
			</tr>
			<tr>
				<td>Merchant Id</td>
				<td><input type="text" name="merchant_id" id="merchant_id" value="255196" /></td>
			</tr>
			<tr>
				<td>Order Id</td>
				<td><input type="text" name="order_id" value="<%=session.getAttribute("ordernumber") %>" /></td>
			</tr>
			<tr>
				<td>Currency</td>
				<td><input type="text" name="currency" value="INR" /></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount" value="<%= session.getAttribute("totalamount") %>" /></td>
			</tr>
			<tr>
				
				<td>Redirect URL</td>
				<td>
					<input type="text" name="redirect_url" value="https://rmbbangalore.org/mobile/ccavResponseHandler" />
					<!-- <input type="text" name="redirect_url" value="http://localhost:8080/rmbbangalore/mobile/ccavResponseHandler"/> -->
					<!-- <input type="text" name="redirect_url" value="https://ultrasmartsolutions.com/equitywalanew/ccavResponseHandler" /> -->
				</td>
			</tr>
			<tr>
				<td>Cancel URL</td>
				<td>
					<input type="text" name="cancel_url" value="https://rmbbangalore.org/mobile/ccavResponseHandler" />
					<!-- <input type="text" name="cancel_url" value="http://localhost:8080/rmbbangalore/mobile/ccavResponseHandler" /> -->
					<!-- <input type="text" name="cancel_url" value="https://ultrasmartsolutions.com/equitywalanew/ccavResponseHandler" /> -->
				</td>
			</tr>
			<tr>
				<td>Language</td>
				<td><input type="text" name="language" id="language" value="EN" /></td>
			</tr>
			<tr>
				<td colspan="2">Billing information(optional):</td>
			</tr>
			<tr>
				<td>Billing Name</td>
				<td><input type="text" name="billing_name" value="<%=session.getAttribute("billingname") %>" /></td>
			</tr>
			<tr>
				<td>Billing Address:</td>
				<td><input type="text" name="billing_address" value="<%=session.getAttribute("billingaddress1") %>, <%=session.getAttribute("billingaddress2") %>" /></td>
			</tr>
			<tr>
				<td>Billing City:</td>
				<td><input type="text" name="billing_city" value="<%=session.getAttribute("billingcityname") %>" /></td>
			</tr>
			<tr>
				<td>Billing State:</td>
				<td><input type="text" name="billing_state" value="<%=session.getAttribute("billingstatename") %>" /></td>
			</tr>
			<tr>
				<td>Billing Zip:</td>
				<td><input type="text" name="billing_zip" value="<%=session.getAttribute("billingpincode") %>" /></td>
			</tr>
			<tr>
				<td>Billing Country:</td>
				<td><input type="text" name="billing_country" value="<%=session.getAttribute("billingcountryname") %>" /></td>
			</tr>
			<tr>
				<td>Billing Tel:</td>
				<td><input type="text" name="billing_tel" value="<%=session.getAttribute("billingmobileno") %>" /></td>
			</tr>
			<tr>
				<td>Billing Notes:</td>
				<td><input type="text" name="billing_notes" value="" /></td>
			</tr>
			<tr>
				<td>Billing Email:</td>
				<td><input type="text" name="billing_email" value="<%=session.getAttribute("billingemail") %>" /></td>
			</tr>
			<tr>
				<td colspan="2">Shipping information(optional):</td>
			</tr>
			<tr>
				<td>Shipping Name</td>
				<td><input type="text" name="delivery_name" value="<%=session.getAttribute("deliveryname") %>" /></td>
			</tr>
			<tr>
				<td>Shipping Address:</td>
				<td><input type="text" name="delivery_address" value="<%=session.getAttribute("deliveryaddress1") %> , <%=session.getAttribute("deliveryaddress2") %>" /></td>
			</tr>
			<tr>
				<td>Shipping City:</td>
				<td><input type="text" name="delivery_city" value="<%=session.getAttribute("deliverycityname") %>" /></td>
			</tr>
			<tr>
				<td>Shipping State:</td>
				<td><input type="text" name="delivery_state" value="<%=session.getAttribute("deliverystatename") %>" /></td>
			</tr>
			<tr>
				<td>Shipping Zip:</td>
				<td><input type="text" name="delivery_zip" value="<%=session.getAttribute("deliverypincode") %>" /></td>
			</tr>
			<tr>
				<td>Shipping Country:</td>
				<td><input type="text" name="delivery_country" value="<%=session.getAttribute("deliverycountryname") %>" /></td>
			</tr>
			<tr>
				<td>Shipping Tel:</td>
				<td><input type="text" name="delivery_tel" value="<%=session.getAttribute("deliverymobileno") %>" /></td>
			</tr>
			<tr>
				<td>Shipping Email:</td>
				<td><input type="text" name="delivery_email" value="<%=session.getAttribute("deliveryemail") %>" /></td>
			</tr>
			<tr>
				<td>Merchant Param1</td>
				<td><input type="text" name="merchant_param1" value="additional Info." /></td>
			</tr>
			<tr>
				<td>Merchant Param2</td>
				<td><input type="text" name="merchant_param2" value="additional Info." /></td>
			</tr>
			<tr>
				<td>Merchant Param3</td>
				<td><input type="text" name="merchant_param3" value="additional Info." /></td>
			</tr>
			<tr>
				<td>Merchant Param4</td>
				<td><input type="text" name="merchant_param4" value="additional Info." /></td>
			</tr>
			<tr>
				<td>Merchant Param5</td>
				<td><input type="text" name="merchant_param5" value="additional Info." /></td>
			</tr>
			<tr>
				<td>Promo Code:</td>
				<td><input type="text" name="promo_code" value=""/></td>
			</tr>
			<tr>
				<td>Customer Id:</td>
				<td><input type="text" name="customer_identifier" value=""/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<!-- <input type="submit" value="Checkout"> -->
					<script language='javascript'>document.customerData.submit();</script>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
