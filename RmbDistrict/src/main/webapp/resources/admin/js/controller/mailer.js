app.controller('rmbbMailer',['$scope','$filter','$http','$window','$location',function($scope, $filter, $http, $window, $location)
		{	
			var baseUrl = $location.protocol() + "://"+location.host + u;
			$scope.regUsers="All";
			$scope.MemberLi="All";
			$scope.regMem;
			$scope.spin =0;
			
			var link = baseUrl+'getAllMemberContactInfoForMailer';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getmember = data;			
			}).error(function(data, status, headers, config) {
				$scope.getmember = "Response Fail";
			});	
			
			
			
			var link = baseUrl + 'Events';
			$http.get(link).success(function(data, status, headers, config)
			{
				$scope.events = data;
			}).error(function(data, status, headers, config) 
			{
				$scope.events = "Response Fail";
			});
			
			
			$scope.getMembers = function(){
				var link = baseUrl + 'getAllRegisteredMembersByEventID?eventid='+$scope.eventDrop;
				$http.get(link).success(function(data, status, headers, config)
				{
					$scope.RegisteredMembers = data;
				}).error(function(data, status, headers, config) 
				{
					$scope.RegisteredMembers = "Response Fail";
				});
				
				
			}
			
			$scope.memberlistUpdate = function(input){
				if($scope.membertype == "")
				{
					return true;
				}
				return angular.equals(input, $scope.membertype);
			}
			
			$scope.malierList = [];
			$scope.addEventMailerList = function(){
				if($scope.regUsers == "")
				{
					$scope.regUserError=1;
					$scope.regUserMsg = "Please select a Registered User";
				}
				else if($scope.regUsers == "All"){
					
					for(i=0;i<$scope.regMem.length;i++)
					{
						$scope.malierList.push({'memberEmail':$scope.regMem[i].emailId, 'memberFirstName': $scope.regMem[i].firstName, 'memberLastName': $scope.regMem[i].lastName, 'memberMobileNumber': $scope.regMem[i].mobileNo});
					}
				}else{
					for(i=0;i<$scope.regMem.length;i++)
					{
						if($scope.regUsers == $scope.regMem[i].memberId)
						{
							$scope.malierList.push({'memberEmail':$scope.regMem[i].emailId, 'memberFirstName': $scope.regMem[i].firstName, 'memberLastName': $scope.regMem[i].lastName, 'memberMobileNumber': $scope.regMem[i].mobileNo});
						}
					}
				}
			}
			
			$scope.addMemberMailerList = function(){
				if($scope.MemberLi == "")
				{
					$scope.memLiError=1;
					$scope.memLirMsg = "Please select a Member";
				}
				else if($scope.MemberLi == "All"){
					
					for(i=0;i<$scope.getmember.length;i++)
					{
						$scope.malierList.push({'memberEmail':$scope.getmember[i].memberEmail, 'memberFirstName': $scope.getmember[i].memberFirstName, 'memberLastName': $scope.getmember[i].memberLastName, 'memberMobileNumber': $scope.getmember[i].memberMobileNumber});
					}
				}else{
					for(i=0;i<$scope.getmember.length;i++)
					{
						if($scope.MemberLi == $scope.getmember[i].memberId)
						{
							$scope.malierList.push({'memberEmail':$scope.getmember[i].memberEmail, 'memberFirstName': $scope.getmember[i].memberFirstName, 'memberLastName': $scope.getmember[i].memberLastName, 'memberMobileNumber': $scope.getmember[i].memberMobileNumber});
						}
					}
				}
			}
			
			
			$scope.removeEventMailerList = function(item) {
				var index = -1;
				for(var i=0; i<$scope.malierList.length; i++) {
					if($scope.malierList[i] == item){
						index = i;
						break;
					}
				}
				if(index < 0) {
					$window.alert("Error while removing record...Please try again!");
					return;
				}
				$scope.malierList.splice(index, 1);
			};
			$scope.emailList = [];
			$scope.phoneList = [];
			$scope.sendMail = function()
			{
				$scope.spin =1;
				$scope.mailContent = CKEDITOR.instances.mailContent.getData();
				
				
				if(!$scope.mailContent)
				{
					$scope.mailContError=1;
					$scope.mailContMsg = "Please enter content";
					document.getElementById("mailContent").focus();
				}
				else if(!$scope.malierList.length)
				{
					alert("mailer list is empty");
					return;
				}
				else{
					
					for(i=0;i<$scope.malierList.length;i++)
					{
						$scope.emailList.push($scope.malierList[i].email);
						$scope.phoneList.push($scope.malierList[i].mobileNo);
					}
					
					if(!$scope.smsSE){
						$scope.smsSE = false;
					}
					if(!$scope.emailSE){
						$scope.emailSE = false;
					}
					var pw = $scope.mailContent.replace(/&/g,"$");
					var pw1 = pw.replace(/#/g,"~");
					var pw2 = pw1.replace(/%/g,"!");
					console.log("pw2= "+pw2);
					//var cont = encodeURI(pw);
					console.log("cont= "+pw2);
					alert("Sending message in background you can continue with your work");
					 var mai = {};
					 var mailerList = $scope.malierList;
					 
					 mai = {'members':mailerList, 'Message':pw2}
					
					var link = baseUrl +"emailSmsBlast?phoneCheck="+$scope.smsSE+"&emailCheck="+$scope.emailSE;
					 $http({
							method : 'POST',
							url : link,
							data : mai
						})
						.success(function(data, status)
						{
						$scope.addeventcharge = data;
						$scope.spin = 0;
						$window.location.reload();
					}).error(function(data,status,headers,config) {
						$scope.addeventcharge = "Response Fail";
					});
				}
			}
			
			const escapeRegExp = (string) => {
				   return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')
				}
			
	} ]);
