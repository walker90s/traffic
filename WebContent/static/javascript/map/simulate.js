//选中的页数
var s_page=1;

$(function(){
	var time = currenttime();
	$("#time").html(time);
	page();
});
//页数点击事件
function page_click(obj){
	$(".lookpage").each(function(){
		$(this).removeClass("active");
	});
	$(obj).addClass("active");
	var page = $(obj).children("a").text();
	s_page = page;
	lookpage(page);
}
//分页查找数据
function lookpage(page){
	var date = $(".la_ti").text();
	date = jQuery.trim(date);
	var type = "";
	if(date == MESSAGE.dataManagement){
		type = "0";
	}else{
		type = "1";
	}
	$.ajax({
		url : 'getSimPage.html',
		type : 'POST',
		data : {
			"page" : page,
			"type" : type
		},
		dataType : 'json',
		success : function(data) {
			if(data!=null){
				$(".table tr:gt(0)").html("");
				var tr = "";
				if(type==0){
					for ( var i = 0; i < data.length; i++) {
						tr += "<tr><td align='center' valign='middle'>"+data[i].id+"</td>";
						if(data[i].name!=null){
							tr += "<td align='center' valign='middle'>"+data[i].name +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].username!=null){
							tr += "<td align='center' valign='middle'>"+data[i].importUserName +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].simulateSoft!=null){
							tr += "<td align='center' valign='middle'>"+data[i].simulateSoft +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].simulateModel!=null){
							tr += "<td align='center' valign='middle'>"+data[i].simulateModel +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].simulateTime!=null){
							tr += "<td align='center' valign='middle'>"+data[i].simulateTime +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].simulateStartTime!=null){
							tr += "<td align='center' valign='middle'>"+data[i].simulateStartTime +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].simulateEndTime!=null){
							tr += "<td align='center' valign='middle'>"+data[i].simulateEndTime +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].comment!=null){
							tr += "<td align='center' valign='middle'>"+data[i].comment +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						tr += "<td align='center' valign='middle'><a href='javascript:void(0);' onclick='deleteData(this);' model='"+data[i].simulateModel+"' dataId="+data[i].id+" title='删除' class='fa fa-trash cz_an'></a></td></tr>";
					}
				}else{
					for ( var i = 0; i < data.length; i++) {
						tr += "<tr><td align='center' valign='middle'>"+data[i].id +"</td>";
						tr += "<td align='center' valign='middle'>"+data[i].loginname +"</td>";
						if(data[i].name!=null){
							tr += "<td align='center' valign='middle'>"+data[i].name +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].registeredTime!=null){
							tr += "<td align='center' valign='middle'>"+data[i].registeredTime +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].studentid!=null){
							tr += "<td align='center' valign='middle'>"+data[i].studentid +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].studentClass!=null){
							tr += "<td align='center' valign='middle'>"+data[i].studentClass +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						if(data[i].professional!=null){
							tr += "<td align='center' valign='middle'>"+data[i].professional +"</td>";
						}else{
							tr += "<td align='center' valign='middle'></td>";
						}
						tr += "<td align='center' valign='middle'><!--<a href='#' title='保存' class='fa fa-hdd-o cz_an'></a>--><a href='javascript:deleteUser("+data[i].id +");' title='删除' class='fa fa-trash cz_an'></a></td></tr>";
					}
				}
				$(".table").append(tr);
			}
		},
		error : function(req) {
			alert(req);
			alert(MESSAGE.searchData+"error");
		}
	});
};
//删除用户数据
function deleteUser(id){
	if(confirm("确认删除吗？")){
		$.ajax({
			url : 'deleteUser.html',
			type : 'POST',
			data : {
				"id" : id
			},
			dataType : 'text',
			success : function(data) {
				if (data != null && data=="ok") {
					alert(MESSAGE.userDeleteSuccess);
					//删除后加载数据
					delete_page();
				}else{
					alert(MESSAGE.userDeleteFailed);
				}
			},
			error : function(req) {
				alert(req);
				alert(MESSAGE.userDelete+"error");
			}
		});
	}
}
//删除后填充数据
function delete_page(){
	lookpage(s_page);
	var date = $(".la_ti").text();
	date = jQuery.trim(date);
	var type = "";
	if(date == MESSAGE.dataManagement){
		type = "0";
	}else{
		type = "1";
	}
	$.ajax({
		url : 'countPage.html',
		type : 'POST',
		data : {
			"type" : type
		},
		dataType : 'text',
		success : function(data) {
			if (data != null) {
				if(data==0){
					data = 1;
				}
				$(".page span").html(MESSAGE.all+data+MESSAGE.strip+"，"+MESSAGE.eachPage+"10"+MESSAGE.strip)
				$(".page span").attr("page",data);
				page();
			}
		},
		error : function(req) {
			alert(req);
			alert(MESSAGE.findTheNumberOfPages+"error");
		}
	});
}

