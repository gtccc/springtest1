package com.gtc;

import com.gtc.dao.UserDao;
import com.gtc.entity.User;
import com.gtc.util.csvUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gtc on 2016/12/20.
 */
@Controller
public class controller {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(){
        return userDao.listAllUser();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String getUser(@org.springframework.web.bind.annotation.RequestBody User user){
        int a=2;
        try{
            a = userDao.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return a+"";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        User user = new User(1,"s","b");
        int a = (Integer)sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().flush();
        return a+"";
    }

    @RequestMapping(value = "csv", method = RequestMethod.GET)
    @ResponseBody
    public String findBuyCSV( HttpServletResponse response) {
        List<Map<String, Object>> dataList=null;
        String sTitle = "id,name,sex";
        String fName = "gtctest";
        String mapKey = "id,name,sex";
        dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        List<User> list = userDao.listAllUser();
        for (int i=0; i<list.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("id", list.get(i).getId());
            map.put("name", list.get(i).getName());
            map.put("sex", list.get(i).getSex());
            dataList.add(map);
        }

        try {
            final OutputStream os = response.getOutputStream();
            csvUtil.responseSetProperties(fName, response);
            csvUtil.doExport(dataList, sTitle, mapKey, os);
            return null;

        } catch (Exception e) {
           System.out.print(e);

        }
        return ("数据导出出错");
    }


}
