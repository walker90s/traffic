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

    <!--add by flgang-->
    <LINK rel=stylesheet type=text/css href="static/style/project_menu/project_menu.css">
    <LINK href="static/style/project_menu/font-awesome.css" rel="stylesheet">
    <script type="text/javascript" src="static/javascript/project_menu/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="static/javascript/project_menu/google-maps.js"></script>
    <LINK  rel="stylesheet"  type="text/css" href="static/javascript/project_menu/jquery-ui-1.8.6.custom/css/smoothness/jquery-ui.css"/>
    <script type="text/javascript" src="static/javascript/project_menu/jquery-ui-1.8.6.custom/js/jquery-ui-1.8.6.custom.min.js"></script>
    <script type="text/javascript" src="static/javascript/project_menu/jquery-ui-1.8.6.custom/js/jquery-ui-i18n.min.js"></script>
    <script>$(document).ready(function(){$(".vertical-nav").verticalnav({speed: 400,align: "left"});});</script>


    <!--<link rel="stylesheet" href="static/style/gs_menu/gsStyles.css" type="text/css" />-->

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
        .menu ol { padding-left:15px; border:#E7E7E7 1px solid; border-top:none;background: #f7f2e5;}
        .menu li i{background-color: #ae9c7e;padding: 1px 4px;color: #fff;text-shadow: 0px 0px 0px rgba(255, 255, 255, 0.8);font-family: 宋体;font-style:normal;}
        .menu a{color: #3f3f3f;text-decoration: none;}
        .menu .no {display:none;}
        .menu ol a{width: 228px;display: block;line-height: 2em;margin-left: 20px;}
    </style>
</head>
<body class="body">
<!--头部-->
<div class="header">
    <div class="logo main_left">
        <div id="now_time"></div>
    </div>
    <div class="h_r">
        <div class="h_r_t">
            <a href="/traffic/?locale=zh_CN" class="main_right">中文</a><a href="/traffic/?locale=en_US" class="main_right">English</a>
            <c:if test="${type!=null}">
                <a href="quitting.html" title="<spring:message code='message.exit' />" class="main_right"><i class="fa fa-power-off m_r10"></i><spring:message code="message.exit" /></a>
            </c:if>
            <c:if test="${type==null}">
                <a href="regsiter.html" title="<spring:message code='message.register' />" class="main_right"><i class="fa fa-unlock-alt m_r10"></i><spring:message code="message.register" /></a>
                <a href="login.html" title="<spring:message code='message.login' />" class="main_right bor"><i class="fa fa-user m_r10"></i><spring:message code="message.login" /></a><spring:message code="message.title" />
            </c:if>

        </div>
        <div class="nav">
            <ul class="main_right">
                <li><a href="index.html"><span class="i1"><spring:message code="message.mapShows" /></span></a></li>
                <li><a href="page2.html" class="on"><span class="i2"><spring:message code="message.resultsShow" /></span></a> </li>
                <li><a href="page3.html?type=1"><span class="i3"><spring:message code="message.simulationControl" /></span></a> </li>
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
                        <i class="fa fa-user m_r10"></i><spring:message code="message.notLoggedIn" />
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
    <div class="left" style="height:1000px;">
        <div class="l_n" id="model_list">
            <div>
                <h2>
                    <div class="dh_cn_llist">
                        <%--<div class="cn_div"><b></b><a href="#" class="on">按类型</a><a href="#">按顺序</a></div>--%>
                    </div>
                    <a href="#"><i class="fa fa-plus"></i><spring:message code="message.model" /></a>
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
                    <h3 class="ti_b" onclick="TT.Index.toggleModel(1);">
                        <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
                        <a href="javascript:void(0);"><i id="model_1" class="fa fa-angle-up"></i><spring:message code="message.macroscopicModel" /></a>
                    </h3>
                    <ul class="l_n_list" id="model_ul_1" style="display: none;"></ul>

                    <h3 class="ti_b" onclick="TT.Index.toggleModel(2);">
                        <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
                        <a href="javascript:void(0);"><i id="model_2" class="fa fa-angle-up"></i><spring:message code="message.mediumModel" /></a>
                    </h3>
                    <ul class="l_n_list" id="model_ul_2" style="display: none;"></ul>

                    <h3 class="ti_b" onclick="TT.Index.toggleModel('3');">
                        <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
                        <a href="javascript:void(0);"><i id="model_3" class="fa fa-angle-up"></i><spring:message code="message.microscopicModel" /></a>
                    </h3>
                    <ul class="l_n_list" id="model_ul_3" style="display: none;"></ul>

                    <h3 class="ti_b" onclick="TT.Index.toggleModel('4');">
                        <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
                        <a href="javascript:void(0);"><i id="model_4" class="fa fa-angle-up"></i><spring:message code="message.intersectionModel" /></a>
                    </h3>
                    <ul class="l_n_list" id="model_ul_4" style="display: none;"></ul>

                </div>
                <div id="gsDiv" style="display:none;">
                    <h3 class="ti_b" onclick="TT.Index.toggleModel('hg1');">
                        <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
                        <a href="javascript:void(0);"><i id="model_hg1" class="fa fa-angle-up"></i><spring:message code="message.model" /></a>
                    </h3>
                    <ul class="expmenu" id="model_ul_hg1">
                        <li>
                            <div class="header" style="height:30px;font-size: 16px;line-height: 32px;">
                                <span class="label" ><spring:message code="message.macroscopicModel" /></span>
                            </div>
                            <span class="no">
                                <ul class="menu" style="display:block;" id="macroUl">
                                    <li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0)" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> 91号洲际高速</a></li>
                                    <ol >
                                        <a href="javascript:void(0)" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测流量</a>
                                    </ol>
                                </ul>
                            </span>
                        </li>
                    </ul>



                    <h3 class="ti_b" onclick="TT.Index.toggleModel('hg3');">
                        <!--<i class="fa fa-angle-down">上下箭头换样式就可以了“fa-angle-down”</i>-->
                        <a href="javascript:void(0);"><i id="model_hg3" class="fa fa-angle-up"></i><spring:message code="message.model" /></a>
                    </h3>
                    <ul class="expmenu2" id="model_ul_hg3">
                        <li>
                            <div class="header" style="height:30px;font-size: 16px;line-height: 32px;">
                                <span class="label" ><spring:message code="message.microscopicModel" /></span>
                            </div>
                            <span class="no">
                                <ul class="menu" style="display:block;" id="microUl">
                                    <li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0)" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> 91号洲际高速</a></li>
                                    <ol >
                                        <a href="javascript:void(0)" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测流量</a>
                                    </ol>
                                </ul>
                            </span>
                        </li>
                    </ul>

                </div>

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
        <!--"height:1000px;"临时高度-->
        <div class="map" id="map" style="height:100%;width: 100%;">
            <!--图层按钮-->
            <div class="b_t_c" style="z-index: 1000;">
                <a href="javascript:TT.Index.toggle('layer_controller');" class="button_1 main_right"><spring:message code="message.layer" /></a>
                <!--" class="hide"控制显示/隐藏"-->
                <ul id="layer_controller" class="hide">
                    <li><a href="javascript:void(0);" onclick="TT.Index.changeLayer(0, this)" class="fa">&nbsp;<spring:message code="message.intersection" /></a></li>
                    <li><a href="javascript:void(0);" onclick="TT.Index.changeLayer(1, this)" class="fa">&nbsp;<spring:message code="message.trafficLights" /></a></li>
                    <!--<li><a href="javascript:void(0);" onclick="TT.Index.changeLayer(2, this)" class="fa">&nbsp;<spring:message code="message.signalMachine" /></a></li>-->
                    <%--<li><a href="#">信号灯&nbsp;<i class="fa fa-angle-down"></i></a>
                        <div style="display: none"><a href="#">分类</a><a href="#">分类</a><a href="#">分类</a><a href="#">分类</a></div>
                    </li>--%>
                    <li><a href="javascript:void(0);" onclick="TT.Index.changeLayer(3, this)" class="fa">&nbsp;<spring:message code="message.Detector" /></a></li>
                    <li><a href="javascript:void(0);" onclick="TT.Index.changeLayer(4, this)" class="fa">&nbsp;<spring:message code="message.video" /></a></li>
                    <!--<li><a href="javascript:void(0);" onclick="TT.Index.changeLayer(5, this)" class="fa">&nbsp;<spring:message code="message.section" /></a></li>-->
                </ul>
            </div>
            <!--图层按钮-->
        </div>

        <div id="popup" class="ol-popup">
            <a href="javascript:void(0);" id="popup-closer" class="ol-popup-closer"></a>
            <div class="ds_dialog_inner" style="width:250px; height:100%;">
                <div id="popup-content" class="tcbox clearfix" style=" display: block">
                    <%--<ul class="box_top_zs" style="width:100%;"><li><strong>名称:</strong>8:00</li></ul>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<div  id="intersection_popup_div" style="position: absolute; left: 86px; top: 125px; opacity: 1; display: block; z-index: 1991;display: none;"
     class="ds_dialog ds_dialog_active">
    <div class="ds_dialog_outer">
        <table class="ds_dialog_border">
            <tbody>
            <tr>
                <td class="ds_dialog_tl"></td>
                <td class="ds_dialog_tc"></td>
                <td class="ds_dialog_tr"></td>
            </tr>
            <tr>
                <td class="ds_dialog_ml">
                </td>
                <td class="ds_dialog_mc">
                    <div class="ds_dialog_inner" style="width:1000px; height:350px;">
                        <!--标题-->
                        <div class="ds_dialog_header" colspan="2">
                            <div class="ds_dialog_title" style="display: block;">
                                <a id="intersection_popup_a_time" href="javascript:TT.Index.changePopTab('intersection_popup_a_time')" class="on" ><spring:message code="message.signalTimingScheme" /></a>
                                <a id="intersection_popup_a_flow" href="javascript:TT.Index.changePopTab('intersection_popup_a_flow')"><spring:message code="message.observedFlow" /></a>
                                <a id="intersection_popup_a_name" href="javascript:void(0);" style="width: 350px;float: right">广顺北大街</a>
                            </div>
                            <div class="ds_dialog_close" style="display: block;"><a  href="javascript:TT.Index.closeIntersection();">×</a></div>
                        </div>
                        <!--内容 一-->
                        <div id="intersection_popup_time_div" class="tcbox" style=" height:350px;">
                            <div class="box_l_t"><img src="static/images/b_img2.jpg"/>
                                <font class="b1"style="left:160px; top:240px; ">2</font>
                                <font class="b2" style="left:195px; top:240px; ">1</font>
                                <font class="t1" style="left:104px; top:35px; ">1</font>
                                <font class="t2" style="left:138px; top:35px; ">2</font>
                                <font class="r1" style="left:275px; top:96px; ">3</font>
                                <font class="r2" style="left:275px; top:128px; ">4</font>
                                <font class="l1" class="l3" style="left:22px; top:185px; ">3</font>
                                <font class="l1" class="l3" style="left:22px; top:153px; ">4</font>
                            </div>
                            <div class="box_r_t">
                                <div class="box_top_lg"><span id="time_cycle"><spring:message code="message.cycle" />：s</span><span id="time_phase"><spring:message code="message.phaseDifference" />：0</span></div>
                                <table width="100%" class="table table-bordered" id="time_table">
                                    <thead>
                                    <tr>
                                        <th width="15%" valign="middle"><spring:message code="message.stage" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.greenLight" />（s）</th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.yellowLight" />（s）</th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.redLight" />（s）</th>
                                    </tr>
                                    </thead>
                                    <%--<tr>
                                        <td width="12%" align="center" valign="middle">1</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                    </tr>--%>

                                </table>

                            </div>
                        </div>
                        <!--内容 一 结束-->
                        <!--内容 二-->
                        <div id="intersection_popup_flow_div" class="tcbox" style=" height:350px; display:none;">
                            <div class="box_l_t"><img src="static/images/b_img1.jpg"/>
                                <font class="b1" id="northLeft" style="left:160px; top:240px; ">northLeft</font>
                                <font class="b2" id="northRight" style="left:195px; top:240px; ">northRight</font>
                                <font class="b3" id="northDirect" style="left:178px; top:258px; ">northDirect</font>
                                <font class="t1" id="southRight" style="left:104px; top:35px; ">southRight</font>
                                <font class="t2" id="southLeft" style="left:138px; top:35px; ">southLeft</font>
                                <font class="t3" id="southDirect" style="left:122px; top:18px; ">southDirect</font>
                                <font class="r1" id="eastRight" style="left:275px; top:96px; ">eastRight</font>
                                <font class="r2" id="eastLeft" style="left:275px; top:128px; ">eastLeft</font>
                                <font class="r3" id="eastDirect" style="left:290px; top:112px; ">eastDirect</font>
                                <font class="l1" id="westLeft" style="left:22px; top:152px; ">westLeft</font>
                                <font class="l2" id="westDirect" style="left:5px; top:167px; ">westDirect</font>
                                <font class="l3" id="westRight" style="left:22px; top:185px; ">westRight</font>
                            </div>
                            <div class="box_r_t">
                                <table width="100%" class="table table-bordered" id="flow_table">
                                    <thead>
                                    <tr>
                                        <th width="15%" valign="middle">&nbsp;</th>
                                        <th colspan="3" bgcolor="#e4edf3"><spring:message code="message.north" /></th>
                                        <th colspan="3"><spring:message code="message.east" /></th>
                                        <th colspan="3" bgcolor="#e4edf3"><spring:message code="message.south" /></th>
                                        <th colspan="3"><spring:message code="message.west" /></th>
                                    </tr>
                                    <tr>
                                        <th width="15%" valign="middle"><spring:message code="message.time" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.turnRight" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.straightLine" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.turnLeft" /></th>
                                        <th><spring:message code="message.turnRight" /></th>
                                        <th><spring:message code="message.straightLine" /></th>
                                        <th><spring:message code="message.turnLeft" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.turnRight" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.straightLine" /></th>
                                        <th bgcolor="#e4edf3"><spring:message code="message.turnLeft" /></th>
                                        <th><spring:message code="message.turnRight" /></th>
                                        <th><spring:message code="message.straightLine" /></th>
                                        <th><spring:message code="message.turnLeft" /></th>
                                    </tr>
                                    </thead>
                                    <%--<tr>
                                        <td width="12%" align="center" valign="middle">8：00-9：00</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle">100</td>
                                        <td align="center" valign="middle">100</td>
                                        <td align="center" valign="middle">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle" bgcolor="#e4edf3">100</td>
                                        <td align="center" valign="middle">100</td>
                                        <td align="center" valign="middle">100</td>
                                        <td align="center" valign="middle">110</td>
                                    </tr>--%>
                                </table>
                            </div>
                        </div>
                        <!--内容 二 结束-->
                    </div>
                </td>
                <td class="ds_dialog_mr">
                </td>
            </tr>
            <tr>
                <td class="ds_dialog_bl">
                </td>
                <td class="ds_dialog_bc"></td>
                <td class="ds_dialog_br">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div  id="highWay_observeflow_popup_div" style="position: absolute; left: 86px; top: 125px; opacity: 1; display: block; z-index: 1991;display: none;"
     class="ds_dialog ds_dialog_active">
    <div class="ds_dialog_outer">
    <table class="ds_dialog_border">
            <tbody>
            <tr>
                <td class="ds_dialog_tl"></td>
                <td class="ds_dialog_tc"></td>
                <td class="ds_dialog_tr"></td>
            </tr>
            <tr>
                <td class="ds_dialog_ml">
                </td>
                <td class="ds_dialog_mc">
                    <div class="ds_dialog_inner" style="width:1000px; height:450px;">
                        <!--标题-->
                        <div class="ds_dialog_header" colspan="2">
                            <div class="ds_dialog_title" style="display: block;">
                                <a id="observeflow_position" href="javascript:void(0);" style="width: 350px;float: left;"></a>
                            </div>
                            <div class="ds_dialog_close" style="display: block;"><a  href="javascript:TT.Index.closeHighWayObserveFlowWindow();">×</a></div>
                        </div>
                        <!--内容 一-->
                          <div class="ri_cn" >
				        	  <div class="m_l15  m_b15 m_r15" style="background:#FFF;"> 
				              	  <div id="highWay_observeflow_Container" style="min-width:700px;min-height:400px;"></div>
				              </div>
				          </div>
                        <!--内容 一 结束-->

                    </div>
                </td>
                <td class="ds_dialog_mr">
                </td>
            </tr>
            <tr>
                <td class="ds_dialog_bl">
                </td>
                <td class="ds_dialog_bc"></td>
                <td class="ds_dialog_br">
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</div>
<div id="over_div" class="box_bg"
     style="position: absolute; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 1001; visibility: visible; zoom: 1; display: none;">

</div>
<script type="text/javascript" src="static/javascript/api/LAB.min.js"></script>
<script type="text/javascript">
    function currentTime() {
        var week = ['<spring:message code="message.sunday" />', '<spring:message code="message.monday" />', '<spring:message code="message.tuesday" />', '<spring:message code="message.wednesday" />', '<spring:message code="message.thursday" />', '<spring:message code="message.friday" />', '<spring:message code="message.saturday" />'];
        var d = new Date(), str = '';
        str += d.getFullYear() + '-';
        str += d.getMonth() + 1 + '-';
        str += d.getDate() + ' ';
        str += week[d.getDay()]; //获取当前星期X(0-6,0代表星期天)
        /* str += d.getHours()+'时';
         str  += d.getMinutes()+'分';
         str+= d.getSeconds()+'秒';*/
        return str;
    }
    $LAB
    		.script('././'+'<spring:message code="js.message_lang" />')
            .script("static/javascript/api/jquery/jquery-1.11.3.min.js")
            .script("static/javascript/api/ol/ol.js")
            .script("static/javascript/public/public.js")
            .script("static/javascript/highcharts-4.1.9/js/highcharts.js")
            .wait()
            .script("static/javascript/map/map.js")
            .script("static/javascript/app/index.js")
            .wait(function () {
                TT.Index.init();
            })
            .wait(function () {
                $("#now_time").html(currentTime());
                $('.left').css('height', $(window).height() - 120);
                $(window).resize(function () {
                    $('.left').css('height', $(window).height() - 120);
                });
            })
    function Switch_Chinese_English(obj){
        var href = window.location.href;
        var strs = href.split("?");
        if(href.indexOf("?") > -1){
            href += "&locale=" + obj;
        }else{
            href += "?locale=" + obj;
        }
        window.location.href = href;
    }
</script>

<!--主体 结束-->
</body>
</html>
