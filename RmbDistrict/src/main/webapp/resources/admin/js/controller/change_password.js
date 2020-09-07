app.controller('passwordCtrl', function($scope, $http, $window, $filter, $location) {
	var baseUrl = $location.protocol() + "://" + location.host + u;	
	 
	$scope.changePassword = function(memberid) {		
		if($scope.oldpassword == undefined) {
			$window.alert("Please enter your old password!");
			document.getElementById("oldpassword").focus();
			return;
		} else if($scope.newpassword == undefined) {
			$window.alert("Please enter new password!");
			document.getElementById("newpassword").focus();
			return;
		} else if($scope.renewpassword == undefined) {
			$window.alert("Please enter new password again!");
			document.getElementById("renewpassword").focus();
			return;
		} else if($scope.newpassword != $scope.renewpassword) {
			$window.alert("Password did not match please enter password again!");
			document.getElementById("renewpassword").focus();
			return;
		} else {
			var pw = $scope.newpassword.replace("&","$");
			var pw1 = pw.replace("#","~");
			var pw2 = pw1.replace("%","!");

			var link = baseUrl+'changePassword?memberid='+memberid+'&password='+pw2;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.changepassword = data;
				$window.alert("Password Changed Successfully...");
				$window.location.href = url;
			}).error(function(data, status, headers, config) {
				$scope.changepassword = "Response Fail";
			});
		}
	}
	$scope.checkCurrentPassword = function(memberid) {		
		var pw = $scope.oldpassword.replace("&","$");
		var pw1 = pw.replace("#","~");
		var pw2 = pw1.replace("%","!");
		var link = baseUrl+'checkCurrentPassword?memberid='+memberid+'&password='+pw2;				
		$http.post(link).success(function(data, status, headers, config) {
			$scope.changepassword = data;				
			if(data == "Password Matched"){					
				return;
			} else {
				$window.alert("Wrong password! Please enter correct password!");
				document.getElementById("oldpassword").focus();
				return;
			}			
		}).error(function(data, status, headers, config) {
			$scope.changepassword = "Response Fail";
		});		
	}
	
	$scope.forgetPassword = function() {			
		$scope.a="";
		if($scope.email == undefined) {
			$window.alert("Please enter registered email");
			document.getElementById("email").focus();
			return;
		} else {
			var firstname = "";
			var lastname = "";
			var usertypeid = 0;
			var link = baseUrl+'getMember';
			$http.get(link).success(function(data, status, headers, config) {					
				$scope.getmember = data;
				for (i in $scope.getmember) {
					if ($scope.getmember[i].memberEmail == $scope.email) {
						$scope.a="yes";
						var firstname = $scope.getmember[i].memberFirstName;
						var lastname = $scope.getmember[i].memberLastName;
						var usertypeid = $scope.getmember[i].userTypeId;
					}
				}
				if($scope.a == "yes") {
					var link = baseUrl+'sendEmailForForgetPassword?firstname='+firstname+'&lastname='+lastname+'&email='+$scope.email;						
					$http.post(link).success(function(data, status, headers, config) {
						$scope.forgetpassword = data;
						$window.alert("'An email is sent to reset the password. Please check your email.");
						$window.location.href = url;
					}).error(function(data, status, headers, config) {
						$scope.forgetpassword = "Response Fail";
					});
				} else {						
					$window.alert("Invalid Email! Please enter valid registered email!");
					return;
				}
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});			
		}
	}
	$scope.resetPassword = function() {			
		var newretypepassword = $scope.newretypepassword;			
		if($scope.email == undefined) {
			$window.alert("Please enter registered email");
			document.getElementById("email").focus();
			return;
		} else if($scope.newpassword == undefined) {
			$window.alert("Please enter new password!");
			document.getElementById("newpassword").focus();
			return;
		} else if($scope.renewpassword == undefined) {
			$window.alert("Please enter new password again!");
			document.getElementById("renewpassword").focus();
			return;
		} else if($scope.newpassword != $scope.renewpassword) {
			$window.alert("Password did not match please enter password again!");
			document.getElementById("renewpassword").focus();
			return;
		} else {			
			var link = baseUrl+'resetPassword?email='+$scope.email+'&newpassword='+$scope.newpassword;				
			$http.post(link).success(function(data, status, headers, config) {
				$scope.resetpassword = data;
				$window.alert("Your password has been reset! Please login to continue!");
				$window.location.href = url;
			}).error(function(data, status, headers, config) {
				$scope.resetpassword = "Response Fail";
				$window.alert("Sorry something wrong! Please try again after sometime!");
			});				
		}
	}
	
});