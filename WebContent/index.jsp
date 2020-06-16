<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="/e-JAWI/login/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/e-JAWI/login/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/e-JAWI/login/css/util.css">
<link rel="stylesheet" type="text/css" href="/e-JAWI/login/css/main.css">
</head>
<body>

	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('/e-JAWI/login/images/bg-01.jpg');">
			<div class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
				<form class="login100-form validate-form flex-sb flex-w" action="LoginController" method="post">
					<span class="login100-form-title p-b-53"> Sign In </span>

					<div class="p-t-31 p-b-9">
						<span class="txt1"> Login As: </span>
					</div>
					<div class="wrap-input100" data-validate="">
						<select class="input100" name="action" required>
							<option value="" selected disabled>Login As:</option>
							<option value="LoginTeacher">Teacher / Admin</option>
							<option value="LoginParent">Parent</option>
							<option value="LoginStudent">Student</option>
						</select> <span class="focus-input100"></span>
					</div>

					<div class="p-t-31 p-b-9">
						<span class="txt1"> Email </span>
					</div>
					<div class="wrap-input100 validate-input"
						data-validate="Email is required">
						<input class="input100" type="text" name="email"> <span
							class="focus-input100"></span>
					</div>

					<div class="p-t-13 p-b-9">
						<span class="txt1"> Password </span>
						<!-- <a href="#"
							class="txt2 bo1 m-l-5"> Forgot? </a> -->
					</div>
					<div class="wrap-input100 validate-input"
						data-validate="Password is required">
						<input class="input100" type="password" name="password"> <span
							class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn">Sign In</button>
					</div>

					<!-- <div class="w-full text-center p-t-55">
						<span class="txt2"> Not a member? </span> <a href="#"
							class="txt2 bo1"> Sign up now </a>
					</div> -->
				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="/e-JAWI/login/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/e-JAWI/login/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="/e-JAWI/login/vendor/bootstrap/js/popper.js"></script>
	<script src="/e-JAWI/login/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="/e-JAWI/login/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="/e-JAWI/login/vendor/daterangepicker/moment.min.js"></script>
	<script src="/e-JAWI/login/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="/e-JAWI/login/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="/e-JAWI/login/js/main.js"></script>
</body>
</html>