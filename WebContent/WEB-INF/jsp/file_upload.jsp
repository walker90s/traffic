<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2015/11/13
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>欢迎访问北方工业大学交通仿真系统!</title>
  <link href="static/style/base.css" rel="stylesheet" type="text/css" />
  <link href="static/style/layout.css" rel="stylesheet" type="text/css" />
  <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
</head>
<body class="l_body">
<div class="login_box">
  <form  enctype="multipart/form-data" id="loginForm" action="uploadFile" method="post" >
    <input type="file" name="file" size="50" />
    <br />
    <input type="submit" value="Upload File" />
  </form>
</div>
</body>
</html>
