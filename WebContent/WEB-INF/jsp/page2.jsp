<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="message.title" /></title>
    <link href="static/style/base.css" rel="stylesheet" type="text/css"/>
    <link href="static/style/layout.css" rel="stylesheet" type="text/css"/>
    <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">

	<!--add by flgang-->
	<LINK rel=stylesheet type=text/css href="static/style/project_menu/project_menu.css">
	<LINK href="static/style/project_menu/font-awesome.css" rel="stylesheet">
	<script type="text/javascript" src="static/javascript/project_menu/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="static/javascript/project_menu/google-maps.js"></script>
	<LINK  rel="stylesheet"  type="text/css" href="static/javascript/project_menu/jquery-ui-1.8.6.custom/css/smoothness/jquery-ui.css"/>
	<script type="text/javascript" src="static/javascript/project_menu/jquery-ui-1.8.6.custom/js/jquery-ui-1.8.6.custom.min.js"></script>
	<script type="text/javascript" src="static/javascript/project_menu/jquery-ui-1.8.6.custom/js/jquery-ui-i18n.min.js"></script>
	<script>$(document).ready(function(){$(".vertical-nav").verticalnav({speed: 400,align: "left"});});</script>


	<link rel="stylesheet" href="static/style/gs_menu/subMenuGS.css" type="text/css" />

	<script type="text/javascript">
		$(document).ready(function() {

			/* 滑动/展开 */
			$("ul.expmenu li > div.header").click(function() {

				var arrow = $(this).find("span.arrow");

				if (arrow.hasClass("up")) {
					arrow.removeClass("up");
					arrow.addClass("down");
				} else if (arrow.hasClass("down")) {
					arrow.removeClass("down");
					arrow.addClass("up");
				}

				$(this).parent().find("ul.menu").slideToggle();

			});
			$("ul.expmenu2 li > div.header").click(function() {

				var arrow = $(this).find("span.arrow");

				if (arrow.hasClass("up")) {
					arrow.removeClass("up");
					arrow.addClass("down");
				} else if (arrow.hasClass("down")) {
					arrow.removeClass("down");
					arrow.addClass("up");
				}

				$(this).parent().find("ul.menu").slideToggle();

			});

		});
	</script>
	<script language="JavaScript">
		<!--//
		function ShowMenu(obj, n) {
			var Nav = obj.parentNode;
			if (!Nav.id) {
				var BName = Nav.getElementsByTagName("ol");
				var HName = Nav.getElementsByTagName("h2");
				var t = 2;
			} else {
				var BName = document.getElementById(Nav.id).getElementsByTagName("span");
				var HName = document.getElementById(Nav.id).getElementsByTagName(".header");
				var t = 1;
			}
			for (var i = 0; i < HName.length; i++) {
				HName[i].innerHTML = HName[i].innerHTML.replace("-", "+");
				HName[i].className = "";
			}
			obj.className = "h" + t;
			for (var i = 0; i < BName.length; i++) {
				if (i != n) {
					BName[i].className = "no";
				}
			}
			if (BName[n].className == "no") {
				BName[n].className = "";
				obj.innerHTML = obj.innerHTML.replace("+", "-");
			} else {
				BName[n].className = "no";
				obj.className = "";
				obj.innerHTML = obj.innerHTML.replace("-", "+");
			}
		}
		//-->
	</script>
	<style>
		.menu ol { padding-left:15px; }
		/*.menu ol { padding-left:15px; border:#E7E7E7 1px solid; border-top:none;background: #f7f2e5;}*/
		.menu li i{background-color: #ae9c7e;padding: 1px 4px;color: #fff;text-shadow: 0px 0px 0px rgba(255, 255, 255, 0.8);font-family: 宋体;font-style:normal;}
		.menu a{color: #3f3f3f;text-decoration: none;}
		.menu .no {display:none;}
		.menu ol a{width: 228px;display: block;line-height: 2em;margin-left: 20px;}
	</style>
</head>
<body  class="body">
<!--头部-->
	<div class="header">
    	<div class="logo main_left">
        	<div id="now_time"></div>
        </div>
        <div class="h_r">
        	<div class="h_r_t">
				<a href="/page2?locale=zh_CN" class="main_right">中文</a>/<a href="/page2?locale=en_US" class="main_right">English</a>
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
		<div class="left" style="height:1000px">
			<div class="l_n" id="model_list" style="height:300px">
				<div>
					<h2>
						<div class="dh_cn_llist">
							<%--<div class="cn_div"><b></b><a href="#" class="on">按类型</a><a href="#">按顺序</a></div>--%>
						</div>
						<a href="#"><i class="fa fa-plus"></i><spring:message code="message.simulationResults" /></a>
					</h2>
					<div class="kePublic" style="z-index: 9900;">
						<!--效果html开始-->
						<div class="content">
							<ul class="vertical-nav dark red">
								<li><a href="#" style="color:#000000;line-height:0px;height:32px;width:200px;"><spring:message code="message.chooseProject" /></a>
									<ul id="projectListUl" >
										<li><a href="#" style="line-height:0px;" id="wj">望京</a></li>
										<li><a href="#" style="line-height:0px;" id="gs">91号洲际高速</a></li>
									</ul>
								</li>
							</ul>
						</div>
						<!--效果html结束-->
						<div class="clear"></div>
					</div>

					<div id="wjDiv">
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

					<div id="gsDiv" style="display:none;">
						<div id="gsMacroDiv">
							<h3 class="ti_b" onclick="toggleModel('hg1');">
								<!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
								<a href="javascript:void(0);"><i id="model_hg1" class="fa fa-angle-up"></i><spring:message code="message.model" /></a>
							</h3>
							<ul class="expmenu" id="model_ul_hg1">
								<li>
									<div class="header" style="height:30px;font-size: 16px;line-height: 32px;">
										<span class="label" ><spring:message code="message.macroscopicModel" /></span>
									</div>
	                            <span class="no">
	                                <ul class="menu" style="display:block;"  id="macroUl">
										<li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0)" id="macro_gs_a" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> 91号洲际高速</a></li>
									</ul>
	                            </span>
								</li>
							</ul>
						</div>

						<div id="gsMicroDiv">
							<h3 class="ti_b" onclick="toggleModel('hg3');">
								<!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
								<a href="javascript:void(0);"><i id="model_hg3" class="fa fa-angle-up"></i><spring:message code="message.model" /></a>
							</h3>
							<ul class="expmenu2" id="model_ul_hg3">
								<li>
									<div class="header" style="height:30px;font-size: 16px;line-height: 32px;">
										<span class="label" ><spring:message code="message.microscopicModel" /></span>
									</div>
	                            <span class="no">
	                                <ul class="menu" style="display:block;"  id="microUl">
										<li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0)" id="micro_gs_a" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> 91号洲际高速</a></li>
									</ul>
	                            </span>
								</li>
							</ul>
						</div>
						<div id="gsComparaDiv">
							<h3 class="ti_b" >
								<!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
								<a href="javascript:void(0);"><i id="model_result_hg3" class="fa fa-angle-up"></i><spring:message code="message.resultComparison" /></a>
							</h3>
						</div>
					</div>
				</div>

			</div>
			<div class="ms_st" id="simulateBaseInfoDiv" style="height: 220px;"><spring:message code="message.noRelevantData" /></div>
		</div>
    	<!--左侧 结束-->
        <!--展开关闭-->
        <div class="zkgb_b" style="height:500px;">
            <a href="#" class="on"></a>
            <a href="#" class="off hide"></a>
        </div>
        <!--展开关闭 结束-->

		<!--add by flgang beginning-->
		<div id="subMenu_gs_macro_div" class="subMenu_gs_macro">
			<div><button class="gsButton1">模型</button><button class="gsButton1">仿真结果</button><button class="gsButton1">时间段</button><button class="gsButton1">下载</button></div>
			<div><button id="macroModelYearBaseId" name="macroModelYearType" class="gsButton2" value="1" >基础年模型</button><button id="macroResultSpeedId" name="macroResultType" class="gsButton2" value="speed">平均行驶速度</button><button id="macroTimeAmId" name="macroResultTime" class="gsButton2" value="AM">上午</button></div>
			<div><button id="macroModelYearNetNoId" name="macroModelYearType" class="gsButton2" value="3">未来年模型_无路网改善</button><button id="macroResultVolumneId" name="macroResultType" class="gsButton2" value="volume">流量</button><button id="macroTimePmId" name="macroResultTime" class="gsButton2" value="PM">下午</button></div>
			<div><button id="macroModelYearNetId" name="macroModelYearType" class="gsButton2" value="2">未来年模型_有路网改善</button><button id="macroResultVcId" name="macroResultType" class="gsButton2" value="v/c">V/C比</button></div>
			<div><span style="padding-left:500px;"></span><button id="queryMacroResultId" class="gsButtonResult">查询显示结果</button></div>
		</div>
		<div id="subMenu_gs_micro_div" class="subMenu_gs_micro">
			<div><button class="gsButton1">模型</button><button class="gsButton1">仿真结果</button><button class="gsButton1">时间段</button><button class="gsButton1">下载</button></div>
			<div><button id="microModelYearBaseId" name="microModelYearType" class="gsButton2" value="1" >基础年模型</button><button id="microResultSpeedId" name="microResultType"  class="gsButton2" value="speed">平均行驶速度</button><button id="microTimeAmId" name="microResultTime" class="gsButton2" value="AM">上午</button></div>
			<div><button id="microModelYearNetNoId" name="microModelYearType" class="gsButton2" value="3" >未来年模型_无路网改善</button><button id="microResultVolumneId" name="microResultType" class="gsButton2" value="volume">流量</button><button id="microTimePmId" name="microResultTime" class="gsButton2" value="PM">下午</button></div>
			<div><button id="microModelYearNetId" name="microModelYearType"  class="gsButton2" value="2" >未来年模型_有路网改善</button><button id="microResultDelayHourId" name="microResultType" class="gsButton2" value="delayHour">延时</button></div>
			<div><button id="microModelYearAlternative1Id" name="microModelYearType"  class="gsButton2" value="4" >备选方案一</button><button id="microResultSpeedPicId" name="microResultType" class="gsButton2" value="speedPic">速度图</button></div>
			<div><button id="microModelYearAlternative2Id" name="microModelYearType"  class="gsButton2" value="5" >备选方案二</button></div>
			<div><span style="padding-left:500px;"></span><button id="queryMicroResultId" class="gsButtonResult">查询显示结果</button></div>
		</div>
		<div id="subMenu_gs_macro_result_div" class="subMenu_gs_macro_result">
			<div><button class="gsButton1">模型</button><button class="gsButton1">道路类型</button><button class="gsButton1">道路名称</button><button class="gsButton1">方向</button><button class="gsButton1">比较参数</button><button class="gsButton1">时间段</button></div>
			<div><button class="gsButton2">基础年模型</button><button class="gsButton2">城市道路</button><button class="gsButton2">SR-91</button><button class="gsButton2">东向</button><button class="gsButton2">流量</button><button class="gsButton2">上午</button></div>
			<div><button class="gsButton2">未来年模型_无路网改善</button><button class="gsButton2">高速公路</button><button class="gsButton2">SR-55</button><button class="gsButton2">西向</button><button class="gsButton2">速度</button><button class="gsButton2">下午</button></div>
			<div><button class="gsButton2">未来年模型_有路网改善</button><span style="padding-left:30px;"></span><button class="gsButton3">通用车道</button><button class="gsButton2">SR-57</button><button class="gsButton2">南向</button><button class="gsButton2">V/C比</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">HOV车道</button><button class="gsButton2">其他</button><button class="gsButton2">北向</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">上匝道</button><button class="gsButton2">全选</button><button class="gsButton2">全选</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">下匝道</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">高速连接道</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">全选</button></div>
			<div><span style="padding-left:150px;"></span><button class="gsButton2">全选</button><span style="padding-left:500px;"></span><button class="gsButtonResult">显示比较结果</button></div>
		</div>
		<div id="subMenu_gs_micro_result_div" class="subMenu_gs_macro_result">
			<div><button class="gsButton1">模型</button><button class="gsButton1">道路类型</button><button class="gsButton1">道路名称</button><button class="gsButton1">方向</button><button class="gsButton1">比较参数</button><button class="gsButton1">时间</button><button class="gsButton1">车辆类型</button></div>
			<div><button class="gsButton2">基础年模型</button><button class="gsButton2">城市道路</button><button class="gsButton2">SR-91</button><button class="gsButton2">东向</button><button class="gsButton2">系统评价参数</button><button class="gsButton4">From</button><input class="gsInput1" ></input><button class="gsButton2">SOV</button></div>
			<div><button class="gsButton2">未来年模型_无路网改善</button><button class="gsButton2">高速公路</button><button class="gsButton2">SR-55</button><button class="gsButton2">西向</button><span style="padding-left:30px;"></span><button class="gsButton3">延时</button><button class="gsButton4">To</button><input class="gsInput1" ></input><button class="gsButton2">HOV</button></div>
			<div><button class="gsButton2">未来年模型_有路网改善</button><span style="padding-left:30px;"></span><button class="gsButton3">通用车道</button><button class="gsButton2">SR-57</button><button class="gsButton2">南向</button><span style="padding-left:30px;"></span><button class="gsButton3">VHT</button><span style="padding-left:150px;"></span><button class="gsButton2">Truck</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">HOV车道</button><button class="gsButton2">其他</button><button class="gsButton2">北向</button><span style="padding-left:30px;"></span><button class="gsButton3">VMT</button><span style="padding-left:150px;"></span><button class="gsButton2">全选</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">上匝道</button><button class="gsButton2">全选</button><button class="gsButton2">全选</button><button class="gsButton2">监测点评价参数</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">下匝道</button><span style="padding-left:330px;"></span><button class="gsButton3">流量</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">高速连接道</button><span style="padding-left:330px;"></span><button class="gsButton3">速度</button></div>
			<div><span style="padding-left:180px;"></span><button class="gsButton3">全选</button><span style="padding-left:330px;"></span><button class="gsButton3">速度图</button></div>
			<div><span style="padding-left:150px;"></span><button class="gsButton2">全选</button><span style="padding-left:500px;"></span><button class="gsButtonResult">显示比较结果</button></div>
		</div>
		<!--add by flgang ending-->

    	<div class="right">
        	<div id="roadTypeDiv" class="tool_top">
            <ul>
               	<li><strong><spring:message code="message.roadType" />：</strong></li>
               	<li class="main_right"><label><input id="checkAll" name="checkAll" checked type="checkbox" onclick="checkAllRoadType(1)" value="" /><spring:message code="message.select" /></label></li>
                <li class="main_right"><label><input id="unCheckAll" name="unCheckAll" type="checkbox" onclick="checkAllRoadType(0)" value="" /><spring:message code="message.reverseSelection" /></label></li>
               	<li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Primary Arterial" />Primary Arterial</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Minor Arterial" />Minor Arterial</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Ramp" />Ramp</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Local Street" />Local Street</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Centroid Connector" />Centroid Connector</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Major Arterial" />Major Arterial</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="External Connection" />External Connection</label></li>
                <li><label><input name="roadtype" type="checkbox" onclick="roadTypeChange()" checked value="Highway" />Highway</label></li>
            </ul>
            </div>
           <div class="fl_list">
           	<a href="#" title="地图" id="mapButton0" onclick="showMapResultTableDiv()" class="button_1 button-danger_1"><spring:message code="message.map" /></a>
           	<a href="#" title="表格" id="bigResultButton1" onclick="showBigResultTableDiv1()" class="button_1"><spring:message code="message.table" /></a><a href="#" id="bigResultButton2" onclick="showBigResultTableDiv2()" title="通行指数（路段）" class="button_1"><spring:message code="message.trafficIndex" />（<spring:message code="message.section" />）</a><a href="#" id="bigResultButton3" onclick="showBigResultTableDiv3()"title="通行指数（里程）" class="button_1"><spring:message code="message.trafficIndex" />（<spring:message code="message.mileage" />）</a>
           	<a href="#" id="midButton1" onclick="showMidResultTableDiv1()"title="表格" class="button_1"><spring:message code="message.table" /></a><a href="#" id="midButton2" onclick="showMidResultTableDiv2()"title="密度" class="button_1"><spring:message code="message.density" /></a><a href="#" id="midButton3" onclick="showMidResultTableDiv3()"title="流量" class="button_1"><spring:message code="message.flow" /></a><a href="#" id="midButton4" onclick="showMidResultTableDiv4()"title="速度" class="button_1"><spring:message code="message.speed" /></a>
           	<a href="#" id="micButton1" onclick="showMicResultTableDiv1()"title="表格" class="button_1"><spring:message code="message.table" /></a><a href="#" id="micButton2" onclick="showMicResultTableDiv2()"title="密度" class="button_1"><spring:message code="message.density" /></a><a href="#" id="micButton3" onclick="showMicResultTableDiv3()"title="流量" class="button_1"><spring:message code="message.flow" /></a><a href="#" id="micButton4" onclick="showMicResultTableDiv4()"title="速度" class="button_1"><spring:message code="message.speed" /></a>
           	<a href="#" id="interButton1" onclick="showInterResultTableDiv1()"title="路口表格" class="button_1"><spring:message code="message.intersectionTable" /></a><a href="#" id="interButton2" onclick="showInterResultTableDiv2()"title="转向表格" class="button_1"><spring:message code="message.steeringTable" /></a>
           </div>
       	  <!--"height:400px;"临时高度-->
       	 <div id="mapResultDiv0" style="display:block">
         <div class="ri_cn" style="height:510px;">
         		<div class="m_l15  m_b15 m_r15" style="background:#FFF;">
                	<iframe id="mapContainer" style="width:100%;height:490px" scrolling="no"></iframe>
                </div>
          </div>
        </div>
         <!-- 宏观模型开始 -->
       	  <div id="macResultDiv1" style="display:none">
       	  <div class="ri_cn" style="height:400px;">
          	<div class="m_l15 m_r15">
    	<table id="macResultTable" width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="5%"><spring:message code="message.serialNumber" /></th>
            <th width="20%"><spring:message code="message.roadName" /></th>
            <th width="20%"><spring:message code="message.roadType" /></th>
            <th width="15%"><spring:message code="message.roadLength" /></th>
            <th width="10%">AB_Flow</th>
            <th width="10%">BA_Flow</th>
            <th width="10%">AB_VOC</th>
            <th width="10%">BA_VOC</th>
            </tr>
         </thead>
          <tr>
            <td align="center" valign="middle" colspan="8"><spring:message code="message.noRelevantData" /></td>
          </tr>
        </table>
		
    </div>

          </div>
        <div id="macPageDiv" class="page m_l15 m_r15 du">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
        </div>
        <div id="macResultDiv2" style="display:none">
         <div class="ri_cn" style="height:400px; ">
          		<div class="m_l15  m_b15 m_r15" style="background:#FFF;"> 
                	<div id="macContainer" style="min-width:700px;height:400px"></div>
                </div>
          </div>
        </div>
         <!-- 宏观模型结束 -->
        
        <!-- 中观模型开始 -->
        <div id="midResultDiv1" style="display:none">
       	  <div class="ri_cn" style="height:400px;">
          	<div class="m_l15 m_r15">
    	<table id="midResultTable" width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="5%"><spring:message code="message.serialNumber" /></th>
            <th width="20%"><spring:message code="message.road" /></th>
            <th width="15%">aorb</th>
            <th width="15%"><spring:message code="message.timeSlot" /></th>
            <th width="15%"><spring:message code="message.density" /></th>
            <th width="15%"><spring:message code="message.flow" /></th>
            <th width="15%"><spring:message code="message.speed" /></th>
            </tr>
         </thead>
          <tr>
            <td align="center" valign="middle" colspan="7"><spring:message code="message.noRelevantData" /></td>
          </tr>
        </table>
		
    </div>

          </div>
        <div id="midPageDiv" class="page m_l15 m_r15 du">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
        </div>
        <div id="midResultDiv2" style="display:none">
         <div class="ri_cn" style="height:400px; ">
          		<div class="m_l15  m_b15 m_r15" style="background:#FFF;"> 
                	<div id="midContainer" style="min-width:700px;height:400px"></div>
                </div>
          </div>
        </div>
        <!-- 中观模型结束 -->
        
        <!-- 微观模型开始 -->
        <div id="micResultDiv1" style="display:none">
       	  <div class="ri_cn" style="height:400px;">
          	<div class="m_l15 m_r15">
    	<table id="micResultTable" width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
          	<th width="5%"><spring:message code="message.serialNumber" /></th>
            <th width="10%"><spring:message code="message.startTime" /></th>
            <th width="10%"><spring:message code="message.endTime" /></th>
            <th width="5%">group</th>
            <th width="5%">type</th>
            <th width="5%">count</th>
            <th width="5%">delay</th>
            <th width="5%">density</th>
            <th width="5%">flow</th>
            <th width="5%">speed</th>
            <th width="5%">level</th>
            <th width="5%">service measure</th>
            <th width="5%">los value</th>
            <th width="5%">travel time</th>
            <th width="5%">vdt</th>
            <th width="5%">vht</th>
            <th width="5%">ptd</th>
            <th width="5%">stop time</th>
            </tr>
         </thead>
          <tr>
            <td align="center" valign="middle" colspan="18"><spring:message code="message.noRelevantData" /></td>
          </tr>
        </table>
		
    </div>

          </div>
        <div id="micPageDiv" class="page m_l15 m_r15 du">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
        </div>
        <div id="micResultDiv2" style="display:none">
         <div class="ri_cn" style="height:400px; ">
          		<div class="m_l15  m_b15 m_r15" style="background:#FFF;"> 
                	<div id="micContainer" style="min-width:700px;height:400px"></div>
                </div>
          </div>
        </div>
        <!-- 微观模型结束 -->
        
        <!-- 路口模型开始 -->
        <div id="interResultDiv1" style="display:none">
       	  <div class="ri_cn" style="height:400px;">
          	<div class="m_l15 m_r15">
    	<table id="interResultTable1" width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="30%">Direction</th>
            <th width="30%">Approach Delay</th>
            <th width="40%">Approach LOS</th>
            </tr>
         </thead>
          <tr>
            <td align="center" valign="middle" colspan="3"><spring:message code="message.noRelevantData" /></td>
          </tr>
        </table>
    </div>
          </div>
        <div id="interPageDiv1" class="page m_l15 m_r15 du">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
        </div>
        <div id="interResultDiv2" style="display:none">
       	  <div class="ri_cn" style="height:400px;">
          	<div class="m_l15 m_r15">
    	<table id="interResultTable2" width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="11%">turn</th>
            <th width="11%">Volume</th>
            <th width="11%">Flow</th>
            <th width="14%">vc Ratio</th>
            <th width="14%">Control Delay</th>
            <th width="14%">Queue Delay</th>
            <th width="14%">Total Delay</th>
            <th width="11%">LOS</th>
            </tr>
         </thead>
          <tr>
            <td align="center" valign="middle" colspan="8"><spring:message code="message.noRelevantData" /></td>
          </tr>
        </table>
		
    </div>

          </div>
        <div id="interPageDiv2" class="page m_l15 m_r15 du">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
        </div>
        <!-- 路口模型结束 -->
      </div>
    </div>
