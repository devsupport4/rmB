<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<title>Add News</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script	src="<%=request.getContextPath()%>/resources/admin/js/jquery.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/bootstrap.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/angular.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/controller/app.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>"	rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>"	rel="stylesheet" type="text/css" />
		<script src="https://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
		<script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
		<script>
			webshims.setOptions('forms-ext', {types: 'date'});
			webshims.polyfill('forms forms-ext');
		</script>
		<style>
			input[type="date"]:hover::-webkit-calendar-picker-indicator {
			  color: red;
			}
			input[type="date"]:hover:after {
			  content: " Date Picker ";
			  color: #555;
			  padding-right: 5px;
			}
			input[type="date"]::-webkit-inner-spin-button {
			
			  -webkit-appearance: none;
			  margin: 0;
			}
		</style>
	</head>
	<body ng-app="rcbs" ng-controller="newsCtrl" ng-init="getCurrentDefaultYear()">			
		<%@include file="header.jsp"%><br>
		<div class="container">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"> Add News </h3>
				</div>
				<div class="panel-body" style="background-color: #f2f2f2;">					
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Rotary Year<font color="red" size="3">*</font></label> 
									<select class="form-control" id="rotaryyearid" name="rotaryyearid" ng-model="rotaryyearid" ng-options="item.rotaryYearId as item.rotaryYearTitle for item in rotaryyear">
										<option value="">Rotary Year</option>													
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Service Project</label>
									<select class="form-control" id="project" name="project" ng-model="project">
										<option value="">Service Project</option>
										<option ng-repeat="item in serviceproject" value="{{item.serviceProjectId}}">{{item.serviceProjectTitle}}</option>
									</select>		
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>News Title<font color="red" size="3">*</font></label> 
									<input type="text" class="form-control" id="newstitle" name="newstitle" ng-model="newstitle" maxlength="250">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>News Sub-Title</label> 
									<input type="text" class="form-control" id="newssubtitle" name="newssubtitle" ng-model="newssubtitle" maxlength="250">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>News Date</label> 
									<input type="date" class="form-control" id="newsdate" name="newsdate" ng-model="newsdate">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Beneficiary Type</label> 
									<select class="form-control" id="beneficiarytypeid" name="beneficiarytypeid" ng-model="beneficiarytypeid" ng-options="item.beneficiaryTypeId as item.beneficiaryTypeTitle for item in beneficiarytype">
										<option value="">Beneficiary Type</option>													
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>No. Of Beneficiary Benefited</label> 
									<input type="text" class="form-control" id="beneficiaryno" name="beneficiaryno" ng-model="beneficiaryno">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>News Description<font color="red" size="3">*</font></label>
									<textarea rows="4" class="form-control" id="newsdescription" name="newsdescription" ng-model="newsdescription"></textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Image Title</label> <input type="text" class="form-control" id="newsimagetitle" name="newsimagetitle" ng-model="newsimagetitle" maxlength="100">
								</div>
							</div>							
							<div class="col-md-6">
								<div class="form-group">
									<label>News Image<font color="red" size="3">*</font></label>
									<input type="file" class="form-control" id="imgInp" file-model="file" accept=".jpg,.jpeg,.png">
								</div>
							</div>							
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-12" align="center">
								<img src="#" id="target"/>																									
								<form name="myForm">
									<input type="hidden" name="x" id="valuex" ng-model="valuex"/>
									<input type="hidden" name="y" id="valuey" ng-model="valuey"/>
									<input type="hidden" name="w" id="valuew" ng-model="valuew"/>
									<input type="hidden" name="h" id="valueh" ng-model="valueh"/>																	
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="form-group">
									<button type="submit" name="submit" ng-click="addNews()" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
									<a href="manage_news" class="btn btn-danger" data-dismiss="modal">Cancel</a>									
								</div>
							</div>
						</div>
					
				</div>
			</div>
		</div>		
		<%@include file="footer.jsp"%><br>
		<script>
			jQuery(function($) {
				 function readURL(input) {
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				            	if ($('#target').data('Jcrop')) {
								    $('#target').data('Jcrop').destroy();
								    $('#target').removeAttr('style');
								}
				                $('#target').attr('src', e.target.result);
				                
				                $('#target').Jcrop({
				                	aspectRatio: 2 / 1,	
				                	boxWidth: 650,   
				                    boxHeight: 325,
				                    setSelect: [ 200, 200, 650, 325 ],
				    				onSelect : setCoordinates
				    			});
				            }		            
				            reader.readAsDataURL(input.files[0]);
				        }
				    }
				    
				    $("#imgInp").change(function(){
				        readURL(this);
				    });
				    
					
			});
			function setCoordinates(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm.x.value = Math.round(c.x);
				document.myForm.y.value = Math.round(c.y);
				document.myForm.w.value = Math.round(c.w);
				document.myForm.h.value = Math.round(c.h);
			};
			function checkCoordinates() {
				if (document.myForm.x.value == "" || document.myForm.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};			
		</script>
	</body>
</html>
