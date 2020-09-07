var app = angular.module("rcbs", []);

var u = "/RmbDistrict/";
var url = "/RmbDistrict/";
var adminurl = "/RmbDistrict/manageRmbDistrict/";

/*var u = "/";
var url = "/";
var adminurl = "/manageRmbDistrict/";*/

app.controller('clubCtrl', ['$scope', '$filter', '$http', '$window', '$location' , '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.search = '';
    $scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.successMsg = true;
	$scope.errorMsg = true;
	
	var link = baseUrl+'getAllClubs';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allclubs = data;			
	}).error(function(data, status, headers, config) {
		$scope.allclubs = "Response Fail";
	});
	
	
	var link = baseUrl+'getClubsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
	$http.get(link).success(function(data, status, headers, config)	{
		$scope.clubs = data;		
	}).error(function(data, status, headers, config) {
		$scope.clubs = "Response Fail";
	});
	
	
	$scope.changepage = function() {			
		if($scope.pageSize == "All") {		
			var link = baseUrl+'getAllClubs';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.clubs = data;			
			}).error(function(data, status, headers, config) {
				$scope.clubs = "Response Fail";
			});
		} else {			
			var link = baseUrl+'getClubsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.clubs = data;		
			}).error(function(data, status, headers, config) {
				$scope.clubs = "Response Fail";
			});
		}		
	}
	
	$scope.next = function() {
		$scope.currentPage = $scope.currentPage + 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;				
		var link = baseUrl+'getClubsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.clubs = data;		
		}).error(function(data, status, headers, config) {
			$scope.clubs = "Response Fail";
		});
	}
	
	$scope.prev = function() {
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;		
		var link = baseUrl+'getClubsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.clubs = data;		
		}).error(function(data, status, headers, config) {
			$scope.clubs = "Response Fail";
		});			
	}
	
	$scope.searchRecord = function() {
		var search = $scope.search;		
		if(search == undefined || search == "") {						
			var link = baseUrl+'getClubsByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.clubs = data;		
			}).error(function(data, status, headers, config) {
				$scope.clubs = "Response Fail";
			});
		} else {						
			var link = baseUrl+'searchClub?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.clubs = data;				
			}).error(function(data, status, headers, config) {
				$scope.clubs = "Response Fail";
			});
		}
	}
	
	$scope.addClub = function() {
		
		if($scope.clubdescription == undefined){
			$scope.clubdescription = "";
		}
		
		if($scope.clubname == undefined){
			$window.alert("Please enter club name");
			document.getElementById("clubname").focus();
			return;
		} else {
			var link = baseUrl+'addClub?clubname='+$scope.clubname+'&clubdescription='+$scope.clubdescription;			
			$http.post(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
	    			$scope.addclub = data;
	    			$scope.successMsg = false;
	    			$timeout(function () { 
	    				$scope.successMsg = true;
	    				$window.location.href = adminurl+'manage_clubs';
	    			}, 1000);    				    									
	    		} else {
	    			$scope.errorMsg = false;
	    			$timeout(function () { 
	    				$scope.errorMsg = true;
	    				$window.location.href = adminurl+'manage_clubs';
	    			}, 1000);
	    		}    				
			}).error(function(data, status, headers, config) {
					$scope.addclub = "Response Fail";
			});
		}
	}
	
	$scope.getClubDetail = function(clubid) {
		for (i in $scope.clubs) {
			if ($scope.clubs[i].clubId == clubid) {
				$scope.clubid = $scope.clubs[i].clubId;
				$scope.editclubname = $scope.clubs[i].clubName;
				$scope.editclubdescription = $scope.clubs[i].clubDescription;									
			}				
		}
	}
	
	$scope.editClub = function(clubid) {
		
		if($scope.editclubdescription == undefined){
			$scope.editclubdescription = "";
		}
		
		if($scope.editclubname == undefined){
			$window.alert("Please enter club name");
			document.getElementById("clubname").focus();
			return;
		} else {
			var link = baseUrl+'editClub?clubid='+clubid+'&clubname='+$scope.editclubname+'&clubdescription='+$scope.editclubdescription;			
			$http.post(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
	    			$scope.editclub = data;
	    			$scope.successMsg = false;
	    			$timeout(function () { 
	    				$scope.successMsg = true;
	    				$window.location.href = adminurl+'manage_clubs';
	    			}, 1000);    				    									
	    		} else {
	    			$scope.errorMsg = false;
	    			$timeout(function () { 
	    				$scope.errorMsg = true;
	    				$window.location.href = adminurl+'manage_clubs';
	    			}, 1000);
	    		}    				
			}).error(function(data, status, headers, config) {
					$scope.editclub = "Response Fail";
			});
		}
	}
	
	$scope.checkAll = function() {
		if ($scope.selectedAll) {
			$scope.selectedAll = false;
		}
		else {
            $scope.selectedAll = true;
        }
		angular.forEach($scope.clubs, function (item) {
			item.selected = $scope.selectedAll;
		});
	}
	
	$scope.deleteClub = function() {
		deleteC = $window.confirm('Are you sure you want to delete record?');
		if(deleteC) {			
		    angular.forEach($scope.clubs,
		    		function(item) {		    			
		    			if (item.selected) {
		    					var link = baseUrl+'deleteClub?id='+item.clubId;		    					
			    				$http['delete'](link).success(function(data, status, headers, config) {
			    							$scope.deleteclub = data;
			    						}).error(function(data, status, headers, config) {
			    							$scope.deleteclub = "Response Fail";
			    						});
		    				}
		    				
		    		});
			$window.location.href = adminurl+'manage_clubs';
		}
	}
	
}]);
