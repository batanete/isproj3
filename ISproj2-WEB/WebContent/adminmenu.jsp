<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Stuff Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link href="layout/admin.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script type="text/javascript">
	function message(){ 
		var do_display=${display_message};
	
		if(do_display)
	   		alert("${message}"); 
	} 
	

	
</script> 
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
                        <a href="adminmaterial.jsp"><i class="fa fa-downl"></i> Materials</a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header" style="padding-top:10px;margin-top: 10px;">
                            EasyDesk
                        </h1>
                    </div>
                </div>
                <div class="row col-lg-12">
                    <div class="col-lg-4 col-md-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <a href="adminuser.jsp">
                                    <div class="col-xs-12 text-right">
                                        <div style="text-align: center; font-size: 2em;color:#fff;"><i class="fa fa-user"></i> Users</div>
                                    </div>
                                    </a>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                     <a href="admincourse.jsp">
                                    <div class="col-xs-12">
                                        <div style="text-align: center; font-size: 2em;color:#fff;"><i class="fa fa-book" aria-hidden="true"></i>
Courses</div>
                                    </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                     <a href="adminmaterial.jsp">
                                    <div class="col-xs-12">
                                        <div style="text-align: center; font-size: 2em;color:#fff;"><i class="fa fa-download"></i> Materials</div>
                                    </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>