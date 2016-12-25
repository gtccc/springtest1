package com.gtc.dao;

import com.gtc.entity.User;

import java.util.List;

/**
 * Created by gtc on 2016/12/21.
 */
public interface UserDao {

    /**
     * 保存用户
     *
     * @param user 保存的用户信息
     * @return 1 success ,0 fail
     */
    public int save(User user);

    /**
     * 显示所有用户
     *
     * @return 用户的列表
     */
    public List<User> listAllUser();

    /**
     * 显示所有用户信息
     *
     * @param id 查询莫个id的用户信息
     * @return 用户id，anme，sex
     */
    public String findUser(int id);
}
