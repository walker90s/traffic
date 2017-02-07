<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="message.title" />!</title>
<link href="static/style/base.css" rel="stylesheet" type="text/css" />
<link href="static/style/layout.css" rel="stylesheet" type="text/css" />
<LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
<script type="text/javascript" src="static/javascript/api/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="static/javascript/map/simulate.js"></script>
<style type="text/css">
	.lookpage{
	}
</style>
</head>
<body>
<!--头部-->
	<div class="header">
    	<div class="logo main_left">
        	<div id="time">2015年,9月12日,星期六</div>
        </div>
        <div class="h_r">
        	<div class="h_r_t"><a href="quitting.html" title="安全退出" class="main_right"><i class="fa fa-power-off m_r10"></i><spring:message code="message.exit" /></a><a href="index.html" title="系统首页" class="main_right bor"><i class="fa fa-home m_r10"></i><spring:message code="message.systemHome" /></a><!--<a href="regsiter.html" title="注册" class="main_right"><i class="fa fa-unlock-alt m_r10"></i>注册</a><a href="login.html" title="登录" class="main_right bor"><i class="fa fa-user m_r10"></i>登录</a>--><spring:message code="message.title" />!</div>
            <div class="nav">
            	<ul class="main_right admin_w">
                    <li><a href="usermanage.html" ><span class="a2"><spring:message code="message.userManagement" /></span></a> </li>
                	<li><a href="simulate.html"><span class="a1"><spring:message code="message.dataManagement" /></span></a></li>
                </ul>
            	<div class="n_b_hy">
           	    <img src="static/images/my01.jpg" width="60" height="60" class="img_t m_l15 m_t10"/>
                <div class="hytxt p_l20 m_l10"><i class="fa fa-user m_r10"></i><spring:message code="message.helloAdmin" /> <font class="f_c_1 p_l10">${username}</font>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                </div>
            </div>
        </div>
    </div>
<!--头部 结束-->
<!--主体-->
<!--表格-->
    <div class="la_ti m_l15 m_r15 p_b20 p_t20">
        <spring:message code="message.dataManagement" />
</div>
    <div class="m_l15 m_r15">
    	<table width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="3%"><spring:message code="message.serialNumber" /></th>
            <th width="12%"><spring:message code="message.simulationDataName" /></th>
            <th width="7%"><spring:message code="message.uploadPerson" /></th>
            <th width="6%"><spring:message code="message.simulationSoftware" /></th>
            <th width="10%"><spring:message code="message.simulationModel" /></th>
            <th width="12%"><spring:message code="message.simulationTime" /></th>
            <th width="10%"><spring:message code="message.simulationStartTime" /></th>
            <th width="12%"><spring:message code="message.simulationEndTime" /></th>
            <th width="18%"><spring:message code="message.explain" /></th>
            <th width="10%"><spring:message code="message.operation" /></th>
          </tr>
         </thead>
         <c:if test="${Simlist!=null }">
         	<c:forEach items="${Simlist }" var="list">
         		<tr>
            		<td align="center" valign="middle">${list.id }</td>
            		<td align="center" valign="middle">${list.name }</td>
            		<td align="center" valign="middle">${list.importUserName }</td>
            		<td align="center" valign="middle">${list.simulateSoft }</td>
            		<td align="center" valign="middle">${list.simulateModel }</td>
            		<td align="center" valign="middle">${list.simulateTime }</td>
            		<td align="center" valign="middle">${list.simulateStartTime }</td>
            		<td align="center" valign="middle">${list.simulateEndTime }</td>
            		<td align="center" valign="middle">${list.comment }</td>
            		<td align="center" valign="middle"><a href="javascript:void(0);" onClick="deleteData(this);" dataId=${list.id } model="${list.simulateModel }" title="删除" class="fa fa-trash cz_an"></a></td>
          		</tr>
         	</c:forEach>
         </c:if>
        </table>
		<div class="page">
        	<span class="main_left" page="${page }"><spring:message code="message.all" />${page }<spring:message code="message.page" />，<spring:message code="message.eachPage" />10<spring:message code="message.strip" /></span>
          <ul class="pager pager-pills main_right">
                <li class="previous disabled">
                <a href="#">«</a>
                </li>
                <li class="active">
                <a href="#">1</a>
                </li>
                <li>
                <a href="#">2</a>
                </li>
                <li>
                <a href="#">3</a>
                </li>
                <li>
                <a href="#">4</a>
                </li>
                <li>
                <a href="#">5</a>
                </li>
                <li class="next">
                <a href="#">»</a>
                </li>
            </ul>
        </div>
</div>
<!--主体 结束-->
</body>
</html>