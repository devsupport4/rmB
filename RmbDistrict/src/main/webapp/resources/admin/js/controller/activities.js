app.controller('memberActivitiesCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;	
		
		
		$scope.getMemberActivitiesDetailById = function(memberid) {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;
			
			var link = baseUrl+'getMemberReferencesAndBusinessAndMeetingById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberActivities = data;
				var a = $scope.getMemberActivities.split("-");
				$scope.noofreference = a[0];
				$scope.businessamount = a[1];
				$scope.noofmeeting = a[2];
				/*var link = baseUrl+'getMemberMeetingsById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMemberMeetings = data;			
				}).error(function(data, status, headers, config) {
					$scope.getMemberMeetings = "Response Fail";
				});*/
			}).error(function(data, status, headers, config) {
				$scope.getMemberActivities = "Response Fail";
			});
			
			var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetail = data;
				
				$scope.firstname = $scope.getmemberdetail.memberFirstName;
				$scope.lastname = $scope.getmemberdetail.memberLastName;
				$scope.businesscategoryname = $scope.getmemberdetail.businessCategoryName;
				$scope.memberemail = $scope.getmemberdetail.memberEmail;
				$scope.membermobileno = $scope.getmemberdetail.memberMobileNumber;
				$scope.profilepic = $scope.getmemberdetail.memberProfilePicture;
				
			}).error(function(data, status, headers, config) {
				$scope.getmemberdetail = "Response Fail";
			});			
			
					
		}
		
		$scope.getMemberActivitiesByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;

			var link = baseUrl+'getMemberReferencesAndBusinessAndMeetingById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberActivities = data;
				var a = $scope.getMemberActivities.split("-");
				$scope.noofreference = a[0];
				$scope.businessamount = a[1];
				$scope.noofmeeting = a[2];
			}).error(function(data, status, headers, config) {
				$scope.getMemberActivities = "Response Fail";
			});
		}
		
		
		
		
		/*$scope.getMembersActivitiesByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;

			var link = baseUrl+'getMemberReferencesAndBusinessAndMeetingByDate?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;
			$window.alert(link);
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMembersActivities = data;				
			}).error(function(data, status, headers, config) {
				$scope.getMembersActivities = "Response Fail";
			});
		}*/
		
		
		
		
});