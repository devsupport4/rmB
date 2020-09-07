<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Manage News</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
	</head>	
	<body ng-app="rcbs" ng-controller="newsCtrl">
		<%@include file="header.jsp" %>		
		<div class="panel-body">
			<div class="container">
				<div class="row panel panel-primary" style="width:auto;background-color:#eee;">
					<div class="col-md-3" align="center">
						<h3 style="color:#db3615">Manage News</h3>    
					</div>
					<div class="col-md-3" align="center">
						<div class="form-group"style="margin-top:15px">
							<input ng-model="search" id="search" class="form-control" placeholder="Filter text">
						</div>
					</div>
					<div class="col-md-3" align="center">
						<div class="form-group" style="margin-top:15px;">
							<a href="add_news" class="btn btn-success btn-default">Add News &nbsp;<span class="glyphicon glyphicon-plus"></span></a>
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
				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" ng-submit="deletenews()">
							<div class="table-responsive">
								<table id="mytable" class="table table-bordred table-striped">
									<thead>										
										<th width="60%">NEWS TITLE</th>
										<th width="30%">NEWS DATE</th>										
										<th width="10%"><input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"> All</th>
									</thead>
									<tbody>
										<tr ng-repeat="item in news | filter:search | startFrom:currentPage*pageSize | limitTo:pageSize"  style="cursor:pointer;cursor:hand">											
											<td data-toggle="modal" data-target="#myModal" ng-click='getNewsDetail(item.newsId)'>{{item.newsTitle}}</td>
											<td data-toggle="modal" data-target="#myModal" ng-click='getNewsDetail(item.newsId)'>{{item.newsDate | date: 'dd/MM/yyyy'}}</td>
											<td><input type="checkbox" ng-model="item.selected" value="{{item.newsId}}"></td>
										</tr>
									</tbody>
								</table>
								<div class="form-group" style="float:right; margin-right:65px">
									<input type="submit" id="Delete" name="submit" class="btn btn-danger" value="Delete">
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
			    		<button type="submit" class="btn btn-primary" ng-disabled="currentPage >= news.length/pageSize - 1" ng-click="currentPage=currentPage+1">
			    			<i class="glyphicon glyphicon-step-forward"></i>
			    		</button>
					</div>
				</div>
			</div>
			
			<!--Edit Modal-->
			<div class="container">
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Update News</h4>
							</div>
							<div class="modal-body">
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
												<label>News Detail<font color="red" size="3">*</font></label>
												<textarea rows="3" class="form-control" id="newsdescription" name="newsdescription" ng-model="newsdescription"></textarea>
											</div>
										</div>
									</div>
									<div class="row">										
										<div class="col-md-5">
											<div class="form-group">
												<label>Image Title</label> 
												<input type="text" class="form-control" id="imagetitle" name="imagetitle" ng-model="imagetitle" maxlength="100">
											</div>
										</div>
										<div class="col-md-5">
											<div class="form-group">
												<label>News Image<font color="red" size="3">*</font></label> 
												<input type="file" class="form-control" id="imgInp">
											</div>
										</div>
										<div class="col-md-2">
											<img class="img-responsive" alt="Image" ng-src="{{image}}">
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
												<button type="submit" name="submit" ng-click="editNews(newsid)" class="btn btn-success">Submit <i class="fa fa-plus" aria-hidden="true" ng-if="temp1 == 1"></i><i class="fa fa-refresh fa-spin" ng-if="temp2 == 1"></i></button>
												<a href="manage_news" class="btn btn-danger" data-dismiss="modal">Cancel</a>
											</div>
										</div>									
									</div>															
							</div>						
						</div>
					</div>
				</div>
			</div>			
		</div><br>
		
		<script>
			$('#myModal').on('hidden', function()
					{
						document.location.reload();
					})
		</script>
		<%@include file="footer.jsp" %>
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
