
var PT={};

PT.ProIndex={
	    /**
	     * 加载项目列表
	     */
	    loadProjectList : function(){
	    	$.ajax({
				url:"queryAllList",
				type:"POST",
				contentType:"application/json",
				dataType:"json",
				//data:JSON.stringify({"fromUserId":1,"toUserId":2}),
				processData:false,
				success: function(data){
		            if(null!=data && null!=data.projectList){
		            	htmlArr=[];
		            	for(var i = 0,len=data.projectList.length; i < len; i++) {
		                    var json = data.projectList[i];
		                    var name = json.projectName;
		                    var id = json.id;

		                    var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
		                    htmlArr.push(' <li><a href="javascript:PT.ProIndex.changeProjectModel('+ id +',\''+ name +'\');" title="'+ name +'" style="line-height:0px;" >' + vname +'</a></li>');

		            	}
	                    $("#projectListUl").html(htmlArr.join(''));
	                    delete htmlArr;
	                    delete data.projectList;
		            }else{
		            	$("#projectListUl").html('');
		            }
				}
	        });
	    },
	    changeProjectModel:function(projectId,projectName){
	    	if(null!=projectId && null!=projectName){
	    		if(projectId==1){
	                $('#gsDiv').css('display','none');
	                $('#wjDiv').css('display','');
	                window.frames['mapContainer'].TT.Map.setMapCenter(116.4718, 39.99191,13);
	    		}else if(projectId>=2){
	    			$.ajax({
	    				url:"projectModelYearType/queryAllList",
	    				type:"POST",
	    				contentType:"application/json",
	    				dataType:"json",
	    				data:JSON.stringify({"projectId":projectId}),
	    				processData:false,
	    				success: function(data){
	    	                $('#gsDiv').css('display','');
	    	                $('#wjDiv').css('display','none');
	    		            if(null!=data && (null!=data.macroModelYearTypeList || null!=data.microModelYearTypeList)){
	    		            	$('#gsComparaDiv').css('display','');
	    		            }else{
	    		            	$('#gsComparaDiv').css('display','none');
	    		            }
	    	                if(null!=data && null!=data.macroModelYearTypeList){
	    		            	$('#gsMacroDiv').css('display','');
	    		            	htmlArr=[];
	    		            	for(var i = 0,len=data.macroModelYearTypeList.length; i < len; i++) {
	    		                    var json = data.macroModelYearTypeList[i];
	    		                    var name = json.model_name;
	    		                    //var id = json.id;
	    		                    var id=json.model_id;
	    		                    var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
//	    		                    htmlArr.push(' <li><a href="javascript:PT.ProIndex.changeProjectModel('+ id +',\''+ name +'\');" style="line-height:0px;" >' + vname +'</a></li>');
	    			                htmlArr.push('<li"><a href="javascript:PT.ProIndex.showMacroResultSubMenu('+ id +');" title="'+ name +'"  style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> '+vname+'</a></li>');
	    		            	}
	    	                    $("#macroUl").html(htmlArr.join(''));
	    	                    delete htmlArr;
	    	                    delete data.macroModelYearTypeList;
	    		            }else{
	    		            	$('#gsMacroDiv').css('display','none');
	    		            }
	    		            if(null!=data && null!=data.microModelYearTypeList){
	    		            	$('#gsMicroDiv').css('display','');
	    		            	htmlArr=[];
	    		            	for(var i = 0,len=data.microModelYearTypeList.length; i < len; i++) {
	    		                    var json = data.microModelYearTypeList[i];
	    		                    var name = json.model_name;
	    		                    var id = json.id;

	    		                    var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
//	    		                    htmlArr.push(' <li><a href="javascript:PT.ProIndex.changeProjectModel('+ id +',\''+ name +'\');" style="line-height:0px;" >' + vname +'</a></li>');
	    			                htmlArr.push('<li"><a href="javascript:PT.ProIndex.showMicroResultSubMenu('+ id +');" title="'+ name +'"  style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> '+vname+'</a></li>');
	    		            	}
	    	                    $("#microUl").html(htmlArr.join(''));
	    	                    delete htmlArr;
	    	                    delete data.microModelYearTypeList;
	    		            }else{
	    		            	$('#gsMicroDiv').css('display','none');
	    		            }

	    				}
	    	        });

	                htmlArr=[];

//	                htmlArr.push('<ol><a href="javascript:TT.Index.showObserveFlowLayer('+ projectId +', 1 );" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测流量</a></ol>');
//	                $('#macroUl').html(htmlArr.join(''));
//	                delete htmlArr;
//	                htmlArr=[];
//	                htmlArr.push('<li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0);" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> '+projectName+'</a></li>');
//	                htmlArr.push('<ol><a href="javascript:TT.Index.showObserveFlowLayer('+ projectId +', 3 );" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测流量</a></ol>');
//	                $('#microUl').html(htmlArr.join(''));
//	                delete htmlArr;
	                window.frames['mapContainer'].TT.Map.setMapCenter(-117.851521,33.849019,10);
	    		}
	    	}
	    },
	    showMacroResultSubMenu:function(modelId){
	    	$('#subMenu_gs_micro_div').css('display','none');
			$('#subMenu_gs_macro_div').toggle();
			$('#subMenu_gs_macro_div .gsButton2').each(function(){  
				var idName=this.id;
				var name=this.name;
				$('#'+idName).unbind('click');
				$('#'+idName).bind('click',function(){
					PT.ProIndex.clearResultSubMenuColor(idName,name,'subMenu_gs_macro_div');
					$('#'+idName).css('background-color','red');
				});
			});
			$('#queryMacroResultId').unbind('click');
			$('#queryMacroResultId').bind('click',function(){
				PT.ProIndex.queryMacroResultSubMenu(modelId);
			});
	    },
	    clearResultSubMenuColor:function(idName,nameStr,divId){
	    	$('#'+divId).find('button[name='+nameStr+']').each(function(){
				if(idName==this.id){
					$('#'+this.id).css('background-color','red');
					if("macroModelYearType"==nameStr){
						PT.ProIndex.macroModelYearType=this.value;
					}else if("macroResultType"==nameStr){
						PT.ProIndex.macroResultType=this.value;
					}else if("macroResultTime"==nameStr){
						PT.ProIndex.macroResultTime=this.value;
					}else if("microModelYearType"==nameStr){
						PT.ProIndex.microModelYearType=this.value;
					}else if("microResultType"==nameStr){
						PT.ProIndex.microResultType=this.value;
					}else if("microResultTime"==nameStr){
						PT.ProIndex.microResultTime=this.value;
					}
				}else{
					$('#'+this.id).css('background-color','#FFD700');
				}
			});
	    },
	    showMicroResultSubMenu:function(modelId){
			$('#subMenu_gs_macro_div').css('display','none');
			$('#subMenu_gs_micro_div').toggle();
			$('#subMenu_gs_micro_div .gsButton2').each(function(){  
				var idName=this.id;
				var name=this.name;
				$('#'+idName).unbind('click');
				$('#'+idName).bind('click',function(){
					PT.ProIndex.clearResultSubMenuColor(idName,name,'subMenu_gs_micro_div');
					$('#'+idName).css('background-color','red');
				});
			});
			$('#queryMicroResultId').unbind('click');
			$('#queryMicroResultId').bind('click',function(){
				PT.ProIndex.queryMicroResultSubMenu(modelId);
			});
	    },
	    queryMacroResultSubMenu:function(modelId){
			if(null!=PT.ProIndex.macroModelYearType && null!=PT.ProIndex.macroResultType && null!=PT.ProIndex.macroResultTime){
				$.ajax({
					url:"projectModelResultMoe/queryGeomMoeList",
					type:"POST",
					contentType:"application/json",
					dataType:"json",
					data:JSON.stringify({"model_id":modelId,"model_year_type":PT.ProIndex.macroModelYearType}),
					processData:false,
					success: function(data){
						debugger;
						window.frames['mapContainer'].TT.ResMap.clearMapLayer();
						window.frames['mapContainer'].TT.ResMap.clearLayerLayer();
			            if(null!=data && null!=data.linkResultMoeData){
			            	PT.ProIndex.linkResultMoeData = data.linkResultMoeData;
			            	delete data.linkResultMoeData;
//			                window.frames['mapContainer'].TT.Map.setMapCenter(116.4718, 39.99191,13);
			            	PT.ProIndex.addMacroResultLayer(PT.ProIndex.linkResultMoeData,modelId);
//			                //添加选择事件
//			            	window.frames['mapContainer'].TT.ResMap.addSelectEvent();
//			                var source = new ol.source.Vector({wrapX: false});
//			                for(var j = 0,len=jsonArray.length; j < len; j++){
//			                    json = jsonArray[j];
//			                    var point = [json.lon ,json.lat];
//			                    point = ol.proj.transform(
//			                        point, 'EPSG:4326', 'EPSG:3857');
//			                    var featureId = "observeFlow$$id$$"+projectId+"$$" + json.model_id +"$$" + json.observe_position;
//			                    delete json;
//			                    var pointFeature = new ol.Feature(new ol.geom.Point(point));
//			                    pointFeature.setId(featureId);
//			                    source.addFeature(pointFeature);
//			                }
//			                TT.Map.positionPointLayer.setSource(source);
//			                TT.Map.positionPointLayer.setMap(TT.Map.map);
//			                delete jsonArray;
			            }
					}
		        });
			}else{
				alert("请先选择查询条件");
			}
	    },
	    queryMicroResultSubMenu:function(modelId){
			if(null!=PT.ProIndex.microModelYearType && null!=PT.ProIndex.microResultType && null!=PT.ProIndex.microResultTime){
				$.ajax({
					url:"projectModelResultMoe/queryGeomMoeList",
					type:"POST",
					contentType:"application/json",
					dataType:"json",
					data:JSON.stringify({"model_id":modelId,"model_year_type":PT.ProIndex.microModelYearType}),
					processData:false,
					success: function(data){
						debugger;
						window.frames['mapContainer'].TT.ResMap.clearMapLayer();
						window.frames['mapContainer'].TT.ResMap.clearLayerLayer();
			            if(null!=data && null!=data.linkResultMoeData){
			            	PT.ProIndex.linkResultMoeData = data.linkResultMoeData;
			            	delete data.linkResultMoeData;
			            	PT.ProIndex.addMacroResultLayer(PT.ProIndex.linkResultMoeData,modelId);
			            }
					}
		        });
			}else{
				alert("请先选择查询条件");
			}
	    },
	    /**
	     * 添加宏观模型moe数据
	     */
	    addMacroResultLayer : function(jsonArray,modelId){
	        var source = new window.frames['mapContainer'].ol.source.Vector({wrapX: false});
	        for(var j = 0,len=jsonArray.length; j < len ; j++){
	            var json = jsonArray[j];
	            //a-b路段
	            var geom = json.a_longitude+","+json.a_latitude+";"+json.b_longitude+","+json.b_latitude;
	            if(geom != null && geom !='') {
	                var abVoc = json.volume;
	                var color =  window.frames['mapContainer'].TT.ResMap.getVocColor(abVoc);
	                var abFlow = json.speed_mph;
	                var width = window.frames['mapContainer'].TT.ResMap.getFlowWidth(abFlow);
	                var featureId = "query_macro_Result_abid_" + modelId + "_" + json.id;
	                source = window.frames['mapContainer'].TT.ResMap.addToSource(featureId, geom, color, width, source);
	            }
	            //b-a路段
//	            geom = json.b_longitude+","+json.b_latitude+";"+json.a_longitude+","+json.a_latitude;
//	            if(geom != null && geom !='') {
//	                var baVoc = json.volume;
//	                var color =  window.frames['mapContainer'].TT.ResMap.getVocColor(abVoc);
//	                var abFlow = json.speed_mph;
//	                var width = window.frames['mapContainer'].TT.ResMap.getFlowWidth(abFlow);
//	                var featureId = "query_macro_Result_abid_" + modelId + "_" + json.id;
//	                source = window.frames['mapContainer'].TT.ResMap.addToSource(featureId, geom, color, width, source);
//	            }
	        }
	        window.frames['mapContainer'].TT.Map.lineLayer.setSource(source);
	        window.frames['mapContainer'].TT.Map.lineLayer.setMap(window.frames['mapContainer'].TT.Map.map);
	    },
	    
	    /**
	     * 叠加观测点流量
	     */
	    showObserveFlowLayer: function (projectId,modelType) {
	        TT.Index.clearMapLayer();
	        TT.Index.clearLayerLayer();
	    	TT.Index.addObserveFlowLayer(projectId,modelType);
	    	TT.Index.addHighWaySelectEvent();
	    },

	    /**
	     * 叠加观测点流量
	     */
	    addObserveFlowLayer: function (projectId,modelType) {
	    	$.ajax({
				url:"projectModelObserveFlow/queryGeomInfoList",
				type:"POST",
				contentType:"application/json",
				dataType:"json",
				data:JSON.stringify({"projectId":projectId,"modelType":modelType}),
				processData:false,
				success: function(data){
		            if(null!=data && null!=data.projectModelFlowGeomData){
		            	jsonArray = data.projectModelFlowGeomData;
		            	delete data.projectModelFlowGeomData;
		                var source = new ol.source.Vector({wrapX: false});
		                for(var j = 0,len=jsonArray.length; j < len; j++){
		                    json = jsonArray[j];
		                    var point = [json.lon ,json.lat];
		                    point = ol.proj.transform(
		                        point, 'EPSG:4326', 'EPSG:3857');
		                    var featureId = "observeFlow$$id$$"+projectId+"$$" + json.model_id +"$$" + json.observe_position;
		                    delete json;
		                    var pointFeature = new ol.Feature(new ol.geom.Point(point));
		                    pointFeature.setId(featureId);
		                    source.addFeature(pointFeature);
		                }
		                TT.Map.positionPointLayer.setSource(source);
		                TT.Map.positionPointLayer.setMap(TT.Map.map);
		                delete jsonArray;
		            }
				}
	        });
	    },

	    /**
	     * 添加feature选择事件
	     */
	    addHighWaySelectEvent : function(){
	        TT.Map.select.on('select', function(e) {
	            var features = e.target.getFeatures();
	            if(features != null && features.getArray().length > 0) {
	                var feature = features.getArray()[0];
	                var featureId = feature.getId();
	                TT.Index.clickHighWayFeature(featureId);
	                e.target.getFeatures().remove(feature);
	            }
	        });
	        TT.Map.map.addInteraction(TT.Map.select);
	    }
};
