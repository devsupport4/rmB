app.controller('newsDetailCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.getNewsDetailById = function(id,projectid) {		
		var link = baseUrl + 'getNewsDetailById?id='+id+'&projectid='+projectid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getnewsdetailbyid = data;
		}).error(function(data, status, headers, config) {
			$scope.getnewsdetailbyid = "Response Fail";
		});
	}
});