package com.gtc.dao;

import com.gtc.entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gtc on 2016/12/22.
 */
@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int save(User user) throws HibernateError{
        int a = (Integer)sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().flush();
        return a;
    }

    @Override
    public List<User> listAllUser(){

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return criteria.list();
    }

    @Override
    public String findUser(int id){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        for( int i=0; i<criteria.list().size(); i++){
            User us = (User)criteria.list().get(i);
            if (us.getId() == id){
                return us.toString();
            }
        }
        return "Not found";
    }

}
