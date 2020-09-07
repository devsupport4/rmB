app.controller('VocationCtrl',function($scope, $http, $window, $filter, $location,$timeout) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		$scope.currentPage = 0;
		$scope.pageSize = 10;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	    
	    $scope.numberOfPages=function() {
			return Math.ceil($scope.getVocation.length/$scope.pageSize);
		}

	    $scope.addVocation = function(){
	    	if(!$scope.description)
	    	{
	    		$scope.description = "";
	    	}
	    	
	    	if(!$scope.title)
	    	{
	    		$scope.titleError = 1;
	    	}else{
	    		var link = baseUrl+'AddVocation?title='+$scope.title+'&desc='+$scope.description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addVocation = data;		
					if($scope.addVocation == "Success"){
						alert("Vocation added Successfully!");
						location.reload();
					}
					else{
						alert("Something went wrong while adding vocation");
					}
				}).error(function(data, status, headers, config) {
					$scope.addVocation = "Response Fail";
					alert("Something went wrong while adding vocation");
				});
	    	}
	    }
		
		
		$scope.getVocation = function(){
			var link = baseUrl+'getAllVocation';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getVocation = data;			
			}).error(function(data, status, headers, config) {
				$scope.getVocation = "Response Fail";
			});
			
		}
		
		$scope.deleteVocation = function()
		{
			d = $window.confirm('Are you sure you want to delete member?');
			if(d)
			{
			    angular.forEach($scope.getVocation,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteVocation?vocationid='+item.VocationId;
				    			$http['delete'](link).success(
				    					function(data, status, headers, config)
				    					{
				    						$scope.delteVocation = data;
				    						if($scope.delteVocation = "Delete successful"){
				    							alert("delete Successfull");
				    						}
				    					}).
				    					error(function(data, status, headers, config)
				    					{
				    						$scope.deletemembers = "Response Fail";
				    					});
			    				}
			    		});			    
			    location.reload();
			}
		}
	
		
		
});