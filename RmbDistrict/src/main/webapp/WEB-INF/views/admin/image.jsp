<html>
<head>
<title>Image Cropper</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css"	type="text/css" />
<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front/images/favicon.png">

<script language="Javascript">
	jQuery(function($) {
		 function readURL(input) {
		        if (input.files && input.files[0]) {
		            var reader = new FileReader();
		            
		            reader.onload = function (e) {
		                $('#target').attr('src', e.target.result);
		                
		                $('#target').Jcrop({
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
		document.myForm.x.value = c.x;
		document.myForm.y.value = c.y;
		document.myForm.w.value = c.w;
		document.myForm.h.value = c.h;
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
</head>

<body>
	<input type='file' id="imgInp" />
	<img src="#" id="target" />
	<form name="myForm">
		<input type="text" name="x" value=""/>
		<input type="text" name="y" value=""/>
		<input type="text" name="w" value=""/>
		<input type="text" name="h" value=""/>		
	</form>
</body>
</html>