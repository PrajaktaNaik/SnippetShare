<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/fileinput.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/common.js" />" ></script>
<title>Board</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<style>
body {
background: #E9E9E9;
}
#blog-landing {
margin-top: 10px;
position: relative;
max-width: 100%;
width: 100%;
}
img {
width: 100%;
max-width: 100%;
height: auto;
}
.white-panel {
position: absolute;
background: white;
box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
padding: 10px;
}
.white-panel h1 {
font-size: 1em;
}
.white-panel h1 a {
color: #A92733;
}
.white-panel:hover {
box-shadow: 1px 1px 10px rgba(0,0,0,0.5);
margin-top: -5px;
-webkit-transition: all 0.3s ease-in-out;
-moz-transition: all 0.3s ease-in-out;
-o-transition: all 0.3s ease-in-out;
transition: all 0.3s ease-in-out;
}
</style>
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
			$('#myModal').on('shown.bs.modal', function () {
				  $('#myInput').focus()
				})
		
		</script>
</head>
<!-- NAVBAR
================================================== -->
<body>
<div>
<div class="row">
<h3  align="center">Board Title: </h3>
</div>
<div class="row" style="margin-left:90px">
		<div class="col-sm-4">
			<!-- 	<form action="demo_form.asp">
				  <input type="file" name="pic" accept="image/*" class="btn btn-warning"><br>
				  <input type="submit" class="btn btn-primary" value="Upload">
				</form> -->
				<input data-toggle="modal" data-target="#myModal" type="button" value="Add New Snippet" class="btn btn-warning">
		</div>
</div>
	
<div class="container marketing">
<section id="blog-landing">
<c:forEach items="${allSnippetsVO }" var="snippet">
<article class="white-panel"><img src="data:image/jpg;base64,${snippet.image }" id="photo-id"/>
<p><b><c:out value="${snippet.description }"></c:out></b></p>
<div class="lbl">Snippet Owner:<c:out value="${snippet.ownerId }"></c:out></div>
<c:if test="${snippet.ownerId == loggedInUser}">
 <input type="button" value="Delete" class="btn btn-warning" onclick="createRequest('/snippetshare/deleteSnippet', {imageId:'${snippet.snippetId}',boardId:'${boardId }'}, 'post');" />
</c:if>
</article>

</c:forEach>


</section>
</div>

</div>
<!-- Bootstrap core JavaScript
    ================================================== --> 
<!-- Placed at the end of the document so the pages load faster --> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<c:url value="/resources/js/pinterest_grid.js"  />"></script>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Add New Snippet</h4>
		      </div>
		      <div class="modal-body">
		        <form id="createBoardForm" method="post" action="uploadSnippet" enctype="multipart/form-data">
		        		<div class="row">
		        	<input type="hidden" name="boardIdHidden" value="${boardId }" />
		            <div class="col-xs-8">
		                <label class="control-label">Description</label>
		                <input type="text" class="form-control" id="snippetDescription" name="snippetDescription" /><br>
		            </div>
		             <div class="col-xs-8">
		                <label class="control-label">File to upload:</label>
		                <input type="file" name="file"><br /> 
		            </div>
		              <div class="col-xs-8">
		               
		               <!--   <input type="submit" value="Upload"> Press here to upload the file! -->
		            </div>
		        </div>
		       
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <input type="submit" onclick="validate('ADD');" class="btn btn-primary" value="Create Snippet"></input>
		      </div>
		       </form>
	
		       </div>
		    </div>
		  </div>
		</div>
		
		
<script >
        $(document).ready(function() {

            $('#blog-landing').pinterest_grid({
                no_columns: 4,
                padding_x: 10,
                padding_y: 10,
                margin_bottom: 50,
                single_column_breakpoint: 700
            });

        });
    </script>
</body>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</html>