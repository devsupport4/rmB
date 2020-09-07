app.controller('registeredCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
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
		
		}
		
		var link = baseUrl+'getCountry';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcountry = data;
		}).error(function(data,status,headers,config) {
			$scope.getcountry = "Response Fail";
		});
		
		$scope.signup = function(membertype,eid) {
			
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
			if(!$scope.rtcmpadd)
			{
				$scope.rtcmpadd="";
			}
			if(!$scope.businessclsadd)
			{
				$scope.businessclsadd="";
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
			} else if(!$scope.password) {			
				$scope.errorPassword = 1;
				$scope.msgPassword = "Please enter Password";
				document.getElementById("password").focus();
			} else if(!$scope.cpassword) {			
				$scope.errorCPassword = 1;
				$scope.msgCPassword = "Please enter Confirm Password";
				document.getElementById("cpassword").focus();
			} else if($scope.password!=$scope.cpassword) {			
				$scope.errorCPassword = 1;
				$scope.msgCPassword = "Please enter Confirm Password same as Password";
				document.getElementById("cpassword").focus();
			} else {
				if($scope.rotarianadd==true)
				{
				var rotstatus="y";
				}
			else
				{
				var rotstatus="n";
				}
			$scope.spin = 1;
			var link = baseUrl+'addRotaryUser?membertype='+membertype+'&firstname='+$scope.firstnameadd+'&lastname='+$scope.lastnameadd+'&gender='+$scope.genderadd+'&email='+$scope.emailadd+'&mobileno='+$scope.mobilenoadd+'&rotarian='+rotstatus+'&rmemberid='+$scope.rtmemberadd+'&rclubname='+$scope.rtclubadd+'&rchapname='+$scope.rtchapadd+'&cmpname='+$scope.rtcmpadd+'&cmpwebsite='+$scope.rtwebsiteadd+'&countryid='+$scope.countryidadd+'&cmpshortdesc='+$scope.shortdescadd+'&cmpbusiness='+$scope.businessclsadd+'&password='+$scope.password;
			$http.post(link).success(function(data, status, headers, config) {
    			$scope.addrotaryuser = data;
    			if($scope.addrotaryuser == "Success") {
    				$scope.spin = 0;
    				$scope.userSubmitSuccess = 1;
    				$scope.msgSuccess = "You are successfully registered.";
    				$timeout(function() {
    					$window.location.href = url+'login_member?eid='+eid;		
    				}, 2000);
    			} else {
    				$scope.spin = 0;
    				$scope.userSubmitError = 1;
    				$scope.msgError = $scope.addrotaryuser;
    				$timeout(function() {
    					$scope.userSubmitError = 0;		
    				}, 4000);
    			}
    		}).error(function(data, status, headers, config) {
				$scope.addrotaryuser = data;
				$scope.spin = 0;
				$scope.userSubmitError = 1;
				$scope.msgError = $scope.addrotaryuser;				
			});
			}
		}
	});