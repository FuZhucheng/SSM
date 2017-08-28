package com.fuzhu.serviceImpl;

import com.fuzhu.dao.UserDao;
import com.fuzhu.entity.User;
import com.fuzhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/2.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User login(String account) {
        User user = userDao.login(account);
        return user;
    }
    @Override
    public User queryById(long id) {
        User user = userDao.queryById(id);
        return user;
    }

    @Override
    public List<User> queryAll() {
        List<User> userList=userDao.queryAll();
        return userList;
    }

    @Override
    public int deleteUser(long id) {
        int t = userDao.deleteUser(id);
        return t;
    }

    @Override
    public int insertUser(User user) {
        int t = userDao.insertUser(user);
        return t;
    }

    @Override
    public List<User> queryTopN() {
        List<User>userList =userDao.queryTopN();
        return userList;
    }

    @Override
    public List<User> findUserByProvince(String province) {
        List<User> userList = userDao.findUserByProvince(province);
        System.out.println(userList.toString());
        return userList;
    }
}