<!--主体 结束-->
<script type="text/javascript" src="static/javascript/api/LAB.min.js"></script>
<script type="text/javascript">
$LAB
        .script("static/javascript/api/jquery/jquery-1.11.3.min.js")
        .script("static/javascript/public/common.js")
        .script("static/javascript/highcharts-4.1.9/js/highcharts.js")
        .script("static/javascript/app/projectIndex.js")
        .wait()
        .wait(function(){
            jQuery("#now_time").html(currentTime());
            PT.ProIndex.loadProjectList();
			loadModelList();
			var height = $(window).height();
			$("#model_list").height(height-350);
        })
        .wait();

var currentSimulateBaseId = "";
var currentSimulateModelId = "";
var currentSimulateModelType = "";

/**
 * 加载模型列表
 */
function loadModelList(){
	
	$.getJSON("simulateList", function(jsonArray){
		for(var i = 0; i < jsonArray.length; i++) {
			var html = "";
			var json = jsonArray[i];
			var name = json.name;
			var id = json.id;
			var simulateModelId = json.simulateModelId;

			var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
			var li = " <li><a href='javascript:void(0);' onclick='changeSimulateInfo("+id+","+json.type+","+simulateModelId+")' title='"+ name +"'><i class='fa fa-random'></i>"+ vname +"</a></li>";
			html += li;
			if(currentSimulateBaseId == ""){
				currentSimulateBaseId = id;
				currentSimulateModelId = simulateModelId;
				currentSimulateModelType = json.type;
			}
			var oldHtml = $("#model_ul_"+json.type).html();
			html = oldHtml + html;
			$("#model_ul_"+json.type).html(html);
			$("#model_ul_"+json.type).show();
		}
		changeSimulateInfo(currentSimulateBaseId,currentSimulateModelType,currentSimulateModelId);
	});
};
/**
 * 根据模型类型隐藏或显示模型列表
 * @param type
 */
