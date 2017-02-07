/**
 * Created by songyang on 2015/10/17.
 */
TT.ResMap = {
    //当前图层类型：null无图层,0路口，1：红绿灯2：信号机3：视频检测器4：电视监控
    nowLayerType : null,
    //当前模型id
    nowModelId : null,
    //模型json数据
    dataJsonArray : null,
    //line中心点坐标数组
    centerArray : null,
    //高亮feature;
    highFeature : null,
    //高亮feature原颜色
    highFeatureStyle : null,
    //图层json数据
    layerJsonArray : {},
    //结果id
    resultId : null,
    /**
     * 初始化
     */
    init: function () {
        TT.Map.init();
        var type = TT.getUrlParam("type");
        var modelId = TT.getUrlParam("modelId");
        TT.ResMap.resultId = TT.getUrlParam("resultId");
        TT.ResMap.changeModel(type, modelId, TT.ResMap.resultId);
    },
    /**
     * 叠加路口
     */
    appIntersectionLayer: function (jsonArray) {
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom.split(",");
            var point = [parseFloat(geom[0]), parseFloat(geom[1])];
            point = ol.proj.transform(
                point, 'EPSG:4326', 'EPSG:3857');
            var featureId = "intersection_id_" + json.modelId +"_" + json.id;

            var pointFeature = new ol.Feature(new ol.geom.Point(point));
            pointFeature.setId(featureId);
            source.addFeature(pointFeature);

        }
        TT.Map.pointLayer.setSource(source);
        TT.Map.pointLayer.setMap(TT.Map.map);
    },
    /**
     * 切换模型
     * @param modelId
     */
    changeModel : function (type, modelId, resultId) {
        var url = "getModelResult?type=" + type + "&resultId=" + resultId + "&modelId=" + modelId;
        $.getJSON(url, function(jsonArray) {
            TT.ResMap.dataJsonArray = jsonArray;
            TT.ResMap.clearMapLayer();
            TT.ResMap.clearLayerLayer();
            if (jsonArray != null) {
                //展示模型数据
                TT.ResMap.showModelLayer(type, jsonArray);
                //添加选择事件
                TT.ResMap.addSelectEvent();
            } else {
                alert(MESSAGE.dataLoadingFailed);
            }
        });
    },
    /**
     * 叠加模型数据
     */
    showModelLayer: function (type, dataArray) {
        switch (Number(type)){
            case 1:
            //宏观模型
                TT.ResMap.addBigModelLayer(dataArray);
                break;
            case 2:
                //中观模型
                TT.ResMap.addMatlabModelLayer(dataArray);
                break;
            case 3:
                TT.ResMap.addMiniModelLayer(dataArray);
                break;
            case 4:
                //路口模型
                TT.ResMap.appIntersectionLayer(dataArray);
                break;
            default:
                break;
        }
    },
    /**
     * 添加宏观模型数据
     * @param model
     */
    addBigModelLayer : function(jsonArray){
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            //a-b路段
            var geom = json.abGeom;
            if(geom != null && geom !='') {
                var abVoc = json.trafficModelBigResult.abVoc;
                var color = TT.ResMap.getVocColor(abVoc);
                var abFlow = json.trafficModelBigResult.abFlow;
                var width = TT.ResMap.getFlowWidth(abFlow);
                var featureId = "model_big_id_" + json.modelId + "_" + json.id;
                source = TT.ResMap.addToSource(featureId, geom, color, width, source);
            }
            //b-a路段
            geom = json.baGeom;
            if(geom != null && geom !='') {
                var baVoc = json.trafficModelBigResult.baVoc;
                var color = TT.ResMap.getVocColor(baVoc);
                var baFlow = json.trafficModelBigResult.baFlow;
                var width = TT.ResMap.getFlowWidth(baFlow);
                var featureId = "model_big_baid_" + json.modelId + "_" + json.id;
                source = TT.ResMap.addToSource(featureId, geom, color, width, source);
            }
        }
        TT.Map.lineLayer.setSource(source);
        TT.Map.lineLayer.setMap(TT.Map.map);
    },
    /**
     * 添加中观模型数据
     * @param model
     */
    addMatlabModelLayer : function(jsonArray){
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom;
            if(geom != null && geom !='') {
                var color = [0,255,0, 0.5];
                var width = 3;
                var featureId = "model_matlab_id_" + json.id;
                source = TT.ResMap.addToSource(featureId, geom, color, width, source);
            }
        }
        TT.Map.lineLayer.setSource(source);
        TT.Map.lineLayer.setMap(TT.Map.map);
    },
    /**
     * 添加微观模型数据
     * @param model
     */
    addMiniModelLayer : function(jsonArray){
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom;
            if(geom != null && geom !='') {
                var color = [0,255,0, 0.5];
                var width = 3;
                var featureId = "model_mini_id_" + json.modelId +"_" + json.id;
                source = TT.ResMap.addToSource(featureId, geom, color, width, source);
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
                TT.ResMap.clickFeature(featureId);
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
        lineFeature.setId(featureId)
        source.addFeature(lineFeature);
        return source;
    },

    /**
     * feature点击处理
     * @param featureId
     */
    clickFeature : function (featureId) {
        //如果之前有高亮的feature，还原高亮feature
        if(TT.ResMap.highFeature != null){
            TT.ResMap.highFeature.setStyle(TT.ResMap.highFeatureStyle);
        }
        var center = TT.Map.initCenter;
        if(featureId.indexOf("intersection_id_") > -1){
            var source = TT.Map.pointLayer.getSource();
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
            var feature = source.getFeatureById(featureId);
            var extent = feature.getGeometry().getExtent();
            center = ol.extent.getCenter(extent);

            TT.ResMap.highFeature = feature;
            TT.ResMap.highFeatureStyle = feature.getStyle();
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
        TT.ResMap.createPopu(featureId, center);
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
    createPopu : function(featureId, center){
        var data = {};
        var ids = featureId.split("_");
        var dataId = ids[ids.length - 1];
        var dataArray = TT.ResMap.dataJsonArray;
        for(var i = 0; i < dataArray.length; i++) {
            if(dataId == dataArray[i].id){
                data = dataArray[i];
            }
        }
        var html = TT.ResMap.getPopupHtml(featureId, data);
        var name = "";
        for(var o in data){
            if(o == "roadname" || o == "name"){
                name = data[o];
                break;
            }
            //html += o + ":" + data[o] +"</br>";
        }

        var content = document.getElementById('popup-content');
        content.innerHTML = html;
        TT.Map.overlay.setPosition(center);
        $("#popup-closer").click(function() {
            if(TT.ResMap.highFeature != null){
                TT.ResMap.highFeature.setStyle(TT.ResMap.highFeatureStyle);
            }
            TT.Map.overlay.setPosition(undefined);
            this.blur();
            return false;
        });
        if(featureId.indexOf("model_matlab_") > -1){
            //中观模型
            TT.ResMap.showPopDiv(2, data);
        }
        if(featureId.indexOf("model_mini_") > -1){
            //微观模型
            TT.ResMap.showPopDiv(3, data);
        }
        if(featureId.indexOf("intersection_id_") > -1){
            //微观模型
            TT.ResMap.showPopDiv(4, data);
        }
    },
    /**
     * 显示微观中观结果弹出框
     */
    showPopDiv : function(type, data){
        var url = "getModelResultByTypeAndId?type=" + type + "&roadId=" + data.roadId +"&resultId=" + TT.ResMap.resultId + "&aorb=" + data.aorb;
        $.getJSON(url, function(jsonArray) {
            $("#pop_name").html(data.roadname);

            $("#pop_length").html(data.length);

            var tableHtml = "";
            switch (Number(type)){
                case 1:
                    $("#pop_type").html(MESSAGE.section);
                    break;
                case 2:
                    $("#pop_model_type").html(MESSAGE.mediumModel);
                    $("#pop_type").html(MESSAGE.section);
                    tableHtml = TT.ResMap.getMatlabTableHtml(jsonArray);
                    break;
                case 3:
                    $("#pop_model_type").html(MESSAGE.microscopicModel);
                    $("#pop_type").html(MESSAGE.section);
                    tableHtml = TT.ResMap.getMatlabMiniHtml(jsonArray);
                    break;
                case 4:
                    $("#pop_model_type").html(MESSAGE.intersectionModel);
                    $("#pop_type").html(MESSAGE.section);

                    break;
                default :
                    break;
            }


            $("#popup_table").html(tableHtml);

            var st=document.documentElement.scrollTop|| document.body.scrollTop;//滚动条距顶部的距离
            var sl=document.documentElement.scrollLeft|| document.body.scrollLeft;//滚动条距左边的距离
            var ch=document.documentElement.clientHeight;//屏幕的高度
            var cw=document.documentElement.clientWidth;//屏幕的宽度
            var objH=$("#popup_div").height();//浮动对象的高度
            var objW=$("#popup_div").width();//浮动对象的宽度
            var objT=Number(st)+(Number(ch)-Number(objH))/2;
            var objL=Number(sl)+(Number(cw)-Number(objW))/2;

            $("#popup_div").css('left',objL);
            $("#popup_div").css('top',objT);
            $("#popup_div").show();
        });
    },
    /**
     * 获取地图pop框内容
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
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.nothing+"</li>";
            html += "</ul>";
            return html;
        }
        //模型
        if(featureId.indexOf("model_big_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["roadname"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>" + data["linkType"] +"</li>";
            html += "<li><strong>"+MESSAGE.length+"（km）：</strong>" + data["length"] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.macroscopicModle+"</li>";

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
            html += "<li><strong>"+MESSAGE.roadTraffic+"：</strong>" + flow +"</li>";
            html += "<li><strong>"+MESSAGE.trafficLevel+"：</strong>" + voc +"</li>";
            html += "<li><strong>"+MESSAGE.passageTime+"（min）：</strong>" + time +"</li>";
            html += "<li><strong>"+MESSAGE.averageVelocity+"（km/h）：</strong>" + speed +"</li>";
            html += "</ul>";
            return html;
        }
        //中观模型
        if(featureId.indexOf("model_matlab_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["roadname"] +"</li>";
            html += "<li><strong>"+MESSAGE.type+"：</strong>" + data["linkType"] +"</li>";
            html += "<li><strong>"+MESSAGE.length+"（km）：</strong>" + data["length"] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.mediumModel+"</li>";
            html += "</ul>";
            return html;
        }
        //微观模型
        if(featureId.indexOf("model_mini_") > -1){
            html += "<li><strong>"+MESSAGE.name+"：</strong>" + data["roadname"] +"</li>";
            html += "<li><strong"+MESSAGE.type+"：</strong>" + data["linkType"] +"</li>";
            html += "<li><strong>"+MESSAGE.length+"（km）：</strong>" + data["length"] +"</li>";
            html += "<li><strong>"+MESSAGE.relatedModel+"：</strong>"+MESSAGE.microscopicModel+"</li>";
            html += "</ul>";
            return html;
        }
    },
    /**
     * 获取中观模型弹出框表格内容
     * @param resultList
     */
    getMatlabTableHtml : function(resultList){
        var trs = '<thead><tr>';
        trs += '<th width="26%" valign="middle">'+MESSAGE.timeSlot+'</th>';
        trs += '   <th width="27%" bgcolor="#e4edf3">'+MESSAGE.density+'</th>';
        trs += '   <th width="24%" bgcolor="#e4edf3">'+MESSAGE.flow+'</th>';
        trs += '   <th width="23%" bgcolor="#e4edf3">'+MESSAGE.speed+'</th>';
        trs += '   </tr>';
        trs += '</thead>';
        for(var i = 0; i < resultList.length; i++){
            var result = resultList[i];
            var trHtml = '<tr>';
            trHtml += '<td width="26%" align="center" valign="middle">'+ result.time +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.density +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.flow +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.speed +'</td>';
            trHtml += '</tr>';
            trs += trHtml;
        }
        return trs;
    },
    /**
     * 获取微观模型弹出框表格内容
     * @param resultList
     */
    getMatlabMiniHtml : function(resultList){
        var trs = "";
        trs = '<thead><tr>';
        trs += '   <th width="10%" valign="middle">'+MESSAGE.startTime +'</th>';
        trs += '   <th width="10%" bgcolor="#e4edf3">'+MESSAGE.endTime +'</th>';
        trs += '   <th width="20%" bgcolor="#e4edf3">'+MESSAGE.density +'</th>';
        trs += '   <th width="20%" bgcolor="#e4edf3">'+MESSAGE.flow +'</th>';
        trs += '   <th width="20%" bgcolor="#e4edf3">'+MESSAGE.speed +'</th>';
        trs += '   <th width="20%" bgcolor="#e4edf3">'+MESSAGE.serviceLevel+'</th>';
        trs += '   </tr>';
        trs += '</thead>';
        for(var i = 0; i < resultList.length; i++){
            var result = resultList[i];
            var trHtml = '<tr>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.startInterval +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.endInterval +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.linkDensity +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.linkFlow +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.linkSpeed +'</td>';
            trHtml += '<td align="center" valign="middle" bgcolor="#FFFFFF">'+ result.linkLevel +'</td>';
            trHtml += '</tr>';
            trs += trHtml;
        }
        return trs;
    },
    /**
     * 关闭路口弹出框
     */
    closePopDiv : function(){
        $("#popup_div").hide();
    },
    /**
     * 清除点图层和线图层
     */
    clearMapLayer : function(){
        //关闭模型列表
        if(TT.ResMap.nowModelId != null){
            $("#model_i_"+TT.ResMap.nowModelId).attr("class", "fa fa-angle-down");
            $("#model_ul_"+TT.ResMap.nowModelId).hide();
        }
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
        //清空模型线图层
        var llSource = TT.Map.lineLayer.getSource();
        if(llSource != null){
            llSource.clear();
        }

        TT.ResMap.highFeature = null;
        TT.ResMap.highFeatureStyle = null;

    },
    /**
     * 清空图层图层
     */
    clearLayerLayer : function(){
        $('#layer_controller').find("a").css('background', '');

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
    },
    /**
     * 获取道路颜色
     * @param voc
     * @returns {number[]}
     */
    getVocColor : function(voc){
        var color = [0,139,0, 0.5];
        if(voc >= 0.25 && voc < 0.5){
            color = [0,255,0, 0.5];
        }else if(voc >= 0.5 && voc < 0.75){
            color = [255,255,0, 0.5];
        }else if(voc >= 0.75 && voc < 1){
            color = [255,149,0, 0.5];
        }else if(voc >= 1.25 && voc < 1.5){
            color = [255,74,0, 0.5];
        }else if(voc >= 1.5 && voc < 1.75){
            color = [255,223,0, 0.5];
        }else if(voc >= 1.75 && voc < 2){
            color = [255, 0, 0, 0.5];
        }else if(voc >= 2){
            color = [0, 0, 0, 0.5];
        }
        return color;
    },
    /**
     * 获取道路宽度
     * @param flow
     * @returns {number}
     */
    getFlowWidth : function(flow){
        var width = 3;
        if(flow >= 45 && flow < 90){
            width = 5;
        }else if(flow >= 90 && flow < 180){
            width = 7;
        }else if(flow >= 180){
            width = 9;
        }
        return width;
    },
}