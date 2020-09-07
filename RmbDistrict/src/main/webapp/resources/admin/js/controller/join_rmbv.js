app.controller('joinRMBVCtrl', function($scope, $http, $window, $filter,
		$location) {

	var baseUrl = $location.protocol() + "://" + location.host + u;

	$scope.setFlag = function() {
		$scope.errorFirstName = 0;
		$scope.errorLastName = 0;
		$scope.errorMobile = 0;
		$scope.errorEmail = 0;
		$scope.submitError = 0;
	}
	
	$scope.joinRmbf = function() {
		if(!$scope.message){
			$scope.message = "";
		}
		if(!$scope.rotarian){
			$scope.rotarian = "";
		}
		
		if(!$scope.firstname) {
			$scope.errorFirstName = 1;
			$scope.msgFirstName = "Please enter your first name";
			document.getElementById("firstname").focus();
		} else if(!$scope.lastname){
			$scope.errorLastName = 1;
			$scope.msgLastName = "Please enter your last name";
			document.getElementById("lastname").focus();
		} else if(!$scope.mobilenumber){
			$scope.errorMobile = 1;
			$scope.msgMobile = "Please enter your mobile number";
			document.getElementById("mobilenumber").focus();
		} else if($scope.mobilenumber.length < 10){
			$scope.errorMobile = 1;
			$scope.msgMobile = "Please enter 10 digit mobile number";
			document.getElementById("mobilenumber").focus();
		} else if($scope.mobilenumber.length > 10){
			$scope.errorMobile = 1;
			$scope.msgMobile = "Phone number is invalid.";
			document.getElementById("mobilenumber").focus();
		} else	if(!$scope.email){
			$scope.errorEmail = 1;
			$scope.msgEmail = "Please enter your email";
			document.getElementById("email").focus();
		} else {
			$scope.spin=1;
			
			/*$scope.message = tools_replaceAll($scope.message, "&" , "$" );
			$scope.message = tools_replaceAll($scope.message, "#" , "~" );
			$scope.message = tools_replaceAll($scope.message, "%" , "!" );*/
			
			var link = baseUrl+'joinRmbf?firstname='+$scope.firstname+'&lastname='+$scope.lastname+'&mobilenumber='+$scope.mobilenumber+'&email='+$scope.email+'&rotarian='+$scope.rotarian+'&description='+$scope.message;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.joinrmbv = data;
				if($scope.joinrmbv == "Success"){
					$scope.spin = 0;
					$scope.submitSuccess=1;
					$scope.msgSuccess="Your form has been submited successfully.";
					$timeout(function(){
						$scope.submitSuccess = 0;
						$window.location.href = u;
					}, 2000);
				} else {
					$scope.spin = 0;
					$scope.submitError=1;
					$scope.msgError="Sorry! Something went wrong! Please try after sometime.";
				}				
			}).error(function(data, status, headers, config) {
				$scope.spin = 0;
				$scope.submitError=1;
				$scope.msgError="Sorry! Something went wrong! Please try after sometime.";
			});
		}
	}	
});