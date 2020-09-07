<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/app.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">
		<link href="<c:url value="/resources/admin/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/menustyle.css"></c:url>" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">		
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
		<script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
		<script src="https://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
		<script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
<style type="text/css">

table{
 margin: auto;
  width: 50%;

  padding: 10px;
}
input{  width: 98%;}
.center {
  margin: auto;
  width: 50%;

  padding: 10px;
}
</style>
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.5/xlsx.full.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.5/jszip.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


</head>

<body id="page-top" ng-app="rpl" ng-controller="ReadExcelCtrl">
 <%@ include file="header.jsp" %> 
<center> <h1> Excel Import</h1></center>

<div class="container-fluid center" >

<input type="file" id="fileUpload" style="cursor: pointer;" > 

</div>

<div class="container-fluid center"> 
<input type="button" id="upload" value="Upload" style="cursor: pointer;width:25%;"  ng-click="Upload()" style="cursor: pointer;">
</div>

<hr />


<form ng-submit="addExcel()">
<div id="dvExcel" style="text-align: center;" onload="findDupes()"></div>
<br><br><br>




<center>
<input type="button" value="check" name="btn" class="ter"  style="width: 6%;cursor: pointer;"> 
<input type="submit" value="submit" name="btn" class="ter"  style="width: 6%;cursor: pointer;"> </center>
</form>
</body>


<script>
$(document).ready(function(){
    $('.ter').click(function(e) {
        var stored  =   [];
        var inputs  =   $('.test');
        $.each(inputs,function(k,v){
            var getVal  =   $(v).val();
            if(stored.indexOf(getVal) != -1){
                $(v).focus().css({"background-color": "red"});
				
				}
            else{
                stored.push($(v).val());
                $(v).focus().css({"background-color": "#a3f5a361"});
                     
            }
        });
    });
});
</script>

<script	src="<%=request.getContextPath()%>/resources/js/angular.min.js"></script>
<script	src="<%=request.getContextPath()%>/resources/js/controller/conf.js"></script>
<script	src="<%=request.getContextPath()%>/resources/js/controller/readExcel.js"></script>
</html>