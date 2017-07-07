<!DOCTYPE html>
<html lang="en">

<script type="text/javascript">
	function message(){ 
		var do_display=${display_message};
	
		if(do_display)
	   		alert("${message}"); 
	} 

	
</script> 

<head>
<meta charset="utf-8">
<title>Stuff Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="layout/admin.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="layout/users.js"></script>
</head>

<body onload="message()">
	<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
				<form method="post" action="Logout">
				<div class="login">	
					<button type="submit" class="btn btn-primary" style="background-color:none;"><i class="fa fa-fw fa-power-off" name="logout"></i> Logout</button>
				</div>
				</form>
			</div>
		</div>
			
			</div>
			<div>
				<ul class="nav navbar-nav side-nav">
					<li><a href="adminmenu.jsp"><i class="fa fa-home"></i> Home</a></li>
					<li><a href="adminuser.jsp"><i class="fa fa-user"></i> Users</a>
					</li>
					<li><a href="admincourse.jsp"><i class="fa fa-book"></i>
							Courses</a></li>
					<li><a href="adminmaterial.jsp"><i class="fa fa-download"></i>
							Materials</a></li>
				</ul>
			</div>
		</div>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header" style="margin-top: 10px; padding-top:10px;">
							EasyDesk - <small> Users</small>
						</h1>
						<div style="margin-bottom: 20px;">
							<ul class="nav nav-pills nav-justified">
								<li><button class="cre  activeUsers  " data-toggle="tab">Create
										Users</button></li>
								<li><button class="del" data-toggle="tab">Delete
										Users</button></li>
								<li><button class="ed" data-toggle="tab">Edit
										Users</button></li>
								
							</ul>
						</div>
						<div class="create">
							<div class="row">
								<div>
									<form method="POST" action="AddUser">
										<div class="form-group col-lg-12">
											<label style="display: block;">User
												Type:</label>
											
											<div class="radio" style="display: inline;">
												<label><input id="prof" type="radio" name="opt_type" value="Professor">Professor</label>
											</div>
											<div class="radio" style="display: inline;">
												<label><input id="stu" type="radio" name="opt_type" value="Student">Student</label>
											</div>
										</div>
										<div class="form-group both col-lg-4">
											<label for="exampleInputEmail1">Name</label> <input
												 class="form-control" id=""
												aria-describedby="emailHelp" name="name">
										</div>
										<div class="form-group both col-lg-4">
											<label for="exampleInputEmail1">Institution Email</label> <input
												 class="form-control" id=""
												aria-describedby="emailHelp"
												name="institutional_email">
										</div>
										<div class="form-group  both col-lg-4">
											<label for="exampleInputEmail1">Alternative Email</label> <input
												 class="form-control" id=""
												aria-describedby="emailHelp"
												name="alternative_email">
										</div>
										<div class="form-group both col-lg-4">
											<label for="exampleInputEmail1">Password</label> <input
												class="form-control" id="" type="password"
												aria-describedby="emailHelp" name="password">
										</div>
										<div class="form-group both col-lg-4">
											<label for="exampleInputEmail1">Birth Date</label> <input
												" class="form-control" 
												aria-describedby="emailHelp" name="birth_date">
										</div>
										<div class="form-group both col-lg-4">
											<label for="exampleInputEmail1">Address</label> <input
												" class="form-control" 
												aria-describedby="emailHelp" name="address">
										</div>
										<div class="form-group both col-lg-4">
											<label for="exampleInputPassword1">Phone</label> <input
												class="form-control"
												id="" name="phone">
										</div>

										<div class="form-group both professor col-lg-4 professor">
											<label for="exampleInputPassword1">Internal Number</label> <input type=""
												 class="form-control "
												
												name="professor_internal_number">
										</div>
										<div class="form-group professor col-lg-4">
											<label for="exampleInputPassword1 ">Category</label> <input
												 class="form-control professor"
												name="category">
										</div>

										<div class="form-group professor col-lg-4">
											<label for="exampleInputPassword1">Internal Phone</label> <input
												 class="form-control professor"
												
												name="internal_phone">
										</div>

										<div class="form-group professor col-lg-4">
											<label for="exampleInputPassword1">Office</label> <input
												 class="form-control professor"
												id="" name="office">
										</div>

										<div class="form-group professor col-lg-4">
											<label for="exampleInputPassword1">Salary</label> <input
												 class="form-control professor"
												name="salary">
										</div>

										<div class="form-group student col-lg-4">
											<label for="exampleInputPassword1">Internal Number</label> <input
												 class="form-control"
												
												name="internal_number">
										</div>

										<div class="form-group student col-lg-4">
											<label for="exampleInputPassword1">Enrolment Year</label> <input
												class="form-control"
												
												name="enrollement_year">
										</div>
										<div class="form-group col-lg-4" id="createuserdiv">
											<button type="submit" class="btn btn-primary col-lg-4"
												id="createUserBtn" name="create_user" style="margin-top: 25px;">Create
												User</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="delete">
							<div class="row">
								<form method="POST" action="DeleteUser">
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Institution Email</label> <input
											 class="form-control" name="institution_email"
											aria-describedby="emailHelp">
									</div>
									<div class="form-group col-lg-4">
										<button type="submit" class="btn btn-primary col-lg-4"
											style="margin-top: 25px;">Delete User</button>
									</div>
								</form>
							</div>
						</div>
						
						<div class="edit">
							<div class="row">
								<form method="POST" action="EditUser">
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Institutional Email</label> <input
											 class="form-control" name="edit_institution_email" id="exampleInputEmail1"
											aria-describedby="emailHelp"
											>
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputPassword1">Field Name</label> 
										<input
											 class="form-control" name="field_name"
											id="exampleInputPassword1">
									   <span>Options: name, date_birth, institution_email, alternative_email, password, phone, role, category, internal_number, internal_phone, office, salary, enrollement_year, salary</span>
											
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputPassword1">New Value</label> <input
											 name="new_value" class="form-control"
											id="exampleInputPassword1" >
									</div>
									<div class="form-group col-lg-4" style="float: right;">
										<button type="submit" class="btn btn-primary col-lg-4"
											style="margin-top: 10px; float: right;">Edit User</button>
									</div>
								</form>
							</div>
						</div>
					</div>
</body>
</html>
