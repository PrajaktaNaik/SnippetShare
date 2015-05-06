<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Snippet Share</title>
<link href="<c:url value="/resources/css/boards.css" />" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Abel|Arvo" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<%-- <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet"> --%>
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
</style>
</head>

<body>
<!-- begin #container -->
<div id="container">
    <!-- begin #header -->
    <div id="header">
    
    	<h1><a href=""><span> </span> <span> </span> <span> </span> <span> </span> <span> </span> <span> </span><span> </span>
                                                SNIPPETSHARE </a></h1>
        <div class="submenu">
            <ul>
                <li><a href="file:///C:/Users/Fareen/Desktop/yu/pr.html">PROFILE</a></li>
                
                <li><a href="file:///C:/Users/Fareen/Desktop/yu/lg.html">LOGOUT</a></li>
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
    <!-- end #header -->

<!-- end #container -->
</body>
</html>
