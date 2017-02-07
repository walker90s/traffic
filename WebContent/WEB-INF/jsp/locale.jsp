<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="message.title" /></title>
    <link rel="stylesheet" href="static/style/ol.css" type="text/css">
    <link href="static/style/base.css" rel="stylesheet" type="text/css"/>
    <link href="static/style/layout.css" rel="stylesheet" type="text/css"/>
    <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
    <LINK rel=stylesheet type=text/css href="static/style/popup.css">
</head>
<body class="body">
    <div>
        <a href="/locale?langType=zh">中文</a>/
        <a href="/locale?langType=en">英文</a>
    </div>
    <div>
        <spring:message code="js.message_lang" />
    </div>
    <div>
        <spring:message code="message.demo" arguments="这个是0;这个是1" argumentSeparator=";"/>
    </div>
    <!--
    <script language="javascript">
        document.write("<script src='<spring:message code="js.message_lang" />'><\/script>");
    </script>-->
</body>
</html>