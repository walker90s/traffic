// JavaScript Document

var winWidth = 0;
var winHeight = 0;
function findDimensions() //函数：获取尺寸
{
//获取窗口宽度
if (window.innerWidth)
winWidth = window.innerWidth;
else if ((document.body) && (document.body.clientWidth))
winWidth = document.body.clientWidth;
//获取窗口高度
if (window.innerHeight)
winHeight = window.innerHeight;
else if ((document.body) && (document.body.clientHeight))
winHeight = document.body.clientHeight;
//通过深入Document内部对body进行检测，获取窗口大小
if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth)
{
winHeight = document.documentElement.clientHeight;
winWidth = document.documentElement.clientWidth;
}
}
function autoHeight(){
    findDimensions(); 
	var autoHeight= winHeight-81;
	$("#treeMenu").css("height",(autoHeight-5-60)+"px");
	$("#mapPanel").css("height",(autoHeight-5-60)+"px");
	$("#ifm").css("height",(autoHeight-5-60)+"px");
	$("#map").css("height",(autoHeight-5-80)+"px");
}
function openMdiPage(url,obj){
   $("#ifm").attr("src",url);
   $("#mapPanel").css("display","none");
   $("#ifm").css("display","block");
   $(".popMenu").css("display","none"); 
   $(".base").css("display","none");

   selectDefault(obj);
   jQuery('#LegendWrapper').html('');
}
function closeIfm(){
	$("#mapPanel").css("display","block");
	$("#ifm").css("display","none");
	$(".base").css("display","block");
}
function shrinkMenu(o){
   if($("#treeMenu").css("display")=="block"){
      o.src="images/center_right.gif";
	  $("#treeMenu").css("display","none");
	  $("#treeMenu")[0].parentNode.style.width="1px";
   }
   else{
      o.src="images/center_left.gif";
	  $("#treeMenu").css("display","block");
	  $("#treeMenu")[0].parentNode.style.width="181px";
   }
}
function dispEvent(o){
  if(document.all){
   o.click();
  }
  else{
   var evt =   document.createEvent("MouseEvents");    
   evt.initEvent("click", true, true);    
   o.dispatchEvent(evt);   
   var evt1 =   document.createEvent("MouseEvents");    
   evt1.initEvent("mousedown", true, true);  
   o.dispatchEvent(evt1); 
  }
}
function btnClick(id){
  /*
  if(id=='t1'){
	   if($("#ifm").css("display")=="block"){
		   if($("#subTitleDiv").css("display")=="block"){

		   }
		   else{
		      $("#subTitleDiv").css("display","block");
		   }
	   }else{
		   if($("#subTitleDiv").css("display")=="block"){
		      $("#subTitleDiv").css("display","none");
		   }else{
		      $("#subTitleDiv").css("display","block");
		   }
	   }
  }else{
	  var div = $("#"+id)[0];
	  var aTag = div.getElementsByTagName("a");
	  dispEvent(aTag[0]);
  }
   */
}
var offsetW=0,offsetH=0,zIndex=11000;
function popupMenu(id,obj){ 
   $("#mapPanel").css("display","block");
   $("#ifm").css("display","none");
   $(".base").css("display","block");
   var o= document.getElementById(id);
   o.style.display="block";
   if(offsetW==0){
      offsetH=130;
      offsetW=(document.body.offsetWidth-o.offsetWidth-4);
   }
   if((offsetH+o.offsetHeight)>winHeight)
   {
     offsetH = 130;
   }

   if('radartl'==id){
	   o.style.top="400px";
	   o.style.left="180px";
   }else if('weathertl'==id){
	   o.style.top="400px";
	   o.style.left="300px";
   }else if('waterloggingDetail'==id){
	   o.style.top="110px";
	   o.style.left="280px";
   }else{
	   offsetH = 130;
	   o.style.top=offsetH+"px";
	   o.style.left=offsetW+"px";
   }
   o.style.zIndex=zIndex;  
   zIndex=zIndex+1;
   offsetH=offsetH+34; 
   
   if(id =="nlgdzz"){
		centerPosInit = new OpenLayers.LonLat(117.21, 39.13);
		centerPosInit.transform(hykj.proj, hykj.aliasproj);
		hykj.map.setCenter(centerPosInit , hykj.nlMaplevel);
		delete centerPosInit;
		hykj.nlFlag=true;
   }else{
		if(hykj.nlFlag ){
			hykj.nlFlag=false;
			centerPosInit = new OpenLayers.LonLat(117.1, 39.4);
			centerPosInit.transform(hykj.proj, hykj.aliasproj);
			hykj.map.setCenter(centerPosInit , hykj.maplevel);
			delete centerPosInit;
		}
   }
   
   
   //alert(id);
   if(id == "pyl"){
      //localAws('precipitation1hr');
	   hykj.sk.aws.addMarkerFeatureByAjax();
   }else if(id == "pylCount"){
	   hykj.setTypeFeatureChecked("zdz",false);
	   hykj.sk.aws.dealCountAwsPost();
   }else if(id == "pqpe"){
	   hykj.setTypeFeatureChecked("qpe",true);
      //localQpe('-1');
   }else if(id == "pqpf"){
	   hykj.setTypeFeatureChecked("qpf",true);
      //localQpf('-1');
   }else if(id == "psd"){
	   hykj.setTypeFeatureChecked("lighting",true);
      //localLightning('-1');
   }else if(id == "pzh"){
	   hykj.setTypeFeatureChecked("ws",true);
      hykj.sk.serverWeather.addMarkerByAjax();
   }else if(id =="gdzz"){
	   rValue=retHourFunc();
	   $("#gdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.grid.setTime(val);
	   delete rValue;
	   gridData();
   }else if(id =="nlgdzz"){
	   rValue=retNlHourFunc();
	   $("#nlgdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.nlgrid.setTime(val);
	   delete rValue;
	   minuteValue=retMinuteFunc();
	   $("#nlgdzz-minute").attr("value",minuteValue);
	   var minuteVal = {};
	   minuteVal[minuteValue] = true;
	   hykj.produced.nlgrid.setMinute(minuteVal);
	   delete minuteValue;
	   nlgridData();
   }else if(id =="dzybgdzz"){
	   rValue=retHourFunc();
	   $("#dzybgdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.gridRevise.setTime(val);
	   delete rValue;
	   gridReviseData();
   }
   else if(id == "zdzz"){
	   rValue=retHourFunc();
	   $("#zdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.station.setTime(val);
	   delete rValue;
	   stationData();
   }else if(id =="ljgdzz"){
	   rValue=retHourFunc();
	   $("#ljgdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.qpfgrid.setTime(val);
	   delete rValue;
	   qpfgridData();
   }else if(id == "ljzdzz"){
	   rValue=retHourFunc();
	   $("#ljzdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.qpfstation.setTime(val);
	   delete rValue;
	   qpfstationData();
   }else if(id =="ljThreeDayGdzz"){
	   rValue=retThreeDayHourFunc();
	   $("#ljThreeDayGdzz-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.qpfThreeDayGrid.setTime(val);
	   delete rValue;
	   qpfThreeDayGridData();
   }else if(id=="pqdl"){
	   hykj.setTypeFeatureChecked("qdl",true);
	   hykj.qdlyb.getQdlContourLineWKT();
   }else if(id=="pyb"){
	   hykj.setTypeFeatureChecked("wll",true);
	   hykj.wllyb.getWllContourLineWKT();
   }else if(id=="disasterConversionSign"){
	   hykj.setTypeFeatureChecked("zdz",false);
	    nowDate = new Date();
		nowHour = nowDate.getHours();
		retValue = null;
		if(nowHour < 10 ){
			retValue = "a0" + nowHour;
		}else{
			retValue = "a" + nowHour;
		}
		disasterDate = new Date();
	   $("#disasterBeginDate-datepicker").attr("value",disasterDate.format('%Y-%m-%d')).change();
	   $('#disasterBeginHour').attr("value", retValue).change();
	   nowDate.setTime(nowDate.getTime()+12*60*60*1000);
	   disasterDate.setTime(nowDate.getTime()+12*60*60*1000);
	   $("#disasterEndDate-datepicker").attr("value",disasterDate.format('%Y-%m-%d')).change();
	   nowHour = nowDate.getHours();
		if(nowHour < 10 ){
			retValue = "a0" + nowHour;
		}else{
			retValue = "a" + nowHour;
		}
	   $('#disasterEndHour').attr("value", retValue).change();
	   delete nowDate;
	   delete nowHour;
	   delete retValue;
	   delete disasterDate;
   }
   selectDefault(obj);
}
function disasterValidTimeFunc(validTime){
   disasterBdate=$("#disasterBeginDate-datepicker").val();
   disasterBHour=$('#disasterBeginHour').val().substring(1);
   if(parseInt(disasterBHour)<10){
	   disasterBHour="0"+disasterBHour;
   }
   bDateStr=disasterBdate+" "+disasterBHour+":00:00";
   bDateStr=new Date(bDateStr.replace(/-/g,"/"));
   disasterDate = new Date(bDateStr);
   disasterDate.setTime(disasterDate.getTime()+validTime*60*60*1000);
   $("#disasterEndDate-datepicker").attr("value",disasterDate.format('%Y-%m-%d')).change();
   nowHour = disasterDate.getHours();
	if(nowHour < 10 ){
		retValue = "a0" + nowHour;
	}else{
		retValue = "a" + nowHour;
	}
   $('#disasterEndHour').attr("value", retValue).change();
   delete disasterBdate;
   delete disasterBHour;
   delete bDateStr;
   delete nowHour;
   delete retValue;
   delete disasterDate;
}
function retHourFunc(){
	try{
		retValue=null;
		nowDate = new Date();
		nowHour = nowDate.getHours();
		if( (nowHour>=0 && nowHour<2) ){
			retValue="a02";
		}else if(nowHour>=2 && nowHour<5){
			retValue="a05";
		}else if(nowHour>=5 && nowHour<8){
			retValue="a08";
		}else if(nowHour>=8 && nowHour<11){
			retValue="a11";
		}else if(nowHour>=11 && nowHour<14){
			retValue="a14";
		}else if(nowHour>=14 && nowHour<17){
			retValue="a17";
		}else if(nowHour>=17 && nowHour<20){
			retValue="a20";
		}else if(nowHour>=20 && nowHour<=23){
			retValue="a23";
		}
		return retValue;
		delete nowDate;
		delete nowHour;
	}finally{
		delete retValue;
	}
}
function retNlHourFunc(){
	try{
		retValue=null;
		nowDate = new Date();
		nowHour = nowDate.getHours();
		if( (nowHour>=0 && nowHour<10) ){
			retValue="a0"+nowHour;
		}else if(nowHour>=10 && nowHour<=23){
			retValue="a"+nowHour;
		}
		return retValue;
		delete nowDate;
		delete nowHour;
	}finally{
		delete retValue;
	}
}
function retMinuteFunc(){
	try{
		retValue=null;
		nowDate = new Date();
		nowMinute = nowDate.getMinutes();
		if( (nowMinute>=0 && nowMinute<10) ){
			retValue="a10";
		}else if(nowMinute>=10 && nowMinute<20){
			retValue="a20";
		}else if(nowMinute>=20 && nowMinute<30){
			retValue="a30";
		}else if(nowMinute>=30 && nowMinute<40){
			retValue="a40";
		}else if(nowMinute>=40 && nowMinute<50){
			retValue="a50";
		}else if(nowMinute>=50 && nowMinute<60){
			retValue="a00";
		}
		return retValue;
		delete nowDate;
		delete nowMinute;
	}finally{
		delete retValue;
	}
}
function retThreeDayHourFunc(){
	try{
		retValue=null;
		nowDate = new Date();
		nowHour = nowDate.getHours();
		if( (nowHour>=0 && nowHour<8) ){
			retValue="a08";
		}else if(nowHour>=8 && nowHour<20){
			retValue="a20";
		}else if(nowHour>=20 && nowHour<=23){
			retValue="a08";
		}
		return retValue;
		delete nowDate;
		delete nowHour;
	}finally{
		delete retValue;
	}
}
function dealCountValidTimeFuncBak(validTime){
	var newDate = new Date();
	var year=newDate.getFullYear();
	var month=newDate.getMonth()+1;
	var date=newDate.getDate();
	var newDateStr=year+"-"+(month<10? ("0"+month):month)+"-"+(date<10? ("0"+date):date);
	$('#zdz-count-end-datepicker').val(newDateStr);
	hykj.sk.aws.setCountEndDate(date);
	var hour = newDate.getHours() < 10 ? '0' + newDate.getHours() : '' + newDate.getHours();
	$('#zdz-count-end-time').val(hour);
	hykj.sk.aws.setCountEndHour(hour);
	var minute="00"; 
	if(newDate.getMinutes()<10){
		minute="05"; 
	}else if(newDate.getMinutes()<15){
		minute="10"; 
	}else if(newDate.getMinutes()<20){
		minute="15"; 
	}else if(newDate.getMinutes()<25){
		minute="20"; 
	}else if(newDate.getMinutes()<30){
		minute="25"; 
	}else if(newDate.getMinutes()<35){
		minute="30"; 
	}else if(newDate.getMinutes()<40){
		minute="35"; 
	}else if(newDate.getMinutes()<45){
		minute="40"; 
	}else if(newDate.getMinutes()<50){
		minute="45"; 
	}else if(newDate.getMinutes()<55){
		minute="50"; 
	}else if(newDate.getMinutes()>=55){
		minute="55"; 
	}
	$('#zdz-count-end-minute').val(minute);
	hykj.sk.aws.setCountEndMinute(minute);
	newDate.setTime(newDate.getTime()-validTime*60*60*1000);
	//newDate.setDate(newDate.getDate()-1);
	var jyear=newDate.getFullYear();
	var jmonth=newDate.getMonth()+1;
	var jdate=newDate.getDate();
	var jnewDateStr=jyear+"-"+(jmonth<10? ("0"+jmonth):jmonth)+"-"+(jdate<10? ("0"+jdate):jdate);
	$('#zdz-count-begin-datepicker').val(jnewDateStr);
	hykj.sk.aws.setCountBeginDate(jnewDateStr);
	var jhour = newDate.getHours() < 10 ? '0' + newDate.getHours() : '' + newDate.getHours();
	$('#zdz-count-begin-time').val(jhour);
	hykj.sk.aws.setCountBeginHour(jhour);
	var jminute="00"; 
	if(newDate.getMinutes()<10){
		jminute="05"; 
	}else if(newDate.getMinutes()<15){
		jminute="10"; 
	}else if(newDate.getMinutes()<20){
		jminute="15"; 
	}else if(newDate.getMinutes()<25){
		jminute="20"; 
	}else if(newDate.getMinutes()<30){
		jminute="25"; 
	}else if(newDate.getMinutes()<35){
		jminute="30"; 
	}else if(newDate.getMinutes()<40){
		jminute="35"; 
	}else if(newDate.getMinutes()<45){
		jminute="40"; 
	}else if(newDate.getMinutes()<50){
		jminute="45"; 
	}else if(newDate.getMinutes()<55){
		jminute="50"; 
	}else if(newDate.getMinutes()>=55){
		jminute="55"; 
	}
	$('#zdz-count-begin-minute').val(jminute);
	hykj.sk.aws.setCountBeginMinute(jminute);
	hykj.sk.aws.dealCountAwsPost();
}
function popupTableMenu(id,obj){ 
	if(null!=$("#mapPanel")){
		$("#mapPanel").css("display","block");
	}
	if(null!=$("#ifm")){
		$("#ifm").css("display","none");
	}
	if(null!=$(".base")){
		$(".base").css("display","block");
	}

    var o= document.getElementById(id);
    if(null!=o){
	   o.style.display="block";
	   if(offsetW==0){
	      offsetH=130;
	      offsetW=(document.body.offsetWidth-o.offsetWidth-4);
	   }
	   if((offsetH+o.offsetHeight)>winHeight)
	   {
	     offsetH = 130;
	   }
	   //alert(offsetH+","+offsetW);
	   //o.style.top=offsetH+"px";
	   //o.style.left=offsetW+"px";
		o.style.top="200px";
		o.style.left="240px";
	   o.style.zIndex=zIndex;  
	   zIndex=zIndex+1;
	   offsetH=offsetH+34; 
    }
   if(id=="bgdz"){
	   rValue=retHourFunc();
	   $("#zdzz-table-time").attr("value",rValue);
	   var val = {};
	   val[rValue] = true;
	   hykj.produced.station.setTime(val);
	   delete rValue;
   }
   else if(id == "pyl"){
      localAws('precipitation1hr');
   }else if(id == "pqpe"){
      localQpe('-1');
   }else if(id == "pqpf"){
      localQpf('-1');
   }else if(id == "psd"){
      localLightning('-1');
   }else if(id == "pzh"){
      hykj.sk.serverWeather.addMarkerByAjax();
   }
   
   selectDefault(obj);

   
}

function popupMenuAdjust(id,obj){ 
	   $("#mapPanel").css("display","block");
	   $("#ifm").css("display","none");
	   $(".base").css("display","block");
	   var o= document.getElementById(id);
	   o.style.display="block";
	   if(offsetW==0){
	      offsetH=130;
	      offsetW=(document.body.offsetWidth-o.offsetWidth-4);
	   }
	   if((offsetH+o.offsetHeight)>winHeight)
	   {
	     offsetH = 130;
	   }
	   //alert(offsetH+","+offsetW);
	   //o.style.top=offsetH+"px";
	   //o.style.left=offsetW+"px";
	   o.style.top="200px";
	   o.style.left="240px";
	   o.style.zIndex=zIndex;  
	   zIndex=zIndex+1;
	   offsetH=offsetH+34; 
	   
	   //alert(id);
	   if(null!=hykj.lon && null!=hykj.lat){
		   o.style.top= hykj.lat + "px";
		   o.style.left= hykj.lon + "px";
	   }
	   selectDefault(obj);

	}

function setCenter(id){
  var o= document.getElementById(id);
  o.style.display="block";
  o.style.top = (document.body.offsetHeight-o.offsetHeight)/2+"px";
  o.style.left = (document.body.offsetWidth-o.offsetWidth)/2+"px";
  
}
function closeMenu(id){
   $("#"+id).css("display","none");
   if("gdzz"==id){
	   jQuery('#LegendWeatherWrapper').html('');
	   hykj.setTypeFeatureChecked('gdzz',false);
   }else if("zdzz"==id){
	   hykj.setTypeFeatureChecked('zdzz',false);
   }else if("ljgdzz"==id){
	   hykj.setTypeFeatureChecked('ljgdzz',false);
   }else if("ljzdzz"==id){
	   hykj.setTypeFeatureChecked('ljzdzz',false);
   }

}

function showLegend(id){
	jQuery("#"+id).css("display","inline");
}

function closeTable(id){
   var parentE = $("#"+id)[0].parentNode;
   if($("#"+id).css("display")=="none"){
      $("#"+id).css("display","block");
      parentE.style.borderBottom = "0px";
   }
   else{
      $("#"+id).css("display","none");
	  parentE.style.height="1px";
      parentE.style.borderBottom = "1px solid white";
   }
}
function tab(a,b,c) 
{ 
  for(i=1;i<=b;i++){ 
   if(c==i){ 
    document.getElementById(a+"_mo_"+i).style.display = "block";  // 显示模块内容
    document.getElementById(a+"_to_"+i).className = "no";   // 改变菜单为选中样式
	var o = document.getElementById(a+"_mo_"+i).getElementsByTagName("a");
	if(o.length>0)
	dispEvent(o[0]); 
   } 
   else{ 
    document.getElementById(a+"_mo_"+i).style.display = "none"; // 隐藏没有选择的模块
    document.getElementById(a+"_to_"+i).className = "q";  // 清空没有选择的菜单样式
   } 
  } 
}
function closeShortTab(o){
   var tabm_mo_1Div=$("#tabm_mo_1").is(":hidden");;
   if(tabm_mo_1Div){
	   $("#tabm_mo_1").css("display","block");
   }
   if($("#fxb").css("display")=="block"){
      $("#fxb").css("display","none");
	  o.src="images/down.gif";
   }
   else{
      $("#fxb").css("display","block");
	  o.src="images/up.gif";
   }
}
var preObj=null;
function setCurrentClick(){
   var es = $("#treeMenu");
   var as = es[0].getElementsByTagName("a");

   for(var k=0;k<as.length;k++)
   {
      as[k].onmouseover = function(){
         if(this.className!="select")
	     this.style.color="orange";
	  };
      as[k].onmouseout = function(){
	     if(this.className!="select")
	     this.style.color="black";
	  };
	  as[k].onmousedown = function(){ 
	     if(preObj==null){
	       preObj = this;
		 }
		 if(preObj!=this){
		   preObj.style.color="black";
		   preObj.className="";
		   preObj=this;
		 }
		 this.style.color="red";
	     this.className="select";
	  };
   }
}
function selectDefault(obj){
   if(preObj==null){
	       preObj = obj;
   }
   if(preObj!=obj){
	 preObj.style.color="black";
	 preObj.className="";
	 preObj=obj;
   }
   if(null!=obj){
	   obj.style.color="red";
	   obj.className="select";
   }

}
function imgMouseEvent(){
   var imgs = $("img");
   for(var k=0;k<imgs.length;k++)
   {
      imgs[k].onmouseover = function(){
	     this.style.backgroundColor=''; 
	  };
	  imgs[k].onmouseout = function(){
	     this.style.backgroundColor=''; 
	  };
   }
}
function closeLi(className)
{
   var lis = document.getElementsByClassName(className);
   if(null!=lis){
	   for(var k=0;k<lis.length;k++)
	   {
	      if(lis[k].style.display == "none")
	        lis[k].style.display = "block";
		  else
		    lis[k].style.display = "none";
	   }
   }
}
function closeIeLi(id)
{

	   for(var k=0;k<10;k++)
	   {
		  var ids=document.getElementById((id+k));
		  if(null!=ids){
			  if(ids.style.display == "none"){
				  ids.style.display = "block";
				  ids.style.backgroundImage='url(../images/left_li_title2.gif)';
			  }else
				  ids.style.display = "none";
		  }else{
			  break;
		  }

	   }
}

	/*
    var intX;
    var intY;
    var blnDrag = false; //鼠标是否已经按下
    
    //鼠标按下
    function MouseDown(obj) {
       blnDrag = true;
       intX = event.clientX - obj.parentNode.parentNode.style.pixelLeft;
       intY = event.clientY - obj.parentNode.parentNode.style.pixelTop;
    }
    
    //鼠标拖动
     function DragDiv(obj) {
       if (!blnDrag) {
             return false; 
       }
       else {
          obj.parentNode.parentNode.style.pixelLeft = event.clientX - intX;
          obj.parentNode.parentNode.style.pixelTop = event.clientY - intY; 
       }
     } 
     //鼠标松开时
     function mouseUp() { 
         blnDrag = false;
     }
     
     function mouseOver(obj) {
        obj.style.cursor = "hand"; 
     }
     */
     /*
    var intX;
    var intY;
    var blnDrag = false; //鼠标是否已经按下
    
    //鼠标按下
    function MouseDown(obj) {
       blnDrag = true;
       intX = event.clientX - obj.parentNode.style.pixelLeft;
       intY = event.clientY - obj.parentNode.style.pixelTop;
    }
    
    //鼠标拖动
     function DragDiv(obj) {
       if (!blnDrag) {
             return false; 
       }
       else {
          obj.parentNode.style.pixelLeft = event.clientX - intX;
          obj.parentNode.style.pixelTop = event.clientY - intY; 
       }
     } 
     //鼠标松开时
     function mouseUp() { 
         blnDrag = false;
     }
     
     function mouseOver(obj) {
        obj.style.cursor = "hand"; 
     }
*/

var intX;
var intY;
var blnDrag = false; //鼠标是否已经按下

//鼠标按下
function MouseDown(obj) {
   blnDrag = true;
   intX = getEvent(event).clientX - obj.parentNode.style.pixelLeft;
   intY = getEvent(event).clientY - obj.parentNode.style.pixelTop;
}

//鼠标拖动
 function DragDiv(obj) {
   if (!blnDrag) {
         return false; 
   }
   else {
	  var e = getEvent(event);
	  var x1 = obj.getBoundingClientRect().left;
	  var x2 = x1 + obj.offsetWidth;
	  var y1 = obj.getBoundingClientRect().top;
	  var y2 = y1 + obj.offsetHeight;
	  if(y1<0)
	  {
		  obj.parentNode.style.pixelTop = 1;
	  }
	  else{
		  obj.parentNode.style.pixelLeft = getEvent(event).clientX - intX;
          obj.parentNode.style.pixelTop = getEvent(event).clientY - intY; 
	  }
   }
 } 
 //鼠标松开时
 function mouseUp() { 
     blnDrag = false;
 }
 
 function mouseOut(obj) {
	 blnDrag = false;
	 var y1 = obj.getBoundingClientRect().top;
	     if(y1<0)
	     {
		   obj.parentNode.style.pixelTop = 1;
	     }
 }
 
 function mouseOver(obj) {
    obj.style.cursor = "hand"; 
 }
 
 //辅助方法，处理IE和FF不同的事件模型 
 function getEvent(e){ 
    if(typeof e == 'undefined'){ 
            e = window.event; 
    } 
    //alert(e.x?e.x : e.layerX); 
    if(typeof e.x == 'undefined'){ 
            e.x = e.layerX; 
    } 
    if(typeof e.y == 'undefined'){ 
            e.y = e.layerY; 
    } 
    return e; 
 }
 var MoveDiv = function(){};
 MoveDiv.Move=function(id,id2)
 {
	 document.onselectstart = function()
     {
    	return false;
     };
     var o=document.getElementById(id);
     var o2=document.getElementById(id2);
     
     o.onselectstart = function()
     {
    	return false;
     };
     o2.onselectstart = function()
     {
    	return false;
     };

     o2.onmousedown = function(e) {
         e = e||window.event;
         var x=e.layerX||e.offsetX;
         var y=e.layerY||e.offsetY;
         x=x-document.body.scrollLeft;
         y=y-document.body.scrollTop;

         o2.onmouseup=function()
         {
        	 var b=o.getElementsByTagName('input');
        	 if(b!=null && b.length>=1){
            	 b[0].disabled = false;
        	 }

        	 document.onmousemove=null;
         };
         o.onmouseup=function()
         {
        	 var b=o.getElementsByTagName('input');
        	 if(b!=null && b.length>=1){
            	 b[0].disabled = false;
        	 }

        	 document.onmousemove=null;
         };
         document.onmousemove = function(e)
         {
             e=e||window.event;
             o.style.left=(e.clientX-x)+"px";
             o.style.top=(e.clientY-y)+"px";
        	 var b=o.getElementsByTagName('input');
        	 if(b!=null && b.length>=1){
            	 b[0].disabled = true;
            	 b[0].style.background="white";
            	 b[0].style.border='1px solid #7f9db9';
        	 }
         };

     };
     
 };
/*
 MoveDiv.Move=function(id,id2)
 {
     var o=document.getElementById(id);
     var o2=document.getElementById(id2);
     o2.onselectstart = function()
     {
         return(false);
     };

     o2.onmousedown = function(e) {
         e = e||window.event;
         var x=e.layerX||e.offsetX;
         var y=e.layerY||e.offsetY;
         x=x-document.body.scrollLeft;
         y=y-document.body.scrollTop;

         document.onmousemove = function(e)
         {
             e=e||window.event;
             o.style.left=(e.clientX-x)+"px";
             o.style.top=(e.clientY-y)+"px";
         };

         o2.onmouseup=function()
         {
             document.onmousemove=null;
         };
         
     };
 };
*/
 /*
 MoveDiv.Move=function(id,id2)
 {
     var o=document.getElementById(id);
     var o2=document.getElementById(id2);
     o.onselectstart = function()
     {
    	return false;
     };
     o2.onselectstart = function()
     {
    	return false;
     };
     o2.onmousedown = function(e) {
         e = e||window.event;
         var x=e.layerX||e.offsetX;
         var y=e.layerY||e.offsetY;
         x=x-document.body.scrollLeft;
         y=y-document.body.scrollTop;

         document.onmousemove = function(e)
         {
             e=e||window.event;
             o.style.left=(e.clientX-x)+"px";
             o.style.top=(e.clientY-y)+"px";
        	 var b=o.getElementsByTagName('input');
        	 if(b!=null && b.length>=1){
            	 b[0].disabled = true;
            	 b[0].style.background="white";
            	 b[0].style.border='1px solid #7f9db9';
        	 }
         };
         o2.onmouseup=function()
         {
        	 var b=o.getElementsByTagName('input');
        	 if(b!=null && b.length>=1){
            	 b[0].disabled = false;
        	 }
        	 document.onmousemove=null;
         };
     };
 };
 */ 