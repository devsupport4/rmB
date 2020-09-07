app.controller('ReadExcelCtrl', function($scope, $http, $window, $filter,
		$location, $timeout) {
	var baseUrl = $location.protocol() + "://" + location.host + url;
	
	$scope.data=[];
	
	$scope.Upload = function () {
	        //Reference the FileUpload element.
	        var fileUpload = document.getElementById("fileUpload");
	 
	        //Validate whether File is valid Excel file.
	        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xls|.xlsx)$/;
	        if (regex.test(fileUpload.value.toLowerCase())) {
	            if (typeof (FileReader) != "undefined") {
	                var reader = new FileReader();
	 
	                //For Browsers other than IE.
	                if (reader.readAsBinaryString) {
	                    reader.onload = function (e) {
	                        ProcessExcel(e.target.result);
	                    };
	                    reader.readAsBinaryString(fileUpload.files[0]);
	                } else {
	                    //For IE Browser.
	                    reader.onload = function (e) {
	                        var data = "";
	                        var bytes = new Uint8Array(e.target.result);
	                        for (var i = 0; i < bytes.byteLength; i++) {
	                            data += String.fromCharCode(bytes[i]);
	                        }
	                        ProcessExcel(data);
	                    };
	                    reader.readAsArrayBuffer(fileUpload.files[0]);
	                }
	            } else {
	                alert("This browser does not support HTML5.");
	            }
	        } else {
	            alert("Please upload a valid Excel file.");
	        }
	    };
	    function ProcessExcel(data) {
	        //Read the Excel File data.
	        var workbook = XLSX.read(data, {
	            type: 'binary'
	        });
	 
	        //Fetch the name of First Sheet.
	        var firstSheet = workbook.SheetNames[0];
	 
	        //Read all rows from First Sheet into an JSON array.
	        var excelRows = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[firstSheet]);
	 
	        //Create a HTML Table element.
	        var table = document.createElement("table");
	        table.border = "1";
	        table.setAttribute("name","exe");
	 
	        //Add the header row.
	        var row = table.insertRow(-1);
	        table.id='abc';
	        
	 
	        /*Add the header cells.
	   
	  		headerCell = document.createElement("TH");
	        headerCell.innerHTML = "No";
	        row.appendChild(headerCell);
	        */
	        headerCell = document.createElement("TH");
	        headerCell.innerHTML = "Number";
	        row.appendChild(headerCell);
	        
			headerCell = document.createElement("TH");
	        headerCell.innerHTML = "Name";
	        row.appendChild(headerCell);
			

			
	      
	        for (var i = 0; i < excelRows.length; i++) {
	        	/* var data[];
	    		var num[]; */
	            var row = table.insertRow(-1);
	 
			            
			             /*   var cell = row.insertCell(-1);
			                var abc13 =document.createElement('input');
						    abc13.value = excelRows[i].No;
					        abc13.setAttribute("name","No");
					        cell.appendChild(abc13);
					        abc13.className='text1';
			          */
			                var cell = row.insertCell(-1);
			                var abc =document.createElement('input');
						    abc.value = excelRows[i].Number;
					        abc.setAttribute("name","number");
					   		$scope.num =abc.value;			        
					        cell.appendChild(abc);
							 abc.className='test';

							 var cell = row.insertCell(-1);
				                var abc2 =document.createElement('input');
							    abc2.value = excelRows[i].Name;
						        abc2.setAttribute("name","name");
						     
						    	$scope.num1 =abc2.value;
						        cell.appendChild(abc2);
						        
						        
						   $scope.data.push({"number":$scope.num,"name":$scope.num1});
			   	      
	
				        }

	        var dvExcel = document.getElementById("dvExcel");
	        dvExcel.innerHTML = "";
	        dvExcel.appendChild(table);
			
	    };
	    

	$scope.BatchStartTime = "06:00";
	$scope.BatchEndTime = "07:00";
	$scope.currentPage = 0;
	$scope.pageSize = 10;
	$scope.search = "";
	$scope.startindex = $scope.currentPage;
	$scope.pages = [5, 10, 20, 50, 100, 500];
	
	var link = baseUrl+'getBatchByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;    
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getBatchList = data;
	}).error(function(data, status, headers, config) {
		$scope.getBatchList = "Response Fail";
	});
var i;
		$scope.addExcel = function(){
	
			for(i=0;i<$scope.data.length;i++){
		
				var link = baseUrl+'addExcelData?number='+$scope.data[i].number+'&name='+$scope.data[i].name;
		
		$http.post(link).success(function(data, status, headers, config) {
			$scope.getState = data;
			
			  location.reload(true); 
		}).error(function(data, status, headers, config) {
			$scope.getState = "Response Fail";
		});
	}
		
		}

	
});