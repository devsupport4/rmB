app.controller('sidebarCtrl', function($scope, $http, $window, $filter,$location) {
	var baseUrl = $location.protocol() + "://" + location.host + u;
		
	var link = baseUrl+'getSocialMediaLinks';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getsocialmedialinks = data;
	}).error(function(data, status, headers, config) {
		$scope.getsocialmedialinks = "Response Fail";
	});	
	
	$scope.getMemberProfile = function(memberid){
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
		//alert(link);
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetails = data;
			$scope.memberProfilePicture = $scope.getmemberdetails.memberProfilePicture;
		}).error(function(data, status, headers, config) {
			$scope.getmemberdetails = "Response Fail";
		});
	}
	
	$scope.checkFrontLogin = function() {				
		var username = $scope.username;
		var password = $scope.password;
		var loggedin = $scope.loggedin;
		
		if(loggedin == true)
		{
			loggedin = "y";
		}
		else
		{
			loggedin = "n";
		}
		if (username == undefined || username == "") {
			$window.alert("Please enter username");
			document.getElementById("username").focus();
			return;
		} else if (password == undefined || password == "") {
			$window.alert("Please enter password");
			document.getElementById("password").focus();
			return;
		} else {
			var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password+'&loggedin='+loggedin;
			//alert(link);
			$http.post(link).success(function(data, status, headers,config) {
				if (data == "") {
					$window.alert("User Name or Password Incorrect...Try Again");
				} else {
					//alert("mk");
					$window.location.href = url+'index2';	
					
				}
			}).error(function(data, status, headers,config) {
					$window.alert("Some thing wrong...Try again1");
			});
		}
	}
	
	
	
	$scope.checkMobileFrontLogin = function(r) {
		var username = $scope.username;
		var password = $scope.password;
		var loggedin = $scope.loggedin;
		
		if(loggedin == true)
		{
			loggedin = "y";
		}
		else
		{
			loggedin = "n";
		}
		
		if (username == undefined || username == "") {
			$window.alert("Please enter username");
			document.getElementById("username").focus();
			return;
		} else if (password == undefined || password == "") {
			$window.alert("Please enter password");
			document.getElementById("password").focus();
			return;
		} else {
			var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password+'&loggedin='+loggedin;
	
			$http.post(link).success(function(data, status, headers,config) {
				$scope.checklogin = data;
				
				if($scope.checklogin == "Successfully Login")
				{
					//alert($window.location.href);
					$window.location.href = url+'';
					
				//	$window.history.back() ;
					
				} else {
					$window.alert("Invalid Email ID or Password");
				}
			}).error(function(data, status, headers,config) {
					$window.alert("Some thing wrong...Try again2");
			});
		}
	}
	
	
	
	
	
		
	$scope.checkMemberLogin = function() {				
		var username = $scope.username;
		var password = $scope.password;
		if (username == undefined || username == "") {
			$window.alert("Please enter username");
			document.getElementById("username").focus();
			return;
		} else if (password == undefined || password == "") {
			$window.alert("Please enter password");
			document.getElementById("password").focus();
			return;
		} else {
			var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password;
			$http.post(link).success(function(data, status, headers,config) {
				if (data == "")	{
					$window.alert("User Name or Password Incorrect...Try Again");
				} else {
					$window.location.href = url+'members_directory';				
				}
			}).error(function(data, status, headers,config) {
				$window.alert("Some thing wrong...Try again3");
			});
		}
	}
		
	
	$scope.Frontlogout = function() {		
		var link = baseUrl + 'Frontlogout';
		
		$http.post(link).success(function(data, status, headers, config) {
			$window.location.href = url+'';
		}).error(function(data, status, headers, config) {
			$scope.frontlogout = "Response Fail";
		});
	}
$scope.pathname = $window.location.pathname; 	
	
	if($scope.pathname == "/Frontlogout"){
		
		$window.location.href = url+'';
	}
	
		
});