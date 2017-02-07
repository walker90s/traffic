
$(function(){
    loadProjectList();
    //加载左侧的树
    loadModelList();
    var height = $(window).height();
    $(".ri_cn").height(height-131);
    $("#left").height(height-131);
});
/**
 * 隐藏或显示dom元素
 */
function toggle(domId){
    jQuery("#"+domId).toggle();
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



	function showMenu(){
		document.getElementById('project').style.display=='none'?document.getElementById('project').style.display='block':document.getElementById('project').style.display='none';
	}
//	function showModel(){
//		document.getElementById('chose').style.display=='none'?document.getElementById('chose').style.display='block':document.getElementById('chose').style.display='none';
//	}
//	function uploadModel(){
//		document.getElementById('add').style.display=='none'?document.getElementById('add').style.display='block':document.getElementById('add').style.display='none'
//	}
	function editModel(){
		document.getElementById('add').style.display=='none'?document.getElementById('add').style.display='block':document.getElementById('add').style.display='none'
	}
//	function showProgram(){
//		document.getElementById('program').style.display=='none'?document.getElementById('program').style.display='block':document.getElementById('program').style.display='none'
//	}
/**
 * 加载项目列表
 */
 function loadProjectList(){
	$.ajax({
		url:"queryAllList",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		//data:JSON.stringify({"fromUserId":1,"toUserId":2}),
		processData:false,
		success: function(data){
			htmlArr=[];
            if(null!=data && null!=data.projectList){
            	for(var i = 0,len=data.projectList.length; i < len; i++) {
                    var json = data.projectList[i];
                    var name = json.projectName;
                    var id = json.id;
                    var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
                    htmlArr.push(' <li><a href="javascript:void();" title="'+ name +'" style="line-height:10px;" >' + vname +'  <span title="删除" onclick="deleteProject('+id+')" class="fa_project_menu  fa-trash cz_an_project_menu"></span><span title="编辑" onclick="editProject('+id+')" class="fa_project_menu fa-list cz_an_project_menu"></span> </a></li>');
            	}
            }
            htmlArr.push('<li><a href="javascript:showMenu();" style="line-height:10px;">添加新项目</a></li>');
            $("#projectListUl").html(htmlArr.join(''));
            delete htmlArr;
            delete data.projectList;
		}
    });
};
/**
 * 加载模型列表
 */
function loadModelList(){
    $.getJSON("getModelAllJSONArray", function(jsonArray){
        var modelJsonArray = jsonArray;

        for(var i = 0; i < jsonArray.length; i++) {
            var html = "";
            var json = jsonArray[i];
            var name = json.name;
            var id = json.id;

            var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
            var li = " <li><a href='javascript:void(0);' onclick='lookModelByname(this)' title='"+ name +"'><i class='fa fa-random'></i>"+ vname +"</a></li>";
            html += li;

            
            var oldHtml = $("#model_ul_"+json.type).html();
            html = oldHtml + html;
            $("#model_ul_"+json.type).html(html);
            $("#model_ul_"+json.type).show();
        }
    });
};

/**
 * 加载年模型列表
 */
function loadYearsModelList(){
	$.ajax({
		url:"queryAllList",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		//data:JSON.stringify({"fromUserId":1,"toUserId":2}),
		processData:false,
		success: function(data){
			htmlArr=[];
            if(null!=data && null!=data.projectList){
            	for(var i = 0,len=data.projectList.length; i < len; i++) {
                    var json = data.projectList[i];
                    var name = json.projectName;
                    var id = json.id;
                    var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
                    htmlArr.push(' <li><a href="javascript:void();" title="'+ name +'" style="line-height:10px;" >' + vname +'  <span title="删除" onclick="deleteProject('+id+')" class="fa_project_menu  fa-trash cz_an_project_menu"></span><span title="编辑" onclick="editProject('+id+')" class="fa_project_menu fa-list cz_an_project_menu"></span> </a></li>');
            	}
            }
            htmlArr.push('<li><a href="javascript:editModel();" style="line-height:10px;">添加新模型</a></li>');
            $("#chooseModel").html(htmlArr.join(''));
            delete htmlArr;
            delete data.projectList;
		}
    });
};


/**
 * 删除项目
 */
function deleteProject(proId){
	$.ajax({
		url:"deleteTrafficProject",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"proId":proId}),
		processData:false,
		success:function(data){
			if(null!=data && null!=data.message_status){
				if(data.message_status==1){
					alert("删除成功!");
					loadProjectList();
					
				}else{
					alert("删除失败!");
				}
			} 
		}
	});
}
/**
 * 编辑项目
 */
