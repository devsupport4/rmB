app.controller('myFamilyDetailCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	$scope.showtable =0;
	var link = baseUrl+'getCountry';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getcountry = data;
	}).error(function(data, status, headers, config) {
		$scope.country = "Response Fail";
	});
	
	var link = baseUrl+'getState';
	$http.get(link).success( function(data, status, headers, config) {
		$scope.getrelatedstate = data;
	}).error(function(data, status, headers, config) {
		$scope.getrelatedstate = "Response Fail";
	});
	
	$scope.getspousedetail = function(memberid) {				
		var link = baseUrl+'getspousesequence?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getspousesequence = data;
		}).error(function(data,status,headers,config) {
			$scope.getspousesequence = "Response Fail";
		});
		
		var link = baseUrl+'getspousedata?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getspousedata = data;
		}).error(function(data,status,headers,config) {
			$scope.getspousedata = "Response Fail";
		});
		
		var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;
			
			$scope.memberid = $scope.getmember.memberId;
			$scope.membershipnumber = $scope.getmember.membershipNumber;								
			$scope.membercategoryid = $scope.getmember.memberCategoryId;
			$scope.tenureplan = $scope.getmember.tenurePlan;								
			$scope.memberfirstname = $scope.getmember.memberFirstName;
			$scope.membermiddlename = $scope.getmember.memberMiddleName;
			$scope.memberlastname = $scope.getmember.memberLastName;
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});		
	}
	
	$scope.numberofspouse = 0;
	$scope.spousememberid = function(id, memberid) {
		
		if(id == 'null') {
			var link = baseUrl+'getMember';
			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember = data;
				for(i in $scope.getmember) {
					if($scope.getmember[i].memberId == memberid) {
						id = $scope.getmember[i].membershipNumber;
					}
				}
				
				if($scope.getspousesequence.length == 0) {
					$scope.numberofspouse = 0;
				} else {
					for(i in $scope.getspousesequence) {
						$scope.numberofspouse = $scope.getspousesequence[i].sequence;
					}
				}
				
				$scope.numberofspouse = $scope.numberofspouse+1;
				$scope.spouseid = id + "F0" + $scope.numberofspouse;
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});
		} else {
			if($scope.getspousesequence.length == 0) {
				$scope.numberofspouse = 0;
			} else {
				for(i in $scope.getspousesequence) {
					$scope.numberofspouse = $scope.getspousesequence[i].sequence;
				}
			}
			
			$scope.numberofspouse = $scope.numberofspouse+1;
			$scope.spouseid = id + "F0" + $scope.numberofspouse;
		}	
	}
	
	$scope.education = [{}];						
	
	$scope.addEducationRow = function() {		
		$scope.education.push({'degreeName':$scope.degreename, 'passingYear': $scope.passingyear, 'grade': $scope.grade, 'instituteName': $scope.institutename});
		document.getElementById("degreename").value = "";
		document.getElementById("passingyear").value = "";
		document.getElementById("grade").value = "";
		document.getElementById("institutename").value = "";
		
	};
	
	$scope.removeEducationRow = function(degreename) {				
		var index = -1;		
		var comArr = eval($scope.education);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].degreeName === degreename ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.education.splice( index, 1 );
	};
	
	$scope.residentialaddresssameasmember = function(memberid) {				
		if(!$scope.residentialaddress) {
			var link = baseUrl+'getMemberResidentialLandline?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.familyresidentiallandline = data;
				$scope.getfamilyresidentiallandline = $scope.familyresidentiallandline;
			}).error(function(data, status, headers, config) {
				$scope.familyresidentiallandline = "Response Fail";
			});
			
			var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember = data;
						
				$scope.memberid = $scope.getmember.memberId;
				$scope.address1 = $scope.getmember.memberAddress1;
				$scope.address2 = $scope.getmember.memberAddress2;
				$scope.address3 = $scope.getmember.memberAddress3;
				$scope.statename = $scope.getmember.memberStateId;
				$scope.cityname = $scope.getmember.memberCityName;
				$scope.pincode = $scope.getmember.memberPincode;
				$scope.mobilenumber = $scope.getmember.memberMobileNumber;
							
				$scope.address1edit = $scope.getmember.memberAddress1;
				$scope.address2edit = $scope.getmember.memberAddress2;
				$scope.address3edit = $scope.getmember.memberAddress3;
				$scope.statenameedit = $scope.getmember.memberStateId;
				$scope.citynameedit = $scope.getmember.memberCityName;
				$scope.pincodeedit = $scope.getmember.memberPincode;
				$scope.mobilenumberedit = $scope.getmember.memberMobileNumber;						
						
				var link = baseUrl+'getStateDetailById?stateid='+$scope.statename;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getstatedetail = data;
					$scope.countryname = $scope.getstatedetail.countryId;		
				}).error(function(data, status, headers, config) {
					$scope.getrelatedstate = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});
		} else {			
			$scope.address1 = "";
			$scope.address2 = "";
			$scope.address3 = "";
			$scope.countryname = "";
			$scope.statename = "";
			$scope.cityname = "";
			$scope.pincode = "";
			$scope.mobilenumber = "";
			$scope.familyresidentiallandline = [{}];
			
			$scope.address1edit = "";
			$scope.address2edit = "";
			$scope.address3edit = "";
			$scope.countrynameedit = "";
			$scope.statenameedit = "";
			$scope.citynameedit = "";
			$scope.pincodeedit = "";
			$scope.mobilenumberedit = "";
			$scope.getfamilyresidentiallandline = [{}];
		}	
	}
	
	$scope.workdetailsameasmember = function(memberid) {
		if(!$scope.workdetail) {
			var link = baseUrl+'getMemberWorkLandline?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.familyworklandline = data;
				$scope.getfamilyworklandline = $scope.familyworklandline;
			}).error(function(data, status, headers, config) {
				$scope.familyworklandline = "Response Fail";
			});
			
			var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember = data;
				
				$scope.memberid = $scope.getmember.memberId;
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
				$scope.phonenumberwork = $scope.getmember.memberCompanyPhoneNumber;
				$scope.communication = $scope.getmember.memberCommunicationAddress;
				
				$scope.occupationedit = $scope.getmember.memberOccupation;
				$scope.designationedit = $scope.getmember.memberDesignation;
				$scope.companynameedit = $scope.getmember.memberCompanyName;
				$scope.businessnatureedit = $scope.getmember.memberBusinessNature;
				$scope.faxnumberedit = $scope.getmember.memberFaxNumber;
				$scope.websiteedit = $scope.getmember.memberWebsiteName;
				$scope.emailedit = $scope.getmember.memberCompanyEmail;
				$scope.address1workedit = $scope.getmember.memberCompanyAddress1;
				$scope.address2workedit = $scope.getmember.memberCompanyAddress2;
				$scope.address3workedit = $scope.getmember.memberCompanyAddress3;
				$scope.statenameworkedit = $scope.getmember.memberCompanyStateId;
				$scope.citynameworkedit = $scope.getmember.memberCompanyCityName;
				$scope.pincodeworkedit = $scope.getmember.memberCompanyPincode;
				$scope.mobilenumberworkedit = $scope.getmember.memberCompanyMobileNumber;
				$scope.phonenumberworkedit = $scope.getmember.memberCompanyPhoneNumber;
				$scope.communicationedit = $scope.getmember.memberCommunicationAddress;		
						
				var link = baseUrl+'getStateDetailById?stateid='+$scope.statenamework;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getworkstatedetail = data;			
					$scope.countrynamework = $scope.getworkstatedetail.countryId;			
				}).error(function(data, status, headers, config) {
					$scope.getworkstatedetail = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail"
			});
		} else {			
			$scope.occupation = "";
			$scope.designation = "";
			$scope.companyname = "";
			$scope.businessnature = "";
			$scope.faxnumber = "";
			$scope.website = "";				
			$scope.address1work = "";
			$scope.address2work = "";
			$scope.address3work = "";
			$scope.statenamework = "";
			$scope.citynamework = "";
			$scope.pincodework = "";
			$scope.mobilenumberwork = "";
			$scope.phonenumberwork = "";
			$scope.communication = "";
			$scope.familyworklandline = [{}];
			
			$scope.occupationedit = "";
			$scope.designationedit = "";
			$scope.companynameedit = "";
			$scope.businessnatureedit = "";
			$scope.faxnumberedit = "";
			$scope.websiteedit = "";				
			$scope.address1workedit = "";
			$scope.address2workedit = "";
			$scope.address3workedit = "";
			$scope.statenameworkedit = "";
			$scope.citynameworkedit = "";
			$scope.pincodeworkedit = "";
			$scope.mobilenumberworkedit = "";
			$scope.phonenumberworkedit = "";
			$scope.communicationedit = "";
			$scope.getfamilyworklandline = [{}];
		}	
	}
	
	$scope.date = [];
	for(var i = 1; i<=31; i++) {
		$scope.date.push(i);
	}
	
	$scope.familyresidentiallandline = [{}];
	
	$scope.addFamilyResidentialLandlineRow = function() {
		if($scope.phonenumber == "" || $scope.phonenumber == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumber").focus();
			return;
		} else {
			$scope.familyresidentiallandline.push({'landlinePhoneNumber':$scope.phonenumber});
		}
	};
	
	$scope.removeFamilyResidentialLandlineRow = function(landlinephonenumber) {				
		var index = -1;		
		var comArr = eval( $scope.familyresidentiallandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.familyresidentiallandline.splice( index, 1 );
	};
	
	$scope.familyworklandline = [{}];
	
	$scope.addFamilyWorkLandlineRow = function() {
		$scope.showtable =1;
		if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumberwork").focus();
			return;
		} else {
			$scope.familyworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork});
			$scope.phonenumberwork="";
		}
	};
	
	$scope.removeFamilyWorkLandlineRow = function(landlinephonenumber) {				
		var index = -1;		
		var comArr = eval( $scope.familyworklandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.familyworklandline.splice( index, 1 );
	};
	
	$scope.addspousedetail = function() {
		
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
			
		
		
		
		
		var sequence = $scope.numberofspouse;			
		var spouseid = $scope.spouseid;
		var relation = $scope.relation;
		var membercategoryid = $scope.membercategoryname;
		var memberfamilytitle = $scope.memberfamilytitle;
		var firstname = $scope.firstname;
		var middlename = $scope.middlename;
		var lastname = $scope.lastname;
		var gender = $scope.gender;
		var birthdate = $scope.birthdate;
		var birthmonth = $scope.birthmonth;
		var birthyear = $scope.birthyear;			
		var bloodgroup = $scope.bloodgroup;			
		var aadharnumber = $scope.aadharnumber;
		var passportnumber = $scope.passportnumber;
		var pannumber = $scope.pannumber;
		var email = $scope.email;
		var password = $scope.password;
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
		var emailwork = $scope.emailwork;
		var address1work = $scope.address1work;
		var address2work = $scope.address2work;
		var address3work = $scope.address3work;
		var countrynamework = $scope.countrynamework;
		var statenamework = $scope.statenamework;
		var citynamework = $scope.citynamework;
		var pincodework = $scope.pincodework;
		var mobilenumberwork = $scope.mobilenumberwork;			
		var communication = $scope.communication;						
		
		if(membercategoryid == "" || membercategoryid == undefined ) {
			membercategoryid = 0;
		}			
		if(memberfamilytitle == "" || memberfamilytitle == undefined ) {
			memberfamilytitle = "";
		}
		if(middlename == "" || middlename == undefined ) {
			middlename = "";
		}
		if(birthdate == "" || birthdate == undefined) {
			birthdate = "";
		}
		if(birthmonth == "" || birthmonth == undefined) {
			birthmonth = "";
		}
		if(birthyear == "" || birthyear == undefined) {
			birthyear = "0001";
		}						
		if(bloodgroup == "" || bloodgroup == undefined) {
			bloodgroup = "";
		}
		if(aadharnumber == "" || aadharnumber == undefined) {
			aadharnumber = "";
		}
		if(passportnumber == "" || passportnumber == undefined) {
			passportnumber = "";
		}
		if(pannumber == "" || pannumber == undefined) {
			pannumber = "";
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
		if(mobilenumber == "" || mobilenumber == undefined) {
			mobilenumber = "";
		}		
		if(occupation == "" || occupation == undefined) {
			occupation = "";
		}
		if(designation == "" || designation == undefined) {
			designation = "";
		}
		if(companyname == "" || companyname == undefined) {
			companyname = "";
		}
		if(businessnature == "" || businessnature == undefined) {
			businessnature = "";
		}
		if(faxnumber == "" || faxnumber == undefined) {
			faxnumber = "";
		}
		if(website == "" || website == undefined) {
			website = "";
		}
		if(emailwork == "" || emailwork == undefined) {
			emailwork = "";
		}
		if(address1work == "" || address1work == undefined) {
			address1work = "";
		}
		if(address2work == "" || address2work == undefined) {
			address2work = "";
		}
		if(address3work == "" || address3work == undefined) {
			address3work = "";
		}
		if(countrynamework == "" || countrynamework == undefined) {
			countrynamework = 0;
		}
		if(statenamework == "" || statenamework == undefined) {
			statenamework = 0;
		}
		if(citynamework == "" || citynamework == undefined) {
			citynamework = "";
		}				
		if(pincodework == "" || pincodework == undefined) {
			pincodework = "";
		}
		if(mobilenumberwork == "" || mobilenumberwork == undefined) {
			mobilenumberwork = "";
		}			
		
		if(relation == "" || relation == undefined) {
			$window.alert("Please select relation");
			document.getElementById("relation").focus();
			return;
		} else if(firstname == "" || firstname == undefined) {
			$window.alert("Please enter first name");
			document.getElementById("firstname").focus();
			return;
		} else if(lastname == "" || lastname == undefined) {
			$window.alert("Please enter last name");
			document.getElementById("lastname").focus();
			return;
		} else if(gender == "" || gender == undefined) {
			$window.alert("Please select gender");
			document.getElementById("gender").focus();
			return;
		} else if((birthdate != "" || birthmonth != "") && birthyear == "") {
			$window.alert("Please enter the value of birthyear");
			document.getElementById("birthyear").focus();
			return;
		} else if(email == "" || email == undefined) {
			$window.alert("Please enter email id");
			document.getElementById("email").focus();
			return;
		} else if(password == "" || password == undefined) {
			$window.alert("Please enter password");
			document.getElementById("password").focus();
			return;
		} else if(address1 == "" || address1 == undefined) {
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
		} else if(communication == "" || communication == undefined) {
			$window.alert("Please select address for communication");
			document.getElementById("communication").focus();
			return;
		} else {
			if(statename == "" || statename == undefined) {
				statename = 0;
			}			
			var birth = birthyear+"-"+birthmonth+"-"+birthdate;
						
			var bg = encodeURIComponent(bloodgroup);				
			var link = baseUrl +'addSpouseDetail?membershipno='+spouseid+'&relation='+relation+'&membercategoryid='+membercategoryid+'&memberfamilytitle='+memberfamilytitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+birth+'&bloodgroup='+bg+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&email='+email+'&password='+password+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&emailwork='+emailwork+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communication='+communication+'&sequence='+sequence+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;			
			
			var formData = new FormData();
			formData.append("profile",imgInp.files[0]);
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; } }).success(function(data, status) {
				$scope.addspousedetail = data;
				var a = 1, b = 1, c = 1, d = 1;
				if($scope.education.length == 1 && ($scope.familyresidentiallandline.length == 1 || $scope.residentialaddress == true && $scope.familyresidentiallandline.length == 0) && ($scope.familyworklandline.length == 1 || $scope.workdetail == true)) {
					$window.alert("Family Detail Added Successfully");
					$window.location.href = url+"my_family_detail";
				}
				angular.forEach($scope.education, function(item) {
					if(item.degreeName != null) {
						var link = baseUrl+'addMemberFamilyEducation?degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName;
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addfamilyeducation = data;
							a = a + 1;
							d = d + 1;
							if(($scope.education.length == 1 && $scope.familyresidentiallandline.length == 1 && a == $scope.familyworklandline.length) || (d+1 == $scope.familyresidentiallandline.length + $scope.familyworklandline.length + $scope.education.length)) {
								$window.alert("Family Detail Added Successfully");
								$window.location.href = url+"my_family_detail";								
							}
						}).error(function(data, status, headers, config) {
							$scope.familyeducation = "Response Fail";
						});
					}
				});
				
				angular.forEach($scope.familyresidentiallandline, function(item) {
					if(item.landlinePhoneNumber) {
						var link = baseUrl+'addFamilyMemberLandlinePhoneNumber?landlinephonenumber='+item.landlinePhoneNumber+'&location='+'R';
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addlandlinenumber = data;
							b = b + 1;
							d = d + 1;
							if(($scope.education.length == 1 && $scope.familyworklandline.length == 1 && b == $scope.familyresidentiallandline.length) || (d+1 == $scope.familyresidentiallandline.length + $scope.familyworklandline.length + $scope.education.length)) {
								$window.alert("Family Detail Added Successfully");
								$window.location.href = url+"my_family_detail";								
							}
						}).error(function(data, status, headers, config) {
							$scope.addlandlinenumber = "Response Fail";
						});
					}
				});
				
				angular.forEach($scope.familyworklandline,function(item) {
					if(item.landlinePhoneNumber) {
						var link = baseUrl+'addFamilyMemberLandlinePhoneNumber?landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W';
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addlandlinenumber = data;
							c = c + 1;
							d = d + 1;
							if(($scope.education.length == 1 && $scope.familyresidentiallandline.length == 1 && c == $scope.familyworklandline.length) || (d+1 == $scope.familyresidentiallandline.length + $scope.familyworklandline.length + $scope.education.length)) {
								$window.alert("Spouse Detail Added Successfully");
								$window.location.href = url+"my_family_detail";								
							}
						}).error(function(data, status, headers, config) {
							$scope.addlandlinenumber = "Response Fail";
						});
					}
				});
			}).error(function(data, status, headers, config) {
				$scope.addspousedetail = "Response Fail";
			});
		}
	}
	
	$scope.getspouse = function(membersfamilyid, id, memberid) {
		
		var link = baseUrl+'getSpouseDetailById?membersfamilyid='+membersfamilyid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getspousedetail = data;
			
			$scope.membersfamilyid = $scope.getspousedetail.membersFamilyId;
			$scope.spouseidedit = $scope.getspousedetail.membershipNumber;					
			$scope.relationedit = $scope.getspousedetail.memberFamilyTypeOfRelation;
			$scope.membercategoryedit = $scope.getspousedetail.memberCategoryId;
			$scope.memberfamilytitleedit = $scope.getspousedetail.memberFamilyTitleName;
			$scope.firstnameedit = $scope.getspousedetail.memberFamilyFirstName;
			$scope.middlenameedit = $scope.getspousedetail.memberFamilyMiddleName;
			$scope.lastnameedit = $scope.getspousedetail.memberFamilyLastName;
			$scope.genderedit = $scope.getspousedetail.memberFamilyGender;
			$scope.birthedit = $scope.getspousedetail.memberFamilyDateOfBirth;

			if($scope.birthedit == null) {
				$scope.birthdateedit = "";
				$scope.birthmonthedit = "";
				$scope.birthyearedit = "";
			} else {
				var a = $scope.birthedit.split("-");
				$scope.birthdateedit = a[2];
				$scope.birthmonthedit = a[1];
				$scope.birthyearedit = a[0];
			}			
			
			$scope.bloodgroupedit = $scope.getspousedetail.memberFamilyBloodGroup;					
			$scope.aadharnumberedit = $scope.getspousedetail.memberFamilyAadharNumber;
			$scope.passportnumberedit = $scope.getspousedetail.memberFamilyPassportNumber;
			$scope.pannumberedit = $scope.getspousedetail.memberFamilyPanNumber;
			$scope.profileedit = $scope.getspousedetail.memberFamilyProfilePicture;
			$scope.emailedit = $scope.getspousedetail.memberFamilyEmail;
			$scope.emailedit1 = $scope.getspousedetail.memberFamilyEmail;
			$scope.passwordedit = $scope.getspousedetail.memberFamilyPassword;
			$scope.address1edit = $scope.getspousedetail.memberFamilyAddress1;
			$scope.address2edit = $scope.getspousedetail.memberFamilyAddress2;
			$scope.address3edit = $scope.getspousedetail.memberFamilyAddress3;
			$scope.statenameedit = $scope.getspousedetail.memberFamilyStateId;
			$scope.citynameedit = $scope.getspousedetail.memberFamilyCityName;
			$scope.pincodeedit = $scope.getspousedetail.memberFamilyPincode;
			$scope.mobilenumberedit = $scope.getspousedetail.memberFamilyMobileNumber;
			$scope.phonenumberedit = $scope.getspousedetail.memberFamilyPhoneNumber;
			$scope.occupationedit = $scope.getspousedetail.memberFamilyOccupation;
			$scope.designationedit = $scope.getspousedetail.memberFamilyDesignation;
			$scope.companynameedit = $scope.getspousedetail.memberFamilyCompanyName;
			$scope.businessnatureedit = $scope.getspousedetail.memberFamilyBusinessNature;
			$scope.faxnumberedit = $scope.getspousedetail.memberFamilyFaxNumber;
			$scope.websiteedit = $scope.getspousedetail.memberFamilyWebsiteName;
			$scope.emailworkedit = $scope.getspousedetail.memberFamilyCompanyEmail;
			$scope.address1workedit = $scope.getspousedetail.memberFamilyCompanyAddress1;
			$scope.address2workedit = $scope.getspousedetail.memberFamilyCompanyAddress2;
			$scope.address3workedit = $scope.getspousedetail.memberFamilyCompanyAddress3;
			$scope.statenameworkedit = $scope.getspousedetail.memberFamilyCompanyStateId;
			$scope.citynameworkedit = $scope.getspousedetail.memberFamilyCompanyCityName;
			$scope.pincodeworkedit = $scope.getspousedetail.memberFamilyCompanyPincode;
			$scope.mobilenumberworkedit = $scope.getspousedetail.memberFamilyCompanyMobileNumber;
			$scope.phonenumberworkedit = $scope.getspousedetail.memberFamilyCompanyPhoneNumber;
			$scope.communicationedit = $scope.getspousedetail.memberFamilyCommunicationAddress;
			
			var link = baseUrl+'getStateDetailById?stateid='+$scope.statenameedit;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getstatedetail = data;			
				$scope.countrynameedit = $scope.getstatedetail.countryId;			
			}).error(function(data, status, headers, config) {
				$scope.getstatedetail = "Response Fail";
			});
			
			var link = baseUrl+'getStateDetailById?stateid='+$scope.statenameworkedit;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getworkstatedetail = data;			
				$scope.countrynameworkedit = $scope.getworkstatedetail.countryId;			
			}).error(function(data, status, headers, config) {
				$scope.getworkstatedetail = "Response Fail";
			});
			
			var link = baseUrl+'getFamilyEducationDetail?membersfamilyid='+membersfamilyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getfamilyeducationdetail = data;
			}).error(function(data, status, headers, config) {
				$scope.getfamilyeducationdetail = "Response Fail";
			});
			
			var link = baseUrl+'getFamilyResidentialLandline?membersfamilyid='+membersfamilyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getfamilyresidentiallandline = data;
			}).error(function(data, status, headers, config) {
				$scope.getfamilyresidentiallandline = "Response Fail";
			});
			
			var link = baseUrl+'getFamilyWorkLandline?membersfamilyid='+membersfamilyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getfamilyworklandline = data;
			}).error(function(data, status, headers, config) {
				$scope.getfamilyworklandline = "Response Fail";
			});
			
		}).error(function(data, status, headers, config) {
			$scope.getspousedetail = "Response Fail";
		});				
	}
	
	$scope.getfamilybarcode = function(membersfamilyid) {
		$scope.memberfamilybarcode = $scope.getspousedetail.memberFamilyBarcode;		
	}
	
	$scope.getfamilyqrcode = function(membersfamilyid) {
		$scope.memberfamilyqrcode = $scope.getspousedetail.memberFamilyQrcode;			
	}		
	
	$scope.addEducationRow1 = function() {		
		$scope.getfamilyeducationdetail.push({'degreeName':$scope.degreenameedit, 'passingYear': $scope.passingyearedit, 'grade': $scope.gradeedit, 'instituteName': $scope.institutenameedit});
	};
	
	$scope.removeEducationRow1 = function(degreename) {				
		var index = -1;		
		var comArr = eval( $scope.getfamilyeducationdetail);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].degreeName === degreename ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getfamilyeducationdetail.splice( index, 1 );
	};
	
	
	$scope.addFamilyResidentialLandlineRow1 = function() {
		if($scope.phonenumberedit == "" || $scope.phonenumberedit == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumberedit").focus();
			return;
		} else {
			$scope.getfamilyresidentiallandline.push({'landlinePhoneNumber':$scope.phonenumberedit});
		}
	};
	
	$scope.removeFamilyResidentialLandlineRow1 = function(landlinephonenumber) {				
		var index = -1;		
		var comArr = eval( $scope.getfamilyresidentiallandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getfamilyresidentiallandline.splice( index, 1 );
	};
	
	$scope.addFamilyWorkLandlineRow1 = function() {
		if($scope.phonenumberworkedit == "" || $scope.phonenumberworkedit == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumberworkedit").focus();
			return;
		} else {
			$scope.getfamilyworklandline.push({'landlinePhoneNumber':$scope.phonenumberworkedit});
		}
	};
	
	$scope.removeFamilyWorkLandlineRow1 = function(landlinephonenumber) {				
		var index = -1;		
		var comArr = eval( $scope.getfamilyworklandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getfamilyworklandline.splice( index, 1 );
	};
	
	$scope.editspousedetail = function(membersfamilyid, temp) {
		
		var valuex = document.getElementById("valuex1").value;
		var valuey = document.getElementById("valuey1").value;
		var valuew = document.getElementById("valuew1").value;
		var valueh = document.getElementById("valueh1").value;
		
				
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
		
		
		
		
		var temp = temp;
		var sequence = $scope.numberofspouse;
		var spouseid = $scope.spouseidedit;			
		var relation = $scope.relationedit;
		var membercategoryid = $scope.membercategoryedit;
		var memberfamilytitle = $scope.memberfamilytitleedit;
		var firstname = $scope.firstnameedit;
		var middlename = $scope.middlenameedit;
		var lastname = $scope.lastnameedit;
		var gender = $scope.genderedit;
		var birthdate = $scope.birthdateedit;
		var birthmonth = $scope.birthmonthedit;
		var birthyear = $scope.birthyearedit;
		var bloodgroup = $scope.bloodgroupedit;			
		var aadharnumber = $scope.aadharnumberedit;
		var passportnumber = $scope.passportnumberedit;
		var pannumber = $scope.pannumberedit;
		var profilepicture = $scope.profileedit;
		var email = $scope.emailedit;
		var password = $scope.passwordedit;
		var address1 = $scope.address1edit;
		var address2 = $scope.address2edit;
		var address3 = $scope.address3edit;
		var countryname = $scope.countrynameedit;
		var statename = $scope.statenameedit;
		var cityname = $scope.citynameedit;
		var pincode = $scope.pincodeedit;
		var mobilenumber = $scope.mobilenumberedit;			
		var occupation = $scope.occupationedit;
		var designation = $scope.designationedit;
		var companyname = $scope.companynameedit;
		var businessnature = $scope.businessnatureedit;
		var faxnumber = $scope.faxnumberedit;
		var website = $scope.websiteedit;
		var emailwork = $scope.emailworkedit;
		var address1work = $scope.address1workedit;
		var address2work = $scope.address2workedit;
		var address3work = $scope.address3workedit;
		var countrynamework = $scope.countrynameworkedit;
		var statenamework = $scope.statenameworkedit;
		var citynamework = $scope.citynameworkedit;
		var pincodework = $scope.pincodeworkedit;
		var mobilenumberwork = $scope.mobilenumberworkedit;			
		var communication = $scope.communicationedit;
		var nologinedit = $scope.nologinedit;						
		
		if(membercategoryid == "" || membercategoryid == undefined) {
			membercategoryid = 0;
		}
		if(memberfamilytitle == "" || memberfamilytitle == undefined) {
			memberfamilytitle = "";
		}
		if(middlename == "" || middlename == undefined ) {
			middlename = "";
		}
		if(birthdate == "" || birthdate == undefined) {
			birthdate = "";
		}
		if(birthmonth == "" || birthmonth == undefined) {
			birthmonth = "";
		}
		if(birthyear == "" || birthyear == undefined) {
			birthyear = "";
		}		
		if(bloodgroup == "" || bloodgroup == undefined) {
			bloodgroup = "";
		}			
		if(aadharnumber == "" || aadharnumber == undefined) {
			aadharnumber = "";
		}
		if(passportnumber == "" || passportnumber == undefined) {
			passportnumber = "";
		}
		if(pannumber == "" || pannumber == undefined) {
			pannumber = "";
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
		if(mobilenumber == "" || mobilenumber == undefined) {
			mobilenumber = "";
		}						
		if(occupation == "" || occupation == undefined) {
			occupation = "";
		}
		if(designation == "" || designation == undefined) {
			designation = "";
		}
		if(companyname == "" || companyname == undefined) {
			companyname = "";
		}
		if(businessnature == "" || businessnature == undefined) {
			businessnature = "";
		}
		if(faxnumber == "" || faxnumber == undefined) {
			faxnumber = "";
		}
		if(website == "" || website == undefined) {
			website = "";
		}
		if(emailwork == "" || emailwork == undefined) {
			emailwork = "";
		}
		if(address1work == "" || address1work == undefined) {
			address1work = "";
		}
		if(address2work == "" || address2work == undefined) {
			address2work = "";
		}
		if(address3work == "" || address3work == undefined) {
			address3work = "";
		}
		if(countrynamework == "" || countrynamework == undefined) {
			countrynamework = 0;
		}
		if(statenamework == "" || statenamework == undefined) {
			statenamework = 0;
		}
		if(citynamework == "" || citynamework == undefined) {
			citynamework = "";
		}				
		if(pincodework == "" || pincodework == undefined) {
			pincodework = "";
		}
		if(mobilenumberwork == "" || mobilenumberwork == undefined) {
			mobilenumberwork = "";
		}					
		if(relation == "" || relation == undefined) {
			$window.alert("Please select relation");
			document.getElementById("relationedit").focus();
			return;
		} else if(firstname == "" || firstname == undefined) {
			$window.alert("Please enter first name");
			document.getElementById("firstnameedit").focus();
			return;
		} else if(lastname == "" || lastname == undefined) {
			$window.alert("Please enter last name");
			document.getElementById("lastnameedit").focus();
			return;
		} else if(gender == "" || gender == undefined) {
			$window.alert("Please select gender");
			document.getElementById("genderedit").focus();
			return;
		} else if(birthyear == undefined) {
			$window.alert("Please enter the value of birthyear");
			document.getElementById("birthyear").focus();
			return;
		} else if(email == "" || email == undefined) {
			$window.alert("Please enter email id");
			document.getElementById("emailedit").focus();
			return;
		} else if(password == "" || password == undefined) {
			$window.alert("Please enter password");
			document.getElementById("passwordedit").focus();
			return;
		} else if(address1 == "" || address1 == undefined) {
			$window.alert("Please enter address line-1");
			document.getElementById("address1edit").focus();
			return;
		} else if(countryname == "" || countryname == undefined) {
			$window.alert("Please select country");
			document.getElementById("countrynameedit").focus();
			return;
		} else if((statename == "" || statename == undefined) && countryname == 1) {
			$window.alert("Please select state");
			document.getElementById("statenameedit").focus();
			return;
		} else if(cityname == "" || cityname == undefined) {
			$window.alert("Please enter city name");
			document.getElementById("citynameedit").focus();
			return;
		} else if(communication == "" || communication == undefined) {
			$window.alert("Please select address for communication");
			document.getElementById("communicationedit").focus();
			return;
		} else {
			if(statename == "" || statename == undefined) {
				statename = 0;
			}			
			var birth = birthyear+"-"+birthmonth+"-"+birthdate;			
			var bg = encodeURIComponent(bloodgroup);
			
			var link = baseUrl+'deleteFamilyEducation?membersfamilyid='+membersfamilyid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletefamilyeducation = data;
			}).error(function(data, status, headers, config) {
				$scope.deletefamilyeducation = "Response Fail";
			});
			
			var link = baseUrl+'deleteFamilyResidentialLandline?membersfamilyid='+membersfamilyid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteresidentiallandline = data;
			}).error(function(data, status, headers, config) {
				$scope.deleteresidentiallandline = "Response Fail";
			});
			
			var link = baseUrl+'deleteFamilyWorkLandline?membersfamilyid='+membersfamilyid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteworklandline = data;
			}).error(function(data, status, headers, config) {
				$scope.deleteworklandline = "Response Fail";
			});			
			if($scope.code == 0) {
				var link = baseUrl +'editSpouseDetail1?membersfamilyid='+membersfamilyid+'&relation='+relation+'&membercategoryid='+membercategoryid+'&memberfamilytitle='+memberfamilytitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+birth+'&bloodgroup='+bg+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&profilepicture='+$scope.profileedit+'&email='+email+'&password='+password+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&emailwork='+emailwork+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communication='+communication+'&spouseid='+spouseid+'&sequence='+sequence+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;				
			//	$window.alert(link);
				var formData = new FormData();
				formData.append("profile",imgInp1.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) {
					return data;
				}
				}).success(function(data, status) {
					$scope.addspousedetail = data;
					var a = 0, b = 0, c = 0, d = 0;
					if($scope.getfamilyeducationdetail.length == 0) {
						$window.alert("Family Detail Updated Successfully");
						$window.location.href = url+"my_family_detail";						
					}
					
					angular.forEach($scope.getfamilyeducationdetail,function(item) {
						if(item.degreeName != null) {
							var link = baseUrl+'editMemberFamilyEducation?membersfamilyid='+membersfamilyid+'&degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editfamilyeducation = data;
								a = a + 1;
								d = d + 1;
								if(($scope.getfamilyeducationdetail.length == a && $scope.getfamilyresidentiallandline.length == 0 && $scope.getfamilyworklandline.length == 0) || (d == $scope.getfamilyresidentiallandline.length + $scope.getfamilyworklandline.length + $scope.getfamilyeducationdetail.length)) {
									$window.alert("Family Detail Updated Successfully");
									$window.location.href = url+"my_family_detail";									
								}
							}).error(function(data, status, headers, config) {
								$scope.editfamilyeducation = "Response Fail";
							});
						}
					});
					
					angular.forEach($scope.getfamilyresidentiallandline,function(item) {
						if(item.landlinePhoneNumber) {
							var link = baseUrl+'editFamilyMemberLandlinePhoneNumber?membersfamilyid='+membersfamilyid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'R';
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editlandlinenumber = data;
								b = b + 1;
								d = d + 1;
								if(($scope.getfamilyeducationdetail.length == 0 && $scope.getfamilyresidentiallandline.length == b && $scope.getfamilyworklandline.length == 0) || (d == $scope.getfamilyresidentiallandline.length + $scope.getfamilyworklandline.length + $scope.getfamilyeducationdetail.length)) {
									$window.alert("Family Detail Updated Successfully");
									$window.location.href = url+"my_family_detail";									
								}
							}).error(function(data, status, headers, config) {
								$scope.editlandlinenumber = "Response Fail";
							});
						}
					});
					
					angular.forEach($scope.getfamilyworklandline,function(item) {
						if(item.landlinePhoneNumber) {
							var link = baseUrl+'editFamilyMemberLandlinePhoneNumber?membersfamilyid='+membersfamilyid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W';
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editlandlinenumber = data;
								c = c + 1;
								d = d + 1;
								if(($scope.getfamilyeducationdetail.length == 0 && $scope.getfamilyresidentiallandline.length == 0 && $scope.getfamilyworklandline.length == c) || (d == $scope.getfamilyresidentiallandline.length + $scope.getfamilyworklandline.length + $scope.getfamilyeducationdetail.length)) {
									$window.alert("Family Detail Updated Successfully");
									$window.location.href = url+"my_family_detail";						
								}
							}).error(function(data, status, headers, config) {
								$scope.editlandlinenumber = "Response Fail";
							});
						}
					});
				}).error(function(data, status, headers, config) {
					$scope.editspousedetail = "Response Fail";
				});
			} else {
				var link = baseUrl +'editSpouseDetail?membersfamilyid='+membersfamilyid+'&relation='+relation+'&membercategoryid='+membercategoryid+'&memberfamilytitle='+memberfamilytitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+birth+'&bloodgroup='+bg+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&profilepicture='+profilepicture+'&email='+email+'&password='+password+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&emailwork='+emailwork+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communication='+communication+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;					
			//	$window.alert(link);
				var formData = new FormData();
				formData.append("profile",imgInp1.files[0]); 
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) {
					return data;
				}
				}).success(function(data, status) {
					$scope.addspousedetail = data;
					var a = 0, b = 0, c = 0, d = 0;
					if($scope.getfamilyeducationdetail.length == 0) {
						$window.alert("Family Detail Updated Successfully");
						$window.location.href = url+"my_family_detail";						
					}
					angular.forEach($scope.getfamilyeducationdetail,function(item) {
						if(item.degreeName != null) {
							var link = baseUrl+'editMemberFamilyEducation?membersfamilyid='+membersfamilyid+'&degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editfamilyeducation = data;
								a = a + 1;
								d = d + 1;
								if(($scope.getfamilyeducationdetail.length == a && $scope.getfamilyresidentiallandline.length == 0 && $scope.getfamilyworklandline.length == 0) || (d == $scope.getfamilyresidentiallandline.length + $scope.getfamilyworklandline.length + $scope.getfamilyeducationdetail.length)) {
									$window.alert("Family Detail Updated Successfully");
									$window.location.href = url+"my_family_detail";									
								}
							}).error(function(data, status, headers, config) {
								$scope.editfamilyeducation = "Response Fail";
							});
						}
					});
					angular.forEach($scope.getfamilyresidentiallandline,function(item) {
						if(item.landlinePhoneNumber) {
							var link = baseUrl+'editFamilyMemberLandlinePhoneNumber?membersfamilyid='+membersfamilyid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'R';
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editlandlinenumber = data;
								b = b + 1;
								d = d + 1;
								if(($scope.getfamilyeducationdetail.length == 0 && $scope.getfamilyresidentiallandline.length == b && $scope.getfamilyworklandline.length == 0) || (d == $scope.getfamilyresidentiallandline.length + $scope.getfamilyworklandline.length + $scope.getfamilyeducationdetail.length)) {
									$window.alert("Family Detail Updated Successfully");
									$window.location.href = url+"my_family_detail";
								}
							}).error(function(data, status, headers, config) {
								$scope.editlandlinenumber = "Response Fail";
							});
						}
					});
					
					angular.forEach($scope.getfamilyworklandline,function(item) {
						if(item.landlinePhoneNumber) {
							var link = baseUrl+'editFamilyMemberLandlinePhoneNumber?membersfamilyid='+membersfamilyid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W';
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editlandlinenumber = data;
								c = c + 1;
								d = d + 1;									
								if(($scope.getfamilyeducationdetail.length == 0 && $scope.getfamilyresidentiallandline.length == 0 && $scope.getfamilyworklandline.length == c) || (d == $scope.getfamilyresidentiallandline.length + $scope.getfamilyworklandline.length + $scope.getfamilyeducationdetail.length)) {
									$window.alert("Family Detail Updated Successfully");
									$window.location.href = url+"my_family_detail";									
								}
							}).error(function(data, status, headers, config) {
								$scope.editlandlinenumber = "Response Fail";
							});
						}
					});
				}).error(function(data, status, headers, config) {
					$scope.addspousedetail = "Response Fail";
				});
			}
		}
	}
	
	$scope.redirectcontactdetail = function(memberid) {			
		$window.location.href = url + "my_contact_detail";
	}
	
	$scope.redirectreference = function(membercategoryid,temp) {			
		if(membercategoryid == 2) {
			if(temp == 2) {
				$window.location.href = url + "my_payment_detail";
			}else{
				$window.location.href = adminurl + "payment_detail";
			}
		} else {
			if(temp == 2) {
				$window.location.href = url + "my_reference";
			}else{
				$window.location.href = adminurl + "reference";
			}
		}			
	}
	
	$scope.deletefamilymember = function(membersfamilyid) {
		d = $window.confirm('Are you sure you want to delete this family member?');
		if(d) {
			var link = baseUrl+'deleteFamilyMember?membersfamilyid='+membersfamilyid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletefamilymember = data;
				$window.location.reload();
			}).error(function(data, status, headers, config) {
				$scope.deletefamilymember = "Response Fail";
			});
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