app.controller('memberProfileCtrl', function($scope, $http, $window, $filter, $location) {
	var baseUrl = $location.protocol() + "://" + location.host + u;	
	$scope.redirectToMemberProfile = function(id) {					
		$window.location.href = url + 'member_profile?id='+id;
	}
	
	$scope.fla = 0;
	var a=document.createElement('a');
	a.href=document.referrer;
	$scope.urlRed = document.referrer;
	var pieces = a.pathname.split(/[\s/]+/);
	
	if(pieces[pieces.length-1] == 'registered_members_directory'){
		$scope.fla = 1;
	}else if(pieces[pieces.length-1] == 'members_directory'){
		$scope.fla = 2;
	}else{$scope.fla = 0;}
	a='';
	
	$scope.getMemberDetailById = function(id) {		
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+id;					
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetail = data;
			$scope.memberid = $scope.getmemberdetail.memberId;
			$scope.firstname = $scope.getmemberdetail.memberFirstName;
			$scope.lastname = $scope.getmemberdetail.memberLastName;							
												
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
					
		var link = baseUrl+'getspousedata?memberid='+id;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getspousedata = data;
		}).error(function(data,status,headers,config) {
			$scope.getspousedata = "Response Fail";
		});
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