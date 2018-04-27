<%--
  Created by IntelliJ IDEA.
  User: welljun06
  Date: 2017/12/30
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/style.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>注册界面</title>
</head>
<style>
    body{background:#33425B;color:#5B5B5D;font-family:"Microsoft YaHei","Segoe UI","Lucida Grande",Helvetica,Arial,sans-serif}
    *{margin:0;margin:0}
    .container-fluid{margin:0 auto;text-align:center;overflow:hidden;padding: 0;height:100%;}
    *,*:after,*:before{-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box}

</style>
<body>
<p class="atitle">用户注册</p>

<div class="container-fluid">
    <div style="height: 10% "></div>
    <div class="demo form-bg" style="padding: 0px 0;">
        <div class="container" >
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/RegisterServlet" method="post">
                        <span class="heading">用户注册</span>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="用户名" name="uname" value="${form.uname}">
                            <span>${form.errors.uname}</span>
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="密码" name="password" >
                            <span>${form.errors.password}</span>
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="确认密码" name="confirm">
                            <span>${form.errors.confirm}</span>
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="手机" name="phone" value="${form.phone}">
                            <span>${form.errors.phone}</span>
                            <i class="fa fa-mobile"></i>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="邮箱" name="email" value="${form.email}">
                            <span>${form.errors.email}</span>
                            <i class="fa fa-envelope-o"></i>
                        </div>

                        <div class="form-group">

                            <button type="submit" class="btn btn-default">注册</button>
                        </div>
                        <a href="${pageContext.request.contextPath}/LoginUIServlet" style="color: #0f0f0f">登录</a>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
