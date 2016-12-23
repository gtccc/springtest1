package com.gtc.dao;

import com.gtc.entity.User;

import java.util.List;

/**
 * Created by gtc on 2016/12/21.
 */
public interface UserDao {
    public int save(User user);

    public List<User> listAllUser();

    public String findUser(int id);
}
