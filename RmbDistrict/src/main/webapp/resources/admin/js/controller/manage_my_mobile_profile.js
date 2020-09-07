app.controller('manageProfileCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;	
	var link = baseUrl+'getAllClubs';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allclubs = data;			
	}).error(function(data, status, headers, config) {
		$scope.allclubs = "Response Fail";
	});
	
	var link = baseUrl+'getCountry';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getcountry = data;
	}).error(function(data, status, headers, config) {
		$scope.getcountry = "Response Fail";
	});
	
	var link = baseUrl+'getState';
	$http.get(link).success( function(data, status, headers, config) {
		$scope.getrelatedstate = data;
	}).error(function(data, status, headers, config) {
		$scope.getrelatedstate = "Response Fail";
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
	
	
	$scope.getmemberdetail = function(memberid) {
		var link = baseUrl+'getMemberEducationDetail?memberid='+memberid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmembereducationdetail = data;
		}).error(function(data, status, headers, config) {
			$scope.getmembereducationdetail = "Response Fail";
		});
				
		
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
		
		/*var link = baseUrl+'getMemberDetailByMemberId?memberid='+memberid;
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
		});	*/
		
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
					
					/*$scope.memberid = $scope.getmember.memberId;
					$scope.membershipId = $scope.getmember.membershipNumber;
					$scope.oldmembershipId = $scope.getmember.oldMembershipNumber;
					$scope.memberclubname = $scope.getmember.clubId;
					$scope.membertypename = $scope.getmember.memberTypeId;
					$scope.businesscategoryid = $scope.getmember.businessCategoryId;
					$scope.tenureplan = $scope.getmember.tenurePlan;*/
					/*document.getElementById("datepicker").value = $scope.getmember.joiningDate;*/					
					$scope.membertitle = $scope.getmember.memberNameTitle;
					$scope.firstname = $scope.getmember.memberFirstName;					
					$scope.middlename = $scope.getmember.memberMiddleName;
					$scope.lastname = $scope.getmember.memberLastName;
					$scope.gender = $scope.getmember.memberGender;
					document.getElementById("datepicker1").value = $scope.getmember.memberDateOfBirth;					
					document.getElementById("datepicker2").value = $scope.getmember.memberAnniversaryDate;					
					/*$scope.membertypename = $scope.getmember.memberBloodGroup;*/
					$scope.gender = $scope.getmember.memberGender;
					$scope.$parent.aadharnumber = $scope.getmember.memberAadharNumber;
					$scope.$parent.countrynamecitizenship = $scope.getmember.memberCountryIdCitizenship;
					$scope.passportnumber = $scope.getmember.memberPassportNumber;
					$scope.pannumber = $scope.getmember.memberPanNumber;
					$scope.profilepicture = $scope.getmember.memberProfilePicture;
					$scope.email = $scope.getmember.memberEmail;
					$scope.email1 = $scope.getmember.memberEmail;					
					$scope.memberbarcode = $scope.getmember.memberBarcode;
					$scope.memberqrcode = $scope.getmember.memberQrcode;
					
				}).error(function(data, status, headers, config) {
					$scope.getmember = "Response Fail";
				});					
			}).error(function(data, status, headers, config) {
				$scope.membercategoryname = "Response Fail";
			});
		}).error(function(data,status,headers,config) {
			$scope.currentrotaryyear = "Responce Fail";
		});
		
		var link = baseUrl+'deleteMemberEducationFront?memberid='+memberid;
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deletemembereducation = data;
		}).error(function(data, status, headers, config) {
			$scope.deletemembereducation = "Response Fail";
		});
		
		$scope.editmembereducationdetail = function(memberid) {			
			angular.forEach($scope.getmembereducationdetail, function(item) {
				if(item.degreeName != null)	{
					var link = baseUrl+'editMemberEducation?degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName+'&memberid='+memberid;			
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addmembereducation = data;						
						$scope.spin = 0;
						$scope.nospin = 1;
						$window.alert("Member Education Detail Edited Successfully");
						$window.location.href = url+"manage_my_profile";							
						
					}).error(function(data, status, headers, config) {
						$scope.addmembereducation = "Response Fail";
					});
				}
			});
			
		}
		
		
		
		
		
		
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
	

	
	$scope.addWorkLandlineRow1 = function() {
		if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumberwork").focus();
			return;
		} else {
			$scope.getmemberworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork});
		}
	};
	
	$scope.removeWorkLandlineRow1 = function(landlinephonenumber) {				
		var index = -1;		
		var comArr = eval( $scope.getmemberworklandline);
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.getmemberworklandline.splice( index, 1 );
	};
	
	
	$scope.editmemberdetail = function(memberid) {
				
		if($scope.getLastSequence1 == undefined)
			$scope.getLastSequence1 = 0;
		var temp = temp;
		var sequence = $scope.getLastSequence1 + 1;
		
					
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
			membertypename = "";
		}
		
		if(firstname == "" || firstname == undefined) {
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
			/*
			var link = baseUrl+'deleteMemberEducation?memberid='+memberid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletemembereducation = data;
			}).error(function(data, status, headers, config) {
				$scope.deletemembereducation = "Response Fail";
			});*/
			
			var link = baseUrl+'editmemberpersonaldetail1?rotaryyearid='+$scope.rotaryyearid+'&memberid='+memberid+'&membertitle='+membertitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+dateofbirth+'&bloodgroup='+bg+'&anniversarydate='+anniversarydate+'&aadharnumber='+aadharnumber+'&countrynamecitizenship='+countrynamecitizenship+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&email='+email+'&sequence='+sequence;			
			
			$http.post(link).success( function(data, status, headers, config) {
					$scope.editMemberDetail = data;
					$window.alert("Member Details updated successfuly");
					$window.location.href = url+"manage_my_profile";
				}).
				error(function(data, status, headers, config)
				{
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
	
	
	
$scope.editcontactdetail = function(memberid) {
		
		
		var address1 = $scope.address1;
		var address2 = $scope.address2;
		var address3 = $scope.address3;
		var countryname = $scope.countryname;
		var statename = $scope.statename;
		var cityname = $scope.cityname;
		var pincode = $scope.pincode;
		var mobilenumber = $scope.mobilenumber;		
		
		
		if(address2 == "" || address2 == undefined) {
			address2 = "";
		}
		if(address3 == "" || address3 == undefined) {
			address3 = "";
		}
		if(pincode == "" || pincode == undefined) {
			pincode = "";
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
		} else {
			$scope.spin = 1;
			$scope.nospin = 0;
			if(statename == "" || statename == undefined) {
				statename = 0;
			}
			
			/*var link = baseUrl+'deleteMemberResidentialLandline?memberid='+memberid;
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
			*/
			
			var link = baseUrl+'editContactDetailForMobile?memberid='+memberid+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber;			
			/*$window.alert(link);*/
			
			$http.post(link).success( function(data, status, headers, config) {
				$scope.editcontactdetail = data;
				
				var a = 0, b = 0, c = 0;
				if($scope.getmemberresidentiallandline.length == 0 && $scope.getmemberworklandline.length == 0) {
					
					$scope.spin = 0;
					$scope.nospin = 1;
					$window.alert("Data edited successfully");					
					$window.location.href = url+"manage_my_profile";
					
						
										
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
				$scope.spin = 0;
				$scope.nospin = 1;
				$window.alert("Member Details updated successfuly");
				$window.location.href = url+"manage_my_profile";
			}).
			error(function(data, status, headers, config)
			{
				$scope.editcontactdetail = "Response Fail";
			});	
			
			
			/*
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
						var link = baseUrl+'editMemberLandlinePhoneNumber?memberid='+memberid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W';
						
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
			});*/
		}
    }
	
	$scope.editworkdetail = function(memberid, membercategoryid, tenureplan, temp) {	
		
		var temp = temp;
			
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
		 if(communication == "" || communication == undefined ) {
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
			
			
			var link = baseUrl+'editWorkDetail?memberid='+memberid+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&aboutbusiness='+$scope.aboutbusiness+'&businesskeywords='+$scope.businesskeywords+'&email='+email+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communicationaddress='+communication+'&businessgoals='+$scope.businessgoals+'&accomplishments='+$scope.accomplishments+'&interests='+$scope.interests+'&networks='+$scope.networks+'&skills='+$scope.skills+'&idealreferral='+$scope.idealreferral+'&topproduct='+$scope.topproduct+'&topproblemsolved='+$scope.topproblemsolved;			
			/*$window.alert(link);*/
			
			
			$http.post(link).success( function(data, status, headers, config) {
				$scope.editWorkDetail = data;
				$window.alert("Member Details updated successfuly");
				$window.location.href = url+"manage_my_profile";
			}).
			error(function(data, status, headers, config)
			{
				$scope.editWorkDetail = "Response Fail";
			});	
			
			
			/*var formData=new FormData();
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
					$window.location.href = url+"manage_my_profile";			
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
								$window.location.href = url+"manage_my_profile";
																
							}
						}).error(function(data, status, headers, config) {
							$scope.editlandlinenumber = "Response Fail";
						});
					}
				});
				angular.forEach($scope.getmemberworklandline, function(item) {
					if(item.landlinePhoneNumber) {
						var link = baseUrl+'editMemberLandlinePhoneNumber?memberid='+memberid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W';
						
						$http.post(link).success(function(data, status, headers, config) {
							$scope.editlandlinenumber = data;
							b = b + 1;
							c = c + 1;
							if(($scope.getmemberworklandline.length == b && $scope.getmemberresidentiallandline.length == 0) || (c == $scope.getmemberresidentiallandline.length + $scope.getmemberworklandline.length)) {
								
								$scope.spin = 0;
								$scope.nospin = 1;
								$window.alert("Data added successfully");
								$window.location.href = url+"manage_my_profile";								
							}
						}).error(function(data, status, headers, config) {
							$scope.editlandlinenumber = "Response Fail";
						});
					}
				});
			}).error(function(data, status, headers, config) {
				$scope.editcontactdetail = "Response Fail";
			});*/
		}
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
				$scope.familyaddress1 = $scope.getmember.memberAddress1;
				$scope.familyaddress2 = $scope.getmember.memberAddress2;
				$scope.familyaddress3 = $scope.getmember.memberAddress3;
				$scope.familystatename = $scope.getmember.memberStateId;
				$scope.familycityname = $scope.getmember.memberCityName;
				$scope.familypincode = $scope.getmember.memberPincode;
				$scope.familymobilenumber = $scope.getmember.memberMobileNumber;
							
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
			$scope.familyaddress1 = "";
			$scope.familyaddress2 = "";
			$scope.familyaddress3 = "";
			$scope.familycountryname = "";
			$scope.familystatename = "";
			$scope.familycityname = "";
			$scope.familypincode = "";
			$scope.familymobilenumber = "";
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
				$scope.familyoccupation = $scope.getmember.memberOccupation;
				$scope.familydesignation = $scope.getmember.memberDesignation;
				$scope.familycompanyname = $scope.getmember.memberCompanyName;
				$scope.familybusinessnature = $scope.getmember.memberBusinessNature;
				$scope.familyfaxnumber = $scope.getmember.memberFaxNumber;
				$scope.familywebsite = $scope.getmember.memberWebsiteName;
				$scope.familyemail = $scope.getmember.memberCompanyEmail;
				$scope.familyaddress1work = $scope.getmember.memberCompanyAddress1;
				$scope.familyaddress2work = $scope.getmember.memberCompanyAddress2;
				$scope.familyaddress3work = $scope.getmember.memberCompanyAddress3;
				$scope.familystatenamework = $scope.getmember.memberCompanyStateId;
				$scope.familycitynamework = $scope.getmember.memberCompanyCityName;
				$scope.familypincodework = $scope.getmember.memberCompanyPincode;
				$scope.familymobilenumberwork = $scope.getmember.memberCompanyMobileNumber;
				$scope.phonenumberwork = $scope.getmember.memberCompanyPhoneNumber;
				$scope.familycommunication = $scope.getmember.memberCommunicationAddress;
				
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
			$scope.familyoccupation = "";
			$scope.familydesignation = "";
			$scope.familycompanyname = "";
			$scope.familybusinessnature = "";
			$scope.familyfaxnumber = "";
			$scope.familywebsite = "";				
			$scope.familyaddress1work = "";
			$scope.familyaddress2work = "";
			$scope.familyaddress3work = "";
			$scope.familystatenamework = "";
			$scope.familycitynamework = "";
			$scope.familypincodework = "";
			$scope.familymobilenumberwork = "";
			$scope.phonenumberwork = "";
			$scope.familycommunication = "";
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
		if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined) {
			$window.alert("Please enter phone number");
			document.getElementById("phonenumberwork").focus();
			return;
		} else {
			$scope.familyworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork});
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
		var firstname = $scope.familyfirstname;
		var middlename = $scope.familymiddlename;
		var lastname = $scope.familylastname;
		var gender = $scope.familygender;
		var birthdate = $scope.birthdate;
		var birthmonth = $scope.birthmonth;
		var birthyear = $scope.birthyear;			
		var bloodgroup = $scope.bloodgroup;			
		var aadharnumber = $scope.aadharnumber;
		var passportnumber = $scope.passportnumber;
		var pannumber = $scope.pannumber;
		var email = $scope.email;
		var password = $scope.password;
		var address1 = $scope.familyaddress1;
		var address2 = $scope.familyaddress2;
		var address3 = $scope.familyaddress3;
		var countryname = $scope.familycountryname;
		var statename = $scope.familystatename;
		var cityname = $scope.familycityname;
		var pincode = $scope.familypincode;
		var mobilenumber = $scope.familymobilenumber;			
		var occupation = $scope.familyoccupation;
		var designation = $scope.familydesignation;
		var companyname = $scope.familycompanyname;
		var businessnature = $scope.familybusinessnature;
		var faxnumber = $scope.familyfaxnumber;
		var website = $scope.familywebsite;
		var emailwork = $scope.familyemailwork;
		var address1work = $scope.familyaddress1work;
		var address2work = $scope.familyaddress2work;
		var address3work = $scope.familyaddress3work;
		var countrynamework = $scope.familycountrynamework;
		var statenamework = $scope.familystatenamework;
		var citynamework = $scope.familycitynamework;
		var pincodework = $scope.familypincodework;
		var mobilenumberwork = $scope.familymobilenumberwork;			
		var communication = $scope.familycommunication;						
		
		if(membercategoryid == "" || membercategoryid == undefined ) {
			membercategoryid = 0;
		}			
		if(memberfamilytitle == "" || memberfamilytitle == undefined ) {
			memberfamilytitle = "";
		}
		if(familymiddlename == "" || familymiddlename == undefined ) {
			familymiddlename = "";
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
		if(familyaddress2 == "" || familyaddress2 == undefined) {
			familyaddress2 = "";
		}
		if(familyaddress3 == "" || familyaddress3 == undefined) {
			familyaddress3 = "";
		}
		if(familypincode == "" || familypincode == undefined) {
			familypincode = "";
		}
		if(familymobilenumber == "" || familymobilenumber == undefined) {
			familymobilenumber = "";
		}		
		if(familyoccupation == "" || familyoccupation == undefined) {
			familyoccupation = "";
		}
		if(familydesignation == "" || familydesignation == undefined) {
			familydesignation = "";
		}
		if(familycompanyname == "" || familycompanyname == undefined) {
			familycompanyname = "";
		}
		if(familybusinessnature == "" || familybusinessnature == undefined) {
			familybusinessnature = "";
		}
		if(familyfaxnumber == "" || familyfaxnumber == undefined) {
			familyfaxnumber = "";
		}
		if(familywebsite == "" || familywebsite == undefined) {
			familywebsite = "";
		}
		if(familyemailwork == "" || familyemailwork == undefined) {
			familyemailwork = "";
		}
		if(familyaddress1work == "" || familyaddress1work == undefined) {
			familyaddress1work = "";
		}
		if(familyaddress2work == "" || familyaddress2work == undefined) {
			familyaddress2work = "";
		}
		if(familyaddress3work == "" || familyaddress3work == undefined) {
			familyaddress3work = "";
		}
		if(familycountrynamework == "" || familycountrynamework == undefined) {
			familycountrynamework = 0;
		}
		if(familystatenamework == "" || familystatenamework == undefined) {
			familystatenamework = 0;
		}
		if(familycitynamework == "" || familycitynamework == undefined) {
			familycitynamework = "";
		}				
		if(familypincodework == "" || familypincodework == undefined) {
			familypincodework = "";
		}
		if(familymobilenumberwork == "" || familymobilenumberwork == undefined) {
			familymobilenumberwork = "";
		}			
		
		if(relation == "" || relation == undefined) {
			$window.alert("Please select relation");
			document.getElementById("relation").focus();
			return;
		} else if(familyfirstname == "" || familyfirstname == undefined) {
			$window.alert("Please enter first name");
			document.getElementById("familyfirstname").focus();
			return;
		} else if(familylastname == "" || familylastname == undefined) {
			$window.alert("Please enter last name");
			document.getElementById("familylastname").focus();
			return;
		} else if(familygender == "" || familygender == undefined) {
			$window.alert("Please select gender");
			document.getElementById("familygender").focus();
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
		} else if(familyaddress1 == "" || familyaddress1 == undefined) {
			$window.alert("Please enter address line-1");
			document.getElementById("familyaddress1").focus();
			return;
		} else if(familycountryname == "" || familycountryname == undefined) {
			$window.alert("Please select country");
			document.getElementById("familycountryname").focus();
			return;
		} else if((familystatename == "" || familystatename == undefined) && familycountryname == 1) {
			$window.alert("Please select state");
			document.getElementById("familystatename").focus();
			return;
		} else if(familycityname == "" || familycityname == undefined) {
			$window.alert("Please enter city name");
			document.getElementById("familycityname").focus();
			return;
		} else if(familycommunication == "" || familycommunication == undefined) {
			$window.alert("Please select address for communication");
			document.getElementById("familycommunication").focus();
			return;
		} else {
			if(familystatename == "" || familystatename == undefined) {
				familystatename = 0;
			}			
			var birth = birthyear+"-"+birthmonth+"-"+birthdate;
						
			var bg = encodeURIComponent(bloodgroup);				
			var link = baseUrl +'addSpouseDetail?membershipno='+spouseid+'&relation='+relation+'&membercategoryid='+membercategoryid+'&memberfamilytitle='+memberfamilytitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+birth+'&bloodgroup='+bg+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&email='+email+'&password='+password+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&emailwork='+emailwork+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communication='+communication+'&sequence='+sequence+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;			
			$window.alert(link);
			var formData = new FormData();
			formData.append("profile",imgInp.files[0]);
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; } }).success(function(data, status) {
				$scope.addspousedetail = data;
				var a = 1, b = 1, c = 1, d = 1;
				if($scope.education.length == 1 && ($scope.familyresidentiallandline.length == 1 || $scope.residentialaddress == true && $scope.familyresidentiallandline.length == 0) && ($scope.familyworklandline.length == 1 || $scope.workdetail == true)) {
					$window.alert("Family Detail Added Successfully");
					$window.location.href = url+"manage_my_profile";
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
								$window.location.href = url+"manage_my_profile";								
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
								$window.location.href = url+"manage_my_profile";								
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
								$window.location.href = url+"manage_my_profile";								
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
				var link = baseUrl +'editSpouseDetail1?membersfamilyid='+membersfamilyid+'&relation='+relation+'&membercategoryid='+membercategoryid+'&memberfamilytitle='+memberfamilytitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+birth+'&bloodgroup='+bg+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&profilepicture='+profilepicture+'&email='+email+'&password='+password+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&emailwork='+emailwork+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communication='+communication+'&spouseid='+spouseid+'&sequence='+sequence+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;					
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
						$window.location.href = url+"manage_my_profile";						
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
									$window.location.href = url+"manage_my_profile";									
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
									$window.location.href = url+"manage_my_profile";									
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
									$window.location.href = url+"manage_my_profile";						
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
						$window.location.href = url+"manage_my_profile";					
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
									$window.location.href = url+"manage_my_profile";									
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
									$window.location.href = url+"manage_my_profile";
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
									$window.location.href = url+"manage_my_profile";									
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
		$window.location.href = adminurl + "manage_contact_detail?memberid="+memberid;
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
	
	
	
	$scope.editmemberprofile = function(memberid) {
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
			
		var profilepicture = $scope.profilepicture;				
		
			var link = baseUrl +'editProfilePicture1?memberid='+memberid+'&profileimage='+profilepicture+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;					
			
			var formData = new FormData();
			formData.append("profilepicture",imgInp2.files[0]);
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) {
				return data;
			}
			}).success(function(data, status) {
				$scope.addspousedetail = data;
				$window.alert("Profile Picture Updated Successfully");
				$window.location.href = url+"manage_my_profile";
			}).error(function(data, status, headers, config) {
				$scope.editspousedetail = "Response Fail";
			});
			
	}
	
	
	
	
	
	
	
	
	
});