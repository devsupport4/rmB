app.controller('thankYouSlipCtrl',function($scope, $http, $window, $filter, $location) {
	
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		var link = baseUrl+'getAllMembersDirectory';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
		
		$scope.saveThankYouSlip = function(temp) {	
			$scope.slipdate = document.getElementById("datepicker").value;
			if($scope.comment == undefined){
				$scope.comment = "";
			}		
			if($scope.tomemberid == undefined || $scope.tomemberid == ""){
				$window.alert("Please select member!");
				document.getElementById("tomemberid").focus();
				return;
			} else if($scope.amount == undefined){
				$window.alert("Please enter amount!");
				document.getElementById("amount").focus();
				return;
			} else if($scope.slipdate == undefined){
				$window.alert("Please enter slip date!");
				document.getElementById("slipdate").focus();
				return;
			} else if($scope.businesstype == undefined){
				$window.alert("Please select business type!");
				document.getElementById("businesstype").focus();
				return;
			} else if($scope.referraltype == undefined){
				$window.alert("Please select referral type!");
				document.getElementById("referraltype").focus();
				return;
			}  else {
				$scope.spin = 1;				
				var link = baseUrl+'saveThankYouSlip?tomemberid='+$scope.tomemberid+'&amount='+$scope.amount+'&slipdate='+$scope.slipdate+'&businesstype='+$scope.businesstype+'&referraltype='+$scope.referraltype+'&comment='+$scope.comment;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.savethankyouslip = data;
					$scope.spin = 0;					
					$window.alert("Data submitted successfully");
					if(temp == "new"){
						$window.location.href = url + 'thankyou_slip';
					} else {
						$window.location.href = url;
					};					
				}).error(function(data, status, headers, config) {
					$scope.savethankyouslip = "Response Fail";
				});
			}
		};
				
	});