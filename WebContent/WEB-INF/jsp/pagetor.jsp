<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="message.title" />!</title>
    <link href="static/style/base.css" rel="stylesheet" type="text/css"/>
    <link href="static/style/layout.css" rel="stylesheet" type="text/css"/>
    <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
</head>
<body>
	<c:choose>
		<c:when test="${page == null}">
			<span class="main_left"><spring:message code="message.all" />0<spring:message code="message.strip" />，<spring:message code="message.eachPage" />0<spring:message code="message.strip" /></span>
       <ul class="pager pager-pills main_right">
    	<input type="hidden" id="pageNo" name="pageNo" value="0"/>
    	<input type="hidden" id="pageSize" name="pageSize" value=""/>
  		<li class="previous disabled">
   			<a href="#">«</a>
   		</li>
		<li class="next disabled">
   			<a href="#">»</a>
   		</li>
		</c:when>
		
		<c:otherwise>
			<span class="main_left"><spring:message code="message.all" />${page.totalCount}<spring:message code="message.strip" />，<spring:message code="message.eachPage" />${page.pageSize}<spring:message code="message.strip" /></span>
       <ul class="pager pager-pills main_right">
    	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }"/>
    	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/>
		<c:choose>
			<c:when test="${page.pageNo == 1}">
          		<li class="previous disabled">
           			<a href="#">«</a>
           		</li>
        	</c:when>
       		<c:otherwise>
				<li class="previous">
        			<a href="#">«</a>
        			<a href="#" onclick="javascript:gotoPage(${page.pageNo-1})">«</a>
         		</li>
			</c:otherwise>
		</c:choose>
           
           <c:forEach items="${page.indexList }" var="vardata">
	           	<c:choose>
					<c:when test="${vardata == page.pageNo}">
	           			<li class="active">
	           		</c:when>
	           		<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
	           	<a href="#" onclick="javascript:gotoPage(${vardata})">${vardata}</a>
           </li>
           </c:forEach>
           
		<c:choose>
			<c:when test="${page.pageNo == page.pageSum || page.pageSum == 0}">
          		<li class="next disabled">
           			<a href="#">»</a>
           		</li>
        	</c:when>
       		<c:otherwise>
				<li class="next">
        			<a href="#" onclick="javascript:gotoPage(${page.pageNo+1})">»</a>
         		</li>
			</c:otherwise>
		</c:choose>
       </ul>
		</c:otherwise>
	</c:choose>
   
</body>
</html>