function toggleModel(type){
	if($("#model_ul_" + type).css("display") == "none"){
		$("#model_" + type).attr("class", "fa fa-angle-up");
	}else{
		$("#model_" + type).attr("class", "fa fa-angle-down");
	}
	$("#model_ul_" + type).toggle();
};
function checkAllRoadType(check){
	if(check == 1){//check all
		$("#checkAll").prop("checked","true");
		$("#unCheckAll").removeAttr("checked");
		
		$("[name='roadtype']").prop("checked","true");
	}else{//uncheck all
		$("#unCheckAll").prop("checked","true");
		$("#checkAll").removeAttr("checked");
		
		$("[name='roadtype']").removeAttr("checked");
	}
	roadTypeChange();
}

function roadTypeChange(){
	if(currentSimulateBaseId == ""){
		return;
	}
	
	changeSimulateInfo(currentSimulateBaseId,currentSimulateModelType,currentSimulateModelId);
}


function changeSimulateInfo(id,simulateModelType,simulateModelId){
	currentSimulateBaseId = id;
	currentSimulateModelType = simulateModelType;
	currentSimulateModelId = simulateModelId;
	
	if(currentSimulateModelType == "1"){//宏观模型
		$("#roadTypeDiv").show();
		$("#bigResultButton1").show();
		$("#bigResultButton2").show();
		$("#bigResultButton3").show();
		

		$("#midButton1").hide();
		$("#midButton2").hide();
		$("#midButton3").hide();
		$("#midButton4").hide();
		
		$("#micButton1").hide();
		$("#micButton2").hide();
		$("#micButton3").hide();
		$("#micButton4").hide();
		
		$("#interButton1").hide();
		$("#interButton2").hide();
		
		if($("#mapButton0").attr("class") == "button_1" && $("#bigResultButton1").attr("class") == "button_1" && $("#bigResultButton2").attr("class") == "button_1" && $("#bigResultButton3").attr("class") == "button_1"){
			$("#mapButton0").attr("class","button_1 button-danger_1");
		}
		
		if($("#mapButton0").attr("class") == "button_1 button-danger_1"){
			showMapResultTableDiv();
		}
		
		if($("#bigResultButton1").attr("class") == "button_1 button-danger_1"){
			showBigResultTableDiv1();
		}
		if($("#bigResultButton2").attr("class") == "button_1 button-danger_1"){
			showBigResultTableDiv2();
		}
		if($("#bigResultButton3").attr("class") == "button_1 button-danger_1"){
			showBigResultTableDiv3();
		}
	}
	
	if(currentSimulateModelType == "2"){//中观模型
		$("#roadTypeDiv").hide();
		$("#bigResultButton1").hide();
		$("#bigResultButton2").hide();
		$("#bigResultButton3").hide();
		

		$("#midButton1").show();
		$("#midButton2").show();
		$("#midButton3").show();
		$("#midButton4").show();
		

		$("#micButton1").hide();
		$("#micButton2").hide();
		$("#micButton3").hide();
		$("#micButton4").hide();

		$("#interButton1").hide();
		$("#interButton2").hide();
		
		if($("#mapButton0").attr("class") == "button_1" && $("#midButton1").attr("class") == "button_1" && $("#midButton2").attr("class") == "button_1" && $("#midButton3").attr("class") == "button_1" && $("#midButton4").attr("class") == "button_1"){
			$("#mapButton0").attr("class","button_1 button-danger_1");
		}
		
		if($("#mapButton0").attr("class") == "button_1 button-danger_1"){
			showMapResultTableDiv();
		}
		
		if($("#midButton1").attr("class") == "button_1 button-danger_1"){
			showMidResultTableDiv1();
		}
		if($("#midButton2").attr("class") == "button_1 button-danger_1"){
			showMidResultTableDiv2();
		}
		if($("#midButton3").attr("class") == "button_1 button-danger_1"){
			showMidResultTableDiv3();
		}
		if($("#midButton4").attr("class") == "button_1 button-danger_1"){
			showMidResultTableDiv4();
		}
	}
	
	if(currentSimulateModelType == "3"){//微观模型
		$("#roadTypeDiv").hide();
		$("#bigResultButton1").hide();
		$("#bigResultButton2").hide();
		$("#bigResultButton3").hide();

		$("#midButton1").hide();
		$("#midButton2").hide();
		$("#midButton3").hide();
		$("#midButton4").hide();
		

		$("#micButton1").show();
		$("#micButton2").show();
		$("#micButton3").show();
		$("#micButton4").show();

		$("#interButton1").hide();
		$("#interButton2").hide();
		
		if($("#mapButton0").attr("class") == "button_1" && $("#micButton1").attr("class") == "button_1" && $("#micButton2").attr("class") == "button_1" && $("#micButton3").attr("class") == "button_1" && $("#micButton4").attr("class") == "button_1"){
			$("#mapButton0").attr("class","button_1 button-danger_1");
		}
		
		if($("#mapButton0").attr("class") == "button_1 button-danger_1"){
			showMapResultTableDiv();
		}
		
		if($("#micButton1").attr("class") == "button_1 button-danger_1"){
			showMicResultTableDiv1();
		}
		if($("#micButton2").attr("class") == "button_1 button-danger_1"){
			showMicResultTableDiv2();
		}
		if($("#micButton3").attr("class") == "button_1 button-danger_1"){
			showMicResultTableDiv3();
		}
		if($("#micButton4").attr("class") == "button_1 button-danger_1"){
			showMicResultTableDiv4();
		}
	}
	
	if(currentSimulateModelType == "4"){//路口模型
		$("#roadTypeDiv").hide();
		$("#bigResultButton1").hide();
		$("#bigResultButton2").hide();
		$("#bigResultButton3").hide();
		

		$("#midButton1").hide();
		$("#midButton2").hide();
		$("#midButton3").hide();
		$("#midButton4").hide();
		
		$("#micButton1").hide();
		$("#micButton2").hide();
		$("#micButton3").hide();
		$("#micButton4").hide();

		$("#interButton1").show();
		$("#interButton2").show();
		
		if($("#mapButton0").attr("class") == "button_1" && $("#interButton1").attr("class") == "button_1" && $("#interButton2").attr("class") == "button_1"){
			$("#mapButton0").attr("class","button_1 button-danger_1");
		}
		
		if($("#mapButton0").attr("class") == "button_1 button-danger_1"){
			showMapResultTableDiv();
		}
		
		if($("#interButton1").attr("class") == "button_1 button-danger_1"){
			showInterResultTableDiv1();
		}
		if($("#interButton2").attr("class") == "button_1 button-danger_1"){
			showInterResultTableDiv2();
		}
	}
	
	return false;
}

