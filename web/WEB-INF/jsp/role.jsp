<%-- 
    Document   : role
    Created on : 2013-8-22, 21:31:19
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
            $("#tt").datagrid({
                onClickRow: function(index, data) {
                    console.log(index + " " + data.name);
                    //selectRow(index);
                },
                onSelect: function(index, data) {
                    console.log("row" + index + "is selected");
                },
                onUnselect: function(index, data) {
                    console.log("row" + index + "is unselected");
                }
            });

            function loadAddWinow() {
                $('#adddiv').dialog({
                    title: '添加角色',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    modal: true
                });
                //$('#dd').dialog('refresh', 'new_content.php');
            }
            function addRole() {
                $.messager.progress();	// display the progress bar
                $('#addRoleForm').form('submit', {
                    url: './addrole.interface',
                    onSubmit: function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');	// hide progress bar while the form is invalid
                        }
                        return isValid;	// return false will stop the form submission
                    },
                    success: function() {
                        $('#adddiv').dialog({
                            title: '添加角色',
                            width: 400,
                            height: 200,
                            closed: true,
                            cache: false,
                            modal: true
                        });
                        $.messager.progress('close');	// hide progress bar while submit successfully
                        $("#maindiv").load("./rolejsp.interface");
                    }
                });
            }
              function  loadRsWinow(){
              $('#rsdiv').dialog({
                    title: '角色授权',
                    width: 400,
                    height: 500,
                    closed: false,
                    cache: false,
                    modal: true
                });
            }
             $("#rstree").tree(
                {
                   onCheck:function(node,checked){
                    ///   alert(node.text+" "+checked);
                   }
                }
                );
             function modifyRs(){
                 var nodes = $("#rstree").tree("getChecked");
                 for(var i=0;i<nodes.length;i++){
                     console.log(nodes[i].id+" "+nodes[i].text);
                 }
             }
        </script>
    </head>
    <body>
        <table id="tt" class="easyui-datagrid" style="width:1600px;height:600px"
               url="./role.interface"
               title="DataGrid with Toolbar" iconCls="icon-save"
               toolbar="#tb">
            <thead>
                <tr>
                    <th field="id" width="200px">Role Id</th>
                    <th field="name" width="1400px">Role Name</th>
                </tr>
            </thead>
        </table>
        <div id="tb">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:loadAddWinow()">添加角色</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:loadRsWinow()">授予权限</a>
        </div>
        <div id="adddiv" class="easyui-dialog" data-options='closed:true'>
            <center>
                <form id="addRoleForm" method="post">
                    <div style="margin-top:20px;">
                        <label for="name">角色名:</label>
                        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
                    </div>
                    <div style="margin-top:20px;">
                        <input class="easyui-button" type="button" name="addbtn" value="确定" onclick="javascript:addRole()"/>
                    </div>
                </form>
            </center>
        </div>
        <div id="rsdiv" class="easyui-dialog" data-options='closed:true'>
            <ul id='rstree' class='easyui-tree' data-options="url:'./tree.interface',checkbox:true,cascadeCheck:false"></ul>
            <div style="margin-top:20px;margin-left: 20px">
                        <input class="easyui-button" type="button" value="确定" onclick="javascript:modifyRs()"/>
             </div>
        </div>
    </body>
</html>
