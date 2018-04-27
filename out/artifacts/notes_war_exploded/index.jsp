<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/index.js"></script>
    <title>note</title>
</head>
<body>

<div class="container-fluid" style="height: 100%;">
<div class="row top-content" style="height: 15%;">
    <div class="top-title">
        note
    </div>
    <div class="top-welcome">
    <c:if test="${user!=null}">
        欢迎您：${user.uname}
        <a href="${pageContext.request.contextPath }/LogoutServlet">注销</a>
    </c:if>
    <c:if test="${user==null}">
        <a href="${pageContext.request.contextPath }/RegisterUIServlet">注册</a>
        <a href="${pageContext.request.contextPath }/LoginUIServlet">登录</a>
    </c:if>
    </div>

</div>
<div class="row" style="height: 85%;" >
    <div class="col-md-2 left-navigation"   style="padding: 0em;">
        <div class="left-navigation-list"  id="left-content">
            <div id="create-note">
            </div>
            <div id="received-note">
            </div>
            <script type="text/javascript">
                window.onload=getAllNotes();

            </script>
        </div>
        <div class="left-navigation-button text-center" id="">
        </div>
    </div>

    <div class="col-md-10" id="right-content" >

    </div>
    <script type="text/javascript">
        window.onload=newNote();
    </script>
</div>
</div>

</body>
</html>