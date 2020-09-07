var app = angular.module("rcbs", []);

//var u = "/rmbbangalore/";
var u = "/";

//var url = "/rmbbangalore/";
var url = "/";
//var adminurl = "/rmbbangalore/manageRmbb/";
var adminurl = "/manageRmbb/";

app.controller('pendingPaymentCtlr', ['$scope', '$filter', '$http', '$window', '$location' , '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.search = '';
    $scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.successMsg = true;
	$scope.errorMsg = true;
	
	var link = baseUrl+'getPendingPaymentsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
	$http.get(link).success(function(data, status, headers, config)	{
		$scope.getpendingpayments = data;		 
	}).error(function(data, status, headers, config) {
		$scope.getpendingpayments = "Response Fail";
	});
	
	$scope.changepage = function() {			
		if($scope.pageSize == "All") {		
			var link = baseUrl+'getAllPendingPayments';	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getpendingpayments = data;		 
			}).error(function(data, status, headers, config) {
				$scope.getpendingpayments = "Response Fail";
			});
		} else {			
			var link = baseUrl+'getPendingPaymentsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getpendingpayments = data;		 
			}).error(function(data, status, headers, config) {
				$scope.getpendingpayments = "Response Fail";
			});
		}		
	}
	
	$scope.next = function() {
		$scope.currentPage = $scope.currentPage + 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;				
		var link = baseUrl+'getPendingPaymentsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpendingpayments = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpendingpayments = "Response Fail";
		});
	}
	
	$scope.prev = function() {
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;		
		var link = baseUrl+'getPendingPaymentsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpendingpayments = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpendingpayments = "Response Fail";
		});			
	}
	
	$scope.searchRecord = function() {
		var search = $scope.search;		
		if(search == undefined || search == "") {						
			var link = baseUrl+'getPendingPaymentsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getpendingpayments = data;		 
			}).error(function(data, status, headers, config) {
				$scope.getpendingpayments = "Response Fail";
			});
		} else {						
			var link = baseUrl+'searchPendingPayments?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getpendingpayments = data;				
			}).error(function(data, status, headers, config) {
				$scope.getpendingpayments = "Response Fail";
			});
		}
	}
	
	$scope.getPaymentDetail = function(memberid) {
		var link = baseUrl+'getPendingPaymentDetailByMemberId?memberid='+memberid;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpendingpaymentdetailbymemberid = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpendingpaymentdetailbymemberid = "Response Fail";
		});
	}
	
	$scope.addPaymentInfo = function() {
		if($scope.paymentmethod == "Cash"){
			$scope.paymentdate = document.getElementById("datepicker1").value;
			$scope.paidamount = $scope.cashpaidamount;
		}
			
		if($scope.paymentmethod != "Cash"){
			$scope.paymentdate = document.getElementById("datepicker").value;
			$scope.paidamount = $scope.chequepaidamount;
		}	
		
		if($scope.paymentmethod == undefined){
			$window.alert("Please select payment type");
			document.getElementById("paymentmethod").focus();
			return;
		} 
		if($scope.paymentmethod == "Cash"){
			$scope.bankname = "";
			$scope.branchname = "";
			$scope.chequeno = "";			 
		} 
		if($scope.paymentmethod != "Cash"){
			if($scope.bankname == undefined){
				$window.alert("Please enter bank name");
				document.getElementById("bankname").focus();
				return;
			} else if ($scope.branchname == undefined){
				$window.alert("Please enter branch name");
				document.getElementById("branchname").focus();
				return;
			} else if ($scope.chequeno == undefined){
				$window.alert("Please enter cheque/draft number");
				document.getElementById("chequeno").focus();
				return;
			} 
		} 
		if($scope.paidamount == undefined){
			$window.alert("Please enter amount");			
			return;
		} else {
			var link = baseUrl+'addPaymentInfo?id='+$scope.getpendingpaymentdetailbymemberid.paymentDetailId+'&paymentmethod='+$scope.paymentmethod+'&bankname='+$scope.bankname+'&branchname='+$scope.branchname+'&chequeno='+$scope.chequeno+'&paymentdate='+$scope.paymentdate+'&paidamount='+$scope.paidamount;
			$window.alert(link);
			$http.post(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
	    			$scope.addpaymentinfo = data;
	    			$scope.successMsg = false;
	    			$timeout(function () { 
	    				$scope.successMsg = true;
	    				$window.location.href = adminurl+'pending_payments';
	    			}, 3000);    				    									
	    		} else {
	    			$scope.errorMsg = false;
	    			$timeout(function () { 
	    				$scope.errorMsg = true;
	    				$window.location.href = adminurl+'pending_payments';
	    			}, 3000);
	    		}    				
			}).error(function(data, status, headers, config) {
					$scope.addpaymentinfo = "Response Fail";
			});
		}
	}
	
}]);
