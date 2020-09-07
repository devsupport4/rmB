app.controller('eventRegistrationCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
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
		
		$scope.eventtype = 'All';
		$scope.currentPage = 0;
		$scope.pageSize = 10;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	    
	    $scope.numberOfPages=function() {
			return Math.ceil($scope.getOrders.length/$scope.pageSize);
		}

	    var link = baseUrl+'getCountry';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcountry = data;
		}).error(function(data,status,headers,config) {
			$scope.getcountry = "Response Fail";
		});
		
		
		var link = baseUrl + 'Events';
		$http.get(link).success(function(data, status, headers, config)
		{
			$scope.events = data;
		}).error(function(data, status, headers, config) 
		{
			$scope.events = "Response Fail";
		});
		
		/*
		var link = baseUrl+'getOrderDetailsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getallorderdetails = data;		
		}).error(function(data, status, headers, config) {
			$scope.getallorderdetails = "Response Fail";
		});*/
		
		
		$scope.memberlistUpdate = function(input){
			if($scope.membertype == "")
			{
				return true;
			}
			return angular.equals(input, $scope.membertype);
		}
		
		
		$scope.getAllRegistrations = function() {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			
			document.getElementById("datepicker").value = $scope.fromdate;
			document.getElementById("datepicker1").value = $scope.todate;
			
			var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
						
		}
		
		
		$scope.getOrdersByDate = function() {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}	
		
		
		$scope.paymenStat = function(){
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;
				if($scope.paymentstatusfilter == "Paid"){
					$scope.getOrdersf = [];
					for(i=0;i<$scope.getOrders.length;i++){
						if($scope.getOrders[i].paymentStatu == "Paid" || $scope.getOrders[i].paymentStatu == "Success"){
							$scope.getOrdersf.push($scope.getOrders[i]);
						}
					}
				}else if($scope.paymentstatusfilter == "Unpaid"){
					$scope.getOrdersf = [];
					for(i=0;i<$scope.getOrders.length;i++){
						if($scope.getOrders[i].paymentStatu == "Unpaid" && $scope.getOrders[i].payReq != "No"){
							$scope.getOrdersf.push($scope.getOrders[i]);
						}
					}
				}else if($scope.paymentstatusfilter == "Free Event"){
					$scope.getOrdersf = [];
					for(i=0;i<$scope.getOrders.length;i++){
						if($scope.getOrders[i].payReq == "No" || ($scope.getOrders[i].amount == '0.00' && $scope.getOrders[i].paymentStatu == 'Paid')){
							$scope.getOrdersf.push($scope.getOrders[i]);
						}
					}
				}else if($scope.paymentstatusfilter == ""){
					$scope.getOrdersf = $scope.getOrders;
				}
				else{
					$scope.getOrdersf = [];
					for(i=0;i<$scope.getOrders.length;i++){
						if($scope.paymentstatusfilter == $scope.getOrders[i].paymentStatu){
							$scope.getOrdersf.push($scope.getOrders[i]);
						}
					}
				}$scope.getOrders = $scope.getOrdersf;
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.paySt = function(){
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;
				if($scope.eventtype == "Online"){
					$scope.getOrdersf = [];
					for(i=0;i<$scope.getOrders.length;i++){
						if($scope.getOrders[i].payStat == "Online"){
							$scope.getOrdersf.push($scope.getOrders[i]);
						}
					}
				}else if($scope.eventtype == "Bank"){
					$scope.getOrdersf = [];
					for(i=0;i<$scope.getOrders.length;i++){
						if($scope.getOrders[i].payStat == "Bank"){
							$scope.getOrdersf.push($scope.getOrders[i]);
						}
					}
				}else if($scope.eventtype == ""){
					$scope.getOrdersf = $scope.getOrders;
				}
				$scope.getOrders = $scope.getOrdersf;
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.changepage = function() {			
			if($scope.pageSize == "All") {		
				var link = baseUrl + 'getAllRegisteredMembers';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			} else {			
				var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;			
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			}		
		}
		
		$scope.next = function() {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;				
			var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.prev = function() {
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;		
			var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.searchRecord = function() {
			var search = $scope.search;		
			if(search == undefined || search == "") {						
				var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;			
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchEventRegisteredMembers?keyword='+search+'&eventtype='+$scope.eventtype;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getalleventdetails = data;				
				}).error(function(data, status, headers, config) {
					$scope.getalleventdetails = "Response Fail";
				});
			}
		}
		
		$scope.getOrderDetailsById = function(eventregistrationid) {
			var link = baseUrl + 'getRegisteredMemberDetailByeventregistrationid?eventregistrationid='+eventregistrationid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventdetails = data;
				$scope.orderid = $scope.geteventdetails.eventRegsitrationId;
				$scope.customername = $scope.geteventdetails.firstName+" "+$scope.geteventdetails.lastName;
				$scope.emailid =  $scope.geteventdetails.emailId;
				$scope.mobileno =  $scope.geteventdetails.mobileNo;
				$scope.total =  $scope.geteventdetails.amount;
				$scope.paymentstatus= $scope.geteventdetails.paymentStatu;
				if($scope.geteventdetails.payReq == "No"){$scope.paymentstatus="free"};
				$scope.bankrefno =  $scope.geteventdetails.paymentRefNo;
				$scope.failuremsg = $scope.geteventdetails.failureMsg;
				if($scope.geteventdetails.orderDetails){
					$scope.hidegrid = false;
					$scope.orderDetails = $scope.geteventdetails.orderDetails;
					$scope.ordernumber =  $scope.orderDetails.orderNumber;
					$scope.billername =  $scope.orderDetails.billerName;
					$scope.billeremail =  $scope.orderDetails.billerEmailid;
					$scope.billeraddress1 =  $scope.orderDetails.billerAddress1;
					$scope.billeraddress2 =  $scope.orderDetails.billerAddress2;
					$scope.billercountry =  $scope.orderDetails.billerCountryName;
					$scope.billerstate =  $scope.orderDetails.billerStateName;
					$scope.billercity =  $scope.orderDetails.billerCityName;
					$scope.billerpincode =  $scope.orderDetails.billerPincode;
					$scope.billermobileno =  $scope.orderDetails.billerMobileno;
					$scope.subtotal =  $scope.orderDetails.subTotal;
					$scope.orderstatus =  $scope.orderDetails.orderStatus;
					$scope.trackingid =  $scope.orderDetails.trackingId;
					$scope.paymentmode =  $scope.orderDetails.paymentMode;
					$scope.cardname =  $scope.orderDetails.cardName;
					$scope.bankname =  $scope.orderDetails.bankName;
					$scope.orderdate =  $scope.orderDetails.orderDate;
					$scope.pymntst = $scope.orderDetails.paymentStatus;
				}else{
					$scope.hidegrid = true;
					$scope.orderDetails = "";
					$scope.ordernumber =  "";
					$scope.billername =  "";
					$scope.billeremail =  "";
					$scope.billeraddress1 = "";
					$scope.billeraddress2 = "";
					$scope.billercountry = "";
					$scope.billerstate =  "";
					$scope.billercity =  "";
					$scope.billerpincode =  "";
					$scope.billermobileno = "";
					$scope.subtotal =  "";
					$scope.orderstatus = "";
					$scope.trackingid =  "";
					if($scope.geteventdetails.payStat){$scope.paymentmode = $scope.geteventdetails.payStat;}
					else{$scope.paymentmode = "";}
					$scope.cardname =  "";
					$scope.bankname =  "";
					$scope.orderdate = "";
					$scope.pymntst = "";
				}
			
		}).error(function(data, status, headers, config) {
			$scope.geteventdetails = "Response Fail";
		});
		}
		
	$scope.changeStatus=function(orderid, pymntst) {
		
		if(pymntst==undefined || pymntst=="") {			
			$scope.errorPayment = 1;
			$scope.msgPayment = "Please select Payment Status";
			document.getElementById("pymntst").focus();
		} else {
			$scope.spin = 1;
		
		deleteC = $window.confirm('Are you sure you want to update payment status?');
		if(deleteC) {			
			var link = baseUrl+'updateEventRegistrationStatus?eventregistrationID='+orderid+'&status='+pymntst;
			$http.post(link).success(function(data, status, headers, config) {
					$scope.editstatus = data;
					if ($scope.editstatus == "Success") {
						$scope.spin = 0;
        				$scope.paymentSubmitSuccess = 1;
        				
        				
        				/*if($scope.pageSize == "All") {		
        					var link = baseUrl + 'getAllOrderDetails?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&eventtype='+$scope.eventtype;
        					$http.get(link).success(function(data, status, headers, config) {
        						$scope.getOrders = data;
        					}).error(function(data, status, headers, config) {
        						$scope.getOrders = "Response Fail";
        					});
        				} else {			
        					var link = baseUrl+'getOrdersByDateAndPage?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
        					$http.get(link).success(function(data, status, headers, config) {
        						$scope.getOrders = data;			
        					}).error(function(data, status, headers, config) {
        						$scope.getOrders = "Response Fail";
        					});
        				}*/
        				        				        				
        				//angular.element('.modal').css('display', 'none');// to hide the modal
		    			$timeout(function () { 
		    				$scope.paymentSubmitSuccess = 0;
		    				$scope.msgSuccess = "Payment status updated successfully";
		    				$window.location.href = adminurl+'eventRegistrationsResult';
		    			}, 3000);
		    		} else {
		    			$scope.spin = 0;
        				$scope.paymentSubmitError = 1;
		    			$timeout(function () { 
		    				$scope.paymentSubmitError = 0;
		    				$scope.msgError = "Something went wrong!Please Try Again";
		    			}, 3000);
		    		}    				
				}).error(function(data, status, headers, config) {
						$scope.editstatus = "Response Fail";
				});
			}
		}
	}
	
	$scope.setActiveOrInactiveMember = function(mid,activemember){
		
		if(activemember=="i") {
			$scope.activemember="y";
		} else{
			$scope.activemember="i";
		}
				
		var link = baseUrl+'setMemberActiveOrInA?memberid='+mid+'&memberstatus='+$scope.activemember;
		
		$http.post(link).success(function(data, status, headers, config) {
			$scope.addemployeeproject = data;
			
			if($scope.pageSize == "All") {		
				var link = baseUrl + 'getAllRegisteredMembers';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			} else {			
				var link = baseUrl+'getAllRegisteredMembersForResult?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;			
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			}
			
		}).error(function(data, status, headers, config) {
			$scope.addenquiryassign = "Response Fail";
		});
	}
	
			
		$scope.setFlag = function() {
			$scope.errorPayment = 0;
			$scope.msgPayment = "";
		}
	
		
		$scope.registration = function(eventid, id,membertype) {
			
			var link = baseUrl + 'getEventDetailById?id='+eventid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventdetailbyid = data;
				$scope.reqpaid = $scope.geteventdetailbyid.paid;
			}).error(function(data, status, headers, config) {
				$scope.geteventdetailbyid = "Response Fail";
				$scope.userSubmitError1 = 1;
				$scope.msgError = "something went wrong while fetching data for event";
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
						
	    				if($scope.reqpaid=="Yes"){
	    					var link = baseUrl+'getLastMemberbyEmail?email='+$scope.emailadd+'&mobileno='+$scope.mobilenoadd;
	    					$http.get(link).success(function(data, status, headers, config) {
	    						$scope.memID = data;
	    						console.log("function1");
	    						if($scope.memID == 0){
	    							$scope.userSubmitError = 1;
		    						$scope.msgError = "Something went wrong, while Fetching data!";
		    						console.log("if required pay is yes and member id comes 0");
		    						$window.location.href = adminurl+"admin_home";
	    						}else{
	    							var link = baseUrl + 'getEventRegistrationDetails?eventid='+eventid+'&mid='+$scope.memID;			
	    							$http.get(link).success(function(data, status, headers, config) {
	    								$scope.geteventchargesbyid = data;
	    								console.log("function2");
	    								$scope.amount = $scope.geteventchargesbyid.amount;
	    								$scope.paymentstatus = $scope.geteventchargesbyid.paymentStatu;
	    								if(!$scope.amount || $scope.amount==0){
	    									var link = baseUrl+'updateEventRegistrationPayment?memberid='+$scope.memID+'&eid='+eventid;
	    									$http.post(link).success(function(data, status, headers, config) {
	    										console.log("function3");
	    										$scope.saveref = data;
	    										$scope.paymentspin=0;
	    									}).error(function(data, status, headers, config) {
	    										$scope.saveref = "Response Fail";
	    									});
	    									
	    								}else{
	    									var link = baseUrl+'updateEventPayment?memberid='+$scope.memID+'&eid='+eventid+'&paymentrefno=obtainedBYADMIN&amount='+$scope.amount;
	    									$http.post(link).success(function(data, status, headers, config) {
	    										$scope.saveref = data;
	    										console.log("function4");
	    										$scope.paymentspin=0;				
	    										$window.location.reload();
	    									}).error(function(data, status, headers, config) {
	    										$scope.saveref = "Response Fail";
	    									});
	    								}
	    							}).error(function(data, status, headers, config) {
	    								$scope.geteventchargesbyid = "Response Fail";
	    							});
	    						}
	    					}).error(function(data, status, headers, config) {
	    						$scope.memID = "Response Fail";
	    					});
    					}
	    				
	    				
	    				$timeout(function() {
	    					if($scope.reqpaid=="Yes"){
	    						alert("Thank you for registering");
	    						console.log("if required pay is yes");
	    						$window.location.href = adminurl+'eventRegistrationsResult';
	    						
	    					} else if($scope.reqpaid=="No"){
	    						alert("Thank you for registering");
	    						console.log("if required pay is no");
	    						$window.location.href = adminurl+'eventRegistrationsResult';
	    					}  else {
	    						$scope.userSubmitError = 1;
	    						$scope.msgError = "Something went wrong, while redirecting Please Try again!";
	    						console.log("Something went wrong, while redirecting Please Try again!");
	    						$window.location.href = adminurl+'admin_home';
	    					}
	    				}, 2000);
	    			} else if($scope.addrotaryuser == "Unsuccess") {
	    				$scope.spin = 0;
	    				$scope.userSubmitError = 1;
	    				$scope.msgError = "It seems the user already have an account,tell them to proceed from frontend ";
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
		
		$scope.RegisterNewUser = function(eventDropReg,membertypeReg){
			$scope.eventREG = eventDropReg;
			$scope.membertypeREG = membertypeReg;
			if(!$scope.eventREG){
				alert("select event for which you want to register member");
			}
			else if(!$scope.membertypeREG){
				alert("select member type for of the member");
			}
			else{
				var link = baseUrl + 'Events';
				$http.get(link).success(function(data, status, headers, config)
				{
					$scope.events = data;
					for(i=0; i<$scope.events.length;i++){
						if($scope.events[i].eventId == $scope.eventREG){
							$scope.eventName = $scope.events[i].eventTitle;
						}
					}
					
					$("#RegisterMember").modal('hide');
					$("#RegisterMem").modal();
				}).error(function(data, status, headers, config) 
				{
					$scope.events = "Response Fail";
				});
			}
		}
		
		
});