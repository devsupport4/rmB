app.controller('searchResultCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.getSearchedMembers = function(search){
		$scope.search = search;
		var link = baseUrl+'searchMembers?keyword='+search;			
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember1 = data;				
		}).error(function(data, status, headers, config) {
			$scope.getmember1 = "Response Fail";
		});
	}
	
	$scope.setFlag = function() {
		$scope.errorFirstName = 0;
		$scope.msgFirstName = "";
		$scope.errorLastName = 0;
		$scope.msgLastName = "";
		$scope.errorEmail = 0;
		$scope.msgEmail = "";
		$scope.errorMobileNo = 0;
		$scope.msgMobileNo = "";
		$scope.errorMessage = 0;
		$scope.msgMessage = "";
	}
	
	$scope.sendMessage = function(memberid) {					
		if($scope.userfirstname == undefined || $scope.userfirstname == "") {
			$scope.errorFirstName = 1;
			$scope.msgFirstName = "Please enter your first name";
			document.getElementById("userfirstname").focus();
			return;
		} else if($scope.userlastname == undefined || $scope.userlastname == "") {
			$scope.errorLastName = 1;
			$scope.msgLastName = "Please enter your last name";
			document.getElementById("userlastname").focus();
			return;
		} else if($scope.useremail == undefined || $scope.useremail == "") {			
			$scope.errorEmail = 1;
			$scope.msgEmail = "Please enter your email";
			document.getElementById("useremail").focus();
			return;
		} else if($scope.usermobileno == undefined || $scope.usermobileno == "") {
			$scope.errorMobileNo = 1;
			$scope.msgMobileNo = "Please enter your mobile no.";
			document.getElementById("usermobileno").focus();
			return;
		} else if($scope.usermessage == undefined || $scope.usermessage == "") {
			$scope.errorMessage = 1;
			$scope.msgMessage = "Please enter your message";
			document.getElementById("usermessage").focus();
			return;
		} else {
			$scope.spin = 1;
			var link = baseUrl+'sendUserMessage?memberid='+memberid+'&firstname='+$scope.userfirstname+'&lastname='+$scope.userlastname+'&email='+$scope.useremail+'&mobileno='+$scope.usermobileno+'&usermessage='+$scope.usermessage;				
			$http.post(link).success(function(data, status, headers, config) {
				$scope.sendusermesaage = data;
				$scope.spin = 0;							
				$scope.submitSuccess = 1;
				$scope.msgSuccess = "Your message sent to member";
				$timeout(function(){
					$scope.submitSuccess = 0;
					$('#myModal').modal('hide');				
				}, 2000);
			}).error(function(data, status, headers, config) {
				$scope.sendusermesaage = "Response Fail";
				$scope.submitError = 1;
				$scope.msgError = "Some wrong! Please try again after some time!";
				$timeout(function(){
					$scope.submitError = 0;				
				}, 3000);				
			});
		}
	}
	
	$scope.redirectToMemberProfile = function(id) {					
		$window.location.href = url + 'member_profile?id='+id;
	}
});