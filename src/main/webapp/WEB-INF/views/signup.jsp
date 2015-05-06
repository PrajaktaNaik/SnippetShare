<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/signup.css" />" rel="stylesheet">
<title>Insert title here</title>
<style>

</style>
<script>

</script>
</head>
<body>
<p align="left" class="form-title">
	Snippet Share
</p>
<div class="container">

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3 ">
		<form role="form" method="post" action="signUp" style="margin-top:100px;">
			<h2 style="color: white;">Snippet Share<small><br><br> SignUp Here</small></h2>
			<hr class="colorgraph">
			<div class="form-group">
				<input type="email" name="email" id="email" class="form-control " placeholder="*Email Address" tabindex="1">
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
                        <input type="text" name="firstName" id="firstName" class="form-control " placeholder="*First Name" tabindex="2">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="text" name="lastName" id="lastName" class="form-control " placeholder="*Last Name" tabindex="3">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="password" id="password" class="form-control " placeholder="*Password" tabindex="4">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="rePassword" id="rePassword" class="form-control " placeholder="*Confirm Password" tabindex="5">
					</div>
				</div>
			</div>
		
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-md-6"><input type="button"  onclick="validate();" value="Register" class="btn btn-warning btn-block btn-lg" tabindex="6"></div>
				<div class="col-xs-12 col-md-6"><a href="/snippetshare" class="btn btn-success btn-block btn-lg" tabindex="7">Already Member</a></div>
			</div>
		</form>
	</div>
</div>

</div>
</body>

<script type="text/javascript">
	function validate(){
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var rePassword = document.getElementById("rePassword").value;
		var firstName = document.getElementById("firstName").value;
		var lastName = document.getElementById("lastName").value;
		
		/* alert(email+":"+password+":"+rePassword+":"+firstName+":"+lastName); */
		var msg = "";
		/* alert(email+":"+password+":"+rePassword+":"+firstName+":"+lastName+":"+dob+":"+address1+":"+city+":"+state+":"+zipCode); */
		if(email == '' || password == '' || rePassword == '' || firstName == '' || lastName == ''){
			msg = msg+ "Please enter all mandatory fields (Marked with *).";
		}
		
		if(password != rePassword){
			msg = msg + "\nBoth passwords are not matching."
		}
		
		if(msg == ""){
			document.forms[0].submit();					
		}else{
			alert(msg);	
		}
	}
</script>

</html>