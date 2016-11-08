package com.tt.traffic.service;

import com.tt.traffic.domain.model.User;

import java.util.List;

/**
 * Created by admin on 2015/10/14.
 */
public interface UserService {

    List<User> getUserList(User user);
    /**
     * 登录
     * @param user
     * @return
     */
    User loginInfo(String loginname, String password);
    /**
     * 注册
     * @param user
     */
    Boolean saveUser(User user);
    /**
     * 查询用户总数
     * @return
     */
    int selectCount();
    /**
     * 导出用户信息
     * @return
     */
    List<User> ExportExcalUser();
    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteUser(Integer id);

    /**
     * 按用户名查找用户
     * @return
     */
    User getUsetByName(String loginname);
}
