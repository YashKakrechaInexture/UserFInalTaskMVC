<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Panel</title>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<!-- custom css -->
	<style type="text/css">
		<#include "lib/css/homepage.css">
	</style>
</head>
<body>
	
	<#include "header.ftl">
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<h4>Welcome, ${Session.user.email}.</h4>
				
				<form action="EditServlet?email=${Session.user.email}" method="post">
					<button type="submit" class="btn btn-default">Edit</button>
				</form>
			</div>
		</div>		
	</div>
	
	<#include "footer.ftl">
	
</body>
</html>