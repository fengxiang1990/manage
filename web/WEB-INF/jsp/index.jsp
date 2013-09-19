<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>
    <body background="./images/login_bg.jpg">
        <div align="center" style="margin-top: 100px">
            <h1>欢迎登陆系统</h1>
        </div>
        <form id="login" method="post" action="login.interface">
            <div style="margin-top:200px;margin-left:800px">
            <table>
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" id="username" name="username"/></td>
                </tr>
                <tr>
                    <td>密&nbsp;码:</td>
                    <td><input type="password" id="password" name="password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="登陆"/></td>
                    <td><input type="reset"  value="重置"/></td>
                </tr>
            </table>
            </div>
            <div style="margin-left: 800px">
                <p style="color: red">${msg}</p>
            </div>
        </form>
    </body>
</html>
