<%-- 
    Document   : roleMenu
    Created on : 2013-8-22, 22:46:19
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="./css/easyui.css">
	<link rel="stylesheet" type="text/css" href="./css/icon.css">
	<link rel="stylesheet" type="text/css" href="./css/demo.css">
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
        <script>
            $(document).ready(function(){
                initMenu();
            });
            function initMenu(){
            }
            $('#tree').tree({
	          onClick: function(node){
                    $.get("./getAction.interface",{id:node.id},
                         function(data){
                           $("#maindiv").load(""+data+"",{id:node.id});
                });
	}
});
        </script>
    </head>
    <body>
        <input type="hidden" value="${id}" id="rid" name="rid"/>
        <div id="menudiv" name="menudiv" >
            <ul id='tree' name='tree' class='easyui-tree' data-options="url:'./roleMenu.interface'"></ul> 
        </div>
    </body>
</html>
