app.controller('payNowCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		
		$scope.setValueRadio = function(value){
			$scope.hidebankdetails = value;
		}
		
		
		
		
		$scope.savePaymentDetails = function(mid,eid, paymentrefno,amount){
			
			if(!paymentrefno){
				$scope.errorPaymentNo = 1;
				$scope.msgPaymentNo = "Please enter Payment Reference Number";
				document.getElementById("paymentrefno").focus();
				
				
			} else {
			
				$scope.paymentspin=1;
				var link = baseUrl+'updateEventPayment?memberid='+mid+'&eid='+eid+'&paymentrefno='+paymentrefno+'&amount='+amount;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.saveref = data;
					$scope.paymentspin=0;				
					$window.location.reload();
				}).error(function(data, status, headers, config) {
					$scope.saveref = "Response Fail";
				});
			}
		}
		
		
		$scope.getEventDetailsForPay = function(eventid,mid){
			
			var link = baseUrl + 'getEventDetailById?id='+eventid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventdetailbyid = data;
			}).error(function(data, status, headers, config) {
				$scope.geteventdetailbyid = "Response Fail";
			});
									
			var link = baseUrl + 'getEventRegistrationDetails?eventid='+eventid+'&mid='+mid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventchargesbyid = data;
				
				$scope.amount = $scope.geteventchargesbyid.amount;
				$scope.paymentstatus = $scope.geteventchargesbyid.paymentStatu;
				if(!$scope.amount || $scope.amount==0){
					var link = baseUrl+'updateEventRegistrationPayment?memberid='+mid+'&eid='+eventid;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.saveref = data;
						$scope.paymentspin=0;
					}).error(function(data, status, headers, config) {
						$scope.saveref = "Response Fail";
					});
					
					/*var link = baseUrl+'sendEventPaymentEmail?memberid='+mid;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.sendemail = data;
					}).error(function(data, status, headers, config) {
						$scope.sendemail = "Response Fail";
					});*/
					
				}
			}).error(function(data, status, headers, config) {
				$scope.geteventchargesbyid = "Response Fail";
			});
			
			var link = baseUrl+'getMemberDetailByMemberId?memberid='+mid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetails = data;
				if(!$scope.countryname){
					$scope.countryname=1;
				}
				$scope.firstnameadd = $scope.getmemberdetails.memberFirstName;
				$scope.lastnameadd = $scope.getmemberdetails.memberLastName;
				$scope.genderadd =  $scope.getmemberdetails.memberGender;
				$scope.emailadd =  $scope.getmemberdetails.memberEmail;
				$scope.mobilenoadd =  $scope.getmemberdetails.memberMobileNumber;
				//$scope.rtmemberadd =  $scope.getmemberdetails.membershipNumber;
				//$scope.rtclubadd =  $scope.getmemberdetails.membershipNumber;
				$scope.rtcmpadd =  $scope.getmemberdetails.memberCompanyName;
				$scope.rtwebsiteadd =  $scope.getmemberdetails.memberWebsiteName;
				$scope.shortdescadd =  $scope.getmemberdetails.memberCompanyDescription;
				$scope.businessclsadd =  $scope.getmemberdetails.businessGoals;
				$scope.address1 =  $scope.getmemberdetails.memberAddress1;
				$scope.address2 =  $scope.getmemberdetails.memberAddress2;
				$scope.statename = $scope.getmemberdetails.memberStateId;
				$scope.cityname = $scope.getmemberdetails.memberCityName;
				$scope.pincode = $scope.getmemberdetails.memberPincode;
				
			}).error(function(data,status,headers,config) {
				$scope.getmemberdetails = "Response Fail";
			});
			
			
			
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
			$scope.errorPaymentNo=0;
			$scope.errorAddress1 = 0;
			$scope.errorCountry = 0;
			$scope.errorState = 0;
			$scope.errorCity = 0;
			$scope.errorPincode = 0;
		
		}
		
		var link = baseUrl+'getCountry';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcountry = data;
		}).error(function(data,status,headers,config) {
			$scope.getcountry = "Response Fail";
		});
		
		
		$scope.onCountryChange = function(){
			var link = baseUrl+'GetRelatedState?countryid='+$scope.countryname;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getrelatedstate = data;
			}).error(function(data,status,headers,config) {
				$scope.getrelatedstate = "Response Fail";
			});
		}
		
				
		$scope.payPaymentOnline = function(eventid,mid){
			
									
			if(!$scope.address2) {
				$scope.address2 = "";
			}
						
			if(!$scope.firstnameadd) {			
				$scope.errorFirstName = 1;
				$scope.msgFirstName = "Please enter first name";
				document.getElementById("firstnameadd").focus();			
			} else if(!$scope.lastnameadd) {			
				$scope.errorLastName = 1;
				$scope.msgLastName = "Please enter last name";
				document.getElementById("lastnameadd").focus();			
			} else if(!$scope.emailadd) {			
				$scope.errorEmail = 1;
				$scope.msgEmail = "Please enter email address";
				document.getElementById("emailadd").focus();			
			} else if(!$scope.mobilenoadd) {			
				$scope.errorMobileno = 1;
				$scope.msgMobileno = "Please enter Mobile number";
				document.getElementById("mobilenoadd").focus();
			} else if(!$scope.address1) {			
				$scope.errorAddress1 = 1;
				$scope.msgAddress1 = "Please enter address1";
				document.getElementById("address1").focus();
			} else if(!$scope.countryname) {			
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countryname").focus();			
			} else if(!$scope.statename) {			
				$scope.errorState = 1;
				$scope.msgState = "Please select state";
				document.getElementById("statename").focus();			
			} else if(!$scope.cityname) {			
				$scope.errorCity = 1;
				$scope.msgCity = "Please enter city";
				document.getElementById("cityname").focus();			
			} else if(!$scope.pincode) {			
				$scope.errorPincode = 1;
				$scope.msgPincode = "Please enter pincode";
				document.getElementById("pincode").focus();			
			} else {
				
			$scope.spin = 1;
			var link = baseUrl+'payPaymentOnline?eventid='+eventid+'&memberid='+mid+'&firstname='+$scope.firstnameadd+'&lastname='+$scope.lastnameadd+'&email='+$scope.emailadd+'&mobileno='+$scope.mobilenoadd+'&address1='+$scope.address1+'&address2='+$scope.address2+'&countryname='+$scope.countryname+'&statename='+$scope.statename+'&cityname='+$scope.cityname+'&pincode='+$scope.pincode+'&amount='+$scope.amount;			
    		
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addorder = data;
				$scope.spin =0;
				$window.location.href = url+'dataform';
    		}).error(function(data, status, headers, config) {
				$scope.addorder = data;
				$scope.spin = 0;
				$scope.userSubmitError = 1;
				$scope.msgError = $scope.addorder;				
			});
			}
			
		}
		
		
		
		
		
	});