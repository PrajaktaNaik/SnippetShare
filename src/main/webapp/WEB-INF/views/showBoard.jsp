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
<link href="<c:url value="/resources/css/boards.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/fileinput.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/common.js" />" ></script>
<title>Board</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<style>
h2, h3 {
	color:#d01818;
	padding:10px 0 20px 0;
}
#container { 
	width: 1270px;
	margin: 0 auto;
} 
#header { 
}
#header h1 {
	float:left;
	padding:20px 0 20px 15px;
} 
#header h1 a {
	color: #333333;
	font-size:48px;
	/*text-decoration:none;*/
}
#header h1 span {
	color:#d01818;
}
#header h1 a:hover {
	color:#333333;
	font-size:48px;
	text-decoration:none;
}
#header .submenu {
	float:right;
}
#header .submenu ul {
	padding:42px 15px 0 0;
}
#header .submenu li {
	list-style:none;
	float:left;
}
#header .submenu li a {
	font-size:12px;
	text-transform: none;
	border:none;
	padding:0 0 0 20px;
	color:#707070;
	text-decoration:none;
}
#header .submenu li a:hover {
	color:#d01818;
	background:none;
}
#contentBody {
}
.threeBoxes {
	background:#e9e9e9;
	padding:15px;
}
.buttonLink {
	background:#d01818;
	color:#ffffff;
	padding:5px 10px;
	margin:10px 0;
	display:inline-block;
	text-decoration:none;
	border-bottom:2px solid #b00404;
}
.box {
	float:left;
	width:25%;
	padding:0 35px;
}
.solutions {
	float:left;
	width:600px;
	padding:10px 35px;
} 
.links {
	padding:10px 35px;
	margin-left:600px;
}
.links li {
	display:block;
	border:0;
	background:url('../img/p.png'') no-repeat left 6px;
	margin:0 0 0 35px;
	padding:0 0 10px 7px;
}
.links li a {
	color:#008bce;
	text-decoration:none;
}
.links li a:hover {
	color:#008bce;
	text-decoration:underline;
}
.picFloat {
	float:left;
	padding:0 20px 10px 0;
}
#footer { 
	padding: 0 10px 0 20px;
	border-top:1px solid #CCCCCC;
} 
#footer p {
	margin: 0;
	padding: 30px 0;
	text-align:center;
}
#footer a {
	color:#008bce;
	text-decoration:underline;
	font-size:12px;
}
#footer a:hover {
	color:#008bce;
	text-decoration:none;
	font-size:12px;
}
.clearfloat {
	clear:both;
    height:0;
    font-size: 1px;
    line-height: 0px;
}
/*MAIN MENU*/
#navcontainer ul {
	margin: 0;
	list-style-type: none;
	color: #FFF;
	width: 1270px;
	text-align: center;
	background:url('../img/menuBackground.jpg') repeat-x;
	padding:17px 0;
	height:100px;
}
#navcontainer ul li { 
	display: inline;
	float:left; 
}
#navcontainer ul li a {
	text-decoration: none;
	color: #999999;
	padding: 17px 30px;
	border-right: 1px solid #333333;
	text-transform:uppercase;
}
#navcontainer ul li a:hover {
	background:url("<c:url value="/resources/img/menuActive.jpg" />") repeat-x;
	color:#FFFFFF;
}
#navlist #active a {
	background:url('../img/menuActive.jpg') repeat-x;
	color:#FFFFFF;
}

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
 <div id="container">
    <!-- begin #header -->
    <div id="header">
    
    	<h1><a href=""><span> </span> <span> </span> <span> </span> <span> </span> <span> </span> <span> </span><span> </span>
                                                SNIPPETSHARE </a></h1>
        <div class="submenu">
            <ul>
               <li><input type="button" value="PENDING" onclick="createRequest('/snippetshare/viewPendingRequests', {}, 'post');">&nbsp;&nbsp;</li>
                <!-- <li><a href="#">PENDING RE</a></li> -->
            <li><input type="button" value="LOGOUT" onclick="createRequest('/snippetshare/logout', {}, 'post');"></li>
            </ul>
        </div>
        <div class="clearfloat"></div>
        <div id="navcontainer" style="height: 25px;">
            <ul id="navlist">
             
                <li><a href="/snippetshare/boards">MY BOARDS</a></li>
                <li><a href="/snippetshare/viewSearchBoards">SEARCH BOARDS</a></li>
                <li><a href="/snippetshare/aboutUs">ABOUT US </a></li>
                
                <li><a href="/snippetshare/contact">CONTACT</a></li>
                
            </ul>
        </div>
        
    </div>
        
    </div>
<div class="row">
<h3  align="center"><c:out value="${boardName}"></c:out> </h3>
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
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
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
            
        /* 	$(function(){
				  $("#header").load("header"); 
				 
				 // $("#footer").load("footer.ejs"); 
				});  */
 
        });
    </script>
</body>
<script type="text/javascript">

/*   var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })(); */

</script>
</html>