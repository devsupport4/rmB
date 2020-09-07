var app = angular.module("rcbs", []);

//var u = "/rmbbangalore/";
var u = "/";

//var url = "/rmbbangalore/";
var url = "/";
//var adminurl = "/rmbbangalore/manageRmbb/";
var adminurl = "/manageRmbb/";

app.controller('draftListCtlr', function($scope, $http, $window, $filter,$location,$timeout) {
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.successMsg = true;
	$scope.errorMsg = true;
	
	var link = baseUrl + 'RotaryYear';
	$http.get(link).success(function(data,status,headers,config) {
		$scope.rotaryyear = data;
		for (i in $scope.rotaryyear) {
			if ($scope.rotaryyear[i].defaultYear == "y") {
				$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;
				$scope.rotaryyeartitle = $scope.rotaryyear[i].rotaryYearTitle;
			}
		}
		var link = baseUrl+'getMembershipCharges?rotaryyearid='+$scope.rotaryyearid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmembershipcharges = data;		
		}).error(function(data, status, headers, config) {
			$scope.getmembershipcharges = "Response Fail";
		});
	}).error(function(data,status,headers,config) {
		$scope.rotaryyear = "Responce Fail";
	});	
	
	var link = baseUrl+'getMembershipChargesByMember';		
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmembershipchargesbymember = data;		
	}).error(function(data, status, headers, config) {
		$scope.getmembershipchargesbymember = "Response Fail";
	});
	
	var link = baseUrl+'getMemberCategory';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmembercategory = data;
	}).error(function(data, status, headers, config) {
		$scope.getmembercategory = "Response Fail";
	});
	
	var link = baseUrl+'Currency';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.currencies = data;
	}).error(function(data, status, headers, config)	{
		$scope.currencies = "Response Fail";
	});	
	
	$scope.getPaymentAmountByMemberId = function(rotaryyearid, membercategoryid, memberid) {		
		var link = baseUrl+'getMembershipCharges?rotaryyearid='+rotaryyearid;		
		$http.get(link).success(function(data, status, headers, config) {			
			$scope.getmembershipcharge = data;
			$scope.amount = 0;			
			for (i in $scope.getmembershipcharge) {				
				if ($scope.getmembershipcharge[i].memberCategoryId == membercategoryid) {					
					$scope.amount = $scope.getmembershipcharge[i].billingAmount;					
				}
			}			
			for(i in $scope.getmembershipchargesbymember){
				if ($scope.getmembershipchargesbymember[i].memberId == memberid) {					
					$scope.getmembershipchargesbymember[i].billingAmount = $scope.amount;				
				}
			}			
		}).error(function(data, status, headers, config) {
			$scope.getmembershipcharge = "Response Fail";
		});
	}
	
	$scope.addMembershipPaymentTransaction = function() {
		angular.forEach($scope.getmembershipchargesbymember,function(item) {			
			var link = baseUrl+'addMembershipPaymentTransaction?memberid='+item.memberId+'&rotaryyearid='+item.rotaryYearId+'&membercategoryid='+item.memberCategoryId+'&billingamount='+item.billingAmount+'&currencyid='+item.currencyId;			
			$http.post(link).success(function(data,status,headers,config) {					
				$scope.addmembershippaymenttransaction = data;					
				if ($scope.addmembershippaymenttransaction == "Success") {
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
			}).error(function(data,status,headers,config) {
				$scope.addmembershippaymenttransaction = "Response Fail";
			});			
		});
	}
});
