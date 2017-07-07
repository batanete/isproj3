$(document).ready(function(){
    $(".delete").hide();
    $(".create").show();
    $(".professor").hide();
    $(".student").hide();
    $(".search_form").hide();
    $(".both").hide();
    $(".edit").hide();
    $('.del').on('click', function(){
        $(".create").hide();
        $(".edit").hide();
        $(".search_form").hide();
        $(".delete").show();
        $(".cre").removeClass("activeUsers");
        $(".ed").removeClass("activeUsers");
        $(".search").removeClass("activeUsers");
        $(this).addClass("activeUsers");
    });
    $('.search').on('click', function(){
        $(".delete").hide();
        $(".create").hide();
        $(".edit").hide();
        $(".search_form").show();
        $(".del").removeClass("activeUsers");
        $(".cre").removeClass("activeUsers");
        $(".ed").removeClass("activeUsers");
        $(this).addClass("activeUsers");
    });   
    $('.cre').on('click', function(){
        $(".delete").hide();
        $(".edit").hide();
        $(".search_form").hide();
        $(".create").show();
        $(".ed").removeClass("activeUsers");
        $(".del").removeClass("activeUsers");
        $(this).addClass("activeUsers");
    });
    $('.ed').on('click', function(){
        $(".delete").hide();
        $(".create").hide();
        $(".search_form").hide();
        $(".edit").show();
        $(".del").removeClass("activeUsers");
        $(".cre").removeClass("activeUsers");
        $(this).addClass("activeUsers");
    });       
$('input[type="radio"]').click(function() {
   if($(this).attr('id') == 'prof') {
        $(".professor").show();
        $(".both").show();
        $(".student").hide();   
        $("#createUserBtn").addClass("createuser");
        $("#createuserdiv").addClass("createuser");
   }
   else if($(this).attr('id') == 'stu'){
        $(".professor").hide();
        $(".both").show();
        $(".student").show();  
        $("#createUserBtn").addClass("createuser");
        $("#createuserdiv").addClass("createuser");
   }
   else{
	    $(".professor").hide();
	    $(".student").hide(); 
	    $(".both").hide();
	    $("#createuserdiv").removeClass("createuser");
	    $("#createUserBtn").removeClass("createuser");
	    $("#createUserBtn").removeClass("createuserright");
	    $("#createUserBtn").addClass("createuseralgnleft");
	    $("#createUserBtn").addClass("createuseralgnleft");
       }
    });
 });