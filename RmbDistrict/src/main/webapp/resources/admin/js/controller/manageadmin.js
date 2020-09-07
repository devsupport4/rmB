	app.controller('ManageAdminCtrl', function($scope, $http, $window, $filter,$location)
	{
	
	
		
		$scope.addManageAdmindata = function(){
			var baseUrl = $location.protocol() + "://" + location.host + u;
	
			var link = baseUrl+'addManageAdmin';		

			$http({url: link, method: "POST", data: $scope.manageAdmin}).success(function(data, status, headers, config) {
				$scope.manageAdmindata = data;			
				$scope.getAdminMember();
				}).error(function(data, status, headers, config) {
					$scope.manageAdmindata = "Response Fail";
				});
			}

		var baseUrl = $location.protocol() + "://" + location.host + u;
		var link = baseUrl+'getAllFellowshipName';  
		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAllFellowshipNameList = data;
			
		}).error(function(data, status, headers, config) {
			$scope.getAllFellowshipNameList = "Response Fail";
		});
		$scope.getmember = function(fellowship_id){
			
		var link = baseUrl+'getAllMemberNameList';  

		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAllMemberNamedata = data;
			
		}).error(function(data, status, headers, config) {
			$scope.getAllMemberNamedata = "Response Fail";
		});
		
		}	
		
		$scope.getAdminMember = function(){
			
		var link = baseUrl+'getAllAdminList';  
	
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAllAdmindata = data;
			
		}).error(function(data, status, headers, config) {
			$scope.getAllAdmindata = "Response Fail";
		});
		
		}	
		var link = baseUrl+'getAllAdminList';  

		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAllAdmindata = data;
			
		}).error(function(data, status, headers, config) {
			$scope.getAllAdmindata = "Response Fail";
		});
		
		$scope.getfellowshipName = function(fellowship_id){
			var baseUrl = $location.protocol() + "://" + location.host + u;
			var link = baseUrl+'getFellowshipById?fellowship_id='+fellowship_id;	
			$http.get(link).success(function(data, status, headers, config) {			
				$scope.fellowship_id=fellowship_id;
				$scope.fellowship = data;							
			}).error(function(data, status, headers, config) {
				$scope.fellowship = "Response Fail";
			});
			
		};
		
		


			$scope.editllowship = function(fellowship_id){
				var baseUrl = $location.protocol() + "://" + location.host + u;
				var link = baseUrl+'editFellowshipName?fellowship_id='+fellowship_id;			
				$http({url: link, method: "POST", data: $scope.fellowship}).success(function(data, status, headers, config) {
					$scope.fellowship_id=fellowship_id;
				
					$scope.fellowship1 = data;			
				  location.reload(true); 
					}).error(function(data, status, headers, config) {
						$scope.fellowship1 = "Response Fail";
					});
				
			};
			$scope.deleteAdminMember1 = function(member_id) {
				var baseUrl = $location.protocol() + "://" + location.host + u;
				var link = baseUrl+'deleteAdmin?member_id='+member_id;
				
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.member_id=member_id;
					$scope.manageadminList = data;			
					$scope.getAdminMember();
					}).error(function(data, status, headers, config) {
						$scope.manageadminList = "Response Fail";
					});
			}
			
			

			$scope.deleteAdminMember = function() {
				deleteC = $window.confirm('Are you sure you want to delete record?');
				if(deleteC) {			
				    angular.forEach($scope.getAllAdmindata,
				    		function(item) {		    			
				    			if (item.selected) {
				    					var link = baseUrl+'deleteAdmin?member_id='+item.member_id;	
				    					
					    				$http['delete'](link).success(function(data, status, headers, config) {
					    							$scope.deleteclub = data;
					    						}).error(function(data, status, headers, config) {
					    							$scope.deleteclub = "Response Fail";
					    						});
				    				}
				    				
				    		});
				//	$window.location.href = adminurl+'manage_admin';
				}$scope.getAdminMember();
			}
			$scope.deleteAdminMember11 = function() {
				deleteC = $window.confirm('Are you sure you want to delete record?');
				if(deleteC) {			
				    angular.forEach($scope.getAllAdmindata,
				    		function(item) {		    			
				    			if (item.selected) {
				    					var link = baseUrl+'deleteAdmin?member_id='+item.member_id;	
				    					
					    				$http['delete'](link).success(function(data, status, headers, config) {
					    							$scope.deleteclub = data;
					    						}).error(function(data, status, headers, config) {
					    							$scope.deleteclub = "Response Fail";
					    						});
				    				}
				    				
				    		});
					$window.location.href = adminurl+'manage_admin';
				}
			}
	});