function showMapResultTableDiv(){
	$("#mapButton0").attr("class","button_1 button-danger_1");
	$("#bigResultButton1").attr("class","button_1");
	$("#bigResultButton2").attr("class","button_1");
	$("#bigResultButton3").attr("class","button_1");
	
	$("#midButton1").attr("class","button_1");
	$("#midButton1").attr("class","button_1");
	$("#midButton1").attr("class","button_1");
	$("#midButton1").attr("class","button_1");
	
	$("#micButton1").attr("class","button_1");
	$("#micButton1").attr("class","button_1");
	$("#micButton1").attr("class","button_1");
	$("#micButton1").attr("class","button_1");
	
	$("#interButton1").attr("class","button_1");
	$("#interButton2").attr("class","button_1");
	
	$("#mapResultDiv0").show();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();
	
	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();
	
	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	refreshMapPage();
}

function showBigResultTableDiv1(){
	$("#mapButton0").attr("class","button_1");
	$("#bigResultButton1").attr("class","button_1 button-danger_1");
	$("#bigResultButton2").attr("class","button_1");
	$("#bigResultButton3").attr("class","button_1");
	

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").show();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMacTableInfo("1");
}


function showBigResultTableDiv2(){
	$("#mapButton0").attr("class","button_1");
	$("#bigResultButton1").attr("class","button_1");
	$("#bigResultButton2").attr("class","button_1 button-danger_1");
	$("#bigResultButton3").attr("class","button_1");
	
	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").show();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	refreshMacChartInfo("2");
	
}

