<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/resources/css/signup.css" />" >
<script src="<c:url value="/resources/js/home.js"  />"></script>
<script> 

</script>
	<title>Home</title>

</head>
<body>

<<<<<<< HEAD

<p align="left" class="form-title">
Snippet Share
</p>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="pr-wrap">
                <div class="pass-reset">
                    <label>
                        Enter the email you signed up with</label>
                    <input type="email" placeholder="Email" />
                    <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                </div>
            </div>
            <div class="wrap">
                <p class="form-title">
                    Sign In</p>
                <form class="login">
                <input type="text" placeholder="Username" style="color: black;"/>
                <input type="password" placeholder="Password" />
                <input type="submit" value="Sign In" class="btn btn-success btn-sm" />
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" />
                                    Remember Me
                                </label>
                            </div>
                        </div>
                        <div class="col-md-6 forgot-pass-content">
                            <a href="javascription:void(0)" class="forgot-pass">Forgot Password</a>
                        </div>
                    </div>
                    <div class="row">
                        <div>
                        <a href="/snippetshare/signup" class="btn btn-warning btn-block btn-lg">Sign Up</a>
            	
            </div>
                    </div>
                </div>
                </form>
            </div>
        
        </div>
    </div>
 
</div>
=======
<P>  The time on the server is ${serverTime}. </P>
<%-- <img src="data:image/jpg;base64,${UserPhoto}" id="photo-id"/> --%>
>>>>>>> origin/master
</body>
</html>
