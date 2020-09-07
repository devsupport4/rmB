var app = angular.module("rcbs", []);
//var u = "/rmbbangalore/";
var u = "/";

//var url = "/rmbbangalore/";
var url = "/";
//var adminurl = "/rmbbangalore/manageRmbb/";
var adminurl = "/manageRmbb/";

app.controller('businessCategoryCtrl', ['$scope', '$filter', '$http', '$window', '$location' , '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.search = '';
    $scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    
	var baseUrl = $location.protocol() + "://" + location.host + u;
	
	$scope.successMsg = true;
	$scope.errorMsg = true;
	
	var link = baseUrl+'getAllBusinessCategories';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allbusinesscategories = data;			
	}).error(function(data, status, headers, config) {
		$scope.allbusinesscategories = "Response Fail";
	});
	
	
	var link = baseUrl+'getBusinessCategoryByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
	$http.get(link).success(function(data, status, headers, config)	{
		$scope.businesscategories = data;		
	}).error(function(data, status, headers, config) {
		$scope.businesscategories = "Response Fail";
	});
	
	
	$scope.changepage = function() {			
		if($scope.pageSize == "All") {		
			var link = baseUrl+'getAllBusinessCategories';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.businesscategories = data;			
			}).error(function(data, status, headers, config) {
				$scope.businesscategories = "Response Fail";
			});
		} else {			
			var link = baseUrl+'getBusinessCategoryByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.businesscategories = data;		
			}).error(function(data, status, headers, config) {
				$scope.businesscategories = "Response Fail";
			});
		}		
	}
	
	$scope.next = function() {
		$scope.currentPage = $scope.currentPage + 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;				
		var link = baseUrl+'getBusinessCategoryByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.businesscategories = data;		
		}).error(function(data, status, headers, config) {
			$scope.businesscategories = "Response Fail";
		});
	}
	
	$scope.prev = function() {
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;		
		var link = baseUrl+'getBusinessCategoryByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.businesscategories = data;		
		}).error(function(data, status, headers, config) {
			$scope.businesscategories = "Response Fail";
		});			
	}
	
	$scope.searchRecord = function() {
		var search = $scope.search;		
		if(search == undefined || search == "") {						
			var link = baseUrl+'getBusinessCategoryByPage?&pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;	
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.businesscategories = data;		
			}).error(function(data, status, headers, config) {
				$scope.businesscategories = "Response Fail";
			});
		} else {						
			var link = baseUrl+'searchBusinessCategory?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.businesscategories = data;				
			}).error(function(data, status, headers, config) {
				$scope.businesscategories = "Response Fail";
			});
		}
	}
	
	$scope.addBusinessCategory = function() {
		
		if($scope.businesscategorydescription == undefined){
			$scope.businesscategorydescription = "";
		}
		
		if($scope.businesscategorytitle == undefined){
			$window.alert("Please enter category title");
			document.getElementById("businesscategorytitle").focus();
			return;
		} else {
			var bct = $scope.businesscategorytitle.replace("&","$");
			var bct1 = bct.replace("#","~");
			var bct2 = bct1.replace("%","!");
			var link = baseUrl+'addBusinessCategory?businesscategorytitle='+bct2+'&businesscategorydescription='+$scope.businesscategorydescription;			
			$http.post(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
	    			$scope.addbusinesscategory = data;
	    			$scope.successMsg = false;
	    			$timeout(function () { 
	    				$scope.successMsg = true;
	    				$window.location.href = adminurl+'business_categories';
	    			}, 1000);    				    									
	    		} else {
	    			$scope.errorMsg = false;
	    			$timeout(function () { 
	    				$scope.errorMsg = true;
	    				$window.location.href = adminurl+'business_categories';
	    			}, 1000);
	    		}    				
			}).error(function(data, status, headers, config) {
					$scope.addbusinesscategory = "Response Fail";
			});
		}
	}
	
	$scope.getBusinessCategoryDetail = function(businesscategoryid) {
		for (i in $scope.businesscategories) {
			if ($scope.businesscategories[i].businessCategoryId == businesscategoryid) {
				$scope.businesscategoryid = $scope.businesscategories[i].businessCategoryId;
				$scope.editbusinesscategorytitle = $scope.businesscategories[i].businessCategoryName;
				$scope.editbusinesscategorydescription = $scope.businesscategories[i].businessCategoryDescription;									
			}				
		}
	}
	
	$scope.editBusinessCategory = function(businesscategoryid) {
		
		if($scope.editbusinesscategorydescription == undefined){
			$scope.editbusinesscategorydescription = "";
		}
		
		if($scope.editbusinesscategorytitle == undefined){
			$window.alert("Please enter category title");
			document.getElementById("businesscategorytitle").focus();
			return;
		} else {
			var bct = $scope.editbusinesscategorytitle.replace("&","$");
			var bct1 = bct.replace("#","~");
			var bct2 = bct1.replace("%","!");
			var link = baseUrl+'editBusinessCategory?businesscategoryid='+businesscategoryid+'&businesscategorytitle='+bct2+'&businesscategorydescription='+$scope.editbusinesscategorydescription;			
			$http.post(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
	    			$scope.editbusinesscategory = data;
	    			$scope.successMsg = false;
	    			$timeout(function () { 
	    				$scope.successMsg = true;
	    				$window.location.href = adminurl+'business_categories';
	    			}, 1000);    				    									
	    		} else {
	    			$scope.errorMsg = false;
	    			$timeout(function () { 
	    				$scope.errorMsg = true;
	    				$window.location.href = adminurl+'business_categories';
	    			}, 1000);
	    		}    				
			}).error(function(data, status, headers, config) {
					$scope.editbusinesscategory = "Response Fail";
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
		angular.forEach($scope.businesscategories, function (item) {
			item.selected = $scope.selectedAll;
		});
	}
	
	$scope.deleteBusinessCategory = function() {
		deletebusinessCategory = $window.confirm('Are you sure you want to delete record?');
		if(deletebusinessCategory) {			
		    angular.forEach($scope.businesscategories,
		    		function(item) {		    			
		    			if (item.selected) {
		    					var link = baseUrl+'deleteBusinessCategory?id='+item.businessCategoryId;		    					
			    				$http['delete'](link).success(function(data, status, headers, config) {
			    							$scope.deletebusinesscategory = data;
			    						}).error(function(data, status, headers, config) {
			    							$scope.deletebusinesscategory = "Response Fail";
			    						});
		    				}
		    				
		    		});
			$window.location.href = adminurl+'business_categories';
		}
	}
	
}]);
