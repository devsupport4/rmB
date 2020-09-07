app.controller('orderHistoryCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
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
		
		$scope.memberid = "";
		
		$scope.getAllOrders = function(memberid) {	
			$scope.memberid = memberid;
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			
			document.getElementById("datepicker").value = $scope.fromdate;
			document.getElementById("datepicker1").value = $scope.todate;
			
			var link = baseUrl + 'getAllRegisteredMembers';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
			/*$scope.getOrders = $scope.filterByMember();*/
						
		}
		
		/*$scope.filterByMember = function(){
			$scope.getOrdersf=[];
			alert("inside filter"+$scope.getOrders);
			if($scope.getOrders != "Response Fail")
			{alert("inside if");
				for(i=0;i<$scope.getOrders.length;i++){
					if($scope.getOrders[i].memberId == $scope.memberid){
						$scope.getOrdersf.push($scope.getOrders[i]);
					}
				}
			}
			return $scope.getOrdersf;
		}*/
		
		$scope.getOrdersByDate = function() {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl + 'getAllRegisteredMembers';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}	
		
		
		$scope.paymenStat = function(){
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl + 'getAllRegisteredMembers';
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
			
			var link = baseUrl + 'getAllRegisteredMembers';
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
				var link = baseUrl + 'getAllRegisteredMembers';
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
			var link = baseUrl + 'getAllRegisteredMembers';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.prev = function() {
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;		
			var link = baseUrl + 'getAllRegisteredMembers';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.searchRecord = function() {
			var search = $scope.search;		
			if(search == undefined || search == "") {						
				var link = baseUrl + 'getAllRegisteredMembers';
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
	
			
		$scope.setFlag = function() {
			$scope.errorPayment = 0;
			$scope.msgPayment = "";
		}
	
		
		
});