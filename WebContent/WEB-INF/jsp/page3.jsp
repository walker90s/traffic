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
<!--     
<HTA:APPLICATION ID="oMyApp" 
	    APPLICATIONNAME="Application Executer" 
	    BORDER="no"
	    CAPTION="no"
	    SHOWINTASKBAR="yes"
	    SINGLEINSTANCE="yes"
	    SYSMENU="yes"
	    SCROLL="no"
	    WINDOWSTATE="normal"/>
 -->
</head>
<body>
<!--头部-->
	<div class="header">
    <div class="logo main_left">
        <div id="now_time"></div>
    </div>
        <div class="h_r">
        	<div class="h_r_t">
				<a href="./page3?locale=zh_CN" class="main_right">中文</a>/<a href="./page3?locale=en_US" class="main_right">English</a>
				<c:if test="${type!=null}">
					<a href="quitting.html" title="<spring:message code='message.exit' />" class="main_right"><i class="fa fa-power-off m_r10"></i><spring:message code="message.exit" /></a>
				</c:if>
				<c:if test="${type==null}">
					<a href="regsiter.html" title="注册" class="main_right"><i class="fa fa-unlock-alt m_r10"></i><spring:message code="message.register" /></a>
					<a href="login.html" title="登录" class="main_right bor"><i class="fa fa-user m_r10"></i><spring:message code="message.login" /></a><spring:message code="message.title" />!
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
							<a href="usermanage.html" title="后台管理" class="m_l10 p_l10 p_r20" disabled="disabled">
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
	<ul class="fz_ul_list clearfix">
    	<li><div class="cn1"><h2>TransCAD</h2><p><spring:message code="message.TransCAD" /></p><a href="#" class="button_1 m_r10" onclick="runFile1()"><spring:message code="message.start-up" /></a><span class="u-upload"><a href="#" class="button_1"><spring:message code="message.pathSetting" /></a><input type="file" id="uploadFile" name="uploadFile" onchange="changeRunPath1(this)"/></span><p id="runPath1"></p></div></li>
    	<li><div class="cn2"><h2>DTA</h2><p><spring:message code="message.DTA" /></p><a href="#" class="button_1 m_r10" onclick="runFile2()"><spring:message code="message.start-up" /></a><span class="u-upload"><a href="#" class="button_1"><spring:message code="message.pathSetting" /></a><input type="file" id="uploadFile" name="uploadFile" onchange="changeRunPath2(this)"/></span><p id="runPath2"></p></div></li>
    	<li><div class="cn3"><h2>Paramics</h2><p><spring:message code="message.Paramics" /></p><a href="#" class="button_1 m_r10" onclick="runFile3()"><spring:message code="message.start-up" /></a><span class="u-upload"><a href="#" class="button_1"><spring:message code="message.pathSetting" /></a><input type="file" id="uploadFile" name="uploadFile" onchange="changeRunPath3(this)"/></span><p id="runPath3"></p></div></li>
    	<li><div class="cn4"><h2>Synchro</h2><p><spring:message code="message.Synchro" /></p><a href="#" class="button_1 m_r10" onclick="runFile4()"><spring:message code="message.start-up" /></a><span class="u-upload"><a href="#" class="button_1"><spring:message code="message.pathSetting" /></a><input type="file" id="uploadFile" name="uploadFile" onchange="changeRunPath4(this)"/></span><p id="runPath4"></p></div></li>
    </ul>
    <!--表格-->
    <form id="modelFormId" enctype="multipart/form-data" name="modelForm" action="page3.html" method="post">
    	<input type="hidden" id="modelType" name="modelType" value="${modelType }"/>
    	<input type="hidden" id="localModelFilePath" name="localModelFilePath"/>
    	
    <div class="tb_ti clearfix">
		<div id="uploadDiv" style="display:none" class="main_right"><input type="hidden" value=""  class="m_r10"/><span class="u-upload"><button type="button" onclick="showSelectDialog()"><spring:message code="message.addLocalSimulationData" /></button></span></div><a href="#" onclick="queryServerModel()" title="线上仿真数据" class="a1 m_r10"><spring:message code="message.onlineSimulationData" /></a><a href="#" onclick="queryLocalModel()" title="本地仿真数据" class="a2"><spring:message code="message.localSimulationData" /></a>
    <ul id=read-log class=out></ul>
    </div>
    <c:choose>
    <c:when test="${modelType == 1}"> 
 <div id="localModel" class="m_l15 m_r15" style="display:block">
    	<table width="100%" id="localModelTable" class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="3%"><spring:message code="message.serialNumber" /></th>
            <th width="14%"><spring:message code="message.simulationDataName" /></th>
            <th width="8%"><spring:message code="message.simulationSoftware" /></th>
            <th width="5%"><spring:message code="message.simulationModel" /></th>
            <th width="14%"><spring:message code="message.simulationTime" /></th>
            <th width="14%"><spring:message code="message.simulationStartTime" /></th>
            <th width="14%"><spring:message code="message.simulationEndTime" /></th>
            <th width="16%"><spring:message code="message.explain" /></th>
            <th width="12%"><spring:message code="message.operation" /></th>
          </tr>
         </thead>
          <c:choose>
			<c:when test="${not empty page.pageList}">
				<c:forEach items="${page.pageList }" var="vardata">
				<tr>
					<td align="center" valign="middle">${vardata.id }</td>
		            <td align="center" valign="middle">${vardata.name }</td>
		            <td align="center" valign="middle">${vardata.simulateSoft }</td>
		            <td align="center" valign="middle">${vardata.simulateModel }</td>
		            <td align="center" valign="middle"><fmt:formatDate value="${vardata.simulateTimed }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		            <td align="center" valign="middle"><fmt:formatDate value="${vardata.simulateStartTimed }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		            <td align="center" valign="middle"><fmt:formatDate value="${vardata.simulateEndTimed }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		           	<td align="center" valign="middle"><input id="localSimulateComment${vardata.id }" type="text" value="${vardata.comment }"  class="m_r10"/></td>
		            <td align="center" valign="middle"><a href="#" title="删除" onclick="deleteLocalModelInfo(${vardata.id })" class="fa fa-trash cz_an"></a><a href="#" title="上传" onclick="uploadLocalModelInfo(${vardata.id })" class="u-upload cz_an fa fa-upload"></a></td>
          		</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td align="center" valign="middle" colspan="9"><spring:message code="message.noRelevantData" /></td>
				</tr>
			</c:otherwise>
		</c:choose>
        </table>
		<div id="pageDiv" class="page">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
