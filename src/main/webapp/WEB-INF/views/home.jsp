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
                <form class="login" action="login" method="post">
	                <input id="email" name="email" type="text" placeholder="*Email" style="color: black;"/>
	                <input id="password" name= "password"  type="password" placeholder="*Password" />
	                <input type="button" onclick="validate();" value="Sign In" class="btn btn-success btn-sm" />
	                <div class="remember-forgot">
	                    <div class="row">
	                        <div class="col-md-6 forgot-pass-content">
	                            <a href="javascription:void(0)" class="forgot-pass">Forgot Password</a>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div>
		                        <a href="/snippetshare/viewSignup" class="btn btn-warning btn-block btn-lg">Sign Up</a>
	           				 </div>
	                    </div>
	                </div>
                </form>
            </div>
        
        </div>
    </div>
 
</div>
</body>

<script type="text/javascript">
	function validate(){
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		
		/* alert(email+":"+password+":"+rePassword+":"+firstName+":"+lastName); */
		var msg = "";
		/* alert(email+":"+password+":"+rePassword+":"+firstName+":"+lastName+":"+dob+":"+address1+":"+city+":"+state+":"+zipCode); */
		if(email == '' || password == ''){
			msg = msg+ "Please enter all mandatory fields (Marked with *).";
		}
		
		if(msg == ""){
			document.forms[0].submit();					
		}else{
			alert(msg);	
		}
	}
</script>

</html>
