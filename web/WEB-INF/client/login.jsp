<%--
  Created by IntelliJ IDEA.
  User: welljun06
  Date: 2017/12/29
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <title>Title</title>
</head>
<style>
    body{background:#33425B;color:#5B5B5D;font-family:"Microsoft YaHei","Segoe UI","Lucida Grande",Helvetica,Arial,sans-serif}
    *{margin:0;margin:0}
    .container-fluid{margin:0 auto;text-align:center;overflow:hidden;padding: 0;height:100%;}
    *,*:after,*:before{-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box}


</style>
<body>
<div class="container-fluid">
    <div style="height: 20% "></div>
    <div class="demo form-bg" style="padding: 0px 0;">
        <div class="container" >
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/LoginServlet" method="post">
                        <span class="heading">用户登录</span>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="用户名" name="uname">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" class="form-control" placeholder="密　码" name="password">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">登录</button>
                        </div>
                        <a href="${pageContext.request.contextPath}/RegisterUIServlet" style="color: #0f0f0f">注册</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
</body>
</html>