</div>
    </c:when> 
 <c:when test="${modelType == 2}"> 
		<div id="serverModel" class="m_l15 m_r15" style="display:block">
    	<table width="100%" id="severModelTable" class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="3%"><spring:message code="message.serialNumber" /></th>
            <th width="14%"><spring:message code="message.simulationDataName" /></th>
            <th width="8%"><spring:message code="message.simulationSoftware" /></th>
            <th width="5%"><spring:message code="message.simulationModel" /></th>
            <th width="14%"><spring:message code="message.simulationTime" /></th>
            <th width="14%"><spring:message code="message.simulationStartTime" /></th>
            <th width="14%"><spring:message code="message.simulationEndTime" /></th>
            <th width="16%"><spring:message code="message.explain" /></th>
            <th width="12%"><spring:message code="message.operation" /></th>
          </tr>
         </thead>
         <c:choose>
			<c:when test="${not empty page.pageList}">
				<c:forEach items="${page.pageList }" var="vardata">
				<tr>
					<td align="center" valign="middle">${vardata.id }</td>
		            <td align="center" valign="middle">${vardata.name }</td>
		            <td align="center" valign="middle">${vardata.simulateSoft }</td>
		            <td align="center" valign="middle">${vardata.simulateModel }</td>
		            <td align="center" valign="middle"><fmt:formatDate value="${vardata.simulateTimed }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		            <td align="center" valign="middle"><fmt:formatDate value="${vardata.simulateStartTimed }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		            <td align="center" valign="middle"><fmt:formatDate value="${vardata.simulateEndTimed }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		            <td align="center" valign="middle">${vardata.comment }</td>
		            <td align="center" valign="middle"><a href="#" title="删除" onclick="deleServerModel('${vardata.id }')" class="fa fa-trash cz_an"></a></td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td align="center" valign="middle" colspan="9"><spring:message code="message.noRelevantData" /></td>
				</tr>
			</c:otherwise>
		</c:choose>
        </table>
		<div id="pageDiv" class="page">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
</div>
</c:when> 
    <c:otherwise>
