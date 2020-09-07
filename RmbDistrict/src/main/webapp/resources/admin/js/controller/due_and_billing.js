var app = angular.module("rcbs", []);

//var u = "/rmbbangalore/";
var u = "/";

//var url = "/rmbbangalore/";
var url = "/";
//var adminurl = "/rmbbangalore/manageRmbb/";
var adminurl = "/manageRmbb/";
app.controller('dueBillingCtlr', function($scope, $http, $window, $filter,$location,$timeout) {
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.successMsg = true;
	$scope.errorMsg = true;
	
	var link = baseUrl+'getMember';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmember = data;			
	}).error(function(data, status, headers, config) {
		$scope.getmember = "Response Fail";
	});
	
	var link = baseUrl + 'RotaryYear';
	$http.get(link).success(function(data,status,headers,config) {
		$scope.rotaryyear = data;
		for (i in $scope.rotaryyear) {
			if ($scope.rotaryyear[i].defaultYear == "y") {
				$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;
				$scope.rotaryyeartitle = $scope.rotaryyear[i].rotaryYearTitle;
			}
		}
	}).error(function(data,status,headers,config) {
		$scope.rotaryyear = "Responce Fail";
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
	
	/*$scope.billinglist = [{}];
	$scope.temp = 0;
	$scope.addBillingRow = function() {		
		if($scope.memberid == undefined){
			$scope.memberid = "all";
		} 
		if($scope.rotaryyearid == undefined){
			$window.alert("Please select rotary year!");
			document.getElementById("rotaryyearid").focus();
			return;
		} else if($scope.membercategoryid == undefined){
			$window.alert("Please select member type!");
			document.getElementById("membercategoryid").focus();
			return;
		} else if($scope.billingamount == undefined){
			$window.alert("Please enter billing amount!");
			document.getElementById("billingamount").focus();
			return;
		} else if($scope.currencyid == undefined){
			$window.alert("Please select currency!");
			document.getElementById("currencyid").focus();
			return;
		} else {
			if($scope.memberid != "all"){
				for(i in $scope.getmember){
					if($scope.getmember[i].memberId == $scope.memberid){					
						$scope.memberfirstname = $scope.getmember[i].memberFirstName;
						$scope.memberlastname = $scope.getmember[i].memberLastName;
					}					
				}
			} else {
				$scope.memberfirstname = "All";
				$scope.memberlastname = "";
				$scope.memberid = 0;
			}
			
			for (i in $scope.rotaryyear) {
				if ($scope.rotaryyear[i].rotaryYearId == $scope.rotaryyearid) {				
					$scope.rotaryyeartitle = $scope.rotaryyear[i].rotaryYearTitle;
				}
			}
			
			for (i in $scope.getmembercategory) {
				if ($scope.getmembercategory[i].memberCategoryId == $scope.membercategoryid) {				
					$scope.membercategoryname = $scope.getmembercategory[i].memberCategoryName;
				}
			}
			
			for (i in $scope.currencies) {
				if ($scope.currencies[i].currencyId == $scope.currencyid) {				
					$scope.currencycode = $scope.currencies[i].currencyCode;
				}
			}
			$scope.temp = 1;
			
			$scope.billinglist.push({'memberId' : $scope.memberid, 'memberFirstName' : $scope.memberfirstname, 'memberLastName' : $scope.memberlastname, 'rotaryYearId' : $scope.rotaryyearid, 'rotaryYearTitle' : $scope.rotaryyeartitle, 'memberCategoryId' : $scope.membercategoryid, 'memberCategoryName' : $scope.membercategoryname, 'billingAmount' : $scope.billingamount, 'currencyId' : $scope.currencyid, 'currencyCode' : $scope.currencycode});
			$scope.memberid = undefined;
			//$scope.rotaryyearid = undefined;
			$scope.membercategoryid = undefined;
			$scope.billingamount = undefined;
			$scope.currencyid = undefined;
		}					
	}
	
	$scope.removeBillingRow = function(billingamount) {
		var index = -1;
		var comArr = eval($scope.billinglist);
		for (var i = 0; i < comArr.length; i++) {
			if (comArr[i].billingAmount === billingamount) {
				index = i;
				break;
			}
		}
		if (index === -1) {
			alert("Something gone wrong");
		}
		$scope.billinglist.splice(index, 1);
	};*/
	
	$scope.generateBillingDraft = function() {
		angular.forEach($scope.getmembercategory,function(item) {
			if(item.billingAmount == undefined)
				item.billingAmount = 0;
			var link = baseUrl+'addMembershipCharges?rotaryyearid='+$scope.rotaryyearid+'&membercategoryid='+item.memberCategoryId+'&billingamount='+item.billingAmount+'&currencyid='+item.currencyId;			
			$http.post(link).success(function(data,status,headers,config) {					
				$scope.addmembershipcharges = data;					
				if ($scope.addmembershipcharges == "Success") {
					$scope.successMsg = false;
					$timeout(function () { 
						$scope.successMsg = true;
						$window.location.href = adminurl+'draft_list';
					}, 3000);    				    									
				} else {
					$scope.errorMsg = false;
					$timeout(function () { 
						$scope.errorMsg = true;
						$window.location.href = adminurl+'draft_list';
					}, 3000);
				}									
			}).error(function(data,status,headers,config) {
				$scope.addmembershipcharges = "Response Fail";
			});
		});
	}
});
