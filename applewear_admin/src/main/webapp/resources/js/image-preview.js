 function readURL(input,destination) {
                var url = input.value;
                console.log(url);
                var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
                if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                    	destination.attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                }else{
                	destination.attr('src', '${pageContext.request.contextPath}/resources/images/no_preview.png');
                }
            }
 
 function readResponseURL(input) {	
     var url = input.value;
     var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
     if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
         var reader = new FileReader();
         reader.onload = function (e) {
        	 return e.target.result;
         	/*destination.attr('src', e.target.result);*/
         }
         reader.readAsDataURL(input.files[0]);
     }else{
    	 return "";
     }
 }
 function imageExists(url, callback) {
	    var img = new Image();
	    img.src = url;
	    img.onload = function() { callback(true); };
	    img.onerror = function() { callback(false); };
	    
	  }