function showBigResultTableDiv3(){
	$("#mapButton0").attr("class","button_1");
	$("#bigResultButton1").attr("class","button_1");
	$("#bigResultButton2").attr("class","button_1");
	$("#bigResultButton3").attr("class","button_1 button-danger_1");
	
	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").show();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	refreshMacChartInfo("3");
}

//中观模型开始
function showMidResultTableDiv1(){
	$("#mapButton0").attr("class","button_1");
	$("#midButton1").attr("class","button_1 button-danger_1");
	$("#midButton2").attr("class","button_1");
	$("#midButton3").attr("class","button_1");
	$("#midButton4").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").show();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMidTableInfo("1");
}

function showMidResultTableDiv2(){
	$("#mapButton0").attr("class","button_1");
	$("#midButton1").attr("class","button_1");
	$("#midButton2").attr("class","button_1 button-danger_1");
	$("#midButton3").attr("class","button_1");
	$("#midButton4").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").show();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMidChartInfo("1");
}

function showMidResultTableDiv3(){
	$("#mapButton0").attr("class","button_1");
	$("#midButton1").attr("class","button_1");
	$("#midButton2").attr("class","button_1");
	$("#midButton3").attr("class","button_1 button-danger_1");
	$("#midButton4").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").show();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMidChartInfo("2");
}
function showMidResultTableDiv4(){
	$("#mapButton0").attr("class","button_1");
	$("#midButton1").attr("class","button_1");
	$("#midButton2").attr("class","button_1");
	$("#midButton3").attr("class","button_1");
	$("#midButton4").attr("class","button_1 button-danger_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").show();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMidChartInfo("3");
}
//中观模型结束

