<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2015/11/24
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><spring:message code="message.title" />!</title>
  <link href="static/style/base.css" rel="stylesheet" type="text/css" />
  <link href="static/style/layout.css" rel="stylesheet" type="text/css" />
  <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
  <script type="text/javascript" src="static/javascript/api/jquery/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="static/javascript/map/Page4.js"></script>
  <script type="text/javascript" src="static/javascript/map/addModel.js"></script>
  <script type="text/javascript" src="static/javascript/map/ajaxfileupload.js"></script>
  <script type="text/javascript" src="static/javascript/map/dataquery.js"></script>
  <link href="static/style/manage_menu/style.css" rel="stylesheet" type="text/css" />
  <style>
  p{color:yellow;float:left;}
  body {font-size:12px; 
         margin:0;
        padding:0;}
	#project { position:relative; 
	            margin: auto;         /*---页面居中的写法------*/
	
	}
	.vb { 
	    float:left;
		
	}
	
	
	.bb {float:left;
		
	}
	.vv {float:left;
		
	}
</style>
</head>
<body  class="body">
<!--头部-->
<div class="header">
  <div class="logo main_left">
    <div id="now_time">2015年,9月12日,星期六</div>
  </div>
  <div class="h_r">
    <div class="h_r_t">
      <a href="/modelmanage?locale=zh_CN" class="main_right">中文</a>/<a href="/modelmanage?locale=en_US" class="main_right">English</a>
      <c:if test="${type!=null}">
        <a href="quitting.html" title="<spring:message code='message.exit' />" class="main_right"><i class="fa fa-power-off m_r10"></i><spring:message code="message.exit" /></a>
      </c:if>
      <c:if test="${type==null}">
        <a href="regsiter.html" title="<spring:message code='message.register' />" class="main_right"><i class="fa fa-unlock-alt m_r10"></i><spring:message code="message.register" /></a>
        <a href="login.html" title="<spring:message code='message.login' />" class="main_right bor"><i class="fa fa-user m_r10"></i><spring:message code="message.login" /></a><spring:message code="message.title" />!
      </c:if>
    </div>
    <div class="nav">
      <ul class="main_right">
        <li><a href="index.html"><span class="i1"><spring:message code="message.mapShows" /></span></a></li>
        <li><a href="page2.html" class="on"><span class="i2"><spring:message code="message.resultsShow" /></span></a> </li>
        <li><a href="page3.html"><span class="i3"><spring:message code="message.simulationControl" /></span></a> </li>
        <li><a href="modelmanage.html"><span class="i4"><spring:message code="message.managementModel" /></span></a> </li>
      </ul>
      <div class="n_b_hy">
        <img src="static/images/my01.jpg" width="60" height="60" class="img_t m_l15 m_t10"/>
        <div class="hytxt p_l20 m_l10">
          <c:if test="${type!=null}">
            <c:if test="${type==1}">
              <a href="usermanage.html" title="<spring:message code='message.groundManagement' />" class="m_l10 p_l10 p_r20" disabled="disabled">
                <i class="fa fa-cog m_r10"></i><spring:message code="message.groundManagement" />
              </a>
            </c:if>
            <i class="fa fa-user m_r10"></i>
            <spring:message code="message.hello" />, <font class="f_c_1 p_l10">${username}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </c:if>
          <c:if test="${type==null}">
            <i class="fa fa-user m_r10"></i><spring:message code="message.notLoggedIn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </c:if>
        </div>
      </div>
    </div>
  </div>
