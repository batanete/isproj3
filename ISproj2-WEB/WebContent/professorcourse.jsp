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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="layout/admin.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body onload="message()">
<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
				<form method="post" action="Logout">
				<div class="login">	
					<button type="submit" class="btn btn-primary" style="background-color:none; "><i class="fa fa-fw fa-power-off" name="logout"></i> Logout</button>
				</div>
				</form>
			</div>
		</div>
			
			</div>
 <div>
    <ul class="nav navbar-nav side-nav">
        <li class="">
            <a href="professormenu.jsp"><i class="fa fa-home"></i> Home</a>
        </li>
	    <li><a href="professoruser.jsp"><i class="fa fa-user"></i> Search Users</a>
        <li>
            <a href="professorcourse.jsp"><i class="fa fa-book"></i> Courses</a>
        </li>
        <li>
            <a href="professormaterial.jsp"><i class="fa fa-download"></i> Materials</a>
        </li>
    </ul>
  </div>
</div>
<div id="page-wrapper">
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header" style="margin-top: 10px; padding-top:10px;">
          EasyDesk - <small> Courses</small>
        </h1>
	      
	      <div class="list_students">
        <div class="row">

      		<form method="post" action="ListStudents">
      		<div class="form-group col-lg-12">
				<label style="display: block;"> Order criteria:</label>
				<div class="radio" style="display: inline;">
					<label><input id="adm" type="radio" name="opt_type" value="Ascending">Ascending</label>
				</div>
				<div class="radio" style="display: inline;">
					<label><input id="prof" type="radio" name="opt_type" value="Descending">Descending</label>
				</div>
			</div>
      
         	<div class="form-group col-lg-4">
               <label for="exampleInputPassword1">Course Name</label>
               <input  class="form-control" id="exampleInputPassword1" name="course_name">
             </div>
          		 <div class="form-group col-lg-4" id="createuserdiv" style="">                        
            		 <button type="submit" class="btn btn-primary col-lg-4" id="createUserBtn" style="margin-top: 25px; ">List Students</button>
           	</div>
       		</div>
			<div class="row">
	          <div class="form-group col-lg-4" style="display: block;">
	            
	             <%  
	             
				   if((request.getAttribute("course") == null)){
					   out.println("");
				   }
				   else{
				   out.print("<h5><b>Students in CourseName</b></h5>");
				   ArrayList<String> studentsList = (ArrayList<String>) request.getAttribute("course"); 		   
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
        </div>
      </div>
      </body>
    </html>