//微观模型开始
function showMicResultTableDiv1(){
	$("#mapButton0").attr("class","button_1");
	$("#micButton1").attr("class","button_1 button-danger_1");
	$("#micButton2").attr("class","button_1");
	$("#micButton3").attr("class","button_1");
	$("#micButton4").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").show();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMicTableInfo("1");
}

function showMicResultTableDiv2(){
	$("#mapButton0").attr("class","button_1");
	$("#micButton1").attr("class","button_1");
	$("#micButton2").attr("class","button_1 button-danger_1");
	$("#micButton3").attr("class","button_1");
	$("#micButton4").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").show();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMicChartInfo("1");
}

function showMicResultTableDiv3(){
	$("#mapButton0").attr("class","button_1");
	$("#micButton1").attr("class","button_1");
	$("#micButton2").attr("class","button_1");
	$("#micButton3").attr("class","button_1 button-danger_1");
	$("#micButton4").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").show();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMicChartInfo("2");
}
function showMicResultTableDiv4(){
	$("#mapButton0").attr("class","button_1");
	$("#micButton1").attr("class","button_1");
	$("#micButton2").attr("class","button_1");
	$("#micButton3").attr("class","button_1");
	$("#micButton4").attr("class","button_1 button-danger_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").show();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshMicChartInfo("3");
}
//微观模型结束

//路口模型开始
function showInterResultTableDiv1(){
	$("#mapButton0").attr("class","button_1");
	$("#interButton1").attr("class","button_1 button-danger_1");
	$("#interButton2").attr("class","button_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").show();
	$("#interResultDiv2").hide();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshInterTableInfo1("1");
}

function showInterResultTableDiv2(){
	$("#mapButton0").attr("class","button_1");
	$("#interButton1").attr("class","button_1");
	$("#interButton2").attr("class","button_1 button-danger_1");

	$("#mapResultDiv0").hide();
	
	$("#macResultDiv1").hide();
	$("#macResultDiv2").hide();
	
	$("#midResultDiv1").hide();
	$("#midResultDiv2").hide();

	$("#micResultDiv1").hide();
	$("#micResultDiv2").hide();

	$("#interResultDiv1").hide();
	$("#interResultDiv2").show();
	
	if(currentSimulateBaseId == ""){
		return;
	}
	
	refreshInterTableInfo2("1");
}
//路口模型结束

function refreshMapPage(){
	getAndRefreshBaseInfo();
	var mapUrl = "map?type="+currentSimulateModelType+"&resultId="+currentSimulateBaseId+"&modelId="+currentSimulateModelId;
	alert(mapUrl);
	$("#mapContainer").attr("src", mapUrl);
}

function getAndRefreshBaseInfo(){
	$.ajax({
		url:"chart/base",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId}),
		processData:false,
		success:function(data){
			if(data.base != null){
				refreshBaseInfo(data.base);
			}
		}
	});
}

function refreshBaseInfo(base){
	var baseHtmlStr = "";
	if(base != null){
		baseHtmlStr="<strong>"+base.name+"<spring:message code='message.basicInformation' /></strong><br />"+
		"1、<spring:message code='message.simulationDataName' />:"+base.name+"<br />"+
		"2、<spring:message code='message.simulationSoftware' />:"+base.simulateSoft+"<br />"+
		"3、<spring:message code='message.simulationModel' />:"+base.simulateModel+"<br />"+
		"4、<spring:message code='message.simulationTime' />:"+base.simulateTimes+"<br />"+
		"5、<spring:message code='message.simulationStartTime' />:"+base.simulateStartTimes+"<br />"+
		"6、<spring:message code='message.simulationEndTime' />:"+base.simulateEndTimes+"<br />"+
		"7、<spring:message code='message.uploadPerson' />:"+base.importUserName+"<br />"+
		"8、<spring:message code='message.explain' />:"+base.comment+"";
			
	}
	$("#simulateBaseInfoDiv").html(baseHtmlStr);
}

function gotoPage(pageNo){
	if(currentSimulateModelType == "1"){//宏观模型
		refreshMacTableInfo(pageNo);
	}
	if(currentSimulateModelType == "2"){//中观模型
		refreshMidTableInfo(pageNo);
	}
	if(currentSimulateModelType == "3"){//微观模型
		refreshMicTableInfo(pageNo);
	}
	
	if(currentSimulateModelType == "4"){//路口模型
		if($("#interButton1").attr("class") == "button_1 button-danger_1"){
			refreshInterTableInfo1(pageNo);
		}else{
			refreshInterTableInfo2(pageNo);
		}
	}
}


function refreshMacTableInfo(pageNo){
	var roadType = new Array();
	$("[name='roadtype']").each(function(){ 
         if($(this).prop("checked")){
             roadType.push("'"+$(this).val()+"'");
         }
     });
	
	$.ajax({
		url:"chart/list",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId,"pageNo":pageNo,"roadType":roadType.join(",")}),
		processData:false,
		success:function(data){
			if(data.base != null){
				refreshBaseInfo(data.base);
			}
			
			$("#macResultTable tbody").empty();
			var newTr="";
			if(data.page != null && data.page.pageList!=null && data.page.pageList.length > 0){
				var macResults = data.page.pageList;
				for(var i=0;i<macResults.length;i++){
					newTr+= "<tr>"+
					"<td align='center' valign='middle'>"+macResults[i].id +"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].roadname+"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].link_type+"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].length+"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].ab_flow+"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].ba_flow+"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].ab_voc+"</td>"+
		            "<td align='center' valign='middle'>"+macResults[i].ba_voc+"</td>"+
				"</tr>";
				}
			}else{
				newTr+= "<tr>"+
				"<td align='center' valign='middle' colspan='8'><spring:message code='message.noRelevantData' /></td>"+
				"</tr>";
			}
			$("#macResultTable").append(newTr);
			changeMacPageInfo(data.page);
		}
	});
}