<div class="m_l15 m_r15" style="display:block">
    	<table width="100%"  class="table table-bordered  table-hover table-striped tb_box">
        <thead>
          <tr>
            <th width="3%"><spring:message code="message.serialNumber" /></th>
            <th width="14%"><spring:message code="message.simulationDataName" /></th>
            <th width="8%"><spring:message code="message.simulationSoftware" /></th>
            <th width="5%"><spring:message code="message.simulationModel" /></th>
            <th width="14%"><spring:message code="message.simulationTime" /></th>
            <th width="14%"><spring:message code="message.simulationStartTime" /></th>
            <th width="14%"><spring:message code="message.simulationEndTime" /></th>
            <th width="16%"><spring:message code="message.explain" /></th>
            <th width="12%"><spring:message code="message.operation" /></th>
          </tr>
         </thead>
			<tr>
				<td align="center" valign="middle" colspan="9"><spring:message code="message.noRelevantData" /></td>
			</tr>
        </table>
		<div id="pageDiv" class="page">
        	<jsp:include page="/WEB-INF/jsp/pagetor.jsp"></jsp:include>
        </div>
        </div>
	</c:otherwise>
</c:choose>
</form>

<div id="selectDialog" style="position: absolute; left: 280px; top:170px; opacity: 1; display: none; z-index: 1991;" class="ds_dialog ds_dialog_active">
    <div class="ds_dialog_outer">
        <table class="ds_dialog_border">
            <tbody>
                <tr>
                    <td class="ds_dialog_tl">
                    </td>
                    <td class="ds_dialog_tc">
                    </td>
                    <td class="ds_dialog_tr">
                    </td>
                </tr>
                <tr>
                    <td class="ds_dialog_ml">
                    </td>
                    <td class="ds_dialog_mc">
                    <div class="ds_dialog_inner" style="width:600px; height:450px;">
                      <!--标题-->
                      <div class="ds_dialog_header" colspan="2">
                        <div class="ds_dialog_title" style="display: block;"><a href="#" style="text-align: left ; padding-left:12px;">添加本地仿真结果</a></div>
                        <div class="ds_dialog_close" style="display: block;"><a href="javascript:hideSelectDialog()">×</a></div>
                      </div>
                      <!--内容 二-->
                      <div class="tcbox" style=" height:350px; display: block">
                        <div class="box_bd_xjsj">
                        	<dl>
                                <dd><span class="span"><spring:message code="message.modleType" />：</span><select class="form-control " onchange="dialogModelTypeChange()" id="dialogModelType" name="dialogModelType"><option value ='1' selected='selected'><spring:message code="message.macroscopicModle" /></option><option value ='2'><spring:message code="message.mediumModel" /></option><option value ='3'><spring:message code="message.microscopicModel" /></option><option value ='4'><spring:message code="message.intersectionModel" /></option></select>
                                <span  class="span"> <spring:message code="message.modle" />：</span><select class="form-control " id="dialogModelId" name="dialogModelId"></select></dd>
                            </dl>
                            <dl>
                                <dd><span class="span"><spring:message code="message.simulationSoftware" />：</span><select class="form-control " id="dialogModelSoft" name="dialogModelSoft"><option value ='TransCAD' selected='selected'>TransCAD</option><option value ='DTA'>DTA</option><option value ='Paramics'>Paramics</option><option value ='Synchro'>Synchro</option></select></dd>
                            </dl>
                        	<dl>
                                <dd><span  class="span"><spring:message code="message.simulationTime" />：</span><input id="dialogModelTime" name="dialogModelTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true})" class='Wdate'/></dd>
                                <dd><span  class="span"><spring:message code="message.simulationStartTime" />：</span><input id="dialogModelStartTime" name="dialogModelStartTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true})" class='Wdate' /><span  class="span"><spring:message code="message.simulationEndTime" />：</span><input  id="dialogModelEndTime" name="dialogModelEndTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true})" class='Wdate' /></dd>
                            </dl>
                        	<dl>
                                <dd id="dialogFiledd1"><span  class="span"><spring:message code="message.simulationType" />：</span><input type="text" readonly class="input_t " id="dialogFileName1" name="dialogFileName1"><span class="u-upload  m_l10 ">    <button type="button" class="button_1"><spring:message code="message.selectFile" /></button>    <input id="dialogFile1" type="file" onchange="dialogUploadLocalModel1(this)"></span></dd>
                                <dd id="dialogFiledd2"><span  class="span"><spring:message code="message.simulationType" />：</span><input type="text" readonly class="input_t " id="dialogFileName2" name="dialogFileName2"><span class="u-upload  m_l10 ">    <button type="button" class="button_1"><spring:message code="message.selectFile" /></button>    <input id="dialogFile2" type="file" onchange="dialogUploadLocalModel2(this)"></span></dd>
                                <dd id="dialogFiledd3"><span  class="span"><spring:message code="message.simulationType" />：</span><input type="text" readonly class="input_t " id="dialogFileName3" name="dialogFileName3"><span class="u-upload  m_l10 ">    <button type="button" class="button_1"><spring:message code="message.selectFile" /></button>    <input id="dialogFile3" type="file" onchange="dialogUploadLocalModel3(this)"></span></dd>
                            </dl>
                            <div class="box_an_bf">
                            <button class="button_1 button-danger_1" onclick="dialogUploadFile()"><spring:message code="message.confirm" /></button> <button class="button_1 m_l20" onclick="hideSelectDialog()"><spring:message code="message.cancel" /></button>
                            </div>
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
<div id="over_div" class="box_bg"
     style="position: absolute; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 1001; visibility: visible; zoom: 1; display: none;">
