app.controller('myReferenceCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.getreference = function(memberid) {		
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
			
			var link = baseUrl+'getreference?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getreferences = data;
			}).error(function(data,status,headers,config) {
				$scope.getreferences = "Response Fail";
			});
			
		}).error(function(data, status, headers, config) {
			$scope.getmemberdetail = "Response Fail";
		});
	}
	
	$scope.redirectfamilydetail = function(memberid, temp) {
		if(temp == 2) {
			$window.location.href = adminurl+"my_family_detail?memberid="+memberid;			
		} else {
			$window.location.href = adminurl+"manage_family_detail?memberid="+memberid;		
		}
	}
	
	$scope.redirectpayments = function(temp) {
		if(temp == 2) {
			$window.location.href = url + "my_payment_detail";						
		} else {
			$window.location.href = adminurl + "payment_detail";	
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
});