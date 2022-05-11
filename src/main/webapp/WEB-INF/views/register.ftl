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
	<script type="text/javascript">
		<#include "lib/plugin/cloneData.js">
	</script>
		
	<!-- address plugin javascript -->
	<script type="text/javascript">
		<#include "lib/js/AddressPlugin.js">
	</script>
	
	<!-- custom css -->
	<style type="text/css">
		<#include "lib/css/register.css">
	</style>
</head>
<body>
	<div class="container">
		<#if user??>
			<div>
				<h1 class="heading text-center">Edit Page</h1>
			</div>
			<form id="submitform" enctype="multipart/form-data" method="post" action="UpdateServlet?email=${user.email}&uid=${user.uid}">
		<#else>
			<div>
				<h1 class="heading text-center">Registration Page</h1>
			</div>
			<form id="submitform" enctype="multipart/form-data" method="post" action="aids">
		</#if> 
			<div class="formpart">
				<#if validerror??><span class="error">${validerror?join("<br>")}</span></#if>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="fname">First Name</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon4">Ab</span>
								<input type="text" name="fname" class="form-control" id="fname" placeholder="John" aria-describedby="basic-addon4" <#if user??>value="${user.fname}"</#if><#if failuser??>value="${failuser.fname}"</#if> required>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="lname">Last Name</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon5">Ab</span>
								<input type="text" name="lname" class="form-control" id="lname" placeholder="Doe" aria-describedby="basic-addon5" <#if user??>value="${user.lname}"</#if><#if failuser??>value="${failuser.lname}"</#if> required>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="email">Email Address</label>
							<#if user??>
								<br>${user.email}
							<#else>
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon1">@</span>
									<input type="email" name="email" class="form-control" id="email" placeholder="name@example.com" aria-describedby="basic-addon1" <#if failuser??>value="${failuser.email}"</#if> required>
								</div>
								<div id="msg"></div>
							</#if>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="phone">Phone No.</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon0">
									<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
								</span>
								<input type="number" name="phone" class="form-control" id="phone" placeholder="1234567890" aria-describedby="basic-addon0" <#if user??>value="${user.phone?string.computer}"</#if><#if failuser??>value="${failuser.phone?string.computer}"</#if> required>
							</div>
						</div>
					</div>
				</div>
				<#if !user??>
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
				<#else>
					<input type="hidden" name="password" value="${user.password}">
				</#if>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Gender</label>
							<#if user??>
								<div class="gender-group">
									<input type="radio" name="gender" id="male" <#if user.gender=="male">checked</#if> value="male" required>
									<label for="male">Male</label>
									
									<input type="radio" name="gender" id="female" <#if user.gender=="female">checked</#if> value="female">
									<label for="female">Female</label>
									
									<input type="radio" name="gender" id="other" <#if user.gender=="other">checked</#if> value="other">
									<label for="other">Female</label>
								</div>
							<#else>
								<#if failuser??>
									<div class="gender-group">
										<input type="radio" name="gender" id="male" <#if failuser.gender=="male">checked</#if> value="male" required>
										<label for="male">Male</label>
										
										<input type="radio" name="gender" id="female" <#if failuser.gender=="female">checked</#if> value="female">
										<label for="female">Female</label>
										
										<input type="radio" name="gender" id="other" <#if failuser.gender=="other">checked</#if> value="other">
										<label for="other">Female</label>
									</div>
								<#else>
									<div class="gender-group">
										<input type="radio" name="gender" id="male" value="male" required>
										<label for="male">Male</label>
										<input type="radio" name="gender" id="female" value="female">
										<label for="female">Female</label>
										<input type="radio" name="gender" id="other" value="other">
										<label for="other">Other</label>
									</div>
								</#if>
							</#if>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="birthdate">Birthdate</label>
							<input type="date" class="form-control" name="birthdate" id="birthdate" <#if user??>value="${user.birthdate}"</#if><#if failuser??>value="${failuser.birthdate}"</#if> required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Hobby</label>
							<div class="hobby-group">
								<input type="checkbox" id="Movies" name="hobby" class="hobby" <#if user?? && user.hobby?contains("Movies")>checked</#if><#if failuser?? && failuser.hobby?contains("Movies")>checked</#if> value="Movies">
								<label for="Movies">Movies </label>
								
								<input type="checkbox" id="Cricket" name="hobby" class="hobby" <#if user?? && user.hobby?contains("Cricket")>checked</#if><#if failuser?? && failuser.hobby?contains("Cricket")>checked</#if> value="Cricket">
								<label for="Cricket">Cricket </label>
								
								<input type="checkbox" id="VideoGame" name="hobby" class="hobby" <#if user?? && user.hobby?contains("VideoGame")>checked</#if><#if failuser?? && failuser.hobby?contains("VideoGame")>checked</#if> value="VideoGame">
								<label for="VideoGame">VideoGame </label>
								
								<input type="checkbox" id="Song" name="hobby" class="hobby" <#if user?? && user.hobby?contains("Song")>checked</#if><#if failuser?? && failuser.hobby?contains("Song")>checked</#if> value="Song">
								<label for="Song">Song </label>
								
								<input type="checkbox" id="Dance" name="hobby" class="hobby" <#if user?? && user.hobby?contains("Dance")>checked</#if><#if failuser?? && failuser.hobby?contains("Dance")>checked</#if> value="Dance">
								<label for="Dance">Dance </label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="profilepic">Profile Pic</label>
							<#if user??>
								<input type="file" id="profilepic" name="profilepic" accept="image/*" <#if user.inputStream??>value="${user.inputStream}"</#if>>
								<img <#if user.base64Image??>src="data:image/jpg;base64,${user.base64Image}"<#else>src=""</#if> id="imgPreview" width="200" height="200"/>
							<#elseif failuser??>
								<input type="file" id="profilepic" name="profilepic" accept="image/*" <#if failuser.inputStream??>value="${failuser.inputStream}"</#if> required>
								<img <#if failuser.base64Image??>src="data:image/jpg;base64,${failuser.base64Image}"<#else>src=""</#if> id="imgPreview" width="200" height="200"/>
							<#else>
								<input type="file" id="profilepic" name="profilepic" accept="image/*" required>
								<img src="" id="imgPreview" width="200" height="200"/>
							</#if>
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
							<input type="text" name="que1" class="form-control" id="que1" placeholder="Ex. Ahmedabad" <#if user??>value="${user.que1}"</#if><#if failuser??>value="${failuser.que1}"</#if> required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que2">2. What Is The Name Of Your Favourite Pet?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que2" class="form-control" id="que2" placeholder="Ex. Dog" <#if user??>value="${user.que2}"</#if><#if failuser??>value="${failuser.que2}"</#if> required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que3">3. What Is The Name Of Your First School?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que3" class="form-control" id="que3" placeholder="Ex. Hogwarts School" <#if user??>value="${user.que3}"</#if><#if failuser??>value="${failuser.que3}"</#if> required>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div id="main-container">
					<#if user?? && user.address??>
						<#list user.address as address>
							<div class="container-item card">
								<h4>Address Fields</h4>
								<div class="form-group address-group">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="home">Home Address</label>
												<input type="text" name="address[${address?index}].home" class="form-control" id="home_0" placeholder="123B, ABC Street" value="${address.home}" required>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label for="city">City</label>
												<input type="text" name="address[${address?index}].city" class="form-control" id="city_0" placeholder="Ahmedabad" value="${address.city}" required>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="state">State</label>
												<input type="text" name="address[${address?index}].state" class="form-control" id="state_0" placeholder="Gujarat" value="${address.state}" required>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label for="country">Country</label>
												<input type="text" name="address[${address?index}].country" class="form-control" id="country_0" placeholder="India" value="${address.country}" required>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="pincode">Pincode</label>
												<input type="text" name="address[${address?index}].pincode" class="form-control" id="pincode_0" placeholder="123456" value="${address.pincode}" required>
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
						</#list>
					<#else>
						<#if failuser?? && failuser.address??>
							<#list failuser.address as address>
								<div class="container-item card">
									<h4>Address Fields</h4>
									<div class="form-group address-group">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="home">Home Address</label>
													<input type="text" name="address[${address?index}].home" class="form-control" id="home_0" placeholder="123B, ABC Street" value="${address.home}" required>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="city">City</label>
													<input type="text" name="address[${address?index}].city" class="form-control" id="city_0" placeholder="Ahmedabad" value="${address.city}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="state">State</label>
													<input type="text" name="address[${address?index}].state" class="form-control" id="state_0" placeholder="Gujarat" value="${address.state}" required>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="country">Country</label>
													<input type="text" name="address[${address?index}].country" class="form-control" id="country_0" placeholder="India" value="${address.country}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="pincode">Pincode</label>
													<input type="text" name="address[${address?index}].pincode" class="form-control" id="pincode_0" placeholder="123456" value="${address.pincode}" required>
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
							</#list>
						<#else>
							<div class="container-item card">
								<h4>Address Fields</h4>
								<div class="form-group address-group">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="home">Home Address</label>
												<input type="text" name="address[0].home" class="form-control" id="home_0" placeholder="123B, ABC Street" required>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label for="city">City</label>
												<input type="text" name="address[0].city" class="form-control" id="city_0" placeholder="Ahmedabad" required>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="state">State</label>
												<input type="text" name="address[0].state" class="form-control" id="state_0" placeholder="Gujarat" required>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label for="country">Country</label>
												<input type="text" name="address[0].country" class="form-control" id="country_0" placeholder="India" required>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="pincode">Pincode</label>
												<input type="text" name="address[0].pincode" class="form-control" id="pincode_0" placeholder="123456" required>
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
						</#if>
					</#if>
				</div>
				<a class="btn btn-primary" id="add-more" href="javascript:;" role="button">Add Address</a>
			</div>
			
			<div class="button-group">
				<button type="reset" id="reset-btn" class="btn btn-default">Reset</button>
				<button type="submit" id="submit-btn" class="btn btn-success">Submit</button>
			</div>
		</form>
		
		<#if !user??>
			<div>
				<a class="btn btn-primary" id="login-btn" href="index.jsp" role="button">Login</a>
			</div>
		</#if>
		
	</div>
	
	<!-- custom js -->
	<script type="text/javascript">
		<#include "lib/js/Register.js">
	</script>
	
</body>
</html>