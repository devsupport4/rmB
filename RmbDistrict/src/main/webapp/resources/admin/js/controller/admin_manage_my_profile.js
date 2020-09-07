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
	
	var link = baseUrl+'getAllVocation';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getVocation = data;			
	}).error(function(data, status, headers, config) {
		$scope.getVocation = "Response Fail";
	});
	
	var link = baseUrl+'getAllBusinessCategories';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allbusinesscategories = data;			
	}).error(function(data, status, headers, config) {
		$scope.allbusinesscategories = "Response Fail";
	});
	
	$scope.getmemberdetail = function(memberid) {
		$scope.memberid = memberid;
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
					$scope.vocation = $scope.getmember.VocationId;
										
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



					$scope.sequence = $scope.getmember.sequence;
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
	
	
	$scope.typeschange = function() {
		var typemn="OTHR";
		if($scope.membercategoryname == 7){
			typemn="OTHR";
		}
		else{
			typemn="RMBF";
		}
		var me=$scope.membershipId;
		var me1 = me.slice(4);
		
		$scope.membershipId = typemn+me1;
	}
	
	$scope.addEducationRowEdit = function() {	
		var link = baseUrl+'editMemberEducation?degreename='+$scope.degreename+'&passingyear='+$scope.passingyear+'&grade='+$scope.grade+'&institutename='+$scope.institutename+'&memberid='+$scope.memberid;
		$http.post(link).success(function(data, status, headers, config) {
			$scope.addmembereducation = data;
			if($scope.addmembereducation == "Success") {
				var link = baseUrl+'getMemberEducationDetail?memberid='+$scope.memberid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmembereducationdetail = data;
				}).error(function(data, status, headers, config) {
					$scope.getmembereducationdetail = "Response Fail";
				});
				$scope.degreename="";
				$scope.passingyear="";
				$scope.grade="";
				$scope.institutename="";
			}
		}).error(function(data, status, headers, config) {
			$scope.addmembereducation = "Response Fail";
		});
	};
	
	$scope.removeEducationRowEdit = function(membereducationid) {
		var link = baseUrl+'deleteMemberEducation?membereducationid='+membereducationid;
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deletemembereducation = data;
			if($scope.deletemembereducation == "Success"){
				var link = baseUrl+'getMemberEducationDetail?memberid='+$scope.memberid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmembereducationdetail = data;
				}).error(function(data, status, headers, config) {
					$scope.getmembereducationdetail = "Response Fail";
				});
			}
			else{
				alert(" Something went wrong while deleting record");
			}
			
		}).error(function(data, status, headers, config) {
			$scope.deletemembereducation = "Response Fail";
		});
	};
	
	$scope.editmemberdetail = function(memberid) {
		console.log("editmember initiated");
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
		
		
		var sequence = $scope.sequence;
		
		var membershipid = $scope.membershipId;
		var memberclubname = $scope.memberclubname;
		var membercategoryname = $scope.membercategoryname;
		var companyname = $scope.companyname;
		var membertypename = $scope.membertypename;			
		var tenureplan = $scope.tenureplan;
		var joiningdate = $scope.joiningdate;			
		var membertitle = $scope.membertitle;
		var firstname = $scope.firstname;
		var middlename = $scope.middlename;
		var lastname = $scope.lastname;
		var gender = $scope.gender;
		var dateofbirth = $scope.dateofbirth;					
		var bloodgroup = $scope.bloodgroup;	
		var anniversarydate = $scope.anniversarydate;
		var aadharnumber = $scope.aadharnumber;
		var countrynamecitizenship = $scope.countrynamecitizenship;
		var passportnumber = $scope.passportnumber;
		var pannumber = $scope.pannumber;
		var email = $scope.email;		
		/*var joiningdate = document.getElementById("datepicker").value;
		var dateofbirth = document.getElementById("datepicker1").value;
		var anniversarydate = document.getElementById("datepicker2").value;*/	
		
		if(!$scope.vocation)
		{				
			$scope.vocation = 0;
		}
		if(joiningdate == "" || joiningdate == undefined) {
			joiningdate = "";
		}if(dateofbirth == "" || dateofbirth == undefined) {
			dateofbirth = "";
		}if(anniversarydate == "" || anniversarydate == undefined) {
			anniversarydate = "";
		}
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
				
			var link = baseUrl+'editmemberdetailll?rotaryyearid='+$scope.rotaryyearid+'&memberid='+memberid+'&membershipid='+membershipid+'&memberclubname='+memberclubname+'&membercategoryname='+membercategoryname+'&membertypename='+membertypename+'&businesscategoryid='+$scope.businesscategoryid+'&tenureplan='+tenureplan+'&joiningdate='+joiningdate+'&membertitle='+membertitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+dateofbirth+'&bloodgroup='+bg+'&anniversarydate='+anniversarydate+'&aadharnumber='+aadharnumber+'&countrynamecitizenship='+countrynamecitizenship+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&profilepicture='+$scope.profilepicture+'&email='+email+'&sequence='+sequence+'&vocation='+$scope.vocation+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&fellowship_id='+$scope.fellowship_id;
			
alert(link);
				var formData=new FormData();
			formData.append("profile",imgInp.files[0]);					
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
				
				console.log("editmember response received");
				$scope.editMemberDetail = data;
					$scope.spin = 0;
					$scope.nospin = 1;
					$window.alert("Member Detail Updated Successfully");
					
					if($scope.membercategoryname == 7){
						$window.location.href = adminurl+"manage_members";
					}
					else{
						$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
					}
					
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
	
});