function editProject(proId){
	showMenu();
	$.ajax({
		url:"editTrafficProject",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"proId":proId}),
		processData:false,
		success:function(data){
			if(null!=data && null!=data.message_status){
				if(data.message_status==1 && data.trafficProject!=null){
					$('#proName').val(data.trafficProject.projectName);
					$('#proBackground').val(data.trafficProject.proBackground);
					$('#proNeed_goal').val(data.trafficProject.need_goals);
					$('#proId').val(data.trafficProject.id);
					loadProjectList();
				}else{
					alert("编辑失败!");
				}
			} 
		}
	});
}


/**
 * 删除年模型
 */
function deleteYearsProject(proId){
	$.ajax({
		url:"deleteTrafficProject",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"proId":proId}),
		processData:false,
		success:function(data){
			if(null!=data && null!=data.message_status){
				if(data.message_status==1){
					alert("删除成功!");
					loadProjectList();
					
				}else{
					alert("删除失败!");
				}
			} 
		}
	});
}
/**
 * 编辑年模型
 */
function editYearsProject(proId){
	showMenu();
	$.ajax({
		url:"editTrafficProject",
		type:"POST",
		contentType:"application/json",
		dataType:"json",
		data:JSON.stringify({"proId":proId}),
		processData:false,
		success:function(data){
			if(null!=data && null!=data.message_status){
				if(data.message_status==1 && data.trafficProject!=null){
					$('#proName').val(data.trafficProject.projectName);
					$('#proBackground').val(data.trafficProject.proBackground);
					$('#proNeed_goal').val(data.trafficProject.need_goals);
					$('#proId').val(data.trafficProject.id);
					loadProjectList();
				}else{
					alert("编辑失败!");
				}
			} 
		}
	});
}


//根据模型显示相应数据
function lookModelByname(obj){
    var name = $(obj).text();
    $.ajax({
        url: 'getModelByname.html',
        type: 'POST',
        data:{
            "name": name
        },
        dataType:'json',
        success:function(data) {
            if(data!=null){
                for(var i=0; i<data.length; i++){
                    $("[name='area']").val(data[i].area);
                    $("[name='target']").val(data[i].target);
                    $("[name='speedData']").val(data[i].speedData);
                    $("[name='flowData']").val(data[i].flowData);
                    $("[name='modelname']").val(data[i].name);
                    $("[name='content']").val(data[i].content);
                    $("[name='modelPath']").val(data[i].modelPath);
                    $("[name='resultPath']").val(data[i].resultPath);
                    $("[name='chart']").val(data[i].chart);
                    $("[name='linklevel']").val(data[i].linklevel);
                    $("[name='crosslevel']").val(data[i].crosslevel);
                    $("#model").attr("modelid",data[i].id);
                }
            }
        },error:function(req){
            alert(req);
            alert("error");
        }
    });
}

function select_Byname(){
    var name = $("[name=modelname]").val();
    $.ajax({
        url: 'getModelByname.html',
        type: 'POST',
        data:{
            "name": name
        },
        dataType:'json',
        success:function(data) {
            if(data!=null){
                for(var i=0; i<data.length; i++){
                    $("[name='area']").val(data[i].area);
                    $("[name='target']").val(data[i].target);
                    $("[name='speedData']").val(data[i].speedData);
                    $("[name='flowData']").val(data[i].flowData);
                    $("[name='content']").val(data[i].content);
                    $("[name='modelPath']").val(data[i].modelPath);
                    $("[name='resultPath']").val(data[i].resultPath);
                    $("[name='chart']").val(data[i].chart);
                    $("[name='linklevel']").val(data[i].linklevel);
                    $("[name='crosslevel']").val(data[i].crosslevel);
                    $("#model").attr("modelid",data[i].id);
                }
            }
        },error:function(req){
            alert(req);
            alert("error");
        }
    });
}
//速度数据修改路径
function updatePath1(obj){
    var path = obj.value;
    var id = $("#model").attr("modelid");
    /*
    var path2 = [];
    path2 = path.split("\\");
    path = "";
    for(var i =0; i<path2.length-1; i++){path += path2[i]+"\\";}
    */
    $.ajax({
        url: 'updateFilePath.html',
        type: 'POST',
        data:{
            "id": id,
            "speedData": path
        },
        dataType:'text',
        success:function(data) {
            if(data!=null && data!=""){
                alert(MESSAGE.speedDataModificationSuccess);
                $("[name=speedData]").val(path);
            }else{
                alert(MESSAGE.speedDataModificationFailed);
            }
        },error:function(req){
            alert(req);
        }
    });
}
//流量数据修改路径
function updatePath2(obj){
    var path = obj.value;
    var id = $("#model").attr("modelid");
    $.ajax({
        url: 'updateFilePath.html',
        type: 'POST',
        data:{
            "id": id,
            "flowData": path
        },
        dataType:'text',
        success:function(data) {
            if(data!=null && data!=""){
                alert(MESSAGE.trafficDataModificationSuccess);
                $("[name=flowData]").val(path);
            }else{
                alert(MESSAGE.trafficDataModificationFailed);
            }
        },error:function(req){
            alert(req);
        }
    });
}
