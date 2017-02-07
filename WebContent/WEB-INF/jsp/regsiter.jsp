<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2015/11/13
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><spring:message code="message.title" />!</title>
  <link href="static/style/base.css" rel="stylesheet" type="text/css" />
  <link href="static/style/layout.css" rel="stylesheet" type="text/css" />
  <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
</head>
<body class="l_body">
<div class="re_box">
  <div class="r_b_cn">
    <h2 class="h2"><img src="static/images/l01.jpg" width="412" height="39" /></h2>
    <form id="regsitForm" role="form" action="regsit.html" method="post" onsubmit="return check();">
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.userName" />：</label>
        <div class="col-md-4">
          <input id="loginname" name="loginname" type="text" placeholder="<spring:message code='message.enterOneUserName' />" /><font class="p_l10">*</font>
          <span id="loginerr" class="span"></span>
        </div>
      </div>
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.password" />：</label>
        <div class="col-md-4">
          <input id="password" name="password" type="password" placeholder="<spring:message code='message.enterPassword' />" /><font class="p_l10">*</font>
          <span id="pwderr" class="span"></span>
        </div>
      </div>
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.confirmPassword" />：</label>
        <div class="col-md-4">
          <input id="CFpassword" name="CFpassword" type="password" placeholder="<spring:message code='message.enterConfirmPassword' />" /><font class="p_l10">*</font>
          <span id="CFpwderr" class="span"></span>
        </div>
      </div>
      <div class="hr_fg"><hr></div>
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.fullName" />：</label>
        <div class="col-md-4">
          <input name="name" type="text" placeholder="<spring:message code='message.enterName' />" />
        </div>
      </div>
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.studentID" />：</label>
        <div class="col-md-4">
          <input name="studentid" type="text" placeholder="<spring:message code='message.enterStudentID' />" />
        </div>
      </div>
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.class" />：</label>
        <div class="col-md-4">
          <input name="studentClass" type="text" placeholder="<spring:message code='message.enterClass' />" />
        </div>
      </div>
      <div class="form-group clearfix">
        <label class="col-md-2"><spring:message code="message.major" />：</label>
        <div class="col-md-4">
          <input name="professional" type="text" placeholder="<spring:message code='message.enterMajor' />" />
        </div>
      </div>
      <div class="hr_fg"><hr></div>
      <div class="zc_tj_bnt">
        <input id="regsiter" type="button"  class="button_z"/>
      </div>
      <div class="pp"><spring:message code="message.existingAccount" />，<a href="login.html" title="立即登录"><spring:message code="message.loginImmediately" />!</a></div>
    </form>
  </div>
</div>
<script type="text/javascript" src="static/javascript/api/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
      $("#loginerr").hide();
      $("#pwderr").hide();
      $("#CFpwderr").hide();
		$("#regsiter").bind("click", function(){
				$("#regsitForm").submit();
		});
	})
    function resetErr(){
      $("#loginerr").hide();
      $("#loginerr").html("");
      $("#pwderr").hide();
      $("#pwderr").html("");
      $("#CFpwderr").hide();
      $("#CFpwderr").html("");
    }

    function check(){
      resetErr();
      if($("#loginname").val()=="" || $("#loginname").val()=="<spring:message code='message.enterOneUserName' />"){
        $("#loginerr").show();
        $("#loginerr").html("<spring:message code='message.userNameCannotBeEmpty' />！");
        $("#loginname").focus();
        return false;
      }
      if($("#password").val()=="" || $("#password").val()=="<spring:message code='message.enterPassword' />"){
        $("#pwderr").show();
        $("#pwderr").html("<spring:message code='message.passwordCanNotBeEmpty' />！");
        $("#password").focus();
        return false;
      }
      if($("#CFpassword").val()=="<spring:message code='message.enterConfirmPassword' />" || $("#CFpassword").val()==""){
        $("#CFpwderr").show();
        $("#CFpwderr").html("<spring:message code='message.enterConfirmPassword' />！");
        $("#CFpassword").focus();
        return false;
      }
      if($("#CFpassword").val()!=$("#password").val()){
        $("#CFpwderr").show();
        $("#CFpwderr").html("<spring:message code='message.inputSamePassword' />！");
        $("#CFpassword").focus();
        return false;
      }
      return true;
    }
</script>
</body>
</html>
