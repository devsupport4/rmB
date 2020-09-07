app.controller('frontNewsCtrl',function($scope, $http, $window, $filter, $location) {
	
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
			
			var link = baseUrl + 'getNewsDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getcurrentyearnews = data;
			}).error(function(data, status, headers, config) {
				$scope.getcurrentyearnews = "Response Fail";
			});
			
		}).error(function(data,status,headers,config) {
			$scope.currentrotaryyear = "Responce Fail";
		});		 
    }
	
	$scope.getNewsDetailByRotaryYear = function () {		 
		var link = baseUrl + 'getNewsDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;						
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcurrentyearnews = data;
		}).error(function(data, status, headers, config) {
			$scope.getcurrentyearnews = "Response Fail";
		});			 
    }
});