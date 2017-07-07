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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="layout/admin.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="layout/usersprofessor.js"></script>
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
					<li><a href="professormenu.jsp"><i class="fa fa-home"></i> Home</a></li>
					<li><a href="professoruser.jsp"><i class="fa fa-user"></i> Search Users</a>
					</li>
					<li><a href="professorcourse.jsp"><i class="fa fa-book"></i>
							Courses</a></li>
					<li><a href="professormaterial.jsp"><i class="fa fa-download"></i>
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
						
						
						
						<div class="search_form">
							<div class="row">
								<form method="post" action="SearchStudent">
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Name</label> <input
											 class="form-control" name="name" id="exampleInputEmail1"
											aria-describedby="emailHelp">
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Address</label> <input
											 class="form-control" name="address" id="exampleInputEmail1"
											aria-describedby="emailHelp">
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Institutional Email</label> <input
											 class="form-control" id="exampleInputEmail1" name="institutional_email"
											aria-describedby="emailHelp" >
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Alternative Email</label> <input
											t class="form-control" id="exampleInputEmail1" name="alternative_email"
											aria-describedby="emailHelp" >
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Phone</label> <input
											 class="form-control" name="phone" id="exampleInputEmail1"
											aria-describedby="emailHelp">
									</div>
									<div class="form-group col-lg-4">
										<label for="exampleInputEmail1">Number</label> <input
											 class="form-control" name="student_number" id="exampleInputEmail1"
											aria-describedby="emailHelp">
									</div>
									<div class="form-group col-lg-4" style="float:right;">
										<button type="submit" class="btn btn-primary col-lg-4"
											style="float:right;">Search User</button>
									</div>
								</form>
							</div>
							<div class="row">
					          <div class="form-group col-lg-4" style="display: block;">
					            
					            
					             <%  
					             
								   if((request.getAttribute("students") == null)){
									   out.println("");
								   }
								   else{
								   out.print("<h5><b>Students:</b></h5>");
								   ArrayList<String> studentsList = (ArrayList<String>) request.getAttribute("students"); 
								
								   out.println("<ul class='list-group'>");
								   
								  		for (String string : studentsList){
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
