app.controller('oneToOneCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
	/*	
		var link = baseUrl+'getAllMembersDirectory';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});		
		
		$scope.setNewMemberId = function() {
			$scope.memberid = $scope.metmemberid;
		}
		
		$scope.saveOnetoOne = function(temp) {			
			$scope.meetingdate = document.getElementById("datepicker").value;		
			if($scope.metmemberid == undefined || $scope.metmemberid == ""){
				$window.alert("Please select member!");
				document.getElementById("metmemberid").focus();
				return;
			} else if($scope.invitedbymemberid == undefined){
				$window.alert("Please select invited by member!");
				document.getElementById("invitedbymemberid").focus();
				return;
			} else if($scope.meetingdate == undefined){
				$window.alert("Please select meeting date!");
				document.getElementById("meetingdate").focus();
				return;
			} else if($scope.location == undefined){
				$window.alert("Please enter meeting location!");
				document.getElementById("location").focus();
				return;
			} else if($scope.topic == undefined){
				$window.alert("Please enter topics of conversation!");
				document.getElementById("topic").focus();
				return;
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'saveOnetoOne?metmemberid='+$scope.metmemberid+'&invitedbymemberid='+$scope.invitedbymemberid+'&meetingdate='+$scope.meetingdate+'&location='+$scope.location+'&topic='+$scope.topic;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.saveonetoone = data;
					$scope.spin = 0;					
					$window.alert("Data submitted successfully");
					if(temp == "new"){
						$window.location.href = url + 'one_to_one';
					} else {
						$window.location.href = url;
					};					
				}).error(function(data, status, headers, config) {
					$scope.saveonetoone = "Response Fail";
				});
			}
		};	*/	
	});