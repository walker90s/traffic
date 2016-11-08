package com.tt.traffic.service.impl;

import com.tt.traffic.dao.UserMapper;
import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.UserService;
import com.tt.traffic.common.util.SHA1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2015/10/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    public List<User> getUserList(User user) {
    	
        return userMapper.getUserList(user);
    }
    public User getUsetByName(String loginname){
        User user = new User();
        user.setLoginname(loginname);
        return userMapper.loginInfo(user);
    }
    public User loginInfo(String loginname,String password){
        User user = new User();
        user.setLoginname(loginname);
        user.setPassword(new SHA1().getDigestOfString(password.getBytes()));
        return userMapper.loginInfo(user);
    }
    public Boolean saveUser(User user){
        String password = user.getPassword();
        user.setPassword(new SHA1().getDigestOfString(password.getBytes()));
        if(userMapper.insertUser(user)!=0){
            return true;
        }
        return false;
    }

	public int selectCount() {
		
		return userMapper.selectCount();
	}

	public List<User> ExportExcalUser() {
		
		return userMapper.ExportExcalUser();
	}

	public boolean deleteUser(Integer id) {
		if(userMapper.deleteUser(id)!=0){
			return true;
		}else{
			return false;
		}
	}
}
