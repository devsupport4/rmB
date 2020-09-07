<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Add Member</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/upload-img/style.css" media="all" rel="stylesheet" type="text/css" />		 		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.maskedinput.js" type="text/javascript"></script>

		<style>			
			label {
			    display: inline-block;
			    max-width: 100%;
			    margin-bottom: 5px;
			    font-weight: 400;
			}
		</style>
		<script type="text/javascript">
			jQuery(function($){
				   $("#joiningdate").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#dateofbirth").mask("99/99/9999",{placeholder:"DD/MM/YYYY"});
				   $("#phone").mask("(999) 999-9999");
				   $("#tin").mask("99-9999999");
				   $("#ssn").mask("999-99-9999");
				});
		</script>
			
	</head>	
	<%if(request.getParameter("memberid") != null)
	{ %>
	<body ng-app="rcbs" ng-controller="memberCtrl" ng-init="getpayment(<%= request.getParameter("memberid")%>)" ng-cloak>
	<%}
	else
	{%>
	<body ng-app="rcbs" ng-controller="memberCtrl" ng-init="getpayment(<%= session.getAttribute("memberid")%>)" ng-cloak>
	<%} %>
		<%@include file="header.jsp" %>	
		<section>
		<div class="container">
			<div class="row">
				<div class="board">
					<div class="board-inner">
						<ul class="nav nav-tabs" id="myTab">
							<div class="liner"></div>
							<li class="disabled">
								<a href="#membership" data-toggle="tab" title="MEMBERSHIP">
									<span class="round-tabs one">
										<i class="glyphicon glyphicon-home"></i>
									</span>
								</a>
							</li>
							<li class="disabled">
								<a href="#" data-toggle="tab" title="CONTACT DETAILS">
									<span class="round-tabs two">
										<i class="glyphicon glyphicon-gift"></i>
									</span>
								</a>
							</li>
							<li class="disabled">
								<a href="#spouse" data-toggle="tab" title="FAMILY DETAILS">
									<span class="round-tabs three">
										<i class="glyphicon glyphicon-comment"></i>
									</span>
								</a>
							</li>							
							<li class="disabled">
								<a href="#references" data-toggle="tab" title="REFERENCES">
									<span class="round-tabs five">
										<i class="glyphicon glyphicon-ok"></i>
									</span>
								</a>
							</li>
							<li class="active">
								<a href="#payments" data-toggle="tab" title="PAYMENTS">
									<span class="round-tabs four">
										<i class="glyphicon glyphicon-comment"></i>
									</span>
								</a>
							</li>
						</ul>
					</div>
					<div class="tab-content">
						<div class="tab-pane fade in active" id="references">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="border-top: 2px solid blue; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #e2e2e2), color-stop(1, #fafafa));">
										<div class="row" style="color: #333;">
											<div class="col-md-2">
												<div class="panel-title2"> Payments  </div>
											</div>
											<div class="col-md-4">
												<input type="text" value="{{memberfirstname}} {{membermiddlename}} {{memberlastname}}" disabled="disabled" class="form-control input-lg2" placeholder="Old Membership No"/>
											</div>
											<div class="col-md-2">
												<input type="text" disabled="disabled" ng-model="membershipnumber" class="form-control input-lg2" placeholder="Membership No" data-toggle="tooltip" title="New Membership No." />
											</div>											
											<div class="col-md-2 text-right">
												<button data-toggle="modal" data-target="#addPaymentModal" class="btn btn-primary right-block"> ADD PAYMENT INFO</button>
											</div>
										</div>
									</div>
									<div style="padding: 1px 0px 0px 0px;" class="panel-body">
										<div class="table-responsive">   
											<table class="table">
												<thead style="font-weight: 600; background-color: whitesmoke;">
													<tr>
														<th>No.</th>
														<th class="text-right">Trx. Amt.</th>
														<th class="text-right">Trx. Chg.</th>
														<th class="text-right">Total Amount</th>
														<th>Payment Type</th>
														<th>Cheque Date</th>
														<th>Trx. Date</th>
														<th>Comments</th>
														<th>Actions</th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat = "item in getpayments" style="cursor: pointer; cursor: hand;">
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{$index + 1}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)" class="text-right">&#8377; {{item.transactionAmount | currency : "" : 2}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)" class="text-right">&#8377; {{item.transactionCharges | currency : "" : 2}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)" class="text-right">&#8377; {{item.amountCharges | currency : "" : 2}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.paymentType}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.chequeDate | date : 'dd/MM/yyyy'}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.transactionDate | date : 'dd/MM/yyyy'}}</td>
														<td data-toggle="modal" data-target="#editPaymentModal" ng-click="getpaymentdetail(item.paymentId)">{{item.comments}}</td>
														<td>
															<ul class="pagination" style="display: flex; margin: 0px 0;">
																<li data-placement="top" data-toggle="tooltip" title="Delete"><a ng-click="deletepayment(item.paymentId)" href="#" style="background-color: #c9302c; color: #fff;" data-title="Delete"><i class="glyphicon glyphicon-trash"></i></a></li>
															</ul>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<%if(request.getParameter("memberid") != null)
										{ %>
										<a href="" ng-click="redirectreference(membercategoryid)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand;">Previous</a>
										<%}
										else
										{%>
										<a href="" ng-click="redirectreference(membercategoryid)" style="float: right; padding: 10px 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 15px; cursor: pointer; cursor: hand;">Previous</a>
										<%} %>
									</div>
									<div class="col-md-10">
										<div style="padding: 1px 10px 0px 0px;  margin-top: -20px;">
											<nav aria-label="...">
												<ul class="pager">													
													<li class="next"><a href="<%=request.getContextPath() %>/manageRmbb/manage_members">Next</a></li>
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<div class="modal fade" id="addPaymentModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form ng-submit="addpayment()">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
						<h3 class="modal-title" id="lineModalLabel"> ADD PAYMENT INFO </h3>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Payment Type<font color="red" size="3">*</font></label>
									<select class="form-control input-lg2" id="paymenttype" name="paymenttype" ng-model="paymenttype">
										<option value="">Select Payment Type</option>
										<option value="Cheque">Cheque</option>
										<option value="Demand Draft">Demand Draft</option>
										<option value="NEFT">NEFT</option>
										<option value="RTGS">RTGS</option>
										<option value="Wire Transfer">Wire Transfer</option>
										<option value="Cash">Cash</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Cheque' || paymenttype == 'Demand Draft'">
							<div class="col-md-4">
								<div class="form-group">
									<label>Bank Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="banknamecheque" name="banknamecheque" ng-model="$parent.banknamecheque" capitalize-first>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Branch Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="branchnamecheque" name="branchnamecheque" ng-model="$parent.branchnamecheque" capitalize-first>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Bank Account Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="accountnumbercheque" name="accountnumbercheque" ng-model="$parent.accountnumbercheque">
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Cheque'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Cheque Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="chequenumber" name="chequenumber" ng-model="$parent.chequenumber">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Cheque Date<font color="red" size="3">*</font></label>
									<div class="row">
										<div class="col-md-4">
											<select id="chequedate" name="chequedate" ng-model="$parent.chequedate" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="chequemonth" name="chequemonth" ng-model="$parent.chequemonth" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<div class="col-md-4">
											<input type="text" id="chequeyear" name="chequeyear" ng-model="$parent.chequeyear" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Demand Draft'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Demand Draft Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="demanddraftnumber" name="demanddraftnumber" ng-model="$parent.demanddraftnumber">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Demand Draft Date<font color="red" size="3">*</font></label>
									<div class="row">
										<div class="col-md-4">
											<select id="demanddraftdate" name="demanddraftdate" ng-model="$parent.demanddraftdate" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="demanddraftmonth" name="demanddraftmonth" ng-model="$parent.demanddraftmonth" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<div class="col-md-4">
											<input type="text" id="demanddraftyear" name="demanddraftyear" ng-model="$parent.demanddraftyear" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'NEFT' || paymenttype == 'RTGS' || paymenttype == 'Wire Transfer'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Bank Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="banknameneft" name="banknameneft" ng-model="$parent.banknameneft" capitalize-first>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Branch Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="branchnameneft" name="branchnameneft" ng-model="$parent.branchnameneft" capitalize-first>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'NEFT' || paymenttype == 'RTGS' || paymenttype == 'Wire Transfer'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Bank Account Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="accountnumberneft" name="accountnumberneft" ng-model="$parent.accountnumberneft">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Transaction / Reference Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="transactionnumberneft" name="transactionnumberneft" ng-model="$parent.transactionnumberneft">
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Cash'">
							<div class="col-md-5">
								<div class="form-group">
									<label>Transaction / Reference #<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="transactionnumbercash" name="transactionnumbercash" ng-model="$parent.transactionnumbercash">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Contact Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="contactnumbercash" name="contactnumbercash" ng-model="$parent.contactnumbercash">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Payment Place<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="paymentplace" name="paymentplace" ng-model="$parent.paymentplace" capitalize-first>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Other'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Transaction / Reference Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="transactionnumberother" name="transactionnumberother" ng-model="$parent.transactionnumberother">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Contact Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="contactnumberother" name="contactnumberother" ng-model="$parent.contactnumberother">
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Transaction Date</label>
									<div class="row">
										<div class="col-md-4">
											<select id="transactiondate" name="transactiondate" ng-model="$parent.transactiondate" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="transactionmonth" name="transactionmonth" ng-model="$parent.transactionmonth" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<div class="col-md-4">
											<input type="text" id="transactionyear" name="transactionyear" ng-model="$parent.transactionyear" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Paid To Whom</label>
									<input type="text" class="form-control input-lg2"  id="paidtowhom" name="paidtowhom" ng-model="paidtowhom" capitalize-first>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Transaction Amount<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="transactionamount" name="transactionamount" ng-model="transactionamount" ng-keyup="calculateamountcharges()">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="sel1">Currency<font color="red" size="3">*</font></label>
									<select class="form-control input-lg2" id="currencyidtransactionamount" name="currencyidtransactionamount" ng-model="currencyidtransactionamount" ng-options="item.currencyId as item.currencyName for item in currencies" ng-init="currencyidtransactionamount = 1">
										<option value="">Select Currency</option>
										<option ng-repeat="item in currencies" value="{{item.currencyId}}">{{item.currencyName}}</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Transaction Charges<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="transactioncharges" name="transactioncharges" ng-model="transactioncharges" ng-keyup="calculateamountcharges()">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="sel1">Currency<font color="red" size="3">*</font></label>
									<select class="form-control input-lg2" id="currencyidtransactioncharges" name="currencyidtransactioncharges" ng-model="currencyidtransactioncharges" ng-options="item.currencyId as item.currencyName for item in currencies" ng-init="currencyidtransactioncharges = 1">
										<option value="">Select Currency</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Comments</label>
									<textarea class="form-control input-lg2" id="comments" name="comments" ng-model="comments" capitalize-first></textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Image</label>
									<input type="file" id="file" name="file" multiple class="form-control input-lg2"/><br>
									<p style="font-size:13px;margin-top:-10px;">Upload maximum 1024 * 768 size <br> image for better appereance</p>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Amount Charges(INR)<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="amountcharges" name="amountcharges" ng-model="amountcharges" Readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="btn-group btn-group-justified" role="group" aria-label="group button">
							<div class="btn-group" role="group">
								<input type="submit" class="btn btn-success" value="Save">
							</div>
							<div class="btn-group" role="group">
								<button type="button" class="btn btn-primary" data-dismiss="modal"  role="button">Close</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="editPaymentModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form ng-submit="editpayment(paymentid)">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span><span class="sr-only">Close</span></button>
						<h3 class="modal-title" id="lineModalLabel"> Update PAYMENT INFO </h3>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Payment Type<font color="red" size="3">*</font></label>
									<select class="form-control input-lg2" id="paymenttypeedit" name="paymenttypeedit" ng-model="paymenttypeedit">
										<option value="">---Select Payment Type---</option>
										<option value="Cheque">Cheque</option>
										<option value="Demand Draft">Demand Draft</option>
										<option value="NEFT">NEFT</option>
										<option value="RTGS">RTGS</option>
										<option value="Wire Transfer">Wire Transfer</option>
										<option value="Cash">Cash</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttypeedit == 'Cheque' || paymenttypeedit == 'Demand Draft'">
							<div class="col-md-4">
								<div class="form-group">
									<label>Bank Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="banknamechequeedit" name="banknamechequeedit" ng-model="$parent.banknamechequeedit" capitalize-first>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Branch Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="branchnamechequeedit" name="branchnamechequeedit" ng-model="$parent.branchnamechequeedit" capitalize-first>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Bank Account Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="accountnumberchequeedit" name="accountnumberchequeedit" ng-model="$parent.accountnumberchequeedit">
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttypeedit == 'Cheque'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Cheque Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="chequenumberedit" name="chequenumberedit" ng-model="$parent.chequenumberedit">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Cheque Date<font color="red" size="3">*</font></label>
									<div class="row">
										<div class="col-md-4">
											<select id="chequedateedit" name="chequedateedit" ng-model="$parent.chequedateedit" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="chequemonthedit" name="chequemonthedit" ng-model="$parent.chequemonthedit" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<div class="col-md-4">
											<input type="text" id="chequeyearedit" name="chequeyearedit" ng-model="$parent.chequeyearedit" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttypeedit == 'Demand Draft'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Demand Draft Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="demanddraftnumberedit" name="demanddraftnumberedit" ng-model="$parent.demanddraftnumberedit">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Demand Draft Date<font color="red" size="3">*</font></label>
									<div class="row">
										<div class="col-md-4">
											<select id="demanddraftdateedit" name="demanddraftdateedit" ng-model="$parent.demanddraftdateedit" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="demanddraftmonthedit" name="demanddraftmonthedit" ng-model="$parent.demanddraftmonthedit" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<div class="col-md-4">
											<input type="text" id="demanddraftyearedit" name="demanddraftyearedit" ng-model="$parent.demanddraftyearedit" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttypeedit == 'NEFT' || paymenttypeedit == 'RTGS' || paymenttypeedit == 'Wire Transfer'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Bank Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="banknameneftedit" name="banknameneftedit" ng-model="$parent.banknameneftedit" capitalize-first>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Branch Name<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="branchnameneftedit" name="branchnameneftedit" ng-model="$parent.branchnameneftedit" capitalize-first>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttypeedit == 'NEFT' || paymenttypeedit == 'RTGS' || paymenttypeedit == 'Wire Transfer'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Bank Account Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="accountnumberneftedit" name="accountnumberneftedit" ng-model="$parent.accountnumberneftedit">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Transaction / Reference Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="transactionnumberneftedit" name="transactionnumberneftedit" ng-model="$parent.transactionnumberneftedit">
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Cash'">
							<div class="col-md-5">
								<div class="form-group">
									<label>Transaction / Reference #<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="transactionnumbercashedit" name="transactionnumbercashedit" ng-model="$parent.transactionnumbercashedit">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Contact Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="contactnumbercashedit" name="contactnumbercashedit" ng-model="$parent.contactnumbercashedit">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Payment Place<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="paymentplaceedit" name="paymentplaceedit" ng-model="$parent.paymentplaceedit" capitalize-first>
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row" ng-show="paymenttype == 'Other'">
							<div class="col-md-6">
								<div class="form-group">
									<label>Transaction / Reference Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2" id="transactionnumberotheredit" name="transactionnumberotheredit" ng-model="$parent.transactionnumberotheredit">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Contact Number<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="contactnumberotheredit" name="contactnumberotheredit" ng-model="$parent.contactnumberotheredit">
								</div>
							</div>
						</div>
						<div class="space15"></div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Transaction Date</label>
									<div class="row">
										<div class="col-md-4">
											<select id="transactiondateedit" name="transactiondateedit" ng-model="$parent.transactiondateedit" class="form-control input-lg2">
												<option value="">Date</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
										</div>
										<div class="col-md-4">
											<select id="transactionmonthedit" name="transactionmonthedit" ng-model="$parent.transactionmonthedit" class="form-control input-lg2">
												<option value="">Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<div class="col-md-4">
											<input type="text" id="transactionyearedit" name="transactionyearedit" ng-model="$parent.transactionyearedit" class="form-control input-lg2" placeholder="Year">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Paid To Whom</label>
									<input type="text" class="form-control input-lg2"  id="paidtowhomedit" name="paidtowhomedit" ng-model="paidtowhomedit" capitalize-first>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Transaction Amount<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="transactionamountedit" name="transactionamountedit" ng-model="transactionamountedit" ng-keyup="calculateamountchargesedit()">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="sel1">Currency<font color="red" size="3">*</font></label>
									<select id="currencyidtransactionamountedit" name="currencyidtransactionamountedit" ng-model="currencyidtransactionamountedit" ng-options="item.currencyId as item.currencyName for item in currencies" class="form-control input-lg2">
										<option value="">---Select Currency---</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Transaction Charges<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="transactionchargesedit" name="transactionchargesedit" ng-model="transactionchargesedit" ng-keyup="calculateamountchargesedit()">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="sel1">Currency<font color="red" size="3">*</font></label>
									<select id="currencyidtransactionchargesedit" name="currencyidtransactionchargesedit" ng-model="currencyidtransactionchargesedit" ng-options="item.currencyId as item.currencyName for item in currencies" class="form-control input-lg2">
										<option value="">---Select Currency---</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Comments</label>
									<textarea class="form-control input-lg2" id="commentsedit" name="commentsedit" ng-model="commentsedit" capitalize-first></textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Image</label>
									<input type="file" id="fileedit" name="fileedit" multiple class="form-control input-lg2"/><br>
									<p style="font-size:13px;margin-top:-10px;">Upload maximum 1024 * 768 size <br> image for better appereance</p>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Amount Charges(INR)<font color="red" size="3">*</font></label>
									<input type="text" class="form-control input-lg2"  id="amountchargesedit" name="amountchargesedit" ng-model="amountchargesedit" Readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="btn-group btn-group-justified" role="group" aria-label="group button">
							<div class="btn-group" role="group">
								<input type="submit" class="btn btn-success" value="Save">
							</div>
							<div class="btn-group" role="group">
								<button type="button" class="btn btn-primary" data-dismiss="modal"  role="button">Close</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp" %>
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/bootstrap.min.js"></script>		
	</body>
</html>
