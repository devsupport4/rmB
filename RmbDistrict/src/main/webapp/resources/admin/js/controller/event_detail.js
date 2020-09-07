app.controller('eventDetailCtrl', function($scope, $http, $window, $filter,
		$location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.getEventDetailById = function(id, memberid) {
		var link = baseUrl + 'getEventDetailById?id='+id;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.geteventdetailbyid = data;
			$scope.reqpaid = $scope.geteventdetailbyid.paid;
		}).error(function(data, status, headers, config) {
			$scope.geteventdetailbyid = "Response Fail";
		});
		
		var link = baseUrl + 'getEventChargesList?eventid='+id;			
		$http.get(link).success(function(data, status, headers, config) {
			$scope.geteventchargesbyid = data;
		}).error(function(data, status, headers, config) {
			$scope.geteventchargesbyid = "Response Fail";
		});
		
		var link = baseUrl+'getRelatedEventAgenda?eventid='+id;					
		$http.get(link).success(function(data, status, headers,config) {
			$scope.eventrelatedagenda = data;
		}).error(function(data, status, headers,config) {
			$scope.eventrelatedagenda = "Response Fail";
		});
		
		var link = baseUrl+'getRelatedEventImages?eventid='+id;
		$http.get(link).success(function(data, status, headers,config) {
			$scope.eventrelatedimages = data;
		}).error(function(data, status, headers,config) {
			$scope.eventrelatedimages = "Response Fail";
		});
		
		var link = baseUrl+'getRelatedEventUrl?eventid='+id;
		$http.get(link).success(function(data, status, headers,config) {
			$scope.eventrelatedurl = data;
		}).error(function(data, status, headers,config) {
			$scope.eventrelatedurl = "Response Fail";
		});
		
		/*var link = baseUrl+'getMemberByMemberId?memberid='+memberid;					
		$http.get(link).success(function(data, status, headers, config) {						
			$scope.getmember = data;					
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";						
		});*/
		
		var link = baseUrl + 'getMemberEventPaymentStatus?id='+id+'&memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmembereventPay = data;
			$scope.paymentStatus = $scope.getmembereventPay.paymentStatu;
			console.log("payment:"+$scope.paymentStatus);
		}).error(function(data, status, headers, config) {
			$scope.getmembereventresponse = "Response Fail";
		});
		
		var link = baseUrl + 'getMemberEventRegistrationStatus?id='+id+'&memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmembereventReg = data;
			if(!$scope.getmembereventReg){
				$scope.registrationStatus = "Failure";
			}
			else{
				$scope.registrationStatus = "Success";
			}
			if($scope.getmembereventReg.paymentStatu == "Paid"){
				$scope.paymentStatus = "Success";
				console.log("registration free"+$scope.paymentStatus);
			}
			if($scope.getmembereventReg.paymentStatu == "Success"){
				$scope.paymentStatus = "Success";
				console.log("registration free"+$scope.paymentStatus);
			}
		}).error(function(data, status, headers, config) {
			$scope.getmembereventresponse = "Response Fail";
		});
	}
	
	$scope.redirectToPayNowPage = function(memberid, eid){
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetails = data;
			
			$scope.membertype = $scope.getmemberdetails.memberType;
			
			if(!$scope.membertype){
				$scope.membertype="RMBFB Member";
			}
			$window.location.href = url+'pay_now?eventid='+eid;
			
		}).error(function(data,status,headers,config) {
			$scope.getmemberdetails = "Response Fail";
		});
	}
	
	$scope.redirectToRegisteredMEmbersDirectory = function(eid){
		$window.location.href = url+'registered_members_directory?event_id='+eid;
	}
	
	$scope.redirectToRegisterpage = function(memberid, eid){
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberdetails = data;
			
			$scope.membertype = $scope.getmemberdetails.memberType;
			
			if(!$scope.membertype){
				$scope.membertype="RMBFB Member";
			}
			$window.location.href = url+'registration?membertype='+$scope.membertype+'&eid='+eid;
			
		}).error(function(data,status,headers,config) {
			$scope.getmemberdetails = "Response Fail";
		});
	}

	
	$scope.redirectToNextPage = function(membertype, eid){
		if(membertype=="RMBFB Member"){
			$window.location.href = url+'login_member?membertype='+membertype+'&eid='+eid;
		} else {
			//$window.location.href = url+'get_registered?membertype='+membertype+'&eid='+eid;
			$window.location.href = url+'registration?membertype='+membertype+'&eid='+eid;
		}
	}
	
	$scope.checkMemberLogin = function(eid,membertype) {
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
				if (data != "Successfully Login") {
					$window.alert("User Name or Password Incorrect...Try Again");
				} else {$window.location.href = url+'registered_members_directory?event_id='+eid;
				}
			}).error(function(data, status, headers,config) {
					$window.alert("Some thing wrong...Try again");
			});	
						
		}
	}
	
});