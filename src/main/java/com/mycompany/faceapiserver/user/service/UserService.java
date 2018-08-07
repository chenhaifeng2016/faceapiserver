package com.mycompany.faceapiserver.user.service;

import com.mycompany.faceapiserver.user.dao.UserDao;
import com.mycompany.faceapiserver.user.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserDao userDao;


    public int insertUser(User user) throws DataAccessException {
        return userDao.insertUser(user);
    }


    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public User login(String userid_phone_email, String password) {
        return userDao.login(userid_phone_email, password);
    }
}