</div>
<!--主体 结束-->
<script type="text/javascript" src="static/javascript/api/LAB.min.js"></script>
<script type="text/javascript">
$LAB
        .script("static/javascript/api/jquery/jquery-1.11.3.min.js")
        .script("static/javascript/public/common.js")
        .script("static/javascript/My97DatePicker/WdatePicker.js")
        .wait()
        .wait(function(){
            jQuery("#now_time").html(currentTime());
            var modelType = jQuery("#modelType").val();
            if(modelType == "1"){
            	showLocalModel();
            }else{
            	showServerModel();
            }
        });

var fileList = new Array();
var fileResult = new Array();
var fileReader = new Array();

function changeRunPath1(obj){
	jQuery("#runPath1").html(obj.value);
}

function changeRunPath2(obj){
	jQuery("#runPath2").html(obj.value);
}

function changeRunPath3(obj){
	jQuery("#runPath3").html(obj.value);
}

function changeRunPath4(obj){
	jQuery("#runPath4").html(obj.value);
}

function runFile1(){
	var file = jQuery("#runPath1").html();
	if(file == "")
		return;
	runFile(file);
}

function runFile2(){
	var file = jQuery("#runPath2").html();
	if(file == "")
		return;
	runFile(file);
}

function runFile3(){
	var file = jQuery("#runPath3").html();
	if(file == "")
		return;
	runFile(file);
}

function runFile4(){
	var file = jQuery("#runPath4").html();
	if(file == "")
		return;
	runFile(file);
}

function runFile(file){
	try{
		runFileIE(file);
	}catch(e){
		try{
			runFileFireFox(file);
		}catch(e1){
			try{
				runFileChrome(file);
			}catch(e2){
				
			}
		}
	}
}


function runFileIE(file){
	WshShell = new ActiveXObject("WScript.Shell");
    WshShell.Run(file, 1, false);
}

function runFileFireFox(file){
	netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	var file = Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);
	file.initWithPath(file);
	file.launch();
	
	//var oURL = "www.baidu.com";
	//netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	//var process = Components.classes['@mozilla.org/process/util;1'].getService(Components.interfaces.nsIProcess);
	//var targetFile = Components.classes['@mozilla.org/file/local;1'].createInstance(Components.interfaces.nsILocalFile);
	//targetFile.initWithPath("D:/temp/1.exe");
	//process.init(targetFile);
	//var arguments = [oURL];  //arguments参数必须是数组，否则下面语句不执行
	//process.run(false, arguments, arguments.length,{});
}

function runFileChrome(file){
	
}

function showLocalModel(){
	jQuery("#uploadDiv").css('display','block');
}
function showServerModel(){
	jQuery("#uploadDiv").css('display','none');
}

function queryLocalModel(){
	if(jQuery("#modelType").val() == "1")
		return;
	jQuery("#modelType").val("1");
	jQuery("#modelFormId").attr("action", "page3");
	jQuery("#modelFormId").submit();	
}

function queryServerModel(){
	if(jQuery("#modelType").val() == "2")
		return;
	jQuery("#modelType").val("2");
	jQuery("#modelFormId").attr("action","page3");
	jQuery("#modelFormId").submit();
}

