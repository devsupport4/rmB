app.controller('myPaymentCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.getpayment = function(memberid) {	
		
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetail = data;
			
			$scope.memberid = $scope.getmemberdetail.memberId;
			$scope.membershipnumber = $scope.getmemberdetail.membershipNumber;
			$scope.membercategoryid = $scope.getmemberdetail.memberCategoryId;
			$scope.tenureplan = $scope.getmemberdetail.tenurePlan;
			$scope.memberfirstname = $scope.getmemberdetail.memberFirstName;
			$scope.membermiddlename = $scope.getmemberdetail.memberMiddleName;
			$scope.memberlastname = $scope.getmemberdetail.memberLastName;
			
			var link = baseUrl+'getpayment?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getpayments = data;
			}).error(function(data,status,headers,config) {
				$scope.getpayments = "Response Fail";
			});
			
		}).error(function(data, status, headers, config) {
			$scope.getmemberdetail = "Response Fail";
		});
	}
	
	$scope.redirectreference = function(membercategoryid,temp) {			
		if(membercategoryid == 2) {
			if(temp == 2) {
				$window.location.href = url + "my_payment_detail";
			}else{
				$window.location.href = adminurl + "payment_detail";
			}
		} else {
			if(temp == 2) {
				$window.location.href = url + "my_reference";
			}else{
				$window.location.href = adminurl + "reference";
			}
		}			
	}
	
	$scope.Frontlogout = function() {		
		var link = baseUrl + 'Frontlogout';
		$http.post(link).success(function(data, status, headers, config) {
			$window.location.href = url+'';
		}).error(function(data, status, headers, config) {
			$scope.frontlogout = "Response Fail";
		});
	}
	
	$scope.return_myprofile = function(){
		
		$window.location.href = url + "index2";
		
	}
		
});