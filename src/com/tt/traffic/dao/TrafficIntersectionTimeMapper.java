package com.tt.traffic.dao;

import com.tt.traffic.domain.model.TrafficIntersectionTime;
import java.util.List;

public interface TrafficIntersectionTimeMapper {
    /**
     * 根据crossid查询配时信息
     * @param crossid
     * @return
     */
    List<TrafficIntersectionTime> getTimeListByCrossid(Integer crossid);

    /**
     * 根据crossid查询配时信息，根据phase分组
     * @param crossid
     * @return
     */
    List<TrafficIntersectionTime> getTimeListByCrossidGroupByPhase(Integer crossid);

    int deleteByPrimaryKey(Integer id);

    int insert(TrafficIntersectionTime record);

    int insertSelective(TrafficIntersectionTime record);

    TrafficIntersectionTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrafficIntersectionTime record);

    int updateByPrimaryKey(TrafficIntersectionTime record);
}