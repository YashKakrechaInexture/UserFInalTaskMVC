<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Reset Pasword Details</title>
	
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
			<h1 class="heading text-center">Find Your Account</h1>
		</div>
		<form action="ResetPasswordServlet" method="post">
			<div class="outer-box login-box">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
							<label for="email">Email Address</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">@</span>
								<input type="email" name="email" class="form-control" id="email" placeholder="name@example.com" aria-describedby="basic-addon1" required>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
							<label for="birthdate">Birthdate</label>
							<input type="date" class="form-control" name="birthdate" id="birthdate" required>
						</div>
					</div>
				</div>
				<div class="ques">
					<h4>Security Questions</h4>
					<br>
					<div class="row">
						<div class="form-group questionbox">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
								<label for="que1">1. In What City Were You Born?</label>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
								<input type="text" name="que1" class="form-control ans" id="que1" placeholder="Ex. Ahmedabad" required>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="form-group questionbox">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
								<label for="que2">2. What Is The Name Of Your Favourite Pet?</label>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
								<input type="text" name="que2" class="form-control ans" id="que2" placeholder="Ex. Dog" required>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="form-group questionbox">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
								<label for="que3">3. What Is The Name Of Your First School?</label>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
								<input type="text" name="que3" class="form-control ans" id="que3" placeholder="Ex. Hogwarts School" required>
							</div>
						</div>
					</div>
				</div>	
				<div class="btn-groups">
					<button type="submit" class="btn btn-success form-control login-btn" id="reset-pass">Reset Password</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>