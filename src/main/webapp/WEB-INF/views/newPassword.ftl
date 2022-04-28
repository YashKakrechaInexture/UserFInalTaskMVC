<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>New Password</title>
	
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
			<h1 class="heading text-center">Reset Password</h1>
		</div>
		<c:choose>
			<c:when test="${empty requestScope.email}">
				<c:redirect url = "resetPassword.jsp"/>
			</c:when>
			<c:otherwise>
				<form action="NewPasswordServlet?email=${requestScope.email}" method="post">
					<div class="outer-box">
						<div class="row">
							<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
								<div class="login-box">
									<div class="form-group">
										<label for="password1">New Password</label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1">
												<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
											</span>
											<input type="password" name="password1" class="form-control" id="password1" placeholder="JohnDoe@123" aria-describedby="basic-addon1" required>
										</div>
									</div>
									<div class="form-group">
										<label for="password2">Confirm Password</label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon2">
												<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
											</span>
											<input type="password" name="password2" class="form-control" id="password2" placeholder="JohnDoe@123" aria-describedby="basic-addon2" required>
										</div>
									</div>
									<div class="btn-groups">
										<button type="submit" class="btn btn-success form-control login-btn">Update Password</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>