app.controller('registrationCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		function validateemail()  
		{  
		var x=document.getElementById("emailadd").value;  
		var atposition=x.indexOf("@");  
		var dotposition=x.lastIndexOf(".");  
		if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length){  
		  alert("Please enter a valid e-mail address \n atpostion:"+atposition+"\n dotposition:"+dotposition);  
		  return false;  
		  }  
		
		}  
		$window.onload = () => {
			  Array.from(document.querySelector(`#countryidadd`).options)
			    .filter(x => x.value == "1")[0].setAttribute('selected', true);
			};
			
		$scope.getLogedinMemberDetails = function(mid, eid){
			
			var link = baseUrl+'getMemberDetailByMemberId?memberid='+mid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetails = data;
				
				$scope.membertype = $scope.getmemberdetails.memberType;
				$scope.firstnameadd = $scope.getmemberdetails.memberFirstName;
				$scope.lastnameadd = $scope.getmemberdetails.memberLastName;
				$scope.genderadd =  $scope.getmemberdetails.memberGender;
				$scope.emailadd =  $scope.getmemberdetails.memberEmail;
				$scope.mobilenoadd =  $scope.getmemberdetails.memberMobileNumber;
				$scope.rtmemberadd =  $scope.getmemberdetails.rotaryMemberId;
				
				if($scope.membertype=="RMBFB Member" || !$scope.membertype ){
					$scope.rtclubadd =  $scope.getmemberdetails.clubName;
				} else {
					$scope.rtclubadd =  $scope.getmemberdetails.rotaryClubName;
				}
								
				$scope.rtchapadd =  $scope.getmemberdetails.rotaryChapName;
				$scope.rtcmpadd =  $scope.getmemberdetails.memberCompanyName;
				$scope.rtwebsiteadd =  $scope.getmemberdetails.memberWebsiteName;
				$scope.shortdescadd =  $scope.getmemberdetails.memberCompanyDescription;
				$scope.businessclsadd =  $scope.getmemberdetails.businessGoals;
				
			}).error(function(data,status,headers,config) {
				$scope.getmemberdetails = "Response Fail";
			});
			
			var link = baseUrl + 'getEventDetailById?id='+eid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventdetailbyid = data;
				$scope.evnttitle = $scope.geteventdetailbyid.eventTitle;
			}).error(function(data, status, headers, config) {
				$scope.geteventdetailbyid = "Response Fail";
			});
			var link = baseUrl + 'getEventRegistrationDetails?eventid='+eid+'&mid='+mid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventchargesbyid = data;
				$scope.paymentstatus = $scope.geteventchargesbyid.paymentStatu;
				$scope.evntregid = $scope.geteventchargesbyid.eventRegsitrationId;
				
			}).error(function(data, status, headers, config) {
				$scope.geteventchargesbyid = "Response Fail";
			});
		}
		
		$scope.redirectToLoginPage = function(eid,membertype){
			$window.location.href = url+'login_member?membertype='+membertype+'&eid='+eid;
		}
		
		$scope.setFlag = function() {
		
			$scope.errorFirstName = 0;		
			$scope.msgFirstName = "";
			$scope.errorLastName = 0;
			$scope.msgLastName = "";
			$scope.errorMobileno = 0;
			$scope.msgMobileno = "";
			$scope.errorEmail = 0;
			$scope.msgEmail = "";
			$scope.errorGender = 0;
			$scope.msgGender = "";
			
			$scope.errorCompany=0;
			$scope.msgCompany="";
			$scope.errorBusiness=0;
			$scope.msgBusiness="";
			$scope.errorType=0;
			$scope.errorPassword = 0;
			$scope.errorCPassword = 0;
			$scope.userSubmitError = 0;
		
		}
		
		var link = baseUrl+'getCountry';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcountry = data;
		}).error(function(data,status,headers,config) {
			$scope.getcountry = "Response Fail";
		});
		
		$scope.registration = function(eventid, id,membertype) {
						
			var link = baseUrl + 'getEventDetailById?id='+eventid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventdetailbyid = data;
				$scope.reqpaid = $scope.geteventdetailbyid.paid;
			}).error(function(data, status, headers, config) {
				$scope.geteventdetailbyid = "Response Fail";
			});			
			
			$scope.membertype = membertype;
			$scope.memberid = id;
			
			if(!$scope.memberid){
				$scope.memberid=0;
			}
			if(!$scope.evntregid){
				$scope.evntregid=0;
			}
						
			if($scope.evntregid !=0){
				$scope.userSubmitError1 = 1;
				$scope.msgError = "You are already registerd for this event";
				$timeout(function() {
					$scope.userSubmitError1 = 0;
				}, 2000);
				return;
				
			}
			
			
			if(!$scope.rotarianadd) {
				$scope.rotarianadd = "";
			}
			if(!$scope.rtmemberadd) {
				$scope.rtmemberadd = "";
			}
			if(!$scope.rtclubadd) {
				$scope.rtclubadd = "";
			}
			if(!$scope.rtchapadd) {
				$scope.rtchapadd = "";
			}
			if(!$scope.rtwebsiteadd)
			{
				$scope.rtwebsiteadd="";
			}
			if(!$scope.countryidadd)
			{
				$scope.countryidadd=0;
			}
			if(!$scope.shortdescadd)
			{
				$scope.shortdescadd="";
			}
			if(!$scope.rtcmpadd){
				$scope.rtcmpadd="";
			}
			if(!$scope.businessclsadd){
				$scope.businessclsadd="";
			}
			if(!$scope.password){
				$scope.password="";
			}
			if(!$scope.cpassword){
				$scope.cpassword="";
			}
			
			if(!$scope.firstnameadd) {			
				$scope.errorFirstName = 1;
				$scope.msgFirstName = "Please enter first name";
				document.getElementById("firstnameadd").focus();			
			} else if(!$scope.lastnameadd) {			
				$scope.errorLastName = 1;
				$scope.msgLastName = "Please enter last name";
				document.getElementById("lastnameadd").focus();			
			} else if(!$scope.genderadd) {			
				$scope.errorGender = 1;
				$scope.msgGender = "Please select gender";
				document.getElementById("genderadd").focus();			
			} else if(!$scope.emailadd) {			
				$scope.errorEmail = 1;
				$scope.msgEmail = "Please enter email address";
				document.getElementById("emailadd").focus();			
			} else if(!$scope.mobilenoadd) {			
				$scope.errorMobileno = 1;
				$scope.msgMobileno = "Please enter Mobile number";
				document.getElementById("mobilenoadd").focus();
			} else if(!$scope.password && $scope.membertype !="RMBFB Member" && !$scope.memberid) {			
				$scope.errorPassword = 1;
				$scope.msgPassword = "Please enter Password";
				document.getElementById("password").focus();
			} else if(!$scope.cpassword && $scope.membertype !="RMBFB Member" && !$scope.memberid) {			
				$scope.errorCPassword = 1;
				$scope.msgCPassword = "Please enter Confirm Password";
				document.getElementById("cpassword").focus();
			} else if($scope.password!=$scope.cpassword && $scope.membertype !="RMBFB Member" && !$scope.memberid) {			
				$scope.errorCPassword = 1;
				$scope.msgCPassword = "Please enter Confirm Password same as Password";
				document.getElementById("cpassword").focus();
			} else {
				
				var rotstatus="y";
				
				$scope.spin = 1;
				var link = baseUrl+'eventRegistration?eventid='+eventid+'&membertype='+membertype+'&firstname='+$scope.firstnameadd+'&lastname='+$scope.lastnameadd+'&gender='+$scope.genderadd+'&email='+$scope.emailadd+'&mobileno='+$scope.mobilenoadd+'&rotarian='+rotstatus+'&rmemberid='+$scope.rtmemberadd+'&rclubname='+$scope.rtclubadd+'&rchapname='+$scope.rtchapadd+'&cmpname='+$scope.rtcmpadd+'&cmpwebsite='+$scope.rtwebsiteadd+'&countryid='+$scope.countryidadd+'&cmpshortdesc='+$scope.shortdescadd+'&cmpbusiness='+$scope.businessclsadd+'&memberid='+$scope.memberid+'&password='+$scope.password;			
	    		
				$http.post(link).success(function(data, status, headers, config) {
	    			$scope.addrotaryuser = data;
	    			if($scope.addrotaryuser == "Success") {
	    				$scope.spin = 0;
	    				    				
	    				$scope.userSubmitSuccess = 1;
	    				$scope.msgSuccess = "You are successfully registered for this event.Thank You";
						/*if($scope.reqpaid=="Yes"){
							$window.location.href = url+'pay_now?eventid='+eventid;
						} else {
							$window.location.href = url+'';
						}*/
	    				/*var link = baseUrl + 'frontLoginForRegistration?email=' +$scope.emailadd + '&password=' + $scope.password;
	    				$http.post(link).success(function(data, status, headers,config) {
	    					if (data == "") {
	    						$window.alert("User Name or Password Incorrect...Try Again");
	    					} else{
	    						$scope.userSubmitSuccess = 1;
	    	    				$scope.msgSuccess = "You are successfully registered for this event.Thank You";
	    						if($scope.reqpaid=="Yes"){
	        						$window.location.href = url+'pay_now?eventid='+eventid;
	        					} else {
	        						$window.location.href = url+'';
	        					}
	    					}
	    				}).error(function(data, status, headers,config) {
	    						$window.alert("Some thing wrong...Try again");
	    				});*/
	    				
	    				
	    				
	    				$timeout(function() {
	    					if($scope.reqpaid=="Yes"){
	    						alert("Thank you for registering");
	    						$window.location.href = url+'pay_now?eventid='+eventid;
	    					} else if($scope.reqpaid=="No"){
	    						alert("Thank you for registering");
	    						$window.location.href = url+'';
	    					}  else {
	    						$scope.userSubmitError = 1;
	    						$scope.msgError = "Something went wrong, while redirecting Please Try again!";
	    						$window.location.href = url+'';
	    					}
	    				}, 2000);
	    			} else if($scope.addrotaryuser == "Unsuccess") {
	    				$scope.spin = 0;
	    				$scope.userSubmitError = 1;
	    				$scope.msgError = "It seems you already have an account with us, please login";
	    			} else {
	    				$scope.spin = 0;
	    				$scope.userSubmitError = 1;
	    				$scope.msgError = "Something went Wrong! Please Try again" + $scope.addrotaryuser ;
	    			}
	    		}).error(function(data, status, headers, config) {
					$scope.addrotaryuser = data;
					$scope.spin = 0;
					$scope.userSubmitError = 1;
					$scope.msgError = $scope.addrotaryuser;				
				});
			}
		}
				
		$scope.checkloginin = function(username,password,eid) {				
					
						
			loggedin = "n";
		
			var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password+'&loggedin='+loggedin;
			$http.post(link).success(function(data, status, headers,config) {
				if (data == "") {
					$window.alert("User Name or Password Incorrect...Try Again");
				} 
			}).error(function(data, status, headers,config) {
					$window.alert("Some thing wrong...Try again");
			});
			
		}
		
		
		
	});