function deleServerModel(id){
	jQuery("#modelType").val("2");
	jQuery("#modelFormId").attr("action", "model/delete?id="+id);
	jQuery("#modelFormId").submit();
}

function deleteLocalModelInfo(id){
	$.ajax({
		url:"model/local/deleteModeInfo?id="+id,
		type:"POST",
		dataType:"json",
		data:"",
		success:function(data){
			jQuery("#modelType").val("1");
			jQuery("#modelFormId").attr("action", "page3");
			jQuery("#modelFormId").submit();	
		}
	});
}

function uploadLocalModelInfo(id){
	var comment = $("#localSimulateComment"+id).val();
	
	var postData = {"comment":comment};
    $.ajax({
		url:"model/local/uploadModeInfo?id="+id,
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify(postData),
		processData:false,
		success:function(data){
			jQuery("#modelType").val("1");
			jQuery("#modelFormId").attr("action", "page3");
			jQuery("#modelFormId").submit();	
		}
    });
}

function changePageInfo(page){
	var newHtml = "";
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
	$("#pageDiv").html(newHtml);
}

function gotoPage(pageNo){
	var modeType = jQuery("#modelType").val();
	$.ajax({
		url:"model/turnPage?modelType="+modeType+"&pageNo="+pageNo,
		type:"POST",
		dataType:"json",
		data:"",
		success:function(data){
			if(modeType == "1"){
				$("#localModelTable tbody").empty();
				var newTr="";
				if(data != null && data.pageList!=null && data.pageList.length > 0){
					var models = data.pageList;
					for(var i=0;i<models.length;i++){
						newTr+= "<tr>"+
						"<td align='center' valign='middle'>"+models[i].id +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].name +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateSoft +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateModel +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateTimed +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateStartTimed +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateEndTimed +"</td>"+
			            "<td align='center' valign='middle'><input id='localSimulateComment"+models[i].id +"' type='text' value=''  class='m_r10'/></td>"+
			            "<td align='center' valign='middle'><a href='#' title='删除' onclick=\"deleteLocalModelInfo('"+models[i].id +"')\" class='fa fa-trash cz_an'></a><a href=\"#\" title=\"上传\" onclick=\"uploadLocalModelInfo('"+models[i].id+"')\" class=\"u-upload cz_an fa fa-upload\"></a></td>"+
					"</tr>";
					}
				}else{
					newTr+= "<tr>"+"<td align='center' valign='middle' colspan='9'><spring:message code='message.noRelevantData' /></td>"+"</tr>";
				}
				$("#localModelTable").append(newTr);
			}else{
				$("#severModelTable tbody").empty();
				var newTr="";
				if(data != null && data.pageList!=null && data.pageList.length > 0){
					var models = data.pageList;
					for(var i=0;i<models.length;i++){
						newTr+= "<tr>"+
						"<td align='center' valign='middle'>"+models[i].id +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].name +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateSoft +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateModel +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateTimed +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateStartTimed +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].simulateEndTimed +"</td>"+
			            "<td align='center' valign='middle'>"+models[i].comment +"</td>"+
			            "<td align='center' valign='middle'><a href='#' title='删除' onclick=\"deleServerModel('"+models[i].id +"')\" class='fa fa-trash cz_an'></a><a href=\"#\" title=\"上传\" onclick=\"uploadLocalModelInfo('"+models[i].id+"')\" class=\"u-upload cz_an fa fa-upload\"></a></td>"+
					"</tr>";
					}
				}else{
					newTr+= "<tr>"+"<td align='center' valign='middle' colspan='9'><spring:message code='message.noRelevantData' /></td>"+"</tr>";
				}
				$("#severModelTable").append(newTr);
			}
			changePageInfo(data);
		}
	});
}

