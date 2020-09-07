	app.controller('sliderCtrl', ['$scope', '$filter', '$http', '$window', '$location' ,function ($scope, $filter, $http, $window, $location){
		
			
						$scope.currentPage = 0;
						$scope.pageSize = 20;
						$scope.search = '';
						$scope.startindex = $scope.currentPage;
					    
					    $scope.pages = [5, 10, 20, 50, 100, 'All'];
						
						$scope.info = 0;
						$scope.success = 0;
						$scope.spin = 0;
				    
						$scope.numberOfPages=function()
						{
							return Math.ceil($scope.getSliders.length/$scope.pageSize);
						}
					    
					    var baseUrl = $location.protocol()+"://"+location.host+url;
					    
					    var link = baseUrl+'getAllCounts';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.allcounts = data;
						}). error(function(data, status, headers, config) {
							$scope.allcounts = "Response Fail";
						});
					    
					    /*$http.get("https://ipinfo.io/json").then(function (response) {
							$scope.ipaddress = response.data.ip;
						});*/
						
						$http.get("https://api.ipify.org/?format=json").then(function (response) {
							$scope.ipaddress = response.data.ip;
					    });

					    var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(					
								function(data, status, headers, config)
								{
									$scope.getSliders = data;
								}).
								error(function(data, status, headers, config)
								{
									$scope.getSliders = "Response Fail";
								});
						
						$scope.next = function()
						{
							$scope.search = '';
							if($scope.pageSize == "All")
							{
							}
							else
							{
								$scope.currentPage = $scope.currentPage + 1;
								$scope.startindex = $scope.pageSize * $scope.currentPage;
									
								var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
								$http.get(link).success(					
										function(data, status, headers, config)
										{
											$scope.getSliders = data;
										}).
										error(function(data, status, headers, config)
										{
											$scope.getSliders = "Response Fail";
										});
							}
						}
						
						$scope.prev = function()
						{
							$scope.search = '';
							$scope.currentPage = $scope.currentPage - 1;
							$scope.startindex = $scope.pageSize * $scope.currentPage;
							
							var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
							$http.get(link).success(					
									function(data, status, headers, config)
									{
										$scope.getSliders = data;
									}).
									error(function(data, status, headers, config)
									{
										$scope.getSliders = "Response Fail";
									});
						}
						
						$scope.changePage = function()
						{
							$scope.search = '';
							$scope.currentPage = 0;
							$scope.startindex = 0;
							
							if($scope.pageSize == "All")
							{
								var link = baseUrl+'getSliders';
								$http.get(link).success(					
										function(data, status, headers, config)
										{
											$scope.getSliders = data;
										}).
										error(function(data, status, headers, config)
										{
											$scope.getSliders = "Response Fail";
										});
							}
							else
							{
								var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
								$http.get(link).success(					
										function(data, status, headers, config)
										{
											$scope.getSliders = data;
										}).
										error(function(data, status, headers, config)
										{
											$scope.getSliders = "Response Fail";
										});
							}
						}
						
						$scope.addSlider = function()
						{
							var slidertitle = $scope.slidertitleadd;
							var active = $scope.activeadd;
							var redirecturl = $scope.redirecturladd;
							var target = $scope.targetadd;
							
							var active1 = "";
							
							if(active == true)
							{
								active1 = "y";
							}
							else
							{
								active1 = "n";
							}
							
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
							
							if(slidertitle==undefined || slidertitle=="")
							{
								slidertitle = "";
							}
							
							//var URL_REGX = /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/;
							var URL_REGX = /^http(s?):\/\/(www\.)?(((\w+(([\.\-]{1}([a-z]{2,})+)+)(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*$)|(\w+((\.([a-z]{2,})+)+)(\:[0-9]{1,5}(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*$)))|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}(([0-9]|([1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]+)+)(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*)((\:[0-9]{1,5}(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*$)*))$/;

							if(imageadd.files[0]==undefined || imageadd.files[0]=="")
							{
								$window.alert("Please select image!");
								document.getElementById("imageadd").focus();
								return;
							}
							else if(redirecturl && URL_REGX.test(redirecturl) == false)
							{
								$window.alert("Please enter valid url with http or https!");
								document.getElementById("redirecturladd").focus();
								return;
							}
							else if(redirecturl && (target == undefined || target == ""))
							{
								$window.alert("Please select target!");
								document.getElementById("targetadd").focus();
								return;
							}
							else
							{
								$scope.spin = 1;
								
								var tit = slidertitle.replace("&","$");
								var tit1 = tit.replace("#","~");
								var tit2 = tit1.replace("%","!");
								
								var url2;
								if(redirecturl == undefined || redirecturl == "") {
									url2 = "";
								} else {
									var url = redirecturl.replace("&","$");
									var url1 = url.replace("#","~");
									url2 = url1.replace("%","!");
								}
								
								if(target == undefined || target == "") {
									target = "";
								}

								var link = baseUrl+'addSlider?slidertitle='+tit2+'&active='+active1+'&redirecturl='+url2+'&target='+target+'&ipaddress='+$scope.ipaddress+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
								var formData=new FormData();
								formData.append("image",imageadd.files[0]);
								$http({
								        method: 'POST',
								        url: link,
								        headers: {'Content-Type': undefined},
								        data: formData,
								        transformRequest: function(data, headersGetterFunction)
								        {
								        	return data;
								        }
								}).success(function(data, status, headers, config)	{
									$scope.addslider = data;
									$scope.spin = 0;
									$window.alert("Slider Added Successfully...");
									$window.location.reload();
								}).error(function(data, status, headers, config) {
									$scope.addslider = "Response Fail";
								});
							}
						}
						
						
						$scope.getSlider = function(sliderid)
						{
							for (i in $scope.getSliders)
							{
				                if ($scope.getSliders[i].sliderId == sliderid)
				                {
				                	$scope.sliderid = $scope.getSliders[i].sliderId;
				                	$scope.slidertitle = $scope.getSliders[i].sliderTitle;
				                	$scope.sliderimage = $scope.getSliders[i].image;
				                	$scope.active1 = $scope.getSliders[i].active;
				                	$scope.redirecturl = $scope.getSliders[i].redirectUrl;
				                	$scope.target = $scope.getSliders[i].target;
				                }
							}
							
							if($scope.active1 == "y")
								$scope.active = true;
						}
						
						$scope.deleteImage = function()
						{
							$scope.sliderimage = "";
						}
						
						
						$scope.editSlider = function(sliderid)
						{
							var slidertitle = $scope.slidertitle;
							var active = $scope.active;
							var redirecturl = $scope.redirecturl;
							var target = $scope.target;
							
							var active1 = "";
							
							if(active == true)
							{
								active1 = "y";
							}
							else
							{
								active1 = "n";
							}
							
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
							
							if(slidertitle==undefined || slidertitle=="")
							{
								slidertitle = "";
							}
							
							var URL_REGX = /^http(s?):\/\/(www\.)?(((\w+(([\.\-]{1}([a-z]{2,})+)+)(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*$)|(\w+((\.([a-z]{2,})+)+)(\:[0-9]{1,5}(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*$)))|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}(([0-9]|([1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]+)+)(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*)((\:[0-9]{1,5}(\/[a-zA-Z0-9\_\=\?\&\.\#\-\W]*)*$)*))$/;

							if($scope.sliderimage == "" && (image.files[0]==undefined || image.files[0]==""))
							{
								$window.alert("Please select image!");
								document.getElementById("image").focus();
								return;
							}
							else if(redirecturl && URL_REGX.test(redirecturl) == false)
							{
								$window.alert("Please enter valid url with http or https!");
								document.getElementById("redirecturl").focus();
								return;
							}
							else if(redirecturl && (target == undefined || target == ""))
							{
								$window.alert("Please select target!");
								document.getElementById("target").focus();
								return;
							}
							else
							{
								$scope.spin = 1;
								
								var tit = slidertitle.replace("&","$");
								var tit1 = tit.replace("#","~");
								var tit2 = tit1.replace("%","!");
								
								var url2;
								if(redirecturl == undefined || redirecturl == "") {
									url2 = "";
								} else {
									var url = redirecturl.replace("&","$");
									var url1 = url.replace("#","~");
									url2 = url1.replace("%","!");
								}
								
								if(target == undefined || target == "") {
									target = "";
								}

								var link = baseUrl+'editSlider?sliderid='+sliderid+'&slidertitle='+tit2+'&active='+active1+'&redirecturl='+url2+'&target='+target+'&sliderimage='+$scope.sliderimage+'&ipaddress='+$scope.ipaddress+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
								var formData=new FormData();
								formData.append("image",image.files[0]);
								$http({
								        method: 'POST',
								        url: link,
								        headers: {'Content-Type': undefined},
								        data: formData,
								        transformRequest: function(data, headersGetterFunction)
								        {
								        	return data;
								        }
								}).success(function(data, status, headers, config)	{
									$scope.editslider = data;
									$scope.spin = 0;
									$window.alert("Slider Updated Successfully...");
									$window.location.reload();
								}).error(function(data, status, headers, config) {
									$scope.editslider = "Response Fail";
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
							angular.forEach($scope.getSliders, function (item)
							{
								item.selected = $scope.selectedAll;
							});
					    }
						
						
						$scope.deleteSlider = function()
						{
							deleteSlider = $window.confirm('Are you sure you want to delete slider?');
							if(deleteSlider)
							{
							    angular.forEach($scope.getSliders,
							    		function(item)
							    		{
							    			if (item.selected)
							    			{
							    				var link = baseUrl+'deleteSlider?sliderid='+item.sliderId;
							    				$http['delete'](link).success(function(data, status, headers, config) {
							    					$scope.deleteslider = data;
							    				}).error(function(data, status, headers, config) {
							    					$scope.deleteslider = "Response Fail";
							    				});
							    			}
							    		});
							    $window.location.href = adminurl+'manage_slider';
							}
						}
						
						$scope.setActive = function(sliderid, active)
						{
							if(active == "y")
							{
								active = "n";
							}
							else if(active == "n")
							{
								active = "y";
							}
							
							var link = baseUrl+'setActiveOrInActiveSlider?sliderid='+sliderid+'&active='+active+'&ipaddress='+$scope.ipaddress;
							$http.post(link).success(
									function(data, status, headers, config)
									{
										$scope.setactiveorinactiveslider = data;
										$window.location.href = adminurl+'manage_slider';
									}).
									error(function(data, status, headers, config)
									{
										$scope.setactiveorinactiveslider = "Response Fail";
									});
						}
						
						$scope.Frontlogout = function() {		
							var link = baseUrl + 'Frontlogout';
							$http.post(link).success(function(data, status, headers, config) {
								$window.location.href = url+'';
							}).error(function(data, status, headers, config) {
								$scope.frontlogout = "Response Fail";
							});
						}
					
					
					}]);
					