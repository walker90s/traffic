package com.tt.traffic.dao;

import com.tt.traffic.domain.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    void insert(User record);

    void insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> getUserList(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 导出用户信息
     * @return
     */
    List<User> ExportExcalUser();
    /**
     * 查询用户总数
     * @return
     */
    int selectCount();

    /**
     * 登录
     * @param user
     * @return
     */
    User loginInfo(User user);

    /**
     * 注册
     * @param user
     */
    int insertUser(User user);
    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Integer id);
}