function showSelectDialog(){
	
	$("#dialogModelTime").val("");
	$("#dialogModelStartTime").val("");
	$("#dialogModelEndTime").val("");
	
	$("#dialogFileName1").val("");
	$("#dialogFileName2").val("");
	$("#dialogFileName3").val("");
	
	$("#dialogFiledd1").show();
	$("#dialogFiledd2").show();
	$("#dialogFiledd3").show();
	
	var st=document.documentElement.scrollTop|| document.body.scrollTop;//滚动条距顶部的距离
    var sl=document.documentElement.scrollLeft|| document.body.scrollLeft;//滚动条距左边的距离
    var ch=document.documentElement.clientHeight;//屏幕的高度
    var cw=document.documentElement.clientWidth;//屏幕的宽度
    var objH=$("#selectDialog").height();//浮动对象的高度
    var objW=$("#selectDialog").width();//浮动对象的宽度
    var objT=Number(st)+(Number(ch)-Number(objH))/2;
    var objL=Number(sl)+(Number(cw)-Number(objW))/2;

    $("#selectDialog").css('left',objL);
    $("#selectDialog").css('top',objT);
    $("#selectDialog").show();
    $("#over_div").show();
    
	dialogModelTypeChange();
}
function dialogModelTypeChange(){
	var modelType = $("#dialogModelType").val();
	$.getJSON("simulate/modelTypeChange?modelType="+modelType, function(data){
		$("#dialogModelId").html(data.data);
	});
	
	$("#dialogFiledd1").html('<span  class="span"><spring:message code="message.simulationType" />：</span><input type="text" readonly class="input_t " id="dialogFileName1" name="dialogFileName1"><span class="u-upload  m_l10 ">    <button type="button" class="button_1"><spring:message code="message.selectFile" /></button>    <input id="dialogFile1" type="file" onchange="dialogUploadLocalModel1(this)"></span>');
	$("#dialogFiledd2").html('<span  class="span"><spring:message code="message.simulationType" />：</span><input type="text" readonly class="input_t " id="dialogFileName2" name="dialogFileName2"><span class="u-upload  m_l10 ">    <button type="button" class="button_1"><spring:message code="message.selectFile" /></button>    <input id="dialogFile2" type="file" onchange="dialogUploadLocalModel2(this)"></span>');
	$("#dialogFiledd3").html('<span  class="span"><spring:message code="message.simulationType" />：</span><input type="text" readonly class="input_t " id="dialogFileName3" name="dialogFileName3"><span class="u-upload  m_l10 ">    <button type="button" class="button_1"><spring:message code="message.selectFile" /></button>    <input id="dialogFile3" type="file" onchange="dialogUploadLocalModel3(this)"></span>');
		
	if(modelType == "1"){
		$("#dialogFiledd1").show();
		$("#dialogFiledd2").hide();
		$("#dialogFiledd3").hide();
	}else if(modelType == "2"){
		$("#dialogFiledd1").show();
		$("#dialogFiledd2").show();
		$("#dialogFiledd3").show();
	}else if(modelType == "3"){
		$("#dialogFiledd1").show();
		$("#dialogFiledd2").hide();
		$("#dialogFiledd3").hide();
	}else if(modelType == "4"){
		$("#dialogFiledd1").show();
		$("#dialogFiledd2").show();
		$("#dialogFiledd3").show();
	}else{
		$("#dialogFiledd1").hide();
		$("#dialogFiledd2").hide();
		$("#dialogFiledd3").hide();
	}
}

function dialogUploadLocalModel1(obj){
	$("#dialogFileName1").val(obj.value);
}

function dialogUploadLocalModel2(obj){
	$("#dialogFileName2").val(obj.value);
}

function dialogUploadLocalModel3(obj){
	$("#dialogFileName3").val(obj.value);
}

function hideSelectDialog(){
	$("#selectDialog").hide();
	$("#over_div").hide();
}


