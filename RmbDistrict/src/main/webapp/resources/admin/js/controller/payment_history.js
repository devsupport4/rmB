var app = angular.module("rcbs", []);

//var u = "/rmbbangalore/";
var u = "/";

//var url = "/rmbbangalore/";
var url = "/";
//var adminurl = "/rmbbangalore/manageRmbb/";
var adminurl = "/manageRmbb/";


app.controller('paymentHistoryCtlr', ['$scope', '$filter', '$http', '$window', '$location' , '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.search = '';
    $scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.successMsg = true;
	$scope.errorMsg = true;
	
	var link = baseUrl+'getMember';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmember = data;			
	}).error(function(data, status, headers, config) {
		$scope.getmember = "Response Fail";
	});
	
	var link = baseUrl+'getMemberCategory';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmembercategory = data;
	}).error(function(data, status, headers, config) {
		$scope.getmembercategory = "Response Fail";
	});
	
	var link = baseUrl + 'RotaryYear';
	$http.get(link).success(function(data,status,headers,config) {
		$scope.rotaryyear = data;
		$scope.rotaryyearid = 0;
		for (i in $scope.rotaryyear) {
			if ($scope.rotaryyear[i].defaultYear == "y") {
				$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;
				$scope.rotaryyeartitle = $scope.rotaryyear[i].rotaryYearTitle;
			}
		}
		var link = baseUrl+'getPaymentHistoryByPage?rotaryyearid='+$scope.rotaryyearid+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpaymenthistory = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpaymenthistory = "Response Fail";
		});
	}).error(function(data,status,headers,config) {
		$scope.rotaryyear = "Responce Fail";
	});
	
	$scope.changepage = function() {			
		if($scope.pageSize == "All") {		
			var link = baseUrl+'getAllPaymentHistory?rotaryyearid='+$scope.rotaryyearid;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getpaymenthistory = data;		 
			}).error(function(data, status, headers, config) {
				$scope.getpaymenthistory = "Response Fail";
			});
		} else {			
			var link = baseUrl+'getPaymentHistoryByPage?rotaryyearid='+$scope.rotaryyearid+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getpaymenthistory = data;		 
			}).error(function(data, status, headers, config) {
				$scope.getpaymenthistory = "Response Fail";
			});
		}		
	}
	
	$scope.getPaymentHistoryByYear = function() {
		var link = baseUrl+'getPaymentHistoryByPage?rotaryyearid='+$scope.rotaryyearid+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpaymenthistory = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpaymenthistory = "Response Fail";
		});
	}
	
	$scope.next = function() {
		$scope.currentPage = $scope.currentPage + 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;				
		var link = baseUrl+'getPaymentHistoryByPage?rotaryyearid='+$scope.rotaryyearid+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpaymenthistory = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpaymenthistory = "Response Fail";
		});
	}
	
	$scope.prev = function() {
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;		
		var link = baseUrl+'getPaymentHistoryByPage?rotaryyearid='+$scope.rotaryyearid+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getpaymenthistory = data;		 
		}).error(function(data, status, headers, config) {
			$scope.getpaymenthistory = "Response Fail";
		});			
	}
	
	$scope.searchRecord = function() {
		var search = $scope.search;		
		if(search == undefined || search == "") {						
			var link = baseUrl+'getPaymentHistoryByPage?rotaryyearid='+$scope.rotaryyearid+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getpaymenthistory = data;		 
			}).error(function(data, status, headers, config) {
				$scope.getpaymenthistory = "Response Fail";
			});
		} else {						
			var link = baseUrl+'searchPendingPayments?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getpaymenthistory = data;				
			}).error(function(data, status, headers, config) {
				$scope.getpaymenthistory = "Response Fail";
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
	
	$scope.addPayment = function() {
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
		if($scope.memberid == undefined){
			$window.alert("Please select member");
			document.getElementById("memberid").focus();
			return;
		} else if($scope.rotaryyearid == undefined){
			$window.alert("Please select rotary year");
			document.getElementById("rotaryyearid").focus();
			return;
		} else if($scope.membercategoryid == undefined){
			$window.alert("Please select membership type");
			document.getElementById("membercategoryid").focus();
			return;
		} else if($scope.paidamount == undefined){
			$window.alert("Please enter amount");			
			return;
		} else {
			var link = baseUrl+'addPayment?memberid='+$scope.memberid+'&rotaryyearid='+$scope.rotaryyearid+'&membercategoryid='+$scope.membercategoryid+'&paymentmethod='+$scope.paymentmethod+'&bankname='+$scope.bankname+'&branchname='+$scope.branchname+'&chequeno='+$scope.chequeno+'&paymentdate='+$scope.paymentdate+'&paidamount='+$scope.paidamount;
			$window.alert(link);
			$http.post(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
	    			$scope.addpayment = data;
	    			$scope.successMsg = false;
	    			$timeout(function () { 
	    				$scope.successMsg = true;
	    				$window.location.href = adminurl+'payment_history';
	    			}, 3000);    				    									
	    		} else {
	    			$scope.errorMsg = false;
	    			$timeout(function () { 
	    				$scope.errorMsg = true;
	    				$window.location.href = adminurl+'payment_history';
	    			}, 3000);
	    		}    				
			}).error(function(data, status, headers, config) {
					$scope.addpayment = "Response Fail";
			});
		}
	}
	
}]);
