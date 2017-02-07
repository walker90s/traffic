/**
 * Created by songyang on 2015/10/17.
 */
TT.Index = {
    //当前图层类型：null无图层,0路口，1：红绿灯2：信号机3：视频检测器4：电视监控
    nowLayerType : null,
    //当前模型id
    nowModelId : null,
    //模型json数据
    modelJsonArray : null,
    //line中心点坐标数组
    centerArray : null,
    //高亮feature;
    highFeature : null,
    //高亮feature原颜色
    highFeatureStyle : null,
    //图层json数据
    layerJsonArray : {},
    //路口流量数据
    FlowList: null,
    /**
     * 初始化
     */
    init: function () {
        TT.Map.init();
        this.loadProjectList();
        this.loadModelList();
    },
    /**
     * 隐藏或显示dom元素
     */
    toggle : function(domId){
        jQuery("#"+domId).toggle();
    },
    /**
     * 根据模型类型隐藏或显示模型列表
     * @param type
     */
    toggleModel : function(type){
        if($("#model_ul_" + type).css("display") == "none"){
            $("#model_" + type).attr("class", "fa fa-angle-up");
        }else{
            $("#model_" + type).attr("class", "fa fa-angle-down");
        }

        $("#model_ul_" + type).toggle();
    },

    /**
     * 叠加路口
     */
    addIntersectionLayer: function (model) {
        jsonArray = model.trafficIntersectionList;
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom.split(",");
            var point = [parseFloat(geom[0]), parseFloat(geom[1])];
            point = ol.proj.transform(
                point, 'EPSG:4326', 'EPSG:3857');
            var featureId = "intersection_id_" + model.id +"_" + json.id;

            var pointFeature = new ol.Feature(new ol.geom.Point(point));
            pointFeature.setId(featureId);
            source.addFeature(pointFeature);

        }
        TT.Map.pointLayer.setSource(source);
        TT.Map.pointLayer.setMap(TT.Map.map);
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
     * 叠加观测点速度
     */
    showObserveSpeedLayer: function (projectId,modelType) {
        TT.Index.clearMapLayer();
        TT.Index.clearLayerLayer();
    	TT.Index.addObserveSpeedLayer(projectId,modelType);
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
     * 叠加观测点速度
     */
    addObserveSpeedLayer: function (projectId,modelType) {
    	$.ajax({
			url:"projectModelObserveSpeed/queryGeomInfoList",
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
	                    var featureId = "observeSpeed$$id$$"+projectId+"$$" + json.model_id +"$$" + json.location;
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
	                    htmlArr.push(' <li><a href="javascript:TT.Index.changeProjectModel('+ id +',\''+ name +'\');" title="'+ name +'" style="line-height:0px;" >' + vname +'</a></li>');

	            	}
                    $("#projectListUl").html(htmlArr.join(''));
                    delete htmlArr;
                    delete data.projectList;
	            }
			}
        });
    },
    changeProjectModel:function(projectId,projectName){
    	if(null!=projectId && null!=projectName){
    		if(projectId==1){
                $('#gsDiv').css('display','none');
                $('#wjDiv').css('display','');
                TT.Map.setMapCenter(116.4718, 39.99191,13);
    		}else if(projectId>=2){
                $('#gsDiv').css('display','');
                $('#wjDiv').css('display','none');
                htmlArr=[];
                htmlArr.push('<li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0);" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> '+projectName+'</a></li>');
                htmlArr.push('<ol><a href="javascript:TT.Index.showObserveFlowLayer('+ projectId +', 1 );" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测流量</a></ol>');
                htmlArr.push('<ol><a href="javascript:TT.Index.showObserveSpeedLayer('+ projectId +', 1 );" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测速度</a></ol>');
                $('#macroUl').html(htmlArr.join(''));
                delete htmlArr;
                htmlArr=[];
                htmlArr.push('<li  onClick="javascript:ShowMenu(this, 0)"><a href="javascript:void(0);" style="width:180px;margin-left: 0px;-webkit-border-radius: 0px;border-radius: 0px;"> '+projectName+'</a></li>');
                htmlArr.push('<ol><a href="javascript:TT.Index.showObserveFlowLayer('+ projectId +', 3 );" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测流量</a></ol>');
                htmlArr.push('<ol><a href="javascript:TT.Index.showObserveSpeedLayer('+ projectId +', 3 );" style="width:160px;background: none;background-color:greenyellow;margin-left: -45px;-webkit-border-radius: 0px;border-radius: 0px;" >观测速度</a></ol>');
                $('#microUl').html(htmlArr.join(''));
                delete htmlArr;
                TT.Map.setMapCenter(-117.851521,33.849019,10);
    		}
    	}
    },
    /**
     * 加载模型列表
     */
    loadModelList : function(){
        $.getJSON("getModelAllJSONArray", function(jsonArray){
            TT.Index.modelJsonArray = jsonArray;

            for(var i = 0; i < jsonArray.length; i++) {
                var html = '';
                var json = jsonArray[i];
                var name = json.name;
                var id = json.id;

                var vname = name.length > 15 ? name.substr(0,15) + "..." : name;
                var li = ' <li><a href="javascript:TT.Index.changeModel('+ id +');" title="'+ name +'"><i class="fa fa-random"></i>' + vname +'</a></li>'
                html += li;

                var oldHtml = $("#model_ul_"+json.type).html();
                html = oldHtml + html;
                $("#model_ul_"+json.type).html(html);
                $("#model_ul_"+json.type).show();
            }


        });
    },
    /**
     * 切换模型
     * @param modelId
     */
    changeModel : function (modelId) {
        TT.Index.clearMapLayer();
        TT.Index.clearLayerLayer();

        if(TT.Index.nowModelId != modelId){
            //展示模型数据
            TT.Index.showModelLayer(modelId);
            //添加选择事件
            TT.Index.addSelectEvent();
            TT.Index.nowModelId = modelId;
        }else{
            TT.Index.nowModelId = null;
        }
    },
    /**
     * 切换图层
     * @param type
     */
    changeLayer : function(type, dom){
        if(dom.className == "fa"){
            dom.className = "fa fa-check-circle";
        }else{
            dom.className = "fa";
        }
        this.clearMapLayer();
        $.getJSON("getLayerJsonArray?type="+type, function(jsonArray){
            if(TT.Index.layerJsonArray[type] != undefined ){
                TT.Index.layerJsonArray[type] = undefined
            }else{
                TT.Index.layerJsonArray[type] = jsonArray;
            }
            if(type == 5){
                //路段
                var source = TT.Map.layerLineLayer.getSource();
                if(source == null){
                    source = new ol.source.Vector({wrapX: false});
                }
                for(var j = 0; j < jsonArray.length; j++){
                    var json = jsonArray[j];
                    var featureId = "layer_road_id_"+ type + "_" + json.id;
                    var feature = source.getFeatureById(featureId);
                    if(feature == null){
                        var geom = json.geom;
                        var width = 3;
                        var color = [0, 255, 0, 1];
                        source = TT.Index.addToSource(featureId, geom, color, width, source);
                    }else{
                        source.removeFeature(feature);
                    }
                }
                TT.Map.layerLineLayer.setSource(source);

            }else{
                //点
                var source = TT.Map.layerPointLayer.getSource();
                if(source == null){
                    source = new ol.source.Vector({wrapX: false});
                }
                for(var j = 0; j < jsonArray.length; j++){
                    var json = jsonArray[j];
                    var featureId = "layer_point_id_" + type +"_" + json.id;
                    var feature = source.getFeatureById(featureId);
                    if(feature == null){
                        var geom = json.geom.split(",");
                        var point = [parseFloat(geom[0]), parseFloat(geom[1])];
                        point = ol.proj.transform(
                            point, 'EPSG:4326', 'EPSG:3857');

                        var pointFeature = new ol.Feature(new ol.geom.Point(point));
                        var pointStyle = new ol.style.Style({
                            zIndex : 101,
                            image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                                anchor: [0.5, 12],
                                anchorXUnits: 'fraction',
                                anchorYUnits: 'pixels',
                                opacity: 0.95,
                                size: [22, 22],
                                x:22,
                                y:22,
                                src: 'static/images/marker/point_'+type+'.png'
                            }))
                        });
                        pointFeature.setStyle(pointStyle);
                        pointFeature.setId(featureId);
                        source.addFeature(pointFeature);
                    }else{
                        source.removeFeature(feature);
                    }
                }
                TT.Map.layerPointLayer.setSource(source);
            }
            TT.Map.layerLineLayer.setMap(TT.Map.map);
            TT.Map.layerPointLayer.setMap(TT.Map.map);
            TT.Index.addSelectEvent();
        });

    },
    /**
     * 叠加模型数据
     */
    showModelLayer: function (modelId) {
        var dataArray = TT.Index.modelJsonArray;
        for(var i = 0; i < dataArray.length; i++){
            var model = dataArray[i];
            if(model.id == modelId){
                //宏观模型
                if(model.type == 1){
                    TT.Index.addBigModelLayer(model);
                    break;
                }else if(model.type == 2){
                    TT.Index.addMatlabModelLayer(model);
                    break;
                }else if(model.type == 3){
                    TT.Index.addMiniModelLayer(model);
                    break;
                }else{
                    TT.Index.addIntersectionLayer(model);
                    break;
                }
            }
        }
    },
    /**
     * 添加宏观模型数据
     * @param model
     */
    addBigModelLayer : function(model){
        var source = new ol.source.Vector({wrapX: false});
        var jsonArray = model.trafficModelBigList;
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            //a-b路段
            var geom = json.abGeom;
            if(geom != null && geom !='') {
                var abVoc = json.trafficModelBigResult.abVoc;
                var color = TT.Index.getVocColor(abVoc);
                var abFlow = json.trafficModelBigResult.abFlow;
                var width = TT.Index.getFlowWidth(abFlow);
                var featureId = "model_big_id_" + model.id +"_" + json.id;
                source = TT.Index.addToSource(featureId, geom, color, width, source);
            }
            //b-a路段
            geom = json.baGeom;
            if(geom != null && geom !='') {
                var baVoc = json.trafficModelBigResult.baVoc;
                var color = TT.Index.getVocColor(baVoc);
                var baFlow = json.trafficModelBigResult.baFlow;
                var width = TT.Index.getFlowWidth(baFlow);
                var featureId = "model_big_baid_" + model.id +"_" + json.id;
                source = TT.Index.addToSource(featureId, geom, color, width, source);
            }
        }
        TT.Map.lineLayer.setSource(source);
        TT.Map.lineLayer.setMap(TT.Map.map);
    },
    /**
     * 添加中观模型数据
     * @param model
     */
    addMatlabModelLayer : function(model){
        var source = new ol.source.Vector({wrapX: false});
        var jsonArray = model.trafficModelMatlabList;
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom;
            if(geom != null && geom !='') {
                var color = [0,255,0, 0.5];
                var width = 3;
                var featureId = "model_matlab_id_" + model.id +"_" + json.id;
                source = TT.Index.addToSource(featureId, geom, color, width, source);
            }
        }
        TT.Map.lineLayer.setSource(source);
        TT.Map.lineLayer.setMap(TT.Map.map);
    },
    /**
     * 添加微观模型数据
     * @param model
     */
    addMiniModelLayer : function(model){
        var source = new ol.source.Vector({wrapX: false});
        var jsonArray = model.trafficModelMiniList;
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom;
            if(geom != null && geom !='') {
                var color = [0,255,0, 0.5];
                var width = 3;
                var featureId = "model_mini_id_" + model.id +"_" + json.id;
                source = TT.Index.addToSource(featureId, geom, color, width, source);
            }
        }
        TT.Map.lineLayer.setSource(source);
        TT.Map.lineLayer.setMap(TT.Map.map);
    },
    /**
     * 添加feature选择事件
     */
    addSelectEvent : function(){
        TT.Map.select.on('select', function(e) {
            var features = e.target.getFeatures();
            if(features != null && features.getArray().length > 0) {
                var feature = features.getArray()[0];
                var featureId = feature.getId();
                TT.Index.clickFeature(featureId);
                e.target.getFeatures().remove(feature);

            }
        });
        TT.Map.map.addInteraction(TT.Map.select);
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
    },
    /**
     * 将geom坐标串添加到source中，并返回source
     * @param geom
     * @param source
     * @returns {*}
     */
    addToSource : function(featureId, geom, color, width, source){
        var points = geom.split(";");
        var pointArray = new Array();
        for(var i = 0; i < points.length; i++) {
            if (points[i] != "") {
                var point;
                if(points[i] != null && points[i] != ''){
                    point = points[i].split(",");

                    var pArray = new Array();
                    pArray.push(parseFloat(point[0]));
                    pArray.push(parseFloat(point[1]));
                    //var p = [parseFloat(point[0]), parseFloat(point[1])];
                    pArray = ol.proj.transform(
                        pArray, 'EPSG:4326', 'EPSG:3857');
                    pointArray.push(pArray);
                }
            }
        }

        var lineFeature = new ol.Feature(
            new ol.geom.LineString(pointArray)
        );
        lineFeature.setStyle(
            new ol.style.Style({
                stroke: new ol.style.Stroke({
                    width: width,
                    color: color
                })
            })
        );
        lineFeature.setId(featureId);
        source.addFeature(lineFeature);
        return source;
    },

    getVocColor : function(voc){
        var color = [0,255,0, 0.5];
        /*if(voc >= 0.2 && voc < 0.5){
            color = [0,139,0, 0.5];
        }else if(voc >= 0.5 && voc < 0.7){
            color = [255,255,0, 0.5];
        }else if(voc >= 0.7 && voc < 0.8){
            color = [255,74,0, 0.5];
        }else if(voc >= 0.8 && voc < 0.9){
            color = [255,149,0, 0.5];
        }else if(voc >= 0.9 && voc < 1){
            color = [255,223,0, 0.5];
        }else if(voc >= 1 && voc < 2){
            color = [255, 0, 0, 0.5];
        }else if(voc >= 2){
            color = [0, 0, 0, 0.5];
        }*/
        return color;
    },
    getFlowWidth : function(flow){
        var width = 3;
        /*if(flow >= 45 && flow < 90){
            width = 5;
        }else if(flow >= 90 && flow < 180){
            width = 7;
        }else if(flow >= 180){
            width = 9;
        }*/
        return width;
    },
    /**
     * feature点击处理
     * @param featureId
     */
    clickFeature : function (featureId) {
        //如果之前有高亮的feature，还原高亮feature
        if(TT.Index.highFeature != null){
            TT.Index.highFeature.setStyle(TT.Index.highFeatureStyle);
        }
        var center = TT.Map.initCenter;
        if(featureId.indexOf("intersection_id_") > -1 || featureId.indexOf("layer_point_id_") > -1){
            var source = TT.Map.pointLayer.getSource();
            if(featureId.indexOf("layer_point_id_") > -1){
                source = TT.Map.layerPointLayer.getSource();
            }
            var feature = source.getFeatureById(featureId);
            var g = feature.getGeometry();
            center = g.getFirstCoordinate();
            TT.Map.map.setView(new ol.View({
                    center: center,
                    zoom: TT.Map.map.getView().getZoom()
                })
            );
            feature = null;
        }else {
            var source = TT.Map.lineLayer.getSource();
            if(featureId.indexOf("layer_road_id_") > -1){
                source = TT.Map.layerLineLayer.getSource();

            }
            var feature = source.getFeatureById(featureId);
            var extent = feature.getGeometry().getExtent();
            center = ol.extent.getCenter(extent);

            TT.Index.highFeature = feature;
            TT.Index.highFeatureStyle = feature.getStyle();
            var highColor = [0, 0, 255, 1];
            var width = feature.getStyle().getStroke().getWidth();
            feature.setStyle(
                new ol.style.Style({
                    stroke: new ol.style.Stroke({
                        width: width,
                        color: highColor
                    })
                })
            );
            feature = null;

        }
        //创建pop框
        TT.Index.createPopu(featureId, center);
        //设置地图中心点TT.Map.map.getView().getZoom()
        TT.Map.map.setView(new ol.View({
                center: center,
                zoom: TT.Map.map.getView().getZoom()
            })
        );


    },
    /**
     * feature点击处理
     * @param featureId
     */
    clickHighWayFeature : function (featureId) {
        //如果之前有高亮的feature，还原高亮feature
        if(TT.Index.highFeature != null){
            TT.Index.highFeature.setStyle(TT.Index.highFeatureStyle);
        }
        var center = TT.Map.initCenter;
        if(featureId.indexOf("observeFlow$$id$$") > -1 ){
            var source = TT.Map.positionPointLayer.getSource();
            var feature = source.getFeatureById(featureId);
            var g = feature.getGeometry();
            center = g.getFirstCoordinate();
            TT.Map.map.setView(new ol.View({
                    center: center,
                    zoom: TT.Map.map.getView().getZoom()
                })
            );
            feature = null;
        }else if(featureId.indexOf("observeSpeed$$id$$") > -1 ){
            var source = TT.Map.positionPointLayer.getSource();
            var feature = source.getFeatureById(featureId);
            var g = feature.getGeometry();
            center = g.getFirstCoordinate();
            TT.Map.map.setView(new ol.View({
                    center: center,
                    zoom: TT.Map.map.getView().getZoom()
                })
            );
            feature = null;
        }
        //创建pop框
        TT.Index.createHighWayPopu(featureId, center);
        //设置地图中心点TT.Map.map.getView().getZoom()
        TT.Map.map.setView(new ol.View({
                center: center,
                zoom: TT.Map.map.getView().getZoom()
            })
        );
    },
    /**
     * 创建弹出窗口
     */
    createHighWayPopu : function(featureId, center){
        var ids = featureId.split("$$");
        var model_id = ids[ids.length - 2];
        var observe_position = ids[ids.length - 1];
        if("observeFlow"==ids[0]){
            $.ajax({
    			url:"projectModelObserveFlow/queryFlowDataList",
    			type:"POST",
    			contentType:"application/json",
    			dataType:"json",
    			data:JSON.stringify({"observe_position":observe_position,"model_id":model_id}),
    			processData:false,
    			success: function(data){
    	            if(null!=data && null!=data.flow && null!=data.category){
    	            	$('#observeflow_position').text("观测位置: "+observe_position);
    	            	$('#highWay_observeflow_popup_div').css('display','');
    	            	var textTitle = "流量";
    	    			var charData = data.flow;

    	    			charData = eval(charData);
    	    			var category = eval(data.category);
    	    			$("#highWay_observeflow_Container").highcharts({
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
    	                delete data.flow;
    	                delete data.category;
    	            }
    			}
            });
        }else if("observeSpeed"==ids[0]){
            $.ajax({
    			url:"projectModelObserveSpeed/querySpeedDataList",
    			type:"POST",
    			contentType:"application/json",
    			dataType:"json",
    			data:JSON.stringify({"location":observe_position,"model_id":model_id}),
    			processData:false,
    			success: function(data){
    	            if(null!=data && null!=data.speed && null!=data.category){
    	            	$('#observeflow_position').text("观测位置: "+observe_position);
    	            	$('#highWay_observeflow_popup_div').css('display','');
    	            	var textTitle = "速度";
    	    			var charData = data.speed;

    	    			charData = eval(charData);
    	    			var category = eval(data.category);
    	    			$("#highWay_observeflow_Container").highcharts({
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
    	                delete data.speed;
    	                delete data.category;
    	            }
    			}
            });
        }

    },
    /**
     * 创建弹出窗口
     */
    createPopu : function(featureId, center){
        var data = {};
        if(featureId.indexOf("layer_") > -1){
            //图层
            var ids = featureId.split("_");
            var modelId = ids[ids.length - 1];
            var type = ids[ids.length - 2];
            var data  = TT.Index.layerJsonArray[type];
            for(var i = 0; i < data.length; i++){
                if(modelId == data[i].id){
                    data = data[i];
                    break;
                }
            }
        }else{
            var ids = featureId.split("_");
            var modelId = ids[ids.length - 2];
            var dataId = ids[ids.length - 1];
            var dataArray = TT.Index.modelJsonArray;
            for(var i = 0; i < dataArray.length; i++) {
                var model = dataArray[i];
                if(model.id == modelId){
                    var dataList = null;
                    if(featureId.indexOf("model_big") > -1){
                        //宏观模型
                        var dataList = model.trafficModelBigList;
                    }
                    if(featureId.indexOf("model_matlab") > -1){
                        //中观模型
                        var dataList = model.trafficModelMatlabList;
                    }
                    if(featureId.indexOf("model_mini") > -1){
                        //中观模型
                        var dataList = model.trafficModelMiniList;
                    }
                    if(featureId.indexOf("intersection_id_") > -1){
                        //路口
                        dataList = model.trafficIntersectionList;
                    }
                    if(featureId.indexOf("road_id_") > -1){
                        dataList = model.trafficRoadList;
                    }
                    for(var j = 0; j < dataList.length; j++){
                        data = dataList[j];
                        if(data.id == dataId){
                            break;
                        }
                    }
                    break;
                }
            }
        }
        var html = TT.Index.getPopupHtml(featureId, data);
        var name = "";
        for(var o in data){
            if(o == "name"){
                name = data[o];
                break;
            }
            //html += o + ":" + data[o] +"</br>";
        }

        var content = document.getElementById('popup-content');
        content.innerHTML = html;
        TT.Map.overlay.setPosition(center);
        $("#popup-closer").click(function() {
            if(TT.Index.highFeature != null){
                TT.Index.highFeature.setStyle(TT.Index.highFeatureStyle);
            }
            TT.Map.overlay.setPosition(undefined);
            this.blur();
            return false;
        });
        if(featureId.indexOf("intersection_id_") > -1){
            //url = "intersection?crossid="+data.crossId;
            //$("#intersection_iframe").attr("src", url);
            //$("#intersection_popup_title").html(name);
            var crossId = data.crossId;
            $.getJSON("getIntersectionJSONArray?crossid="+crossId, function(json){
                var timeList = json.timeList;
                var flowList = json.flowList;

                $("#time_cycle").html(MESSAGE.cycle+"：" + json.cycle + "s");
                $("#time_phase").html(MESSAGE.phaseDifference+"：" + json.phase );
                TT.Index.FlowList = flowList;
                var timeTrs = TT.Index.getTimeTableHtml(timeList);
                $("#time_table").html(timeTrs);
                var flowTrs = TT.Index.getFlowTableHtml(flowList);
                $("#flow_table").html(flowTrs);
            });
            $("#intersection_popup_a_name").html(name);
            var st=document.documentElement.scrollTop|| document.body.scrollTop;//滚动条距顶部的距离
            var sl=document.documentElement.scrollLeft|| document.body.scrollLeft;//滚动条距左边的距离
            var ch=document.documentElement.clientHeight;//屏幕的高度
            var cw=document.documentElement.clientWidth;//屏幕的宽度
            var objH=$("#intersection_popup_div").height();//浮动对象的高度
            var objW=$("#intersection_popup_div").width();//浮动对象的宽度
            var objT=Number(st)+(Number(ch)-Number(objH))/2;
            var objL=Number(sl)+(Number(cw)-Number(objW))/2;

            $("#intersection_popup_div").css('left',objL);
            $("#intersection_popup_div").css('top',objT);
            $("#intersection_popup_div").show();
            $("#over_div").show();
            //window.location.href = "intersection?crossid="+data.crossId;
        }
    },
    /**
     * 获取配时表格内容
     * @param timeList
     */
    getTimeTableHtml : function(timeList){
        var trs = "";
        for(var i = 0; i < timeList.length; i++){
            var time = timeList[i];
            var trHtml = '<tr>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">' + time.phase + '</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">' + time.phaseg + '</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">' + time.phasey + '</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">' + time.phaser + '</td>';
            trHtml += '</tr>';
            trs += trHtml;
        }
        return trs;
    },
    /**
     * 获取流量表格内容
     * @param timeList
     */
    getFlowTableHtml : function(flowList){
        var trs = "";
        for(var i = 0; i < flowList.length; i++){
            var flow = flowList[i];
            if(i == 0){
                TT.Index.changeIntersectionFlow(i);
            }
            var trHtml = '<tr onclick="TT.Index.changeIntersectionFlow('+ i +')" style="cursor: pointer;">';
            trHtml += '<td width="12%" align="center" valign="middle">'+ flow.times +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">'+ flow.northRight +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">'+ flow.northDirect +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">'+ flow.northLeft +'</td>';
            trHtml += '<td align="center" valign="middle">'+ flow.eastRight +'</td>';
            trHtml += '<td align="center" valign="middle">'+ flow.eastDirect +'</td>';
            trHtml += '<td align="center" valign="middle">'+ flow.eastLeft +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">'+ flow.southRight +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">'+ flow.southDirect +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#e4edf3">'+ flow.southLeft +'</td>';
            trHtml += '<td align="center" valign="middle">'+ flow.westRight +'</td>';
            trHtml += '<td align="center" valign="middle">'+ flow.westDirect +'</td>';
            trHtml += '<td align="center" valign="middle">'+ flow.westLeft +'</td>';
            trHtml += '</tr>';
            trs += trHtml;
        }
        return trs;
    },
    /**
     * 变更路口流量
     * @param index
     */
    changeIntersectionFlow : function(index){
        var flow = TT.Index.FlowList[index];
        $("#northRight").html(flow.northRight);
        $("#northDirect").html(flow.northDirect);
        $("#northLeft").html(flow.northLeft);
        $("#eastRight").html(flow.eastRight);
        $("#eastDirect").html(flow.eastDirect);
        $("#eastLeft").html(flow.eastLeft);
        $("#southRight").html(flow.southRight);
        $("#southDirect").html(flow.southDirect);
        $("#southLeft").html(flow.southLeft);
        $("#westRight").html(flow.westRight);
        $("#westDirect").html(flow.westDirect);
        $("#westLeft").html(flow.westLeft);
    },
    /**
     * 切换路口弹出框标签页
     * @param obj
     */
    changePopTab : function(objId){
        $("#intersection_popup_a_time").attr("class", "");
        $("#intersection_popup_a_flow").attr("class", "");
        $("#"+objId).attr("class", "on");
        $("#intersection_popup_time_div").toggle();
        $("#intersection_popup_flow_div").toggle();
    },
    /**
     * 生成弹出框内容
     * @param featureId
     * @param data
     * @returns {string}
     */
    getPopupHtml : function(featureId, data){
        var html = '<ul class="box_top_zs" style="width:100%;">';
        //模型路口或者图层路口
        if(featureId.indexOf("intersection_id_") > -1 || featureId.indexOf("layer_point_id_0_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["name"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>"+MESSAGE.intersection+"</li>";
            html += "<li><strong>X"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[0] +"</li>";
            html += "<li><strong>Y"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[1] +"</li>";
            html += "<li><strong>"+MESSAGE.RelatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
            return html;
        }
        //信号灯
        if(featureId.indexOf("layer_point_id_1_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>"+MESSAGE.signalLamp+"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>"+MESSAGE.signalLamp+"</li>";
            html += "<li><strong>X"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[0] +"</li>";
            html += "<li><strong>Y"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[1] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
        }
        //信号机
        if(featureId.indexOf("layer_point_id_2_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>"+MESSAGE.signalLamp+"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>"+MESSAGE.signalLamp+"</li>";
            html += "<li><strong>X"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[0] +"</li>";
            html += "<li><strong>Y"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[1] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
            return html;
        }
        //视频检测器
        if(featureId.indexOf("layer_point_id_3_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>"+MESSAGE.videoDetector+"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>"+MESSAGE.videoDetector+"</li>";
            html += "<li><strong>X"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[0] +"</li>";
            html += "<li><strong>Y"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[1] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
            return html;
        }
        //电视监控
        if(featureId.indexOf("layer_point_id_4_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>"+MESSAGE.TVmonitoring+"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>"+MESSAGE.TVmonitoring+"</li>";
            html += "<li><strong>X"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[0] +"</li>";
            html += "<li><strong>Y"+MESSAGE.coordinate+"：</strong>" + data["geom"].split(",")[1] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
            return html;
        }
        //模型路段或者图层路段
        if(featureId.indexOf("road_id_") > -1 || featureId.indexOf("layer_road_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["name"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>"+MESSAGE.section+"</li>";
            html += "<li><strong>OSM-ID：</strong>" + data["osmId"] +"</li>";
            html += "<li><strong>"+MESSAGE.startingIntersection+"：</strong>" + data["snode"] +"</li>";
            html += "<li><strong>"+MESSAGE.terminalIntersection+"：</strong>" + data["tnode"] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
            return html;
        }
        //模型
        if(featureId.indexOf("model_big_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["roadname"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>" + data["linkType"] +"</li>";
            html += "<li><strong>"+MESSAGE.length+"（km）：</strong>" + data["length"] +"</li>";

            var flow = data["trafficModelBigResult"].abFlow;
            var voc = data["trafficModelBigResult"].abVoc;
            var time = data["trafficModelBigResult"].abTime;
            var speed = data["trafficModelBigResult"].abSpeed;
            //b-a方向
            if(featureId.indexOf("model_big_baid") > -1){
                var flow = data["trafficModelBigResult"].baFlow;
                var voc = data["trafficModelBigResult"].baVoc;
                var time = data["trafficModelBigResult"].baTime;
                var speed = data["trafficModelBigResult"].baSpeed;
            }
            html += "<li><strong>"+MESSAGE.LaneNumber+"：</strong></li>";
            html += "<li><strong>"+MESSAGE.RoadCapacity+"   ：</strong></li>";
            html += "<li><strong>"+MESSAGE.TheSpeedLimit+"（km/h）：</strong></li>";
            html += "<li><strong>"+MESSAGE.RelatedModel+"：</strong></li>";
            html += "</ul>";
            return html;
        }
        //中观模型
        if(featureId.indexOf("model_matlab_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["roadname"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>" + data["linkType"] +"</li>";
            html += "<li><strong>"+MESSAGE.length+"（km）：</strong>" + data["length"] +"</li>";
            html += "<li><strong>"+MESSAGE.LaneNumber+"：</strong></li>";
            html += "<li><strong>"+MESSAGE.RoadCapacity+"   ：</strong></li>";
            html += "<li><strong>"+MESSAGE.LimitingVelocity+"（km/h）：</strong></li>";
            html += "<li><strong>"+MESSAGE.RelatedModel+"：</strong></li>";
            html += "</ul>";
            return html;
        }
        //微观模型
        if(featureId.indexOf("model_mini_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["roadname"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>" + data["linkType"] +"</li>";
            html += "<li><strong>"+MESSAGE.length+"（km）：</strong>" + data["length"] +"</li>";
            html += "<li><strong>"+MESSAGE.LaneNumber+"：</strong></li>";
            html += "<li><strong>"+MESSAGE.RoadCapacity+"   ：</strong></li>";
            html += "<li><strong>"+MESSAGE.LimitingVelocity+"（km/h）：</strong></li>";
            html += "<li><strong>"+MESSAGE.RelatedModel+"：</strong></li>";
            html += "</ul>";
            return html;
        }
    },
    /**
     * 关闭路口弹出框
     */
    closeIntersection : function(){
        $("#intersection_popup_div").hide();
        $("#over_div").hide();
        TT.Index.FlowList = null;
    },
    /**
     * 关闭路口弹出框
     */
    closeHighWayObserveFlowWindow : function(){
        $("#highWay_observeflow_popup_div").hide();

    },
    /**
     * 清除点图层和线图层
     */
    clearMapLayer : function(){
        //关闭模型列表
        /*if(TT.Index.nowModelId != null){
         $("#model_i_"+TT.Index.nowModelId).attr("class", "fa fa-angle-down");
         $("#model_ul_"+TT.Index.nowModelId).hide();
         }*/
        TT.Index.nowModelId = null;
        //移除选择器
        if (TT.Map.select !== null) {
            TT.Map.map.removeInteraction(TT.Map.select);
        }
        //关闭弹出框
        TT.Map.overlay.setPosition(undefined);

        //清空模型点图层
        var plSource = TT.Map.pointLayer.getSource();
        if(plSource != null){
            plSource.clear();
        }
        //清空模型点图层
        var pplSource = TT.Map.positionPointLayer.getSource();
        if(pplSource != null){
            pplSource.clear();
        }
        //清空模型线图层
        var llSource = TT.Map.lineLayer.getSource();
        if(llSource != null){
            llSource.clear();
        }

        TT.Index.highFeature = null;
        TT.Index.highFeatureStyle = null;

        //清空观测流量点图层
        var positionFlowSource = TT.Map.positionPointLayer.getSource();
        if(null!=positionFlowSource){
        	positionFlowSource.clear();
        }
        
    },
    /**
     * 清空图层图层
     */
    clearLayerLayer : function(){
        $('#layer_controller').find("a").attr('class', 'fa');

        //清空图层点图层
        var lplSource = TT.Map.layerPointLayer.getSource();
        if(lplSource != null){
            lplSource.clear();
        }
        //清空图层线图层
        var lllSource = TT.Map.layerLineLayer.getSource();
        if(lllSource != null){
            lllSource.clear();
        }
        
    }
}