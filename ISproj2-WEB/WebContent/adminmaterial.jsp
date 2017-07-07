<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="layout/admin.css" rel="stylesheet">
<script type="text/javascript" src="layout/materials.js"></script>
</head>
<body onload="message()">
<div id="wrapper">
	<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
			<form method="post" action="Logout">
			<div class="login">	
				<button type="submit" class="btn btn-primary" style="background-color:none;" ><i class="fa fa-fw fa-power-off" name="logout"></i> Logout</button>
			</div>
			</form>
		</div>
	</div>
		
		</div>
		<div>
			<ul class="nav navbar-nav side-nav">
                    <li class="">
                        <a href="adminmenu.jsp"><i class="fa fa-home"></i> Home</a>
                    </li>
                    <li>
                        <a href="adminuser.jsp"><i class="fa fa-user"></i> Users</a>
                    </li>
                    <li>
                        <a href="admincourse.jsp"><i class="fa fa-book"></i> Courses</a>
                    </li>
                    <li>
                        <a href="adminmaterial.jsp"><i class="fa fa-download"></i> Materials</a>
                    </li>
                </ul>
		</div>
		</div>

		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header" style="margin-top: 10px; padding-top:10px;">
							EasyDesk - <small> Materials</small>
						</h1>
						<div style="margin-bottom: 20px;">
							<ul class="nav nav-pills nav-justified">
							<li><button class="list_materials activeUsers" data-toggle="tab">List Materials in Course</button></li>
							<li><button class="transfer_materials" data-toggle="tab">Transfer Materials in Course</button></li>
							<li><button class="remove" data-toggle="tab">Delete
										Material from Course</button></li>
								
							</ul>
						</div>
						<div class="transfer_material">
							<div class="row">
								<form method="post" action="TransferMaterialStudent">
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Course Name</label> <input
											 class="form-control" name="course_name" id="exampleInputEmail1"
											aria-describedby="emailHelp" >
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Material Name</label> <input
											 class="form-control" name="material_name" id="exampleInputEmail1"
											aria-describedby="emailHelp" >
									</div>
									<div class="form-group col-lg-4" style="">
										<button type="submit" class="btn btn-primary col-lg-6"
											style=" margin-top:25px;">Transfer Material</button>
									</div>
								</form>
							</div>
						</div>

						<div class="remove_material">
							<div class="row">
								<form method="post" action="RemoveMaterialAdmin">
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Course Name</label> <input
											 class="form-control" name="course_name" id="exampleInputEmail1"
											aria-describedby="emailHelp" >
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Material Name</label> <input
											 class="form-control" name="material_name" id="exampleInputEmail1"
											aria-describedby="emailHelp" >
									</div>
									
									<div class="form-group col-lg-4">
										<button type="submit" style="margin-top:25px;" class="btn btn-primary col-lg-6"
											">Delete Material</button>
									</div>
								</form>
							</div>
						</div>
						
						<div class="list_materials_course">
							<div class="row">
								<form method="post" action="ListMaterialAdmin">
									<div class="form-group col-lg-4">
									<label for="exampleInputEmail1">Course Name</label> <input
										 class="form-control" name="course_name" id="exampleInputEmail1"
										aria-describedby="emailHelp">
									</div>
									<div class="form-group col-lg-6" id="createuserdiv" style="">
										<button type="submit" class="btn btn-primary col-lg-4"
											id="createUserBtn" style="margin-top: 25px;">List
											Materials</button>
									</div>
								</form>
							</div>
							<div class="row">
				          <div class="form-group col-lg-4" style="display: block;">
				            
				             <%  
				             
							   if((request.getAttribute("courseList") == null)){
								   out.println("");
							   }
							   else{
							   out.print("<h5><b>Materials in CourseName</b></h5>");
							   ArrayList<String> coursesList = (ArrayList<String>) request.getAttribute("courseList"); 
							
							   out.println("<ul class='list-group'>");
							   
							  		for (String string : coursesList){
							  			 out.println("<li class='list-group-item'>"+ string.toString() + "</li>");
							  			 System.out.println(string);
							  		}
							  		
							  		out.println("</ul>");
							  	}
						   					   
				   				%>
			
				          </div>
				        </div>
						</div>
					</div>
</body>
</html>