</div>
<!--头部 结束-->
<!--主体-->
<div class="layout clearfix">
  <!--左侧-->
  <!--"height:1000px;"临时高度-->
  <div id="left" class="left" style="overflow:auto;">
    <div class="l_n" id="model_list">
      <div>
        <h2>
          <div class="dh_cn_llist">
            <%--<div class="cn_div"><b></b><a href="#" class="on">按类型</a><a href="#">按顺序</a></div>--%>
          </div>
          <a href="#"><i class="fa fa-plus"></i><spring:message code="message.model" /></a>
        </h2>
        
        <div class="htmleaf-container">
			<nav class="animenu"> 
			  <button class="animenu__toggle">
				<span class="animenu__toggle__bar"></span>
				<span class="animenu__toggle__bar"></span>
				<span class="animenu__toggle__bar"></span>
			  </button>
			  <ul class="animenu__nav">
				<li>
				  <a href="#" style="line-height:10px;">选择项目</a>
				  <ul id="projectListUl" class="animenu__nav__child">
				<!-- 	<li><a href="" style="line-height:10px;">添加新项目</a></li> -->
				  </ul>
				</li>     
			  </ul>
			</nav>
		</div>
        
        <h3 class="ti_b" onclick="toggleModel('1');">
          <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
          <a href="javascript:void(0);"><i id="model_1" class="fa fa-angle-up"></i><spring:message code="message.macroscopicModel" /></a>
        </h3>
        <ul class="l_n_list" id="model_ul_1" style="display: none;"></ul>

        <h3 class="ti_b" onclick="toggleModel('2');">
          <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
          <a href="javascript:void(0);"><i id="model_2" class="fa fa-angle-up"></i><spring:message code="message.mediumModel" /></a>
        </h3>
        <ul class="l_n_list" id="model_ul_2" style="display: none;"></ul>

        <h3 class="ti_b" onclick="toggleModel('3');">
          <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
          <a href="javascript:void(0);"><i id="model_3" class="fa fa-angle-up"></i><spring:message code="message.microscopicModel" /></a>
        </h3>
        <ul class="l_n_list" id="model_ul_3" style="display: none;"></ul>

        <h3 class="ti_b" onclick="toggleModel('4');">
          <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
          <a href="javascript:void(0);"><i id="model_4" class="fa fa-angle-up"></i><spring:message code="message.intersectionModel" /></a>
        </h3>
        <ul class="l_n_list" id="model_ul_4" style="display: none;"></ul>

      </div>

    </div>

  </div>
  <!--左侧 结束-->
  <!--展开关闭-->
  <div class="zkgb_b" style="height:500px;">
    <a href="#" class="on"></a>
    <a href="#" class="off hide"></a>
  </div>
  <!--展开关闭 结束-->
  <div class="right">

    <!--"height:400px;"临时高度-->
    <div class="ri_cn">
      <div class=" mks_box">
      
      	
        
        <!--  
        <div class="msk_b_1" id="model" modelid="${TrafficModel.id}">
          <div class="la_ti p_b20 "><spring:message code="message.projectIntroduction" /></div>

          <div class="inp_box clearfix"> <h4 class="h4"><spring:message code="message.researchSite" />：</h4><input type="text" class="input_t main_left" name="area" value="${TrafficModel.area}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a></div>

          <div class="inp_box clearfix"><h4 class="h4"><spring:message code="message.researchObjectives" />：</h4><input type="text" class="input_t main_left" name="target" value="${TrafficModel.target}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a></div>
          <div class="la_ti p_b20 p_t20 "><spring:message code="message.observedData" /></div>

          <div class="inp_box clearfix"> <h4 class="h4"><spring:message code="message.velocityFlow" />：</h4><input type="text" class="input_t main_left" name="speedData" value="${TrafficModel.speedData}"><span class="u-upload"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a><input type="file" onchange="updatePath1(this)"></span><!--<span class="u-upload main_left m_l10 "><button type="button" class="button_1">上传</button>    <input type="file" ></span></div>

          <div class="inp_box clearfix"><h4 class="h4"><spring:message code="message.flowData" />：</h4><input type="text" class="input_t main_left" name="flowData" value="${TrafficModel.flowData}"><span class="u-upload"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a><input type="file" onchange="updatePath2(this)"></span><!--<span class="u-upload main_left m_l10 "><button type="button" class="button_1">上传</button>    <input type="file" ></span></div>
        </div>
         -->
        <div  class="msk_b_1" id="project" style=display:none;>
        <div > <input id="proId" type="hidden" ></input><input id="proModelId" type="hidden" ></input></div><br>
        
          <div > <h2 style=display:inline-block;background-color:#5C4874;width:53px;height:20px><p>项目名称</p></h2>&nbsp&nbsp&nbsp<input id="proName" type="text" ></input></div><br>
		  <div > <h4 style=display:inline-block;background-color:#5C4874;width:53px;height:20px><p>项目介绍</p></h4>&nbsp&nbsp&nbsp
		  <h2 style=display:inline-block;background-color:#85A7D1;width:50px;height:18px>项目背景</h2>&nbsp&nbsp&nbsp<input id="proBackground" type="text"  ></input>
		  <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		  <h2 style=display:inline-block;background-color:#85A7D1;width:90px;height:18px>项目需求与目标</h2>&nbsp&nbsp&nbsp<input id="proNeed_goal" type="text"  ></input>&nbsp&nbsp&nbsp<button onclick="addTrafficProject();">保存</button>
		  </div>
		   <div > <h4 style=display:inline-block;background-color:#5C4874;width:53px;height:20px><p>模型种类</p></h4>&nbsp&nbsp&nbsp
		  		
		  		<label><input onclick="getModelType()" name="model" type="radio" value="1" />宏观模型 </label> 
				<label><input onclick="getModelType()" name="model" type="radio" value="2" />中观模型 </label> 
				<label><input onclick="getModelType()" name="model" type="radio" value="3" />微观模型 </label> 
				<label><input onclick="getModelType()" name="model" type="radio" value="4" />路口模型 </label> 
		  		
		  </div><br>
		  <div id="chose"> <h4 style=display:inline-block;background-color:#5C4874;width:53px;height:20px><p>观测流量</p></h4>&nbsp&nbsp&nbsp
		  
		  
		  <h2 style=display:inline-block;background-color:#85A7D1;width:50px;height:18px>路段流量</h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<div style=display:inline-block><input id="road_flow" name="shpfile" type="file"/><input id="flow" name=""></input></div><button onclick="ajaxFileUpload('road_flow','flow');">上传</button>
		  <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		  <h2 style=display:inline-block;background-color:#85A7D1;width:85px;height:18px>速度数据(可选)</h2>&nbsp&nbsp<div style=display:inline-block><input id="speed_data" name="shpfile" type="file"/><input id="speed" name=""></input></div><button onclick="ajaxFileUpload('speed_data','speed');">上传</button>
		  <button onclick="addTrafficProjectModel();">保存</button>
		  <!-- onclick="document.getElementById('detail').style.display=='none'?document.getElementById('detail').style.display='block':document.getElementById('detail').style.display='none'" -->
		  </div><br>
		  
		  
		  
		  <div id="detail" style=background-color:#FAC090;height:470px class="msk_b_1">
		  
		   <div > <h4 style=display:inline-block;background-color:#5C4874;width:65px;height:20px><p>模型种类</p></h4>&nbsp&nbsp&nbsp
		  		
		  		<label><input onclick="" name="yearModel" type="radio" value="1" />基础年模型 </label> 
				<label><input onclick="" name="yearModel" type="radio" value="2" />未来年模型 </label> 
		  		
		  </div><br>
		  <!-- 
		   <div style=center;display:inline-block;background-color:#5C4874;width:128px;height:22px>
		  		<button onclick="uploadModel()" style=background-color:#85A7D1;width:40px;height:18px>新增</button>
		  			<button onclick="editModel()" style=background-color:#85A7D1;width:40px;height:18px>编辑</button>
		  				<button onclick="" style=background-color:#85A7D1;width:40px;height:18px>删除</button>
		  </div>
		   -->
		 
		  
		  
		  
		  <div>
			  <ul class="animenu__nav">
				<li style="width:auto">
				  <a href="#">选择模型</a>
				  <ul id="chooseModel" class="animenu__nav__child">
				  </ul>
				</li>     
			  </ul>
		</div>
		  <br>
		  <br>
		  <br>
		  
		  			 <br>
		  <br>
		  
			  <div id="add" class="msk_b_1" style=background-color:#4F81BD;height:350px>
			  <div> <h2 style=background-color:#85A7D1;width:217px;height:20px>模型名称&nbsp&nbsp&nbsp<input id="modelName" type="text"  value=""></input></h2></div>
			  <br>
			  <div> <h2 style=background-color:#85A7D1;width:217px;height:20px>模型描述&nbsp&nbsp&nbsp<input id="modelDesc" type="text"  value=""></input></h2></div>
			  <br>
			  <div> <h2 style=display:inline-block;background-color:#85A7D1;width:55px;height:20px>路网文件</h2></div><div style=display:inline-block><input id="gis" name="shpfile" type="file"/><input id="Gis" name=""></input></div><button onclick="ajaxFileUpload('gis','Gis');">上传</button>
			  <br>
			  <div> <h2 style=display:inline-block;background-color:#85A7D1;width:75px;height:20px>交通需求矩阵</h2></div><div style=display:inline-block><input id="traffic_demand" name="matrix" type="file"/><input id="trafficMatrix" name=""></input></div><button onclick="ajaxFileUpload('traffic_demand','trafficMatrix');">上传</button>
			  <br>
			  <div> <h2 style=display:inline-block;background-color:#85A7D1;width:55px;height:20px>仿真结果</h2></div>
			  	<div style=display:inline-block><input id="traffic_flows" name="shpfile" type="file"/><input id="roadFlow" name=""></input></div><button onclick="ajaxFileUpload('traffic_flows','roadFlow');">上传路段流量文件</button>
			 	<div style=display:inline-block><input id="road_speed" name="shpfile" type="file"/><input id="roadSpeed" name=""></input></div><button onclick="ajaxFileUpload('road_speed','roadSpeed');">上传路段速度文件</button>
			  	<div style=display:inline-block><input id="road_result" name="shpfile" type="file"/><input id="results" name=""></input></div><button onclick="ajaxFileUpload('road_result','results');">上传路段结果文件</button>
			  	<div style=display:inline-block><input id="road_lookup" name="shpfile" type="file"/><input id="lookup" name=""></input></div><button onclick="ajaxFileUpload('road_lookup','lookup');">上传路段对照文件</button>
			  	<button onclick="addYearsModel();">保存</button>
			  </div>	
		  				
		  				
		  </div>
		 
			  
			  
		</div> 
		
		
        </div>
        <!-- 
        <div class="msk_b_1 m_t20">
          <div class="la_ti p_b20 "><a href="#" title="<spring:message code='message.newlyBuild' />" class="button_1 button-success_1  main_right m_l10"><spring:message code="message.newlyBuild" /></a><spring:message code="message.developmentModel" /><select class="form-control m_l10" name="modelname" onchange="select_Byname()">
            <c:if test="${modelList!=null}">
              <c:forEach items="${modelList}" var="list">
                <option value="${list.name}">${list.name}</option>
              </c:forEach>
            </c:if>
          </select></div>

          <div class="inp_box clearfix"> <h4 class="h4"><spring:message code="message.modelDescription" />：</h4><input type="text" class="input_t main_left" name="content" value="${TrafficModel.content}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a></div>

          <div class="inp_box clearfix"> <h4 class="h4"><spring:message code="message.modelFolder" />：</h4><input type="text" class="input_t main_left" name="modelPath" value="${TrafficModel.modelPath}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a></div>

          <div class="inp_box clearfix"><h4 class="h4"><spring:message code="message.resultFolder" />：</h4><input type="text" class="input_t main_left" name="resultPath" value="${TrafficModel.resultPath}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a><!--<span class="u-upload main_left m_l10 ">    <button type="button" class="button_1">上传</button>    <input type="file" ></span></div>

          <div class="inp_box clearfix"> <h4 class="h4"><spring:message code="message.comparisonTable" />：</h4><input type="text" class="input_t main_left" name="chart" value="${TrafficModel.chart}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a><!--<span class="u-upload main_left m_l10 ">    <button type="button" class="button_1">上传</button>    <input type="file" ></span></div>
          <div class="la_ti p_b20 "><spring:message code="message.performanceModel" />：</div>

          <div class="inp_box clearfix"><h4 class="h4"><spring:message code="message.roadPerformance" />：</h4><input type="text" class="input_t main_left" name="linklevel" value="${TrafficModel.linklevel}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a><!--<span class="u-upload main_left m_l10 ">    <button type="button" class="button_1">上传</button>    <input type="file" ></span></div>

          <div class="inp_box clearfix"> <h4 class="h4"><spring:message code="message.intersectionPerformance" />：</h4><input type="text" class="input_t main_left" name="crosslevel" value="${TrafficModel.crosslevel}"><a href="#" title="<spring:message code='message.update' />" class="button_1 main_left m_l10"><spring:message code="message.update" /></a><!--<span class="u-upload main_left m_l10 ">    <button type="button" class="button_1">上传</button>    <input type="file" ></span></div>
        </div>
         -->
      </div>
    </div>
  </div>
