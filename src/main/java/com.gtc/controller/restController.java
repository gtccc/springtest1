package com.gtc.controller;

import com.gtc.dao.UserDao;
import com.gtc.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gtc on 2016/12/23.
 */
@Controller
@RequestMapping(value = "/rest")
public class restController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUsers(@PathVariable("id") int id) {
        return userDao.findUser(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return userDao.listAllUser();
    }

    @RequestMapping(value = "/addusers/{id}/{name}/{sex}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String addUser(@PathVariable("id") int id, @PathVariable("name") String name,
                          @PathVariable("sex") String sex) {
        User user = new User(id, name, sex);
        int a = userDao.save(user);
        if (a != 0) {
            return "fail";
        } else {
            return "success" + user;
        }

    }

}
