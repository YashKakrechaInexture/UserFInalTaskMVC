<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<!-- address plugin -->
	<script src="lib/plugin/cloneData.js"></script>
		
	<!-- address plugin javascript -->
	<script src="lib/js/AddressPlugin.js" type="text/javascript"></script>
	
	<!-- custom css -->
	<link rel="stylesheet" href="lib/css/register.css">
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty requestScope.user}">
				<div>
					<h1 class="heading text-center">Registration Page</h1>
				</div>
				<form id="submitform" enctype="multipart/form-data" method="post" action="RegisterServlet">
			</c:when>
			<c:otherwise>
				<div>
					<h1 class="heading text-center">Edit Page</h1>
				</div>
				<form id="submitform" enctype="multipart/form-data" method="post" action="UpdateServlet?email=${requestScope.user.email}&uid=${requestScope.user.uid}">
			</c:otherwise>
		</c:choose>
			<div class="formpart">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="fname">First Name</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon4">Ab</span>
								<input type="text" name="fname" class="form-control" id="fname" placeholder="John" aria-describedby="basic-addon4" value="${requestScope.user.fname}${requestScope.failuser.fname}" required>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="lname">Last Name</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon5">Ab</span>
								<input type="text" name="lname" class="form-control" id="lname" placeholder="Doe" aria-describedby="basic-addon5" value="${requestScope.user.lname}${requestScope.failuser.lname}" required>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="email">Email Address</label>
							<c:choose>
								<c:when test="${not empty requestScope.user}">
									<br><c:out value="${requestScope.user.email}"></c:out>
								</c:when>
								<c:otherwise>
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1">@</span>
										<input type="email" name="email" class="form-control" id="email" placeholder="name@example.com" aria-describedby="basic-addon1" value="${requestScope.failuser.email}" required>
									</div>
									<div id="msg"></div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="phone">Phone No.</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon0">
									<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
								</span>
								<input type="number" name="phone" class="form-control" id="phone" placeholder="1234567890" aria-describedby="basic-addon0" value="${requestScope.user.phone}${requestScope.failuser.phone}" required>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${empty requestScope.user}">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="password1">Password</label>
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon2">
										<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
									</span>
									<input type="password" name="password1" class="form-control" id="password1" placeholder="JohnDoe@123" autocomplete="on" aria-describedby="basic-addon2" required>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="password2">Confirm Password</label>
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon3">
										<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
									</span>
									<input type="password" name="password2" class="form-control" id="password2" placeholder="JohnDoe@123" autocomplete="on" aria-describedby="basic-addon3" required>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Gender</label>
							<c:choose>
								<c:when test="${empty requestScope.user}">
									<c:choose>
										<c:when test="${empty requestScope.failuser}">
											<div class="gender-group">
												<input type="radio" name="gender" id="male" value="male" required>
												<label for="male">Male</label>
												<input type="radio" name="gender" id="female" value="female">
												<label for="female">Female</label>
												<input type="radio" name="gender" id="other" value="other">
												<label for="other">Other</label>
											</div>
										</c:when>
										<c:otherwise>
											<div class="gender-group">
												<c:choose>
													<c:when test="${'male' eq requestScope.failuser.gender}">
														<input type="radio" name="gender" id="male" value="male" checked required>
														<label for="male">Male</label>
													</c:when>
													<c:otherwise>
														<input type="radio" name="gender" id="male" value="male" required>
														<label for="male">Male</label>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${'female' eq requestScope.failuser.gender}">
														<input type="radio" name="gender" id="female" checked value="female">
														<label for="female">Female</label>
													</c:when>
													<c:otherwise>
														<input type="radio" name="gender" id="female" value="female">
														<label for="female">Female</label>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${'other' eq requestScope.failuser.gender}">
														<input type="radio" name="gender" id="other" checked value="other">
														<label for="other">Other</label>
													</c:when>
													<c:otherwise>
														<input type="radio" name="gender" id="other" value="other">
														<label for="other">Other</label>
													</c:otherwise>
												</c:choose>
											</div>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<div class="gender-group">
										<c:choose>
											<c:when test="${'male' eq requestScope.user.gender}">
												<input type="radio" name="gender" id="male" value="male" checked required>
												<label for="male">Male</label>
											</c:when>
											<c:otherwise>
												<input type="radio" name="gender" id="male" value="male" required>
												<label for="male">Male</label>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${'female' eq requestScope.user.gender}">
												<input type="radio" name="gender" id="female" checked value="female">
												<label for="female">Female</label>
											</c:when>
											<c:otherwise>
												<input type="radio" name="gender" id="female" value="female">
												<label for="female">Female</label>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${'other' eq requestScope.user.gender}">
												<input type="radio" name="gender" id="female" checked value="female">
												<label for="female">Female</label>
											</c:when>
											<c:otherwise>
												<input type="radio" name="gender" id="other" value="other">
												<label for="other">Other</label>
											</c:otherwise>
										</c:choose>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="birthdate">Birthdate</label>
							<input type="date" class="form-control" name="birthdate" id="birthdate" value="${requestScope.user.birthdate}${requestScope.failuser.birthdate}" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Hobby</label>
							<div class="hobby-group">
								<c:choose>
									<c:when test="${fn:contains(requestScope.user.hobby,'Movies')}">
										<input type="checkbox" id="Movies" name="hobby" class="hobby" checked value="Movies">
										<label for="Movies">Movies </label>
									</c:when>
									<c:when test="${fn:contains(requestScope.failuser.hobby,'Movies')}">
										<input type="checkbox" id="Movies" name="hobby" class="hobby" checked value="Movies">
										<label for="Movies">Movies </label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="Movies" name="hobby" class="hobby" value="Movies">
										<label for="Movies">Movies </label>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${fn:contains(requestScope.user.hobby,'Cricket')}">
										<input type="checkbox" id="Cricket" name="hobby" class="hobby" checked value="Cricket">
										<label for="Cricket">Cricket </label>
									</c:when>
									<c:when test="${fn:contains(requestScope.failuser.hobby,'Cricket')}">
										<input type="checkbox" id="Cricket" name="hobby" class="hobby" checked value="Cricket">
										<label for="Cricket">Cricket </label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="Cricket" name="hobby" class="hobby" value="Cricket">
										<label for="Cricket">Cricket </label>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${fn:contains(requestScope.user.hobby,'VideoGame')}">
										<input type="checkbox" id="VideoGame" name="hobby" class="hobby" checked value="VideoGame">
										<label for="VideoGame">VideoGame </label>
									</c:when>
									<c:when test="${fn:contains(requestScope.failuser.hobby,'VideoGame')}">
										<input type="checkbox" id="VideoGame" name="hobby" class="hobby" checked value="VideoGame">
										<label for="VideoGame">VideoGame </label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="VideoGame" name="hobby" class="hobby" value="VideoGame">
										<label for="VideoGame">VideoGame </label>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${fn:contains(requestScope.user.hobby,'Song')}">
										<input type="checkbox" id="Song" name="hobby" class="hobby" checked value="Song">
										<label for="Song">Song </label>
									</c:when>
									<c:when test="${fn:contains(requestScope.failuser.hobby,'Song')}">
										<input type="checkbox" id="Song" name="hobby" class="hobby" checked value="Song">
										<label for="Song">Song </label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="Song" name="hobby" class="hobby" value="Song">
										<label for="Song">Song </label>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${fn:contains(requestScope.user.hobby,'Dance')}">
										<input type="checkbox" id="Dance" name="hobby" class="hobby" checked value="Dance">
										<label for="Dance">Dance </label>
									</c:when>
									<c:when test="${fn:contains(requestScope.failuser.hobby,'Dance')}">
										<input type="checkbox" id="Dance" name="hobby" class="hobby" checked value="Dance">
										<label for="Dance">Dance </label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="Dance" name="hobby" class="hobby" value="Dance">
										<label for="Dance">Dance </label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="profilepic">Profile Pic</label>
							<c:choose>
								<c:when test="${empty requestScope.user}">
									<input type="file" id="profilepic" name="profilepic" accept="image/*" value="${requestScope.failuser.inputStream}" required>
									<img src="data:image/jpg;base64,${requestScope.failuser.base64Image}" id="imgPreview" width="200" height="200"/>
								</c:when>
								<c:otherwise>
									<input type="file" id="profilepic" name="profilepic" accept="image/*" value="${requestScope.user.inputStream}">
									<img src="data:image/jpg;base64,${requestScope.user.base64Image}" id="imgPreview" width="200" height="200"/>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="ques">
				<h4>Security Questions</h4>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que1">1. In What City Were You Born?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que1" class="form-control" id="que1" placeholder="Ex. Ahmedabad" value="${requestScope.user.que1}${requestScope.failuser.que1}" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que2">2. What Is The Name Of Your Favourite Pet?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que2" class="form-control" id="que2" placeholder="Ex. Dog" value="${requestScope.user.que2}${requestScope.failuser.que2}" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que3">3. What Is The Name Of Your First School?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que3" class="form-control" id="que3" placeholder="Ex. Hogwarts School" value="${requestScope.user.que3}${requestScope.failuser.que3}" required>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div id="main-container">
					<c:choose>
						<c:when test="${empty requestScope.user}">
							<c:choose>
								<c:when test="${empty requestScope.failuser}">
									<div class="container-item card">
										<h4>Address Fields</h4>
										<div class="form-group address-group">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label for="home">Home Address</label>
														<input type="text" name="home" class="form-control" id="home_0" placeholder="123B, ABC Street" required>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="city">City</label>
														<input type="text" name="city" class="form-control" id="city_0" placeholder="Ahmedabad" required>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label for="state">State</label>
														<input type="text" name="state" class="form-control" id="state_0" placeholder="Gujarat" required>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="country">Country</label>
														<input type="text" name="country" class="form-control" id="country_0" placeholder="India" required>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label for="pincode">Pincode</label>
														<input type="text" name="pincode" class="form-control" id="pincode_0" placeholder="123456" required>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<a href="javascript:void(0)" class="remove-item btn btn-danger">Remove</a>
												</div>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach items="${requestScope.failuser.address}" var="address">
										<div class="container-item card">
											<h4>Address Fields</h4>
											<div class="form-group address-group">
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label for="home">Home Address</label>
															<input type="text" name="home" class="form-control" id="home_0" placeholder="123B, ABC Street" value="${address.home}" required>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label for="city">City</label>
															<input type="text" name="city" class="form-control" id="city_0" placeholder="Ahmedabad" value="${address.city}" required>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label for="state">State</label>
															<input type="text" name="state" class="form-control" id="state_0" placeholder="Gujarat" value="${address.state}" required>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label for="country">Country</label>
															<input type="text" name="country" class="form-control" id="country_0" placeholder="India" value="${address.country}" required>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label for="pincode">Pincode</label>
															<input type="text" name="pincode" class="form-control" id="pincode_0" placeholder="123456" value="${address.pincode}" required>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<a href="javascript:void(0)" class="remove-item btn btn-danger">Remove</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:forEach items="${requestScope.user.address}" var="address">
								<div class="container-item card">
									<h4>Address Fields</h4>
									<div class="form-group address-group">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="home">Home Address</label>
													<input type="text" name="home" class="form-control" id="home_0" placeholder="123B, ABC Street" value="${address.home}" required>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="city">City</label>
													<input type="text" name="city" class="form-control" id="city_0" placeholder="Ahmedabad" value="${address.city}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="state">State</label>
													<input type="text" name="state" class="form-control" id="state_0" placeholder="Gujarat" value="${address.state}" required>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="country">Country</label>
													<input type="text" name="country" class="form-control" id="country_0" placeholder="India" value="${address.country}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="pincode">Pincode</label>
													<input type="text" name="pincode" class="form-control" id="pincode_0" placeholder="123456" value="${address.pincode}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<a href="javascript:void(0)" class="remove-item btn btn-danger">Remove</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<a class="btn btn-primary" id="add-more" href="javascript:;" role="button">Add Address</a>
			</div>
			
			<div class="button-group">
				<button type="reset" id="reset-btn" class="btn btn-default">Reset</button>
				<button type="submit" id="submit-btn" class="btn btn-success">Submit</button>
			</div>
		</form>
		
		<c:if test="${empty requestScope.user}">
			<div>
				<a class="btn btn-primary" id="login-btn" href="index.jsp" role="button">Login</a>
			</div>
		</c:if>
		
	</div>
	
	<!-- custom js -->
	<script type="text/javascript" src="lib/js/Register.js"></script>
	
</body>
</html>