</div>
<!--主体 结束-->
<div class="mbox_win" style="width: 880px; top: 50px; left: 272px; position: fixed; z-index: 1001; visibility: visible; height: 600px; display:none;">
  <div class="mbox_bar" style="display: block;"><spring:message code="message.heading" /></div>
  <a href="javascript:;" id="mboxBtnClose" class="mbox_close" title="关闭"    style="visibility: visible;">×</a>
  <div class="mbox_cont" style="position: static;">
    <iframe src="http://www.baidu.com" class="mbox_ajax_iframe" frameborder="0" name="IFrame_igqo26bf" style="width: 880px; height: 600px; background-image: url('static/images/o_loading.gif'); border: 0px; display: block; background-position: 50% 50%; background-repeat: no-repeat ;">
    </iframe>
  </div>
  <div id="mboxOperate" class="mbox_operate" style="display: none;">
  </div>
</div><div class="box_bg" style="position: absolute; left: 0px; top: 0px; width: 100%; height: 1244px;  z-index: 99; visibility: visible; zoom: 1; display:none;"></div>
</body>
</html>
<script type="text/javascript">
	(function(){
	  var animenuToggle = document.querySelector('.animenu__toggle'),
		  animenuNav    = document.querySelector('.animenu__nav'),
		  hasClass = function( elem, className ) {
			return new RegExp( ' ' + className + ' ' ).test( ' ' + elem.className + ' ' );
		  },
		  toggleClass = function( elem, className ) {
			var newClass = ' ' + elem.className.replace( /[\t\r\n]/g, ' ' ) + ' ';
			if( hasClass(elem, className ) ) {
			  while( newClass.indexOf( ' ' + className + ' ' ) >= 0 ) {
				newClass = newClass.replace( ' ' + className + ' ' , ' ' );
			  }
			  elem.className = newClass.replace( /^\s+|\s+$/g, '' );
			} else {
			  elem.className += ' ' + className;
			}
		  },           
		  animenuToggleNav =  function (){        
			toggleClass(animenuToggle, "animenu__toggle--active");
			toggleClass(animenuNav, "animenu__nav--open");        
		  }

	  if (!animenuToggle.addEventListener) {
		  animenuToggle.attachEvent("onclick", animenuToggleNav);
	  }
	  else {
		  animenuToggle.addEventListener('click', animenuToggleNav);
	  }
	})()
</script>
