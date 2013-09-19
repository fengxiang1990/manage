<%-- 
    Document   : main
    Created on : 2013-8-15, 18:09:11
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
        <script type="text/javascript" src="./js/frame.js"></script>
    </head>
    <body class="easyui-layout" onload="loadLevel1Menu('${msg}')">
	<div data-options="region:'north',border:false" style="height:120px;background:url('./images/top.jpg') no-repeat;padding:10px">
       <div style="height: 20px" id="top">
           <input type ="hidden"  value="${msg}" id="user"/>
       </div>
        <div style="height: 40px" id="lev1" name="lev1">
        </div>
        <div style="height: 40px" id="lev2" name="lev2">
           
       </div>
        </div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
            <div id="menuManage" name="menuManage" style="display: block" >
            </div>
        </div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'内容'" id="centerdiv" name="centerdiv">
            <div id="maindiv" name="maindiv">
            </div>
        </div>
</body>
</html>
