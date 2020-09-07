app.controller('SliderCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;	
		
		


	/*Image calling */
	$scope.redirectSlider=function(redirectUrl){
		
		location.href=redirectUrl;
	}
	var link = baseUrl+'getActiveSliders';

	$http.get(link).
     success(function (data, status, headers, config) {
         //debugger;
         $scope.activeSlider = data;
     }).
     error(function (data, status, headers, config) {
    	   $scope.activeSlider = "No response";
     });
	
	/*Image calling end */
	

		var baseUrl = $location.protocol() + "://" + location.host + u;
	var link = baseUrl+'getAllFellowshipName';  
	
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getAllFellowshipNameList = data;
		
	}).error(function(data, status, headers, config) {
		$scope.getAllFellowshipNameList = "Response Fail";
	});
	
	$scope.pathname = $window.location.pathname; 	
	
	if($scope.pathname == "/Frontlogout"){
		
		$window.location.href = url+'';
	}
	

});