<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage Register requestUser</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/config.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/slider.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
	<style>
.mt-15 {
  margin-top:15px !important;
}

</style>
	</head>	
	<body ng-app="rcbs" ng-controller="sliderCtrl" >
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-4" align="center">
						<h3 style="color:#db3615">Manage Slider</h3>    
					</div>
					<div class="col-md-2" align="center">
						<div class="form-group"style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter With Slider Title">
						</div>
					</div>					
					<div class="col-md-3" align="center">
						<div class="form-group" style="margin-top:15px">
							<select ng-model="pageSize" id="pageSize" class="form-control" style="width: 35%;">
						        <option value="5">5</option>
						        <option value="10">10</option>
						        <option value="15">15</option>
						        <option value="20">20</option>
						     </select>
						</div>
					</div>	
					<div class="col-md-3 mt-15">
						<div class="form-group" style="color:white;">
							<a href="" data-toggle="modal" data-target="#AddSlider" class="btn btn-success" style="float:right; margin-bottom: 10px;">Add Slider <span class="glyphicon glyphicon-plus"></span></a>
						</div>
				    </div>				
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deleteMeeting()">
							<div class="table-responsive">
								<table class="table table-hover">
														<tbody>
															<tr style="color:black">
																<th style="color:black" width="30%">SLIDER TITLE</th>
																<th style="color:black" width="10%">IMAGE</th>
																<th style="color:black" width="40%">URL</th>
																<th style="color:black" width="10%">TARGET</th>
																<th style="color:black" width="5%" class="text-right">A/I</th>
																<th style="color:black" width="5%" class="text-right">All <input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"></th>
															</tr>
															<tr ng-if="getSliders == ''">
																<td colspan="4" style="text-align: center;"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
															</tr>
															<tr ng-repeat="item in getSliders | filter:{sliderTitle: search} " style="cursor:pointer;">
																<td style="color:black" data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'>{{item.sliderTitle}}</td>
																<td style="color:black" data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'><img src="{{item.image}}" class="img-responsive"></td>
																<td style="color:black" data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'>{{item.redirectUrl}}</td>
																<td style="color:black" data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'><span ng-show="item.target == '_blank'">Open URL in new tab</span><span ng-show="item.target == '_self'">Open URL in same tab</span></td>
																<td style="color:black" class="text-right">
																	<input type="checkbox" ng-model="item.selected1" value="{{item.sliderId}}" ng-click="setActive(item.sliderId, item.active)" ng-if="item.active == 'y'" ng-init="item.selected1 = true">
																	<input type="checkbox" ng-model="item.selected1" value="{{item.sliderId}}" ng-click="setActive(item.sliderId, item.active)" ng-if="item.active == 'n'">
																</td>
																<td style="color:black" class="text-right"><input type="checkbox" ng-model="item.selected" value="{{item.sliderId}}"></td>
															</tr>
															
														</tbody>
													</table>
								<div class="form-group" style="float:right; ">
									<input type="submit" id="Delete" name="submit" class="btn btn-danger" ng-click="deleteSlider()" value="Delete">
								</div>
								<div class="clearfix"></div>							
							</div>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-center" >
						<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="currentPage=currentPage-1">
			    			<i class="glyphicon glyphicon-step-backward"></i>
			    		</button>
			    		{{currentPage+1}}/{{numberOfPages()}}
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= meeting.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>

			<div class="container">
			<div class="modal fade" id="AddSlider" tabindex="-1" role="dialog">
		    	<div class="modal-dialog modal-lg">
		        	<div class="modal-content">
		           		<div class="modal-header">
		               		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		               		<h4 class="modal-title">Add Slider</h4>
		           		</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-5">
									<div class="form-group">
										<label>Slider Title</label>
										<input type="text" id="slidertitleadd" name="slidertitleadd" ng-model="slidertitleadd" placeholder="Slider Title" class="form-control">
									</div>
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label>Slider Image <font style="color: red;">*</font></label>
										<input type="file" id="imageadd" name="imageadd" class="form-control"><br>
										<p style="font-size:13px; margin-top:-10px;">Upload minimum 1600 * 400 size image for better appearance</p>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group checkbox">
										<label>&nbsp;</label><br>
										<label><input type="checkbox" id="activeadd" name="activeadd" ng-model="activeadd" ng-init="activeadd=true">Active?</label>
									</div>
								</div>
							</div>
							<div class="row text-center">
								<div class="col-md-12">
									<img src="" id="target" />
									<form name="myForm">
										<input type="hidden" name="x" id="valuex" ng-model="valuex" />
										<input type="hidden" name="y" id="valuey" ng-model="valuey" />
										<input type="hidden" name="w" id="valuew" ng-model="valuew" />
										<input type="hidden" name="h" id="valueh" ng-model="valueh" />
									</form>
								</div>
							</div>
							<div class="row">
								<div class="col-md-9">
									<div class="form-group">
										<label>Redirect URL</label>
										<input type="text" id="redirecturladd" name="redirecturladd" ng-model="redirecturladd" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Target</label>
										<select id="targetadd" name="targetadd" ng-model="targetadd" class="form-control">
											<option value="">Select Target</option>
											<option value="_blank">Open URL in new tab</option>
											<option value="_self">Open URL in same tab</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" ng-click="addSlider()">Submit
								<i class="fa fa-plus" aria-hidden="true" ng-if="spin == 0"></i>
								<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
							</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
		        	</div>
		    	</div>
			</div>
		</div>
		
		<div class="container">
			<div class="modal fade" id="EditSlider" tabindex="-1" role="dialog">
		    	<div class="modal-dialog modal-lg">
		        	<div class="modal-content">
		           		<div class="modal-header">
		               		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		               		<h4 class="modal-title">Edit Slider</h4>
		           		</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Slider Title</label>
										<input type="text" id="slidertitle" name="slidertitle" ng-model="slidertitle" placeholder="Slider Title" class="form-control">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Slider Image <font style="color: red;">*</font></label>
										<input type="file" id="image" name="image" class="form-control"><br>
										<p style="font-size:13px;margin-top:-10px;">Upload minimum 1600 * 400 size image for better appearance</p>
									</div>
								</div>
								<div class="col-md-2 text-center">
									<div class="form-group">
										<image src="{{sliderimage}}" class="img-responsive">
										<br ng-if="sliderimage != ''">
										<a ng-click="deleteImage()" class="btn btn-danger" ng-if="sliderimage != ''" data-toggle="tooltip" title="Remove Slider">
											<span class="glyphicon glyphicon-trash"></span>
										</a>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group checkbox">
										<label>&nbsp;</label><br>
										<label><input type="checkbox" id="active" name="active" ng-model="active">Active?</label>
									</div>
								</div>
							</div>
							<div class="row text-center">
								<div class="col-md-12">
									<img src="" id="target1" />
									<form name="myForm1">
										<input type="hidden" name="x1" id="valuex1" ng-model="valuex1" />
										<input type="hidden" name="y1" id="valuey1" ng-model="valuey1" />
										<input type="hidden" name="w1" id="valuew1" ng-model="valuew1" />
										<input type="hidden" name="h1" id="valueh1" ng-model="valueh1" />
									</form>
								</div>
							</div>
							<div class="row">
								<div class="col-md-9">
									<div class="form-group">
										<label>Redirect URL</label>
										<input type="text" id="redirecturl" name="redirecturl" ng-model="redirecturl" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Target</label>
										<select id="target" name="target" ng-model="target" class="form-control">
											<option value="">Select Target</option>
											<option value="_blank">Open URL in new tab</option>
											<option value="_self">Open URL in same tab</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" ng-click="editSlider(sliderid)"> Update
								<i class="fa fa-plus" aria-hidden="true" ng-if="spin == 0"></i>
								<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
							</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
		        	</div>
		    	</div>
			</div>
		</div>
		
		<!-- For Add -->
		<script>
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target').data('Jcrop')) {
							    $('#target').data('Jcrop').destroy();
							    $('#target').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        if(img.width < 1400 || img.height < 350)
						        {
						        	alert("Minimum image size should be 1600px X 400px");
						        	$('#target').attr('src', "");
						        	document.getElementById("imageadd").value = null;
						        }
						        else
						        {
						        	$('#target').css("min-height", "208px");
								    $('#target').css("min-width", "337px");
									
									$('#target').attr('src', e.target.result);
									$('#target').Jcrop({
										aspectRatio : 2.5 / 1,
										boxWidth : 840,
										//boxHeight : 400,
										minSize : [100, 100],
										maxSize : [1920, 1920],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates,
										onSelect : setCoordinates
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imageadd").change(function() {
					readURL(this);
				});
				$("#imageadd").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates(c) {
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
		
		<!-- For Edit -->
		<script>
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target1').data('Jcrop')) {
							    $('#target1').data('Jcrop').destroy();
							    $('#target1').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        if(img.width < 1400 || img.height < 350)
						        {
						        	alert("Minimum image size should be 1600px X 400px");
						        	$('#target1').attr('src', "");
						        	document.getElementById("uiteam").value = null;
						        }
						        else
						        {
						        	$('#target1').css("min-height", "208px");
								    $('#target1').css("min-width", "337px");
									
									$('#target1').attr('src', e.target.result);
									$('#target1').Jcrop({
										aspectRatio : 2.5 / 1,
										boxWidth : 840,
										//boxHeight : 400,
										minSize : [100, 100],
										maxSize : [1920, 1920],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates1,
										onSelect : setCoordinates1
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#image").change(function() {
					readURL(this);
				});
				$("#image").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates1(c) {
				document.myForm1.x1.value = Math.round(c.x);
				document.myForm1.y1.value = Math.round(c.y);
				document.myForm1.w1.value = Math.round(c.w);
				document.myForm1.h1.value = Math.round(c.h);
			};
			
			function checkCoordinates1() {
				if (document.myForm1.x1.value == "" || document.myForm1.y1.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		</body>
</html>
