app.controller("frontClubInfoCtrl",function($window, $scope, $http, $location, $filter, $interval) {
	var baseUrl = $location.protocol()+"://"+location.host+u;	
	
	var link = baseUrl + 'getClubInfo';
	$http.get(link).success(function(data,status,headers,config){
		$scope.clubinfo = data;
		
		$scope.title = $scope.clubinfo.clubTitle;
		$scope.shorttitle = $scope.clubinfo.clubShortitle;
		$scope.clublogo = $scope.clubinfo.clubLogo;
		$scope.add1 = $scope.clubinfo.meetingAddress1;
		$scope.add2 = $scope.clubinfo.meetingAddress2;
		$scope.day = $scope.clubinfo.meetingDay;
		$scope.time = $scope.clubinfo.meetingTime;
		$scope.map = $scope.clubinfo.mapLink;
	}).error(function(data,status,headers,config){
		$scope.clubinfo = "Responce Fail";
	});						
});