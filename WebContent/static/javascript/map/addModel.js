/**
 * 存储项目信息
 */
function addTrafficProject(){
	var proName=$('#proName').val();
	var proBackground=$('#proBackground').val();
	var proNeed_goal=$('#proNeed_goal').val();
	var proId=$('#proId').val();

	if(null!=proName && ""!=$.trim(proName) && null!=proBackground && null!= proNeed_goal){
		if(proId!=null&&proId!=""){
			$.ajax({
				url:"updateTrafficProject",
				type:"POST",
				contentType:"application/json",
				dataType:"json",
				data:JSON.stringify({"name":proName,"background":proBackground,"need_goal":proNeed_goal,"proId":proId}),
				processData:false,
				success:function(data){
					if(null!=data && null!=data.message_status){
						if(data.proId>0){
							$("#proId").val(data.proId);
						}
						if(data.message_status==1){
							alert("保存成功!");
						}else{
							alert("保存失败!");
						}
					}
				}
			});
		}else{
				$.ajax({
					url:"getTrafficProjectJSONArray",
					type:"POST",
					contentType:"application/json",
					dataType:"json",
					data:JSON.stringify({"name":proName,"background":proBackground,"need_goal":proNeed_goal}),
					processData:false,
					success:function(data){
						if(null!=data && null!=data.message_status){
							if(data.proId>0){
								$("#proId").val(data.proId);
							}
							if(data.message_status==1){
								alert("保存成功!");
							}else{
								alert("保存失败!");
							}
						}
					}
				});
			}
		}else{
			alert("请填写有效的消息内容");
		}
	}
	/**
	* @see 获得模型种类
	* @return String
	*/
	function getModelType(){
	  var value="";
	  var radio=document.getElementsByName("model");
	  for(var i=0;i<radio.length;i++){
		if(radio[i].checked==true){
		  value=radio[i].value;
		  break;
		}
	  }
	  return value;
	}

	/**
	 * 存储模型种类
	 */
	function addTrafficProjectModel(){
		var proId = $("#proId").val();
		var type_id = getModelType();
		var road_flow = $("#flow").val();
		var speed_data = $("#speed").val();
		if(null!=proId && null!=type_id && null!= road_flow&&null!=speed_data){
			$.ajax({
				url:"getTrafficProjectModelJSONArray",
				type:"POST",
				contentType:"application/json",
				dataType:"json",
				data:JSON.stringify({"proId":proId,"type_id":type_id,"road_flow":road_flow,"speed_data":speed_data}),
				processData:false,
				success:function(data){
					if(null!=data && null!=data.message_status){
						if(data.proModelId>0){
							$("#proModelId").val(data.proModelId);
						}
						if(data.message_status==1){
							alert("保存成功!");
						}else{
							alert("保存失败!");
						}
					}
				}
			});
		}else{
			alert("请填写有效的消息内容");
		}
		//document.getElementById('detail').style.display=='none'?document.getElementById('detail').style.display='block':document.getElementById('detail').style.display='none';
	}

	/**
	* @see 获得年模型种类
	* @return String
	*/
	function getYearsModelType(){
	  var value="";
	  var radio=document.getElementsByName("yearModel");
	  for(var i=0;i<radio.length;i++){
		if(radio[i].checked==true){
		  value=radio[i].value;
		  break;
		}
	  }
	  return value;
	}
	/**
	 * 存储年模型信息
	 * 
	 */
	function addYearsModel(){
		var proModelId = $("#proModelId").val();
		var types_id = getYearsModelType();
		var modelName = $("#modelName").val();
		var modelDesc = $("#modelDesc").val();
		var gis = $("#Gis").val();
		var traffic_demand = $("#trafficMatrix").val();
		var traffic_flows = $("#roadFlow").val();
		var road_speed = $("#roadSpeed").val();
		var road_result = $("#results").val();
		var road_lookup = $("#lookup").val();
		if(null!=types_id && null!= modelName&&null!=modelDesc){
			if(proModelId!=null&&proModelId!=""){
				$.ajax({
					url:"projectModelYearType/insert",
					type:"POST",
					contentType:"application/json",
					dataType:"json",
					data:JSON.stringify({"proModelId":proModelId,"types_id":types_id,"modelName":modelName,"modelDesc":modelDesc,"gis":gis,"traffic_demand":traffic_demand,"traffic_flows":traffic_flows,"road_speed":road_speed,"road_result":road_result,"road_lookup":road_lookup}),
					processData:false,
					success:function(data){
						if(null!=data && null!=data.message_status){
							if(data.message_status==1){
								alert("修改成功!");
							}else{
								alert("修改失败!");
							}
						}
					}
				});
			}else{
				$.ajax({
					url:"projectModelYearType/insert",
					type:"POST",
					contentType:"application/json",
					dataType:"json",
					data:JSON.stringify({"proModelId":proModelId,"types_id":types_id,"modelName":modelName,"modelDesc":modelDesc,"gis":gis,"traffic_demand":traffic_demand,"traffic_flows":traffic_flows,"road_speed":road_speed,"road_result":road_result,"road_lookup":road_lookup}),
					processData:false,
					success:function(data){
						if(null!=data && null!=data.message_status){
							if(data.message_status==1){
								alert("保存成功!");
							}else{
								alert("保存失败!");
							}
						}
					}
				});
			}
			
		}else{
			alert("请填写有效的消息内容");
		}
		//document.getElementById('detail').style.display=='none'?document.getElementById('detail').style.display='block':document.getElementById('detail').style.display='none';
	}
	