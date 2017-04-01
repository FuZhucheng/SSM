package com.fuzhu.dao;

import com.fuzhu.entity.User;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
public interface UserDao {
    //根据id查人
    User queryById(long id);
    //查询所有用户
    List<User> queryAll();
    //删除用户
    int deleteUser(long id);

}