function dialogUploadFile(){
	if($("#dialogModelType").val() == ""){
		alert("<spring:message code='message.selectSimulationModelType' />");
		return;
	}
	if($("#dialogModelId").val() == ""){
		alert("<spring:message code='message.selectSimulationModel' />");
		return;
	}
	if($("#dialogModelTime").val() == ""){
		alert("<spring:message code='message.selectSimulationTime' />");
		return;
	}
	if($("#dialogModelStartTime").val() == ""){
		alert("<spring:message code='message.selectSimulationStarttime' />");
		return;
	}
	if($("#dialogModelEndTime").val() == ""){
		alert("<spring:message code='message.selectSimulationEndtime' />");
		return;
	}
	
	var fileCount = 0;
	if($("#dialogModelType").val() == "1"){
		if($("#dialogFileName1").val() == ""){
			alert("<spring:message code='message.pleaseUploadSimulationFile' />");
			return;
		}
		fileCount = 1;
	}else if($("#dialogModelType").val() == "2"){
		if($("#dialogFileName1").val() == "" || $("#dialogFileName2").val() == "" || $("#dialogFileName3").val() == ""){
			alert("<spring:message code='message.pleaseUploadSimulationFile' />");
			return;
		}
		fileCount = 3;
	}else if($("#dialogModelType").val() == "3"){
		if($("#dialogFileName1").val() == ""){
			alert("<spring:message code='message.pleaseUploadSimulationFile' />");
			return;
		}
		fileCount = 1;
	}else if($("#dialogModelType").val() == "4"){
		if($("#dialogFileName1").val() == "" || $("#dialogFileName2").val() == "" || $("#dialogFileName3").val() == ""){
			alert("<spring:message code='message.pleaseUploadSimulationFile' />");
			return;
		}
		fileCount = 3;
	}

	if(fileCount <= 0){
		alert("<spring:message code='message.pleaseUploadSimulationFile' />");
		return;
	}
	
	fileList = new Array();
	fileResult = new Array();
	fileReader = new Array();
	
	try{
		if(!window.FileReader){  
		    alert("<spring:message code='message.browserDoesNotSupportFileReaderInterface' />");
		    return;
		}

		for(i=0;i<fileCount;i++){
			fileReader[fileReader.length] = new FileReader();
		}
		
		readLocalFile(1,fileCount);
		
	}catch(e){
		alert(e);
	}
}

function readLocalFile(fileIndex,fileCount){
	if(fileIndex < fileCount){
		fileReader[fileIndex-1].readAsDataURL($("#dialogFile"+(fileIndex))[0].files[0]);
		fileReader[fileIndex-1].onload=function(e){
			fileList[fileList.length] = $("#dialogFile"+(fileIndex))[0].value;
			fileResult[fileResult.length] = this.result;
			readLocalFile(fileIndex+1,fileCount);
		}
	}else if(fileIndex == fileCount){
		fileReader[fileIndex-1].readAsDataURL($("#dialogFile"+(fileIndex))[0].files[0]);
		fileReader[fileIndex-1].onload=function(e){
			fileList[fileList.length] = $("#dialogFile"+(fileIndex))[0].value;
			fileResult[fileResult.length] = this.result;
			uploadLocalFile();
		}
	}
}
function uploadLocalFile(){

	var softId = $("#dialogModelSoft").val();
	var softName = $("#dialogModelSoft").find("option:selected").text();
	
	var modelType =  $("#dialogModelType").val();
	
	var modelId = $("#dialogModelId").val();
	var modelName = $("#dialogModelId").find("option:selected").text();
	
	var time = $("#dialogModelTime").val();
	var startTime = $("#dialogModelStartTime").val();
	var endTime = $("#dialogModelEndTime").val();
	
	var result = [];
	for(i=0;i<fileResult.length;i++){
		result.push({"fileName":fileList[i],"fileData":fileResult[i]});
	}
	var postData = {"softId":softId,"softName":softName,"modelType":modelType,"modelId":modelId,"modelName":modelName,"time":time,"startTime":startTime,"endTime":endTime,result:result};
    $.ajax({
		url:"model/local/uploadFile",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify(postData),
		processData:false,
		success:function(data){
			hideSelectDialog();
			jQuery("#modelType").val("1");
			jQuery("#modelFormId").attr("action", "page3");
			jQuery("#modelFormId").submit();	
		}
    });
}

//从本地目录读取列表
function readWindowFileList() {
    try{
    	var Message = "<spring:message code='message.pleaseSelectFolder' />"; //选择框提示信息
    	var Shell = new ActiveXObject( "Shell.Application" ); 
    	var Folder = Shell.BrowseForFolder(0,Message,0x0040,0x11);//起始目录为：我的电脑
    	//var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
    	if(Folder != null){ 
    		Folder = Folder.items(); // 返回 FolderItems 对象 
    		Folder = Folder.item(); // 返回 Folderitem 对象 
    		Folder = Folder.Path; // 返回路径
    		if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
    	}

    		
		var fso = new ActiveXObject("Scripting.FileSystemObject");
        var reader = fso.GetFolder(Folder);
        alert("dd");
        var fc = new Enumerator(reader.files);  
        for (; !fc.atEnd(); fc.moveNext()){  
            alert(fc.item());
        }
    }catch(e){
		
    }
}
</script>
</body>
</html>
