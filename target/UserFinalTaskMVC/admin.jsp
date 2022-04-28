<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>User Data Table</title>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Datatable css -->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.2.2/css/buttons.dataTables.min.css">
	
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<!-- Datatable js plugin -->
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.2/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.print.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.colVis.min.js"></script>
	
	<!-- custom css -->
	<link rel="stylesheet" href="lib/css/admin.css">
	
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<br>
		<h4>Welcome Admin, ${sessionScope.email}.</h4>
		
		<div class="table-title">
			<div class="row">
				<div class="col-md-5">
					<h2>User Data <b>Management</b></h2>
				</div>
			</div>
		</div>
		<table id="usertable" class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Uid</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone No.</th>
					<th>Gender</th>
					<th>Birthdate</th>
					<th>Hobby</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.data}" var="user">
					<tr>
						<td>${user.uid}</td>
						<td>${user.fname}</td>
						<td>${user.lname}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td>${user.gender}</td>
						<td>${user.birthdate}</td>
						<td>${user.hobby}</td>
						<td>
							<div class="forms">
								<form action="EditServlet?email=${user.email}" method="post" id="edit-form">
									<button class="span-btn" id="edit-btn" role="button">
										<span class="glyphicon glyphicon-cog span-blue-icon" aria-hidden="true"></span>
									</button>
								</form>
								
								<form action="DeleteServlet?uid=${user.uid}" method="post">
									<button class="span-btn" id="delete-btn" role="button">
										<span class="glyphicon glyphicon-remove-circle span-red-icon" aria-hidden="true"></span>
									</button>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		<div>
			<a class="btn btn-success" id="add-btn" href="register.jsp" role="button">Add User</a>
		</div>
		
		<br><br>
		
	</div>
	
	<div class="foot"></div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- custom js -->
	<script type="text/javascript" src="lib/js/DataTablePlugin.js"></script>
	
</body>
</html>