//下一页
function nextpage(obj){
	var page = $(".page span").attr("page");
	var nextpage = $(obj).prev().children("a").text();
	$(".pager").html("");
	if(page-nextpage>5){
		var li = "<li class='previous' onclick='prevPage(this);'><a href='#'>«</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(nextpage*1+1)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(nextpage*1+2)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(nextpage*1+3)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(nextpage*1+4)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(nextpage*1+5)+"</a></li><li class='next' onclick='nextpage(this);'><a href='#'>»</a></li>";
	}else{
		var pageSize = page-nextpage;
		var li = "<li class='previous' onclick='prevPage(this);'><a href='#'>«</a></li>";
		for(var i =0; i<pageSize;i++){
			li += "<li class='lookpage' onclick='page_click(this);'><a href='#'>"+(nextpage*1+(i+1))+"</a></li>";
		}
		li += "<li class='disabled'><a href='#'>»</a></li>";
	}
	$(".pager").append(li);
}
//上一页
function prevPage(obj){
	var page = $(obj).next().children("a").text();
	$(".pager").html("");
	var li = "";
	if(page!=6){
		li = "<li class='previous' onclick='prevPage(this);'><a href='#'>«</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(page-5)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(page-4)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(page-3)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(page-2)+"</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>"+(page-1)+"</a></li><li class='next' onclick='nextpage(this);'><a href='#'>»</a></li>";
	}else{
		li = "<li class='previous disabled' onclick='prevPage(this);'><a href='#'>«</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>1</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>2</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>3</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>4</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>5</a></li><li class='next' onclick='nextpage(this);'><a href='#'>»</a></li>";
	}
	$(".pager").append(li);
}
//初始化页数
function page(){
	var page = $(".page span").attr("page");
	if(page>5){
		$(".pager").html("");
		var li = "<li class='previous disabled'><a href='#'>«</a></li><li class='lookpage active' onclick='page_click(this);'><a href='#'>1</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>2</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>3</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>4</a></li><li class='lookpage' onclick='page_click(this);'><a href='#'>5</a></li><li class='next' onclick='nextpage(this);'><a href='#'>»</a></li>";
		$(".pager").append(li);
	}else if(page==0){
		$(".pager").html("");
		var li = "<li class='previous disabled'><a href='#'>«</a></li><li class='lookpage active'><a href='#'>1</a></li><li class='next'><a href='#'>»</a></li>";
		$(".pager").append(li);
	}else{
		$(".pager").html("");
		var li = "<li class='previous disabled'><a href='#'>«</a></li>";
		for(var i =0; i<page; i++){
			if((i+1)==1){
				li += "<li class='active lookpage' onclick='page_click(this);'><a href='#'>"+(i*1+1)+"</a></li>";
			}else{
				li += "<li class='lookpage' onclick='page_click(this);'><a href='#'>"+(i*1+1)+"</a></li>";
			}
		}
		li += "<li class='disabled'><a href='#'>»</a></li>";
		$(".pager").append(li);
	}
}
//删除数据
function deleteData(obj){
	if(confirm("确认删除吗？")){
		var id = $(obj).attr("dataId");
		var type = $(obj).attr("model");
		$.ajax({
			url : 'deleteData.html',
			type : 'POST',
			data : {
				"id" : id,
				"type" : type
			},
			dataType : 'text',
			success : function(data) {
				if (data != null && data=="ok") {
					alert(MESSAGE.dataDeleteSuccess);
					delete_page();
				}else{
					alert(MESSAGE.dataDeleteFailed);
				}
			},
			error : function(req) {
				alert(req);
				alert("error");
			}
		});
	}
}
//获取系统当前时间
function currenttime(){
	var myDate = new Date();
	var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
	if(month<10){
		month = "0"+month;
	}
	var day = myDate.getDate();        //获取当前日(1-31)
	if(day<10){
		day = "0"+day;
	}
	var week = myDate.getDay();
	var weekday = "";
	switch (week) {
		case 1:
			weekday = MESSAGE.monday;
			break;
		case 2:
			weekday = MESSAGE.tuesday;
			break;
		case 3:
			weekday = MESSAGE.wednesday;
			break;
		case 4:
			weekday = MESSAGE.thursday;
			break;
		case 5:
			weekday = MESSAGE.friday;
			break;
		case 6:
			weekday = MESSAGE.saturday;
			break;
		default:
			weekday = MESSAGE.sunday;
			break;
	}
	var time = year+"-"+month+"-"+day+" "+weekday;
	return time;
}