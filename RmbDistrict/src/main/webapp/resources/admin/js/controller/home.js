app.controller('homeCtrl',function($scope, $http, $window, $filter, $location) {
	
	var baseUrl = $location.protocol() + "://" + location.host + u;
	



	/*Image calling */
	$scope.redirectSlider=function(redirectUrl){
		
		location.href=redirectUrl;
	}
	var link = baseUrl+'getActiveSliders';

	$http.get(link).
     success(function (data, status, headers, config) {
         //debugger;
         $scope.activeSlider = data;
     }).
     error(function (data, status, headers, config) {
    	   $scope.activeSlider = "No response";
     });
	
	/*Image calling end */
	


	var link = baseUrl+'getAllFellowshipName';  

	$http.get(link).success(function(data, status, headers, config) {
		$scope.getAllFellowshipNameList = data;
		
	}).error(function(data, status, headers, config) {
		$scope.getAllFellowshipNameList = "Response Fail";
	});
	
	var link = baseUrl + 'getLastThreeNewsForHome';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getLastThreeNewsForHome = data;
	}).error(function(data, status, headers, config) {
		$scope.getLastThreeNewsForHome = "Response Fail";
	});
	
	var link = baseUrl + 'Events';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.events = data;
	}).error(function(data, status, headers, config) {
		$scope.events = "Response Fail";
	});
	
	var link = baseUrl+'getLastEightMembersPics';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getLastEightMembersPics = data;			
	}).error(function(data, status, headers, config) {
		$scope.getLastEightMembersPics = "Response Fail";
	});
			
	var link = baseUrl+'getAllMemberPost';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allmemberpost = data;				
	}).error(function(data, status, headers, config) {
		$scope.allmemberpost = "Response Fail";
	});
	
	var link = baseUrl+'getLastEventDetailForFront';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getlasteventdetail = data;
		$scope.eid =$scope.getlasteventdetail.eventId;
		
		var link = baseUrl+'getRelatedEventImages?eventid='+$scope.eid;				
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getimages = data;
		}).error(function(data, status, headers, config) {
			$scope.getimages = "Response Fail";
		});
		
	}).error(function(data, status, headers, config) {
		$scope.getlasteventdetail = "Response Fail";
	});
	
	var link = baseUrl+'getLastThreeMemberPost';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getLastThreeMemberPost = data;				
	}).error(function(data, status, headers, config) {
		$scope.getLastThreeMemberPost = "Response Fail";
	});
			
	$scope.searchByCategory = function(search){
		$scope.search = search;
		var link = baseUrl+'searchMembers?keyword='+$scope.search;				
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;
			$window.location.href = url+'search_result?search='+$scope.search;					
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
	}
			
	$scope.openComments = function(postid) {				
		if(document.getElementById(postid).style.display == "block"){
			document.getElementById(postid).style.display = "none";
		} else {
			document.getElementById(postid).style.display = "block";
			var link = baseUrl+'getPostCommentsById?postid='+postid;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.postallcomments = data;											
			}).error(function(data, status, headers, config) {
				$scope.postallcomments = "Response Fail";
			});
		}
	}
			
	$scope.addComment = function(postid, i) {				
		$scope.comment = document.getElementById('comment'+i).value;				
		if($scope.comment == undefined || $scope.comment == "") {
			$window.alert("Please enter some text");
			document.getElementById('comment'+i).focus();
			return;
		} else {
			var link = baseUrl+'addComment?postid='+postid+'&postcomment='+$scope.comment;					
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addcomment = data;						
				var link = baseUrl+'getPostCommentsById?postid='+postid;					
				$http.get(link).success(function(data, status, headers, config) {
					$scope.postallcomments = data;											
				}).error(function(data, status, headers, config) {
					$scope.postallcomments = "Response Fail";
				});
				document.getElementById('comment'+i).value = "";
			}).error(function(data, status, headers, config) {
				$scope.addcomment = "Response Fail";
			});
		}
	}
			
	$scope.showAlert = function() {
		$window.alert("Please login in order to post your message!");
		$window.location.href = url+'login';
	}
			
	$scope.createNewPost = function() {
		if($scope.memberpost == undefined) {
			$window.alert("Please enter some text");
			document.getElementById("memberpost").focus();
			return;
		} else {
			var link = baseUrl+'addNewMemberPost?memberpost='+$scope.memberpost;					
			$http.post(link).success(function(data, status, headers, config) {
				$scope.newmemberpost = data;
				$scope.memberpost = "";
				var link = baseUrl+'getAllMemberPost';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.allmemberpost = data;			
				}).error(function(data, status, headers, config) {
					$scope.allmemberpost = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.newmemberpost = "Response Fail";
			});
		}
	}				
			
	$scope.getCurrentDefaultYear = function () {				
		var link = baseUrl + 'getClubInfo';
		$http.get(link).success(function(data,status,headers,config){
			$scope.clubinfo = data;
			
			$scope.title = $scope.clubinfo.clubTitle;
			$scope.shorttitle = $scope.clubinfo.clubShortitle;
			$scope.clublogo = $scope.clubinfo.clubLogo;
			$scope.add1 = $scope.clubinfo.meetingAddress1;
			$scope.add2 = $scope.clubinfo.meetingAddress2;
			$scope.day = $scope.clubinfo.meetingDay;
			$scope.time = $scope.clubinfo.meetingTime;
			$scope.map = $scope.clubinfo.mapLink;
			
			var link = baseUrl+'getLastTenCategoriesForHome';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getLastTenCategoriesForHome = data;
				
				var todaydate = $filter('date')(new Date(),'yyyy-MM-dd'); 
				var link = baseUrl + 'getLastThreeEventsByDate?todaydate='+todaydate;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getlastthreeeventsbydate = data;
				}).error(function(data, status, headers, config) {
					$scope.getlastthreeeventsbydate = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.getLastTenCategoriesForHome = "Response Fail";
			});
								
		}).error(function(data,status,headers,config){
			$scope.clubinfo = "Responce Fail";
		});				    
	}		
			
	$scope.redirecttonewsdetail = function(id) {
		$window.location.href = url+'news_detail?id='+id;				
	}
	
	$scope.getBirthdaysAndAnniversariesByDate = function () {
		var currentdate = $filter('date')(new Date(),'MM-dd');
			
		var link = baseUrl+'getFistFourBirthdaysByDate?currentdate='+currentdate;					
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getfirstfourbirthdaysbydate = data;			
		}).error(function(data, status, headers, config) {
			$scope.getfirstfourbirthdaysbydate = "Response Fail";
		});
			
		/*var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getallanniversariesbydate = data;			
		}).error(function(data, status, headers, config) {
			$scope.getallanniversariesbydate = "Response Fail";
		});*/
    }
			
});