function getPageInfo(page){
	var newHtml = "";
	if(page !=null){
	newHtml = newHtml + "<span class='main_left'><spring:message code='message.all' />"+page.totalCount+"<spring:message code='message.strip' />，<spring:message code='message.eachPage' />"+page.pageSize+"<spring:message code='message.strip' /></span>"+
    "<ul class='pager pager-pills main_right'>"+
 	"<input type='hidden' id='pageNo' name='pageNo' value='"+page.pageNo+"'/>"+
 	"<input type='hidden' id='pageSize' name='pageSize' value='"+page.pageSize+"'/>";
	if(page.pageNo == 1){
		newHtml = newHtml + "<li class='previous disabled'><a href='#'>«</a></li>";
	}else{
		newHtml = newHtml + "<li class='previous'><a href='#' onclick='javascript:gotoPage("+(page.pageNo-1)+")'>«</a></li>";
	}
	for(i=0;i<page.indexList.length;i++){
		var vardata = page.indexList[i];
		if(page.pageNo == vardata){
			newHtml = newHtml + "<li class='active'><a href='#' onclick='javascript:gotoPage("+vardata+")'>"+vardata+"</a></li>";
		}else{
			newHtml = newHtml + "<li><a href='#' onclick='javascript:gotoPage("+vardata+")'>"+vardata+"</a></li>";
		}
	}
	if(page.pageNo == page.pageSum || page.pageSum == 0){
		newHtml = newHtml + "<li class='next disabled'><a href='#'>»</a></li>";
	}else{
		newHtml = newHtml + "<li class='next'><a href='#' onclick='javascript:gotoPage("+(page.pageNo+1)+")'>»</a></li>";
	}
	newHtml = newHtml + "</ul>";
	}else{
		newHtml = newHtml + "<span class='main_left'><spring:message code='message.all' />0<spring:message code='message.strip' />，<spring:message code='message.eachPage' />0<spring:message code='message.strip' /></span>"+
	    "<ul class='pager pager-pills main_right'>"+
	 	"<input type='hidden' id='pageNo' name='pageNo' value='0'/>"+
	 	"<input type='hidden' id='pageSize' name='pageSize' value='0'/>"+
	 	"<li class='previous disabled'><a href='#'>«</a></li>"+
	 	"<li class='next disabled'><a href='#'>»</a></li>";
	}
	return newHtml;
}

function changeMacPageInfo(page){
	var newHtml = getPageInfo(page);
	$("#macPageDiv").html(newHtml);
}

function refreshMacChartInfo(flag){
	var roadType = new Array();
	$("[name='roadtype']").each(function(){ 
         if($(this).prop("checked")){
             roadType.push("'"+$(this).val()+"'");
         }
     });
	
	$.ajax({
		url:"chart/chart",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId,"roadType":roadType.join(",")}),
		processData:false,
		success:function(data){
			
			refreshBaseInfo(data.base);
			
			var textTitle = "";
			var charData = "";
			if(flag == "2"){
				textTitle = '通行指数（路段）';
				charData = data.count;
			}
			if(flag == "3"){
				textTitle = '通行指数（里程）';
				charData = data.length;
			}
			charData = eval(charData);
			 $("#macContainer").highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text:textTitle
			        },
			        colors:['#008b00','#00ff00','#ffff00','#ffdf00','#ff9500','#ff4a00','#ff0000','#000000'],
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    color: '#000000',
			                    connectorColor: '#000000',
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: textTitle,
			            data: charData
			        }]
			    });	
		}
	});
}

//中观模型
function refreshMidTableInfo(pageNo){
	$.ajax({
		url:"chart/midList",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId,"pageNo":pageNo}),
		processData:false,
		success:function(data){
			if(data.base != null){
				refreshBaseInfo(data.base);
			}
			
			$("#midResultTable tbody").empty();
			var newTr="";
			if(data.page != null && data.page.pageList!=null && data.page.pageList.length > 0){
				var midResults = data.page.pageList;
				for(var i=0;i<midResults.length;i++){
					newTr+= "<tr>"+
					"<td align='center' valign='middle'>"+midResults[i].id +"</td>"+
		            "<td align='center' valign='middle'>"+midResults[i].roadname+"</td>"+
		            "<td align='center' valign='middle'>"+midResults[i].aorb+"</td>"+
		            "<td align='center' valign='middle'>"+midResults[i].time+"</td>"+
		            "<td align='center' valign='middle'>"+midResults[i].density+"</td>"+
		            "<td align='center' valign='middle'>"+midResults[i].flow+"</td>"+
		            "<td align='center' valign='middle'>"+midResults[i].speed+"</td>"+
				"</tr>";
				}
			}else{
				newTr+= "<tr>"+
				"<td align='center' valign='middle' colspan='7'><spring:message code='message.noRelevantData' /></td>"+
				"</tr>";
			}
			$("#midResultTable").append(newTr);
			changeMidPageInfo(data.page);
		}
	});
}

function changeMidPageInfo(page){
	var newHtml = getPageInfo(page);
	$("#midPageDiv").html(newHtml);
}

function refreshMidChartInfo(flag){
	$.ajax({
		url:"chart/midChart",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId}),
		processData:false,
		success:function(data){
			
			refreshBaseInfo(data.base);
			
			var textTitle = "";
			var charData = "";
			if(flag == "1"){
				textTitle = '密度';
				charData = data.density;
			}
			if(flag == "2"){
				textTitle = '流量';
				charData = data.flow;
			}
			if(flag == "3"){
				textTitle = '速度';
				charData = data.speed;
			}
			charData = eval(charData);
			var category = eval(data.category);
			 $("#midContainer").highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text:textTitle
			        },
			        credits: { 
			            enabled: false   //右下角不显示LOGO 
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        xAxis: {
			            categories:category,
			            gridLineWidth: 1, //设置网格宽度为1 
			            lineWidth: 2,  //基线宽度 
			            labels:{y:26}  //x轴标签位置：距X轴下方26像素 
			        },
			        yAxis: {
			            title: {
			                text:textTitle
			            },
			            lineWidth: 2 //基线宽度 
			        },
			        plotOptions:{ //设置数据点 
			            line:{ 
			                dataLabels:{ 
			                    enabled:true  //在数据点上显示对应的数据值 
			                }, 
			                enableMouseTracking: false //取消鼠标滑向触发提示框 
			            } 
			        }, 
			        exporting: { 
			            enabled: false  //设置导出按钮不可用 
			        }, 
			        series: [{
			            name: textTitle,
			            data: charData
			        }]
			    });	
		}
	});
}

//微观模型
function refreshMicTableInfo(pageNo){
	$.ajax({
		url:"chart/micList",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId,"pageNo":pageNo}),
		processData:false,
		success:function(data){
			if(data.base != null){
				refreshBaseInfo(data.base);
			}
			
			$("#micResultTable tbody").empty();
			var newTr="";
			if(data.page != null && data.page.pageList!=null && data.page.pageList.length > 0){
				var micResults = data.page.pageList;
				for(var i=0;i<micResults.length;i++){
					newTr+= "<tr>"+
					"<td align='center' valign='middle'>"+(micResults[i].Id  == null ? "" : micResults[i].Id)+"</td>"+
					"<td align='center' valign='middle'>"+(micResults[i].start_interval == null ? "" : micResults[i].start_interval)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].end_interval == null ? "" : micResults[i].end_interval)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_group == null ? "" : micResults[i].link_group)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_type == null ? "" : micResults[i].link_type)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_count == null ? "" : micResults[i].link_count)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_delay == null ? "" : micResults[i].link_delay)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_density == null ? "" : micResults[i].link_density)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_flow  == null ? "" : micResults[i].link_flow)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_level == null ? "" : micResults[i].link_level)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].service_measure == null ? "" : micResults[i].service_measure)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].los_value == null ? "" : micResults[i].los_value)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_speed == null ? "" : micResults[i].link_speed)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_travel_time == null ? "" : micResults[i].link_travel_time)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_vdt == null ? "" : micResults[i].link_vdt)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_vht == null ? "" : micResults[i].link_vht)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_ptd == null ? "" : micResults[i].link_ptd)+"</td>"+
		            "<td align='center' valign='middle'>"+(micResults[i].link_stop_time == null ? "" : micResults[i].link_stop_time)+"</td>"+
				"</tr>";
				}
			}else{
				newTr+= "<tr>"+
				"<td align='center' valign='middle' colspan='18'><spring:message code='message.noRelevantData' /></td>"+
				"</tr>";
			}
			$("#micResultTable").append(newTr);
			changeMicPageInfo(data.page);
		}
	});
}

