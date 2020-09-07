app.controller('wallPostCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;	
	
	var link = baseUrl+'getAllMemberPost';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allmemberpost = data;				
	}).error(function(data, status, headers, config) {
		$scope.allmemberpost = "Response Fail";
	});
	
	var link = baseUrl+'getLastEightMembersPics';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getLastEightMembersPics = data;			
	}).error(function(data, status, headers, config) {
		$scope.getLastEightMembersPics = "Response Fail";
	});
	
	$scope.getCurrentDefaultYear = function () {				
		var link = baseUrl + 'getClubInfo';
		$http.get(link).success(function(data,status,headers,config){
			$scope.clubinfo = data;
			
			$scope.title = $scope.clubinfo.clubTitle;
			$scope.shorttitle = $scope.clubinfo.clubShortitle;
			$scope.clublogo = $scope.clubinfo.clubLogo;
			$scope.add1 = $scope.clubinfo.meetingAddress1;
			$scope.add2 = $scope.clubinfo.meetingAddress2;
			$scope.day = $scope.clubinfo.meetingDay;
			$scope.time = $scope.clubinfo.meetingTime;
			$scope.map = $scope.clubinfo.mapLink;
			
			var link = baseUrl+'getLastTenCategoriesForHome';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getLastTenCategoriesForHome = data;
				
				var todaydate = $filter('date')(new Date(),'yyyy-MM-dd'); 
				var link = baseUrl + 'getLastThreeEventsByDate?todaydate='+todaydate;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getlastthreeeventsbydate = data;
				}).error(function(data, status, headers, config) {
					$scope.getlastthreeeventsbydate = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.getLastTenCategoriesForHome = "Response Fail";
			});
								
		}).error(function(data,status,headers,config){
			$scope.clubinfo = "Responce Fail";
		});				    
	}
	
	$scope.getBirthdaysAndAnniversariesByDate = function () {
		var currentdate = $filter('date')(new Date(),'MM-dd');
			
		var link = baseUrl+'getFistFourBirthdaysByDate?currentdate='+currentdate;					
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getfirstfourbirthdaysbydate = data;			
		}).error(function(data, status, headers, config) {
			$scope.getfirstfourbirthdaysbydate = "Response Fail";
		});
			
		/*var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getallanniversariesbydate = data;			
		}).error(function(data, status, headers, config) {
			$scope.getallanniversariesbydate = "Response Fail";
		});*/
    }
	
	$scope.showAlert = function() {
		$window.alert("Please login in order to post your message!");
		$window.location.href = url+'login';
	}
	
	$scope.createNewPost = function() {
		if($scope.memberpost == undefined) {
			$window.alert("Please enter some text");
			document.getElementById("memberpost").focus();
			return;
		} else {
			var link = baseUrl+'addNewMemberPost?memberpost='+$scope.memberpost;					
			$http.post(link).success(function(data, status, headers, config) {
				$scope.newmemberpost = data;
				$scope.memberpost = "";
				var link = baseUrl+'getAllMemberPost';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.allmemberpost = data;			
				}).error(function(data, status, headers, config) {
					$scope.allmemberpost = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.newmemberpost = "Response Fail";
			});
		}
	}
});