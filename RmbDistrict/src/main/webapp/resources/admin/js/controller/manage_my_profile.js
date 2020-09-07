app.controller('manageProfileCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;	
	

		
	//getAllFellowshipName
	var link = baseUrl+'getAllFellowshipName';  
	
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getAllFellowshipNameList = data;
		
	}).error(function(data, status, headers, config) {
		$scope.getAllFellowshipNameList = "Response Fail";
	});
	
	
	var link = baseUrl+'getAllClubs';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allclubs = data;			
	}).error(function(data, status, headers, config) {
		$scope.allclubs = "Response Fail";
	});
	
	var link = baseUrl+'getMemberCategory';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmembercategory = data;
	}).error(function(data, status, headers, config) {
		$scope.getmembercategory = "Response Fail";
	});
	
	var link = baseUrl+'getMemberType';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmembertype = data;
	}).error(function(data, status, headers, config) {
		$scope.getmembertype = "Response Fail";
	});
	
	var link = baseUrl+'getAllBusinessCategories';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allbusinesscategories = data;			
	}).error(function(data, status, headers, config) {
		$scope.allbusinesscategories = "Response Fail";
	});
	/*$scope.getFellowship_id = function(fellowship_id){
		$scope.fellowship_id=fellowship_id;
		
	}*/
	$scope.getmemberdetail = function(memberid) {
		var link = baseUrl+'getMemberEducationDetail?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmembereducationdetail = data;
		}).error(function(data, status, headers, config) {
			$scope.getmembereducationdetail = "Response Fail";
		});
		
		var link = baseUrl + 'getCurrentRotaryYear';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.currentrotaryyear = data;
			$scope.rotaryyearid = $scope.currentrotaryyear.rotaryYearId;
			$scope.rotaryyeartitle = $scope.currentrotaryyear.rotaryYearTitle;			
			
			var link = baseUrl+'getMemberCurrentCategoryId?memberid='+memberid+'&rotaryyearid='+$scope.rotaryyearid;			
			 
			$http.get(link).success(function(data, status, headers, config) {				
				$scope.membercurrentcategory = data;
				$scope.membercategoryname = $scope.membercurrentcategory.memberCategoryId;					
				var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;			
			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember = data;					
					$scope.memberid = $scope.getmember.memberId;
					$scope.membershipId = $scope.getmember.membershipNumber;					
					$scope.oldmembershipId = $scope.getmember.oldMembershipNumber;
					$scope.memberclubname = $scope.getmember.clubId;
					$scope.membertypename = $scope.getmember.memberTypeId;
					$scope.businesscategoryid = $scope.getmember.businessCategoryId;
					$scope.tenureplan = $scope.getmember.tenurePlan;
										
					$scope.membertitle = $scope.getmember.memberTitleName;
					$scope.firstname = $scope.getmember.memberFirstName;					
					$scope.middlename = $scope.getmember.memberMiddleName;
					$scope.lastname = $scope.getmember.memberLastName;
					$scope.gender = $scope.getmember.memberGender;										
										
					$scope.bloodgroup = $scope.getmember.memberBloodGroup;
					$scope.$parent.aadharnumber = $scope.getmember.memberAadharNumber;
					$scope.$parent.countrynamecitizenship = $scope.getmember.memberCountryIdCitizenship;
					$scope.passportnumber = $scope.getmember.memberPassportNumber;
					$scope.pannumber = $scope.getmember.memberPanNumber;
					$scope.profilepicture = $scope.getmember.memberProfilePicture;
					$scope.email = $scope.getmember.memberEmail;
					$scope.email1 = $scope.getmember.memberEmail;					
					$scope.memberbarcode = $scope.getmember.memberBarcode;
					$scope.memberqrcode = $scope.getmember.memberQrcode;
					$scope.joiningdate = $scope.getmember.joiningDate;
					$scope.dateofbirth = $scope.getmember.memberDateOfBirth;
					$scope.anniversarydate = $scope.getmember.memberAnniversaryDate;
					$scope.fellowship_id = $scope.getmember.fellowship_id;
				
					/*document.getElementById("datepicker").value = $scope.getmember.joiningDate;*/
					document.getElementById("datepicker1").value = $scope.getmember.memberDateOfBirth;
					document.getElementById("datepicker2").value = $scope.getmember.memberAnniversaryDate;
				}).error(function(data, status, headers, config) {
					$scope.getmember = "Response Fail";
				});					
			}).error(function(data, status, headers, config) {
				$scope.membercategoryname = "Response Fail";
			});
		}).error(function(data,status,headers,config) {
			$scope.currentrotaryyear = "Responce Fail";
		});		
	}
	
	$scope.addEducationRowEdit = function() {		
		$scope.getmembereducationdetail.push({'degreeName':$scope.degreename, 'passingYear': $scope.passingyear, 'grade': $scope.grade, 'instituteName': $scope.institutename});
	};
	
	$scope.removeEducationRowEdit = function(degreename) {				
		var index = -1;		
		var comArr = eval( $scope.getmembereducationdetail);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].degreeName === degreename ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getmembereducationdetail.splice( index, 1 );
	};
	
	$scope.editmemberdetail = function(memberid, temp) {
		
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
		
		if($scope.getLastSequence1 == undefined)
			$scope.getLastSequence1 = 0;
		var temp = temp;
		var sequence = $scope.getLastSequence1 + 1;
		
		var membershipid = $scope.membershipId;
		var memberclubname = $scope.memberclubname;
		var membercategoryname = $scope.membercategoryname;
		var companyname = $scope.companyname;
		var membertypename = $scope.membertypename;			
		var tenureplan = $scope.tenureplan;
		var joiningdate = "";
		var membertitle = $scope.membertitle;
		var firstname = $scope.firstname;
		var middlename = $scope.middlename;
		var lastname = $scope.lastname;
		var gender = $scope.gender;
		var dateofbirth = document.getElementById("datepicker1").value;			
		var bloodgroup = $scope.bloodgroup;	
		var anniversarydate = document.getElementById("datepicker2").value;
		var aadharnumber = $scope.aadharnumber;
		var countrynamecitizenship = $scope.countrynamecitizenship;
		var passportnumber = $scope.passportnumber;
		var pannumber = $scope.pannumber;
		var email = $scope.email;	
		$scope.vocation = 0;
		
		if(tenureplan == "" || tenureplan == undefined) {
			tenureplan = "";
		}
		if(membertitle == "" || membertitle == undefined) {
			membertitle = "";
		} 
		if(middlename == "" || middlename == undefined) {
			middlename = "";
		}
		if(anniversarydate == "" || anniversarydate == undefined || anniversarydate == "day/month/year" || anniversarydate == "undefined") {
			anniversarydate = "";
		}
		if(dateofbirth == "" || dateofbirth == undefined || dateofbirth == "day/month/year" || dateofbirth == "undefined") {
			dateofbirth = "";
		}
		if(bloodgroup == "" || bloodgroup == undefined) {
			bloodgroup = "";
		}		
		if(aadharnumber == "" || aadharnumber == undefined) {
			aadharnumber = "";
		}
		if(countrynamecitizenship == "" || countrynamecitizenship == undefined) {
			countrynamecitizenship = 0;
		}
		if(passportnumber == "" || passportnumber == undefined) {
			passportnumber = "";
		}
		if(pannumber == "" || pannumber == undefined) {
			pannumber = "";
		}
		if(membertypename == "" || membertypename == undefined) {
			membertypename = 0;
		}
		if(membercategoryname == "" || membercategoryname == undefined) {
			$window.alert("Please select type of member");
			document.getElementById("membercategoryname").focus();
			return;
		} else if($scope.businesscategoryid == undefined) {
			$window.alert("Please select business category");
			document.getElementById("businesscategoryid").focus();
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
		} /*else if(membertypename == "" || membertypename == undefined) {
			$window.alert("Please select nationality");
			document.getElementById("membertypename").focus();
			return;
		}*/ else if(email == "" || email == undefined) {
			$window.alert("Please enter email id");
			document.getElementById("email").focus();
			return;
		} else {
			$scope.spin = 1;
			$scope.nospin = 0;
			var bg = encodeURIComponent(bloodgroup);		
			
			var link = baseUrl+'deleteMemberEducationFront?memberid='+memberid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletemembereducation = data;
			}).error(function(data, status, headers, config) {
				$scope.deletemembereducation = "Response Fail";
			});
	
			
			var link = baseUrl+'editmemberdetailll?rotaryyearid='+$scope.rotaryyearid+'&memberid='+memberid+'&membershipid='+membershipid+'&memberclubname='+memberclubname+'&membercategoryname='+membercategoryname+'&membertypename='+membertypename+'&businesscategoryid='+$scope.businesscategoryid+'&tenureplan='+tenureplan+'&joiningdate='+joiningdate+'&membertitle='+membertitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+dateofbirth+'&bloodgroup='+bg+'&anniversarydate='+anniversarydate+'&aadharnumber='+aadharnumber+'&countrynamecitizenship='+countrynamecitizenship+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&profilepicture='+$scope.profilepicture+'&email='+email+'&sequence='+sequence+'&vocation='+$scope.vocation+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&fellowship_id='+$scope.fellowship_id;
		
			var formData=new FormData();
			
			formData.append("profile",imgInp.files[0]);
			$http({method: 'POST',
				url: link,
				headers: {'Content-Type': undefined},
				data: formData,
				transformRequest: function(data, headersGetterFunction) {
					return data;
				}
			}).success(function(data, status) {
				$scope.editMemberDetail = data;
				var a = 0;
				if($scope.getmembereducationdetail.length == 0)	{
					$scope.spin = 0;
					$scope.nospin = 1;
					$window.alert("Member Detail Updated Successfully");
					if(temp == 2) {
						$window.location.href = url+"my_contact_detail";
					} else {
						$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
					}
				}
				angular.forEach($scope.getmembereducationdetail, function(item) {
					if(item.degreeName != null)	{
						var link = baseUrl+'editMemberEducation?degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName+'&memberid='+memberid;
					
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addmembereducation = data;
							a = a + 1;
							if($scope.getmembereducationdetail.length == a) {
								$scope.spin = 0;
								$scope.nospin = 1;
								$window.alert("Member Detail Edited Successfully");
								if(temp == 2) {
									$window.location.href = url+"my_contact_detail";
								} else {
									$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
								}
							}
						}).error(function(data, status, headers, config) {
							$scope.addmembereducation = "Response Fail";
						});
					}
				});
			}).error(function(data, status, headers, config) {
				$scope.editMemberDetail = "Response Fail";
			});
		}
	}
	
	$scope.redirectcontactdetail = function(memberid,temp) {
		if(temp == 2) {
			$window.location.href = url+"my_contact_detail";
		} else {
			$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
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
	
	// ------------------------------------------------------
	
	var link = baseUrl+'getAllMembersDirectory';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getmember = data;			
	}).error(function(data, status, headers, config) {
		$scope.getmember = "Response Fail";
	});		

	$scope.setNewMemberId = function() {
		$scope.memberid = $scope.metmemberid;
	}
	
	$scope.saveOnetoOne = function(temp) {			
		$scope.meetingdate = document.getElementById("datepicker").value;		
		if($scope.metmemberid == undefined || $scope.metmemberid == ""){
			$window.alert("Please select member!");
			document.getElementById("metmemberid").focus();
			return;
		} /*else if($scope.invitedbymemberid == undefined){
			$window.alert("Please select invited by member!");
			document.getElementById("invitedbymemberid").focus();
			return;
		}*/ else if($scope.meetingdate == undefined){
			$window.alert("Please select meeting date!");
			document.getElementById("meetingdate").focus();
			return;
		} else if($scope.location == undefined){
			$window.alert("Please enter meeting location!");
			document.getElementById("location").focus();
			return;
		} else if($scope.topic == undefined){
			$window.alert("Please enter topics of conversation!");
			document.getElementById("topic").focus();
			return;
		} else {
			$scope.spin = 1;				
			var link = baseUrl+'saveOnetoOne?metmemberid='+$scope.metmemberid+'&invitedbymemberid='+$scope.invitedbymemberid+'&meetingdate='+$scope.meetingdate+'&location='+$scope.location+'&topic='+$scope.topic;				
			$http.post(link).success(function(data, status, headers, config) {
				$scope.saveonetoone = data;
				$scope.spin = 0;					
				$window.alert("Data submitted successfully");
				if(temp == "new"){
					$window.location.href = url + 'one_to_one';
				} else {
					$window.location.href = url;
				};					
			}).error(function(data, status, headers, config) {
				$scope.saveonetoone = "Response Fail";
			});
		}
	};	
	
});