
var coordinate = [];
function ajaxFileUpload(fileElementId,fileUrl) {
	var fileURL=$('#'+fileUrl).val();
	alert(fileElementId);
    coordinate = [];
    $.ajaxFileUpload({
    	
        url: 'uploadSHP',//用于文件上传的服务器端请求地址
        dataType:'json',
        secureuri : false,//一般设置为false
        fileElementId : fileElementId,//文件上传控件的id属性
        success: function(data, status) //服务器成功响应处理函数
        {
        	if(data!=null){
        		if(fileURL.indexOf(data[0])>=0){
        			alert("相同名文件已覆盖!");
        		}else{
        			$('#'+fileUrl).val(data[0]+";"+fileURL);
        		}
        		
        		
        	}
//        	debugger;
//            //var d = eval("(" + data +")");
//            console.log(data);
//            var zb = "";
//            for(var i = 0; i < data.length; i++){
//                if(data[i]!="?"){
//                    zb += data[i]+" ";
//                }else{
//                    zb = zb.substring(0, zb.length-1);
//                    addPolygon(zb);
//                    coordinate.push(zb);
//                    zb = "";
//                }
//            }
        },
        error : function(data, status, e)//服务器响应失败处理函数
        {
            console.log(e);
        }
    });
    return false;
}