<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="message.title" />!</title>
    <link rel="stylesheet" href="static/style/ol.css" type="text/css">
    <link href="static/style/base.css" rel="stylesheet" type="text/css"/>
    <link href="static/style/layout.css" rel="stylesheet" type="text/css"/>
    <LINK rel=stylesheet type=text/css href="static/style/f/font-awesome.css">
    <LINK rel=stylesheet type=text/css href="static/style/popup.css">
</head>
<body class="body">
<!--主体-->
<div class="layout clearfix">
    <!--展开关闭 结束-->
    <div class="right">
        <!--"height:1000px;"临时高度-->
        <div class="map" id="map" style="height:100%;width: 100%;">

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
<div id="popup_div" style="position: absolute; left: 380px; top:170px; opacity: 1; display: block; z-index: 1991;display: none;" class="ds_dialog ds_dialog_active">
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
                    <div class="ds_dialog_inner" style="width:850px;">
                        <!--标题-->
                        <div class="ds_dialog_header" colspan="2">
                            <div class="ds_dialog_title" style="display: block;">
                                <a id="popup_div_title" href="javascript:void(0)" style="text-align: left ; padding-left:12px;"><spring:message code="message.heading" /></a>
                            </div>
                            <div class="ds_dialog_close" style="display: block;">
                                <a href="javascript:TT.ResMap.closePopDiv();">×</a>
                            </div>
                        </div>
                        <!--内容 二-->
                        <div class="tcbox clearfix" style=" display: block">
                            <ul class="box_top_zs">
                                <li><strong><spring:message code="message.name" />：</strong><span id="pop_name"></span></li>
                                <li><strong><spring:message code="message.type" />：</strong><span id="pop_type"></span></li>
                                <li><strong><spring:message code="message.length" />（km）：</strong><span id="pop_length"></span></li>
                                <li><strong><spring:message code="message.relatedModel" />：</strong><span id="pop_model_type"></span></li>
                            </ul>
                            <div class="box_top_zs_r">
                                <table id="popup_table" width="100%"  class="table table-bordered  xjtabl  ">

                                    <%--<tr>--%>
                                        <%--<td width="26%" align="center" valign="middle">8：00-9：00</td>--%>
                                        <%--<td align="center" valign="middle" bgcolor="#FFFFFF">100</td>--%>
                                        <%--<td align="center" valign="middle" bgcolor="#FFFFFF">100</td>--%>
                                        <%--<td align="center" valign="middle" bgcolor="#FFFFFF">100</td>--%>
                                    <%--</tr>--%>
                                </table>
                            </div>

                        </div>
                        <!--内容 二 结束-->
                    </div>
                </td>
                <td class="ds_dialog_mr"></td>
            </tr>
            <tr>
                <td class="ds_dialog_bl"></td>
                <td class="ds_dialog_bc"></td>
                <td class="ds_dialog_br"></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="static/javascript/api/LAB.min.js"></script>
<script type="text/javascript">
    $LAB
    		.script('././'+'<spring:message code="js.message_lang" />')
            .script("static/javascript/api/jquery/jquery-1.11.3.min.js")
            .script("static/javascript/api/ol/ol.js")
            .script("static/javascript/public/public.js")
            .wait()
            .script("static/javascript/map/map.js")
            .script("static/javascript/app/resmap.js")
            .wait(function () {
                TT.ResMap.init();
            })

</script>
<!--主体 结束-->
</body>
</html>