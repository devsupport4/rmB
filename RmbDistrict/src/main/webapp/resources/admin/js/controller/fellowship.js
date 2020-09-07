app.controller('fellowshipCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;	
		
		
		
		$scope.addfellowship = function(){

			var link = baseUrl+'addfellowship';		
			alert(link);
			$http({url: link, method: "POST", data: $scope.fellowship}).success(function(data, status, headers, config) {
				$scope.fellowshipdata = data;			
			  location.reload(true); 
				}).error(function(data, status, headers, config) {
					$scope.fellowshipdata = "Response Fail";
				});
			}


		
		
		
				
	});