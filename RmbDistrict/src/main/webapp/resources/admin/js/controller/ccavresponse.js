app.controller('ccAvResCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.ccavenueresponse = function() {
		var orderid = $scope.orderid;		
		var trackingid = $scope.trackingid;
		var bankrefnumber = $scope.bankrefnumber;
		var orderstatus = $scope.orderstatus;
		var failuremessage = $scope.failuremessage;
		var paymentmode = $scope.paymentmode;
		var cardname = $scope.cardname;		
		var statuscode = $scope.statuscode;
		var statusmessage = $scope.statusmessage;
		var currency = $scope.currency;	
		
		if(orderid == undefined || orderid == "" || orderid == 'null'){
			orderid = "";
		}
		if(trackingid == undefined || trackingid == "" || trackingid == 'null'){
			trackingid = "";
		}
		if(bankrefnumber == undefined || bankrefnumber == "" || bankrefnumber == 'null'){
			bankrefnumber = "";
		}
		if(orderstatus == undefined || orderstatus == "" || orderstatus == 'null'){
			orderstatus = "";
		}
		if(failuremessage == undefined || failuremessage == "" || failuremessage == 'null' ){
			failuremessage = "";
		}		
		if(paymentmode == undefined || paymentmode == "" || paymentmode == 'null'){
			paymentmode = "";
		}
		if(cardname == undefined || cardname == "" || cardname == 'null'){
			cardname = "";
		}
		if(currency == undefined || currency == "" || currency == 'null'){
			currency = "";
		}
		if(statuscode == undefined || statuscode == "" || statuscode == "null"){
			statuscode = "";
		}
		if(statusmessage == undefined || statusmessage == "" || statusmessage == "null"){
			statusmessage = "";
		}
		
		var link = baseUrl+'ccavenueResponse?orderid='+orderid+'&trackingid='+trackingid+'&bankrefnumber='+bankrefnumber+'&orderstatus='+orderstatus+'&failuremessage='+failuremessage+'&paymentmode='+paymentmode+'&cardname='+cardname+'&statuscode='+statuscode+'&statusmessage='+statusmessage+'&currency='+currency;
		
		$http.post(link).success(function(data, status, headers, config) {
			$window.location.href = url+"order_confirmation";
		}).error(function(data, status, headers, config){
			$window.location.href = url+"order_confirmation";
		});
	}
	
}]);