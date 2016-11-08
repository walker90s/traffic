package com.tt.traffic.shptools;

/**
 * Created by admin on 2015/11/5.
 */
public class Test {
    public static void  main(String[] agrs){
        for(int i = 6; i <= 589; i++ ){
            String insert = "insert into traffic_model_relationship (`model_id`, `relationship_type`, `relationship_value`, `orders`) " +
                    "values ('1','road','"+ i + "','" + (i-5)+"');";
            System.out.println(insert);
        }
    }
}
