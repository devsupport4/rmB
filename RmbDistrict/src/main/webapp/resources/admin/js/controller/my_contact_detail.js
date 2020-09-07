app.controller('manageContactDetailCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	var link = baseUrl+'getCountry';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getcountry = data;
	}).error(function(data, status, headers, config) {
		$scope.country = "Response Fail";
	});
	
	$scope.getFellowship_id = function(fellowship_id){
		$scope.fellowship_id=fellowship_id;
		
	}
	
	var link = baseUrl+'getState';
	$http.get(link).success( function(data, status, headers, config) {
		$scope.getrelatedstate = data;
	}).error(function(data, status, headers, config) {
		$scope.getrelatedstate = "Response Fail";
	});
	
	$scope.getcontactdetail = function(memberid) {		
		var link = baseUrl+'getMemberResidentialLandline?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberresidentiallandline = data;
		}).error(function(data, status, headers, config) {
			$scope.getmemberresidentiallandline = "Response Fail";
		});
		
		var link = baseUrl+'getMemberWorkLandline?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmemberworklandline = data;
		}).error(function(data, status, headers, config) {
			$scope.getmemberworklandline = "Response Fail";
		});

		var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
		$http.get(link).success( function(data, status, headers, config) {
			$scope.getmember = data;
			
			$scope.memberid = $scope.getmember.memberId;
			$scope.membershipnumber = $scope.getmember.membershipNumber;						
			$scope.membercategoryid = $scope.getmember.memberCategoryId;
			$scope.companydetailid = $scope.getmember.companyDetailId;
			$scope.memberfirstname = $scope.getmember.memberFirstName;
			$scope.membermiddlename = $scope.getmember.memberMiddleName;
			$scope.memberlastname = $scope.getmember.memberLastName;
			$scope.address1 = $scope.getmember.memberAddress1;
			$scope.address2 = $scope.getmember.memberAddress2;
			$scope.address3 = $scope.getmember.memberAddress3;
			$scope.statename = $scope.getmember.memberStateId;
			$scope.cityname = $scope.getmember.memberCityName;
			$scope.pincode = $scope.getmember.memberPincode;
			$scope.mobilenumber = $scope.getmember.memberMobileNumber;
			$scope.logoimage = $scope.getmember.companyLogo;
			$scope.occupation = $scope.getmember.memberOccupation;
			$scope.designation = $scope.getmember.memberDesignation;
			$scope.companyname = $scope.getmember.memberCompanyName;
			$scope.businessnature = $scope.getmember.memberBusinessNature;
			$scope.faxnumber = $scope.getmember.memberFaxNumber;
			$scope.website = $scope.getmember.memberWebsiteName;
			$scope.email = $scope.getmember.memberCompanyEmail;
			$scope.address1work = $scope.getmember.memberCompanyAddress1;
			$scope.address2work = $scope.getmember.memberCompanyAddress2;
			$scope.address3work = $scope.getmember.memberCompanyAddress3;
			$scope.statenamework = $scope.getmember.memberCompanyStateId;
			$scope.citynamework = $scope.getmember.memberCompanyCityName;
			$scope.pincodework = $scope.getmember.memberCompanyPincode;
			$scope.mobilenumberwork = $scope.getmember.memberCompanyMobileNumber;
			$scope.communication = $scope.getmember.memberCommunicationAddress;
			$scope.membercategoryid = $scope.getmember.memberCategoryId;
			$scope.tenureplan = $scope.getmember.tenurePlan;
			$scope.aboutbusiness = $scope.getmember.memberCompanyDescription;
			$scope.businesskeywords = $scope.getmember.memberCompanyKeywords;
			$scope.businessgoals = $scope.getmember.businessGoals;
			$scope.accomplishments = $scope.getmember.accomplishments;
			$scope.interests = $scope.getmember.interests;
			$scope.networks = $scope.getmember.networks;
			$scope.skills = $scope.getmember.skills;
			$scope.idealreferral = $scope.getmember.idealReferral;
			$scope.topproduct = $scope.getmember.topProduct;
			$scope.topproblemsolved = $scope.getmember.topProblemSolved;	
			
			var link = baseUrl+'getStateDetailById?stateid='+$scope.statename;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getstatedetail = data;
				$scope.countryname = $scope.getstatedetail.countryId;		
			}).error(function(data, status, headers, config) {
				$scope.getrelatedstate = "Response Fail";
			});
			
			var link = baseUrl+'getStateDetailById?stateid='+$scope.statenamework;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getworkstatedetail = data;			
				$scope.countrynamework = $scope.getworkstatedetail.countryId;			
			}).error(function(data, status, headers, config) {
				$scope.getworkstatedetail = "Response Fail";
			});
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
	}
	
	$scope.redirectfamilydetail = function(memberid, membercategoryid, tenureplan, temp) {
		var membershipnumber = "";		
		membershipnumber = $scope.getmember.membershipNumber;
		
		var link = baseUrl+'setSessionMemberId?memberid='+memberid+'&membershipnumber='+membershipnumber;
		$http.post(link).success( function(data, status, headers, config) {
			$scope.setsessionmemberid = data;
			if(temp == 2){
				$window.location.href = url + "my_family_detail";			
			}else{
				$window.location.href = adminurl + "manage_family_detail?memberid="+memberid;
			}
		}).error(function(data, status, headers, config) {
			$scope.setsessionmemberid = "Response Fail";
		});
	}
	
	$scope.addResidentialLandlineRow1 = function() {
		if($scope.phonenumber == "" || $scope.phonenumber == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumber").focus();
			return;
		} else {
			$scope.getmemberresidentiallandline.push({'landlinePhoneNumber':$scope.phonenumber});
		}
	};
	
	$scope.removeResidentialLandlineRow1 = function(landlinephonenumber) {				
		var index = -1;		
		var comArr = eval( $scope.getmemberresidentiallandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getmemberresidentiallandline.splice( index, 1 );
	};
	

	
	$scope.addWorkLandlineRow1 = function()
	{
		var occupation = $scope.occupation;
		var designation = $scope.designation;
		var companyname = $scope.companyname;
		var businessnature = $scope.businessnature;
		var address1work = $scope.address1work;
		var address2work = $scope.address2work;
		var address3work = $scope.address3work;
		var countrynamework = $scope.countrynamework;
		var statenamework = $scope.statenamework;
		var citynamework = $scope.citynamework;
		var pincodework = $scope.pincodework;
		var email = $scope.email;
		var mobilenumberwork = $scope.mobilenumberwork;	
		
		if(occupation == "" || occupation == undefined ){
			occupation = "";
		}
		if(designation == "" || designation == undefined ) {
			designation = "";
		}
		if(companyname == "" || companyname == undefined ) {
			companyname = "";
		}
		if(businessnature == "" || businessnature == undefined ) {
			businessnature = "";
		}
		if(address3work == "" || address3work == undefined ) {
			address3work = "";
		}
		if(address1work == "" || address1work == undefined ) {
			$window.alert("Please enter address line1");
			document.getElementById("address1work").focus();
			return;
		}
		if(address2work == "" || address2work == undefined ) {
			$window.alert("Please enter address line2");
			document.getElementById("address2work").focus();
			return;
		}
		if(countrynamework == "" || countrynamework == undefined ) {
			$window.alert("Please Select country");
			document.getElementById("countrynamework").focus();
			return;
		}
		if(statenamework == "" || statenamework == undefined ) {
			$window.alert("Please select state");
			document.getElementById("statenamework").focus();
			return;
		}
		if(citynamework == "" || citynamework == undefined ) {
			$window.alert("Please enter city");
			document.getElementById("citynamework").focus();
			return;
		}
		if(pincodework == "" || pincodework == undefined ) {
			$window.alert("Please enter pincode");
			document.getElementById("pincodework").focus();
			return;
		}
		if(mobilenumberwork == "" || mobilenumberwork == undefined ) {
			$window.alert("Please enter mobile number");
			document.getElementById("mobilenumberwork").focus();
			return;
		}
		else if(email == "" || email == undefined ) {
			$window.alert("Please enter email");
			document.getElementById("email").focus();
			return;
		}
		else if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined)
		{
			$window.alert("Please enter phone number");
			document.getElementById("phonenumberwork").focus();
			return;
		}
		else
		{
			$scope.getmemberworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork,'memberCompanyName':companyname,'memberDesignation':designation,'memberComapnyAddress1':address1work,'memberComapnyAddress2':address2work,'memberComapnyAddress3':address3work,'memberCompanyCity':citynamework,'memberComapnyMobileNumber':mobilenumberwork,'memberComapnyEmail':email});
			if($scope.getmemberworklandline.length == 3){
				$scope.disableAddButton = 1;
			}
		}
	};
	
	$scope.removeWorkLandlineRow1 = function(memberCompanyName) {				
		var index = -1;		
		var comArr = eval( $scope.getmemberworklandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].memberCompanyName === memberCompanyName ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getmemberworklandline.splice( index, 1 );
	};
	
	$scope.editcontactdetail = function(memberid, membercategoryid, tenureplan, temp) {
		
		
		
		var temp = temp;
		var address1 = $scope.address1;
		var address2 = $scope.address2;
		var address3 = $scope.address3;
		var countryname = $scope.countryname;
		var statename = $scope.statename;
		var cityname = $scope.cityname;
		var pincode = $scope.pincode;
		var mobilenumber = $scope.mobilenumber;		
		var occupation = $scope.occupation;
		var designation = $scope.designation;
		var companyname = $scope.companyname;
		var businessnature = $scope.businessnature;
		var faxnumber = $scope.faxnumber;
		var website = $scope.website;
		var email = $scope.email;
		var address1work = $scope.address1work;
		var address2work = $scope.address2work;
		var address3work = $scope.address3work;
		var countrynamework = $scope.countrynamework;
		var statenamework = $scope.statenamework;
		var citynamework = $scope.citynamework;
		var pincodework = $scope.pincodework;
		var mobilenumberwork = $scope.mobilenumberwork;			
		var communication = $scope.communication;		
		
		var valuex = document.getElementById("valuex").value;
		var valuey = document.getElementById("valuey").value;
		var valuew = document.getElementById("valuew").value;
		var valueh = document.getElementById("valueh").value;		
		
		
		if(valuex == ''){
			valuex = 0;
		}
		if(valuey == ''){
			valuey = 0;
		}
		if(valuew == ''){
			valuew = 0;
		}
		if(valueh == ''){
			valueh = 0;
		}
		
		
		if(address2 == "" || address2 == undefined) {
			address2 = "";
		}
		if(address3 == "" || address3 == undefined) {
			address3 = "";
		}
		if(pincode == "" || pincode == undefined) {
			pincode = "";
		}		
		if(occupation == "" || occupation == undefined ) {
			occupation = "";
		}
		if(designation == "" || designation == undefined ) {
			designation = "";
		}
		if(companyname == "" || companyname == undefined ) {
			companyname = "";
		}
		if(businessnature == "" || businessnature == undefined ) {
			businessnature = "";
		}
		if(faxnumber == "" || faxnumber == undefined ) {
			faxnumber = "";
		}
		if(website == "" || website == undefined ) {
			website = "";
		}
		if($scope.aboutbusiness == undefined ) {
			$scope.aboutbusiness = "";
		}
		if($scope.businesskeywords == undefined ) {
			$scope.businesskeywords = "";
		}
		if(email == "" || email == undefined ) {
			email = "";
		}
		if(address1work == "" || address1work == undefined ) {
			address1work = "";
		}
		if(address2work == "" || address2work == undefined ) {
			address2work = "";
		}
		if(address3work == "" || address3work == undefined ) {
			address3work = "";
		}
		if(countrynamework == "" || countrynamework == undefined ) {
			countrynamework = 0;
		}
		if(statenamework == "" || statenamework == undefined ) {
			statenamework = 0;
		}
		if(citynamework == "" || citynamework == undefined ) {
			citynamework = "";
		}
		if(pincodework == "" || pincodework == undefined ) {
			pincodework = "";
		}
		if(mobilenumberwork == "" || mobilenumberwork == undefined ) {
			mobilenumberwork = "";
		}
		if($scope.businessgoals == undefined ) {
			$scope.businessgoals = "";
		}
		if($scope.accomplishments == undefined ) {
			$scope.accomplishments = "";
		}
		if($scope.interests == undefined ) {
			$scope.interests = "";
		}
		if($scope.networks == undefined ) {
			$scope.networks = "";
		}
		if($scope.skills == undefined ) {
			$scope.skills = "";
		}
		if($scope.idealreferral == undefined ) {
			$scope.idealreferral = "";
		}
		if($scope.topproduct == undefined ) {
			$scope.topproduct = "";
		}
		if($scope.topproblemsolved == undefined ) {
			$scope.topproblemsolved = "";
		}
				
		if(address1 == "" || address1 == undefined) {
			$window.alert("Please enter address line-1");
			document.getElementById("address1").focus();
			return;
		} else if(countryname == "" || countryname == undefined) {
			$window.alert("Please select country");
			document.getElementById("countryname").focus();
			return;
		} else if((statename == "" || statename == undefined) && countryname == 1) {
			$window.alert("Please select state");
			document.getElementById("statename").focus();
			return;
		} else if(cityname == "" || cityname == undefined) {
			$window.alert("Please enter city name");
			document.getElementById("cityname").focus();
			return;
		} else if(mobilenumber == "" || mobilenumber == undefined) {
			$window.alert("Please enter mobile number");
			document.getElementById("mobilenumber").focus();
			return;
		} else if(mobilenumber.length != 10) {
			$window.alert("Mobile number should be 10 digit");
			document.getElementById("mobilenumber").focus();
			return;
		} else if(communication == "" || communication == undefined ) {
			$window.alert("Please select address for communication");
			document.getElementById("communication").focus();
			return;
		} else {
			$scope.spin = 1;
			$scope.nospin = 0;
			if(statename == "" || statename == undefined) {
				statename = 0;
			}
			
			var link = baseUrl+'deleteMemberResidentialLandline?memberid='+memberid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteresidentiallandline = data;
			}).error(function(data, status, headers, config) {
				$scope.deleteresidentiallandline = "Response Fail";
			});
			
			var link = baseUrl+'deleteMemberWorkLandline?memberid='+memberid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteworklandline = data;
			}).error(function(data, status, headers, config) {
				$scope.deleteworklandline = "Response Fail";
			});				
			
			
			var link = baseUrl+'editContactDetail?memberid='+memberid+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&logoimage='+$scope.logoimage+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&aboutbusiness='+$scope.aboutbusiness+'&businesskeywords='+$scope.businesskeywords+'&email='+email+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communicationaddress='+communication+'&businessgoals='+$scope.businessgoals+'&accomplishments='+$scope.accomplishments+'&interests='+$scope.interests+'&networks='+$scope.networks+'&skills='+$scope.skills+'&idealreferral='+$scope.idealreferral+'&topproduct='+$scope.topproduct+'&topproblemsolved='+$scope.topproblemsolved+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;			
			console.log(link);
			var formData=new FormData();
			formData.append("companylogo",imgInp.files[0]);
			$http({method: 'POST',
				url: link,
				headers: {'Content-Type': undefined},
				data: formData,
				transformRequest: function(data, headersGetterFunction) {
					return data;
				}
			}).success(function(data, status) {
				$scope.editcontactdetail = data;
				var a = 0, b = 0, c = 0;
				if($scope.getmemberresidentiallandline.length == 0 && $scope.getmemberworklandline.length == 0) {
					
					$scope.spin = 0;
					$scope.nospin = 1;
					$window.alert("Data edited successfully");
					if(temp == 2) {
						$window.location.href = url + "my_family_detail";
					} else {
						$window.location.href = adminurl + "manage_family_detail?memberid="+memberid;
					}					
				}
				angular.forEach($scope.getmemberresidentiallandline, function(item) {
					if(item.landlinePhoneNumber) {
						var link = baseUrl+'editMemberLandlinePhoneNumber?memberid='+memberid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'R';
						
						$http.post(link).success(function(data, status, headers, config) {
							$scope.editlandlinenumber = data;
							a = a + 1;
							c = c + 1;
							if(($scope.getmemberworklandline.length == 0 && a == $scope.getmemberresidentiallandline.length) || (c == $scope.getmemberresidentiallandline.length + $scope.getmemberworklandline.length)) {
								
								$scope.spin = 0;
								$scope.nospin = 1;
								$window.alert("Data added successfully");									
								if(temp == 2) {
									$window.location.href = url + "my_family_detail";
								} else {
									$window.location.href = adminurl + "manage_family_detail?memberid="+memberid;
								}									
							}
						}).error(function(data, status, headers, config) {
							$scope.editlandlinenumber = "Response Fail";
						});
					}
				});
				angular.forEach($scope.getmemberworklandline, function(item) {
					if(item.landlinePhoneNumber) {
						var link = baseUrl+'editMemberWorkDetails?memberid='+memberid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W'+'&memberCompanyName='+item.memberCompanyName+'&memberDesignation='+item.memberDesignation+'&memberComapnyAddress1='+item.memberComapnyAddress1+'&memberComapnyAddress2='+item.memberComapnyAddress2+'&memberComapnyAddress3='+item.memberComapnyAddress3+'&memberCompanyCity='+item.memberCompanyCity+'&memberComapnyMobileNumber='+item.memberComapnyMobileNumber+'&memberComapnyEmail='+item.memberComapnyEmail;
						
						$http.post(link).success(function(data, status, headers, config) {
							$scope.editlandlinenumber = data;
							b = b + 1;
							c = c + 1;
							if(($scope.getmemberworklandline.length == b && $scope.getmemberresidentiallandline.length == 0) || (c == $scope.getmemberresidentiallandline.length + $scope.getmemberworklandline.length)) {
								
								$scope.spin = 0;
								$scope.nospin = 1;
								$window.alert("Data added successfully");
								if(temp == 2) {
									$window.location.href = url + "my_family_detail";
								} else {
									$window.location.href = adminurl + "manage_family_detail?memberid="+memberid;
								}									
							}
						}).error(function(data, status, headers, config) {
							$scope.editlandlinenumber = "Response Fail";
						});
					}
				});
			}).error(function(data, status, headers, config) {
				$scope.editcontactdetail = "Response Fail";
			});
		}
    }
	
	$scope.redirectmemberdetail = function(memberid,temp) {
		if(temp == 2){
			$window.location.href = url + "manage_my_profile?memberid="+memberid;
		} else {
			$window.location.href = adminurl + "manage_member_detail?memberid="+memberid;
		}
	}
	$scope.Frontlogout = function() {		
		var link = baseUrl + 'Frontlogout';
		$http.post(link).success(function(data, status, headers, config) {
			$window.location.href = url+'';
		}).error(function(data, status, headers, config) {
			$scope.frontlogout = "Response Fail";
		});
	}
});