function changeMicPageInfo(page){
	var newHtml = getPageInfo(page);
	$("#micPageDiv").html(newHtml);
}

function refreshMicChartInfo(flag){
	
	$.ajax({
		url:"chart/micChart",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId}),
		processData:false,
		success:function(data){
			
			refreshBaseInfo(data.base);
			
			var textTitle = "";
			var charData = "";
			if(flag == "1"){
				textTitle = '密度';
				charData = data.density;
			}
			if(flag == "2"){
				textTitle = '流量';
				charData = data.flow;
			}
			if(flag == "3"){
				textTitle = '速度';
				charData = data.speed;
			}
			charData = eval(charData);
			var category = eval(data.category);
			 $("#micContainer").highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text:textTitle
			        },
			        credits: { 
			            enabled: false   //右下角不显示LOGO 
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        xAxis: {
			            categories:category,
			            gridLineWidth: 1, //设置网格宽度为1 
			            lineWidth: 2,  //基线宽度 
			            labels:{y:26}  //x轴标签位置：距X轴下方26像素 
			        },
			        yAxis: {
			            title: {
			                text:textTitle
			            },
			            lineWidth: 2 //基线宽度 
			        },
			        plotOptions:{ //设置数据点 
			            line:{ 
			                dataLabels:{ 
			                    enabled:true  //在数据点上显示对应的数据值 
			                }, 
			                enableMouseTracking: false //取消鼠标滑向触发提示框 
			            } 
			        }, 
			        exporting: { 
			            enabled: false  //设置导出按钮不可用 
			        }, 
			        series: [{
			            name: textTitle,
			            data: charData
			        }]
			    });	
		}
	});
}

//路口模型
function refreshInterTableInfo1(pageNo){
	$.ajax({
		url:"chart/interList1",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId,"modelId":currentSimulateModelId,"pageNo":pageNo}),
		processData:false,
		success:function(data){
			if(data.base != null){
				refreshBaseInfo(data.base);
			}
			
			$("#interResultTable1 tbody").empty();
			var newTr="";
			if(data.page != null && data.page.pageList!=null && data.page.pageList.length > 0){
				var interResults = data.page.pageList;
				for(var i=0;i<interResults.length;i++){
					newTr+= "<tr>"+
					"<td align='center' valign='middle'>"+(interResults[i].direction == null ? "" : interResults[i].direction)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].Approach_Delay == null ? "" : interResults[i].Approach_Delay)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].Approach_LOS == null ? "" : interResults[i].Approach_LOS)+"</td>"+
				"</tr>";
				}
			}else{
				newTr+= "<tr>"+
				"<td align='center' valign='middle' colspan='3'><spring:message code='message.noRelevantData' /></td>"+
				"</tr>";
			}
			$("#interResultTable1").append(newTr);
			changeInterPageInfo1(data.page);
		}
	});
}

function changeInterPageInfo1(page){
	var newHtml = getPageInfo(page);
	$("#interPageDiv1").html(newHtml);
}

function refreshInterTableInfo2(pageNo){
	$.ajax({
		url:"chart/interList2",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"id":currentSimulateBaseId,"modelId":currentSimulateModelId,"pageNo":pageNo}),
		processData:false,
		success:function(data){
			if(data.base != null){
				refreshBaseInfo(data.base);
			}
			
			$("#interResultTable2 tbody").empty();
			var newTr="";
			if(data.page != null && data.page.pageList!=null && data.page.pageList.length > 0){
				var interResults = data.page.pageList;
				for(var i=0;i<interResults.length;i++){
					newTr+= "<tr>"+
					"<td align='center' valign='middle'>"+(interResults[i].turn == null ? "" : interResults[i].turn)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].Volume == null ? "" : interResults[i].Volume)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].Flow == null ? "" : interResults[i].Flow)+"</td>"+
					"<td align='center' valign='middle'>"+(interResults[i].vc_Ratio == null ? "" : interResults[i].vc_Ratio)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].Control_Delay == null ? "" : interResults[i].Control_Delay)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].Queue_Delay == null ? "" : interResults[i].Queue_Delay)+"</td>"+
					"<td align='center' valign='middle'>"+(interResults[i].Total_Delay == null ? "" : interResults[i].Total_Delay)+"</td>"+
		            "<td align='center' valign='middle'>"+(interResults[i].LOS == null ? "" : interResults[i].LOS)+"</td>"+
				"</tr>";
				}
			}else{
				newTr+= "<tr>"+
				"<td align='center' valign='middle' colspan='8'><spring:message code='message.noRelevantData' /></td>"+
				"</tr>";
			}
			$("#interResultTable2").append(newTr);
			changeInterPageInfo2(data.page);
		}
	});
}

function changeInterPageInfo2(page){
	var newHtml = getPageInfo(page);
	$("#interPageDiv2").html(newHtml);
}

</script>

</body>
</html>
<!-- add by flgang-->
<script>

	(function($){
// 		$('#wj').unbind("click");
// 		$('#wj').bind("click",function(){
// 			$('#gsDiv').css('display','none');
// 			$('#wjDiv').css('display','');
// 			window.frames['mapContainer'].TT.Map.setMapCenter(116.4718, 39.99191,13);
// 		});
// 		$('#gs').unbind("click");
// 		$('#gs').bind("click",function(){
// 			$('#gsDiv').css('display','');
// 			$('#wjDiv').css('display','none');
// 			window.frames['mapContainer'].TT.Map.setMapCenter(-117.851521,33.849019,10);
// 		});

// 		$('#macro_gs_a').unbind('click');
// 		$('#macro_gs_a').bind('click',function(){
// 			$('#subMenu_gs_micro_div').css('display','none');
// 			$('#subMenu_gs_macro_div').toggle();
// 		});
// 		$('#micro_gs_a').unbind('click');
// 		$('#micro_gs_a').bind('click',function(){
// 			$('#subMenu_gs_macro_div').css('display','none');
// 			$('#subMenu_gs_micro_div').toggle();
// 		});

		$('#model_result_hg3').unbind('click');
		$('#model_result_hg3').bind('click',function(){
			$('#subMenu_gs_macro_result_div').toggle();
			//$('#subMenu_gs_micro_result_div').toggle();
		});
	})(jQuery);

</script>