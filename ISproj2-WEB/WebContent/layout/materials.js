$(document).ready(function(){
        $(".list_materials_course").show();
        $(".remove_material").hide();
        $(".transfer_material").hide();
        $(".upload_material").hide();
        
        $('.upload').on('click', function(){
            $(".list_materials_course").hide();
            $(".remove_material").hide();
            $(".transfer_material").hide();
            $(".create").hide();
            $(".upload_material").show();
            $(".ed").removeClass("activeUsers");
            $(".list_materials").removeClass("activeUsers");
            $(".remove").removeClass("activeUsers");
            $(this).addClass("activeUsers");
        });
        $('.list').on('click', function(){
            $(".list_materials_course").hide();
            $(".remove_material").hide();
            $(".upload_material").hide();
            $(".transfer_material").hide();
            $(".remove").removeClass("activeUsers");
            $(".list_materials").removeClass("activeUsers");
            $(".upload").removeClass("activeUsers");
            $(".transfer_materials").removeClass("activeUsers");
            $(this).addClass("activeUsers");
        });
        $('.list_materials').on('click', function(){
            $(".list_materials_course").show();
            $(".remove_material").hide();
            $(".transfer_material").hide();
            $(".upload_material").hide();
            $(".remove").removeClass("activeUsers");
            $(".upload").removeClass("activeUsers");
            $(".transfer_materials").removeClass("activeUsers");
            $(this).addClass("activeUsers");
        });
        $('.transfer_materials').on('click', function(){
            $(".list_materials_course").hide();
            $(".remove_material").hide();
            $(".transfer_material").show();
            $(".upload_material").hide();
            $(".remove").removeClass("activeUsers");
            $(".upload").removeClass("activeUsers");
            $(".list_materials").removeClass("activeUsers");
            $(this).addClass("activeUsers");
        });
        $('.remove').on('click', function(){
            $(".list_materials_course").hide();
            $(".transfer_material").hide();
            $(".remove_material").hide();
            $(".upload_material").hide();
            $(".remove_material").show();
            $(".list_materials").removeClass("activeUsers");
            $(".transfer_materials").removeClass("activeUsers");
            $(".upload").removeClass("activeUsers");
            $(this).addClass("activeUsers");
        });
      });