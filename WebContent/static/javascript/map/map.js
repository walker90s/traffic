/**
 * Created by songyang on 2015/10/17.
 */
TT.Map = {
    /**
     * 地图对象
     */
    map: null,
    /**
     * 初始化中心点
     */
    initCenter : [116.4718, 39.9919],
    /**
     * 选择器对象
     */
    select : new ol.interaction.Select(),
    /**
     * 模型点图层
     */
    pointLayer : new ol.layer.Vector({
        source: new ol.source.Vector({wrapX: false}),
        style: new ol.style.Style({
            zIndex : 101,
            image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                anchor: [0.5, 12],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                opacity: 0.95,
                size: [22, 22],
                x:22,
                y:22,
                src: 'static/images/marker/point_0.png'
            }))
        })
    }),
    /**
     * 模型点图层
     */
    positionPointLayer : new ol.layer.Vector({
        source: new ol.source.Vector({wrapX: false}),
        style: new ol.style.Style({
            zIndex : 101,
            image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                anchor: [0.5, 12],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                opacity: 0.95,
                size: [22, 22],
                x:22,
                y:22,
                src: 'static/images/marker/188-pin-map.png'
            }))
        })
    }),
    /**
     * 模型线图层
     */
    lineLayer : new ol.layer.Vector({
            source: new ol.source.Vector({wrapX: false}),
            style: new ol.style.Style({
                zIndex : 100,
                stroke: new ol.style.Stroke({
                    width: 3,
                    color: [255, 0, 0, 1]
                }),
                fill: new ol.style.Fill({
                    color: [0, 0, 255, 0.6]
                })
            })
        }),
    /**
     * 图层点图层
     */
    layerPointLayer : new ol.layer.Vector({
        source: new ol.source.Vector({wrapX: false}),
        style: new ol.style.Style({
            zIndex : 100001,
            image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                anchor: [0.5, 12],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                opacity: 0.95,
                size: [22, 22],
                x:22,
                y:22,
                src: 'static/images/marker/point_0.png'
            }))
        })
    }),
    /**
     * 图层线图层
     */
    layerLineLayer : new ol.layer.Vector({
        source: new ol.source.Vector({wrapX: false}),
        style: new ol.style.Style({
            zIndex : 100,
            stroke: new ol.style.Stroke({
                width: 3,
                color: [255, 0, 0, 1]
            }),
            fill: new ol.style.Fill({
                color: [0, 0, 255, 0.6]
            })
        })
    }),
    /**
     * 弹出框对象
     */
    overlay : new ol.Overlay(/** @type {olx.OverlayOptions} */ ({
        element: document.getElementById('popup'),
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    })),
    init: function () {
        //var container = document.getElementById('popup');
        this.initCenter = ol.proj.transform(
            this.initCenter, 'EPSG:4326', 'EPSG:3857');

        this.map = new ol.Map({
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.OSM()
                }),
                this.lineLayer,
                this.pointLayer,
                this.positionPointLayer,
                this.layerPointLayer,
                this.layerLineLayer
            ],
            overlays: [this.overlay],
            target: 'map',
            controls: ol.control.defaults({
                attribution : false,
                attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
                    collapsible: false
                })
            }),
            view: new ol.View({
                center: this.initCenter,
                zoom: 13
            })
        });

        /*this.map.on('singleclick', function (evt) {
            var coordinate = evt.coordinate;
            var hdms = ol.coordinate.toStringHDMS(ol.proj.transform(
                coordinate, 'EPSG:3857', 'EPSG:4326'));

            console.log(hdms);
            overlay.setPosition(coordinate);
        });*/
    },
    setMapCenterByFeature : function(feature){

    },
    /**
     * 设置中心点和级别
     * @auth flgang
     * @param centerPointX
     * @param centerPointy
     * @param zoomVal
     */
    setMapCenter:function(centerPointX,centerPointY,zoomVal){
        var view=new ol.View({
            center: ol.proj.transform([centerPointX,centerPointY], 'EPSG:4326', 'EPSG:3857'),
            zoom: zoomVal
        });
        TT.Map.map.setView(view);
    }
};