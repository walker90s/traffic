/**
 * Created by songyang on 2015/10/17.
 */
TT.PopMap = {
    map: null,
    initCenter : [116.3524, 39.9031],
    select : new ol.interaction.Select(),
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
                src: 'static/images/marker/phaser.png'
            }))
        })
    }),
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
                this.lineLayer,
                this.pointLayer
            ],
            overlays: [this.overlay],
            target: 'map',
            controls: ol.control.defaults({
                attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
                    collapsible: false
                }),
                attribution : false,
                zoom : false,
                rotate : false
            }),
            view: new ol.View({
                center: this.initCenter,
                zoom: 11
            })
        });
        var is = TT.PopMap.map.getInteractions();
        for(var i = 0; i < is.getArray().length; ){
            TT.PopMap.map.removeInteraction(is.getArray()[i]);
        }

        /*this.map.on('singleclick', function (evt) {
            var coordinate = evt.coordinate;
            var hdms = ol.coordinate.toStringHDMS(ol.proj.transform(
                coordinate, 'EPSG:3857', 'EPSG:4326'));

            console.log(hdms);
            overlay.setPosition(coordinate);
        });*/
    },
    setMapCenterByFeature : function(feature){

    }
}