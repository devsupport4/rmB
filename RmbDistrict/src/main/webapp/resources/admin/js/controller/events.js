app.controller('frontEventCtrl', function($scope, $http, $window, $filter,
		$location) {

	var baseUrl = $location.protocol() + "://" + location.host + u;
	$scope.varE=0;
	$scope.varP=0;
	$scope.todaydate = $filter('date')(new Date(),'yyyy-MM-dd');
	var link = baseUrl + 'Events';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.events = data;
	}).error(function(data, status, headers, config) {
		$scope.events = "Response Fail";
	});
	
	
	$scope.setFound = function(){
		$scope.varE = 1;
	}
	$scope.setFoundP = function(){
		$scope.varP = 1;
	}
	$scope.redirecttoeventdetail = function(id) {
		$window.location.href = url + 'event_detail?id=' + id;
	}
	
	$scope.redirectToRegisteredMEmbersDirectory = function(eid){
		$window.location.href = url+'registered_members_directory?event_id='+eid;
	}
	
});