app.controller('frontBulletinCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	var link = baseUrl + 'RotaryYear';
	$http.get(link).success(function(data,status,headers,config) {
		$scope.rotaryyear = data;						
	}).error(function(data,status,headers,config) {
		$scope.rotaryyear = "Responce Fail";
	});
	
	$scope.getCurrentDefaultYear = function () {
		var link = baseUrl + 'getCurrentRotaryYear';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.currentrotaryyear = data;
			$scope.rotaryyearid = $scope.currentrotaryyear.rotaryYearId;					
			
			var link = baseUrl + 'getClubBulletinDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getcurrentyearbulletins = data;
			}).error(function(data, status, headers, config) {
				$scope.getcurrentyearbulletins = "Response Fail";
			});
		}).error(function(data,status,headers,config) {
			$scope.currentrotaryyear = "Responce Fail";
		});		 
    }
	
	$scope.getBulletinDetailByRotaryYear = function () {	
		var link = baseUrl + 'getClubBulletinDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcurrentyearbulletins = data;
		}).error(function(data, status, headers, config) {
			$scope.getcurrentyearbulletins = "Response Fail";
		});			 
    }
});