<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Page</title>
	
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<!-- custom css -->
	<link rel="stylesheet" href="lib/css/login.css">
	
</head>
<body>
	<div class="container">
		<div>
			<h1 class="heading text-center">Login Page</h1>
		</div>
		<form action="LoginServlet" method="post">
			<div class="outer-box">
				<div class="row">
					<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
						<div class="login-box">
							<div class="form-group">
								<label for="email">Email Address</label>
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon1">@</span>
									<input type="email" name="email" class="form-control" id="email" placeholder="name@example.com" aria-describedby="basic-addon1" required>
								</div>
							</div>
							<div class="form-group">
								<label for="password1">Password</label>
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon2">
										<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
									</span>
									<input type="password" name="password" class="form-control" id="password" placeholder="JohnDoe@123" autocomplete="on" aria-describedby="basic-addon2" required>
								</div>
							</div>
							<div class="btn-groups">
								<button type="submit" class="btn btn-success form-control login-btn">Login</button>
								<a id="signup-link" href="register.jsp">Don't have account?</a>
								<a id="forgotpass-link" href="resetPassword.jsp">Forgot Password</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>