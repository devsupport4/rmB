app.controller('registrationListCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
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
		
		/*
		var link = baseUrl+'getOrderDetailsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getallorderdetails = data;		
		}).error(function(data, status, headers, config) {
			$scope.getallorderdetails = "Response Fail";
		});*/
		
		
		
		
		$scope.getAllOrders = function() {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			
			document.getElementById("datepicker").value = $scope.fromdate;
			document.getElementById("datepicker1").value = $scope.todate;
			
			var link = baseUrl+'getOrdersByDateAndPage?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+"All";
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
			var link = baseUrl + 'getAllOrderDetails?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&eventtype='+"All";
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrderscount = data;
			}).error(function(data, status, headers, config) {
				$scope.getOrderscount = "Response Fail";
			});
						
		}
		
		$scope.getOrdersByDate = function() {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getOrdersByDateAndPage?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
			var link = baseUrl + 'getAllOrderDetails?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&eventtype='+"All";
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrderscount = data;
				//$scope.totalcoiunt = $scope.getOrders.length;
				
				
			}).error(function(data, status, headers, config) {
				$scope.getOrderscount = "Response Fail";
			});
		}	
		
		
		$scope.searchOrder = function(search1) {
			if(!search1) {						
				$scope.fromdate = document.getElementById("datepicker").value;
				$scope.todate = document.getElementById("datepicker1").value;
				
				var link = baseUrl+'getOrdersByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;			
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			} else {						
				var link = baseUrl+'searchOrderMembers?keyword='+search1;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getOrders = data;				
				}).error(function(data, status, headers, config) {
					$scope.getOrders = "Response Fail";
				});
			}
		}
		
		

		var link = baseUrl+'getEventRegistrationDetailsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.getalleventdetails = data;		
		}).error(function(data, status, headers, config) {
			$scope.getalleventdetails = "Response Fail";
		});
		
		
		$scope.changepage = function() {			
			if($scope.pageSize == "All") {		
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
			}		
		}
		
		$scope.next = function() {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;				
			var link = baseUrl+'getOrdersByDateAndPage?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.prev = function() {
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;		
			var link = baseUrl+'getOrdersByDateAndPage?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOrders = data;			
			}).error(function(data, status, headers, config) {
				$scope.getOrders = "Response Fail";
			});
		}
		
		$scope.searchRecord = function() {
			var search = $scope.search;		
			if(search == undefined || search == "") {						
				var link = baseUrl+'getOrdersByDateAndPage?fromdate='+$scope.fromdate+'&todate='+$scope.todate+'&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex+'&eventtype='+$scope.eventtype;
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
		
		$scope.getOrderDetailsById = function(ordernumber) {
			var link = baseUrl + 'getOrderDetailByOrderNumber?ordernumber='+ordernumber;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.geteventdetails = data;
			
				$scope.orderid = $scope.geteventdetails.orderDetailId;
				$scope.customername = $scope.geteventdetails.customerName;
				$scope.emailid =  $scope.geteventdetails.customerEmailid;
				$scope.mobileno =  $scope.geteventdetails.customerMobileno;
				$scope.ordernumber =  $scope.geteventdetails.orderNumber;
				$scope.billername =  $scope.geteventdetails.billerName;
				$scope.billeremail =  $scope.geteventdetails.billerEmailid;
				$scope.billeraddress1 =  $scope.geteventdetails.billerAddress1;
				$scope.billeraddress2 =  $scope.geteventdetails.billerAddress2;
				$scope.billercountry =  $scope.geteventdetails.billerCountryName;
				$scope.billerstate =  $scope.geteventdetails.billerStateName;
				$scope.billercity =  $scope.geteventdetails.billerCityName;
				$scope.billerpincode =  $scope.geteventdetails.billerPincode;
				$scope.billermobileno =  $scope.geteventdetails.billerMobileno;
				$scope.subtotal =  $scope.geteventdetails.subTotal;
				$scope.total =  $scope.geteventdetails.totalAmount;
				$scope.orderstatus =  $scope.geteventdetails.orderStatus;
				$scope.paymentstatus= $scope.geteventdetails.paymentStatus;
				$scope.trackingid =  $scope.geteventdetails.trackingId;
				$scope.bankrefno =  $scope.geteventdetails.bankReferenceNumber;
				$scope.failuremsg = $scope.geteventdetails.failureMessage;
				$scope.paymentmode =  $scope.geteventdetails.paymentMode;
				$scope.cardname =  $scope.geteventdetails.cardName;
				$scope.bankname =  $scope.geteventdetails.bankName;
				$scope.orderdate =  $scope.geteventdetails.orderDate;
				$scope.pymntst = $scope.geteventdetails.paymentStatus;
				/*$scope.eventdate=  $scope.geteventdetails.eventDate;
				$scope.currencytype=$scope.geteventdetails.currencyName;
				$scope.currencycode=$scope.geteventdetails.currencyCode;
				$scope.amount=$scope.geteventdetails.amount;
				$scope.paymentstatus = $scope.geteventdetails.paymentStatu;
				$scope.pymntst = $scope.geteventdetails.paymentStatu;*/
			
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
			var link = baseUrl+'updateStatus?id='+orderid+'&status='+pymntst;
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
		    				$window.location.href = adminurl+'manage_orders';
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