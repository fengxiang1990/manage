<%-- 
    Document   : tree
    Created on : 2013-8-22, 18:37:11
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
        $("#rstree").tree({
	onCheck: function(node,checked){
		alert(node.id+" "+checked);
	}
});
          function modifyRs(){
                 var nodes = $("#rstree").tree("getChecked");
                 var mids = new Array();
                 for(var i=0;i<nodes.length;i++){
                     console.log(nodes[i].id+" "+nodes[i].text);
                     mids.push(nodes[i].id);
                 }
                 var mids_val = mids.toString();
                 $.post("./addRs.interface",{rids:$("#rid").val(),mids:mids_val},function(data){
                    
                 });
             }
   </script>
    </head>
    <body>
       <input type="hidden" id="rid" value="${rid}"/>
       <ul id="rstree" class='easyui-tree' data-options="url:'./tree.interface?rid=${rid}',checkbox:true,cascadeCheck:false"></ul>
       <div style="margin-top:20px;margin-left: 80px">
                        <input class="easyui-button" type="button" value="确定" onclick="javascript:modifyRs()"/>
        </div>
    </body>
</html>
