package com.tt.traffic.shptools;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by admin on 2015/11/2.
 */
public class ReadShp {
    private static int id = 6;
    public static void main(String[] args) {
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        try {
            //ShapefileDataStore sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File("E:\\My Project\\TT\\工大仿真\\路口转向及红绿灯-地图\\cross_polyline.shp").toURI().toURL());

            ShapefileDataStore sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File("E:\\My Project\\TT\\工大仿真\\模型数据库表\\宏观模型\\模型数据\\TransCAD_Wangjing1_polyline.shp").toURI().toURL());
//            sds.setCharset(Charset.forName("GBK"));
            SimpleFeatureSource featureSource = sds.getFeatureSource();
            SimpleFeatureIterator itertor = featureSource.getFeatures().features();

            SimpleFeatureCollection sfc = featureSource.getFeatures();
            OutputStream out = System.out;

            while(itertor.hasNext()) {
                SimpleFeature feature = itertor.next();
                //printAttributes(feature, "c_point");
                printAttributes(feature, "intersection_light");
            }
            itertor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printAttributes(SimpleFeature feature, String type){
        List list = feature.getAttributes();
        if(type.equals("intersection_light")){
            String insert = "insert into traffic_intersection_light (`shpid`,`crossid`,`transcadid`,`geom`) values (";
            insert += "'" + feature.getAttribute("ID").toString() + "',";
            insert += "'" + feature.getAttribute("CROSSID").toString() + "',";
            insert += "'" + feature.getAttribute("TRANSCADID").toString() + "',";
            String geom = feature.getAttribute("the_geom").toString();
            geom = getString(geom);
            insert += "'" + geom + "' );";
            System.out.println(insert);
        }
        if(type.equals("intersection_road")){
            String insert = "insert into traffic_intersection_road (`shpid`,`slink`,`tlink`,`crossid`,`phase`,`color`,`transcadid`,`geom`) values (";
            insert += "'" + feature.getAttribute("ID").toString() + "',";
            insert += "'" + feature.getAttribute("SLINK").toString() + "',";
            insert += "'" + feature.getAttribute("TLINK").toString() + "',";
            insert += "'" + feature.getAttribute("CROSSID").toString() + "',";
            insert += "'" + feature.getAttribute("PHASE").toString() + "',";
            insert += "'" + feature.getAttribute("COLOR").toString() + "',";
            insert += "'" + feature.getAttribute("TRANSCADID").toString() + "',";
            String geom = feature.getAttribute("the_geom").toString();
            geom = getString(geom);
            insert += "'" + geom + "' );";
            System.out.println(insert);
        }
        if(type.equals("model_road")){
            if(feature.getAttribute("AORB").toString().equals("2")) {
                String update = "update traffic_model_big set ba_geom=";
                String geom = feature.getAttribute("the_geom").toString();
                geom = getString(geom);
                update += "'" + geom + "' ";
                update += "where road_id=" +  "'" + feature.getAttribute("ID").toString() + "';";
                System.out.println(update);
            }
            /*else{
                String insert = "insert into traffic_model_big (`road_id`,`model_id`,`length`,`dir`,`link_id`,`link_type`,`ab_speed`,`ab_lanes`,`ab_capacit`,`ab_capaci1`," +
                        "`ab_time`,`ba_speed`,`ba_lanes`,`ba_capacit`,`ba_capaci1`,`ba_time`,`roadname`,`ab_geom`) values (";
                insert += "'" + feature.getAttribute("ID").toString() + "',";
                insert += "'3',";
                insert += "'" + feature.getAttribute("LENGTH").toString() + "',";
                insert += "'" + feature.getAttribute("DIR").toString() + "',";
                insert += "'" + feature.getAttribute("LINK_ID").toString() + "',";
                insert += "'" + feature.getAttribute("LINK_TYPE").toString() + "',";
                insert += "'" + feature.getAttribute("AB_SPEED").toString() + "',";
                insert += "'" + feature.getAttribute("AB_LANES").toString() + "',";
                insert += "'" + feature.getAttribute("AB_CAPACIT").toString() + "',";
                insert += "'" + feature.getAttribute("AB_CAPACI1").toString() + "',";
                insert += "'" + feature.getAttribute("AB_TIME").toString() + "',";
                insert += "'" + feature.getAttribute("BA_SPEED").toString() + "',";
                insert += "'" + feature.getAttribute("BA_LANES").toString() + "',";
                insert += "'" + feature.getAttribute("BA_CAPACIT").toString() + "',";
                insert += "'" + feature.getAttribute("BA_CAPACI1").toString() + "',";
                insert += "'" + feature.getAttribute("BA_TIME").toString() + "',";
                insert += "'" + feature.getAttribute("ROADNAME") + "',";
                String geom = feature.getAttribute("the_geom").toString();
                geom = getString(geom);
                insert += "'" + geom + "'";
                insert += ");";
                System.out.println(insert);
            }*/
        }
        if(type.equals("model_big")){//宏观模型数据
            String insert = "insert into traffic_model_big (`model_id`,`length`,`dir`,`link_id`,`link_type`,`ab_speed`,`ab_lanes`,`ab_capacit`,`ab_capaci1`,`ab_time`" +
                    ",`ba_speed`,`ba_lanes`,`ba_capacit`,`ba_capaci1`,`ba_time`,`roadname`) values (";

            insert += "'3',";
            insert += "'" + feature.getAttribute("LENGTH").toString() + "',";
            insert += "'" + feature.getAttribute("DIR").toString() + "',";
            insert += "'" + feature.getAttribute("LINK_ID") + "',";
            insert += "'" + feature.getAttribute("LINK_TYPE") + "',";
            insert += "'" + feature.getAttribute("AB_SPEED") + "',";
            insert += "'" + feature.getAttribute("AB_LANES") + "',";
            insert += "'" + feature.getAttribute("AB_CAPACIT") + "',";
            insert += "'" + feature.getAttribute("AB_CAPACI1") + "',";
            insert += "'" + feature.getAttribute("AB_TIME") + "',";
            insert += "'" + feature.getAttribute("BA_SPEED") + "',";
            insert += "'" + feature.getAttribute("BA_LANES") + "',";
            insert += "'" + feature.getAttribute("BA_CAPACIT") + "',";
            insert += "'" + feature.getAttribute("BA_CAPACI1") + "',";
            insert += "'" + feature.getAttribute("BA_TIME") + "',";
            insert += "'" + feature.getAttribute("ROADNAME") + "',";

            System.out.println(insert);



        }
        if(type.equals("model_intersection")){//模型路口数据
            String insert = "insert into traffic_model_intersection (`geom`,`name`,`lon`,`lat`,`cross_id`) values (";
            String geom = feature.getAttribute("the_geom").toString();
            geom = getString(geom);
            insert += "'" + geom + "',";
            insert += "'',";
            insert += "'" + feature.getAttribute("X").toString() + "',";
            insert += "'" + feature.getAttribute("Y").toString() + "',";
            insert += "'" + feature.getAttribute("CROSSID") + "'";
            insert += ");";
            System.out.println(insert);


        }
        if(type.equals("r_polyline")){//图层路段数据
            String insert = "insert into traffic_layer_road (`geom`,`osm_id`,`name`,`snode`,`tnode`,`content`) values (";
            String geo = feature.getAttribute("the_geom").toString();
            geo = getString(geo);
            insert += "'" + geo + "',";
            insert += "'" + feature.getAttribute("OSM_ID").toString() + "',";
            insert += "'" + feature.getAttribute("NAME").toString() + "',";
            insert += "'" + feature.getAttribute("SNODE").toString() + "',";
            insert += "'" + feature.getAttribute("TNODE") + "',";
            insert += "'" + feature.getAttribute("备注") + "'";
            insert += ");";
            System.out.println(insert);
        }
        if(type.equals("c_point")) {//图层路口数据
            String insert = "insert into traffic_layer_point (`geom`,`cross_id`,`name`,`content`) values (";
            int size = 1;
            for (Object o : list) {
                String s = o.toString();
                s = getString(s);
                insert += "'" + s;
                if (size != list.size()) {
                    insert += "',";
                } else {
                    insert += "'";
                }
                size++;
            }
            insert += ");";
            System.out.println(insert);
        }
        if (type.equals("Layer_point")){//图层路口数据
            String insert = "insert into traffic_layer_point (`geom`,`cross_id`,`name`,`content`,`type`) values (";
            String geo = feature.getAttribute("the_geom").toString();
            geo = getString(geo);
            insert += "'" + geo + "',";
            insert += "'" + feature.getAttribute("CROSSID").toString() + "',";
            insert += "'',";
            insert += "'',";
            insert += "'" + feature.getAttribute("TYPE") + "'";
            insert += ");";
            System.out.println(insert);
        }

    }

    private static String getString(String s){
        if(s.contains("POINT (")){
            s = s.substring(7, s.length() - 1);
            String[] arr = s.split(" ");
            s = arr[0] + "," + arr[1];
        }
        if(s.contains("MULTILINESTRING")){
            s = s.substring(18, s.length() - 2);
            String[] arr1 = s.trim().split(", ");
            String geom = "";
            int i = 1;
            for(String a : arr1) {
                String[] arr = a.trim().split(" ");
                if(i == arr1.length) {
                    geom += arr[0] + "," + arr[1];
                }else{
                    geom += arr[0] + "," + arr[1] + ";";
                }
                i++;
            }
            s = geom;
        }
        return s;
    }
}
