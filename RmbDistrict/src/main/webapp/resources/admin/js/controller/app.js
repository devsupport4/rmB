var app = angular.module("rcbs", []);

/*var u = "/";
var url = "/";
var adminurl = "/manageRmbDistrict/";*/

var u = "/RmbDistrict/";
var url = "/RmbDistrict/";
var adminurl = "/RmbDistrict/manageRmbDistrict/";

//Allow numbers only with one decimal point derective

app.directive('allowDecimalNumbers', function () {  
    return {  
        restrict: 'A',  
        link: function (scope, elm, attrs, ctrl) {  
            elm.on('keydown', function (event) {  
                var $input = $(this);  
                var value = $input.val();  
                value = value.replace(/[^0-9\.]/g, '')  
                var findsDot = new RegExp(/\./g)  
                var containsDot = value.match(findsDot)  
                if (containsDot != null && ([46, 110, 190].indexOf(event.which) > -1)) {  
                    event.preventDefault();  
                    return false;  
                }  
                $input.val(value);  
                if (event.which == 64 || event.which == 16) {  
                    // numbers  
                    return false;  
                } if ([8, 13, 27, 37, 38, 39, 40, 110].indexOf(event.which) > -1) {  
                    // backspace, enter, escape, arrows  
                    return true;  
                } else if (event.which >= 48 && event.which <= 57) {  
                    // numbers  
                    return true;  
                } else if (event.which >= 96 && event.which <= 105) {  
                    // numpad number  
                    return true;  
                } else if ([46, 110, 190].indexOf(event.which) > -1) {  
                    // dot and numpad dot  
                    return true;  
                } else {  
                    event.preventDefault();  
                    return false;  
                }  
            });  
        }  
    }  
});

// We already have a limitTo filter built-in to angular,
// let's make a startFrom filter
app.filter('startFrom', function() {
	return function(input, start) {
		start = +start; // parse to int
		return input.slice(start);
	}
});

app.directive('prettyp', function() {
	return function(scope, element, attrs) {
		$("[rel^='prettyPhoto']").prettyPhoto({
			deeplinking : false
		});
	}
})

app.directive('disallowSpaces', function() {
	  return {
	    restrict: 'A',

	    link: function($scope, $element) {
	      $element.bind('input', function() {
	        $(this).val($(this).val().replace(/ /g, ''));
	      });
	    }
	  };
	});

app.directive('capitalize', function()
		{
			return{
					require: 'ngModel',
					link: function(scope, element, attrs, modelCtrl)
					{
						var capitalize = function(inputValue)
						{
							if (inputValue == undefined) inputValue = '';
							var capitalized = inputValue.toUpperCase();
							if (capitalized !== inputValue)
							{
								modelCtrl.$setViewValue(capitalized);
								modelCtrl.$render();
							}
							return capitalized;
						}
						modelCtrl.$parsers.push(capitalize);
						capitalize(scope[attrs.ngModel]); // capitalize initial value
					}
				};
		});

/*For Convert Value of Textbox into Uppercase End*/


/*For Convert First Character of Textbox into Uppercase Start*/

app.directive('capitalizeFirst', function(uppercaseFilter, $parse)
		{
			return{
				require: 'ngModel',
				link: function(scope, element, attrs, modelCtrl)
				{
					var capitalize = function(inputValue)
					{
						var capitalized = inputValue.charAt(0).toUpperCase() + inputValue.substring(1);
						if(capitalized !== inputValue)
						{
							modelCtrl.$setViewValue(capitalized);
							modelCtrl.$render();
						}
						return capitalized;
					}
					var model = $parse(attrs.ngModel);
					modelCtrl.$parsers.push(capitalize);
					capitalize(model(scope));
				}
			};
		});



app.filter('limitHtml', function() {
    return function(text, limit) {

        var changedString = String(text).replace(/<[^>]+>/gm, '');
        var length = changedString.length;

        return changedString.length > limit ? changedString.substr(0, limit - 1) : changedString; 
    }
})

/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce)
        {
			return function(text)
			{
				return $sce.trustAsHtml(text);
			};
		}]);
/* For Print data with html tag end */

angular.module('ng').filter('cut', function () {
       return function (value, wordwise, max, tail) {
           if (!value) return '';

           max = parseInt(max, 10);
           if (!max) return value;
           if (value.length <= max) return value;

           value = value.substr(0, max);
           if (wordwise) {
               var lastspace = value.lastIndexOf(' ');
               if (lastspace !== -1) {
                 //Also remove . and , so its gives a cleaner result.
                 if (value.charAt(lastspace-1) === '.' || value.charAt(lastspace-1) === ',') {
                   lastspace = lastspace - 1;
                 }
                 value = value.substr(0, lastspace);
               }
           }

           return value + (tail || ' …');
       };
   });

	app.controller('headerCtrl', function($scope, $http, $window, $filter,$location)
	{
		var baseUrl = $location.protocol() + "://" + location.host + u;
		$scope.logout = function() 
		{
			var link = baseUrl + 'logout';
			$http.post(link).success(function(data, status, headers, config) {
				$window.location.href = url;
			}).error(function(data, status, headers, config) {
				$scope.logout = "Response Fail";
			});
		}
	});
	app.controller('ManageAdminCtrl', function($scope, $http, $window, $filter,$location)
			{
			
			
				
				$scope.addfellowship = function(){
					var baseUrl = $location.protocol() + "://" + location.host + u;
			
					var link = baseUrl+'addManageAdmin';		
					
					$http({url: link, method: "POST", data: $scope.manageAdmin}).success(function(data, status, headers, config) {
						$scope.manageAdmindata = data;			
					  location.reload(true); 
						}).error(function(data, status, headers, config) {
							$scope.manageAdmindata = "Response Fail";
						});
					}

			
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
				
				
					var baseUrl = $location.protocol() + "://" + location.host + u;
				var link = baseUrl+'getAllMemberNameList';  
				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getAllMemberNamedata = data;
					
				}).error(function(data, status, headers, config) {
					$scope.getAllMemberNamedata = "Response Fail";
				});
				

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
					$scope.deletefellowship = function(fellowship_id) {
						var baseUrl = $location.protocol() + "://" + location.host + u;
						var link = baseUrl+'deleteLocation?fellowship_id='+fellowship_id;
						
						$http['delete'](link).success(function(data, status, headers, config) {
							$scope.fellowship_id=fellowship_id;
							$scope.fellowship = data;			
						  location.reload(true); 
							}).error(function(data, status, headers, config) {
								$scope.fellowship = "Response Fail";
							});
					}
					
				
			});
	app.controller('fellowshipCtrl', function($scope, $http, $window, $filter,$location)
	{
	
	
		
		$scope.addfellowship = function(){
			var baseUrl = $location.protocol() + "://" + location.host + u;
	
			var link = baseUrl+'addfellowship';		
			
			$http({url: link, method: "POST", data: $scope.fellowship}).success(function(data, status, headers, config) {
				$scope.fellowshipdata = data;			
			  location.reload(true); 
				}).error(function(data, status, headers, config) {
					$scope.fellowshipdata = "Response Fail";
				});
			}

	
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
		
			$scope.getfellowshipList = function(){
			var baseUrl = $location.protocol() + "://" + location.host + u;
		var link = baseUrl+'getAllFellowshipName';  
		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAllFellowshipNameList = data;
			
		}).error(function(data, status, headers, config) {
			$scope.getAllFellowshipNameList = "Response Fail";
		});
		
	}
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
			$scope.deletefellowship = function(fellowship_id) {
				var baseUrl = $location.protocol() + "://" + location.host + u;
				var link = baseUrl+'deleteLocation?fellowship_id='+fellowship_id;
				
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.fellowship_id=fellowship_id;
					$scope.fellowship = data;			
				  location.reload(true); 
					}).error(function(data, status, headers, config) {
						$scope.fellowship = "Response Fail";
					});
			}
			
		
	});
	app.controller('sidebarCtrl', function($scope, $http, $window, $filter,$location)
	{
		var baseUrl = $location.protocol() + "://" + location.host + u;
		
		var link = baseUrl+'getSocialMediaLinks';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getsocialmedialinks = data;
		}).error(function(data, status, headers, config) {
			$scope.getsocialmedialinks = "Response Fail";
		});
		
		var link = baseUrl + 'getClubInfo';
		$http.get(link).success(function(data,status,headers,config){
			$scope.clubinfo = data;
			
			for (i in $scope.clubinfo) 
			{
				if ($scope.clubinfo[i].clubInfoId == 1) 
				{							
					$scope.title = $scope.clubinfo[i].clubTitle;
					$scope.shorttitle = $scope.clubinfo[i].clubShortitle;
					$scope.clublogo = $scope.clubinfo[i].clubLogo;
					$scope.add1 = $scope.clubinfo[i].meetingAddress1;
					$scope.add2 = $scope.clubinfo[i].meetingAddress2;
					$scope.day = $scope.clubinfo[i].meetingDay;
					$scope.time = $scope.clubinfo[i].meetingTime;
					$scope.map = $scope.clubinfo[i].mapLink;
				}				
			}
			
		}).error(function(data,status,headers,config){
			$scope.clubinfo = "Responce Fail";
		});
		
		$scope.getEventsByDate = function ()
	    {
			var todaydate = $filter('date')(new Date(),'yyyy-MM-dd'); 
			
			var link = baseUrl + 'EventsByDate?todaydate='+todaydate;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.eventsbydate = data;
			}).error(function(data, status, headers, config) {
				$scope.eventsbydate = "Response Fail";
			});
	    }
		
		$scope.getBirthdaysAndAnniversariesByDate = function ()
	    {
			var currentdate = $filter('date')(new Date(),'MM-dd');
			
			var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = "Response Fail";
			});
			
			var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = "Response Fail";
			});
	    }
		
		$scope.getBirthdaysAndAnniversariesByDate = function ()
	    {
			var currentdate = $filter('date')(new Date(),'MM-dd');
			
			var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = "Response Fail";
			});
			
			var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = "Response Fail";
			});
	    }
		
		$scope.checkFrontLogin = function() 
		{				
			var username = $scope.username;
			var password = $scope.password;

			if (username == undefined || username == "") {
				$window.alert("Please enter username");
				document.getElementById("username").focus();
				return;
			} else if (password == undefined || password == "") {
				$window.alert("Please enter password");
				document.getElementById("password").focus();
				return;
			} else {
				var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password;
				$http.post(link).success(function(data, status, headers,config) 
				{
					if (data == "")
					{
						$window.alert("User Name or Password Incorrect...Try Again");
					}
					else
					{
						$window.location.href = url;				
					}
				}).error(function(data, status, headers,config)
				{
					$window.alert("Some thing wrong...Try again");
				});
			}
		}
		
		$scope.checkMemberLogin = function() 
		{				
			var username = $scope.username;
			var password = $scope.password;

			if (username == undefined || username == "") {
				$window.alert("Please enter username");
				document.getElementById("username").focus();
				return;
			} else if (password == undefined || password == "") {
				$window.alert("Please enter password");
				document.getElementById("password").focus();
				return;
			} else {
				var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password;
				$http.post(link).success(function(data, status, headers,config) {
					if (data == "")	{
						$window.alert("User Name or Password Incorrect...Try Again");
					} else {
						$window.location.href = url+'members_directory';				
					}
				}).error(function(data, status, headers,config) {
					$window.alert("Some thing wrong...Try again");
				});
			}
		}
		
		$scope.Frontlogout = function() 
		{				
			var link = baseUrl + 'Frontlogout';
			$http.post(link).success(function(data, status, headers, config) {
				$window.location.href = url;
			}).error(function(data, status, headers, config) {
				$scope.frontlogout = "Response Fail";
			});
		}
		
	});

	app.controller('homeCtrl',function($scope, $http, $window, $filter, $location) {
			var baseUrl = $location.protocol() + "://" + location.host + u;			
			
			var link = baseUrl + 'News';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.news = data;
			}).error(function(data, status, headers, config) {
				$scope.news = "Response Fail";
			});
			
			var link = baseUrl+'getMember';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember = data;			
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});
			
			var link = baseUrl+'getAllMemberPost';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.allmemberpost = data;				
			}).error(function(data, status, headers, config) {
				$scope.allmemberpost = "Response Fail";
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
			
			$scope.getDefaultYear = function ()
		    {
				var link = baseUrl + 'RotaryYear';
				$http.get(link).success(function(data,status,headers,config) {
					$scope.rotaryyear = data;
					for (i in $scope.rotaryyear) {
						if ($scope.rotaryyear[i].defaultYear == "y") {
							$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;
						}
					}
					
					var link = baseUrl + 'getBoardOfDirectorsByRotaryYear?rotaryyearid='+$scope.rotaryyearid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getboardofdirectorsbyrotaryyear = data;
					}).error(function(data, status, headers, config) {
						$scope.getboardofdirectorsbyrotaryyear = "Response Fail";
					});
					
					var link = baseUrl + 'getMembersCommitteeByRotaryYear?rotaryyearid='+$scope.rotaryyearid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getmemberscommitteebyrotaryyear = data;
					}).error(function(data, status, headers, config) {
						$scope.getmemberscommitteebyrotaryyear = "Response Fail";
					});				
				}).error(function(data,status,headers,config) {
					$scope.rotaryyear = "Responce Fail";
				});				
		    }
			
			$scope.getMembersCommitteeDetailByRotaryYear = function ()
		    {				
				var rotaryyearid = $scope.rotaryyearid; 
				var link = baseUrl + 'getMembersCommitteeByRotaryYear?rotaryyearid='+$scope.rotaryyearid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmemberscommitteebyrotaryyear = data;
				}).error(function(data, status, headers, config) {
					$scope.getmemberscommitteebyrotaryyear = "Response Fail";
				});			 
		    }
			
			$scope.getBoardOfDirectorsDetailByRotaryYear = function ()
		    {				
				var rotaryyearid = $scope.rotaryyearid; 
				var link = baseUrl + 'getBoardOfDirectorsByRotaryYear?rotaryyearid='+rotaryyearid;						
				$http.get(link).success(
					function(data, status, headers, config) {
						$scope.getboardofdirectorsbyrotaryyear = data;
					}).error(
					function(data, status, headers, config) {
						$scope.getboardofdirectorsbyrotaryyear = "Response Fail";
					});			 
		    }				
			
			$scope.getCurrentDefaultYear = function () {
				
				var link = baseUrl+'getAllBusinessCategories';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.allbusinesscategories = data;			
				}).error(function(data, status, headers, config) {
					$scope.allbusinesscategories = "Response Fail";
				}); 
				
				var link = baseUrl + 'RotaryYear';
				$http.get(link).success(function(data,status,headers,config) {
					$scope.rotaryyear = data;
					for (i in $scope.rotaryyear) {
						if ($scope.rotaryyear[i].defaultYear == "y") {
							$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;
						}
					}
					var link = baseUrl + 'getNewsDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getcurrentyearnews = data;
					}).error(function(data, status, headers, config) {
						$scope.getcurrentyearnews = "Response Fail";
					});
				}).error(function(data,status,headers,config) {
					$scope.rotaryyear = "Responce Fail";
				});
				
				var link = baseUrl + 'getClubInfo';
				$http.get(link).success(function(data,status,headers,config){
					$scope.clubinfo = data;
					
					for (i in $scope.clubinfo) {
						if ($scope.clubinfo[i].clubInfoId == 1) {							
							$scope.title = $scope.clubinfo[i].clubTitle;
							$scope.shorttitle = $scope.clubinfo[i].clubShortitle;
							$scope.clublogo = $scope.clubinfo[i].clubLogo;
							$scope.add1 = $scope.clubinfo[i].meetingAddress1;
							$scope.add2 = $scope.clubinfo[i].meetingAddress2;
							$scope.day = $scope.clubinfo[i].meetingDay;
							$scope.time = $scope.clubinfo[i].meetingTime;
							$scope.map = $scope.clubinfo[i].mapLink;
						}				
					}					
				}).error(function(data,status,headers,config){
					$scope.clubinfo = "Responce Fail";
				});
				
				var todaydate = $filter('date')(new Date(),'yyyy-MM-dd'); 
				var link = baseUrl + 'EventsByDate?todaydate='+todaydate;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.eventsbydate = data;
				}).error(function(data, status, headers, config) {
					$scope.eventsbydate = "Response Fail";
				});			    
			}		
			
			$scope.redirecttonewsdetail = function(id)
			{
				$window.location.href = url+'news_detail?id='+id;				
			}
			
			$scope.getBirthdaysAndAnniversariesByDate = function ()
		    {
				var currentdate = $filter('date')(new Date(),'MM-dd');
				
				var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getallbirthdaysbydate = data;			
				}).error(function(data, status, headers, config) {
					$scope.getallbirthdaysbydate = "Response Fail";
				});
				
				var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getallanniversariesbydate = data;			
				}).error(function(data, status, headers, config) {
					$scope.getallanniversariesbydate = "Response Fail";
				});
		    }
			
		});

app.controller(
				'loginCtrl',
				function($scope, $http, $window, $filter, $location) {
					var baseUrl = $location.protocol() + "://" + location.host
							+ u;

					$scope.checklogin = function() {
						var member_email = $scope.member_email;
						var member_password = $scope.member_password;

						if (member_email == undefined || member_email == "") {
							$window.alert("Please enter username");
							document.getElementById("username").focus();
							return;
						} else if (member_password == undefined || member_password == "") {
							$window.alert("Please enter password");
							document.getElementById("password").focus();
							return;
						} else {
							var link = baseUrl + 'login?member_email=' + member_email
									+ '&member_password=' + member_password;
							
							$http.post(link)
									.success(
											function(data, status, headers,
													config) {
												if (data == "") {
													$window
															.alert("User Name or Password Incorrect...Try Again bb");
												} else {
													$window.location.href = adminurl
															+ 'admin_home';
												}
											})
									.error(
											function(data, status, headers,
													config) {
												$window
														.alert("Some thing wrong...Try again ww");
											});
						}
					}
				});

			app.controller('eventCtrl',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location) 
			{
				$scope.currentPage = 0;
				$scope.pageSize = 20;
				$scope.search = '';
				
				$scope.getData = function()
				{
					return $filter('filter')($scope.data,$scope.search)
				}
				$scope.numberOfPages = function()
				{
					return Math.ceil($scope.events.length/ $scope.pageSize);
				}
				
				var baseUrl = $location.protocol() + "://" + location.host + u;
				
				$scope.todaydate = $filter('date')(new Date(),'yyyy-MM-dd');
				
				$scope.checkFrontLogin = function(id) 
				{						
					var username = $scope.username;
					var password = $scope.password;

					if (username == undefined || username == "") {
						$window.alert("Please enter username");
						document.getElementById("username").focus();
						return;
					} else if (password == undefined || password == "") {
						$window.alert("Please enter password");
						document.getElementById("password").focus();
						return;
					} else {
						var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password;
						$http.post(link).success(function(data, status, headers,config) 
						{
							if (data == "")
							{
								$window.alert("User Name or Password Incorrect...Try Again");
							}
							else
							{
								$window.location.href = url + 'event_detail?id='+id;				
							}
						}).error(function(data, status, headers,config)
						{
							$window.alert("Some thing wrong...Try again");
						});
					}
				}
				
				
				var link = baseUrl + 'MemberDetails';
					$http.get(link).success(function(data, status, headers, config)
							{
								$scope.memberdetails = data;
							}).error(function(data, status, headers, config)
							{
								$scope.memberdetails = "Response Fail";
							});
				var link = baseUrl + 'EventResponse';
				$http.get(link).success(function(data, status, headers, config) 
						{
							$scope.eventresponse = data;
						}).error(function(data, status, headers, config) 
						{
							$scope.eventresponse = "Response Fail";
						});
				var link = baseUrl + 'EventResponseCounts';
				$http.get(link).success(function(data, status, headers,config) 
						{
							$scope.eventresponsecounts = data;
						}).error(function(data, status, headers,config)
						{
							$scope.eventresponsecounts = "Response Fail";
						});
				var link = baseUrl + 'EventType';
				$http.get(link).success(function(data, status, headers, config) 
						{
							$scope.eventtypes = data;
						}).error(function(data, status, headers, config) 
						{
							$scope.eventtypes = "Response Fail";
						});
				var link = baseUrl + 'Events';
				$http.get(link).success(function(data, status, headers, config)
						{
							$scope.events = data;
						}).error(function(data, status, headers, config) 
						{
							$scope.events = "Response Fail";
						});
				
				$scope.redirecttoeventdetail = function(id)
				{
					$window.location.href = url+'event_detail?id='+id;				
				}
				
				$scope.getEventDetailById = function(id, memberid)
				{					
					var link = baseUrl + 'getEventDetailById?id='+id;			
					$http.get(link).success(function(data, status, headers, config) {
						$scope.geteventdetailbyid = data;
					}).error(function(data, status, headers, config) {
						$scope.geteventdetailbyid = "Response Fail";
					});
					
					var link = baseUrl+'getRelatedEventAgenda?eventid='+id;					
					$http.get(link).success(function(data, status, headers,config) {
						$scope.eventrelatedagenda = data;
					}).error(function(data, status, headers,config) {
						$scope.eventrelatedagenda = "Response Fail";
					});
					
					var link = baseUrl+'getRelatedEventImages?eventid='+id;
					$http.get(link).success(function(data, status, headers,config) {
						$scope.eventrelatedimages = data;
					}).error(function(data, status, headers,config) {
						$scope.eventrelatedimages = "Response Fail";
					});
					
					var link = baseUrl+'getRelatedEventUrl?eventid='+id;
					$http.get(link).success(function(data, status, headers,config) {
						$scope.eventrelatedurl = data;
					}).error(function(data, status, headers,config) {
						$scope.eventrelatedurl = "Response Fail";
					});
					
					var link = baseUrl+'getMemberByMemberId?memberid='+memberid;					
					$http.get(link).success(function(data, status, headers, config) {						
						$scope.getmember = data;					
					}).error(function(data, status, headers, config) {
						$scope.getmember = "Response Fail";						
					});
					
					var link = baseUrl + 'getMemberEventResponse?id='+id+'&memberid='+memberid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getmembereventresponse = data;
					}).error(function(data, status, headers, config) {
						$scope.getmembereventresponse = "Response Fail";
					});
				}
				
				var link = baseUrl+'Currency';					
				$http.get(link).success(function(data, status, headers, config) {						
					$scope.getcurrency = data;					
				}).error(function(data, status, headers, config) {
					$scope.getcurrency = "Response Fail";						
				});
				
				$scope.agendalist = [ {} ];
				$scope.eventimagelist = [ {} ];
				$scope.eventurllist = [ {} ];

				$scope.addAllBillingRow = function() {
					if($scope.agendaconclusion == undefined)
						$scope.agendaconclusion = "";
					if($scope.eventagendasequence == undefined){
						$window.alert("Please add sequence");
						document.getElementById("eventagendasequence").focus();
						return;
					} else if($scope.eventagenda == undefined){
						$window.alert("Please add agenda");
						document.getElementById("eventagenda").focus();
						return;
					} else {
						$scope.agendalist.push({'eventAgendaSequence' : $scope.eventagendasequence,'eventAgendaTitle' : $scope.eventagenda,'eventAgendaConclusion' : $scope.agendaconclusion});
						$scope.eventagendasequence = undefined;
						$scope.eventagenda = undefined;
						$scope.agendaconclusion = undefined;
					}					
				}
				
				$scope.removeAgendaRow = function(eventagenda) {
					var index = -1;
					var comArr = eval($scope.agendalist);
					for (var i = 0; i < comArr.length; i++) {
						if (comArr[i].eventAgendaTitle === eventagenda) {
							index = i;
							break;
						}
					}
					if (index === -1) {
						alert("Something gone wrong");
					}
					$scope.agendalist.splice(index, 1);
				};

				$scope.addUrlRow = function() {
					if($scope.eventurlsequence == undefined){
						$window.alert("Please enter sequence");
						document.getElementById("eventurlsequence").focus();
						return;
					} else if($scope.eventurltitle == undefined){
						$window.alert("Please enter URL title");
						document.getElementById("eventurltitle").focus();
						return;
					} else if($scope.eventurl == undefined){
						$window.alert("Please enter URL");
						document.getElementById("eventurl").focus();
						return;
					} else {
						$scope.eventurllist.push({'eventUrlSequence' : $scope.eventurlsequence,'eventUrlTitle' : $scope.eventurltitle,'eventUrl' : $scope.eventurl});
						$scope.eventurlsequence = undefined;
						$scope.eventurltitle = undefined;
						$scope.eventurl = undefined;
					}				
				}
				
				$scope.removeUrlRow = function(eventurltitle) {
					var index = -1;
					var comArr = eval($scope.eventurllist);
					for (var i = 0; i < comArr.length; i++) {
						if (comArr[i].eventUrlTitle === eventurltitle) {
							index = i;
							break;
						}
					}
					if (index === -1) {
						alert("Something gone wrong");
					}
					$scope.eventurllist.splice(index, 1);
				};

							var formData = new FormData();
							$scope.addImageRow = function() {
								$scope.eventimagelist
										.push({
											'eventImageTitle' : $scope.imagetitle,
											'eventImageDescription' : $scope.imagedescription
										});
								formData.append("eventimage",
										eventimage.files[0]);
								document.getElementById("imagetitle").value = "";
								document.getElementById("imagedescription").value = "";
							};

							$scope.removeImageRow = function(imagetitle) {
								var index = -1;
								var comArr = eval($scope.eventimagelist);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].eventImageTitle === imagetitle) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.eventimagelist.splice(index, 1);
							};
							$scope.spin = 0;
							$scope.nospin = 1;
							
							$scope.addevent = function() {
								
								$scope.eventdescription = CKEDITOR.instances.eventdescription.getData();
								
								if (document.getElementById("notifyviaemail").checked == true)
									$scope.notifyviaemail = 'Yes';
								if (document.getElementById("notifyviaemail").checked == false)
									$scope.notifyviaemail = 'No';
								if (document.getElementById("notifyviasms").checked == true)
									$scope.notifyviasms = 'Yes';
								if (document.getElementById("notifyviasms").checked == false)
									$scope.notifyviasms = 'No';

								var eventtype = $scope.eventtype;
								var eventtitle = $scope.eventtitle;
								var eventsubtitle = $scope.eventsubtitle;
								var eventdate = $filter('date')($scope.eventdate, 'yyyy-MM-dd');
								var eventtime = $scope.eventtime;
								var eventvenue = $scope.eventvenue;
								var eventmaplocation = $scope.eventmaplocation;
								var eventdescription = $scope.eventdescription;
								var notifyviaemail = $scope.notifyviaemail;
								var notifyviasms = $scope.notifyviasms;
								
								

								var createddate = $filter('date')(new Date(),
										'yyyy-MM-dd');
								var createdtime = $filter('date')(new Date(),
										'HH:mm:ss');

								if (eventtype == undefined || eventtype == "") {
									eventtype = 0;
								}
								if (eventsubtitle == undefined
										|| eventsubtitle == "") {
									eventsubtitle = "";
								}
								if (eventvenue == undefined || eventvenue == "") {
									eventvenue = "";
								}
								if (eventmaplocation == undefined
										|| eventmaplocation == "") {
									eventmaplocation = "";
								}
								if (eventdescription == undefined
										|| eventdescription == "") {
									eventdescription = "";
								}
								if(!$scope.rmbfbmember){
									$scope.rmbfbmember=0;
								}
								if(!$scope.rotarian){
									$scope.rotarian=0;
								}
								if(!$scope.others){
									$scope.others=0;
								}
								
								if ($scope.paid == undefined || $scope.paid == "") {
									$window.alert("Please select yes or no!");
									document.getElementById("paid").focus();
									return;
								} else if ($scope.registration == undefined || $scope.registration == "") {
									$window.alert("Please select Event Type!");
									document.getElementById("registration").focus();
									return;
								} else if (eventtitle == undefined || eventtitle == "") {
									$window.alert("Please enter event title!");
									document.getElementById("eventtitle")
											.focus();
									return;
								} else if (eventdate == undefined
										|| eventdate == "") {
									$window.alert("Please enter event date!");
									document.getElementById("eventdate")
											.focus();
									return;
								} else if (eventtime == undefined
										|| eventtime == "") {
									$window.alert("Please enter event time!");
									document.getElementById("eventtime")
											.focus();
									return;
								} else {
									
									eventdescription = encodeURIComponent(eventdescription);
									
									$scope.spin = 1;
									$scope.nospin = 0;
									var a = 1, b = 1, c = 1, d = 1;
									$scope.eventimagelist1 = [];
									$scope.eventimagelist2 = [];
									angular.forEach($scope.eventimagelist,function(item) {
										$scope.eventimagelist1.push(item.eventImageTitle);
										$scope.eventimagelist2.push(item.eventImageDescription);
									});
									var link = baseUrl + 'addEvent?eventtype='+ eventtype + '&eventtitle='+ eventtitle + '&eventsubtitle='+ eventsubtitle + '&eventdate='+ eventdate + '&eventtime='+ eventtime + '&eventvenue='+ eventvenue + '&eventmaplocation='+ eventmaplocation+ '&eventdescription='+ eventdescription+ '&createddate=' + createddate+ '&createdtime=' + createdtime+ '&notifyviaemail='+ notifyviaemail + '&notifyviasms='+ notifyviasms+'&registration='+$scope.registration+'&paid='+$scope.paid+'&rmbfbmember='+$scope.rmbfbmember+'&rotarian='+$scope.rotarian+'&others='+$scope.others;
									$http.post(link).success(function(data, status,headers, config) {
										$scope.addevent = data;										
										if ($scope.eventimagelist.length == 1 && $scope.eventrelatedagenda1.length == 1 && $scope.eventurllist.length == 1 && $scope.registrationlist.length == 1) {
											$scope.spin = 0;
											$scope.nospin = 1;
											$window.alert("Event Added Successfully");
											$window.location.href = adminurl+ 'manage_events';
											document.getElementById("loader").style.display = "none";
										}
										if ($scope.eventimagelist1.length > 1) {
											var link = baseUrl+ 'addEventImages?eventimagetitle='+ $scope.eventimagelist1+ '&eventimagedescription='+ $scope.eventimagelist2+ '&createddate='+ createddate+ '&createdtime='+ createdtime;
											$http({method : 'POST',url : link,headers : {'Content-Type' : undefined},data : formData,transformRequest : function(data,headersGetterFunction) {
													return data;
												}
											}).success(function(data,status) {
												$scope.addeventimages = data;
												a = a + 1;												
												if ($scope.eventimagelist.length == a && $scope.eventrelatedagenda1.length == 1 && $scope.eventurllist.length == 1 && $scope.registrationlist.length == 1) {
													$scope.spin = 0;
													$scope.nospin = 1;
													$window.alert("Event Added Successfully");
													$window.location.href = adminurl+ 'manage_events';
													document.getElementById("loader").style.display = "none";
												}
											}).error(function(data,status,headers,config) {
												$scope.addeventimages = "Response Fail";
											});
										}
										
										angular.forEach($scope.registrationlist,function(item) {
											if (item.registrationFor != null) {
												var link = baseUrl+'addRegistrationCharges?registrationfor='+item.registrationFor+'&currencyid='+item.currencyId+'&amount='+item.amount+'&createddate='+createddate+'&createdtime='+createdtime;
												$http.post(link).success(function(data,status,headers,config) {
													b = b + 1;
													$scope.addeventcharge = data;
													if (a == $scope.eventimagelist.length && b == $scope.eventrelatedagenda1.length && $scope.eventurllist.length == 1 && $scope.registrationlist.length == 1) {
														$scope.spin = 0;
														$scope.nospin = 1;
														$window.alert("Event Added Successfully");
														$window.location.href = adminurl+ 'manage_events';
														document.getElementById("loader").style.display = "none";
													}
												}).error(function(data,status,headers,config) {
													$scope.addeventcharge = "Response Fail";
												});
											}
										});
										
										
										
										angular.forEach($scope.eventrelatedagenda1,function(item) {
											if (item.eventAgendaTitle != null) {
												var link = baseUrl+'addEventAgenda?eventagendasequence='+item.eventAgendaSequence+'&eventagendatitle='+item.eventAgendaTitle+'&eventagendaconclusion='+item.eventAgendaConclusion+'&createddate='+createddate+'&createdtime='+createdtime;
												$http.post(link).success(function(data,status,headers,config) {
													b = b + 1;
													$scope.addeventagenda = data;
													if (a == $scope.eventimagelist.length && b == $scope.eventrelatedagenda1.length && $scope.eventurllist.length == 1 && $scope.registrationlist.length == 1) {
														$scope.spin = 0;
														$scope.nospin = 1;
														$window.alert("Event Added Successfully");
														$window.location.href = adminurl+ 'manage_events';
														document.getElementById("loader").style.display = "none";
													}
												}).error(function(data,status,headers,config) {
													$scope.addeventagenda = "Response Fail";
												});
											}
										});
										
										angular.forEach($scope.eventurllist,function(item) {
											if (item.eventUrlTitle != null) {
												var link = baseUrl+'addEventUrl?eventurlsequence='+item.eventUrlSequence+'&eventurltitle='+item.eventUrlTitle+'&url='+item.eventUrl+'&createddate='+createddate+'&createdtime='+createdtime;
												$http.post(link).success(function(data,status,headers,config) {
													c = c + 1;
													$scope.addeventurl = data;
													if (a == $scope.eventimagelist.length && b == $scope.eventrelatedagenda1.length && c == $scope.eventurllist.length && $scope.registrationlist.length == 1) {
														$scope.spin = 0;
														$scope.nospin = 1;
														$window.alert("Event Added Successfully");
														$window.location.href = adminurl+'manage_events';
														document.getElementById("loader").style.display = "none";
													}
												}).error(function(data,	status, headers, config) {
													$scope.addeventurl = "Response Fail";
												});
											}
										});
										/*if (a == $scope.eventimagelist.length && b == $scope.eventrelatedagenda1.length && c == $scope.eventurllist.length && $scope.registrationlist.length == 1) {*/
											$scope.spin = 0;
											$scope.nospin = 1;
											$window.alert("Event Added Successfully");
											$window.location.href = adminurl+'manage_events';
											document.getElementById("loader").style.display = "none";
										/*}*/
									}).error(function(data, status,headers, config) {
										$scope.addevent = "Response Fail";
									});
								}
							}

							$scope.sendNotificationToMembers = function() {
								var link = baseUrl
										+ 'sendNotificationToMembers';
								$http
										.post(link)
										.success(
												function(data, status, headers,
														config) {
													$scope.sendnotificationtomembers = data;
												})
										.error(
												function(data, status, headers,
														config) {
													$scope.sendnotificationtomembers = "Response Fail";
												});
							}
							
							$scope.geteventdetails = function(eventid) {
								for (i in $scope.events) {
									if ($scope.events[i].eventId == eventid) {
										$scope.eventid = $scope.events[i].eventId;
										$scope.eventtype = $scope.events[i].eventTypeId;
										$scope.eventtitle = $scope.events[i].eventTitle;
										$scope.eventsubtitle = $scope.events[i].eventSubtitle;
										$scope.eventdate = $scope.events[i].eventDate;
										$scope.eventtime = $scope.events[i].eventTime;
										$scope.eventvenue = $scope.events[i].eventVenue;
										$scope.eventmaplocation = $scope.events[i].eventMapLocation;
										/*$scope.eventdescription = $scope.events[i].eventDescription;*/
										CKEDITOR.instances.eventdescription.setData($scope.events[i].eventDescription);
										$scope.registrationedit = $scope.events[i].registration;
										$scope.paidedit = $scope.events[i].paid;
										$scope.rmbfbmemberedit = $scope.events[i].rmbfbMember;
										$scope.rotarianedit = $scope.events[i].rotarian;
										$scope.othersedit = $scope.events[i].others;
									}
								}

								var link = baseUrl
										+ 'getRelatedEventAgenda?eventid='
										+ eventid;
								$http
										.get(link)
										.success(
												function(data, status, headers,
														config) {
													$scope.eventrelatedagenda = data;
													for (i in $scope.eventrelatedagenda) {
														if ($scope.eventrelatedagenda[i].eventId == eventid) {
															$scope.eventagenda = $scope.eventrelatedagenda[i].eventAgendaTitle;
															$scope.agendaconclusion = $scope.eventrelatedagenda[i].eventAgendaConclusion;
														}
													}
												})
										.error(
												function(data, status, headers,
														config) {
													$scope.eventrelatedagenda = "Response Fail";
												});

								var link = baseUrl
										+ 'getRelatedEventImages?eventid='
										+ eventid;
								$http
										.get(link)
										.success(
												function(data, status, headers,
														config) {
													$scope.eventrelatedimages = data;
												})
										.error(
												function(data, status, headers,
														config) {
													$scope.eventrelatedimages = "Response Fail";
												});

								var link = baseUrl+'getRelatedEventUrl?eventid='+ eventid;
								$http.get(link).success(function(data, status, headers,config) {
									$scope.eventrelatedurl = data;
								}).error(function(data, status, headers,config) {
									$scope.eventrelatedurl = "Response Fail";
								});
								
								var link = baseUrl+'getEventChargesList?eventid='+ eventid;
								$http.get(link).success(function(data, status, headers,config) {
									$scope.registrationlist1 = data;
								}).error(function(data, status, headers,config) {
									$scope.registrationlist1 = "Response Fail";
								});
							}
							
							$scope.eventrelatedagenda1=[{}];
							$scope.addAgendaRow = function() {
								if($scope.agendaconclusion == undefined)
									$scope.agendaconclusion = "";
								if($scope.eventagendasequence == undefined){
									$window.alert("Please add sequence");
									document.getElementById("eventagendasequence").focus();
									return;
								} else if($scope.eventagenda == undefined){
									$window.alert("Please add agenda");
									document.getElementById("eventagenda").focus();
									return;
								} else {
									$scope.eventrelatedagenda1.push({'eventAgendaSequence' : $scope.eventagendasequence,'eventAgendaTitle' : $scope.eventagenda,'eventAgendaConclusion' : $scope.agendaconclusion});
									$scope.eventagendasequence = undefined;
									$scope.eventagenda = undefined;
									$scope.agendaconclusion = undefined;
								}
								/*if($scope.eventconclusion == undefined)
									$scope.eventconclusion = "";
								$scope.eventrelatedagenda
										.push({
											'eventAgendaSequence' : $scope.eventagendasequence,
											'eventAgendaTitle' : $scope.eventagenda,
											'eventAgendaConclusion' : $scope.eventconclusion
										});
								$scope.eventagendasequence = undefined;
								$scope.eventagenda = undefined;
								$scope.eventconclusion = undefined;*/
							}

							$scope.removeAgendaRow2 = function(eventagenda) {
								var index = -1;
								var comArr = eval($scope.eventrelatedagenda1);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].eventAgendaTitle === eventagenda) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.eventrelatedagenda1.splice(index, 1);
							};
							
							
							$scope.registrationlist=[{}];

							
							$scope.currencytype = 0;
							for(i in $scope.getcurrency){										
								if($scope.currencytype == $scope.getcurrency[i].currencyId){
									$scope.currencyTitle = $scope.getcurrency[i].currencyName;											
								}
							}
							$scope.amount = 0.000;
							$scope.registrationlist=[{'registrationFor' : "RMBFB Member",'currencyId' : $scope.currencytype,'currencyTitle' : $scope.currencyTitle,'amount' : $scope.amount},
								{'registrationFor' : "RMBF",'currencyId' : $scope.currencytype,'currencyTitle' : $scope.currencyTitle,'amount' : $scope.amount},
								{'registrationFor' : "R I Representative",'currencyId' : $scope.currencytype,'currencyTitle' : $scope.currencyTitle,'amount' : $scope.amount},
								{'registrationFor' : "Others",'currencyId' : $scope.currencytype,'currencyTitle' : $scope.currencyTitle,'amount' : $scope.amount}];
							$scope.currencytype="";
							$scope.amount = "";
							
							$scope.addRegistrationRow = function() {
								
								if(!$scope.currencytype){
									$scope.currencytype = 0;
								}
								if(!$scope.amount){
									$scope.amount = 0;
								}
								if(!$scope.registrationfor){
									$window.alert("Please select Member Type");
									document.getElementById("registrationfor").focus();
									return;
								} else {
									
									for(i in $scope.getcurrency){										
										if($scope.currencytype == $scope.getcurrency[i].currencyId){
											$scope.currencyTitle = $scope.getcurrency[i].currencyName;											
										}
									}
									
									for(i in $scope.registrationlist){
										if($scope.registrationfor == $scope.registrationlist[i].registrationFor){
											$scope.registrationlist[i].currencyId = $scope.currencytype;
											$scope.registrationlist[i].currencyTitle = $scope.currencyTitle;
											$scope.registrationlist[i].amount = $scope.amount;
										}
									}
									//$scope.registrationlist.push({'registrationFor' : $scope.registrationfor,'currencyId' : $scope.currencytype,'currencyTitle' : $scope.currencyTitle,'amount' : $scope.amount});
									$scope.registrationfor = "";
									$scope.currencytype = "";
									$scope.amount = "";
								}								
							}

							$scope.removeRegistrationRow = function(registrationFor) {
								/*var index = -1;
								var comArr = eval($scope.registrationlist);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].registrationFor === registrationFor) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.registrationlist.splice(index, 1);*/
								for(i in $scope.registrationlist){
									if(registrationFor == $scope.registrationlist[i].registrationFor){
										$scope.registrationlist[i].amount = 0;
									}
								}
							};
							
							
							

							$scope.addAgendaRow1 = function() {
								if($scope.agendaconclusion == undefined)
									$scope.agendaconclusion = "";
								if($scope.eventagendasequence == undefined){
									$window.alert("Please add sequence");
									document.getElementById("eventagendasequence").focus();
									return;
								} else if($scope.eventagenda == undefined){
									$window.alert("Please add agenda");
									document.getElementById("eventagenda").focus();
									return;
								} else {
									$scope.eventrelatedagenda.push({'eventAgendaSequence' : $scope.eventagendasequence,'eventAgendaTitle' : $scope.eventagenda,'eventAgendaConclusion' : $scope.agendaconclusion});
									$scope.eventagendasequence = undefined;
									$scope.eventagenda = undefined;
									$scope.agendaconclusion = undefined;
								}
								/*if($scope.eventconclusion == undefined)
									$scope.eventconclusion = "";
								$scope.eventrelatedagenda
										.push({
											'eventAgendaSequence' : $scope.eventagendasequence,
											'eventAgendaTitle' : $scope.eventagenda,
											'eventAgendaConclusion' : $scope.eventconclusion
										});
								$scope.eventagendasequence = undefined;
								$scope.eventagenda = undefined;
								$scope.eventconclusion = undefined;*/
							}

							$scope.removeAgendaRow1 = function(eventagenda) {
								var index = -1;
								var comArr = eval($scope.eventrelatedagenda);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].eventAgendaTitle === eventagenda) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.eventrelatedagenda.splice(index, 1);
							};

							$scope.addUrlRow1 = function() {
								$scope.eventrelatedurl
										.push({
											'eventUrlSequence' : $scope.eventurlsequence,
											'eventUrlTitle' : $scope.eventurltitle,
											'eventUrl' : $scope.eventurl
										});
								document.getElementById("eventurlsequence").value = "";
								document.getElementById("eventurltitle").value = "";
								document.getElementById("eventurl").value = "";
							}

							$scope.removeUrlRow1 = function(eventurltitle) {
								var index = -1;
								var comArr = eval($scope.eventrelatedurl);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].eventUrlTitle === eventurltitle) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.eventrelatedurl.splice(index, 1);
							};

							var formData = new FormData();
							$scope.addImageRow1 = function() {
								$scope.eventrelatedimages
										.push({
											'eventImageTitle' : $scope.imagetitle,
											'eventImageDescription' : $scope.imagedescription
										});
								formData.append("eventimage",
										eventimage.files[0]);
								document.getElementById("imagetitle").value = "";
								document.getElementById("imagedescription").value = "";
							};

							$scope.removeImageRow1 = function(imagetitle) {
								var index = -1;
								var comArr = eval($scope.eventrelatedimages);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].eventImageTitle === imagetitle) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.eventrelatedimages.splice(index, 1);
							};

							var formData = new FormData();
							$scope.eventrelatedimagesnew = [ {} ];
							$scope.addImageRowNew = function() {
								$scope.eventrelatedimagesnew
										.push({
											'eventImageTitle' : $scope.imagetitle,
											'eventImageDescription' : $scope.imagedescription
										});
								formData.append("eventimage",
										eventimage.files[0]);
							};

							$scope.removeImageRowNew = function(imagetitle) {
								var index = -1;
								var comArr = eval($scope.eventrelatedimagesnew);
								for (var i = 0; i < comArr.length; i++) {
									if (comArr[i].eventImageTitle === imagetitle) {
										index = i;
										break;
									}
								}
								if (index === -1) {
									alert("Something gone wrong");
								}
								$scope.eventrelatedimagesnew.splice(index, 1);
							};

							$scope.editevent = function(eventid) {
							$scope.eventrelatedimages1 = [];
							$scope.eventrelatedimages2 = [];

								angular
										.forEach(
												$scope.eventrelatedimagesnew,
												function(item) {
													$scope.eventrelatedimages1
															.push(item.eventImageTitle);
													$scope.eventrelatedimages2
															.push(item.eventImageDescription);
												});

								$scope.eventdescription = CKEDITOR.instances.eventdescription.getData();
								var eventtype = $scope.eventtype;
								var eventtitle = $scope.eventtitle;
								var eventsubtitle = $scope.eventsubtitle;
								var eventdate = $filter('date')(
										$scope.eventdate, 'yyyy-MM-dd');
								var eventtime = $scope.eventtime;
								var eventvenue = $scope.eventvenue;
								var eventmaplocation = $scope.eventmaplocation;
								var eventdescription = $scope.eventdescription;

								var createddate = $filter('date')(new Date(),
										'yyyy-MM-dd');
								var createdtime = $filter('date')(new Date(),
										'HH:mm:ss');

								if (eventsubtitle == undefined
										|| eventsubtitle == "") {
									eventsubtitle = "";
								}
								if (eventvenue == undefined || eventvenue == "") {
									eventvenue = "";
								}
								if (eventmaplocation == undefined
										|| eventmaplocation == "") {
									eventmaplocation = "";
								}
								if (eventdescription == undefined
										|| eventdescription == "") {
									eventdescription = "";
								}
								if(!$scope.rmbfbmemberedit){
									$scope.rmbfbmemberedit=0;
								}
								if(!$scope.rotarianedit){
									$scope.rotarianedit=0;
								}
								if(!$scope.othersedit){
									$scope.othersedit=0;
								}
								
								
								if ($scope.paidedit == undefined || $scope.paidedit == "") {
									$window.alert("Please select either yes or no!");
									document.getElementById("paidedit").focus();
									return;
								} else if ($scope.registrationedit == undefined || $scope.registrationedit == "") {
									$window.alert("Please select event type!");
									document.getElementById("registrationedit").focus();
									return;
								} else if (eventtitle == undefined || eventtitle == "") {
									$window.alert("Please enter event title!");
									document.getElementById("eventtitle")
											.focus();
									return;
								} else if (eventdate == undefined
										|| eventdate == "") {
									$window.alert("Please enter event date!");
									document.getElementById("eventdate")
											.focus();
									return;
								} else if (eventtime == undefined
										|| eventtime == "") {
									$window.alert("Please enter event time!");
									document.getElementById("eventtime")
											.focus();
									return;
								} else {
									eventdescription = encodeURIComponent(eventdescription);
									document.getElementById("loader").style.display = "block";

									var link = baseUrl
											+ 'deleteSelectedEventAgenda?eventid='
											+ eventid;
									$http['delete']
											(link)
											.success(
													function(data, status,
															headers, config) {
														$scope.deleteselectedeventagenda = data;
													})
											.error(
													function(data, status,
															headers, config) {
														$scope.deleteselectedeventagenda = "Response Fail";
													});

									var link = baseUrl
											+ 'deleteSelectedEventImages?eventid='
											+ eventid;
									$http['delete']
											(link)
											.success(
													function(data, status,
															headers, config) {
														$scope.deleteselectedeventimages = data;
													})
											.error(
													function(data, status,
															headers, config) {
														$scope.deleteselectedeventimages = "Response Fail";
													});

									var link = baseUrl
											+ 'deleteSelectedEventUrl?eventid='
											+ eventid;
									$http['delete']
											(link)
											.success(
													function(data, status,
															headers, config) {
														$scope.deleteselectedeventurl = data;
													})
											.error(
													function(data, status,
															headers, config) {
														$scope.deleteselectedeventurl = "Response Fail";
													});

									var link = baseUrl + 'editEvent?eventid='
											+ eventid + '&eventtype='
											+ eventtype + '&eventtitle='
											+ eventtitle + '&eventsubtitle='
											+ eventsubtitle + '&eventdate='
											+ eventdate + '&eventtime='
											+ eventtime + '&eventvenue='
											+ eventvenue + '&eventmaplocation='
											+ eventmaplocation
											+ '&eventdescription='
											+ eventdescription
											+ '&createddate=' + createddate+ '&createdtime=' + createdtime+'&registration='+$scope.registrationedit+'&paid='+$scope.paidedit+'&rmbfbmember='+$scope.rmbfbmemberedit+'&rotarian='+$scope.rotarianedit+'&others='+$scope.othersedit;
									$http
											.post(link)
											.success(
													function(data, status,
															headers, config) {
														$scope.editevent = data;

														var link = baseUrl
																+ 'editEventImages?eventid='
																+ eventid
																+ '&eventimagetitle='
																+ $scope.eventrelatedimages1
																+ '&eventimagedescription='
																+ $scope.eventrelatedimages2;
														$http(
																{
																	method : 'POST',
																	url : link,
																	headers : {
																		'Content-Type' : undefined
																	},
																	data : formData,
																	transformRequest : function(
																			data,
																			headersGetterFunction) {
																		return data;
																	}
																})
																.success(
																		function(
																				data,
																				status) {
																			$scope.editeventimages = data;

																			if ($scope.eventrelatedimages.length == 0
																					&& $scope.eventrelatedagenda.length == 0) {
																				$window
																						.alert("Event Updated Successfully");
																				$window.location.href = adminurl
																						+ 'manage_events';
																			}

																			var a = 0, b = 0, c = 0;
																			angular
																					.forEach(
																							$scope.eventrelatedimages,
																							function(
																									item) {
																								if (item.eventImageTitle != null) {
																									var link = baseUrl
																											+ 'editEventImagesNew?eventid='
																											+ eventid
																											+ '&eventimagetitle='
																											+ item.eventImageTitle
																											+ '&eventimagedescription='
																											+ item.eventImageDescription
																											+ '&image='
																											+ item.image
																											+ '&createddate='
																											+ createddate
																											+ '&createdtime='
																											+ createdtime;
																									$http
																											.post(
																													link)
																											.success(
																													function(
																															data,
																															status,
																															headers,
																															config) {
																														$scope.editeventimagesnew = data;

																														a = a + 1;
																														if (a == $scope.eventrelatedimages.length
																																&& b == $scope.eventrelatedagenda.length
																																&& b == $scope.eventrelatedurl.length) {
																															$window
																																	.alert("Event Updated Successfully");
																															$window.location.href = adminurl
																																	+ 'manage_events';
																														}
																													})
																											.error(
																													function(
																															data,
																															status,
																															headers,
																															config) {
																														$scope.editeventimagesnew = "Response Fail";
																													});
																								}
																							});

																			angular
																					.forEach(
																							$scope.eventrelatedagenda,
																							function(
																									item) {
																								if (item.eventAgendaTitle != null) {
																									var link = baseUrl
																											+ 'editEventAgenda?eventid='
																											+ eventid
																											+ '&eventagendasequence='
																											+ item.eventAgendaSequence
																											+ '&eventagendatitle='
																											+ item.eventAgendaTitle
																											+ '&eventagendaconclusion='
																											+ item.eventAgendaConclusion
																											+ '&createddate='
																											+ createddate
																											+ '&createdtime='
																											+ createdtime;
																									$http
																											.post(
																													link)
																											.success(
																													function(
																															data,
																															status,
																															headers,
																															config) {
																														$scope.editeventagenda = data;

																														b = b + 1;
																														if (a == $scope.eventrelatedimages.length
																																&& b == $scope.eventrelatedagenda.length
																																&& c == $scope.eventrelatedurl.length) {
																															$window
																																	.alert("Event Updated Successfully");
																															$window.location.href = adminurl
																																	+ 'manage_events';
																														}
																													})
																											.error(
																													function(
																															data,
																															status,
																															headers,
																															config) {
																														$scope.editeventagenda = "Response Fail";
																													});
																								}
																							});

																			angular
																					.forEach(
																							$scope.eventrelatedurl,
																							function(
																									item) {
																								if (item.eventUrlTitle != null) {
																									var link = baseUrl
																											+ 'editEventUrl?eventid='
																											+ eventid
																											+ '&eventurlsequence='
																											+ item.eventUrlSequence
																											+ '&eventurltitle='
																											+ item.eventUrlTitle
																											+ '&url='
																											+ item.eventUrl
																											+ '&createddate='
																											+ createddate
																											+ '&createdtime='
																											+ createdtime;
																									$http
																											.post(
																													link)
																											.success(
																													function(
																															data,
																															status,
																															headers,
																															config) {
																														$scope.editeventurl = data;

																														c = c + 1;
																														if (a == $scope.eventrelatedimages.length
																																&& b == $scope.eventrelatedagenda.length
																																&& c == $scope.eventrelatedurl.length) {
																															$window
																																	.alert("Event Updated Successfully");
																															$window.location.href = adminurl
																																	+ 'manage_events';
																														}
																													})
																											.error(
																													function(
																															data,
																															status,
																															headers,
																															config) {
																														$scope.editeventurl = "Response Fail";
																													});
																								}
																							});

																			// $window.alert("Event
																			// Updated
																			// Successfully");
																			// $window.location.href
																			// =
																			// adminurl+'manage_events';

																		})
														error(function(data,
																status,
																headers, config) {
															$scope.editeventimages = "Response Fail";
														});
														// $window.alert("Event
														// Updated
														// Successfully");
														// $window.location.href
														// =
														// adminurl+'manage_events';

													})
											.error(
													function(data, status,
															headers, config) {
														$scope.editevent = "Response Fail";
													});
								}
							}

							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = false;
								} else {
									$scope.selectedAll = true;
								}
								angular.forEach($scope.events, function(item) {
									item.selected = $scope.selectedAll;
								});

							}
							
							
							$scope.addRegistrationRowEdit = function() {
								
								if(!$scope.currencytype){
									$scope.currencytype = 0;
								}
								if(!$scope.amount){
									$scope.amount = 0;
								}
								if(!$scope.registrationfor){
									$window.alert("Please select Member Type");
									document.getElementById("registrationfor").focus();
									return;
								} else {
									
									/*var link = baseUrl+'editRegistrationCharges?id='+$scope.eventid+'&registrationfor='+$scope.registrationfor+'&currencyid='+$scope.currencytype+'&amount='+$scope.amount;
									
									$http.post(link).success(function(data, status, headers, config) {
										$scope.addregcharges = data;
										var link = baseUrl+'getEventChargesList?eventid='+$scope.eventid;
										$http.get(link).success(function(data, status, headers,config) {
											$scope.registrationlist1 = data;
										}).error(function(data, status, headers,config) {
											$scope.registrationlist1 = "Response Fail";
										});
									}).error(function(data, status, headers, config) {
										$scope.addregcharges = "Response Fail";
									});*/
									for (i in $scope.registrationlist1)
									{
										if($scope.registrationlist1[i].registrationFor == $scope.registrationfor){
											var link = baseUrl+'updateRegistrationCharges?id='+$scope.registrationlist1[i].eventChargeId+'&event_id='+$scope.eventid+'&registrationfor='+$scope.registrationfor+'&currencyid='+$scope.currencytype+'&amount='+$scope.amount;
											
											$http.post(link).success(function(data, status, headers, config) {
												$scope.addregcharges = data;
												var link = baseUrl+'getEventChargesList?eventid='+$scope.eventid;
												$http.get(link).success(function(data, status, headers,config) {
													$scope.registrationlist1 = data;
												}).error(function(data, status, headers,config) {
													$scope.registrationlist1 = "Response Fail";
												});
											}).error(function(data, status, headers, config) {
												$scope.addregcharges = "Response Fail";
											});
										}
									}
								}								
							}
							
							$scope.deleteRegistrationRow = function(id){
								for (i in $scope.registrationlist1)
									{
										if($scope.registrationlist1[i].eventChargeId == id){
											var link = baseUrl+'updateRegistrationCharges?id='+id+'&event_id='+$scope.registrationlist1[i].eventId+'&registrationfor='+$scope.registrationlist1[i].registrationFor+'&currencyid='+$scope.registrationlist1[i].currencyId+'&amount='+0;
											
											$http.post(link).success(function(data, status, headers, config) {
												$scope.addregcharges = data;
												var link = baseUrl+'getEventChargesList?eventid='+$scope.eventid;
												$http.get(link).success(function(data, status, headers,config) {
													$scope.registrationlist1 = data;
												}).error(function(data, status, headers,config) {
													$scope.registrationlist1 = "Response Fail";
												});
											}).error(function(data, status, headers, config) {
												$scope.addregcharges = "Response Fail";
											});
										}
									}
								/*var link = baseUrl+ 'deleteEventCharge?id='+ id;
								$http['delete'](link).success(function(data,status,headers,config) {
									$scope.deleteeventcharges = data;
									var link = baseUrl+'getEventChargesList?eventid='+$scope.eventid;
									$http.get(link).success(function(data, status, headers,config) {
										$scope.registrationlist1 = data;
									}).error(function(data, status, headers,config) {
										$scope.registrationlist1 = "Response Fail";
									});
								}).error(function(data,status,headers,config) {
									$scope.deleteeventcharges = "Response Fail";
								});*/
							}

							$scope.deleteevent = function() {
								deleteEvent = $window
										.confirm('Are you sure you want to delete this Event?');
								if (deleteEvent) {
									angular
											.forEach(
													$scope.events,
													function(item) {
														if (item.selected) {
															var link = baseUrl+ 'deleteEvent?eventid='+ item.eventId;
															$http['delete'](link).success(function(data,status,headers,config) {
																$scope.deleteevents = data;
															}).error(function(data,status,headers,config) {
																$scope.deleteevents = "Response Fail";
															});
														}

													});
									$window.location.href = adminurl
											+ 'manage_events';
								}
							}
							
							$scope.AddCommingResponse = function(id, memberid) {
								var joinself = $scope.joinself;
								var joinspouse = $scope.joinspouse;
								var joinannet = $scope.joinannet;
								var noofannetjoin = $scope.noofannetjoin;
								var comment = $scope.comment;								
																
								if(joinself == true) {
									joinself = "Y";
								} else {
									joinself = "N";
								}
								if(joinspouse == true) {
									joinspouse = "Y";
								} else {
									joinspouse = "N";
								}
								if(joinannet == true) {
									joinannet = "Y";
								} else {
									joinannet = "N";
								}
								if(noofannetjoin == null || noofannetjoin == undefined){
									noofannetjoin = 0;
								}
								if(comment == undefined || comment == null){
									comment = "";									
								}
								if(joinself == "N" && joinspouse == "N" && joinannet == "N") {
									$window.alert("Please tick atleast one applicable box");									
									return;
								} else {
									var link = baseUrl+'AddCommingResponse?id='+id+'&memberid='+memberid+'&joinself='+joinself+'&joinspouse='+joinspouse+'&joinannet='+joinannet+'&noofannetjoin='+noofannetjoin+'&comment='+comment;
									
									$http.post(link).success(function(data, status, headers, config) {
										$scope.addcommingresponse = data;
										$window.alert("Your response submitted successfully");
										$window.location.href = url + 'event_detail?id='+id;
									}).error(function(data, status, headers, config) {
										$scope.addcommingresponse = "Response Fail";
									});
								}
									
							}
							
							$scope.AddNotComingResponse = function(id, memberid) {
								var notcomingreason = $scope.notcomingreason;																					
								
								if(notcomingreason == null || notcomingreason == undefined){
									$window.alert("Please enter your reason");
									document.getElementById("not_coming_reason").focus();
									return;
								} else {
									var link = baseUrl+'AddNotComingResponse?id='+id+'&memberid='+memberid+'&notcomingreason='+notcomingreason;
									
									$http.post(link).success(function(data, status, headers, config) {
										$scope.addnotcommingresponse = data;
										$window.alert("Your response submitted successfully");
										$window.location.href = url + 'event_detail?id='+id;
									}).error(function(data, status, headers, config) {
										$scope.addnotcommingresponse = "Response Fail";
									});
								}
									
							}

						} ]);

			app.controller('eventResponseCtrl',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location) {
							$scope.currentPage = 0;
							$scope.pageSize = 20;
							$scope.search = '';

							// $window.alert($scope.notifyviaemail+""+$scope.notifyviasms);

							$scope.getData = function() {
								return $filter('filter')($scope.data,
										$scope.search)
							}

							$scope.numberOfPages = function() {
								return Math
										.ceil($scope.eventresponsecounts.length
												/ $scope.pageSize);
							}

							var baseUrl = $location.protocol() + "://" + location.host + u;

							var link = baseUrl + 'MemberDetails';
							$http.get(link).success(
									function(data, status, headers, config) {
										$scope.memberdetails = data;
									}).error(
									function(data, status, headers, config) {
										$scope.memberdetails = "Response Fail";
									});

							var link = baseUrl + 'EventResponse';
							$http.get(link).success(
									function(data, status, headers, config) {
										$scope.eventresponse = data;
									}).error(
									function(data, status, headers, config) {
										$scope.eventresponse = "Response Fail";
									});

							var link = baseUrl + 'EventResponseCounts';
							$http.get(link).success(function(data, status, headers,config) {
								$scope.eventresponsecounts = data;
							}).error(function(data, status, headers, config) {
								$scope.eventresponsecounts = "Response Fail";
							});

							var link = baseUrl + 'EventType';
							$http.get(link).success(
									function(data, status, headers, config) {
										$scope.eventtypes = data;
									}).error(
									function(data, status, headers, config) {
										$scope.eventtypes = "Response Fail";
									});

							var link = baseUrl + 'Events';
							$http.get(link).success(
									function(data, status, headers, config) {
										$scope.events = data;
									}).error(
									function(data, status, headers, config) {
										$scope.events = "Response Fail";
									});

							$scope.getEventResponceDetail = function(eventid) {
								for (i in $scope.events) {
									if ($scope.events[i].eventId == eventid) {
										$scope.eventtitle = $scope.events[i].eventTitle;
									}
								}

								$scope.comingmembers = [ {} ];
								$scope.notcomingmembers = [ {} ];
								for (i in $scope.eventresponse) {
									if ($scope.eventresponse[i].eventId == eventid && $scope.eventresponse[i].coming == 'Y') {
										$scope.memberid = $scope.eventresponse[i].memeberId;
										$scope.firstname = $scope.eventresponse[i].firstName;
										$scope.middlename = $scope.eventresponse[i].middleName;
										$scope.lastname = $scope.eventresponse[i].lastName;
										$scope.joinself = $scope.eventresponse[i].joinSelf;
										$scope.joinspouse = $scope.eventresponse[i].joinSpouse;
										$scope.joinannet = $scope.eventresponse[i].joinAnnet;
										$scope.noofannetsjoin = $scope.eventresponse[i].noOfAnnetsJoin;
										$scope.comment = $scope.eventresponse[i].comment;

										$scope.comingmembers.push({'memberid' : $scope.memberid, 'firstname' : $scope.firstname, 'middlename' : $scope.middlename, 'lastname' : $scope.lastname, 'joinself' : $scope.joinself, 'joinspouse' : $scope.joinspouse,'joinannet' : $scope.joinannet, 'noofannetsjoin' : $scope.noofannetsjoin, 'comment' : $scope.comment});
									}

									if ($scope.eventresponse[i].eventId == eventid && $scope.eventresponse[i].notcoming == 'Y') {
										$scope.memberid = $scope.eventresponse[i].memeberId;
										$scope.firstname = $scope.eventresponse[i].firstName;
										$scope.middlename = $scope.eventresponse[i].middleName;
										$scope.lastname = $scope.eventresponse[i].lastName;
										$scope.notcomingreason = $scope.eventresponse[i].notComingReason;

										$scope.notcomingmembers.push({ 'memberid' : $scope.memberid, 'firstname' : $scope.firstname, 'middlename' : $scope.middlename, 'lastname' : $scope.lastname, 'notcomingreason' : $scope.notcomingreason});
									}
								}
							}
						} ]);

// Controller for Manage News - Add News

	app.controller('newsCtrl',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location)
	{
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';	

		$scope.getData = function() {
			return $filter('filter')($scope.data,$scope.search)
		}

		$scope.numberOfPages = function() {
			return Math.ceil($scope.news.length/$scope.pageSize);
		}
		
		var baseUrl = $location.protocol() + "://"+location.host + u;
		
		var link = baseUrl + 'BeneficiaryType';
		$http.get(link).success(function(data,status,headers,config){
			$scope.beneficiarytype = data;
		}).error(function(data,status,headers,config){
			$scope.beneficiarytype = "Responce Fail";
		});
		
		$scope.getBirthdaysAndAnniversariesByDate = function ()
	    {
			var currentdate = $filter('date')(new Date(),'MM-dd');
			
			var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = "Response Fail";
			});
			
			var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = "Response Fail";
			});
	    }
		
		var link = baseUrl + 'News';
		$http.get(link).success(function(data, status, headers, config) { 
			$scope.news = data;
		}).error(function(data, status, headers, config) {
			$scope.news = "Response Fail";
		});
		
		var link = baseUrl + 'RotaryYear';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.rotaryyear = data;
		}).error(function(data,status,headers,config) {
			$scope.rotaryyear = "Responce Fail";
		});
		
		var link = baseUrl + 'ServiceProject';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.serviceproject = data;
		}).error(function(data,status,headers,config) {
			$scope.serviceproject = "Responce Fail";
		});
		
		$scope.getCurrentDefaultYear = function ()
	    {
			var link = baseUrl + 'RotaryYear';
			$http.get(link).success(
				function(data,status,headers,config)
				{
					$scope.rotaryyear = data;
					
					for (i in $scope.rotaryyear) 
					{
						if ($scope.rotaryyear[i].defaultYear == "y") 
						{						
							$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;					
						}				
					} 
					
					var link = baseUrl + 'getNewsDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;					
					$http.get(link).success(
							function(data, status, headers, config) {
								$scope.getcurrentyearnews = data;
							}).error(
							function(data, status, headers, config) {
								$scope.getcurrentyearnews = "Response Fail";
							});
					
				})
				.error(function(data,status,headers,config)
				{
					$scope.rotaryyear = "Responce Fail";
				});
			 
	    }
		
		$scope.getNewsDetailByRotaryYear = function ()
	    {				
			var rotaryyearid = $scope.rotaryyearid; 
			var link = baseUrl + 'getNewsDetailByRotaryYear?rotaryyearid='+rotaryyearid;						
			$http.get(link).success(
				function(data, status, headers, config) {
					$scope.getcurrentyearnews = data;
				}).error(
				function(data, status, headers, config) {
					$scope.getcurrentyearnews = "Response Fail";
				});			 
	    }	
		
		$scope.temp1 = 1;
		$scope.temp2 = 0;
		
		$scope.addNews = function() 
		{
			var valuex = document.getElementById("valuex").value;
			var valuey = document.getElementById("valuey").value;
			var valuew = document.getElementById("valuew").value;
			var valueh = document.getElementById("valueh").value;			
			
			var rotaryyear = $scope.rotaryyearid;
			var newstitle = $scope.newstitle;
			var project = $scope.project;
			var newssubtitle = $scope.newssubtitle;
			var beneficiarytypeid = $scope.beneficiarytypeid;
			var beneficiaryno = $scope.beneficiaryno;
			var newsdate = $filter('date')($scope.newsdate,'yyyy-MM-dd');
			var newsimagetitle = $scope.newsimagetitle;
			var newsdescription = $scope.newsdescription;
			
			if (project == undefined || project == "") 
			{
				project = 0;
			}
			if (newssubtitle == undefined || newssubtitle == "") 
			{
				newssubtitle = "";
			}
			if (beneficiarytypeid == undefined || beneficiarytypeid == "") 
			{
				beneficiarytypeid = 0;
			}
			if (beneficiaryno == undefined) 
			{
				beneficiaryno = "";
			}
			if (newsdate == undefined || newsdate == "") 
			{
				newsdate = "";
			}
			if (newsimagetitle == undefined || newsimagetitle == "") 
			{
				newsimagetitle = "";
			}
			// validation
			if (rotaryyear == undefined || rotaryyear == "") 
			{
				$window.alert("Please select rotary year!");
				document.getElementById("rotaryyearid").focus();
				return;			
			} else if (newstitle == undefined || newstitle == "") 
			{
				$window.alert("Please enter News Title!");
				document.getElementById("newstitle").focus();
				return;			
			} else if (document.getElementById("imgInp").value == undefined || document.getElementById("imgInp").value == "") 
			{
				$window.alert("Please choose an image for news!");
				document.getElementById("imgInp").focus();
				return;			
			} else if (newsdescription == undefined || newsdescription == "") {
				$window.alert("Please enter News Description!");
				document.getElementById("newsdescription").focus();
				return;
			} else if (valuew < 832 || valueh < 416) {
				$window.alert("You selected too small area for image please select appropriate area or change image");
				return;
			} else {
				
				$scope.temp1 = 0;
				$scope.temp2 = 1;
				
				var link = baseUrl+'addNews?newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&beneficiarytypeid='+beneficiarytypeid+'&beneficiaryno='+beneficiaryno+'&newsdate='+newsdate+'&newsimagetitle='+newsimagetitle+'&newsdescription='+newsdescription+'&rotaryyearid='+rotaryyear+'&project='+project+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;				
				var formData=new FormData();			
				formData.append("file",imgInp.files[0]);
				$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
				     })
				     .success(
						function(data, status, headers, config)	{
							
							$scope.addnews = data;
							$scope.temp1 = 1;
							$scope.temp2 = 0;
							$window.alert("News Added Successfully...");
							$window.location.href = adminurl+'manage_news';
						}).
						error(function(data, status, headers, config)
						{
							$scope.addnews = "Response Fail";
						});				
			}
		}
		
		$scope.getNewsDetail = function(newsid) 
		{			
			for (i in $scope.news) 
			{
				if ($scope.news[i].newsId == newsid) 
				{
					$scope.newsid = $scope.news[i].newsId;
					$scope.newstitle = $scope.news[i].newsTitle;
					$scope.newssubtitle = $scope.news[i].newsSubtitle;
					$scope.beneficiarytypeid = $scope.news[i].beneficiaryTypeId;
					$scope.beneficiaryno = $scope.news[i].beneficiaryNo;
					$scope.newsdate = $scope.news[i].newsDate;
					$scope.image = $scope.news[i].image;
					$scope.imagetitle = $scope.news[i].imageTitle;
					$scope.newsdescription = $scope.news[i].newsDesc;
					$scope.rotaryyearid = $scope.news[i].rotaryYearId;
					$scope.project = $scope.news[i].serviceProjectId;
				}				
			}
		}
		
		$scope.editNews = function(id) 
		{
			var valuex = document.getElementById("valuex").value;
			var valuey = document.getElementById("valuey").value;
			var valuew = document.getElementById("valuew").value;
			var valueh = document.getElementById("valueh").value;		
			
			var rotaryyear = $scope.rotaryyearid;
			var project = $scope.project;
			var newstitle = $scope.newstitle;
			var beneficiarytypeid = $scope.beneficiarytypeid;
			var beneficiaryno = $scope.beneficiaryno;
			var newssubtitle = $scope.newssubtitle;
			var newsdate = $filter('date')($scope.newsdate,'yyyy-MM-dd');
			var file = document.getElementById("imgInp").value;
			var newsimagetitle = $scope.imagetitle;
			var newsdescription = $scope.newsdescription;			
			
			if (project == undefined || project == "") 
			{
				project = 0;
			}
			if (newssubtitle == undefined || newssubtitle == "") 
			{
				newssubtitle = "";
			}
			if (beneficiarytypeid == undefined || beneficiarytypeid == "") 
			{
				beneficiarytypeid = 0;
			}
			if (beneficiaryno == undefined) 
			{
				beneficiaryno = "";
			}
			if (newsdate == undefined || newsdate == "") 
			{
				newsdate = "";
			}
			if (newsimagetitle == undefined || newsimagetitle == "") 
			{
				newsimagetitle = "";
			}
			// validation
			if (rotaryyear == undefined || rotaryyear == "") 
			{
				$window.alert("Please select rotary year!");
				document.getElementById("rotaryyearid").focus();
				return;			
			} else if (newstitle == undefined || newstitle == "") 
			{
				$window.alert("Please enter News Title!");
				document.getElementById("newstitle").focus();
				return;			
			} else if (newsdescription == undefined || newsdescription == "") {
				$window.alert("Please enter News Description!");
				document.getElementById("newsdescription").focus();
				return;
			} else if (valuew < 832 || valueh < 416) {
				$window.alert("You selected too small area for image please select appropriate area or change image");
				return;
			} else {
				
				$scope.temp1 = 0;
				$scope.temp2 = 1;
				
				if(file == undefined || file == "")
				{
					var link = baseUrl+'editNewsWithoutImage?id='+id+'&newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&beneficiarytypeid='+beneficiarytypeid+'&beneficiaryno='+beneficiaryno+'&newsdate='+newsdate+'&newsimagetitle='+newsimagetitle+'&newsdescription='+newsdescription+'&rotaryyearid='+rotaryyear+'&projectid='+project;					
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editnews = data;
								$window.alert("News updated successfuly");
								$window.location.href = adminurl + 'manage_news';
							}).
							error(function(data, status, headers, config)
							{
								$scope.editnews = "Response Fail";
							});
				}
				else
				{
					var link1 = baseUrl+'editNews?id='+id+'&newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&beneficiarytypeid='+beneficiarytypeid+'&beneficiaryno='+beneficiaryno+'&newsdate='+newsdate+'&newsimagetitle='+newsimagetitle+'&newsdescription='+newsdescription+'&rotaryyearid='+rotaryyear+'&projectid='+project+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
					var formData1=new FormData();
					formData1.append("file",imgInp.files[0]);
					$http({
					        method: 'POST',
					        url: link1,
					        headers: {'Content-Type': undefined},
					        data: formData1,
					        transformRequest: function(data, headersGetterFunction)
					        {
					        	return data;
					        }
					     })
					     .success(
							function(data, status, headers, config)
							{
								$scope.editnews1 = data;
								$scope.temp1 = 1;
								$scope.temp2 = 0;
								$window.alert("News Updated Successfully...");
								$window.location.href = adminurl+'manage_news';
							}).
							error(function(data, status, headers, config)
							{
								$scope.editnews1 = "Response Fail";
							});	
				}
				
							
			}			
		}
		
		$scope.checkAll = function()
		{
			if ($scope.selectedAll)
			{
				$scope.selectedAll = false;
			}
			else
			{
	            $scope.selectedAll = true;
	        }
			angular.forEach($scope.news, function (item)
			{
				item.selected = $scope.selectedAll;
			});
		}
		
		$scope.deletenews = function()
		{
			deleteNews = $window.confirm('Are you sure you want to delete record?');
			if(deleteNews)
			{			
			    angular.forEach($scope.news,
			    		function(item)
			    		{		    			
			    			if (item.selected)
			    				{
			    					var link = baseUrl+'deleteNews?id='+item.newsId;
				    				$http['delete'](link).success(
				    						function(data, status, headers, config)
				    						{
				    							$scope.newsdelete = data;
				    						}).
				    						error(function(data, status, headers, config)
				    						{
				    							$scope.newsdelete = "Response Fail";
				    						});
			    				}
			    				
			    		});
				$window.location.href = adminurl+'manage_news';
			}
		}
		
		$scope.getNewsDetailById = function(id,projectid)
		{		
			var link = baseUrl + 'getNewsDetailById?id='+id+'&projectid='+projectid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getnewsdetailbyid = data;
			}).error(function(data, status, headers, config) {
				$scope.getnewsdetailbyid = "Response Fail";
			});
		}	
} ]);

	app.controller("rotaryYearCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		$scope.currentPage = 0;
	    $scope.pageSize = 5;
	    $scope.search = '';
	    
	    $scope.getData = function ()
	    {
		     // needed for the pagination calc
	    	return $filter('filter')($scope.data, $scope.search);
	    }
	    
	    $scope.numberOfPages=function()
	    {
	    	return Math.ceil($scope.rotaryyear.length/$scope.pageSize);
	    }
		
		var link = baseUrl + 'RotaryYear';
		$http.get(link).success(
			function(data,status,headers,config)
			{
				$scope.rotaryyear = data;					
			})
			.error(function(data,status,headers,config)
			{
				$scope.rotaryyear = "Responce Fail";
			});
		
		$scope.changeDefaultYear = function (id)
	    {
			var link = baseUrl+'changeDefaultYear?id='+id+'&defaultyear='+"y";				
			$http.post(link).success(
				function(data, status, headers, config)
				{
					$scope.defaultyear = data;
					//$window.alert("Data added successfuly");
					$window.location.href = adminurl + 'rotary_year';
				}).
				error(function(data, status, headers, config)
				{
					$scope.defaultyear = "Response Fail";
				});  
	    }
		
		$scope.addRotaryYear = function()
		{
			var yeartitle = $scope.addyeartitle;
			var yearstartdate = $scope.addstartdate;
			var yearenddate = $scope.addenddate;	
			
			if(yeartitle == "" || yeartitle == undefined)
			{
				$window.alert("Please enter year title");
				document.getElementById("yeartitle").focus();
				return;
			}
			else if(yearstartdate == "" || yearstartdate == undefined)
			{
				$window.alert("Please select year start date");
				document.getElementById("startdate").focus();
				return;
			}
			else if(yearenddate == "" || yearenddate == undefined)
			{
				$window.alert("Please select year end date");
				document.getElementById("yearenddate").focus();
				return;
			}
			else
			{
				var link = baseUrl+'addRotaryYear?yeartitle='+yeartitle+'&yearstartdate='+yearstartdate+'&yearenddate='+yearenddate;				
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addrotaryyear = data;
						$window.alert("Data added successfuly");
						$window.location.href = adminurl + 'rotary_year';
					}).
					error(function(data, status, headers, config)
					{
						$scope.addrotaryyear = "Response Fail";
					});
			}
		}
		
		$scope.getRotaryYearDetail = function(rotaryyearid) 
		{			
			for (i in $scope.rotaryyear) 
			{
				if ($scope.rotaryyear[i].rotaryYearId == rotaryyearid) 
				{
					$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;
					$scope.yeartitle = $scope.rotaryyear[i].rotaryYearTitle;
					$scope.startdate = $scope.rotaryyear[i].yearStartDate;
					$scope.enddate = $scope.rotaryyear[i].yearEndDate;	
					$scope.defaultyear = $scope.rotaryyear[i].defaultYear;					
				}				
			}
		}
		
		$scope.editRotaryYear = function(id)
		{
			var yeartitle = $scope.yeartitle;
			var yearstartdate = $scope.startdate;
			var yearenddate = $scope.enddate;
			
			if(yeartitle == "" || yeartitle == undefined)
			{
				$window.alert("Please enter year title");
				document.getElementById("yeartitle").focus();
				return;
			}			
			else if(yearstartdate == "" || yearstartdate == undefined)
			{
				$window.alert("Please select year start date");
				document.getElementById("startdate").focus();
				return;
			}
			else if(yearenddate == "" || yearenddate == undefined)
			{
				$window.alert("Please select year end date");
				document.getElementById("yearenddate").focus();
				return;
			}
			else
			{
				var link = baseUrl+'editRotaryYear?id='+id+'&yeartitle='+yeartitle+'&yearstartdate='+yearstartdate+'&yearenddate='+yearenddate;				
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addrotaryyear = data;
						$window.alert("Data updated successfuly");
						$window.location.href = adminurl + 'rotary_year';
					}).
					error(function(data, status, headers, config)
					{
						$scope.addrotaryyear = "Response Fail";
					});
			}
		}
		
		$scope.checkAll = function()
		{
			if ($scope.selectedAll)
			{
				$scope.selectedAll = false;
			}
			else
			{
	            $scope.selectedAll = true;
	        }
			angular.forEach($scope.rotaryyear, function (item)
			{
				item.selected = $scope.selectedAll;
			});
		}
		
		$scope.deleteRotaryYear = function()
		{
			deleterotaryYear = $window.confirm('Are you sure you want to delete record?');
			if(deleterotaryYear)
			{			
			    angular.forEach($scope.rotaryyear,
			    		function(item)
			    		{		    			
			    			if (item.selected)
			    				{
			    					var link = baseUrl+'deleteRotaryYear?id='+item.rotaryYearId;
				    				$http['delete'](link).success(
				    						function(data, status, headers, config)
				    						{
				    							$scope.deleterotaryyear = data;
				    						}).
				    						error(function(data, status, headers, config)
				    						{
				    							$scope.deleterotaryyear = "Response Fail";
				    						});
			    				}
			    				
			    		});
				$window.location.href = adminurl+'rotary_year';
			}
		}
		
		

					
	});
	
	app.controller("serviceProjectCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		$scope.currentPage = 0;
	    $scope.pageSize = 25;
	    $scope.search = '';
			    
	    $scope.getData = function ()
	    {
		     // needed for the pagination calc
	    	return $filter('filter')($scope.data, $scope.search);
	    }
			    
	    $scope.numberOfPages=function()
	    {
	    	return Math.ceil($scope.serviceproject.length/$scope.pageSize);
	    }	    
				
		var link = baseUrl + 'ServiceProject';
		$http.get(link).success(
			function(data,status,headers,config)
			{
				$scope.serviceproject = data;			
			})
			.error(function(data,status,headers,config)
			{
				$scope.serviceproject = "Responce Fail";
			});
		
		var link = baseUrl + 'ServiceProjectWithImage';
		$http.get(link).success(
			function(data,status,headers,config)
			{
				$scope.serviceprojectwithimage = data;			
			})
			.error(function(data,status,headers,config)
			{
				$scope.serviceprojectwithimage = "Responce Fail";
			});
		
		var link = baseUrl + 'getAllServiceProjectImages';
		$http.get(link).success(
			function(data,status,headers,config)
			{
				$scope.getallserviceprojectimages = data;			
			})
			.error(function(data,status,headers,config)
			{
				$scope.getallserviceprojectimages = "Responce Fail";
			});
		
		
		
		$scope.redirecttoserviceprojectdetail = function(id)
		{
			$window.location.href = url+'service_project_detail?id='+id;			
		}
		
		$scope.getServiceProjectDetailById = function(id)
		{
			var link = baseUrl + 'getServiceProjectDetailById?id='+id;			
			$http.get(link).success(
					function(data, status, headers, config) {
						$scope.geteventdetailbyid = data;
						for(i in $scope.geteventdetailbyid)
						{
							if($scope.geteventdetailbyid.serviceProjectId == id)
							{
								$scope.projecttitle = $scope.geteventdetailbyid.serviceProjectTitle;
								$scope.description = $scope.geteventdetailbyid.serviceProjectDescription;
								$scope.projectsubtitle = $scope.geteventdetailbyid.serviceProjectSubtitle;
							}
						}
					}).error(
					function(data, status, headers, config) {
						$scope.geteventdetailbyid = "Response Fail";
					});		
			
			var link = baseUrl+'getRelatedServiceProjectImages?id='+id;
				$http.get(link).success(function(data, status, headers,config)
						{
							$scope.serviceprojectrelatedimages = data;
						})
						.error(function(data, status, headers,config)
						{
							$scope.serviceprojectrelatedimages = "Response Fail";
						});
				
				var link = baseUrl + 'getPhotoAlbumByServiceProjectId?id='+id;
				$http.get(link).success(function(data,status,headers,config)
					{
						$scope.photoalbumbyserviceprojectid = data;			
					})
					.error(function(data,status,headers,config)
					{
						$scope.photoalbumbyserviceprojectid = "Responce Fail";
					});
		}
		
		$scope.serviceprojectimagelist = [ {} ];
		
		var formData1 = new FormData();
		$scope.addImageRow = function() 
		{
			if($scope.sequence==undefined || $scope.sequence=="")
			{
				$window.alert("Please enter image sequence");
				document.getElementById("sequence").focus();
				return;
			}
			else if($scope.imagetitle==undefined || $scope.imagetitle=="")
			{
				$window.alert("Please enter image title");
				document.getElementById("imagetitle").focus();
				return;
			}
			else
			{
				$scope.serviceprojectimagelist.push({'sequence' : $scope.sequence,'imageTitle' : $scope.imagetitle});
				formData1.append("serviceprojectimage",serviceprojectimage.files[0]);
				document.getElementById("sequence").value = "";
				document.getElementById("imagetitle").value = "";
			}
			
		};

		$scope.removeImageRow = function(imagetitle) 
		{
			var index = -1;
			var comArr = eval($scope.serviceprojectimagelist);
			for (var i = 0; i < comArr.length; i++) 
			{
				if (comArr[i].imageTitle === imagetitle) 
				{
					index = i;
					break;
				}
			}
			if (index === -1) 
			{
				alert("Something gone wrong");
			}
			$scope.serviceprojectimagelist.splice(index, 1);
		};
		
		$scope.nextsequenceno = function() 
		{			
			var link = baseUrl + 'getLastSequenceNo';
			$http.get(link).success(
				function(data,status,headers,config)
				{
					$scope.getlastsequenceno = data;					
					$scope.projectsequence = $scope.getlastsequenceno;					
				})
				.error(function(data,status,headers,config)
				{
					$scope.getlastsequenceno = "Responce Fail";
				});		
		}

		$scope.temp1 = 1; 
		$scope.temp2 = 0;
		$scope.addServiceProject = function()
		{			
			var title = $scope.title;
			var subtitle = $scope.subtitle;
			var projectdate = $scope.projectdate;
			var projectsequence = $scope.projectsequence;	
			
			var description = CKEDITOR.instances.description.getData();		
			
			if(subtitle==undefined || subtitle=="")
			{
				subtitle = "";
			}
			if(projectdate==undefined || projectdate=="")
			{
				projectdate = "";
			}
			if(projectsequence==undefined || projectsequence=="")
			{
				projectsequence = 0;
			}
			if(description==undefined || description=="")
			{
				description = "";
			}
			
			if(title==undefined || title=="")
			{
				$window.alert("Please enter project title");
				document.getElementById("title").focus();
				return;
			}			
			else
			{	
				$scope.temp1 = 0;
				$scope.temp2 = 1;
				
				//$window.alert("Inside Else");
				var d = encodeURIComponent(description);
				
				/*document.getElementById("loader").style.display = "block";*/
				var a = 2;
				$scope.list1 = [];
				$scope.list2 = [];				
				angular.forEach(
					$scope.serviceprojectimagelist,
					function(item)
					{
						$scope.list1.push(item.sequence);
						$scope.list2.push(item.imageTitle);
					});
				
				var link = baseUrl+'addServiceProject?projecttitle='+title+'&projectsubtitle='+subtitle +'&projectdate='+projectdate+'&projectsequence='+projectsequence;				
				var formData = new FormData();				
				formData.append("desc",d);
				$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
				     })
				     .success(
						function(data, status, headers, config)
						{
							$scope.addserviceproject = data;
							
							if ($scope.list1.length > 1) 
							{
								var link = baseUrl + 'addServiceProjectImages?sequence='+$scope.list1+'&imagetitle='+$scope.list2;
								$http({
									method : 'POST',
									url : link,
									headers : {
										'Content-Type' : undefined
									},
									data : formData1,
									transformRequest : function(data,headersGetterFunction)
									{
										return data;
									}
								})
								.success(
										function(data,status,headers,config)
										{
											$scope.addserviceprojectimages = data;
											a = a + 1;
											
											if ($scope.serviceprojectimagelist.length == 0)
											{
												
												$scope.temp2 = 0;
												$scope.temp1 = 1;
												$window.alert("Record Added Successfully");
												$window.location.href = adminurl + 'service_project';												
											}
											//$window.alert("Value of A-->"+a+"//Value of IMage Array Length-->"+$scope.serviceprojectimagelist.length+1);
											if (a == $scope.serviceprojectimagelist.length+1)
											{
												$scope.temp2 = 0;
												$scope.temp1 = 1;
												$window.alert("Record Added Successfully");
												$window.location.href = adminurl + 'service_project';												
											}
								})
								error(function(data,status,headers,config)
								{
									$scope.addserviceprojectimages = "Response Fail";
								});	
							}
													
						}).
						error(function(data, status, headers, config)
						{
							$scope.addserviceproject = "Response Fail";
						});
			}
		}
		
		$scope.getServiceProjectDetail = function(id) 
		{				
			for (i in $scope.serviceproject) 
			{
				if ($scope.serviceproject[i].serviceProjectId == id) 
				{
					$scope.serviceprojectid = $scope.serviceproject[i].serviceProjectId;
					$scope.projecttitle = $scope.serviceproject[i].serviceProjectTitle;
					$scope.projectsubtitle = $scope.serviceproject[i].serviceProjectSubtitle;
					$scope.serviceprojectdate = $scope.serviceproject[i].serviceProjectDate;	
					$scope.serviceprojectsequence = $scope.serviceproject[i].serviceSequence;
					$scope.serviceprojectdescription = $scope.serviceproject[i].serviceProjectDescription;
				}				
			}
			CKEDITOR.instances.description.setData($scope.serviceprojectdescription);
			
			var link = baseUrl+'getRelatedServiceProjectImages?id='+id;
			$http.get(link).success(function(data, status, headers,config)
			{
				$scope.serviceprojectrelatedimages = data;
			})
			.error(function(data, status, headers,config)
			{
				$scope.serviceprojectrelatedimages = "Response Fail";
			});
		}
		
		var formData1 = new FormData();
		$scope.addImageRow1 = function()
		{
			if($scope.sequence==undefined || $scope.sequence=="")
			{
				$window.alert("Please enter image sequence");
				document.getElementById("sequence").focus();
				return;
			}
			else if($scope.imagetitle==undefined || $scope.imagetitle=="")
			{
				$window.alert("Please enter image title");
				document.getElementById("imagetitle").focus();
				return;
			}
			else
			{
				$scope.serviceprojectrelatedimages.push({'sequence' : $scope.sequence,'imageTitle' : $scope.imagetitle});
				formData1.append("serviceprojectimage",serviceprojectimage.files[0]);
				document.getElementById("sequence").value = "";
				document.getElementById("imagetitle").value = "";
			}
		};
		$scope.removeImageRow1 = function(imagetitle)
		{
			var index = -1;
			var comArr = eval($scope.serviceprojectrelatedimages);
			for(var i = 0; i < comArr.length; i++)
			{
				if (comArr[i].imageTitle === imagetitle)
				{
					index = i;
					break;
				}
			}
			if(index === -1)
			{
				alert("Something gone wrong");
			}
			$scope.serviceprojectrelatedimages.splice(index, 1);
		};

		var formData2 = new FormData();
		$scope.serviceprojectrelatedimagesnew = [ {} ];
		$scope.addImageRowNew = function()
		{			
			$scope.serviceprojectrelatedimagesnew.push({'sequence' : $scope.sequence,'imageTitle' : $scope.imagetitle});
			formData2.append("serviceprojectimage",serviceprojectimage.files[0]);
		};
		$scope.removeImageRowNew = function(imagetitle)
		{
			var index = -1;
			var comArr = eval($scope.serviceprojectrelatedimagesnew);
			for(var i = 0; i < comArr.length; i++)
			{
				if(comArr[i].imageTitle === imagetitle)
				{
					index = i;
					break;
				}
			}
			if(index === -1)
			{
				alert("Something gone wrong");
			}
			$scope.serviceprojectrelatedimagesnew.splice(index, 1);
		};
		
		$scope.editServiceProject = function(id)
		{
			$scope.relatedimages1 = [];
			$scope.relatedimages2 = [];
			angular.forEach($scope.serviceprojectrelatedimagesnew,
				function(item)
				{					
					$scope.relatedimages1.push(item.sequence);
					$scope.relatedimages2.push(item.imageTitle);
				});
			
			var title = $scope.projecttitle;
			var subtitle = $scope.projectsubtitle;
			var projectdate = $scope.serviceprojectdate;
			var projectsequence = $scope.serviceprojectsequence;	
			
			var description = CKEDITOR.instances.description.getData();		
			
			if(subtitle==undefined || subtitle=="")
			{
				subtitle = "";
			}
			if(projectdate==undefined || projectdate=="")
			{
				projectdate = "";
			}
			if(projectsequence==undefined || projectsequence=="")
			{
				projectsequence = 0;
			}
			if(description==undefined || description=="")
			{
				description = "";
			}
			
			if(title==undefined || title=="")
			{
				$window.alert("Please enter project title");
				document.getElementById("title").focus();
				return;
			}			
			else
			{	
				$scope.temp1 = 0;
				$scope.temp2 = 1;
				
				var d = encodeURIComponent(description);
				
				var link = baseUrl+'deleteSelectedServiceProjectImages?id='+id;				
				$http['delete'](link)
					.success(function(data, status,headers, config)
					{
						$scope.deleteselectedserviceprojectimages = data;
					})
					.error(function(data, status,headers, config)
					{
						$scope.deleteselectedserviceprojectimages = "Response Fail";
					});

				
				var link = baseUrl+'editServiceProject?id='+id+'&projecttitle='+title+'&projectsubtitle='+subtitle +'&projectdate='+projectdate+'&projectsequence='+projectsequence;				
				var formData=new FormData();				
				formData.append("desc",d);
				$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
				     })
				     .success(function(data, status, headers, config)
				    {
				    	 $scope.editserviceproject = data;
				    	 var link = baseUrl+'editServiceProjectImages?id='+id+'&sequence='+$scope.relatedimages1+'&imagetitle='+$scope.relatedimages2;				    	 
							$http({
									method: 'POST',
									url: link,
									headers: {'Content-Type': undefined},
									data: formData2,
									transformRequest: function(data, headersGetterFunction)
									{
							        	return data;
							        }
								})
								.success(function(data, status)
								{   
								  	$scope.editserviceprojectimages = data;
								  	
								  	if($scope.serviceprojectrelatedimages.length == 0)
								  	{
								  		$scope.temp1 = 1;
										$scope.temp2 = 0;
								  		$window.alert("Record Updated Successfully");
										$window.location.href = adminurl+'service_project';
								  	}
								  	
								  	var a = 0;								  	
								  	angular.forEach($scope.serviceprojectrelatedimages,
									   		function(item)
									   		{
												if(item.imageTitle != null)
												{
													var link = baseUrl+'editServiceProjectImagesNew?id='+id+'&sequence='+item.sequence+'&imagetitle='+item.imageTitle+'&image='+item.image;													
								    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{						    							
							    							$scope.editserviceprojectimagesnew = data;
							    							
							    							a = a + 1;							    							
							    							if(a == $scope.serviceprojectrelatedimages.length)
							    							{
							    								$scope.temp1 = 1;
							    								$scope.temp2 = 0;
							    								$window.alert("Record Updated Successfully");
																$window.location.href = adminurl+'service_project';
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.editserviceprojectimagesnew = "Response Fail";
							    						});
												}
										    });		
								})
								error(function(data, status, headers, config)
								{
									$scope.editserviceprojectimages = "Response Fail";
								});	
					}).
					error(function(data, status, headers, config)
					{
						$scope.editserviceproject = "Response Fail";
					});
			}
		}
		
						
		$scope.checkAll = function()
		{
			if ($scope.selectedAll)
			{
				$scope.selectedAll = false;
			}
			else
			{
				$scope.selectedAll = true;
			}
			angular.forEach($scope.serviceproject, function (item)
			{
				item.selected = $scope.selectedAll;
			});
		}
				
		$scope.deleteServiceProject = function()
		{
			deleteserviceProject = $window.confirm('Are you sure you want to delete this record?');
			if(deleteserviceProject)
			{			
			    angular.forEach($scope.serviceproject,
		    		function(item)
		    		{		    			
		    			if (item.selected)
		   				{
		   					var link = baseUrl+'deleteServiceProject?id='+item.serviceProjectId;
		    				$http['delete'](link).success(
		   						function(data, status, headers, config)
		   						{
		   							$scope.deleteserviceproject = data;
		   						}).
		   						error(function(data, status, headers, config)
		   						{
		   							$scope.deleteserviceproject = "Response Fail";
		   						});
		   				}
		   				
		   		});
			    $window.location.href = adminurl+'service_project';
			}
		}						
});
	
	app.controller('albumCtrl', ['$scope', '$filter', '$http', '$window', '$location' ,function ($scope, $filter, $http, $window, $location){
	    $scope.currentPage = 0;
	    $scope.pageSize = 20;
	    $scope.search = '';
	    
	    $scope.getData = function ()
	    {
	    	return $filter('filter')($scope.data, $scope.search)
	    }
	    
	    $scope.numberOfPages=function()
	    {
	    	return Math.ceil($scope.album.length/$scope.pageSize);
	    } 
	    
	    var baseUrl = $location.protocol()+"://"+location.host+u;
	    
	    $scope.getBirthdaysAndAnniversariesByDate = function ()
	    {
			var currentdate = $filter('date')(new Date(),'MM-dd');
			
			var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = "Response Fail";
			});
			
			var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = "Response Fail";
			});
	    }
	    
	    var link = baseUrl + 'ServiceProject';
		$http.get(link).success(
			function(data,status,headers,config)
			{
				$scope.serviceproject = data;					
			})
			.error(function(data,status,headers,config)
			{
				$scope.serviceproject = "Responce Fail";
			});
	    
	    var link = baseUrl+'Album';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.album = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.album = "Response Fail";
			});

		var link = baseUrl+'AlbumImages';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.albumimages = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.albumimages = "Response Fail";
			});
		
		var link = baseUrl+'getAlbumOneImage';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getalbumoneimage = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getalbumoneimage = "Response Fail";
			});
				
				var formData = new FormData();
				$scope.albumimage = [{}];
				
				$scope.addAlbumImageRow = function()
				{					
					var imagedescription = $scope.imagedescription;
					
					if(imagedescription==undefined || imagedescription=="")
					{
						imagedescription = "Description";
					}

					if($scope.imagetitle==undefined || $scope.imagetitle=="")
					{
						$window.alert("Please Enter Image Title!");
						document.getElementById("imagetitle").focus();
						return;
					}
					else
					{
						$scope.albumimage.push({'imageTitle':$scope.imagetitle, 'imageDescription':imagedescription});
						formData.append("image",image.files[0]);
					}
					
				};
				
				$scope.removeAlbumImageRow = function(imagetitle)
				{				
					var index = -1;
					var comArr = eval( $scope.albumimage);
					for( var i = 0; i < comArr.length; i++ ) {
						if( comArr[i].imageTitle === imagetitle ) {
							index = i;
							break;
						}
					}
					if( index === -1 ) {
						alert( "Something gone wrong" );
					}
					$scope.albumimage.splice( index, 1 );
				};			
				
				$scope.temp1 = 1;
				$scope.temp2 = 0;
				$scope.addalbum = function()
				{
					var project = $scope.project;					
					var title = $scope.albumtitle;
					var subtitle = $scope.albumsubtitle;
					var albumdate = $scope.albumdate;
					var description = $scope.description;
					
					if(project == undefined || project == "")
					{	
						project = 0;
					}									
					if(subtitle == undefined || subtitle == "")
					{	
						subtitle = 0;
					}					
					if(albumdate == undefined || albumdate == "")
					{	
						albumdate = "";
					}										
					if(description==undefined || description=="")
					{
						description="";
					}	
					
					if(title==undefined || title=="")
					{
						$window.alert("Please Enter Album Title!");
						document.getElementById("title").focus();
						return;
					}
					else
					{
						$scope.temp1 = 0;
						$scope.temp2 = 1;
						
						var pap = title.replace("&","$");
						var pap1 = pap.replace("#","~");
						var pap2 = pap1.replace("%","!");
						
						var link = baseUrl + 'addAlbum?projectid='+project+'&title='+pap2+'&subtitle='+subtitle+'&albumdate='+albumdate+'&description='+description;						
						$http.post(link).success(
								function(data, status, headers, config)
								{
									$scope.addalbum = data;											
														
									$scope.titleimage = [];
									$scope.desc = [];
									angular.forEach($scope.albumimage,
										function(item)
										{
											if(item.imageTitle != null)
											{
												var tit = item.imageTitle.replace("&","$");
												var tit1 = tit.replace("#","~");
												var tit2 = tit1.replace("%","!");												
													
												$scope.titleimage.push(tit2);
												$scope.desc.push(item.imageDescription);
											}
										});														
														
										var link = baseUrl + 'addAlbumImage?imagetitle='+$scope.titleimage+'&imagedescription='+$scope.desc;										
										$http({
												method: 'POST',
												url: link,
										        headers: {'Content-Type': undefined},
										        data: formData,
										        transformRequest: function(data, headersGetterFunction)
										        {
										        	return data;
										        }
										       }).success(
													function(data, status, headers, config)
													{
														$scope.addalbumimage = data;
														
														$scope.temp1 = 1;
														$scope.temp2 = 0;
														
														$window.alert("Album added successfully...");
														$window.location.href = adminurl+'photo_album';														
													}).
													error(function(data, status, headers, config)
													{
														$scope.addalbumimage = "Response Fail";
													});
												
												
							}).error(function()
							{
								$scope.addalbum = "Responce fail";
							});
				}																																																																							
			}
			
			$scope.getalbum = function(albumid)
			{
				 var link = baseUrl+'Album';
					$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.album = data;
							
							for (i in $scope.album)
							{
				                if ($scope.album[i].albumId == albumid)
				                {
				                	$scope.albumid = $scope.album[i].albumId;
				                	$scope.albumtitle = $scope.album[i].albumTitle;
				                	$scope.albumsubtitle = $scope.album[i].albumSubtitle;
				                	$scope.albumdate = $scope.album[i].albumDate;          	
				                	$scope.description = $scope.album[i].description;
				                	$scope.project = $scope.album[i].serviceProjectId;		                	
				                }
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.album = "Response Fail";
						});				
					
					var link = baseUrl+'AlbumImage?albumid='+albumid;					
					$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.albumimage = data;
							}).
							error(function(data, status, headers, config)
							{
								$scope.albumimage = "Response Fail";
							});
			}
				
				$scope.removeRow1 = function(imagetitle)
				{				
					var index = -1;
					var comArr = eval( $scope.albumimage);
					for( var i = 0; i < comArr.length; i++ ) {
						if( comArr[i].imageTitle === imagetitle ) {
							index = i;
							break;
						}
					}
					if( index === -1 ) {
						alert( "Something gone wrong" );
					}
					$scope.albumimage.splice( index, 1 );
				};
				
				var formData = new FormData();
				$scope.albumimagenew = [{}];
				
				$scope.addAlbumImageRowEdit = function()
				{
					if($scope.imagedescription==undefined || $scope.imagedescription=="")
					{
						$scope.imagedescription = "Description";
					}

					if($scope.imagetitle==undefined || $scope.imagetitle=="")
					{
						$window.alert("Please Enter Image Title!");
						document.getElementById("imagetitle").focus();
						return;
					}
					else
					{
						$scope.albumimagenew.push({'imageTitle':$scope.imagetitle, 'imageDescription':$scope.imagedescription});
						formData.append("image",image.files[0]);
					}
				};
						
				$scope.removeAlbumImageRowEdit = function(imagetitle)
				{				
					var index = -1;
					var comArr = eval( $scope.albumimagenew);
					for( var i = 0; i < comArr.length; i++ ) {
						if( comArr[i].imageTitle === imagetitle ) {
							index = i;
							break;
						}
					}
					if( index === -1 ) {
						alert( "Something gone wrong" );
					}
					$scope.albumimagenew.splice( index, 1 );
				};
				
				$scope.editalbum = function(albumid)
				{
					var project = $scope.project;					
					var title = $scope.albumtitle;
					var subtitle = $scope.albumsubtitle;
					var albumdate = $scope.albumdate;
					var description = $scope.description;		
					
					if(project == undefined || project == "")
					{	
						project = 0;
					}					
					if(subtitle == undefined || subtitle == "")
					{	
						subtitle = "";
					}					
					if(albumdate == undefined || albumdate == "")
					{	
						albumdate = "";
					}										
					if(description==undefined || description=="")
					{
						description="";
					}					
					if(title==undefined || title=="")
					{
						$window.alert("Please Enter Album Title!");
						document.getElementById("title").focus();
						return;
					}
					else
					{
						$scope.temp1 = 0;
						$scope.temp2 = 1;
						
						var link = baseUrl+'deleteSelectedAlbumImage?albumid='+albumid;
						$http['delete'](link).success(
								function(data, status, headers, config)
								{
									$scope.deleteselectedalbumimage = data;
								}).
								error(function(data, status, headers, config)
								{
									$scope.deleteselectedalbumimage = "Response Fail";
								});
													
						var pap = title.replace("&","$");
						var pap1 = pap.replace("#","~");
						var pap2 = pap1.replace("%","!");
						
						$scope.title = [];
						$scope.desc = [];
						angular.forEach($scope.albumimagenew,
							function(item)
							{
								if(item.imageTitle != null)
								{
									$scope.title.push(item.imageTitle);
									$scope.desc.push(item.imageDescription);
								}
							});
						
						var link = baseUrl + 'editAlbum?albumid='+albumid+'&project='+project+'&title='+pap2+'&subtitle='+subtitle+'&albumdate='+albumdate+'&description='+description;
						
						$http.put(link).success(
								function(data, status, headers, config)
								{
									$scope.editalbum = data;
									
									var link = baseUrl+'editAlbumImage?albumid='+albumid+'&imagetitle='+$scope.title+'&imagedescription='+$scope.desc;

									$http({
											method: 'POST',
											url: link,
											headers: {'Content-Type': undefined},
											data: formData,
											transformRequest: function(data, headersGetterFunction)
											{
									        	return data;
									        }
										})
										.success(function(data, status)
										{   
										  	$scope.editalbumimage = data;
										  	
										  	if($scope.albumimage.length == 0)
										  	{
										  		$scope.temp1 = 0;
												$scope.temp2 = 1; 
												
										  		$window.alert("Album Updated Successfully");
												$window.location.href = adminurl+'photo_album';
										  	}
										  	
										  	var a = 0;
										  	
										  	angular.forEach($scope.albumimage,
											   		function(item)
											   		{
														if(item.imageTitle != null)
														{
															var link = baseUrl+'editAlbumImageNew?albumid='+albumid+'&imagetitle='+item.imageTitle+'&imagedescription='+item.description+'&image='+item.image;
										    				$http.post(link).success(
									    						function(data, status, headers, config)
									    						{						    							
									    							$scope.editalbumimagenew = data;
									    							
									    							a = a + 1;
									    							
									    							if(a == $scope.albumimage.length)
									    							{
									    								$scope.temp1 = 0;
									    								$scope.temp2 = 1;
									    								
									    								$window.alert("Album Updated Successfully");
																		$window.location.href = adminurl+'photo_album';
									    							}
									    							
									    						}).
									    						error(function(data, status, headers, config)
									    						{
									    							$scope.editalbumimagenew = "Response Fail";
									    						});
														}
												    });											
										})
										error(function(data, status, headers, config)
										{
											$scope.editalbumimage = "Response Fail";
										});
								}).
								error(function(data, status, headers, config)
								{
									$scope.editalbum = "Response Fail";
								});
					}
				}				
				
				$scope.checkAll = function()
				{
					if ($scope.selectedAll)
					{
						$scope.selectedAll = false;
					}
					else
					{
			            $scope.selectedAll = true;
			        }
					angular.forEach($scope.album, function (item)
					{
						item.selected = $scope.selectedAll;
					});
			    }
				
				$scope.deletealbum = function()
				{
					deleteAlbum = $window.confirm('Are you sure you want to delete album?');
					if(deleteAlbum)
					{
					    angular.forEach($scope.album,
					    		function(item)
					    		{
					    			if (item.selected)
					    				{
						    				var link = baseUrl+'deleteAlbum?albumid='+item.albumId;
						    				$http['delete'](link).success(
						    						function(data, status, headers, config)
						    						{
						    							$scope.deletealbum = data;
						    						}).
						    						error(function(data, status, headers, config)
						    						{
						    							$scope.deletealbum = "Response Fail";
						    						});
					    				}
					    		});					    
						$window.location.href = adminurl+'photo_album';
					}
				}				
	}]);
	
	app.controller("bulletinCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
				var baseUrl = $location.protocol()+"://"+location.host+u;
				
				$scope.currentPage = 0;
			    $scope.pageSize = 20;
			    $scope.search = '';
			    
			    $scope.getData = function ()
			    {
				     // needed for the pagination calc
			    	return $filter('filter')($scope.data, $scope.search);
			    }
			    
			    $scope.numberOfPages=function()
			    {
			    	return Math.ceil($scope.clubbulletin.length/$scope.pageSize);
			    }
			    
			    $scope.getBirthdaysAndAnniversariesByDate = function ()
			    {
					var currentdate = $filter('date')(new Date(),'MM-dd');
					
					var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallbirthdaysbydate = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallbirthdaysbydate = "Response Fail";
					});
					
					var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallanniversariesbydate = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallanniversariesbydate = "Response Fail";
					});
			    }
				
				var link = baseUrl + 'ClubBulletin';
				$http.get(link).success(
					function(data,status,headers,config)
					{
						$scope.clubbulletin = data;					
					})
					.error(function(data,status,headers,config)
					{
						$scope.clubbulletin = "Responce Fail";
					});
				
				$scope.getCurrentDefaultYear = function ()
			    {
					var link = baseUrl + 'RotaryYear';
					$http.get(link).success(
						function(data,status,headers,config)
						{
							$scope.rotaryyear = data;
							
							for (i in $scope.rotaryyear) 
							{
								if ($scope.rotaryyear[i].defaultYear == "y") 
								{						
									$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;					
								}				
							} 
							
							var link = baseUrl + 'getClubBulletinDetailByRotaryYear?rotaryyearid='+$scope.rotaryyearid;					
							$http.get(link).success(
									function(data, status, headers, config) {
										$scope.getcurrentyearbulletins = data;
									}).error(
									function(data, status, headers, config) {
										$scope.getcurrentyearbulletins = "Response Fail";
									});
							
						})
						.error(function(data,status,headers,config)
						{
							$scope.rotaryyear = "Responce Fail";
						});
					 
			    }
				
				$scope.getBulletinDetailByRotaryYear = function ()
			    {				
					var rotaryyearid = $scope.rotaryyearid; 
					var link = baseUrl + 'getClubBulletinDetailByRotaryYear?rotaryyearid='+rotaryyearid;						
					$http.get(link).success(
						function(data, status, headers, config) {
							$scope.getcurrentyearbulletins = data;
						}).error(
						function(data, status, headers, config) {
							$scope.getcurrentyearbulletins = "Response Fail";
						});			 
			    }
				
				$scope.temp1 = 1;
				$scope.temp2 = 0;
				$scope.addBulletin = function()
				{
					var rotaryyearid = $scope.rotaryyearid;
					var issueno = $scope.issueno;
					var bulletintitle = $scope.bulletintitle;	
					var issuedate = $scope.issuedate;
					
					if(rotaryyearid == "" || rotaryyearid == undefined)
					{
						$window.alert("Please select rotary year");
						document.getElementById("rotaryyearid").focus();
						return;
					}
					else if(issueno == "" || issueno == undefined)
					{
						$window.alert("Please enter issue date");
						document.getElementById("issueno").focus();
						return;
					}
					else if(bulletintitle == "" || bulletintitle == undefined)
					{
						$window.alert("Please enter bulletin title");
						document.getElementById("bulletintitle").focus();
						return;
					}
					else if(issuedate == "" || issuedate == undefined)
					{
						$window.alert("Please enter bulletin issue date");
						document.getElementById("issuedate").focus();
						return;
					}
					else
					{
						$scope.temp1 = 0;
						$scope.temp2 = 1;
						
						var link = baseUrl+'addClubBulletin?rotaryyearid='+rotaryyearid+'&issueno='+issueno+'&bulletintitle='+bulletintitle+'&issuedate='+issuedate;				
						var formData=new FormData();			
						formData.append("file",file.files[0]);
						$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     })
						     .success(
								function(data, status, headers, config)
								{
									$scope.addclubbulletin = data;
									$scope.temp1 = 1;
									$scope.temp2 = 0;
									$window.alert("Bulletin Added Successfully...");
									$window.location.href = adminurl+'manage_club_bulletins';
								}).
								error(function(data, status, headers, config)
								{
									$scope.addclubbulletin = "Response Fail";
								});
					}
				}
				
				$scope.getClubBulletinDetail = function(id) 
				{			
					for (i in $scope.clubbulletin) 
					{
						if ($scope.clubbulletin[i].bulletinId == id) 
						{
							$scope.bulletinid = $scope.clubbulletin[i].bulletinId;
							$scope.editissueno = $scope.clubbulletin[i].issueNo;
							$scope.editbulletintitle = $scope.clubbulletin[i].bulletinTitle;
							$scope.editissuedate = $scope.clubbulletin[i].bulletinDate;	
							$scope.editrotaryyearid = $scope.clubbulletin[i].rotaryYearId;					
						}				
					}
				}
				
				$scope.editClubBulletin = function(id)
				{
					var rotaryyearid = $scope.editrotaryyearid;
					var issueno = $scope.editissueno;
					var bulletintitle = $scope.editbulletintitle;
					var issuedate = $scope.editissuedate;
					var pdffile = document.getElementById("file").value;					
					
					if(rotaryyearid == "" || rotaryyearid == undefined)
					{
						$window.alert("Please select rotary year id");
						document.getElementById("rotaryyearid").focus();
						return;
					}			
					else if(issueno == "" || issueno == undefined)
					{
						$window.alert("Please enter issue number");
						document.getElementById("issueno").focus();
						return;
					}
					else if(bulletintitle == "" || bulletintitle == undefined)
					{
						$window.alert("Please enter bulletin title");
						document.getElementById("bulletintitle").focus();
						return;
					}
					else if(issuedate == "" || issuedate == undefined)
					{
						$window.alert("Please enter bulletin issue date");
						document.getElementById("issuedate").focus();
						return;
					}
					else
					{
						$scope.temp1 = 0;
						$scope.temp2 = 1;
						
						if(pdffile == "")
						{
							var link = baseUrl+'editClubBulletinWithoutFile?id='+id+'&rotaryyearid='+rotaryyearid+'&issueno='+issueno+'&bulletintitle='+bulletintitle+'&issuedate='+issuedate;							
							$http.post(link).success(
								function(data, status, headers, config)
								{
									$scope.editclubbulletinwithoutfile = data;
									$window.alert("Bulletin Updated Successfully...");
									$window.location.href = adminurl + 'club_bulletins';
								}).
								error(function(data, status, headers, config)
								{
									$scope.editclubbulletinwithoutfile = "Response Fail";
								});
						}
						if(pdffile != "")
						{
							var link = baseUrl+'editClubBulletinWithFile?id='+id+'&rotaryyearid='+rotaryyearid+'&issueno='+issueno+'&bulletintitle='+bulletintitle+'&issuedate='+issuedate;							
							var formData=new FormData();			
							formData.append("file",file.files[0]);
							$http({
							        method: 'POST',
							        url: link,
							        headers: {'Content-Type': undefined},
							        data: formData,
							        transformRequest: function(data, headersGetterFunction)
							        {
							        	return data;
							        }
							     })
							     .success(
									function(data, status, headers, config)
									{
										$scope.editclubbulletinwithfile = data;
										$scope.temp1 = 1;
										$scope.temp2 = 0;
										$window.alert("Bulletin Updated Successfully...");
										$window.location.href = adminurl+'manage_club_bulletins';
									}).
									error(function(data, status, headers, config)
									{
										$scope.editclubbulletinwithfile = "Response Fail";
									});
						}					
					}
				}
				
				$scope.checkAll = function()
				{
					if ($scope.selectedAll)
					{
						$scope.selectedAll = false;
					}
					else
					{
			            $scope.selectedAll = true;
			        }
					angular.forEach($scope.clubbulletin, function (item)
					{
						item.selected = $scope.selectedAll;
					});
				}
				
				$scope.deleteClubBulletin = function()
				{
					deleteclubBulletin = $window.confirm('Are you sure you want to delete record?');
					if(deleteclubBulletin)
					{			
					    angular.forEach($scope.clubbulletin,
					    		function(item)
					    		{		    			
					    			if (item.selected)
					    				{
					    					var link = baseUrl+'deleteClubBulletin?id='+item.bulletinId;
						    				$http['delete'](link).success(
						    						function(data, status, headers, config)
						    						{
						    							$scope.deleteclubbulletin = data;
						    						}).
						    						error(function(data, status, headers, config)
						    						{
						    							$scope.deleteclubbulletin = "Response Fail";
						    						});
					    				}
					    				
					    		});
						$window.location.href = adminurl+'manage_club_bulletins';
					}
				}						
			});
	
	app.controller('memberCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
	{
		$scope.currentPage = 0;
	    $scope.pageSize = 10;
	    $scope.search = '';
	    $scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	    
	    var baseUrl = $location.protocol()+"://"+location.host+u;
		
		$scope.a = 0;
		$scope.rotaryYearId = 0;
		/*$scope.getfellowshipList = function(){*/
			var baseUrl = $location.protocol() + "://" + location.host + u;
		var link = baseUrl+'getAllFellowshipName';  
		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAllFellowshipNameList = data;
			
		}).error(function(data, status, headers, config) {
			$scope.getAllFellowshipNameList = "Response Fail";
		});
		
/*	}*/
		$scope.getmember = function(fellowship_id){
			
			var link = baseUrl+'getAllMemberNameList';  

			$http.get(link).success(function(data, status, headers, config) {
				$scope.getAllMemberNamedata = data;
				
			}).error(function(data, status, headers, config) {
				$scope.getAllMemberNamedata = "Response Fail";
			});
			
			}
		
		$scope.changepage = function()
		{			
			if($scope.pageSize == "All") {
				$scope.a = 0;
				
				var link = baseUrl+'getMember';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember1 = data;
					$scope.a = 1;
					
					for(i in $scope.getmember1){
						if($scope.$scope.getmember1[i].status == "y"){		            		
		            		$scope.activemember= true;
		               	} else {
		            		$scope.activemember= false;
		            	}
					}
										
					$scope.totalcount = $scope.getmember.length;					
				}).error(function(data, status, headers, config) {
					$scope.getmember1 = "Response Fail";
				});
			} else {
				$scope.a = 0;
				var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember1 = data;
					$scope.a = 1;
					for(i in $scope.getmember1){
						if($scope.$scope.getmember1[i].status == "y"){		            		
		            		$scope.activemember= true;
		               	} else {
		            		$scope.activemember= false;
		            	}
					}
				}).error(function(data, status, headers, config) {
					$scope.getmember1 = "Response Fail";
				});
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
				
				var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember1 = data;
					$scope.a = 1;
					
				}).error(function(data, status, headers, config) {
					$scope.getmember1 = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.addenquiryassign = "Response Fail";
			});
		}
		
		
		
		var link = baseUrl+'getMember';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
		
		var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember1 = data;
			$scope.a = 1;
			
		}).error(function(data, status, headers, config) {
			$scope.getmember1 = "Response Fail";
		});
		
		var link = baseUrl + 'RotaryYear';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.rotaryyear = data;
			for (i in $scope.rotaryyear) {
				if ($scope.rotaryyear[i].defaultYear == "y") {
					$scope.rotaryYearId = $scope.rotaryyear[i].rotaryYearId;
					$scope.rotaryyeartitle = $scope.rotaryyear[i].rotaryYearTitle;
				}
			}
		}).error(function(data,status,headers,config) {
			$scope.rotaryyear = "Responce Fail";
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
		
		var link = baseUrl+'getAllBusinessCategories';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allbusinesscategories = data;			
		}).error(function(data, status, headers, config) {
			$scope.allbusinesscategories = "Response Fail";
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
		
		var link = baseUrl+'getCountry';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getcountry = data;
		}).error(function(data, status, headers, config) {
			$scope.country = "Response Fail";
		});
		
		var link = baseUrl+'getState';
		$http.get(link).success( function(data, status, headers, config) {
			$scope.getrelatedstate = data;
		}).error(function(data, status, headers, config) {
			$scope.getrelatedstate = "Response Fail";
		});
		
		var link = baseUrl+'Currency';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.currencies = data;
		}).
		error(function(data, status, headers, config)	{
			$scope.currencies = "Response Fail";
		});
		
		$scope.redirectmemberdetail = function(memberid,temp) {
			if(temp == 2){
				$window.location.href = url + "manage_my_profile?memberid="+memberid;
			} else {
				$window.location.href = adminurl + "manage_member_detail?memberid="+memberid;
			}
		}
		
		$scope.redirectToDetail = function(memberid) {
			$window.location.href = url + "search_detail?memberid="+memberid;
		}
		
		$scope.redirectToMemberProfile = function(id) {					
			$window.location.href = url + 'member_profile?id='+id;
		}
		
		$scope.onCountryChange = function()
		{
			var id = $scope.countryname;
			var link = baseUrl+'GetRelatedState?countryid='+id;
			$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getrelatedstate = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.relatedstate= "Response Fai";
					});
		}
		
		$scope.next = function()
		{		
			if($scope.pageSize == "All")
			{
				
			}
			else
			{
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;
					
				$scope.a = 0;
					
				var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config)	{
					$scope.getmember1 = data;
					$scope.a = 1;
				}).error(function(data, status, headers, config) {
					$scope.getmember1 = "Response Fail";
				});
			}			
		}
		
		$scope.prev = function()
		{
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;		
			
			$scope.a = 0;
				
			var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config)	{
				$scope.getmember1 = data;
				$scope.a = 1;
			}).error(function(data, status, headers, config) {
				$scope.getmember1 = "Response Fail";
			});			
		}
		
		$scope.searchmember = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				$scope.a = 0;
				
				var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getmember1 = data;
							$scope.a = 1;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getmember1 = "Response Fail";
						});
			}
			else
			{
				$scope.a = 0;
				
				var link = baseUrl+'searchMembers?keyword='+search;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember1 = data;
					$scope.a = 1;
				}).error(function(data, status, headers, config) {
					$scope.getmember1 = "Response Fail";
				});
			}
		}
		
		$scope.getSearchedMembers = function(search){
			$scope.search = search;
			var link = baseUrl+'searchMembers?keyword='+search;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember1 = data;				
			}).error(function(data, status, headers, config) {
				$scope.getmember1 = "Response Fail";
			});
		}
		
		$scope.deletemember = function()
		{
			d = $window.confirm('Are you sure you want to delete member?');
			if(d)
			{
			    angular.forEach($scope.getmember1,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteMember?memberid='+item.memberId;
				    			$http['delete'](link).success(
				    					function(data, status, headers, config)
				    					{
				    						$scope.deletemembers = data;
				    					}).
				    					error(function(data, status, headers, config)
				    					{
				    						$scope.deletemembers = "Response Fail";
				    					});
			    				}
			    		});			    
				$window.location.href = adminurl+'manage_members';
			}
		}
		
		$scope.getMemberDetailsById = function(memberid) {			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;			
			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetail = data;
				for(i in $scope.getmemberdetail){
					$scope.firstname = $scope.getmemberdetail[i].memberFirstName;
					$scope.lastname = $scope.getmemberdetail[i].memberLastName;
					$scope.businesscategoryname = $scope.getmemberdetail[i].businessCategoryName;
					$scope.memberemail = $scope.getmemberdetail[i].memberEmail;
					$scope.membermobileno = $scope.getmemberdetail[i].memberMobileNumber;
					$scope.profilepic = $scope.getmemberdetail[i].memberProfilePicture;
				}
			}).error(function(data, status, headers, config) {
				$scope.getmemberdetail = "Response Fail";
			});
		}
		
		$scope.getmemberdetail = function(memberid) {			
			var link = baseUrl+'getMemberEducationDetail?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmembereducationdetail = data;
			}).error(function(data, status, headers, config) {
				$scope.getmembereducationdetail = "Response Fail";
			});
			
			var link = baseUrl + 'RotaryYear';
			$http.get(link).success(function(data,status,headers,config) {
				$scope.rotaryyear = data;
				for (i in $scope.rotaryyear) {
					if ($scope.rotaryyear[i].defaultYear == "y") {
						$scope.rotaryYearId = $scope.rotaryyear[i].rotaryYearId;
						$scope.rotaryyeartitle = $scope.rotaryyear[i].rotaryYearTitle;
					}
				}				
				var link = baseUrl+'getMemberCurrentCategoryId?memberid='+memberid+'&rotaryyearid='+$scope.rotaryYearId;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.membercurrentcategory = data;					
					$scope.membercategoryname = $scope.membercurrentcategory.memberCategoryId;					
					var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
					$http.get(link).success(function(data, status, headers, config) {						
						$scope.getmember = data;						
						for(i in $scope.getmember) {							
							if($scope.getmember[i].memberId == memberid) {								
								
								$window.alert($scope.getmember[i].memberFirstName);
								$scope.memberid = $scope.getmember[i].memberId;
								$scope.membershipId = $scope.getmember[i].membershipNumber;
								$scope.oldmembershipId = $scope.getmember[i].oldMembershipNumber;
								$scope.memberclubname = $scope.getmember[i].clubId;
								$scope.membertypename = $scope.getmember[i].memberTypeId;
								$scope.businesscategoryid = $scope.getmember[i].businessCategoryId;
								$scope.tenureplan = $scope.getmember[i].tenurePlan;
								$scope.joiningdate1 = $scope.getmember[i].joiningDate;
								$scope.joiningdate = $filter('date')($scope.joiningdate1, 'dd/MM/yyyy');
								$scope.membertitle = $scope.getmember[i].memberTitleName;
								$scope.firstname = $scope.getmember[i].memberFirstName;
								$scope.middlename = $scope.getmember[i].memberMiddleName;
								$scope.lastname = $scope.getmember[i].memberLastName;
								$scope.gender = $scope.getmember[i].memberGender;
								$scope.dateofbirth1 = $scope.getmember[i].memberDateOfBirth;
								$scope.dateofbirth = $filter('date')($scope.dateofbirth1, 'dd/MM/yyyy');
								$scope.anniversarydate1 = $scope.getmember[i].memberAnniversaryDate;
								$scope.anniversarydate = $filter('date')($scope.anniversarydate1, 'dd/MM/yyyy');
								$scope.bloodgroup = $scope.getmember[i].memberBloodGroup;
								$scope.$parent.aadharnumber = $scope.getmember[i].memberAadharNumber;
								$scope.$parent.countrynamecitizenship = $scope.getmember[i].memberCountryIdCitizenship;
								$scope.passportnumber = $scope.getmember[i].memberPassportNumber;
								$scope.pannumber = $scope.getmember[i].memberPanNumber;
								$scope.profilepicture = $scope.getmember[i].memberProfilePicture;
								$scope.email = $scope.getmember[i].memberEmail;
								$scope.email1 = $scope.getmember[i].memberEmail;
								$scope.password = $scope.getmember[i].memberPassword;
								$scope.memberbarcode = $scope.getmember[i].memberBarcode;
								$scope.memberqrcode = $scope.getmember[i].memberQrcode;
								
							}
						}
					}).error(function(data, status, headers, config) {
						$scope.getmember = "Response Fail";
					});					
				}).error(function(data, status, headers, config) {
					$scope.membercategoryname = "Response Fail";
				});
			}).error(function(data,status,headers,config) {
				$scope.rotaryyear = "Responce Fail";
			});		
		}
		
		$scope.sendMessage = function(memberid) {
			if($scope.userfirstname == undefined) {
				$window.alert("Please enter your first name");
				document.getElementById("userfirstname").focus();
				return;
			} else if($scope.userlastname == undefined) {
				$window.alert("Please enter your last name");
				document.getElementById("userlastname").focus();
				return;
			} else if($scope.useremail == undefined) {
				$window.alert("Please enter your email");
				document.getElementById("useremail").focus();
				return;
			} else if($scope.usermobileno == undefined) {
				$window.alert("Please enter your mobile number");
				document.getElementById("usermobileno").focus();
				return;
			} else if($scope.usermessage == undefined) {
				$window.alert("Please enter your message");
				document.getElementById("usermessage").focus();
				return;
			} else {
				$scope.spin = 1;
				var link = baseUrl+'sendUserMessage?memberid='+memberid+'&firstname='+$scope.userfirstname+'&lastname='+$scope.userlastname+'&email='+$scope.useremail+'&mobileno='+$scope.usermobileno+'&usermessage='+$scope.usermessage;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.sendusermesaage = data;
					$scope.spin = 0;							
					$window.alert("Your message has been send!");
					$window.location.href = url;
				}).error(function(data, status, headers, config) {
					$scope.sendusermesaage = "Response Fail";
					$window.alert("Some problem occured! Please try again after some time!");
				});
			}
		}
		
		$scope.typeschange = function() {
			
			var link = baseUrl + 'getLastMemberSequenceByCategory';			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getlastmembersequence1 = data;
				
				if($scope.getlastmembersequence1.length == 0) {
					$scope.getLastSequence1 = 0;
				} else {
					$scope.getLastSequence1 = $scope.getlastmembersequence1[0].sequence;
				}			
				
				var familyplan = $scope.familyplan;
				var count =  $scope.getLastSequence1 + 1;
				var typemem = "";
				var family = "";
//typemem = "RMBF";
				if($scope.membercategoryname == 7){
					typemem="OTHR";
				}
				else{
					typemem="RMBF";
				}
				
				if(count >= 0 && count <= 9) {
					family = typemem + "0000" + count;
					$scope.membershipId = family;
				} else if(count >= 10 && count <= 99) {
					family = typemem+ "000" + count;
					$scope.membershipId = family;
				} else if(count >= 100 && count <= 999) {
					family = typemem+ "00" + count;
					$scope.membershipId = family;
				} else if(count >= 1000 && count <= 9999) {
					family = typemem+ "0" + count;
					$scope.membershipId = family;
				} else if(count >= 10000 && count <= 99999) {
					family = typemem + count;
					$scope.membershipId = family;
				} else {
					$window.alert("Your membership number is not generated");
				}
			}).error(function(data, status, headers, config) {
				$scope.getlastmembersequence1 = "Response Fail";
			});
		}
		
		$scope.education = [{}];						
		
		$scope.addEducationRow = function()
		{		
			$scope.education.push({'degreeName':$scope.degreename, 'passingYear': $scope.passingyear, 'grade': $scope.grade, 'instituteName': $scope.institutename});
			document.getElementById("degreename").value = "";
			document.getElementById("passingyear").value = "";
			document.getElementById("grade").value = "";
			document.getElementById("institutename").value = "";
			
		};
		
		$scope.removeEducationRow = function(degreename)
		{				
			var index = -1;		
			var comArr = eval( $scope.education);
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
		
		$scope.addmemberdetail = function()
		{		
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
			
			if($scope.getLastSequence1 == undefined)
				$scope.getLastSequence1 = 0;
			
			var sequence = $scope.getLastSequence1 + 1;			
			var membershipid  = $scope.membershipId;
			var memberclubname = $scope.memberclubname;
			var membercategoryname = $scope.membercategoryname;					
			var tenureplan = $scope.tenureplan;			
			var joiningdate = document.getElementById("joiningdate").value;			
			//var jdate = $filter('date')(new Date(joiningdate), 'dd/MM/yyyy');
			var expirydate = $filter('date')(new Date(), 'yyyy/MM/dd');
			var membertitle = $scope.membertitle;
			var firstname = $scope.firstname;
			var middlename = $scope.middlename;
			var lastname = $scope.lastname;
			var gender = $scope.gender;
			var dateofbirth = document.getElementById("dateofbirth").value;	
			//var bdate = $filter('date')(new Date(dateofbirth), 'dd/MM/yyyy');
			var bloodgroup = $scope.bloodgroup;
			var anniversarydate = document.getElementById("anniversarydate").value;	
			//var adate = $filter('date')(new Date(anniversarydate), 'dd/MM/yyyy');
			var membertypename = $scope.membertypename;	
			var aadharnumber = $scope.aadharnumber;
			var countrynamecitizenship = $scope.countrynamecitizenship;
			var passportnumber = $scope.passportnumber;
			var pannumber = $scope.pannumber;
			var email = $scope.email;
			var membermobilenumber = $scope.mobilenumber;
			var password = $scope.password;	
			var joiningdate1 = "";
			var dateofbirth1 = "";
			var anniversarydate1 = "";	
			
			if(!$scope.vocation)
			{				
				$scope.vocation = 0;
			}
			if(!joiningdate)
			{				
				joiningdate1 = "";
			}
			else
			{	
				var jd = joiningdate.split('/');				
				if(jd[0] > 31)
				{
					$window.alert("Please enter valid joining date");
					document.getElementById("joiningdate").focus();
					return;
				}	
				else if(jd[1] > 12)
				{
					$window.alert("Please enter valid joining date");
					document.getElementById("joiningdate").focus();
					return;										
				}
				else
				{
					joiningdate1 = jd[2]+"-"+jd[1]+"-"+jd[0];
				}
			}
			if(tenureplan == "" || tenureplan == undefined)
			{
				tenureplan = "1";
			}						
			if(membertitle == "" || membertitle == undefined)
			{
				membertitle = "";
			}
			if(middlename == "" || middlename == undefined)
			{
				middlename = "";
			}
			if(!dateofbirth)
			{
				dateofbirth1 = "";
			}
			else
			{
				var bd = dateofbirth.split('/');
				
				if(bd[0] > 31)
				{
					$window.alert("Please enter valid birth date");
					document.getElementById("dateofbirth").focus();
					return;
				}	
				else if(bd[1] > 12)
				{
					$window.alert("Please enter valid birth date");
					document.getElementById("dateofbirth").focus();
					return;										
				}
				else
				{
					dateofbirth1 = bd[2]+"-"+bd[1]+"-"+bd[0];
				}
			}
			if(bloodgroup == "" || bloodgroup == undefined)
			{
				bloodgroup = "";
			}
			if(!anniversarydate)
			{
				anniversarydate1 = "";
			}
			else
			{				
				var ad = anniversarydate.split('/');
				
				if(ad[0] > 31)
				{
					$window.alert("Please enter valid anniversary date");
					document.getElementById("anniversarydate").focus();
					return;
				}	
				else if(ad[1] > 12)
				{
					$window.alert("Please enter valid anniversary date");
					document.getElementById("anniversarydate").focus();
					return;								
				}
				else
				{
					anniversarydate1 = ad[2]+"-"+ad[1]+"-"+ad[0];	
				}
			}
			if(aadharnumber == "" || aadharnumber == undefined)
			{
				aadharnumber = "";
			}
			if(countrynamecitizenship == "" || countrynamecitizenship == undefined)
			{
				countrynamecitizenship = 0;
			}
			if(passportnumber == "" || passportnumber == undefined)
			{
				passportnumber = "";
			}
			if(pannumber == "" || pannumber == undefined)
			{
				pannumber = "";
			}
			if(membertypename == "" || membertypename == undefined)
			{
				membertypename = 0;
			}
			
			if(memberclubname == "" || memberclubname == undefined) {
				$window.alert("Please select club name of member");
				document.getElementById("memberclubname").focus();
				return;
			} else if(membercategoryname == "" || membercategoryname == undefined) {
				$window.alert("Please select type of member");
				document.getElementById("membercategoryname").focus();
				return;
			} else if($scope.businesscategoryid == undefined) {
				$window.alert("Please select business category");
				document.getElementById("businesscategoryid").focus();
				return;
			}
			else if(firstname == "" || firstname == undefined)
			{
				$window.alert("Please enter first name");
				document.getElementById("firstname").focus();
				return;
			}
			else if(lastname == "" || lastname == undefined)
			{
				$window.alert("Please enter last name");
				document.getElementById("lastname").focus();
				return;
			}
			else if(gender == "" || gender == undefined)
			{
				$window.alert("Please select gender");
				document.getElementById("gender").focus();
				return;
			}			
			/*else if(membertypename == "" || membertypename == undefined)
			{
				$window.alert("Please select nationality");
				document.getElementById("membertypename").focus();
				return;
			}	*/		
			else if((membermobilenumber == "" && email == "") || (membermobilenumber == undefined && email == undefined))
			{
				$window.alert("Please enter email id or mobile number");
				return;
			}
			else if(password == "" || password == undefined)
			{
				$window.alert("Please enter password");
				document.getElementById("password").focus();
				return;
			}
			else
			{
				$scope.nospin = 0;
				$scope.spin = 1;
				var bg = encodeURIComponent(bloodgroup);		
				if(!email){
					$scope.emailCHeck = "y";
					$scope.mobileCHeck = $scope.mobilenumber;
				}
				else if(!membermobilenumber){
					$scope.mobileCHeck = 0;
					$scope.emailCHeck = $scope.email;
				}
				else{
					$scope.mobileCHeck = $scope.mobilenumber;
					$scope.emailCHeck = $scope.email;
				}
				var link = baseUrl+"checkmobilenumber?mobilenumber="+$scope.mobileCHeck+"&email="+$scope.emailCHeck;
				$http.get(link).success(function(data, status, headers, config) { 
					if (data == "Success") {
						alert("User with same Credentials already exist"); 
						$scope.nospin = 1;
						$scope.spin = 0;
		    		}
					else{
							if(!email){
								email="";
							}
							else if(!membermobilenumber){
								membermobilenumber="";
							}
				//	alert(scope.fellowship_id);
					
				var link = baseUrl+'addmemberdetail?rotaryyearid='+$scope.rotaryYearId+'&membershipid='+membershipid+'&memberclubname='+memberclubname+'&membercategoryname='+membercategoryname+'&membertypename='+membertypename+'&businesscategoryid='+$scope.businesscategoryid+'&tenureplan='+tenureplan+'&joiningdate='+joiningdate1+'&startdate='+joiningdate1+'&enddate='+expirydate+'&membertitle='+membertitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+dateofbirth1+'&bloodgroup='+bg+'&anniversarydate='+anniversarydate1+'&aadharnumber='+aadharnumber+'&countrynamecitizenship='+countrynamecitizenship+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&mobile='+membermobilenumber+'&email='+email+'&password='+password+'&sequence='+sequence+'&vocation='+$scope.vocation+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&fellowship_id='+$scope.fellowship_id;				
				var formData=new FormData();				
				formData.append("profile",imgInp.files[0]);				
				$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
						})
						 .success(function(data, status)
								 {   
									 $scope.addmemberdetail = data;
									 
									 if($scope.education.length == 1)
									 {
										 if($scope.membercategoryname == 7){
											 $scope.nospin = 1;
											 $scope.spin = 0;
											 $window.alert("Visitor Detail Added Successfully");
											 $window.location.href = adminurl+"manage_members"; 
										 }
										 else{
											 $scope.nospin = 1;
											 $scope.spin = 0;
											 $window.alert("Member Detail Added Successfully");
											 $window.location.href = adminurl+"contact_detail?membercategoryid="+membercategoryname+'&tenureplan='+tenureplan;
										 	}
										}
									 
									 var a = 1;
										
									 angular.forEach($scope.education,
										   		function(item)
										   		{
													if(item.degreeName != null)
													{
									    				var link = baseUrl+'addMemberEducation?degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName;
									    				
									    				$http.post(link).success(
								    						function(data, status, headers, config)
								    						{
								    							$scope.addmembereducation = data;
								    							a = a + 1;								    							
								    							if($scope.education.length == a)
								    							{
								    								if($scope.membercategoryname == 7){
																		 $scope.nospin = 1;
																		 $scope.spin = 0;
																		 $window.alert("Visitor Detail Added Successfully");
																		 $window.location.href = adminurl+"manage_members"; 
																	 }
																	 else{
																		 $scope.nospin = 1;
																		 $scope.spin = 0;
																		 $window.alert("Member Detail Added Successfully");
																		 $window.location.href = adminurl+"contact_detail?membercategoryid="+membercategoryname+'&tenureplan='+tenureplan;
																	 	}
								    							}
								    						}).
								    						error(function(data, status, headers, config)
								    						{
								    							$scope.addmembereducation = "Response Fail";
								    						});
													}
											    });
										
										
								 }).
						error(function(data, status, headers, config)
								{
									$scope.addmemberdetail = "Response Fail";
								});
					}
				}).error(function(data, status, headers, config) {
						$scope.addbusinesscategory = "Response Fail";
				});
			}
		}	
		
		$scope.getcontactdetail1 = function(membershipnumber)
		{
			var link = baseUrl+'getMember';
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getmember = data;
						
						for(i in $scope.getmember)
						{
							if($scope.getmember[i].membershipNumber == membershipnumber)
							{
								$scope.membersid = $scope.getmember[i].membersId;
								$scope.membercategoryid = $scope.getmember[i].memberCategoryId;
								$scope.companydetailid = $scope.getmember[i].companyDetailId;
								$scope.membershipnumber = $scope.getmember[i].membershipNumber;
								$scope.oldmembershipnumber = $scope.getmember[i].oldMembershipNumber;
								$scope.memberfirstname = $scope.getmember[i].memberFirstName;
								$scope.membermiddlename = $scope.getmember[i].memberMiddleName;
								$scope.memberlastname = $scope.getmember[i].memberLastName;
								$scope.membermobilenumber = $scope.getmember[i].memberMobileNumber;
								$scope.mobilenumber = $scope.membermobilenumber;
								$scope.memberemail = $scope.getmember[i].memberEmail;
							}
						}
						
						if($scope.membercategoryid == 2)
						{
							for(i in $scope.getcompany)
							{
								if($scope.getcompany[i].companyDetailId == $scope.companydetailid)
								{
									$scope.companyname = $scope.getcompany[i].companyName;
									$scope.businessnature = $scope.getcompany[i].companyBusinessNature;
									$scope.faxnumber = $scope.getcompany[i].companyFaxNumber;
									$scope.website = $scope.getcompany[i].companyWebsiteName;
									$scope.email = $scope.getcompany[i].contactPersonEmail;
									$scope.address1work = $scope.getcompany[i].localAddress1;
									$scope.address2work = $scope.getcompany[i].localAddress2;
									$scope.address3work = $scope.getcompany[i].localAddress3;
									$scope.statenamework = $scope.getcompany[i].localStateId;
									$scope.citynamework = $scope.getcompany[i].localCityName;
									$scope.pincodework = $scope.getcompany[i].localPincode;
									$scope.mobilenumberwork = $scope.getcompany[i].localMobileNumber;
									$scope.phonenumberwork = $scope.getcompany[i].localPhoneNumber;
								}
							}
						}
					}).
					error(function(data, status, headers, config)
					{
						$scope.getmember = "Response Fail";
					});	
		}
		
		$scope.residentiallandline = [{}];
		
		$scope.addResidentialLandlineRow = function()
		{
			if($scope.phonenumber == "" || $scope.phonenumber == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumber").focus();
				return;
			}
			else
			{
				$scope.residentiallandline.push({'landlinePhoneNumber':$scope.phonenumber});
			}
		};
		
		$scope.removeResidentialLandlineRow = function(landlinephonenumber)
		{				
			var index = -1;		
			var comArr = eval( $scope.residentiallandline);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].landlinePhoneNumber === landlinephonenumber ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.residentiallandline.splice( index, 1 );
		};
		
		$scope.worklandline = [{}];
		
		$scope.addWorkLandlineRow = function()
		{
			var occupation = $scope.occupation;
			var designation = $scope.designation;
			var companyname = $scope.companyname;
			var businessnature = $scope.businessnature;
			var address1work = $scope.address1work;
			var address2work = $scope.address2work;
			var address3work = $scope.address3work;
			var countrynamework = $scope.countrynamework;
			var statenamework = $scope.statenamework;
			var citynamework = $scope.citynamework;
			var pincodework = $scope.pincodework;
			var email = $scope.email;
			var mobilenumberwork = $scope.mobilenumberwork;	
			
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
			if(email == "" || email == undefined ) {
				email = "";
			}
			if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumberwork").focus();
				return;
			}
			else
			{
				$scope.getmemberworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork,'memberCompanyName':companyname,'memberDesignation':designation,'memberComapnyAddress1':address1work,'memberComapnyAddress2':address2work,'memberComapnyAddress3':address3work,'memberCompanyCity':citynamework,'memberComapnyMobileNumber':mobilenumberwork,'memberComapnyEmail':email});
				if($scope.getmemberworklandline.length == 3){
					$scope.disableAddButton = 1;
				}
			}
		};
		
		$scope.removeWorkLandlineRow = function(memberCompanyName)
		{				
			var index = -1;		
			var comArr = eval( $scope.getmemberworklandline);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].memberCompanyName === memberCompanyName ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.worklandline.splice( index, 1 );
		};
		
		$scope.addcontactdetail = function(membercategoryid, tenureplan)
	    {
			console.log("addcontactdetail initiated");
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
			
			if($scope.getLastSequence == undefined)
				$scope.getLastSequence = 0;
			
			var address1 = $scope.address1;
			var address2 = $scope.address2;
			var address3 = $scope.address3;
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;
			var pincode = $scope.pincode;
			var mobilenumber = $scope.mobilenumber;			
			var occupation = $scope.occupation;
			var designation = $scope.designation;
			var companyname = $scope.companyname;
			var businessnature = $scope.businessnature;
			var faxnumber = $scope.faxnumber;
			var website = $scope.website;
			var memberemail = $scope.memberemail;
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
			
			var createddate = $filter('date')(new Date(), 'yyyy/MM/dd');
			
			
			if(address2 == "" || address2 == undefined)
			{
				address2 = "";
			}
			if(address3 == "" || address3 == undefined)
			{
				address3 = "";
			}
			if(pincode == "" || pincode == undefined)
			{
				pincode = "";
			}			
			if(occupation == "" || occupation == undefined )
			{
				occupation = "";
			}
			if(designation == "" || designation == undefined )
			{
				designation = "";
			}
			if(companyname == "" || companyname == undefined )
			{
				companyname = "";
			}
			if(businessnature == "" || businessnature == undefined )
			{
				businessnature = "";
			}
			if(faxnumber == "" || faxnumber == undefined )
			{
				faxnumber = "";
			}
			if(website == "" || website == undefined )
			{
				website = "";
			}
			if($scope.aboutbusiness == undefined ) {
				$scope.aboutbusiness = "";
			}
			if($scope.businesskeywords == undefined ) {
				$scope.businesskeywords = "";
			}
			if(email == "" || email == undefined )
			{
				email = "";
			}
			if(address1work == "" || address1work == undefined )
			{
				address1work = "";
			}
			if(address2work == "" || address2work == undefined )
			{
				address2work = "";
			}
			if(address3work == "" || address3work == undefined )
			{
				address3work = "";
			}
			if(countrynamework == "" || countrynamework == undefined )
			{
				countrynamework = 0;
			}
			if(statenamework == "" || statenamework == undefined )
			{
				statenamework = 0;
			}
			if(citynamework == "" || citynamework == undefined )
			{
				citynamework = "";
			}
			if(pincodework == "" || pincodework == undefined )
			{
				pincodework = "";
			}
			if(mobilenumberwork == "" || mobilenumberwork == undefined )
			{
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
			
			if(address1 == "" || address1 == undefined)
			{
				$window.alert("Please enter address line-1");
				document.getElementById("address1").focus();
				return;
			}
			else if(countryname == "" || countryname == undefined)
			{
				$window.alert("Please select country");
				document.getElementById("countryname").focus();
				return;
			}
			else if((statename == "" || statename == undefined) && countryname == 1)
			{
				$window.alert("Please select state");
				document.getElementById("statename").focus();
				return;
			}
			else if(cityname == "" || cityname == undefined)
			{
				$window.alert("Please enter city name");
				document.getElementById("cityname").focus();
				return;
			}			
			else if(mobilenumber == "" || mobilenumber == undefined)
			{
				$window.alert("Please enter mobile number");
				document.getElementById("mobilenumber").focus();
				return;
			}
			else if(mobilenumber.length != 10)
			{
				$window.alert("Mobile number should be 10 digit");
				document.getElementById("mobilenumber").focus();
				return;
			}			
			else if(communication == "" || communication == undefined )
			{
				$window.alert("Please select address for communication");
				document.getElementById("communication").focus();
				return;
			}
			else
			{
				if(statename == "" || statename == undefined)
				{
					statename = 0;
				}
				console.log("addcontactdetail Sending request");
				var link = baseUrl+'addContactDetail?address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&memberemail='+memberemail+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&aboutbusiness='+$scope.aboutbusiness+'&businesskeywords='+$scope.businesskeywords+'&email='+email+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communicationaddress='+communication+'&businessgoals='+$scope.businessgoals+'&accomplishments='+$scope.accomplishments+'&interests='+$scope.interests+'&networks='+$scope.networks+'&skills='+$scope.skills+'&idealreferral='+$scope.idealreferral+'&topproduct='+$scope.topproduct+'&topproblemsolved='+$scope.topproblemsolved+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;								
				var formData=new FormData();				
				formData.append("companylogo",imgInp.files[0]);				
				$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
				}).success(function(data, status) {
					console.log("addcontactdetail Getting Response");
					$scope.addcontactdetail = data;
					if($scope.residentiallandline.length == 1 && $scope.worklandline.length == 1) {
						$window.alert("Data added successfully");
						$window.location.href = adminurl+"family_detail";
					}
					var a = 0, b = 0, c = 0;
					angular.forEach($scope.residentiallandline, function(item) {
						if(item.landlinePhoneNumber) {
							var link = baseUrl+'addMemberLandlinePhoneNumber?landlinephonenumber='+item.landlinePhoneNumber+'&location='+'R';
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addlandlinenumber = data;
								a = a + 1;
								c = c + 1;
								if(($scope.worklandline.length == 1 && a+1 == $scope.residentiallandline.length) || (c+2 == $scope.residentiallandline.length + $scope.worklandline.length)) {
									$window.alert("Data added successfully");
									$window.location.href = adminurl+"family_detail";
								}
							}).error(function(data, status, headers, config) {
								$scope.addlandlinenumber = "Response Fail";
							});
						}
					});
					angular.forEach($scope.worklandline, function(item) {
						if(item.landlinePhoneNumber) {// yuvi memm
							//var link = baseUrl+'addMemberLandlinePhoneNumber?landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W';
							var link = baseUrl+'addMemberWorkDetails?landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W'+'&memberCompanyName='+item.memberCompanyName+'&memberDesignation='+item.memberDesignation+'&memberComapnyAddress1='+item.memberComapnyAddress1+'&memberComapnyAddress2='+item.memberComapnyAddress2+'&memberComapnyAddress3='+item.memberComapnyAddress3+'&memberCompanyCity='+item.memberCompanyCity+'&memberComapnyMobileNumber='+item.memberComapnyMobileNumber+'&memberComapnyEmail='+item.memberComapnyEmail;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addlandlinenumber = data;
								b = b + 1;
								c = c + 1;
								if(($scope.residentiallandline.length == 1 && b+1 == $scope.worklandline.length) || (c+2 == $scope.residentiallandline.length + $scope.worklandline.length)) {
									$window.alert("Data added successfully");
									$window.location.href = adminurl+"family_detail";
								}
							}).error(function(data, status, headers, config) {
								$scope.addlandlinenumber = "Response Fail";
							});
						}
					});
				}).error(function(data, status, headers, config) {
					$scope.addcontactdetail = "Response Fail";
				});
			}
		}
		
		$scope.getspousedetail = function(memberid)
		{				
			var link = baseUrl+'getspousesequence?memberid='+memberid;
			$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getspousesequence = data;
						
					}).error(function(data,status,headers,config)
					{
						$scope.getspousesequence = "Response Fail";
					});
			
			var link = baseUrl+'getspousedata?memberid='+memberid;
			$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getspousedata = data;
						
					}).error(function(data,status,headers,config)
					{
						$scope.getspousedata = "Response Fail";
					});
			
			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getmember = data;
						
						for(i in $scope.getmember)
						{
							if($scope.getmember[i].memberId == memberid)
							{
								$scope.memberid = $scope.getmember[i].memberId;
								$scope.membershipnumber = $scope.getmember[i].membershipNumber;								
								$scope.membercategoryid = $scope.getmember[i].memberCategoryId;
								$scope.tenureplan = $scope.getmember[i].tenurePlan;								
								$scope.memberfirstname = $scope.getmember[i].memberFirstName;
								$scope.membermiddlename = $scope.getmember[i].memberMiddleName;
								$scope.memberlastname = $scope.getmember[i].memberLastName;
							}
						}
					}).
					error(function(data, status, headers, config)
					{
						$scope.getmember = "Response Fail";
					});	
			
		}
		
		$scope.numberofspouse = 0;
		$scope.spousememberid = function(id, memberid)
		{
			if(id == 'null')
			{
				var link = baseUrl+'getMember';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getmember = data;
							
							for(i in $scope.getmember)
							{
								if($scope.getmember[i].memberId == memberid)
								{
									id = $scope.getmember[i].membershipNumber;
								}
							}
							
							if($scope.getspousesequence.length == 0)
							{
								$scope.numberofspouse = 0;
							}
							else
							{
								for(i in $scope.getspousesequence)
								{
									$scope.numberofspouse = $scope.getspousesequence[i].sequence;
								}
							}
							
							$scope.numberofspouse = $scope.numberofspouse+1;
							$scope.spouseid = id + "F0" + $scope.numberofspouse;
							
							
						}).
						error(function(data, status, headers, config)
						{
							$scope.getmember = "Response Fail";
						});	
			}
			else
			{
				if($scope.getspousesequence.length == 0)
				{
					$scope.numberofspouse = 0;
				}
				else
				{
					for(i in $scope.getspousesequence)
					{
						$scope.numberofspouse = $scope.getspousesequence[i].sequence;
					}
				}
				
				$scope.numberofspouse = $scope.numberofspouse+1;
				$scope.spouseid = id + "F0" + $scope.numberofspouse;
			}	
		}
		
		$scope.residentialaddresssameasmember = function(memberid)
		{				
			if(!$scope.residentialaddress)
			{
				var link = baseUrl+'getMemberResidentialLandline?memberid='+memberid;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.familyresidentiallandline = data;
							$scope.getfamilyresidentiallandline = $scope.familyresidentiallandline;
						}).
						error(function(data, status, headers, config)
						{
							$scope.familyresidentiallandline = "Response Fail";
						});
				
				var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getmember = data;
							
							for(i in $scope.getmember)
							{
								if($scope.getmember[i].memberId == memberid)
								{
									$scope.memberid = $scope.getmember[i].memberId;
									$scope.address1 = $scope.getmember[i].memberAddress1;
									$scope.address2 = $scope.getmember[i].memberAddress2;
									$scope.address3 = $scope.getmember[i].memberAddress3;
									$scope.statename = $scope.getmember[i].memberStateId;
									$scope.cityname = $scope.getmember[i].memberCityName;
									$scope.pincode = $scope.getmember[i].memberPincode;
									$scope.mobilenumber = $scope.getmember[i].memberMobileNumber;
									
									$scope.address1edit = $scope.getmember[i].memberAddress1;
									$scope.address2edit = $scope.getmember[i].memberAddress2;
									$scope.address3edit = $scope.getmember[i].memberAddress3;
									$scope.statenameedit = $scope.getmember[i].memberStateId;
									$scope.citynameedit = $scope.getmember[i].memberCityName;
									$scope.pincodeedit = $scope.getmember[i].memberPincode;
									$scope.mobilenumberedit = $scope.getmember[i].memberMobileNumber;
								}
							}
							
							var link = baseUrl+'getState';
							$http.get(link).success(					
									function(data, status, headers, config)
									{
										$scope.getrelatedstate = data;
										
										for(i in $scope.getrelatedstate)
										{
											if($scope.getrelatedstate[i].stateId == $scope.statename)
											{
												$scope.countryname = $scope.getrelatedstate[i].countryId;												
											}
										}
									}).
									error(function(data, status, headers, config)
									{
										$scope.getrelatedstate = "Response Fail";
									});
							
						}).
						error(function(data, status, headers, config)
						{
							$scope.getmember = "Response Fail";
						});
			}
			else
			{
				
				$scope.address1 = "";
				$scope.address2 = "";
				$scope.address3 = "";
				$scope.countryname = "";
				$scope.statename = "";
				$scope.cityname = "";
				$scope.pincode = "";
				$scope.mobilenumber = "";
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
		
		$scope.workdetailsameasmember = function(memberid)
		{	
			if(!$scope.workdetail)
			{
				var link = baseUrl+'getMemberWorkLandline?memberid='+memberid;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.familyworklandline = data;
							$scope.getfamilyworklandline = $scope.familyworklandline;
						}).
						error(function(data, status, headers, config)
						{
							$scope.familyworklandline = "Response Fail";
						});
				
				var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getmember = data;
							
							for(i in $scope.getmember)
							{
								if($scope.getmember[i].memberId == memberid)
								{
									$scope.memberid = $scope.getmember[i].memberId;
									$scope.occupation = $scope.getmember[i].memberOccupation;
									$scope.designation = $scope.getmember[i].memberDesignation;
									$scope.companyname = $scope.getmember[i].memberCompanyName;
									$scope.businessnature = $scope.getmember[i].memberBusinessNature;
									$scope.faxnumber = $scope.getmember[i].memberFaxNumber;
									$scope.website = $scope.getmember[i].memberWebsiteName;
									$scope.email = $scope.getmember[i].memberCompanyEmail;
									$scope.address1work = $scope.getmember[i].memberCompanyAddress1;
									$scope.address2work = $scope.getmember[i].memberCompanyAddress2;
									$scope.address3work = $scope.getmember[i].memberCompanyAddress3;
									$scope.statenamework = $scope.getmember[i].memberCompanyStateId;
									$scope.citynamework = $scope.getmember[i].memberCompanyCityName;
									$scope.pincodework = $scope.getmember[i].memberCompanyPincode;
									$scope.mobilenumberwork = $scope.getmember[i].memberCompanyMobileNumber;
									$scope.phonenumberwork = $scope.getmember[i].memberCompanyPhoneNumber;
									$scope.communication = $scope.getmember[i].memberCommunicationAddress;
									
									$scope.occupationedit = $scope.getmember[i].memberOccupation;
									$scope.designationedit = $scope.getmember[i].memberDesignation;
									$scope.companynameedit = $scope.getmember[i].memberCompanyName;
									$scope.businessnatureedit = $scope.getmember[i].memberBusinessNature;
									$scope.faxnumberedit = $scope.getmember[i].memberFaxNumber;
									$scope.websiteedit = $scope.getmember[i].memberWebsiteName;
									$scope.emailedit = $scope.getmember[i].memberCompanyEmail;
									$scope.address1workedit = $scope.getmember[i].memberCompanyAddress1;
									$scope.address2workedit = $scope.getmember[i].memberCompanyAddress2;
									$scope.address3workedit = $scope.getmember[i].memberCompanyAddress3;
									$scope.statenameworkedit = $scope.getmember[i].memberCompanyStateId;
									$scope.citynameworkedit = $scope.getmember[i].memberCompanyCityName;
									$scope.pincodeworkedit = $scope.getmember[i].memberCompanyPincode;
									$scope.mobilenumberworkedit = $scope.getmember[i].memberCompanyMobileNumber;
									$scope.phonenumberworkedit = $scope.getmember[i].memberCompanyPhoneNumber;
									$scope.communicationedit = $scope.getmember[i].memberCommunicationAddress;
								}
							}
							
							var link = baseUrl+'getState';
							$http.get(link).success(					
									function(data, status, headers, config)
									{
										$scope.getrelatedstate = data;
										
										for(i in $scope.getrelatedstate)
										{
											if($scope.getrelatedstate[i].stateId == $scope.statenamework)
											{
												$scope.countrynamework = $scope.getrelatedstate[i].countryId;
											}
										}
									}).
									error(function(data, status, headers, config)
									{
										$scope.getrelatedstate = "Response Fail";
									});
							
						}).
						error(function(data, status, headers, config)
						{
							$scope.getmember = "Response Fail";
						});
			}
			else
			{
				
				$scope.occupation = "";
				$scope.designation = "";
				$scope.companyname = "";
				$scope.businessnature = "";
				$scope.faxnumber = "";
				$scope.website = "";				
				$scope.address1work = "";
				$scope.address2work = "";
				$scope.address3work = "";
				$scope.statenamework = "";
				$scope.citynamework = "";
				$scope.pincodework = "";
				$scope.mobilenumberwork = "";
				$scope.phonenumberwork = "";
				$scope.communication = "";
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
		for(var i = 1; i<=31; i++)
		{
			$scope.date.push(i);
		}
		
		$scope.familyresidentiallandline = [{}];
		
		$scope.addFamilyResidentialLandlineRow = function()
		{
			if($scope.phonenumber == "" || $scope.phonenumber == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumber").focus();
				return;
			}
			else
			{
				$scope.familyresidentiallandline.push({'landlinePhoneNumber':$scope.phonenumber});
			}
		};
		
		$scope.removeFamilyResidentialLandlineRow = function(landlinephonenumber)
		{				
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
		
		$scope.addFamilyWorkLandlineRow = function()
		{
			if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumberwork").focus();
				return;
			}
			else
			{
				$scope.familyworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork});
			}
		};
		
		$scope.removeFamilyWorkLandlineRow = function(landlinephonenumber)
		{				
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
		
		$scope.addspousedetail = function(temp) {
			
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
			var firstname = $scope.firstname;
			var middlename = $scope.middlename;
			var lastname = $scope.lastname;
			var gender = $scope.gender;
			var birthdate = $scope.birthdate;
			var birthmonth = $scope.birthmonth;
			var birthyear = $scope.birthyear;			
			var bloodgroup = $scope.bloodgroup;			
			var aadharnumber = $scope.aadharnumber;
			var passportnumber = $scope.passportnumber;
			var pannumber = $scope.pannumber;
			var email = $scope.email;
			var password = $scope.password;
			var address1 = $scope.address1;
			var address2 = $scope.address2;
			var address3 = $scope.address3;
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;
			var pincode = $scope.pincode;
			var mobilenumber = $scope.mobilenumber;			
			var occupation = $scope.occupation;
			var designation = $scope.designation;
			var companyname = $scope.companyname;
			var businessnature = $scope.businessnature;
			var faxnumber = $scope.faxnumber;
			var website = $scope.website;
			var emailwork = $scope.emailwork;
			var address1work = $scope.address1work;
			var address2work = $scope.address2work;
			var address3work = $scope.address3work;
			var countrynamework = $scope.countrynamework;
			var statenamework = $scope.statenamework;
			var citynamework = $scope.citynamework;
			var pincodework = $scope.pincodework;
			var mobilenumberwork = $scope.mobilenumberwork;			
			var communication = $scope.communication;						
			
			if(membercategoryid == "" || membercategoryid == undefined )
			{
				membercategoryid = 0;
			}			
			if(memberfamilytitle == "" || memberfamilytitle == undefined )
			{
				memberfamilytitle = "";
			}
			if(middlename == "" || middlename == undefined )
			{
				middlename = "";
			}
			if(birthdate == "" || birthdate == undefined)
			{
				birthdate = "";
			}
			if(birthmonth == "" || birthmonth == undefined)
			{
				birthmonth = "";
			}
			if(birthyear == "" || birthyear == undefined)
			{
				birthyear = "0001";
			}						
			if(bloodgroup == "" || bloodgroup == undefined)
			{
				bloodgroup = "";
			}
			if(aadharnumber == "" || aadharnumber == undefined)
			{
				aadharnumber = "";
			}
			if(passportnumber == "" || passportnumber == undefined)
			{
				passportnumber = "";
			}
			if(pannumber == "" || pannumber == undefined)
			{
				pannumber = "";
			}
			if(address2 == "" || address2 == undefined)
			{
				address2 = "";
			}
			if(address3 == "" || address3 == undefined)
			{
				address3 = "";
			}
			if(pincode == "" || pincode == undefined)
			{
				pincode = "";
			}
			if(mobilenumber == "" || mobilenumber == undefined)
			{
				mobilenumber = "";
			}		
			if(occupation == "" || occupation == undefined )
			{
				occupation = "";
			}
			if(designation == "" || designation == undefined )
			{
				designation = "";
			}
			if(companyname == "" || companyname == undefined )
			{
				companyname = "";
			}
			if(businessnature == "" || businessnature == undefined )
			{
				businessnature = "";
			}
			if(faxnumber == "" || faxnumber == undefined )
			{
				faxnumber = "";
			}
			if(website == "" || website == undefined )
			{
				website = "";
			}
			if(emailwork == "" || emailwork == undefined )
			{
				emailwork = "";
			}
			if(address1work == "" || address1work == undefined )
			{
				address1work = "";
			}
			if(address2work == "" || address2work == undefined )
			{
				address2work = "";
			}
			if(address3work == "" || address3work == undefined )
			{
				address3work = "";
			}
			if(countrynamework == "" || countrynamework == undefined )
			{
				countrynamework = 0;
			}
			if(statenamework == "" || statenamework == undefined )
			{
				statenamework = 0;
			}
			if(citynamework == "" || citynamework == undefined )
			{
				citynamework = "";
			}				
			if(pincodework == "" || pincodework == undefined )
			{
				pincodework = "";
			}
			if(mobilenumberwork == "" || mobilenumberwork == undefined )
			{
				mobilenumberwork = "";
			}			
			
			if(relation == "" || relation == undefined)
			{
				$window.alert("Please select relation");
				document.getElementById("relation").focus();
				return;
			}
			else if(firstname == "" || firstname == undefined)
			{
				$window.alert("Please enter first name");
				document.getElementById("firstname").focus();
				return;
			}
			else if(lastname == "" || lastname == undefined)
			{
				$window.alert("Please enter last name");
				document.getElementById("lastname").focus();
				return;
			}
			else if(gender == "" || gender == undefined)
			{
				$window.alert("Please select gender");
				document.getElementById("gender").focus();
				return;
			}
			else if((birthdate != "" || birthmonth != "") && birthyear == "")
			{
				$window.alert("Please enter the value of birthyear");
				document.getElementById("birthyear").focus();
				return;
			}
			else if(email == "" || email == undefined)
			{
				$window.alert("Please enter email id");
				document.getElementById("email").focus();
				return;
			}
			else if(password == "" || password == undefined)
			{
				$window.alert("Please enter password");
				document.getElementById("password").focus();
				return;
			}
			else if(address1 == "" || address1 == undefined)
			{
				$window.alert("Please enter address line-1");
				document.getElementById("address1").focus();
				return;
			}
			else if(countryname == "" || countryname == undefined)
			{
				$window.alert("Please select country");
				document.getElementById("countryname").focus();
				return;
			}
			else if((statename == "" || statename == undefined) && countryname == 1)
			{
				$window.alert("Please select state");
				document.getElementById("statename").focus();
				return;
			}
			else if(cityname == "" || cityname == undefined)
			{
				$window.alert("Please enter city name");
				document.getElementById("cityname").focus();
				return;
			}			
			else if(communication == "" || communication == undefined )
			{
				$window.alert("Please select address for communication");
				document.getElementById("communication").focus();
				return;
			}
			else
			{
				if(statename == "" || statename == undefined)
				{
					statename = 0;
				}			
				var birth = birthyear+"-"+birthmonth+"-"+birthdate;
							
				var bg = encodeURIComponent(bloodgroup);				
				var link = baseUrl +'addSpouseDetail?membershipno='+spouseid+'&relation='+relation+'&membercategoryid='+membercategoryid+'&memberfamilytitle='+memberfamilytitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+birth+'&bloodgroup='+bg+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&email='+email+'&password='+password+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&emailwork='+emailwork+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communication='+communication+'&sequence='+sequence+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;			
				
				var formData = new FormData();
				formData.append("profile",imgInp.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) {
					return data;
				}
				}).success(function(data, status) {
					$scope.addspousedetail = data;
					var a = 1, b = 1, c = 1, d = 1;
					if($scope.education.length == 1 && ($scope.familyresidentiallandline.length == 1 || $scope.residentialaddress == true && $scope.familyresidentiallandline.length == 0) && ($scope.familyworklandline.length == 1 || $scope.workdetail == true)) {
						$window.alert("Family Detail Added Successfully");
						if(temp == 2) {
							$window.location.href = url+"my_family_detail";
						} else {
							$window.location.href = adminurl+"family_detail";
						}
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
									if(temp == 2) {
										$window.location.href = url+"my_family_detail";
									} else {
										$window.location.href = adminurl+"family_detail";
									}
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
									if(temp == 2) {
										$window.location.href = url+"my_family_detail";
									} else {
										$window.location.href = adminurl+"family_detail";
									}
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
									if(temp == 2) {
										$window.location.href = url+"my_family_detail";
									} else {
										$window.location.href = adminurl+"family_detail";
									}
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
		
		$scope.getspouse = function(membersfamilyid, id, memberid)
		{		
			var link = baseUrl+'getFamilyEducationDetail?membersfamilyid='+membersfamilyid;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getfamilyeducationdetail = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getfamilyeducationdetail = "Response Fail";
					});
			
			var link = baseUrl+'getFamilyResidentialLandline?membersfamilyid='+membersfamilyid;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getfamilyresidentiallandline = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getfamilyresidentiallandline = "Response Fail";
					});
			
			var link = baseUrl+'getFamilyWorkLandline?membersfamilyid='+membersfamilyid;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getfamilyworklandline = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getfamilyworklandline = "Response Fail";
					});
			
			
			for(i in $scope.getspousedata)
			{
				if($scope.getspousedata[i].membersFamilyId == membersfamilyid)
				{
					$scope.membersfamilyid = $scope.getspousedata[i].membersFamilyId;
					$scope.spouseidedit = $scope.getspousedata[i].membershipNumber;					
					$scope.relationedit = $scope.getspousedata[i].memberFamilyTypeOfRelation;
					$scope.membercategoryedit = $scope.getspousedata[i].memberCategoryId;
					$scope.memberfamilytitleedit = $scope.getspousedata[i].memberFamilyTitleName;
					$scope.firstnameedit = $scope.getspousedata[i].memberFamilyFirstName;
					$scope.middlenameedit = $scope.getspousedata[i].memberFamilyMiddleName;
					$scope.lastnameedit = $scope.getspousedata[i].memberFamilyLastName;
					$scope.genderedit = $scope.getspousedata[i].memberFamilyGender;
					$scope.birthedit = $scope.getspousedata[i].memberFamilyDateOfBirth;

					if($scope.birthedit == null)
					{
						$scope.birthdateedit = "";
						$scope.birthmonthedit = "";
						$scope.birthyearedit = "";
					}
					else
					{
						var a = $scope.birthedit.split("-");
						$scope.birthdateedit = a[2];
						$scope.birthmonthedit = a[1];
						$scope.birthyearedit = a[0];
					}			
					
					$scope.bloodgroupedit = $scope.getspousedata[i].memberFamilyBloodGroup;					
					$scope.aadharnumberedit = $scope.getspousedata[i].memberFamilyAadharNumber;
					$scope.passportnumberedit = $scope.getspousedata[i].memberFamilyPassportNumber;
					$scope.pannumberedit = $scope.getspousedata[i].memberFamilyPanNumber;
					$scope.profileedit = $scope.getspousedata[i].memberFamilyProfilePicture;
					$scope.emailedit = $scope.getspousedata[i].memberFamilyEmail;
					$scope.emailedit1 = $scope.getspousedata[i].memberFamilyEmail;
					$scope.passwordedit = $scope.getspousedata[i].memberFamilyPassword;
					$scope.address1edit = $scope.getspousedata[i].memberFamilyAddress1;
					$scope.address2edit = $scope.getspousedata[i].memberFamilyAddress2;
					$scope.address3edit = $scope.getspousedata[i].memberFamilyAddress3;
					$scope.statenameedit = $scope.getspousedata[i].memberFamilyStateId;
					$scope.citynameedit = $scope.getspousedata[i].memberFamilyCityName;
					$scope.pincodeedit = $scope.getspousedata[i].memberFamilyPincode;
					$scope.mobilenumberedit = $scope.getspousedata[i].memberFamilyMobileNumber;
					$scope.phonenumberedit = $scope.getspousedata[i].memberFamilyPhoneNumber;
					$scope.occupationedit = $scope.getspousedata[i].memberFamilyOccupation;
					$scope.designationedit = $scope.getspousedata[i].memberFamilyDesignation;
					$scope.companynameedit = $scope.getspousedata[i].memberFamilyCompanyName;
					$scope.businessnatureedit = $scope.getspousedata[i].memberFamilyBusinessNature;
					$scope.faxnumberedit = $scope.getspousedata[i].memberFamilyFaxNumber;
					$scope.websiteedit = $scope.getspousedata[i].memberFamilyWebsiteName;
					$scope.emailworkedit = $scope.getspousedata[i].memberFamilyCompanyEmail;
					$scope.address1workedit = $scope.getspousedata[i].memberFamilyCompanyAddress1;
					$scope.address2workedit = $scope.getspousedata[i].memberFamilyCompanyAddress2;
					$scope.address3workedit = $scope.getspousedata[i].memberFamilyCompanyAddress3;
					$scope.statenameworkedit = $scope.getspousedata[i].memberFamilyCompanyStateId;
					$scope.citynameworkedit = $scope.getspousedata[i].memberFamilyCompanyCityName;
					$scope.pincodeworkedit = $scope.getspousedata[i].memberFamilyCompanyPincode;
					$scope.mobilenumberworkedit = $scope.getspousedata[i].memberFamilyCompanyMobileNumber;
					$scope.phonenumberworkedit = $scope.getspousedata[i].memberFamilyCompanyPhoneNumber;
					$scope.communicationedit = $scope.getspousedata[i].memberFamilyCommunicationAddress;
				}
			}			
		}
		
		$scope.getfamilybarcode = function(membersfamilyid)
		{
			for(i in $scope.getspousedata)
			{
				if($scope.getspousedata[i].membersFamilyId == membersfamilyid)
				{
					$scope.memberfamilybarcode = $scope.getspousedata[i].memberFamilyBarcode;
				}
			}
		}
		
		$scope.getfamilyqrcode = function(membersfamilyid)
		{
			for(i in $scope.getspousedata)
			{
				if($scope.getspousedata[i].membersFamilyId == membersfamilyid)
				{
					$scope.memberfamilyqrcode = $scope.getspousedata[i].memberFamilyQrcode;
				}
			}
		}		
		
		$scope.addEducationRow1 = function()
		{		
			$scope.getfamilyeducationdetail.push({'degreeName':$scope.degreenameedit, 'passingYear': $scope.passingyearedit, 'grade': $scope.gradeedit, 'instituteName': $scope.institutenameedit});
		};
		
		$scope.removeEducationRow1 = function(degreename)
		{				
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
		
		
		$scope.addFamilyResidentialLandlineRow1 = function()
		{
			if($scope.phonenumberedit == "" || $scope.phonenumberedit == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumberedit").focus();
				return;
			}
			else
			{
				$scope.getfamilyresidentiallandline.push({'landlinePhoneNumber':$scope.phonenumberedit});
			}
		};
		
		$scope.removeFamilyResidentialLandlineRow1 = function(landlinephonenumber)
		{				
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
		
		$scope.addFamilyWorkLandlineRow1 = function()
		{
			if($scope.phonenumberworkedit == "" || $scope.phonenumberworkedit == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumberworkedit").focus();
				return;
			}
			else
			{
				$scope.getfamilyworklandline.push({'landlinePhoneNumber':$scope.phonenumberworkedit});
			}
		};
		
		$scope.removeFamilyWorkLandlineRow1 = function(landlinephonenumber)
		{				
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
		
		$scope.editspousedetail = function(membersfamilyid, temp)
		{
			var valuex = document.getElementById("valuex1").value;
			var valuey = document.getElementById("valuey1").value;
			var valuew = document.getElementById("valuew1").value;
			var valueh = document.getElementById("valueh1").value;
			
						
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
			
			if(membercategoryid == "" || membercategoryid == undefined )
			{
				membercategoryid = 0;
			}
			if(memberfamilytitle == "" || memberfamilytitle == undefined )
			{
				memberfamilytitle = "";
			}
			if(middlename == "" || middlename == undefined )
			{
				middlename = "";
			}
			if(birthdate == "" || birthdate == undefined)
			{
				birthdate = "";
			}
			if(birthmonth == "" || birthmonth == undefined)
			{
				birthmonth = "";
			}
			if(birthyear == "" || birthyear == undefined)
			{
				birthyear = "";
			}		
			if(bloodgroup == "" || bloodgroup == undefined)
			{
				bloodgroup = "";
			}			
			if(aadharnumber == "" || aadharnumber == undefined)
			{
				aadharnumber = "";
			}
			if(passportnumber == "" || passportnumber == undefined)
			{
				passportnumber = "";
			}
			if(pannumber == "" || pannumber == undefined)
			{
				pannumber = "";
			}
			if(address2 == "" || address2 == undefined)
			{
				address2 = "";
			}
			if(address3 == "" || address3 == undefined)
			{
				address3 = "";
			}
			if(pincode == "" || pincode == undefined)
			{
				pincode = "";
			}
			if(mobilenumber == "" || mobilenumber == undefined)
			{
				mobilenumber = "";
			}						
			if(occupation == "" || occupation == undefined )
			{
				occupation = "";
			}
			if(designation == "" || designation == undefined )
			{
				designation = "";
			}
			if(companyname == "" || companyname == undefined )
			{
				companyname = "";
			}
			if(businessnature == "" || businessnature == undefined )
			{
				businessnature = "";
			}
			if(faxnumber == "" || faxnumber == undefined )
			{
				faxnumber = "";
			}
			if(website == "" || website == undefined )
			{
				website = "";
			}
			if(emailwork == "" || emailwork == undefined )
			{
				emailwork = "";
			}
			if(address1work == "" || address1work == undefined )
			{
				address1work = "";
			}
			if(address2work == "" || address2work == undefined )
			{
				address2work = "";
			}
			if(address3work == "" || address3work == undefined )
			{
				address3work = "";
			}
			if(countrynamework == "" || countrynamework == undefined )
			{
				countrynamework = 0;
			}
			if(statenamework == "" || statenamework == undefined )
			{
				statenamework = 0;
			}
			if(citynamework == "" || citynamework == undefined )
			{
				citynamework = "";
			}				
			if(pincodework == "" || pincodework == undefined )
			{
				pincodework = "";
			}
			if(mobilenumberwork == "" || mobilenumberwork == undefined )
			{
				mobilenumberwork = "";
			}					
			if(relation == "" || relation == undefined)
			{
				$window.alert("Please select relation");
				document.getElementById("relationedit").focus();
				return;
			}
			else if(firstname == "" || firstname == undefined)
			{
				$window.alert("Please enter first name");
				document.getElementById("firstnameedit").focus();
				return;
			}
			else if(lastname == "" || lastname == undefined)
			{
				$window.alert("Please enter last name");
				document.getElementById("lastnameedit").focus();
				return;
			}
			else if(gender == "" || gender == undefined)
			{
				$window.alert("Please select gender");
				document.getElementById("genderedit").focus();
				return;
			}
			else if(birthyear == undefined)
			{
				$window.alert("Please enter the value of birthyear");
				document.getElementById("birthyear").focus();
				return;
			}
			else if(email == "" || email == undefined)
			{
				$window.alert("Please enter email id");
				document.getElementById("emailedit").focus();
				return;
			}
			else if(password == "" || password == undefined)
			{
				$window.alert("Please enter password");
				document.getElementById("passwordedit").focus();
				return;
			}
			if(address1 == "" || address1 == undefined)
			{
				$window.alert("Please enter address line-1");
				document.getElementById("address1edit").focus();
				return;
			}
			else if(countryname == "" || countryname == undefined)
			{
				$window.alert("Please select country");
				document.getElementById("countrynameedit").focus();
				return;
			}
			else if((statename == "" || statename == undefined) && countryname == 1)
			{
				$window.alert("Please select state");
				document.getElementById("statenameedit").focus();
				return;
			}
			else if(cityname == "" || cityname == undefined)
			{
				$window.alert("Please enter city name");
				document.getElementById("citynameedit").focus();
				return;
			}			
			else if(communication == "" || communication == undefined )
			{
				$window.alert("Please select address for communication");
				document.getElementById("communicationedit").focus();
				return;
			}
			else
			{
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
							if(temp == 2) {
								$window.location.href = url+"my_family_detail";
							} else {
								$window.location.href = adminurl+"family_detail";
							}
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
										if(temp == 2) {
											$window.location.href = url+"my_family_detail";
										} else {
											$window.location.href = adminurl+"family_detail";
										}
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
										if(temp == 2) {
											$window.location.href = url+"my_family_detail";
										} else {
											$window.location.href = adminurl+"family_detail";
										}
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
										if(temp == 2) {
											$window.location.href = url+"my_family_detail";
										} else {
											$window.location.href = adminurl+"family_detail";
										}
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
							if(temp == 2) {
								$window.location.href = url+"my_family_detail";
							} else {
								$window.location.href = adminurl+"family_detail";
							}
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
										if(temp == 2) {
											$window.location.href = url+"my_family_detail";
										} else {
											$window.location.href = adminurl+"family_detail";
										}
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
										if(temp == 2) {
											$window.location.href = url+"my_family_detail";
										} else {
											$window.location.href = adminurl+"family_detail";
										}
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
										if(temp == 2) {
											$window.location.href = url+"my_family_detail";
										} else {
											$window.location.href = adminurl+"family_detail";
										}
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
		
		$scope.redirectcontactdetail = function(memberid,temp) {			
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
		
		$scope.getreference = function(memberid)
		{			
			var link = baseUrl+'getreference?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getreferences = data;
				}).error(function(data,status,headers,config) {
					$scope.getreferences = "Response Fail";
				});
			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember1 = data;
				for(i in $scope.getmember1) {
					if($scope.getmember1[i].memberId == memberid) {
						$scope.memberid = $scope.getmember1[i].memberId;
						$scope.membershipnumber = $scope.getmember1[i].membershipNumber;
						$scope.membercategoryid = $scope.getmember1[i].memberCategoryId;
						$scope.tenureplan = $scope.getmember1[i].tenurePlan;
						$scope.memberfirstname = $scope.getmember1[i].memberFirstName;
						$scope.membermiddlename = $scope.getmember1[i].memberMiddleName;
						$scope.memberlastname = $scope.getmember1[i].memberLastName;
					}
				}
			}).error(function(data, status, headers, config) {
				$scope.getmember1 = "Response Fail";
			});
		}
		
		$scope.addreference= function(temp)
		{			
			var memberid = $scope.memberid;
			var firstname = $scope.firstname;
			var lastname = $scope.lastname;
			var email =$scope.email;
			var mobilenumber =$scope.mobilenumber;
			var address1 =$scope.address1;
			var address2 =$scope.address2;
			var countryname =$scope.countryname;
			var statename =$scope.statename;
			var cityname =$scope.cityname;
			var pincode =$scope.pincode;
			var companyname = $scope.companyname;
			var occupation =$scope.occupation;		
			
			if(memberid == "" || memberid == undefined)
			{
				$window.alert("Inside if");
				memberid = 0;
				
				if(address1 =="" || address1 == undefined)
				{
					address1 = "";
				}
				if(address2 =="" || address2 == undefined)
				{
					address2 = "";
				}
				if(countryname =="" || countryname == undefined)
				{
					countryname = 0;
				}
				if(statename =="" || statename == undefined)
				{
					statename = 0;
				}
				if(cityname =="" || cityname == undefined)
				{
					cityname = "";
				}
				if(pincode =="" || pincode == undefined)
				{
					pincode = "";
				}
				if(companyname =="" || companyname == undefined)
				{
					companyname = "";
				}
				if(occupation =="" || occupation == undefined)
				{
					occupation = "";
				}				
				
				if(firstname == "" || firstname == undefined)
				{
					$window.alert("Please enter first name");
					document.getElementById("firstname").focus();
					return;
				}
				else if(lastname =="" || lastname == undefined)
				{
					$window.alert("Please enter last name");
					document.getElementById("lastname").focus();
					return;
				}
				else if(email =="" || email == undefined)
				{
					$window.alert("Please enter email id");
					document.getElementById("email").focus();
					return;
				}
				else if(mobilenumber =="" || mobilenumber == undefined)
				{
					$window.alert("Please enter mobile number");
					document.getElementById("mobilenumber").focus();
					return;
				}
				
				else
				{
					var link = baseUrl+'addReference?referencemembersid='+memberid+'&firstname='+firstname+'&lastname='+lastname+'&email='+email+'&mobilenumber='+mobilenumber+'&address1='+address1+'&address2='+address2+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&companyname='+companyname+'&occupation='+occupation;
					$http.post(link).success(
					    	function(data, status, headers, config)
					    	{
					    		$scope.addreference = data;
					    		
					    		$window.alert("Reference Added successfully");
					    		if(temp == 2){
					    			$window.location.href = url + 'my_reference';
					    		}else{
					    			$window.location.href = adminurl + 'reference';
					    		}
			    			}).
			    			error(function(data, status, headers, config)
			    			{
			    				$scope.addreference = "Response Fail";
					    	});
				}
			}
			else
			{
				$window.alert("Inside else");
				var firstname = "";
				var lastname = "";
				var email = "";
				var mobilenumber = "";
				var address1 = "";
				var address2 = "";
				var countryname = 0;
				var statename = 0;
				var cityname = "";
				var pincode = "";
				var companyname = "";
				var occupation = "";
				
				var link = baseUrl+'addReference?referencemembersid='+memberid+'&firstname='+firstname+'&lastname='+lastname+'&email='+email+'&mobilenumber='+mobilenumber+'&address1='+address1+'&address2='+address2+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&companyname='+companyname+'&occupation='+occupation;
				
				$http.post(link).success(
				    	function(data, status, headers, config)
				    	{
				    		$scope.addreference = data;
				    		
				    		$window.alert("Reference Added successfully");
				    		if(temp == 2){
				    			$window.location.href = url + 'my_reference';
				    		}else{
				    			$window.location.href = adminurl + 'reference';
				    		}
		    			}).
		    			error(function(data, status, headers, config)
		    			{
		    				$scope.addreference = "Response Fail";
				    	});
				
			}
		}
		
		$scope.redirectcontactdetail = function(memberid,temp)
		{
			if(temp == 2) {
				$window.location.href = url+"my_contact_detail";
			} else {
				$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
			}
		}
		
		$scope.redirectpayments = function(temp)
		{
			if(temp == 2)
			{
				$window.location.href = url + "my_payment_detail";
			}else{
				$window.location.href = adminurl + "payment_detail";
			}
		}
		
		$scope.getpayment = function(memberid)
		{			
			var link = baseUrl+'getpayment?memberid='+memberid;
			$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getpayments = data;
						
					}).error(function(data,status,headers,config)
					{
						$scope.getpayments = "Response Fail";
					});
			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getmember = data;
						
						for(i in $scope.getmember)
						{
							if($scope.getmember[i].memberId == memberid)
							{
								$scope.memberid = $scope.getmember[i].memberId;
								$scope.membershipnumber = $scope.getmember[i].membershipNumber;
								$scope.oldmembershipnumber = $scope.getmember[i].oldMembershipNumber;
								$scope.membercategoryid = $scope.getmember[i].memberCategoryId;
								$scope.memberfirstname = $scope.getmember[i].memberFirstName;
								$scope.membermiddlename = $scope.getmember[i].memberMiddleName;
								$scope.memberlastname = $scope.getmember[i].memberLastName;
							}
						}
					}).
					error(function(data, status, headers, config)
					{
						$scope.getmember = "Response Fail";
					});
		}
		
		$scope.calculateamountcharges = function()
		{
			var transactionamount = Number($scope.transactionamount || 0);
			var transactioncharges = Number($scope.transactioncharges || 0);
			
			$scope.amountcharges = transactionamount + transactioncharges;
		}
		
		$scope.addpayment = function(temp)
		{
			var paymenttype = $scope.paymenttype;
			var transactiondate = $scope.transactiondate;
			var transactionmonth = $scope.transactionmonth;
			var transactionyear = $scope.transactionyear;
			var paidtowhom = $scope.paidtowhom;
			var transactionamount = $scope.transactionamount;
			var currencyidtransactionamount = $scope.currencyidtransactionamount;
			var transactioncharges = $scope.transactioncharges;
			var currencyidtransactioncharges = $scope.currencyidtransactioncharges;
			var banknamecheque = $scope.banknamecheque;
			var branchnamecheque = $scope.branchnamecheque;
			var accountnumbercheque = $scope.accountnumbercheque;
			var chequenumber = $scope.chequenumber;
			var chequedate = $scope.chequedate;
			var chequemonth = $scope.chequemonth;
			var chequeyear = $scope.chequeyear;
			var demanddraftnumber = $scope.demanddraftnumber;
			var demanddraftdate = $scope.demanddraftdate;
			var demanddraftmonth = $scope.demanddraftmonth;
			var demanddraftyear = $scope.demanddraftyear;
			var banknameneft = $scope.banknameneft;
			var branchnameneft = $scope.branchnameneft;
			var accountnumberneft = $scope.accountnumberneft;
			var transactionnumberneft = $scope.transactionnumberneft;
			var transactionnumbercash = $scope.transactionnumbercash;
			var contactnumbercash = $scope.contactnumbercash;
			var paymentplace = $scope.paymentplace;
			var transactionnumberother = $scope.transactionnumberother;
			var contactnumberother = $scope.contactnumberother;
			var comments = $scope.comments;
			var amountcharges = $scope.amountcharges;
			
			if(paymenttype == "Cheque")
			{
				if(banknamecheque==undefined || banknamecheque=="")
				{
					$window.alert("Please enter bank name!");
					document.getElementById("banknamecheque").focus();
					return;
				}
				
				else if(branchnamecheque==undefined || branchnamecheque=="")
				{
					$window.alert("Please enter branch name!");
					document.getElementById("branchnamecheque").focus();
					return;
				}
				else if(accountnumbercheque==undefined || accountnumbercheque=="")
				{
					$window.alert("Please enter account number!");
					document.getElementById("accountnumbercheque").focus();
					return;
				}
				else if(chequenumber==undefined || chequenumber=="")
				{
					$window.alert("Please enter cheque number!");
					document.getElementById("chequenumber").focus();
					return;
				}
				else if(chequedate==undefined || chequedate=="")
				{
					$window.alert("Please select cheque date!");
					document.getElementById("chequedate").focus();
					return;
				}
				else if(chequemonth==undefined || chequemonth=="")
				{
					$window.alert("Please select cheque month!");
					document.getElementById("chequemonth").focus();
					return;
				}
				else if(chequeyear==undefined || chequeyear=="")
				{
					$window.alert("Please enter cheque year!");
					document.getElementById("chequeyear").focus();
					return;
				}
				
			}
			
			if(paymenttype == "Demand Draft")
			{
				if(banknamecheque==undefined || banknamecheque=="")
				{
					$window.alert("Please enter bank name!");
					document.getElementById("banknamecheque").focus();
					return;
				}
				
				else if(branchnamecheque==undefined || branchnamecheque=="")
				{
					$window.alert("Please enter branch name!");
					document.getElementById("branchnamecheque").focus();
					return;
				}
				else if(accountnumbercheque==undefined || accountnumbercheque=="")
				{
					$window.alert("Please enter account number!");
					document.getElementById("accountnumbercheque").focus();
					return;
				}
				else if(demanddraftnumber==undefined || demanddraftnumber=="")
				{
					$window.alert("Please enter demand draft number!");
					document.getElementById("demanddraftnumber").focus();
					return;
				}
				else if(demanddraftdate==undefined || demanddraftdate=="")
				{
					$window.alert("Please select demand draft date!");
					document.getElementById("demanddraftdate").focus();
					return;
				}
				else if(demanddraftmonth==undefined || demanddraftmonth=="")
				{
					$window.alert("Please select demand draft month!");
					document.getElementById("demanddraftmonth").focus();
					return;
				}
				else if(demanddraftyear==undefined || demanddraftyear=="")
				{
					$window.alert("Please enter demand draft year!");
					document.getElementById("demanddraftyear").focus();
					return;
				}
				
			}
			
			if(paymenttype=="NEFT" || paymenttype=="RTGS" || paymenttype=="Wire Transfer")
			{
				if(banknameneft==undefined || banknameneft=="")
				{
					$window.alert("Please enter bank name!");
					document.getElementById("banknameneft").focus();
					return;
				}								
				else if(branchnameneft==undefined || branchnameneft=="")
				{
					$window.alert("Please enter branch name!");
					document.getElementById("branchnameneft").focus();
					return;
				}
				else if(accountnumberneft==undefined || accountnumberneft=="")
				{
					$window.alert("Please enter account number!");
					document.getElementById("accountnumberneft").focus();
					return;
				}
				else if(transactionnumberneft==undefined || transactionnumberneft=="")
				{
					$window.alert("Please enter transaction number!");
					document.getElementById("transactionnumberneft").focus();
					return;
				}
			}
			
			if(paymenttype=="Cash")
			{
				if(transactionnumbercash==undefined || transactionnumbercash=="")
				{
					$window.alert("Please enter transaction number!");
					document.getElementById("transactionnumbercash").focus();
					return;
				}
				else if(contactnumbercash==undefined || contactnumbercash=="")
				{
					$window.alert("Please enter contact number!");
					document.getElementById("contactnumbercash").focus();
					return;
				}
				else if(paymentplace==undefined || paymentplace=="")
				{
					$window.alert("Please enter payment place!");
					document.getElementById("paymentplace").focus();
					return;
				}
			}
			
			if(paymenttype=="Other")
			{
				if(transactionnumberother==undefined || transactionnumberother=="")
				{
					$window.alert("Please enter transaction number!");
					document.getElementById("transactionnumberother").focus();
					return;
				}
				else if(contactnumberother==undefined || contactnumberother=="")
				{
					$window.alert("Please enter contact number!");
					document.getElementById("contactnumberother").focus();
					return;
				}
			}
			
			if(paidtowhom==undefined || paidtowhom=="")
			{
				paidtowhom = "";
			}
			if(comments==undefined || comments=="")
			{
				comments = "";
			}
			if(transactioncharges==undefined || transactioncharges=="")
			{
				transactioncharges = 0;
			}
			if(currencyidtransactioncharges==undefined || currencyidtransactioncharges=="")
			{
				currencyidtransactioncharges = 0;
			}
			
			if(paymenttype==undefined || paymenttype=="")
			{
				$window.alert("Please select payment type!");
				document.getElementById("paymenttype").focus();
				return;
			}
			else if(transactiondate==undefined || transactiondate=="")
			{
				$window.alert("Please enter transaction date!");
				document.getElementById("transactiondate").focus();
				return;
			}
			else if(transactionamount==undefined || transactionamount=="")
			{
				$window.alert("Please enter transaction amount!");
				document.getElementById("transactionamount").focus();
				return;
			}
			else if(currencyidtransactionamount==undefined || currencyidtransactionamount=="")
			{
				$window.alert("Please select currency!");
				document.getElementById("currencyidtransactionamount").focus();
				return;
			}			
			else if(amountcharges==undefined || amountcharges=="")
			{
				$window.alert("Please enter amount charges!");
				document.getElementById("amountcharges").focus();
				return;
			}
			else
			{
				if(paymenttype=="Cheque")
				{
					var cd = chequeyear+"-"+chequemonth+"-"+chequedate;
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'addPaymentCheque?transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&bankname='+banknamecheque+'&branchname='+branchnamecheque+'&accountnumber='+accountnumbercheque+'&chequenumber='+chequenumber+'&chequedate='+cd+'&comments='+comments+'&amountcharges='+amountcharges;
					var formData=new FormData();								
					
					$http.post(link).
					    success(function(data, status, headers, config)
					    {
							$scope.addpayment = data;
							
							for(var i=0; i<file.files.length;i++)
							{
								formData.append("file",file.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Made Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.addpayment = "Response Fail";
						});
				}
				if(paymenttype=="Demand Draft")
				{
					var dd = demanddraftyear+"-"+demanddraftmonth+"-"+demanddraftdate;
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'addPaymentDemandDraft?transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&bankname='+banknamecheque+'&branchname='+branchnamecheque+'&accountnumber='+accountnumbercheque+'&demanddraftnumber='+demanddraftnumber+'&demanddraftdate='+dd+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					    success(function(data, status, headers, config)
					    {
							$scope.addpayment = data;
							
							for(var i=0; i<file.files.length;i++)
							{
								formData.append("file",file.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Made Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.addpayment = "Response Fail";
						});
				}
				else if(paymenttype=="NEFT" || paymenttype=="RTGS" || paymenttype=="Wire Transfer")
				{
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'addPaymentNeft?transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&bankname='+banknameneft+'&branchname='+branchnameneft+'&accountnumber='+accountnumberneft+'&transactionnumber='+transactionnumberneft+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					     success(function(data, status, headers, config)
					    {
							$scope.addpayment = data;
							
							for(var i=0; i<file.files.length;i++)
							{
								formData.append("file",file.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Made Successfully...");
							if(temp == 2){
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.addpayment = "Response Fail";
						});
				}
				else if(paymenttype=="Cash")
				{
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'addPaymentCash?transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&transactionnumber='+transactionnumbercash+'&contactnumber='+contactnumbercash+'&paymentplace='+paymentplace+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					     success(function(data, status, headers, config)
					    {
							$scope.addpayment = data;
							
							for(var i=0; i<file.files.length;i++)
							{
								formData.append("file",file.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Made Successfully...");
							if(temp == 2){
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.addpayment = "Response Fail";
						});
				}
				else if(paymenttype=="Other")
				{
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'addPaymentOther?transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&transactionnumber='+transactionnumberother+'&contactnumber='+contactnumberother+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					     success(function(data, status, headers, config)
					    {
							$scope.addpayment = data;
							
							for(var i=0; i<file.files.length;i++)
							{
								formData.append("file",file.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Made Successfully...");
							if(temp == 2){
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.addpayment = "Response Fail";
						});
				}
			}
		}
		
		$scope.getpaymentdetail = function(paymentid)
		{
			for(i in $scope.getpayments)
			{
				if($scope.getpayments[i].paymentId == paymentid)
				{
					$scope.paymentid = $scope.getpayments[i].paymentId;
					$scope.paymenttypeedit = $scope.getpayments[i].paymentType;
					$scope.$parent.banknamechequeedit = $scope.getpayments[i].bankName;
					$scope.$parent.branchnamechequeedit = $scope.getpayments[i].branchName;
					$scope.$parent.accountnumberchequeedit = $scope.getpayments[i].accountNumber;
					$scope.$parent.chequenumberedit = $scope.getpayments[i].chequeNumber;
					$scope.cd = $scope.getpayments[i].chequeDate;
					if($scope.cd != null)
					{
						var cd1 = $scope.cd.split("-");
						$scope.$parent.chequedateedit = cd1[2];
						$scope.$parent.chequemonthedit = cd1[1];
						$scope.$parent.chequeyearedit = cd1[0];
					}
					$scope.$parent.demanddraftnumberedit = $scope.getpayments[i].demandDraftNumber;
					$scope.dd = $scope.getpayments[i].demandDraftDate;
					if($scope.dd != null)
					{
						var dd1 = $scope.dd.split("-");
						$scope.$parent.demanddraftdateedit = dd1[2];
						$scope.$parent.demanddraftmonthedit = dd1[1];
						$scope.$parent.demanddraftyearedit = dd1[0];
					}
					$scope.$parent.banknameneftedit = $scope.getpayments[i].bankName;
					$scope.$parent.branchnameneftedit = $scope.getpayments[i].branchName;
					$scope.$parent.accountnumberneftedit = $scope.getpayments[i].accountNumber;
					$scope.$parent.transactionnumberneftedit = $scope.getpayments[i].transactionNumber;
					$scope.$parent.transactionnumbercashedit = $scope.getpayments[i].transactionNumber;
					$scope.$parent.contactnumbercashedit = $scope.getpayments[i].contactNumber;
					$scope.$parent.paymentplaceedit = $scope.getpayments[i].paymentPlace;
					$scope.$parent.transactionnumberotheredit = $scope.getpayments[i].transactionNumber;
					$scope.$parent.contactnumberotheredit = $scope.getpayments[i].contactNumber;
					$scope.td = $scope.getpayments[i].transactionDate;
					if($scope.td != null)
					{
						var td1 = $scope.td.split("-");
						$scope.$parent.transactiondateedit = td1[2];
						$scope.$parent.transactionmonthedit = td1[1];
						$scope.$parent.transactionyearedit = td1[0];
					}
					$scope.paidtowhomedit = $scope.getpayments[i].paidToWhom;
					$scope.transactionamountedit = $scope.getpayments[i].transactionAmount;
					$scope.currencyidtransactionamountedit = $scope.getpayments[i].currencyIdTransactionAmount;
					$scope.transactionchargesedit = $scope.getpayments[i].transactionCharges;
					$scope.currencyidtransactionchargesedit = $scope.getpayments[i].currencyIdTransactionCharges;
					$scope.commentsedit = $scope.getpayments[i].comments;
					$scope.amountchargesedit = $scope.getpayments[i].amountCharges;
				}
			}
		}
		
		$scope.editpayment = function(paymentid,temp)
		{
			var paymenttype = $scope.paymenttypeedit;
			var transactiondate = $scope.transactiondateedit;
			var transactionmonth = $scope.transactionmonthedit;
			var transactionyear = $scope.transactionyearedit;
			var paidtowhom = $scope.paidtowhomedit;
			var transactionamount = $scope.transactionamountedit;
			var currencyidtransactionamount = $scope.currencyidtransactionamountedit;
			var transactioncharges = $scope.transactionchargesedit;
			var currencyidtransactioncharges = $scope.currencyidtransactionchargesedit;
			var banknamecheque = $scope.banknamechequeedit;
			var branchnamecheque = $scope.branchnamechequeedit;
			var accountnumbercheque = $scope.accountnumberchequeedit;
			var chequenumber = $scope.chequenumberedit;
			var chequedate = $scope.chequedateedit;
			var chequemonth = $scope.chequemonthedit;
			var chequeyear = $scope.chequeyearedit;
			var demanddraftnumber = $scope.demanddraftnumberedit;
			var demanddraftdate = $scope.demanddraftdateedit;
			var demanddraftmonth = $scope.demanddraftmonthedit;
			var demanddraftyear = $scope.demanddraftyearedit;
			var banknameneft = $scope.banknameneftedit;
			var branchnameneft = $scope.branchnameneftedit;
			var accountnumberneft = $scope.accountnumberneftedit;
			var transactionnumberneft = $scope.transactionnumberneftedit;
			var transactionnumbercash = $scope.transactionnumbercashedit;
			var contactnumbercash = $scope.contactnumbercashedit;
			var paymentplace = $scope.paymentplaceedit;
			var transactionnumberother = $scope.transactionnumberotheredit;
			var contactnumberother = $scope.contactnumberotheredit;
			var comments = $scope.commentsedit;
			var amountcharges = $scope.amountchargesedit;		
			
			if(paymenttype == "Cheque")
			{
				if(banknamecheque==undefined || banknamecheque=="")
				{
					$window.alert("Please enter bank name!");
					document.getElementById("banknamechequeedit").focus();
					return;
				}
				
				else if(branchnamecheque==undefined || branchnamecheque=="")
				{
					$window.alert("Please enter branch name!");
					document.getElementById("branchnamechequeedit").focus();
					return;
				}
				else if(accountnumbercheque==undefined || accountnumbercheque=="")
				{
					$window.alert("Please enter account number!");
					document.getElementById("accountnumberchequeedit").focus();
					return;
				}
				else if(chequenumber==undefined || chequenumber=="")
				{
					$window.alert("Please enter cheque number!");
					document.getElementById("chequenumberedit").focus();
					return;
				}
				else if(chequedate==undefined || chequedate=="")
				{
					$window.alert("Please select cheque date!");
					document.getElementById("chequedateedit").focus();
					return;
				}
				else if(chequemonth==undefined || chequemonth=="")
				{
					$window.alert("Please select cheque month!");
					document.getElementById("chequemonthedit").focus();
					return;
				}
				else if(chequeyear==undefined || chequeyear=="")
				{
					$window.alert("Please enter cheque year!");
					document.getElementById("chequeyearedit").focus();
					return;
				}
			}
			
			if(paymenttype == "Demand Draft")
			{
				if(banknamecheque==undefined || banknamecheque=="")
				{
					$window.alert("Please enter bank name!");
					document.getElementById("banknamechequeedit").focus();
					return;
				}
				
				else if(branchnamecheque==undefined || branchnamecheque=="")
				{
					$window.alert("Please enter branch name!");
					document.getElementById("branchnamechequeedit").focus();
					return;
				}
				else if(accountnumbercheque==undefined || accountnumbercheque=="")
				{
					$window.alert("Please enter account number!");
					document.getElementById("accountnumberchequeedit").focus();
					return;
				}
				else if(demanddraftnumber==undefined || demanddraftnumber=="")
				{
					$window.alert("Please enter demand draft number!");
					document.getElementById("demanddraftnumberedit").focus();
					return;
				}
				else if(demanddraftdate==undefined || demanddraftdate=="")
				{
					$window.alert("Please select demand draft date!");
					document.getElementById("demanddraftdateedit").focus();
					return;
				}
				else if(demanddraftmonth==undefined || demanddraftmonth=="")
				{
					$window.alert("Please select demand draft month!");
					document.getElementById("demanddraftmonthedit").focus();
					return;
				}
				else if(demanddraftyear==undefined || demanddraftyear=="")
				{
					$window.alert("Please enter demand draft year!");
					document.getElementById("demanddraftyearedit").focus();
					return;
				}
			}
			
			if(paymenttype=="NEFT" || paymenttype=="RTGS" || paymenttype=="Wire Transfer")
			{
				if(banknameneft==undefined || banknameneft=="")
				{
					$window.alert("Please enter bank name!");
					document.getElementById("banknameneftedit").focus();
					return;
				}								
				else if(branchnameneft==undefined || branchnameneft=="")
				{
					$window.alert("Please enter branch name!");
					document.getElementById("branchnameneftedit").focus();
					return;
				}
				else if(accountnumberneft==undefined || accountnumberneft=="")
				{
					$window.alert("Please enter account number!");
					document.getElementById("accountnumberneftedit").focus();
					return;
				}
				else if(transactionnumberneft==undefined || transactionnumberneft=="")
				{
					$window.alert("Please enter transaction number!");
					document.getElementById("transactionnumberneftedit").focus();
					return;
				}
			}
			
			if(paymenttype=="Cash")
			{
				if(transactionnumbercash==undefined || transactionnumbercash=="")
				{
					$window.alert("Please enter transaction number!");
					document.getElementById("transactionnumbercashedit").focus();
					return;
				}
				else if(contactnumbercash==undefined || contactnumbercash=="")
				{
					$window.alert("Please enter contact number!");
					document.getElementById("contactnumbercashedit").focus();
					return;
				}
				else if(paymentplace==undefined || paymentplace=="")
				{
					$window.alert("Please enter payment place!");
					document.getElementById("paymentplaceedit").focus();
					return;
				}
			}
			
			if(paymenttype=="Other")
			{
				if(transactionnumberother==undefined || transactionnumberother=="")
				{
					$window.alert("Please enter transaction number!");
					document.getElementById("transactionnumberotheredit").focus();
					return;
				}
				else if(contactnumberother==undefined || contactnumberother=="")
				{
					$window.alert("Please enter contact number!");
					document.getElementById("contactnumberotheredit").focus();
					return;
				}
			}
			
			if(paidtowhom==undefined || paidtowhom=="")
			{
				paidtowhom = "";
			}
			if(comments==undefined || comments=="")
			{
				comments = "";
			}
			if(transactioncharges==undefined || transactioncharges=="")
			{
				transactioncharges = 0;
			}
			if(currencyidtransactioncharges==undefined || currencyidtransactioncharges=="")
			{
				currencyidtransactioncharges = 0;
			}
			
			if(paymenttype==undefined || paymenttype=="")
			{
				$window.alert("Please select payment type!");
				document.getElementById("paymenttypeedit").focus();
				return;
			}
			else if(transactiondate==undefined || transactiondate=="")
			{
				$window.alert("Please enter transaction date!");
				document.getElementById("transactiondateedit").focus();
				return;
			}
			else if(transactionamount==undefined || transactionamount=="")
			{
				$window.alert("Please enter transaction amount!");
				document.getElementById("transactionamountedit").focus();
				return;
			}
			else if(currencyidtransactionamount==undefined || currencyidtransactionamount=="")
			{
				$window.alert("Please select currency!");
				document.getElementById("currencyidtransactionamountedit").focus();
				return;
			}
			else if(amountcharges==undefined || amountcharges=="")
			{
				$window.alert("Please enter amount charges!");
				document.getElementById("amountchargesedit").focus();
				return;
			}
			else
			{
				if(paymenttype=="Cheque")
				{				
					var cd = chequeyear+"-"+chequemonth+"-"+chequedate;
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'editPaymentCheque?paymentid='+paymentid+'&transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&bankname='+banknamecheque+'&branchname='+branchnamecheque+'&accountnumber='+accountnumbercheque+'&chequenumber='+chequenumber+'&chequedate='+cd+'&comments='+comments+'&amountcharges='+amountcharges;
					var formData=new FormData();								
					
					$http.post(link).
					    success(function(data, status, headers, config)
					    {
							$scope.editpayment = data;
							
							for(var i=0; i<fileedit.files.length;i++)
							{
								formData.append("file",fileedit.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Updated Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.editpayment = "Response Fail";
						});
				}
				if(paymenttype=="Demand Draft")
				{					
					var dd = demanddraftyear+"-"+demanddraftmonth+"-"+demanddraftdate;
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'editPaymentDemandDraft?paymentid='+paymentid+'&transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&bankname='+banknamecheque+'&branchname='+branchnamecheque+'&accountnumber='+accountnumbercheque+'&demanddraftnumber='+demanddraftnumber+'&demanddraftdate='+dd+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					    success(function(data, status, headers, config)
					    {
							$scope.editpayment = data;
							
							for(var i=0; i<fileedit.files.length;i++)
							{
								formData.append("file",fileedit.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Updated Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.editpayment = "Response Fail";
						});
				}
				else if(paymenttype=="NEFT" || paymenttype=="RTGS" || paymenttype=="Wire Transfer")
				{										
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'editPaymentNeft?paymentid='+paymentid+'&transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&bankname='+banknameneft+'&branchname='+branchnameneft+'&accountnumber='+accountnumberneft+'&transactionnumber='+transactionnumberneft+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					     success(function(data, status, headers, config)
					    {
							$scope.editpayment = data;
							
							for(var i=0; i<fileedit.files.length;i++)
							{
								formData.append("file",fileedit.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Updated Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.editpayment = "Response Fail";
						});
				}
				else if(paymenttype=="Cash")
				{				
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'editPaymentCash?paymentid='+paymentid+'&transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&transactionnumber='+transactionnumbercash+'&contactnumber='+contactnumbercash+'&paymentplace='+paymentplace+'&comments='+comments+'&amountcharges='+amountcharges;
					
					var formData=new FormData();								
					
					$http.post(link).
					     success(function(data, status, headers, config)
					    {
							$scope.editpayment = data;
							
							for(var i=0; i<fileedit.files.length;i++)
							{
								formData.append("file",fileedit.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Updated Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.editpayment = "Response Fail";
						});
				}
				else if(paymenttype=="Other")
				{					
					var td = transactionyear+"-"+transactionmonth+"-"+transactiondate;
					var link = baseUrl+'editPaymentOther?paymentid='+paymentid+'&transactiondate='+td+'&transactionamount='+transactionamount+'&currencyidtransactionamount='+currencyidtransactionamount+'&transactioncharges='+transactioncharges+'&currencyidtransactioncharges='+currencyidtransactioncharges+'&paidtowhom='+paidtowhom+'&paymenttype='+paymenttype+'&transactionnumber='+transactionnumberother+'&contactnumber='+contactnumberother+'&comments='+comments+'&amountcharges='+amountcharges;

					var formData=new FormData();								
					
					$http.post(link).
					     success(function(data, status, headers, config)
					    {
							$scope.addpayment = data;
							
							for(var i=0; i<fileedit.files.length;i++)
							{
								formData.append("file",fileedit.files[i]);
							}
							
							var link = baseUrl+'addPaymentImage';
							$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     }).
						     success(
									function(data, status, headers, config)
									{
										$scope.paidimage = data;
									}).
							error(function(data, status, headers, config)
									{
										$scope.paidimage = "Response Fail";
									});
							
							$window.alert("Payment Updated Successfully...");
							if(temp == 2)
							{
								$window.location.href = url + 'my_payment_detail';
							}else{
								$window.location.href = adminurl + 'payment_detail';
							}
						}).
						error(function(data, status, headers, config)
						{
							$scope.editpayment = "Response Fail";
						});
				}
			}
		}
		
		$scope.addEducationRowEdit = function()
		{		
			$scope.getmembereducationdetail.push({'degreeName':$scope.degreename, 'passingYear': $scope.passingyear, 'grade': $scope.grade, 'instituteName': $scope.institutename});
		};
		
		$scope.removeEducationRowEdit = function(degreename)
		{				
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
		$scope.spin = 0;
		$scope.nospin = 1;
		
		$scope.editmemberdetail = function(memberid, temp) {
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
			
			if($scope.getLastSequence1 == undefined)
				$scope.getLastSequence1 = 0;
			var temp = temp;
			var sequence = $scope.getLastSequence1 + 1;
			
			var membershipid = $scope.membershipId;
			var memberclubname = $scope.memberclubname;
			var membercategoryname = $scope.membercategoryname;
			var companyname = $scope.companyname;
			var membertypename = $scope.membertypename;			
			var tenureplan = $scope.tenureplan;
			var joiningdate = document.getElementById("joiningdate").value;			
			var membertitle = $scope.membertitle;
			var firstname = $scope.firstname;
			var middlename = $scope.middlename;
			var lastname = $scope.lastname;
			var gender = $scope.gender;
			var dateofbirth = document.getElementById("dateofbirth").value;			
			var bloodgroup = $scope.bloodgroup;	
			var anniversarydate = document.getElementById("anniversarydate").value;
			var aadharnumber = $scope.aadharnumber;
			var countrynamecitizenship = $scope.countrynamecitizenship;
			var passportnumber = $scope.passportnumber;
			var pannumber = $scope.pannumber;
			var email = $scope.email;
			var password = $scope.password;		
			
			if(!joiningdate) {
				joiningdate1 = "";
			} else {
				var jd = joiningdate.split('/');
				if(jd[0] > 31) {
					$window.alert("Please enter valid joining date");
					document.getElementById("joiningdate").focus();
					return;
				} else if(jd[1] > 12) {
					$window.alert("Please enter valid joining date");
					document.getElementById("joiningdate").focus();
					return;
				} else {
					joiningdate1 = jd[2]+"-"+jd[1]+"-"+jd[0];
				}
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
			if(!dateofbirth) {
				dateofbirth1 = "";
			} else {
				var bd = dateofbirth.split('/');
				if(bd[0] > 31) {
					$window.alert("Please enter valid birth date");
					document.getElementById("dateofbirth").focus();
					return;
				} else if(bd[1] > 12) {
					$window.alert("Please enter valid birth date");
					document.getElementById("dateofbirth").focus();
					return;
				} else {
					dateofbirth1 = bd[2]+"-"+bd[1]+"-"+bd[0];
				}
			}
			if(bloodgroup == "" || bloodgroup == undefined) {
				bloodgroup = "";
			}
			if(!anniversarydate) {
				anniversarydate1 = "";
			} else {
				var ad = anniversarydate.split('/');
				if(ad[0] > 31) {
					$window.alert("Please enter valid anniversary date");
					document.getElementById("anniversarydate").focus();
					return;
				} else if(ad[1] > 12) {
					$window.alert("Please enter valid anniversary date");
					document.getElementById("anniversarydate").focus();
					return;
				} else {
					anniversarydate1 = ad[2]+"-"+ad[1]+"-"+ad[0];
				}
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
			} else if(membertypename == "" || membertypename == undefined) {
				$window.alert("Please select nationality");
				document.getElementById("membertypename").focus();
				return;
			} else if(email == "" || email == undefined) {
				$window.alert("Please enter email id");
				document.getElementById("email").focus();
				return;
			} else if(password == "" || password == undefined) {
				$window.alert("Please enter password");
				document.getElementById("password").focus();
				return;
			} else {
				$scope.spin = 1;
				$scope.nospin = 0;
				var bg = encodeURIComponent(bloodgroup);		
				
				var link = baseUrl+'deleteMemberEducation?memberid='+memberid;
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deletemembereducation = data;
				}).error(function(data, status, headers, config) {
					$scope.deletemembereducation = "Response Fail";
				});
				
				var link = baseUrl+'editmemberdetail1?rotaryyearid='+$scope.rotaryYearId+'&memberid='+memberid+'&membershipid='+membershipid+'&memberclubname='+memberclubname+'&membercategoryname='+membercategoryname+'&membertypename='+membertypename+'&businesscategoryid='+$scope.businesscategoryid+'&tenureplan='+tenureplan+'&joiningdate='+joiningdate1+'&membertitle='+membertitle+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&gender='+gender+'&dateofbirth='+dateofbirth1+'&bloodgroup='+bg+'&anniversarydate='+anniversarydate1+'&aadharnumber='+aadharnumber+'&countrynamecitizenship='+countrynamecitizenship+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&profilepicture='+$scope.profilepicture+'&email='+email+'&password='+password+'&sequence='+sequence+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
				var formData=new FormData();
				formData.append("profile",imgInp.files[0]);
				$http({method: 'POST',
					url: link,
					headers: {'Content-Type': undefined},
					data: formData,
					transformRequest: function(data, headersGetterFunction) {
						return data;
					}
				}).success(function(data, status) {
					$scope.editMemberDetail = data;
					var a = 0;
					if($scope.getmembereducationdetail.length == 0)	{
						$scope.spin = 0;
						$scope.nospin = 1;
						$window.alert("Member Detail Updated Successfully");
						if(temp == 2) {
							$window.location.href = url+"my_contact_detail";
						} else {
							$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
						}
					}
					angular.forEach($scope.getmembereducationdetail, function(item) {
						if(item.degreeName != null)	{
							var link = baseUrl+'editMemberEducation?degreename='+item.degreeName+'&passingyear='+item.passingYear+'&grade='+item.grade+'&institutename='+item.instituteName+'&memberid='+memberid;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addmembereducation = data;
								a = a + 1;
								if($scope.getmembereducationdetail.length == a) {
									$scope.spin = 0;
									$scope.nospin = 1;
									$window.alert("Member Detail Edited Successfully");
									if(temp == 2) {
										$window.location.href = url+"my_contact_detail";
									} else {
										$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
									}
								}
							}).error(function(data, status, headers, config) {
								$scope.addmembereducation = "Response Fail";
							});
						}
					});
				}).error(function(data, status, headers, config) {
					$scope.editMemberDetail = "Response Fail";
				});
			}
		}
		
		$scope.redirectcontactdetail = function(memberid,temp)
		{
			if(temp == 2) {
				$window.location.href = url+"my_contact_detail";
			} else {
				$window.location.href = adminurl+"manage_contact_detail?memberid="+memberid;
			}
		}
		
		$scope.getcontactdetail = function(memberid)
		{		
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

			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;
			$http.get(link).success( function(data, status, headers, config) {
				$scope.getmember = data;
				
				for(i in $scope.getmember) {
					if($scope.getmember[i].memberId == memberid) {
						$scope.memberid = $scope.getmember[i].memberId;
						$scope.membershipnumber = $scope.getmember[i].membershipNumber;						
						$scope.membercategoryid = $scope.getmember[i].memberCategoryId;
						$scope.companydetailid = $scope.getmember[i].companyDetailId;
						$scope.memberfirstname = $scope.getmember[i].memberFirstName;
						$scope.membermiddlename = $scope.getmember[i].memberMiddleName;
						$scope.memberlastname = $scope.getmember[i].memberLastName;
						$scope.address1 = $scope.getmember[i].memberAddress1;
						$scope.address2 = $scope.getmember[i].memberAddress2;
						$scope.address3 = $scope.getmember[i].memberAddress3;
						$scope.statename = $scope.getmember[i].memberStateId;
						$scope.cityname = $scope.getmember[i].memberCityName;
						$scope.pincode = $scope.getmember[i].memberPincode;
						$scope.mobilenumber = $scope.getmember[i].memberMobileNumber;
						$scope.logoimage = $scope.getmember[i].companyLogo;
						$scope.occupation = $scope.getmember[i].memberOccupation;
						$scope.designation = $scope.getmember[i].memberDesignation;
						$scope.companyname = $scope.getmember[i].memberCompanyName;
						$scope.businessnature = $scope.getmember[i].memberBusinessNature;
						$scope.faxnumber = $scope.getmember[i].memberFaxNumber;
						$scope.website = $scope.getmember[i].memberWebsiteName;
						$scope.email = $scope.getmember[i].memberCompanyEmail;
						$scope.address1work = $scope.getmember[i].memberCompanyAddress1;
						$scope.address2work = $scope.getmember[i].memberCompanyAddress2;
						$scope.address3work = $scope.getmember[i].memberCompanyAddress3;
						$scope.statenamework = $scope.getmember[i].memberCompanyStateId;
						$scope.citynamework = $scope.getmember[i].memberCompanyCityName;
						$scope.pincodework = $scope.getmember[i].memberCompanyPincode;
						$scope.mobilenumberwork = $scope.getmember[i].memberCompanyMobileNumber;
						$scope.communication = $scope.getmember[i].memberCommunicationAddress;
						$scope.membercategoryid = $scope.getmember[i].memberCategoryId;
						$scope.tenureplan = $scope.getmember[i].tenurePlan;
						$scope.aboutbusiness = $scope.getmember[i].memberCompanyDescription;
						$scope.businesskeywords = $scope.getmember[i].memberCompanyKeywords;
						$scope.businessgoals = $scope.getmember[i].businessGoals;
						$scope.accomplishments = $scope.getmember[i].accomplishments;
						$scope.interests = $scope.getmember[i].interests;
						$scope.networks = $scope.getmember[i].networks;
						$scope.skills = $scope.getmember[i].skills;
						$scope.idealreferral = $scope.getmember[i].idealReferral;
						$scope.topproduct = $scope.getmember[i].topProduct;
						$scope.topproblemsolved = $scope.getmember[i].topProblemSolved;
					}
				}
				
				var link = baseUrl+'getState';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getrelatedstate = data;
					for(i in $scope.getrelatedstate) {
						if($scope.getrelatedstate[i].stateId == $scope.statename) {
							$scope.countryname = $scope.getrelatedstate[i].countryId;
						}
					}
					for(i in $scope.getrelatedstate) {
						if($scope.getrelatedstate[i].stateId == $scope.statenamework) {
							$scope.countrynamework = $scope.getrelatedstate[i].countryId;
						}
					}
				}).error(function(data, status, headers, config) {
					$scope.getrelatedstate = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});
		}
		
		$scope.redirectfamilydetail = function(memberid, membercategoryid, tenureplan, temp)
		{
			var membershipnumber = "";
			for(i in $scope.getmember) {
				if($scope.getmember[i].memberId == memberid) {
					membershipnumber = $scope.getmember[i].membershipNumber;
				}
			}
			var link = baseUrl+'setSessionMemberId?memberid='+memberid+'&membershipnumber='+membershipnumber;
			$http.post(link).success( function(data, status, headers, config) {
				$scope.setsessionmemberid = data;
				if(temp == 2){
					$window.location.href = url + "my_family_detail";			
				}else{
					$window.location.href = adminurl + "manage_family_detail?memberid="+memberid;
				}
			}).error(function(data, status, headers, config) {
				$scope.setsessionmemberid = "Response Fail";
			});
		}
		
		$scope.addResidentialLandlineRow1 = function()
		{
			if($scope.phonenumber == "" || $scope.phonenumber == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumber").focus();
				return;
			}
			else
			{
				$scope.getmemberresidentiallandline.push({'landlinePhoneNumber':$scope.phonenumber});
			}
		};
		
		$scope.removeResidentialLandlineRow1 = function(landlinephonenumber)
		{				
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
		

		
		$scope.addWorkLandlineRow1 = function()
		{
			var occupation = $scope.occupation;
			var designation = $scope.designation;
			var companyname = $scope.companyname;
			var businessnature = $scope.businessnature;
			var address1work = $scope.address1work;
			var address2work = $scope.address2work;
			var address3work = $scope.address3work;
			var countrynamework = $scope.countrynamework;
			var statenamework = $scope.statenamework;
			var citynamework = $scope.citynamework;
			var pincodework = $scope.pincodework;
			var email = $scope.email;
			var mobilenumberwork = $scope.mobilenumberwork;	
			
			if(occupation == "" || occupation == undefined ){
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
			if(address3work == "" || address3work == undefined ) {
				address3work = "";
			}
			if(address1work == "" || address1work == undefined ) {
				$window.alert("Please enter address line1");
				document.getElementById("address1work").focus();
				return;
			}
			if(address2work == "" || address2work == undefined ) {
				$window.alert("Please enter address line2");
				document.getElementById("address2work").focus();
				return;
			}
			if(countrynamework == "" || countrynamework == undefined ) {
				$window.alert("Please Select country");
				document.getElementById("countrynamework").focus();
				return;
			}
			if(statenamework == "" || statenamework == undefined ) {
				$window.alert("Please select state");
				document.getElementById("statenamework").focus();
				return;
			}
			if(citynamework == "" || citynamework == undefined ) {
				$window.alert("Please enter city");
				document.getElementById("citynamework").focus();
				return;
			}
			if(pincodework == "" || pincodework == undefined ) {
				$window.alert("Please enter pincode");
				document.getElementById("pincodework").focus();
				return;
			}
			if(mobilenumberwork == "" || mobilenumberwork == undefined ) {
				$window.alert("Please enter mobile number");
				document.getElementById("mobilenumberwork").focus();
				return;
			}
			else if(email == "" || email == undefined ) {
				$window.alert("Please enter email");
				document.getElementById("email").focus();
				return;
			}
			else if($scope.phonenumberwork == "" || $scope.phonenumberwork == undefined)
			{
				$window.alert("Please enter phone number");
				document.getElementById("phonenumberwork").focus();
				return;
			}
			else
			{
				$scope.getmemberworklandline.push({'landlinePhoneNumber':$scope.phonenumberwork,'memberCompanyName':companyname,'memberDesignation':designation,'memberComapnyAddress1':address1work,'memberComapnyAddress2':address2work,'memberComapnyAddress3':address3work,'memberCompanyCity':citynamework,'memberComapnyMobileNumber':mobilenumberwork,'memberComapnyEmail':email});
				if($scope.getmemberworklandline.length == 3){
					$scope.disableAddButton = 1;
				}
			}
		};
		
		$scope.removeWorkLandlineRow1 = function(landlinephonenumber)
		{				
			var index = -1;		
			var comArr = eval( $scope.worklandline);
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
		
		$scope.editcontactdetail = function(memberid, membercategoryid, tenureplan, temp)
	    {
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
			var address1 = $scope.address1;
			var address2 = $scope.address2;
			var address3 = $scope.address3;
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;
			var pincode = $scope.pincode;
			var mobilenumber = $scope.mobilenumber;			
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
			
			if(address2 == "" || address2 == undefined) {
				address2 = "";
			}
			if(address3 == "" || address3 == undefined) {
				address3 = "";
			}
			if(pincode == "" || pincode == undefined) {
				pincode = "";
			} 
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
					
			if(address1 == "" || address1 == undefined) {
				$window.alert("Please enter address line-1");
				document.getElementById("address1").focus();
				return;
			}
			else if(countryname == "" || countryname == undefined) {
				$window.alert("Please select country");
				document.getElementById("countryname").focus();
				return;
			}
			else if((statename == "" || statename == undefined) && countryname == 1) {
				$window.alert("Please select state");
				document.getElementById("statename").focus();
				return;
			}
			else if(cityname == "" || cityname == undefined) {
				$window.alert("Please enter city name");
				document.getElementById("cityname").focus();
				return;
			}
			else if(mobilenumber == "" || mobilenumber == undefined) {
				$window.alert("Please enter mobile number");
				document.getElementById("mobilenumber").focus();
				return;
			}
			else if(mobilenumber.length != 10) {
				$window.alert("Mobile number should be 10 digit");
				document.getElementById("mobilenumber").focus();
				return;
			}			
			else if(communication == "" || communication == undefined ) {
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
				
				var link = baseUrl+'editContactDetail?memberid='+memberid+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&occupation='+occupation+'&designation='+designation+'&companyname='+companyname+'&businessnature='+businessnature+'&faxnumber='+faxnumber+'&website='+website+'&aboutbusiness='+$scope.aboutbusiness+'&businesskeywords='+$scope.businesskeywords+'&email='+email+'&address1work='+address1work+'&address2work='+address2work+'&address3work='+address3work+'&statenamework='+statenamework+'&citynamework='+citynamework+'&pincodework='+pincodework+'&mobilenumberwork='+mobilenumberwork+'&communicationaddress='+communication+'&businessgoals='+$scope.businessgoals+'&accomplishments='+$scope.accomplishments+'&interests='+$scope.interests+'&networks='+$scope.networks+'&skills='+$scope.skills+'&idealreferral='+$scope.idealreferral+'&topproduct='+$scope.topproduct+'&topproblemsolved='+$scope.topproblemsolved+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
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
							var link = baseUrl+'editMemberWorkDetails?memberid='+memberid+'&landlinephonenumber='+item.landlinePhoneNumber+'&location='+'W'+'&memberCompanyName='+item.memberCompanyName+'&memberDesignation='+item.memberDesignation+'&memberComapnyAddress1='+item.memberComapnyAddress1+'&memberComapnyAddress2='+item.memberComapnyAddress2+'&memberComapnyAddress3='+item.memberComapnyAddress3+'&memberCompanyCity='+item.memberCompanyCity+'&memberComapnyMobileNumber='+item.memberComapnyMobileNumber+'&memberComapnyEmail='+item.memberComapnyEmail;
							
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
				});
			}
	    }
		
		$scope.deletefamilymember = function(membersfamilyid)
		{
			d = $window.confirm('Are you sure you want to delete this family member?');
			if(d)
			{
				var link = baseUrl+'deleteFamilyMember?membersfamilyid='+membersfamilyid;
				$http['delete'](link).success(
						function(data, status, headers, config)
						{
							$scope.deletefamilymember = data;
							$window.location.reload();
						}).
						error(function(data, status, headers, config)
						{
							$scope.deletefamilymember = "Response Fail";
						});
			}
		}
		
		$scope.deletereference = function(referenceid)
		{
			d = $window.confirm('Are you sure you want to delete this reference?');
			if(d)
			{
				var link = baseUrl+'deleteReference?referenceid='+referenceid;
				$http['delete'](link).success(
						function(data, status, headers, config)
						{
							$scope.deletereference = data;
							$window.location.reload();
						}).
						error(function(data, status, headers, config)
						{
							$scope.deletereference = "Response Fail";
						});
			}
		}
		$scope.successMsg = true;
		$scope.errorMsg = true;
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
		    			var link = baseUrl+'getAllBusinessCategories';
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.allbusinesscategories = data;			
		    			}).error(function(data, status, headers, config) {
		    				$scope.allbusinesscategories = "Response Fail";
		    			});
		    			$scope.successMsg = false;
		    			$timeout(function () { 
		    				$scope.successMsg = true;		    				
		    				$('#businessCategoryModal').modal('hide');
		    			}, 1000);    				    									
		    		} else {
		    			$scope.errorMsg = false;
		    			$timeout(function () { 
		    				$scope.errorMsg = true;		    				
		    				$('#businessCategoryModal').modal('hide');
		    			}, 1000);
		    		}    				
				}).error(function(data, status, headers, config) {
						$scope.addbusinesscategory = "Response Fail";
				});
			}
		}
		
		$scope.checkmobilenumber = function()
		{
			var link = baseUrl+"checkmobilenumber?mobilenumber="+$scope.mobilenumber+"&email="+$scope.email;
			$http.get(link).success(function(data, status, headers, config) { 
				if (data == "Success") {
					alert("User with same Credentials already exist"); 
	    		}
				else{
				}
			}).error(function(data, status, headers, config) {
					$scope.addbusinesscategory = "Response Fail";
			});
		}
		
	}]);
	
	app.controller('birthdayCtrl', function($scope, $http, $window, $filter, $location) 
	{
				var baseUrl = $location.protocol() + "://" + location.host + u;

				$scope.getBirthdaysAndAnniversariesByDate = function ()
			    {
					var currentdate = $filter('date')(new Date(),'MM-dd');
					
					var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallbirthdaysbydate = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallbirthdaysbydate = "Response Fail";
					});
					
					var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallanniversariesbydate = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallanniversariesbydate = "Response Fail";
					});
			    }
				
				$scope.currentmonth = function()
				{					
					$scope.birthmonth = $filter('date')(new Date(), 'MM');
					$scope.currentmonthname = $filter('date')(new Date(), 'MMMM');
					var link = baseUrl+'getAllBirthdays?birthmonth='+$scope.birthmonth;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallbirthdays = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallbirthdays = "Response Fail";
					});
					
					var link = baseUrl+'getAllAnniversaries?anniversarymonth='+$scope.birthmonth;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallanniversaries = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallanniversaries = "Response Fail";
					});
				}
				
				$scope.monthchange = function()
				{										
					$scope.currentmonthname = $filter('date')(new Date($scope.birthmonth), 'MMMM');					
					var link = baseUrl+'getAllBirthdays?birthmonth='+$scope.birthmonth;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallbirthdays = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallbirthdays = "Response Fail";
					});
					
					var link = baseUrl+'getAllAnniversaries?anniversarymonth='+$scope.birthmonth;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getallanniversaries = data;			
					}).error(function(data, status, headers, config) {
						$scope.getallanniversaries = "Response Fail";
					});
				}
	});
	
	app.controller('boardOfDirectorsCtrl', function($scope, $http, $window, $filter, $location) 
	{
				var baseUrl = $location.protocol() + "://" + location.host + u;	
				
				$scope.currentPage = 0;
			    $scope.pageSize = 24;
			    $scope.search = '';	 
			    $scope.startindex = $scope.currentPage;
			    
			    $scope.currentPage1 = 0;
				$scope.pageSize1 = 20;
				$scope.search1 = '';
				
				$scope.getData1 = function()
				{
					return $filter('filter')($scope.data,$scope.search1)
				}
				$scope.numberOfPages1 = function()
				{
					return Math.ceil($scope.boardofdirectors.length/ $scope.pageSize1);
				}
			    
			    var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.currentPage;			    
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember1 = data;
					$scope.a = 1;
				}).error(function(data, status, headers, config) {
					$scope.getmember1 = "Response Fail";
				});			
				
				$scope.next = function()
				{		
						$scope.currentPage = $scope.currentPage + 1;
						$scope.startindex = $scope.pageSize * $scope.currentPage;
							
						$scope.a = 0;
							
						var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config)	{
							$scope.getmember1 = data;
							$scope.a = 1;
						}).error(function(data, status, headers, config) {
							$scope.getmember1 = "Response Fail";
						});
								
				}
				
				$scope.prev = function()
				{
					$scope.currentPage = $scope.currentPage - 1;
					$scope.startindex = $scope.pageSize * $scope.currentPage;		
					
					$scope.a = 0;
						
					var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config)	{
						$scope.getmember1 = data;
						$scope.a = 1;
					}).error(function(data, status, headers, config) {
						$scope.getmember1 = "Response Fail";
					});			
				}
				
				$scope.searchmember = function() {
					var search = $scope.search;					
					if(search == undefined || search == "") {
						$scope.a = 0;						
						var link = baseUrl+'getMemberByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getmember1 = data;
							$scope.a = 1;
						}).error(function(data, status, headers, config) {
							$scope.getmember1 = "Response Fail";
						});
					} else {
						$scope.a = 0;					
						var link = baseUrl+'searchMembers?keyword='+search;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getmember1 = data;
							$scope.a = 1;
						}).error(function(data, status, headers, config) {
							$scope.getmember1 = "Response Fail";
						});
					}
				}
			    
			    $scope.getData = function ()
			    {
				     // needed for the pagination calc
			    	return $filter('filter')($scope.data, $scope.search);
			    }
			    
			    $scope.numberOfPages=function()
			    {
			    	return Math.ceil($scope.boardOfDirectorsList.length/$scope.pageSize);
			    }
			    
			    $scope.numberOfPagesForMembersDirectory = function(){
			    	return Math.ceil($scope.getmember.length/$scope.pageSizeForMembersDirectory);
			    }
			    
			    $scope.redirecttomemberfamilyinfo = function(membersfamilyid, memberid)
			    {			    	
			    	$window.location.href = url + 'members_family_info?membersfamilyid='+membersfamilyid+'&memberid='+memberid;
			    }
			    
			    $scope.getspouse = function(membersfamilyid, memberid) {			    	
			    	var link = baseUrl+'getspousedata?memberid='+memberid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getspousedata = data;
						for(i in $scope.getspousedata) {
							if($scope.getspousedata[i].membersFamilyId == membersfamilyid) {
								$scope.membersfamilyid = $scope.getspousedata[i].membersFamilyId;
								$scope.spouseidedit = $scope.getspousedata[i].membershipNumber;					
								$scope.relationedit = $scope.getspousedata[i].memberFamilyTypeOfRelation;
								$scope.memberfamilytitleedit = $scope.getspousedata[i].memberFamilyTitleName;
								$scope.firstnameedit = $scope.getspousedata[i].memberFamilyFirstName;
								$scope.middlenameedit = $scope.getspousedata[i].memberFamilyMiddleName;
								$scope.lastnameedit = $scope.getspousedata[i].memberFamilyLastName;
								$scope.genderedit = $scope.getspousedata[i].memberFamilyGender;
								$scope.birthedit = $scope.getspousedata[i].memberFamilyDateOfBirth;									
								$scope.bloodgroupedit = $scope.getspousedata[i].memberFamilyBloodGroup;					
								$scope.aadharnumberedit = $scope.getspousedata[i].memberFamilyAadharNumber;
								$scope.passportnumberedit = $scope.getspousedata[i].memberFamilyPassportNumber;
								$scope.pannumberedit = $scope.getspousedata[i].memberFamilyPanNumber;
								$scope.profileedit = $scope.getspousedata[i].memberFamilyProfilePicture;
								$scope.emailedit = $scope.getspousedata[i].memberFamilyEmail;										
								$scope.passwordedit = $scope.getspousedata[i].memberFamilyPassword;
								$scope.address1edit = $scope.getspousedata[i].memberFamilyAddress1;
								$scope.address2edit = $scope.getspousedata[i].memberFamilyAddress2;
								$scope.address3edit = $scope.getspousedata[i].memberFamilyAddress3;
								$scope.statenameedit = $scope.getspousedata[i].memberFamilyStateId;
								$scope.citynameedit = $scope.getspousedata[i].memberFamilyCityName;
								$scope.pincodeedit = $scope.getspousedata[i].memberFamilyPincode;
								$scope.mobilenumberedit = $scope.getspousedata[i].memberFamilyMobileNumber;
								$scope.phonenumberedit = $scope.getspousedata[i].memberFamilyPhoneNumber;
								$scope.occupationedit = $scope.getspousedata[i].memberFamilyOccupation;
								$scope.designationedit = $scope.getspousedata[i].memberFamilyDesignation;
								$scope.companynameedit = $scope.getspousedata[i].memberFamilyCompanyName;
								$scope.businessnatureedit = $scope.getspousedata[i].memberFamilyBusinessNature;
								$scope.faxnumberedit = $scope.getspousedata[i].memberFamilyFaxNumber;
								$scope.websiteedit = $scope.getspousedata[i].memberFamilyWebsiteName;
								$scope.emailworkedit = $scope.getspousedata[i].memberFamilyCompanyEmail;
								$scope.address1workedit = $scope.getspousedata[i].memberFamilyCompanyAddress1;
								$scope.address2workedit = $scope.getspousedata[i].memberFamilyCompanyAddress2;
								$scope.address3workedit = $scope.getspousedata[i].memberFamilyCompanyAddress3;
								$scope.statenameworkedit = $scope.getspousedata[i].memberFamilyCompanyStateId;
								$scope.citynameworkedit = $scope.getspousedata[i].memberFamilyCompanyCityName;
								$scope.pincodeworkedit = $scope.getspousedata[i].memberFamilyCompanyPincode;
								$scope.mobilenumberworkedit = $scope.getspousedata[i].memberFamilyCompanyMobileNumber;
								$scope.phonenumberworkedit = $scope.getspousedata[i].memberFamilyCompanyPhoneNumber;
								$scope.communicationedit = $scope.getspousedata[i].memberFamilyCommunicationAddress;
							}
						}
					}).error(function(data,status,headers,config) {
						$scope.getspousedata = "Response Fail";
					});					
					
					var link = baseUrl+'getFamilyEducationDetail?membersfamilyid='+membersfamilyid;
					$http.get(link).success(					
							function(data, status, headers, config)
							{
								$scope.getfamilyeducationdetail = data;
							}).
							error(function(data, status, headers, config)
							{
								$scope.getfamilyeducationdetail = "Response Fail";
							});
					
					var link = baseUrl+'getFamilyResidentialLandline?membersfamilyid='+membersfamilyid;
					$http.get(link).success(					
							function(data, status, headers, config)
							{
								$scope.getfamilyresidentiallandline = data;
							}).
							error(function(data, status, headers, config)
							{
								$scope.getfamilyresidentiallandline = "Response Fail";
							});
					
					var link = baseUrl+'getFamilyWorkLandline?membersfamilyid='+membersfamilyid;
					$http.get(link).success(					
							function(data, status, headers, config)
							{
								$scope.getfamilyworklandline = data;
							}).
							error(function(data, status, headers, config)
							{
								$scope.getfamilyworklandline = "Response Fail";
							});								
				}
			    
			    $scope.checkFrontLoginForMembersDirectory = function() 
				{				
					var username = $scope.username;
					var password = $scope.password;

					if (username == undefined || username == "") {
						$window.alert("Please enter username");
						document.getElementById("username").focus();
						return;
					} else if (password == undefined || password == "") {
						$window.alert("Please enter password");
						document.getElementById("password").focus();
						return;
					} else {
						var link = baseUrl + 'frontLogin?userName=' + username + '&password=' + password;
						$http.post(link).success(function(data, status, headers,config) 
						{
							if (data == "")
							{
								$window.alert("User Name or Password Incorrect...Try Again");
							}
							else
							{
								$window.location.href = url + 'members_directory';				
							}
						}).error(function(data, status, headers,config)
						{
							$window.alert("Some thing wrong...Try again");
						});
					}
				}
			    
			    var link = baseUrl+'getMember';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getmember = data;			
				}).error(function(data, status, headers, config) {
					$scope.getmember = "Response Fail";
				});
				
				var link = baseUrl+'getMemberAndMemberFamily';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.memberAndMemberFamilyList = data;			
				}).error(function(data, status, headers, config) {
					$scope.memberAndMemberFamilyList = "Response Fail";
				});
				
				var link = baseUrl + 'RotaryYear';
				$http.get(link).success(function(data,status,headers,config) {
					$scope.rotaryyear = data;
				}).error(function(data,status,headers,config) {
					$scope.rotaryyear = "Responce Fail";
				});
			    
			   var link = baseUrl + 'BoardOfDirectors';
					$http.get(link).success(function(data,status,headers,config) {
						$scope.boardofdirectors = data;
					}).error(function(data,status,headers,config) {
						$scope.boardofdirectors = "Responce Fail";
					});
					
				$scope.redirectToMemberProfile = function(id)
				{					
					$window.location.href = url + 'member_profile?id='+id;
				}
				
				$scope.getMemberDetailById = function(id) {					
					var link = baseUrl+'getMemberDetailByMemberId?memberid='+id;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getmemberdetail = data;
						$scope.memberid = $scope.getmemberdetail.memberId;
						$scope.firstname = $scope.getmemberdetail.memberFirstName;
						$scope.lastname = $scope.getmemberdetail.memberLastName;							
												
					}).error(function(data, status, headers, config) {
						$scope.getmember = "Response Fail";
					});
					
					var link = baseUrl+'getspousedata?memberid='+id;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getspousedata = data;
					}).error(function(data,status,headers,config) {
						$scope.getspousedata = "Response Fail";
					});
				}
				
				$scope.sendMessage = function(memberid) {					
					if($scope.userfirstname == undefined) {
						$window.alert("Please enter your first name");
						document.getElementById("userfirstname").focus();
						return;
					} else if($scope.userlastname == undefined) {
						$window.alert("Please enter your last name");
						document.getElementById("userlastname").focus();
						return;
					} else if($scope.useremail == undefined) {
						$window.alert("Please enter your email");
						document.getElementById("useremail").focus();
						return;
					} else if($scope.usermobileno == undefined) {
						$window.alert("Please enter your mobile number");
						document.getElementById("usermobileno").focus();
						return;
					} else if($scope.usermessage == undefined) {
						$window.alert("Please enter your message");
						document.getElementById("usermessage").focus();
						return;
					} else {
						$scope.spin = 1;
						var link = baseUrl+'sendUserMessage?memberid='+memberid+'&firstname='+$scope.userfirstname+'&lastname='+$scope.userlastname+'&email='+$scope.useremail+'&mobileno='+$scope.usermobileno+'&usermessage='+$scope.usermessage;				
						$http.post(link).success(function(data, status, headers, config) {
							$scope.sendusermesaage = data;
							$scope.spin = 0;							
							$window.alert("Your message has been send!");
							$window.location.href = url + 'members_directory';
						}).error(function(data, status, headers, config) {
							$scope.sendusermesaage = "Response Fail";
							$window.alert("Some problem occured! Please try again after some time!");
						});
					}
				}
				
				$scope.addBoardOfDirectors = function()
				{
					var id = $scope.addid.split('.');
					var memberid = 0;
					var memberfamilyid = 0;
					if(id[1] == 'm')
					{
						memberid = id[0];
						memberfamilyid = 0;
					}
					if(id[1] == 'f')
					{
						memberid = 0;
						memberfamilyid = id[0];
					}					
					var rotaryyearid = $scope.addrotaryyearid;
					var sequence = $scope.addsequence;
					var designation = $scope.adddesignation;		
					
					if(rotaryyearid == "" || rotaryyearid == undefined)
					{
						$window.alert("Please select rotary year");
						document.getElementById("rotaryyearid").focus();
						return;
					}
					else if(sequence == "" || sequence == undefined)
					{
						$window.alert("Please enter sequence number");
						document.getElementById("sequence").focus();
						return;
					}
					else if(designation == "" || designation == undefined)
					{
						$window.alert("Please enter designation");
						document.getElementById("designation").focus();
						return;
					}
					else if($scope.addid == "" || $scope.addid == undefined)
					{
						$window.alert("Please select member");
						document.getElementById("addid").focus();
						return;
					}
					else
					{
						var link = baseUrl+'addBoardOfDirectors?rotaryyearid='+rotaryyearid+'&sequence='+sequence+'&designation='+designation+'&memberid='+memberid+'&memberfamilyid='+memberfamilyid;
						
						$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addboardofdirectors = data;
								$window.alert("Data added successfuly");
								$window.location.href = adminurl + 'board_of_directors';
							}).
							error(function(data, status, headers, config)
							{
								$scope.addboardofdirectors = "Response Fail";
							});
					}
				}
				
				$scope.getBoardOfDirectorsDetail = function(id) 
				{			
					for (i in $scope.boardofdirectors) 
					{
						if ($scope.boardofdirectors[i].boardOfDirectorsId == id) 
						{
							$scope.rotaryyearid = $scope.boardofdirectors[i].rotaryYearId;
							$scope.sequence = $scope.boardofdirectors[i].sequence;
							$scope.designation = $scope.boardofdirectors[i].designation;
							$scope.editid = $scope.boardofdirectors[i].id;				
						}				
					}					
				}
				
				$scope.editBoardOfDirectors = function(id)
				{
					var editid = $scope.editid.split('.');
					var memberid = 0;
					var memberfamilyid = 0;
					if(editid[1] == 'm')
					{
						memberid = editid[0];
						memberfamilyid = 0;
					}
					if(editid[1] == 'f')
					{
						memberid = 0;
						memberfamilyid = editid[0];
					}
					
					var rotaryyearid = $scope.rotaryyearid;
					var sequence = $scope.sequence;
					var designation = $scope.designation;					
					
					if(rotaryyearid == "" || rotaryyearid == undefined)
					{
						$window.alert("Please select rotary year");
						document.getElementById("rotaryyearid").focus();
						return;
					}
					else if(sequence == "" || sequence == undefined)
					{
						$window.alert("Please enter sequence number");
						document.getElementById("sequence").focus();
						return;
					}
					else if(designation == "" || designation == undefined)
					{
						$window.alert("Please enter designation");
						document.getElementById("designation").focus();
						return;
					}
					else if(editid == "" || editid == undefined)
					{
						$window.alert("Please select member");
						document.getElementById("editid").focus();
						return;
					}
					else
					{
						var link = baseUrl+'editBoardOfDirectors?id='+id+'&rotaryyearid='+rotaryyearid+'&sequence='+sequence+'&designation='+designation+'&memberid='+memberid+'&memberfamilyid='+memberfamilyid;
						
						$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editdirectors = data;
								$window.alert("Data updated successfuly");
								$window.location.href = adminurl + 'board_of_directors';
							}).
							error(function(data, status, headers, config)
							{
								$scope.editdirectors = "Response Fail";
							});
					}
				}
				
				$scope.checkAll = function()
				{
					if ($scope.selectedAll)
					{
						$scope.selectedAll = false;
					}
					else
					{
			            $scope.selectedAll = true;
			        }
					angular.forEach($scope.boardofdirectors, function (item)
					{
						item.selected = $scope.selectedAll;
					});
				}
				
				$scope.deleteBoardOfDirectors = function()
				{
					deleteDirectors = $window.confirm('Are you sure you want to delete record?');
					if(deleteDirectors)
					{			
					    angular.forEach($scope.boardofdirectors,
					    		function(item)
					    		{		    			
					    			if (item.selected)
					    				{
					    					var link = baseUrl+'deleteBoardOfDirectors?id='+item.boardOfDirectorsId;
						    				$http['delete'](link).success(
						    						function(data, status, headers, config)
						    						{
						    							$scope.deleterotaryyear = data;
						    						}).
						    						error(function(data, status, headers, config)
						    						{
						    							$scope.deleterotaryyear = "Response Fail";
						    						});
					    				}
					    				
					    		});
						$window.location.href = adminurl+'board_of_directors';
					}
				}
				
				$scope.getCurrentDefaultYear = function ()
			    {					
					var link = baseUrl + 'RotaryYear';
					$http.get(link).success(
						function(data,status,headers,config)
						{
							$scope.rotaryyear = data;
							
							for (i in $scope.rotaryyear) 
							{
								if ($scope.rotaryyear[i].defaultYear == "y") 
								{						
									$scope.addrotaryyearid = $scope.rotaryyear[i].rotaryYearId;					
								}				
							}		
						})
						.error(function(data,status,headers,config)
						{
							$scope.rotaryyear = "Responce Fail";
						});
					 
			    }				
		});
	
	app.controller('committeeCtrl', function($scope, $http, $window, $filter, $location) 
			{
						var baseUrl = $location.protocol() + "://" + location.host + u;	
						
						$scope.currentPage = 0;
					    $scope.pageSize = 5;
					    $scope.search = '';
					    
					
						var link = baseUrl+'getAllFellowshipName';  
						
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getAllFellowshipNameList = data;
							
						}).error(function(data, status, headers, config) {
							$scope.getAllFellowshipNameList = "Response Fail";
						});
						
						
					
							var link = baseUrl+'getAllMemberNameList';  
						
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getAllMemberNamedata = data;
								
							}).error(function(data, status, headers, config) {
								$scope.getAllMemberNamedata = "Response Fail";
							});
							
						
						
					    $scope.getData = function ()
					    {
						     // needed for the pagination calc
					    	return $filter('filter')($scope.data, $scope.search);
					    }
					    
					    $scope.numberOfPages=function()
					    {
					    	return Math.ceil($scope.memberscommittee.length/$scope.pageSize);
					    }
					    
					    var link = baseUrl+'getMember';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getmember = data;			
						}).error(function(data, status, headers, config) {
							$scope.getmember = "Response Fail";
						});
						
						var link = baseUrl + 'RotaryYear';
						$http.get(link).success(function(data,status,headers,config) {
							$scope.rotaryyear = data;
						}).error(function(data,status,headers,config) {
							$scope.rotaryyear = "Responce Fail";
						});
					    
					   var link = baseUrl + 'MembersCommittee';
							$http.get(link).success(function(data,status,headers,config) {
								$scope.memberscommittee = data;
							}).error(function(data,status,headers,config) {
								$scope.memberscommittee = "Responce Fail";
							});
					   			
									
						
						$scope.addMembersCommittee = function()
						{					
							var rotaryyearid = $scope.addrotaryyearid;
							var sequence = $scope.addsequence;
							var designation = $scope.adddesignation;
							var memberid = $scope.addmemberid;							
							if(rotaryyearid == "" || rotaryyearid == undefined)
							{
								$window.alert("Please select rotary year");
								document.getElementById("rotaryyearid").focus();
								return;
							}
							else if(sequence == "" || sequence == undefined)
							{
								$window.alert("Please enter sequence number");
								document.getElementById("sequence").focus();
								return;
							}
							else if(designation == "" || designation == undefined)
							{
								$window.alert("Please enter designation");
								document.getElementById("designation").focus();
								return;
							}
							/*else if(memberid == "" || memberid == undefined)
							{
								$window.alert("Please select member");
								document.getElementById("memberid").focus();
								return;
							}*/
							else
							{
								var link = baseUrl+'addMembersCommittee?rotaryyearid='+rotaryyearid+'&sequence='+sequence+'&designation='+designation+'&memberid='+memberid;
						//		alert(link);
								$http.post(link).success(
									function(data, status, headers, config)
									{
										$scope.addmemberscommittee = data;
										$window.alert("Data added successfuly");
										$window.location.href = adminurl + 'members_committee';
									}).
									error(function(data, status, headers, config)
									{
										$scope.addmemberscommittee = "Response Fail";
									});
							}
						}
						
						$scope.getMembersCommitteeDetail = function(id) 
						{							
							for (i in $scope.memberscommittee) 
							{
								if ($scope.memberscommittee[i].membersCommitteeId == id) 
								{
									$scope.memberscommitteeid = $scope.memberscommittee[i].membersCommitteeId;
									$scope.sequence = $scope.memberscommittee[i].sequence;
									$scope.designation = $scope.memberscommittee[i].designation;
									$scope.memberid = $scope.memberscommittee[i].memberId;	
									$scope.rotaryyearid = $scope.memberscommittee[i].rotaryYearId;
									$scope.fellowship_id = $scope.memberscommittee[i].fellowship_id;	
								}				
							}
						}
						
						$scope.editMembersCommittee = function(id)
						{
							var rotaryyearid = $scope.rotaryyearid;
							var sequence = $scope.sequence;
							var designation = $scope.designation;
							var memberid = $scope.memberid;
							var fellowship_id = $scope.fellowship_id;
							
							if(rotaryyearid == "" || rotaryyearid == undefined)
							{
								$window.alert("Please select rotary year");
								document.getElementById("rotaryyearid").focus();
								return;
							}
							else if(sequence == "" || sequence == undefined)
							{
								$window.alert("Please enter sequence number");
								document.getElementById("sequence").focus();
								return;
							}
							else if(designation == "" || designation == undefined)
							{
								$window.alert("Please enter designation");
								document.getElementById("designation").focus();
								return;
							}
							else if(memberid == "" || memberid == undefined)
							{
								$window.alert("Please select member");
								document.getElementById("memberid").focus();
								return;
							}
							else
							{
								var link = baseUrl+'editMembersCommittee?id='+id+'&rotaryyearid='+rotaryyearid+'&sequence='+sequence+'&designation='+designation+'&memberid='+memberid+'&fellowship_id='+fellowship_id;								
							//alert(link);
								$http.post(link).success(
									function(data, status, headers, config)
									{
										$scope.editcommittee = data;
										$window.alert("Data updated successfuly");
										$window.location.href = adminurl + 'members_committee';
									}).
									error(function(data, status, headers, config)
									{
										$scope.editcommittee = "Response Fail";
									});
							}
						}
						
						$scope.checkAll = function()
						{
							if ($scope.selectedAll)
							{
								$scope.selectedAll = false;
							}
							else
							{
					            $scope.selectedAll = true;
					        }
							angular.forEach($scope.memberscommittee, function (item)
							{
								item.selected = $scope.selectedAll;
							});
						}
						
						$scope.deleteMembersCommittee = function()
						{
							deleteCommittee = $window.confirm('Are you sure you want to delete record?');
							if(deleteCommittee)
							{			
							    angular.forEach($scope.memberscommittee,
							    		function(item)
							    		{		    			
							    			if (item.selected)
							    				{
							    					var link = baseUrl+'deleteMembersCommittee?id='+item.membersCommitteeId;
								    				$http['delete'](link).success(
								    						function(data, status, headers, config)
								    						{
								    							$scope.deletecommittee = data;
								    						}).
								    						error(function(data, status, headers, config)
								    						{
								    							$scope.deletecommittee = "Response Fail";
								    						});
							    				}
							    				
							    		});
								$window.location.href = adminurl+'members_committee';
							}
						}
						
						$scope.getCurrentDefaultYear = function ()
					    {					
							var link = baseUrl + 'RotaryYear';
							$http.get(link).success(
								function(data,status,headers,config)
								{
									$scope.rotaryyear = data;
									
									for (i in $scope.rotaryyear) 
									{
										if ($scope.rotaryyear[i].defaultYear == "y") 
										{						
											$scope.addrotaryyearid = $scope.rotaryyear[i].rotaryYearId;					
										}				
									}									
								})
								.error(function(data,status,headers,config)
								{
									$scope.rotaryyear = "Responce Fail";
								});
							 
					    }
	});
	
	app.controller('socialMediaCtrl', function($scope, $http, $window, $filter, $location) 
	{
		var baseUrl = $location.protocol() + "://" + location.host + u;	
						
		$scope.currentPage = 0;
		$scope.pageSize = 5;
		$scope.search = '';
		    
		$scope.getData = function () {
			return $filter('filter')($scope.data, $scope.search);
		}
		
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getsocialmedialinks.length/$scope.pageSize);
		}
		
		var link = baseUrl+'getSocialMediaLinks';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getsocialmedialinks = data;
		}).error(function(data, status, headers, config) {
			$scope.getsocialmedialinks = "Response Fail";
		});
		
		$scope.addSocialMediaLink = function() {
			
			var mediaplatformtitle = $scope.mediaplatformtitle;
			var medialink = $scope.medialink;			
							
			if(mediaplatformtitle == "" || mediaplatformtitle == undefined) {
				$window.alert("Please select social media platform");
				document.getElementById("mediaplatformtitle").focus();
				return;
			} else if(medialink == "" || medialink == undefined) {
				$window.alert("Please enter link");
				document.getElementById("medialink").focus();
				return;
			} else {
				var link = baseUrl+'addSocialMediaLink?mediaplatformtitle='+mediaplatformtitle+'&medialink='+medialink;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addsocialmedialink = data;
					$window.alert("Data added successfuly");
					$window.location.href = adminurl + 'manage_social_media_links';
				}).error(function(data, status, headers, config) {
					$scope.addsocialmedialink = "Response Fail";
				});
			}
		}
		
		$scope.getSocialMediaLinkDetail = function(id) {
			for (i in $scope.getsocialmedialinks) {
				if ($scope.getsocialmedialinks[i].socialMediaLinkId == id) {					
					$scope.socialmedialinkid = $scope.getsocialmedialinks[i].socialMediaLinkId;
					$scope.editmediaplatformtitle = $scope.getsocialmedialinks[i].socialMediaPlatformTitle;
					$scope.editmedialink = $scope.getsocialmedialinks[i].socialMediaUrl;					
				}
			}
		}
		
		$scope.editSocialMediaLink = function(id) {
			var mediaplatformtitle = $scope.editmediaplatformtitle;
			var medialink = $scope.editmedialink;			
							
			if(mediaplatformtitle == "" || mediaplatformtitle == undefined) {
				$window.alert("Please select social media platform");
				document.getElementById("mediaplatformtitle").focus();
				return;
			} else if(medialink == "" || medialink == undefined) {
				$window.alert("Please enter link");
				document.getElementById("medialink").focus();
				return;
			} else {
				var link = baseUrl+'editSocialMediaLink?id='+id+'&mediaplatformtitle='+mediaplatformtitle+'&medialink='+medialink;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editsocialmedialink = data;
					$window.alert("Data updated successfuly");
					$window.location.href = adminurl + 'manage_social_media_links';
				}).error(function(data, status, headers, config) {
					$scope.editsocialmedialink = "Response Fail";
				});
			}
		}
		
		$scope.checkAll = function() {
			if ($scope.selectedAll) {
				$scope.selectedAll = false;
			} else {
				$scope.selectedAll = true;
			}
			angular.forEach($scope.getsocialmedialinks, function (item) {
				item.selected = $scope.selectedAll;
			});
		}
		
		$scope.deleteSocialMediaLink = function() {
			deleteLink = $window.confirm('Are you sure you want to delete record?');
			if(deleteLink) {
				angular.forEach($scope.getsocialmedialinks,function(item) {
					if (item.selected) {
						var link = baseUrl+'deleteSocialMediaLink?id='+item.socialMediaLinkId;
						$http['delete'](link).success(function(data, status, headers, config) {
							$scope.deletesocialmedialink = data;
						}).error(function(data, status, headers, config) {
							$scope.deletesocialmedialink = "Response Fail";
						});
					}
				});
				$window.location.href = adminurl+'manage_social_media_links';
			}
		}
	});
	
	app.controller('awardCtrl', ['$scope', '$filter', '$http', '$window', '$location' ,function ($scope, $filter, $http, $window, $location){
	    $scope.currentPage = 0;
	    $scope.pageSize = 20;
	    $scope.search = '';
	    
	    $scope.getData = function ()
	    {
	    	return $filter('filter')($scope.data, $scope.search)
	    }
	    
	    $scope.numberOfPages=function()
	    {
	    	return Math.ceil($scope.awards.length/$scope.pageSize);
	    } 
	    
	    var baseUrl = $location.protocol()+"://"+location.host+u;
	    
	    $scope.getBirthdaysAndAnniversariesByDate = function ()
	    {
			var currentdate = $filter('date')(new Date(),'MM-dd');
			
			var link = baseUrl+'getAllBirthdaysByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallbirthdaysbydate = "Response Fail";
			});
			
			var link = baseUrl+'getAllAnniversariesByDate?currentdate='+currentdate;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = data;			
			}).error(function(data, status, headers, config) {
				$scope.getallanniversariesbydate = "Response Fail";
			});
	    }	    
	    
	    var link = baseUrl+'Award';
		$http.get(link).success(function(data, status, headers, config)	{
			$scope.awards = data;
		}).error(function(data, status, headers, config) {
			$scope.awards = "Response Fail";
		});

		var link = baseUrl+'AwardImages';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.awardimages = data;
		}).error(function(data, status, headers, config) {
			$scope.awardimages = "Response Fail";
		});
		
		/*var link = baseUrl+'getAwardOneImage';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getawardoneimage = data;
		}).error(function(data, status, headers, config) {
			$scope.getawardoneimage = "Response Fail";
		});*/
		
		var link = baseUrl + 'RotaryYear';
		$http.get(link).success(function(data,status,headers,config) {
			$scope.rotaryyear = data;
		}).error(function(data,status,headers,config) {
			$scope.rotaryyear = "Responce Fail";
		});
				
		var formData = new FormData();
		$scope.awardimage = [{}];
				
		$scope.addAwardImageRow = function()
		{					
			var imagedescription = $scope.imagedescription;
					
			if(imagedescription==undefined || imagedescription=="")
			{
				imagedescription = "Description";
			}

			if($scope.imagetitle==undefined || $scope.imagetitle=="")
			{
				$window.alert("Please Enter Image Title!");
				document.getElementById("imagetitle").focus();
				return;
			}
			else
			{
				$scope.awardimage.push({'imageTitle':$scope.imagetitle, 'imageDescription':imagedescription});
				formData.append("image",image.files[0]);
			}					
		};
				
		$scope.removeAwardImageRow = function(imagetitle)
		{				
			var index = -1;
			var comArr = eval( $scope.awardimage);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].imageTitle === imagetitle ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.awardimage.splice( index, 1 );
		};	
		
		$scope.getCurrentDefaultYear = function ()
	    {
			var link = baseUrl + 'RotaryYear';
			$http.get(link).success(function(data,status,headers,config) {
				$scope.rotaryyear = data;
					
				for (i in $scope.rotaryyear) 
				{
					if ($scope.rotaryyear[i].defaultYear == "y") 
					{						
						$scope.rotaryyearid = $scope.rotaryyear[i].rotaryYearId;					
					}				
				} 
					
				var link = baseUrl + 'getAwardDetailByRotaryYearWithOneImage?rotaryyearid='+$scope.rotaryyearid;					
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getcurrentyearaward = data;
				}).error(function(data, status, headers, config) {
					$scope.getcurrentyearaward = "Response Fail";
				});					
			}).error(function(data,status,headers,config) {
				$scope.rotaryyear = "Responce Fail";
			});			 
	    }
				
		$scope.temp1 = 1;
		$scope.temp2 = 0;
		$scope.addAward = function()
		{
			var rotaryyear = $scope.rotaryyearid;					
			var title = $scope.awardtitle;
			var subtitle = $scope.awardsubtitle;
			var awarddate = $scope.awarddate;
			var description = $scope.description;			
												
			if(subtitle == undefined)
			{	
				subtitle = "";
			}					
			if(awarddate == undefined)
			{	
				awarddate = "";
			}										
			if(description==undefined)
			{
				description="";
			}	
			if (rotaryyear == undefined || rotaryyear == "") 
			{
				$window.alert("Please select rotary year!");
				document.getElementById("rotaryyearid").focus();
				return;			
			}
			else if(title==undefined || title=="")
			{
				$window.alert("Please Enter Album Title!");
				document.getElementById("title").focus();
				return;
			}
			else
			{
				$scope.temp1 = 0;
				$scope.temp2 = 1;
					
				var pap = title.replace("&","$");
				var pap1 = pap.replace("#","~");
				var pap2 = pap1.replace("%","!");
						
				var link = baseUrl + 'addAward?rotaryyearid='+rotaryyear+'&title='+pap2+'&subtitle='+subtitle+'&awarddate='+awarddate+'&description='+description;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addaward = data;					
					$scope.titleimage = [];
					$scope.desc = [];
					angular.forEach($scope.awardimage,function(item){
						if(item.imageTitle != null) {
							var tit = item.imageTitle.replace("&","$");
							var tit1 = tit.replace("#","~");
							var tit2 = tit1.replace("%","!");
							$scope.titleimage.push(tit2);
							$scope.desc.push(item.imageDescription);
						}
					});
					
					var link = baseUrl + 'addAwardImage?imagetitle='+$scope.titleimage+'&imagedescription='+$scope.desc;
					$http({
						method: 'POST',
						url: link,
						headers: {'Content-Type': undefined},
						data: formData,
						transformRequest: function(data, headersGetterFunction) {
							return data;
						}
					}).success(function(data, status, headers, config) {
						$scope.addalbumimage = data;
						$scope.temp1 = 1;
						$scope.temp2 = 0;
						
						$window.alert("Record added successfully...");
						$window.location.href = adminurl+'manage_awards';
					}).error(function(data, status, headers, config) {
						$scope.addawardimage = "Response Fail";
					});
				}).error(function() {
					$scope.addalbum = "Responce fail";
				});
			}																																																																							
		}
			
		$scope.getaward = function(awardid)
		{
			var link = baseUrl+'Award';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.awards = data;
				for(i in $scope.awards) {
					if ($scope.awards[i].awardId == awardid) {
						$scope.awardid = $scope.awards[i].awardId;
						$scope.awardtitle = $scope.awards[i].awardTitle;
		               	$scope.awardsubtitle = $scope.awards[i].awardSubtitle;
		               	$scope.awarddate = $scope.awards[i].awardDate;          	
		               	$scope.description = $scope.awards[i].awardDescription;
		               	$scope.rotaryyearid = $scope.awards[i].rotaryYearId;
					}
				}
			}).error(function(data, status, headers, config) {
				$scope.awards = "Response Fail";
			});
			
			var link = baseUrl+'AwardImage?awardid='+awardid;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.awardimage = data;
			}).error(function(data, status, headers, config) {
				$scope.awardimage = "Response Fail";
			});
		}
				
		$scope.removeRow1 = function(imagetitle)
		{				
			var index = -1;
			var comArr = eval( $scope.awardimage);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].imageTitle === imagetitle ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.awardimage.splice( index, 1 );
		};
				
		var formData = new FormData();
		$scope.awardimagenew = [{}];
				
		$scope.addAwardImageRowEdit = function() {
			if($scope.imagedescription==undefined) {
				$scope.imagedescription = "";
			}

			if($scope.imagetitle==undefined) {
				$window.alert("Please Enter Image Title!");
				document.getElementById("imagetitle").focus();
				return;
			} else {
				$scope.awardimagenew.push({'imageTitle':$scope.imagetitle, 'imageDescription':$scope.imagedescription});
				formData.append("image",image.files[0]);
			}
		};
		
		$scope.removeAwardImageRowEdit = function(imagetitle) {
			var index = -1;
			var comArr = eval( $scope.awardimagenew);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].imageTitle === imagetitle ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.awardimagenew.splice( index, 1 );
		};
		
		$scope.editAward = function(awardid) {
			var rotaryyear = $scope.rotaryyearid;					
			var title = $scope.awardtitle;
			var subtitle = $scope.awardsubtitle;
			var awarddate = $scope.awarddate;
			var description = $scope.description;		
			
			if(subtitle == undefined || subtitle == "") {
				subtitle = "";
			}
			if(awarddate == undefined || awarddate == "") {
				awarddate = "";
			}
			if(description==undefined || description=="") {
				description="";
			}
			if(title==undefined) {
				$window.alert("Please Enter Album Title!");
				document.getElementById("title").focus();
				return;
			} else if(rotaryyear == undefined){
				$window.alert("Please Select Rotary Year!");
				document.getElementById("rotaryyear").focus();
				return;
			} else {
				$scope.temp1 = 0;
				$scope.temp2 = 1;
				
				var link = baseUrl+'deleteSelectedAwardImage?awardid='+awardid;
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deleteselectedawardimage = data;
				}).error(function(data, status, headers, config) {
					$scope.deleteselectedawardimage = "Response Fail";
				});
				
				var pap = title.replace("&","$");
				var pap1 = pap.replace("#","~");
				var pap2 = pap1.replace("%","!");
				$scope.title = [];
				$scope.desc = [];
				angular.forEach($scope.awardimagenew,function(item) {
					if(item.imageTitle != null) {
						$scope.title.push(item.imageTitle);
						$scope.desc.push(item.imageDescription);
					}
				});
				var link = baseUrl + 'editAward?awardid='+awardid+'&rotaryyearid='+rotaryyear+'&title='+pap2+'&subtitle='+subtitle+'&awarddate='+awarddate+'&description='+description;
				$http.put(link).success(function(data, status, headers, config) {
					$scope.editaward = data;
					var link = baseUrl+'editAwardImage?awardid='+awardid+'&imagetitle='+$scope.title+'&imagedescription='+$scope.desc;
					$http({
						method: 'POST',
						url: link,
						headers: {'Content-Type': undefined},
						data: formData,
						transformRequest: function(data, headersGetterFunction) {
							return data;
						}
					}).success(function(data, status) {
						$scope.editawardimage = data;
						if($scope.awardimage.length == 0) {
							$scope.temp1 = 0;
							$scope.temp2 = 1;
							$window.alert("Record Updated Successfully");
							$window.location.href = adminurl+'manage_awards';
						}
						var a = 0;
						angular.forEach($scope.awardimage,function(item) {
							if(item.imageTitle != null) {
								var link = baseUrl+'editAwardImageNew?awardid='+awardid+'&imagetitle='+item.imageTitle+'&imagedescription='+item.description+'&image='+item.image;
								$http.post(link).success(function(data, status, headers, config) {
									$scope.editawardimagenew = data;
									a = a + 1;
									if(a == $scope.awardimage.length) {
										$scope.temp1 = 0;
										$scope.temp2 = 1;
										$window.alert("Record Updated Successfully");
										$window.location.href = adminurl+'manage_awards';
									}									
								}).error(function(data, status, headers, config) {
									$scope.editawardimagenew = "Response Fail";
								});
							}
						});
					}).error(function(data, status, headers, config) {
						$scope.editalbumimage = "Response Fail";
					});
				}).error(function(data, status, headers, config) {
					$scope.editalbum = "Response Fail";
				});
			}
		}
		
		$scope.checkAll = function() {
			if ($scope.selectedAll) {
				$scope.selectedAll = false;
			} else {
				$scope.selectedAll = true;
			}
			angular.forEach($scope.awards, function (item) {
				item.selected = $scope.selectedAll;
			});
		}
		$scope.deleteaward = function() {
			deleteAward = $window.confirm('Are you sure you want to delete record?');
			if(deleteAward) {
				angular.forEach($scope.awards,function(item) {
					if (item.selected) {
						var link = baseUrl+'deleteAward?awardid='+item.awardId;
						$http['delete'](link).success(function(data, status, headers, config) {
							$scope.deleteawards = data;
						}).error(function(data, status, headers, config) {
							$scope.deleteawards = "Response Fail";
						});
					}
				});
				$window.location.href = adminurl+'manage_awards';
			}
		}		
		
		$scope.getAwardDetailById = function(awardid)
		{			
			var link = baseUrl + 'getAwardDetailById?awardid='+awardid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getawarddetailbyid1 = data;
			}).error(function(data, status, headers, config) {
				$scope.getawarddetailbyid1 = "Response Fail";
			});
			
			var link = baseUrl+'AwardImage?awardid='+awardid;					
			$http.get(link).success(function(data, status, headers, config) {
				$scope.awardimage = data;
			}).error(function(data, status, headers, config) {
				$scope.awardimage = "Response Fail";
			});
		}
	}]);
	
	app.controller("beneficiaryTypeCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
		var baseUrl = $location.protocol()+"://"+location.host+u;		
		$scope.currentPage = 0;
	    $scope.pageSize = 20;
	    $scope.search = '';
			    
	    $scope.getData = function ()
	    {
	    	return $filter('filter')($scope.data, $scope.search);
	    }
				    
	    $scope.numberOfPages=function()
	    {
	    	return Math.ceil($scope.beneficiarytype.length/$scope.pageSize);
	    }
				
		var link = baseUrl + 'BeneficiaryType';
		$http.get(link).success(function(data,status,headers,config){
			$scope.beneficiarytype = data;
		}).error(function(data,status,headers,config){
			$scope.beneficiarytype = "Responce Fail";
		});
		
		$scope.addBeneficiaryType = function()
		{					
			var title = $scope.title;			
			
			if(title == "" || title == undefined)
			{
				$window.alert("Please enter title");
				document.getElementById("title").focus();
				return;
			} else {
				var link = baseUrl+'addBeneficiaryType?title='+title;				
				$http.post(link).success(function(data, status, headers, config){
					$scope.addbeneficiarytype = data;
					$window.alert("Data added successfuly");
					$window.location.href = adminurl + 'manage_beneficiary_type';
				}).error(function(data, status, headers, config){
					$scope.addbeneficiarytype = "Response Fail";
				});
			}
		}
			
		$scope.getBeneficiaryTypeDetail = function(beneficiarytypeid) 
		{						
			for (i in $scope.beneficiarytype) 
			{
				if ($scope.beneficiarytype[i].beneficiaryTypeId == beneficiarytypeid) 
				{
					$scope.beneficiarytypeid = $scope.beneficiarytype[i].beneficiaryTypeId;
					$scope.beneficiarytitle = $scope.beneficiarytype[i].beneficiaryTypeTitle;										
				}				
			}
		}
			
		$scope.editBeneficiaryType = function(id)
		{
			var title = $scope.beneficiarytitle;		
			
			if(title == "" || title == undefined)
			{
				$window.alert("Please enter title");
				document.getElementById("title").focus();
				return;
			} else {
				var link = baseUrl+'editBeneficiaryType?id='+id+'&title='+title;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editbeneficiarytype = data;
					$window.alert("Data updated successfuly");
					$window.location.href = adminurl + 'manage_beneficiary_type';
				}).error(function(data, status, headers, config) {
					$scope.editbeneficiarytype = "Response Fail";
				});
			}
		}
				
		$scope.checkAll = function()
		{
			if ($scope.selectedAll)
			{
				$scope.selectedAll = false;
			}
			else
			{
	            $scope.selectedAll = true;
	        }
			angular.forEach($scope.beneficiarytype, function (item)
			{
				item.selected = $scope.selectedAll;
			});
		}
			
		$scope.deleteBeneficiaryType = function()
		{
			deletebeneficiarytype = $window.confirm('Are you sure you want to delete record?');
			if(deletebeneficiarytype)
			{			
			    angular.forEach($scope.beneficiarytype,function(item) {
			    	if (item.selected) {
			    		var link = baseUrl+'deleteBeneficiaryType?id='+item.beneficiaryTypeId;
			    		$http['delete'](link).success(function(data, status, headers, config) {
			    			$scope.deletebeneficiarytype = data;
			    		}).error(function(data, status, headers, config) {
			    			$scope.deletebeneficiarytype = "Response Fail";
			    		});
			    	}
			    });
			    $window.location.href = adminurl+'manage_beneficiary_type';
			}
		}				
	});
	
	app.controller("serviceProjectBeneficiaryCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		$scope.currentPage = 0;
	    $scope.pageSize = 20;
	    $scope.search = '';
					    
	    $scope.getData = function () {
	    	return $filter('filter')($scope.data, $scope.search);
	    }
					    
	    $scope.numberOfPages=function() {
	    	return Math.ceil($scope.serviceproject.length/$scope.pageSize);
	    }
	    
	    var link = baseUrl + 'ServiceProject';
	    $http.get(link).success(function(data,status,headers,config) {
	    	$scope.serviceproject = data;
	    }).error(function(data,status,headers,config) {
	    	$scope.serviceproject = "Responce Fail";
	    });
		
		var link = baseUrl + 'BeneficiaryType';
		$http.get(link).success(function(data,status,headers,config){
			$scope.beneficiarytype = data;
		}).error(function(data,status,headers,config){
			$scope.beneficiarytype = "Responce Fail";
		});
			
		var link = baseUrl+'getMember';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
			
		$scope.spin = 0;
		
		$scope.addServiceProjectBeneficiary = function() {
			var serviceprojectid = $scope.serviceprojectid;
			var beneficiarytypeid = $scope.beneficiarytypeid;
			var beneficiaryid = $scope.beneficiaryid;
			var donorid = $scope.donorid;
			var startdate = document.getElementById("startdate").value;
			var enddate = document.getElementById("enddate").value;
			
			var startdate1 = "";
			var enddate1 = "";
			
			if(!startdate)
			{				
				startdate1 = "";
			}
			else
			{	
				var st = startdate.split('/');				
				if(st[0] > 31)
				{
					$window.alert("Please enter valid start date");
					document.getElementById("startdate").focus();
					return;
				}	
				else if(st[1] > 12)
				{
					$window.alert("Please enter valid start date");
					document.getElementById("startdate").focus();
					return;			
				}
				else
				{
					startdate1 = st[2]+"-"+st[1]+"-"+st[0];
				}
			}
			
			if(!enddate)
			{				
				enddate1 = "";
			}
			else
			{	
				var en = enddate.split('/');				
				if(en[0] > 31)
				{
					$window.alert("Please enter valid end date");
					document.getElementById("enddate").focus();
					return;
				}	
				else if(en[1] > 12)
				{
					$window.alert("Please enter valid end date");
					document.getElementById("enddate").focus();
					return;			
				}
				else
				{
					enddate1 = en[2]+"-"+en[1]+"-"+en[0];
				}
			}
			
			if(beneficiarytypeid == undefined || beneficiarytypeid == "")
			{
				beneficiarytypeid = 0;
			}
			
			if(serviceprojectid == undefined || serviceprojectid == "")
			{
				$window.alert("Please select service project");
				document.getElementById("serviceprojectid").focus();
				return;
			}
			else if(beneficiaryid == undefined || beneficiaryid == "")
			{
				$window.alert("Please select beneficiary");
				document.getElementById("beneficiaryid").focus();
				return;
			}
			else if(donorid == undefined || donorid == "")
			{
				$window.alert("Please select donor");
				document.getElementById("donorid").focus();
				return;
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addServiceProjectBeneficiary?serviceprojectid='+serviceprojectid+'&beneficiarytypeid='+beneficiarytypeid+'&beneficiaryid='+beneficiaryid+'&donorid='+donorid+'&startdate='+startdate1+'&enddate='+enddate1;													
				$http.post(link).success(
    				function(data, status, headers, config)
    				{						    							
    					$scope.addserviceprojectbeneficiary = data;
    					
    					$scope.spin = 0;
    					
    					$window.alert("Service Project Beneficiary Added Successfully");
						$window.location.href = adminurl+'manage_service_project_beneficiary';
    				}).
    				error(function(data, status, headers, config)
    				{
    					$scope.addserviceprojectbeneficiary = "Response Fail";
    				});
			}
		}
		
		
		$scope.getServiceProjectBeneficiary = function(serviceprojectid)
		{
			var link = baseUrl + 'getServiceProjectBeneficiaryByServiceProjectId?serviceprojectid='+serviceprojectid;
			$http.get(link).success(
				function(data,status,headers,config)
				{
					$scope.serviceprojectbeneficiary = data;
					$scope.serviceprojectname = $scope.serviceprojectbeneficiary[0].serviceProjectTitle;
				})
				.error(function(data,status,headers,config)
				{
					$scope.serviceprojectbeneficiary = "Responce Fail";
				});
			
		}
		
		$scope.getServiceProjectBeneficiary1 = function(serviceprojectbenificiaryid)
		{
			for(i in $scope.serviceprojectbeneficiary)
			{
				if($scope.serviceprojectbeneficiary[i].serviceProjectBeneficiaryId == serviceprojectbenificiaryid)
				{
					$scope.serviceprojectbeneficiaryid = $scope.serviceprojectbeneficiary[i].serviceProjectBeneficiaryId;
					$scope.serviceprojectidedit = $scope.serviceprojectbeneficiary[i].serviceProjectId;
					$scope.beneficiarytypeidedit = $scope.serviceprojectbeneficiary[i].beneficiaryTypeId;
					$scope.beneficiaryidedit = $scope.serviceprojectbeneficiary[i].beneficiaryId;
					$scope.donoridedit = $scope.serviceprojectbeneficiary[i].donorId;
					$scope.startdate1 = $scope.serviceprojectbeneficiary[i].startDate;
					$scope.startdateedit = $filter('date')($scope.startdate1, 'dd/MM/yyyy');
					$scope.enddate1 = $scope.serviceprojectbeneficiary[i].endDate;
					$scope.enddateedit = $filter('date')($scope.enddate1, 'dd/MM/yyyy');
				}
			}
		}
				
		$scope.editServiceProjectBeneficiary = function(serviceprojectbeneficiaryid)
		{
			var serviceprojectid = $scope.serviceprojectidedit;
			var beneficiarytypeid = $scope.beneficiarytypeidedit;
			var beneficiaryid = $scope.beneficiaryidedit;
			var donorid = $scope.donoridedit;
			var startdate = document.getElementById("startdateedit").value;
			var enddate = document.getElementById("enddateedit").value;
			
			var startdate1 = "";
			var enddate1 = "";
			
			if(!startdate)
			{				
				startdate1 = "";
			}
			else
			{	
				var st = startdate.split('/');				
				if(st[0] > 31)
				{
					$window.alert("Please enter valid start date");
					document.getElementById("startdateedit").focus();
					return;
				}	
				else if(st[1] > 12)
				{
					$window.alert("Please enter valid start date");
					document.getElementById("startdateedit").focus();
					return;			
				}
				else
				{
					startdate1 = st[2]+"-"+st[1]+"-"+st[0];
				}
			}
			
			if(!enddate)
			{				
				enddate1 = "";
			}
			else
			{	
				var en = enddate.split('/');				
				if(en[0] > 31)
				{
					$window.alert("Please enter valid end date");
					document.getElementById("enddateedit").focus();
					return;
				}	
				else if(en[1] > 12)
				{
					$window.alert("Please enter valid end date");
					document.getElementById("enddateedit").focus();
					return;			
				}
				else
				{
					enddate1 = en[2]+"-"+en[1]+"-"+en[0];
				}
			}
			
			if(beneficiarytypeid == undefined || beneficiarytypeid == "")
			{
				beneficiarytypeid = 0;
			}
			
			if(serviceprojectid == undefined || serviceprojectid == "")
			{
				$window.alert("Please select service project");
				document.getElementById("serviceprojectidedit").focus();
				return;
			}
			else if(beneficiaryid == undefined || beneficiaryid == "")
			{
				$window.alert("Please select beneficiary");
				document.getElementById("beneficiaryidedit").focus();
				return;
			}
			else if(donorid == undefined || donorid == "")
			{
				$window.alert("Please select donor");
				document.getElementById("donoridedit").focus();
				return;
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editServiceProjectBeneficiary?serviceprojectbeneficiaryid='+serviceprojectbeneficiaryid+'&serviceprojectid='+serviceprojectid+'&beneficiarytypeid='+beneficiarytypeid+'&beneficiaryid='+beneficiaryid+'&donorid='+donorid+'&startdate='+startdate1+'&enddate='+enddate1;
	    		$http.post(link).success(
    				function(data, status, headers, config)
    				{
    					$scope.editserviceprojectbeneficiary = data;
    					
    					$scope.spin = 0;
    					
    					$window.alert("Service Project Beneficiary Updaed Successfully");
						$window.location.href = adminurl+'manage_service_project_beneficiary';
    				}).
    				error(function(data, status, headers, config)
    				{
    					$scope.editserviceprojectbeneficiary = "Response Fail";
    				});
			}
		}
				
		$scope.checkAll = function()
		{
			if ($scope.selectedAll)
			{
				$scope.selectedAll = false;
			}
			else
			{
				$scope.selectedAll = true;
			}
			angular.forEach($scope.serviceprojectbeneficiary, function (item)
			{
				item.selected = $scope.selectedAll;
			});
		}
				
		$scope.deleteServiceProjectBeneficiary = function()
		{
			deleteserviceProjectBeneficiary = $window.confirm('Are you sure you want to delete this record?');
			if(deleteserviceProjectBeneficiary)
			{			
			    angular.forEach($scope.serviceprojectbeneficiary,
		  		function(item)
		   		{		    			
		   			if (item.selected)
					{
						var link = baseUrl+'deleteServiceProjectBeneficiary?id='+item.serviceProjectBeneficiaryId;
		   				$http['delete'](link).success(
							function(data, status, headers, config)
							{
								$scope.deleteserviceprojectbeneficiary = data;
							}).
							error(function(data, status, headers, config)
							{
								$scope.deleteserviceprojectbeneficiary = "Response Fail";
							});
		   				}
		  				
		   		});
			    $window.location.href = adminurl+'manage_service_project_beneficiary';
			}
		}	
	});
	
	app.controller("clubInfoCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
				var baseUrl = $location.protocol()+"://"+location.host+u;				
				$scope.getClubInfoDetail = function() 
				{					
					var link = baseUrl + 'getClubInfo';
					$http.get(link).success(function(data,status,headers,config){
						$scope.clubinfo = data;
						
						$scope.title = $scope.clubinfo.clubTitle;
						$scope.shorttitle = $scope.clubinfo.clubShortitle;
						$scope.clublogo = $scope.clubinfo.clubLogo;
						$scope.clubno = $scope.clubinfo.clubNo;
						$scope.districtno = $scope.clubinfo.districtNo;
						$scope.zoneno = $scope.clubinfo.zoneNo;
						$scope.add1 = $scope.clubinfo.meetingAddress1;
						$scope.add2 = $scope.clubinfo.meetingAddress2;
						$scope.day = $scope.clubinfo.meetingDay;
						$scope.time = $scope.clubinfo.meetingTime;
						$scope.map = $scope.clubinfo.mapLink;
						$scope.personname = $scope.clubinfo.contactPersonName;
						$scope.contactemail = $scope.clubinfo.contactEmail;
						$scope.contacttelephoneno = $scope.clubinfo.contactTelephoneNo;
						$scope.contactmobileno = $scope.clubinfo.contactMobileNo;
						$scope.contactaddress = $scope.clubinfo.contactAddress;
						$scope.id = $scope.clubinfo.clubInfoId;
						
						/*for (i in $scope.clubinfo) 
						{
							if ($scope.clubinfo[i].clubInfoId == 1) 
							{							
								$scope.title = $scope.clubinfo[i].clubTitle;
								$scope.shorttitle = $scope.clubinfo[i].clubShortitle;
								$scope.clublogo = $scope.clubinfo[i].clubLogo;
								$scope.clubno = $scope.clubinfo[i].clubNo;
								$scope.districtno = $scope.clubinfo[i].districtNo;
								$scope.zoneno = $scope.clubinfo[i].zoneNo;
								$scope.add1 = $scope.clubinfo[i].meetingAddress1;
								$scope.add2 = $scope.clubinfo[i].meetingAddress2;
								$scope.day = $scope.clubinfo[i].meetingDay;
								$scope.time = $scope.clubinfo[i].meetingTime;
								$scope.map = $scope.clubinfo[i].mapLink;
								$scope.personname = $scope.clubinfo[i].contactPersonName;
								$scope.contactemail = $scope.clubinfo[i].contactEmail;
								$scope.contacttelephoneno = $scope.clubinfo[i].contactTelephoneNo;
								$scope.contactmobileno = $scope.clubinfo[i].contactMobileNo;
								$scope.contactaddress = $scope.clubinfo[i].contactAddress;
							}				
						}*/
						
					}).error(function(data,status,headers,config){
						$scope.clubinfo = "Responce Fail";
					});				
				}
					
				$scope.updateclubinfo = function()
				{
					
					
					if(!$scope.id){
						var id = 0;
					} else {
						var id = $scope.id;
					}
					//var id = 1;
					var title = $scope.title;		
					var shorttitle = $scope.shorttitle;
					var clublogo = $scope.clublogo;
					var clubno = $scope.clubno;
					var districtno = $scope.districtno;
					var zoneno = $scope.zoneno;
					var add1 = $scope.add1;
					var add2 = $scope.add2;
					var day = $scope.day;
					var time = document.getElementById("timepicker").value;
					var map = $scope.map;
					var personname = $scope.personname;
					var contactemail = $scope.contactemail;
					var contacttelephoneno = $scope.contacttelephoneno;
					var contactmobileno = $scope.contactmobileno;
					var contactaddress = $scope.contactaddress;
					
					if(shorttitle == "" || shorttitle == undefined)
					{
						shorttitle = "";
					} 
					if(map == "" || map == undefined)
					{
						map = "";
					}
					if(clublogo == "" || clublogo == undefined)
					{
						clublogo = "";
					}
					if(clubno == "" || clubno == undefined)
					{
						clubno = "";
					}
					if(districtno == "" || districtno == undefined)
					{
						districtno = "";
					}
					if(zoneno == "" || zoneno == undefined)
					{
						zoneno = "";
					}
					if(contacttelephoneno == "" || contacttelephoneno == undefined)
					{
						contacttelephoneno = "";
					}
					if(contactmobileno == "" || contactmobileno == undefined)
					{
						contactmobileno = "";
					}
					if(contactaddress == "" || contactaddress == undefined)
					{
						contactaddress = "";
					}
					if(title == "" || title == undefined) {
						$window.alert("Please enter club name");
						document.getElementById("title").focus();
						return;
					} else if(add1 == "" || add1 == undefined) {
						$window.alert("Please enter address line 1");
						document.getElementById("add1").focus();
						return;
					} else if(add2 == "" || add2 == undefined) {
						$window.alert("Please enter address line 2");
						document.getElementById("add2").focus();
						return;
					} else if(day == "" || day == undefined) {
						$window.alert("Please select week day");
						document.getElementById("day").focus();
						return;
					} else if(time == "" || time == undefined) {
						$window.alert("Please enter time");
						document.getElementById("time").focus();
						return;
					} else if(personname == "" || personname == undefined) {
						$window.alert("Please enter contact person name");
						document.getElementById("personname").focus();
						return;
					} else if(contactemail == "" || contactemail == undefined) {
						$window.alert("Please enter contact email id");
						document.getElementById("contactemail").focus();
						return;
					} else {
						var link = baseUrl+'updateClubInfo?id='+id+'&title='+title+'&shorttitle='+shorttitle+'&clublogo='+clublogo+'&clubno='+clubno+'&districtno='+districtno+'&zoneno='+zoneno+'&add1='+add1+'&add2='+add2+'&day='+day+'&time='+time+'&map='+map+'&personname='+personname+'&contactemail='+contactemail+'&contacttelephoneno='+contacttelephoneno+'&contactmobileno='+contactmobileno+'&contactaddress='+contactaddress+'&oldimage='+$scope.clublogo;
						var formData = new FormData();
						formData.append("logo",logo.files[0]);
						$http({
						        method: 'POST',
						        url: link,
						        headers: {'Content-Type': undefined},
						        data: formData,
						        transformRequest: function(data, headersGetterFunction)
						        {
						        	return data;
						        }
						     })
						     .success(
								function(data, status, headers, config)
								{
									$scope.editclubinfo = data;
									$scope.temp1 = 1;
									$scope.temp2 = 0;
									$window.alert("Info Updated Successfully...");
									$window.location.href = adminurl+'manage_club_info';
								}).
								error(function(data, status, headers, config)
								{
									$scope.editclubinfo = "Response Fail";
								});
					}
				}							
	});
	
	app.controller("contactCtrl",function($window, $scope, $http, $location, $filter, $interval)
	{
				var baseUrl = $location.protocol()+"://"+location.host+u;	
				
				$scope.getClubInfoDetail = function() 
				{					
					var link = baseUrl + 'getClubInfo';
					$http.get(link).success(function(data,status,headers,config){
						$scope.clubinfo = data;
						
						for (i in $scope.clubinfo) 
						{
							if ($scope.clubinfo[i].clubInfoId == 1) 
							{	
								$scope.personname = $scope.clubinfo[i].contactPersonName;
								$scope.contactemail = $scope.clubinfo[i].contactEmail;
								$scope.contacttelephoneno = $scope.clubinfo[i].contactTelephoneNo;
								$scope.contactmobileno = $scope.clubinfo[i].contactMobileNo;
								$scope.contactaddress = $scope.clubinfo[i].contactAddress;
							}				
						}
						
					}).error(function(data,status,headers,config){
						$scope.clubinfo = "Responce Fail";
					});				
				}
				
				$scope.spin = 0;
				$scope.nospin = 1;			
				$scope.sendrequest = function()
				{					
					var fname = $scope.fname;		
					var lname = $scope.lname;
					var email = $scope.email;					
					var mobileno = $scope.mobileno;
					var request = $scope.request;					
									
					if(fname == "" || fname == undefined) {
						$window.alert("Please enter first name");
						document.getElementById("fname").focus();
						return;
					} else if(lname == "" || lname == undefined) {
						$window.alert("Please enter last name");
						document.getElementById("lname").focus();
						return;
					} else if(email == "" || email == undefined) {
						$window.alert("Please enter email id");
						document.getElementById("email").focus();
						return;
					} else if(mobileno == "" || mobileno == undefined) {
						$window.alert("Please enter mobile number");
						document.getElementById("mobileno").focus();
						return;
					} else if(request == "" || request == undefined) {
						$window.alert("Please enter your request or query");
						document.getElementById("request").focus();
						return;
					} else {
						$scope.spin = 1;
						$scope.nospin = 0;
						var link = baseUrl+'submitRequest?fname='+fname+'&lname='+lname+'&email='+email+'&mobileno='+mobileno+'&requestmsg='+request;						
						$http.post(link).success(function(data, status, headers, config) {
							$scope.submitrequest = data;
							$scope.spin = 0;
							$scope.nospin = 1;
							$window.alert("Your request send successfully");
							$window.location.href = url;
						}).error(function(data, status, headers, config) {
							$scope.submitrequest = "Response Fail";
						});
					}
				}							
	});
	
	app.controller('referralCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		var link = baseUrl+'getMember';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
		
		$scope.getMemberReferencesById = function(memberid) {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;			
			
			var link = baseUrl+'getMemberReferencesById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferences = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferences = "Response Fail";
			});
			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetail = data;
				for(i in $scope.getmemberdetail){
					$scope.firstname = $scope.getmemberdetail[i].memberFirstName;
					$scope.lastname = $scope.getmemberdetail[i].memberLastName;
					$scope.businesscategoryname = $scope.getmemberdetail[i].businessCategoryName;
					$scope.memberemail = $scope.getmemberdetail[i].memberEmail;
					$scope.membermobileno = $scope.getmemberdetail[i].memberMobileNumber;
					$scope.profilepic = $scope.getmemberdetail[i].memberProfilePicture;
				}
			}).error(function(data, status, headers, config) {
				$scope.getmemberdetail = "Response Fail";
			});
		}
		
		$scope.getReferencesByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getMemberReferencesById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberReferences = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberReferences = "Response Fail";
			});
		}
		
		$scope.saveReferral = function(temp) {
			$scope.referdate = document.getElementById("datepicker").value;
			if($scope.referralemail == undefined){
				$scope.referralemail = "";
			}
			if($scope.referraladdress == undefined){
				$scope.referraladdress = "";
			}
			if($scope.comment == undefined){
				$scope.comment = "";
			}
			if($scope.card == true){
				$scope.card = "Given your card";
			} else {
				$scope.card = "";
			}
			if($scope.call == true){
				$scope.call = "Told them you would call";
			} else {
				$scope.call = "";
			}
			if($scope.tomemberid == undefined || $scope.tomemberid == ""){
				$window.alert("Please select member!");
				document.getElementById("tomemberid").focus();
				return;
			} else if($scope.referralname == undefined){
				$window.alert("Please enter referral name!");
				document.getElementById("referralname").focus();
				return;
			} else if($scope.referdate == undefined){
				$window.alert("Please select refer date!");
				document.getElementById("datepicker").focus();
				return;
			} else if($scope.referraltype == undefined){
				$window.alert("Please select referral type!");
				document.getElementById("referraltype").focus();
				return;
			} else if(($scope.card == undefined && $scope.call == undefined) || ($scope.card == false && $scope.call == false)){
				$window.alert("Please select any referral status!");				
				return;
			} else if($scope.referralcontactno == undefined){
				$window.alert("Please enter referral contact number!");
				document.getElementById("referralcontactno").focus();
				return;
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'saveReferral?tomemberid='+$scope.tomemberid+'&referralname='+$scope.referralname+'&referdate='+$scope.referdate+'&referraltype='+$scope.referraltype+'&card='+$scope.card+'&call='+$scope.call+'&referralemail='+$scope.referralemail+'&referralcontactno='+$scope.referralcontactno+'&referraladdress='+$scope.referraladdress+'&comment='+$scope.comment;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.savereferral = data;
					$scope.spin = 0;					
					$window.alert("Data submitted successfully");
					if(temp == "new"){
						$window.location.href = url + 'referrals';
					} else {
						$window.location.href = url;
					};					
				}).error(function(data, status, headers, config) {
					$scope.savereferral = "Response Fail";
				});
			}
		};
	});
	
	app.controller('thankYouSlipCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		var link = baseUrl+'getMember';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
		
		$scope.getMemberBusinessById = function(memberid) {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;			
			
			var link = baseUrl+'getMemberBusinessById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberBusiness = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberBusiness = "Response Fail";
			});
			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetail = data;
				for(i in $scope.getmemberdetail){
					$scope.firstname = $scope.getmemberdetail[i].memberFirstName;
					$scope.lastname = $scope.getmemberdetail[i].memberLastName;
					$scope.businesscategoryname = $scope.getmemberdetail[i].businessCategoryName;
					$scope.memberemail = $scope.getmemberdetail[i].memberEmail;
					$scope.membermobileno = $scope.getmemberdetail[i].memberMobileNumber;
					$scope.profilepic = $scope.getmemberdetail[i].memberProfilePicture;
				}
			}).error(function(data, status, headers, config) {
				$scope.getmemberdetail = "Response Fail";
			});
		}
		
		$scope.getBusinessByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getMemberBusinessById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberBusiness = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberBusiness = "Response Fail";
			});
		}
		
		$scope.saveThankYouSlip = function(temp) {	
			$scope.slipdate = document.getElementById("datepicker").value;
			if($scope.comment == undefined){
				$scope.comment = "";
			}		
			if($scope.tomemberid == undefined || $scope.tomemberid == ""){
				$window.alert("Please select member!");
				document.getElementById("tomemberid").focus();
				return;
			} else if($scope.amount == undefined){
				$window.alert("Please enter amount!");
				document.getElementById("amount").focus();
				return;
			} else if($scope.slipdate == undefined){
				$window.alert("Please enter slip date!");
				document.getElementById("slipdate").focus();
				return;
			} else if($scope.businesstype == undefined){
				$window.alert("Please select business type!");
				document.getElementById("businesstype").focus();
				return;
			} else if($scope.referraltype == undefined){
				$window.alert("Please select referral type!");
				document.getElementById("referraltype").focus();
				return;
			}  else {
				$scope.spin = 1;				
				var link = baseUrl+'saveThankYouSlip?tomemberid='+$scope.tomemberid+'&amount='+$scope.amount+'&slipdate='+$scope.slipdate+'&businesstype='+$scope.businesstype+'&referraltype='+$scope.referraltype+'&comment='+$scope.comment;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.savethankyouslip = data;
					$scope.spin = 0;					
					$window.alert("Data submitted successfully");
					if(temp == "new"){
						$window.location.href = url + 'thankyou_slip';
					} else {
						$window.location.href = url;
					};					
				}).error(function(data, status, headers, config) {
					$scope.savethankyouslip = "Response Fail";
				});
			}
		};
	});
	
	app.controller('oneToOneCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;
		
		var link = baseUrl+'getMember';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getmember = data;			
		}).error(function(data, status, headers, config) {
			$scope.getmember = "Response Fail";
		});
		
		
		$scope.setNewMemberId = function() {
			$scope.memberid = $scope.metmemberid;
		}
		
		$scope.getMemberMeetingsDetailById = function(memberid) {	
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;			
			
			var link = baseUrl+'getMemberMeetingsById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberMeetings = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberMeetings = "Response Fail";
			});
			
			var link = baseUrl+'getMemberByMemberId?memberid='+memberid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmemberdetail = data;
				for(i in $scope.getmemberdetail){
					$scope.firstname = $scope.getmemberdetail[i].memberFirstName;
					$scope.lastname = $scope.getmemberdetail[i].memberLastName;
					$scope.businesscategoryname = $scope.getmemberdetail[i].businessCategoryName;
					$scope.memberemail = $scope.getmemberdetail[i].memberEmail;
					$scope.membermobileno = $scope.getmemberdetail[i].memberMobileNumber;
					$scope.profilepic = $scope.getmemberdetail[i].memberProfilePicture;
				}
			}).error(function(data, status, headers, config) {
				$scope.getmemberdetail = "Response Fail";
			});
		}
		
		$scope.getMeetingsByDate = function(memberid) {
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getMemberMeetingsById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberMeetings = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberMeetings = "Response Fail";
			});
		}
		
		$scope.saveOnetoOne = function(temp) {			
			$scope.meetingdate = document.getElementById("datepicker").value;		
			if($scope.metmemberid == undefined || $scope.metmemberid == ""){
				$window.alert("Please select member!");
				document.getElementById("metmemberid").focus();
				return;
			} else if($scope.invitedbymemberid == undefined){
				$window.alert("Please select invited by member!");
				document.getElementById("invitedbymemberid").focus();
				return;
			} else if($scope.meetingdate == undefined){
				$window.alert("Please select meeting date!");
				document.getElementById("meetingdate").focus();
				return;
			} else if($scope.location == undefined){
				$window.alert("Please enter meeting location!");
				document.getElementById("location").focus();
				return;
			} else if($scope.topic == undefined){
				$window.alert("Please enter topics of conversation!");
				document.getElementById("topic").focus();
				return;
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'saveOnetoOne?metmemberid='+$scope.metmemberid+'&invitedbymemberid='+$scope.invitedbymemberid+'&meetingdate='+$scope.meetingdate+'&location='+$scope.location+'&topic='+$scope.topic;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.saveonetoone = data;
					$scope.spin = 0;					
					$window.alert("Data submitted successfully");
					if(temp == "new"){
						$window.location.href = url + 'one_to_one';
					} else {
						$window.location.href = url;
					};					
				}).error(function(data, status, headers, config) {
					$scope.saveonetoone = "Response Fail";
				});
			}
		};		
	});
	
	app.controller('chapterSummaryCtrl',function($scope, $http, $window, $filter, $location) {
		var baseUrl = $location.protocol()+"://"+location.host+u;	
		
		$scope.getChapterSummary = function() {			
			$scope.currentDate = $filter('date')(new Date(), 'dd');
			$scope.currentMonth = $filter('date')(new Date(), 'MM');
			$scope.currentYear = $filter('date')(new Date(), 'yyyy');
			$scope.fromdate = "0"+1+"/"+$scope.currentMonth+"/"+$scope.currentYear;		
			document.getElementById("datepicker").value = $scope.fromdate;
			$scope.todate = $scope.currentDate+"/"+$scope.currentMonth+"/"+$scope.currentYear;
			document.getElementById("datepicker1").value = $scope.todate;			
			
			var link = baseUrl+'getChapterSummaryByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getChapterSummaryByDate = data;	
				
				$scope.totalReferenceGivenInside = 0;
				$scope.totalReferenceGivenOutside = 0;
				$scope.totalReferenceReceivedInside = 0;
				$scope.totalReferenceReceivedOutside = 0;
				$scope.totalMemberMeetingCount = 0;
				$scope.totalMemberBusinessTransactionCount = 0;
				
				for(i in $scope.getChapterSummaryByDate){
					$scope.totalReferenceGivenInside = $scope.totalReferenceGivenInside + $scope.getChapterSummaryByDate[i].referenceGivenInside;
					$scope.totalReferenceGivenOutside = $scope.totalReferenceGivenOutside + $scope.getChapterSummaryByDate[i].referenceGivenOutside;
					$scope.totalReferenceReceivedInside = $scope.totalReferenceReceivedInside + $scope.getChapterSummaryByDate[i].referenceReceivedInside;
					$scope.totalReferenceReceivedOutside = $scope.totalReferenceReceivedOutside + $scope.getChapterSummaryByDate[i].referenceReceivedOutside;
					$scope.totalMemberMeetingCount = $scope.totalMemberMeetingCount + $scope.getChapterSummaryByDate[i].memberMeetingCount;
					$scope.totalMemberBusinessTransactionCount = $scope.totalMemberBusinessTransactionCount + $scope.getChapterSummaryByDate[i].memberBusinessTransactionCount;
				}
				
			}).error(function(data, status, headers, config) {
				$scope.getChapterSummaryByDate = "Response Fail";
			});		
		}
		
		$scope.getMemberBusinessdetails = function(memberid,fromdate,todate) {	
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			var link = baseUrl+'getMemberBusinessForChapterSummaryById?memberid='+memberid+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMemberBusiness = data;			
			}).error(function(data, status, headers, config) {
				$scope.getMemberBusiness = "Response Fail";
			});
		}
		
		$scope.getChaptersSummary = function() {
			
			$scope.fromdate = document.getElementById("datepicker").value;
			$scope.todate = document.getElementById("datepicker1").value;
			
			var link = baseUrl+'getChapterSummaryByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getChapterSummaryByDate = data;	
				
				$scope.totalReferenceGivenInside = 0;
				$scope.totalReferenceGivenOutside = 0;
				$scope.totalReferenceReceivedInside = 0;
				$scope.totalReferenceReceivedOutside = 0;
				$scope.totalMemberMeetingCount = 0;
				$scope.totalMemberBusinessTransactionCount = 0;
				
				for(i in $scope.getChapterSummaryByDate){
					$scope.totalReferenceGivenInside = $scope.totalReferenceGivenInside + $scope.getChapterSummaryByDate[i].referenceGivenInside;
					$scope.totalReferenceGivenOutside = $scope.totalReferenceGivenOutside + $scope.getChapterSummaryByDate[i].referenceGivenOutside;
					$scope.totalReferenceReceivedInside = $scope.totalReferenceReceivedInside + $scope.getChapterSummaryByDate[i].referenceReceivedInside;
					$scope.totalReferenceReceivedOutside = $scope.totalReferenceReceivedOutside + $scope.getChapterSummaryByDate[i].referenceReceivedOutside;
					$scope.totalMemberMeetingCount = $scope.totalMemberMeetingCount + $scope.getChapterSummaryByDate[i].memberMeetingCount;
					$scope.totalMemberBusinessTransactionCount = $scope.totalMemberBusinessTransactionCount + $scope.getChapterSummaryByDate[i].memberBusinessTransactionCount;
				}
				
			}).error(function(data, status, headers, config) {
				$scope.getChapterSummaryByDate = "Response Fail";
			});
		}			
	});
	
	
	/*Business Controller*/
	

	app.controller('businessCtrl',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location)
		{
			$scope.currentPage = 0;
			$scope.pageSize = 20;
			$scope.search = '';	

			$scope.getData = function() {
				return $filter('filter')($scope.data,$scope.search)
			}

			$scope.numberOfPages = function() {
				return Math.ceil($scope.business.length/$scope.pageSize);
			}
			
			var baseUrl = $location.protocol() + "://"+location.host + u;
			
						
			var link = baseUrl + 'business';			
			$http.get(link).success(function(data, status, headers, config) { 
				$scope.business = data;
			}).error(function(data, status, headers, config) {
				$scope.business = "Response Fail";
			});		
					
			
			
			$scope.temp1 = 1;
			$scope.temp2 = 0;			
			
			$scope.getBusinessDetail = function(thankyouslipid) 
			{			
				var link = baseUrl + 'getBusinessDetailById?thankyouslipid='+thankyouslipid;				
				$http.get(link).success(function(data, status, headers, config) { 
					$scope.getBusinessbyid = data;
				}).error(function(data, status, headers, config) {
					$scope.getBusinessbyid = "Response Fail";
				});	
			}		
			
			
			$scope.checkAll = function()
			{
				if ($scope.selectedAll)
				{
					$scope.selectedAll = false;
				}
				else
				{
		            $scope.selectedAll = true;
		        }
				angular.forEach($scope.business, function (item)
				{
					item.selected = $scope.selectedAll;
				});
			}
			
			$scope.deleteBusiness = function()
			{
				deleteBusiness = $window.confirm('Are you sure you want to delete record?');
				if(deleteBusiness)
				{			
				    angular.forEach($scope.business,
				    		function(item)
				    		{		    			
				    			if (item.selected)
				    				{
				    					var link = baseUrl+'deleteBusiness?id='+item.thankYouSlipId;
					    				$http['delete'](link).success(
					    						function(data, status, headers, config)
					    						{
					    							$scope.businessdelete = data;
					    						}).
					    						error(function(data, status, headers, config)
					    						{
					    							$scope.businessdelete = "Response Fail";
					    						});
				    				}
				    				
				    		});
					$window.location.href = adminurl+'manage_business_transaction';
				}
			}
			
			/*$scope.getNewsDetailById = function(id,projectid)
			{		
				var link = baseUrl + 'getNewsDetailById?id='+id+'&projectid='+projectid;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getnewsdetailbyid = data;
				}).error(function(data, status, headers, config) {
					$scope.getnewsdetailbyid = "Response Fail";
				});
			}*/	
	} ]);
	
	app.controller('meetingCtrl',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location)
		{			
			$scope.currentPage = 0;
			$scope.pageSize = 20;
			$scope.search = '';	

			$scope.getData = function() {
				return $filter('filter')($scope.data,$scope.search)
			}

			$scope.numberOfPages = function() {
				return Math.ceil($scope.meeting.length/$scope.pageSize);
			}
			
			var baseUrl = $location.protocol() + "://"+location.host + u;
			
						
			var link = baseUrl + 'meeting';			
			$http.get(link).success(function(data, status, headers, config) { 
				$scope.meeting = data;
			}).error(function(data, status, headers, config) {
				$scope.meeting = "Response Fail";
			});		
					
			
			
			$scope.temp1 = 1;
			$scope.temp2 = 0;			
			
			$scope.getMeetingDetail = function(onetooneid) 
			{			
				var link = baseUrl + 'getMeetingDetailById?onetooneid='+onetooneid;				
				$http.get(link).success(function(data, status, headers, config) { 
					$scope.getMeetingbyid = data;
				}).error(function(data, status, headers, config) {
					$scope.getMeetingbyid = "Response Fail";
				});	
			}		
			
			
			$scope.checkAll = function()
			{
				if ($scope.selectedAll)
				{
					$scope.selectedAll = false;
				}
				else
				{
		            $scope.selectedAll = true;
		        }
				angular.forEach($scope.meeting, function (item)
				{
					item.selected = $scope.selectedAll;
				});
			}
			
			$scope.deleteMeeting = function()
			{
				deleteMeeting = $window.confirm('Are you sure you want to delete record?');
				if(deleteMeeting)
				{			
				    angular.forEach($scope.meeting,
				    		function(item)
				    		{		    			
				    			if (item.selected)
				    				{
				    					var link = baseUrl+'deleteMeeting?meetingid='+item.oneToOneId;
					    				$http['delete'](link).success(
					    						function(data, status, headers, config)
					    						{
					    							$scope.meetingdelete = data;
					    						}).
					    						error(function(data, status, headers, config)
					    						{
					    							$scope.meetingdelete = "Response Fail";
					    						});
				    				}
				    				
				    		});
					$window.location.href = adminurl+'manage_members_meeting';
				}
			}
			
			/*$scope.getNewsDetailById = function(id,projectid)
			{		
				var link = baseUrl + 'getNewsDetailById?id='+id+'&projectid='+projectid;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getnewsdetailbyid = data;
				}).error(function(data, status, headers, config) {
					$scope.getnewsdetailbyid = "Response Fail";
				});
			}*/	
	} ]);
	
	app.controller('referenceCtrl',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location)
		{			
			$scope.currentPage = 0;
			$scope.pageSize = 20;
			$scope.search = '';	

			$scope.getData = function() {
				return $filter('filter')($scope.data,$scope.search)
			}

			$scope.numberOfPages = function() {
				return Math.ceil($scope.reference.length/$scope.pageSize);
			}
			
			var baseUrl = $location.protocol() + "://"+location.host + u;
			
						
			var link = baseUrl + 'reference';			
			$http.get(link).success(function(data, status, headers, config) { 
				$scope.reference = data;
			}).error(function(data, status, headers, config) {
				$scope.reference = "Response Fail";
			});		
					
			var link = baseUrl+'getAllMembersDirectory';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember = data;			
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});	
			
			$scope.temp1 = 1;
			$scope.temp2 = 0;			
			
			$scope.updateReferral = function(Referralid) {
				$scope.referdate = document.getElementById("datepicker").value;
				$scope.closedate = document.getElementById("datepicker2").value;
				if($scope.getReferencebyid.email == undefined){
					$scope.getReferencebyid.email = "";
				}
				if($scope.getReferencebyid.apprValue == undefined){
					$scope.getReferencebyid.apprValue = "";
				}
				if($scope.getReferencebyid.address == undefined){
					$scope.getReferencebyid.address = "";
				}
				if($scope.getReferencebyid.comments == undefined){
					$scope.getReferencebyid.comments = "";
				}
				if($scope.getReferencebyid.referralStatus1){
					$scope.referralStatus1 = "Given your card";
				} else {
					$scope.referralStatus1 = "";
				}
				if($scope.getReferencebyid.referralStatus2){
					$scope.referralStatus2 = "Told them you would call";
				} else {
					$scope.referralStatus2 = "";
				}
				if(!$scope.getReferencebyid.CloseComment)
				{
					$scope.getReferencebyid.CloseComment="";
				}
				
				
				
				 if(!$scope.getReferencebyid.toMemberId){
					$window.alert("Please select member!");
					document.getElementById("tomemberid").focus();
					return;
				} else if(!$scope.getReferencebyid.referralName){
					$window.alert("Please enter referral name!");
					document.getElementById("referralname").focus();
					return;
				} else if($scope.referdate == undefined){
					$window.alert("Please select refer date!");
					document.getElementById("datepicker").focus();
					return;
				} else if($scope.closedate == undefined){
					$window.alert("Please select Close by date date!");
					document.getElementById("datepicker2").focus();
					return;
				} else if(!$scope.getReferencebyid.referralType){
					$window.alert("Please select referral type!");
					document.getElementById("referraltype").focus();
					return;
				} else if($scope.referralStatus1 != "Given your card" && $scope.referralStatus2 != "Told them you would call"){
					$window.alert("Please select any connection! ");				
					return;
				} else if(!$scope.getReferencebyid.referralStatus){
					$window.alert("Please select referral Status!");
					document.getElementById("referalstatus").focus();
					return;
				} else if(!$scope.getReferencebyid.contactNumber){
					$window.alert("Please enter referral contact number!");
					document.getElementById("referralcontactno").focus();
					return;
				} else if(!$scope.getReferencebyid.CloseReason && $scope.getReferencebyid.referralStatus == "C"){
					$window.alert("Please select reason!");
					document.getElementById("CloseReason").focus();
					return;
				} else {
					$scope.spin = 1;				
					var link = baseUrl+'updateReferral?Referralid='+Referralid+'&tomemberid='+$scope.getReferencebyid.toMemberId+'&referralname='+$scope.getReferencebyid.referralName+'&referdate='+$scope.referdate+'&referraltype='+$scope.getReferencebyid.referralType+'&card='+$scope.referralStatus1+'&call='+$scope.referralStatus2+'&referralemail='+$scope.getReferencebyid.email+'&referralcontactno='+$scope.getReferencebyid.contactNumber+'&referraladdress='+$scope.getReferencebyid.address+'&comment='+$scope.getReferencebyid.comments+'&closedate='+$scope.closedate+'&referalstatus='+$scope.getReferencebyid.referralStatus+'&apprvalue='+$scope.getReferencebyid.apprValue+'&closeComment='+$scope.getReferencebyid.CloseComment+'&closeReason='+$scope.getReferencebyid.CloseReason;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.savereferral = data;
						$scope.spin = 0;
						$window.alert("Data submitted successfully");
						location.reload(true);					
					}).error(function(data, status, headers, config) {
						$scope.savereferral = "Response Fail";
					});
				}
			};
			
			$scope.getReferenceDetail = function(memberreferralid,fromfirstname,fromlastname) 
			{			
				var link = baseUrl + 'getReferenceDetailById?memberreferralid='+memberreferralid;				
				$http.get(link).success(function(data, status, headers, config) { 
					$scope.getReferencebyid = data;
					$scope.fromMemberName = fromfirstname+" "+fromlastname;
				}).error(function(data, status, headers, config) {
					$scope.getReferencebyid = "Response Fail";
				});	
			}		
			
			
			$scope.checkAll = function()
			{
				if ($scope.selectedAll)
				{
					$scope.selectedAll = false;
				}
				else
				{
		            $scope.selectedAll = true;
		        }
				angular.forEach($scope.reference, function (item)
				{
					item.selected = $scope.selectedAll;
				});
			}
			
			$scope.deleteRef = function()
			{
				deleteRef = $window.confirm('Are you sure you want to delete record?');
				if(deleteRef)
				{			
				    angular.forEach($scope.reference,
				    		function(item)
				    		{		    			
				    			if (item.selected)
				    				{
				    					var link = baseUrl+'deleteRef?referenceid='+item.memberReferralId;
					    				$http['delete'](link).success(
					    						function(data, status, headers, config)
					    						{
					    							$scope.referencedelete = data;
					    						}).
					    						error(function(data, status, headers, config)
					    						{
					    							$scope.referencedelete = "Response Fail";
					    						});
				    				}
				    				
				    		});
					$window.location.href = adminurl+'manage_reference';
				}
			}		
			
	} ]);


