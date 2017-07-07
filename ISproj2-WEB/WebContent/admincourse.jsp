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
<script type="text/javascript" src="layout/courses.js"></script>
</head>
<body onload="message()">
	<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<form method="post" action="Logout">
							<div class="login">
								<button type="submit" class="btn btn-primary"
									style="background-color: none;">
									<i class="fa fa-fw fa-power-off" name="logout"></i> Logout
								</button>
							</div>
						</form>
					</div>
				</div>

			</div>
			<div>
				<ul class="nav navbar-nav side-nav">
					<li class=""><a href="adminmenu.jsp"><i class="fa fa-home"></i>
							Home</a></li>
					<li><a href="adminuser.jsp"><i class="fa fa-user"></i>
							Users</a></li>
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
						<h1 class="page-header" style="margin-top: 10px;">
							EasyDesk - <small> Courses</small>
						</h1>
						<div style="margin-bottom: 20px;">
							<ul class="nav nav-pills nav-justified">
								<li><button class="cre  activeUsers  " data-toggle="tab">Create
										Course</button></li>
								<li><button class="ed   " data-toggle="tab">Edit
										Course</button></li>
							</ul>
						</div>

						<div class="create">
							<div class="row">
								<div>
									<form method="post" action="CreateCourse">
										<div class="form-group col-lg-4">
											<label for="exampleInputEmail1">Course Name</label> <input
												class="form-control" name="course_name"
												id="exampleInputEmail1" aria-describedby="emailHelp">
										</div>



										<div class="form-group col-lg-4" id="createuserdiv">
											<button type="submit" class="btn btn-primary col-lg-6"
												id="createUserBtn" style="margin-top: 25px;">Create
												Course</button>
										</div>
									</form>
								</div>
							</div>
						</div>


						<div class="edit">
							<div class="row">
								<form method="POST" action="EditCourse">
									<div class="form-group col-lg-12">
										<label style="display: block;" for="exampleInputPassword1">Operation
											Type:</label>

										<div class="radio" style="display: inline;">
											<label><input id="add_prof" type="radio"
												name="opt_type" value="AddProfessor">Add Professor</label>
										</div>
										<div class="radio" style="display: inline;">
											<label><input id="add_stu" type="radio"
												name="opt_type" value="AddStudent">Add Student</label>
										</div>
										<div class="radio" style="display: inline;">
											<label><input id="edit_cours" type="radio"
												name="opt_type" value="ChangeCourseName">Change
												course name</label>
										</div>

										<div class="radio" style="display: inline;">
											<label><input id="remove_stu" type="radio"
												name="opt_type" value="RemoveStudent">Remove Student</label>
										</div>
										<div class="radio" style="display: inline;">
											<label><input id="remove_cours" type="radio"
												name="opt_type" value="RemoveCourse">Remove Course</label>
										</div>
									</div>
									<div class="add_professor">
										<div class="form-group col-lg-4">
											<label for="exampleInputEmail1">Course Name</label> <input
												class="form-control" id="exampleInputEmail1"
												name="professor_course_name" aria-describedby="emailHelp">
										</div>
										<div class="form-group col-lg-4">
											<label for="exampleInputPassword1">Professor
												Institutional Email</label> <input " class="form-control"
												name="professor_institution_email"
												id="exampleInputPassword1">
										</div>
										<div class="form-group col-lg-4" style="float: right;">
											<button type="submit" class="btn btn-primary col-lg-6"
												style="margin-top: 25px;">Add Professor</button>
										</div>
									</div>
									<div class="add_student">
										<div class="form-group col-lg-4">
											<label for="exampleInputEmail1">Course Name</label> <input
												class="form-control" name="student_course_name"
												id="exampleInputEmail1">
										</div>
										<div class="form-group col-lg-4">
											<label for="exampleInputPassword1">Student
												Institutional Email</label> <input class="form-control"
												name="student_institution_email" id="exampleInputPassword1">
										</div>
										<div class="form-group col-lg-4" style="float: right;">
											<button type="submit" class="btn btn-primary col-lg-6"
												style="margin-top: 25px;">Add Student</button>
										</div>
									</div>

									<div class="change_course_name">
										<div class="form-group col-lg-4">
											<label for="exampleInputEmail1">Course Name</label> <input
												class="form-control" id="exampleInputEmail1"
												name="change_course_name" aria-describedby="emailHelp">
										</div>
										<div class="form-group col-lg-4">
											<label for="exampleInputEmail1">New Course Name</label> <input
												class="form-control" id="exampleInputEmail1"
												name="new_course_name" aria-describedby="emailHelp">
										</div>
										<div class="form-group col-lg-4">
											<button type="submit" class="btn btn-primary col-lg-6"
												style="margin-top: 25px;">Change course name</button>
										</div>
									</div>


									<div class="remove_student">
										<div class="form-group col-lg-6">
											<label for="exampleInputEmail1">Course Name</label> <input
												class="form-control" id="exampleInputEmail1"
												aria-describedby="emailHelp"
												name="remove_student_course_name">
										</div>
										<div class="form-group col-lg-6">
											<label for="exampleInputPassword1">Student
												Institutional Email</label> <input class="form-control"
												id="exampleInputPassword1"
												name="remove_student_institutional_email">
										</div>
										<div class="form-group col-lg-6" style="float: right;">
											<button type="submit" class="btn btn-primary col-lg-6"
												style="margin-top: 25px; float: right;">Remove
												Student</button>
										</div>
									</div>
									<div class="remove_course">
										<div class="form-group col-lg-6">
											<label for="exampleInputEmail1">Course Name</label> <input
												class="form-control" name="remove_course_name"
												id="exampleInputEmail1" aria-describedby="emailHelp">
										</div>
										<div class="form-group col-lg-6" style="float: left;">
											<button type="submit" class="btn btn-primary col-lg-6"
												style="margin-top: 25px; float: left;">Remove
												Course</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
</body>
</html>