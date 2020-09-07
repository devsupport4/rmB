app.controller('membersDirectoryCtrl', function($scope, $http, $window, $filter, $location, $timeout) {
	var baseUrl = $location.protocol() + "://" + location.host + u;	
				
	$scope.currentPage = 0;
    $scope.pageSize = 24;
    $scope.search = '';	 
    $scope.startindex = $scope.currentPage;   
				
	$scope.getData = function() {
		return $filter('filter')($scope.data,$scope.search)
	}
	
	$scope.numberOfPages = function() {
		return Math.ceil($scope.boardofdirectors.length/ $scope.pageSize);
	}
			    
	var link = baseUrl+'getMemberForMembersDirectoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.currentPage;
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getMemberForMembersDirectory = data;
		$scope.a = 1;
	}).error(function(data, status, headers, config) {
		$scope.getMemberForMembersDirectory = "Response Fail";
	});
	
	
	$scope.next = function() {
		$scope.currentPage = $scope.currentPage + 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		
		$scope.a = 0;
		
		var link = baseUrl+'getMemberForMembersDirectoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMemberForMembersDirectory = data;
			$scope.a = 1;
		}).error(function(data, status, headers, config) {
			$scope.getMemberForMembersDirectory = "Response Fail";
		});								
	}
				
	$scope.prev = function() {
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		$scope.a = 0;
		
		var link = baseUrl+'getMemberForMembersDirectoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMemberForMembersDirectory = data;
			$scope.a = 1;
		}).error(function(data, status, headers, config) {
			$scope.getMemberForMembersDirectory = "Response Fail";
		});			
	}
				
	$scope.searchmember = function() {		
		var search = $scope.search;
		if(search == undefined || search == "") {
			$scope.a = 0;
			var link = baseUrl+'getMemberForMembersDirectoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberForMembersDirectory = data;
				$scope.a = 1;
			}).error(function(data, status, headers, config) {
				$scope.getMemberForMembersDirectory = "Response Fail";
			});
		} else {
			$scope.a = 0;
			var link = baseUrl+'searchMembersForMembersDirectory?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {				
				$scope.getMemberForMembersDirectory = data;
				$scope.a = 1;
			}).error(function(data, status, headers, config) {
				$scope.getMemberForMembersDirectory = "Response Fail";
			});
		}
	}
	
	$scope.redirectToMemberProfile = function(id) {					
		$window.location.href = url + 'member_profile?id='+id;
	}
	
	$scope.getAllMemberDirectory = function () {			
		var link = baseUrl + 'getAllMembersDirectory';						
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMemberdirectory = data;				
		}).error(function(data, status, headers, config) {
			$scope.getMemberdirectory = "Response Fail";
		});			 
    }
	
	
	
	
	$scope.getMemberDetailById = function(id) {					
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+id;					
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetail = data;
			$scope.memberid = $scope.getmemberdetail.memberId;
			$scope.firstname = $scope.getmemberdetail.memberFirstName;
			$scope.lastname = $scope.getmemberdetail.memberLastName;							
									
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
		
		var link = baseUrl+'getspousedata?memberid='+id;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getspousedata = data;
		}).error(function(data,status,headers,config) {
			$scope.getspousedata = "Response Fail";
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
});