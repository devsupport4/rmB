app.controller('myProfileCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.getMemberDetailsById = function(memberid) {
		var link = baseUrl+'getMemberByMemberId?memberid='+memberid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetail = data;
			
			$scope.firstname = $scope.getmemberdetail.memberFirstName;
			$scope.lastname = $scope.getmemberdetail.memberLastName;
			$scope.businesscategoryname = $scope.getmemberdetail.businessCategoryName;
			$scope.memberemail = $scope.getmemberdetail.memberEmail;
			$scope.membermobileno = $scope.getmemberdetail.memberMobileNumber;
			$scope.profilepic = $scope.getmemberdetail.memberProfilePicture;
			
		}).error(function(data, status, headers, config) {
			$scope.getmemberdetail = "Response Fail";
		});
	}
});