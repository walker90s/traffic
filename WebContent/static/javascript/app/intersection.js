/**
 * Created by songyang on 2015/10/17.
 */
TT.Intersection = {
    groupArray : new Array(),

    groupIndex : 0,

    usedSecond : 0,

    changeSecond : 0,
    /**
     * 初始化
     */
    init: function (crossid) {
        TT.PopMap.init();
        this.loadModelList(crossid);
    },

    /**
     * 加载模型列表
     */
    loadModelList : function(crossid){
        $.getJSON("getIntersectionJSONArray?crossid="+crossid, function(json){
            var roadList = json.roadList;
            TT.Intersection.addRoad(roadList);
            var lightList = json.lightList;
            TT.Intersection.addLight(lightList);
            var timeList = json.timeList;
            TT.Intersection.changeByTime(timeList);

            //var center = TT.Intersection.getPointByGeomString(json.geom);
            var geom = json.lon + "," + json.lat;
            var center = TT.Intersection.getPointByGeomString(geom.split(","));

            TT.PopMap.map.setView(new ol.View({
                    center: center,
                    zoom: 20
                })
            );
        });
    },
    /**
     * 叠加红绿灯
     * @param lightList
     */
    addLight : function(lightList){
        jsonArray = lightList;
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom.split(",");
            var point = this.getPointByGeomString(geom);
            var featureId = "light_id_" + json.shpid;

            var pointFeature = new ol.Feature(new ol.geom.Point(point));
            pointFeature.setId(featureId);
            source.addFeature(pointFeature);

        }
        TT.PopMap.pointLayer.setSource(source);
        TT.PopMap.pointLayer.setMap(TT.PopMap.map);
    },

    getPointByGeomString : function(geom){
        var point = [parseFloat(geom[0]), parseFloat(geom[1])];
        point = ol.proj.transform(
            point, 'EPSG:4326', 'EPSG:3857');
        return point;
    },
    /**
     * 添加路段
     * @param roadList
     */
    addRoad : function(roadList){
        jsonArray = roadList;
        var source = new ol.source.Vector({wrapX: false});
        for(var j = 0; j < jsonArray.length; j++){
            var json = jsonArray[j];
            var geom = json.geom;
            var width = (parseInt(json.phase)) * 6;
            var color = this.getVocColor(json.color);
            var featureId = "road_id_" + json.id;
            source = TT.Intersection.addToSource(featureId, geom, color, width, source);
        }
        TT.PopMap.lineLayer.setSource(source);
        TT.PopMap.lineLayer.setMap(TT.PopMap.map);
    },
    getVocColor : function(voc){
        var color = [0,255,0, 0.5];
      if(voc == 1){
          color = [0,139,0, 0.5];
      }else if(voc == 2){
          color = [255,255,0, 0.5];
      }else if(voc == 3){
          color = [255, 0, 0, 0.5];
      }return color;
    },
    getFlowWidth : function(flow){
        var width = 2;
        if(flow >= 45 && flow < 90){
            width = 6;
        }else if(flow >= 90 && flow < 180){
            width = 10;
        }else if(flow >= 180){
            width = 14;
        }
        return width;
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
     * 根据时间规则变换红绿灯
     * @param timeList
     */
    changeByTime : function(timeList){

        for(var i = 0; i < timeList.length; i++){
            var time = timeList[i];
            var timeArray = new Array();
            if(TT.Intersection.groupArray.length >= time.phase){
                timeArray = TT.Intersection.groupArray[time.phase - 1];
                timeArray.push(time);
                TT.Intersection.groupArray[time.phase - 1] = timeArray;
            }else{
                timeArray.push(time);
                TT.Intersection.groupArray.push(timeArray);
            }
        }
        setInterval(TT.Intersection.changeLight, 1000);
    },
    /**
     * 变换颜色
     */
    changeLight : function(){
        var array = TT.Intersection.groupArray[TT.Intersection.groupIndex];
        var time = array[0];
        //当时间执行完一组，重置执行时间，数组下标向后移动一位
        if(time.phasea == TT.Intersection.usedSecond){
            if(TT.Intersection.groupArray.length == TT.Intersection.groupIndex + 1){
                TT.Intersection.groupIndex = 0;
            }else{
                TT.Intersection.groupIndex++;
            }
            TT.Intersection.usedSecond = 0;
        }else{
            //已用时间为0代表刚开始执行，应为绿灯
            if(TT.Intersection.usedSecond == 0){
                TT.Intersection.changeSecond = time.phaseg;
                TT.Intersection.changeLightColor(array, "phaseg");
            }else if(TT.Intersection.changeSecond == TT.Intersection.usedSecond && TT.Intersection.usedSecond == time.phaseg){
                //如果变化时间等于已用时间，并且已用时间等于绿灯时间则为黄灯
                TT.Intersection.changeSecond += time.phasey;
                TT.Intersection.changeLightColor(array, "phasey");
            }else if(TT.Intersection.changeSecond == TT.Intersection.usedSecond && TT.Intersection.usedSecond == (time.phaseg + time.phasey)){
                //如果变化时间等于已用时间，并且已用时间等于绿灯时间+黄灯时间则为红灯
                TT.Intersection.changeSecond += time.phaser;
                TT.Intersection.changeLightColor(array, "phaser");
            }

            TT.Intersection.usedSecond++;
        }
    },

    changeLightColor : function(array, color){
        for(var i = 0; i < array.length; i++){
            time = array[i];
            var featureId = "light_id_" + time.xlsid;
            console.log(featureId + "===========color:"+color);

            var source = TT.PopMap.pointLayer.getSource();
            var feature = source.getFeatureById(featureId);

            feature.setStyle(
                new ol.style.Style({
                zIndex : 101,
                image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                    anchor: [0.5, 12],
                    anchorXUnits: 'fraction',
                    anchorYUnits: 'pixels',
                    opacity: 0.95,
                    size: [22, 22],
                    x:22,
                    y:22,
                    src: 'static/images/marker/'+ color +'.png'
                }))
            })
            );
        }
    },
    /**
     * 清除点图层和线图层
     */
    clearMapLayer : function(){
        if (TT.Map.select !== null) {
            TT.Map.map.removeInteraction(TT.Map.select);
        }
        TT.Map.overlay.setPosition(undefined);

        var plSource = TT.Map.pointLayer.getSource();
        var llSource = TT.Map.lineLayer.getSource();
        if(plSource != null){
            plSource.clear();
        }
        if(llSource != null){
            llSource.clear();
        }

        TT.Index.highFeature = null;
        TT.Index.highFeatureStyle = null;

    }
}