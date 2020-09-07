app.controller('boardOfDirectorCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	var link = baseUrl + 'RotaryYear';
	$http.get(link).success(function(data,status,headers,config) {
		$scope.rotaryyear = data;						
	}).error(function(data,status,headers,config) {
		$scope.rotaryyear = "Responce Fail";
	});
	
	$scope.getDefaultYear = function () {
		var link = baseUrl + 'getCurrentRotaryYear';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.rotaryyear = data;
			$scope.rotaryyearid = $scope.rotaryyear.rotaryYearId;			
			
			var link = baseUrl + 'getBoardOfDirectorsByRotaryYear?rotaryyearid='+$scope.rotaryyearid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getboardofdirectorsbyrotaryyear = data;
			}).error(function(data, status, headers, config) {
				$scope.getboardofdirectorsbyrotaryyear = "Response Fail";
			});
			
			/*var link = baseUrl + 'getMembersCommitteeByRotaryYear?rotaryyearid='+$scope.rotaryyearid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberscommitteebyrotaryyear = data;
			}).error(function(data, status, headers, config) {
				$scope.getmemberscommitteebyrotaryyear = "Response Fail";
			});*/				
		}).error(function(data,status,headers,config) {
			$scope.rotaryyear = "Responce Fail";
		});				
    }
	
	$scope.getBoardOfDirectorsDetailByRotaryYear = function () {	 
		var link = baseUrl + 'getBoardOfDirectorsByRotaryYear?rotaryyearid='+$scope.rotaryyearid;						
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getboardofdirectorsbyrotaryyear = data;
		}).error(function(data, status, headers, config) {
			$scope.getboardofdirectorsbyrotaryyear = "Response Fail";
		});			 
    }		
});