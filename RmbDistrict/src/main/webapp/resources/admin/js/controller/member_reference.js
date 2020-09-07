app.controller('memberReferralCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;		
		
		
		$scope.reasonError = 1;
		$scope.spin = 0;
		$scope.getMembersReferencesById = function(memberid,fromdate,todate) {	
			/*$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;*/		
			$scope.fromdate = fromdate;
			$scope.todate = todate;
			/*document.getElementById("datepicker").value = fromdate;
			document.getElementById("datepicker1").value = todate;*/
					
			var link = baseUrl+'getMemberReferencesById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferences = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferences = "Response Fail";
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
		
		$scope.getMemberReferencesById = function(memberid) {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;			
								
			var link = baseUrl+'getMemberReferencesById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferences = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferences = "Response Fail";
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
		
		$scope.getReferencesByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getMemberReferencesById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;		
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferences = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferences = "Response Fail";
			});
		}
		
		
		
		
		
		$scope.getMembersReferralsById = function(memberid,fromdate,todate) {	
			/*$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;*/		
			$scope.fromdate = fromdate;
			$scope.todate = todate;
			/*document.getElementById("datepicker").value = fromdate;
			document.getElementById("datepicker1").value = todate;*/
					
			var link = baseUrl+'getMemberReferralsById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferrals = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferrals = "Response Fail";
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
		
		$scope.getMemberReferralsById = function(memberid) {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;			
								
			var link = baseUrl+'getMemberReferralsById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferrals = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferrals = "Response Fail";
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
		
		$scope.getReferralsByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getMemberReferralsById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;		
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferrals = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferrals = "Response Fail";
			});
		}
		
		
		$scope.getReferenceDetail = function(memberreferralid) 
		{			
			var link = baseUrl + 'getReferenceDetailById?memberreferralid='+memberreferralid;				
			$http.get(link).success(function(data, status, headers, config) { 
				$scope.getReferencebyid = data;
				$scope.getReferencebyid.CloseReason="";
			}).error(function(data, status, headers, config) {
				$scope.getReferencebyid = "Response Fail";
			});	
		}
		
		$scope.ChangeRefStatus = function(memberreferralid,status){
			if(status != "C")
			{
				var link = baseUrl + 'ChangeRefStatus?memberreferralid='+memberreferralid+'&status='+status;				
				$http.post(link).success(function(data, status, headers, config) { 
					$scope.getReferencebyid = data;
					document.location.reload();
				}).error(function(data, status, headers, config) {
					$scope.getReferencebyid = "Response Fail";
				});
			}
				
		}
		
		$scope.CloseRef = function(memberreferralid){
			if(!$scope.getReferencebyid.CloseComment)
			{
				$scope.getReferencebyid.CloseComment="";
			}
			if(!$scope.getReferencebyid.CloseReason)
			{
				$scope.reasonError = 1;
				$scope.reasonMsg = "Reason is mandatory";
				return;
			}
			else {
				$scope.spin = 1;
				var link = baseUrl + 'CloseRef?memberreferralid='+memberreferralid+'&closeComment='+$scope.getReferencebyid.CloseComment+'&closeReason='+$scope.getReferencebyid.CloseReason;				
				$http.post(link).success(function(data, status, headers, config) { 
					$scope.closeref = data;
					document.location.reload();
				}).error(function(data, status, headers, config) {
					$scope.closeref = "Response Fail";
				});
			}
				
		}
	});