<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2015/11/13
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title><spring:message code="message.title" /></title>
  <link href="static/style/base.css" rel="stylesheet" type="text/css" />
  <link href="static/style/layout.css" rel="stylesheet" type="text/css" />
  <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
</head>
<body class="l_body">
<div class="login_box">
  <form id="loginForm" action="login.html" method="post"  onsubmit="return check();">
    <dl class="box1">
      <input id="loginname" name="loginname" type="text" placeholder="<spring:message code='message.enterOneUserName' />" />
      <span id="nameerr" class="span"></span>
    </dl>
    <dl class="box2">
      <input id="password" name="password" type="password"  placeholder="<spring:message code='message.enterPassword' />"/>
      <span id="pwderr" class="span"></span>
    </dl>
    <dl class="box3">
      <img id="codeImg" src="images/my01.gif" width="54" height="30" class="main_right" /><input id="code" name="code" type="text"  placeholder="<spring:message code='message.inputVerificationCode' />"/>
      <span id="codeerr" class="span"></span>
    </dl>
    <dl class="box4 text-center">
      <input id="logged" name="logged" type="button" />
    </dl>
    <dl class="box5 text-center">
      <spring:message code="message.noAccount" />，<a href="regsiter.html" title="<spring:message code='message.registerImmediately' />"><spring:message code="message.registerImmediately" />!</a>
      <!--<div class="lo_dl_ld"><p class="p"></p><a href="#"><img id="submitImg" src="images/lo_bt.gif" width="255" height="40" /></a></div>-->
    </dl>
  </form>
</div>
<script type="text/javascript" src="static/javascript/api/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
  var errInfo = "${errInfo}";
  $(document).ready(function(){
    resetErr();
    changeCode();
    $(window).keydown(function(event){
      switch(event.keyCode) {
        case 13:
          $("#loginForm").submit();
          break;
        // ...
        // 不同的按键可以做不同的事情
        // 不同的浏览器的keycode不同
        // 更多详细信息:     http://unixpapa.com/js/key.html
        // ...
      }
    });
    $("#logged").bind("click",function(){
      $("#loginForm").submit();
    });
    $("#codeImg").bind("click",changeCode);
    if(errInfo!=""){
      if(errInfo.indexOf("<spring:message code='message.verificationCode' />")>-1){
        $("#codeerr").show();
        $("#codeerr").html(errInfo);
        $("#code").focus();
      }else{
        $("#nameerr").show();
        $("#nameerr").html(errInfo);
      }
    }
    $("#loginname").focus();
  });

  function genTimestamp(){
    var time = new Date();
    return time.getTime();
  }

  function changeCode(){
    $("#codeImg").attr("src","code.shtml?t="+genTimestamp());
  }

  function resetErr(){
    $("#nameerr").hide();
    $("#nameerr").html("");
    $("#pwderr").hide();
    $("#pwderr").html("");
    $("#codeerr").hide();
    $("#codeerr").html("");
  }

  function check(){
    resetErr();
    if($("#loginname").val()=="" || $("#loginname").val()=="<spring:message code='message.enterOneUserName' />"){
      $("#nameerr").show();
      $("#nameerr").html("<spring:message code='message.userNameCannotBeEmpty' />！");
      $("#loginname").focus();
      return false;
    }
    if($("#password").val()=="" || $("#password").val()=="<spring:message code='message.enterPassword' />"){
      $("#pwderr").show();
      $("#pwderr").html("<spring:message code='message.passwordCanNotBeEmpty' />！");
      $("#password").focus();
      return false;
    }
    if($("#code").val()=="<spring:message code='message.inputVerificationCode' />" || $("#code").val()==""){
      $("#codeerr").show();
      $("#codeerr").html("<spring:message code='message.verificationCodeCannotBeEmpty' />！");
      $("#code").focus();
      return false;
    }
    return true;
  }
</script>
</body>
</html>
