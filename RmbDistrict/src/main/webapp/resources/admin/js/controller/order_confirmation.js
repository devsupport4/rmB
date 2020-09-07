app.controller('orderConfirmationCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.getOrderDetail = function(ordernumber){
		
		var link = baseUrl+'getOrderDetailByOrderNumber?ordernumber='+ordernumber;
		$http.get(link).success(function(data, status, headers, config){
			$scope.orderdetail = data;		
			
			if($scope.orderdetail.paymentStatus == "Success"){				
				var link = baseUrl+'sendOrderConfirmationMail?ordernumber='+ordernumber;
				$http.post(link).success(function(data, status, headers, config){
					$scope.emailresponse = data;					
				}).error(function(data, status, headers, config){
					$scope.emailresponse = data;
				});
						
				var link = baseUrl+'sendOrderConfirmationSMS?ordernumber='+ordernumber;
				$http.post(link).success(function(data, status, headers, config){
					$scope.smsresponse = data;
				}).error(function(data, status, headers, config){
					$scope.smsresponse = data;
				});				
			}
		}).error(function(data, status, headers, config){
			$scope.orderdetail = "Response Fail";
		});
	}
	
	$scope.backToHome = function(){
		$window.location.href = baseUrl;
	}
	
}]);